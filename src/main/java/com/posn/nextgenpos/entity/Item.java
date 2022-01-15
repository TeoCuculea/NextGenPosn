/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.entity;

import com.posn.nextgenpos.allinterfaces.Prototype;
import com.posn.nextgenpos.common.ItemDetails;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

/**
 *
 * @author teodo
 */
@Entity
@Table(name = "ITEMS")
public class Item implements Serializable, Prototype<ItemDetails> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    
    @Min(0)
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne(mappedBy = "item")
    private ProductSpecification productSpecification;

    public Integer getQuantity() {
        return quantity;
    }

    public ProductSpecification getProductSpecification() {
        return productSpecification;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
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
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.posn.nextgenpos.entity.Item[ id=" + id + " ]";
    }

    @Override
    public ItemDetails clone() {
        return new ItemDetails(this.getId(), this.getQuantity());
    }

}
