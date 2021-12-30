<%-- 
    Document   : menu
    Created on : 24-Nov-2021, 12:14:41
    Author     : teodo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}">NextGenPOS</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <c:if test="${pageContext.request.isUserInRole('Admin')}">

                    <li class="nav-item ${activePage eq 'Items' ? 'active' : ''}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Items">Items</a>
                    </li>
                    </c:if>
                    <c:if test="${pageContext.request.isUserInRole('Sales')}">
                    <li class="nav-item ${activePage eq 'Sales' ? 'active' : ''}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Sales">Sales</a>
                    </li>
                    </c:if>
                    <c:if test="${pageContext.request.isUserInRole('Admin')}">
                        <li class="nav-item ${activePage eq 'Users' ? 'active' : ''}">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Users">Users</a>
                        </li>
                    </c:if>
                </ul>
                <ul class="navbar-nav d-flex">
                    <li class="nav-item">
                        <c:choose>
                            <c:when test="${pageContext.request.getRemoteUser() == null}">
                                <a href="${pageContext.request.contextPath}/Login">Login</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/Logout">Logout</a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>