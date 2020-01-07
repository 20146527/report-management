<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 27/07/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="createTemplateUrl" value="/manager-template.html">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:url var="listTemplateUrl" value="/manager-template.html">
    <c:param name="urlType" value="url_list"/>
</c:url>
<html>
<head>
    <title>Tạo cuộc họp</title>
</head>
<body>

<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item">Template</li>
            <li class="breadcrumb-item active">Danh sách</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->

<!-- Container fluid  -->
<div class="container-fluid">
    <!-- Start Page Content -->
    <div class="row">
        <div class="col-12">

            <!-- List -->
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Danh sách</h4>
                    <h6 class="card-subtitle">Xuất dữ liệu dạng Copy, CSV, Excel, PDF & In</h6>
                    <%--alert--%>
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-${alert} alert-dismissible fade show">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <strong>${messageResponse}</strong>
                        </div>
                    </c:if>
                    <%--alert--%>
                    <button type="button" name="button" onclick="location.href='${createTemplateUrl}'"
                            class="btn btn-outline-info">
                        <i class="fa fa-plus"></i>&nbsp;
                        <span>Thêm mới template</span>
                    </button>
                    <div class="table-responsive m-t-10">
                        <table id="example23"
                               class="display nowrap table table-hover table-striped table-bordered"
                               cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Tên</th>
                                <th>Thời gian tạo</th>
                                <th>Thời gian sửa</th>
                                <th>Hành động</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="index" value="0"/>
                            <c:forEach items="${items}" varStatus="loop" var="row">
                                <c:set var="index" value="${index + 1}"/>
                                <tr>
                                    <td>${index}</td>
                                    <td>${row.getName()}</td>
                                    <td>${row.getCreDate()}</td>
                                    <td>${row.getModDate()}</td>
                                    <td>
                                        <c:url var="editUrl" value="/manager-template.html">
                                            <c:param name="urlType" value="url_edit"/>
                                            <c:param name="pojo.templateId" value="${row.getTemplateId()}"/>
                                        </c:url>
                                        <button type="button" name="edit" class="btn btn-success"
                                                onclick="location.href='${editUrl}'"
                                                data-tooltip="Sửa">
                                            <i class="fa fa-pencil-square-o"></i>&nbsp;
                                        </button>
                                        <button type="button" name="remove" class="btn btn-danger"
                                                onclick="removeTemplate(this)" templateId="${row.getTemplateId()}"
                                                data-tooltip="Xóa" templateName="${row.getName()}">
                                            <i class="fa fa-trash"></i>&nbsp;
                                        </button>
                                    </td>
                                </tr>
                                <%--                                </form>--%>
                            </c:forEach>
                            </tbody>
                        </table>
                        <form action="${listTemplateUrl}" method="get" id="formUrl">
                            <input type="hidden" name="crudaction" id="crudaction"/>
                            <input type="hidden" name="urlType" id="urlType"/>
                        </form>
                    </div>

                </div>
            </div>
            <!-- End List -->
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="modalRemove" role="dialog" data-backdrop="static" data-keyboard="false"></div>


<script>
    document.title = "Quản lý cuộc họp";
    $(document).ready(function () {
        //searchMeeting();
        //$('#myModal').modal({backdrop: 'static', keyboard: false});
    });

    function removeTemplate(btn) {
        let url = '/ajax-template-edit.html';
        let templateId = $(btn).attr('templateId');
        let templateName = $(btn).attr('templateName');
        swal({
                title: "Xác nhận xóa ?",
                text: "Template: " + templateName,
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Xóa",
                cancelButtonText: "Hủy",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function (isConfirm) {
                if (isConfirm) {
                    $.ajax({
                        type: 'post',
                        url: url,
                        data: {
                            urlType: 'url_delete',
                            templateId: templateId
                        },
                        success: function (res) {
                            if(res == 'Success'){
                                swal({
                                    title: "Đã xóa !",
                                    text: "Đã xóa template " + templateName,
                                    type: "success"
                                }, function () {
                                    swal.close();
                                    $('#urlType').val('url_list');
                                    $('#formUrl').submit();
                                })
                            }else {
                                swal({
                                    title: "Xảy ra lỗi !",
                                    text: "Lỗi khi xóa " + templateName,
                                    type: "error"
                                }, function () {
                                    swal.close();
                                    $('#urlType').val('url_list');
                                    $('#formUrl').submit();
                                })
                            }

                        },
                        error: function (res) {
                            swal("Lỗi !!", "Không gửi được yêu cầu xóa", "success");
                            setTimeout(function () {
                                $('#urlType').val('url_list');
                                $('#formUrl').submit();
                            }, 1000);
                        }
                    });
                } else {
                    swal("Đã hủy !!", "Hủy yêu cầu xóa", "error");
                }
            });
    }


</script>

</body>
</html>
