/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.common;

import com.posn.nextgenpos.entity.ProductSpecification;
import java.util.List;

/**
 *
 * @author teodo
 */
public class ProductCatalogDetails {
    private Long id;
    private List<ProductSpecification> productSpecification;

    public ProductCatalogDetails(Long id, List<ProductSpecification> productSpecification) {
        this.id = id;
        this.productSpecification = productSpecification;
    }

    public Long getId() {
        return id;
    }

    public List<ProductSpecification> getProductSpecification() {
        return productSpecification;
    }
    
}
