package jlxy.eroad.server.bean.result.platform;

/**
 *
 * @author yecq
 */
public class UserSimpleInfoBean {

    private String id;
    private String username;
    private String type;
    private String status;

    public UserSimpleInfoBean() {
    }

    public UserSimpleInfoBean(String id, String username, String type, String status) {
        this.id = id;
        this.username = username;
        this.type = type;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
