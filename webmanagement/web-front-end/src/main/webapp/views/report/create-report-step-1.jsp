<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="editUserUrl" value="/ajax-user-edit.html">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:url var="listUserUrl" value="/manager-user-list.html">
    <c:param name="urlType" value="url_list"/>
</c:url>
<html>
<head>
    <title>Danh sách người dùng</title>
</head>
<body>

<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item">Biên bản</li>
            <li class="breadcrumb-item active">Tạo biên bản</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->

<!-- Container fluid  -->
<div class="container-fluid">
    <!-- Start Page Content -->
    <div class="row">
        <div class="col-12">

            <div class="card">
                <div class="card-body">
                    <div class="card-title">
                        <h3 class="text-center">Bước 1/3: Chọn dữ liệu tạo biên bản</h3>
                        <hr>
                        <%--alert--%>
                        <c:if test="${not empty messageResponse}">
                            <div class="alert alert-${alert} alert-dismissible fade show">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <strong>${messageResponse}</strong>
                            </div>
                        </c:if>
                        <%--alert--%>
                        <form action="" method="POST" enctype="multipart/form-data">

                            <div class="form-group">
                                <label for="metting" class=" control-label">Chọn cuộc họp</label>

                                <select name="metting" id="metting" class="form-control">
                                    <option value="0">Chọn cuộc họp...</option>
                                    <c:forEach items="${meetingList}" varStatus="loop" var="op">
                                        <option value="${op.meetingId}">${op.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="session" class=" control-label">Chọn phiên họp</label>

                                <select name="session" id="session" class="form-control">
                                    <option value="0">Chọn phiên...</option>

                                </select>
                            </div>
                            <div class="form-group">
                                <label for="template" class=" control-label">Chọn template</label>
                                <select name="template" id="template" class="form-control">
                                    <c:forEach items="${templateList}" varStatus="loop" var="op">
                                        <option value="${op.templateId}">${op.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="row">
                                <div class="col-lg-6 col-md-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="form-group">
                                                <label for="fileAudio" class=" control-label">Chọn file bóc băng</label>

                                                <div>
                                                    <select name="file" id="fileAudio" class="form-control">
                                                        <option value="0">Chọn file...</option>

                                                    </select>

                                                    <div id="fileAudioReject">
                                                        <h6 class="mt-1" style="color: red">Chưa có quyền truy cập file</h6>
                                                        <button id="btn-request-audio" fileId="0" type="button" class="btn btn-danger sweet-confirm"
                                                                onclick="requestPermission(20, this)" data-tooltip="Xin cấp quyền">
                                                            <i class="fa fa-paper-plane" aria-hidden="true"></i>
                                                            <span>Yêu cầu quyền</span>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="col-lg-6 col-md-12">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="form-group">
                                                <label for="fileSteno" class=" control-label">Chọn file tốc ký</label>
                                                <div>
                                                    <select name="file" id="fileSteno" class="form-control">
                                                        <option value="0">Chọn file...</option>

                                                    </select>
                                                    <div id="fileStenoReject">
                                                        <h6 class="mt-1" style="color: red">Chưa có quyền truy cập file</h6>
                                                        <button id="btn-request-steno" fileId="0" type="button" class="btn btn-danger sweet-confirm"
                                                                onclick="requestPermission(30, this)" data-tooltip="Xin cấp quyền">
                                                            <i class="fa fa-paper-plane" aria-hidden="true"></i>
                                                            <span>Yêu cầu quyền</span>
                                                        </button>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>

                        </form>
                        <hr>
                        <div>
                            <button type="submit" name="submit" id="btnNext"
                                    onclick="nextStep()" data-tooltip="Tiếp theo >> Chỉnh sửa nội dung ghép"
                                    class="btn btn-lg btn-info btn-block">
                                <i class="fa fa-step-forward fa-lg"></i>&nbsp;
                                <span>Tiếp theo</span>
                            </button>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog"></div>


<script>
    document.title = "Tạo biên bản";
    let meetingId = 0;
    let recordId = 0;
    let sessionId = 1;
    let stenoId = 0;
    let templateId = 0;
    let btnNextStep2 = false;
    $(document).ready(function () {
        //$('#myModal').modal({backdrop: 'static', keyboard: false});
        // $('#btnNext').prop('disabled', true);
        initSelect();
        meeting();
        session();
        record();
        steno();
        template();
    });

    function initSelect() {
        $('#metting').val('0');
        $('#session').prop('disabled', true);
        $('#fileAudio').prop('disabled', true);
        $('#fileSteno').prop('disabled', true);
        templateId = $('#template').val();
        $('#fileAudioReject').hide();
        $('#fileStenoReject').hide();
    }

    function meeting() {
        $('#metting').on('change', function (e) {
            let optionSelected = $("option:selected", this);
            let valueSelected = optionSelected.val();
            let url = "/ajax-create-report.html?step=1";
            if(valueSelected !== 0){
                $("#overlay").css({"display": "block"});
                $.ajax({
                    url: url,
                    type: 'get',
                    data: {
                        meetingId: valueSelected
                    },
                    success: function (res) {
                        meetingId = valueSelected;
                        $("#overlay").css({"display": "none"});
                        let listSession = jQuery.parseJSON(res);
                        if(listSession.length !== 0){
                            $('#session').prop('disabled', false);
                            $('#fileAudio').prop('disabled', true);
                            $('#fileSteno').prop('disabled', true);
                            $('#session').empty().append(new Option("Chọn phiên...", 0));
                            $.each(listSession, function (i, item) {
                                $("#session").append(new Option(item.name, item.sessionId));
                            });
                        }else {
                            $('#session').prop('disabled', true);
                            $('#fileAudio').prop('disabled', true);
                            $('#fileSteno').prop('disabled', true);
                        }
                    },
                    error: function (res) {
                        console.log(res);
                    }
                });
            }
        })
    }

    function session() {
        $('#session').on('change', function (e) {
            let optionSelected = $("option:selected", this);
            let valueSelected = optionSelected.val();
            let url = "/ajax-create-report.html?step=1";
            if(valueSelected !== 0){
                sessionId = valueSelected;
                $("#overlay").css({"display": "block"});
                $.ajax({
                    url: url,
                    type: 'get',
                    data: {
                        meetingId: meetingId,
                        sessionId: valueSelected
                    },
                    success: function (res) {
                        $("#overlay").css({"display": "none"});
                        let listRecordAndSteno = jQuery.parseJSON(res);
                        //create option audio
                        let listRecord = listRecordAndSteno[0];
                        if(listRecord.length !== 0){
                            $('#fileAudio').prop('disabled', false);
                            $('#fileAudio').empty().append(new Option("Chọn file...", 0));
                            $.each(listRecord, function (i, item) {
                                $("#fileAudio").append(new Option(item.name, item.recordId));
                            });
                        }else {
                            $('#fileAudio').prop('disabled', true);
                        }
                        //create option
                        let listSteno = listRecordAndSteno[1];
                        if(listSteno.length !== 0){
                            $('#fileSteno').prop('disabled', false);
                            $('#fileSteno').empty().append(new Option("Chọn file...", 0));
                            $.each(listSteno, function (i, item) {
                                $("#fileSteno").append(new Option(item.name, item.stenoId));
                            });
                        }else {
                            $('#fileSteno').prop('disabled', true);
                        }

                    },
                    error: function (res) {
                        console.log(res);
                    }
                });
            }
        })
    }

    function record() {
        $('#fileAudio').on('change', function (e) {
            let optionSelected = $("option:selected", this);
            let valueSelected = optionSelected.val();
            recordId = valueSelected;
            $.ajax({
                url: '/ajax-permission.html',
                type: 'get',
                data: {
                    type: 'checkFile',
                    typeFile: 20,
                    fileId: recordId
                },
                success: function (res) {
                    console.log("Audio Permission: "+res);
                    if(res == 'Reject'){
                        $("#btn-request-audio").attr("fileId", recordId);
                        $("#fileAudio").removeClass("is-invalid").addClass("is-invalid");
                        $('#fileAudioReject').show();
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            });

            btnNextStep2 = true;
            // $('#btnNext').prop('disabled', false);
        })
    }

    function steno() {
        $('#fileSteno').on('change', function (e) {
            let optionSelected = $("option:selected", this);
            let valueSelected = optionSelected.val();
            stenoId = valueSelected;

            $.ajax({
                url: '/ajax-permission.html',
                type: 'get',
                data: {
                    type: 'checkFile',
                    typeFile: 30,
                    fileId: stenoId
                },
                success: function (res) {
                    console.log("Steno Permission: "+res);
                    if(res == 'Reject'){
                        $("#btn-request-steno").attr("fileId", stenoId);
                        $("#fileSteno").removeClass("is-invalid").addClass("is-invalid");
                        $('#fileStenoReject').show();
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            });

            btnNextStep2 = true;
            // $('#btnNext').prop('disabled', false);
        })
    }

    function template() {
        $('#template').on('change', function (e) {
            let optionSelected = $("option:selected", this);
            let valueSelected = optionSelected.val();
            templateId = valueSelected
        })
    }

    function nextStep() {
        let url = "";
        if(btnNextStep2){
            url = "/report-create.html?step=2&sessionId="+sessionId+"&recordId="+recordId + "&stenoId=" + stenoId + "&templateId=" + templateId;
        }else {
            url = "/report-create.html?step=3&sessionId="+sessionId+"&recordId="+recordId + "&stenoId=" + stenoId + "&templateId=" + templateId;
        }

        $(location).attr('href', url);
    }

    function requestPermission(typeFile, btn){
        $(btn).hide();
        let fileId = $(btn).attr("fileId");
        swal({
                title: "Yêu cầu quyền ?",
                text: "Bạn muốn truy cập file này !!",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Yêu cầu",
                cancelButtonText: "Hủy",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function (isConfirm) {
                if (isConfirm) {
                    $.ajax({
                        type: 'get',
                        url: '/ajax-permission.html',
                        data: {
                            type: 'requestFilePermission',
                            typeFile: typeFile,
                            fileId: fileId
                        },
                        success: function (res) {
                            if(res == 'Success'){
                                swal({
                                    title: "Thành công !",
                                    text: "Đã yêu cầu cấp quyền",
                                    type: "success"
                                }, function () {
                                    swal.close();
                                })
                            }else {
                                swal({
                                    title: "Lỗi !",
                                    text: "Xảy ra lỗi",
                                    type: "success"
                                }, function () {
                                    $(btn).show();
                                    swal.close();
                                })
                            }
                        },
                        error: function (res) {
                            console.log(res);
                            swal("Lỗi !!", "Không gửi được yêu cầu", "success");
                        }
                    });
                } else {
                    $(btn).show();
                    swal("Đã hủy !!", "Hủy yêu cầu cấp quyền", "error");
                }
            });
    }


</script>

</body>
</html>
