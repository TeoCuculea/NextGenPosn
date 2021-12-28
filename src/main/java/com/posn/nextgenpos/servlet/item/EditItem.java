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
@WebServlet(name = "EditItem", urlPatterns = {"/Items/EditItem"})
public class EditItem extends HttpServlet {

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
    @Inject
    ItemBean itemBean;

    @Inject
    private ProductSpecificationBean prodSpecsBean;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ItemDetails> items = itemBean.getAllItems();
        request.setAttribute("items", items);
      

        int itemId = Integer.parseInt(request.getParameter("id"));
        
        ItemDetails item = itemBean.findById(itemId);
        ProductDetails prodDetails = prodSpecsBean.findByItemId(itemId);
        request.setAttribute("item", item);
        request.setAttribute("itemSpecs",prodDetails);
        
        request.getRequestDispatcher("/WEB-INF/pages/item/editItem.jsp").forward(request, response);
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
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));
        Integer itemId = Integer.parseInt(request.getParameter("item_id"));

        itemBean.updateItem(itemId, quantity);
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Double price = Double.parseDouble(request.getParameter("priceperunit"));
        Integer productId = Integer.parseInt(request.getParameter("product_id"));
        
        itemBean.updateItem(itemId, quantity);
        prodSpecsBean.updateProductSpecification(productId,name,description,price,itemId);
        response.sendRedirect(request.getContextPath() + "/Items");
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