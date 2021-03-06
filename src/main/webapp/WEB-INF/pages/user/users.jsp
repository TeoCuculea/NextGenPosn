<%-- 
    Document   : users
    Created on : 27-Dec-2021, 16:50:37
    Author     : teodo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Users">
    <h1>Users</h1>
    <form method="POST" action="${pageContext.request.contextPath}/Users">
        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/Users/Create" role="button">Add
            User</a>
        <div class="row">
            <div class="col-md-1">
                <h5> Checkbox</h5>
            </div>
            <div class="col-md-3">
                <h4>Username</h4>
            </div>
            <div class="col-md-3">
                <h4>Email</h4>
            </div>
            <div class="col-md-3">
                <h4>Position</h4>
            </div>
        </div>

        <c:forEach var="user" items="${users}" varStatus="status">
            <div class="row">
                <div class="col-md-1">
                    <input type="checkbox" name="user_ids" value="${user.id}"/>
                </div>                
                <div class="col-md-3">
                    ${user.username}
                </div>
                <div class="col-md-3">
                    ${user.email}
                </div>
                <div class="col-md-3">
                    ${user.position}
                </div>
            </div>
        </c:forEach>
    </form>
</t:pageTemplate>
