/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.posn.nextgenpos.servlet.catalog;

import com.posn.nextgenpos.common.LineDetails;
import com.posn.nextgenpos.common.PaymentDetails;
import com.posn.nextgenpos.common.ProductDetails;
import com.posn.nextgenpos.common.SaleDetails;
import com.posn.nextgenpos.ejb.ItemBean;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author barb_
 */
@WebServlet(name = "Payment", urlPatterns = {"/Catalogs/Payment"})
public class Payment extends HttpServlet {
        @Inject
        ItemBean itemBean;
    
        @Inject
        LineItemBean lineItemBean;
        
        @Inject
        SaleBean saleBean;
        
        @Inject
        PaymentBean paymentBean;
    
        @Inject
        ProductSpecificationBean prodSpecsBean;

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
        HttpSession session = request.getSession();
        SaleDetails sale = (SaleDetails) session.getAttribute("sale");
        
        List<LineDetails> lineItemDetails = lineItemBean.getAllBySaleId(sale.getId());
        List<ProductDetails> prodSpecs = lineItemBean.getAllProductSpecificationsBySaleId(sale.getId());
        List<PaymentDetails> payments = paymentBean.getAllPayments();
        prodSpecs = prodSpecsBean.addTaxes(prodSpecs);
        request.setAttribute("cartItem", lineItemDetails);
        request.setAttribute("cartItemSpecs", prodSpecs);
        request.setAttribute("payments", payments);
        
        request.getRequestDispatcher("/WEB-INF/pages/payment/payments.jsp").forward(request, response);
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
        
        Double total = Double.parseDouble(request.getParameter("total"));
        Double amount = Double.parseDouble(request.getParameter("amount"));
        
        HttpSession session = request.getSession();
        
        SaleDetails sale = (SaleDetails) session.getAttribute("sale");
        saleBean.updateSale(sale.getId(), true, total, amount-total);
        itemBean.decreaseQuantityOfSaleItems(sale.getId());
        paymentBean.createPayment(sale.getId(), amount, total);
        
        session.setAttribute("sale", null);
        
        response.sendRedirect(request.getContextPath()+"/Catalogs");
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
