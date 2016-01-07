package jlxy.eroad.server.bean.param.platform;

/**
 *
 * @author yecq
 */
public class GetUserDetailBean {

    private String type;
    private String id;

    public GetUserDetailBean(String type, String id) {
        this.type = type;
        this.id = id;
    }

    public GetUserDetailBean() {
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
