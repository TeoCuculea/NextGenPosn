/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.posn.nextgenpos.ejb;

import com.posn.nextgenpos.common.ProductCatalogDetails;
import com.posn.nextgenpos.common.ProductDetails;
import com.posn.nextgenpos.entity.ProductCatalog;
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
public class ProductCatalogBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private static final Logger LOG = Logger.getLogger(SaleBean.class.getName());
    @PersistenceContext
    private EntityManager em;

    public ProductCatalogDetails getCatalog() {
        LOG.info("getCatalog");
        try {
            ProductCatalog catalog = ProductCatalog.getInstance();//(ProductCatalog) em.createQuery("SELECT p FROM ProductCatalog p").getSingleResult();
            return copyCatalogToDetails(catalog);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    public void createCatalog(List<ProductDetails> productSpecification) {
        LOG.info("createCatalog");
        ProductCatalog catalog = ProductCatalog.getInstance();
        catalog.setProductSpecification(productSpecification);
        em.persist(catalog);
    }
    public void updateCatalog(List<ProductDetails> productSpecification) {
        LOG.info("updateCatalog");
        ProductCatalog catalog = ProductCatalog.getInstance();
        catalog.setProductSpecification(productSpecification);
    }
     public ProductCatalogDetails copyCatalogToDetails(ProductCatalog catalog) {
        ProductCatalogDetails catalogDetail = catalog.clone();
        return catalogDetail;
    }
}
