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
<link href="<c:url value='/template/css/wavesurfer/ribbon.css'/>" rel="stylesheet"/>
<link href="<c:url value='/template/css/wavesurfer/style.css'/>" rel="stylesheet"/>
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
                        <h3 class="text-center">Bước 2/3: Chỉnh sửa nội dung ghép</h3>
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
                        <!-- Waveform-->
                        <div id="waveform">

                            <!-- Here be waveform -->
                        </div>
                        <div class="row m-t-20">
                            <div class="col-12 text-center controls">
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
                        </div>
                        <!-- End Waveform -->
                        <hr>
                        <div class="row">
                            <div class="col-md-6 col-sm-12">
                                <!-- Transcript -->
                                <div id="transcript-editor">
                                    <form action="" method="post" id="">
                                        <label for="transcriptEditor95" class="control-label">Nội dung bóc băng</label>
                                        <textarea id="transcriptEditor95" name="pojo.content">
                                            ${transcript.content}
                                        </textarea>
                                    </form>
                                </div>
                                <!-- End Steno -->
                            </div>
                            <div class="col-md-6 col-sm-12">
                                <!-- Steno -->
                                <div id="steno-editor">
                                    <form action="" method="post" id="editReportForm">
                                        <label for="stenoEditor95" class="control-label">Nội dung tốc ký</label>
                                        <textarea id="stenoEditor95" name="pojo.content">
                                            <h2>Nội dung tiếng Việt chuyển đổi từ Tốc ký</h2>
                                            <p>Kính thưa các đồng chí lãnh đạo thành phố thưa toàn thể các đồng chí Giám đốc các sở, ban, ngành, thành phố thực hiện Quy chế và làm việc của Ubnd thành phố hôm nay, Ubnd thành phố Hà Nội tổ chức Hội nghị lãnh đạo Sở, ban, ngành, địa phương 8 tháng 1 năm 2017. Chủ trì Hội nghị trân trọng giới thiệu chung về Uỷ viên Ban Chấp hành Trung ương Đảng, Chủ tịch Ubnd thành phố Tháp Tùng, nay xin trân trọng giới thiệu đồng chí Phó Chủ tịch Ubnd thành phố Đồng chí Phó Chủ tịch Ngô Văn Quý tham gia Đoàn công tác Phó Bí thư Thành uỷ làm việc tại Hà Nội đồng chí Phó Chủ tịch Lê Hồng Sơn Vừa Tết trồng cây tại tỉnh Vĩnh Phúc. Các đồng chí Giám đốc Sở, Thủ trưởng Thủ trưởng các sở, ban, ngành, thành phố, đồng chí Tô Văn Động Tham gia Đoàn công tác đồng chí Bí thư Thành uỷ tại việc Liên đoàn Văn phòng Ubnd thành phố đồng chí các đồng chí Hội đồng quản trị, Tổng Giám đốc Công ty của thành phố, Tcty thuộc Hà Nội, đại diện cho Trung ương và thành phố tới dự hội nghị Văn phòng Ubnd thành phố Hà Nội báo cáo chương trình Hội nghị như sau. Thứ nhất, đồng chí Giám đốc Sở và đồng chí Giám đốc Sở Kế hoạch và Đầu tư trình bày báo cáo kinh tế xã hội tháng 1 năm 2007, nhiệm vụ trọng tâm tháng 2 năm 1007 tiếp theo. Đồng chí Chánh Văn phòng Uỷ ban nhân dân thành phố báo cáo kết quả thực hiện nhiệm vụ chỉ đạo, điều hành Uỷ Ban Nhân Dân Thành Phố Tháng 1 Năm 2007 và một số nhiệm vụ trọng tâm trong tháng 22007 tiếp theo. Đồng chí Chủ tịch Ubnd thành phố chủ trì phần phần tham luận xin ý kiến của các sở, ban, ngành và các đồng chí Phó Chủ tịch Ubnd thành phố kết luận. Xin trân trọng kính mời Chủ tịch Ubnd thành phố chủ trì hội nghị đối với bể nước do Uỷ ban Tài trợ đến hết năm 2016. Thời tiết thời tiết năm nay tương đối thuận lợi không có rét đậm rét hại. 3 D tốt diện tích cấy vụ xuân đến nay là 15 % một số giấy ăn phía Bắc như phương Tây Ba vì ba lăm phần % thứ hai về tưới nước đổ ải thì đến nay toàn bộ diện tích tưới nước đạt 60 % đến 0 h sáng ngày hôm nay đã mở ra hoặc 3 đợt tập trung khoảng 56 ngày là đủ nước để gieo cấy vụ xuân rồi rồi rồi sợ tóc sang một đại biểu khác nói tiếp đi đến đoạn sau thì đại biểu Nam nói tiếp về nuôi biến nuôi của nó là phương pháp làm trắng da gia tác làm công tác đang rao bán iphone 5 c và tay chân miệng 202016 đã xây dựng song 6 chặng đua tràn ở Mê Linh đang cập nhật công nghệ năm 2017. Tổ chức nghiệm thu xong các trạm đã xây dựng xong thì hệ thống công trình đầu mối hệ thống ống dẫn và đặc biệt sử dụng nước tốt. Tuy nhiên, việc sử dụng nước sạch cho thành tập quán việc đấu nối vào nhà tên con trai thứ hai là dự án rau sạch Việt thứ hai lần việc này thành phố đã giao cho cấp huyện thực hiện Sở Sở chỉ tham gia ý kiến về chuyên ngành toàn bộ hạ tầng vùng rau là thành phố chợ cấp huyện làm chủ đầu tư sở tham gia ý kiến theo thiết kế của cơ sở các dự án trong các dự án thành phố hỗ trợ hạ tầng vùng thuỷ lợi, giao thông để phục vụ cho hạ tầng sản xuất rau của thành phố. Qua khảo sát thấy cùng Hạo Tùng do được đầu tư rất lớn. Tuy nhiên, sản xuất hiệu quả không cao sở ta nên tập trung vào hỗ trợ giống và hỗ trợ nông nghiệp công nghệ cao bao gồm nhà lưới nhà kính hệ thống tưới</p>
                                        </textarea>
                                    </form>
                                </div>
                                <!-- End Steno -->
                            </div>
                        </div>

                        <div>
                            <button type="submit" name="submit" id="btnNext"
                                    onclick="nextStep()" data-tooltip="Tiếp theo >> Hoàn thiện biên bản"
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
    let sessionId = ${sessionId};
    let recordId = ${recordId};
    let stenoId = ${stenoId};
    let templateId = ${templateId}
    $(document).ready(function () {
        //$('#myModal').modal({backdrop: 'static', keyboard: false});
        //insertData()
    });
    function nextStep() {
        // var url = "/report-create.html?step=3&sessionId="+sessionId+"&recordId="+recordId;
        // $(location).attr('href', url);
        $("#overlay").css({"display": "block"});
        let transcriptEditor = tinymce.get('transcriptEditor95');
        let stenoEditor = tinymce.get('stenoEditor95');
        $.ajax({
            type: 'post',
            url: '/ajax-create-report.html?urlType=url_next_step',
            data: {
                urlType: 'url_next_step',
                transcriptContent: transcriptEditor.getContent(),
                stenoContent: stenoEditor.getContent(),
                step: 2,
                sessionId: sessionId,
                recordId: recordId,
                stenoId: stenoId,
                templateId: templateId
            },
            success: function (res) {
                var url = "/report-create.html?step=3&sessionId="+sessionId+"&recordId="+recordId + "&stenoId=" + stenoId + "&templateId=" + templateId;
                $(location).attr('href', url);
            },
            error: function (res) {
                console.log(res);
            }
        })
    }

</script>

<!-- wavesurfer.js -->
<script src="<c:url value='/template/js/wavesurfer/wavesurfer.js'/>"></script>

<!-- cursor plugin -->
<script src="<c:url value='/template/js/wavesurfer/wavesurfer.cursor.js'/>"></script>

<!-- App -->
<script>
    // Create an instance
    var wavesurfer;
    var pathXML = '<c:url value="/file/"/>' + '${transcript.xmlPath}';
    var pathAudio = '<c:url value="/file/"/>' + '${transcript.audioPath}';
    // Init & load
    document.addEventListener('DOMContentLoaded', function () {
        initPlayer();
    });

    function initPlayer() {
        var options = {
            container: '#waveform',
            waveColor: '#c1e7f4',
            progressColor: '#03a9f4',
            loaderColor: '#03a9f4',
            plugins: [
                WaveSurfer.cursor.create({
                    showTime: true,
                    opacity: 1,
                    customShowTimeStyle: {
                        'background-color': '#000',
                        color: '#fff',
                        padding: '2px',
                        'font-size': '10px'
                    }
                })
            ]
        };
        // Init wavesurfer
        wavesurfer = WaveSurfer.create(options);

        // Load audio from URL
        wavesurfer.load(pathAudio);

    }

</script>

<script src="<c:url value='/template/js/wavesurfer/trivia.js'/>"></script>

<%--Tiny Editor--%>
<script src="<c:url value='/template/js/tinymce/tinymce.js'/>"></script>
<script src="<c:url value='/template/js/tinymce/init-steno95.js'/>"></script>
<script src="<c:url value='/template/js/tinymce/init-transcript95.js'/>"></script>
</body>
</html>
