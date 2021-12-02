package ir.smmh.fy.code;

public interface Code {
    String getFilename();

    String getExt();

    String getContents();

    void setContents(String contents);

    boolean isModified();
}
