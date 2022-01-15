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
@WebServlet(name = "NewReturnLineItem", urlPatterns = {"/NewReturnLineItem"})
public class NewReturnLineItem extends HttpServlet {

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
        Integer returnId = Integer.parseInt(request.getParameter("retId"));
        Integer prodSpecId = Integer.parseInt(request.getParameter("itemId"));
        Integer quantity = Integer.parseInt(request.getParameter("quan"));
        Integer saleId = Integer.parseInt(request.getParameter("saleId"));
        
        LineDetails lineDetail = lineItemBean.findByProdSpecIdAndSaleId(saleId, prodSpecId);
        
        if(lineDetail.getQuantity()<quantity){
            request.setAttribute("quantityError", true);
        }
        else{
            request.setAttribute("quantityError", null);
            lineItemBean.updateLineItemQuantity(lineDetail.getId(), lineDetail.getQuantity()-quantity);
            
            ProductDetails prodSpec = prodSpecBean.findById(prodSpecId);
            ItemDetails item = itemBean.findByProdSpecId(prodSpec.getId());
            itemBean.updateItem(item.getId(), item.getQuantity()-quantity);
            
            lineDetail = lineItemBean.findByProdSpecIdAndReturnId(returnId, prodSpecId);
            if(lineDetail!=null){
                //Existing returnLineItem
                lineItemBean.updateLineItemQuantity(lineDetail.getId(),lineDetail.getQuantity()+quantity);
            }else{
                //New returnLineItem
                lineItemBean.createReturnLineItem(quantity, prodSpecId, returnId);
            }
        }
        response.sendRedirect(request.getContextPath() + "/Sales/ProcessReturn?id="+saleId);
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
