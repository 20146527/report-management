<%--
  Created by IntelliJ IDEA.
  User: HungPhan
  Date: 8/1/2019
  Time: 8:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
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
            <li class="breadcrumb-item active">Quản lý bộ âm tiết Tiếng Việt</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->

<div class="container-fluid">

    <div class="col-12">

        <div class="card">
            <div class="card-body">
                <div class="card-title">
                    <h3 class="text-center">Quản danh sách bộ âm tiết Tiếng Việt</h3>
                </div>

                <hr>


                <ul class="nav nav-pills m-t-30 m-b-30">
                    <li class="nav-item"><a href="#navpills-list-word" class="nav-link active" id="tab-1"
                                            data-toggle="tab"
                                            aria-expanded="false">Danh sách</a></li>

                    <li class="nav-item"><a href="#navpills-upload-word" class="nav-link" id="tab-2"
                                            data-toggle="tab"
                                            aria-expanded="false">Tải lên</a></li>

                </ul>
                <div class="tab-content br-n pn">

                    <div id="navpills-list-word" class="tab-pane <c:if test="${tab == 0}">active</c:if>">
                        <div class="table-responsive">
                            <table id="table-word-manger" class="table table-striped table-bordered" style="width:100%">
                                <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Thời gian chỉnh sửa</th>
                                    <th>Tạo bởi</th>
                                    <th>Độ dài</th>
                                    <th>Trạng thái</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%--@elvariable id="list" type="java.util.List<ubnd.core.data.obj.ListWord>"--%>
                                <c:set var="index" value="0"/>
                                <c:forEach items="${list}" varStatus="loop" var="tempt">
                                    <c:set var="index" value="${index+1}"/>
                                <tr>
                                    <td>${index}</td>
                                    <td>${tempt.timeUpdate}</td>
                                    <td>${tempt.userCreate}</td>
                                    <td>${tempt.length}</td>
                                    <c:if test="${tempt.status == 0}">
                                        <td><span class="badge btn-success">Hoạt động</span></td>
                                    </c:if>

                                    <c:if test="${tempt.status == 1}">
                                        <td><span class="badge btn-warning">Vô hiệu hóa</span></td>
                                    </c:if>

                                    <td>
                                        <button id="detail-dict" class="btn btn-success"
                                                onclick="infoWord('<c:url
                                                        value="../../../file/word/txt/"/>' ,'${tempt.nameFile}')"
                                                data-tooltip="Chi tiết">
                                            <i class="fa fa-info" aria-hidden="true"></i>
                                        </button>

                                        <c:if test="${tempt.status == 1}">
                                            <button id="detail-dict" class="btn btn-info"
                                                    onclick="defaultWord('${tempt.id}')" data-tooltip="Mặc định">
                                                <i class="fa fa-check" aria-hidden="true"></i>
                                            </button>
                                        </c:if>
                                    </td>
                                </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                    <div id="navpills-upload-word" class="tab-pane <c:if test="${tab == 1}">active</c:if>">
                        <div class="mr-1 ml-1 mt-3 mb-3">Nhấn <a
                                href="${pageContext.request.contextPath}/word-manager.html?type=download"
                                style="color: #0d71bb">vào
                            đây</a> để tải file mẫu về
                        </div>

                        <form id="form-upload-word">

                            <input type="file" name="files[]" id="filer_input" multiple>
                            <button id="btn-upload-word" class="btn btn-block btn-success">Tải lên file mới</button>

                        </form>

                    </div>

                </div>


            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div id="info-word" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">

            <div class="modal-header">
                <h4 class="modal-title">Chỉnh Sửa</h4>
            </div>

            <div id="modal-body" class="modal-body">

            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
            </div>

        </div>

    </div>
</div>

<script src="<c:url value="../../../template/js/steno/word/word-manager.js"/>"></script>
<script src="<c:url value="/template/js/steno/word/custom-input-upload-word.js"/>"></script>


</body>
</html>
