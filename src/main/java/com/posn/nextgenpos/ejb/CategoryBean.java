/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.posn.nextgenpos.ejb;

import com.posn.nextgenpos.common.CategoryDetails;
import com.posn.nextgenpos.common.ProductDetails;
import com.posn.nextgenpos.entity.Category;
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
 * @author barb_
 */
@Stateless
public class CategoryBean {

    private static final Logger LOG = Logger.getLogger(ItemBean.class.getName());
    @PersistenceContext
    private EntityManager em;

    @Inject
    private ProductSpecificationBean prodSpecBean;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void createCategory(String categoryName) {
        LOG.info("createItem");
        Category category = new Category();
        category.setCategoryName(categoryName);

        em.persist(category);
    }

    public List<CategoryDetails> getAllCategories() {
        LOG.info("getAllCategories");
        try {
            List<Category> categories = (List<Category>) em.createQuery("SELECT c from Category c").getResultList();
            return copyCategoriesToDetails(categories);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public CategoryDetails getCategoryById(Integer categoryId) {
        Category category = em.find(Category.class, categoryId);
        return new CategoryDetails(category.getId(), category.getCategoryName());
    }

    public void deleteCategory(Collection<Integer> categoryId) {
        LOG.info("deleteCategoryById");
        try {
            for (Integer id : categoryId) {

                Category category = em.find(Category.class, id);

                Collection<ProductSpecification> productSpecification = category.getProductSpecification();
                Iterator<ProductSpecification> i = productSpecification.iterator();
                while (i.hasNext()) {
                    ProductSpecification prod = (ProductSpecification) i.next();
                    prod.dropCategory(category);
                }
                em.remove(category);
            }
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void addProductToCategory(Integer categoryId, Integer productId) {
        LOG.info("addProductToCategory");

        try {
            Category category = em.find(Category.class, categoryId);
            ProductSpecification product = em.find(ProductSpecification.class, productId);

            category.addProduct(product);
            product.addCategory(category);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void removeProductFromCategory(Integer categoryId, Integer productId) {
        LOG.info("removeProductFromCategory");

        try {
            Category category = em.find(Category.class, categoryId);
            ProductSpecification product = em.find(ProductSpecification.class, productId);

            category.dropProduct(product);
            product.dropCategory(category);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public void getAllProductsFromCategory(Integer categoryId) {
        LOG.info("getAllProductsFromCategory");
        
        List<ProductDetails> productSpecificationList = null;
        try {
            Category category = em.find(Category.class, categoryId);
            productSpecificationList = prodSpecBean.copyProductsToDetails((List<ProductSpecification>) category.getProductSpecification());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public List<CategoryDetails> copyCategoriesToDetails(List<Category> categories) {
        List<CategoryDetails> detailsList = new ArrayList();
        for (Category category : categories) {
            CategoryDetails categoryDetail = new CategoryDetails(category.getId(), category.getCategoryName());
            detailsList.add(categoryDetail);
        }
        return detailsList;
    }
}
