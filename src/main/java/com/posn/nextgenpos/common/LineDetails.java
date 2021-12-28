/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.common;

import com.posn.nextgenpos.entity.ProductSpecification;

/**
 *
 * @author teodo
 */
public class LineDetails implements java.io.Serializable {
    private Integer id;
    private int quantity;

    public LineDetails(Integer id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
}
