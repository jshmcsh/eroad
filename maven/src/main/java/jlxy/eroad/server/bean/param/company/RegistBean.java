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
public class RegistBean {
    private String username;
    private String passwd;
    private String company_name;
    private String company_license;
    private String phone_number;
    private String company_licence_path;
    private String company_represent;

    public RegistBean(String username, String passwd, String company_name, String company_license, String phone_number, String company_licence_path, String company_represent) {
        this.username = username;
        this.passwd = passwd;
        this.company_name = company_name;
        this.company_license = company_license;
        this.phone_number = phone_number;
        this.company_licence_path = company_licence_path;
        this.company_represent = company_represent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
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

    public String getCompany_licence_path() {
        return company_licence_path;
    }

    public void setCompany_licence_path(String company_licence_path) {
        this.company_licence_path = company_licence_path;
    }

    public String getCompany_represent() {
        return company_represent;
    }

    public void setCompany_represent(String company_represent) {
        this.company_represent = company_represent;
    }
    
}
