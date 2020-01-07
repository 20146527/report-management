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
            <li class="breadcrumb-item">Thống kê</li>
            <li class="breadcrumb-item active">Thống kê file</li>
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
                    <form action="" method="get" id="searchStatisticFileForm">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="meeting" class=" control-label">Cuộc họp</label>
                                    <select name="meetingId" id="meeting" class="form-control">
                                        <option value="0">Chọn cuộc họp...</option>

                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="session" class=" control-label">Phiên họp</label>
                                    <select name="sessionId" id="session" class="form-control">
                                        <option value="0">Chọn phiên họp...</option>

                                    </select>
                                </div>
                            </div>
                            <div class="col-md-5 col-sm-12">
                                <div class="form-group">
                                    <label class="control-label">Thời gian từ</label>
                                    <input type="text" class="form-control"
                                           id="time-start" value=""
                                           name="timeStart" placeholder="Chọn thời gian">
                                </div>
                            </div>
                            <div class="col-md-5 col-sm-12">
                                <div class="form-group">
                                    <label class="control-label">Thời gian đến</label>
                                    <input type="text" class="form-control"
                                           id="time-end" value=""
                                           name="timeEnd" placeholder="Chọn thời gian">
                                </div>
                            </div>
                            <div class="col-md-2 col-sm-12">
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
                    <h4 class="card-title">Danh sách file</h4>

                    <%--alert--%>
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-${alert} alert-dismissible fade show">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <span>${messageResponse}</span>
                        </div>
                    </c:if>
                    <%--alert--%>
                        <div class="table-responsive m-t-10">
                            <table id="myTable" class="display nowrap table table-hover table-striped table-bordered"
                                   cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Loại file</th>
                                    <th>Số lượng</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>File ghi âm</td>
                                    <td>2350</td>
                                    <td>
                                        <button type="button" name="submit" class="btn btn-info" onclick="location.href='statistic-files-record.html'"
                                                data-tooltip="Chi tiết">
                                            <i class="fa fa-asterisk"></i>&nbsp;
                                        </button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>File bóc băng</td>
                                    <td>2350</td>
                                    <td>
                                        <button type="button" name="submit" class="btn btn-info"
                                                data-tooltip="Chi tiết">
                                            <i class="fa fa-asterisk"></i>&nbsp;
                                        </button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>File tốc ký</td>
                                    <td>2350</td>
                                    <td>
                                        <button type="button" name="submit" class="btn btn-info"
                                                data-tooltip="Chi tiết">
                                            <i class="fa fa-asterisk"></i>&nbsp;
                                        </button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>File biên bản</td>
                                    <td>2350</td>
                                    <td>
                                        <button type="button" name="submit" class="btn btn-info"
                                                data-tooltip="Chi tiết">
                                            <i class="fa fa-asterisk"></i>&nbsp;
                                        </button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>File template</td>
                                    <td>2350</td>
                                    <td>
                                        <button type="button" name="submit" class="btn btn-info"
                                                data-tooltip="Chi tiết">
                                            <i class="fa fa-asterisk"></i>&nbsp;
                                        </button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>6</td>
                                    <td>Dữ liệu âm thanh mẫu (CSDL tiếng nói)</td>
                                    <td>2350</td>
                                    <td>
                                        <button type="button" name="submit" class="btn btn-info"
                                                data-tooltip="Chi tiết">
                                            <i class="fa fa-asterisk"></i>&nbsp;
                                        </button>
                                    </td>
                                </tr>
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
    document.title = "Thống kê file";
    $(document).ready(function () {
        $('#time-start').datetimepicker({
            format: 'DD/MM/YYYY hh:mm A'
        });
        $('#time-end').datetimepicker({
            format: 'DD/MM/YYYY hh:mm A'
        });
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
