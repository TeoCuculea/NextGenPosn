<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Login">

    <c:if test="${message != null}">
        <div class="alert alert-danger" role="alert">
                ${message}
        </div>
    </c:if>

    <form class="form-signin" method="POST" action="j_security_check">
        <div class="row justify-content-center">
            <div class="col-md-4  d-flex justify-content-center">
                <h1 class="h3 my-3 font-weight-normal">Please sign in</h1>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-4">
                <label for="inputEmail" class="sr-only">Username</label>
                <input autofocus
                       class="form-control"
                       id="inputEmail"
                       name="j_username"
                       placeholder="Username"
                       required
                       type="text"
                >
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-4">
                <label for="inputPassword" class="sr-only">Password</label>
                <input class="form-control mb-5"
                       id="inputPassword"
                       name="j_password"
                       placeholder="Password"
                       required
                       type="password"
                >
            </div>
        </div>
        <div class="row">
            <div class="col d-flex justify-content-center">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </div>
        </div>
    </form>

</t:pageTemplate>