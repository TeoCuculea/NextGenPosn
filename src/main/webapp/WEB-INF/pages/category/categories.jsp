<%-- 
    Document   : categories
    Created on : 31-Dec-2021, 11:38:28
    Author     : teodo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Category">
    <h1>Category</h1>
    <form method="POST" action="${pageContext.request.contextPath}/Categories">
        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/Categories/AddCategory" role="button">Add category</a>
        <button class="btn btn-danger" type="submit">Delete Category</button>
        <c:forEach var="category" items="${categories}" varStatus="status">
             <div class="row">
                <div class="col-md">
                    <input type="checkbox" name="category_ids" value="${category.id}"/>
                </div>
                 <div class="col-md">
                    ${category.id}
                </div>
                <div class="col-md-2">
                    ${category.categoryName}
                </div>
                <div class="col-md-3">
                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/Categories/EditCategory?id=${category.id}" role="button">Edit Category</a>
                </div>
            </div>
        </c:forEach>
    </form>
</t:pageTemplate>
