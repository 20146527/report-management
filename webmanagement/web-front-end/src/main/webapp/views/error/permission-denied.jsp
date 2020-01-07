<%--
  Created by IntelliJ IDEA.
  User: HungPhan
  Date: 5/28/2019
  Time: 1:33 PM
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Lỗi</title>
    <!-- Bootstrap Core CSS -->
    <link href="<c:url value='/template/css/lib/bootstrap/bootstrap.min.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/template/css/helper.css'/>" rel="stylesheet"/>
    <link href="<c:url value='/template/css/style.css'/>" rel="stylesheet"/>
</head>


<body class="fix-header fix-sidebar">
<!-- Preloader - style you can find in spinners.css -->
<div class="preloader">
    <svg class="circular" viewBox="25 25 50 50">
        <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"/>
    </svg>
</div>

<!-- Main wrapper  -->
<div class="error-page" id="wrapper">
    <div class="error-box">
        <div class="error-body text-center">
            <h1>Hmmm...!</h1>
            <h3 class="text-uppercase">Permission Denied</h3>
            <p class="text-muted m-t-30 m-b-30">Liên hệ admin để cấp quyền sử dụng chức năng này.</p>
            <a class="btn btn-info btn-rounded waves-effect waves-light m-b-40" href="/home.html">Trở về trang chủ</a>
        </div>
    </div>
</div>
<!-- End Wrapper -->

<!-- End Wrapper -->
<!-- All Jquery -->
<script src="<c:url value='/template/js/lib/jquery/jquery-3.4.1.js'/>"></script>
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

<script src="<c:url value='/template/js/scripts.js'/>"></script>

</body>
</html>
