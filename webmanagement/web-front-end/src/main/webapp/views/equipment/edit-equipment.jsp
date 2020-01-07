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
<c:url var="editEquipmentUrl" value="/ajax-equipment-edit.html">
    <c:param name="urlType" value="url_edit_equipment"/>
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
                        <c:if test="${not empty item.pojo.equipmentId}">
                            <h5 class="modal-title" id="exampleModalLabel">Chỉnh sửa thông tin</h5>
                        </c:if>
                        <c:if test="${empty item.pojo.equipmentId}">
                            <h5 class="modal-title" id="exampleModalLabel">Thêm mới thiết bị</h5>
                        </c:if>

                    </div>
                    <div class="modal-body">
                        <div class="form-validation">
                            <form action="${editEquipmentUrl}" method="post" id="editEquipmentForm" class="form-valide">
                                <div class="form-group">
                                    <label for="name" class="control-label mb-1">Tên thiết bị <span class="text-danger">*</span></label>
                                    <div>
                                        <input type="text" class="form-control" id="name" name="pojo.name"
                                               value="${item.pojo.name}" placeholder="VD: Máy ghi âm Sony">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="brand" class="control-label mb-1">Thương hiệu <span class="text-danger">*</span></label>
                                    <div>
                                        <input type="text" class="form-control" id="brand" name="pojo.brand"
                                               value="${item.pojo.brand}" placeholder="VD: Sony">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="description" class="control-label mb-1">Mô tả thiết bị</label>
                                    <input type="text" class="form-control" id="description" name="pojo.description"
                                           value="${item.pojo.description}" placeholder="Nhập vào mô tả thiết bị">
                                </div>
                                <div class="form-group">
                                    <div>
                                        <label for="status">Trạng thái <span class="text-danger">*</span></label>
                                        <select class="form-control" id="status" name="pojo.status">
                                            <option value="">Chọn trạng thái</option>
                                            <option value="1">Hoạt động</option>
                                            <option value="0">Không hoạt động</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="dayStart" class="control-label mb-1">Sử dụng từ (ngày nhập) <span class="text-danger">*</span></label>
                                    <div>
                                        <input type="date" class="form-control" id="dayStart" name="pojo.dayStart"
                                               value="${item.pojo.dayStart}" placeholder="">
                                    </div>
                                </div>
                                <input type="hidden" name="crudaction" id="crudactionEdit" value="insert_update"/>
                                <input type="hidden" name="roomId" id="roomId" value="${roomId}"/>
                                <c:if test="${not empty item.pojo.equipmentId}">
                                    <input type="hidden" name="pojo.equipmentId" id="equipmentId" value="${item.pojo.equipmentId}">
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

<script src="<c:url value='/template/js/manager/validate-equipment.js'/>"></script>