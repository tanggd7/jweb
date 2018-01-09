var mainObj;
$(function () {

    mainObj = {

        loadSearch: function (options) {
            var row = parseInt(options.length / 6);
            if (options.length % 6 > 0) {
                row++
            }
            var index = 0;
            var html = '<div class="form-group">';
            for (var i = 0; i < row; i++) {
                html += '<div class="row">';
                for (var j = 0; j < 6; j++) {
                    var option = options[index];
                    html += '   <div class="col-xs-2">';
                    html += '       <label for="' + option.id + '">' + option.name + '</label>';
                    html += $.fastForm.generate(option.tag, option.args, option.id, option.id, 'form-control');
                    html += '   </div>';
                    index++;
                    if (typeof (options[index]) == 'undefined') {
                        break;
                    }
                }
                html += '</div>';
            }
            html += '</div>';
            return html;
        },

        loadButton: function (buttons) {
            var html = '<div class="form-group">';
            for (var i = 0; i < buttons.length; i++) {
                html += '<button type="button" class="btn ' + ($.common.isEmpty(buttons[i].cls) ? 'btn-primary' : buttons[i].cls) + '" onclick="' + buttons[i].event + '">';
                html += '   <i class="fa ' + buttons[i].icon + '"></i> ' + buttons[i].name;
                html += '</button>';
            }
            html += '</div>';
            return html;
        },

        loadGrid: function (columns) {
            var newColumns = [];
            var td = '';
            $.each(columns, function (index, value, array) {
                var map = {};
                map.data = value.data;
                newColumns.push(map);
                td += '<th ' + ($.common.isEmpty(value.width) ? '' : 'width="' + value.width + '"') + '">' + value.title + '</th>';
            });
            var html = '<thead>';
            html += '<tr>';
            html += td;
            html += '</tr>';
            html += '</thead>';
            html += '<tfoot>';
            html += '<tr>';
            html += td;
            html += '</tr>';
            html += '</tfoot>';
            $('#desktop_content_body_dataTable').append(html);

            $.common.dataTable({
                id: 'desktop_content_body_dataTable',
                url: url + '/getDateTable',
                height: 100,
                columns: newColumns
            })
        }

    }

    var resfile = $('#main_page_resfile').val();
    var url = $('#main_page_url').val();

    $.getJSON('../json/' + resfile + '.json', function (data) {
        var options = data.options;
        var buttons = data.buttons;
        var columns = data.columns;

        var html = '';
        html += mainObj.loadSearch(options);
        html += '<div class="form-group">';
        html += '   <button type="button" class="btn btn-primary" onclick=""><i class="fa fa-search"></i> 查询</button>';
        html += '</div>';
        html += mainObj.loadButton(buttons);
        $('#desktop_content_body_form').append(html);
        mainObj.loadGrid(columns);
        $.getScript('../script/' + resfile + '.js');
    });

})