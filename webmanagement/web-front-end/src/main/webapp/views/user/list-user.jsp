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
<c:url var="listUserUrl" value="/manager-user-list.html">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url var="searchUserUrl" value="/manager-user-list.html">
    <c:param name="urlType" value="url_search"/>
</c:url>
<html>
<head>
    <title>Danh sách người dùng</title>
</head>
<body>

<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item">Quản lý người dùng</li>
            <li class="breadcrumb-item active">Danh sách</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->

<!-- Container fluid  -->
<div class="container-fluid">
    <!-- Start Page Content -->
    <div class="row">
        <div class="col-12">

            <!-- Search -->
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Tìm kiếm</h4>
                    <h6 class="card-subtitle">Nhập thông tin tìm kiếm</h6>
                    <form action="${listUserUrl}" method="get" id="searchSpeakerForm">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="name" class="control-label">Họ và tên</label>
                                    <input type="text" class="form-control" id="name" name="pojo.fullName"
                                           placeholder="Nhập họ và tên" value="${item.pojo.fullName}">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="email" class="control-label">Email</label>
                                    <input type="text" class="form-control" id="email" name="pojo.email"
                                           placeholder="Nhập địa chỉ email" value="${item.pojo.email}">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="phone" class="control-label">SDT</label>
                                    <input type="text" class="form-control" id="phone" name="pojo.phone"
                                           placeholder="Nhập sdt" value="${item.pojo.phone}">
                                </div>
                            </div>
                            <input type="hidden" name="urlType" value="url_list">
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label class="control-label">Hành động</label><br>
                                    <button type="submit" id="btnSearch"
                                            class="btn btn-warning btn-rounded m-b-10 m-l-5 btn-block">
                                        <i class="fa fa-search"></i> Tìm kiếm
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- End Search -->

            <!-- List -->
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Danh sách</h4>

                    <button type="button" name="button" onclick="update(this)" class="btn btn-outline-info">
                        <i class="fa fa-plus"></i>&nbsp;
                        <span>Thêm mới người dùng</span>
                    </button>
                    <h6 class="card-subtitle">Xuất dữ liệu dạng Copy, CSV, Excel, PDF & In</h6>
                    <%--alert--%>
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-${alert} alert-dismissible fade show">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <span>${messageResponse}</span>
                        </div>
                    </c:if>
                    <%--alert--%>

                    <div class="table-responsive m-t-40">
                        <table id="example23"
                               class="display nowrap table table-hover table-striped table-bordered"
                               cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Username</th>
                                <th>Họ và tên</th>
                                <th>Email</th>
                                <th>SDT</th>
                                <th>Địa chỉ</th>
                                <th>Hành động</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${items}" varStatus="loop" var="row">
                                <tr>
                                    <td>${row.getUserId()}</td>
                                    <td>${row.getUserName()}</td>
                                    <td>${row.getFullName()}</td>
                                    <td>${row.getEmail()}</td>
                                    <td>${row.getPhone()}</td>
                                    <td>${row.getAddress()}</td>
                                    <td>
                                        <c:url var="editUrl" value="/ajax-user-edit.html">
                                            <c:param name="urlType" value="url_edit"/>
                                            <c:param name="pojo.userId" value="${row.getUserId()}"/>
                                        </c:url>
                                        <c:url var="deleteUrl" value="/ajax-user-edit.html">
                                            <c:param name="urlType" value="url_delete"/>
                                            <c:param name="pojo.userId" value="${row.getUserId()}"/>
                                            <c:param name="pojo.fullName" value="${row.getFullName()}"/>
                                        </c:url>
                                        <button type="button" name="submit" class="btn btn-success"
                                                sc-url="${editUrl}" onclick="update(this)"
                                                data-tooltip="Sửa">
                                            <i class="fa fa-pencil-square-o"></i>&nbsp;
                                        </button>
                                        <button type="button" name="submit" class="btn btn-danger"
                                                userId="${row.getUserId()}" fullname="${row.getFullName()}"
                                                onclick="removeUser(this)" data-tooltip="Xóa">
                                            <i class="fa fa-trash"></i>&nbsp;
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <form action="${listUserUrl}" method="get" id="formUrl">
                        <input type="hidden" name="crudaction" id="crudaction"/>
                        <input type="hidden" name="urlType" id="urlType"/>
                    </form>

                </div>
            </div>
            <!-- End List -->

        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog" data-backdrop="static" data-keyboard="false"></div>
<div class="modal fade" id="modalRemove" role="dialog" data-backdrop="static" data-keyboard="false"></div>

<script>
    document.title = "Quản lý người dùng";
    $(document).ready(function () {
        //$('#myModal').modal({backdrop: 'static', keyboard: false});
    });

    function update(btn) {
        var editUrl = $(btn).attr('sc-url');
        if (typeof editUrl == 'undefined') {
            editUrl = '${editUserUrl}';
        }
        $("#overlay").css({"display": "block"});
        $('#myModal').load(editUrl, '', function () {
            $("#overlay").css({"display": "none"});
            $('#myModal').modal('toggle');
            //addOrEditUser();
        });
    }

    function addOrEditUser() {
        $('#btnSave').click(function () {
            $('#editUserForm').submit();
        });
        $('#editUserForm').submit(function (e) {
            e.preventDefault();
            $('#crudactionEdit').val('insert_update');
            $.ajax({
                type: $(this).attr('method'),
                url: $(this).attr('action'),
                data: $(this).serialize(),
                dataType: 'html',
                success: function (res) {
                    if (res.trim() == "redirect_insert") {
                        $('#crudaction').val('redirect_insert');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    } else if (res.trim() == "redirect_update") {
                        $('#crudaction').val('redirect_update');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    } else if (res.trim() == "redirect_error") {
                        $('#crudaction').val('redirect_error');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            });
        });
    }

    function remove(btn) {
        var removeUrl = $(btn).attr('sc-url');
        $('#modalRemove').load(removeUrl, '', function () {
            $('#modalRemove').modal('toggle');
            removeEXE();
        });
    }

    function removeEXE() {
        $('#btnRemove').click(function () {
            $('#removeUserForm').submit();
        });
        $('#removeUserForm').submit(function (e) {
            e.preventDefault();
            $.ajax({
                type: $(this).attr('method'),
                url: $(this).attr('action'),
                data: $(this).serialize(),
                dataType: 'html',
                success: function (res) {
                    if (res.trim() == "redirect_delete") {
                        $('#crudaction').val('redirect_delete');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    } else if (res.trim() == "redirect_error") {
                        $('#crudaction').val('redirect_error');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            });
        });
    }

    function removeUser(btn) {
        let url = '/ajax-user-edit.html?urlType=url_delete';
        let userId = $(btn).attr('userId');
        let fullname = $(btn).attr('fullname');
        swal({
                title: "Xác nhận xóa ?",
                text: "Người dùng: " + fullname,
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Xóa",
                cancelButtonText: "Hủy",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function (isConfirm) {
                if (isConfirm) {
                    $.ajax({
                        type: 'post',
                        url: url,
                        data: {
                            urlType: 'url_delete',
                            userId: userId
                        },
                        success: function (res) {
                            // swal("Đã xóa !!", "Đã xóa người dùng: "+fullname, "success");
                            swal({
                                title: "Đã xóa !",
                                text: "Đã xóa người dùng: " + fullname,
                                type: "success"
                            }, function () {
                                swal.close();
                                $('#crudaction').val('redirect_delete');
                                $('#urlType').val('url_list');
                                $('#formUrl').submit();
                            })
                        },
                        error: function (res) {
                            swal("Lỗi !!", "Không gửi được yêu cầu xóa", "success");
                            setTimeout(function () {
                                $('#crudaction').val('redirect_error');
                                $('#urlType').val('url_list');
                                $('#formUrl').submit();
                            }, 1000);
                        }
                    });
                } else {
                    swal("Đã hủy !!", "Hủy yêu cầu xóa", "error");
                }
            });
    }
</script>


</body>
</html>
