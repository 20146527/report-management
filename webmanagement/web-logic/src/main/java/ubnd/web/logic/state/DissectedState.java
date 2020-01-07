package ubnd.web.logic.state;

import ubnd.web.logic.utils.RegexUtils;

public class DissectedState extends State {
    @Override
    public void nextChar(String c) {
        super.nextChar(c);
        if (RegexUtils.regexSpace(c) || RegexUtils.regexPunctuation(c)) {
            state = 0;
            if (s.toString().equals("")) {
                addList(c);
            } else {
                list.add(s.toString());
                s = new StringBuilder();
                list.add(c);
            }
        } else if (RegexUtils.regexNumber(c)) {
            if (state == 2) {
                list.add(s.toString());
                s = new StringBuilder();
            }
            s.append(c);
            state = 1;
        } else if (RegexUtils.regexChar(c)) {
            if (state == 1) {
                list.add(s.toString());
                s = new StringBuilder();
            }
            s.append(c);
            state = 2;
        }
    }
}
