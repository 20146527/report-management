<%--
  Created by IntelliJ IDEA.
  User: HungPhan
  Date: 10/15/2019
  Time: 10:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
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
                <h3 class="text-center">Quản trị module hệ thống</h3>
            </div>

            <div class="card-body">
                <div class="vtabs" style="width: 100%">
                    <ul class="nav nav-tabs tabs-vertical" role="tablist">
                        <c:forEach items="${moduleDtoList}" varStatus="loop" var="tempt">
                            <li class="nav-item"><a id="${tempt.moduleID}" href="/manager-module.html?module=${tempt.moduleID}"
                                                    class="ver-tab nav-link <c:if test="${tempt.moduleID == id}">active</c:if>"><span
                                    class="hidden-xs-down">${tempt.nameModule}</span> </a></li>
                        </c:forEach>
                    </ul>

                    <div class="tab-content" id="module-panel" style="padding-top: 0px !important;">
                        <ul class="nav nav-pills">
                            <li class="nav-item"> <a href="#user-module" class="nav-link active" id="nav-custom" data-toggle="tab" aria-expanded="false">Người dùng</a> </li>
                            <li class="nav-item"> <a href="#role-module" class="nav-link" id="nav-audio" data-toggle="tab" aria-expanded="false">Vai trò</a> </li>
                        </ul>
                        <div class="tab-content br-n pn" style="display: block !important">
                            <div id="user-module" class="tab-pane active">

                            </div>
                            <div id="role-module" class="tab-pane">
                                <div class="card">
                                    <div class="card-title" data-toggle="collapse" data-target="#table-module-role" data-tooltip="Nhấn vào để Ẩn/Hiển thị dữ liệu" >
                                        <h4 class="text-center" style="display: block !important;">Danh vai trò đã thêm vào module</h4>
                                        <div class="card-body">
                                            <table class="table table-striped" id="table-module-role">
                                                <thead>
                                                <tr>
                                                    <th>Tên</th>
                                                    <th>Mô tả</th>
                                                    <th>Hành động</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>QTV</td>
                                                    <td>Quản trị viên</td>
                                                    <td>
                                                        <button id="2" type="button" class="btn btn-danger sweet-delete" data-tooltip="Xóa vai trò">
                                                            <i class="fa fa-trash-o" aria-hidden="true"></i>
                                                            <span>Xóa</span>
                                                        </button>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <div class="card">
                                    <div class="card-title" data-toggle="collapse" data-target="#table-role-list" data-tooltip="Nhấn vào để Ẩn/Hiển thị dữ liệu" >
                                        <h4 class="text-center" style="display: block !important;">Danh sách tất cả vai trò</h4>
                                        <div class="card-body">
                                            <table class="table table-striped" id="table-role-list">
                                                <thead>
                                                <tr>
                                                    <th>Tên</th>
                                                    <th>Mô tả</th>
                                                    <th>Hành động</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td>QTV</td>
                                                    <td>Quản trị viên</td>
                                                    <td>
                                                        <button id="1" type="button" class="btn btn-success sweet-confirm" data-tooltip="Thêm vai trò">
                                                            <i class="fa fa-plus" aria-hidden="true"></i>
                                                            <span>Thêm</span>
                                                        </button>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>
</div>

<script>
    document.title = "Quản trị Module";
    let id_module = '${id}';
</script>

<script src="<c:url value="/template/js/roles/module/manager-module.js"/>"></script>

</body>
</html>
