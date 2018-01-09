/**
 * @Author 汤国栋
 * @Date 2017-07-24 15:31
 */
var desktopObj;
$(function () {

    'use strict'

    var sideMenu; // 菜单树集合

    // 主页页面对象
    desktopObj = {

        /**
         * 加载左侧菜单栏
         */
        loadSideMenu: function () {
            $.post('desktop/getSideMenu', {}, function (data) {
                sideMenu = data;
                $.each(sideMenu, function (index, value, array) {
                    var html = '<li class="header">功能菜单</li>';
                    html += desktopObj.generateMenuHtml(value);
                    $('#desktop_sidebar_menu').append(html);
                })
            }, 'JSON');
        },

        /**
         * 生成菜单树
         *
         * @param tree          菜单树属性集合
         * @returns {string}    拼装好的HTML
         */
        generateMenuHtml: function (tree) {
            var html = '';
            if (typeof(tree.resourceTrees) == 'undefined' || tree.resourceTrees == null || tree.resourceTrees.length == 0) {
                html += '<li>';
                html += '   <a href="javascript:;" onclick="desktopObj.loadContentBody(' + tree.id + ', ' + tree.parent_id + ');">';
                html += '       <i class="' + ($.common.isEmpty(tree.icon) ? 'fa fa-circle-o' : tree.icon) + '"></i>' + tree.name;
                html += '   </a>';
                html += '</li>';
            } else {
                html += '<li class="treeview">';
                html += '   <a href="#">';
                html += '       <i class="' + ($.common.isEmpty(tree.icon) ? 'fa fa-circle-o' : tree.icon) + '"></i>';
                html += '       <span>' + tree.name + '</span>';
                html += '       <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>';
                html += '   </a>';
                html += '   <ul class="treeview-menu">';
                for (var i = 0; i < tree.resourceTrees.length; i++) {
                    html += desktopObj.generateMenuHtml(tree.resourceTrees[i]);
                }
                html += '   </ul>';
                html += '</li>';
            }
            return html;
        },

        /**
         * 加载主体内容（包括主标题，描述，面包屑）
         *
         * @param treeId        需要显示菜单的菜单ID。
         * @param parent_id     父菜单ID，用于显示面包屑。
         */
        loadContentBody: function (treeId, parent_id) {
            // 加载标题和描述
            var tree = desktopObj.getMenuResource(treeId);
            if (tree != null) {
                $('#desktop_content_header').empty();
                $('#desktop_content_header_description').empty();
                $('#desktop_content_header').text(tree.name);
                $('#desktop_content_header_description').text(tree.description);
            }
            // 加载面包屑
            var trees = desktopObj.getMenuParentResource(parent_id);
            var html = '<li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>';
            for (var i = trees.length - 1; i >= 0; i--) {
                html += '<li>' + trees[i] + '</li>';
            }
            html += '<li class="active">' + tree.name + '</li>';
            $('#desktop_content_header_breadcrumb').empty();
            $('#desktop_content_header_breadcrumb').html(html);

            // 加载主体
            $('#desktop_content_body').empty();
            $('#desktop_content_body').load(tree.url, {}, function () {
            });
        },

        getMenuParentResource: function (parent_id) {
            var trees = [];
            var treeId = parent_id;
            while ($.common.isNotEmpty(treeId)) {
                var tree = desktopObj.getMenuResource(treeId);
                if (tree == null) {
                    treeId = '';
                } else {
                    trees.push(tree.name);
                    treeId = tree.parent_id;
                }
            }
            return trees;
        },

        /**
         * 获取菜单对象
         *
         * @param treeId
         * @returns {*}
         */
        getMenuResource: function (treeId) {
            var tree = null;
            $.each(sideMenu, function (index, value, array) {
                tree = desktopObj.readResource(value, treeId);
            });
            return tree;
        },

        /**
         * 循环读取资源
         *
         * @param tree
         * @param treeId
         * @returns {*}
         */
        readResource: function (tree, treeId) {
            var resultTree = null;
            if (treeId == tree.id) {
                return tree;
            }
            if (typeof(tree.resourceTrees) != 'undefined' && tree.resourceTrees != null && tree.resourceTrees.length > 0) {
                for (var i = 0; i < tree.resourceTrees.length; i++) {
                    var t = desktopObj.readResource(tree.resourceTrees[i], treeId);
                    if (t != null) {
                        resultTree = t
                        return resultTree;
                    }
                }
            }
            return resultTree;
        }

    };

    desktopObj.loadSideMenu();

})