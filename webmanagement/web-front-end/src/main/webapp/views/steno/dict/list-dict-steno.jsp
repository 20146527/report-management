<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>

<%--<c:url var="upLoadDist" value="/manager-dict-steno-list.html?type=upload"/>--%>

<html>
<head>
    <title>Danh sách bộ gõ</title>
</head>
<body>
<link href="<c:url value='/template/css/lib/jquery-filer/jquery.filer.css'/>" rel="stylesheet">
<link href="<c:url value='/template/css/lib/jquery-filer/themes/jquery.filer-dragdropbox-theme.css'/>" rel="stylesheet">
<script src="<c:url value='/template/js/lib/jquery-filer/jquery.filer.min.js'/>"></script>
<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item">Quản lý bộ gõ</li>
            <li class="breadcrumb-item active">Danh sách</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->

<!-- Container fluid -->
<div class="container-fluid">
    <!-- Start Page Content -->

    <div class="row">
        <div class="col-12">

            <div class="card">
                <div class="card-body">
                    <div class="card-title">
                        <h3 class="text-center">Quản lý bộ từ điển tốc ký</h3>
                    </div>
                    <hr>
                    <ul class="nav nav-pills m-t-30 m-b-30">
                        <li class="nav-item"><a href="#navpills-list-dict" class="nav-link active" id="tab-1"
                                                data-toggle="tab"
                                                aria-expanded="false">Danh sách</a></li>

                        <li class="nav-item"><a href="#navpills-upload-dict" class="nav-link" id="tab-2"
                                                data-toggle="tab"
                                                aria-expanded="false">Tải lên</a></li>

                    </ul>

                    <div class="tab-content br-n pn">

                        <div id="navpills-list-dict" class="tab-pane active">
                            <h4 class="card-title">Nội dung bộ từ điển tốc ký</h4>
                            <div class="table-responsive">
                                <table id="steno-content" class="table table-striped table-bordered" style="width:100%">

                                    <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Tên</th>
                                        <th>Độ dài</th>
                                        <th>Ngày sửa</th>
                                        <th>Người sửa</th>
                                        <th>Trạng thái</th>
                                        <th>Hành động</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:set var="index" value="0"/>
                                    <c:forEach items="${list}" varStatus="loop" var="tempt">
                                        <c:set var="index" value="${index + 1}"/>
                                    <tr>
                                        <td class="index">${index}</td>
                                        <td class="name-dict">${tempt.getDto().getDictName()}</td>
                                        <td class="lenght-dict">${tempt.getDto().getLength()}</td>
                                        <td class="mod-date-dict">${tempt.getDto().getModDate()}</td>
                                        <td class="user-mod-dict">${tempt.getUserName()}</td>
                                        <td>
                                            <c:if test="${tempt.getDto().getDictDefault() == 0}">
                                                <span class="badge btn-success">Hoạt động</span>
                                            </c:if>
                                        </td>
                                        <td>
                                            <button id="detail-dict" class="btn btn-success"
                                                    onclick="clickDetail(this)" data-tooltip="Chi tiết">
                                                <i class="fa fa-info" aria-hidden="true"></i>
                                            </button>

                                            <c:if test="${tempt.getDto().getDictDefault() != 0}">

                                                <button id="bt-default-dict" class="btn btn-info"
                                                        onclick="redirectFunction('default', this)"
                                                        data-tooltip="Mặc định">
                                                    <i class="fa fa-check" aria-hidden="true"></i>
                                                </button>

                                                <button id="bt-delete-dict" class="btn btn-danger"
                                                        onclick="fnDelete('delete', this)" data-tooltip="Xóa">
                                                    <i class="fa fa-trash-o" aria-hidden="true"></i>
                                                </button>

                                            </c:if>

                                            <button id="download" class="btn btn-info"
                                                    onclick="downloadDict('${tempt.getDto().getDictName()}')"
                                                    data-tooltip="Tải xuống">
                                                <i class="fa fa-download" aria-hidden="true"></i>
                                            </button>

                                        </td>
                                    </tr>
                                    </c:forEach>

                                </table>
                            </div>

                            <button class="btn btn-block btn-success mt-3" onclick="createDict()">Tạo mới bộ từ điển
                                bẳng bộ quy tắc
                            </button>

                        </div>

                        <div id="navpills-upload-dict" class="tab-pane">
                            <div class="mr-1 ml-1 mt-2 mb-5">Nhấn <a
                                    href="${pageContext.request.contextPath}/manager-dict-steno-list.html?data=download&type=template"
                                    style="color: #0d71bb">vào
                                đây</a> để tải file mẫu về
                            </div>
                            <form method="post" id="form-upload-dict">
                                <div class="row">
                                    <div class="col-lg-12 col-md-12 col-sm-12"
                                    >
                                        <c:if test="${not empty messageResponse}">

                                            <div class="alert alert-danger alert-dismissible fade show">
                                                <button type="button" class="close" data-dismiss="alert"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span></button>
                                                    ${messageResponse}
                                            </div>

                                        </c:if>

                                        <div class="form-group">
                                            <label for="dictName" class=" control-label">Tên bộ luật</label>

                                            <input class="form-control" type="text" id="dictName"
                                                   name="pojo.dictName" placeholder="Tên bộ luật"
                                                   <c:if test="${not empty defaultName}">disabled</c:if> />
                                        </div>


                                        <div class="form-group">

                                            <input type="file" name="files[]" id="filer_input" multiple>

                                        </div>


                                        <div class="row mb-2">

                                            <div class="col-lg-11">
                                                <h5>Đặt làm bộ gõ mặc định</h5>
                                            </div>


                                            <div class="col-lg-1">
                                                <label class="switch control-label" for="checkDictDefault"
                                                       style="float: right">
                                                    <input type="checkbox" name="checkDictDefault" id="checkDictDefault"
                                                           checked="checked">
                                                    <span class="slider round"></span>
                                                </label>
                                            </div>

                                        </div>

                                        <button type="submit" name="submit" class="btn btn-block btn-primary"
                                                id="bt-upload-new-dict">
                                            <i class="fa fa-upload"></i>&nbsp;
                                            <span>Tải lên bộ gõ mới</span>
                                        </button>

                                    </div>

                                </div>
                            </form>
                        </div>

                    </div>

                </div>
            </div>
        </div>
    </div>
    <!-- End PAge Content -->
</div>
<!-- End Container fluid -->

<div class="modal fade" id="modalCart" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <!--Header-->
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <!--Body-->
            <div id="body-dict" class="modal-body">


            </div>
            <!--Footer-->
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>

    $(document).ready(function () {
        <c:if test="${not empty messageResponse}">
        $("#tab-1").removeClass("active");
        $("#navpills-list-dict").removeClass("active");

        $("#tab-2").addClass("active");
        $("#navpills-upload-dict").addClass("active");
        </c:if>
    });

    function clickDetail(elem) {
        let nameFile = $(elem).closest("tr").find(".name-dict").text();
        let path = nameFile + ".json";
        $.get('<c:url  value="../../../file/dict/"/>' + path, function (data) {
            let index = 0;
            let body_dict = $("#body-dict");
            body_dict.empty();

            let d = ' <table id="table-dict" class="table table-hover">\n' +
                '                <thead>\n' +
                '                <tr>\n' +
                '                <th>STT</th>\n' +
                '                <th>Mã Steno</th>\n' +
                '            <th>Âm Tiết</th>\n' +
                '            </tr>\n' +
                '            </thead>\n' +
                '            <tbody id="body-table">';

            $.each(data, function (n, v) {
                index++;

                d += '<tr>\n' +
                    '<th scope="row">' + index + '</th>\n' +
                    '<td>' + n + '</td>\n' +
                    '<td>' + v + '</td>\n' +
                    '</tr>';

            });

            d += '</tbody>\n' +
                '                </table>';

            body_dict.html(d);


            $("#table-dict").DataTable({
                "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
            });
            $("#myModalLabel").text("Nội dung file: " + nameFile);
            $("#modalCart").modal('show');
        });

    }

</script>

<script src="<c:url value="/template/js/steno/dict/list-dict-steno.js"/>"></script>
<script src="<c:url value="/template/js/steno/dict/custom-input-upload-dict.js"/>"></script>


</body>
</html>
