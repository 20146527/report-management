<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="editMeetingUrl" value="/ajax-meeting-edit.html">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:url var="deleteMeetingUrl" value="/ajax-meeting-edit.html">
    <c:param name="urlType" value="url_delete"/>
</c:url>
<c:choose>
    <c:when test="${not empty messageResponse}">
        ${messageResponse}
    </c:when>
    <c:otherwise>
        <!-- Modal -->
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <c:if test="${not empty item.pojo.meetingId}">
                        <h5 class="modal-title" id="exampleModalLabel">Chỉnh sửa cuộc họp</h5>
                    </c:if>
                    <c:if test="${empty item.pojo.meetingId}">
                        <h5 class="modal-title" id="exampleModalLabel">Thêm cuộc họp</h5>
                    </c:if>

                </div>
                <div class="form-validation">
                    <form action="${editMeetingUrl}" method="post" id="editMeetingForm" class="form-valide">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="mettingName">Tên cuộc họp <span class="text-danger">*</span></label>
                                <div>
                                    <input type="text" id="mettingName" name="pojo.name" class="form-control"
                                           value="${item.pojo.name}" placeholder="Vui lòng nhập tên cuộc họp.">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="timeStart">Thời gian bắt đầu <span class="text-danger">*</span></label>
                                <div>
                                    <input type="text" id="timeStart" name="timeStart" class="form-control"
                                           value="${item.timeStart}" placeholder="Nhấp vào để chọn thời gian">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="timeEnd">Thời gian kết thúc <span class="text-danger">*</span></label>
                                <div>
                                    <input type="text" id="timeEnd" name="timeEnd" class="form-control"
                                           value="${item.timeEnd}" placeholder="Nhấp vào để chọn thời gian">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputLocation">Địa điểm cuộc họp</label>
                                <input type="text" class="form-control" id="inputLocation" name="pojo.address"
                                       value="${item.pojo.address}"
                                       placeholder="Vui lòng nhập địa điểm diễn ra cuộc họp">
                            </div>

                            <div class="form-group">
                                <label for="inputDescription">Mô tả</label>
                                <textarea class="form-control" rows="5" id="inputDescription"
                                          name="pojo.description">${item.pojo.description}</textarea>
                            </div>
                            <c:if test="${not empty item.pojo.meetingId}">
                                <input type="hidden" name="pojo.meetingId" value="${item.pojo.meetingId}"/>
                                <input type="hidden" name="pojo.creUID" value="${item.pojo.creUID}">
                                <input type="hidden" name="pojo.creDate" value="${item.pojo.creDate}">
                                <input type="hidden" name="pojo.modUID" value="${item.pojo.modUID}">
                                <input type="hidden" name="pojo.modDate" value="${item.pojo.modDate}">
                            </c:if>
                            <input type="hidden" name="crudaction" id="crudactionEdit" value="insert_update"/>
                            <input type="hidden" name="viewType" id="viewType" value="${item.viewType}">

                        </div>
                        <div class="modal-footer">
                            <c:if test="${not empty item.pojo.meetingId}">
                                <c:if test="${item.viewType == 'calendar'}">
                                    <c:url value="/manager-session-list.html" var="sessionListUrl">
                                        <c:param name="urlType" value="url_list"/>
                                        <c:param name="meetingId" value="${item.pojo.meetingId}"/>
                                    </c:url>
                                    <button type="button" name="detail" class="btn btn-info"
                                            data-tooltip="Mở chi tiết sang tab mới">
                                        <a href="${sessionListUrl}" target="_blank" style="color: white">
                                            <i class="fa fa-asterisk"></i>&nbsp;
                                            <span>Chi tiết</span>
                                        </a>
                                    </button>

                                    <button type="button" name="remove" class="btn btn-danger"
                                            meetingId="${item.pojo.meetingId}" meetingName="${item.pojo.name}"
                                            onclick="removeMeeting(this)" data-tooltip="Xóa">
                                        <i class="fa fa-trash"></i>
                                        <span>Xóa</span>&nbsp;
                                    </button>
                                </c:if>
                            </c:if>
                            <button type="button" id="btnSave" class="btn btn-primary" data-tooltip="Lưu lại cuộc họp">
                                <i class="fa fa-save"></i>
                                <span>Lưu</span>&nbsp;
                            </button>
                            <button type="button" class="btn btn-secondary" data-tooltip="Đóng cửa sổ" data-dismiss="modal">
                                <i class="fa fa-times"></i>
                                <span>Đóng</span>&nbsp;
                            </button>
                        </div>
                    </form>
                </div>
                <form action="${deleteMeetingUrl}" method="post" id="removeMeetingForm">
                    <input type="hidden" name="meetingId" value="${item.pojo.meetingId}">
                    <input type="hidden" name="viewType" value="${item.viewType}">
                </form>
            </div>
        </div>

        <script src="<c:url value='/template/js/manager/validate-meeting.js'/>"></script>
        <script>
            $(document).ready(function () {
                $('#timeStart').datetimepicker({
                    format: 'DD/MM/YYYY hh:mm A'
                });
                $('#timeEnd').datetimepicker({
                    format: 'DD/MM/YYYY hh:mm A'
                });
            });
            // function removeMeeting() {
            //     $('#removeMeetingForm').submit();
            // }

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
                                        $(location).attr('href', '/manager-meeting-list.html?urlType=url_list&viewType=calendar');
                                        swal.close();
                                    })
                                },
                                error: function (res) {
                                    swal({
                                        title: "Lỗi !",
                                        text: "Không xóa được cuộc họp",
                                        type: "error"
                                    }, function () {
                                        $(location).attr('href', '/manager-meeting-list.html?urlType=url_list&viewType=calendar');
                                        swal.close();
                                    })
                                }
                            });
                        } else {
                            swal("Đã hủy !!", "Hủy yêu cầu xóa", "error");
                        }
                    });
            }
        </script>
    </c:otherwise>
</c:choose>
