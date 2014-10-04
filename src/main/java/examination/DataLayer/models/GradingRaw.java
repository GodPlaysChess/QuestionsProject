package examination.DataLayer.models;

/**
 * author: a.savanovich
 * Date: 04.10.14
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */
public class GradingRaw implements BaseModel {
    private int systemId;
    private int percent;
    private int markCode;

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getMarkCode() {
        return markCode;
    }

    public void setMarkCode(int markCode) {
        this.markCode = markCode;
    }

    @Override
    public long getId() {
        return systemId;
    }
}
