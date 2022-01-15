<%-- 
    Document   : preturn.jsp
    Created on : 15-Jan-2022, 15:09:59
    Author     : teodo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Process Return">
    <h1>Process Return</h1>
    <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6"> 
            <table name="preturn" style="width:100%">
                <th>Name</th>
                <th>Quantity</th>
                <th>Description</th>
                <th>Price/<br>Item[RON]</th>
                <th>Adauga la<br> returnare</th>
                <form class="needs-validation"  novalidate="" method="POST" action="${pageContext.request.contextPath}/Sales/ProcessReturn">
                    <c:forEach var="itemSpec" items="${itemSpecs}" varStatus="status">
                        <tr>
                            <td>${itemSpec.name}</td>
                            <td>
                                <div class="col-md-2">
                                    <input type="number" id="quantity${cart[status.index].id}" name="quantity" placeholder="" value="${cart[status.index].quantity}" required="" min="1">
                                    <div class="invalid-feedback">
                                        Quantity is required.
                                    </div>
                                </div>
                            </td>
                            <td>${itemSpec.description}</td>
                            <td>${itemSpec.pricePerUnit}</td>
                            <td> <a href="${pageContext.request.contextPath}/NewReturnLineItem?saleId=${saleId}&retId=${returnId}&itemId=${itemSpec.id}&quan=" 
                                    onclick="this.href += document.getElementById('quantity${cart[status.index].id}').value" 
                                    class="btn btn-lg btn-primary" style="z-index:0">+</a></td>
                        </tr>
                    </c:forEach>
                </form>
            </table >
        </div>
        <c:if test="${pageContext.request.isUserInRole('Casier')}">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                <form method="POST" action="${pageContext.request.contextPath}/ValidareCart"> 
                    <table name="lista_returnuri" width="100%" >
                        <tr>
                            <c:if test="${quantityError!=null}">
                                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                        Not enough items!Insert a lower quantity.
                                        <button type="button" id="closeQuantityError" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                            </c:if>
                        </tr>
                        <tr>
                            <th>Sterge <br>produs<br> din return</th>
                            <th>Cantitate</th>
                            <th>Nume <br>produs</th>
                            <th>Pret/<br>bucata[RON]</th> 
                            <th>Subtotal[RON]</th>
                        </tr>
                        <c:forEach var="itemSpec" items="${cartItemSpecs}" varStatus="status">
                            <tr>
                                <td><a href="${pageContext.request.contextPath}/DeleteFromReturnList?id=${cartItem[status.index].id}&saleId=${saleId}" class="btn btn-danger">-</a></td>
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
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/CancelReturn" role="button" padding = "100px">Cancel</a>
                        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/Refund" role="button" padding = "100px">Refund</a>
                    </div>
                </form> 
            </div>
        </c:if>
    </div>
</div>
<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict';

        window.addEventListener('load', function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');

            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>

</t:pageTemplate>
