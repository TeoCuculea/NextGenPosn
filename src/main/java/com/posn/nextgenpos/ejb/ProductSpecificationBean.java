/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.posn.nextgenpos.ejb;

import com.posn.nextgenpos.common.ProductDetails;
import com.posn.nextgenpos.entity.Category;
import com.posn.nextgenpos.entity.Item;
import com.posn.nextgenpos.entity.ProductSpecification;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
public class ProductSpecificationBean {

    private static final Logger LOG = Logger.getLogger(ProductSpecificationBean.class.getName());
    @PersistenceContext
    private EntityManager em;

    @Inject
    CategoryBean categoryBean;

    public List<ProductDetails> copyProductsToDetails(List<ProductSpecification> products) {
        List<ProductDetails> detailsList = new ArrayList();
        for (ProductSpecification product : products) {
            ProductDetails prod = product.clone();
            detailsList.add(prod);
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
        ProductDetails prod = productSpecification.clone();
        return prod;
    }

    public ProductDetails findByItemId(Integer itemId) {
        ProductSpecification productSpecification = (ProductSpecification) em.createQuery("SELECT p FROM ProductSpecification p WHERE p.item.id = :id").setParameter("id", itemId).getSingleResult();
        ProductDetails prod = productSpecification.clone();
        return prod;
    }

    public void createProductSpecification(String name, String description, Double pricePerUnit, Integer itemId, Integer categoryId) {
        LOG.info("createProductSpecification");
        ProductSpecification productSpecification = new ProductSpecification();
        productSpecification.setName(name);
        productSpecification.setDescription(description);
        productSpecification.setPricePerUnit(pricePerUnit);

        Item item = em.find(Item.class, itemId);
        item.setProductSpecification(productSpecification);
        productSpecification.setItem(item);

        em.persist(productSpecification);
        em.flush();

        Category category = em.find(Category.class, categoryId);
        category.addProduct(productSpecification);
        productSpecification.addCategory(category);
    }

    public void updateProductSpecification(Integer productId, String name, String description, Double pricePerUnit, Integer itemId, Integer categoryId) {
        LOG.info("updateProductSpecification");
        ProductSpecification productSpecification = em.find(ProductSpecification.class, productId);
        productSpecification.setName(name);
        productSpecification.setDescription(description);
        productSpecification.setPricePerUnit(pricePerUnit);

        //normal s-ar sterge legatura cu vechiul Item cand avem CASCADE ALL la tabele
        Item item = em.find(Item.class, itemId);
        item.setProductSpecification(productSpecification);
        productSpecification.setItem(item);

        List<Category> oldCategory = (List<Category>) productSpecification.getCategories();
        if (!oldCategory.isEmpty()) {
            oldCategory.get(0).dropProduct(productSpecification);
            productSpecification.dropCategory(oldCategory.get(0));
        }
        
        Category category = em.find(Category.class, categoryId);
        category.addProduct(productSpecification);
        productSpecification.addCategory(category);
    }

    public void deleteProductSpecificationByIds(Collection<Integer> productId) {
        LOG.info("deleteProductSpecificationByIds");
        for (Integer id : productId) {
            ProductSpecification productSpecification = em.find(ProductSpecification.class, id);

            Collection<Category> categories = productSpecification.getCategories();
            Iterator<Category> i = categories.iterator();
            while (i.hasNext()) {
                Category category = (Category) i.next();
                category.dropProduct(productSpecification);
            }
            em.remove(productSpecification);
        }
    }

    public void deleteProductSpecificationByItemIds(List<Integer> itemIds) {
        for (Integer id : itemIds) {
            ProductSpecification productSpecification = (ProductSpecification) em.createQuery("SELECT p FROM ProductSpecification p WHERE p.item.id = :id").setParameter("id", id).getSingleResult();
            em.remove(productSpecification);
        }
    }

    public List<Integer> getAllIds(List<ProductDetails> itemSpecs) {
        List<Integer> productIds = new ArrayList<>();
        try {
            Iterator<ProductDetails> i = itemSpecs.iterator();
            while (i.hasNext()) {
                ProductDetails prod = (ProductDetails) i.next();
                productIds.add(prod.getId());
            }
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
        return productIds;
    }

    public List<ProductDetails> getAllProductSpecificationsWithFilters(List<Integer> categoryIds) {
        LOG.info("getAllProductSpecificationsWithFilters");
        List<ProductDetails> products = new ArrayList<>();
        List<ProductDetails> allProducts = new ArrayList<>();
        try {
            for (Integer categoryId : categoryIds) {
                products = categoryBean.getAllProductsFromCategory(categoryId);
                allProducts.addAll(products);
            }
            return allProducts;
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public List<ProductDetails> addTaxes(List<ProductDetails> products) {
        List<ProductDetails> taxa = new ArrayList<>();
        for (ProductDetails product : products) {
            ProductDetails tax = product.clone();
            taxa.add(tax);
        }
        return taxa;
    }
}
