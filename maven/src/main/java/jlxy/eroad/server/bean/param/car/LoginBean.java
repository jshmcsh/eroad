package jlxy.eroad.server.bean.param.car;

import jlxy.eroad.server.bean.result.PositionBean;

public class LoginBean {

    private String username;
    private String password;
    private PositionBean position;

    public LoginBean() {
    }

    public LoginBean(String username, String password, PositionBean position) {
        this.username = username;
        this.password = password;
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PositionBean getPosition() {
        return position;
    }

    public void setPosition(PositionBean position) {
        this.position = position;
    }
}
