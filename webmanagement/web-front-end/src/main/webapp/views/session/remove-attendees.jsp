<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="removeMemberUrl" value="/ajax-session-edit.html">
    <c:param name="urlType" value="url_remove_member"/>
    <c:param name="sessionId" value="${sessionId}"/>
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
                        <h5 class="modal-title" id="exampleModalLabel">Xác nhận</h5>
                    </div>
                    <div class="modal-body">
                        <h3>Xác nhận bỏ "${fullName}" khỏi danh sách người tham dự họp?</h3>
                    </div>
                    <form action="${removeMemberUrl}" method="post" id="removeMemberForm">
                        <input type="hidden" name="attendeesId" value="${attendeesId}">
                        <input type="hidden" name="sessionId" value="${sessionId}">
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                        <button type="button" id="btnRemove" class="btn btn-danger">Xóa</button>
                    </div>
                </div>
            </div>

    </c:otherwise>
</c:choose>