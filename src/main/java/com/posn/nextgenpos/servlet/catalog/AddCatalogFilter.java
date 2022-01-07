/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.posn.nextgenpos.servlet.catalog;

import com.posn.nextgenpos.common.CategoryDetails;
import com.posn.nextgenpos.common.ProductDetails;
import com.posn.nextgenpos.ejb.CategoryBean;
import com.posn.nextgenpos.ejb.ProductCatalogBean;
import com.posn.nextgenpos.ejb.ProductSpecificationBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author teodo
 */
@WebServlet(name = "AddCatalogFilter", urlPatterns = {"/Catalogs/AddCatalogFilter"})
public class AddCatalogFilter extends HttpServlet {

    @Inject
    CategoryBean categoryBean;
    
    @Inject
    ProductSpecificationBean prodSpecsBean;
    
    @Inject
    ProductCatalogBean prodCatBean;
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
        List<CategoryDetails> categories = categoryBean.getAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/pages/catalog/addCatalogFilter.jsp").forward(request, response);
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
        String[] itemIdsAsString = null;
        itemIdsAsString = request.getParameterValues("category_ids");
        if (itemIdsAsString != null) {
            List<Integer> categoryIds = new ArrayList<>();
            for (String ids : itemIdsAsString) {
                CategoryDetails cat = categoryBean.findById(Integer.parseInt(ids));
                categoryIds.add(cat.getId());
            }
            List<ProductDetails> itemSpecs = prodSpecsBean.getAllProductSpecificationsWithFilters(categoryIds);
            itemSpecs = prodSpecsBean.addTaxes(itemSpecs);
            prodCatBean.updateCatalog(itemSpecs);
        }
        response.sendRedirect(request.getContextPath() + "/Catalogs");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "AddCatalogFilter v1.0";
    }// </editor-fold>

}
