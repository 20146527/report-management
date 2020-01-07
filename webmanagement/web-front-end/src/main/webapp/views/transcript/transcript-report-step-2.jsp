<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
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
<link href="<c:url value='/template/css/wavesurfer/elan.css'/>" rel="stylesheet"/>
<link href="<c:url value='/template/css/wavesurfer/ribbon.css'/>" rel="stylesheet"/>
<link href="<c:url value='/template/css/wavesurfer/style.css'/>" rel="stylesheet"/>
<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item">Âm thanh</li>
            <li class="breadcrumb-item active">Xử lý âm thanh</li>
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
                        <h3 class="text-center">Bước 2/3: Bóc băng</h3>
                        <hr>
                        <%--alert--%>
                        <c:if test="${not empty messageResponse}">
                            <div class="alert alert-${alert} alert-dismissible fade show">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <strong>${messageResponse}</strong>
                            </div>
                        </c:if>
                        <%--alert--%>
                        <div class="form-group">
                            <p class="mb-1"><span class="badge badge-secondary">Phiên</span> ${record.sessionDto.name}
                            </p>
                            <p class="mb-1"><span class="badge badge-secondary">Dung lượng</span> ${record.length} MB
                            </p>
                            <p class="mb-1"><span class="badge badge-secondary">Tên file</span> ${record.name}</p>
                            <div id="audio-spectrum"></div>
                        </div>
                        <!-- Create action buttons -->
                        <button type="button" id="btn-play" class="btn btn-outline-info">
                            <i class="fa fa-play fa-lg"></i>&nbsp;
                            <span>Play</span>
                        </button>
                        <button type="button" id="btn-pause" class="btn btn-outline-info">
                            <i class="fa fa-pause fa-lg"></i>&nbsp;
                            <span>Pause</span>
                        </button>
                        <button type="button" id="btn-stop" class="btn btn-outline-info">
                            <i class="fa fa-stop fa-lg"></i>&nbsp;
                            <span>Stop</span>
                        </button>
                        <hr>

                        <div class="row">
                            <div class="col-5">
                                <h4>Tùy chọn xử lý</h4>
                                <hr>
                                <ul class="nav nav-pills m-t-30 m-b-30">
                                    <li class=" nav-item"><a href="#navpills-1" class="nav-link active"
                                                             data-toggle="tab" aria-expanded="false">Cơ bản</a></li>
                                    <li class="nav-item"><a href="#navpills-2" class="nav-link" data-toggle="tab"
                                                            aria-expanded="false">Nâng cao</a></li>
                                </ul>
                                <div class="tab-content br-n pn">
                                    <div id="navpills-1" class="tab-pane active">
                                        <div class="row">
                                            <div class="col-lg-8 text-center">
                                                <h5>Lọc nhiễu âm (noise) </h5>
                                            </div>
                                            <div class="col-lg-4">
                                                <label class="switch">
                                                    <input type="checkbox" checked>
                                                    <span class="slider round"></span>
                                                </label>
                                            </div>
                                            <hr>
                                            <div class="col-lg-8 text-center">
                                                <h5>Lọc tiếng vang (echo) </h5>
                                            </div>
                                            <div class="col-lg-4">
                                                <label class="switch">
                                                    <input type="checkbox" checked>
                                                    <span class="slider round"></span>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="navpills-2" class="tab-pane">
                                        <h2>Tùy chọn nâng cao ở đây</h2>
                                        <h3>comming soon!!!</h3>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-4 col-md-1">
                                    </div>
                                    <div class="col-lg-4 col-md-10">
                                        <button type="button" name="buttom"
                                                onclick="runProcessAudio()"
                                                class="btn btn-primary btn-block">
                                            <i class="fa fa-caret-square-o-right"></i>&nbsp;
                                            <span>Xử lý</span>
                                        </button>
                                    </div>
                                    <div class="col-lg-4 col-md-1">
                                    </div>
                                </div>
                            </div>
                            <div class="col-7">
                                <h4>Tình trạng xử lý</h4>
                                <hr>
                                <h5 class="m-t-30">Đang xử lý<span class="pull-right badge badge-info">0%</span></h5>
                                <div class="progress">
                                    <div class="progress-bar bg-primary wow animated progress-animated"
                                         style="height:14px" role="progressbar"><span
                                            class="sr-only">60% Complete</span></div>
                                </div>
                                <div>
                                    <p>Chi tiết xử lý</p>
                                    <ol id="descriptionProcess" style="max-height: 150px; overflow: auto">

                                    </ol>
                                </div>

                            </div>
                        </div>

                        <hr>
                        <div>
                            <button type="button" name="submit" id="btnNext"
                                    onclick="nextStep()"
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
    document.title = "Xử lý âm thanh";
    $(document).ready(function () {
        //$('#myModal').modal({backdrop: 'static', keyboard: false});
        $('#btnNext').prop('disabled', true);
        var pathAudio = '${record.path}';
        console.log("PATH AUDIO: "+ pathAudio);
        loadAudio(pathAudio);
        let status = '${record.status}';
        if(status === '4'){
            let data = jQuery.parseJSON('${record.processingInfo}');
            getProcessStatus(data);
        }
    });

    function demoProgress() {
        var myProgress = setInterval(changeProgress, 100);
        var progress = 0;

        function changeProgress() {
            if (progress === 100) {
                clearInterval(myProgress);
                $('#btnNext').prop('disabled', false);
                $('.pull-right').removeClass('badge-info').addClass('badge-success');
                $('.progress-bar').removeClass('bg-primary').addClass('bg-success');
            } else {
                progress++;
                $('.progress-bar').attr("style", "width: " + progress + "%; height:14px");
                $('.pull-right').text(progress + "%")
            }

        }
    }

    function runProcessAudio() {
        $('#descriptionProcess').append("<li>Đang upload file âm thanh...</li>");
        var url = "/ajax-transcript.html?step=2";
        $.ajax({
            url: url,
            type: 'post',
            data: {
                noiseCancelling: true,
                echoCancelling: true,
                recordId: '${record.recordId}',
                pathAudio: '${record.path}'
            },
            success: function (res) {
                var data = jQuery.parseJSON(res);
                getProcessStatus(data);
                //console.log(res);

            },
            error: function (res) {
                console.log(res);
            }
        })
    }

    function getProcessStatus(status) {
        console.log("Du lieu nhan vao status: "+ status);
        var myProgress = setInterval(getStatus, 5000);
        var progress = 0;
        function getStatus() {
            var fullURL = status.url+"?id="+status.meetingId;
            $.ajax({
                headers:{
                    'Token': status.accessToken
                },
                url: fullURL,
                type: 'get',
                success: function (res) {
                    console.log("SUCCESS STATUS: "+ JSON.stringify(res));
                    var data = res;
                    if(data.progress === 100){
                        clearInterval(myProgress);
                        $('.pull-right').removeClass('badge-info').addClass('badge-success');
                        $('.progress-bar').removeClass('bg-primary').addClass('bg-success');
                        getTranscription(status);
                    }else {
                        $('#descriptionProcess').append("<li>"+data.status_message+"</li>");
                        $('.progress-bar').attr("style", "width: " + data.progress + "%; height:14px");
                        $('.pull-right').text(data.progress + "%")
                    }
                },
                error: function (res) {
                    console.log("ERROR STATUS: " + JSON.stringify(res));
                }
            })
        }

    }

    function getTranscription(status) {
        var url = "/ajax-transcript.html?step=2";
        var data = JSON.stringify(status);
        console.log("INPUT trancription: "+ data);
        $.ajax({
            url: url,
            type: 'post',
            data: {
                complete: true,
                recordId: '${record.recordId}',
                pathAudio: '${record.path}',
                data: data
            },
            success: function (res) {
                console.log("SUCCESS TRANS: "+res);
                $('#btnNext').prop('disabled', false);
                $('#descriptionProcess').append("<li>Ghi file xml thành công</li>");
                $('.progress-bar').attr("style", "width: 100%; height:14px");
                $('.pull-right').text("100%")
            },
            error: function (res) {
                console.log("ERROR TRANS: "+JSON.stringify(res));
            }
        })
    }

    function loadAudio(pathAudio) {
        // Store the 3 buttons in some object
        var buttons = {
            play: document.getElementById("btn-play"),
            pause: document.getElementById("btn-pause"),
            stop: document.getElementById("btn-stop")
        };

        // Create an instance of wave surfer with its configuration
        var Spectrum = WaveSurfer.create({
            container: '#audio-spectrum',
            progressColor: "#03a9f4"
        });

        // Handle Play button
        buttons.play.addEventListener("click", function () {
            Spectrum.play();

            // Enable/Disable respectively buttons
            buttons.stop.disabled = false;
            buttons.pause.disabled = false;
            buttons.play.disabled = true;
        }, false);

        // Handle Pause button
        buttons.pause.addEventListener("click", function () {
            Spectrum.pause();

            // Enable/Disable respectively buttons
            buttons.pause.disabled = true;
            buttons.play.disabled = false;
        }, false);


        // Handle Stop button
        buttons.stop.addEventListener("click", function () {
            Spectrum.stop();

            // Enable/Disable respectively buttons
            buttons.pause.disabled = true;
            buttons.play.disabled = false;
            buttons.stop.disabled = true;
        }, false);


        // Add a listener to enable the play button once it's ready
        Spectrum.on('ready', function () {
            buttons.play.disabled = false;
        });

        // If you want a responsive mode (so when the user resizes the window)
        // the spectrum will be still playable
        window.addEventListener("resize", function () {
            // Get the current progress according to the cursor position
            var currentProgress = Spectrum.getCurrentTime() / Spectrum.getDuration();

            // Reset graph
            Spectrum.empty();
            Spectrum.drawBuffer();
            // Set original position
            Spectrum.seekTo(currentProgress);

            // Enable/Disable respectively buttons
            buttons.pause.disabled = true;
            buttons.play.disabled = false;
            buttons.stop.disabled = false;
        }, false);

        // Load the audio file from your domain !

        Spectrum.load('<c:url value="/file/"/>' + pathAudio);
    }

    function nextStep() {
        var recordId = '${record.recordId}';
        var url = "/report-transcript.html?step=3&recordId="+recordId;
        $(location).attr('href', url);
    }

</script>
<script src="<c:url value='/template/js/wavesurfer/wavesurfer.js'/>"></script>
</body>
</html>
