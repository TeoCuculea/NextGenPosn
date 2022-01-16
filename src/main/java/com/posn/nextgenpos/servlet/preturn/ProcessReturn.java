/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.posn.nextgenpos.servlet.preturn;

import com.posn.nextgenpos.common.CategoryDetails;
import com.posn.nextgenpos.common.LineDetails;
import com.posn.nextgenpos.common.ProductCatalogDetails;
import com.posn.nextgenpos.common.ProductDetails;
import com.posn.nextgenpos.common.SaleDetails;
import com.posn.nextgenpos.ejb.CategoryBean;
import com.posn.nextgenpos.ejb.LineItemBean;
import com.posn.nextgenpos.ejb.ProductCatalogBean;
import com.posn.nextgenpos.ejb.ProductSpecificationBean;
import com.posn.nextgenpos.ejb.SaleBean;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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
    private ProductCatalogBean prodCatBean;

    @Inject
    private CategoryBean categoryBean;

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
        int saleId = 0;
        String o = request.getParameter("id");
        request.getSession().setAttribute("returnSaleId", o);
        saleId= Integer.parseInt(o);
        /*if (o != null) {
            request.getServletContext().setAttribute("sale_id", Integer.parseInt(o));
        }
        String comp="";
        if (request.getServletContext().getAttribute("sale_id") != null) {
            saleId = (int) request.getServletContext().getAttribute("sale_id");
            comp=(String) request.getServletContext().getAttribute("sale_id");
        }*/
        ProductCatalogDetails catalog = prodCatBean.getCatalog();
        //prodCatBean.deleteCatalog();
        if (catalog.getId() == null) {
            List<ProductDetails> itemSpecs = lineItemBean.getAllProductSpecificationsBySaleId(saleId);
            itemSpecs = prodSpecsBean.addTaxes(itemSpecs);
            prodCatBean.createCatalog(itemSpecs);
            request.setAttribute("itemSpecs", itemSpecs);
        } else {
            List<ProductDetails> itemSpecs = catalog.getProductSpecification();
            prodCatBean.updateCatalog(itemSpecs);
            request.setAttribute("itemSpecs", itemSpecs);
        }
        //<ProductDetails> itemSpecs = catalog.getProductSpecification();
        List<ProductDetails> itemSpecs = lineItemBean.getAllProductSpecificationsBySaleId(saleId);
        SaleDetails sale = saleBean.findById(saleId);
        request.getServletContext().setAttribute("sale", sale);
        /*if( itemSpecs == null)
        {
            List<LineDetails> cart = lineItemBean.getAllBySaleId(saleId);
            request.setAttribute("cart", cart);
        }
        else{*/
            List<LineDetails> cart = lineItemBean.getAllWithFiltersBySaleId(itemSpecs, saleId);
            request.setAttribute("cart", cart);
        //}
        List<CategoryDetails> categories = categoryBean.getAllCategories();
        request.setAttribute("categories", categories);

        /*Integer returnId;
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
        request.setAttribute("returnId", returnId);*/
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
        String buton = request.getParameter("delete");
        String act = request.getParameter("sort");
        String stare = request.getParameter("sortare");
        String saleId = (String) request.getSession().getAttribute("returnSaleId");
        if (buton == null) {
            //nu s-a apasat
        } else if (buton.equals("deleteFilters")) {
            
            List<ProductDetails> products = lineItemBean.getAllProductSpecificationsBySaleId(Integer.parseInt(saleId));
            request.setAttribute("itemSpecs", products);
            prodCatBean.updateCatalog(products);
        }
        if (act == null) {
            //nu s-a apasat butonul
        } else if (act.equals("sortByName") && stare.equals("ASC")) {
            ProductCatalogDetails catalog = prodCatBean.getCatalog();
            List<ProductDetails> itemSpecs = catalog.getProductSpecification()
                    .stream()
                    .sorted(Comparator.comparing(ProductDetails::getName))
                    .collect(Collectors.toList());
            prodCatBean.updateCatalog(itemSpecs);
            request.setAttribute("itemSpecs", itemSpecs);
        } else if (act.equals("sortByName") && stare.equals("DESC")) {
            ProductCatalogDetails catalog = prodCatBean.getCatalog();
            List<ProductDetails> itemSpecs = catalog.getProductSpecification()
                    .stream()
                    .sorted(Comparator.comparing(ProductDetails::getName).reversed())
                    .collect(Collectors.toList());
            prodCatBean.updateCatalog(itemSpecs);
            request.setAttribute("itemSpecs", itemSpecs);
        } else if (act.equals("sortByPrice") && stare.equals("ASC")) {
            ProductCatalogDetails catalog = prodCatBean.getCatalog();
            List<ProductDetails> itemSpecs = catalog.getProductSpecification()
                    .stream()
                    .sorted(Comparator.comparingDouble(ProductDetails::getPricePerUnit))
                    .collect(Collectors.toList());
            prodCatBean.updateCatalog(itemSpecs);
            request.setAttribute("itemSpecs", itemSpecs);
        } else if (act.equals("sortByPrice") && stare.equals("DESC")) {
            ProductCatalogDetails catalog = prodCatBean.getCatalog();
            List<ProductDetails> itemSpecs = catalog.getProductSpecification()
                    .stream()
                    .sorted(Comparator.comparingDouble(ProductDetails::getPricePerUnit).reversed())
                    .collect(Collectors.toList());
            prodCatBean.updateCatalog(itemSpecs);
            request.setAttribute("itemSpecs", itemSpecs);
        }
        response.sendRedirect(request.getContextPath() + "/Sales/ProcessReturn?id="+saleId);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "ProcessReturn v1.0";
    }// </editor-fold>

}
