package ubnd.web.logic.utils;

import org.json.JSONObject;
import ubnd.common.utils.FileUtils;
import ubnd.core.data.obj.DictObject;
import ubnd.core.data.obj.StenoObject;
import ubnd.core.service.DictStenoService;
import ubnd.core.service.impl.DictStenoServiceImpl;
import ubnd.web.logic.state.DissectedState;
import ubnd.web.logic.state.State;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StenoUtils {
    public static String wordToSteno(HttpServletRequest request, String textConvert) throws IOException {
        DictStenoService service = new DictStenoServiceImpl();
        DictObject object = service.getDefaultDict();
        String pathDict = FileUtils.getPathDict(request) + File.separator + object.getDto().getContent();
        String content_dict = FileUtils.readContentFile(pathDict);

        State state = new DissectedState();
        textConvert = textConvert.replace("\r", "");
        for (int i = 0; i < textConvert.length(); i++) {
            state.nextChar(String.valueOf(textConvert.charAt(i)));
        }
        if (!state.s.toString().equals("")) {
            state.list.add(state.s.toString());
        }

        ArrayList<StenoObject> arrayList = getListDict(content_dict);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < state.list.size(); i++) {
            String s = state.list.get(i);
            String before, before1, after, after1, after2;
            try {
                before = String.valueOf(state.list.get(i - 1).charAt(0));
            } catch (Exception e) {
                before = "";
            }
            try {
                before1 = String.valueOf(state.list.get(i - 2).charAt(0));
            } catch (Exception e) {
                before1 = "";
            }
            try {
                after = String.valueOf(state.list.get(i + 1).charAt(0));
            } catch (Exception e) {
                after = "";
            }
            try {
                after1 = String.valueOf(state.list.get(i + 2).charAt(0));
            } catch (Exception e) {
                after1 = "";
            }
            try {
                after2 = String.valueOf(state.list.get(i + 3).charAt(0));
            } catch (Exception e) {
                after2 = "";
            }

            if (i == 0) {
                if (RegexUtils.regexSpace(s)) {
                    stringBuilder.append(RegexUtils.getKeySteno("{^ ^}", arrayList));
                } else if (RegexUtils.regexChar(s)) {
                    if (StringUtils.checkTypeString(s)) {
                        stringBuilder.append(RegexUtils.getKeySteno("{-|}", arrayList));
                        stringBuilder.append(" ");
                        stringBuilder.append(RegexUtils.getKeySteno(s, arrayList));
                    } else {
                        stringBuilder.append(RegexUtils.getKeySteno(s, arrayList));
                    }
                } else if (RegexUtils.regexNumber(s)) {
                    stringBuilder.append(NumberUtils.getNumberSteno(s));
                } else if (RegexUtils.regexPunctuation(s)) {
                    stringBuilder.append(RegexUtils.getKeySteno(StringUtils.checkPunctuation(s, before, after, after1), arrayList));
                } else if (s.equals("\n")) {
                    stringBuilder.append(RegexUtils.getKeySteno(s, arrayList));
                }
            } else {
                if (RegexUtils.regexSpace(s)) {
                    if (before.equals(" ")) {
                        stringBuilder.append(" ").append(RegexUtils.getKeySteno("{^ ^}", arrayList));
                    } else if (RegexUtils.regexPunctuation(s)) {
                        if (RegexUtils.regexTypeOne(StringUtils.checkPunctuation(before, before1, s, after))) {
                            stringBuilder.append(" ").append(RegexUtils.getKeySteno("{^ ^}", arrayList));
                        } else if (RegexUtils.regexTypeOne(StringUtils.checkPunctuation(after, s, after1, after2))) {
                            stringBuilder.append(" ").append(RegexUtils.getKeySteno("{^ ^}", arrayList));
                        }
                    } else if (before.equals("\n")) {
                        stringBuilder.append(" ").append(RegexUtils.getKeySteno("{^ ^}", arrayList));
                    }
                } else if (RegexUtils.regexNumber(s)) {
                    if (before.equals(" ") && RegexUtils.regexNumber(before1)) {
                        stringBuilder.append(" ")
                                .append(RegexUtils.getKeySteno("{^ ^}", arrayList))
                                .append(" ")
                                .append(NumberUtils.getNumberSteno(s));
                    } else if (before.equals(" ") && !RegexUtils.regexNumber(before1)) {
                        stringBuilder.append(" ").append(NumberUtils.getNumberSteno(s));
                    } else {
                        stringBuilder.append(" ")
                                .append(RegexUtils.getKeySteno("{-|}", arrayList))
                                .append(" ")
                                .append(NumberUtils.getNumberSteno(s));
                    }
                } else if (RegexUtils.regexPunctuation(s)) {
                    stringBuilder.append(" ").append(RegexUtils.getKeySteno(StringUtils.checkPunctuation(s, before, after, after1), arrayList));
                } else if (RegexUtils.regexChar(s)) {
                    if (before.equals(" ")) {
                        if (StringUtils.checkTypeString(s)) {
                            if (before1.equals(".") || before1.equals("?") || before1.equals("!")) {
                                stringBuilder.append(" ")
                                        .append(RegexUtils.getKeySteno(s, arrayList));
                            } else {
                                stringBuilder.append(" ")
                                        .append(RegexUtils.getKeySteno("{^ ^}{-|}", arrayList))
                                        .append(" ")
                                        .append(RegexUtils.getKeySteno(s, arrayList));
                            }
                        } else {
                            stringBuilder.append(" ")
                                    .append(RegexUtils.getKeySteno(s, arrayList));
                        }
                    } else if (RegexUtils.regexNumber(before)) {
                        stringBuilder.append(" ")
                                .append(RegexUtils.getKeySteno(s, arrayList));
                    } else if (RegexUtils.regexPunctuation(before)) {
                        if (StringUtils.checkTypeString(s)) {
                            stringBuilder.append(" ")
                                    .append(RegexUtils.getKeySteno("{-|}", arrayList))
                                    .append(" ")
                                    .append(RegexUtils.getKeySteno(s, arrayList));
                        } else {
                            stringBuilder.append(" ")
                                    .append(RegexUtils.getKeySteno(s, arrayList));
                        }
                    }
                } else if (s.equals("\n")) {
                    stringBuilder.append(" ")
                            .append(RegexUtils.getKeySteno(s, arrayList));
                }
            }
        }

        return stringBuilder.toString();
    }

    public static String stenoToWord(HttpServletRequest request, String content) throws IOException {
        DictStenoService service = new DictStenoServiceImpl();
        DictObject object = service.getDefaultDict();
        String pathDict = FileUtils.getPathDict(request) + File.separator + object.getDto().getContent();
        String content_dict = FileUtils.readContentFile(pathDict);
        String[] list = content.split(" ");
        ArrayList<StenoObject> arrayList = getListDict(content_dict);
        List<String> result = new ArrayList<>();
        boolean check = false;
        boolean checkNumber;
        for (int i = 0; i < list.length; i++) {
            String before;

            if (result.size() > 0) {
                before = result.get(result.size() - 1);
            } else {
                before = "";
            }

            try {
                String s = list[i + 1];
                checkNumber = String.valueOf(s.charAt(0)).equals("#");
            } catch (Exception e) {
                checkNumber = false;
            }

            if (String.valueOf(list[i].charAt(0)).equals("#")) {
                result.add(NumberUtils.convertStenoToNumber(list[i]));
                if (!checkNumber) {
                    result.add(" ");
                }
                check = false;
            } else {
                String value = RegexUtils.getValueSteno(list[i], arrayList);
                String valueBefore;

                try {
                    valueBefore = list[i - 1];
                } catch (Exception e) {
                    valueBefore = "";
                }


                switch (value) {
                    case "^`^":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                            result.add("`");
                        }
                        result.add("`");
                        check = false;
                        break;
                    case "^^^":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("^");
                        check = false;
                        break;
                    case "{^~}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("~");
                        result.add(" ");
                        check = false;
                        break;
                    case "{^~^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("~");
                        check = false;
                        break;
                    case "{^ ^}{-|}":
                        if (!before.equals(" ")) {
                            result.add(" ");
                        }
                        check = true;
                        break;
                    case "{-|}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        check = true;
                        break;
                    case "{^<^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("<");
                        check = false;
                        break;
                    case "{^=^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("=");
                        check = false;
                        break;
                    case "{^>^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add(">");
                        check = false;
                        break;
                    case "{^|^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("|");
                        check = false;
                        break;
                    case "{^_^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("_");
                        check = false;
                        break;
                    case "{^-^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("-");
                        check = false;
                        break;
                    case "{,}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add(",");
                        result.add(" ");
                        check = false;
                        break;
                    case "{^,^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add(",");
                        check = false;
                        break;
                    case "{;}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add(";");
                        result.add(" ");
                        check = false;
                        break;
                    case "{^;^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add(";");
                        check = false;
                        break;
                    case "{:}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add(":");
                        result.add(" ");
                        check = true;
                        break;
                    case "{^:^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add(":");
                        check = false;
                        break;

                    case "{?}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("?");
                        result.add(" ");
                        check = true;
                        break;
                    case "{^?^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("?");
                        check = false;
                        break;

                    case "{^/^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("/");
                        check = false;
                        break;
                    case "{.}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add(".");
                        result.add(" ");
                        check = true;
                        break;
                    case "{^.^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add(".");
                        check = false;
                        break;
                    case "{'^}":
                        result.add("'");
                        check = false;
                        break;
                    case "{^'}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("'");
                        result.add(" ");
                        check = false;
                        break;
                    case "{^'^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("'");
                        check = false;
                        break;

                    case "{\"^}":
                        result.add("\"");
                        check = false;
                        break;
                    case "{^\"}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("\"");
                        result.add(" ");
                        check = false;
                        break;
                    case "{^\"^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("\"");
                        check = false;
                        break;
                    case "{(^}":
                        result.add("(");
                        check = false;
                        break;
                    case "{^(^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("(");
                        check = false;
                        break;
                    case "{^)}":
                        if (before.equals(" ") || !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add(")");
                        result.add(" ");
                        check = false;
                        break;
                    case "{^)^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add(")");
                        check = false;
                        break;
                    case "{[^}":
                        result.add("]");
                        check = false;
                        break;
                    case "{^[^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("[");
                        check = false;
                        break;
                    case "{^]}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("]");
                        result.add(" ");
                        check = false;
                        break;
                    case "{^]^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("]");
                        check = false;
                        break;
                    case "{{^}":
                        result.add("{");
                        check = false;
                        break;
                    case "{^{^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("{");
                        check = false;
                        break;
                    case "{^}}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("}");
                        result.add(" ");
                        check = false;
                        break;
                    case "{^}^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("}");
                        check = false;
                        break;
                    case "{^@^}":
                        if (before.equals(" ") || !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("@");
                        check = false;
                        break;
                    case "{$^}":
                        result.add("@");
                        check = false;
                        break;
                    case "{^*^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("*");
                        check = false;
                        break;
                    case "{^\\^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("\\");
                        check = false;
                        break;
                    case "{#^}":
                        result.add("#");
                        check = false;
                        break;
                    case "{&%}":
                        result.add("%");
                        result.add(" ");
                        check = false;
                        break;
                    case "{^+^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("+");
                        check = false;
                        break;
                    case "{^ ^}":
                        result.add(" ");
                        check = false;
                        break;
                    case "{!}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("!");
                        result.add(" ");
                        check = true;
                        break;
                    case "{^!^}":
                        if (before.equals(" ") && !valueBefore.equals("{^ ^}")) {
                            result.remove(result.size() - 1);
                        }
                        result.add("!");
                        check = true;
                        break;
                    default:
                        if (RegexUtils.regexChar(value)) {
                            if (check) {
                                result.add(convert(value));
                            } else {
                                result.add(value);
                            }
                            result.add(" ");
                            check = false;
                        } else {
                            result.add(value);
                        }
                        break;
                }
            }

        }
        StringBuilder builder = new StringBuilder();
        for (String s : result) {
            builder.append(s);
        }

        return HtmlUtils.textToHTML(builder.toString());
    }


    private static ArrayList<StenoObject> getListDict(String content_dict) {
        ArrayList<StenoObject> arrayList = new ArrayList<>();
        JSONObject o = new JSONObject(content_dict);
        Iterator keys = o.keys();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            String value = o.getString(key);
            arrayList.add(new StenoObject(key, value));
        }

        return arrayList;
    }

    private static String convert(String s) {
        StringBuilder x = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i == 0) {
                x = new StringBuilder(String.valueOf(s.charAt(i)).toUpperCase());
            } else {
                x.append(s.charAt(i));
            }
        }
        return x.toString();
    }
}
