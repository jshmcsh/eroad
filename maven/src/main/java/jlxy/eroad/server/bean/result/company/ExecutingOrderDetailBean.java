package jlxy.eroad.server.bean.result.company;

import java.util.List;
import jlxy.eroad.server.bean.result.PositionBean;

public class ExecutingOrderDetailBean {

    private CarSimpleBean car;
    private PositionBean start_position;
    private PositionBean recv_position;
    private List<PositionBean> positions;

    public ExecutingOrderDetailBean() {
    }

    public ExecutingOrderDetailBean(CarSimpleBean car, PositionBean start_position, PositionBean recv_position, List<PositionBean> positions) {
        this.car = car;
        this.start_position = start_position;
        this.recv_position = recv_position;
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

    public PositionBean getStart_position() {
        return start_position;
    }

    public void setStart_position(PositionBean start_position) {
        this.start_position = start_position;
    }

    public PositionBean getRecv_position() {
        return recv_position;
    }

    public void setRecv_position(PositionBean recv_position) {
        this.recv_position = recv_position;
    }
}
