/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.classes;

import com.posn.nextgenpos.classes.Register;
import com.posn.nextgenpos.entity.ProductCatalog;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kicky
 */
public class Store {
    
    private String name;
    private String address;
    private ProductCatalog productCatalog;
    private Register register= new Register(productCatalog);
    
    Store(){}
    
    public Store( String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ProductCatalog getProductCatalog(){
        return productCatalog;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }
    
    public void setProductCatalog(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }    
}
