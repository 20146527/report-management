<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="editMeetingUrl" value="/ajax-meeting-edit.html">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<html>
<head>
    <title>Tạo cuộc họp</title>
</head>
<body>

<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html"><fmt:message key="label.home" bundle="${lang}"/></a></li>
            <li class="breadcrumb-item"><fmt:message key="label.report" bundle="${lang}"/></li>
            <li class="breadcrumb-item active"><fmt:message key="label.report.create" bundle="${lang}"/></li>
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
                        <h3 class="text-center">Tạo cuộc họp</h3>
                        <hr>
                        <%--alert--%>
                        <c:if test="${not empty messageResponse}">
                            <div class="alert alert-${alert} alert-dismissible fade show">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <strong>${messageResponse}</strong>
                            </div>
                        </c:if>
                        <%--alert--%>
                        <form action="${editMeetingUrl}" method="post">
                            <div class="form-group">
                                <label for="mettingName">Tên cuộc họp:</label>
                                <input type="text" id="mettingName" name="pojo.name" class="form-control input-lg"
                                       placeholder="Vui lòng nhập tên cuộc họp.">
                            </div>

                            <div class="form-group">
                                <label for="timeStart">Thời gian bắt đầu:</label>
                                <input type="datetime-local" id="timeStart" name="timeStart" class="form-control input-lg"
                                       placeholder="ngày/tháng/năm giờ:phút:giây">
                            </div>

                            <div class="form-group">
                                <label for="timeEnd">Thời gian kết thúc:</label>
                                <input type="datetime-local" id="timeEnd" name="timeEnd" class="form-control input-lg"
                                       placeholder="ngày/tháng/năm giờ:phút:giây">
                            </div>

                            <div class="form-group">
                                <label for="inputLocation">Địa điểm cuộc họp:</label>
                                <input  type="text" class="form-control input-lg" id="inputLocation" name="pojo.address"
                                        placeholder="Vui lòng nhập địa điểm diễn ra cuộc họp">
                            </div>

                            <div class="form-group">
                                <label for="inputDescription">Mô tả</label>
                                <textarea class="form-control" rows="5" id="inputDescription" name="pojo.description">
                                </textarea>
                            </div>
                            <div>
                                <button type="submit" name="submit"
                                        class="btn btn-lg btn-info btn-block">
                                    <i class="fa fa-save fa-lg"></i>&nbsp;
                                    <span id="payment-button-amount">Lưu</span>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog"></div>


<script>
    document.title = "Tạo cuộc họp";
    $(document).ready(function () {
        //$('#myModal').modal({backdrop: 'static', keyboard: false});
    });

</script>

</body>
</html>
