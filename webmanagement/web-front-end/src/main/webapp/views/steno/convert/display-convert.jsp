<%--
  Created by IntelliJ IDEA.
  User: HungPhan
  Date: 9/27/2019
  Time: 9:49 AM
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
            <li class="breadcrumb-item active">Dữ liệu chuyển đổi</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->

<div class="container-fluid">

    <div class="col-12">

        <div class="card">
            <div class="card-body">

                <div class="card-title">
                    <h3 class="text-center">Nội dung chuyển đổi</h3>
                </div>

                <hr>

                <c:if test="${option == 1}">
                    <label for="txtEditor"></label>
                    <textarea class="form-control z-depth-1" rows="20" id="txtEditor">${ContentConvert}</textarea>
                </c:if>

                <c:if test="${option == 2}">
                    <div class="data-content-editor" id="data-content">${ContentConvert}</div>
                </c:if>

                <c:if test="${option == 3}">
                    <label for="txtEditor"></label>
                    <textarea class="form-control z-depth-1" rows="20" id="txtEditor">${ContentConvert}</textarea>
                </c:if>

                <button class="mt-3 btn btn-block btn-success" onclick="fnOnclickDownload()">Tải xuống
                </button>
            </div>
        </div>
    </div>
</div>

<!-- The Modal -->
<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Tải xuống</h4>
            </div>

            <form id="form-download-file" class="form-valide" method="post">
                <!-- Modal body -->
                <div class="modal-body">
                    <div class="form-validation">

                        <div class="form-group">

                            <div>
                                <input type="text" class="form-control" id="name-file" name="name-file"
                                       placeholder="Nhập tên file">
                            </div>

                            <input type="text" hidden name="data-convert" id="data-convert">

                            <div class="form-check mt-2">
                                <input class="form-check-input" type="checkbox" id="defaultCheck1" name="check-decode">
                                <label class="form-check-label" for="defaultCheck1">
                                    Mã hóa dữ liệu
                                </label>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-info">Tải xuống</button>

                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>
            </form>

        </div>
    </div>
</div>

<script src="<c:url value="/template/js/steno/convert/display-convert.js"/>"></script>

</body>
</html>
