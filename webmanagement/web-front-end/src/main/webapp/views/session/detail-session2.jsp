<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<%--<c:url var="editAttendeesUrl" value="/ajax-attendees-edit.html">--%>
<%--    <c:param name="urlType" value="url_attendees_edit"/>--%>
<%--</c:url>--%>
<%--<c:url var="addMemberUrl" value="/ajax-attendees-edit.html">--%>
<%--    <c:param name="urlType" value="add_member"/>--%>
<%--</c:url>--%>
<html>
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
                                <td>${item.getName()}</td>
                                <td>${item.getAddress()}</td>
                                <td>${item.getDescription()}</td>
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
                    <%--                    <h4 class="card-title">Chi tiết phiên họp</h4>--%>
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
                        <li class=" nav-item"><a href="#navpills-1" class="nav-link active" data-toggle="tab"
                                                 aria-expanded="false">Người họp</a></li>
                        <li class="nav-item"><a href="#navpills-2" class="nav-link" data-toggle="tab"
                                                aria-expanded="false">Ghi âm</a></li>
                        <li class="nav-item"><a href="#navpills-3" class="nav-link" data-toggle="tab"
                                                aria-expanded="true">Tốc ký</a></li>
                    </ul>
                    <div class="tab-content br-n pn">
                        <div id="navpills-1" class="tab-pane active">
                            <div class="card">
                                <div class="card-body">
                                    <button type="button" name="button" class="btn btn-info"
                                            onclick="">
                                        <i class="fa fa-magic"></i>&nbsp;
                                        <span>Nhận dạng tự động</span>
                                    </button>
                                    <div class="table-responsive mt-4">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Họ và tên</th>
                                                <th>Cơ quan</th>
                                                <th>Chức vụ</th>
                                                <th>Hành động</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${items}" varStatus="loop" var="row">
                                                <tr>
                                                    <td>${row.getAttendeesId()}</td>
                                                    <td>${row.getSpeakerDto().getFullName()}</td>
                                                    <td>${row.getSpeakerDto().getOtherName()}</td>
                                                    <td>${row.getDutyDto().getDutyDescription()}</td>
                                                    <td>
                                                        <button type="button" name="submit" class="btn btn-success"
                                                                data-toggle="tooltip" title="Sửa">
                                                            <i class="fa fa-pencil-square-o"></i>&nbsp;
                                                        </button>
                                                        <button type="button" name="submit" class="btn btn-danger"
                                                                data-toggle="tooltip" title="Xóa">
                                                            <i class="fa fa-times"></i>&nbsp;
                                                        </button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="table-responsive m-t-40">
                                <table id="myTable" class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th>Họ và tên</th>
                                        <th>Email</th>
                                        <th>Số điện thoại</th>
                                        <th>Cơ quan</th>
                                        <th>Chức vụ</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${listSpeaker}" varStatus="loop" var="row">
                                        <tr>
                                            <td>${row.getFullName()}</td>
                                            <td>${row.getEmail()}</td>
                                            <td>${row.getPhone()}</td>
                                            <td>${row.getOtherName()}</td>
                                            <td>
                                                <select name="dutyId" id="duty${row.getSpeakerId()}" class="form-control">
                                                    <option value="0">Thành viên</option>
                                                    <option value="1">Thư ký</option>
                                                    <option value="2">Chủ tọa</option>
                                                </select>
                                            </td>
                                            <td>
                                                <input type="hidden" name="speakerId" id="speakerId${row.getSpeakerId()}"
                                                       value="${row.getSpeakerId()}">
                                                    <%--                                                        <input type="hidden" name="sessionId" value="${sessionId}">--%>
                                                <button type="button" name="submit" class="btn btn-info" sp-id="${row.getSpeakerId()}"
                                                        onclick="addMember(this)" data-toggle="tooltip" title="Thêm thành viên">
                                                    <i class="fa fa-plus"></i>&nbsp;
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                        <div id="navpills-2" class="tab-pane">
                            <div class="row">
                                <h3>Ghi âm phiên họp</h3>
                            </div>
                        </div>
                        <div id="navpills-3" class="tab-pane">
                            <div class="row">
                                <h3>Soạn thảo tốc ký</h3>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <!-- End List -->
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog" data-backdrop="static" data-keyboard="false"></div>


<script>
    document.title = "Chi tiết phiên họp 2";
    $(document).ready(function () {

    });

    function addMember(btn) {
        var speakerID = $(btn).attr('sp-id');
        var dutyID = $('#duty'+speakerID).val();
        var sessionID = ${item.sessionId};
        console.log(speakerID+"---"+dutyID+"---"+sessionID);
        $.ajax({
            type: 'post',
            url: '${addMemberUrl}',
            data:{
               speakerID: speakerID,
               dutyID: dutyID,
               sessionID: sessionID
            },
            success: function (res) {
                console.log(res);
            },
            error: function (res) {
                console.log(res);
            }
        })
    }

    function addAttendees(btn) {
        editUrl = '${editAttendeesUrl}';
        $('#myModal').load(editUrl, '', function () {
            $('#myModal').modal('toggle');
        });
    }


</script>

</body>
</html>














