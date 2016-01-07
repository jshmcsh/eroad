package jlxy.eroad.server.bean.result;

public class PositionBean {

    private String x;
    private String y;

    public PositionBean() {
    }

    public PositionBean(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }
}
