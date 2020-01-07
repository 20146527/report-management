$(document).ready(function () {
    $("#filer_input").filer({
        limit: null,
        maxSize: null,
        extensions: ["xlsx"],
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
            $("#btn-upload-steno").css({"display": "none"});
        }, beforeRender: function () {
            $("#btn-upload-steno").css({"display": "none"});
        }, onFileCheck: function () {
            $("#btn-upload-steno").css({"display": "block"});
        }

    });

    $("#table-list-rule").DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });
});

function confirmAction(type, id) {
    let text = '';
    if (type === 1) {
        text = 'Bạn có muốn thay đổi bộ quy tắc mặc định?';
    } else {
        text = 'Bạn có muốn xóa bộ quy tắc này?';
    }
    swal({
            title: "Thông báo !",
            text: text,
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Có, tiếp tục !!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true,
        },
        function () {
            if (type === 1) {
                window.location.href = '/typing-rules.html?type=default&id=' + id;
            } else {
                window.location.href = '/typing-rules.html?type=delete&id=' + id;
            }
        });
}

