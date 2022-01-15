/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.posn.nextgenpos.servlet.preturn;

import com.posn.nextgenpos.common.LineDetails;
import com.posn.nextgenpos.common.PaymentDetails;
import com.posn.nextgenpos.common.ProductDetails;
import com.posn.nextgenpos.common.SaleDetails;
import com.posn.nextgenpos.ejb.LineItemBean;
import com.posn.nextgenpos.ejb.PaymentBean;
import com.posn.nextgenpos.ejb.ProductSpecificationBean;
import com.posn.nextgenpos.ejb.SaleBean;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author barb_
 */
@WebServlet(name = "Refund", urlPatterns = {"/Refund"})
public class Refund extends HttpServlet {

    @Inject
    SaleBean saleBean;
    
    @Inject
    PaymentBean paymentBean;
    
    @Inject
    ProductSpecificationBean prodSpecBean;
    
    @Inject
    LineItemBean lineItemBean;
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Integer saleId = Integer.parseInt(request.getParameter("saleId"));
        List<LineDetails> lineItemList = lineItemBean.getAllBySaleId(saleId);
        lineItemBean.deleteLineItemWithZeroQuantity(saleId);
        
        List<ProductDetails> productList = lineItemBean.getAllProductSpecificationsBySaleId(saleId);
        productList = prodSpecBean.addTaxes(productList);
        double total = 0.00;
        for(ProductDetails prodSpec:productList){
            total+=prodSpec.getPricePerUnit();
        }
        PaymentDetails payment= paymentBean.findBySaleId(saleId);
        paymentBean.updatePayment(payment.getId(), saleId, payment.getAmount(), total);
        saleBean.updateSale(saleId, true, total, payment.getAmount()-total);
        
        
        response.sendRedirect(request.getContextPath() + "/Sales");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
