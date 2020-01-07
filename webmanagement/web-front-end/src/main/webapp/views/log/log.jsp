<%--
  Created by IntelliJ IDEA.
  User: HungPhan
  Date: 7/18/2019
  Time: 5:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <div class="card-title">
                        <h3 class="text-center">Quản lý lịch sử</h3>
                    </div>
                    <hr>

                    <div class="row">
                        <div class="col-4">
                            <ul class="list-group">
                                <li class="list-group-item active"
                                    style="background-color: #6300a4 !important; border-color:#6300a4 !important;">Lịch sử bộ từ điển
                                </li>
                                <li class="list-group-item"><a href="/dict-history.html">Bộ từ điển mặc định</a></li>
                                <li class="list-group-item"><a href="/dict-person-history.html">Bộ từ điển cá nhân</a></li>
                            </ul>

                        </div>
                        <div class="col-4">
                            <ul class="list-group">
                                <li class="list-group-item active"
                                    style="background-color: #ff9400 !important; border-color:#ff9400 !important;">Lịch
                                    sử bộ gõ
                                </li>
                                <li class="list-group-item ">Bộ gõ mặc định</li>
                                <li class="list-group-item ">Bộ gõ cá nhân</li>
                            </ul>

                        </div>
                        <div class="col-4">
                            <ul class="list-group">
                                <li class="list-group-item active"
                                    style="background-color: #0fad00 !important; border-color:#0fad00 !important;">Lịch
                                    sử bộ gõ
                                </li>
                                <li class="list-group-item ">Bộ gõ mặc định</li>
                                <li class="list-group-item ">Bộ gõ cá nhân</li>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
