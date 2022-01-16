/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.common;

import java.util.List;

/**
 *
 * @author teodo
 */
public class ProductCatalogDetails implements java.io.Serializable{
    private Integer id;
    private List<ProductDetails> productSpecification;

    public ProductCatalogDetails(Integer id, List<ProductDetails> productSpecification) {
        this.id = id;
        this.productSpecification = productSpecification;
    }

    public Integer getId() {
        return id;
    }

    public List<ProductDetails> getProductSpecification() {
        return productSpecification;
    }
    
}
