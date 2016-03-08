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
public class HistoryOrderDetailBean {
    private String order_number;

    public HistoryOrderDetailBean(String order_number) {
        this.order_number = order_number;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }
    
}
