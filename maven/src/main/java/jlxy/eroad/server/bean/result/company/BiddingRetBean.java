/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlxy.eroad.server.bean.result.company;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author CP
 */
public class BiddingRetBean {

    private String create_time;
    private String order_id;
    private String destination;
    private String start_address;
    private String expect_fare;
    private String start_time;
    private String expect_end_time;
    private String sketch;
    private List<BiddingCarBean> bid_car_arr;
    
    public BiddingRetBean(){
        bid_car_arr=new LinkedList();
    }
    public BiddingRetBean(String create_time, String order_id, String destination, String start_address, String expect_fare, String start_time, String expect_end_time, String sketch, List<BiddingCarBean> bid_car_arr) {
        this.create_time = create_time;
        this.order_id = order_id;
        this.destination = destination;
        this.start_address = start_address;
        this.expect_fare = expect_fare;
        this.start_time = start_time;
        this.expect_end_time = expect_end_time;
        this.sketch = sketch;
        this.bid_car_arr = bid_car_arr;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }

    public String getExpect_fare() {
        return expect_fare;
    }

    public void setExpect_fare(String expect_fare) {
        this.expect_fare = expect_fare;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getExpect_end_time() {
        return expect_end_time;
    }

    public void setExpect_end_time(String expect_end_time) {
        this.expect_end_time = expect_end_time;
    }

    public String getSketch() {
        return sketch;
    }

    public void setSketch(String sketch) {
        this.sketch = sketch;
    }

    public List<BiddingCarBean> getBid_car_arr() {
        return bid_car_arr;
    }

    public void setBid_car_arr(List<BiddingCarBean> bid_car_arr) {
        this.bid_car_arr = bid_car_arr;
    }
    
    
}
