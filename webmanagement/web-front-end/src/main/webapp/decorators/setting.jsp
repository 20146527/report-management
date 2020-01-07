<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <link rel="stylesheet" href="<c:url value='/template/css/avatar.css'/>"/>
<%--    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">--%>
    <link href="<c:url value='/template/css/tiny-style.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/template/css/lib/bootstrap/bootstrap-colorselector.css'/>" rel="stylesheet"/>
    <script src="<c:url value='/template/js/lib/jquery/jquery-3.4.1.js'/>"></script>

    <link href="<c:url value='/template/css/file-browser/styles.css'/>" rel="stylesheet">

    <link href="<c:url value='/template/css/lib/tagify/tagify.css'/>" rel="stylesheet"/>
    <script src="<c:url value='/template/js/lib/tagify/tagify.js'/>"></script>
    <script src="<c:url value='/template/js/lib/tagify/jQuery.tagify.min.js'/>"></script>
</head>

<body class="fix-header fix-sidebar">
<!-- Preloader - style you can find in spinners.css -->
<div class="preloader">
    <svg class="circular" viewBox="25 25 50 50">
        <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"/>
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
<script src="<c:url value='/template/js/scripts.js'/>"></script>
<script src="<c:url value='/template/js/lib/form-validation/jquery.validate.min.js'/>"></script>

<%--<script src="<c:url value='/template/js/lib/bootstrap/js/bootstrap-colorselector.js'/>"></script>--%>
<script>
    // $('#colorselector80').colorselector();
    // $('#colorselector50').colorselector();
    // $('#colorselector20').colorselector();
</script>

</body>

</html>