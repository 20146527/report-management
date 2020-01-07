<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="editUserUrl" value="/ajax-user-edit.html">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:choose>
    <c:when test="${not empty messageResponse}">
        ${messageResponse}
    </c:when>
    <c:otherwise>
        <!-- Modal -->
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <c:if test="${not empty item.pojo.userId}">
                        <h5 class="modal-title" id="exampleModalLabel">Chỉnh sửa người dùng</h5>
                        <script>
                            var userId = ${item.pojo.userId};
                        </script>
                        <script src="<c:url value="/template/js/roles/role/init-table.js"/>"></script>
                    </c:if>
                    <c:if test="${empty item.pojo.userId}">
                        <h5 class="modal-title" id="exampleModalLabel">Thêm người dùng</h5>
                    </c:if>

                </div>
                <div class="form-validation">
                    <form action="${editUserUrl}" method="post" id="editUserForm" class="form-valide">
                        <div class="modal-body">
                            <ul class="nav nav-pills m-t-30 m-b-30">
                                <c:if test="${not empty item.pojo.userId}">
                                    <li class=" nav-item"><a href="#navpills-1" class="nav-link active"
                                                             data-toggle="tab" aria-expanded="false">Thông tin</a></li>
                                    <li class="nav-item"><a href="#navpills-2" class="nav-link" data-toggle="tab"
                                                            aria-expanded="false">Vai trò</a></li>
                                </c:if>
                                <c:if test="${empty item.pojo.userId}">
                                    <li class=" nav-item"><a href="#navpills-1" class="nav-link active"
                                                             data-toggle="tab" aria-expanded="false">Thông tin</a></li>
                                </c:if>
                            </ul>

                            <div class="tab-content br-n pn">
                                <div id="navpills-1" class="tab-pane active">
                                        <%--Tab Info User--%>
                                    <div class="form-group">
                                        <label for="userName" class="control-label mb-1">Tên tài khoản <span
                                                class="text-danger">*</span></label>
                                        <div>
                                            <input type="text" class="form-control" id="userName" name="pojo.userName"
                                                   value="${item.pojo.userName}" placeholder="Nhập vào tên tài khoản">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="passWord" class="control-label mb-1">Mật khẩu <span
                                                class="text-danger">*</span></label>
                                        <div>
                                            <input type="password" class="form-control" id="passWord"
                                                   name="pojo.passWord" value="${item.pojo.passWord}" placeholder="Nhập vào mật khẩu">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="fullName" class="control-label mb-1">Họ và tên <span
                                                class="text-danger">*</span></label>
                                        <div>
                                            <input type="text" class="form-control" id="fullName" name="pojo.fullName"
                                                   value="${item.pojo.fullName}" placeholder="Nhập vào họ và tên">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="email" class="control-label mb-1">Thư điện tử <span
                                                class="text-danger">*</span></label>
                                        <div>
                                            <input type="text" class="form-control" id="email" name="pojo.email"
                                                   value="${item.pojo.email}" placeholder="Nhập vào Email">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="phone" class="control-label mb-1">Số điện thoại</label>
                                        <input type="text" class="form-control" id="phone" name="pojo.phone"
                                               value="${item.pojo.phone}" placeholder="Nhập vào SDT">
                                    </div>
                                    <div class="form-group">
                                        <label for="address" class="control-label mb-1">Địa chỉ</label>
                                        <input type="text" class="form-control" id="address" name="pojo.address"
                                               value="${item.pojo.address}" placeholder="Nhập vào địa chỉ">
                                    </div>
                                    <div class="form-group">
                                        <label for="gender" class=" control-label">Giới tính <span
                                                class="text-danger">*</span></label>
                                        <div>
                                            <select name="pojo.gender" id="gender" class="form-control">
                                                <option value="0">Nam</option>
                                                <option value="1">Nữ</option>
                                            </select>
                                        </div>
                                    </div>
                                    <input type="hidden" name="password" value="${item.pojo.passWord}">
                                    <c:if test="${not empty item.pojo.userId}">
                                        <input type="hidden" name="pojo.userId" value="${item.pojo.userId}"/>
                                        <input type="hidden" name="pojo.creUID" value="${item.pojo.creUID}">
                                        <input type="hidden" name="pojo.creDate" value="${item.pojo.creDate}">
                                    </c:if>
                                    <input type="hidden" name="crudaction" id="crudactionEdit" value="insert_update"/>

                                        <%--Tab Info User--%>
                                </div>
                                <div id="navpills-2" class="tab-pane">
                                        <%--Tab Update Role--%>
                                    <div id="panel-user-role">

                                    </div>
                                    <div id="panel-list-role">

                                    </div>
                                        <%--                            <div class="card">--%>
                                        <%--                                <div class="card-title" data-toggle="collapse" data-target="#table-user-role" data-tooltip="Nhấn vào để Ẩn/Hiển thị dữ liệu">--%>
                                        <%--                                    <h4 class="text-center"style="display: block !important;">Danh sách quyền đã thêm</h4>--%>
                                        <%--                                </div>--%>
                                        <%--                                <div class="card-body">--%>
                                        <%--                                    <table class="table table-striped m-b-20" id="table-user-role">--%>
                                        <%--                                        <thead>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <th>#</th>--%>
                                        <%--                                            <th>Tên</th>--%>
                                        <%--                                            <th>Mô tả</th>--%>
                                        <%--                                            <th>Hành động</th>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        </thead>--%>
                                        <%--                                        <tbody>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>1</td>--%>
                                        <%--                                            <td>Quản trị viên</td>--%>
                                        <%--                                            <td>Admin</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <button class="btn btn-danger sweet-delete" data-tooltip="Thêm Quyền cho người dùng">--%>
                                        <%--                                                    <i class="fa fa-trash-o" aria-hidden="true"></i>--%>
                                        <%--                                                    <span>Xóa</span>--%>
                                        <%--                                                </button>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        </tbody>--%>
                                        <%--                                    </table>--%>
                                        <%--                                </div>--%>
                                        <%--                            </div>--%>

                                        <%--                            <div class="card">--%>
                                        <%--                                <div class="card-title" data-toggle="collapse" data-target="#table-list-role" data-tooltip="Nhấn vào để Ẩn/Hiển thị dữ liệu">--%>
                                        <%--                                    <h4 class="text-center" style="display: block !important;">Danh sách quyền</h4>--%>
                                        <%--                                </div>--%>
                                        <%--                                <div class="card-body">--%>
                                        <%--                                    <table class="table table-striped" id="table-list-role">--%>
                                        <%--                                        <thead>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <th>#</th>--%>
                                        <%--                                            <th>Tên</th>--%>
                                        <%--                                            <th>Mô tả</th>--%>
                                        <%--                                            <th>Hành động</th>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        </thead>--%>
                                        <%--                                        <tbody>--%>
                                        <%--                                        <tr>--%>
                                        <%--                                            <td>1</td>--%>
                                        <%--                                            <td>Quản trị viên</td>--%>
                                        <%--                                            <td>Admin</td>--%>
                                        <%--                                            <td>--%>
                                        <%--                                                <button class="btn btn-success sweet-confirm" data-tooltip="Thêm Quyền cho người dùng">--%>
                                        <%--                                                    <i class="fa fa-plus" aria-hidden="true"></i>--%>
                                        <%--                                                    <span>Thêm</span>--%>
                                        <%--                                                </button>--%>
                                        <%--                                            </td>--%>
                                        <%--                                        </tr>--%>
                                        <%--                                        </tbody>--%>
                                        <%--                                    </table>--%>
                                        <%--                                </div>--%>
                                        <%--                            </div>--%>


                                        <%--End Tab Role--%>
                                </div>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="submit" id="btnSave" class="btn btn-primary">Lưu</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </c:otherwise>
</c:choose>

<script>
    $(document).ready(function () {
        //$('#myModal').modal({backdrop: 'static', keyboard: false});
        changeValueGender();
    });

    function changeValueGender() {
        var gender = '${item.pojo.gender}';
        // console.log(gender);
        $('#gender').val(gender).change();
    }
</script>

<%--<script src="<c:url value='/template/js/manager/validate-user.js'/>"></script>--%>
<script>
    console.log('${username_exist}');
    var form_validation = function () {
        var e = function () {
            $.validator.addMethod("userExist", function (value, element) {
                let status = true;
                let username = $('#userName').val();
                let username_exist = '${username_exist}';
                let user = typeof username_exist != 'object' ? JSON.parse(username_exist) : username_exist;
                $.each(user, function (i, item) {
                    if (username == item) {
                        status = false;
                    }
                });
                return this.optional(element) || status;
            }, "Tên người dùng đã tồn tại!");

            $(".form-valide").validate({
                ignore: [],
                errorClass: "invalid-feedback animated fadeInDown",
                errorElement: "div",
                errorPlacement: function (e, a) {
                    $(a).parents(".form-group > div").append(e)
                },
                highlight: function (e) {
                    $(e).closest(".form-group").removeClass("is-invalid").addClass("is-invalid")
                },
                success: function (e) {
                    $(e).closest(".form-group").removeClass("is-invalid"), $(e).remove()
                },
                rules: {
                    "pojo.userName": {
                        required: !0,
                        userExist: true
                    },
                    "pojo.passWord": {
                        required: !0,
                        minlength: 5
                    },
                    "pojo.fullName": {
                        required: !0
                    },
                    "pojo.email": {
                        required: !0,
                        email: !0
                    },
                    "pojo.gender": {
                        required: !0
                    }
                },
                messages: {
                    "pojo.userName": {
                        required: "Tên người dùng không được bỏ trống!",
                        userExist: "Tên người dùng đã tồn tại!"
                    },
                    "pojo.passWord": {
                        required: "Không bỏ trống mật khẩu!",
                        minlength: "Đặt mật khẩu dài hơn 6 ký tự!"
                    },
                    "pojo.fullName": "Không được bỏ trống!",
                    "pojo.email": {
                        required: "Không bỏ trống email!",
                        email: "Nhập đúng định dạng email!"
                    },
                    "pojo.gender": "Không được bỏ trống!",

                }
            })
        }
        return {
            init: function () {
                e(), $(".js-select2").on("change", function () {
                    $(this).valid()
                })
            }
        }
    }();
    $(function () {
        form_validation.init()
    });
</script>