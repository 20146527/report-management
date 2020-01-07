<%--
  Created by IntelliJ IDEA.
  User: HungPhan
  Date: 10/2/2019
  Time: 9:31 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>

<c:url var="searchFileSteno" value="/steno-file-manager.html"/>

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

<div class="container-fluid">


    <!-- Search -->
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Tìm kiếm</h4>
            <h6 class="card-subtitle">Nhập thông tin tìm kiếm</h6>
            <form method="get" action="${searchFileSteno}">
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
                            <label for="tag" class="control-label">Tên biên bản</label>
                            <input type="text" class="form-control" id="tag" name="nameFileSteno"
                                   value="${nameFileSteno}"
                                   placeholder="Nhập tên biên bản">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="user-create" class=" control-label">Người tạo</label>
                            <select name="createUser" id="user-create" class="form-control">
                                <option value="0">Chọn người tạo...</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-12">
                        <div class="form-group">
                            <label class="control-label">Tạo biên bản từ</label>
                            <input type="text" class="form-control"
                                   id="search-report-time-start" value="${timeStart}"
                                   name="timeStart" placeholder="Chọn thời gian">
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-12">
                        <div class="form-group">
                            <label class="control-label">Tạo biên bản đến</label>
                            <input type="text" class="form-control"
                                   id="search-report-time-end" value="${timeEnd}"
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
                        <table id="list-file-steno" class="table table-striped table-bordered" style="width:100%">

                            <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên file</th>
                                <th>Tên cuộc họp</th>
                                <th>Tên phiên họp</th>
                                <th>Người sửa</th>
                                <th>Thời gian</th>
                                <th>Hành động</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:set var="index" value="0"/>
                            <jsp:useBean id="listData" scope="request"
                                         type="java.util.List<ubnd.core.data.obj.StenographyObject>"/>
                            <c:forEach items="${listData}" varStatus="loop" var="tempt">
                                <c:set var="index" value="${index + 1}"/>
                            <tr>
                                <td class="index">${index}</td>
                                <td class="name-file">${tempt.nameFile}</td>
                                <td class="name-meeting">${tempt.meetingName}</td>
                                <td class="name-session">${tempt.sessionName}</td>
                                <td class="name-edit">${tempt.modUID}</td>
                                <td class="time-edit">${tempt.modDate}</td>
                                <td class="action">

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


</div>


<script>
    let changeData = true;
    $(document).ready(function () {

        $("#list-file-steno").DataTable({
            "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
        });

        $('#search-report-time-start').datetimepicker({
            format: 'DD/MM/YYYY hh:mm A'
        });

        $('#search-report-time-end').datetimepicker({
            format: 'DD/MM/YYYY hh:mm A'
        });


        $('#meeting').val('0');
        $('#session').prop('disabled', true);

        getMeeting();
        getSessionByMeeting();
        getUserBySession();
        getListUserCreateByMeetingID(-1);

    });

    function getMeeting() {
        let url = '/ajax-report-edit.html';
        $.ajax({
            url: url,
            type: 'get',
            data: {
                urlType: 'ajax_meeting_session'
            },
            success: function (res) {
                let listMeeting = jQuery.parseJSON(res);
                if (listMeeting.length !== 0) {
                    $('#meeting').empty().append(new Option("Chọn cuộc họp ...", 0));
                    $.each(listMeeting, function (i, item) {
                        $("#meeting").append(new Option(item.name, item.meetingId));
                    });
                }

                let meetingID = '${meetingId}';
                if (meetingID !== '' && parseInt(meetingID) !== 0 && changeData) {
                    $('#meeting').val(meetingID);
                }
                if (meetingID !== '' && parseInt(meetingID) !== 0 && changeData) {
                    ajaxGetSession(meetingID);
                    let sessionId = '${sessionId}';
                    if (sessionId === '' && parseInt(sessionId) === 0) {
                        getListUserCreateByMeetingID(meetingID);
                    }
                }


            },
            error: function (res) {
                console.log(res);
            }
        });
    }


    function getUserBySession() {
        $('#session').on('change', function (e) {
            let optionSelected = $("option:selected", this);
            let valueSelected = optionSelected.val();
            if (valueSelected !== 0) {
                changeData = false;
                getListUserCreateBySessionID(valueSelected);
            }
        })
    }

    function getSessionByMeeting() {
        $('#meeting').on('change', function (e) {
            let optionSelected = $("option:selected", this);
            let valueSelected = optionSelected.val();
            if (valueSelected !== 0) {
                changeData = false;
                ajaxGetSession(valueSelected);
                getListUserCreateByMeetingID(valueSelected);
            }
        })
    }

    function ajaxGetSession(meetingId) {
        var url = "/ajax-report-edit.html";
        $.ajax({
            url: url,
            type: 'get',
            data: {
                urlType: 'ajax_meeting_session',
                meetingId: meetingId
            },
            success: function (res) {
                let listSession = jQuery.parseJSON(res);
                if (listSession.length !== 0) {
                    $('#session').prop('disabled', false);
                    $('#session').empty().append(new Option("Chọn phiên...", 0));

                    $.each(listSession, function (i, item) {
                        $("#session").append(new Option(item.name, item.sessionId));
                    });

                    let sessionId = '${sessionId}';
                    if (sessionId !== '' && parseInt(sessionId) !== 0 && changeData) {
                        $('#session').val(sessionId);
                        getListUserCreateBySessionID(sessionId);
                    }

                } else {
                    $('#session').prop('disabled', true);
                }
            },
            error: function (res) {
                console.log(res);
            }
        });
    }


    function getListUserCreateByMeetingID(meetingId) {
        $.ajax({
            url: "/steno-file-manager.html",
            type: 'POST',
            data: {
                type: 'getUserCreateInMeeting',
                meetingId: meetingId
            },
            success: function (res) {
                $("#user-create").empty().append(new Option("Chọn người tạo ...", 0));
                $.parseJSON(res).forEach(function (item) {
                    $("#user-create").append(new Option(item.userName, item.id));
                });
                let createUser = '${createUser}';
                if (createUser !== '' && parseInt(createUser) !== 0 && changeData) {
                    $('#user-create').val(createUser);
                }
            }
        });
    }

    function getListUserCreateBySessionID(sessionID) {
        $.ajax({
            url: "/steno-file-manager.html",
            type: 'POST',
            data: {
                type: 'getUserCreateInSession',
                sessionID: sessionID
            },
            success: function (res) {
                $("#user-create").empty().append(new Option("Chọn người tạo ...", 0));
                $.parseJSON(res).forEach(function (item) {
                    $("#user-create").append(new Option(item.userName, item.id));
                });

                let createUser = '${createUser}';
                if (createUser !== '' && parseInt(createUser) !== 0 && changeData) {
                    $('#user-create').val(createUser);
                }
            }
        });
    }


</script>

</body>
</html>
