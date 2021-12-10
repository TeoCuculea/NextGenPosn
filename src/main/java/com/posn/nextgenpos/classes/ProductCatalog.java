/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.classes;

import com.posn.nextgenpos.entity.ProductSpecification;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stoic
 */
public class ProductCatalog {
    
    List<ProductSpecification> productSpecification;
    
    public ProductCatalog(){
        productSpecification = new ArrayList<>();
    }
    
    /*public ProductSpecification getProductDescription(int itemId) {
        for(ProductSpecification prod:productSpecification){
            if(prod.getProductSpecificationId()==itemId){
                return prod;
            }
        }
        return null;
    }*/
    
    public void addProductSpecification(ProductSpecification prodSpecs)
    {
        this.productSpecification.add(prodSpecs);
    }
    
    public List<ProductSpecification> getProdSpecs()
    {
        return productSpecification;
    }
}
