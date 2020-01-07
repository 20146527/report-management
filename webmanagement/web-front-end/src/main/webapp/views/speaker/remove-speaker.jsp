<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="deleteSpeakerUrl" value="/ajax-speaker-edit.html">
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
                        <h3>Xác nhận xóa thông tin người họp: "${item.pojo.fullName}" ?</h3>
                    </div>
                    <form action="${deleteSpeakerUrl}" method="post" id="removeSpeakerForm">
                        <input type="hidden" name="pojo.speakerId" value="${item.pojo.speakerId}">
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                        <button type="button" id="btnRemove" class="btn btn-danger">Xóa</button>
                    </div>
                </div>
            </div>

    </c:otherwise>
</c:choose>