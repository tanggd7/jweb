/**
 * @Author 汤国栋
 * @Date 2017-08-03 14:23
 */
var sysUserObj;
$(function () {

    'use strict'

    sysUserObj = {

        loadGrid: function () {
            $.common.dataTable({
                id: 'sysUser_datatable',
                url: 'sysUser/getUserList',
                height: 380,
                columns: [
                    {"data": "first_name"},
                    {"data": "last_name"},
                    {"data": "position"},
                    {"data": "office"},
                    {"data": "start_date"},
                    {"data": "salary"}
                ]
            })
        }

    }

    sysUserObj.loadGrid();

})