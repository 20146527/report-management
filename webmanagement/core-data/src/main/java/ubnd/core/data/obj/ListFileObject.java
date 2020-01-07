package ubnd.core.data.obj;

import java.util.List;

public class ListFileObject {
    private String nameFolder;
    private String nameDisplay;
    private String path;
    private List<String> listFile;

    public ListFileObject(String path, String nameFolder, String nameDisplay ,List<String> listFile) {
        this.path = path;
        this.nameFolder = nameFolder;
        this.nameDisplay = nameDisplay;
        this.listFile = listFile;
    }

    public String getNameDisplay() {
        return nameDisplay;
    }

    public void setNameDisplay(String nameDisplay) {
        this.nameDisplay = nameDisplay;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNameFolder() {
        return nameFolder;
    }

    public void setNameFolder(String nameFolder) {
        this.nameFolder = nameFolder;
    }

    public List<String> getListFile() {
        return listFile;
    }

    public void setListFile(List<String> listFile) {
        this.listFile = listFile;
    }
}
