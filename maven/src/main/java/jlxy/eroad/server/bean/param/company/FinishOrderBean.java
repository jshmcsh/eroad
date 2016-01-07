package jlxy.eroad.server.bean.param.company;

public class FinishOrderBean {

    private String order_id;
    private String car_id;
    private int evaluate;
    private String remark;

    public FinishOrderBean() {
    }

    public FinishOrderBean(String order_id, String car_id, int evaluate, String remark) {
        this.order_id = order_id;
        this.car_id = car_id;
        this.evaluate = evaluate;
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public int getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(int evaluate) {
        this.evaluate = evaluate;
    }
}
