<%--
  Created by IntelliJ IDEA.
  User: HungPhan
  Date: 11/4/2019
  Time: 4:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
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
            <li class="breadcrumb-item active">Quản lý file tốc ký</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->
<!-- Container fluid -->
<div class="container-fluid">
    <!-- Start Page Content -->


    <div class="row">
        <div class="col-12">

            <div class="card">
                <div class="card-body">
                    <div class="card-title">
                        <h3 class="text-center">Quản lý bộ từ điển tốc ký</h3>
                    </div>
                    <hr>

                    <div class="table-responsive">
                        <table id="steno-content" class="table table-striped table-bordered" style="width:100%">

                            <thead>
                            <tr>
                                <th>STT</th>
                                <th>Mã Phím</th>
                                <th>Phím Qwerty</th>
                                <th style="text-align: left !important;">Phím Steno</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:set var="index" value="0"/>
                            <jsp:useBean id="list" scope="request"
                                         type="java.util.List<ubnd.core.data.obj.LayoutObject>"/>
                            <c:forEach items="${list}" varStatus="loop" var="tempt">
                                <c:set var="index" value="${index + 1}"/>
                            <tr>

                                <td class="index">${index}</td>

                                <td class="keyQwerty">${tempt.keyQwerty}</td>

                                <td class="valueQwerty">${tempt.valueQwerty}</td>

                                <td class="form-group">
                                    <label for="${index}-input" id="${index}-label"
                                           style="margin-bottom: 0!important; color: red; font-style: italic; float: left; display: none">*Lỗi</label>
                                    <input id="${index}-input" class="form-control" type="text"
                                           value="${tempt.valueSteno}" onkeyup="checkValue($(this))"/>
                                </td>

                            </tr>
                            </c:forEach>

                        </table>
                    </div>

                    <div class="row mt-5">
                        <div class="col">
                            <button class="btn btn-block btn-danger"><i class="fa fa-ban" aria-hidden="true"></i> Hủy
                            </button>
                        </div>
                        <div class="col">
                            <button class="btn btn-block btn-info" onclick="updateLayout()"><i
                                    class="fa fa-check-circle-o"
                                    aria-hidden="true"></i> Đồng ý
                            </button>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<c:url value='/template/js/lib/toastr/toastr.min.js'/>"></script>
<script src="<c:url value="/template/js/steno/layouts/checkInput.js"/>"></script>

</body>
</html>
