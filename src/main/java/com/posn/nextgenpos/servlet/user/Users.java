package com.posn.nextgenpos.servlet.user;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */




import com.posn.nextgenpos.common.UserDetails;
import com.posn.nextgenpos.ejb.UserBean;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*@ServletSecurity(
       httpMethodConstraints = {
                @HttpMethodConstraint(
                        value = "POST", rolesAllowed = {"AdminRole"}
                )
                ,
                @HttpMethodConstraint(
                        value = "GET", rolesAllowed = {"ClientRole"}
                )
        }
)*/
/**
 *
 * @author teodo
 */
@DeclareRoles({"Admin", "Casier", "DirectorGeneral"})
@ServletSecurity(value= @HttpConstraint(rolesAllowed = {"Admin"}))
@WebServlet(name = "Users", urlPatterns = {"/Users"})
public class Users extends HttpServlet {

    @Inject
    UserBean userBean;

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
        request.setAttribute("activePage", "Users");
        List<UserDetails> users = userBean.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/pages/user/users.jsp").forward(request, response);
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
        String[] userIdsAsString = request.getParameterValues("user_ids");
        if(userIdsAsString !=null)
        {
            Set<Integer> userIds = new HashSet<Integer>();
            for(String userIdAsString : userIdsAsString)
            {
                userIds.add(Integer.parseInt(userIdAsString));
            }
        }
        response.sendRedirect(request.getContextPath()+"/Users");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Users v1.0";
    }// </editor-fold>

}
