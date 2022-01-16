/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.posn.nextgenpos.servlet.preturn;

import com.posn.nextgenpos.common.ItemDetails;
import com.posn.nextgenpos.common.LineDetails;
import com.posn.nextgenpos.common.ProductDetails;
import com.posn.nextgenpos.ejb.ItemBean;
import com.posn.nextgenpos.ejb.LineItemBean;
import com.posn.nextgenpos.ejb.ProductSpecificationBean;
import java.io.IOException;
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
@WebServlet(name = "DeleteFromReturnList", urlPatterns = {"/DeleteFromReturnList"})
public class DeleteFromReturnList extends HttpServlet {

    @Inject
    LineItemBean lineItemBean;

    @Inject
    ProductSpecificationBean prodSpecBean;

    @Inject
    ItemBean itemBean;

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
        Integer lineItemId = Integer.parseInt(request.getParameter("id"));
        Integer saleId = Integer.parseInt(request.getParameter("saleId"));

        LineDetails lineItem = lineItemBean.findById(lineItemId);
        ProductDetails prodSpecs = lineItemBean.getProductSpecificationsByLineItemId(lineItem.getId());
        ItemDetails item = itemBean.findByProdSpecId(prodSpecs.getId());
        itemBean.updateItem(item.getId(), item.getQuantity() + lineItem.getQuantity());

        LineDetails saleLineItem = lineItemBean.findByProdSpecIdAndSaleId(saleId, prodSpecs.getId());
        if (saleLineItem == null) {
            lineItemBean.createSaleLineItem(lineItem.getQuantity(), prodSpecs.getId(), saleId);
        } else {
            lineItemBean.updateLineItemQuantity(saleLineItem.getId(), saleLineItem.getQuantity() + lineItem.getQuantity());
        }
        lineItemBean.deleteLineItem(lineItem.getId());

        response.sendRedirect(request.getContextPath() + "/Sales/ProcessReturn?id=" + saleId);
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
