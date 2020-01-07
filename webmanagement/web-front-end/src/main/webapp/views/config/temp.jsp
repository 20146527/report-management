<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 13/12/2019
  Time: 4:31 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<table id="table-attendees" class="table table-hover">
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
    <c:set var="index" value="0"/>
    <c:forEach items="${items}" varStatus="loop" var="row">
        <c:set var="index" value="${index + 1}"/>
        <tr>
            <td>${index}</td>
            <td>${row.getSpeakerDto().getFullName()}</td>
            <td>${row.getSpeakerDto().getOtherName()}</td>
            <td>${row.getDutyDto().getDutyDescription()}</td>
            <td>
                <button type="button" name="submit" class="btn btn-danger"
                        onclick="removeAttendees(this)" attendeesId="${row.getAttendeesId()}"
                        data-tooltip="Xóa" speakerName="${row.getSpeakerDto().getFullName()}">
                    <i class="fa fa-trash"></i>&nbsp;
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>



<table id="myTable" class="table table-bordered table-striped">
    <thead>
    <tr>
        <th>STT</th>
        <th>Họ và tên</th>
        <th>Email</th>
        <th>Số điện thoại</th>
        <th>Cơ quan</th>
        <th>Chức vụ</th>
        <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="index2" value="0"/>
    <c:forEach items="${listSpeaker}" varStatus="loop" var="row">
        <c:set var="index2" value="${index2 + 1}"/>
        <tr>
            <td>${index2}</td>
            <td>${row.getFullName()}</td>
            <td>${row.getEmail()}</td>
            <td>${row.getPhone()}</td>
            <td>${row.getOtherName()}</td>
            <td>
                <select name="dutyId" id="duty${row.getSpeakerId()}" class="form-control">
                    <option value="3">Thành viên</option>
                    <option value="2">Thư ký</option>
                    <option value="1">Chủ tọa</option>
                </select>
            </td>
            <td>
                <button type="button" name="submit" class="btn btn-info" sp-id="${row.getSpeakerId()}"
                        onclick="addAttendees(this)" data-tooltip="Thêm">
                    <i class="fa fa-plus"></i>&nbsp;
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
