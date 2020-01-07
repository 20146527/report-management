<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="apple-touch-icon" href="<c:url value="/template/images/favicon.png"/>">
    <link rel="shortcut icon" href="<c:url value="/template/images/favicon.png"/>">
    <link rel="icon" type="image/png" sizes="16x16" href="<c:url value='/template/images/favicon.png'/>">
    <!-- Bootstrap Core CSS -->
    <link href="<c:url value='/template/css/lib/bootstrap/bootstrap.min.css'/>" rel="stylesheet"/>
    <!-- Custom CSS -->

    <link href="<c:url value='/template/css/lib/calendar2/semantic.ui.min.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/template/css/lib/calendar2/pignose.calendar.min.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/template/css/lib/owl.carousel.min.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/template/css/lib/owl.theme.default.min.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/template/css/helper.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/template/css/style.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/template/css/global_style.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/template/css/progress.css'/>" rel="stylesheet">
    <link href="<c:url value='/template/css/lib/jquery-filer/jquery.filer.css'/>" rel="stylesheet">
    <link href="<c:url value='/template/css/lib/jquery-filer/themes/jquery.filer-dragdropbox-theme.css'/>" rel="stylesheet">
    <script src="<c:url value='/template/js/lib/jquery/jquery-3.4.1.js'/>"></script>
    <script src="<c:url value='/template/js/lib/jquery-filer/jquery.filer.min.js'/>"></script>

    <link rel="stylesheet" href="<c:url value='/template/css/lib/froala_editor_2.9.6/froala_editor.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/css/lib/froala_editor_2.9.6/froala_style.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/css/lib/froala_editor_2.9.6/plugins/code_view.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/css/lib/froala_editor_2.9.6/plugins/colors.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/css/lib/froala_editor_2.9.6/plugins/emoticons.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/css/lib/froala_editor_2.9.6/plugins/image_manager.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/css/lib/froala_editor_2.9.6/plugins/image.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/css/lib/froala_editor_2.9.6/plugins/line_breaker.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/css/lib/froala_editor_2.9.6/plugins/table.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/css/lib/froala_editor_2.9.6/plugins/char_counter.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/css/lib/froala_editor_2.9.6/plugins/video.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/css/lib/froala_editor_2.9.6/plugins/fullscreen.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/css/lib/froala_editor_2.9.6/plugins/file.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/css/lib/froala_editor_2.9.6/plugins/codemirror.min.css'/>">
    <link href="<c:url value='/template/css/lib/editor/editor.css'/>" rel="stylesheet">
    <link href="<c:url value='/template/css/accordion/accordion.css'/>" rel="stylesheet">
    <link href="<c:url value='/template/css/pagination/pagination.css'/>" rel="stylesheet">
    <link href="<c:url value='/template/css/lib/jquery/jquerysctipttop.css'/>" rel="stylesheet">
    <link href="<c:url value='/template/css/datetime/bootstrap-datetimepicker.css'/>" rel="stylesheet">
    <link href="<c:url value='/template/css/floating/materialize.css'/>" rel="stylesheet">
    <link href="<c:url value="/template/css/lib/sweetalert/sweetalert.css"/>" rel="stylesheet">
    <link href="<c:url value="/template/css/lib/jquery/jquery-ui.css"/>" rel="stylesheet">
    <%--    <link href="<c:url value="/template/css/lib/toastr/toastr.min.css"/>" rel="stylesheet">--%>


    <title>Tốc ký</title>

</head>

<body class="fix-header fix-sidebar">

<div id="overlay">
    <progress id="progress" class="pure-material-progress-circular"
              style="margin: 20% auto auto; display: block;"></progress>
</div>

<!-- Preloader - style you can find in spinners.css -->
<div class="preloader">
    <svg class="circular" viewBox="25 25 50 50">
        <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"></circle>
    </svg>
</div>

<!-- Main wrapper  -->
<div id="main-wrapper">

    <%@include file="/common/views/header.jsp" %>

    <%@include file="/common/views/menu.jsp" %>

    <!-- Page wrapper  -->
    <div class="page-wrapper">

        <dec:body/>

    </div>
    <!-- End Page wrapper  -->
    <%@include file="/common/views/footer.jsp" %>
</div>
<!-- End Wrapper -->
<!-- All Jquery -->
<!-- Bootstrap tether Core JavaScript -->
<script src="<c:url value='/template/js/lib/bootstrap/js/popper.min.js'/>"></script>
<script src="<c:url value='/template/js/lib/bootstrap/js/bootstrap.min.js'/>"></script>
<!-- slimscrollbar scrollbar JavaScript -->
<script src="<c:url value='/template/js/jquery.slimscroll.js'/>"></script>
<!--Menu sidebar -->
<%--<script src="<c:url value='/template/js/sidebarmenu.js'/>"></script>--%>
<!--stickey kit -->
<script src="<c:url value='/template/js/lib/sticky-kit-master/dist/sticky-kit.min.js'/>"></script>
<!--Custom JavaScript -->

<script src="<c:url value="/template/js/lib/datatables/datatables.min.js"/>"></script>
<script src="<c:url value="/template/js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"/>"></script>
<script src="<c:url value="/template/js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.flash.min.js"/>"></script>
<script src="<c:url value="/template/js/lib/datatables/cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"/>"></script>
<script src="<c:url value="/template/js/lib/datatables/cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"/>"></script>
<script src="<c:url value="/template/js/lib/datatables/cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"/>"></script>
<script src="<c:url value="/template/js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"/>"></script>
<script src="<c:url value="/template/js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"/>"></script>
<script src="<c:url value="/template/js/lib/datatables/datatables-init.js"/>"></script>

<script src="<c:url value='/template/js/scripts.js'/>"></script>
<script src="<c:url value='/template/js/easytimer/easytimer.min.js'/>"></script>
<script src="<c:url value='/template/js/lib/form-validation/jquery.validate.min.js'/>"></script>
<%--<script src="<c:url value='/template/js/manager/validate-speaker.js'/>"></script>--%>
<%--<script src="<c:url value='/template/js/lib/form-validation/validate-init.js'/>"></script>--%>
<script src="<c:url value='/template/js/jquery.base64.js'/>"></script>
<%--Tiny Editor--%>
<script src="<c:url value='/template/js/tinymce/tinymce.js'/>"></script>
<script src="<c:url value='/template/js/tinymce/init.js'/>"></script>


<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/codemirror.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/xml.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/froala_editor.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/align.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/code_beautifier.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/code_view.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/colors.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/draggable.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/emoticons.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/font_size.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/font_family.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/image.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/file.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/image_manager.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/line_breaker.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/link.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/lists.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/paragraph_format.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/paragraph_style.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/video.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/table.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/url.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/entities.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/char_counter.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/inline_style.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/save.min.js'/>"></script>
<script type="text/javascript"
        src="<c:url value='/template/js/lib/froala_editor_2.9.6/plugins/fullscreen.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/template/js/lib/froala_editor_2.9.6/languages/vi.js'/>"></script>
<%--<script type="text/javascript" src="<c:url value='/template/js/pagination/pagination.min.js'/>"></script>--%>
<script type="text/javascript" src="<c:url value='/template/js/lib/moment/moment.js'/>"></script>
<script type="text/javascript" src="<c:url value='/template/js/datetime/bootstrap-datetimepicker.min.js'/>"></script>

<%--Sweet Alert--%>
<script src="<c:url value='/template/js/lib/sweetalert/sweetalert.min.js'/>"></script>
<script src="<c:url value='/template/js/lib/jquery-ui/jquery-ui.min.js'/>"></script>
<%--<script src="<c:url value='/template/js/lib/toastr/toastr.min.js'/>"></script>--%>



</body>

</html>