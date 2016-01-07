package jlxy.eroad.server.bean.result.platform;

/**
 *
 * @author yecq
 */
public class UserDetailCarBean {

    private String id;
    private String plate_number;
    private String car_photo_url;
    private String driver_name;
    private String driver_number;
    private String driver_photo_url;
    private String status;

    public UserDetailCarBean() {
    }

    public UserDetailCarBean(String id, String plate_number, String car_photo_url, String driver_name, String driver_number, String driver_photo_url, String status) {
        this.id = id;
        this.plate_number = plate_number;
        this.car_photo_url = car_photo_url;
        this.driver_name = driver_name;
        this.driver_number = driver_number;
        this.driver_photo_url = driver_photo_url;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getCar_photo_url() {
        return car_photo_url;
    }

    public void setCar_photo_url(String car_photo_url) {
        this.car_photo_url = car_photo_url;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getDriver_number() {
        return driver_number;
    }

    public void setDriver_number(String driver_number) {
        this.driver_number = driver_number;
    }

    public String getDriver_photo_url() {
        return driver_photo_url;
    }

    public void setDriver_photo_url(String driver_photo_url) {
        this.driver_photo_url = driver_photo_url;
    }
}
