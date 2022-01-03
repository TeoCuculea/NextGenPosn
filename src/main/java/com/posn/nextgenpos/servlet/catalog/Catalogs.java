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
import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

        //PROBLEMS THAT REQUIRE ATTENTION

//	1.request.isUserInRole('Casier') always returns false
//		-some problem with the role, maybe its only local
//	2.item page not showing category name
//	3.save button from edit item not working
//		maybe 2 and 3 are connected, in database the id's in tables are ok

/**
 *
 * @author teodo
 */
@DeclareRoles({"Admin", "Casier", "DirectorGeneral"})
/*@ServletSecurity(
//atributul value din adnotarea de mai jos ne permite sa setam ce roluri au drepturi/voie sa vada pagina aceasta, respectiva.
        value = @HttpConstraint(rolesAllowed = {"Admin"})
//atributul de mai jos se refera la faptul ca doar rolul AdminRole poate sa faca POST pe acest servlet.
//       , httpMethodConstraints = {
//                @HttpMethodConstraint(
//                        value = "POST", rolesAllowed = {"AdminRole"}
//                )
//                ,
//                @HttpMethodConstraint(
//                        value = "GET", rolesAllowed = {"ClientRole"}
//                )
//        }
)*/
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
        
        request.setAttribute("trying",request.isUserInRole("Casier"));
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
