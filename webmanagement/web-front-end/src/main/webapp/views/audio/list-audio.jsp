<%--
  Created by IntelliJ IDEA.
  User: HungPhan
  Date: 10/2/2019
  Time: 9:31 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="uploadAudioUrl" value="/manager-audio.html">
    <c:param name="urlType" value="url_upload"/>
</c:url>
<html>
<head>
    <title>Title</title>
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
            <li class="breadcrumb-item active">Quản lý file ghi âm</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->

<div class="container-fluid">


    <!-- Search -->
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Tìm kiếm</h4>
            <h6 class="card-subtitle">Nhập thông tin tìm kiếm</h6>
            <form method="get" action="">
                <input hidden name="type" value="searchFileSteno">
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="meeting" class=" control-label">Cuộc họp</label>
                            <select name="meetingId" id="meeting" class="form-control">
                                <option value="0">Chọn cuộc họp...</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="session" class="control-label">Phiên họp</label>
                            <select name="sessionId" id="session" class="form-control">
                                <option value="0">Chọn phiên họp...</option>

                            </select>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="tag" class="control-label">Tên file</label>
                            <input type="text" class="form-control" id="tag" name="nameFileSteno"
                                   value=""
                                   placeholder="Nhập tên biên bản">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="user-create" class=" control-label">Trạng thái</label>
                            <select name="createUser" id="user-create" class="form-control">
                                <option value="0">Chọn trạng thái...</option>
                                <option value="1">Chưa xử lý</option>
                                <option value="4">Đang xử lý</option>
                                <option value="5">Đã xử lý</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-12">
                        <div class="form-group">
                            <label class="control-label">Tạo file từ</label>
                            <input type="text" class="form-control"
                                   id="search-time-start" value="${timeStart}"
                                   name="timeStart" placeholder="Chọn thời gian">
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-12">
                        <div class="form-group">
                            <label class="control-label">Tạo file đến</label>
                            <input type="text" class="form-control"
                                   id="search-time-end" value="${timeEnd}"
                                   name="timeEnd" placeholder="Chọn thời gian">
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label class="control-label">Hành động</label><br>
                            <button type="submit"
                                    class="btn btn-warning btn-rounded m-b-10 m-l-5 btn-block">
                                <i class="fa fa-search"></i> Tìm kiếm
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!-- End Search -->


    <div class="card">
        <div class="card-body">
            <%--alert--%>
            <c:if test="${not empty messageResponse}">
                <div class="alert alert-${alert} alert-dismissible fade show">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <strong>${messageResponse}</strong>
                </div>
            </c:if>
            <%--alert--%>
            <ul class="nav nav-pills m-t-30 m-b-30">
                <li class="nav-item"><a href="#navpills-list-file" class="nav-link active" id="tab-1"
                                        data-toggle="tab"
                                        aria-expanded="false">Danh sách</a></li>

                <li class="nav-item"><a href="#navpills-statistic" class="nav-link" id="tab-2"
                                        data-toggle="tab"
                                        aria-expanded="false">Thống kê</a></li>

            </ul>

            <div class="tab-content br-n pn">

                <div id="navpills-list-file" class="tab-pane active">
                    <div class="table-responsive">
                        <table id="list-file-audio" class="table table-striped table-bordered" style="width:100%">
                            <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên file</th>
                                <th>Tên cuộc họp</th>
                                <th>Tên phiên họp</th>
                                <th>Trạng thái</th>
                                <th>Hành động</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:set var="index" value="0"/>
                            <c:forEach items="${items}" varStatus="loop" var="row">
                                <c:set var="index" value="${index + 1}"/>
                            <tr>
                                <td class="index">${index}</td>
                                <td>${row.getName()}</td>
                                <td>${row.getSessionDto().getMeetingDto().getName()}</td>
                                <td>${row.getSessionDto().getName()}</td>
                                <td>
                                    <c:if test="${row.getStatus() eq '1'}">
                                        <span class="badge badge-danger">Chưa xử lý</span>
                                    </c:if>
                                    <c:if test="${row.getStatus() eq '4'}">
                                        <span class="badge badge-warning">Đang xử lý</span>
                                    </c:if>
                                    <c:if test="${row.getStatus() eq '5'}">
                                        <span class="badge badge-success">Đã xử lý</span>
                                    </c:if>
                                </td>
                                <td>
                                    <button type="button" name="submit" class="btn btn-info"
                                            onclick=""
                                            data-tooltip="Chi tiết bóc băng">
                                        &nbsp;<i class="fa fa-asterisk"></i>
                                    </button>
                                    <button type="button" name="submit" class="btn btn-danger"
                                            audioId="${row.getRecordId()}" audioName="${row.getName()}"
                                            onclick="" data-tooltip="Xóa">
                                        <i class="fa fa-trash"></i>&nbsp;
                                    </button>
                                </td>
                            </tr>
                            </c:forEach>

                        </table>
                    </div>
                </div>

                <div id="navpills-statistic" class="tab-pane">
                    <%@include file="/common/views/empty.jsp" %>
                </div>

            </div>


        </div>
    </div>

    <div class="fixed-action-btn-custom" data-tooltip="Tải lên file" onclick="showUploadAudio()">
        <div class="btn-floating-custom btn-large-custom red">
            <i class="fa fa-cloud-upload large-custom" aria-hidden="true"></i>
        </div>
    </div>
</div>
<div class="modal fade" id="modal-upload" role="dialog" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-xl" role="document">
        <div class="modal-content">
            <!--Header-->
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Upload file âm thanh</h5>
            </div>
            <!--Body-->
            <div class="modal-body">
                <form action="${uploadAudioUrl}" method="post" enctype="multipart/form-data" class="form-valide">
                    <div class="form-group">
                        <label for="audioName">Tên file ghi âm: <span class="text-danger">*</span></label>
                        <div>
                            <input type="text" id="audioName" name="audioName" class="form-control input-lg"
                                   value="${audioName}" placeholder="Vui lòng nhập tên file âm thanh">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="meetingUpload" class=" control-label">Chọn cuộc họp</label>

                        <select name="meetingId" id="meetingUpload" class="form-control">
                            <option value="0">Chọn cuộc họp...</option>

                        </select>
                    </div>
                    <div class="form-group">
                        <label for="sessionUpload" class=" control-label">Chọn phiên họp</label>

                        <select name="session" id="sessionUpload" class="form-control">
                            <option value="0">Chọn phiên...</option>

                        </select>
                    </div>
                    <div class="form-group">
                        <label for="divInput" class="control-label" id="label-upload">Tải file ghi âm vào phiên: </label>
                        <div id="divInput">
                            <input type="file" id="filer_input" name="file" >
                        </div>
                    </div>
                    <input type="hidden" name="urlType" value="url_upload"/>

                    <button type="submit" id="btnUpload" name="button" class="btn btn-block btn-info">
                        <i class="fa fa-upload"></i>
                        <span>Tải lên</span>&nbsp;
                    </button>
                </form>
            </div>
            <!--Footer-->
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Close</button>
            </div>
            <script src="<c:url value='/template/js/input-file/inputFileAudioRecord.js'/>"></script>
        </div>
    </div>
</div>

<script src="<c:url value='/template/js/manager/validate-search-time.js'/>"></script>
<script>
    document.title = "Quản lý âm thanh";
    $(document).ready(function () {

        $("#list-file-audio").DataTable({
            "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
        });
        $('#search-time-start').datetimepicker({
            format: 'DD/MM/YYYY hh:mm A'
        });
        $('#search-time-end').datetimepicker({
            format: 'DD/MM/YYYY hh:mm A'
        });

        choseMeetingUpload();
    });

    function showUploadAudio() {
        $("#modal-upload").modal('show');
        initMeetingUpload();
    }

    function initMeetingUpload() {
        $("#overlay").css({"display": "block"});
        $.ajax({
            url: '/ajax-query.html',
            type: 'get',
            data: {
                type: 'get_all_meeting'
            },
            success: function (res) {
                $("#overlay").css({"display": "none"});
                let listMeeting = jQuery.parseJSON(res);
                if (listMeeting.length !== 0) {
                    $('#meetingUpload').empty().append(new Option("Chọn cuộc họp...", 0));
                    $.each(listMeeting, function (i, item) {
                        $("#meetingUpload").append(new Option(item.name, item.meetingId));
                    });
                } else {
                    $('#meetingUpload').prop('disabled', true);
                }

            },
            error: function (res) {
                console.log(res);
            }
        })
    }

    function choseMeetingUpload() {
        $('#meetingUpload').on('change', function (e) {
            let optionSelected = $("option:selected", this);
            let valueSelected = optionSelected.val();
            if(valueSelected !== 0){
                $("#overlay").css({"display": "block"});
                $.ajax({
                    url: '/ajax-query.html',
                    type: 'get',
                    data: {
                        type: 'get_session_by_meetingId',
                        meetingId: valueSelected
                    },
                    success: function (res) {
                        $("#overlay").css({"display": "none"});
                        let listSession = jQuery.parseJSON(res);
                        if(listSession.length !== 0){
                            $('#sessionUpload').prop('disabled', false);
                            $('#sessionUpload').empty().append(new Option("Chọn phiên...", 0));
                            $.each(listSession, function (i, item) {
                                $("#sessionUpload").append(new Option(item.name, item.sessionId));
                            });
                        }else {
                            $('#sessionUpload').prop('disabled', true);
                        }
                    },
                    error: function (res) {
                        console.log(res);
                    }
                });
            }
        })
    }

</script>

</body>
</html>
