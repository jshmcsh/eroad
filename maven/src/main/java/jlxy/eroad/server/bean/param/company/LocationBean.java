/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlxy.eroad.server.bean.param.company;

/**
 *
 * @author CP
 */
public class LocationBean {
    private String province;
    private String city;
    private String latitude;
    private String longtitude;
    
    public LocationBean(){}
    public LocationBean(String province, String city, String latitude, String longtitude) {
        this.province = province;
        this.city = city;
        this.latitude = latitude;
        this.longtitude = longtitude;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }
    
    
}
