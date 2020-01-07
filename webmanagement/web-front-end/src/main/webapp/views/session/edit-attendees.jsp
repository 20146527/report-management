<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:choose>
    <c:when test="${not empty messageResponse}">
        ${messageResponse}
    </c:when>
    <c:otherwise>
        <!-- Modal -->
        <div class="modal-dialog modal-xl">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Chỉnh sửa người tham dự</h5>
                    </div>
                    <div class="modal-body">
                        <ul class="nav nav-pills m-t-30 m-b-30">
                            <li class=" nav-item"><a href="#navpills-4" class="nav-link active" data-toggle="tab" aria-expanded="false">Tự động</a></li>
                            <li class="nav-item"><a href="#navpills-5" class="nav-link" data-toggle="tab" aria-expanded="false">Nhập tay
                            </a></li>
                        </ul>
                        <div class="tab-content br-n pn">
                            <div id="navpills-4" class="tab-pane active">
                                <div class="row">
                                    <h1>Chỗ này dành để nhận dạng tự động</h1>
                                </div>
                            </div>
                            <div id="navpills-5" class="tab-pane">
                                <div class="row">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>

    </c:otherwise>
</c:choose>

<script src="<c:url value="/template/js/lib/datatables/datatables.min.js"/>"></script>
<script src="<c:url value="/template/js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"/>"></script>
<script src="<c:url value="/template/js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.flash.min.js"/>"></script>
<script src="<c:url value="/template/js/lib/datatables/cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"/>"></script>
<script src="<c:url value="/template/js/lib/datatables/cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"/>"></script>
<script src="<c:url value="/template/js/lib/datatables/cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"/>"></script>
<script src="<c:url value="/template/js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"/>"></script>
<script src="<c:url value="/template/js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"/>"></script>
<script src="<c:url value="/template/js/lib/datatables/datatables-init.js"/>"></script>