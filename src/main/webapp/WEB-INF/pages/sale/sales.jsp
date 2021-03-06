<%-- 
    Document   : sales
    Created on : 28-Dec-2021, 17:15:58
    Author     : teodo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Sales">
    <h1>Sales</h1>
    <table name="sale" style="width:100%">
        <tr>
            <th>Sale ID</th>
            <th>Date</th>
            <th>Is Complete</th>
            <th>Change</th>
            <th>Total</th>
            <th>Payment_ID</th>
            <th>Payment Amount</th>
            <th>Process return</th>
        </tr>
        <c:forEach var="sale" items="${sales}" varStatus="status">
            <tr>
                <td>${sale.id}</td>
                <td>${sale.date}</td>  
                <td>${sale.isComplete}</td> 
                <td>${Math.round(sale.change*100)/100}</td> 
                <td>${sale.total}</td> 
                <td>${payments[status.index].id}</td>
                <td>${payments[status.index].amount}</td>
                <td><a href="${pageContext.request.contextPath}/Sales/ProcessReturn?id=${sale.id}" 
                    class="btn btn-lg btn-primary" >Process Return</a></td>
        </tr>
    </c:forEach>
</table>
</form>
</t:pageTemplate>