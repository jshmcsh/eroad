package jlxy.eroad.server.bean.param.company;

public class CancelLaunchingOrderBean {

    private int order_id;
    private int company_id;

    public CancelLaunchingOrderBean(int order_id, int company_id) {
        this.order_id = order_id;
        this.company_id = company_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }


   
}
