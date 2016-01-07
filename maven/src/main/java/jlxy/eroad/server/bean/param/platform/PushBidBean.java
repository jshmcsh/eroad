package jlxy.eroad.server.bean.param.platform;

import jlxy.eroad.server.bean.result.OrderDetailBean;

/**
 *
 * @author yecq
 */
public class PushBidBean {

    private OrderDetailBean order;

    public PushBidBean() {
    }

    public PushBidBean(OrderDetailBean order) {
        this.order = order;
    }

    public OrderDetailBean getOrder() {
        return order;
    }

    public void setOrder(OrderDetailBean order) {
        this.order = order;
    }
}
