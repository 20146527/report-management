<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="editSpeakerUrl" value="/ajax-speaker-edit.html">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:url var="listSpeakerUrl" value="/manager-speaker-list.html">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url var="searchSpeakerUrl" value="/manager-speaker-list.html">
    <c:param name="urlType" value="url_search"/>
</c:url>
<html>
<head>
    <title>Danh sách người họp</title>
</head>
<body>

<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item">Quản lý người họp</li>
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

            <!-- Search -->
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Tìm kiếm</h4>
                    <h6 class="card-subtitle">Nhập thông tin tìm kiếm</h6>
                    <form action="${searchSpeakerUrl}" method="get" id="searchSpeakerForm">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label">Họ và tên</label>
                                    <input type="text" class="form-control"
                                           id="fullname"
                                           name="pojo.fullName"
                                           placeholder="Nhập tên người họp">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="control-label">Email</label>
                                    <input type="text" class="form-control"
                                           id="email"
                                           name="pojo.email"
                                           placeholder="Nhập địa chỉ email">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="control-label">Số điện thoại</label>
                                    <input type="text" class="form-control"
                                           id="phone"
                                           name="pojo.phone"
                                           placeholder="Nhập số điện thoại">
                                </div>
                            </div>
                            <input type="hidden" name="urlType" value="url_search">
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label class="control-label">Hành động</label><br>
                                    <button type="submit" id="btnSearch"
                                            class="btn btn-warning btn-rounded m-b-10 m-l-5 btn-block">
                                        <i class="fa fa-search"></i> Tìm kiếm
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- End Search -->

            <!-- List -->
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Danh sách</h4>

                    <button type="button" name="button" onclick="update(this)" class="btn btn-outline-info">
                        <i class="fa fa-plus"></i>&nbsp;
                        <span>Thêm mới người họp</span>
                    </button>
                    <h6 class="card-subtitle">Xuất dữ liệu dạng Copy, CSV, Excel, PDF & In</h6>
                    <%--alert--%>
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-${alert} alert-dismissible fade show">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <span>${messageResponse}</span>
                        </div>
                    </c:if>
                    <%--alert--%>

                        <div class="table-responsive m-t-10">
                            <table id="example23"
                                   class="display nowrap table table-hover table-striped table-bordered"
                                   cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Tên</th>
                                    <th>Chức vụ</th>
                                    <th>Sdt</th>
                                    <th>Sample</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${items}" varStatus="loop" var="row">
                                    <tr>
                                        <td>${row.getSpeakerId()}</td>
                                        <td>${row.getFullName()}</td>
                                        <td>${row.getRegency()}</td>
                                        <td>${row.getPhone()}</td>
                                        <td>2</td>
                                        <c:url var="editUrl" value="/ajax-speaker-edit.html">
                                            <c:param name="urlType" value="url_edit"/>
                                            <c:param name="pojo.speakerId" value="${row.getSpeakerId()}"/>
                                        </c:url>
                                        <c:url var="deleteUrl" value="/ajax-speaker-edit.html">
                                            <c:param name="urlType" value="url_delete"/>
                                            <c:param name="pojo.speakerId" value="${row.getSpeakerId()}"/>
                                            <c:param name="pojo.fullName" value="${row.getFullName()}"/>
                                        </c:url>
                                        <c:url var="listASampleUrl" value="/ajax-audio-sample.html">
                                            <c:param name="urlType" value="url_as_list"/>
                                            <c:param name="pojo.speakerId" value="${row.getSpeakerId()}"/>
                                        </c:url>
                                        <td>
                                            <button type="button" name="submit" class="btn btn-info"
                                                    sc-url="${listASampleUrl}" onclick="asList(this)"
                                                    data-tooltip="Âm thanh mẫu">
                                                <i class="fa fa-file-audio-o"></i>&nbsp;
                                            </button>
                                            <button type="button" name="submit" class="btn btn-success"
                                                    sc-url="${editUrl}" onclick="update(this)"
                                                    data-tooltip="Sửa">
                                                <i class="fa fa-pencil-square-o"></i>&nbsp;
                                            </button>
                                            <button type="button" name="submit" class="btn btn-danger"
                                                    sc-url="${deleteUrl}" onclick="remove(this)"
                                                    data-tooltip="Xóa">
                                                <i class="fa fa-trash"></i>&nbsp;
                                            </button>
                                        </td>
                                    </tr>
                                    <%--                                </form>--%>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    <form action="${listSpeakerUrl}" method="get" id="formUrl">
                        <input type="hidden" name="crudaction" id="crudaction"/>
                        <input type="hidden" name="urlType" id="urlType"/>
                    </form>

                </div>
            </div>
            <!-- End List -->
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog" data-backdrop="static" data-keyboard="false"></div>
<div class="modal fade" id="modalAS" role="dialog" data-backdrop="static" data-keyboard="false"></div>
<div class="modal fade" id="modalRemove" role="dialog" data-backdrop="static" data-keyboard="false"></div>

<script>
    document.title = "Quản lý người họp";
    $(document).ready(function () {
        //$('#myModal').modal({backdrop: 'static', keyboard: false});
    });
    function asList(btn) {
        var asList = $(btn).attr('sc-url');
        $("#overlay").css({"display": "block"});
        $('#modalAS').load(asList,'', function () {
            $("#overlay").css({"display": "none"});
            $('#modalAS').modal('toggle');
            //asControl();
        });
    }
    function asControl() {
        $('#uploadASampleForm').submit(function (e) {
            e.preventDefault();
            $.ajax({
                type: $(this).attr('method'),
                enctype : 'multipart/form-data',
                url: $(this).attr('action'),
                data: $(this).serialize(),
                dataType: 'html',
                success: function (res) {
                    console.log(res);
                    if(res.trim() == "redirect_insert"){
                        $('#crudaction').val('redirect_insert');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    }else if (res.trim() == "redirect_update") {
                        $('#crudaction').val('redirect_update');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    } else if (res.trim() == "redirect_error") {
                        $('#crudaction').val('redirect_error');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            });
        });
    }
    function remove(btn) {
        var removeUrl = $(btn).attr('sc-url');
        $('#modalRemove').load(removeUrl,'', function () {
            $('#modalRemove').modal('toggle');
            removeEXE();
        });
    }
    function removeEXE() {
        $('#btnRemove').click(function () {
            $('#removeSpeakerForm').submit();
        });
        $('#removeSpeakerForm').submit(function (e) {
            e.preventDefault();
            $.ajax({
                type: $(this).attr('method'),
                url: $(this).attr('action'),
                data: $(this).serialize(),
                dataType: 'html',
                success: function (res) {
                    if(res.trim()=="redirect_delete"){
                        $('#crudaction').val('redirect_delete');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            });
        });
    }
    function update(btn) {
        var editUrl = $(btn).attr('sc-url');
        if (typeof editUrl == 'undefined') {
            editUrl = '${editSpeakerUrl}';
        }
        $("#overlay").css({"display": "block"});
        $('#myModal').load(editUrl,'', function () {
            $('#myModal').modal('toggle');
            $("#overlay").css({"display": "none"});
            //addOrEdit();
        });
    }
    function addOrEdit() {
        $('#btnSave').click(function () {
            $('#editSpeakerForm').submit();
        });
        $('#editSpeakerForm').submit(function (e) {
            e.preventDefault();
            $('#crudactionEdit').val('insert_update');
            $.ajax({
                type: $(this).attr('method'),
                url: $(this).attr('action'),
                data: $(this).serialize(),
                dataType: 'html',
                success: function (res) {
                    if(res.trim()=="redirect_insert"){
                        $('#crudaction').val('redirect_insert');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    }else if (res.trim() == "redirect_update") {
                        $('#crudaction').val('redirect_update');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    } else if (res.trim() == "redirect_error") {
                        $('#crudaction').val('redirect_error');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            });
        });
    }

    // function remove(btn) {
    //     var removeUrl = $(btn).attr('sc-url');
    //     $.ajax({
    //         type: 'post',
    //         url: removeUrl,
    //         data: $(this).serialize(),
    //         success: function (res) {
    //             console.log(res);
    //         },
    //         error: function (res) {
    //             console.log(res);
    //         }
    //     })
    // }

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
</body>
</html>
