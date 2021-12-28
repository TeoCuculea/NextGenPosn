/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.posn.nextgenpos.ejb;

import com.posn.nextgenpos.common.LineDetails;
import com.posn.nextgenpos.entity.ProductSpecification;
import com.posn.nextgenpos.entity.Sale;
import com.posn.nextgenpos.entity.SaleLineItem;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author barb_
 */
@Stateless
public class LineItemBean {

    private static final Logger LOG = Logger.getLogger(ItemBean.class.getName());
    @PersistenceContext
    private EntityManager em;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void createLineItem(Integer quantity, Integer productSpecificationId, Integer saleId) {
       LOG.info("createLineItem");
       
       SaleLineItem lineItem = new SaleLineItem();
       lineItem.setQuantity(quantity);
       
       ProductSpecification prodSpec = em.find(ProductSpecification.class,productSpecificationId);
       Sale sale = em.find(Sale.class,saleId);
       
       lineItem.setProdSpecs(prodSpec);
       lineItem.setSale(sale);
       
       em.persist(lineItem);
    }

    public List<LineDetails> getAllLineItem(){
        LOG.info("getAllItems");
        try {
            List<SaleLineItem> lineItems = (List<SaleLineItem>) em.createQuery("SELECT i FROM Sale_Line_Item i").getResultList();
            return copyLineItemsToDetails(lineItems);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void deleteLineItemById(Integer lineItemId){
        LOG.info("deleteLineItemById");
        
        SaleLineItem lineItem = em.find(SaleLineItem.class,lineItemId);
        em.remove(lineItem);
    }
    
    public void deleteLineItemBySaleId(Integer saleId){
        LOG.info("deleteLineItemBySaleId");
        
        List<SaleLineItem> lineItems = em.createQuery("SELECT i FROM SALE_LINE_ITEMS i WHERE i.sale.id =:id ").setParameter("id", saleId).getResultList();
        for(SaleLineItem item:lineItems){
            em.remove(item);
        }
    }
    
    private List<LineDetails> copyLineItemsToDetails(List<SaleLineItem> lineItems) {
        List<LineDetails> lineItemDetailsList = new ArrayList<>();
        for(SaleLineItem lineItem:lineItems){
            LineDetails lineItemDetails = new LineDetails(lineItem.getId(),lineItem.getQuantity());
            lineItemDetailsList.add(lineItemDetails);
        }
        return lineItemDetailsList;
    }
}
