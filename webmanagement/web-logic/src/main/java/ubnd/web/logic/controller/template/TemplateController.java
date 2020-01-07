package ubnd.web.logic.controller.template;


import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;
import ubnd.common.constant.CoreConstant;
import ubnd.common.utils.FileUtils;
import ubnd.common.utils.SessionUtils;
import ubnd.common.utils.UploadUtils;
import ubnd.core.dto.TemplateDto;
import ubnd.core.dto.UserDto;
import ubnd.core.web.common.WebConstants;
import ubnd.core.web.utils.FormUtils;
import ubnd.core.web.utils.SingletonServiceUtil;
import ubnd.core.web.utils.WebCommonUtil;
import ubnd.web.logic.command.TemplateCommand;
import ubnd.web.logic.utils.TimeUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@WebServlet(urlPatterns = {"/manager-template.html", "/ajax-template-edit.html"})
public class TemplateController extends HttpServlet {
    private final String READ_DOC_TEMPLATE = "read_doc_template";
    private final String UPLOAD_TEMPLATE = "upload_template";

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        TemplateCommand command = FormUtils.populate(TemplateCommand.class, request);
        TemplateDto pojo = command.getPojo();

        //get User Session anh TimeUtil
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);

        if (command.getUrlType() != null) {
            if (command.getUrlType().equals(WebConstants.URL_LIST)) {
                Map<String, Object> mapProperty = new HashMap<>();
                mapProperty.put("status", 0);
                List<TemplateDto> templateDtoList = SingletonServiceUtil.getTemplateServiceInstance().findByProperty(mapProperty, null, null, null, null);
                if (command.getCrudaction() != null) {
                    Map<String, String> mapMessage = buildRedirectionMessageMap();
                    WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMessage);
                }
                request.setAttribute(WebConstants.LIST_ITEMS, templateDtoList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/template/list-template.jsp");
                dispatcher.forward(request, response);
            }

            if (command.getUrlType().equals(WebConstants.URL_EDIT)) {
                if(pojo.getTemplateId() != null){
                    Integer templateId = pojo.getTemplateId();
                    pojo = SingletonServiceUtil.getTemplateServiceInstance().findById(templateId);
                    command.setPojo(pojo);
                }else {
                    String nameDefault = "Template_"+userDto.getUserName() + "_" + TimeUtils.getCurrentTime();
                    pojo.setName(nameDefault);
                }
                //message
                if (command.getCrudaction() != null) {
                    Map<String, String> mapMessage = buildRedirectionMessageMap();
                    WebCommonUtil.addRedirectMessage(request, command.getCrudaction(), mapMessage);
                }
                request.setAttribute(WebConstants.FORM_ITEM, command);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/template/edit-template.jsp");
                dispatcher.forward(request, response);
            }
            if(command.getUrlType().equals(UPLOAD_TEMPLATE)){
                pojo = (TemplateDto) SessionUtils.getInstance().getValue(request, UPLOAD_TEMPLATE);
                command.setPojo(pojo);
                request.setAttribute(WebConstants.FORM_ITEM, command);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/template/edit-template.jsp");
                dispatcher.forward(request, response);
            }

            if (command.getUrlType().equals(WebConstants.URL_DELETE)) {
                request.setAttribute(WebConstants.FORM_ITEM, command);
                RequestDispatcher dispatcher = request.getRequestDispatcher("views/template/remove-template.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        TemplateCommand command = FormUtils.populate(TemplateCommand.class, request);
        TemplateDto pojo = command.getPojo();

        //get User Session
        UserDto userDto = (UserDto) SessionUtils.getInstance().getValue(request, WebConstants.LOGIN);

        UploadUtils uploadUtils = new UploadUtils();
        Set<String> value = new HashSet<>();
        value.add("urlType");
        value.add("templateId");
        value.add("name");
        value.add("fontSize");
        value.add("font");
        value.add("status");
        value.add("creUID");
        value.add("creDate");

        Object[] objects = uploadUtils.writeOrUpdateFile(request, value, WebConstants.DOC);

        try{
            //read docx
            String urlType = null;
            Map<String, String> mapValue = (Map<String, String>) objects[3];
            for (Map.Entry<String, String> item : mapValue.entrySet()) {
                if (item.getKey().equals("urlType")) {
                    urlType = item.getValue();
                }
            }
            if(urlType != null && urlType.equals(READ_DOC_TEMPLATE)){
                String address = FileUtils.getRealPath(request, CoreConstant.FOLDER_FILE);
                String fileLocation = address + File.separator + objects[1].toString();
                DocumentConverter converter = new DocumentConverter();
                Result<String> result = converter.convertToHtml(new File(fileLocation));
                String html = result.getValue();
                Set<String> warnings = result.getWarnings();
                pojo = returnDtoFromUpload(pojo, mapValue);
                pojo.setContent(html);
                SessionUtils.getInstance().putValue(request, UPLOAD_TEMPLATE, pojo);
                String direct = "/manager-template.html?urlType=upload_template";
                response.sendRedirect(direct);
            }
        }catch (Exception e) {
            request.setAttribute(WebConstants.MESSAGE_RESPONSE, WebConstants.REDIRECT_ERROR);
        }


        if (command.getUrlType() != null) {

            if (command.getUrlType().equals(WebConstants.URL_EDIT)) {
                if(pojo.getTemplateId() != null){
                    pojo.setModUID(userDto.getUserId());
                    pojo.setModDate(TimeUtils.getCurrentTimestamp());
                    TemplateDto dto = SingletonServiceUtil.getTemplateServiceInstance().updateTemplate(pojo);
                    Integer templateId = dto.getTemplateId();
                    response.sendRedirect("/manager-template.html?urlType=url_edit&pojo.templateId=" + templateId + "&crudaction=redirect_update");
                }else {
                    pojo.setFont("Arial");
                    pojo.setFontSize(14);
                    pojo.setStatus(0);
                    pojo.setCreUID(userDto.getUserId());
                    pojo.setCreDate(TimeUtils.getCurrentTimestamp());
                    pojo.setModUID(userDto.getUserId());
                    pojo.setModDate(TimeUtils.getCurrentTimestamp());
                    SingletonServiceUtil.getTemplateServiceInstance().saveTemplate(pojo);
                    response.sendRedirect("/manager-template.html?urlType=url_list&crudaction=redirect_insert");
                }
            }
            if (command.getUrlType().equals(WebConstants.URL_DELETE)) {
                String templateId = request.getParameter("templateId");
                if(templateId != null){
                    SingletonServiceUtil.getTemplateServiceInstance().deleteTemplate(Integer.valueOf(templateId));
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("Success");
                }else {
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write("Error");
                }
            }
        }
    }

    private Map<String, String> buildRedirectionMessageMap() {
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put(WebConstants.REDIRECT_INSERT, "Thêm template thành công");
        mapMessage.put(WebConstants.REDIRECT_UPDATE, "Chỉnh sửa template thành công");
        mapMessage.put(WebConstants.REDIRECT_DELETE, "Xóa template thành công");
        mapMessage.put(WebConstants.REDIRECT_ERROR, "Có lỗi xảy ra");
        return mapMessage;
    }

    private TemplateDto returnDtoFromUpload(TemplateDto dto, Map<String, String> mapValue){
        for(Map.Entry<String, String> item: mapValue.entrySet()){
            switch (item.getKey()){
                case "templateId":
                    dto.setTemplateId(Integer.parseInt(item.getValue()));
                    break;
                case "name":
                    dto.setName(item.getValue());
                    break;
                case "fontSize":
                    dto.setFontSize(Integer.valueOf(item.getValue()));
                    break;
                case "font":
                    dto.setFont(item.getValue());
                    break;
                case "status":
                    dto.setStatus(Integer.valueOf(item.getValue()));
                    break;
                case "creUID":
                    dto.setCreUID(Integer.valueOf(item.getValue()));
                    break;
                case "creDate":
                    dto.setCreDate(Timestamp.valueOf(item.getValue()));
                    break;
            }
        }
        return dto;
    }


}