let option = 0;
let content = '';

let option_html = ' <label for="changeTyping">Chọn chế độ gõ</label>\n' +
    '                            <select class="form-control" id="changeTyping">\n' +
    '                                <option id="select_one">Gõ tiếng việt</option>\n' +
    '                                <option id="select_two">Tốc ký hiển thị tức thời</option>\n' +
    '                            </select>';

let html_1 = '<div class="row btn">\n' +
    '                                <button class="btn btn-info" onclick="triggerUndo()"><i class="fa fa-undo"\n' +
    '                                                                                        aria-hidden="true"></i> Undo\n' +
    '                                </button>\n' +
    '                                <button class="btn btn-info" onclick="triggerRedo()"><i class="fa fa-repeat"\n' +
    '                                                                                        aria-hidden="true"></i> Redo\n' +
    '                                </button>\n' +
    '\n' +
    '                                <label class="switch control-label" for="insMode" data-tooltip="Bật/Tắt chế độ chèn từ">\n' +
    '                                    <input type="checkbox" name="insMode" id="insMode" onclick="cB(this.checked, $(\'#txtEditor\'))">\n' +
    '                                    <span class="slider round"></span>\n' +
    '                                </label>\n' +
    '                            </div>\n' +
    '                            <label for="txtEditor"></label>\n' +
    '                            <textarea class="form-control z-depth-1" rows="10" id="txtEditor"\n' +
    '                                      onkeydown="checkArrows(event.keyCode, this)"></textarea>' +
    '<script src="../../template/js/steno/typing/autocompleteTextarea.js"></script>';


let html_2 = '<div class="row shorthand" id="shorthand">\n' +
    '                                        <div class="col-6">\n' +
    '                                            <div class="form-group">\n' +
    '                                                <label for="output">Văn bản:</label>\n' +
    '                                                <textarea class="form-control  z-depth-1" rows="10" wrap="off" id="output" style="overflow-y: scroll"></textarea>\n' +
    '                                            </div>\n' +
    '                                        </div>\n' +
    '\n' +
    '                                        <div class="col-6">\n' +
    '                                            <div class="form-group">\n' +
    '                                                <label for="verticalNotes">Tốc ký:</label>\n' +
    '                                                <textarea class="form-control  z-depth-1" rows="10" wrap="off" id="verticalNotes"></textarea>\n' +
    '                                            </div>\n' +
    '                                        </div>\n' +
    '\n' +
    '                                    </div> <script src="../../template/js/steno/typing/ploverdemo.js"></script>\n';


$('#changeTyping').on('change', function () {
    let id_selected = $(this).children(":selected").attr("id");
    let name_typing = $("#name-typing");
    let parent = $(".parent");

    changeData();

    switch (id_selected) {
        case 'select_one':
            name_typing.text(" Chế độ gõ tiếng Việt");
            parent.empty();
            // select_option_typing.html(html);
            parent.html(html_1);
            option = 0;
            break;
        case 'select_two':
            name_typing.text(" Chế độ tốc ký hiển thị tức thời");
            parent.empty();
            // select_option_typing.html(html);
            parent.html(html_2);
            option = 1;
            break;
    }
});

function fnCheckShorthand() {
    let shorthand = $("#shorthand");
    if (shorthand.length > 0) {
        if ($(".shorthand").length > 0) {
            shorthand.removeClass('shorthand');
        } else {
            shorthand.addClass('shorthand');
        }
    }
}

function changeData() {
    let data_content = $("#data-content");
    let output = $("#output");
    let data = data_content.froalaEditor('html.get');
    switch (option) {
        case 0:
            content = $("#txtEditor").val().replace(/(?:\r\n|\r|\n)/g, '<br />');
            if (content !== "") {
                data_content.froalaEditor('html.set', data + " " + content);
            }
            break;
        case 1:
            content = output.val();
            if (content !== "") {
                data_content.froalaEditor('html.set', data + " " + content);
            }
            break;
    }
}

function fnOnDone() {
    changeData();
    let name_typing = $("#name-typing");
    name_typing.text(" Chế độ gõ tiếng Việt");
    let parent = $(".parent");
    $('#changeTyping').prop('selectedIndex', 0);
    parent.empty();
    parent.html(html_1);
    $("#li-tab-2").trigger('click');
    $("#tab-2").trigger('click');
}

function fnOnSave(name) {
    let data_content = $("#data-content");
    let data = data_content.froalaEditor('html.get');
    if (data.trim() == null || data.trim() === "") {
        alert("Chưa có dữ liệu để lưu!")
    } else {
        $("#data-typing").val(data_content.froalaEditor('html.get'));
        $('#myModal').modal('toggle');
        if (name !== '') {
            let input = $('#name-file');
            let d = new Date();
            let n = d.getTime();
            input.val(name + "_" + n);
            $("#data-name").val(name + "_" + n);
        }
    }
}