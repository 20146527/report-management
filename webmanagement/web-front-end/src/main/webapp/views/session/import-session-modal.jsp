<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="validateExcel" value="/ajax-session-edit.html">
</c:url>
<c:choose>
    <c:when test="${not empty messageResponse}">
        ${messageResponse}
    </c:when>
    <c:otherwise>
        <!-- Modal -->
        <div class="modal-dialog modal-xl">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Import phiên họp từ tệp Excel</h5>
                    </div>
                    <div class="modal-body">
                        <form action="${validateExcel}" method="post" enctype="multipart/form-data" id="importSessionForm">
                            <div class="form-group">
                                <label for="divInput" class=" control-label">Tải lên file Excel</label>
                                <div id="divInput" class="custom-file mb-3">
                                    <input type="file" class="custom-file-input input-lg" id="inputFile"
                                           name="file" onchange="inputNameFile()" value="">
                                    <label class="custom-file-label" id="lable-name-file-audio">Nhấp vào đây để tải
                                        lên file</label>
                                </div>
                            </div>
                            <input type="hidden" name="urlType" id="urlTypeImport" value="read_excel"/>
                            <button type="button" id="validateData" class="btn btn-primary">Kiểm tra</button>
                            <c:if test="${not empty items}">
                                <div class="table-responsive m-t-40">
                                    <table id="myTable" class="table table-bordered table-striped">
                                        <thead>
                                        <tr>
                                            <th>Tên</th>
                                            <th>Địa điểm</th>
                                            <th>Thời gian bắt đầu</th>
                                            <th>Thời gian kết thúc</th>
                                            <th>Mô tả</th>
                                        </tr>
                                        </thead>
                                        <tfoot>
                                        <tr>
                                            <th>Tên</th>
                                            <th>Địa điểm</th>
                                            <th>Thời gian bắt đầu</th>
                                            <th>Thời gian kết thúc</th>
                                            <th>Mô tả</th>
                                        </tr>
                                        </tfoot>
                                        <tbody>
                                        <c:forEach items="${items}" varStatus="loop" var="row">
                                            <tr>
                                                <td>${row.getName()}</td>
                                                <td>${row.getAddress()}</td>
                                                <td>${row.getTimeStart()}</td>
                                                <td>${row.getTimeEnd()}</td>
                                                <td>${row.getDescription()}</td>
                                            </tr>
                                        </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                            </c:if>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>

    </c:otherwise>
</c:choose>

<script>
    $(document).ready(function () {
       // $('#validateData').click(function () {
       //     $('#urlType').val('read_excel');
       //     $('#importSessionForm').submit();
       // })
    });

    function inputNameFile() {
        var x = document.getElementById("inputFile");
        if(x.files.length == 1){
            //chon file sai
            var file = x.files[0];
            console.log(file.name);
            //$('#inputFile').val("File: ");
            var nameFile = file.name;
            document.getElementById("lable-name-file-audio").innerHTML = nameFile;
            //document.getElementById("inputFile").value = "Johnny Bravo";
        }else {
            //chi chon 1 file
            console.log('chon nhieu file');
            $('#inputFile').val('Chỉ chọn 1 file');
        }
    }
</script>