<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="editSessionUrl" value="/ajax-session-edit.html">
    <c:param name="urlType" value="url_edit"/>
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
                        <c:if test="${not empty item.pojo.sessionId}">
                            <h5 class="modal-title" id="exampleModalLabel">Chỉnh sửa phiên họp</h5>
                        </c:if>
                        <c:if test="${empty item.pojo.sessionId}">
                            <h5 class="modal-title" id="exampleModalLabel">Thêm phiên họp</h5>
                        </c:if>

                    </div>
                    <div class="form-validation">
                        <form action="${editSessionUrl}" method="post" id="editSessionForm" class="form-valide">
                            <div class="modal-body">

                                <div class="form-group">
                                    <label for="sessionName">Tên phiên họp <span class="text-danger">*</span></label>
                                    <div>
                                        <input type="text" id="sessionName" name="pojo.name" class="form-control "
                                               value="${item.pojo.name}" placeholder="Vui lòng nhập tên cuộc họp.">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="timeStart">Thời gian bắt đầu <span class="text-danger">*</span></label>
                                    <div>
                                        <input type="text" id="timeStart" name="timeStart" class="form-control " onchange=""
                                               value="${item.timeStart}" placeholder="Nhấp vào để chọn thời gian">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="timeEnd">Thời gian kết thúc <span class="text-danger">*</span></label>
                                    <div>
                                        <input type="text" id="timeEnd" name="timeEnd" class="form-control " onchange=""
                                               value="${item.timeEnd}" placeholder="Nhấp vào để chọn thời gian">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="roomId" class=" control-label">Địa điểm cuộc họp <span class="text-danger">*</span></label>
                                    <div>
                                        <select name="roomId" id="roomId" class="form-control" onchange="">
                                            <option value="">Chọn địa điểm họp</option>
                                            <c:forEach items="${roomList}" var="room">
                                                <option value="${room.roomId}">${room.roomName}</option>
                                            </c:forEach>
                                        </select>
                                        <div id="room-error"></div>
                                    </div>
                                </div>
                                <c:choose>
                                    <c:when test="${not empty meetingId}">
                                        <input type="hidden" name="meetingId" id="meetingId" value="${meetingId}">
                                    </c:when>
                                    <c:otherwise>
                                        <div class="form-group">
                                            <label for="meeting" class=" control-label">Chọn cuộc họp <span class="text-danger">*</span></label>
                                            <select name="meetingId" id="meeting" class="form-control">
                                                <c:forEach items="${meetingList}" var="meeting">
                                                    <option value="${meeting.meetingId}">${meeting.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </c:otherwise>
                                </c:choose>

                                <div class="form-group">
                                    <label for="inputDescription">Mô tả</label>
                                    <textarea class="form-control" rows="5" id="inputDescription" name="pojo.description">${item.pojo.description}</textarea>
                                </div>
                                <c:if test="${not empty item.pojo.sessionId}">
                                    <input type="hidden" name="pojo.sessionId" value="${item.pojo.sessionId}"/>
                                    <input type="hidden" name="pojo.creUID" value="${item.pojo.creUID}">
                                    <input type="hidden" name="pojo.creDate" value="${item.pojo.creDate}">
                                    <input type="hidden" name="pojo.modUID" value="${item.pojo.modUID}">
                                    <input type="hidden" name="pojo.modDate" value="${item.pojo.modDate}">
                                </c:if>
                                <input type="hidden" name="crudaction" id="crudactionEdit" value="insert_update"/>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" id="btnSave" class="btn btn-primary">Lưu</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        <script>
            $(document).ready(function () {
                // $('#btnSave').prop('disabled', true);
                $('#timeStart').datetimepicker({
                    format: 'DD/MM/YYYY hh:mm A'
                    // format: 'DD-MM-YYYY hh:mm a'
                });
                $('#timeEnd').datetimepicker({
                    format: 'DD/MM/YYYY hh:mm A'
                });
                //searchMeeting();
                //$('#myModal').modal({backdrop: 'static', keyboard: false});
            });

            function testValidateTime() {
                let timeStart = $('#timeStart').val();
                let timeEnd = $('#timeEnd').val();
                let roomId = $('#roomId').val();
                if(timeStart != '' && timeEnd !='' && roomId !=''){
                    $.ajax({
                        type: 'get',
                        url: '/ajax-validate.html',
                        data: {
                            type: 'validate_room_session',
                            timeStart: timeStart,
                            timeEnd: timeEnd,
                            roomId: roomId
                        },
                        success: function (res) {
                            console.log(res);
                            if(res == 'Success'){

                            }else {

                            }
                        },
                        error: function (res) {
                            console.log(res);
                        }
                    });
                }else {
                    console.log('Chua nhap du thong tin');
                }

            }
        </script>
    </c:otherwise>
</c:choose>

<script src="<c:url value='/template/js/manager/validate-session.js'/>"></script>
<script>
    function changeRoom() {
        let select = $('#roomId');
        let timeStart = $('#timeStart').val();
        let timeEnd = $('#timeEnd').val();
        let roomId = $(select).val();
        if(timeStart != '' && timeEnd !='' && roomId !=''){
            $.ajax({
                type: 'get',
                url: '/ajax-validate.html',
                data: {
                    type: 'validate_room_session',
                    timeStart: timeStart,
                    timeEnd: timeEnd,
                    roomId: roomId
                },
                success: function (res) {
                    console.log(res);
                    if(res == 'Reject'){
                        $('#btnSave').prop('disabled', true);
                        $(select).removeClass("is-invalid").addClass("is-invalid");
                        $('#room-error').addClass("invalid-feedback animated fadeInDown").append('Phòng đã được dùng trong thời gian này');
                    }else {
                        $('#btnSave').prop('disabled', false);
                        $(select).removeClass("is-invalid").addClass("is-valid");
                        $('#room-error').removeClass("invalid-feedback animated fadeInDown").empty();
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            });
        }
    }
</script>