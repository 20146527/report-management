<%--
  Created by IntelliJ IDEA.
  User: HungPhan
  Date: 12/9/2019
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item active">Chi tiết bộ quy tắc gõ</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->


<div class="container-fluid">
    <div class="col-12">
        <div class="card">
            <div class="card-body">
                <div class="card-title">
                    <h3 class="text-center">Chi tiết bộ quy tắc gõ</h3>
                </div>

                <hr>

                <ul class="nav nav-pills m-t-30 m-b-30">

                    <li class="nav-item"><a href="#first-word" class="nav-link active" id="tab-1"
                                            data-toggle="tab"
                                            aria-expanded="false">Âm đầu</a></li>

                    <li class="nav-item"><a href="#main-word" class="nav-link" id="tab-2"
                                            data-toggle="tab"
                                            aria-expanded="false">Âm chính</a></li>

                    <li class="nav-item"><a href="#last-word" class="nav-link" id="tab-3"
                                            data-toggle="tab"
                                            aria-expanded="false">Âm cuối</a></li>
                </ul>

                <div class="tab-content br-n pn">
                    <div id="first-word" class="tab-pane active">
                        <h4 class="card-title mb-4">Danh sách quy tắc cho âm đầu</h4>

                        <div class="table-responsive">
                            <table id="table-list-first" class="table table-striped table-bordered" style="width:100%">

                                <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Âm đầu</th>
                                    <th>Phím steno</th>
                                    <th>Ngày sửa</th>
                                    <th>Người sửa</th>
                                    <th>Trạng thái</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:set var="index" value="0"/>
                                <jsp:useBean id="first" scope="request"
                                             type="java.util.List<ubnd.core.data.obj.StenoRuleObject>"/>
                                <c:forEach items="${first}" varStatus="loop" var="tempt">
                                    <c:set var="index" value="${index + 1}"/>
                                <tr class="${index}-column-first">
                                    <td class="index">${index}</td>
                                    <td class="value-word">${tempt.value}</td>
                                    <td class="value-key-steno">${tempt.key}</td>
                                    <td>${tempt.modeDate}</td>
                                    <td>${tempt.modUser}</td>
                                    <td>
                                        <c:if test="${tempt.status == 0}">
                                            <span class="badge btn-success">Hoạt động</span>
                                        </c:if>

                                        <c:if test="${tempt.status == 2}">
                                            <span class="badge btn-warning">Vô hiệu hóa</span>
                                        </c:if>
                                    </td>
                                    <td>
                                        <button id="bt-edit-dict" class="btn btn-info" data-tooltip="Chỉnh sửa"
                                                onclick="openModalEit(1, this, '${tempt.id}')">
                                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                        </button>


                                        <c:if test="${tempt.status == 0}">
                                            <button id="bt-default-dict" class="btn btn-warning"
                                                    onclick="disableRule(1, '${tempt.id}', ${id})"
                                                    data-tooltip="Vô hiệu hóa">
                                                <i class="fa fa-ban" aria-hidden="true"></i>
                                            </button>
                                        </c:if>

                                        <c:if test="${tempt.status == 2}">
                                            <button id="bt-default-dict" class="btn btn-success"
                                                    onclick="disableRule(1, '${tempt.id}', ${id})"
                                                    data-tooltip="Kích hoạt">
                                                <i class="fa fa-check" aria-hidden="true"></i>
                                            </button>
                                        </c:if>

                                        <button id="bt-delete-dict" class="btn btn-danger"
                                                onclick="deleteRule(1, '${tempt.id}', ${id})" data-tooltip="Xóa">
                                            <i class="fa fa-trash-o" aria-hidden="true"></i>
                                        </button>
                                    </td>
                                </tr>
                                </c:forEach>

                            </table>
                        </div>

                    </div>

                    <div id="main-word" class="tab-pane">
                        <h4 class="card-title mb-4">Danh sách quy tắc cho âm chính</h4>


                        <div class="table-responsive">
                            <table id="table-list-main" class="table table-striped table-bordered" style="width:100%">

                                <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Âm chính</th>
                                    <th>Phím steno</th>
                                    <th>Ngày sửa</th>
                                    <th>Người sửa</th>
                                    <th>Trạng thái</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:set var="index" value="0"/>
                                <jsp:useBean id="main" scope="request"
                                             type="java.util.List<ubnd.core.data.obj.StenoRuleObject>"/>
                                <c:forEach items="${main}" varStatus="loop" var="tempt">
                                    <c:set var="index" value="${index + 1}"/>
                                <tr class="${index}-column-main">
                                    <td class="index">${index}</td>
                                    <td class="value-word">${tempt.value}</td>
                                    <td class="value-key-steno">${tempt.key}</td>
                                    <td>${tempt.modeDate}</td>
                                    <td>${tempt.modUser}</td>
                                    <td>
                                        <c:if test="${tempt.status == 0}">
                                            <span class="badge btn-success">Hoạt động</span>
                                        </c:if>

                                        <c:if test="${tempt.status == 2}">
                                            <span class="badge btn-warning">Vô hiệu hóa</span>
                                        </c:if>
                                    </td>
                                    <td>
                                        <button id="bt-edit-dict" class="btn btn-info" data-tooltip="Chỉnh sửa"
                                                onclick="openModalEit(2, this, '${tempt.id}')">
                                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                        </button>


                                        <c:if test="${tempt.status == 0}">
                                            <button id="bt-default-dict" class="btn btn-warning"
                                                    onclick="disableRule(2, '${tempt.id}', ${id})"
                                                    data-tooltip="Vô hiệu hóa">
                                                <i class="fa fa-ban" aria-hidden="true"></i>
                                            </button>
                                        </c:if>

                                        <c:if test="${tempt.status == 2}">
                                            <button id="bt-default-dict" class="btn btn-success"
                                                    onclick="disableRule(2, '${tempt.id}', ${id})"
                                                    data-tooltip="Kích hoạt">
                                                <i class="fa fa-check" aria-hidden="true"></i>
                                            </button>
                                        </c:if>

                                        <button id="bt-delete-dict" class="btn btn-danger"
                                                onclick="deleteRule(2, '${tempt.id}', ${id})" data-tooltip="Xóa">
                                            <i class="fa fa-trash-o" aria-hidden="true"></i>
                                        </button>
                                    </td>
                                </tr>
                                </c:forEach>

                            </table>
                        </div>

                    </div>

                    <div id="last-word" class="tab-pane">
                        <h4 class="card-title mb-4">Danh sách quy tắc cho âm cuối</h4>

                        <div class="table-responsive">
                            <table id="table-list-last" class="table table-striped table-bordered" style="width:100%">

                                <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Âm cuối</th>
                                    <th>Phím steno</th>
                                    <th>Ngày sửa</th>
                                    <th>Người sửa</th>
                                    <th>Trạng thái</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:set var="index" value="0"/>
                                <jsp:useBean id="last" scope="request"
                                             type="java.util.List<ubnd.core.data.obj.StenoRuleObject>"/>
                                <c:forEach items="${last}" varStatus="loop" var="tempt">
                                    <c:set var="index" value="${index + 1}"/>
                                <tr class="${index}-column-last">
                                    <td class="index">${index}</td>
                                    <td class="value-word">${tempt.value}</td>
                                    <td class="value-key-steno">${tempt.key}</td>
                                    <td>${tempt.modeDate}</td>
                                    <td>${tempt.modUser}</td>
                                    <td>
                                        <c:if test="${tempt.status == 0}">
                                            <span class="badge btn-success">Hoạt động</span>
                                        </c:if>

                                        <c:if test="${tempt.status == 2}">
                                            <span class="badge btn-warning">Vô hiệu hóa</span>
                                        </c:if>
                                    </td>
                                    <td>
                                        <button id="bt-edit-dict" class="btn btn-info" data-tooltip="Chỉnh sửa"
                                                onclick="openModalEit(3, this, '${tempt.id}')">
                                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                        </button>


                                        <c:if test="${tempt.status == 0}">
                                            <button id="bt-default-dict" class="btn btn-warning"
                                                    onclick="disableRule(3, '${tempt.id}', ${id})"
                                                    data-tooltip="Vô hiệu hóa">
                                                <i class="fa fa-ban" aria-hidden="true"></i>
                                            </button>
                                        </c:if>

                                        <c:if test="${tempt.status == 2}">
                                            <button id="bt-default-dict" class="btn btn-success"
                                                    onclick="disableRule(3, '${tempt.id}', ${id})"
                                                    data-tooltip="Kích hoạt">
                                                <i class="fa fa-check" aria-hidden="true"></i>
                                            </button>
                                        </c:if>

                                        <button id="bt-delete-dict" class="btn btn-danger"
                                                onclick="deleteRule(3, '${tempt.id}', ${id})" data-tooltip="Xóa">
                                            <i class="fa fa-trash-o" aria-hidden="true"></i>
                                        </button>
                                    </td>
                                </tr>
                                </c:forEach>

                            </table>
                        </div>

                    </div>

                </div>

            </div>
        </div>
    </div>
</div>

<script>
    const oder = "${oder}";
</script>

<script src="<c:url value="/template/js/steno/typing/rule/typing-rule.js"/>"></script>


<!-- Modal -->
<div id="edit-steno-rule" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Chỉnh Sửa
                    <div class="modal-type" style="display: none"></div>
                    <div class="modal-key" style="display: none"></div>
                    <div class="modal-idCode" style="display: none"></div>
                </h4>
            </div>
            <div class="modal-body">
                <div id="content-input-suggestions" class="mb-4">
                    <h5>Thứ tự và các từ hợp lệ: <span style="font-style: italic; color: red">STKPWH</span></h5>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="value-code">Âm đầu</label>
                            <input type="text" class="form-control" id="value-code" disabled>
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <label for="steno-value">Phím Steno</label>
                            <input type="text" class="form-control" id="steno-value" onkeyup="checkFirst()"
                                   style="text-transform:uppercase">
                            <label class="mt-2" id="value-error-steno"
                                   style="color: red; display: none; font-style: italic; font-size: smaller">*Lỗi</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-danger" onclick="updateRule('${id}')">Cập nhật</button>
            </div>
        </div>

    </div>
</div>

</body>
</html>
