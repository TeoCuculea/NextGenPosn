/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.posn.nextgenpos.servlet.catalog;

import com.posn.nextgenpos.common.LineDetails;
import com.posn.nextgenpos.common.ProductDetails;
import com.posn.nextgenpos.common.SaleDetails;
import com.posn.nextgenpos.ejb.LineItemBean;
import com.posn.nextgenpos.ejb.ProductCatalogBean;
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
 * @author teodo
 */
@WebServlet(name = "Catalogs", urlPatterns = {"/Catalogs"})
public class Catalogs extends HttpServlet {

    @Inject 
    private ProductCatalogBean productCatalogBean;
    
    @Inject
    private ProductSpecificationBean prodSpecsBean;
    
    @Inject 
    LineItemBean lineItemBean;
    
    @Inject
    SaleBean saleBean;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        request.setAttribute("activePage", "Catalogs");
        
        List<ProductDetails> itemSpecs = prodSpecsBean.getAllProductSpecifications();
        request.setAttribute("itemSpecs", itemSpecs);
        
        HttpSession session = request.getSession();
        
        SaleDetails incompleteSale = saleBean.getIncompleteSale();
        if(incompleteSale!=null)
        {
            session.setAttribute("sale", incompleteSale);
        }
        if(session.getAttribute("sale")!=null)
        {
            SaleDetails sale = (SaleDetails) session.getAttribute("sale");
            List<LineDetails> lineItemDetails = lineItemBean.getAllBySaleId(sale.getId());
            List<ProductDetails> prodSpecs = lineItemBean.getAllProductSpecificationsBySaleId(sale.getId());

            request.setAttribute("cartItem", lineItemDetails);
            request.setAttribute("cartItemSpecs", prodSpecs);
        }
        request.getRequestDispatcher("/WEB-INF/pages/catalog/catalogs.jsp").forward(request, response);
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
        return "Catalog V1.0";
    }// </editor-fold>

}
