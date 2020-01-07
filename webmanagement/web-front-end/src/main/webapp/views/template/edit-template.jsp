<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="editTemplateUrl" value="/manager-template.html">
    <c:param name="urlType" value="url_edit"/>
</c:url>

<c:url var="uploadTemplateUrl" value="/manager-template.html">
    <c:param name="urlType" value="read_doc_template"/>
</c:url>

<html>
<head>
    <title>Danh sách người họp</title>
</head>
<body>
<link href="<c:url value='/template/css/lib/jquery-filer/jquery.filer.css'/>" rel="stylesheet">
<link href="<c:url value='/template/css/lib/jquery-filer/themes/jquery.filer-dragdropbox-theme.css'/>" rel="stylesheet">
<script src="<c:url value='/template/js/lib/jquery-filer/jquery.filer.min.js'/>"></script>
<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item">Quản lý biên bản</li>
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

            <!-- Editor -->
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-8">
                            <c:if test="${not empty item.pojo.templateId}">
                                <h4 class="card-title">Chỉnh sửa template</h4>
                            </c:if>
                            <c:if test="${empty item.pojo.templateId}">
                                <h4 class="card-title">Thêm mới template</h4>
                            </c:if>

                            <h6 class="card-subtitle">Nhập vào vùng soạn thảo để chỉnh sửa</h6>
                        </div>
                        <div class="col-4">
                            <button type="button" name="button" class="float-right btn btn-outline-info"
                                    onclick="showUpload()">
                                <i class="fa fa-file-word-o"></i>&nbsp;
                                <span>Tải lên từ Word</span>
                            </button>
                        </div>
                    </div>
                    <hr>

                    <%--alert--%>
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-${alert} alert-dismissible fade show">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <strong>${messageResponse}</strong>
                        </div>
                    </c:if>
                    <%--alert--%>

                    <div id="upload-template" class="card">
                        <div class="card-body">
                            <h4 class="card-title">Nhập nội dung template bằng file Microsoft Docx</h4>
                            <form action="${uploadTemplateUrl}" method="post" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="divInput" class=" control-label">Tải lên template từ file .docx</label>
                                    <div id="divInput">

                                    </div>
                                </div>
                                <input type="hidden" name="urlType" value="read_doc_template"/>
                                <input type="hidden" name="name" value="${item.pojo.name}" >
                                <c:if test="${not empty item.pojo.templateId}">
                                    <input type="hidden" name="fontSize" value="${item.pojo.fontSize}">
                                    <input type="hidden" name="font" value="${item.pojo.font}">

                                    <input type="hidden" name="templateId" value="${item.pojo.templateId}">
                                    <input type="hidden" name="status" value="${item.pojo.status}">
                                    <input type="hidden" name="creUID" value="${item.pojo.creUID}">
                                    <input type="hidden" name="creDate" value="${item.pojo.creDate}">
                                </c:if>
                                <button type="submit" id="btnUpload" name="button" class="btn btn-block btn-info">
                                    <i class="fa fa-upload"></i>
                                    <span>Tải lên</span>&nbsp;
                                </button>
                            </form>
                        </div>
                    </div>


                    <form action="${editTemplateUrl}" method="post" id="editTemplateForm" class="form-valide">
                        <div class="form-group">
                            <label for="templateName">Tên template: <span class="text-danger">*</span></label>
                            <div>
                                <input type="text" id="templateName" name="pojo.name" class="form-control input-lg"
                                       value="${item.pojo.name}" placeholder="Vui lòng nhập tên template.">
                            </div>
                        </div>
                        <p>Thêm nội dung</p>
                        <div class="row">
                            <div class="col-11">
                                <div class="form-group">
                                    <select name="add" id="add" class="form-control">
                                        <option value="">Chọn nội dung...</option>
                                        <option value="1">Chủ trì cuộc họp</option>
                                        <option value="2">Thư ký cuộc họp</option>
                                        <option value="3">Thời điểm họp</option>
                                        <option value="4">Địa điểm họp</option>
                                        <option value="5">Chủ đề họp</option>
                                        <option value="6">Số người tham gia</option>
                                        <option value="7">Nội dung họp</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-1">
                                <button type="button" name="submit" class="btn btn-info"
                                        sc-url="" onclick="addContent()"
                                        data-tooltip="Thêm">
                                    <i class="fa fa-plus"></i>&nbsp;
                                </button>
                            </div>
                        </div>

                        <div>
                            <textarea id="full-featured" name="pojo.content">
                                ${item.pojo.content}
                            </textarea>
                        </div>
                        <c:if test="${not empty item.pojo.templateId}">
                            <input type="hidden" name="pojo.fontSize" value="${item.pojo.fontSize}">
                            <input type="hidden" name="pojo.font" value="${item.pojo.font}">

                            <input type="hidden" name="pojo.templateId" value="${item.pojo.templateId}">
                            <input type="hidden" name="pojo.status" value="${item.pojo.status}">
                            <input type="hidden" name="pojo.creUID" value="${item.pojo.creUID}">
                            <input type="hidden" name="pojo.creDate" value="${item.pojo.creDate}">
                        </c:if>

                        <div class="mt-3">
                            <button type="submit" name="submit" class="btn btn-block btn-primary">
                                <i class="fa fa-save"></i>&nbsp;
                                <span>Lưu lại</span>
                            </button>
                        </div>

                    </form>

                </div>
            </div>
            <!-- End Editor -->
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="modalRemove" role="dialog" data-backdrop="static" data-keyboard="false"></div>

<script>
    document.title = "Soạn thảo template";
    $(document).ready(function () {
        $('#upload-template').hide();
        //$('#myModal').modal({backdrop: 'static', keyboard: false});
    });

    function showUpload() {
        let html = '<input type="file" id="filer_input" name="file" >\n' +
            '<script src="/template/js/input-file/inputFileDocxTemplate.js"/>'
        $('#divInput').empty();
        $('#divInput').append(html);
        $('#upload-template').show();

    }

    function addContent() {
        var valueAdd = $('#add').val();
        var contentAdd = $('#add :selected').text();
        console.log(valueAdd);
        tinymce.activeEditor.execCommand('mceInsertContent', false, '<span style="background-color: #bdc3c7;" class="template' + valueAdd + '">' + contentAdd + '</span> <p></p>');
    }

    function inputNameFile() {
        var x = document.getElementById("inputFile");
        if (x.files.length == 1) {
            //chon file sai
            var file = x.files[0];
            console.log(file.name);
            //$('#inputFile').val("File: ");
            var nameFile = file.name;
            document.getElementById("lable-name-file-audio").innerHTML = nameFile;
            //document.getElementById("inputFile").value = "Johnny Bravo";
        } else {
            //chi chon 1 file
            console.log('chon nhieu file');
            $('#inputFile').val('Chỉ chọn 1 file');
            $('#validateData').attr("disabled", true);
        }
    }

</script>
<%--Tiny Editor--%>
<script src="<c:url value='/template/js/tinymce/tinymce.js'/>"></script>
<script src="<c:url value='/template/js/tinymce/init-template.js'/>"></script>
<script src="<c:url value='/template/js/manager/validate-template.js'/>"></script>
</body>
</html>
