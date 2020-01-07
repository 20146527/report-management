<%--
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
    <c:param name="viewType" value="table"/>
</c:url>
<c:url var="listMeetingUrl" value="/manager-meeting-list.html">
    <c:param name="urlType" value="url_list"/>
    <c:param name="viewType" value="table"/>
</c:url>
<c:url var="searchMeetingUrl" value="/manager-meeting-list.html">
    <c:param name="urlType" value="url_search"/>
    <c:param name="viewType" value="table"/>
</c:url>
<c:url value="/manager-session-list.html" var="sessionListUrl">
    <c:param name="urlType" value="url_list"/>
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
            <!-- Search -->
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Tìm kiếm</h4>
                    <h6 class="card-subtitle">Nhập thông tin tìm kiếm</h6>
                    <form action="${listMeetingUrl}" method="get" id="searchMeetingForm" class="form-validate-search-time">
                        <input type="hidden" name="urlType" value="url_list" />
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label">Tên cuộc họp</label>
                                    <input type="text" class="form-control"
                                           id="search-vehicle-lp"
                                           name="pojo.name" value="${item.pojo.name}"
                                           placeholder="Nhập tên cuộc họp">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="control-label">Tạo cuộc họp từ</label>
                                    <div>
                                        <input type="text" class="form-control"
                                               id="search-time-start" value="${item.timeStart}"
                                               name="timeStart" placeholder="Chọn thời gian">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="control-label">Tạo cuộc họp đến</label>
                                    <div>
                                        <input type="text" class="form-control"
                                               id="search-time-end" value="${item.timeEnd}"
                                               name="timeEnd" placeholder="Chọn thời gian">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label class="control-label">Hành động</label><br>
                                    <button type="submit" id="btnSearch"
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

            <!-- List -->
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Danh sách</h4>
                    <h6 class="card-subtitle">Xuất dữ liệu dạng Copy, CSV, Excel, PDF & In</h6>
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
                            <c:url var="calendarMeetingUrl" value="/manager-meeting-list.html">
                                <c:param name="urlType" value="url_list"/>
                                <c:param name="viewType" value="calendar"/>
                            </c:url>
                            <button type="button" name="button" onclick="location.href='${calendarMeetingUrl}'"
                                    class="btn btn-outline-success pull-right">
                                <i class="fa fa-calendar"></i>&nbsp;
                                <span>Xem dạng lịch</span>
                            </button>
                        </div>
                    </div>

                    <div class="table-responsive m-t-10">
                        <table id="example23"
                               class="display nowrap table table-hover table-striped table-bordered"
                               cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>STT</th>
                                <th>Tên</th>
                                <th>Địa điểm</th>
                                <th>Thời gian bắt đầu</th>
                                <th>Hành động</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="index" value="0"/>
                            <c:forEach items="${items}" varStatus="loop" var="row">
                                <c:set var="index" value="${index + 1}"/>
                                <tr>
                                    <td>${index}</td>
                                    <td>${row.getName()}</td>
                                    <td>${row.getAddress()}</td>
                                    <td>${row.getTimeStart()}</td>
                                    <input type="hidden" name="meetingId" value="${row.getMeetingId()}"/>
                                    <td>
                                        <c:url value="/manager-session-list.html" var="sessionListUrl">
                                            <c:param name="urlType" value="url_list"/>
                                            <c:param name="meetingId" value="${row.getMeetingId()}"/>
                                        </c:url>
                                        <c:url var="editUrl" value="/ajax-meeting-edit.html">
                                            <c:param name="urlType" value="url_edit"/>
                                            <c:param name="viewType" value="table"/>
                                            <c:param name="pojo.meetingId" value="${row.getMeetingId()}"/>
                                        </c:url>
                                        <c:url var="deleteUrl" value="/ajax-meeting-edit.html">
                                            <c:param name="urlType" value="url_delete"/>
                                            <c:param name="viewType" value="table"/>
                                            <c:param name="pojo.meetingId" value="${row.getMeetingId()}"/>
                                            <c:param name="pojo.name" value="${row.getName()}"/>
                                        </c:url>
                                        <button type="button" name="detail" class="btn btn-info"
                                                onclick="location.href='${sessionListUrl}'"
                                                data-tooltip="Chi tiết cuộc họp">
                                            <i class="fa fa-asterisk"></i>&nbsp;
                                        </button>
                                        <button type="button" name="edit" class="btn btn-success"
                                                sc-url="${editUrl}" onclick="update(this)"
                                                data-tooltip="Sửa">
                                            <i class="fa fa-pencil-square-o"></i>&nbsp;
                                        </button>
                                        <button type="button" name="remove" class="btn btn-danger"
                                                meetingId="${row.getMeetingId()}" meetingName="${row.getName()}"
                                                onclick="removeMeeting(this)" data-tooltip="Xóa">
                                            <i class="fa fa-trash"></i>&nbsp;
                                        </button>
                                    </td>
                                </tr>
                                <%--                                </form>--%>
                            </c:forEach>
                            </tbody>
                        </table>
                        <form action="${listMeetingUrl}" method="get" id="formUrl">
                            <input type="hidden" name="crudaction" id="crudaction"/>
                            <input type="hidden" name="urlType" id="urlType"/>
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
    document.title = "Quản lý cuộc họp";
    $(document).ready(function () {

        $('#search-time-start').datetimepicker({
            format: 'DD/MM/YYYY hh:mm A'
        });
        $('#search-time-end').datetimepicker({
            format: 'DD/MM/YYYY hh:mm A'
        });
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
            createOrEdit();
        });
    }

    function createOrEdit() {


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
                dataType: 'html',
                success: function (res) {
                    console.log("CUMeeting: "+res);
                    if (res.trim() === "redirect_insert") {
                        $('#crudaction').val('redirect_insert');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    } else if (res.trim() === "redirect_update") {
                        $('#crudaction').val('redirect_update');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    } else if (res.trim() === "redirect_error") {
                        $('#crudaction').val('redirect_error');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            })
        });
    }

    function remove(btn) {
        var removeUrl = $(btn).attr('sc-url');
        $('#modalRemove').load(removeUrl, '', function () {
            $('#modalRemove').modal('toggle');
            removeEXE();
        });
    }

    function removeEXE() {
        $('#btnRemove').click(function () {
            $('#removeMeetingForm').submit();
        });
        $('#removeMeetingForm').submit(function (e) {
            e.preventDefault();
            $.ajax({
                type: $(this).attr('method'),
                url: $(this).attr('action'),
                data: $(this).serialize(),
                dataType: 'html',
                success: function (res) {
                    if (res.trim() === "redirect_delete") {
                        $('#crudaction').val('redirect_delete');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            });
        });
    }

    function removeMeeting(btn) {
        let url = '/ajax-meeting-edit.html?urlType=url_delete';
        let meetingId = $(btn).attr('meetingId');
        let meetingName = $(btn).attr('meetingName');
        swal({
                title: "Xác nhận xóa ?",
                text: "Cuộc họp: " + meetingName,
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
                        url: url,
                        data: {
                            urlType: 'url_delete',
                            meetingId: meetingId,
                            viewType: 'table'
                        },
                        success: function (res) {
                            // swal("Đã xóa !!", "Đã xóa người dùng: "+fullname, "success");
                            swal({
                                title: "Đã xóa !",
                                text: "Đã xóa cuộc họp: " + meetingName,
                                type: "success"
                            }, function () {
                                swal.close();
                                $('#urlType').val('url_list');
                                $('#formUrl').submit();
                            })
                        },
                        error: function (res) {
                            swal("Lỗi !!", "Không gửi được yêu cầu xóa", "success");
                            setTimeout(function () {
                                $('#urlType').val('url_list');
                                $('#formUrl').submit();
                            }, 1000);
                        }
                    });
                } else {
                    swal("Đã hủy !!", "Hủy yêu cầu xóa", "error");
                }
            });
    }
</script>
<script src="<c:url value='/template/js/manager/validate-search-time.js'/>"></script>

</body>
</html>
