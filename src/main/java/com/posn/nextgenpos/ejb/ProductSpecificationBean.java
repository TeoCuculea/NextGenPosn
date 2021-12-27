/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.posn.nextgenpos.ejb;

import com.posn.nextgenpos.common.ProductDetails;
import com.posn.nextgenpos.entity.Item;
import com.posn.nextgenpos.entity.ProductSpecification;
import java.util.ArrayList;
import java.util.Collection;
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
public class ProductSpecificationBean {
    
    private static final Logger LOG = Logger.getLogger(ProductSpecificationBean.class.getName());
    @PersistenceContext
    private EntityManager em;
    
    private List<ProductDetails> copyProductsToDetails(List<ProductSpecification> products) {
        List<ProductDetails> detailsList = new ArrayList();
        for (ProductSpecification product : products) {
            ProductDetails productDetails = new ProductDetails(product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPricePerUnit()
            );
            detailsList.add(productDetails);
        }
        return detailsList;
    }

    public List<ProductDetails> getAllProductSpecifications() {
        LOG.info("getAllProductSpecifications");
        try {
            List<ProductSpecification> products = (List<ProductSpecification>) em.createQuery("SELECT p FROM ProductSpecification p").getResultList();
            return copyProductsToDetails(products);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    
    public ProductDetails findById(Integer productId) {
        ProductSpecification productSpecification = em.find(ProductSpecification.class, productId);
        return new ProductDetails(productSpecification.getId(), productSpecification.getName(), productSpecification.getDescription(), productSpecification.getPricePerUnit());
    }
    
    public ProductDetails findByItemId(Integer itemId)
    {
        ProductSpecification productSpecification = (ProductSpecification)em.createQuery("SELECT p FROM ProductSpecification p WHERE p.item.id = :id").setParameter("id",itemId).getSingleResult();
        return new ProductDetails(productSpecification.getId(), productSpecification.getName(), productSpecification.getDescription(), productSpecification.getPricePerUnit());
    }
    
    public void createProductSpecification(String name, String description, Double pricePerUnit, Integer itemId) {
        LOG.info("createProductSpecification");
        ProductSpecification productSpecification = new ProductSpecification();
        productSpecification.setName(name);
        productSpecification.setDescription(description);
        productSpecification.setPricePerUnit(pricePerUnit);
        
        Item item = em.find(Item.class, itemId);
        item.setProductSpecification(productSpecification);
        productSpecification.setItem(item);
        
        em.persist(productSpecification);
    }

    public void updateProductSpecification(Integer productId, String name, String description, Double pricePerUnit, Integer itemId) {
        LOG.info("updateProductSpecification");
        ProductSpecification productSpecification = em.find(ProductSpecification.class, productId);
        productSpecification.setName(name);
        productSpecification.setDescription(description);
        productSpecification.setPricePerUnit(pricePerUnit);
        
        //normal s-ar sterge legatura cu vechiul Item cand avem CASCADE ALL la tabele
        
        Item item = em.find(Item.class, itemId);
        item.setProductSpecification(productSpecification);
        productSpecification.setItem(item);
    }
    
    public void deleteProductSpecificationByIds(Collection<Integer> productId) 
    {
        LOG.info("deleteProductSpecificationByIds");
        for (Integer id : productId) {
            ProductSpecification productSpecification = em.find(ProductSpecification.class, id);
            em.remove(productSpecification);
        }
    }

    public void deleteProductSpecificationByItemIds(List<Integer> itemIds) {
        for (Integer id : itemIds) {
            ProductSpecification productSpecification = (ProductSpecification)em.createQuery("SELECT p FROM ProductSpecification p WHERE p.item.id = :id").setParameter("id",id).getSingleResult();
            em.remove(productSpecification);
        }
    }
}
