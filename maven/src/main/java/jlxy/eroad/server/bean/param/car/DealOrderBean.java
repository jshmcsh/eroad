package jlxy.eroad.server.bean.param.car;

public class DealOrderBean {

    private String order_id;
    private double price;

    public DealOrderBean() {
    }

    public DealOrderBean(String order_id, double price) {
        this.order_id = order_id;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
