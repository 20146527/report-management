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
<c:url var="editRoleUrl" value="/ajax-permission-edit.html">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:choose>
    <c:when test="${not empty messageResponse}">
        ${messageResponse}
    </c:when>
    <c:otherwise>
        <!-- Modal -->
        <div class="modal-dialog modal-lg">
            <div id="overlay" class="overlay-modal">
                <progress id="progress" class="pure-material-progress-circular"
                          style="margin: 20% auto auto; display: block;"></progress>
            </div>
            <div class="modal-content">
                <div class="modal-header">
                    <c:if test="${not empty roleId}">
                        <h5 class="modal-title" id="exampleModalLabel">Chỉnh sửa thông tin quyền</h5>
                    </c:if>
                    <c:if test="${empty item.roleId}">
                        <h5 class="modal-title" id="exampleModalLabel">Thêm mới quyền người dùng</h5>
                    </c:if>

                </div>
                <div class="form-validation">
                    <form action="${editRoleUrl}" method="post" id="editRoleForm" class="form-valide">
                        <div class="modal-body">

                            <div class="form-group">
                                <label for="roleName" class="control-label mb-1">Tên quyền <span
                                        class="text-danger">*</span></label>
                                <div>
                                    <input type="text" class="form-control" id="roleName" name="roleName"
                                           value="${item.roleName}" placeholder="VD: Admin">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="roleDescription" class="control-label mb-1">Mô tả quyền <span
                                        class="text-danger">*</span></label>
                                <div>
                                    <input type="text" class="form-control" id="roleDescription" name="roleDescription"
                                           value="${item.roleDescription}" placeholder="Nhập mô tả quyền">
                                </div>
                            </div>
                            <input type="hidden" name="crudaction" id="crudactionEdit" value="insert_update"/>
                            <input type="hidden" name="listRoleSize" id="listRoleSize" value="${item.listRoleSize}"/>
                            <c:if test="${not empty item.roleId}">
                                <input type="hidden" name="roleId" id="roleId" value="${roleId}">
                            </c:if>

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

<script src="<c:url value='/template/js/manager/validate-role.js'/>"></script>
<script>
    $(document).ready(function () {
        $(".overlay-modal").click(function () {
            $(".overlay-modal").css({"display": "block"});
        });
    });
</script>