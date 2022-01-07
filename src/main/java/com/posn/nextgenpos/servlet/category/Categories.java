/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.posn.nextgenpos.servlet.category;

import com.posn.nextgenpos.common.CategoryDetails;
import com.posn.nextgenpos.ejb.CategoryBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DeclareRoles;
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
@DeclareRoles({"Admin"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"Admin","DirectorGeneral"}))
@WebServlet(name = "Categories", urlPatterns = {"/Categories"})
public class Categories extends HttpServlet {

    @Inject
    CategoryBean categoryBean;
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
        request.setAttribute("activePage", "Categories");
        List<CategoryDetails> categories = categoryBean.getAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/pages/category/categories.jsp").forward(request, response);
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
        String[] itemIdsAsString = request.getParameterValues("category_ids");
        if(itemIdsAsString!=null)
        {
            List<Integer> categoryIds = new ArrayList<>();
            for(String ids : itemIdsAsString)
            {
                categoryIds.add(Integer.parseInt(ids));  
            }
            categoryBean.deleteCategory(categoryIds);
        }
        response.sendRedirect(request.getContextPath()+"/Categories");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Categories v1.0";
    }// </editor-fold>

}
