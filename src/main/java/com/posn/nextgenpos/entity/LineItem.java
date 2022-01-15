/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.entity;

import com.posn.nextgenpos.allinterfaces.Prototype;
import com.posn.nextgenpos.common.LineDetails;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

/**
 *
 * @author teodo
 */
@Entity
@Table(name="SALE_LINE_ITEMS")
public class LineItem implements Serializable, Prototype<LineDetails> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    @Min(0)
    private int quantity;  
    //private int itemId, saleID;-> tabel
    @ManyToOne
    @JoinColumn(name="sale_id")
    private Sale sale;
    
    @ManyToOne
    @JoinColumn(name="productspec_id", referencedColumnName="id")
    private ProductSpecification prodSpecs;
    
    @ManyToOne
    @JoinColumn(name="return_id")
    private Return returns;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }
  
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductSpecification getProdSpecs() {
        return prodSpecs;
    }

    public void setProdSpecs(ProductSpecification prodSpecs) {
        this.prodSpecs = prodSpecs;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Return getReturns() {
        return returns;
    }

    public void setReturns(Return returns) {
        this.returns = returns;
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
        if (!(object instanceof LineItem)) {
            return false;
        }
        LineItem other = (LineItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.posn.nextgenpos.entity.LineItem[ id=" + id + " ]";
    }

    @Override
    public LineDetails clone() {
        return new LineDetails(this.id,this.quantity);
    }
    
}
