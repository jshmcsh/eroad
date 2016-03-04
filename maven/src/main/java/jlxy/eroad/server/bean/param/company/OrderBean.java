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
public class OrderBean {
    private String order_number;
    private String start_time;
    private String expect_end_time;
    private String expect_fare;
    private String description;
    private String start_address;
    private String destination;
    
    public OrderBean(String order_number,String start_time,String expect_end_time,String expect_fare,String description,String start_address,String destination){
        this.order_number=order_number;
        this.start_time=start_time;
        this.expect_end_time=expect_end_time;
        this.expect_fare=expect_fare;
        this.description=description;
        this.start_address=start_address;
        this.destination=destination;
    }
    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
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

    public String getExpect_fare() {
        return expect_fare;
    }

    public void setExpect_fare(String expect_fare) {
        this.expect_fare = expect_fare;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart_address() {
        return start_address;
    }

    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
