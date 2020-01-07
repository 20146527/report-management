<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="formLogin" value="/login.html"/>
<html>
<head>
    <title>Đăng Nhập</title>
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="img-bgr">
            <div class="wrap-login100">
                <div class="login100-pic js-tilt" data-tilt>
                    <img src="<c:url value='/template/assets/images/img-01.png'/>" alt="IMG">

                    <c:if test="${not empty messageResponse}">
                        <div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
                            <span id="error-login" class="badge badge-pill badge-danger">${messageResponse}</span>

                                <%--<button type="button" class="close" data-dismiss="alert" aria-label="Close">--%>
                                <%--<span aria-hidden="true">&times;</span>--%>
                                <%--</button>--%>
                        </div>
                    </c:if>

                </div>

                <form action="${formLogin}" method="post">
                    <span class="login100-form-title">
						Member Login
					</span>

                    <div class="wrap-input100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
                        <input class="input100" type="text" name="pojo.userName" placeholder="Email">
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
                    </div>

                    <div class="wrap-input100 validate-input" data-validate="Password is required">
                        <input class="input100" type="password" name="pojo.passWord" placeholder="Password">
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
                    </div>

                    <button type="submit" name="submit" class="login100-form-btn">Login</button>

                    <div class="text-center p-t-12">
						<span class="txt1">
							Forgot
						</span>
                        <a class="txt2" href="#">
                            Username / Password?
                        </a>
                    </div>

                    <div class="text-center p-t-136">
                        <a class="txt2" href="#">
                            Create your Account
                            <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
