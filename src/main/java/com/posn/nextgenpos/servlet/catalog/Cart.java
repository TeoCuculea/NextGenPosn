/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.posn.nextgenpos.servlet.catalog;

import com.posn.nextgenpos.common.SaleDetails;
import com.posn.nextgenpos.ejb.ItemBean;
import com.posn.nextgenpos.ejb.LineItemBean;
import com.posn.nextgenpos.ejb.ProductSpecificationBean;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author barb_
 */
@WebServlet(name = "Cart", urlPatterns = {"/Cart"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"Casier"}))
public class Cart extends HttpServlet {

    @Inject
    ItemBean itemBean;
    
    @Inject
    LineItemBean lineItemBean;
    
    @Inject
    ProductSpecificationBean prodSpecBean;

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
        
        Integer productId = Integer.parseInt(request.getParameter("id"));
        Integer quantity = Integer.parseInt(request.getParameter("quan"));
        
        HttpSession session = request.getSession();
        SaleDetails sale = (SaleDetails) session.getAttribute("sale");
        if(itemBean.enoughQuantity(productId,quantity)){
            session.setAttribute("quantityError", null);
            lineItemBean.createSaleLineItem(quantity, productId, sale.getId());
        }else
        {
            session.setAttribute("quantityError", true);
        }
        
        response.sendRedirect(request.getContextPath()+"/Catalogs");
        
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
