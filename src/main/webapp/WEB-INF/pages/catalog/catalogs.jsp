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
            <form method="POST" action="${pageContext.request.contextPath}/Catalogs">   
                <table class="display" name="catalog" style="width:100%">
                    <tr>
                        <th>Adauga <br>produs<br> in cos</th>
                        <th>Cantitate</th>
                        <th>Nume <br>produs</th>
                        <th>Descriere</th>
                        <th>Pret/<br>bucata[RON]</th> 
                    </tr>
                    <c:forEach var="itemSpec" items="${itemSpecs}" varStatus="status">
                        <tr>
                        <div class="col-md">
                            <td><button class="w3-button w3-large w3-circle w3-xlarge w3-ripple w3-black" style="z-index:0">+</button></td>
                        </div>
                        <div class="col-md-2">
                            <td><label for="quantity" >Cantitate</label>
                                <input type="text" class="form-control" id="quantity" name="quantity" placeholder="" value="" required=""></td>
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
                    <c:forEach var="itemSpecs" items="${cart}" varStatus="status">     
                        <tr><div class="col-md">
                            <td><input type="checkbox" name="item_ids" value="${item.id}" /><td>
                        </div>
                        <div class="col-md-2">
                            <td>${itemSpecs.name}<td>
                        </div>
                        <div class="col-md-2">
                            <td>${itemSpecs.pricePerUnit}<td>
                        </div>
                        </tr>
                        <!--  camp subtotal la fiecare item eventual
                        jos de tot totalul si buton de mergem mai departe/validiare-->
                    </c:forEach>
                </table>
                <div style="text-align:center">
                    Total:
                </div>
                <div style="text-align:center">
                    <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/Catalogs/Payment" role="button" padding = "100px">Spre plata</a>
                </div>
            </form> 
        </div>
    </div>
</t:pageTemplate>
