/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.common;

/**
 *
 * @author barb_
 */
public class ReturnDetails {
    private Integer id;
    private Integer saleId;
    
    public ReturnDetails(Integer id, Integer saleId) {
        this.id = id;
        this.saleId = saleId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSaleId() {
        return saleId;
    }
    
    
}
