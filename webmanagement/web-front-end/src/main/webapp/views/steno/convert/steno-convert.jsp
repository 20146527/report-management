<%--
  Created by IntelliJ IDEA.
  User: HungPhan
  Date: 9/25/2019
  Time: 4:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item active">Chuyển đồi dữ liệu tốc ký</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->

<div class="container-fluid">

    <div class="col-12">

        <div class="card">
            <div class="card-body">

                <c:if test="${not empty error}">
                    <div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
                        <span id="error-login" class="badge badge-pill badge-danger">${error}</span>

                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </c:if>

                <div class="form-group">
                    <label for="convertSteno">Chọn loại chuyển đổi</label>
                    <select class="form-control" id="convertSteno">
                        <option id="convert-1">Chuyển đồi từ tiếng Việt sang tốc ký</option>
                        <option id="convert-2">Chuyển đổi từ tốc ký sang tiếng Việt</option>
                        <option id="convert-3">Xem nội dung file</option>
                    </select>
                </div>


                <div class="form-group">
                    <label for="type-file">Chọn loại file</label>
                    <select class="form-control" id="type-file">
                        <option id="type-1">File không mã hóa</option>
                        <option id="type-2">File mã hóa</option>
                    </select>
                </div>


                <input type="file" name="files[]" id="filer_input" multiple="multiple">

                <button class="btn btn-block btn-success" id="convert" onclick="fnClickConvert()">Convert</button>

            </div>
        </div>
    </div>
</div>

<script src="<c:url value='/template/js/input-file/custom.js'/>"></script>


</body>
</html>
