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
import com.posn.nextgenpos.entity.Return;
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
    public void createSaleLineItem(Integer quantity, Integer productSpecificationId, Integer saleId) {
       LOG.info("createSaleLineItem");
       
       LineItem lineItem = new LineItem();
       lineItem.setQuantity(quantity);
       
       ProductSpecification prodSpec = em.find(ProductSpecification.class,productSpecificationId);
       Sale sale = em.find(Sale.class,saleId);
       
       lineItem.setProdSpecs(prodSpec);
       lineItem.setSale(sale);
       
       em.persist(lineItem);
    }

    public int createReturnLineItem(Integer quantity, Integer productSpecificationId, Integer returnId){
        LOG.info("createSaleLineItem");
       
       LineItem lineItem = new LineItem();
       lineItem.setQuantity(quantity);
       
       ProductSpecification prodSpec = em.find(ProductSpecification.class,productSpecificationId);
       Return ret = em.find(Return.class,returnId);
       
       lineItem.setProdSpecs(prodSpec);
       lineItem.setReturns(ret);
       
       em.persist(lineItem);
       em.flush();
       int lineItemId = lineItem.getId();
       return lineItemId;
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
    
    public List<LineDetails> getAllByReturnId(Integer returnId){
        LOG.info("getAllByReturnId");
        try {
            List<LineItem> lineItems = (List<LineItem>) em.createQuery("SELECT i FROM LineItem i WHERE i.returns.id = :id").setParameter("id", returnId).getResultList();
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
    
    public List<ProductDetails> getAllProductSpecificationsByReturnId(Integer returnId)
    {
        LOG.info("getAllProductSpecificationsByReturnId");
        List<ProductSpecification> prodSpecsList = new ArrayList<>();
        try{
            List<LineItem> lineItemsList = (List<LineItem>) em.createQuery("SELECT i FROM LineItem i WHERE i.returns.id = :id").setParameter("id", returnId).getResultList();
            for(LineItem lineItem:lineItemsList){
                prodSpecsList.add(lineItem.getProdSpecs());
            }
            return prodSpecsBean.copyProductsToDetails(prodSpecsList);
        }
        catch(Exception ex){
            throw new EJBException(ex);
        }
    }
    
    public ProductDetails getProductSpecificationsByLineItemId(Integer lineId){
        LineItem lineItem = em.find(LineItem.class,lineId);
        return lineItem.getProdSpecs().clone();
    }
    
    public LineDetails findByProdSpecIdAndReturnId(Integer returnId, Integer prodId){
        LOG.info("findByProdSpecIdAndReturnId");
        try {
            List<LineItem> lineItems = (List<LineItem>) em.createQuery("SELECT i FROM LineItem i WHERE i.returns.id = :id AND i.prodSpecs.id=:id2")
                    .setParameter("id", returnId)
                    .setParameter("id2", prodId)
                    .getResultList();
            if(!lineItems.isEmpty()){
                return lineItems.get(0).clone();
            }
            return null;
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    
    public LineDetails findByProdSpecIdAndSaleId(Integer saleId, Integer prodId){
        LOG.info("findByProdSpecIdAndReturnId");
        try {
            List<LineItem> lineItems = (List<LineItem>) em.createQuery("SELECT i FROM LineItem i WHERE i.sale.id = :id AND i.prodSpecs.id=:id2")
                    .setParameter("id", saleId)
                    .setParameter("id2", prodId)
                    .getResultList();
            if(!lineItems.isEmpty()){
                return lineItems.get(0).clone();
            }
            return null;
        } catch (Exception ex) {
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

    public void updateLineItemQuantity(Integer lineItemId, Integer newQuantity) {
        LineItem lineItem = em.find(LineItem.class,lineItemId);
        lineItem.setQuantity(newQuantity);
    }

    public LineDetails findById(Integer lineItemId) {
        LineItem lineItem = em.find(LineItem.class,lineItemId);
        return lineItem.clone();
    }

    public void deleteLineItem(Integer id) {
        LineItem lineItem = em.find(LineItem.class,id);
        em.remove(lineItem);
    }

    public List<LineDetails> getAllWithFiltersBySaleId(List<ProductDetails> prodSpecs, int saleId) {
        LOG.info("getAllWithFiltersBySaleId");
        List<LineItem> lineItemList = new ArrayList<LineItem>();
        try{
            for(ProductDetails prodSpec : prodSpecs)
            {
                LineItem lineItem = (LineItem) em.createQuery("SELECT i FROM LineItem i WHERE i.sale.id = :id and i.prodSpecs.id=:id2").setParameter("id", saleId).setParameter("id2", prodSpec.getId()).getSingleResult();
                lineItemList.add(lineItem);
            }
            return copyLineItemsToDetails(lineItemList);
        }
        catch(Exception ex){
            throw new EJBException(ex);
        }
    }

}
