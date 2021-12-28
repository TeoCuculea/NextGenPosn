/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.posn.nextgenpos.servlet.sale;

import com.posn.nextgenpos.common.SaleDetails;
import com.posn.nextgenpos.ejb.SaleBean;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
@WebServlet(name = "Sales", urlPatterns = {"/Sales"})
public class Sales extends HttpServlet {

    @Inject
    private SaleBean saleBean;
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
        request.setAttribute("activePage", "Sales");
        List<SaleDetails> sales = saleBean.getAllSales();
        request.setAttribute("sales", sales);
        request.getRequestDispatcher("/WEB-INF/pages/sale/sales.jsp").forward(request, response);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String currentDate = request.getParameter("currentDate");
        LocalDateTime dateTime = LocalDateTime.parse(currentDate, formatter);
        Boolean isComplete = Boolean.parseBoolean(request.getParameter("is_complete"));
        Double total = Double.parseDouble(request.getParameter("total"));
        Double change = Double.parseDouble(request.getParameter("change"));
        Integer paymentId = Integer.parseInt(request.getParameter("pay_id"));//trebe formular in moment payment, pun valori la value
        Integer saleId = saleBean.createSale(dateTime, isComplete, total, change, paymentId);
        
        response.sendRedirect(request.getContextPath()+ "/Sales");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Sale V1.0";
    }// </editor-fold>

}
