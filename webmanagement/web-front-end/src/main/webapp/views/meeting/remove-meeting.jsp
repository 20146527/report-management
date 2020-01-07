<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 27/07/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
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
                        <h5 class="modal-title" id="exampleModalLabel">Xác nhận</h5>
                    </div>
                    <div class="modal-body">
                        <h3>Xác nhận xóa thông tin: "${item.pojo.name}" ?</h3>
                    </div>
                    <form action="${deleteMeetingUrl}" method="post" id="removeMeetingForm">
                        <input type="hidden" name="pojo.meetingId" value="${item.pojo.meetingId}">
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                        <button type="button" id="btnRemove" class="btn btn-danger">Xóa</button>
                    </div>
                </div>
            </div>

    </c:otherwise>
</c:choose>