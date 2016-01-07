package jlxy.eroad.server.bean.result.regular;

import java.util.List;
import jlxy.eroad.server.bean.result.PositionBean;

public class SearchOrderResultBean {

    private String plate_number;
    private List<PositionBean> positions;

    public SearchOrderResultBean(String plateNumber, List<PositionBean> positions) {
        this.plate_number = plateNumber;
        this.positions = positions;
    }

    public SearchOrderResultBean() {
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plateNumber) {
        plate_number = plateNumber;
    }

    public List<PositionBean> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionBean> positions) {
        this.positions = positions;
    }
}
