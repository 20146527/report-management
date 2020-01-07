<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<style>
    .permission-table td:not(:last-child) {
        text-overflow: ellipsis;
        white-space: nowrap;
        overflow: hidden;
    }
</style>
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
                <h3 class="text-center">Quản trị quyền vai trò</h3>
            </div>

            <div class="card-body">

                <div class="vtabs" style="width: 100%">
                    <ul class="nav nav-tabs tabs-vertical" role="tablist">
                        <li class="nav-item">
                            <div style="background-color: #ffffff !important;" class="ver-tab nav-link">
                                <button type="button" onclick="" class="btn btn-outline-info btn-block">
                                    <span><i class="fa fa-plus"></i></span>&nbsp;
                                    <span class="hidden-xs-down">Thêm mới</span>
                                </button>

                            </div>

                        </li>
                        <c:forEach items="${listRole}" varStatus="loop" var="item">
                            <li class="nav-item"><a id="${item.roleId}"
                                                    href="/manager-permission.html?tab=${item.roleId}"
                                                    class="ver-tab nav-link <c:if test="${item.roleId == roleId}">active</c:if>"><span
                                    class="hidden-xs-down">${item.roleName}</span> </a></li>
                        </c:forEach>
                    </ul>

                    <div class="tab-content">
                        <div id="panel-permission-role">

                        </div>
                        <div id="panel-permission-list">

                        </div>

                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script>
    document.title = "Quản lý quyền";
    var roleId = '${roleId}';
</script>
<script src="<c:url value="/template/js/roles/permission/init-table.js"/>"></script>
<%--<script src="<c:url value="/template/js/roles/permission/add-remove-permission.js"/>"></script>--%>
</body>
</html>
