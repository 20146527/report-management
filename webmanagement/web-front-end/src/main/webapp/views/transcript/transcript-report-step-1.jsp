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
                        <h3 class="text-center">Bước 1/3: Chọn file ghi âm cuộc họp</h3>
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
                                <label for="file" class=" control-label">Chọn file ghi âm</label>

                                <select name="file" id="file" class="form-control">
                                    <option value="0">Chọn file...</option>

                                </select>
                            </div>

                            <div id="audio-spectrum"></div>

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
                            <div>
                                <button type="button" name="button" id="btnNext"
                                        onclick="nextStep()"
                                        class="btn btn-lg btn-info btn-block">
                                    <i class="fa fa-step-forward fa-lg"></i>&nbsp;
                                    <span>Tiếp theo</span>
                                </button>
                            </div>
                        </form>

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
    let meetingId = 0;
    let recordId = 0;
    let statusRecord;
    let recordObject;
    $(document).ready(function () {
        //$('#myModal').modal({backdrop: 'static', keyboard: false});
        //loadAudio();
        $('#btnNext').prop('disabled', true);
        initSelect();
        meeting();
        session();
        record();
    });

    function initSelect() {
        $('#metting').val('0');
        $('#session').prop('disabled', true);
        $('#file').prop('disabled', true);
    }

    function meeting() {
        $('#metting').on('change', function (e) {
            let optionSelected = $("option:selected", this);
            let valueSelected = optionSelected.val();
            let url = "/ajax-transcript.html?step=1";
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
                            $('#file').prop('disabled', true);
                            $('#session').empty().append(new Option("Chọn phiên...", 0))
                            $.each(listSession, function (i, item) {
                                $("#session").append(new Option(item.name, item.sessionId));
                            });
                        }else {
                            $('#session').prop('disabled', true);
                            $('#file').prop('disabled', true);
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
            let url = "/ajax-transcript.html?step=1";
            if(valueSelected !== 0){
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
                        let listRecord = jQuery.parseJSON(res);
                        recordObject = listRecord;
                        if(listRecord.length !== 0){
                            $('#file').prop('disabled', false);
                            $('#file').empty().append(new Option("Chọn file...", 0))
                            $.each(listRecord, function (i, item) {
                                $("#file").append(new Option(item.name, item.path));
                            });
                        }else {
                            $('#file').prop('disabled', true);
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
        $('#file').on('change', function (e) {
            $("#overlay").css({"display": "block"});
            let optionSelected = $("option:selected", this);
            let valueSelected = optionSelected.val();
            $.each(recordObject, function (i, item) {
               if(item.path === valueSelected){
                   recordId = item.recordId
                   statusRecord = item.status;
               }
            });
            $('#audio-spectrum').empty();
            loadAudio(valueSelected);
            $("#overlay").css({"display": "none"});
            $('#btnNext').prop('disabled', false);
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
        Spectrum.load('<c:url value="/file/"/>'+pathAudio);
    }

    function nextStep() {
        let url = "/report-transcript.html?step=2&recordId="+recordId;
        if(statusRecord == 5){
            url = "/report-transcript.html?step=3&recordId="+recordId;
        }
        $(location).attr('href', url);
    }
</script>
<!-- wavesurfer.js -->
<script src="<c:url value='/template/js/wavesurfer/wavesurfer.js'/>"></script>

<script>

</script>

</body>
</html>
