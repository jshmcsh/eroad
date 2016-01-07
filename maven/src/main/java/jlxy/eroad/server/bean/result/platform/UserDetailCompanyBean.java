package jlxy.eroad.server.bean.result.platform;

import jlxy.eroad.server.bean.result.PositionBean;

/**
 *
 * @author yecq
 */
public class UserDetailCompanyBean {

    private String id;
    private String name;
    private String address;
    private String province;
    private String city;
    private PositionBean position;
    private String license_number;
    private String license_photo_url;
    private String status;

    public UserDetailCompanyBean() {
    }

    public UserDetailCompanyBean(String id, String name, String address, String province, String city, PositionBean position, String license_number, String license_photo_url, String status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.province = province;
        this.city = city;
        this.position = position;
        this.license_number = license_number;
        this.license_photo_url = license_photo_url;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public PositionBean getPosition() {
        return position;
    }

    public void setPosition(PositionBean position) {
        this.position = position;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public String getLicense_photo_url() {
        return license_photo_url;
    }

    public void setLicense_photo_url(String license_photo_url) {
        this.license_photo_url = license_photo_url;
    }
}
