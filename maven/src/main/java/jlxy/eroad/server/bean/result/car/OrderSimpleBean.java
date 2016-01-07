package jlxy.eroad.server.bean.result.car;

public class OrderSimpleBean {

    private String order_id;
    private String send_time;
    private String start_addr;
    private String end_addr;
    private String start_time;
    private String end_time;
    private double price;

    public OrderSimpleBean() {
    }

    public OrderSimpleBean(String order_id, String send_time, String start_addr, String end_addr, String start_time, String end_time, double price) {
        this.order_id = order_id;
        this.send_time = send_time;
        this.start_addr = start_addr;
        this.end_addr = end_addr;
        this.start_time = start_time;
        this.end_time = end_time;
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

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public String getStart_addr() {
        return start_addr;
    }

    public void setStart_addr(String start_addr) {
        this.start_addr = start_addr;
    }

    public String getEnd_addr() {
        return end_addr;
    }

    public void setEnd_addr(String end_addr) {
        this.end_addr = end_addr;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
