package AppUtils;

public class FileName {
    private String oldName;
    private String newName;
    private String extension;

    public FileName() {
    }

    public FileName(String fileName) {
        this.oldName = fileName;
        this.newName = "";
        if (oldName.indexOf(".") > 0) {
            this.extension = oldName.substring(oldName.lastIndexOf("."));
        } else {
            this.extension = "";
        }
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
