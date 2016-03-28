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
public class CompanyBean {
    private String id;
    private String username;
    private String state;
    private String company_name;
    private String company_license;
    private String phone_number;
    private String companylicence_pic_path;
    private String company_represent;

    public CompanyBean(String id, String username, String state, String company_name, String company_license, String phone_number, String companylicence_pic_path, String company_represent) {
        this.id = id;
        this.username = username;
        this.state = state;
        this.company_name = company_name;
        this.company_license = company_license;
        this.phone_number = phone_number;
        this.companylicence_pic_path = companylicence_pic_path;
        this.company_represent = company_represent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_license() {
        return company_license;
    }

    public void setCompany_license(String company_license) {
        this.company_license = company_license;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCompanylicence_pic_path() {
        return companylicence_pic_path;
    }

    public void setCompanylicence_pic_path(String companylicence_pic_path) {
        this.companylicence_pic_path = companylicence_pic_path;
    }

    public String getCompany_represent() {
        return company_represent;
    }

    public void setCompany_represent(String company_represent) {
        this.company_represent = company_represent;
    }
    
    
    
}
