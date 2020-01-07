package ubnd.core.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ubnd.common.constant.CoreConstant;
import ubnd.common.utils.FileUtils;
import ubnd.core.dto.ParagraphTranscriptDto;
import ubnd.core.dto.TextTranscriptDto;
import ubnd.core.dto.TranscriptDto;
import ubnd.core.persistence.data.entity.TranscriptEntity;
import ubnd.core.service.TranscriptService;
import ubnd.core.service.ultis.SingletonDaoUtil;
import ubnd.core.utlis.TranscriptBeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class TranscriptServiceImpl implements TranscriptService {

    public List<TranscriptDto> findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        Object[] objects = SingletonDaoUtil.getTranscriptDaoInstance().findByProperty(property, sortExpression, sortDirection, offset, limit, null);
        List<TranscriptDto> dtoList = new ArrayList<>();
        for(TranscriptEntity item: (List<TranscriptEntity>)objects[1]){
            TranscriptDto dto = TranscriptBeanUtils.entityToDTO(item);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<TranscriptDto> findAll() {
        List<TranscriptEntity> entities = SingletonDaoUtil.getTranscriptDaoInstance().findAll();
        List<TranscriptDto> dtos = new ArrayList<>();
        for(TranscriptEntity item: entities){
            TranscriptDto dto = TranscriptBeanUtils.entityToDTO(item);
            dtos.add(dto);
        }
        return dtos;
    }

    public TranscriptDto findById(Integer id) {
        TranscriptEntity entity = SingletonDaoUtil.getTranscriptDaoInstance().findByID(id);
        return TranscriptBeanUtils.entityToDTO(entity);
    }

    public void save(TranscriptDto dto) {
        TranscriptEntity entity = TranscriptBeanUtils.dtoToEntity(dto);
        SingletonDaoUtil.getTranscriptDaoInstance().save(entity);
    }

    public TranscriptDto update(TranscriptDto dto) {
        TranscriptEntity entity = TranscriptBeanUtils.dtoToEntity(dto);
        entity = SingletonDaoUtil.getTranscriptDaoInstance().update(entity);
        return TranscriptBeanUtils.entityToDTO(entity);
    }

    public String createXML(HttpServletRequest request, String folderName, String fileName, String data) {
        String address = FileUtils.getRealPath(request, CoreConstant.FOLDER_FILE);
        checkAndCreateFolder(address, folderName);

        List<TextTranscriptDto> textTranscriptDtoList = getListTextTranscription(data);
        List<ParagraphTranscriptDto> paragraphTranscriptDtoList = getListParagraphTranscript(textTranscriptDtoList);
        String fileLocation = address + File.separator + folderName + File.separator + fileName;
        writeXMLParagraphTranscript(fileLocation, paragraphTranscriptDtoList);
        return folderName + File.separator + fileName;
//        try {
//            List<TextTranscriptDto> textTranscriptDtoList = getListTextTranscription(data);
//            List<ParagraphTranscriptDto> paragraphTranscriptDtoList = getListParagraphTranscript(textTranscriptDtoList);
//            Document document = createXMLDocument(paragraphTranscriptDtoList);
//
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//            DOMSource source = new DOMSource(document);
//            String fileLocation = address + File.separator + folderName + File.separator + fileName;
//            File file = new File(fileLocation);
//            StreamResult result = new StreamResult(file);
//            transformer.transform(source, result);
//
//        }  catch (TransformerException e) {
//            e.printStackTrace();
//            return "error write xml";
//        }
    }

    public void updateXML(HttpServletRequest request, String xmlPath,  String content, String speaker){
        String address = FileUtils.getRealPath(request, CoreConstant.FOLDER_FILE);
        String fileLocation = address + File.separator + xmlPath;
        List<ParagraphTranscriptDto> paragraphOldList = getParagraphTransByXML(fileLocation);
        List<ParagraphTranscriptDto> paragraphUpdateList = new ArrayList<>();
        JSONObject jsonContent = new JSONObject(content);
        JSONObject jsonSpeaker = new JSONObject(speaker);
        for (int i = 0; i < paragraphOldList.size(); i++){
            ParagraphTranscriptDto dto = paragraphOldList.get(i);
            dto.setText(jsonContent.getString(""+i));
            dto.setSpeaker(jsonSpeaker.getString(""+i));
            paragraphUpdateList.add(dto);
        }

        writeXMLParagraphTranscript(fileLocation, paragraphUpdateList);
    }

    public String writeJSONContent(HttpServletRequest request, String folderName, String fileName, String data){
        String address = FileUtils.getRealPath(request, CoreConstant.FOLDER_FILE);
        String fileLocation = address + File.separator + folderName + File.separator + fileName;
        FileUtils.writeOrUpdateFile(fileLocation, data);
        return folderName + File.separator + fileName;
    }


    public List<ParagraphTranscriptDto> getParagraphTransByXML(String path){
        List<ParagraphTranscriptDto> list = new ArrayList<>();
        List<String> timeStarts = new ArrayList<>();
        List<String> timeEdnds = new ArrayList<>();
        List<String> texts = new ArrayList<>();
        List<String> comments = new ArrayList<>();
        try {
            File file = new File(path);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            //get time start - end
            Element annotationDocument = doc.getDocumentElement();
            Element timeOrder = (Element) annotationDocument.getElementsByTagName("TIME_ORDER").item(0);
            NodeList timeSlots = timeOrder.getElementsByTagName("TIME_SLOT");
            for (int i = 0; i < timeSlots.getLength(); i++) {
                Node node = timeSlots.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element timeSlot = (Element) node;
                    String timeValue = timeSlot.getAttribute("TIME_VALUE");
                    if (i % 2 == 0) {
                        timeStarts.add(timeValue);
                    } else {
                        timeEdnds.add(timeValue);
                    }

                }
            }
            //get text
            Element tierText = (Element) annotationDocument.getElementsByTagName("TIER").item(1);
            NodeList annotationTexts = tierText.getElementsByTagName("ANNOTATION");
            for (int i = 0; i < annotationTexts.getLength(); i++) {
                Node node = annotationTexts.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element annotationText = (Element) node;
                    Element ref = (Element) annotationText.getElementsByTagName("REF_ANNOTATION").item(0);
                    String text = ref.getElementsByTagName("ANNOTATION_VALUE").item(0).getTextContent();
                    texts.add(text);
                }
            }
            //get comment
            Element tierComment = (Element) annotationDocument.getElementsByTagName("TIER").item(2);
            NodeList annotationComments = tierComment.getElementsByTagName("ANNOTATION");
            for (int i = 0; i < annotationComments.getLength(); i++) {
                Node node = annotationComments.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element annotationComment = (Element) node;
                    Element ref = (Element) annotationComment.getElementsByTagName("REF_ANNOTATION").item(0);
                    String comment = ref.getElementsByTagName("ANNOTATION_VALUE").item(0).getTextContent();
                    comments.add(comment);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < timeStarts.size(); i++) {
            Integer seq = i+1;
            String speaker = comments.get(i);
            double timeStart = Double.parseDouble(timeStarts.get(i));
            double timeEnd = Double.parseDouble(timeEdnds.get(i));
            String text = texts.get(i);
            double reliabilityID = 0;
            double reliabilityText = 0;
            ParagraphTranscriptDto dto = new ParagraphTranscriptDto(seq, speaker, timeStart, timeEnd, text, reliabilityID, reliabilityText);
            list.add(dto);
        }
        return list;
    }

    private List<TextTranscriptDto> getListTextTranscription(String data) {
        List<TextTranscriptDto> list = new ArrayList<>();
        try {
            JSONObject objectFile = new JSONObject(data);
            JSONArray arrayData = objectFile.getJSONArray("data");
            JSONObject objectData = arrayData.getJSONObject(0);
            String transcript = objectData.getString("transcript");
            JSONArray arrayTranscriptInfo = objectData.getJSONArray("transcript_info");
            for (int i = 0; i < arrayTranscriptInfo.length(); i++) {
                JSONObject objectText = arrayTranscriptInfo.getJSONObject(i);
                TextTranscriptDto dto = new TextTranscriptDto();
                dto.setKey(objectText.getInt("key"));
                dto.setSeq(objectText.getInt("seq"));
                dto.setIdSpeaker(objectText.getString("idSpeaker"));
                dto.setTimeStart(objectText.getDouble("timeStart"));
                dto.setTimeEnd(objectText.getDouble("timeEnd"));
                dto.setText(objectText.getString("text"));
                dto.setReliabilityID(objectText.getDouble("reliabilityID"));
                dto.setReliabilityText(objectText.getDouble("reliabilityText"));
                list.add(dto);
            }
            //System.out.println(transcript);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @param textTranscriptDtoList: danh sach cac am tiet
     * @return danh sach doan van
     */
    private List<ParagraphTranscriptDto> getListParagraphTranscript(List<TextTranscriptDto> textTranscriptDtoList) {
        List<ParagraphTranscriptDto> list = new ArrayList<>();
        Integer numberParagraph = textTranscriptDtoList.get(textTranscriptDtoList.size() - 1).getKey();
        for (int i = 0; i < numberParagraph; i++) {
            Integer seq = i + 1;
            String speaker = "Nghia ne!";
            double timeStart = -1;
            double timeEnd = -1;
            StringBuilder text = new StringBuilder();
            double reliabilityID = 70;
            double reliabilityText = 70;

            for (TextTranscriptDto item : textTranscriptDtoList) {
                if (item.getKey().equals(seq)) {
                    //set Time Start
                    if (timeStart == -1) {
                        timeStart = item.getTimeStart() * 1000;
                    }
                    timeEnd = item.getTimeEnd() * 1000;
                    text.append(" ").append(item.getText());
                }
            }
            list.add(new ParagraphTranscriptDto(seq, speaker, timeStart, timeEnd, text.toString(), reliabilityID, reliabilityText));
        }
        return list;
    }

    /**
     * Ghi file xml dung de hien thi doan van
     * @param paragraphTranscriptList: danh sach doan van
     */
    private void writeXMLParagraphTranscript(String path, List<ParagraphTranscriptDto> paragraphTranscriptList){
        try {
            Document doc = createXMLDocument(paragraphTranscriptList);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            DOMSource source = new DOMSource(doc);
            File file = new File(path);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        }  catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private Document createXMLDocument(List<ParagraphTranscriptDto> paragraphTranscriptList){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element annotationDocument = doc.createElement("ANNOTATION_DOCUMENT");
            doc.appendChild(annotationDocument);

            Attr format = doc.createAttribute("FORMAT");
            format.setValue("2.6");
            annotationDocument.setAttributeNode(format);

            Attr version = doc.createAttribute("FORMAT");
            version.setValue("2.6");
            annotationDocument.setAttributeNode(version);
            //header
            Element header = doc.createElement("HEADER");
            annotationDocument.appendChild(header);

            Attr mediaFile = doc.createAttribute("MEDIA_FILE");
            mediaFile.setValue("");
            header.setAttributeNode(mediaFile);

            Attr timeUnits = doc.createAttribute("TIME_UNITS");
            timeUnits.setValue("milliseconds");
            header.setAttributeNode(timeUnits);

            Element media = doc.createElement("MEDIA_DESCRIPTOR");
            header.appendChild(media);

            Attr mediaURL = doc.createAttribute("MEDIA_URL");
            mediaURL.setValue("audio.mp3");
            media.setAttributeNode(mediaURL);

            Attr mimeType = doc.createAttribute("MIME_TYPE");
            mimeType.setValue("audio/mpeg");
            media.setAttributeNode(mimeType);
            //end header
            //time order
            Element timeOrder = doc.createElement("TIME_ORDER");
            annotationDocument.appendChild(timeOrder);
            for (ParagraphTranscriptDto item : paragraphTranscriptList) {
                //time start
                Element timeSlotStart = doc.createElement("TIME_SLOT");
                timeOrder.appendChild(timeSlotStart);

                Attr timeSlotIdStart = doc.createAttribute("TIME_SLOT_ID");
                int idTimeStart = 2 * item.getSeq() - 1;
                timeSlotIdStart.setValue("TS" + idTimeStart);
                timeSlotStart.setAttributeNode(timeSlotIdStart);

                Attr timeValueStart = doc.createAttribute("TIME_VALUE");
                int valueStart = (int) item.getTimeStart();
                timeValueStart.setValue("" + valueStart);
                timeSlotStart.setAttributeNode(timeValueStart);
                //time end----------------------------------------------
                Element timeSlotEnd = doc.createElement("TIME_SLOT");
                timeOrder.appendChild(timeSlotEnd);

                Attr timeSlotIdEnd = doc.createAttribute("TIME_SLOT_ID");
                int idTimeEnd = 2 * item.getSeq();
                timeSlotIdEnd.setValue("TS" + idTimeEnd);
                timeSlotEnd.setAttributeNode(timeSlotIdEnd);

                Attr timeValueEnd = doc.createAttribute("TIME_VALUE");
                int valueEnd = (int) item.getTimeEnd();
                timeValueEnd.setValue("" + valueEnd);
                timeSlotEnd.setAttributeNode(timeValueEnd);

            }
            //end time order
            //tier edu
            Element tierEDU = doc.createElement("TIER");
            annotationDocument.appendChild(tierEDU);

            Attr edu1 = doc.createAttribute("DEFAULT_LOCALE");
            edu1.setValue("vi");
            tierEDU.setAttributeNode(edu1);

            Attr edu2 = doc.createAttribute("LINGUISTIC_TYPE_REF");
            edu2.setValue("EDU");
            tierEDU.setAttributeNode(edu2);

            Attr edu3 = doc.createAttribute("TIER_ID");
            edu3.setValue("EDU");
            tierEDU.setAttributeNode(edu3);

            for(ParagraphTranscriptDto item: paragraphTranscriptList){
                Element annotation = doc.createElement("ANNOTATION");
                tierEDU.appendChild(annotation);

                Element alignableAnnotation = doc.createElement("ALIGNABLE_ANNOTATION");
                annotation.appendChild(alignableAnnotation);

                Attr annotationId = doc.createAttribute("ANNOTATION_ID");
                annotationId.setValue("EDU"+item.getSeq());
                alignableAnnotation.setAttributeNode(annotationId);

                Attr timeSlotREF1 = doc.createAttribute("TIME_SLOT_REF1");
                int REF1 = 2 * item.getSeq() - 1;
                timeSlotREF1.setValue("TS"+REF1);
                alignableAnnotation.setAttributeNode(timeSlotREF1);

                Attr timeSlotREF2 = doc.createAttribute("TIME_SLOT_REF2");
                int REF2 = 2 * item.getSeq();
                timeSlotREF2.setValue("TS"+REF2);
                alignableAnnotation.setAttributeNode(timeSlotREF2);

                Element annotationValue = doc.createElement("ANNOTATION_VALUE");
                annotationValue.appendChild(doc.createTextNode(item.getSeq().toString()));
                alignableAnnotation.appendChild(annotationValue);
            }
            //end tier edu
            //tier text
            Element tierText = doc.createElement("TIER");
            annotationDocument.appendChild(tierText);

            Attr text1 = doc.createAttribute("DEFAULT_LOCALE");
            text1.setValue("vi");
            tierText.setAttributeNode(text1);

            Attr text2 = doc.createAttribute("LINGUISTIC_TYPE_REF");
            text2.setValue("EDUProp");
            tierText.setAttributeNode(text2);

            Attr text3 = doc.createAttribute("PARENT_REF");
            text3.setValue("EDU");
            tierText.setAttributeNode(text3);

            Attr text4 = doc.createAttribute("TIER_ID");
            text4.setValue("Text");
            tierText.setAttributeNode(text4);

            for(ParagraphTranscriptDto item: paragraphTranscriptList){
                Element annotation = doc.createElement("ANNOTATION");
                tierText.appendChild(annotation);

                Element refAnnotation = doc.createElement("REF_ANNOTATION");
                annotation.appendChild(refAnnotation);

                Attr annotationId = doc.createAttribute("ANNOTATION_ID");
                annotationId.setValue("TEXT"+item.getSeq());
                refAnnotation.setAttributeNode(annotationId);

                Attr annotationREF = doc.createAttribute("ANNOTATION_REF");
                annotationREF.setValue("EDU"+item.getSeq());
                refAnnotation.setAttributeNode(annotationREF);

                Element annotationValue = doc.createElement("ANNOTATION_VALUE");
                annotationValue.appendChild(doc.createTextNode(item.getText()));
                refAnnotation.appendChild(annotationValue);
            }
            //end tier text
            //tier comments
            Element tierComments = doc.createElement("TIER");
            annotationDocument.appendChild(tierComments);

            Attr comment1 = doc.createAttribute("DEFAULT_LOCALE");
            comment1.setValue("vi");
            tierComments.setAttributeNode(comment1);

            Attr comment2 = doc.createAttribute("LINGUISTIC_TYPE_REF");
            comment2.setValue("EDUProp");
            tierComments.setAttributeNode(comment2);

            Attr comment3 = doc.createAttribute("PARENT_REF");
            comment3.setValue("EDU");
            tierComments.setAttributeNode(comment3);

            Attr comment4 = doc.createAttribute("TIER_ID");
            comment4.setValue("Comments");
            tierComments.setAttributeNode(comment4);

            for(ParagraphTranscriptDto item: paragraphTranscriptList){
                Element annotation = doc.createElement("ANNOTATION");
                tierComments.appendChild(annotation);

                Element refAnnotation = doc.createElement("REF_ANNOTATION");
                annotation.appendChild(refAnnotation);

                Attr annotationId = doc.createAttribute("ANNOTATION_ID");
                annotationId.setValue("COMMENT"+item.getSeq());
                refAnnotation.setAttributeNode(annotationId);

                Attr annotationREF = doc.createAttribute("ANNOTATION_REF");
                annotationREF.setValue("EDU"+item.getSeq());
                refAnnotation.setAttributeNode(annotationREF);

                Element annotationValue = doc.createElement("ANNOTATION_VALUE");
                annotationValue.appendChild(doc.createTextNode(item.getSpeaker()));
                refAnnotation.appendChild(annotationValue);
            }
            //end tier comments
            return doc;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String documentToString(Document document){
        try {
            DOMSource source = new DOMSource(document);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            StringWriter stringWriter = new StringWriter();
            StreamResult streamResult = new StreamResult(stringWriter);
            transformer.transform(source, streamResult);
            return stringWriter.toString();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            return null;
        } catch (TransformerException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void checkAndCreateFolder(String address, String folder) {
        File folderRoot = new File(address);
        if (!folderRoot.exists()) {
            folderRoot.mkdirs();
        }
        File folderChild = new File(address + File.separator + folder);
        if (!folderChild.exists()) {
            folderChild.mkdirs();
        }
    }
}
