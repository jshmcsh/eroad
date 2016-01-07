package jlxy.eroad.server.bean.result.company;

import java.util.List;
import jlxy.eroad.server.bean.result.car.OrderSimpleBean;

public class BiddingOrderBean {

    private OrderSimpleBean simple;
    private List<BiddingCarBean> cars;

    public BiddingOrderBean() {
    }

    public BiddingOrderBean(OrderSimpleBean simple, List<BiddingCarBean> cars) {
        this.simple = simple;
        this.cars = cars;
    }

    public List<BiddingCarBean> getCars() {
        return cars;
    }

    public void setCars(List<BiddingCarBean> cars) {
        this.cars = cars;
    }

    public OrderSimpleBean getSimple() {
        return simple;
    }

    public void setSimple(OrderSimpleBean simple) {
        this.simple = simple;
    }
}
