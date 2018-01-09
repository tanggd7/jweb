(function ($) {

    $.common = {

        /**
         * 判断obj是为空
         *
         * @param obj  字符串
         * @returns {Boolean}
         */
        isEmpty: function (obj) {
            if (obj == '' || obj == null || typeof(obj) == 'undefined') {
                return true;
            } else {
                return false;
            }
        },

        /**
         * 判断obj是不为空
         *
         * @param obj  字符串
         * @returns {Boolean}
         */
        isNotEmpty: function (obj) {
            if (obj == '' || obj == null || typeof(obj) == 'undefined') {
                return false;
            } else {
                return true;
            }
        },

        /**
         * ajax异步传输
         */
        ajax: function (options) {
            var p = $.extend({
                // url
                // data
                // func 回调方法
                isMask: true // 是否有遮罩层
            }, options || {});

            $.ajax({
                async: true,
                url: p.url,
                type: 'POST',
                // dataType : 'json',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(p.data),
                success: function (data) {
                    if ((data.startWith('{') && data.endWith('}')) || (data.startWith('[') && data.endWith(']'))) {
                        p.func(eval('data=' + data));
                    } else {
                        p.func(data);
                    }
                },
                error: function (data) {
                    alert('回调出错');
                    // $.common.showMesg.danger('回调出错！');
                },
                beforeSend: function () {
                    // 请求前的处理
                },
                complete: function () {
                    // 请求完成的处理
                }
            });
        },

        /**
         * 生成grid表格
         *
         * @param options
         */
        dataTable: function (options) {
            /*
                exp:
                'columns': [
                    {'data': 'first_name'},
                    {'data': 'last_name'},
                    {'data': 'position'},
                    {'data': 'office'},
                    {'data': 'start_date'},
                    {'data': 'salary'}
                ]
             */
            var p = $.extend({
                // id       标签ID
                // url      地址
                // columns  列表
                height: 400 // 表格内容高度
            }, options || {});

            if ($.common.isEmpty(p.id) || $.common.isEmpty(p.url) || $.common.isEmpty(p.columns)) {
                alert('请输入id，url，columns！');
                return;
            }

            $('#' + p.id).DataTable({
                ajax: {
                    url: p.url,
                    type: 'POST',
                    data: function (d) {
                    }
                },
                columns: p.columns,
                searching: false,
                ordering: false,
                scrollX: true,
                scrollY: p.height,
                scrollCollapse: true,
                paging: true,
                pagingType: 'full_numbers',
                processing: true,
                serverSide: true,
                autoWidth: true,
                info: true,
                language: {
                    sProcessing: '处理中...',
                    sLengthMenu: '每页 _MENU_ 条记录',
                    sZeroRecords: '没有找到记录',
                    sInfo: '显示第 _START_ 至 _END_ 条记录，共 _TOTAL_ 条',
                    sInfoEmpty: '显示第 0 至 0 条记录，共 0 条',
                    sInfoFiltered: '(由 _MAX_ 条记录过滤)',
                    sInfoPostFix: '',
                    sSearch: '搜索:',
                    sUrl: '',
                    sEmptyTable: '表中数据为空',
                    sLoadingRecords: '载入中...',
                    sInfoThousands: ',',
                    oPaginate: {
                        sFirst: '首页',
                        sPrevious: '上页',
                        sNext: '下页',
                        sLast: '末页'
                    },
                    oAria: {
                        sSortAscending: ': 以升序排列此列',
                        sSortDescending: ': 以降序排列此列'
                    }
                }
            });
        }

    };

})(jQuery);

/**
 * 扩展String
 */
String.prototype.endWith = function (str) {
    if (str == null || str == '' || this.length == 0 || str.length > this.length) {
        return false;
    }
    if (this.substring(this.length - str.length) == str) {
        return true;
    } else {
        return false;
    }
    return true;
};

String.prototype.startWith = function (str) {
    if (str == null || str == '' || this.length == 0 || str.length > this.length) {
        return false;
    }
    if (this.substr(0, str.length) == str) {
        return true;
    } else {
        return false;
    }
    return true;
};