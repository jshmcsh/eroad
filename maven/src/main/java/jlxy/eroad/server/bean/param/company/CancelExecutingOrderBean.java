package jlxy.eroad.server.bean.param.company;

public class CancelExecutingOrderBean {

    private String order_id;
    private String car_id;
    private String reason;
    private int evaluate;

    public CancelExecutingOrderBean() {
    }

    public CancelExecutingOrderBean(String order_id, String car_id, String reason, int evaluate) {
        this.order_id = order_id;
        this.car_id = car_id;
        this.reason = reason;
        this.evaluate = evaluate;
    }

    public int getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(int evaluate) {
        this.evaluate = evaluate;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
