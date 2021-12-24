/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.common;

import java.time.LocalDateTime;

/**
 *
 * @author teodo
 */
public class SaleDetails implements java.io.Serializable {
    private Integer id;
    private LocalDateTime date;
    private boolean isComplete;
    private double total;
    private double change;

    public SaleDetails(Integer id, LocalDateTime date, boolean isComplete, double total, double change) {
        this.id = id;
        this.date = date;
        this.isComplete = isComplete;
        this.total = total;
        this.change = change;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isIsComplete() {
        return isComplete;
    }

    public double getTotal() {
        return total;
    }

    public double getChange() {
        return change;
    }
    
}
