/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.posn.nextgenpos.entity;

import com.posn.nextgenpos.classes.Register;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author teodo
 */
@Entity
@Table(name="SALES")
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private LocalDateTime date;
    private boolean isComplete;
    private double total;
    private double change;
    
    @OneToMany(mappedBy="sale")
    private List<SaleLineItem> lineItems;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isIsComplete() {
        return isComplete;
    }

    public List<SaleLineItem> getLineItems() {
        return lineItems;
    }

    public double getTotal() {
        return total;
    }

    public double getChange() {
        return change;
    }

    public Payment getPayment() {
        return payment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public void setLineItems(List<SaleLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
        if (!(object instanceof Sale)) {
            return false;
        }
        Sale other = (Sale) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.posn.nextgenpos.entity.Sale[ id=" + id + " ]";
    }
}
