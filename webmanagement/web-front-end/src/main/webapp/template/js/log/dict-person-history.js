function downloadPer(name) {
    window.location.href = "/dict-person-history.html?type=download&nameFile=" + name;
}

function restorePer(name) {
    window.location.href = "/dict-person-history.html?type=restore&nameFile=" + name;
}

function restoreOnePer(elem, id) {
    let status = $(elem).closest("tr").find(".status");
    let restore = $(elem).closest("tr").find("#restore-one-per-dict");
    $.ajax({
        url: "/query.html?type=restoreByID",
        type: "post",
        data: {
            id: id
        },
        success: function () {
            status.text("Hoạt động");
            restore.remove();
        }
    })
}


function restoreHistoryOnePer(elem, status, id) {
    $.ajax({
        url: "/query.html?type=restoreOnePer",
        type: "post",
        data: {
            id: id,
            status: status
        },
        success: function () {
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
                    table.DataTable();
                }
            })
        }
    })
}

function closeModal() {
    location.reload(true);
}