<%@ page import="ubnd.core.dto.AttendeesDto" %>
<%@ page import="java.util.List" %>
<%@ page import="ubnd.core.dto.SpeakerDto" %><%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="addMemberUrl" value="/manager-session-detail.html">
    <c:param name="urlType" value="url_add_member"/>
    <c:param name="sessionId" value="${item.pojo.getSessionId()}"/>
</c:url>
<c:url var="sessionDetailUrl" value="/manager-session-detail.html">
    <c:param name="urlType" value="url_detail"/>
    <c:param name="sessionId" value="${item.pojo.getSessionId()}"/>
</c:url>
<c:url var="importAttendeesUrl" value="/manager-session-detail.html">
    <c:param name="urlType" value="url_import_attendees"/>
    <c:param name="sessionId" value="${item.pojo.getSessionId()}"/>
</c:url>
<html>
<%
//    List<AttendeesDto> attendeesDtoList = (List<AttendeesDto>) request.getAttribute("items");
//    List<SpeakerDto> speakerDtoList = (List<SpeakerDto>) request.getAttribute("listSpeaker");
%>
<head>
    <title>Quản lý phiên họp</title>
</head>
<body>

<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item">Phiên họp</li>
            <li class="breadcrumb-item active">Chi tiết</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->

<!-- Container fluid  -->
<div class="container-fluid">
    <!-- Start Page Content -->
    <div class="row">
        <div class="col-12">
            <!-- Info Meeting -->
            <div class="card">
                <div class="card-title">
                    <h4>Thông tin phiên họp</h4>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Tên phiên:</th>
                                <th>Địa điểm:</th>
                                <th>Mô tả</th>
                                <th>Bắt đầu lúc:</th>
                                <th>Kết thúc lúc:</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%--                            <c:forEach items="${infoMeeting}" var="info">--%>
                            <tr>
                                <td>${item.pojo.getName()}</td>
                                <td>${item.pojo.getRoomDto().getRoomName()}</td>
                                <td>${item.pojo.getDescription()}</td>
                                <td class="color-success">${item.getTimeStart()}</td>
                                <td class="color-warning">${item.getTimeEnd()}</td>
                            </tr>
                            <%--                            </c:forEach>--%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!-- End Info Meeting-->

            <!-- List -->
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Quản lý người tham dự</h4>
                    <h6 class="card-subtitle">Chọn thêm, xóa người tham dự</h6>
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-${alert} alert-dismissible fade show">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <strong>${messageResponse}</strong>
                        </div>
                    </c:if>
                    <%--alert--%>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Danh sách người tham dự</h5>
                            <button type="button" name="button" class="btn btn-outline-info"
                                    onclick="">
                                <i class="fa fa-magic"></i>&nbsp;
                                <span>Nhận dạng tự động</span>
                            </button>
                            <button type="button" name="button" class="btn btn-outline-success"
                                    onclick="location.href='${importAttendeesUrl}'">
                                <i class="fa fa-file-excel-o"></i>&nbsp;
                                <span>Tải lên từ Excel</span>
                            </button>
                            <div class="table-responsive mt-4">
                                <div id="list-attendees">

                                </div>
                            </div>
                        </div>
                    </div>
                    <h5 class="card-title">Danh sách người họp chưa thêm</h5>
                    <div class="table-responsive m-t-10">
                        <div id="list-speaker">

                        </div>
                        <form action="${sessionDetailUrl}" method="get" id="formUrl">
                            <input type="hidden" name="crudaction" id="crudaction"/>
                            <input type="hidden" name="urlType" id="urlType"/>
                            <input type="hidden" name="sessionId" id="sessionId" value="${item.pojo.getSessionId()}">
                        </form>
                    </div>

                </div>
            </div>
            <!-- End List -->
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog" data-backdrop="static" data-keyboard="false"></div>
<div class="modal fade" id="modalRemove" role="dialog" data-backdrop="static" data-keyboard="false"></div>

<script>
    document.title = "Chi tiết phiên họp";
    $(document).ready(function () {
        initTable();
    });

    function attendanceMember(btn) {
        let attendeesId = $(btn).attr('attendeesId');
        let status = $(btn).attr('status');
        $.ajax({
            type: 'post',
            url: '/ajax-session-edit.html',
            data: {
                urlType: 'url_attendance_member',
                attendeesId: attendeesId
            },
            success: function (res) {
                if(status == 0){
                    $('#spanStatus'+attendeesId).empty();
                    $('#spanStatus'+attendeesId).html('<span class="badge badge-info">Tham dự</span>\n');
                    $('#btnAttendance'+attendeesId).empty();
                    $('#btnAttendance'+attendeesId).html('<button type="button" name="submit" class="btn btn-secondary"\n' +
                        '                      onclick="attendanceMember(this)" attendeesId="'+attendeesId+'"\n' +
                        '                      data-tooltip="Không tham dự" status="2">\n' +
                        '                  <i class="fa fa-ban"></i>&nbsp;\n'+
                        '                   </button>\n');

                }else {
                    $('#spanStatus'+attendeesId).empty();
                    $('#spanStatus'+attendeesId).html('<span class="badge badge-secondary">Không tham dự</span>\n');
                    $('#btnAttendance'+attendeesId).empty();
                    $('#btnAttendance'+attendeesId).html('<button type="button" name="submit" class="btn btn-info"\n' +
                        '                      onclick="attendanceMember(this)" attendeesId="'+attendeesId+'"\n' +
                        '                      data-tooltip="Sẽ tham dự" status="0">\n' +
                        '                  <i class="fa fa-check"></i>&nbsp;\n'+
                        '                   </button>\n');
                }
            },
            error: function (res) {

            }
        });
    }

    function addAttendees(btn) {
        var speakerID = $(btn).attr('sp-id');
        var dutyID = $('#duty' + speakerID).val();
        var sessionID = '${sessionId}';
        let speakerName = $(btn).attr('speakerName');
        swal({
                title: "Xác nhận thêm?",
                text: speakerName + " vào danh sách tham dự",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#26ddaf",
                confirmButtonText: "Thêm",
                cancelButtonText: "Hủy",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function (isConfirm) {
                if (isConfirm) {
                    $.ajax({
                        type: 'post',
                        url: '/ajax-session-edit.html',
                        data: {
                            urlType: 'url_add_member',
                            speakerID: speakerID,
                            dutyID: dutyID,
                            sessionID: sessionID
                        },
                        success: function (res) {
                            // swal("Đã xóa !!", "Đã xóa người dùng: "+fullname, "success");
                            swal({
                                title: "Đã thêm!",
                                text: "Đã thêm " + speakerName,
                                type: "success"
                            }, function () {
                                initTable();
                                swal.close();
                            })
                        },
                        error: function (res) {
                            swal({
                                title: "Lỗi !",
                                text: "Không gửi được yêu cầu",
                                type: "error"
                            }, function () {
                                swal.close();
                            })
                        }
                    });
                } else {
                    swal("Đã hủy !!", "Hủy yêu cầu thêm", "error");
                }
            });
    }

    function removeAttendees(btn) {
        let attendeesId = $(btn).attr('attendeesId');
        let speakerName = $(btn).attr('speakerName');
        swal({
                title: "Xác nhận xóa ?",
                text: speakerName + " khỏi danh sách tham dự",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Xóa",
                cancelButtonText: "Hủy",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function (isConfirm) {
                if (isConfirm) {
                    $.ajax({
                        type: 'post',
                        url: '/ajax-session-edit.html',
                        data: {
                            urlType: 'url_remove_member',
                            attendeesId: attendeesId
                        },
                        success: function (res) {
                            // swal("Đã xóa !!", "Đã xóa người dùng: "+fullname, "success");
                            swal({
                                title: "Đã xóa !",
                                text: "Đã xóa : " + speakerName,
                                type: "success"
                            }, function () {
                                initTable();
                                swal.close();
                            })
                        },
                        error: function (res) {
                            swal({
                                title: "Lỗi !",
                                text: "Không gửi được yêu cầu",
                                type: "error"
                            }, function () {
                                swal.close();
                            })
                        }
                    });
                } else {
                    swal("Đã hủy !!", "Hủy yêu cầu xóa", "error");
                }
            });
    }

    function initTable() {
        let sessionId = '${sessionId}';
        $.ajax({
            url: "/ajax-session-edit.html",
            type: "get",
            data: {
                urlType: "url_get_member",
                sessionId: sessionId
            },
            success: function (data) {
                let arrJSON = typeof data != 'object' ? jQuery.parseJSON(data) : data;
                let listAttendees = arrJSON[0];
                let listSpeaker = arrJSON[1];
                setTableAttendees(listAttendees);
                setTableSpeaker(listSpeaker);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
            }
        });
    }

    function setTableAttendees(listAttendees) {
        let html = '<table id="table-attendees" class="table table-hover">\n' +
            '    <thead>\n' +
            '    <tr>\n' +
            '        <th>#</th>\n' +
            '        <th>Họ và tên</th>\n' +
            '        <th>Cơ quan</th>\n' +
            '        <th>Chức vụ</th>\n' +
            '        <th>Trạng thái</th>\n' +
            '        <th>Hành động</th>\n' +
            '    </tr>\n' +
            '    </thead>\n' +
            '    <tbody>';
        $.each(listAttendees, function (i, item) {
            i = i + 1;
            html += '<tr>\n' +
                '            <td>'+i+'</td>\n' +
                '            <td>'+item.speakerDto.fullName+'</td>\n' +
                '            <td>'+item.speakerDto.otherName+'</td>\n' +
                '            <td>'+item.dutyDto.dutyDescription+'</td>\n';
            if(item.status === 0){
                html += '    <td id="spanStatus'+item.attendeesId+'"><span class="badge badge-secondary">Không tham dự</span></td>\n';
            }else {
                html += '    <td id="spanStatus'+item.attendeesId+'"><span class="badge badge-info">Tham dự</span></td>\n';
            }
            html += '        <td>\n' +
                '                <div class="row float-right">'+
                '                <div class="col-auto float-right" id="btnAttendance'+item.attendeesId+'">\n';
            if(item.status === 0){
                html += '           <button type="button" name="submit" class="btn btn-info"\n' +
                    '                      onclick="attendanceMember(this)" attendeesId="'+item.attendeesId+'"\n' +
                    '                      data-tooltip="Sẽ tham dự" status="'+item.status+'">\n' +
                    '                  <i class="fa fa-check"></i>&nbsp;\n'+
                '                   </button>\n';
            }else {
                html += '           <button type="button" name="submit" class="btn btn-secondary"\n' +
                    '                       onclick="attendanceMember(this)" attendeesId="'+item.attendeesId+'"\n' +
                    '                       data-tooltip="Không tham dự" status="'+item.status+'">\n' +
                    '                   <i class="fa fa-ban"></i>&nbsp;\n'+
                    '               </button>\n';
            }

            html+= '             </div>\n'+
                '                <div class="col-auto float-right" id="btnRemove">\n'+
                '                   <button type="button" name="submit" class="btn btn-danger"\n' +
                '                           onclick="removeAttendees(this)" attendeesId="'+item.attendeesId+'"\n' +
                '                           data-tooltip="Xóa" speakerName="'+item.speakerDto.fullName+'">\n' +
                '                       <i class="fa fa-trash"></i>&nbsp;\n' +
                '                   </button>\n' +
                '               </div>\n'+
                '               </div>\n'+
                '            </td>\n' +
                '        </tr>';
        });
        html += '</tbody>\n' +
            '</table>';

        $('#list-attendees').empty();
        $('#list-attendees').html(html);
        $("#table-attendees").DataTable({
            "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
        });
    }

    function setTableSpeaker(listSpeaker) {
        let html = '<table id="table-speaker" class="table table-bordered table-striped">\n' +
            '    <thead>\n' +
            '    <tr>\n' +
            '        <th>STT</th>\n' +
            '        <th>Họ và tên</th>\n' +
            '        <th>Email</th>\n' +
            '        <th>Số điện thoại</th>\n' +
            '        <th>Cơ quan</th>\n' +
            '        <th>Chức vụ</th>\n' +
            '        <th>Hành động</th>\n' +
            '    </tr>\n' +
            '    </thead>\n' +
            '    <tbody>';
        $.each(listSpeaker, function (i, item) {
            i = i + 1;
            html += '<tr>\n' +
               '            <td>'+i+'</td>\n' +
               '            <td>'+item.fullName+'</td>\n' +
               '            <td>'+item.email+'</td>\n' +
               '            <td>'+item.phone+'</td>\n' +
               '            <td>'+item.otherName+'</td>\n' +
               '            <td>\n' +
               '                <select name="dutyId" id="duty'+item.speakerId+'" class="form-control">\n' +
               '                    <option value="3">Thành viên</option>\n' +
               '                    <option value="2">Thư ký</option>\n' +
               '                    <option value="1">Chủ tọa</option>\n' +
               '                </select>\n' +
               '            </td>\n' +
               '            <td>\n' +
               '                <button type="button" name="submit" class="btn btn-success" sp-id="'+item.speakerId+'"\n' +
               '                        onclick="addAttendees(this)" data-tooltip="Thêm" speakerName="'+item.fullName+'">\n' +
               '                    <i class="fa fa-plus"></i>&nbsp;\n' +
               '                </button>\n' +
               '            </td>\n' +
               '        </tr>';
        });
        html += '</tbody>\n' +
            '</table>';

        $('#list-speaker').empty();
        $('#list-speaker').html(html);
        $("#table-speaker").DataTable({
            "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
        });
    }

</script>

</body>
<%--<%!--%>
<%--    //kiem tra xem speaker da them vao phien hop hay chua--%>
<%--    private Boolean checkAddAttendees(int speakerID, List<AttendeesDto> attendeesDtoList) {--%>
<%--        Boolean check = false;--%>
<%--        for (int i = 0; i < attendeesDtoList.size(); i++) {--%>
<%--            if (speakerID == attendeesDtoList.get(i).getSpeakerDto().getSpeakerId()) {--%>
<%--                return true;--%>
<%--            }--%>
<%--        }--%>
<%--        return check;--%>
<%--    }--%>
<%--%>--%>
</html>














