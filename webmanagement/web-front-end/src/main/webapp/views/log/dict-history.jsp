<%--
  Created by IntelliJ IDEA.
  User: HungPhan
  Date: 7/19/2019
  Time: 9:44 AM
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
                        <h3 class="text-center">Lịch sử bộ từ điển mặc định</h3>
                    </div>
                    <hr>

                    <div class="col-12">
                        <div class="table-responsive">
                            <table id="history" class="table table-striped table-bordered" style="width:100%">

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
                                <c:forEach items="${list}" varStatus="loop" var="tempt">
                                <tr>
                                    <td class="index">${tempt.getIndex()}</td>
                                    <td class="name-dict">${tempt.getDto().getDictName()}</td>
                                    <td class="lenght-dict">${tempt.getDto().getLength()}</td>
                                    <td class="mod-date-dict">${tempt.getDto().getModDate()}</td>
                                    <td class="user-mod-dict">${tempt.getUserName()}</td>
                                    <td>
                                        <c:if test="${tempt.getDto().getDictDefault() == 1}">
                                            Hoạt động
                                        </c:if>

                                        <c:if test="${tempt.getDto().getStatus() == 1}">
                                            Đã Xóa
                                        </c:if>

                                    </td>

                                    <td>


                                        <c:if test="${tempt.getDto().getStatus() == 1}">

                                            <button id="detail-dict" class="btn btn-success"
                                                    onclick="infoHistory(this,  '${default_dict}' )"
                                                    data-tooltip="Chi tiết">
                                                <i class="fa fa-info" aria-hidden="true"></i>
                                            </button>

                                            <button id="detail-history" class="btn btn-warning"
                                                    onclick="redirectFunction('restore', this)"
                                                    data-tooltip="Khôi phục">
                                                <i class="fa fa-history" aria-hidden="true"></i>
                                            </button>


                                        </c:if>

                                        <c:if test="${tempt.getDto().getStatus() == 0}">

                                            <c:if test="${tempt.getDto().getDictDefault() == 1}">
                                                <button id="detail-dict" class="btn btn-success"
                                                        onclick="clickDetail(this)" data-tooltip="Chi tiết">
                                                    <i class="fa fa-info" aria-hidden="true"></i>
                                                </button>
                                            </c:if>

                                            <c:if test="${tempt.getDto().getDictDefault() == 0}">

                                                <button id="detail-dict" class="btn btn-success"
                                                        onclick="infoHistory(this, '${default_dict}')"
                                                        data-tooltip="Chi tiết">
                                                    <i class="fa fa-info" aria-hidden="true"></i>
                                                </button>


                                                <button id="detail-default" class="btn btn-primary"
                                                        onclick="redirectFunction('default', this)"
                                                        data-tooltip="Mặc định">
                                                    <i class="fa fa-check-square-o" aria-hidden="true"></i>
                                                </button>
                                            </c:if>

                                        </c:if>

                                        <button id="download" class="btn btn-info"
                                                onclick="download('${tempt.getDto().getDictName()}')"
                                                data-tooltip="Tải xuống">
                                            <i class="fa fa-download" aria-hidden="true"></i>
                                        </button>

                                    </td>
                                </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<!-- The Modal -->
<div class="modal fade" id="dict-history-dialog">
    <div class="modal-dialog modal-xl modal-dialog-scrollable">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title" id="modal-header-info-dict">Modal Heading</h4>
            </div>

            <!-- Modal body -->
            <div class="modal-body" id="modal-body-info">

            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $("#history").DataTable({
            "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
        });
    });

    function clickDetail(elem) {
        let nameFile = $(elem).closest("tr").find(".name-dict").text();
        let path = nameFile + ".json";
        $.get('<c:url  value="../../file/dict/"/>' + path, function (data) {
            let index = 0;
            let body = $("#modal-body-info");
            body.empty();
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

            body.html(d);

            $("#table-dict").DataTable({
                "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
            });
            $("#modal-header-info-dict").text(nameFile);
            $("#dict-history-dialog").modal('show');
        });

    }

    function infoHistory(elem, defaul) {
        let body = $("#modal-body-info");
        $("#overlay").css({"display": "block"});

        body.empty();
        let nameFile = $(elem).closest("tr").find(".name-dict").text();
        let path = defaul + ".json";
        let path1 = nameFile + ".json";
        $("#modal-header-info-dict").text(nameFile);


        $.get('<c:url  value="../../file/dict/"/>' + path, function (d) {
            $.get('<c:url  value="../../file/dict/"/>' + path1, function (d1) {
                let data1 = '';
                let data2 = '';
                let i1 = Object.keys(d).length;
                let i2 = Object.keys(d1).length;
                let l = i1 > i2 ? i2 : i1;

                for (let i = 0; i < l; i++) {

                    let k1 = Object.keys(d)[i];
                    let v1 = d[k1];

                    let k2 = Object.keys(d1)[i];
                    let v2 = d1[k2];

                    if (k1 === k2 && v1 === v2) {
                        data1 = data1 + '<li style="color: #0c0c0c">' + (i + 1) + " " + k1 + " : " + v1 + '</li>';
                        data2 = data2 + '<li style="color: #0c0c0c">' + (i + 1) + " " + k2 + " : " + v2 + '</li>';
                    } else {
                        data1 = data1 + '<li style="color: #0c0c0c">' + (i + 1) + " " + k1 + " : " + v1 + '</li>';
                        data2 = data2 + '<li style="color: red">' + (i + 1) + " " + k2 + " : " + v2 + '</li>';
                    }
                }

                if (i2 > l) {
                    for (let m = l; m < i2 - l; m++) {
                        let k2 = Object.keys(d1)[m];
                        let v2 = d1[k2];
                        data2 = data2 + '<li style="color: #0ea432">' + (m + 1) + " " + k2 + " : " + v2 + '</li>';
                    }
                } else if (i1 > l) {
                    for (let n = l; n < i1 - l; n++) {
                        let k1 = Object.keys(d)[n];
                        let v1 = d[k1];
                        data1 = data1 + '<li style="color: #0ea432">' + (n + 1) + " " + k1 + " : " + v1 + '</li>';
                    }
                }


                let script = '<div class="row">\n' +
                    '                    <div class="col-6">\n' +
                    '                        <ul>\n' + data1 +
                    '                        </ul>\n' +
                    '                    </div>\n' +
                    '\n' +
                    '                    <div class="col-6" style=" border-left: 1px solid grey;">\n' +
                    '                        <ul>\n' + data2 +
                    '                        </ul>\n' +
                    '                    </div>\n' +
                    '                </div>';

                body.html(script);
                $("#overlay").css({"display": "none"});
                $("#dict-history-dialog").modal('show');
            });
        });
    }

</script>

<script src="<c:url value='/template/js/log/dict-history.js'/>"></script>

</body>
</html>
