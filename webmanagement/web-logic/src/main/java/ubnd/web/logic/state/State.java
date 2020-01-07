package ubnd.web.logic.state;

import java.util.ArrayList;
import java.util.List;

public class State {
    public List<String> list = new ArrayList<>();
    public StringBuilder s = new StringBuilder();
    public int state = 0;

    public void addList(String s) {
        list.add(s);
    }

    public void nextChar(String c) {

    }

    public List<String> getList(){
        return list;
    }
}
