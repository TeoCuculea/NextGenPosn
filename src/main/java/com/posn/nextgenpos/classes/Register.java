/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.classes;

import com.posn.nextgenpos.entity.ProductSpecification;
import com.posn.nextgenpos.entity.Sale;

/**
 *
 * @author teodo
 */
public class Register {

    private ProductCatalog catalog;
    private Sale currentSale;//sale details

    public Register(ProductCatalog catalog) {
        this.catalog = catalog;
    }

    public void makeNewSale() {
        currentSale = new Sale();
    }

    /*void endSale(double amount) {
        currentSale.becomeComplete(this, amount);
    }

    void enterItem(int itemId, int itemQuantity) {
        currentSale.makeLineItem(itemId, itemQuantity);
    }

    void makePayment(double amount) {
        currentSale.makePayment(amount);
    }

    double getTotal() {
        return currentSale.getTotal();
    }

    void printShoppingList() {
        currentSale.printShopingList();
    }*/

    public Sale getCurrentSale() {
        return currentSale;
    }
    
}
