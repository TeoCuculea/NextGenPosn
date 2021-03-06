/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.posn.nextgenpos.servlet.preturn;

import com.posn.nextgenpos.common.LineDetails;
import com.posn.nextgenpos.common.ProductDetails;
import com.posn.nextgenpos.common.ReturnDetails;
import com.posn.nextgenpos.ejb.LineItemBean;
import com.posn.nextgenpos.ejb.ProductSpecificationBean;
import com.posn.nextgenpos.ejb.ReturnBean;
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
 * @author teodo
 */
@WebServlet(name = "ProcessReturn", urlPatterns = {"/Sales/ProcessReturn"})
public class ProcessReturn extends HttpServlet {

    @Inject
    private SaleBean saleBean;
    
    @Inject 
    private LineItemBean lineItemBean;
    
    @Inject
    private ProductSpecificationBean prodSpecsBean;

    @Inject
    private ReturnBean returnBean;
    
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
        Integer saleId = Integer.parseInt(request.getParameter("id"));
        
        List<LineDetails> cart = lineItemBean.getAllBySaleId(saleId);
        request.setAttribute("cart", cart);
        List<ProductDetails> products = lineItemBean.getAllProductSpecificationsBySaleId(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("itemSpecs", products);
        
        Integer returnId;
        ReturnDetails retDetail = returnBean.findBySaleId(saleId);
        if(retDetail != null){
            List<LineDetails> lineItemDetails = lineItemBean.getAllByReturnId(retDetail.getId());
            List<ProductDetails> prodSpecs = lineItemBean.getAllProductSpecificationsByReturnId(retDetail.getId());
            prodSpecs = prodSpecsBean.addTaxes(prodSpecs);
            request.setAttribute("cartItem", lineItemDetails);
            request.setAttribute("cartItemSpecs", prodSpecs);
            returnId = retDetail.getId();
        }else{
            returnId = returnBean.createReturn(saleId);
        }
        request.setAttribute("returnId", returnId);
        request.setAttribute("saleId", saleId);
        request.getRequestDispatcher("/WEB-INF/pages/preturn/preturn.jsp").forward(request, response);
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
