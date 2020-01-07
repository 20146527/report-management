<%@ page import="ubnd.core.dto.RoomDto" %>
<%@ page import="java.util.List" %>
<%@ page import="ubnd.core.dto.EquipmentDto" %><%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="addEquipmentUrl" value="/ajax-equipment-edit.html">
    <c:param name="urlType" value="url_edit_equipment"/>
</c:url>
<c:url var="edEquipmentUrl" value="/manager-equipment.html">
    <c:param name="urlType" value="url_ed_equipment"/>
</c:url>
<c:url var="addRoomUrl" value="/ajax-equipment-edit.html">
    <c:param name="urlType" value="url_edit_room"/>
</c:url>
<c:url var="edRoomUrl" value="/manager-equipment.html">
    <c:param name="urlType" value="url_ed_room"/>
</c:url>
<c:url var="equipmentListUrl" value="/manager-equipment.html">
    <c:param name="urlType" value="url_list"/>
</c:url>
<%
    List<RoomDto> roomDtos = (List<RoomDto>) request.getAttribute("listRoom");
    List<EquipmentDto> equipmentDtos = (List<EquipmentDto>) request.getAttribute("listEquipment");
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
            <li class="breadcrumb-item">Quản lý người dùng</li>
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
                        <h3>Quản lý vật tư</h3>
                        <hr>
                        <%--alert--%>
                        <c:if test="${not empty messageResponse}">
                            <div class="alert alert-${alert} alert-dismissible fade show">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <span>${messageResponse}</span>
                            </div>
                        </c:if>
                        <%--alert--%>
                        <!-- Nav tabs -->
                        <div class="vtabs " style="width: 100%">
                            <ul class="nav nav-tabs tabs-vertical" role="tablist">
                                <%
                                    for (int i = 0; i < roomDtos.size(); i++) {
                                        if (i == 0) {
                                %>
                                <li class="nav-item"><a class="nav-link active" data-toggle="tab"
                                                        href="#room<%=roomDtos.get(i).getRoomId()%>"
                                                        role="tab"><span class="hidden-sm-up"><i
                                        class="ti-home"></i></span> <span
                                        class="hidden-xs-down"><%=roomDtos.get(i).getRoomName()%></span> </a></li>
                                <%
                                } else {
                                %>
                                <li class="nav-item"><a class="nav-link" data-toggle="tab"
                                                        href="#room<%=roomDtos.get(i).getRoomId()%>"
                                                        role="tab"><span class="hidden-sm-up"><i
                                        class="ti-user"></i></span> <span
                                        class="hidden-xs-down"><%=roomDtos.get(i).getRoomName()%></span></a></li>
                                <%
                                        }
                                    }
                                %>
                            </ul>
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <%
                                    for (int i = 0; i < roomDtos.size(); i++) {
                                        if (i == 0) {
                                            int roomId = roomDtos.get(i).getRoomId();
                                            request.setAttribute("roomId", roomId);
                                %>
                                <div class="tab-pane active" id="room<%=roomId%>" role="tabpanel">
                                    <div class="card">
                                        <div class="card-body">
                                            <p>Thông tin phòng họp</p>
                                            <div class="table-responsive">
                                                <table class="table">
                                                    <thead>
                                                    <tr>
                                                        <th>Tên</th>
                                                        <th>Mô tả</th>
                                                        <th>Trạng thái</th>
                                                        <th>Hành động</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <%--                            <c:forEach items="${infoMeeting}" var="info">--%>
                                                    <tr>
                                                        <c:url var="editRoomUrl" value="/ajax-equipment-edit.html">
                                                            <c:param name="urlType" value = "url_edit_room"/>
                                                            <c:param name="roomId" value = "${roomId}"/>
                                                        </c:url>
                                                        <td><%=roomDtos.get(i).getRoomName()%></td>
                                                        <td><%=roomDtos.get(i).getRoomDescription()%></td>
                                                        <%
                                                            if(roomDtos.get(i).getStatus() == 0){
                                                        %>
                                                        <td><span class="badge badge-success">Đang hoạt động</span></td>
                                                        <td>
                                                            <button type="button" name="submit" class="btn btn-default btn-outline"
                                                                    roomId="${roomId}" edRoom="1" onclick="roomED(this)"
                                                                    data-tooltip="Vô hiệu hóa phòng">
                                                                <i class="fa fa-2x fa-toggle-on"></i>&nbsp;
                                                            </button>
                                                            <button type="button" name="submit" class="btn btn-success"
                                                                    sc-url="${editRoomUrl}" onclick="updateRoom(this)"
                                                                    data-tooltip="Sửa">
                                                                <i class="fa fa-pencil-square-o"></i>&nbsp;
                                                            </button>
                                                        </td>
                                                        <%
                                                        } else {
                                                        %>
                                                        <td><span class="badge badge-danger">Đã vô hiệu hóa</span></td>
                                                        <td>
                                                            <button type="button" name="submit" class="btn btn-default btn-outline"
                                                                    roomId="${roomId}" edRoom="0" onclick="roomED(this)"
                                                                    data-tooltip="Kích hoạt phòng">
                                                                <i class="fa fa-2x fa-toggle-off"></i>&nbsp;
                                                            </button>
                                                            <button type="button" name="submit" class="btn btn-success"
                                                                    sc-url="${editRoomUrl}" onclick="updateRoom(this)"
                                                                    data-tooltip="Sửa">
                                                                <i class="fa fa-pencil-square-o"></i>&nbsp;
                                                            </button>
                                                        </td>
                                                        <%
                                                            }
                                                        %>
                                                    </tr>
                                                    <%--                            </c:forEach>--%>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <p>Danh sách thiết bị</p>
                                    <div class="m-b-5">
                                        <button type="button" name="button" class="btn btn-outline-info"
                                                onclick="updateEquipment(this)" roomId="${roomId}">
                                            <i class="fa fa-plus"></i>&nbsp;
                                            <span>Thêm mới thiết bị</span>
                                        </button>
                                    </div>
                                    <div class="table-responsive">
                                        <table class="table table-hover ">
                                            <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Tên thiết bị</th>
                                                <th>Trạng thái</th>
                                                <th>Hãng</th>
                                                <th>Mô tả</th>
                                                <th>Sử dụng từ</th>
                                                <th>Hành động</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <%
                                                for (int j = 0; j < equipmentDtos.size(); j++) {
                                                    if (equipmentDtos.get(j).getRoomDto().getRoomId() == roomDtos.get(i).getRoomId()) {
                                                        int equipmentId = equipmentDtos.get(j).getEquipmentId();
                                                        request.setAttribute("equipmentId", equipmentId);
                                            %>
                                            <c:url var="editEquipmentUrl" value="/ajax-equipment-edit.html">
                                                <c:param name="urlType" value="url_edit_equipment"/>
                                                <c:param name="pojo.equipmentId" value="${equipmentId}"/>
                                                <c:param name="roomId" value="${roomId}"/>
                                            </c:url>
                                            <%
                                                        if (equipmentDtos.get(j).getStatus() == 1) {
                                            %>
                                            <tr>
                                                <th scope="row"><%=j%></th>
                                                <td><%=equipmentDtos.get(j).getName()%></td>
                                                <td><span class="badge badge-success">Đang hoạt động</span></td>
                                                <td><%=equipmentDtos.get(j).getBrand()%></td>
                                                <td><%=equipmentDtos.get(j).getDescription()%></td>
                                                <td><%=equipmentDtos.get(j).getDayStart()%></td>
                                                <td>
                                                    <button type="button" name="submit" class="btn btn-default btn-outline"
                                                            equipmentId="${equipmentId}" edEquipment="0"
                                                            onclick="equipmentED(this)" data-tooltip="Tắt">
                                                        <i class="fa fa-2x fa-toggle-on"></i>&nbsp;
                                                    </button>
                                                    <button type="button" name="submit" class="btn btn-success" roomId="${roomId}"
                                                            sc-url="${editEquipmentUrl}" onclick="updateRoom(this)"
                                                            data-tooltip="Sửa">
                                                        <i class="fa fa-pencil-square-o"></i>&nbsp;
                                                    </button>

                                                </td>
                                            </tr>
                                            <%
                                            } else {
                                            %>
                                            <tr>
                                                <th scope="row"><%=j%></th>
                                                <td><%=equipmentDtos.get(j).getName()%></td>
                                                <td><span class="badge badge-danger">Đã tắt</span></td>
                                                <td><%=equipmentDtos.get(j).getBrand()%></td>
                                                <td><%=equipmentDtos.get(j).getDescription()%></td>
                                                <td><%=equipmentDtos.get(j).getDayStart()%></td>
                                                <td>
                                                    <button type="button" name="submit" class="btn btn-default btn-outline"
                                                            equipmentId="${equipmentId}" edEquipment="1"
                                                            onclick="equipmentED(this)" data-tooltip="Bật">
                                                        <i class="fa fa-2x fa-toggle-off"></i>&nbsp;
                                                    </button>
                                                    <button type="button" name="submit" class="btn btn-success" roomId="${roomId}"
                                                            sc-url="${editEquipmentUrl}" onclick="updateRoom(this)"
                                                            data-tooltip="Sửa">
                                                        <i class="fa fa-pencil-square-o"></i>&nbsp;
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

                                </div>
                                <%
                                } else {
                                    int roomId = roomDtos.get(i).getRoomId();
                                    request.setAttribute("roomId", roomId);
                                %>
                                <div class="tab-pane" id="room<%=roomId%>" role="tabpanel">
                                    <div class="card">
                                        <div class="card-body">
                                            <p>Thông tin phòng họp</p>
                                            <div class="table-responsive">
                                                <table class="table">
                                                    <thead>
                                                    <tr>
                                                        <th>Tên</th>
                                                        <th>Mô tả</th>
                                                        <th>Trạng thái</th>
                                                        <th>Hành động</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <%--                            <c:forEach items="${infoMeeting}" var="info">--%>
                                                    <tr>
                                                        <c:url var="edit2RoomUrl" value="/ajax-equipment-edit.html">
                                                            <c:param name="urlType" value="url_edit_room"/>
                                                            <c:param name="roomId" value="${roomId}"/>
                                                        </c:url>
                                                        <td><%=roomDtos.get(i).getRoomName()%></td>
                                                        <td><%=roomDtos.get(i).getRoomDescription()%></td>
                                                        <%
                                                            if(roomDtos.get(i).getStatus() == 0){
                                                        %>
                                                        <td><span class="badge badge-success">Đang hoạt động</span></td>
                                                        <td>
                                                            <button type="button" name="submit" class="btn btn-default btn-outline"
                                                                    roomId="${roomId}" edRoom="1" onclick="roomED(this)"
                                                                    data-tooltip="Vô hiệu hóa phòng">
                                                                <i class="fa fa-2x fa-toggle-on"></i>&nbsp;
                                                            </button>
                                                            <button type="button" name="submit" class="btn btn-success"
                                                                    sc-url="${edit2RoomUrl}" onclick="updateRoom(this)"
                                                                    data-tooltip="Sửa">
                                                                <i class="fa fa-pencil-square-o"></i>&nbsp;
                                                            </button>
                                                        </td>
                                                        <%
                                                        } else {
                                                        %>
                                                        <td><span class="badge badge-danger">Đã vô hiệu hóa</span></td>
                                                        <td>
                                                            <button type="button" name="submit" class="btn btn-default btn-outline"
                                                                    roomId="${roomId}" edRoom="0" onclick="roomED(this)"
                                                                    data-tooltip="Kích hoạt phòng">
                                                                <i class="fa fa-2x fa-toggle-off"></i>&nbsp;
                                                            </button>
                                                            <button type="button" name="submit" class="btn btn-success"
                                                                    sc-url="${edit2RoomUrl}" onclick="updateRoom(this)"
                                                                    data-tooltip="Sửa">
                                                                <i class="fa fa-pencil-square-o"></i>&nbsp;
                                                            </button>
                                                        </td>
                                                        <%
                                                            }
                                                        %>
                                                    </tr>
                                                    <%--                            </c:forEach>--%>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <p>Danh sách thiết bị</p>
                                    <div class="m-b-5">
                                        <button type="button" name="button" class="btn btn-outline-info"
                                                onclick="updateEquipment(this)" roomId="${roomId}">
                                            <i class="fa fa-plus"></i>&nbsp;
                                            <span>Thêm mới thiết bị</span>
                                        </button>
                                    </div>
                                    <div class="table-responsive">
                                        <table class="table table-hover ">
                                            <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Tên thiết bị</th>
                                                <th>Status</th>
                                                <th>Hãng</th>
                                                <th>Mô tả</th>
                                                <th>Sử dụng từ</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <%
                                                for (int j = 0; j < equipmentDtos.size(); j++) {
                                                    if (equipmentDtos.get(j).getRoomDto().getRoomId() == roomDtos.get(i).getRoomId()) {
                                                        int equipmentId = equipmentDtos.get(j).getEquipmentId();
                                                        request.setAttribute("equipmentId", equipmentId);
                                            %>
                                            <c:url var="edit2EquipmentUrl" value="/ajax-equipment-edit.html">
                                                <c:param name="urlType" value="url_edit_equipment"/>
                                                <c:param name="pojo.equipmentId" value="${equipmentId}"/>
                                                <c:param name="roomId" value="${roomId}"/>
                                            </c:url>
                                            <%
                                                        if (equipmentDtos.get(j).getStatus() == 1) {
                                            %>
                                            <tr>
                                                <th scope="row"><%=j%></th>
                                                <td><%=equipmentDtos.get(j).getName()%></td>
                                                <td><span class="badge badge-success">Đang hoạt động</span></td>
                                                <td><%=equipmentDtos.get(j).getBrand()%></td>
                                                <td><%=equipmentDtos.get(j).getDescription()%></td>
                                                <td><%=equipmentDtos.get(j).getDayStart()%></td>
                                                <td>
                                                    <button type="button" name="submit" class="btn btn-default btn-outline"
                                                            equipmentId="${equipmentId}" edEquipment="0"
                                                            onclick="equipmentED(this)" data-tooltip="Tắt">
                                                        <i class="fa fa-2x fa-toggle-on"></i>&nbsp;
                                                    </button>
                                                    <button type="button" name="submit" class="btn btn-success" roomId="${roomId}"
                                                            sc-url="${edit2EquipmentUrl}" onclick="updateEquipment(this)"
                                                            data-tooltip="Sửa">
                                                        <i class="fa fa-pencil-square-o"></i>&nbsp;
                                                    </button>
                                                </td>
                                            </tr>
                                            <%
                                            } else {
                                            %>
                                            <tr>
                                                <th scope="row"><%=j%></th>
                                                <td><%=equipmentDtos.get(j).getName()%></td>
                                                <td><span class="badge badge-danger">Đã tắt</span></td>
                                                <td><%=equipmentDtos.get(j).getBrand()%></td>
                                                <td><%=equipmentDtos.get(j).getDescription()%></td>
                                                <td><%=equipmentDtos.get(j).getDayStart()%></td>
                                                <td>
                                                    <button type="button" name="submit" class="btn btn-default btn-outline"
                                                            equipmentId="${equipmentId}" edEquipment="1"
                                                            onclick="equipmentED(this)" data-tooltip="Bật">
                                                        <i class="fa fa-2x fa-toggle-off"></i>&nbsp;
                                                    </button>
                                                    <button type="button" name="submit" class="btn btn-success" roomId="${roomId}"
                                                            sc-url="${edit2EquipmentUrl}" onclick="updateEquipment(this)"
                                                            data-tooltip="Sửa">
                                                        <i class="fa fa-pencil-square-o"></i>&nbsp;
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

                                </div>
                                <%
                                        }
                                    }
                                %>

                                <div class="tab-pane p-20" id="profile4" role="tabpanel">2</div>
                                <div class="tab-pane p-20" id="messages4" role="tabpanel">3</div>
                            </div>
                        </div>
                        <div class="m-t-10">
                            <button type="button" name="button" onclick="updateRoom(this)"
                                    class="btn btn-outline-info">
                                <i class="fa fa-plus"></i>&nbsp;
                                <span>Thêm mới phòng</span>
                            </button>
                        </div>
                        <form action="${equipmentListUrl}" method="get" id="formUrl">
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
<div class="modal fade" id="modalRoom" role="dialog" data-backdrop="static" data-keyboard="false"></div>
<div class="modal fade" id="modalEquipment" role="dialog" data-backdrop="static" data-keyboard="false"></div>

<script>
    document.title = "Quản lý vật tư";
    $(document).ready(function () {
        //$('#myModal').modal({backdrop: 'static', keyboard: false});
    });
    //Open modal update or create Room
    function updateRoom(btn) {
        var editUrl = $(btn).attr('sc-url');
        if (typeof editUrl == 'undefined') {
            editUrl = '${addRoomUrl}';
        }
        $('#modalRoom').load(editUrl,'', function () {
            $('#modalRoom').modal('toggle');
            //addOrEdit();
        });
    }
    //Enable or Disable Room
    function roomED(btn) {
        var roomId = $(btn).attr('roomId');
        var ed = $(btn).attr('edRoom');
        $.ajax({
            type: 'POST',
            url: '${edRoomUrl}',
            data:{
                roomId: roomId,
                ed: ed
            },
            success: function (res) {
                if(res.trim() == "redirect_update"){
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
    //Open modal update or create Room
    function updateEquipment(btn) {
        var editUrl = $(btn).attr('sc-url');
        var roomId = $(btn).attr('roomId');
        if (typeof editUrl == 'undefined') {
            editUrl = '/ajax-equipment-edit.html?urlType=url_edit_equipment&roomId='+roomId;
        }
        $('#modalEquipment').load(editUrl,'', function () {
            $('#modalEquipment').modal('toggle');
            //addOrEdit();
        });
    }
    //Enable or Disable Equipment
    function equipmentED(btn) {
        var equipmentId = $(btn).attr('equipmentId');
        var ed = $(btn).attr('edEquipment');
        $.ajax({
            type: 'POST',
            url: '${edEquipmentUrl}',
            data:{
                equipmentId: equipmentId,
                ed: ed
            },
            success: function (res) {
                if(res.trim() == "redirect_update"){
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

</script>

</body>
</html>
