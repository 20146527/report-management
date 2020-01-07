<%--
  Created by IntelliJ IDEA.
  User: HungPhan
  Date: 8/20/2019
  Time: 8:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="uploadRule" value="/typing-rules.html?type=upload"/>

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
            <li class="breadcrumb-item active">Quản lý bộ quy tắc gõ</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->
<div class="container-fluid">
    <div class="col-12">
        <div class="card">
            <div class="card-body">
                <div class="card-title">
                    <h3 class="text-center">Quản lý bộ quy tắc gõ</h3>
                </div>

                <hr>

                <ul class="nav nav-pills m-t-30 m-b-30">

                    <li class="nav-item"><a href="#tab-list-rules" class="nav-link active" id="tab-1"
                                            data-toggle="tab"
                                            aria-expanded="false">Danh sách</a></li>

                    <li class="nav-item"><a href="#tab-upload" class="nav-link" id="tab-2"
                                            data-toggle="tab"
                                            aria-expanded="false">Tải lên file</a></li>

                    <%--                    <li class="nav-item"><a href="#tab-add-rule" class="nav-link" id="tab-3"--%>
                    <%--                                            data-toggle="tab"--%>
                    <%--                                            aria-expanded="false">Thêm mới</a></li>--%>
                </ul>

                <div class="tab-content br-n pn">
                    <div id="tab-list-rules" class="tab-pane active">
                        <h4 class="card-title mb-4">Danh sách bộ quy tắc gõ tốc ký</h4>

                        <div class="table-responsive">
                            <table id="table-list-rule" class="table table-striped table-bordered" style="width:100%">

                                <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Tên</th>
                                    <th>Ngày sửa</th>
                                    <th>Người sửa</th>
                                    <th>Trạng thái</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:set var="index" value="0"/>
                                <jsp:useBean id="dataRule" scope="request"
                                             type="java.util.List<ubnd.core.data.obj.VersionRule>"/>
                                <c:forEach items="${dataRule}" varStatus="loop" var="tempt">
                                    <c:set var="index" value="${index + 1}"/>
                                <tr>
                                    <td class="index">${index}</td>
                                    <td class="name-dict">${tempt.name}</td>
                                    <td class="mod-date-dict">${tempt.timeUpdate}</td>
                                    <td class="user-mod-dict">${tempt.updateBy}</td>
                                    <td>
                                        <c:if test="${tempt.status == 0}">
                                            <span class="badge btn-success">Hoạt động</span>
                                        </c:if>

                                        <c:if test="${tempt.status == 2}">
                                            <span class="badge btn-warning">Không hoạt động</span>
                                        </c:if>
                                    </td>
                                    <td>
                                        <button class="btn btn-info" data-tooltip="Chi tiết"
                                                onclick="window.location.href='/typing-detail.html?id=${tempt.id}'"><i
                                                class="fa fa-pencil-square-o" aria-hidden="true"></i></button>

                                        <c:if test="${tempt.status == 2}">
                                            <button class="btn btn-success" data-tooltip="Mặc định"
                                                    onclick="confirmAction(1, ${tempt.id})">
                                                <i class="fa fa-check-square-o" aria-hidden="true"></i>
                                            </button>
                                            <button class="btn btn-danger" data-tooltip="Xóa"
                                                    onclick="confirmAction(2, ${tempt.id})">
                                                <i class="fa fa-trash-o" aria-hidden="true"></i>
                                            </button>
                                        </c:if>
                                    </td>
                                    </c:forEach>

                            </table>
                        </div>

                    </div>

                    <div id="tab-upload" class="tab-pane">
                        <h4 class="card-title mb-4">Upload mới bộ quy tắc gõ</h4>

                        <div class="mr-1 ml-1 mt-2 mb-3">Nhấn <a
                                href="${pageContext.request.contextPath}/typing-rules.html?type=download"
                                style="color: #0d71bb">vào
                            đây</a> để tải file mẫu về
                        </div>

                        <form action="${uploadRule}" method="post" enctype="multipart/form-data">
                            <input type="file" name="files[]" id="filer_input" multiple>
                            <button class="btn btn-block btn-success mt-2" id="btn-upload-steno">Tải lên</button>
                        </form>

                    </div>


                </div>

            </div>
        </div>
    </div>
</div>


<script src="<c:url value="/template/js/steno/typing/custom-input-upload-steno.js"/>"></script>

</body>
</html>
