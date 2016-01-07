package jlxy.eroad.server.bean.param.regular;

public class SearchOrderBean {

    private String order_code;

    public SearchOrderBean() {
    }

    public SearchOrderBean(String order_code) {
        this.order_code = order_code;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String orderCode) {
        order_code = orderCode;
    }
}
