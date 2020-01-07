<%@ page import="com.google.gson.JsonObject" %>
<%@ page import="org.json.JSONArray" %><%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 27/07/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="editMeetingUrl" value="/ajax-meeting-edit.html">
    <c:param name="urlType" value="url_edit"/>
    <c:param name="viewType" value="calendar"/>
</c:url>
<html>
<%
    JSONArray array = (JSONArray) request.getAttribute("data-calendar");
%>
<head>
    <title>Tạo cuộc họp</title>
</head>
<body>
<link href="<c:url value="/template/css/calendar/core/main.min.css"/>" rel="stylesheet">
<link href="<c:url value="/template/css/calendar/daygrid/main.min.css"/>" rel="stylesheet">
<link href="<c:url value="/template/css/calendar/timegrid/main.min.css"/>" rel="stylesheet">
<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item">Cuộc họp</li>
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

            <!-- Calendar -->
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Lịch cuộc họp</h4>
                    <%--alert--%>
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-${alert} alert-dismissible fade show">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <strong>${messageResponse}</strong>
                        </div>
                    </c:if>
                    <%--alert--%>
                    <div class="row">
                        <div class="col-lg-6 col-md-12">
                            <button type="button" name="button" onclick="update(this)"
                                    class="btn btn-outline-info pull-left">
                                <i class="fa fa-plus"></i>&nbsp;
                                <span>Thêm mới cuộc họp</span>
                            </button>
                        </div>
                        <div class="col-lg-6 col-md-12">
                            <c:url var="tableMeetingUrl" value="/manager-meeting-list.html">
                                <c:param name="urlType" value="url_list"/>
                                <c:param name="viewType" value="table"/>
                            </c:url>
                            <button type="button" name="button" onclick="location.href='${tableMeetingUrl}'"
                                    class="btn btn-outline-warning pull-right">
                                <i class="fa fa-list"></i>&nbsp;
                                <span>Xem dạng bảng</span>
                            </button>
                        </div>
                    </div>

                    <div id="calendar"> </div>
                </div>
            </div>
            <!-- Calendar -->
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog" data-backdrop="static" data-keyboard="false"></div>
<div class="modal fade" id="modalRemove" role="dialog" data-backdrop="static" data-keyboard="false"></div>


<script>
    document.title = "Quản lý cuộc họp";
    $(document).ready(function () {
        var data = <%= array %>;
        initCalendar(data);
        //searchMeeting();
        //$('#myModal').modal({backdrop: 'static', keyboard: false});
    });

    function update(btn) {
        var editUrl = $(btn).attr('sc-url');
        if (typeof editUrl == 'undefined') {
            editUrl = '${editMeetingUrl}';
        }
        $("#overlay").css({"display": "block"});
        $('#myModal').load(editUrl, '', function () {
            $("#overlay").css({"display": "none"});
            $('#myModal').modal('toggle');
            //createOrEdit();
        });
    }

    function initCalendar(data) {
        var calendarEl = document.getElementById('calendar');

        var calendar = new FullCalendar.Calendar(calendarEl, {
            plugins: ['interaction', 'dayGrid', 'timeGrid'],
            defaultView: 'dayGridMonth',
            defaultDate: '${defaultDate}',
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'dayGridMonth,timeGridWeek,timeGridDay'
            },
            locale: 'vi',
            selectable: true,
            select: function (info) {
                //alert('selected ' + info.startStr + ' to ' + info.endStr);
                var createURL = "/ajax-meeting-edit.html?urlType=url_edit&timeStart="+info.startStr+"&timeEnd="+info.endStr+"&viewType=calendar&inputType="+info.view.type;
                $('#myModal').load(createURL, '', function () {
                    $('#myModal').modal('toggle');
                    //In Modal
                    $('#btnSave').click(function () {
                        $('#editMeetingForm').submit();
                    });
                    $('#editMeetingForm').submit(function (e) {
                        e.preventDefault();
                        $('#crudactionEdit').val('insert_update');
                        $.ajax({
                            type: $(this).attr('method'),
                            url: $(this).attr('action'),
                            data: $(this).serialize(),
                            success: function (res) {
                                var data = jQuery.parseJSON(res);
                                console.log(data.id+"---"+data.title);
                                $('#myModal').modal('hide');
                                calendar.addEvent({
                                    id: data.id,
                                    title: data.title,
                                    start: data.start,
                                    end: data.end
                                });
                                $('.alert-dismissible').css({"display": "none"});
                            },
                            error: function (res) {
                                console.log(res);
                            }
                        })
                    });
                });
                // https://stackoverflow.com/questions/21204305/rerendering-events-in-fullcalendar-after-ajax-database-update
            },
            eventClick: function (info) {
                var editURL = "/ajax-meeting-edit.html?urlType=url_edit&pojo.meetingId="+info.event.id+"&viewType=calendar";
                $('#myModal').load(editURL, '', function () {
                    $('#myModal').modal('toggle');
                    //In Modal
                    $('#btnSave').click(function () {
                        $('#editMeetingForm').submit();
                    });
                    $('#editMeetingForm').submit(function (e) {
                        e.preventDefault();
                        $('#crudactionEdit').val('insert_update');
                        $.ajax({
                            type: $(this).attr('method'),
                            url: $(this).attr('action'),
                            data: $(this).serialize(),
                            success: function (res) {
                                var data = jQuery.parseJSON(res);
                                console.log(data.id+"---"+data.title);
                                $('#myModal').modal('hide');
                                var event = calendar.getEventById(info.event.id);
                                event.remove();
                                calendar.addEvent({
                                    id: data.id,
                                    title: data.title,
                                    start: data.start,
                                    end: data.end
                                });
                                $('.alert-dismissible').css({"display": "none"});
                            },
                            error: function (res) {
                                console.log(res);
                            }
                        })
                    });
                });
                // change the border color just for fun
                info.el.style.borderColor = 'red';
            },
            events: data
        });

        calendar.render();
    }



</script>
<%--Calendar--%>
<script src="<c:url value='/template/js/calendar/core/main.min.js'/>"></script>
<script src="<c:url value='/template/js/calendar/interaction/main.min.js'/>"></script>
<script src="<c:url value='/template/js/calendar/daygrid/main.min.js'/>"></script>
<script src="<c:url value='/template/js/calendar/timegrid/main.min.js'/>"></script>
<script src="<c:url value='/template/js/calendar/locale/locales-all.js'/>"></script>
</body>
</html>
