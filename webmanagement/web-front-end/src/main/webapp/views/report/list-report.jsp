<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="listReportUrl" value="/report-list.html">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url var="searchReportUrl" value="/report-list.html">
    <c:param name="urlType" value="url_search"/>
</c:url>
<html>
<head>
    <title>Danh sách người họp</title>
</head>
<body>

<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item">Quản lý biên bản</li>
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
                    <form action="${listReportUrl}" method="get" id="searchSpeakerForm" class="form-validate-search-time">
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
                                    <label for="session" class=" control-label">Phiên họp</label>
                                    <select name="sessionId" id="session" class="form-control">
                                        <option value="0">Chọn phiên họp...</option>

                                    </select>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="tag" class="control-label">Tên biên bản</label>
                                    <input type="text" class="form-control" id="tag" name="pojo.name" value="${item.pojo.name}" placeholder="Nhập tên biên bản">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="preside" class=" control-label">Chủ tọa</label>
                                    <select name="speakerId" id="preside" class="form-control">
                                        <option value="0">Chọn chủ tọa...</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-12">
                                <div class="form-group">
                                    <label class="control-label">Tạo biên bản từ</label>
                                    <input type="text" class="form-control"
                                           id="search-time-start" value="${item.timeStart}"
                                           name="timeStart" placeholder="Chọn thời gian">
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-12">
                                <div class="form-group">
                                    <label class="control-label">Tạo biên bản đến</label>
                                    <input type="text" class="form-control"
                                           id="search-time-end" value="${item.timeEnd}"
                                           name="timeEnd" placeholder="Chọn thời gian">
                                </div>
                            </div>
                            <input type="hidden" name="urlType" value="url_list">
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

                    <button type="button" name="button" onclick="update(this)" class="btn btn-primary">
                        <i class="fa fa-plus"></i>&nbsp;
                        <span>Thêm mới</span>
                    </button>
                    <h6 class="card-subtitle">Xuất dữ liệu dạng Copy, CSV, Excel, PDF & In</h6>
                    <%--alert--%>
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-${alert} alert-dismissible fade show">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <span>${messageResponse}</span>
                        </div>
                    </c:if>
                    <%--alert--%>

                    <div class="table-responsive m-t-10">
                        <table id="example23"
                               class="display nowrap table table-hover table-striped table-bordered"
                               cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Tên</th>
                                <th>Phiên</th>
                                <th>Thời gian tạo</th>
                                <th>Tag</th>
                                <th>Hành động</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>#</th>
                                <th>Tên</th>
                                <th>Phiên</th>
                                <th>Thời gian tạo</th>
                                <th>Tag</th>
                                <th>Hành động</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="${items}" varStatus="loop" var="row">
                                <tr>
                                    <td>${row.reportId}</td>
                                    <td>${row.name}</td>
                                    <td>${row.sessionDto.name}</td>
                                    <td>${row.creDate}</td>
                                    <td>${row.tag}</td>
                                    <td>
                                        <c:url var="editReportUrl" value="/report-edit.html">
                                            <c:param name="urlType" value="url_edit"/>
                                            <c:param name="pojo.reportId" value="${row.reportId}"/>
                                        </c:url>

                                        <button type="button" name="submit" class="btn btn-success"
                                                onclick="location.href='${editReportUrl}'"
                                                data-tooltip="Sửa">
                                            <i class="fa fa-pencil-square-o"></i>&nbsp;
                                        </button>
                                        <button type="button" name="submit" class="btn btn-danger"
                                                reportId="${row.reportId}" reportName="${row.name}"
                                                onclick="removeReport(this)" data-tooltip="Xóa">
                                            <i class="fa fa-trash"></i>&nbsp;
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <form action="${listReportUrl}" method="get" id="formUrl">
                        <input type="hidden" name="crudaction" id="crudaction"/>
                        <input type="hidden" name="urlType" id="urlType"/>
                    </form>

                </div>
            </div>
            <!-- End List -->
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="modalRemove" role="dialog" data-backdrop="static" data-keyboard="false"></div>
<script>
    document.title = "Quản lý biên bản";
    $(document).ready(function () {
        $('#search-time-start').datetimepicker({
            format: 'DD/MM/YYYY hh:mm A'
        });
        $('#search-time-end').datetimepicker({
            format: 'DD/MM/YYYY hh:mm A'
        });

        $('#meeting').val('0');
        $('#session').prop('disabled', true);

        getMeeting();
        getSessionByMeeting();
        getPreside(0);
        getPresideBySession();
        insertValueSearch();
    });

    function insertValueSearch() {

    }

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
                if(listMeeting.length !== 0){
                    $('#meeting').empty().append(new Option("Chọn cuộc họp ...", 0));
                    $.each(listMeeting, function (i, item) {
                        $("#meeting").append(new Option(item.name, item.meetingId));
                    });
                }

                let meetingId = '${item.meetingId}';
                if(meetingId !== '' && meetingId !== '0'){
                    $('#meeting').val(meetingId);
                    ajaxGetSession(meetingId);
                }
            },
            error: function (res) {
                console.log(res);
            }
        });
    }

    function getPreside(sessionId) {
        let url = '/ajax-report-edit.html';
        $.ajax({
            url: url,
            type: 'get',
            data: {
                urlType: 'ajax_preside',
                sessionId: sessionId
            },
            success: function (res) {
                let listPreside = jQuery.parseJSON(res);
                if(listPreside.length !== 0){
                    $('#preside').empty().append(new Option("Chọn chủ tọa ...", 0));
                    $.each(listPreside, function (i, item) {
                        $("#preside").append(new Option(item.speakerDto.fullName, item.speakerDto.speakerId));
                    });
                }

                let speakerId = '${item.speakerId}';
                if(speakerId !== '' && speakerId !== '0'){
                    $('#preside').val(speakerId);
                }
            },
            error: function (res) {
                console.log(res);
            }
        });
    }

    function getSessionByMeeting() {
        $('#meeting').on('change', function (e) {
            var optionSelected = $("option:selected", this);
            var valueSelected = optionSelected.val();
            if(valueSelected !== 0){
                ajaxGetSession(valueSelected);
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
                //console.log(res);
                let listSession = jQuery.parseJSON(res);
                if(listSession.length !== 0){
                    $('#session').prop('disabled', false);
                    $('#session').empty().append(new Option("Chọn phiên...", 0));
                    $.each(listSession, function (i, item) {
                        $("#session").append(new Option(item.name, item.sessionId));
                    });

                    let sessionId = '${item.sessionId}';
                    if(sessionId !== '' && sessionId !== '0'){
                        $('#session').val(sessionId);
                    }

                }else {
                    $('#session').prop('disabled', true);
                }
            },
            error: function (res) {
                console.log(res);
            }
        });
    }

    function getPresideBySession() {
        $('#session').on('change', function (e) {
            var optionSelected = $("option:selected", this);
            var valueSelected = optionSelected.val();
            console.log(valueSelected);
            var url = "/ajax-report-edit.html";
            if(valueSelected !== 0){
                $.ajax({
                    url: url,
                    type: 'get',
                    data: {
                        urlType: 'ajax_preside',
                        sessionId: valueSelected
                    },
                    success: function (res) {
                        let listPreside = jQuery.parseJSON(res);
                        if(listPreside.length !== 0){
                            $('#preside').empty().append(new Option("Chọn chủ tọa ...", 0));
                            $.each(listPreside, function (i, item) {
                                $("#preside").append(new Option(item.speakerDto.fullName, item.speakerDto.speakerId));
                            });
                        }
                    },
                    error: function (res) {
                        console.log(res);
                    }
                });
            }
        })
    }

    function removeReport(btn) {
        let url = '/ajax-report-edit.html?urlType=url_delete';
        let reportId = $(btn).attr('reportId');
        let reportName = $(btn).attr('reportName');
        swal({
                title: "Xác nhận xóa ?",
                text: "Biên bản: " + reportName,
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
                            reportId: reportId
                        },
                        success: function (res) {
                            // swal("Đã xóa !!", "Đã xóa người dùng: "+fullname, "success");
                            swal({
                                title: "Đã xóa !",
                                text: "Đã xóa biên bản: " + reportName,
                                type: "success"
                            }, function () {
                                swal.close();
                                $('#crudaction').val('redirect_delete');
                                $('#urlType').val('url_list');
                                $('#formUrl').submit();
                            })
                        },
                        error: function (res) {
                            swal("Lỗi !!", "Không gửi được yêu cầu xóa", "success");
                            setTimeout(function () {
                                $('#crudaction').val('redirect_error');
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
