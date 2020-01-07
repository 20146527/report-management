<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HungPhan
  Date: 10/21/2019
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item active">Thiết lập thứ tự ưu tiên</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->

<div class="container-fluid">

    <div class="col-12">

        <div class="card">

            <div class="card-title">
                <h3 class="text-center">Danh sách bộ từ điển cá nhân</h3>
            </div>
            '
            <div class="card-body">
                <div id="sortable">
                    <c:set var="index" value="0"/>
                    <jsp:useBean id="list" scope="request" type="java.util.List<ubnd.core.data.obj.StenoObject>"/>
                    <c:forEach var="list" items="${list}" varStatus="loop">
                        <c:set var="index" value="${index + 1}"/>
                        <div class="ui-state-default row"
                             style=" border: 1px solid; padding: 5px; box-shadow: 2px 3px rgba(136,136,136,0.49); margin: 5px">
                            <div class="col-1" style="padding: 10px">${index}</div>
                            <div class="col-11" style="padding: 10px">
                                <div class="row">
                                    <div class="col key">${list.key}</div>
                                    <div class="col value">${list.value}</div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

                <div class="row mt-3">
                    <div class="col">
                        <button class="btn btn-block btn-danger" onclick="history.back()">Quay lại</button>
                    </div>
                    <div class="col">
                        <button class="btn btn-block btn-success sweet-confirm">Lưu lại</button>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<script>
    $(function () {
        $("#sortable").sortable();
        $("#sortable").disableSelection();
    });
</script>

<script src="<c:url value="/template/js/steno/oder/steno-setting-oder.js"/>"></script>
<script src="<c:url value="/template/js/steno/oder/sweetalert.custom.js"/>"></script>


</body>
</html>
