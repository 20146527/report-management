package ubnd.common.utils;

public class ConvertUnsigned {

    private String charact;

    public ConvertUnsigned() {
        // 0-&gt;16
        // a,// ă,// â
        char[] charA = {'à', 'á', 'ạ', 'ả', 'ã',// 0-&gt;16
                'â', 'ầ', 'ấ', 'ậ', 'ẩ', 'ẫ', 'ă', 'ằ', 'ắ', 'ặ', 'ẳ', 'ẵ'};
        // 17-&gt;27
        // e
        char[] charE = {'ê', 'ề', 'ế', 'ệ', 'ể', 'ễ',// 17-&gt;27
                'è', 'é', 'ẹ', 'ẻ', 'ẽ'};
        // i 28-&gt;32
        char[] charI = {'ì', 'í', 'ị', 'ỉ', 'ĩ'};
        // o 33-&gt;49
        // ô
        // ơ
        char[] charO = {'ò', 'ó', 'ọ', 'ỏ', 'õ',// o 33-&gt;49
                'ô', 'ồ', 'ố', 'ộ', 'ổ', 'ỗ',// ô
                'ơ', 'ờ', 'ớ', 'ợ', 'ở', 'ỡ'};
        // u 50-&gt;60
        // ư
        char[] charU = {'ù', 'ú', 'ụ', 'ủ', 'ũ',// u 50-&gt;60
                'ư', 'ừ', 'ứ', 'ự', 'ử', 'ữ'};
        // y 61-&gt;65
        char[] charY = {'ỳ', 'ý', 'ỵ', 'ỷ', 'ỹ'};
        // 66-67
        char[] charD = {'đ', ' '};
        charact = String.valueOf(charA, 0, charA.length)
                + String.valueOf(charE, 0, charE.length)
                + String.valueOf(charI, 0, charI.length)
                + String.valueOf(charO, 0, charO.length)
                + String.valueOf(charU, 0, charU.length)
                + String.valueOf(charY, 0, charY.length)
                + String.valueOf(charD, 0, charD.length);
    }

    private char GetAlterChar(char pC) {
        if ((int) pC == 32) {
            return ' ';
        }

        int i = 0;
        while (i < charact.length() && charact.charAt(i) != pC) {
            i++;
        }
        if (i > 67)
            return pC;

        if (i == 66) {
            return 'd';
        }
        if (i <= 16) {
            return 'a';
        }
        if (i <= 27) {
            return 'e';
        }
        if (i <= 32) {
            return 'i';
        }
        if (i <= 49) {
            return 'o';
        }
        if (i <= 60) {
            return 'u';
        }
        if (i <= 65) {
            return 'y';
        }
        return pC;
    }

    public String ConvertString(String pStr) {

        String convertString = pStr.toLowerCase();

        Character[] returnString = new Character[convertString.length()];
        for (int i = 0; i < convertString.length(); i++) {
            char temp = convertString.charAt(i);
            if ((int) temp < 97 || temp > 122) {
                char tam1 = this.GetAlterChar(temp);
                if ((int) temp != 32)
                    convertString = convertString.replace(temp, tam1);
            }
        }
        return convertString;
    }
}
