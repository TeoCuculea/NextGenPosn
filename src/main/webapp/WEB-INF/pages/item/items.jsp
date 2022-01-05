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
        <table name="items" width="100%">
            <th>Select <br>Item</th>
            <th>Item Id</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Description</th>
            <th>Price/<br>Item[RON]</th>
            <th>Category</th>
            <th>Edit</th>
        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/Items/AddItem" role="button">Add item</a>
        <button class="btn btn-danger" type="submit">Delete Items</button>
        <c:forEach var="item" items="${items}" varStatus="status">
            <tr>
                    <td><input type="checkbox" name="item_ids" value="${item.id}"/></td>
                    <td>${item.id}</td>
                    <td>${itemSpecs[status.index].name}</td>
                    <td>${item.quantity}</td>
                    <td>${itemSpecs[status.index].description}</td>
                    <td>${itemSpecs[status.index].pricePerUnit}</td>
                    <td>${categories[status.index].categoryName}</td>
                    <td><a class="btn btn-secondary" href="${pageContext.request.contextPath}/Items/EditItem?id=${item.id}" role="button">Edit Item</a></td>
        </tr>
        </c:forEach>
        </table>
    </form>
</t:pageTemplate>