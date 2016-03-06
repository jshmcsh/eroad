package jlxy.eroad.server.bean.param.company;

public class CancelLaunchingOrderBean {

    private String order_id;

    public CancelLaunchingOrderBean(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

   
}
