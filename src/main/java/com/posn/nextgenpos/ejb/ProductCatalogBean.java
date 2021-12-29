/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.posn.nextgenpos.ejb;

import com.posn.nextgenpos.common.ProductCatalogDetails;
import com.posn.nextgenpos.common.ProductDetails;
import com.posn.nextgenpos.entity.ProductCatalog;
import com.posn.nextgenpos.entity.ProductSpecification;
import java.util.List;
import java.util.logging.Logger;
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

    public void createCatalog(List<ProductSpecification> productSpecification) {
        LOG.info("createCatalog");
        ProductCatalog catalog = new ProductCatalog();
        catalog.setProductSpecification(productSpecification);
    }
}
