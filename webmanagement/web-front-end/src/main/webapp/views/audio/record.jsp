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
    <title>Ghi âm cuộc họp</title>
</head>
<body>
<style>
    #controls {

    }

    li {
        list-style: none;
        margin-bottom: 1rem;
    }

    audio {
        display: block;
        width: 100%;
        margin-top: 0.2rem;
    }

    .clock-timer {
        margin: auto;
        width: 350px;
        height: 350px;
        border-radius: 50%;
        border: 2px solid red;
        font-size: 2.5em;
    }

    .stopwatch {
        margin-top: 45%;
    }

</style>

<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item">Âm thanh</li>
            <li class="breadcrumb-item active">Ghi âm</li>
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
                        <h3 class="text-center">Ghi âm cuộc họp</h3>
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
                        <div class="row">
                            <div class="col-md-12 col-lg-6">
                                <div class="row m-t-10">
                                    <div class="col-lg-8 m-b-10">
                                        <label class="vertical-center">Liên kết cuộc họp </label>
                                    </div>
                                    <div class="col-lg-4 m-b-10">
                                        <label class="switch control-label vertical-center" for="linkMeeting">
                                            <input type="checkbox" name="linkMeeting" id="linkMeeting" onchange="onChangeLinkMeeting()">
                                            <span class="slider round"></span>
                                        </label>
                                    </div>
                                </div>
                                <hr>
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
                                <hr>
                                <div class="text-center">
                                    <div class="clock-timer mb-3">
                                        <div class="stopwatch">00:00:00</div>
                                    </div>
                                    <div id="controls" class="mb-2">
                                        <button type="button" class="btn btn-danger" id="recordButton">
                                            <i class="fa fa-microphone"></i>
                                            <span>Ghi âm</span>
                                        </button>
                                        <button type="button" class="btn btn-warning" id="pauseButton" disabled>
                                            <i class="fa fa-pause"></i>
                                            <span>Tạm dừng</span>
                                        </button>
                                        <button type="button" class="btn btn-danger" id="stopButton" disabled>
                                            <i class="fa fa-stop"></i>
                                            <span>Dừng ghi</span>
                                        </button>
                                    </div>
                                    <div id="formats">Format: Nhấn ghi để xem tần số lấy mẫu</div>
                                </div>

                                <h4>Danh sách file ghi</h4>
                                <hr>
                                <ol id="recordingsList"></ol>
                            </div>
                            <div class="col-md-12 col-lg-6">
                                <div class="row m-t-10">
                                    <div class="col-lg-8 m-b-10">
                                        <label class="vertical-center">Sử dụng cấu hình mặc định </label>
                                    </div>
                                    <div class="col-lg-4 m-b-10">
                                        <label class="switch control-label vertical-center" for="configDefault">
                                            <input type="checkbox" name="configDefault" id="configDefault" checked onchange="onChangeConfigDefault()">
                                            <span class="slider round"></span>
                                        </label>
                                    </div>
                                </div>
                                <hr>
                                <form action="" method="POST" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label for="direction" class=" control-label">Hướng thu âm ưu tiên</label>

                                        <select name="direction" id="direction" class="form-control config">
                                            <option value="0">Đa hướng</option>
                                            <option value="1">Hướng tập trung chủ tọa</option>
                                            <option value="2">Hướng ưu tiên số 1</option>
                                            <option value="3">Hướng ưu tiên số 2</option>
                                            <option value="4">Hướng ưu tiên số 3</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="numberOfMic" class=" control-label">Số lượng micro</label>
                                        <input type="number" id="numberOfMic" name="numberOfMic" class="form-control config">
                                    </div>
                                    <div class="form-group">
                                        <label for="samplingFrequency" class=" control-label">Tần số lấy mẫu</label>
                                        <input type="number" id="samplingFrequency" name="samplingFrequency" class="form-control config">
                                    </div>
                                    <div class="form-group">
                                        <label for="channel" class=" control-label">Kênh âm thanh</label>
                                        <input type="number" id="channel" name="channel" class="form-control config">
                                    </div>
                                    <div class="form-group">
                                        <label for="bitrate" class=" control-label">Số bit mã hóa</label>
                                        <input type="number" id="bitrate" name="bitrate" class="form-control config">
                                    </div>
                                    <div class="form-group">
                                        <label for="maxTimeRecord" class=" control-label">Thời lượng ghi âm tối đa</label>
                                        <input type="number" id="maxTimeRecord" name="maxTimeRecord" class="form-control config">
                                    </div>
                                    <div class="form-group">
                                        <label for="maxSizeRecord" class=" control-label">Kích thước file tối đa</label>
                                        <input type="number" id="maxSizeRecord" name="maxSizeRecord" class="form-control config">
                                    </div>
                                    <div class="form-group">
                                        <label for="pathRecord" class=" control-label">Vị trí lưu file ghi âm</label>
                                        <input type="number" id="pathRecord" name="pathRecord" class="form-control config">
                                    </div>
                                    <div class="form-group">
                                        <label for="fileNameRecordStructure" class=" control-label">Cấu trúc tên file ghi âm</label>
                                        <select name="fileNameRecordStructure" id="fileNameRecordStructure" class="form-control config">
                                            <option value="1">ID cuộc họp + ID phiên họp + ID file ghi</option>
                                            <option value="2">ID cuộc họp + Tên phiên họp + Tên file ghi</option>
                                            <option value="3">ID phiên họp + Tên file ghi</option>
                                            <option value="4">ID phiên họp + ID file ghi</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="maxDurationStorageRecord" class=" control-label">Thời gian lưu trữ ghi âm tối đa</label>
                                        <select name="maxDurationStorageRecord" id="maxDurationStorageRecord" class="form-control config">
                                            <option value="0">Không giới hạn</option>
                                            <option value="1">6 tháng</option>
                                            <option value="2">1 năm</option>
                                            <option value="3">2 năm</option>
                                            <option value="4">3 năm</option>
                                        </select>
                                    </div>
                                </form>
                            </div>
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
    document.title = "Ghi âm";
    var meetingId = 0;
    var recordId = 0;
    var recordObject;
    $(document).ready(function () {
        //$('#myModal').modal({backdrop: 'static', keyboard: false});
        //loadAudio();
        $('#session').prop('disabled', true);
        meeting();
        session();
        onChangeLinkMeeting();
        onChangeConfigDefault();
    });

    function onChangeLinkMeeting() {
        if($('#linkMeeting').is(":checked")){
            $('#metting').prop('disabled', false);
            //$('#linkMeeting').prop("checked", true);
        }else {
            console.log("Da bo check");
            $('#metting').prop('disabled', true);
            //$('#linkMeeting').prop("checked", true);
        }
    }

    function onChangeConfigDefault() {
        if($('#configDefault').is(":checked")){
            $('.config').prop('disabled', true);
        }else {
            $('.config').prop('disabled', false);
        }
    }

    function meeting() {
        $('#metting').on('change', function (e) {
            var optionSelected = $("option:selected", this);
            var valueSelected = optionSelected.val();
            var url = "/ajax-transcript.html?step=1";
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
                        var listSession = jQuery.parseJSON(res);
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
            var optionSelected = $("option:selected", this);
            var valueSelected = optionSelected.val();
            var url = "/ajax-transcript.html?step=1";
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
                        var listRecord = jQuery.parseJSON(res);
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

</script>
<!-- timmer.js -->
<script src="<c:url value='/template/js/easytimer/easytimer.min.js'/>"></script>
<!-- wavesurfer.js -->
<script src="<c:url value='/template/js/wavesurfer/wavesurfer.js'/>"></script>

<script src="<c:url value='/template/js/recorder.js'/>"></script>
<script>
    var recordUploadURL = '${recordASampleUrl}';
    var filenameUpload = '${nameFile}'+'.wav';
    var timer = new easytimer.Timer();
    //webkitURL is deprecated but nevertheless
    URL = window.URL || window.webkitURL;

    var gumStream; 						//stream from getUserMedia()
    var rec; 							//Recorder.js object
    var input; 							//MediaStreamAudioSourceNode we'll be recording

    // shim for AudioContext when it's not avb.
    var AudioContext = window.AudioContext || window.webkitAudioContext;
    var audioContext //audio context to help us audio

    var recordButton = document.getElementById("recordButton");
    var stopButton = document.getElementById("stopButton");
    var pauseButton = document.getElementById("pauseButton");

    //add events to those 2 buttons
    recordButton.addEventListener("click", startRecording);
    stopButton.addEventListener("click", stopRecording);
    pauseButton.addEventListener("click", pauseRecording);

    function startRecording() {
        console.log("recordButton clicked");

        /*
            Simple constraints object, for more advanced audio features see
            https://addpipe.com/blog/audio-constraints-getusermedia/
        */

        var constraints = {audio: true, video: false}

        /*
           Disable the audio button until we get a success or fail from getUserMedia()
       */

        recordButton.disabled = true;
        stopButton.disabled = false;
        pauseButton.disabled = false

        /*
            We're using the standard promise based getUserMedia()
            https://developer.mozilla.org/en-US/docs/Web/API/MediaDevices/getUserMedia
        */

        navigator.mediaDevices.getUserMedia(constraints).then(function (stream) {
            console.log("getUserMedia() success, stream created, initializing Recorder.js ...");

            /*
                create an audio context after getUserMedia is called
                sampleRate might change after getUserMedia is called, like it does on macOS when recording through AirPods
                the sampleRate defaults to the one set in your OS for your playback device

            */
            audioContext = new AudioContext();

            //update the format
            document.getElementById("formats").innerHTML = "Format: 1 channel pcm @ " + audioContext.sampleRate / 1000 + "kHz"

            /*  assign to gumStream for later use  */
            gumStream = stream;

            /* use the stream */
            input = audioContext.createMediaStreamSource(stream);

            /*
                Create the Recorder object and configure to audio mono sound (1 channel)
                Recording 2 channels  will double the file size
            */
            rec = new Recorder(input, {numChannels: 1})

            //start the recording process
            rec.record()

            console.log("Recording started");
            timer.start();
        }).catch(function (err) {
            //enable the audio button if getUserMedia() fails
            recordButton.disabled = false;
            stopButton.disabled = true;
            pauseButton.disabled = true
        });
    }

    function pauseRecording() {
        console.log("pauseButton clicked rec.recording=", rec.recording);
        if (rec.recording) {
            //pause
            rec.stop();
            pauseButton.innerHTML = "Tiếp tục";
            timer.pause();
        } else {
            //resume
            rec.record()
            pauseButton.innerHTML = "Tạm dừng";
            timer.start();
        }
    }

    function stopRecording() {
        console.log("stopButton clicked");

        //disable the stop button, enable the audio too allow for new recordings
        stopButton.disabled = true;
        recordButton.disabled = false;
        pauseButton.disabled = true;

        //reset button just in case the recording is stopped while paused
        pauseButton.innerHTML = "Tạm dừng";

        //tell the recorder to stop the recording
        rec.stop();
        timer.stop();
        //stop microphone access
        gumStream.getAudioTracks()[0].stop();

        //create the wav blob and pass it on to createDownloadLink
        rec.exportWAV(createDownloadLink);
    }

    function createDownloadLink(blob) {

        var url = URL.createObjectURL(blob);
        var au = document.createElement('audio');
        var li = document.createElement('li');
        var link = document.createElement('a');

        //name of .wav file to use during upload and download (without extendion)
        var filename = new Date().toISOString();

        //add controls to the <audio> element
        au.controls = true;
        au.src = url;

        //save to disk link
        link.href = url;
        link.download = filename + ".wav"; //download forces the browser to donwload the file using the  filename
        link.innerHTML = "Lưu về máy";

        //add the new audio element to li
        li.appendChild(au);

        //add the filename to the li
        li.appendChild(document.createTextNode(filename + ".wav "))

        //add the save to disk link to li
        li.appendChild(link);

        //upload link
        var upload = document.createElement('a');
        upload.href = "#";
        upload.innerHTML = "Upload";
        upload.addEventListener("click", function (event) {
            var xhr = new XMLHttpRequest();
            xhr.onload = function (e) {
                if (this.readyState === 4) {
                    console.log("Server returned: ", e.target.responseText);
                }
            };
            var fd = new FormData();
            fd.append("audio_data", blob, filenameUpload);
            xhr.open("POST", recordUploadURL, true);
            xhr.send(fd);
        })
        li.appendChild(document.createTextNode(" "))//add a space in between
        li.appendChild(upload)//add the upload link to li

        //add the li element to the ol
        recordingsList.appendChild(li);
    }

    //cho nay danh cho cai dong ho
    timer.addEventListener('secondsUpdated', function (e) {
        $('.stopwatch').html(timer.getTimeValues().toString());
    });
    timer.addEventListener('started', function (e) {
        $('.stopwatch').html(timer.getTimeValues().toString());
    });
    timer.addEventListener('reset', function (e) {
        $('.stopwatch').html(timer.getTimeValues().toString());
    });
</script>

</body>
</html>
