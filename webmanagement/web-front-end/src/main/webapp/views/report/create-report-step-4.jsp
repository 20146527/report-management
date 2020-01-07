<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="editUserUrl" value="/ajax-user-edit.html">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:url var="listUserUrl" value="/manager-user-list.html">
    <c:param name="urlType" value="url_list"/>
</c:url>
<html>
<head>
    <title>Danh sách người dùng</title>
</head>
<body>

<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item">Biên bản</li>
            <li class="breadcrumb-item active">Tạo biên bản</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->

<!-- Container fluid  -->
<div class="container-fluid">
    <!-- Start Page Content -->
    <div class="row">
        <div class="col-12">

            <div class="card">
                <div class="card-body">
                    <div class="card-title">
                        <h3 class="text-center">Bước 4/x: Chọn dữ liệu tốc ký</h3>
                        <hr>
                        <%--alert--%>
                        <c:if test="${not empty messageResponse}">
                            <div class="alert alert-${alert} alert-dismissible fade show">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <strong>${messageResponse}</strong>
                            </div>
                        </c:if>
                        <%--alert--%>
                        <div>
                            <form>
                                <div class="custom-file mb-3">
                                    <input type="file" class="custom-file-input input-lg" id="inputFile">
                                    <label class="custom-file-label" id="lable-name2">Nhấp vào đây sẽ bật ra các tất cả file tốc ký của cuộc họp</label>
                                </div>
                                <div class="mt-2">
                                    <div class="row">
                                        <div class="col-6">
                                            <div class="form-group">
                                                <label for="output">Văn bản:</label>
                                                <textarea class="form-control" rows="10" id="output"></textarea>
                                            </div>
                                        </div>

                                        <div class="col-6">
                                            <div class="form-group">
                                                <label for="verticalNotes">Tốc ký:</label>
                                                <textarea class="form-control" rows="10" wrap="off" id="verticalNotes"></textarea>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </form>
                        </div>
                        <hr>
                        <div>
                            <button type="submit" name="submit"
                                    onclick="location.href='/report-create.html?step=5'"
                                    class="btn btn-lg btn-info btn-block">
                                <i class="fa fa-step-forward fa-lg"></i>&nbsp;
                                <span>Tiếp theo</span>
                            </button>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog"></div>


<script>
    document.title = "Tạo biên bản";
    $(document).ready(function () {
        //$('#myModal').modal({backdrop: 'static', keyboard: false});
    });

</script>
<!-- wavesurfer.js -->
<script src="<c:url value='/template/js/wavesurfer/wavesurfer.js'/>"></script>

<!-- regions plugin -->
<script src="<c:url value='/template/js/wavesurfer/wavesurfer.regions.js'/>"></script>

<!-- ELAN format renderer -->
<script src="<c:url value='/template/js/wavesurfer/wavesurfer.elan.js'/>"></script>

<!-- App -->
<script src="<c:url value='/template/js/wavesurfer/app.js'/>"></script>

<script src="<c:url value='/template/js/wavesurfer/trivia.js'/>"></script>

<script src="<c:url value="../../template/js/steno/ploverdemo.js"/>"></script>

</body>
</html>
