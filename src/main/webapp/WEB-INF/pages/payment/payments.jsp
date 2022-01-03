<%-- 
    Document   : payments
    Created on : 29-Dec-2021, 19:00:57
    Author     : teodo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Payment">
    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
        <form method="POST" action="${pageContext.request.contextPath}/Catalogs/Payment"> 
            <table name="lista_cumparaturi" width="100%" >
                <tr>
                    <th>Cantitate</th>
                    <th>Nume <br>produs</th>
                    <th>Pret/<br>bucata[RON]</th> 
                    <th>Subtotal[RON]</th>
                </tr>
                <c:forEach var="itemSpecs" items="${cartItemSpecs}" varStatus="status">
                    <tr>
                            <td id="cartQuantity{itemSpecs.id}">
                                ${cartItem[status.index].quantity}
                            </td>
                            <td>${itemSpecs.name}</td>
                            <td>${itemSpecs.pricePerUnit}</td>
                            <td ${total=total + itemSpecs.pricePerUnit * cartItem[status.index].quantity}>
                                ${itemSpecs.pricePerUnit * cartItem[status.index].quantity}
                            </td>
                    </tr>
                </c:forEach>
            </table>
            <div style="text-align:center">
                Total:${total}
                <input type ="hidden" name="total" id="total" value="${total}">
            </div>
            <div>
                <label for="amount">Introdu suma:</label>
                <input type="text" name="amount" id="amount" placeholder="" value="" required="">
            </div>
            <div style="text-align:center">
                <button class="btn btn-primary btn-lg btn-block" type="submit">Plata</button>
            </div>
        </form> 
    </div>
</t:pageTemplate>