<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="formUrl" value="/upload-ava.html"/>
<c:url var="changePassWord" value="/personal-information.html"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-fluid">
    <form action="${formUrl}" method="post" enctype="multipart/form-data" id="formEdit">
        <div class="bg-white m-l-0 m-r-0 box-shadow pb-3">

            <div class="col-md-12" style="text-align: center">
                <h2 style="padding: 15px"><strong>Thông tin cá nhân</strong></h2>
            </div>

            <div class="row" style="margin: auto">

                <div class="col-md-4">

                    <div class="avatar-wrapper">
                        <img id="profile-pic" class="profile-pic" src="<c:url value='/template/images/avatar/${login.getAvaPath()}'/>"/>

                        <div class="upload-button">
                            <i class="fa fa-arrow-circle-up" aria-hidden="true"></i>
                        </div>
                        <input class="file-upload" type="file" accept="image/*"/>
                    </div>

                </div>

                <div class="col-md-8">
                    <div class="row mb-3">
                        <div class="col-md-4"><h3><b>Tên Đăng Nhập:</b></h3></div>
                        <div class="col-md-8"><h3>${login.getUserName()}</h3></div>
                    </div>

                    <div class="row mt-3 mb-3">
                        <div class="col-md-4"><h3><b>Mật Khẩu:</b></h3></div>
                        <div class="col-md-6"><h3>***************</h3></div>
                        <div class="col-md-2">
                            <div style="display: table-cell; width: 100%">&nbsp;</div>
                            <div style="display: table-cell; white-space: nowrap;">
                                <button type="button" class="btn btn-link" id="edit"
                                        data-toggle="modal" data-target="#exampleModalCenter">Sửa
                                </button>
                            </div>
                        </div>
                    </div>

                    <div class="row mt-3 mb-3">
                        <div class="col-md-4"><h3><b>Họ Và Tên:</b></h3></div>
                        <div class="col-md-8"><h3>${login.getFullName()}</h3></div>
                    </div>

                    <div class="row mt-3 mb-3">
                        <div class="col-md-4"><h3><b>Địa Chỉ:</b></h3></div>
                        <div class="col-md-8"><h3>${login.getAddress()}</h3></div>
                    </div>

                    <div class="row mt-3 mb-3">
                        <div class="col-md-4"><h3><b>Số Điện Thoại:</b></h3></div>
                        <div class="col-md-8"><h3>${login.getPhone()}</h3></div>
                    </div>

                    <div class="row mt-3 mb-3">
                        <div class="col-md-4"><h3><b>Email:</b></h3></div>
                        <div class="col-md-8"><h3>${login.getEmail()}</h3></div>
                    </div>

                    <div class="row mt-3 mb-3">
                        <div class="col-md-4"><h3><b>Chức Vụ:</b></h3></div>
                        <div class="col-md-8"><h3>${login.getRoleDto().getRoleName()}</h3></div>
                    </div>

                </div>

            </div>

            <div class="row mt-3 mb-3">
                <div class="col-1"></div>

                <div class="col-5">
                    <button type="submit" name="submit"
                            onclick="document.location.href='/home.html';"
                            class="btn btn-warning btn-block">
                        <i class="ace-icon fa fa-times"></i>&nbsp;
                        <span>Hủy</span>
                    </button>
                </div>

                <div class="col-5">
                    <button type="submit"
                            name="submit"
                            class="btn btn-info btn-block" onclick="">
                        <i class="fa fa-save"></i>&nbsp;
                        <span>Lưu lại</span>
                    </button>
                </div>

                <div class="col-1"></div>
            </div>
        </div>
    </form>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">

    <form action="${changePassWord}" method="post">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title" id="exampleModalLongTitle">Cập nhật mật khẩu</h3>
                </div>
                <div class="modal-body">

                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-danger">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>

                    <div class="form-group">

                        <label for="oldPassword">Nhập mật khẩu hiện tại</label>

                        <input type="password" name="oldPassword" class="form-control" id="oldPassword"
                               placeholder="Password">

                    </div>

                    <div class="form-group">
                        <label for="newPassword">Nhập mật khẩu mới</label>

                        <input type="password" name="newPassword" class="form-control" id="newPassword"
                               placeholder="Password">

                    </div>

                    <div class="form-group">
                        <label for="renewPassword">Nhập mật lại khẩu mới</label>

                        <input type="password" name="renewPassword" class="form-control" id="renewPassword"
                               placeholder="Password">
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" id="close">Close</button>

                    <button id="submit123" type="submit" class="btn btn-primary">Lưu</button>
                </div>
            </div>
        </div>
    </form>
</div>
<script>
    $(document).ready(function () {

        $("#close").click(function () {
            $("#exampleModalCenter").modal.close();
        });
    });
</script>

<c:if test="${not empty messageResponse}">
    <script>
        window.onload = function () {
            document.getElementById('edit').click();
        }
    </script>
</c:if>

</body>
</html>
