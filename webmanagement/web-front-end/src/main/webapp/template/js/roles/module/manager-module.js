function updateDataUserModule() {

    $.ajax({
        url: "/manager-module.html",
        type: "get",
        data: {
            data: "user",
            module: id_module
        },
        success: function (data) {
            let arrJSON = typeof data != 'object' ? JSON.parse(data) : data;
            let userDto = arrJSON[0];
            let userModule = arrJSON[1];
            setDataUserModule(userDto, userModule);

        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

function setDataUserModule(userDto, userModule) {

    let html = '<div class="card">\n' +
        '\n' +
        '          <div class="card-title" data-toggle="collapse" data-target="#table-list-user-module" data-tooltip="Nhấn vào để Ẩn/Hiển thị dữ liệu" >\n' +
        '                <h4 class="text-center" style="display: block !important;">Danh sách người dùng Module</h4>\n' +
        '          </div>\n' +
        '\n' +
        '          <div class="card-body">\n' +
        '                 <table class="table table-striped" id="table-list-user-module">\n' +
        '                          <thead>\n' +
        '                                                <tr>\n' +
        '                                                    <th scope="col">STT</th>\n' +
        '                                                    <th scope="col">Tài khoản</th>\n' +
        '                                                    <th scope="col">Tên</th>\n' +
        '                                                    <th scope="col">Số điện thoại</th>\n' +
        '                                                    <th scope="col">Hành động</th>\n' +
        '                                                </tr>\n' +
        '                                                </thead>\n' +
        '                                                <tbody>';

    for (let i = 0; i < userModule.length; i++) {
        let userDto = userModule[i].userDto;
        html += '  <tr>\n' +
            '                                                        <th scope="row">' + (i + 1) + '</th>\n' +
            '                                                        <td>' + userDto.userName + '</td>\n' +
            '                                                        <td>' + userDto.fullName + '</td>\n' +
            '                                                        <td>' + userDto.phone + '</td>\n' +
            '                                                        <td>\n' +
            '                                                            <div class="sweetalert ">\n' +
            '                                                                <button id="' + userModule[i].userModuleID + '"\n' +
            '                                                                        class="btn btn-danger sweet-delete"\n' +
            '                                                                        data-tooltip="Xóa người dùng khỏi module"><i\n' +
            '                                                                        class="fa fa-trash-o" aria-hidden="true"></i>\n' +
            '                                                                    Xóa\n' +
            '                                                                </button>\n' +
            '                                                            </div>\n' +
            '                                                        </td>\n' +
            '                                                    </tr>';
    }

    html += '                 </tbody>\n' +
        '                                            </table>\n' +
        '\n' +
        '                                        </div>\n' +
        '\n' +
        '                                    </div>';

    html += ' <div class="card">\n' +
        '                                        <div class="card-title" data-toggle="collapse"\n' +
        '                                             data-target="#body-table-list-user"\n' +
        '                                             data-tooltip="Nhấn vào để Ẩn/Hiển thị dữ liệu">\n' +
        '                                            <h4 class="text-center" style="display: block !important;">Danh sách người dùng hệ thống</h4>\n' +
        '                                        </div>\n' +
        '\n' +
        '                                        <div class="card-body collapse show" id="body-table-list-user">\n' +
        '                                            <table class="table table-striped" id="table-list-user">\n' +
        '                                                <thead>\n' +
        '                                                <tr>\n' +
        '                                                    <th scope="col">STT</th>\n' +
        '                                                    <th scope="col">Tài khoản</th>\n' +
        '                                                    <th scope="col">Tên</th>\n' +
        '                                                    <th scope="col">Số điện thoại</th>\n' +
        '                                                    <th scope="col">Hành động</th>\n' +
        '                                                </tr>\n' +
        '                                                </thead>\n' +
        '                                                <tbody>';


    for (let i = 0; i < userDto.length; i++) {
        let tempt = userDto[i];
        html += ' <tr>\n' +
            '                                                        <th scope="row">' + (i + 1) + '</th>\n' +
            '                                                        <td>' + tempt.userName + '</td>\n' +
            '                                                        <td>' + tempt.fullName + '</td>\n' +
            '                                                        <td>' + tempt.phone + '</td>\n' +
            '                                                        <td>\n' +
            '                                                            <div class="sweetalert ">\n' +
            '                                                                <button id="' + tempt.userId + '"\n' +
            '                                                                        class="btn btn-success sweet-confirm"\n' +
            '                                                                        data-tooltip="Thêm người dùng vào module"><i\n' +
            '                                                                        class="fa fa-plus" aria-hidden="true"></i> Thêm\n' +
            '                                                                </button>\n' +
            '                                                            </div>\n' +
            '                                                        </td>\n' +
            '                                                    </tr>';
    }

    html += '                   </tbody>\n' +
        '                                            </table>\n' +
        '\n' +
        '                                        </div>\n' +
        '                                    </div>';


    html += '<script src="/template/js/roles/module/sweetalert.user.js"></script>';

    let user_module = $("#user-module");
    user_module.empty();
    user_module.html(html);

    $("#table-list-user-module").DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });
    $("#table-list-user").DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });
}

function initTableRole(){
    $.ajax({
        url: "/manager-module.html",
        type: "get",
        data: {
            data: "role",
            module: id_module
        },
        success: function (data) {
            let arrJSON = typeof data != 'object' ? JSON.parse(data) : data;
            let listAllRole = arrJSON[0];
            let listRoleModule = arrJSON[1];
            setTableRoleModule(listRoleModule, listAllRole);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

function setTableRoleModule(listRoleModule, listAllRole){
    let html = '<div class="card">\n' +
        '    <div class="card-title" data-toggle="collapse" data-target="#table-module-role" data-tooltip="Nhấn vào để Ẩn/Hiển thị dữ liệu" >\n' +
        '        <h4 class="text-center" style="display: block !important;">Danh vai trò đã thêm vào module</h4>\n' +
        '    </div>\n' +
        '        <div class="card-body">\n' +
        '            <table class="table table-striped" id="table-module-role">\n' +
        '                <thead>\n' +
        '                <tr>\n' +
        '                    <th>Tên</th>\n' +
        '                    <th>Mô tả</th>\n' +
        '                    <th>Hành động</th>\n' +
        '                </tr>\n' +
        '                </thead>\n' +
        '                <tbody>';
    $.each(listRoleModule, function (i, item) {
        html += '<tr>\n' +
            '                    <td>'+item.roleDto.roleName+'</td>\n' +
            '                    <td>'+item.roleDto.roleDescription+'</td>\n' +
            '                    <td>\n' +
            '                        <button id="'+item.roleModuleId+'" type="button" class="btn btn-danger sweet-delete-role" data-tooltip="Xóa vai trò">\n' +
            '                            <i class="fa fa-trash-o" aria-hidden="true"></i>\n' +
            '                            <span>Xóa</span>\n' +
            '                        </button>\n' +
            '                    </td>\n' +
            '                </tr>';
    });
    html += '</tbody>\n' +
        '            </table>\n' +
        '    </div>\n' +
        '</div>\n' +
        '<div class="card">\n' +
        '    <div class="card-title" data-toggle="collapse" data-target="#table-role-list" data-tooltip="Nhấn vào để Ẩn/Hiển thị dữ liệu" >\n' +
        '        <h4 class="text-center" style="display: block !important;">Danh sách tất cả vai trò</h4>\n' +
        '    </div>\n' +
        '        <div class="card-body">\n' +
        '            <table class="table table-striped" id="table-role-list">\n' +
        '                <thead>\n' +
        '                <tr>\n' +
        '                    <th>Tên</th>\n' +
        '                    <th>Mô tả</th>\n' +
        '                    <th>Hành động</th>\n' +
        '                </tr>\n' +
        '                </thead>\n' +
        '                <tbody>';
    $.each(listAllRole, function (i, item) {
        html += '<tr>\n' +
            '                    <td>'+item.roleName+'</td>\n' +
            '                    <td>'+item.roleDescription+'</td>\n' +
            '                    <td>\n' +
            '                        <button id="'+item.roleId+'" type="button" class="btn btn-success sweet-confirm-role" data-tooltip="Thêm vai trò">\n' +
            '                            <i class="fa fa-plus" aria-hidden="true"></i>\n' +
            '                            <span>Thêm</span>\n' +
            '                        </button>\n' +
            '                    </td>\n' +
            '                </tr>';
    });
    html += '</tbody>\n' +
        '            </table>\n' +
        '    </div>\n' +
        '</div>';
    html += '<script src="/template/js/roles/module/add-remove-role.js"></script>'

    $('#role-module').empty();
    $('#role-module').html(html);
    $("#table-module-role").DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });
    $("#table-role-list").DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });
}

$(document).ready(function () {
    updateDataUserModule();
    initTableRole();
});