package ir.smmh.fy.code;

public class PlainText implements Code {

    private String contents;

    public PlainText(String contents) {
        this.contents = contents;
    }

    @Override
    public String getFilename() {
        return "test";
    }

    @Override
    public String getExt() {
        return "txt";
    }

    @Override
    public String getContents() {
        return contents;
    }

    @Override
    public void setContents(final String contents) {
        this.contents = contents;
    }

    @Override
    public boolean isModified() {
        return true;
    }
}