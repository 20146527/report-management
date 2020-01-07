<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="editUserUrl" value="/ajax-user-edit.html">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:url var="uploadRecordUrl" value="/manager-upload-record.html">
    <c:param name="urlType" value="url_upload"/>
</c:url>
<html>
<head>
    <title>Ghi âm cuộc họp</title>
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
            <li class="breadcrumb-item">Âm thanh</li>
            <li class="breadcrumb-item active">Tải lên</li>
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
                    <div class="row">
                        <div class="col-11">
                            <h4 class="card-title">Upload file ghi âm</h4>
                        </div>
                        <div class="col-1">
                            <div class="float-right">
                                <button onclick="back()" id="btnBack" class="btn btn-outline-info" data-tooltip="Trở về">
                                    <i class="fa fa-arrow-left fa-lg"></i>&nbsp;
                                </button>
                            </div>
                        </div>
                    </div>
                    <h6 class="card-subtitle">Chọn cuộc họp và phiên họp trước khi upload</h6>
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
                    <button type="button" name="detail" class="btn btn-info"
                            onclick="testUpload()">
                        <i class="fa fa-upload"></i>&nbsp;
                        <span>TEST UPLOAD</span>
                    </button>

                    <div class="table-session">

                    </div>
                    <div class="table-meeting">
                        <h4>Danh sách cuộc họp</h4>
                        <table id="table-meeting" class="table table-hover">
                            <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên</th>
                                <th>Địa điểm</th>
                                <th>Thời gian bắt đầu</th>
                                <th>Thời gian kết thúc</th>
                                <th>Hành động</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${meetingList}" varStatus="loop" var="row">
                                <tr>
                                    <td>${row.getMeetingId()}</td>
                                    <td>${row.getName()}</td>
                                    <td>${row.getAddress()}</td>
                                    <td>${row.getTimeStart()}</td>
                                    <td>${row.getTimeEnd()}</td>
                                    <td>
                                        <button type="button" name="detail" class="btn btn-info"
                                                meetingId="${row.getMeetingId()}" onclick="chooseMeeting(this)"
                                                data-tooltip="Chọn cuộc họp">
                                            <i class="fa fa-asterisk"></i>&nbsp;
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="upload-panel">
                        <form action="${uploadRecordUrl}" method="post" enctype="multipart/form-data" class="form-valide">
                            <div class="form-group">
                                <label for="audioName">Tên file ghi âm: <span class="text-danger">*</span></label>
                                <div>
                                    <input type="text" id="audioName" name="audioName" class="form-control input-lg"
                                           value="${audioName}" placeholder="Vui lòng nhập tên file âm thanh">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="divInput" class="control-label" id="label-upload">Tải file ghi âm vào phiên: </label>
                                <div id="divInput">
                                    <input type="file" id="filer_input" name="file" >
                                </div>
                            </div>
                            <input type="hidden" name="urlType" value="url_upload"/>
                            <input type="hidden" name="sessionId" id="sessionId" >
                            <button type="submit" id="btnUpload" name="button" class="btn btn-block btn-info">
                                <i class="fa fa-upload"></i>
                                <span>Tải lên</span>&nbsp;
                            </button>
                        </form>

                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog"></div>
<script src="<c:url value='/template/js/input-file/inputFileAudioRecord.js'/>"></script>
<script src="<c:url value='/template/js/manager/validate-upload-record.js'/>"></script>
<script>
    document.title = "Upload ghi âm";
    $(document).ready(function () {
        $(".upload-panel").css({"display": "none"});
        $('#btnBack').hide();
        $("#table-meeting").DataTable({
            "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
        });
    });


    function chooseMeeting(btn) {
        let meetingId = $(btn).attr('meetingId');
        if(typeof meetingId != 'undefined'){
            var url = "/ajax-transcript.html?step=1";
            $.ajax({
                url: url,
                type: 'get',
                data: {
                    meetingId: meetingId
                },
                success: function (res) {
                    let listSession = jQuery.parseJSON(res);
                    $('.table-meeting').hide();
                    $('.table-session').show();
                    $('#btnBack').show();
                    $('.table-session').empty();
                    let table = '<h4>Danh sách phiên họp</h4>'+
                        '<table id="table-session" class="table table-hover">\n' +
                        '            <thead>\n' +
                        '            <tr>\n' +
                        '                <th>STT</th>\n' +
                        '                <th>Tên</th>\n' +
                        '                <th>Địa điểm</th>\n' +
                        '                <th>Thời gian bắt đầu</th>\n' +
                        '                <th>Thời gian kết thúc</th>\n' +
                        '                <th>Hành động</th>\n' +
                        '             </tr>\n' +
                        '             </thead>\n' +
                        '             <tbody>'
                    $.each(listSession, function (i, item) {
                        table += '<tr>\n' +
                            '          <td>'+i+'</td>\n' +
                            '          <td>'+item.name+'</td>\n' +
                            '          <td>'+item.roomDto.roomName+'</td>\n' +
                            '          <td>'+item.timeStart+'</td>\n' +
                            '          <td>'+item.timeEnd+'</td>\n' +
                            '          <td>\n' +
                            '               <button type="button" name="detail" class="btn btn-info"\n' +
                            '                       sessionId="'+item.sessionId+'" onclick="chooseSession(this)"\n' +
                            '                       sessionName="'+item.name+'" data-tooltip="Chọn phiên họp">\n' +
                            '                       <i class="fa fa-asterisk"></i>&nbsp;\n' +
                            '               </button>\n' +
                            '          </td>\n' +
                            '     </tr>';
                    })
                    table += '</tbody>\n' +
                        '</table>';
                    $('.table-session').html(table);
                    //console.log(res);
                },
                error: function (res) {
                    console.log(res);
                }
            });
        }

    }

    function chooseSession(btn) {

        let sessionId = $(btn).attr('sessionId');
        let sessionName = $(btn).attr('sessionName');
        $('.table-session').hide();
        $('#label-upload').text("Tải file ghi âm vào phiên: "+sessionName);
        $('#sessionId').val(sessionId);
        $(".upload-panel").css({"display": "block"});

    }

    function back() {
        $('.table-session').hide();
        $(".upload-panel").hide();
        $('.table-meeting').show();
        $('#btnBack').hide();
    }

    function testUpload() {
        $.ajax({
            type: 'get',
            url: '/ajax-upload-record.html',
            data: {
                urlType: 'url_upload'
            },
            success: function (res) {
                console.log(res);
            },
            error: function (res) {
                console.log(res);
            }
        });
    }

</script>


</body>
</html>
