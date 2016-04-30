package jlxy.eroad.server.bean.result.company;

public class BiddingCarBean {

    private String username;
    private String phone_number;
    private String car_number;
    private String price;
    
    public BiddingCarBean(){}
    public BiddingCarBean(String username, String phone_number, String car_number, String price) {
        this.username = username;
        this.phone_number = phone_number;
        this.car_number = car_number;
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

   
}
