$(document).ready(function () {
    initTable();
});

function initTable() {
    $.ajax({
        url: "/ajax-user-edit.html",
        type: "get",
        data: {
            urlType: "ajax_list_role",
            userId: userId
        },
        success: function (data) {
            let arrJSON = typeof data != 'object' ? jQuery.parseJSON(data) : data;
            let listAllRole = arrJSON[0];
            let listUserRole = arrJSON[1];
            setTableUserRole(listUserRole);
            setTableListRole(listAllRole);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

function setTableUserRole(listUserRole) {
    let html = '<div class="card">\n' +
        '                                <div class="card-title" data-toggle="collapse" data-target="#table-user-role" data-tooltip="Nhấn vào để Ẩn/Hiển thị dữ liệu">\n' +
        '                                    <h4 class="text-center"style="display: block !important;">Danh sách vai trò đã thêm</h4>\n' +
        '                                </div>\n' +
        '                                <div class="card-body">\n' +
        '                                    <table class="table table-striped m-b-20" id="table-user-role">\n' +
        '                                        <thead>\n' +
        '                                        <tr>\n' +
        '                                            <th>#</th>\n' +
        '                                            <th>Tên</th>\n' +
        '                                            <th>Mô tả</th>\n' +
        '                                            <th>Hành động</th>\n' +
        '                                        </tr>\n' +
        '                                        </thead>\n' +
        '                                        <tbody>\n';
    $.each(listUserRole, function (i, item) {
        html += '<tr>\n' +
            '                                            <td>'+ i+1 +'</td>\n' +
            '                                            <td>'+item.roleDto.roleName+'</td>\n' +
            '                                            <td>'+item.roleDto.roleDescription+'</td>\n' +
            '                                            <td>\n' +
            '                                                <button type="button" id="'+item.userRoleId+'" class="btn btn-danger sweet-delete" data-tooltip="Xóa Quyền khỏi người dùng">\n' +
            '                                                    <i class="fa fa-trash-o" aria-hidden="true"></i>\n' +
            '                                                    <span>Xóa</span>\n' +
            '                                                </button>\n' +
            '                                            </td>\n' +
            '                                        </tr>';
    });

    html += '                                    </tbody>\n' +
        '                                    </table>\n' +
        '                                </div>\n' +
        '                            </div>';
    $("#panel-user-role").empty();
    $("#panel-user-role").html(html);
    $("#table-user-role").DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });
}

function setTableListRole(listAllRole) {
    let html = ' <div class="card">\n' +
        '                                <div class="card-title" data-toggle="collapse" data-target="#table-list-role" data-tooltip="Nhấn vào để Ẩn/Hiển thị dữ liệu">\n' +
        '                                    <h4 class="text-center" style="display: block !important;">Danh sách tất cả vai trò</h4>\n' +
        '                                </div>\n' +
        '                                <div class="card-body">\n' +
        '                                    <table class="table table-striped" id="table-list-role">\n' +
        '                                        <thead>\n' +
        '                                        <tr>\n' +
        '                                            <th>#</th>\n' +
        '                                            <th>Tên</th>\n' +
        '                                            <th>Mô tả</th>\n' +
        '                                            <th>Hành động</th>\n' +
        '                                        </tr>\n' +
        '                                        </thead>\n' +
        '                                        <tbody>\n';
    $.each(listAllRole, function (i, item) {
        html += '<tr>\n' +
            '                                            <td>'+ i+1 +'</td>\n' +
            '                                            <td>'+item.roleName+'</td>\n' +
            '                                            <td>'+item.roleDescription+'</td>\n' +
            '                                            <td>\n' +
            '                                                <button type="button" id="'+item.roleId+'" class="btn btn-success sweet-confirm" data-tooltip="Thêm Quyền cho người dùng">\n' +
            '                                                    <i class="fa fa-plus" aria-hidden="true"></i>\n' +
            '                                                    <span>Thêm</span>\n' +
            '                                                </button>\n' +
            '                                            </td>\n' +
            '                                        </tr>';
    });

    html += '                                    </tbody>\n' +
        '                                    </table>\n' +
        '                                </div>\n' +
        '                            </div>';
    html += '<script src="/template/js/roles/role/add-remove-role.js"></script>';
    $("#panel-list-role").empty();
    $("#panel-list-role").html(html);
    $("#table-list-role").DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });
}