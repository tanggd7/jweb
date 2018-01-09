(function ($) {

    $.fastForm = {

        generate: function (tag, args, id, name, cls, value) {
            if (tag == 'input') {
                return $.fastForm.input(args, id, name, cls, value);
            } else if (tag == 'select') {
                return $.fastForm.select(args, id, name, cls, value);
            } else {

            }
        },

        input: function (args, id, name, cls, value) {
            var html = '<input';
            if ($.common.isNotEmpty(args)) {
                html += ' type="' + args + '"';
            }
            if ($.common.isNotEmpty(id)) {
                html += ' id="' + id + '"';
            }
            if ($.common.isNotEmpty(name)) {
                html += ' name="' + name + '"';
            }
            if ($.common.isNotEmpty(cls)) {
                html += ' class="' + cls + '"';
            }
            if ($.common.isNotEmpty(value)) {
                html += ' value="' + value + '"';
            }
            html += '/>';
            return html;
        },

        select: function (args, id, name, cls, value) {
            var html = '<select';
            if ($.common.isNotEmpty(id)) {
                html += ' id="' + id + '"';
            }
            if ($.common.isNotEmpty(name)) {
                html += ' name="' + name + '"';
            }
            if ($.common.isNotEmpty(cls)) {
                html += ' class="' + cls + '"';
            }
            html += '>';
            html += '<option value ="">请选择</option>';
            $.each(args, function (index, val, array) {
                if ($.common.isNotEmpty(value) && val.value == value) {
                    html += '<option value ="' + val.value + '" selected>' + val.name + '</option>';
                } else {
                    html += '<option value ="' + val.value + '">' + val.name + '</option>';
                }
            })
            html += '</select>';
            return html;
        }

    };

})(jQuery);