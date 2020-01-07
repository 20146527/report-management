<%--
  Created by IntelliJ IDEA.
  User: HungPhan
  Date: 5/28/2019
  Time: 11:01 AM
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
            <li class="breadcrumb-item">Cá nhân</li>
            <li class="breadcrumb-item active">Bộ từ điển cá nhân</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->

<div class="container-fluid">

    <div class="col-12">

        <div class="card">
            <div class="card-body">
                <div class="card-title">
                    <h3 class="text-center">Quản lý bộ từ điển tốc ký cá nhân</h3>
                </div>
                <hr>
                <ul class="nav nav-pills m-t-30 m-b-30">
                    <li class="nav-item"><a href="#navpills-person-list-dict" class="nav-link active" id="tab-1"
                                            data-toggle="tab"
                                            aria-expanded="false">Danh sách</a></li>

                    <li class="nav-item"><a href="#navpills-add-person-dict" class="nav-link" id="tab-2"
                                            data-toggle="tab"
                                            aria-expanded="false">Thêm mới</a></li>

                </ul>

                <div class="tab-content br-n pn">

                    <div id="navpills-person-list-dict" class="tab-pane active">
                        <h4 class="card-title">Nội dung bộ từ điển cá nhân</h4>
                        <div class="table-responsive">
                            <table id="table-person-dict" class="table table-striped table-bordered" style="width:100%">

                                <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Mã Steno</th>
                                    <th>Âm tiết/Cụm từ/Câu</th>
                                    <th>Ngày sửa</th>
                                    <th>Trạng thái</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>

                                <tbody>
                                <%--@elvariable id="list" type="java.util.List<ubnd.core.data.obj.DictPerson>"--%>
                                <c:set var="index" scope="session" value="0"/>
                                <c:forEach items="${list}" varStatus="loop" var="tempt">
                                    <c:set var="index" scope="session" value="${index + 1}"/>
                                <tr>
                                    <td class="index" id="${tempt.idCode}">${index}</td>
                                    <td class="code-steno">${tempt.codeSteno}</td>
                                    <td class="word">${tempt.word}</td>
                                    <td class="mod-date-dict">${tempt.modUpdate}</td>
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
                                                onclick="editDict(this)">
                                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                        </button>


                                        <c:if test="${tempt.status == 0}">
                                            <button id="bt-default-dict" class="btn btn-warning"
                                                    onclick="redirectFunction('disable', this)"
                                                    data-tooltip="Vô hiệu hóa">
                                                <i class="fa fa-ban" aria-hidden="true"></i>
                                            </button>
                                        </c:if>

                                        <c:if test="${tempt.status == 2}">
                                            <button id="bt-default-dict" class="btn btn-success"
                                                    onclick="redirectFunction('enable', this)" data-tooltip="Kích hoạt">
                                                <i class="fa fa-check" aria-hidden="true"></i>
                                            </button>
                                        </c:if>

                                        <button id="bt-delete-dict" class="btn btn-danger"
                                                onclick="fnDeleteDict('delete', this)" data-tooltip="Xóa">
                                            <i class="fa fa-trash-o" aria-hidden="true"></i>
                                        </button>
                                    </td>
                                </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                    <div id="navpills-add-person-dict" class="tab-pane">
                        <h4 class="card-title mb-4">Thêm mới bộ quy tắc gõ cho từ điển</h4>
                        <h5 class="mb-4">Thứ tự gõ và các từ hợp lệ: <i style="color: red">${oder}</i>
                        </h5>
                        <div class="row mb-2">
                            <div class="col-5">Mã Steno (Phím/ Tổ hợp phím)</div>
                            <div class="col-5">Từ/ Cụm từ/ Câu</div>
                            <div class="col-2"></div>
                        </div>

                        <div id="add-new-dict">

                            <div class="row add-dict" id="add-dict-0" style="display: flex; align-items: flex-end;">

                                <div class="add-block col-5">
                                    <form class="input-key-steno">
                                        <label for="key-steno-0" id="label-key-steno-0" class="none"></label>

                                        <input style="text-transform:uppercase" type="text"
                                               class="form-control key-steno" id="key-steno-0"
                                               onkeyup="onChangeDataInput(0)">
                                    </form>
                                </div>

                                <div class="col-5">
                                    <label for="value-0" id="label-value-0" class="none"></label>
                                    <input type="text" class="form-control value" id="value-0"
                                           onkeyup="onchangeInputWord(0)">
                                </div>

                                <div class="col-2" style="display: flex; align-items: flex-end;">
                                    <div class="row btn-status">
                                        <button type="button" id="btn-add-block" class="btn btn-outline-primary size"><i
                                                class="fa fa-plus"></i>
                                        </button>

                                        <button type="button" class="btn btn-outline-success ml-2 size none"
                                                id="success-0"><i class="fa fa-check"></i>
                                        </button>

                                        <button type="button" class="btn btn-outline-danger ml-2 size none"
                                                id="danger-0"><i class="fa fa-close"></i>
                                        </button>

                                        <button type="button" class="btn btn-outline-warning ml-2 size none"
                                                id="warning-0"><i class="fa fa-exclamation-triangle"></i>
                                        </button>

                                    </div>

                                </div>

                            </div>

                        </div>

                        <div class="button-submit row mt-5">
                            <div class="col-5">
                                <button id="btn-cancel" class="btn btn-danger" style="width: 100%"><i class="fa fa-times"></i>&nbsp;<Hủy></Hủy></button>
                            </div>

                            <div class="col-5">
                                <button id="btn-submit-dict" class="btn btn-success" style="width: 100%"
                                        onclick="onClickUpdateDict()"><i class="fa fa-upload"></i> Thêm mới
                                </button>
                            </div>

                            <div class="col-2"></div>


                        </div>

                    </div>

                </div>

            </div>
        </div>
    </div>
</div>


<!-- Modal -->
<div id="notification" class="modal fade" role="dialog">
    <div class="modal-dialog modal-sm">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" style="color: red"><i class="fa fa-exclamation-triangle"></i>Thông Báo</h4>
            </div>
            <div class="modal-body">
                <p>Vui lòng đảm bảo đúng các trường!</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>


<!-- Modal -->
<div id="edit-person-dict" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Chỉnh Sửa
                    <div class="modal-value" style="display: none"></div>
                    <div class="modal-code" style="display: none"></div>
                    <div class="modal-steno-value" style="display: none"></div>
                </h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="steno-code">Mã steno (Phím/ Tổ hợp phím)</label>
                        <label class="code-error-steno" style="color:red; display: none">*Lỗi</label>
                        <input type="text" class="form-control" id="steno-code" style="text-transform: uppercase">
                    </div>
                    <div class="form-group">
                        <label for="steno-value">Âm tiết/Cụm từ/Câu</label>
                        <label class="value-error-steno" style="color: red; display: none">*Lỗi</label>
                        <input type="text" class="form-control" id="steno-value">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-danger" onclick="updatePersonCodeSteno()">Cập nhật</button>
            </div>
        </div>

    </div>
</div>

<script>
    const fullStr = "${oder}";
    const subStr = fullStr.substring(12, fullStr.length);
</script>


<script src="<c:url value="/template/js/steno/dict/person-dict-steno.js"/>"></script>

<script>
    $(document).ready(function () {
        $("#table-person-dict").DataTable({
            "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
        });
        let index_id = 1;
        $("#btn-add-block").click(function () {
            let id = index_id++;

            let data_append_block = ' <div class=" row add-dict mt-3" style="display: flex; align-items: flex-end;" id="add-dict-' + id + '" >\n' +
                '\n' +
                '                            <div class="add-block col-5">\n' +
                '                            <form class="input-key-steno">\n' +
                '                                <label for="key-steno-' + id + '" id="label-key-steno-' + id + '" class="none"></label>\n' +
                '                                    <input style="text-transform:uppercase" type="text" class="form-control key-steno" id="key-steno-' + id + '" onkeyup="onChangeDataInput(' + id + ')">\n' +
                '                                </div>\n' +
                '\n' +
                '                                <div class="col-5">\n' +
                '                                   <label for="value-' + id + '" id="label-value-' + id + '" class="none"></label>\n' +
                '                                    <input type="text" class="form-control value" id="value-' + id + '" onkeyup="onchangeInputWord(' + id + ')">\n' + '</form>' +
                '                                </div>\n' +
                '\n' +
                '                                <div class="col-2" style="display: flex; align-items: flex-end;">\n' +
                '\n' +
                '                                    <div class="row">\n' +
                '\n' +
                '                                        <button type="button" class="btn btn-outline-danger delete-block size"  id="' + id + '"  \n' +
                '                                               ><i class="fa fa-trash"></i>\n' +
                '                                        </button>\n' +
                '\n' +
                '                                        <button type="button" class="btn btn-outline-success ml-2 none size" id="success-' + id + '"\n' +
                '                                               ><i class="fa fa-check"></i>\n' +
                '                                        </button>\n' +
                '\n' +
                '                                        <button type="button"  class="btn btn-outline-warning ml-2 none size" id="warning-' + id + '"\n' +
                '                                             ><i class="fa fa-exclamation-triangle"></i>\n' +
                '                                        </button>\n' +
                '\n' +
                '                                        <button type="button" class="btn btn-outline-danger ml-2 none size" id="danger-' + id + '"\n' +
                '                                               ><i class="fa fa-close"></i>\n' +
            '                                        </button>\n' +
            '\n' +
            '                                    </div>\n' +
            '\n' +
            '                                </div>\n' + script_delete_block() +
            '\n' +
            '                            </div>';
            $("#add-new-dict").append(data_append_block);
        });

    });
</script>

<div id="input-person" data-path="${dict_person}"></div>

</body>
</html>
