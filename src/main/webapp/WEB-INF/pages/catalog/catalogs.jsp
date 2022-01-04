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
    <div class="clearfix">
        <form "needs-validation"  novalidate="" method="POST" action="${pageContext.request.contextPath}/Catalogs">
            <button name="delete" value="deleteFilters" class="btn btn-danger" type="submit">Delete filters</button>
            <input type="submit" name="sort" value="sortByName"/>
            <input type="submit" name="sort" value="sortByPrice"/>
        </form>
        <form class="needs-validation"  novalidate="" method="POST" action="${pageContext.request.contextPath}/Catalogs/AddCatalogFilter">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                    Filters
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <c:forEach var="category" items="${categories}" varStatus="status">
                        <li class="dropdown-item"><input type="checkbox" name="category_ids" value="${category.id}"></li>
                        <li class="dropdown-item">${category.categoryName}</li>
                        </c:forEach> 
                    <button class="btn btn-primary btn-lg btn-block col-md-12 " type="submit">Apply filters</button>
                </ul>
            </div> 
        </form >
    </div>
    <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> 
            <form class="needs-validation"  novalidate=""  method="POST" action="${pageContext.request.contextPath}/Catalogs">   
                <table class="display" name="catalog" style="width:100%">
                    <tr>
                        <c:if test="${sessionScope.sale!=null && pageContext.request.isUserInRole('Casier')}">
                        <th>Adauga <br>produs<br> in cos</th>
                        <th>Cantitate</th>
                        </c:if>
                        <th>Nume <br>produs</th>
                        <th>Descriere</th>
                        <th>Pret/<br>bucata[RON]</th> 
                    </tr>
                    <c:forEach var="itemSpec" items="${itemSpecs}" varStatus="status">
                        <tr>
                            <c:if test="${sessionScope.sale!=null && pageContext.request.isUserInRole('Casier')}">
                            <td>
                                <a href="${pageContext.request.contextPath}/Cart?id=${itemSpec.id}&quan=" 
                                   onclick="this.href += document.getElementById('quantity${itemSpec.id}').value" 
                                   class="btn btn-lg btn-primary" style="z-index:0">+</a>
                            </td>
                            <td>
                                <label for="quantity" >Cantitate</label>
                                <input type="number" class="form-control" id="quantity${itemSpec.id}" name="quantity" placeholder="" value="" required="" min="1">
                                <div class="invalid-feedback">
                                    Quantity is required.
                                </div>
                            </td>
                            </c:if>
                            <td>${itemSpec.name}</td>
                            <td>${itemSpec.description}</td>
                            <td>${itemSpec.pricePerUnit}</td>
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
                                <c:forEach var="itemSpec" items="${cartItemSpecs}" varStatus="status">
                                    <tr>
                                        <td><a href="${pageContext.request.contextPath}/DeleteFromCart?id=${cartItem[status.index].id}" class="btn btn-danger">-</a></td>
                                        <td id="cartQuantity{
                                                itemSpec.id
                                            }">
                                            ${cartItem[status.index].quantity}
                                        </td>
                                        <td>${itemSpec.name}</td>
                                        <td>${Math.round(itemSpec.pricePerUnit*100.0)/100.0}</td>
                                        <td ${total=Math.round((total+Math.round( itemSpec.pricePerUnit * cartItem[status.index].quantity*100)/100)*100)/100}>
                                            ${Math.round( itemSpec.pricePerUnit * cartItem[status.index].quantity*100)/100}
                                        </td>
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
