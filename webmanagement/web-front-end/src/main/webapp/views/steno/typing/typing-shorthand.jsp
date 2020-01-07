<%--@elvariable id="dict_person" type="java.lang.String"--%>
<%--@elvariable id="dict_default" type="java.lang.String"--%>
<%--
  Created by IntelliJ IDEA.
  User: HungPhan
  Date: 9/6/2019
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<script>
    <c:if test="${not empty save}">
    alert("Lưu file thành công");
    window.location = '/typing-shorthand.html';
    </c:if>
</script>


<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item active">Gõ tốc ký</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->

<div class="container-fluid">

    <div class="col-12">

        <div class="card">
            <div class="card-body">

                <ul class="nav nav-pills m-t-30 m-b-30">
                    <li class="nav-item" id="li-tab-1" onclick="fnCheckShorthand()"><a href="#block-editor"
                                                                                       class="nav-link active"
                                                                                       id="tab-1"
                                                                                       data-toggle="tab"
                                                                                       aria-expanded="false">Trình soạn
                        thảo</a></li>

                    <li class="nav-item" id="li-tab-2" onclick="fnCheckShorthand()"><a href="#tab-edit-content"
                                                                                       class="nav-link"
                                                                                       id="tab-2"
                                                                                       data-toggle="tab"
                                                                                       aria-expanded="false">Nội dung đã
                        lưu</a></li>

                </ul>

                <div class="tab-content br-n pn">

                    <div id="block-editor" class="tab-pane active">

                        <div class="form-group" id="select-option-typing">
                            <label for="changeTyping">Chọn chế độ gõ</label>
                            <select class="form-control" id="changeTyping">
                                <option id="select_one">Gõ tiếng việt</option>
                                <option id="select_two">Tốc ký hiển thị tức thời</option>
                            </select>
                        </div>

                        <h5 class="text-center mt-3 mb-3" id="name-typing">
                            Chế độ gõ tiếng Việt
                        </h5>

                        <div class="parent">
                            <div class="row btn">
                                <button class="btn btn-info" onclick="triggerUndo()"><i class="fa fa-undo"
                                                                                        aria-hidden="true"></i> Undo
                                </button>
                                <button class="btn btn-info" onclick="triggerRedo()"><i class="fa fa-repeat"
                                                                                        aria-hidden="true"></i> Redo
                                </button>

                                <label class="switch control-label" for="insMode" data-tooltip="Bật/Tắt chế độ chèn từ">
                                    <input type="checkbox" name="insMode" id="insMode"
                                           onclick="cB(this.checked, $('#txtEditor'))">
                                    <span class="slider round"></span>
                                </label>
                            </div>
                            <label for="txtEditor"></label>
                            <textarea class="form-control z-depth-1" rows="10" id="txtEditor"
                                      onkeydown="checkArrows(event.keyCode, this)"></textarea>


                        </div>


                        <button class="btn btn-block btn-success mt-3" id="complete-editor" onclick="fnOnDone()">
                            Xong
                        </button>
                    </div>


                    <div class="content tab-pane" id="tab-edit-content">
                        <hr>
                        <div class="row">
                            <div class="col-1">
                                <label class="switch control-label vertical-center" for="linkMeeting"
                                       data-tooltip="Liên kết cuộc họp">
                                    <input type="checkbox" name="linkMeeting" id="linkMeeting"
                                           onchange="onChangeLinkMeeting()">
                                    <span class="slider round"></span>
                                </label>
                            </div>
                            <div class="col-5">
                                <select name="metting" id="metting" class="form-control">
                                    <option value="0">Chọn cuộc họp...</option>
                                    <c:forEach items="${meetingList}" varStatus="loop" var="op">
                                        <option value="${op.meetingId}">${op.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-6">
                                <select name="session" id="session" class="form-control">
                                    <option value="0">Chọn phiên...</option>

                                </select>
                            </div>
                        </div>
                        <hr>
                        <div class="data-content" id="data-content"></div>

                        <button class="btn btn-block btn-success mt-3" onclick="fnOnSave('${name}')"><i
                                class="fa fa-floppy-o"
                                aria-hidden="true"></i> Lưu lại
                        </button>

                    </div>

                </div>

            </div>
        </div>
    </div>
</div>

<script>
    const binarySteno = '${binarySteno}';
</script>
<script src="<c:url value="/template/js/steno/typing/changeView.js"/>"></script>
<script src="<c:url value="/template/js/steno/typing/autocompleteTextarea.js"/>"></script>
<script>
    $(function () {
        $('#data-content').froalaEditor({
            height: 300,
        });
    });


    $(document).ready(function () {
        $('#session').prop('disabled', true);
        meeting();
        session();
        onChangeLinkMeeting();
    });

    function meeting() {
        $('#metting').on('change', function (e) {
            var optionSelected = $("option:selected", this);
            var valueSelected = optionSelected.val();
            var url = "/ajax-create-report.html?step=1";
            if (valueSelected !== 0) {
                $('#meetingId').val(valueSelected);
                $.ajax({
                    url: url,
                    type: 'get',
                    data: {
                        meetingId: valueSelected
                    },
                    success: function (res) {
                        var listSession = jQuery.parseJSON(res);
                        if (listSession.length !== 0) {
                            let session = $('#session');
                            session.prop('disabled', false);
                            $('#file').prop('disabled', true);
                            session.empty().append(new Option("Chọn phiên...", 0));
                            $.each(listSession, function (i, item) {
                                $("#session").append(new Option(item.name, item.sessionId));
                            });
                        } else {
                            $('#session').prop('disabled', true);
                            $('#file').prop('disabled', true);
                        }
                    },
                    error: function (res) {
                        console.log(res);
                    }
                });
            }
        })
    }

    function session() {
        $('#session').on('change', function (e) {
            var optionSelected = $("option:selected", this);
            var valueSelected = optionSelected.val();
            var url = "/ajax-create-report.html?step=1";
            if (valueSelected !== 0) {
                $('#sessionId').val(valueSelected);
            }
        })
    }

    function onChangeLinkMeeting() {
        if ($('#linkMeeting').is(":checked")) {
            $('#metting').prop('disabled', false);
            //$('#linkMeeting').prop("checked", true);
        } else {
            console.log("Da bo check");
            $('#metting').prop('disabled', true);
            $('#session').prop('disabled', true);
            $('#sessionId').val("1");
            //$('#linkMeeting').prop("checked", true);
        }
    }
</script>


<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Nhập tên muốn lưu</h4>
            </div>

            <form id="form-name-file" class="form-valide" method="post">
                <!-- Modal body -->
                <div class="modal-body">
                    <div class="form-validation">
                        <div class="form-group">
                            <label for="name-file">Nhập tên file</label>
                            <div>
                                <input type="text" class="form-control" id="name-file" name="file-name"
                                       <c:if test="${not empty fileNameStenoStructure}">disabled</c:if>
                                       placeholder="Nhập tên file">
                            </div>

                            <input type="text" hidden name="name-file" id="data-name">
                            <input type="text" hidden name="data-typing" id="data-typing">
                            <input type="hidden" name="sessionId" id="sessionId" value="1">
                            <input type="hidden" name="meetingId" id="meetingId" value="1">
                        </div>


                    </div>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-info">Lưu lại</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>
            </form>

        </div>
    </div>
</div>

<script>

    var form_validation = function () {
        var e = function () {
            $.validator.addMethod("regexp", function (value, element) {
                return this.optional(element) || !/[\\/"?*<>|:]/.test(value);
            }, 'Kiểm tra lại tên');

            $(".form-valide").validate({
                ignore: [],
                errorClass: "invalid-feedback animated fadeInDown",
                errorElement: "div",
                errorPlacement: function (e, a) {
                    $(a).parents(".form-group > div").append(e)
                },
                highlight: function (e) {
                    $(e).closest(".form-group").removeClass("is-invalid").addClass("is-invalid")
                },
                success: function (e) {
                    $(e).closest(".form-group").removeClass("is-invalid"), $(e).remove()
                },
                rules: {
                    "file-name": {
                        required: true,
                        regexp: true
                    }
                },
                messages: {
                    "file-name": "Tên nhập vào không hợp lệ"
                }
            })
        };
        return {
            init: function () {
                e(), $(".js-select2").on("change", function () {
                    $(this).valid()
                })
            }
        }
    }();
    $(function () {
        form_validation.init();
    });


</script>

<div id="input-person" data-path="${dict_person}"></div>

</body>
</html>
