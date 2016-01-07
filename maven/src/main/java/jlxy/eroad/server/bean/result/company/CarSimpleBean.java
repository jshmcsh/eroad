package jlxy.eroad.server.bean.result.company;

public class CarSimpleBean {

    private String car_id;
    private String plate_number;
    private String status;
    private int credit;

    public CarSimpleBean() {
    }

    public CarSimpleBean(String car_id, String plate_number, String status, int credit) {
        this.car_id = car_id;
        this.plate_number = plate_number;
        this.status = status;
        this.credit = credit;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
