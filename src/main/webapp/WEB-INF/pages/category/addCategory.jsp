<%-- 
    Document   : addCategory
    Created on : 31-Dec-2021, 11:46:45
    Author     : teodo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Add Category">
    <h1>Add Category</h1>
    <form class="needs-validation"  novalidate="" method="POST" action="${pageContext.request.contextPath}/Categories/AddCategory">
         <div class="row">
            <div class="col-md">
                <label for="name">Category Name</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="" value="" required="">
                <div class="invalid-feedback">
                    Category Name is required.
                </div>
            </div>
        </div>
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