<%-- 
    Document   : catalogs
    Created on : 29-Dec-2021, 18:10:00
    Author     : teodo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Catalogs">
    <h1>Catalogs</h1>
    <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> 
            <form method="POST" action="${pageContext.request.contextPath}/Catalogs">   
                <table class="display" name="catalog" style="width:100%">
                    <tr>
                        <th>Adauga <br>produs<br> in cos</th>
                        <th>Cantitate</th>
                        <th>Nume <br>produs</th>
                        <th>Descriere</th>
                        <th>Pret/<br>bucata[RON]</th> 
                    </tr>
                    <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/Catalogs/AddFilter" role="button">Filters</a>
                    <button class="btn btn-danger" type="submit">Delete filters</button> 
                    <c:forEach var="itemSpec" items="${itemSpecs}" varStatus="status">
                        <tr>
                        <div class="col-md">
                            <td><a href="${pageContext.request.contextPath}/Cart?id=${itemSpec.id}&quan=" onclick="this.href+=document.getElementById('quantity${itemSpec.id}').value" class="w3-button w3-large w3-circle w3-xlarge w3-ripple w3-black" style="z-index:0">+</a></td>
                        </div>
                        <div class="col-md-2">
                            <td><label for="quantity" >Cantitate</label>
                                <input type="text" class="form-control" id="quantity${itemSpec.id}" name="quantity" placeholder="" value="" required=""></td>
                            <div class="invalid-feedback">
                                Quantity is required.
                            </div>
                        </div>
                        <div class="col-md-2">
                            <td>${itemSpec.name}</td>
                        </div>
                        <div class="col-md-2">
                            <td>${itemSpec.description}</td>
                        </div>
                        <div class="col-md-2">
                            <td>${itemSpec.pricePerUnit}</td>
                        </div>
                        </tr>
                    </c:forEach>
                </table>
            </form>
        </div> 
        <c:if test="${pageContext.request.isUserInRole('Casier')}">
            <c:choose>
                <c:when test="${sessionScope.sale !=null}" >
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                    <form method="POST" action="${pageContext.request.contextPath}/ValidareCart"> 
                        <table name="lista_cumparaturi" width="100%" >
                            <tr>
                                <th>Sterge <br>produs<br> din cos</th>
                                <th>Cantitate</th>
                                <th>Nume <br>produs</th>
                                <th>Pret/<br>bucata[RON]</th> 
                                <th>Subtotal[RON]</th>
                            </tr>
                            <c:forEach var="itemSpecs" items="${cartItemSpecs}" varStatus="status">
                                <tr>
                                    <div class="col-md">
                                        <td><a href="${pageContext.request.contextPath}/DeleteFromCart?id=${cartItem[status.index].id}">-</a></td>
                                    </div>
                                    <div>
                                        <td id="cartQuantity{itemSpecs.id}">
                                            ${cartItem[status.index].quantity}
                                        </td>
                                    </div>
                                    <div class="col-md-2">
                                        <td>${itemSpecs.name}</td>
                                    </div>
                                    <div class="col-md-2">
                                        <td>${itemSpecs.pricePerUnit}</td>
                                    </div>
                                    <div>
                                        <td ${total=total + itemSpecs.pricePerUnit * cartItem[status.index].quantity}>
                                            ${itemSpecs.pricePerUnit * cartItem[status.index].quantity}
                                        </td>
                                    </div>
                                </tr>
                            </c:forEach>
                        </table>
                        <div style="text-align:center">
                            Total:${total}
                        </div>
                        <div style="text-align:center">
                            <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/Catalogs/Payment" role="button" padding = "100px">Spre plata</a>
                        </div>
                    </form> 
                </div>
                </c:when>
                <c:otherwise>
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                        <a href="${pageContext.request.contextPath}/NewSale" class="btn btn-primary btn-lg" role="button">New Sale</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:if>
    </div>
</t:pageTemplate>
