/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.common;

import com.posn.nextgenpos.allinterfaces.Prototype;
/**
 *
 * @author teodo
 */
public class ProductDetails implements java.io.Serializable, Prototype<ProductDetails>{
    private Integer id;
    private String name;
    private String description;
    private Double pricePerUnit;

    public ProductDetails(Integer id, String name, String description, Double pricePerUnit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pricePerUnit = pricePerUnit;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    @Override
    public ProductDetails clone() {
        return new ProductDetails(this.id, this.name, this.description, this.pricePerUnit+this.pricePerUnit*19/100);
    }
    
}
