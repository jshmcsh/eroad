package jlxy.eroad.server.bean.result.company;

public class BiddingCarBean {

    private String car_id;
    private String plate_number;
    private int credit;
    private double price;

    public BiddingCarBean() {
    }

    public BiddingCarBean(String car_id, String plate_number, int credit, double price) {
        this.car_id = car_id;
        this.plate_number = plate_number;
        this.credit = credit;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
