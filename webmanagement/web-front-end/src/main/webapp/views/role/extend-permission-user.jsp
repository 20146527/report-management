<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="listExPermissionUrl" value="/manager-extend-permission.html">
    <c:param name="urlType" value="url_list"/>
</c:url>
<html>
<head>
    <title>Title</title>
</head>
<body>

<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/home.html">Trang chủ</a></li>
            <li class="breadcrumb-item active">Quản trị module hệ thống</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->
<div class="container-fluid">

    <div class="col-12">
        <div class="card">

            <div class="card-title">
                <h3 class="text-center">Cấp quyền người dùng</h3>
            </div>
            <hr>
            <ul class="nav nav-pills m-t-30 m-b-30">
                <li class="nav-item"> <a href="#navpills-extend-permission" class="nav-link <c:if test="${tab == 1}">active</c:if>" id="nav-custom" data-toggle="tab" aria-expanded="false">Danh sách chờ</a> </li>
                <li class="nav-item"> <a href="#navpills-permission-granted" class="nav-link <c:if test="${tab == 2}">active</c:if>" id="nav-audio" data-toggle="tab" aria-expanded="false">Quyền đã cấp</a> </li>
            </ul>
            <div class="tab-content br-n pn">
                <div id="navpills-extend-permission" class="tab-pane <c:if test="${tab == 1}">active</c:if>">
                    <h4>Danh sách chờ cấp quyền</h4>
                    <div class="table-responsive m-t-10">
                        <table id="table-waiting-list"
                               class="display nowrap table table-hover table-striped table-bordered"
                               cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Họ và tên</th>
                                <th>Loại file</th>
                                <th>File</th>
                                <th>Số lần</th>
                                <th>Loại quyền</th>
                                <th>Hành động</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${waitingList}" varStatus="loop" var="row">
                                <tr>
                                    <td>${row.getExtendPermissionUserId()}</td>
                                    <td>${row.userDto.getFullName()}</td>
                                    <td>
                                        <c:if test="${row.objectDto.getTypeObject() eq 20}">
                                            <span class="badge badge-warning">File âm thanh</span>
                                        </c:if>
                                        <c:if test="${row.objectDto.getTypeObject() eq 30}">
                                            <span class="badge badge-primary">File tốc ký</span>
                                        </c:if>
                                        <c:if test="${row.objectDto.getTypeObject() eq 40}">
                                            <span class="badge badge-danger">File biên bản</span>
                                        </c:if>
                                    </td>
                                    <td>${row.objectDto.getDescription()}</td>
                                    <td>
                                        <select class="form-control" id="count${row.getExtendPermissionUserId()}">
                                            <option value="1">Chỉ một lần</option>
                                            <option value="2">Trong ngày</option>
                                            <option value="3">Mãi mãi</option>
                                        </select>
                                    </td>
                                    <td>
                                        <select class="form-control" id="operator${row.getExtendPermissionUserId()}">
                                            <option value="1">Chỉ xem</option>
                                            <option value="3">Chỉnh sửa</option>
                                            <option value="4">Xem, Sửa & Xóa</option>
                                        </select>
                                    </td>
                                    <td>
                                        <button id="${row.getExtendPermissionUserId()}" type="button" class="btn btn-danger sweet-reject" data-tooltip="Từ chối cấp quyền">
                                            <i class="fa fa-trash" aria-hidden="true"></i>
                                            <span>Từ chối</span>
                                        </button>
                                        <button id="${row.getExtendPermissionUserId()}" type="button" class="btn btn-success sweet-accept" data-tooltip="Chấp nhận quyền">
                                            <i class="fa fa-plus" aria-hidden="true"></i>
                                            <span>Chấp nhận</span>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div id="navpills-permission-granted" class="tab-pane <c:if test="${tab == 2}">active</c:if>">
                    <h4>Danh sách quyền đã cấp</h4>
                    <div class="table-responsive m-t-10">
                        <table id="table-licensed-list"
                               class="display nowrap table table-hover table-striped table-bordered"
                               cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Cấp cho</th>
                                <th>File</th>
                                <th>Số lần cấp</th>
                                <th>Loại quyền</th>
                                <th>Người cấp</th>
                                <th>Ngày cấp</th>
                                <th>Trạng thái</th>
                                <th>Hành động</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${licensedList}" varStatus="loop" var="row">
                                <tr>
                                    <td>${row.getExtendPermissionUserId()}</td>
                                    <td>${row.userDto.getFullName()}</td>
                                    <td>
                                        <c:if test="${row.objectDto.getTypeObject() eq 20}">
                                            <i class="fa fa-volume-up" aria-hidden="true"></i>
                                            <span> :${row.objectDto.getDescription()}</span>
                                        </c:if>
                                        <c:if test="${row.objectDto.getTypeObject() eq 30}">
                                            <i class="fa fa-american-sign-language-interpreting" aria-hidden="true"></i>
                                            <span> :${row.objectDto.getDescription()}</span>
                                        </c:if>
                                        <c:if test="${row.objectDto.getTypeObject() eq 40}">
                                            <i class="fa fa-file-text" aria-hidden="true"></i>
                                            <span> :${row.objectDto.getDescription()}</span>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${row.getCount() eq 1}">
                                            <span class="badge badge-secondary">Chỉ một lần</span>
                                        </c:if>
                                        <c:if test="${row.getCount() eq 2}">
                                            <span class="badge badge-info">Trong ngày</span>
                                        </c:if>
                                        <c:if test="${row.getCount() eq 3}">
                                            <span class="badge badge-success">Mãi mãi</span>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${row.operatorDto.getOperatorID() eq 1}">
                                            <div class="text-center">
                                                <button class="btn btn-outline-dark" data-tooltip="Chỉ xem">
                                                    <i class="fa fa-eye" aria-hidden="true"></i>
                                                </button>
                                            </div>
                                        </c:if>
                                        <c:if test="${row.operatorDto.getOperatorID() eq 3}">
                                            <div class="text-center">
                                                <button class="btn btn-outline-dark" data-tooltip="Chỉnh sửa">
                                                    <i class="fa fa-edit" aria-hidden="true"></i>
                                                </button>
                                            </div>
                                        </c:if>
                                        <c:if test="${row.operatorDto.getOperatorID() eq 4}">
                                            <div class="text-center">
                                                <button class="btn btn-outline-dark" data-tooltip="Xem, Sửa & Xóa">
                                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                                </button>
                                            </div>
                                        </c:if>
                                    </td>
                                    <td>${row.getCreUserName()}</td>
                                    <td>${row.getCreDate()}</td>
                                    <td>
                                        <c:if test="${row.getRequestTag() eq 0}">
                                            <span class="badge badge-success">Còn hạn</span>
                                        </c:if>
                                        <c:if test="${row.getRequestTag() eq 2}">
                                            <span class="badge badge-danger">Hết hạn</span>
                                        </c:if>
                                    </td>
                                    <td>
                                        <button id="${row.getExtendPermissionUserId()}" type="button" class="btn btn-danger sweet-cancel" data-tooltip="Hủy cấp quyền">
                                            <i class="fa fa-trash" aria-hidden="true"></i>
                                            <span>Hủy</span>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    document.title = "Cấp quyền người dùng";
    $(document).ready(function () {
        $("#table-waiting-list").DataTable({
            "lengthMenu": [[10, 15, 25, 50, -1], [10, 15, 25, 50, "All"]]
        });
        $("#table-licensed-list").DataTable({
            "lengthMenu": [[10, 15, 25, 50, -1], [10, 15, 25, 50, "All"]]
        });
    });

</script>
<script src="<c:url value="/template/js/roles/extend-permission/accept-reject-permission.js"/>"></script>
</body>
</html>
