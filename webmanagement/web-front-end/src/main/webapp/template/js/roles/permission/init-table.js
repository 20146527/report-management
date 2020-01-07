$(document).ready(function () {
    initTable();
});

function initTable() {
    $.ajax({
        url: "/ajax-manager-permission.html",
        type: "get",
        data: {
            urlType: "url_list",
            tab: roleId
        },
        success: function (data) {
            let arrJSON = typeof data != 'object' ? jQuery.parseJSON(data) : data;
            let listAllPermission = arrJSON[0];
            let listRolePermission = arrJSON[1];
            setTablePermissionRole(listRolePermission);
            setTablePermissionList(listAllPermission);

        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}
function setTablePermissionRole(listRolePermission) {
    let html = '<div class="card">\n' +
        '                            <div class="card-title" data-toggle="collapse" data-target="#table-permission-role" data-tooltip="Nhấn vào để Ẩn/Hiển thị dữ liệu" >\n' +
        '                                <h4 class="text-center" style="display: block !important;">Danh sách quyền của vai trò</h4>\n' +
        '                            </div>\n' +
        '                            <div class="card-body">\n' +
        '                                <table class="table table-striped" id="table-permission-role">\n' +
        '                                    <thead>\n' +
        '                                    <tr>\n' +
        '                                        <th>Tên</th>\n' +
        '                                        <th>Đối tượng</th>\n' +
        '                                        <th>Mô tả</th>\n' +
        '                                        <th>Hành động</th>\n' +
        '                                    </tr>\n' +
        '                                    </thead>\n' +
        '                                    <tbody>\n';

    $.each(listRolePermission, function (i, item) {
        html += '<tr>\n' +
            '                                        <td>'+item.objectDto.nameObject+'</td>\n' +
            '                                        <td style="display: inline-block; width: 180px; white-space: nowrap; overflow: hidden !important; text-overflow: ellipsis;" title="'+item.objectDto.description+'">'+item.objectDto.description+'</td>\n' +
            '                                        <td>\n'+item.operatorDto.nameOperator+'</td>\n' +
            '                                        <td>\n' +
            '                                            <button id="'+item.rolePermissionId+'" type="button" class="btn btn-danger sweet-delete" data-tooltip="Bỏ quyền khỏi vao trò">\n' +
            '                                                <i class="fa fa-trash-o" aria-hidden="true"></i>\n' +
            '                                                <span>Xóa</span>\n' +
            '                                            </button>\n' +
            '                                        </td>\n' +
            '                                    </tr>';
    });

    html += '                            </tbody>\n' +
        '                                </table>\n' +
        '                            </div>\n' +
        '                        </div>';

    $('#panel-permission-role').empty();
    $('#panel-permission-role').html(html);
    $("#table-permission-role").DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });

}
function setTablePermissionList(listAllPermission) {
    let html = '<div class="card">\n' +
        '                            <div class="card-title" data-toggle="collapse" data-target="#table-permission-list" data-tooltip="Nhấn vào để Ẩn/Hiển thị dữ liệu" >\n' +
        '                                <h4 class="text-center" style="display: block !important;">Danh sách tất cả đối tượng</h4>\n' +
        '                            </div>\n' +
        '                    ' +
        '        <div class="card-body">\n' +
        '                                <table class="table table-striped" id="table-permission-list">\n' +
        '                                    <thead>\n' +
        '                                    <tr>\n' +
        '                                        <th>Tên</th>\n' +
        '                                        <th>Đối tượng</th>\n' +
        '                                        <th>Chọn quyền</th>\n' +
        '                                        <th>Hành động</th>\n' +
        '                                    </tr>\n' +
        '                                    </thead>\n' +
        '                                    <tbody>\n';

    $.each(listAllPermission, function (i, item) {
        html += '<tr>\n' +
            '                                        <td>'+item.nameObject+'</td>\n' +
            '                                        <td style="display: inline-block; width: 180px; white-space: nowrap; overflow: hidden !important; text-overflow: ellipsis;" title="'+item.description+'">'+item.description+'</td>\n' +
            '                                        <td\n>'+
            '                                            <select name="OperatorId" id="operator'+item.objectID+'" class="form-control">\n' +
            '                                               <option value="1">Chỉ xem</option>\n' +
            '                                               <option value="2">Xem & Thêm mới</option>\n' +
            '                                               <option value="3">Xem, Thêm & Chỉnh sửa</option>\n' +
            '                                               <option value="4">Xem, Thêm, Sửa & Xóa</option>\n' +
            '                                           </select>'+
            '                                        </td>\n' +
            '                                        <td>\n' +
            '                                            <button id="'+item.objectID+'" type="button" class="btn btn-success sweet-confirm" data-tooltip="Thêm quyền vào vai trò">\n' +
            '                                                <i class="fa fa-plus" aria-hidden="true"></i>\n' +
            '                                                <span>Thêm</span>\n' +
            '                                            </button>\n' +
            '                                        </td>\n' +
            '                                    </tr>';
    });
    html +='                                    </tbody>\n' +
        '                                </table>\n' +
        '                            </div>\n' +
        '                        </div>';
    html += '<script src="/template/js/roles/permission/add-remove-permission.js"></script>';

    $('#panel-permission-list').empty();
    $('#panel-permission-list').html(html);
    $("#table-permission-list").DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });
}