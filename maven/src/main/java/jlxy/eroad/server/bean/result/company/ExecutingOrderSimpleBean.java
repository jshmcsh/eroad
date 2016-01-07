package jlxy.eroad.server.bean.result.company;

public class ExecutingOrderSimpleBean {

    private String order_id;
    private String order_code;
    private String send_addr;
    private String recv_addr;

    public ExecutingOrderSimpleBean() {
    }

    public ExecutingOrderSimpleBean(String order_id, String order_code, String send_addr, String recv_addr) {
        this.order_id = order_id;
        this.order_code = order_code;
        this.send_addr = send_addr;
        this.recv_addr = recv_addr;
    }

    public String getRecv_addr() {
        return recv_addr;
    }

    public void setRecv_addr(String recv_addr) {
        this.recv_addr = recv_addr;
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

    public String getSend_addr() {
        return send_addr;
    }

    public void setSend_addr(String send_addr) {
        this.send_addr = send_addr;
    }
}
