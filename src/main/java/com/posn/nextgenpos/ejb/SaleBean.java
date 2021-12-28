/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.posn.nextgenpos.ejb;

import com.posn.nextgenpos.common.SaleDetails;
import com.posn.nextgenpos.entity.Payment;
import com.posn.nextgenpos.entity.Sale;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author teodo
 */
@Stateless
public class SaleBean {

    private static final Logger LOG = Logger.getLogger(SaleBean.class.getName());
    @PersistenceContext
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<SaleDetails> getAllSales() {
        try {
            List<Sale> sales = (List<Sale>) em.createQuery("SELECT s from Sale s").getResultList();
            return copySalesToDetails(sales);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<SaleDetails> copySalesToDetails(List<Sale> sales) {
        List<SaleDetails> saleDetailsList = new ArrayList();
        for (Sale sale : sales) {
            SaleDetails saleDetails = new SaleDetails(sale.getId(),
                    sale.getDate(),
                    sale.isIsComplete(),
                    sale.getTotal(),
                    sale.getChange()
            );
            saleDetailsList.add(saleDetails);
        }
        return saleDetailsList;
    }
    
    public int createSale(LocalDateTime Date,boolean isComplete, double total, double change, Integer paymentId)
    {
        LOG.info("createSale");
        Sale sale = new Sale();
        sale.setDate(Date);
        sale.setIsComplete(isComplete);
        sale.setTotal(total);
        sale.setChange(change);
        
        Payment payment = em.find(Payment.class, paymentId);
        payment.setSale(sale);
        sale.setPayment(payment);
        em.persist(sale);
        em.flush();
        int saleId = sale.getId();
        return saleId;
    }
    
    public SaleDetails findById(Integer saleId)
    {
        Sale sale = em.find(Sale.class, saleId);
        return new SaleDetails(sale.getId(), sale.getDate(), sale.isIsComplete(), sale.getChange(), sale.getTotal());
    }
    
}
