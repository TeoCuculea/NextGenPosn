<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="NextGen POS System">
        <h1>Welcome to the NextGen POS System</h1>
        <c:choose>
                <c:when test="${pageContext.request.getRemoteUser() == null}">
                        <h1>Un simplu client nelogat esti in acest moment! </h1>
                </c:when>
                <c:otherwise>
                        <h2>Esti logat ca si: ${pageContext.request.getRemoteUser()}</h2>
                </c:otherwise>
        </c:choose>
</t:pageTemplate>