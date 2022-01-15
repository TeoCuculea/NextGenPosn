/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.posn.nextgenpos.ejb;

import com.posn.nextgenpos.common.LineDetails;
import com.posn.nextgenpos.common.ReturnDetails;
import com.posn.nextgenpos.entity.Return;
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
public class ReturnBean {

    private static final Logger LOG = Logger.getLogger(SaleBean.class.getName());
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private LineItemBean lineItemBean;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<ReturnDetails> getAllReturns(){
        try {
            List<Return> returnList = (List<Return>) em.createQuery("SELECT r FROM Return r").getResultList();
            return copyReturnsToDetails(returnList);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    private List<ReturnDetails> copyReturnsToDetails(List<Return> returns){
        List<ReturnDetails> returnDetailsList = new ArrayList();
        for (Return ret : returns) {
            ReturnDetails retDetails = ret.clone();
            returnDetailsList.add(retDetails);
        }
        return returnDetailsList;
    }
    
    public int createReturn(Integer saleId){
        LOG.info("createReturn");
        Return ret = new Return();
        
        Sale sale = em.find(Sale.class,saleId);
        ret.setSale(sale);
        
        em.persist(ret);
        em.flush();
        int retId = ret.getId();
        return retId;
    }
    
    public ReturnDetails findById(Integer returnId)
    {
        Return ret = em.find(Return.class,returnId);
        ReturnDetails retDetails = ret.clone();
        return retDetails;
    }
    
    public ReturnDetails findBySaleId(Integer saleId){
        List<Return> retList = (List<Return>) em.createQuery("SELECT r FROM Return r WHERE r.sale.id =:id").setParameter("id", saleId).getResultList();
        if(!retList.isEmpty()){
            ReturnDetails retDetails = retList.get(0).clone();
            return retDetails;
        }
        return null;
    }

    public void deleteReturn(Integer id){
        Return ret = em.find(Return.class,id);
        List<LineDetails> lineItemList = lineItemBean.getAllByReturnId(ret.getId());
        for(LineDetails lineItem:lineItemList){
            LineItem returnLineItem = em.find(LineItem.class,lineItem.getId());
            em.remove(returnLineItem);
        }
        em.remove(ret);
    }
}
