<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html lang="en">
<head>
    <title><dec:title default="Đăng Nhập"/></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="apple-touch-icon" href="<c:url value="/template/images/favicon.png"/>">
    <link rel="shortcut icon" href="<c:url value="/template/images/favicon.png"/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/template/assets/vendor/bootstrap/css/bootstrap.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/template/assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/template/assets/vendor/animate/animate.css"/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/template/assets/vendor/css-hamburgers/hamburgers.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/assets/vendor/select2/select2.min.css'/>">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/assets/css/util.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/template/assets/css/main.css'/>">
    <!--===============================================================================================-->
</head>
<dec:head/>
<body>

<dec:body/>

<!--===============================================================================================-->
<script src="<c:url value='/template/js/lib/jquery/jquery-3.4.1.js'/>"></script>
<!--===============================================================================================-->
<script src="<c:url value='/template/js/lib/bootstrap/js/popper.min.js'/>"></script>
<script src="<c:url value='/template/js/lib/bootstrap/js/bootstrap.min.js'/>"></script>
<!--===============================================================================================-->
<script src="<c:url value='/template/assets/vendor/select2/select2.min.js'/>"></script>
<!--===============================================================================================-->
<script src="<c:url value='/template/assets/vendor/tilt/tilt.jquery.min.js'/>"></script>
<script>
    $('.js-tilt').tilt({
        scale: 1.1
    })
</script>
<!--===============================================================================================-->
<script src="<c:url value='/template/assets/js/main.js'/>"></script>

</body>
</html>