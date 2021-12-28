<%-- 
    Document   : addUser
    Created on : 27-Dec-2021, 16:50:25
    Author     : teodo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Add User">
    <h1>Add User</h1>
    <form class="needs-validation"  novalidate="" method="POST" action="${pageContext.request.contextPath}/Users/Create">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="" value="" required="">
                <div class="invalid-feedback">
                    Username is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="" value="" required="">
                <div class="invalid-feedback">
                    Email is required.
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="" value="" required="">
                <div class="invalid-feedback">
                    Password is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="position">Position</label>
                <select class="custom-select d-block w-100" id="position" name="position" required="">
                    <option value="">Choose...</option>
                    <option value="ADMINISTRATOR">Administrator</option>
                    <option value="CLIENT">Client</option>
                    <option value="CASIER">Casier</option>
                    <option value="DG">DG</option>
                </select>
                <div class="invalid-feedback">
                    Please select a position.
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
