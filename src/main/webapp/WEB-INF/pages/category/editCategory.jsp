<%-- 
    Document   : editCategory
    Created on : 31-Dec-2021, 11:47:05
    Author     : teodo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Edit Category">
    <h1>Edit Category</h1>
    <form class="needs-validation"  novalidate="" method="POST" action="${pageContext.request.contextPath}/Categories/EditCategory">
         <div class="row">
            <div class="col-md">
                <label for="name">Category Name</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="" value="${category.categoryName}" required="">
                <div class="invalid-feedback">
                    Category Name is required.
                </div>
            </div>
        </div>
        <input type="hidden" name="category_id" value="${category.id}"/>
        <input type="hidden" name="product_id" value="${itemSpecs.id}"/>
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
