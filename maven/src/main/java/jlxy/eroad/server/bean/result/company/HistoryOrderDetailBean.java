package jlxy.eroad.server.bean.result.company;

import java.util.List;
import jlxy.eroad.server.bean.result.PositionBean;

public class HistoryOrderDetailBean {

    private CarSimpleBean car;
    private List<PositionBean> positions;

    public HistoryOrderDetailBean() {
    }

    public HistoryOrderDetailBean(CarSimpleBean car, List<PositionBean> positions) {
        this.car = car;
        this.positions = positions;
    }

    public List<PositionBean> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionBean> positions) {
        this.positions = positions;
    }

    public CarSimpleBean getCar() {
        return car;
    }

    public void setCar(CarSimpleBean car) {
        this.car = car;
    }
}
