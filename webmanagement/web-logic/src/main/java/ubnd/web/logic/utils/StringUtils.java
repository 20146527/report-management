package ubnd.web.logic.utils;

public class StringUtils {
    static boolean checkTypeString(String s) {
        try {
            return Character.isUpperCase(s.charAt(0));
        } catch (Exception e) {
            return false;
        }

    }

    static String checkPunctuation(String content, String before, String after, String after1) {
        String s;
        switch (content) {
            case "`":
                //1
                s = "{^`^}";
                break;
            case "^":
                //1
                s = "{^^^}";
                break;
            case "~":
                //2
                if (before.equals("") && after.equals(" ")) {
                    s = "{^~}";
                } else {
                    s = "{^~^}";
                }
                break;
            case "<":
                //1
                s = "{^<^}";
                break;
            case "=":
                //1
                s = "{^=^}";
                break;
            case ">":
                //1
                s = "{^>^}";
                break;
            case "|":
                //1
                s = "{^|^}";
                break;
            case "_":
                //1
                s = "{^_^}";
                break;
            case "-":
                //1
                s = "{^-^}";
                break;
            case ",":
                if (before.equals("") && after.equals(" ")) {
                    s = "{,}";
                } else {
                    s = "{^,^}";
                }
                break;
            case ";":
                //2
                if (before.equals("") && after.equals(" ")) {
                    s = "{;}";
                } else {
                    s = "{^;^}";
                }
                break;
            case ":":
                //3
                if (before.equals("") && after.equals(" ") && checkTypeString(after1)) {
                    s = "{:}";
                } else {
                    s = "{^:^}";
                }
                break;
            case "?":
                //3
                if (before.equals("") && after.equals(" ") && checkTypeString(after1)) {
                    s = "{?}";
                } else {
                    s = "{^:^}";
                }
                break;
            case "/":
                s = "{^/^}";
                break;
            case "'":
                //4
                if (before.equals(" ") && !after.equals(" ")) {
                    s = "{'^}";
                } else if (!before.equals(" ") && after.equals(" ")) {
                    s = "{^'}";
                } else {
                    s = "{^'^}";
                }
                break;
            case "\"":
                //4
                if (before.equals(" ") && !after.equals(" ")) {
                    s = "{\"^}";
                } else if (!before.equals(" ") && after.equals(" ")) {
                    s = "{^\"}";
                } else {
                    s = "{^\"^}";
                }
                break;
            case "(":
                //3
                if (before.equals(" ") && !after.equals(" ")) {
                    s = "{(^}";
                } else {
                    s = "{^(^}";
                }
                break;
            case ")":
                //3
                if (!before.equals(" ") && after.equals(" ")) {
                    s = "{^)}";
                } else {
                    s = "{^)^}";
                }
                break;
            case "[":
                //3
                if (before.equals(" ") && !after.equals(" ")) {
                    s = "{[^}";
                } else {
                    s = "{^]^}";
                }
                break;
            case "]":
                //3
                if (!before.equals(" ") && after.equals(" ")) {
                    s = "{[^}";
                } else {
                    s = "{^[^}";
                }
                break;
            case "{":
                //3
                if (before.equals(" ") && !after.equals(" ")) {
                    s = "{{^}";
                } else {
                    s = "{^}^}";
                }
                break;
            case "}":
                //3
                if (!before.equals(" ") && after.equals(" ")) {
                    s = "{{^}";
                } else {
                    s = "{^{^}";
                }
                break;
            case "@":
                s = "{^@^}";
                break;
            case "$":
                s = "{$^}";
                break;
            case "*":
                s = "{^*^}";
                break;
            case "\\":
                s = "{^\\^}";
                break;
            case "#":
                s = "{#^}";
                break;
            case "%":
                s = "{&%}";
                break;
            case "+":
                s = "{^+^}";
                break;
            case ".":
                if (!before.equals(" ") && after.equals(" ") && StringUtils.checkTypeString(after1)) {
                    s = "{.}";
                } else {
                    s = "{^.^}";
                }
                break;
            case "!":
                if (!before.equals(" ") && after.equals(" ") && StringUtils.checkTypeString(after1)) {
                    s = "{!}";
                } else {
                    s = "{^!^}";
                }
                break;
            default:
                s = content;
                break;
        }

        return s;
    }
}
