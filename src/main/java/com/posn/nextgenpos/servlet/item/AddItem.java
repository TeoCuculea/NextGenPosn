/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.posn.nextgenpos.servlet.item;

import com.posn.nextgenpos.common.CategoryDetails;
import com.posn.nextgenpos.ejb.CategoryBean;
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
//@ServletSecurity( value = @HttpConstraint(rolesAllowed = { "AdminRole"}))
@WebServlet(name = "AddItem", urlPatterns = {"/Items/AddItem"})
public class AddItem extends HttpServlet {

    @Inject
    ItemBean itemBean;
    
    @Inject
    private ProductSpecificationBean prodSpecsBean;
    
    @Inject
    private CategoryBean categoryBean;
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
        List<CategoryDetails> category = categoryBean.getAllCategories();
        request.setAttribute("category", category);
        request.getRequestDispatcher("/WEB-INF/pages/item/addItem.jsp").forward(request, response);
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
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Double price = Double.parseDouble(request.getParameter("priceperunit"));
        Integer categoryId = Integer.parseInt(request.getParameter("category_id"));
        Integer itemId = itemBean.createItem(quantity);
        prodSpecsBean.createProductSpecification(name, description, price,itemId, categoryId);
        response.sendRedirect(request.getContextPath()+ "/Items");//ma intoarce inapoi in pagina Items     
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Add Item 1.0";
    }// </editor-fold>

}
