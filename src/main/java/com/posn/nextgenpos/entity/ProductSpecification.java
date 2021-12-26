/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author teodo
 */
@Entity
@Table(name = "PRODUCT_SPECIFICATION")
public class ProductSpecification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    private String name;
    private String description;
    private Double pricePerUnit;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="item_id", referencedColumnName="id")
    private Item item;
    
    @OneToMany(mappedBy="prodSpecs")
    private List<SaleLineItem> lineItem;
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Item getItem() {
        return item;
    }

    public List<SaleLineItem> getLineItem() {
        return lineItem;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setLineItem(List<SaleLineItem> lineItem) {
        this.lineItem = lineItem;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductSpecification)) {
            return false;
        }
        ProductSpecification other = (ProductSpecification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String s;
        s="ID: "+this.id+"\nName: "+this.name+"\nDescription: "+this.description+"\nPricePerUnit:"+this.pricePerUnit;
        return s;
    }
    
}
