package ubnd.common.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import ubnd.common.constant.CoreConstant;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UploadUtils {
    private final Logger log = Logger.getLogger(this.getClass());

    // Create a factory for disk-based file items
    private DiskFileItemFactory factory = new DiskFileItemFactory();
    private int maxMemoryFile = 1023 * 1024 * 250;
    private int maxRequestFile = 1023 * 1024 * 250;

    public String writeOrUpdateFile(HttpServletRequest request) {
//        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        String filename;
        String address = FileUtils.getPathTemptWord(request);
        File file = new File(address);
        if (!file.exists()) {
            file.mkdir();
        }
        String addr;
//        checkAndCreateFolder(address, address);
        File uploadFile = null;
        // Create a factory for disk-based file items

        // Set factory constraints

        factory.setSizeThreshold(maxMemoryFile);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint

        upload.setSizeMax(maxRequestFile);

        // Parse the request
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    filename = item.getName();
                    uploadFile = new File(address + File.separator + filename);
                    try {

                        if (uploadFile.exists()) {
                            uploadFile.delete();
                        }
                        item.write(uploadFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            addr = address + File.separator + System.currentTimeMillis() + ".xlsx";
            uploadFile.renameTo(new File(addr));
        } catch (FileUploadException e) {
            addr = null;
        }

        return addr;
    }

    public Object[] uploadMultiFle(HttpServletRequest request, String path) throws Exception {
        boolean check = false;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemoryFile);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(maxMemoryFile);
        List<FileItem> items = upload.parseRequest(request);
        String nameFolder = "";
        String selectTime = "";
        String typeName = "";
        for (FileItem item : items) {
            if (item.isFormField()) {
                String field = item.getFieldName();
                String value = item.getString();
                if (field.equals("selectTime")) {
                    selectTime = value;
                }
                if (!selectTime.equals("")) {
                    if (field.equals("choseTime")) {
                        nameFolder = TimeUtils.convertStringDateToTimeMillis(value);
                    } else if (field.equals("selectUploadFolder")) {
                        nameFolder = value;

                    }

                }

                if (field.equals("selectType")) {
                    if (value.equals("1")) {
                        typeName = "text_";
                    } else {
                        typeName = "steno_";
                    }
                }
            } else {
                checkAndCreateFolder(path, nameFolder);
                String name = item.getName();
                File uploadedFile = new File(path + File.separator + nameFolder + File.separator + typeName + name);
                boolean isExist = uploadedFile.exists();
                if (isExist) {
                    uploadedFile.delete();
                    item.write(uploadedFile);
                } else {
                    item.write(uploadedFile);
                }
            }
        }

        if (selectTime.equals("1")) {
            check = true;
        }
        return new Object[]{check, nameFolder};
    }


    public Object[] writeOrUpdateFile(HttpServletRequest request, Set<String> titleValue, String path) {


        //String address = "/"+ CoreConstant.FOLDER_UPLOAD;
        String address = FileUtils.getRealPath(request, CoreConstant.FOLDER_FILE);
        checkAndCreateFolder(address, path);
        boolean check = true;
        String fileLocation = null;
        String name = null;
        Long fileSize = null;
        Map<String, String> mapReturnValue = new HashMap<>();
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            System.out.println("have not enctype multipart/form-data ");
            check = false;
        }
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
        factory.setSizeThreshold(maxMemoryFile);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(maxMemoryFile);
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    name = item.getName();
                    fileSize = item.getSize();
                    if (StringUtils.isNotBlank(name)) {
                        fileLocation = path + File.separator + name;
                        File uploadedFile = new File(address + File.separator + path + File.separator + name);
                        System.out.println(address + File.separator + path);
                        boolean isExist = uploadedFile.exists();
                        try {
                            if (isExist) {
                                uploadedFile.delete();
                                item.write(uploadedFile);
                            } else {
                                item.write(uploadedFile);
                            }
                        } catch (Exception e) {
                            check = false;
                            log.error(e.getMessage(), e);
                        }
                    }
                } else {
                    if (titleValue != null) {
                        String nameField = item.getFieldName();
                        String valueField = null;
                        try {
                            valueField = item.getString("UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            log.error(e.getMessage(), e);
                        }
                        if (titleValue.contains(nameField)) {
                            mapReturnValue.put(nameField, valueField);
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            check = false;
            log.error(e.getMessage(), e);
        }
        String fileName = "";
        if (StringUtils.isNotBlank(name)) {
            fileName = name;
        }
        return new Object[]{check, fileLocation, fileName, mapReturnValue, fileSize};
    }

    private void checkAndCreateFolder(String address, String path) {
        File folderRoot = new File(address);
        if (!folderRoot.exists()) {
            folderRoot.mkdirs();
        }
        File folderChild = new File(address + File.separator + path);
        if (!folderChild.exists()) {
            folderChild.mkdirs();
        }
    }
}
