<%-- 
    Document   : items
    Created on : 24-Nov-2021, 13:05:21
    Author     : teodo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Items">
    <h1>Items</h1>
    <form method="POST" action="${pageContext.request.contextPath}/Items">
        <a class="btn btn-primary btn-lg" href="/NextGenPos/Items/AddItem" role="button">Add item</a>
        <button class="btn btn-danger" type="submit">Delete Items</button>
        <c:forEach var="item" items="${items}" varStatus="status">
            <div class="row">
                <div class="col-md">
                    <input type="checkbox" name="item_ids" value="${item.id}"/>
                </div>
                 <div class="col-md">
                    ${item.id}
                </div>
                <div class="col-md-4">
                    ${item.quantity}
                </div>
                <div class="col-md-3">
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditItem?id=${item.id}" role="button">Edit Item</a>
                </div>
            </div>
        </c:forEach>
    </form>
</t:pageTemplate>