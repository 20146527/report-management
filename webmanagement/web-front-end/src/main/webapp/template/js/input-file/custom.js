let contentFile = '';
let option = 1;
let type = 1;
$(document).ready(function () {

    //Example 2
    $("#filer_input").filer({
        limit: 1,
        maxSize: null,
        extensions: ["txt"],
        changeInput: '<div class="jFiler-input-dragDrop"><div class="jFiler-input-inner"><div class="jFiler-input-icon"><i class="icon-jfi-cloud-up-o"></i></div><div class="jFiler-input-text"><h3>Vui lòng chọn file</h3> <span style="display:inline-block; margin: 15px 0"></span></div><a class="jFiler-input-choose-btn blue">Chọn file</a></div></div>',
        showThumbs: true,
        theme: "dragdropbox",
        templates: {
            box: '<ul class="jFiler-items-list jFiler-items-grid"></ul>',
            item: '<li class="jFiler-item">\
						<div class="jFiler-item-container">\
							<div class="jFiler-item-inner">\
								<div class="jFiler-item-thumb">\
									<div class="jFiler-item-status"></div>\
									<div class="jFiler-item-thumb-overlay">\
										<div class="jFiler-item-info">\
											<div style="display:table-cell;vertical-align: middle;">\
												<span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name}}</b></span>\
												<span class="jFiler-item-others">{{fi-size2}}</span>\
											</div>\
										</div>\
									</div>\
									{{fi-image}}\
								</div>\
								<div class="jFiler-item-assets jFiler-row">\
									<ul class="list-inline pull-left">\
										<li>{{fi-progressBar}}</li>\
									</ul>\
									<ul class="list-inline pull-right">\
										<li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
									</ul>\
								</div>\
							</div>\
						</div>\
					</li>',
            itemAppend: '<li class="jFiler-item">\
							<div class="jFiler-item-container">\
								<div class="jFiler-item-inner">\
									<div class="jFiler-item-thumb">\
										<div class="jFiler-item-status"></div>\
										<div class="jFiler-item-thumb-overlay">\
											<div class="jFiler-item-info">\
												<div style="display:table-cell;vertical-align: middle;">\
													<span class="jFiler-item-title"><b title="{{fi-name}}">{{fi-name}}</b></span>\
													<span class="jFiler-item-others">{{fi-size2}}</span>\
												</div>\
											</div>\
										</div>\
										{{fi-image}}\
									</div>\
									<div class="jFiler-item-assets jFiler-row">\
										<ul class="list-inline pull-left">\
											<li><span class="jFiler-item-others">{{fi-icon}}</span></li>\
										</ul>\
										<ul class="list-inline pull-right">\
											<li><a class="icon-jfi-trash jFiler-item-trash-action"></a></li>\
										</ul>\
									</div>\
								</div>\
							</div>\
						</li>',
            progressBar: '<div class="bar"></div>',
            itemAppendToEnd: false,
            canvasImage: true,
            removeConfirmation: true,
            _selectors: {
                list: '.jFiler-items-list',
                item: '.jFiler-item',
                progressBar: '.bar',
                remove: '.jFiler-item-trash-action'
            }
        },
        captions: {
            button: "Chọn file",
            feedback: "Chọn file để upload",
            drop: "Thả file ở đây",
            removeConfirmation: "Bạn có muốn loại bỏ file?",
            errors: {
                filesLimit: "Chỉ {{fi-limit}} files được cho phép.",
                filesType: "Chỉ file định dạng txt được chấp nhận",
                folderUpload: "Bạn không được phép upload đến thư mục này"
            }
        }, onEmpty: function () {
            $("#convert").css({"display": "none"})
        }, beforeRender: function () {
            $("#convert").css({"display": "none"})
        }, onFileCheck: function () {
            let input = $('#filer_input');
            let fileReader = new FileReader();
            fileReader.onload = function (fileLoadedEvent) {
                contentFile = fileLoadedEvent.target.result;
                $("#convert").css({"display": "block"})
            };
            fileReader.readAsText(input[0].files[0], "UTF-8");
        }

    });


});

$('#convertSteno').on('change', function () {
    let id_selected = $(this).children(":selected").attr("id");
    if (id_selected === 'convert-1')
        option = 1;
    else if (id_selected === 'convert-2')
        option = 2;
    else
        option = 3;
});

$('#type-file').on('change', function () {
    let id_selected = $(this).children(":selected").attr("id");
    if (id_selected === 'type-1')
        type = 1;
    else
        type = 2;
});


function fnClickConvert() {
    $.ajax({
        url: "/steno-convert.html",
        type: "post",
        data: {
            type: "setContentConvert",
            content: contentFile,
            option: option,
            typeFile: type
        },
        success: function () {
            window.location = "/steno-display-convert.html";
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}
