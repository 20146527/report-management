<%--
  Created by IntelliJ IDEA.
  User: HungPhan
  Date: 7/19/2019
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
                        <h3 class="text-center">Lịch sử bộ từ điển cá nhân</h3>
                    </div>
                    <hr>

                    <div class="col-12">
                        <div class="table-responsive">
                            <table id="history" class="table table-striped table-bordered" style="width:100%">

                                <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Thời gian</th>
                                    <th>Trạng thái</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>

                                <tbody>
                                <%--@elvariable id="list" type="java.util.ArrayList"--%>
                                <c:forEach items="${list}" varStatus="loop" var="tempt">
                                <tr>
                                    <td class="index" id="${tempt.getNameDict()}">${tempt.getIndex()}</td>
                                    <td class="name-dict">${tempt.getTimeUpdate()}</td>
                                    <td class="status">
                                        <c:if test="${tempt.getIndex() == 1}">
                                            Hoạt động
                                        </c:if>
                                    </td>

                                    <td>

                                        <button id="detail-dict" class="btn btn-success"
                                                onclick="infoHistory(this)"
                                                data-tooltip="Chi tiết">
                                            <i class="fa fa-info" aria-hidden="true"></i>
                                        </button>

                                        <c:if test="${tempt.getIndex() != 1}">
                                            <button id="detail-dict" class="btn btn-warning"
                                                    onclick="restorePer('${tempt.getNameDict()}')"
                                                    data-tooltip="Khôi phục">
                                                <i class="fa fa-history" aria-hidden="true"></i>
                                            </button>
                                        </c:if>

                                        <button id="download" class="btn btn-info"
                                                onclick="downloadPer('${tempt.getNameDict()}')"
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
                <div class="table-responsive" id="table-responsive-person-dict">

                </div>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeModal()">Close
                </button>
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


    function infoHistory(elem) {
        let nameFile = $(elem).closest("tr").find(".index").attr("id");
        let s = $(elem).closest("tr").find(".status").text();
        let t = '<table id="table-person-dict" class="table table-striped table-bordered" style="width:100%">\n' +
            '\n' +
            '                    </table>';
        let display_table = $("#table-responsive-person-dict");
        display_table.empty();
        display_table.append(t);
        let h = '';
        if (s.trim()) {
            h = ' <thead>\n' +
                '                        <tr id="header">\n' +
                '                            <th>STT</th>\n' +
                '                            <th>Mã Steno</th>\n' +
                '                            <th>Âm tiết/Cụm từ/Câu</th>\n' +
                '                            <th>Ngày sửa</th>\n' +
                '                            <th>Trạng thái</th>\n' +
                '                            <th>Hành động</th>\n' +
                '                        </tr>\n' +
                '                        </thead>\n' +
                '\n' +
                '                        <tbody id="tb-body">'
        } else {
            h = ' <thead>\n' +
                '                        <tr id="header">\n' +
                '                            <th>STT</th>\n' +
                '                            <th>Mã Steno</th>\n' +
                '                            <th>Âm tiết/Cụm từ/Câu</th>\n' +
                '                            <th>Ngày sửa</th>\n' +
                '                            <th>Trạng thái</th>\n' +
                '                        </tr>\n' +
                '                        </thead>\n' +
                '\n' +
                '                        <tbody id="tb-body">';
        }
        let table = $("#table-person-dict");
        table.empty();
        table.append(h);
        let header = $("#modal-header-info-dict");
        header.text(nameFile.substring(0, nameFile.length - 5));
        let modal = $("#dict-history-dialog");

        <%--@elvariable id="user" type="java.lang.String"--%>
        $.get('<c:url  value="../../file/dict/person/${user}/version/"/>' + nameFile, function (d) {
            let dictObj = d.dict;
            let length = dictObj.length;
            for (let i = 0; i < length; i++) {
                let obj = dictObj[i];
                let id = obj.idCode;
                let modeDate = obj.modeDate;
                let status = obj.status;
                let key = obj.key;
                let value = obj.value;
                let h1 = '<td class="index" id="' + id + '">' + (i + 1) + '</td>';
                let h2 = '<td class="code-steno">' + key + '</td>';
                let h3 = '<td class="word">' + value + '</td>';
                let h4 = '<td class="mod-date-dict">' + modeDate + '</td>';
                let h5 = '';
                let h6 = '';
                if (status === 0) {
                    h5 = '<td>Hoạt động</td>';
                    h6 = ' <button id="bt-edit-dict" class="btn btn-success" data-tooltip="Chi tiết"\n' +
                        '                                        onclick="info_dict(this)">\n' +
                        '                                    <i class="fa fa-info" aria-hidden="true"></i>\n' +
                        '                                </button>';
                } else if (status === 1) {
                    h5 = '<td class="status">Đã xóa</td>';

                    h6 = '<button id="restore-one-per-dict" class="btn btn-warning mr-1"\n' +
                        '                                                    onclick="restoreOnePer(this, ' + id + ')"\n' +
                        '                                                    data-tooltip="Khôi phục">\n' +
                        '                                                <i class="fa fa-history" aria-hidden="true"></i>\n' +
                        '                                            </button>';

                    h6 = h6 + ' <button id="bt-edit-dict" class="btn btn-success " data-tooltip="Chi tiết"\n' +
                        '                                        onclick="info_dict(this)">\n' +
                        '                                    <i class="fa fa-info" aria-hidden="true"></i>\n' +
                        '                                </button>';

                } else {
                    h5 = '<td>Vô hiệu hóa</td>';
                    h6 = ' <button id="bt-edit-dict" class="btn btn-success" data-tooltip="Chi tiết"\n' +
                        '                                        onclick="info_dict(this)">\n' +
                        '                                    <i class="fa fa-info" aria-hidden="true"></i>\n' +
                        '                                </button>';
                }
                let html = '';
                if (s.trim()) {
                    html = '<tr>' + h1 + h2 + h3 + h4 + h5 + '<td>' + h6 + '</td>' + '</tr>';
                } else {
                    html = '<tr>' + h1 + h2 + h3 + h4 + h5 + '</tr>';
                }

                $("#tb-body").append(html);
            }
            table.DataTable({
                "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
            });
            modal.modal("show");
        });

    }

    function info_dict(elem) {
        let id = $(elem).closest("tr").find(".index").attr("id");
        let t = '<table id="table-person-dict" class="table table-striped table-bordered" style="width:100%">\n' +
            '\n' +
            '                    </table>';
        let display_table = $("#table-responsive-person-dict");
        display_table.empty();
        display_table.append(t);
        let overlay = $("#overlay");
        overlay.css({"display": "block"});
        $.ajax({
            url: "/query.html?type=findDictByID",
            type: "post",
            data: {
                id: id
            },
            success: function (response) {
                let header = $("#modal-header-info-dict");
                header.text("Lịch sử");
                overlay.css({"display": "none"});
                let h = ' <thead>\n' +
                    '                        <tr id="header">\n' +
                    '                            <th>STT</th>\n' +
                    '                            <th>Mã Steno</th>\n' +
                    '                            <th>Âm tiết/Cụm từ/Câu</th>\n' +
                    '                            <th>Ngày sửa</th>\n' +
                    '                            <th>Trạng thái</th>\n' +
                    '                            <th>Hành động</th>\n' +
                    '                        </tr>\n' +
                    '                        </thead>\n' +
                    '\n' +
                    '                        <tbody id="tb-body">';

                let table = $("#table-person-dict");
                table.empty();
                table.append(h);
                let arr = JSON.parse(response);
                for (let i = 0; i < arr.length; i++) {
                    let obj = arr[i];
                    console.log(obj);
                    let id = obj.idCode;
                    let modeDate = obj.modUpdate;
                    let status = obj.status;
                    let key = obj.codeSteno;
                    let value = obj.word;
                    let h1 = '<td class="index" id="' + id + '">' + (i + 1) + '</td>';
                    let h2 = '<td class="code-steno">' + key + '</td>';
                    let h3 = '<td class="word">' + value + '</td>';
                    let h4 = '<td class="mod-date-dict">' + modeDate + '</td>';
                    let h5 = '';
                    let h6 = '';
                    if (status === 0) {
                        h5 = '<td>Hoạt động</td>';

                    } else if (status === 1) {
                        h5 = '<td>Đã xóa</td>';

                    } else {
                        h5 = '<td>Vô hiệu hóa</td>';
                    }

                    if (i === 0) {
                        h6 = 'Đang sử dụng';
                    } else {
                        h6 = '<button id="detail-dict" class="btn btn-warning mr-1"\n' +
                            '                                                    onclick="restoreHistoryOnePer(this, ' + status + ', ' + id + ')"\n' +
                            '                                                    data-tooltip="Khôi phục">\n' +
                            '                                                <i class="fa fa-history" aria-hidden="true"></i>\n' +
                            '                                            </button>';
                    }
                    let html = '<tr>' + h1 + h2 + h3 + h4 + h5 + '<td>' + h6 + '</td>' + '</tr>';

                    $("#tb-body").append(html);
                }
                table.DataTable({
                    "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
                });
            }
        });
    }


</script>

<script src="<c:url value='/template/js/log/dict-person-history.js'/>"></script>

</body>
</html>