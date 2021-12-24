/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.posn.nextgenpos.ejb;

import com.posn.nextgenpos.common.ItemDetails;
import com.posn.nextgenpos.entity.Item;
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
public class ItemBean {

    private static final Logger LOG = Logger.getLogger(ItemBean.class.getName());
    @PersistenceContext
    private EntityManager em;
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<ItemDetails>getAllItems(){
        LOG.info("getAllItems");
        try {
            List<Item> items = (List<Item>) em.createQuery("SELECT i FROM Item i").getResultList();
            return copyItemsToDetails(items);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }  
    public ItemDetails findById(Integer itemId)
    {
        Item item = em.find(Item.class, itemId);
        return new ItemDetails(item.getId(), item.getQuantity());
    }
    public void createItem(Integer quantity)
    {
        LOG.info("createItem");
        Item item = new Item();
        item.setQuantity(quantity);
        
        em.persist(item);
    }
    public void deleteItemsByIds(Collection<Integer> ids)
    {
        LOG.info("deleteItemsByIds");
        for(Integer id : ids)
        {
            Item item = em.find(Item.class, id);
            em.remove(item);
        }
    }
    public void updateItem(Integer itemId, Integer quantity)
    {
        LOG.info("updateItem");
        Item item = em.find(Item.class, itemId);
        item.setQuantity(quantity);
    }
    private List<ItemDetails> copyItemsToDetails(List<Item> items)
    {
        List<ItemDetails> detailsList = new ArrayList();
        for(Item item : items)
        {
            ItemDetails itemDetails = new ItemDetails(item.getId(),
                    item.getQuantity()
            );
            detailsList.add(itemDetails);
        }
        return detailsList;
    }
}
