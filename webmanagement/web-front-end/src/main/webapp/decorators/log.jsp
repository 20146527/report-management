<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="vn">

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
    <title>Lịch sử</title>
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
    <link rel="stylesheet" href="<c:url value='/template/css/manager.css'/>"/>
    <link href="<c:url value='/template/css/tiny-style.css'/>" rel="stylesheet"/>
    <script src="<c:url value='/template/js/lib/jquery/jquery-3.4.1.js'/>"></script>
    <link href="<c:url value='/template/css/progress.css'/>" rel="stylesheet">
</head>

<body class="fix-header fix-sidebar">

<div id="overlay">
    <progress id="progress" class="pure-material-progress-circular"
              style="margin: 20% auto auto; display: block;"></progress>
</div>

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

        <!-- Bread crumb -->
        <div class="row page-titles">
            <div class="col-md-5 align-self-center">
                <h3 class="text-primary">Dashboard</h3></div>
            <div class="col-md-7 align-self-center">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
                    <li class="breadcrumb-item">Lịch sử</li>
                </ol>
            </div>
        </div>
        <!-- End Bread crumb -->

        <dec:body/>

        <%@include file="/common/views/footer.jsp" %>

    </div>
    <!-- End Page wrapper  -->
</div>
<!-- End Wrapper -->
<!-- All Jquery -->
<!-- Bootstrap tether Core JavaScript -->
<script src="<c:url value='/template/js/lib/bootstrap/js/popper.min.js'/>"></script>
<script src="<c:url value='/template/js/lib/bootstrap/js/bootstrap.min.js'/>"></script>
<!-- slimscrollbar scrollbar JavaScript -->
<script src="<c:url value='/template/js/jquery.slimscroll.js'/>"></script>
<%--<!--Menu sidebar -->--%>
<%--<script src="<c:url value='/template/js/sidebarmenu.js'/>"></script>--%>
<!--stickey kit -->
<script src="<c:url value='/template/js/lib/sticky-kit-master/dist/sticky-kit.min.js'/>"></script>
<!--Custom JavaScript -->


<%--<!-- Amchart -->--%>
<%--<script src="<c:url value='/template/js/lib/morris-chart/raphael-min.js'/>"></script>--%>
<%--<script src="<c:url value='/template/js/lib/morris-chart/morris.js'/>"></script>--%>
<%--<script src="<c:url value='/template/js/lib/morris-chart/dashboard1-init.js'/>"></script>--%>
<%--<script src="<c:url value='/template/js/lib/calendar-2/moment.latest.min.js'/>"></script>--%>
<%--<!-- scripit init-->--%>
<%--<script src="<c:url value='/template/js/lib/calendar-2/semantic.ui.min.js'/>"></script>--%>
<%--<!-- scripit init-->--%>
<%--<script src="<c:url value='/template/js/lib/calendar-2/prism.min.js'/>"></script>--%>
<%--<!-- scripit init-->--%>
<%--<script src="<c:url value='/template/js/lib/calendar-2/pignose.calendar.min.js'/>"></script>--%>
<%--<!-- scripit init-->--%>
<%--<script src="<c:url value='/template/js/lib/calendar-2/pignose.init.js'/>"></script>--%>

<%--<script src="<c:url value='/template/js/lib/owl-carousel/owl.carousel.min.js'/>"></script>--%>
<%--<script src="<c:url value='/template/js/lib/owl-carousel/owl.carousel-init.js'/>"></script>--%>

<%--<!-- scripit init-->--%>

<script src="<c:url value='/template/js/scripts.js'/>"></script>
<script src="<c:url value='/template/js/lib/datatables/datatables.min.js'/>"></script>
<script src="<c:url value='/template/js/log/dict-history.js'/>"></script>
<script src="<c:url value='/template/js/jquery.base64.js'/>"></script>


</body>

</html>