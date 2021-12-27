/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.posn.nextgenpos.servlet.item;

import com.posn.nextgenpos.common.ItemDetails;
import com.posn.nextgenpos.common.ProductDetails;
import com.posn.nextgenpos.ejb.ItemBean;
import com.posn.nextgenpos.ejb.ProductSpecificationBean;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "Items", urlPatterns = {"/Items"})
public class Items extends HttpServlet {

    @Inject
    private ItemBean itemBean;
    
    @Inject
    private ProductSpecificationBean prodSpecsBean;
    
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
        request.setAttribute("activePage","Items");
        List<ItemDetails> items = itemBean.getAllItems();
        request.setAttribute("items", items);
        List<ProductDetails> itemSpecs = prodSpecsBean.getAllProductSpecifications();
        request.setAttribute("itemSpecs", itemSpecs);
        request.getRequestDispatcher("/WEB-INF/pages/item/items.jsp").forward(request, response);
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
         String[] itemIdsAsString = request.getParameterValues("item_ids");
        if(itemIdsAsString!=null)
        {
            List<Integer> itemIds = new ArrayList<>();
            for(String ids : itemIdsAsString)
            {
                itemIds.add(Integer.parseInt(ids));  
            }
            prodSpecsBean.deleteProductSpecificationByItemIds(itemIds);
        }
        response.sendRedirect(request.getContextPath()+"/Items");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Items";
    }// </editor-fold>

}
