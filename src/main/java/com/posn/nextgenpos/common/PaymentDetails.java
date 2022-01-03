/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.common;

/**
 *
 * @author teodo
 */
public class PaymentDetails implements java.io.Serializable {
    private Integer id;
    private double amount;
    private double total;

    public PaymentDetails(Integer id, double amount, double total) {
        this.id = id;
        this.amount = amount;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public double getTotal() {
        return total;
    }   
}
