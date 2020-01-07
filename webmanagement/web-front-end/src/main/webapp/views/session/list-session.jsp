<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="editSessionUrl" value="/ajax-session-edit.html">
    <c:param name="urlType" value="url_edit"/>
    <c:param name="meetingId" value="${meetingInfo.pojo.getMeetingId()}"/>
</c:url>
<c:url var="listSessionUrl" value="/manager-session-list.html">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url var="importSessionUrl" value="/manager-session-import.html">
    <c:param name="urlType" value="url_import_session"/>
    <c:param name="meetingId" value="${meetingInfo.pojo.getMeetingId()}"/>
</c:url>
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
            <!-- Info Meeting -->
            <c:if test="${not empty meetingInfo.pojo.getMeetingId()}">
                <div class="card">
                    <div class="card-title">
                        <h4>Thông tin cuộc họp</h4>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Tên:</th>
                                    <th>Địa điểm:</th>
                                    <th>Mô tả</th>
                                    <th>Bắt đầu lúc:</th>
                                    <th>Kết thúc lúc:</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <%--                            <c:forEach items="${infoMeeting}" var="info">--%>
                                <tr>
                                    <td>${meetingInfo.pojo.getName()}</td>
                                    <td>${meetingInfo.pojo.getAddress()}</td>
                                    <td>${meetingInfo.pojo.getDescription()}</td>
                                    <td class="color-success">${meetingInfo.getTimeStart()}</td>
                                    <td class="color-warning">${meetingInfo.getTimeEnd()}</td>
                                </tr>
                                    <%--                            </c:forEach>--%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </c:if>
            <!-- End Info Meeting-->

            <!-- Search Session -->
            <c:if test="${empty meetingInfo.pojo.getMeetingId()}">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Tìm kiếm</h4>
                        <h6 class="card-subtitle">Nhập thông tin tìm kiếm</h6>
                        <form action="" method="get" id="searchSessionForm" class="form-validate-search-time">
                            <input type="hidden" name="urlType" value="url_list" />
                            <div class="row">
                                <div class="col-md-4 col-sm-12">
                                    <div class="form-group">
                                        <label for="meetingSearchId" class=" control-label">Cuộc họp</label>
                                        <select name="meetingSearchId" id="meetingSearchId" class="form-control">
                                            <option value="">Chọn cuộc họp ...</option>
                                            <c:forEach items="${meetingList}" var="meeting">
                                                <option value="${meeting.meetingId}">${meeting.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-12">
                                    <div class="form-group">
                                        <label for="room" class=" control-label">Phòng họp</label>
                                        <select name="roomId" id="room" class="form-control">
                                            <option value="">Chọn phòng họp ...</option>
                                            <c:forEach items="${roomList}" var="room">
                                                <option value="${room.roomId}">${room.roomName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-12">
                                    <div class="form-group">
                                        <label class="control-label">Tên phiên họp</label>
                                        <input type="text" class="form-control"
                                               name="pojo.name" value="${item.pojo.name}"
                                               placeholder="Nhập tên phiên họp">
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-12">
                                    <div class="form-group">
                                        <label class="control-label">Mô tả phiên</label>
                                        <input type="text" class="form-control"
                                               name="pojo.description" value="${item.pojo.description}"
                                               placeholder="Nhập mô tả">
                                    </div>
                                </div>
                                <div class="col-md-3 col-sm-12">
                                    <div class="form-group">
                                        <label class="control-label">Tạo phiên từ</label>
                                        <input type="text" class="form-control"
                                               id="search-time-start" value="${item.timeStart}"
                                               name="timeStart" placeholder="Chọn thời gian">
                                    </div>
                                </div>
                                <div class="col-md-3 col-sm-12">
                                    <div class="form-group">
                                        <label class="control-label">Tạo phiên đến</label>
                                        <input type="text" class="form-control"
                                               id="search-time-end" value="${item.timeEnd}"
                                               name="timeEnd" placeholder="Chọn thời gian">
                                    </div>
                                </div>
                                <div class="col-md-2 col-sm-12">
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
            </c:if>
            <!-- End Search Session -->

            <!-- List -->
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Danh sách phiên họp</h4>
                    <%--alert--%>
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-${alert} alert-dismissible fade show">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <strong>${messageResponse}</strong>
                        </div>
                    </c:if>
                    <%--alert--%>
                    <button type="button" name="button" onclick="update(this)"
                            class="btn btn-outline-info">
                        <i class="fa fa-plus"></i>&nbsp;
                        <span>Thêm mới phiên</span>
                    </button>
                    <button type="button" name="button" onclick="(location.href='${importSessionUrl}')"
                            class="btn btn-outline-success">
                        <i class="fa fa-file-excel-o"></i>&nbsp;
                        <span>Tải lên từ Excel</span>
                    </button>
                    <div class="table-responsive m-t-10">
                        <table id="myTable" class="table table-bordered table-striped">
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
                                    <td>${row.getRoomDto().getRoomName()}</td>
                                    <td>${row.getTimeStart()}</td>
                                    <td>
                                        <c:url var="editUrl" value="/ajax-session-edit.html">
                                            <c:param name="urlType" value="url_edit"/>
                                            <c:param name="pojo.sessionId" value="${row.getSessionId()}"/>
                                            <c:param name="meetingId" value="${meetingInfo.pojo.getMeetingId()}"/>
                                        </c:url>
                                        <c:url var="detailUrl" value="/manager-session-detail.html">
                                            <c:param name="urlType" value="url_detail"/>
                                            <c:param name="sessionId" value="${row.getSessionId()}"/>
                                        </c:url>
                                        <c:url var="deleteUrl" value="/ajax-session-edit.html">
                                            <c:param name="urlType" value="url_delete"/>
                                            <c:param name="pojo.sessionId" value="${row.getSessionId()}"/>
                                            <c:param name="pojo.name" value="${row.getName()}"/>
                                        </c:url>
                                        <button type="button" name="submit" class="btn btn-info"
                                                onclick="location.href='${detailUrl}'"
                                                data-tooltip="Chi tiết phiên">
                                            &nbsp;<i class="fa fa-asterisk"></i>
                                        </button>
                                        <button type="button" name="submit" class="btn btn-success"
                                                sc-url="${editUrl}" onclick="update(this)"
                                                data-tooltip="Chỉnh sửa">
                                            <i class="fa fa-pencil-square-o"></i>&nbsp;
                                        </button>
                                        <button type="button" name="submit" class="btn btn-danger"
                                                sessionId="${row.getSessionId()}" sessionName="${row.getName()}"
                                                onclick="removeSession(this)" data-tooltip="Xóa">
                                            <i class="fa fa-trash"></i>&nbsp;
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                    <form action="${listSessionUrl}" method="get" id="formUrl">
                        <input type="hidden" name="crudaction" id="crudaction"/>
                        <input type="hidden" name="urlType" id="urlType"/>
                        <input type="hidden" name="meetingId" id="meetingId" value="${meetingInfo.pojo.getMeetingId()}"/>
                    </form>
                </div>
            </div>
            <!-- End List -->
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog" data-backdrop="static" data-keyboard="false"></div>
<div class="modal fade" id="modalRemove" role="dialog" data-backdrop="static" data-keyboard="false"></div>
<div class="modal fade" id="modalImport" role="dialog" data-backdrop="static" data-keyboard="false"></div>

<script>
    document.title = "Quản lý phiên họp";
    $(document).ready(function () {
        $('#search-time-start').datetimepicker({
            format: 'DD/MM/YYYY hh:mm A'
        });
        $('#search-time-end').datetimepicker({
            format: 'DD/MM/YYYY hh:mm A'
        });
    });

    function update(btn) {
        var editUrl = $(btn).attr('sc-url');
        if (typeof editUrl == 'undefined') {
            editUrl = '${editSessionUrl}';
        }
        $("#overlay").css({"display": "block"});
        $('#myModal').load(editUrl, '', function () {
            $("#overlay").css({"display": "none"});
            $('#myModal').modal('toggle');
            //createOrEdit();
        });
    }

    function createOrEdit() {
        $('#btnSave').click(function () {
            $('#editSessionForm').submit();
        });
        $('#editSessionForm').submit(function (e) {
            e.preventDefault();
            $('#crudactionEdit').val('insert_update');
            $.ajax({
                type: $(this).attr('method'),
                url: $(this).attr('action'),
                data: $(this).serialize(),
                dataType: 'html',
                success: function (res) {
                    if (res.trim() == "redirect_insert") {
                        $('#crudaction').val('redirect_insert');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    } else if (res.trim() == "redirect_update") {
                        $('#crudaction').val('redirect_update');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    } else if (res.trim() == "redirect_error") {
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
        $('#modalRemove').load(removeUrl,'', function () {
            $('#modalRemove').modal('toggle');
            removeEXE();
        });
    }
    function removeEXE() {
        $('#btnRemove').click(function () {
            $('#removeSessionForm').submit();
        });
        $('#removeSessionForm').submit(function (e) {
            e.preventDefault();
            $.ajax({
                type: $(this).attr('method'),
                url: $(this).attr('action'),
                data: $(this).serialize(),
                dataType: 'html',
                success: function (res) {
                    if(res.trim()=="redirect_delete"){
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

    function removeSession(btn) {
        let url = '/ajax-session-edit.html?urlType=url_delete';
        let sessionId = $(btn).attr('sessionId');
        let sessionName = $(btn).attr('sessionName');
        swal({
                title: "Xác nhận xóa ?",
                text: "Phiên họp: " + sessionName,
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
                            sessionId: sessionId
                        },
                        success: function (res) {
                            // swal("Đã xóa !!", "Đã xóa người dùng: "+fullname, "success");
                            swal({
                                title: "Đã xóa !",
                                text: "Đã xóa phiên: " + sessionName,
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














