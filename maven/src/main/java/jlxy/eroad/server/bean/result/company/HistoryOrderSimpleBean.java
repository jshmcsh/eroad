package jlxy.eroad.server.bean.result.company;

public class HistoryOrderSimpleBean {

    private String order_id;
    private String order_code;
    private String send_addr;
    private String recv_addr;
    private String start_time;
    private String finish_time;
    private String status;

    public HistoryOrderSimpleBean() {
    }

    public HistoryOrderSimpleBean(String order_id, String order_code, String send_addr, String recv_addr, String start_time, String finish_time, String status) {
        this.order_id = order_id;
        this.order_code = order_code;
        this.send_addr = send_addr;
        this.recv_addr = recv_addr;
        this.start_time = start_time;
        this.finish_time = finish_time;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getRecv_addr() {
        return recv_addr;
    }

    public void setRecv_addr(String recv_addr) {
        this.recv_addr = recv_addr;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(String finish_time) {
        this.finish_time = finish_time;
    }
}
