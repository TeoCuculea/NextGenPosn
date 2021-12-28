/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author barb_
 */
@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @ManyToMany
    @JoinTable(
            name="CATEGORY_PRODUCT_SPECIFICATION",
            joinColumns=
                    @JoinColumn(name="CATEGORY_ID",referencedColumnName="ID"),
            inverseJoinColumns=
                    @JoinColumn(name="PRODUCT_SPECIFICATION_ID",referencedColumnName="ID")
    )
    Collection<ProductSpecification> productSpecification;
    
    private String categoryName;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Collection<ProductSpecification> getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(Collection<ProductSpecification> productSpecification) {
        this.productSpecification = productSpecification;
    }
    
    public void addProduct(ProductSpecification prodSpec)
    {
        this.getProductSpecification().add(prodSpec);
    }
    
    public void dropProduct(ProductSpecification prodSpecs)
    {
        this.getProductSpecification().remove(prodSpecs);
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
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.posn.nextgenpos.entity.Category[ id=" + id + " ]";
    }
    
}
