/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.posn.nextgenpos.ejb;

import com.posn.nextgenpos.common.LineDetails;
import com.posn.nextgenpos.common.ProductDetails;
import com.posn.nextgenpos.entity.ProductSpecification;
import com.posn.nextgenpos.entity.Sale;
import com.posn.nextgenpos.entity.LineItem;
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
 * @author barb_
 */
@Stateless
public class LineItemBean {

    private static final Logger LOG = Logger.getLogger(ItemBean.class.getName());
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    ProductSpecificationBean prodSpecsBean;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void createLineItem(Integer quantity, Integer productSpecificationId, Integer saleId) {
       LOG.info("createLineItem");
       
       LineItem lineItem = new LineItem();
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
            List<LineItem> lineItems = (List<LineItem>) em.createQuery("SELECT i FROM LineItem i").getResultList();
            return copyLineItemsToDetails(lineItems);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public List<LineDetails> getAllBySaleId(Integer saleId){
        LOG.info("getAllBySaleId");
        try {
            List<LineItem> lineItems = (List<LineItem>) em.createQuery("SELECT i FROM LineItem i WHERE i.sale.id = :id").setParameter("id", saleId).getResultList();
            return copyLineItemsToDetails(lineItems);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    
    public List<ProductDetails> getAllProductSpecificationsBySaleId(Integer saleId)
    {
        LOG.info("getAllProductSpecificationsBySaleId");
        List<ProductSpecification> prodSpecsList = new ArrayList<>();
        try{
            List<LineItem> lineItemsList = (List<LineItem>) em.createQuery("SELECT i FROM LineItem i WHERE i.sale.id = :id").setParameter("id", saleId).getResultList();
            for(LineItem lineItem:lineItemsList){
                prodSpecsList.add(lineItem.getProdSpecs());
            }
            return prodSpecsBean.copyProductsToDetails(prodSpecsList);
        }
        catch(Exception ex){
            throw new EJBException(ex);
        }
    }
    
    public void deleteLineItemById(Integer lineItemId){
        LOG.info("deleteLineItemById");
        
        LineItem lineItem = em.find(LineItem.class,lineItemId);
        em.remove(lineItem);
    }
    
    public void deleteLineItemBySaleId(Integer saleId){
        LOG.info("deleteLineItemBySaleId");
        
        List<LineItem> lineItems = em.createQuery("SELECT i FROM LineItem i WHERE i.sale.id =:id").setParameter("id", saleId).getResultList();
        for(LineItem item:lineItems){
            em.remove(item);
        }
    }
    
    private List<LineDetails> copyLineItemsToDetails(List<LineItem> lineItems) {
        List<LineDetails> lineItemDetailsList = new ArrayList<>();
        for(LineItem lineItem:lineItems){
            LineDetails lineItemDetails = lineItem.clone();
            lineItemDetailsList.add(lineItemDetails);
        }
        return lineItemDetailsList;
    }

    List<LineDetails> getAllByReturnId(Integer id) {
        LOG.info("getAllByReturnId");
        try {
            List<LineItem> lineItems = (List<LineItem>) em.createQuery("SELECT i FROM LineItem i WHERE i.returns.id = :id").setParameter("id", id).getResultList();
            return copyLineItemsToDetails(lineItems);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
}
