/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.posn.nextgenpos.ejb;

import com.posn.nextgenpos.common.LineDetails;
import com.posn.nextgenpos.common.SaleDetails;
import com.posn.nextgenpos.entity.Sale;
import com.posn.nextgenpos.entity.LineItem;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

    @Inject
    private LineItemBean lineItemBean;
    
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
            SaleDetails saleDetails = sale.clone();
            saleDetailsList.add(saleDetails);
        }
        return saleDetailsList;
    }

    public int createSale(LocalDateTime Date, boolean isComplete, double total, double change) {
        LOG.info("createSale");
        Sale sale = new Sale();
        sale.setDate(Date);
        sale.setIsComplete(isComplete);
        sale.setTotal(total);
        sale.setChange(change);

        em.persist(sale);
        em.flush();
        int saleId = sale.getId();
        return saleId;
    }

    public SaleDetails findById(Integer saleId) {
        Sale sale = em.find(Sale.class, saleId);
        SaleDetails salD = sale.clone();
        return salD;
    }

    public SaleDetails getIncompleteSale() {
        List<Sale> sale = (List<Sale>) em.createQuery("SELECT s FROM Sale s WHERE s.isComplete = :c").setParameter("c", false).getResultList();
        
        if (!sale.isEmpty()) {
            return new SaleDetails(sale.get(0).getId(),
                    sale.get(0).getDate(),
                    sale.get(0).getIsComplete(),
                    sale.get(0).getTotal(),
                    sale.get(0).getChange());
        }
        return null;
    }

    public void updateSale(Integer saleId, boolean isComplete, double total, double change) {
        Sale sale = em.find(Sale.class, saleId);
        sale.setIsComplete(isComplete);
        sale.setTotal(total);
        sale.setChange(change);
    }

    public void deleteSale(Integer id) {
        Sale sale = em.find(Sale.class,id);
        List<LineDetails> lineItemList = lineItemBean.getAllBySaleId(sale.getId());
        for(LineDetails lineItem:lineItemList){
            LineItem saleLineItem = em.find(LineItem.class,lineItem.getId());
            em.remove(saleLineItem);
        }
        
        em.remove(sale);
    }

}
