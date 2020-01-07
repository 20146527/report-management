<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="editASampleUrl" value="/ajax-audio-sample.html">
    <c:param name="urlType" value="url_upload"/>
</c:url>
<c:url var="recordASampleUrl" value="/ajax-audio-sample.html">
    <c:param name="urlType" value="url_as_record"/>
</c:url>
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
        width: 250px;
        height: 250px;
        border-radius: 50%;
        border: 2px solid red;
        font-size: 2.5em;
    }

    .stopwatch {
        margin-top: 45%;
    }

</style>
<link href="<c:url value='/template/css/lib/jquery-filer/jquery.filer.css'/>" rel="stylesheet">
<link href="<c:url value='/template/css/lib/jquery-filer/themes/jquery.filer-dragdropbox-theme.css'/>" rel="stylesheet">
<script src="<c:url value='/template/js/lib/jquery-filer/jquery.filer.min.js'/>"></script>
<c:choose>
    <c:when test="${not empty messageResponse}">
        ${messageResponse}
    </c:when>
    <c:otherwise>
        <!-- Modal -->
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">File âm thanh mẫu</h5>
                </div>
                <div class="modal-body">
                    <ul class="nav nav-pills m-t-30 m-b-30">
                        <li class=" nav-item"><a href="#navpills-1" class="nav-link active" data-toggle="tab"
                                                 aria-expanded="false">Ghi âm</a></li>
                        <li class="nav-item"><a href="#navpills-2" class="nav-link" data-toggle="tab"
                                                aria-expanded="false">Tải lên</a></li>
                        <li class="nav-item"><a href="#navpills-3" class="nav-link" data-toggle="tab"
                                                aria-expanded="true">Danh sách</a></li>
                    </ul>
                    <div class="tab-content br-n pn">
                        <div id="navpills-1" class="tab-pane active">
                            <div class="row">
                                <div class="col-lg-4 col-md-12">
                                    <h4>Điều khiển ghi âm</h4>
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
                                </div>
                                <div class="col-lg-8 col-md-12">
                                    <h4>Danh sách file ghi</h4>
                                    <hr>
                                    <ol id="recordingsList"></ol>
                                </div>
                            </div>


                        </div>
                        <div id="navpills-2" class="tab-pane">
                            <form action="${editASampleUrl}" method="post" enctype="multipart/form-data" id="uploadASampleForm">
                                <div class="form-group">
                                    <label for="fullName" class="control-label mb-1">Tên file</label>
                                    <input type="text" class="form-control" id="fullName" name="pojo.name"
                                           value="${nameFile}" placeholder="Tên file" disabled="disabled">
                                </div>
                                <div class="form-group">
                                    <label for="divInput" class=" control-label">Tải lên file audio (mp3, wav)</label>
                                    <div id="divInput">
                                        <input type="file" id="filer_input" name="file" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="otherName" class="control-label mb-1">Mốc thời gian (giây)</label>
                                    <input type="text" class="form-control" id="otherName" name="pojo.timeIndex"
                                           value="" placeholder="VD: 1-2, 4-6, ...">
                                </div>
                                <input type="hidden" name="speakerId" value="${speakerId}">
                                <input type="hidden" name="nameFile" value="${nameFile}">
                                <button type="submit" id="btnUpload" name="button" class="btn btn-block btn-info">
                                    <i class="fa fa-upload"></i>
                                    <span>Tải lên</span>&nbsp;
                                </button>
                            </form>
                        </div>
                        <div id="navpills-3" class="tab-pane">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Tên</th>
                                        <th>Kích thước</th>
                                        <th>Ngày tải</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${items}" varStatus="loop" var="row">
                                        <tr>
                                            <td>${row.getAudioSampleId()}</td>
                                            <td>${row.getName()}</td>
                                            <td><span class="badge badge-primary">${row.getSize()} MB</span></td>
                                            <td>${row.getCreDate()}</td>
                                            <td>
<%--                                                <audio controls src="file:///E:/upload/nghia.wav"/>--%>
                                                <button type="button" name="submit" class="btn btn-success"
                                                        data-toggle="tooltip" title="Phát">
                                                    <i class="fa fa-play"></i>&nbsp;
                                                </button>
                                                <button type="button" name="submit" class="btn btn-danger"
                                                        data-toggle="tooltip" title="Xóa">
                                                    <i class="fa fa-times"></i>&nbsp;
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>

    </c:otherwise>
</c:choose>
<script src="<c:url value='/template/js/input-file/inputFileAudioRecord.js'/>"></script>
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
        link.download = filenameUpload; //download forces the browser to donwload the file using the  filename
        link.innerHTML = "Lưu về máy";

        //add the new audio element to li
        li.appendChild(au);

        //add the filename to the li
        li.appendChild(document.createTextNode(filenameUpload))

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
            fd.append("speakerId", ${speakerId});
            fd.append("nameFile", ${nameFile});
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