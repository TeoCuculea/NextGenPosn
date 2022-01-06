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
<%--        <c:if test="${pageContext.request.isUserInRole('DirectorGeneral')}">--%>
        <p id="info">Aici apar notificarile.</p>
        <p id="salut"></p>
        <script>
                function subscribe(publisher) {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                        if (this.readyState === 4 && this.status === 200) {
                                document.getElementById("info").innerHTML = this.responseText;
                                subscribe(publisher);
                        }
                };
                xhttp.open("GET", publisher, true);
                xhttp.send();
                document.getElementById("salut").innerHTML = "Salut din JS";
                }
        </script>
<%--        </c:if>--%>
</t:pageTemplate>