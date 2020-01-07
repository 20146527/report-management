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
<c:url var="editTranscriptUrl" value="/ajax-transcript.html">
    <c:param name="step" value="3"/>
    <c:param name="recordId" value="${recordId}"/>
</c:url>
<html>
<head>
    <title>Danh sách người dùng</title>
</head>
<body>
<link href="<c:url value='/template/css/wavesurfer/elan.css'/>" rel="stylesheet"/>
<link href="<c:url value='/template/css/wavesurfer/ribbon.css'/>" rel="stylesheet"/>
<link href="<c:url value='/template/css/wavesurfer/style.css'/>" rel="stylesheet"/>
<style>
    .dropdown-menu li:hover{
        background-color: #99abb4;
    }
    tbody tr td {
        color: #000;
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
                        <h3 class="text-center">Bước 3/3: Xem thông tin bóc băng</h3>
                        <hr>
                        <%--alert--%>
                        <c:if test="${not empty messageResponse}">
                            <div class="alert alert-${alert} alert-dismissible fade show">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <strong>${messageResponse}</strong>
                            </div>
                        </c:if>
                        <div id="noti">

                        </div>
                        <%--alert--%>

                        <!-- Waveform + Elan -->
                        <div id="demo">
                            <div id="waveform">
                                <div class="progress progress-striped active" id="progress-bar">
                                    <div class="progress-bar progress-bar-info"></div>
                                </div>

                                <!-- Here be waveform -->
                            </div>
                        </div>
                        <div class="row m-t-20">
                            <div class="col-4">
                                <div class="dropdown">
                                    <button class="btn btn-outline-info dropdown-toggle" type="button" data-toggle="dropdown">Chế độ hiển thị
                                        <span class="caret"></span></button>
                                    <ul class="dropdown-menu">
                                        <li onclick="displayAnnotationTable()" class="li-table">
                                            <i class="fa fa-table fa-lg"></i>&nbsp;
                                            <span>Danh sách đoạn</span>
                                        </li>
                                        <li onclick="displayEditor()" class="li-editor">
                                            <i class="fa fa-pencil-square-o fa-lg"></i>&nbsp;
                                            <span>Trình biên soạn</span>
                                        </li>
                                        <li onclick="displayAllOption()" class="li-all">
                                            <i class="fa fa-angle-double-right fa-lg"></i>&nbsp;
                                            <span>Hiển thị tất cả</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-4 text-center">
                                <button class="btn btn-outline-info" data-action="play">
                                    <i class="fa fa-play fa-lg"></i>&nbsp;
                                    <span>Play</span>
                                    /
                                    <i class="fa fa-pause fa-lg"></i>&nbsp;
                                    <span>Pause</span>
                                </button>

                                <button id="btnSave" class="btn btn-outline-success" onclick="editTranscript()">

                                    <span>Lưu thay đổi</span>
                                </button>
                            </div>
                            <div class="col-4">
                                <button id="btnSearch" onclick="showSearch()" class="btn btn-outline-warning float-right" data-tooltip="Tìm kiếm">
                                    <i class="fa fa-search fa-lg"></i>&nbsp;
                                </button>
                            </div>
                        </div>

                        <div id="card-search" class="card">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-11">
                                        <h4 class="card-title">Tìm kiếm</h4>
                                    </div>
                                    <div class="col-1">
                                        <div class="float-right">
                                            <button onclick="hideSearch()" class="btn btn-outline-danger" data-tooltip="Đóng tìm kiếm">
                                                <i class="fa fa-times fa-lg"></i>&nbsp;
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <h6 class="card-subtitle">Nhập thông tin để tìm kiếm nội dung phát biểu, thời điểm nói</h6>
                                <form>
                                    <div class="row">
                                        <div class="col-7">
                                            <label class="control-label">Tìm kiếm nội dung nói</label>
                                            <input type="text" id="inputSearchContent" class="form-control" onkeyup="searchContent()" placeholder="Nhập vào nội dung nói ...">
                                        </div>
                                        <div class="col-5">
                                            <label class="control-label">Tìm kiếm người nói</label>
                                            <input type="text" id="inputSearchSpeaker" class="form-control" onkeyup="searchSpeaker()" placeholder="Nhập vào tên người nói ...">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <div id="annotations" class="table-responsive m-t-20">
                            <!-- Here be transcript -->
                        </div>
                        <!-- End Waveform + Elan -->
                        <div id="editor" class="m-t-20">
                            <form action="${editTranscriptUrl}" method="post" id="editTranscriptForm">
                                <textarea id="full-featured-90" name="">
                                </textarea>
                                <input type="hidden" name="" value="${transcript.transcriptId}">
                                <button type="submit" class="btn btn-outline-success" onclick="">
                                    <span>Lưu thay đổi</span>
                                </button>
                            </form>
                        </div>

                        <ul class="nav nav-pills m-t-30 m-b-30">
                            <li class=" nav-item"> <a href="#navpills-1" class="nav-link active" data-toggle="tab" aria-expanded="false">Thông tin</a> </li>
                            <li class="nav-item"> <a href="#navpills-3" class="nav-link" data-toggle="tab" aria-expanded="true">Mở rộng</a> </li>
                        </ul>
                        <div class="tab-content br-n pn">
                            <div id="navpills-1" class="tab-pane active">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <h4>Thông tin file</h4>
                                        <hr>
                                        <div class="row">
                                            <div class="col-lg-6">
                                                <h5>Tên file</h5>
                                                <p>01022018083200-Phien1.m4a</p>
                                            </div>
                                            <div class="col-lg-6">
                                                <h5>Thời lượng</h5>
                                                <p>01 giờ 25 phút</p>
                                            </div>
                                            <div class="col-lg-6">
                                                <h5>Dung lượng file</h5>
                                                <p>985 MB</p>
                                            </div>
                                            <div class="col-lg-6">
                                                <h5>Bit rate</h5>
                                                <p>256 kbs</p>
                                            </div>
                                            <div class="col-lg-6">
                                                <h5>Thiết bị tạo</h5>
                                                <p>SONY ICD-SX743</p>
                                            </div>
                                            <div class="col-lg-6">
                                                <h5>Người tạo</h5>
                                                <p>Trần Nguyên Nghĩa</p>
                                            </div>
                                            <div class="col-lg-6">
                                                <h5>Thời gian tạo</h5>
                                                <p>01/02/2018 08:05:22</p>
                                            </div>
                                            <div class="col-lg-6">
                                                <h5>Thời gian sửa đổi</h5>
                                                <p>01/03/2018 20:32:05</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <h4>Phát nâng cao</h4>
                                        <hr>
                                        <div class="form-group">
                                            <label for="speakers" class=" control-label">Phát âm thanh từ</label>
                                            <select name="speaker" id="speakers" class="form-control">
                                                <c:forEach items="${transcripts}" varStatus="loop" var="op">
                                                    <option value="${op.transcriptId}">${op.speaker}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-8">
                                                <h5>Pickup (Chỉ phát âm thanh người được chọn) </h5>
                                            </div>
                                            <div class="col-lg-4">
                                                <label class="switch">
                                                    <input type="checkbox" checked>
                                                    <span class="slider round"></span>
                                                </label>
                                            </div>

                                            <div class="col-lg-8">
                                                <h5>Focus (Làm rõ tiếng người nói) </h5>
                                            </div>
                                            <div class="col-lg-4">
                                                <label class="switch">
                                                    <input type="checkbox" checked>
                                                    <span class="slider round"></span>
                                                </label>
                                            </div>

                                            <div class="col-lg-8">
                                                <h5>Remove All (Loại bỏ tiếng người khác, nhiễu âm) </h5>
                                            </div>
                                            <div class="col-lg-4">
                                                <label class="switch">
                                                    <input type="checkbox">
                                                    <span class="slider round"></span>
                                                </label>
                                            </div>
                                            <div class="col-4">

                                            </div>
                                            <div class="col-4">
                                                <button type="submit" name="submit"
                                                        class="btn btn-primary btn-block">
                                                    <i class="fa fa-play fa-lg"></i>&nbsp;
                                                    <span>Phát</span>
                                                </button>
                                            </div>
                                            <div class="col-4">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="navpills-3" class="tab-pane">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <h4>Xử lý nâng cao</h4>
                                        <hr>
                                        <div class="row">
                                            <div class="col-lg-6 text-center">
                                                <h5>Lọc nhiễu âm (noise) </h5>
                                            </div>
                                            <div class="col-lg-6">
                                                <label class="switch">
                                                    <input type="checkbox">
                                                    <span class="slider round"></span>
                                                </label>
                                            </div>
                                            <hr>
                                            <div class="col-lg-6 text-center">
                                                <h5>Lọc tiếng vang (echo) </h5>
                                            </div>
                                            <div class="col-lg-6">
                                                <label class="switch">
                                                    <input type="checkbox" checked>
                                                    <span class="slider round"></span>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <h4>Phát nâng cao</h4>
                                        <hr>
                                        <div class="form-group">
                                            <label for="speaker" class=" control-label">Phát âm thanh
                                                từ</label>

                                            <select name="speaker" id="speaker" class="form-control">
                                                <option value="0">Tất cả mọi người</option>
                                                <option value="1">Vũ Thị Hương Giang (Chủ tọa)</option>
                                                <option value="2">Trần Nguyên Nghĩa (Thư ký)</option>
                                                <option value="3">Phan Thanh Hùng (Thành viên)</option>
                                                <option value="4">Ngô Đức Minh (Thành viên)</option>
                                            </select>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-8">
                                                <h5>Pickup (Chỉ phát âm thanh người được chọn) </h5>
                                            </div>
                                            <div class="col-lg-4">
                                                <label class="switch">
                                                    <input type="checkbox" checked>
                                                    <span class="slider round"></span>
                                                </label>
                                            </div>

                                            <div class="col-lg-8">
                                                <h5>Focus (Làm rõ tiếng người nói) </h5>
                                            </div>
                                            <div class="col-lg-4">
                                                <label class="switch">
                                                    <input type="checkbox" checked>
                                                    <span class="slider round"></span>
                                                </label>
                                            </div>

                                            <div class="col-lg-8">
                                                <h5>Remove All (Loại bỏ tiếng người khác, nhiễu âm) </h5>
                                            </div>
                                            <div class="col-lg-4">
                                                <label class="switch">
                                                    <input type="checkbox">
                                                    <span class="slider round"></span>
                                                </label>
                                            </div>
                                            <div class="col-4">

                                            </div>
                                            <div class="col-4">
                                                <button type="submit" name="submit"
                                                        class="btn btn-primary btn-block">
                                                    <i class="fa fa-play fa-lg"></i>&nbsp;
                                                    <span>Phát</span>
                                                </button>
                                            </div>
                                            <div class="col-4">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <hr>
                        <div>
                            <button type="submit" name="submit"
                                    onclick="location.href='/report-create.html?step=4'"
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
        $('#btnSave').hide();
        initEditContentAndSpeaker();
        changeSpeaker();
        displayEditor();

        let ctrlDown = false,
            ctrlKey = 17,
            cmdKey = 91,
            sKey = 83;

        $(document).keydown(function (e) {
            if (e.keyCode == ctrlKey || e.keyCode == cmdKey) ctrlDown = true;
        }).keyup(function (e) {
            if (e.keyCode == ctrlKey || e.keyCode == cmdKey) ctrlDown = false;
        });

        $(window).keydown(function (e) {
            if (ctrlDown && (e.keyCode == sKey)){
                // console.log("CTRL S ne!");
                // update();
                event.preventDefault();
            }
        });

    });

    function hideSearch() {
        $('#card-search').hide();
        $('#btnSearch').show();
    }

    function showSearch() {
        $('#card-search').show();
        $('#btnSearch').hide();
    }

    function displayEditor() {
        hideSearch();
        $('#btnSearch').hide();
        $('#annotations').hide();
        $('#editor').show();
        $('.li-table').removeAttr('style');
        $('.li-editor').css({'background-color': '#aec8d1'});
        $('.li-all').removeAttr('style');
    }

    function displayAnnotationTable() {
        hideSearch();
        $('#annotations').show();
        $('#editor').hide();
        $('.li-table').css({'background-color': '#aec8d1'});
        $('.li-editor').removeAttr('style');
        $('.li-all').removeAttr('style');
    }

    function displayAllOption() {
        hideSearch();
        $('#annotations').show();
        $('#editor').show();
        $('.li-table').removeAttr('style');
        $('.li-editor').removeAttr('style');
        $('.li-all').css({'background-color': '#aec8d1'});
    }

    function changeSpeaker() {
        //select value
        $('#speakers').val('${transcript.transcriptId}');
        //on change select
        $('#speakers').on('change', function (e) {
            var optionSelected = $("option:selected", this);
            var valueSelected = optionSelected.val();
            var recordId = '${recordId}';
            var data = '${jsonTranscripts}';
            var speaker;
            var transcriptList = jQuery.parseJSON(data);
            $.each(transcriptList, function (i, item) {
                if(item.transcriptId.toString() === valueSelected){
                    speaker = item.speakerId;
                }
            });
            var url = "/report-transcript.html?step=3&recordId="+recordId+"&speaker="+speaker;
            $(location).attr('href', url);
        })
    }

    function initEditContentAndSpeaker() {
        $(".wavesurfer-annotations").delegate("td.wavesurfer-tier-Text", "dblclick", function(){
            var tdContent = $(this);
            var valueText = $(tdContent).text();
            $(tdContent).empty();
            var tdContentID = $(tdContent).attr('id');
            $(tdContent).append('<div class="row">\n' +
                '               <div class="col-lg-10">\n' +
                '                   <input type="text" class="form-control" id="'+tdContentID+'" value="'+valueText+'">\n' +
                '             </div>\n' +
                '               <div class="col-lg-auto">\n' +
                '                   <button type="button" class="btn btn-outline-info" onclick="editRowContent(this)" id="btn-save-row-content" data-tooltip="Hoàn tất"> <i class="fa fa-check"></i>&nbsp;</button>\n' +
                '               </div>\n' +
                '             </div>');
        });

        $(".wavesurfer-annotations").delegate("td.wavesurfer-tier-Comments", "dblclick", function(){
            var td = $(this);
            var valueText = $(td).text();
            var data = '${attendees}';
            var attendees = jQuery.parseJSON(data);
            $(td).empty();
            var option;
            $.each(attendees, function (i, item) {
                option += '<option value="'+item.speakerDto.fullName+'">'+item.speakerDto.fullName+'</option>'
            });
            $(td).append('<div class="row">\n' +
                '           <div class="col-9">\n' +
                '               <select name="speaker" class="form-control">\n' + option +
                '               </select> \n' +
                '           </div>\n' +
                '           <div class="col-3">\n' +
                '               <button type="button" class="btn btn-outline-info" onclick="editRowContent(this)" data-tooltip="Hoàn tất"> <i class="fa fa-check"></i>&nbsp;</button>\n' +
                '           </div>\n' +
                '         </div>')
        });
    }

    function editRowContent(btn){
        var td = $(btn).parents().eq(2);
        //alert($(td).html());
        var input = $(td).find('.form-control');
        var valueText = $(input).val();
        $(td).empty();
        $(td).append(valueText);
        $('#btnSave').show();
    }


    function getContentFromTable() {
        var data = $(".wavesurfer-annotations tr.row-content").map(function (index, elem) {
            var content = [];
            $('.wavesurfer-tier-Text', this).each(function () {
                var data = $(this).val()||$(this).text();
                content.push(data);
                //console.log(d);
            });
            return content;
        });
        return data;
        //console.log(data);
        //console.log(data[0]);
    }

    function getSpeakerFromTable() {
        var data = $(".wavesurfer-annotations tr.row-content").map(function (index, elem) {
            var speaker = [];
            $('.wavesurfer-tier-Comments', this).each(function () {
                var data = $(this).val()||$(this).text();
                speaker.push(data);
                //console.log(d);
            });
            return speaker;
        });
        return data;
        //console.log(data);
        //console.log(data[0]);
    }

    function editTranscript() {
        var content = getContentFromTable();
        var speaker = getSpeakerFromTable();
        content = JSON.stringify(content);
        speaker = JSON.stringify(speaker);
        var transcriptId = '${transcript.transcriptId}';
        var xmlPath = '${transcript.xmlPath}';
        //console.log("CONTENT: "+JSON.stringify(content));
        //console.log("SPEAKER: "+JSON.stringify(speaker));
        var url = "/ajax-transcript.html?step=3";
        $.ajax({
            url: url,
            type: 'post',
            data: {
                content: content,
                speaker: speaker,
                transcriptId: transcriptId,
                xmlPath: xmlPath
            },
            success: function (res) {
                $('#noti').append('<div class="alert alert-warning alert-dismissible fade show">\n' +
                    '                   <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>\n' +
                    '                   <span>'+res+': Cập nhật dữ liệu bóc tách thành công</span>\n' +
                    '              </div>');
                console.log(res);
            },
            error: function (res) {
                console.log(res);
            }
        });
    }

    function searchContent() {
        var input, filter, table, tbody, tr, td, i, txtValue;
        input = $('#inputSearchContent');
        filter = input.val().toUpperCase();
        table = $('.table-transcript');
        tbody = table.find("tbody");
        tr = tbody.find("tr");
        for(i = 0; i < tr.length; i++){
            td = tr[i].getElementsByTagName("td")[1];
            if(td){
                txtValue = td.textContent || td.innerText;
                if(txtValue.toUpperCase().indexOf(filter) > -1){
                    tr[i].style.display="";
                }else {
                    tr[i].style.display="none";
                }
            }
        }
    }

    function searchSpeaker() {
        var input, filter, table, tbody, tr, td, i, txtValue;
        input = $('#inputSearchSpeaker');
        filter = input.val().toUpperCase();
        table = $('.table-transcript');
        tbody = table.find("tbody");
        tr = tbody.find("tr");
        for(i = 0; i < tr.length; i++){
            td = tr[i].getElementsByTagName("td")[2];
            if(td){
                txtValue = td.textContent || td.innerText;
                if(txtValue.toUpperCase().indexOf(filter) > -1){
                    tr[i].style.display="";
                }else {
                    tr[i].style.display="none";
                }
            }
        }
    }
    
    var update = function updateTranscript() {
        $('#editTranscriptForm').submit();
    }
    $('#editTranscriptForm').submit(function (e) {
        e.preventDefault();
        let editor = tinymce.get('full-featured-90');
        let content = editor.getContent();
        let transcriptId = '${transcript.transcriptId}';
        $.ajax({
            type: $(this).attr('method'),
            url: $(this).attr('action'),
            data: {
                content: content,
                transcriptId: transcriptId
            },
            success: function (res) {
                toastr.success('Đã lưu lại','Cập nhật văn bản',{
                    timeOut: 5000,
                    "closeButton": true,
                    "debug": false,
                    "newestOnTop": true,
                    "progressBar": true,
                    "positionClass": "toast-top-right",
                    "preventDuplicates": true,
                    "onclick": null,
                    "showDuration": "300",
                    "hideDuration": "1000",
                    "extendedTimeOut": "1000",
                    "showEasing": "swing",
                    "hideEasing": "linear",
                    "showMethod": "fadeIn",
                    "hideMethod": "fadeOut",
                    "tapToDismiss": false

                });
            },
            error: function (res) {
                console.log(res);
            }
        })
    });

</script>

<!-- wavesurfer.js -->
<script src="<c:url value='/template/js/wavesurfer/wavesurfer.js'/>"></script>

<!-- regions plugin -->
<script src="<c:url value='/template/js/wavesurfer/wavesurfer.regions.js'/>"></script>

<!-- ELAN format renderer -->
<script src="<c:url value='/template/js/wavesurfer/wavesurfer.elan.js'/>"></script>

<!-- App -->
<script>
    // Create an instance
    var wavesurfer;
    let pathXML = '<c:url value="/file/"/>' + '${transcript.xmlPath}';
    let pathAudio = '<c:url value="/file/"/>' + '${transcript.audioPath}';
    let annotations = ${arrayAnnotations};
    let contentHTML = '${transcript.content}';
    // Init & load
    document.addEventListener('DOMContentLoaded', function() {
        initPlayer();
        if(contentHTML === ""){
            console.log("Set content bang json");
            setTextAnnotation();
        }else {
            console.log("Set content bang content");
            $('#full-featured-90').html(contentHTML);
        }
    });

    function initPlayer() {
        var options = {
            container: '#waveform',
            waveColor: '#c1e7f4',
            progressColor: '#03a9f4',
            loaderColor: '#03a9f4',
            cursorColor: 'navy',
            selectionColor: '#d0e9c2',
            loopSelection: false,
            plugins: [
                WaveSurfer.elan.create({
                    url: pathXML,
                    container: '#annotations',
                    tiers: {
                        Text: true,
                        Comments: true
                    }
                }),
                WaveSurfer.regions.create()
            ]
        };

        // if (location.search.match('scroll')) {
        //     options.minPxPerSec = 100;
        //     options.scrollParent = true;
        // }
        //
        // if (location.search.match('normalize')) {
        //     // options.normalize = true;
        //     options.minPxPerSec = 100;
        //     options.scrollParent = true;
        // }
        options.minPxPerSec = 100;
        options.scrollParent = true;

        // Init wavesurfer
        wavesurfer = WaveSurfer.create(options);

        /* Progress bar */
        (function() {
            var progressDiv = document.querySelector('#progress-bar');
            var progressBar = progressDiv.querySelector('.progress-bar');

            var showProgress = function(percent) {
                progressDiv.style.display = 'block';
                progressBar.style.width = percent + '%';
            };

            var hideProgress = function() {
                progressDiv.style.display = 'none';
            };

            wavesurfer.on('loading', showProgress);
            wavesurfer.on('ready', hideProgress);
            wavesurfer.on('destroy', hideProgress);
            wavesurfer.on('error', hideProgress);
        })();

        wavesurfer.elan.on('ready', function(data) {
            //wavesurfer.load('transcripts/' + data.media.url);
            wavesurfer.load(pathAudio);
        });

        wavesurfer.elan.on('select', function(start, end) {
            wavesurfer.backend.play(start, end);
        });

        wavesurfer.elan.on('ready', function() {
            var classList = wavesurfer.elan.container.querySelector('table')
                .classList;
            ['table', 'table-striped', 'table-hover', 'table-transcript'].forEach(function(cl) {
                classList.add(cl);
            });
        });

        var prevAnnotation, prevRow, region;
        var onProgress = function(time) {
            var annotation = wavesurfer.elan.getRenderedAnnotation(time);
            highlightTextEditor(time);
            if (prevAnnotation != annotation) {
                prevAnnotation = annotation;

                region && region.remove();
                region = null;

                if (annotation) {
                    // console.log("Nghia ne!!");
                    // Highlight annotation table row
                    var row = wavesurfer.elan.getAnnotationNode(annotation);
                    // console.log("Rowwwwwwwwww!!");
                    // console.log(row);

                    prevRow && prevRow.classList.remove('table-success');
                    prevRow = row;
                    row.classList.add('table-success');
                    var before = row.previousSibling;
                    if (before) {
                        wavesurfer.elan.container.scrollTop = before.offsetTop;
                    }

                    // Region
                    region = wavesurfer.addRegion({
                        start: annotation.start,
                        end: annotation.end,
                        resize: false,
                        color: 'rgba(223, 240, 216, 0.7)'
                    });
                }
            }
        };


        wavesurfer.on('audioprocess', onProgress);
    }

    function setTextAnnotation() {
        let contentEditor = '';
        $.each(annotations, function (i, item) {
            let annotation = annotations[i];
            //console.log(annotation.text);
            let backgroundColor = "#92feae";
            if(annotation.reliabilityText > 80){
                backgroundColor = "#92feae";
            }else if(annotation.reliabilityText < 50){
                backgroundColor = "#fe9292";
            }else{
                backgroundColor = "#fef892";
            }
            contentEditor += '<span id="annotation' +annotation.seq+ '" style="background-color: ' +backgroundColor+';">' +annotation.text+ " " + '</span>';
        });
        $('#full-featured-90').html(contentEditor);
        //tinyMCE.activeEditor.setContent(contentEditor);
    }

    function highlightTextEditor(time) {
        let annotation;
        $.each(annotations, function (i, item) {
            if(annotations[i].timeStart <= time && time <= annotations[i].timeEnd){
                annotation = item;
                let id = '#annotation'+annotation.seq;
                var ed = tinymce.get('full-featured-90');
                var elements = $(ed.getBody()).find(id);
                var element = elements[0];
                $(element).css({'background-color': '#ffffff'});
                $(element).attr("data-mce-style", "background-color: #ffffff;");
                //console.log("Text Hightlight ne: " + annotation.text);
                return true;
            }
        });
    }


</script>


<script src="<c:url value='/template/js/wavesurfer/trivia.js'/>"></script>
<%--Tiny Editor--%>
<script src="<c:url value='/template/js/tinymce/tinymce.js'/>"></script>
<script src="<c:url value='/template/js/tinymce/init90.js'/>"></script>

</body>
</html>
