<%@ page import="ubnd.core.dto.PermissionRoleDto" %>
<%@ page import="ubnd.core.dto.RoleDto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="edPermissionUrl" value="/ajax-permission-edit.html">
    <c:param name="urlType" value="url_ed_permission"/>
</c:url>
<c:url var="addRoleUrl" value="/ajax-permission-edit.html">
    <c:param name="urlType" value="url_edit"/>
    <c:param name="listRoleSize" value="${listRoleSize}"/>
</c:url>
<%
    List<RoleDto> roleDtos = (List<RoleDto>) request.getAttribute("listRole");
    List<PermissionRoleDto> permissionRoleDtos = (List<PermissionRoleDto>) request.getAttribute("listPermission");
%>
<html>
<head>
    <title>Thông tin hệ thống</title>
</head>
<body>

<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item">Quản lý quyền</li>
            <li class="breadcrumb-item active">Danh sách</li>
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
                        <h3>Quản lý quyền</h3>
                        <hr>
                        <%--alert--%>
                        <c:if test="${not empty messageResponse}">
                            <div class="alert alert-${alert} alert-dismissible fade show">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <span>${messageResponse}</span>
                            </div>
                        </c:if>
                        <%--alert--%>
                        <!-- Nav tabs -->
                        <div class="vtabs " style="width: 100%">
                            <ul class="nav nav-tabs tabs-vertical" role="tablist">
                                <%
                                    for (int i = 0; i < roleDtos.size(); i++) {
                                        if (i == 0) {
                                %>
                                <li class="nav-item"><a class="nav-link active" data-toggle="tab"
                                                        href="#role<%=roleDtos.get(i).getRoleId()%>"
                                                        role="tab"><span class="hidden-sm-up"><i
                                        class="ti-home"></i></span> <span
                                        class="hidden-xs-down"><%=roleDtos.get(i).getRoleName()%></span> </a>
                                </li>
                                <%
                                } else {
                                %>
                                <li class="nav-item"><a class="nav-link" data-toggle="tab"
                                                        href="#role<%=roleDtos.get(i).getRoleId()%>"
                                                        role="tab"><span class="hidden-sm-up"><i
                                        class="ti-home"></i></span> <span
                                        class="hidden-xs-down"><%=roleDtos.get(i).getRoleName()%></span> </a>
                                </li>
                                <%
                                        }
                                    }
                                %>
                            </ul>
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <%
                                    for (int i = 0; i < roleDtos.size(); i++) {
                                        if (i == 0) {
                                            int roleId = roleDtos.get(i).getRoleId();
                                            request.setAttribute("roleId", roleId);
                                %>
                                <div class="tab-pane active" id="role<%=roleId%>" role="tabpanel">
                                    <!-- Card Role Info -->
                                    <div class="card">
                                        <div class="card-body">
                                            <p>Thông tin quyền</p>
                                            <div class="table-responsive">
                                                <table class="table">
                                                    <thead>
                                                    <tr>
                                                        <th>Tên</th>
                                                        <th>Mô tả</th>
                                                        <th>Hành động</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td><%=roleDtos.get(i).getRoleName()%>
                                                        </td>
                                                        <td><%=roleDtos.get(i).getRoleDipcription()%>
                                                        </td>
                                                        <td>
                                                            <button type="button" name="submit" class="btn btn-danger"
                                                                    sc-url="" onclick=""
                                                                    data-tooltip="Xóa quyền">
                                                                <i class="fa fa-times"></i>&nbsp;
                                                            </button>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- End Card Role Info -->
                                    <!-- List Permission -->
                                    <p>Danh sách quyền</p>
                                    <div class="table-responsive">
                                        <table class="table table-hover ">
                                            <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Loại quyền</th>
                                                <th>Trạng thái</th>
                                                <th>Hành động</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <%
                                                for (int j = 0; j < permissionRoleDtos.size(); j++) {
                                                    if (permissionRoleDtos.get(j).getRoleDto().getRoleId() == roleDtos.get(i).getRoleId()) {
                                                        int permissionRoleId = permissionRoleDtos.get(j).getPermissionRoleId();
                                                        request.setAttribute("permissionRoleId", permissionRoleId);
                                                        if (permissionRoleDtos.get(j).getStatus() == 0) {
                                            %>
                                            <tr>
                                                <td><%=j%>
                                                </td>
                                                <td><%=permissionRoleDtos.get(j).getFunctionDto().getFunctionName()%>
                                                </td>
                                                <td><span class="badge badge-success">Đã cấp quyền</span></td>
                                                <td>
                                                    <button type="button" name="submit"
                                                            class="btn btn-default btn-outline"
                                                            permissionRoleId="${permissionRoleId}" edPermission="1"
                                                            onclick="permissionED(this)" data-tooltip="Bỏ cấp quyền">
                                                        <i class="fa fa-2x fa-toggle-on"></i>&nbsp;
                                                    </button>
                                                </td>
                                            </tr>

                                            <%
                                            } else {
                                            %>
                                            <tr>
                                                <td><%=j%>
                                                </td>
                                                <td><%=permissionRoleDtos.get(j).getFunctionDto().getFunctionName()%>
                                                </td>
                                                <td><span class="badge badge-danger">Chưa cấp quyền</span></td>
                                                <td>
                                                    <button type="button" name="submit"
                                                            class="btn btn-default btn-outline"
                                                            permissionRoleId="${permissionRoleId}" edPermission="0"
                                                            onclick="permissionED(this)" data-tooltip="Cấp quyền">
                                                        <i class="fa fa-2x fa-toggle-off"></i>&nbsp;
                                                    </button>
                                                </td>
                                            </tr>
                                            <%
                                                        }
                                                    }
                                                }
                                            %>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- End List Permission -->
                                </div>
                                <%
                                } else {
                                    int roleId = roleDtos.get(i).getRoleId();
                                    request.setAttribute("roleId", roleId);
                                    request.setAttribute("roleName", roleDtos.get(i).getRoleName());
                                %>
                                <div class="tab-pane" id="role<%=roleId%>" role="tabpanel">
                                    <!-- Card Role Info -->
                                    <div class="card">
                                        <div class="card-body">
                                            <p>Thông tin quyền</p>
                                            <div class="table-responsive">
                                                <table class="table">
                                                    <thead>
                                                    <tr>
                                                        <th>Tên</th>
                                                        <th>Mô tả</th>
                                                        <th>Hành động</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <c:url var="deleteUrl" value="/ajax-permission-edit.html">
                                                            <c:param name="urlType" value="url_delete"/>
                                                            <c:param name="roleId" value="${roleId}"/>
                                                            <c:param name="roleName" value="${roleName}"/>
                                                        </c:url>
                                                        <td><%=roleDtos.get(i).getRoleName()%>
                                                        </td>
                                                        <td><%=roleDtos.get(i).getRoleDipcription()%>
                                                        </td>
                                                        <td>
                                                            <button type="button" name="submit" class="btn btn-danger"
                                                                    sc-url="${deleteUrl}" onclick="remove(this)"
                                                                    data-tooltip="Xóa quyền">
                                                                <i class="fa fa-times"></i>&nbsp;
                                                            </button>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- End Card Role Info -->
                                    <!-- List Permission -->
                                    <p>Danh sách quyền</p>
                                    <div class="table-responsive">
                                        <table class="table table-hover ">
                                            <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Loại quyền</th>
                                                <th>Trạng thái</th>
                                                <th>Hành động</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <%
                                                for (int j = 0; j < permissionRoleDtos.size(); j++) {
                                                    if (permissionRoleDtos.get(j).getRoleDto().getRoleId() == roleDtos.get(i).getRoleId()) {
                                                        int permissionRoleId = permissionRoleDtos.get(j).getPermissionRoleId();
                                                        request.setAttribute("permissionRoleId", permissionRoleId);
                                                        if (permissionRoleDtos.get(j).getStatus() == 0) {
                                            %>
                                            <tr>
                                                <td><%=j%>
                                                </td>
                                                <td><%=permissionRoleDtos.get(j).getFunctionDto().getFunctionName()%>
                                                </td>
                                                <td><span class="badge badge-success">Đã cấp quyền</span></td>
                                                <td>
                                                    <button type="button" name="submit"
                                                            class="btn btn-default btn-outline"
                                                            permissionRoleId="${permissionRoleId}" edPermission="1"
                                                            onclick="permissionED(this)" data-tooltip="Bỏ cấp quyền">
                                                        <i class="fa fa-2x fa-toggle-on"></i>&nbsp;
                                                    </button>
                                                </td>
                                            </tr>

                                            <%
                                            } else {
                                            %>
                                            <tr>
                                                <td><%=j%>
                                                </td>
                                                <td><%=permissionRoleDtos.get(j).getFunctionDto().getFunctionName()%>
                                                </td>
                                                <td><span class="badge badge-danger">Chưa cấp quyền</span></td>
                                                <td>
                                                    <button type="button" name="submit"
                                                            class="btn btn-default btn-outline"
                                                            permissionRoleId="${permissionRoleId}" edPermission="0"
                                                            onclick="permissionED(this)" data-tooltip="Cấp quyền">
                                                        <i class="fa fa-2x fa-toggle-off"></i>&nbsp;
                                                    </button>
                                                </td>
                                            </tr>
                                            <%
                                                        }
                                                    }
                                                }
                                            %>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- End List Permission -->
                                </div>
                                <%
                                        }
                                    }
                                %>
                            </div>
                        </div>
                        <!-- Nav tabs -->
                        <div class="m-t-10">
                            <button type="button" name="button" onclick="updateRole(this)"
                                    class="btn btn-outline-info">
                                <i class="fa fa-plus"></i>&nbsp;
                                <span>Thêm mới quyền</span>
                            </button>
                        </div>
                        <form action="" method="get" id="formUrl">
                            <input type="hidden" name="crudaction" id="crudaction"/>
                            <input type="hidden" name="urlType" id="urlType"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="modalCreateRole" role="dialog" data-backdrop="static" data-keyboard="false"></div>
<div class="modal fade" id="modalRemove" role="dialog" data-backdrop="static" data-keyboard="false"></div>

<script>
    document.title = "Quản lý quyền";
    $(document).ready(function () {
        //$('#myModal').modal({backdrop: 'static', keyboard: false});
    });

    //Open modal update or create Room
    function updateRole(btn) {
        var editUrl = $(btn).attr('sc-url');
        if (typeof editUrl == 'undefined') {
            editUrl = '${addRoleUrl}';
        }
        $('#modalCreateRole').load(editUrl, '', function () {
            $('#modalCreateRole').modal('toggle');
            //addOrEdit();
        });
    }

    //Enable or Disable Permission
    function permissionED(btn) {
        var permissionRoleId = $(btn).attr('permissionRoleId');
        var ed = $(btn).attr('edPermission');
        $.ajax({
            type: 'POST',
            url: '${edPermissionUrl}',
            data: {
                permissionRoleId: permissionRoleId,
                ed: ed
            },
            success: function (res) {
                if (res.trim() == "redirect_update") {
                    $('#crudaction').val('redirect_update');
                    $('#urlType').val('url_list');
                    $('#formUrl').submit();
                }
            },
            error: function (res) {
                console.log(res);
            }

        })
    }

    //remove role and permission
    function remove(btn) {
        var removeUrl = $(btn).attr('sc-url');
        $('#modalRemove').load(removeUrl, '', function () {
            $('#modalRemove').modal('toggle');
            removeEXE();
        });
    }

    function removeEXE() {
        $('#btnRemove').click(function () {
            $('#removeRoleForm').submit();
        });
        $('#removeRoleForm').submit(function (e) {
            e.preventDefault();
            $.ajax({
                type: $(this).attr('method'),
                url: $(this).attr('action'),
                data: $(this).serialize(),
                dataType: 'html',
                success: function (res) {
                    if (res.trim() == "redirect_delete") {
                        $('#crudaction').val('redirect_delete');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    } else if (res.trim() == "redirect_error") {
                        $('#crudaction').val('redirect_error');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            });
        });
    }
</script>

</body>
</html>
