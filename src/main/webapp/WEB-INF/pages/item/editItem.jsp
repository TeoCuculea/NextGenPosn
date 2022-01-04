<%-- 
    Document   : addItem
    Created on : 24-Dec-2021, 20:10:27
    Author     : teodo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Edit Item">
    <h1>Edit Item</h1>
    <form class="needs-validation"  novalidate="" method="POST" action="${pageContext.request.contextPath}/Items/EditItem">
        <div class="row">
            <div class="col-md">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="" value="${itemSpecs.name}" required="">
                <div class="invalid-feedback">
                    Name is required.
                </div>
            </div>
            <div class="col-md-2">
                <label for="quantity">Quantity</label>
                <input type="number" class="form-control" id="quantity" name="quantity" placeholder="" value="${item.quantity}" required="" min="1">
                <div class="invalid-feedback">
                    Quantity is required.
                </div>
            </div>
            <div class="col-md-2">
                <label for="description">Description</label>
                <input type="text" class="form-control" id="description" name="description" placeholder="" value="${itemSpecs.description}" required="">
                <div class="invalid-feedback">
                    Quantity is required.
                </div>
            </div>
            <div class="col-md-2">
                <label for="priceperunit">Price Per Unit</label>
                <input type="number" class="form-control" id="priceperunit" name="priceperunit" placeholder="" value="${itemSpecs.pricePerUnit}" required="" min="0">
                <div class="invalid-feedback">
                    Quantity is required.
                </div>
            </div>
        </div>
        <div class="col-md-2">
            <label for="category_id">Category</label>
            <select class="custom-select d-block w-100" id="category_id" name="category_id" required="">
                <option value="${category.id}">${category.categoryName}</option>
                <c:forEach var="category" items="${categories}" varStatus="status">
                    <option value="${category.id}">${category.categoryName}</option>
                </c:forEach>
            </select>
            <div class="invalid-feedback">
                Please select a category.
            </div>
        </div>
        <input type="hidden" name="item_id" value="${item.id}"/>
        <input type="hidden" name="product_id" value="${itemSpecs.id}"/>
        <input type="hidden" name="category_id" value="${category.id}"/>
        <hr class="col-md-12">

        <button class="btn btn-primary btn-lg btn-block col-md-12" type="submit">Save</button>
    </form>

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
