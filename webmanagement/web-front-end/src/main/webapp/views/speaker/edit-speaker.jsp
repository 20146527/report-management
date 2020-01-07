<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="editSpeakerUrl" value="/ajax-speaker-edit.html">
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
                    <c:if test="${not empty item.pojo.speakerId}">
                        <h5 class="modal-title" id="exampleModalLabel">Chỉnh sửa thông tin người họp</h5>
                    </c:if>
                    <c:if test="${empty item.pojo.speakerId}">
                        <h5 class="modal-title" id="exampleModalLabel">Thêm mới người họp</h5>
                    </c:if>

                </div>
                <div class="form-validation">
                    <form action="${editSpeakerUrl}" method="post" id="editSpeakerForm" class="form-valide">
                        <div class="modal-body">

                            <div class="form-group">
                                <label for="pojo.fullName" class="control-label mb-1">Họ và tên <span
                                        class="text-danger">*</span></label>
                                <div>
                                    <input type="text" class="form-control" id="pojo.fullName" name="pojo.fullName"
                                           value="${item.pojo.fullName}" placeholder="Nhập vào họ và tên">
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="otherName" class="control-label mb-1">Cơ quan</label>
                                <input type="text" class="form-control" id="otherName" name="pojo.otherName"
                                       value="${item.pojo.otherName}" placeholder="Nhập tên cơ quan">
                            </div>
                            <div class="form-group">
                                <label for="birthday" class="control-label mb-1">Ngày sinh</label>
                                <input type="text" class="form-control" id="birthday" name="pojo.birthday"
                                       value="${item.pojo.birthday}" placeholder="Chọn thời gian">
                            </div>
                            <div class="form-group">
                                <label for="pojo.email" class="control-label mb-1">Thư điện tử <span
                                        class="text-danger">*</span></label>
                                <div>
                                    <input type="text" class="form-control" id="pojo.email" name="pojo.email"
                                           value="${item.pojo.email}" placeholder="Nhập vào Email">
                                </div>

                            </div>
                            <div class="form-group">
                                <label for="pojo.phone" class="control-label mb-1">Số điện thoại <span
                                        class="text-danger">*</span></label>
                                <div>
                                    <input type="text" class="form-control" id="pojo.phone" name="pojo.phone"
                                           value="${item.pojo.phone}" placeholder="Nhập vào SDT">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="regency" class="control-label mb-1">Chức vụ</label>
                                <input type="text" class="form-control" id="regency" name="pojo.regency"
                                       value="${item.pojo.regency}" placeholder="Nhập vào chức vụ">
                            </div>
                            <div class="form-group">
                                <label for="gender" class=" control-label">Giới tính</label>
                                <select name="pojo.gender" id="gender" class="form-control">
                                    <option value="0">Nam</option>
                                    <option value="1">Nữ</option>
                                </select>
                            </div>
                            <c:if test="${not empty item.pojo.speakerId}">
                                <input type="hidden" name="pojo.speakerId" value="${item.pojo.speakerId}"/>
                                <input type="hidden" name="pojo.status" value="0">
                                <input type="hidden" name="pojo.creUID" value="${item.pojo.creUID}">
                                <input type="hidden" name="pojo.creDate" value="${item.pojo.creDate}">
                                <input type="hidden" name="pojo.modUID" value="${item.pojo.modUID}">
                                <input type="hidden" name="pojo.modDate" value="${item.pojo.modDate}">
                            </c:if>
                            <c:if test="${empty item.pojo.speakerId}">
                                <input type="hidden" name="pojo.status" value="0">
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

    </c:otherwise>
</c:choose>
<script>
    $('#birthday').datetimepicker({
        format: 'DD/MM/YYYY'
    });
</script>
<script src="<c:url value='/template/js/manager/validate-speaker.js'/>"></script>