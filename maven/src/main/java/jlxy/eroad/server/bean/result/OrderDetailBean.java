package jlxy.eroad.server.bean.result;

public class OrderDetailBean {

    private String order_id;
    private String order_code;
    private String description;

    private String send_province;
    private String send_city;
    private PositionBean send_x;
    private String send_time;

    private String recv_province;
    private String recv_city;
    private PositionBean recv_y;
    private String recv_time;

    private double price;

    public OrderDetailBean() {
    }

    public OrderDetailBean(String order_id, String order_code, String description, String send_province, String send_city, PositionBean send_x, String send_time, String recv_province, String recv_city, PositionBean recv_y, String recv_time, double price) {
        this.order_id = order_id;
        this.order_code = order_code;
        this.description = description;
        this.send_province = send_province;
        this.send_city = send_city;
        this.send_x = send_x;
        this.send_time = send_time;
        this.recv_province = recv_province;
        this.recv_city = recv_city;
        this.recv_y = recv_y;
        this.recv_time = recv_time;
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

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSend_province() {
        return send_province;
    }

    public void setSend_province(String send_province) {
        this.send_province = send_province;
    }

    public String getSend_city() {
        return send_city;
    }

    public void setSend_city(String send_city) {
        this.send_city = send_city;
    }

    public PositionBean getSend_x() {
        return send_x;
    }

    public void setSend_x(PositionBean send_x) {
        this.send_x = send_x;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public String getRecv_province() {
        return recv_province;
    }

    public void setRecv_province(String recv_province) {
        this.recv_province = recv_province;
    }

    public String getRecv_city() {
        return recv_city;
    }

    public void setRecv_city(String recv_city) {
        this.recv_city = recv_city;
    }

    public PositionBean getRecv_y() {
        return recv_y;
    }

    public void setRecv_y(PositionBean recv_y) {
        this.recv_y = recv_y;
    }

    public String getRecv_time() {
        return recv_time;
    }

    public void setRecv_time(String recv_time) {
        this.recv_time = recv_time;
    }
}
