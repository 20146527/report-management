package ubnd.web.logic.utils;

class NumberUtils {
    private static String[] number = {"O", "S", "T", "P", "H", "A", "F", "-P", "L", "-T"};

    static String getNumberSteno(String n) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n.length(); i++) {
            int x = Integer.parseInt(String.valueOf(n.charAt(i)));
            if (i == 0) {
                s = new StringBuilder("#" + number[x]);
            } else {
                s.append(" ").append("#").append(number[x]);
            }
        }
        return s.toString();
    }

    static String convertStenoToNumber(String s) {
        String x = String.valueOf(s.charAt(1));
        for (int i = 0; i < number.length; i++) {
            if (number[i].equals(x)) {
                return String.valueOf(i);
            }
        }
        return null;
    }
}
