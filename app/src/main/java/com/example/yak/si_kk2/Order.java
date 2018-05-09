package com.example.yak.si_kk2;

/**
 * Created by Yak on 08/05/2018.
 */

public class Order {
    private String orderName;
    private String detilOrder;

    public Order(String orderName, String detilOrder) {
        this.orderName = orderName;
        this.detilOrder = detilOrder;
    }

    public Order() {
    }

    public String getOrderName() {
        return orderName;
    }

    public String getDetilOrder() {
        return detilOrder;
    }
}
