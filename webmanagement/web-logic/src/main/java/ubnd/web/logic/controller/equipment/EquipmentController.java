package ubnd.web.logic.controller.equipment;

import ubnd.core.dto.EquipmentDto;
import ubnd.core.dto.RoomDto;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.FormUtils;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.core.web.utils.WebCommonUtil;
import ubnd.web.logic.command.EquipmentCommand;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/manager-equipment.html", "/ajax-equipment-edit.html"})
public class EquipmentController extends HttpServlet {
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        EquipmentCommand command = FormUtils.populate(EquipmentCommand.class, request);
        EquipmentDto pojo = command.getPojo();
        if(command.getUrlType() != null){
            //view list equipment
            if (command.getUrlType().equals(WebConstants.URL_LIST)) {
                List<EquipmentDto> equipmentDtos = SingletonServiceUtil.getEquipmentServiceInstance().findAll();
                List<RoomDto> roomDtos = SingletonServiceUtil.getRoomServiceInstance().findAll();
                request.setAttribute("listRoom", roomDtos);
                request.setAttribute("listEquipment", equipmentDtos);
                if (command.getCrudaction() != null) {
                    Map<String, String> mapMessage = buidMapRedirectMessage();
                    WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMessage);
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/equipment/list-equipment.jsp");
                dispatcher.forward(request, response);
            }
            //show modal edit or create room
            if (command.getUrlType().equals("url_edit_room")) {
                if (command.getRoomId() != null) {
                    RoomDto roomDto = SingletonServiceUtil.getRoomServiceInstance().findById(command.getRoomId());
                    command.setRoomName(roomDto.getRoomName());
                    command.setRoomDescription(roomDto.getRoomDescription());
                    command.setStatusDB(roomDto.getStatus());
                }
                request.setAttribute(WebConstants.FORM_ITEM, command);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/equipment/edit-room.jsp");
                dispatcher.forward(request, response);
            }
            //show modal edit or create equipment
            if(command.getUrlType().equals("url_edit_equipment")){
                if(command.getRoomId() != null){
                    request.setAttribute("roomId", command.getRoomId());
                }else {
                    request.setAttribute("roomId", 0);
                }
                Integer equipmentId = command.getPojo().getEquipmentId();
                if(equipmentId != null){
                    EquipmentDto equipmentDto = SingletonServiceUtil.getEquipmentServiceInstance().findByID(equipmentId);
                    command.setPojo(equipmentDto);
                }
                request.setAttribute(WebConstants.FORM_ITEM, command);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/equipment/edit-equipment.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        EquipmentCommand command = FormUtils.populate(EquipmentCommand.class, request);
        if (command.getUrlType() != null) {
            //CUD Room
            if (command.getUrlType().equals("url_edit_room")) {
                if (command.getRoomId() != null) {
                    //update room
                    RoomDto dto = new RoomDto();
                    dto.setRoomId(command.getRoomId());
                    dto.setRoomName(command.getRoomName());
                    dto.setRoomDescription(command.getRoomDescription());
                    if (command.getStatus() == null){
                        dto.setStatus(command.getStatusDB());
                    }else {
                        dto.setStatus(command.getStatus());
                    }
                    dto = SingletonServiceUtil.getRoomServiceInstance().update(dto);
                    response.sendRedirect("/manager-equipment.html?urlType=url_list&crudaction=redirect_update");
                } else {
                    //insert room
                    RoomDto dto = new RoomDto();
                    dto.setRoomName(command.getRoomName());
                    dto.setRoomDescription(command.getRoomDescription());
                    if (command.getStatus() == null){
                        dto.setStatus(0);
                    }else {
                        dto.setStatus(command.getStatus());
                    }
                    SingletonServiceUtil.getRoomServiceInstance().save(dto);
                    response.sendRedirect("/manager-equipment.html?urlType=url_list&crudaction=redirect_insert");
                }
            }
            if(command.getUrlType().equals("url_ed_room")){
                //enable or disable room
                String ed = request.getParameter("ed");
                String roomId = request.getParameter("roomId");
                if(ed != null && roomId != null){
                    Integer id = Integer.valueOf(roomId);
                    Integer status = Integer.valueOf(ed);
                    SingletonServiceUtil.getRoomServiceInstance().enableDisableRoom(id, status);
                    response.setContentType("text/plain");
                    response.getWriter().write("redirect_update");
                }
            }
            //CUD Equipment
            if (command.getUrlType().equals("url_edit_equipment")) {
                EquipmentDto equipmentDto = command.getPojo();
                RoomDto roomDto = new RoomDto();
                roomDto.setRoomId(command.getRoomId());
                equipmentDto.setRoomDto(roomDto);
                if (equipmentDto.getEquipmentId() != null) {
                    //update equipment
                    EquipmentDto dto = SingletonServiceUtil.getEquipmentServiceInstance().update(equipmentDto);
                    response.sendRedirect("/manager-equipment.html?urlType=url_list&crudaction=redirect_update");
                } else {
                    //insert equiment
                    SingletonServiceUtil.getEquipmentServiceInstance().save(equipmentDto);
                    response.sendRedirect("/manager-equipment.html?urlType=url_list&crudaction=redirect_insert");
                }
            }
            if(command.getUrlType().equals("url_ed_equipment")){
                //enable or disable equipment
                String equipmentId = request.getParameter("equipmentId");
                String ed = request.getParameter("ed");
                if(equipmentId != null && ed != null){
                    Integer id = Integer.valueOf(equipmentId);
                    Integer status = Integer.valueOf(ed);
                    SingletonServiceUtil.getEquipmentServiceInstance().enableDisable(id, status);
                    response.setContentType("text/plain");
                    response.getWriter().write("redirect_update");
                }
            }
        }
    }

    /**
     * @param dtos
     * @return
     */
    private List<RoomDto> getRoomFromListEquipment(List<EquipmentDto> dtos) {
        List<RoomDto> roomDtos = new ArrayList<RoomDto>();
        for (EquipmentDto item : dtos) {
            if (roomDtos == null) {
                roomDtos.add(item.getRoomDto());
            } else {
                boolean check = checkAddRoomDto(roomDtos, item.getRoomDto().getRoomId());
                if (!check) {
                    roomDtos.add(item.getRoomDto());
                }
            }
        }
        return roomDtos;
    }

    /**
     * @param roomDtos
     * @param roomID
     * @return
     */
    private boolean checkAddRoomDto(List<RoomDto> roomDtos, Integer roomID) {
        boolean check = false;
        for (RoomDto item : roomDtos) {
            if (item.getRoomId() == roomID) {
                return true;
            }
        }
        return check;
    }

    private Map<String, String> buidMapRedirectMessage() {
        Map<String, String> mapMessage = new HashMap<String, String>();
        mapMessage.put(WebConstants.REDIRECT_INSERT, "Thêm thông tin thành công");
        mapMessage.put(WebConstants.REDIRECT_UPDATE, "Cập nhật thành công");
        mapMessage.put(WebConstants.REDIRECT_DELETE, "Xóa thành công");
        mapMessage.put(WebConstants.REDIRECT_ERROR, "Có lỗi xảy ra");
        return mapMessage;
    }
}