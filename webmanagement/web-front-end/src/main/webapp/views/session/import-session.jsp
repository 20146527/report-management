<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="validateExcel" value="/manager-session-import.html">
    <c:param name="meetingId" value="${meetingId}"/>
</c:url>
<c:url var="dowloadTemplate" value="/manager-session-detail.html">
    <c:param name="urlType" value="download_template_session"/>
</c:url>
<html>
<head>
    <title>Quản lý phiên họp</title>
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
            <li class="breadcrumb-item">Phiên họp</li>
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
            <!-- Info upload -->
            <div class="card">
                <div class="card-title">
                    <h4>Nhập dữ liệu phiên họp từ tệp Excel</h4>
                </div>
                <div class="mr-1 ml-1 mt-2 mb-2">Nhấn <a href="${dowloadTemplate}" style="color: #0d71bb">vào đây</a> để tải file mẫu Excel
                <div class="card-body">
                    <form action="${validateExcel}" method="post" enctype="multipart/form-data" id="importSessionForm">
                        <div class="form-group">
<%--                            <label for="divInput" class=" control-label">Tải lên phiên họp từ file .xlsx</label>--%>
                            <div id="divInput">
                                <input type="file" id="filer_input" name="file" >
                            </div>
                        </div>
                        <input type="hidden" name="urlType" id="urlType"/>
                        <input type="hidden" name="id" id="id" value="${meetingId}">
                        <button type="button" id="validateData" class="btn btn-block btn-primary">
                            <i class="fa fa-upload"></i>
                            <span>Kiểm tra dữ liệu</span>&nbsp;
                        </button>
                    </form>
                </div>
            </div>
            <!-- End Info Meeting-->

            <!-- List -->
            <c:if test="${not empty items}">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Kiểm tra lại thông tin phiên họp</h4>
                            <%--alert--%>
                        <c:if test="${not empty messageResponse}">
                            <div class="alert alert-${alert} alert-dismissible fade show">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <strong>${messageResponse}</strong>
                            </div>
                        </c:if>
                            <%--alert--%>
                        <div class="table-responsive">
                            <div class="table-responsive m-t-10">
                                <table id="myTable" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th>Tên</th>
                                        <th>Địa điểm</th>
                                        <th>Thời gian bắt đầu</th>
                                        <th>Thời gian kết thúc</th>
                                        <th>Mô tả</th>
                                    </tr>
                                    </thead>
                                    <tfoot>
                                    <tr>
                                        <th>Tên</th>
                                        <th>Địa điểm</th>
                                        <th>Thời gian bắt đầu</th>
                                        <th>Thời gian kết thúc</th>
                                        <th>Mô tả</th>
                                    </tr>
                                    </tfoot>
                                    <tbody>
                                    <c:forEach items="${items}" varStatus="loop" var="row">
                                        <tr>
                                            <td>${row.getName()}</td>
                                            <td>${row.getAddress()}</td>
                                            <td>${row.getTimeStart()}</td>
                                            <td>${row.getTimeEnd()}</td>
                                            <td>${row.getDescription()}</td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <button type="button" id="importData" class="btn btn-primary">Lưu lại</button>
                    </div>
                </div>
            </c:if>
            <!-- End List -->
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog" data-backdrop="static" data-keyboard="false"></div>
<script src="<c:url value='/template/js/input-file/inputFileExcelSession.js'/>"></script>
<script>
    document.title = "Nhập dữ liệu excel";
    $(document).ready(function () {
        $('#validateData').click(function () {
            $('#urlType').val('read_excel_session');
            $('#importSessionForm').submit();
        });
        $('#importData').click(function () {
            $('#urlType').val('import_session_data');
            $('#importSessionForm').prop('enctype', false);
            $('#importSessionForm').submit();
        });

    });

    function inputNameFile() {
        var x = document.getElementById("inputFile");
        if(x.files.length == 1){
            //chon file sai
            var file = x.files[0];
            console.log(file.name);
            //$('#inputFile').val("File: ");
            var nameFile = file.name;
            document.getElementById("lable-name-file-audio").innerHTML = nameFile;
            //document.getElementById("inputFile").value = "Johnny Bravo";
        }else {
            //chi chon 1 file
            console.log('chon nhieu file');
            $('#inputFile').val('Chỉ chọn 1 file');
        }
    }

</script>

</body>
</html>














