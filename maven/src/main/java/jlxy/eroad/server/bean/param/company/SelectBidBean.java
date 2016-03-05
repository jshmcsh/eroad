package jlxy.eroad.server.bean.param.company;

public class SelectBidBean {

    private String order_id;
    private String car_id;
    private String company_id;
    private String price;

    public SelectBidBean(String order_id, String car_id, String company_id, String price) {
        this.order_id = order_id;
        this.car_id = car_id;
        this.company_id = company_id;
        this.price = price;
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

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    
}
