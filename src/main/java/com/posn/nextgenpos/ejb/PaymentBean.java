/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package com.posn.nextgenpos.ejb;

import com.posn.nextgenpos.common.PaymentDetails;
import com.posn.nextgenpos.entity.Payment;
import com.posn.nextgenpos.entity.Sale;
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
public class PaymentBean {

    private static final Logger LOG = Logger.getLogger(ItemBean.class.getName());
    @PersistenceContext
    private EntityManager em;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void createPayment(Integer saleId, Double amount, Double total) {
        LOG.info("createPayment");
        Payment payment = new Payment();
        payment.setTotal(total);
        payment.setAmount(amount);

        Sale sale = em.find(Sale.class, saleId);
        sale.setPayment(payment);
        payment.setSale(sale);

        em.persist(payment);
    }

    public void deletePaymentByIds(Collection<Integer> paymentIds) {
        LOG.info("deletePaymentsByIds");

        for (Integer id : paymentIds) {
            Payment payment = em.find(Payment.class, id);

            em.remove(payment);
        }
    }

    public void deletePaymentBySaleIds(Collection<Integer> saleIds) {
        LOG.info("deleteBySaleIds");

        for (Integer id : saleIds) {
            Payment payment = (Payment) em.createQuery("SELECT p FROM Payment p WHERE p.sale.id = :id").setParameter("id", id).getSingleResult();
            em.remove(payment);
        }
    }

    public void updatePayment(Integer paymentId, Integer saleId, Double amount, Double total) {
        LOG.info("updatePayment");
        Payment payment = em.find(Payment.class, paymentId);
        payment.setAmount(amount);
        payment.setTotal(total);

        Sale sale = em.find(Sale.class, saleId);
        payment.setSale(sale);
        sale.setPayment(payment);
    }

    public PaymentDetails findById(Integer paymentId) {
        Payment payment = em.find(Payment.class, paymentId);
        return new PaymentDetails(payment.getId(), payment.getAmount(), payment.getTotal());
    }

    public PaymentDetails findBySaleId(Integer saleId) {
        Payment payment = (Payment) em.createQuery("SELECT p FROM Payment p WHERE p.sale.id = :id").setParameter("id", saleId).getSingleResult();
        return new PaymentDetails(payment.getId(), payment.getAmount(), payment.getTotal());
    }
    
    public List<PaymentDetails> getAllPayments(){
        LOG.info("getAllPayments");
        try{
            List<Payment> payments = (List<Payment>) em.createQuery("SELECT p FROM Payment p").getResultList();
            return copyPaymentToDetails(payments);
        }catch(Exception ex){
            throw new EJBException(ex);
        }
    }

    private List<PaymentDetails> copyPaymentToDetails(List<Payment> payments) {
        List<PaymentDetails> paymentList = new ArrayList<>();
        for(Payment payment: payments){
            PaymentDetails paymentDetails = new PaymentDetails(payment.getId(),payment.getAmount(),payment.getTotal());
            paymentList.add(paymentDetails);
        }
        return paymentList;
    }
}
