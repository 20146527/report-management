<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="saveReport" value="/report-create.html">
    <c:param name="step" value="3"/>
</c:url>
<html>
<head>
    <title>Danh sách người dùng</title>
</head>
<body>
<link href="<c:url value='/template/css/lib/tagify/tagify.css'/>" rel="stylesheet"/>
<script src="<c:url value='/template/js/lib/tagify/tagify.js'/>"></script>
<script src="<c:url value='/template/js/lib/tagify/jQuery.tagify.min.js'/>"></script>

<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item">Biên bản</li>
            <li class="breadcrumb-item active">Tạo biên bản</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->

<!-- Container fluid  -->
<div class="container-fluid">
    <!-- Start Page Content -->
    <div class="row">
        <div class="col-12">

            <div class="card">
                <div class="card-body">
                    <div class="card-title">
                        <h3 class="text-center">Bước 3/3: Chỉnh sửa biên bản</h3>
                        <hr>
                        <%--alert--%>
                        <c:if test="${not empty messageResponse}">
                            <div class="alert alert-${alert} alert-dismissible fade show">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <strong>${messageResponse}</strong>
                            </div>
                        </c:if>
                        <%--alert--%>
                        <form action="${saveReport}" method="post" id="editReportForm">
                            <div class="form-group">
                                <label for="name" class="control-label">Tên biên bản</label>
                                <input type="text" class="form-control" id="name" name="pojo.name"
                                       placeholder="Tên biên bản" value="${report.pojo.name}">
                            </div>
                            <div class="form-group">
                                <label for="tag" class="control-label">Thẻ phân loại (Tag)</label>
                                <div class="row">
                                    <div class="col-11">
                                        <input type="text" id="tag" name="pojo.tag"
                                               placeholder="Nhập vào thẻ phân loại">
                                    </div>
                                    <div class="col-1">
                                        <button type="button" name="submit" class="btn btn-danger tags--removeAllBtn vertical-center"
                                                data-tooltip="Xóa hết tag">
                                            <i class="fa fa-times"></i>&nbsp;
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" name="sessionId" value="${sessionId}">
                            <input type="hidden" name="templateId" value="1">
                            <input type="hidden" name="recordId" value="${recordId}">
                            <input type="hidden" name="stenoId" value="1">
                            <input type="hidden" name="pojo.status" value="0">

                            <textarea id="full-featured" name="pojo.content">
                                ${report.pojo.content}
                            </textarea>
                            <button type="submit" name="submit" class="btn btn-info">
                                <i class="fa fa-save"></i>&nbsp;
                                <span>Lưu lại</span>
                            </button>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog"></div>

<script>
    document.title = "Tạo biên bản";
    let report = ${data_report_insert};

    $(document).ready(function () {
        //$('#myModal').modal({backdrop: 'static', keyboard: false});
        //insertData()
    });

    function insertData() {
        var editor = tinymce.get('full-featured');
        insertDataInElement(editor, "template1");
        insertDataInElement(editor, "template2");
        insertDataInElement(editor, "template3");
        insertDataInElement(editor, "template4");
        insertDataInElement(editor, "template5");
        insertDataInElement(editor, "template6");
        insertDataInElement(editor, "template7");

    }

    function insertDataInElement(editor, key) {
        var listElement = $(editor.getBody()).find('.' + key);
        $.each(listElement, function (i, item) {
            var element = listElement[i];
            $(element).text(report[key]);
        });
    }

</script>
<script>
    //Auto complete Tag
    (function () {
        var tagList = ${tags};

        var input = document.querySelector('input[id=tag]'),

            // init Tagify script on the above inputs
            tagify = new Tagify(input, {
                // after 2 characters typed, all matching values from this list will be suggested in a dropdown
                // whitelist: ["A# .NET", "Nghia", "Hung", "A+", "A++", "ABAP", "ABC", "ABC ALGOL", "ABSET", "ABSYS", "ACC", "Accent", "Ace DASL", "ACL2", "Avicsoft", "ACT-III", "Action!", "ActionScript", "Ada", "Adenine", "Agda", "Agilent VEE", "Agora", "AIMMS", "Alef", "ALF", "ALGOL 58", "ALGOL 60", "ALGOL 68", "ALGOL W", "Alice", "Alma-0", "AmbientTalk", "Amiga E", "AMOS", "AMPL", "Apex (Salesforce.com)", "APL", "AppleScript", "Arc", "ARexx", "Argus", "AspectJ", "Assembly language", "ATS", "Ateji PX", "AutoHotkey", "Autocoder", "AutoIt", "AutoLISP / Visual LISP", "Averest", "AWK", "Axum", "Active Server Pages", "ASP.NET", "B", "Babbage", "Bash", "BASIC", "bc", "BCPL", "BeanShell", "Batch (Windows/Dos)", "Bertrand", "BETA", "Bigwig", "Bistro", "BitC", "BLISS", "Blockly", "BlooP", "Blue", "Boo", "Boomerang", "Bourne shell (including bash and ksh)", "BREW", "BPEL", "B", "C--", "C++ – ISO/IEC 14882", "C# – ISO/IEC 23270", "C/AL", "Caché ObjectScript", "C Shell", "Caml", "Cayenne", "CDuce", "Cecil", "Cesil", "Céu", "Ceylon", "CFEngine", "CFML", "Cg", "Ch", "Chapel", "Charity", "Charm", "Chef", "CHILL", "CHIP-8", "chomski", "ChucK", "CICS", "Cilk", "Citrine (programming language)", "CL (IBM)", "Claire", "Clarion", "Clean", "Clipper", "CLIPS", "CLIST", "Clojure", "CLU", "CMS-2", "COBOL – ISO/IEC 1989", "CobolScript – COBOL Scripting language", "Cobra", "CODE", "CoffeeScript", "ColdFusion", "COMAL", "Combined Programming Language (CPL)", "COMIT", "Common Intermediate Language (CIL)", "Common Lisp (also known as CL)", "COMPASS", "Component Pascal", "Constraint Handling Rules (CHR)", "COMTRAN", "Converge", "Cool", "Coq", "Coral 66", "Corn", "CorVision", "COWSEL", "CPL", "CPL", "Cryptol", "csh", "Csound", "CSP", "CUDA", "Curl", "Curry", "Cybil", "Cyclone", "Cython", "Java", "Javascript", "M2001", "M4", "M#", "Machine code", "MAD (Michigan Algorithm Decoder)", "MAD/I", "Magik", "Magma", "make", "Maple", "MAPPER now part of BIS", "MARK-IV now VISION:BUILDER", "Mary", "MASM Microsoft Assembly x86", "MATH-MATIC", "Mathematica", "MATLAB", "Maxima (see also Macsyma)", "Max (Max Msp – Graphical Programming Environment)", "Maya (MEL)", "MDL", "Mercury", "Mesa", "Metafont", "Microcode", "MicroScript", "MIIS", "Milk (programming language)", "MIMIC", "Mirah", "Miranda", "MIVA Script", "ML", "Model 204", "Modelica", "Modula", "Modula-2", "Modula-3", "Mohol", "MOO", "Mortran", "Mouse", "MPD", "Mathcad", "MSIL – deprecated name for CIL", "MSL", "MUMPS", "Mystic Programming L"]
                whitelist: tagList

            })


// "remove all tags" button event listener
        document.querySelector('.tags--removeAllBtn')
            .addEventListener('click', tagify.removeAllTags.bind(tagify))

// Chainable event listeners
        tagify.on('add', onAddTag)
            .on('remove', onRemoveTag)
            .on('input', onInput)
            .on('edit', onTagEdit)
            .on('invalid', onInvalidTag)
            .on('click', onTagClick)
            .on('dropdown:show', onDropdownShow)
            .on('dropdown:hide', onDropdownHide)
            .on('dropdown:select', onDropdownSelect)

// tag added callback
        function onAddTag(e) {
            //console.log("onAddTag: ", e.detail);
            //console.log("original input value: ", input.value)
            tagify.off('add', onAddTag) // exmaple of removing a custom Tagify event
        }

// tag remvoed callback
        function onRemoveTag(e) {
            //console.log(e.detail);
            //console.log("tagify instance value:", tagify.value)
        }

// on character(s) added/removed (user is typing/deleting)
        function onInput(e) {
            //console.log(e.detail);
            //console.log("onInput: ", e.detail);
        }

        function onTagEdit(e) {
            //console.log("onTagEdit: ", e.detail);
        }

// invalid tag added callback
        function onInvalidTag(e) {
            //console.log("onInvalidTag: ", e.detail);
        }

// invalid tag added callback
        function onTagClick(e) {
            //console.log(e.detail);
            //console.log("onTagClick: ", e.detail);
        }

        function onDropdownShow(e) {
            //console.log("onDropdownShow: ", e.detail)
        }

        function onDropdownHide(e) {
            //console.log("onDropdownHide: ", e.detail)
        }

        function onDropdownSelect(e) {
            //console.log("onDropdownSelect: ", e.detail)
        }

    })()
</script>

<%--Tiny Editor--%>
<script src="<c:url value='/template/js/tinymce/tinymce.js'/>"></script>
<script src="<c:url value='/template/js/tinymce/init.js'/>"></script>

</body>
</html>
