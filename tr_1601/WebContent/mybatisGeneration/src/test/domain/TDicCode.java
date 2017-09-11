package test.domain;

public class TDicCode {
    private String tDicId;

    private String tDicCode;

    private String tName;

    private String tType;

    public String gettDicId() {
        return tDicId;
    }

    public void settDicId(String tDicId) {
        this.tDicId = tDicId == null ? null : tDicId.trim();
    }

    public String gettDicCode() {
        return tDicCode;
    }

    public void settDicCode(String tDicCode) {
        this.tDicCode = tDicCode == null ? null : tDicCode.trim();
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName == null ? null : tName.trim();
    }

    public String gettType() {
        return tType;
    }

    public void settType(String tType) {
        this.tType = tType == null ? null : tType.trim();
    }
}