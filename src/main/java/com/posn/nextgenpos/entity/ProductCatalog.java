/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.entity;

import com.posn.nextgenpos.allinterfaces.Prototype;
import com.posn.nextgenpos.common.ProductCatalogDetails;
import com.posn.nextgenpos.common.ProductDetails;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author teodo
 */
@Entity
@Table(name="CATALOG")
public class ProductCatalog implements Serializable, Prototype<ProductCatalogDetails>{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private List<ProductDetails> productSpecification;
    private static ProductCatalog instance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public static ProductCatalog getInstance() {
        if(instance == null)
            instance = new ProductCatalog();
        return instance;
    }
       
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<ProductDetails> getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(List<ProductDetails> productSpecification) {
        this.productSpecification = productSpecification;
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
        if (!(object instanceof ProductCatalog)) {
            return false;
        }
        ProductCatalog other = (ProductCatalog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.posn.nextgenpos.entity.ProductCatalog[ id=" + id + " ]";
    }

    @Override
    public ProductCatalogDetails clone() {
        return new ProductCatalogDetails(this.getId(), this.getProductSpecification());
    }  
}
