<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="editRoomUrl" value="/ajax-equipment-edit.html">
    <c:param name="urlType" value="url_edit_room"/>
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
                        <c:if test="${not empty item.roomId}">
                            <h5 class="modal-title" id="exampleModalLabel">Chỉnh sửa phòng</h5>
                        </c:if>
                        <c:if test="${empty item.roomId}">
                            <h5 class="modal-title" id="exampleModalLabel">Thêm mới phòng</h5>
                        </c:if>

                    </div>
                    <div class="modal-body">
                        <div class="form-validation">
                            <form action="${editRoomUrl}" method="post" id="editSpeakerForm" class="form-valide">
                                <div class="form-group">
                                    <label for="roomName" class="control-label mb-1">Tên phòng <span class="text-danger">*</span></label>
                                    <div>
                                        <input type="text" class="form-control" id="roomName" name="roomName"
                                               value="${item.roomName}" placeholder="VD: Phòng 901">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="roomDescription" class="control-label mb-1">Mô tả phòng</label>
                                    <input type="text" class="form-control" id="roomDescription" name="roomDescription"
                                           value="${item.roomDescription}" placeholder="Nhập vào mô tả phòng">
                                </div>
                                <div class="form-group">
                                    <div>
                                        <label for="status">Trạng thái <span class="text-danger">*</span></label>
                                        <select class="form-control" id="status" name="status">
                                            <option value="">Chọn trạng thái</option>
                                            <option value="0">Hoạt động</option>
                                            <option value="1">Không hoạt động</option>
                                        </select>
                                    </div>
                                </div>
                                <input type="hidden" name="statusDB" id="statusDB" value="${item.getStatusDB()}">
                                <input type="hidden" name="crudaction" id="crudactionEdit" value="insert_update"/>
                                <c:if test="${not empty item.roomId}">
                                    <input type="hidden" name="roomId" id="roomId" value="${item.roomId}">
                                </c:if>
                                <button type="submit" id="btnSave" class="btn btn-primary">Lưu</button>
                            </form>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>

    </c:otherwise>
</c:choose>

<script src="<c:url value='/template/js/manager/validate-room.js'/>"></script>