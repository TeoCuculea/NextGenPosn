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
    <!--<form method="POST" action="${pageContext.request.contextPath}/Sales">
        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/Sales/AddISales" role="button">Add sales?</a>
        <button class="btn btn-danger" type="submit">Delete Sales</button>-->
    <table name="sale" style="width:100%">
        <tr>
            <th>Sale ID</th>
            <th>Date</th>
            <th>IsComplete</th>
            <th>Change</th>
            <th>Total</th>
            
            <!-- comment<th>Sale Payment_ID</th> <th>Payment_ID</th>
            <th>Payment Amount</th>-->
        </tr>
        <c:forEach var="sale" items="${sales}" varStatus="status">
            <tr>
                <td>${sale.id}</td>
                <td>${sale.date}</td>  
                <td>${sale.isComplete}</td> 
                <td>${sale.change}</td> 
                <td>${sale.total}</td> 
                
                <!-- <td>{sale.getPayment}</td> comment<td>{payment.id}</td>
                <td>{payment.amount}</td>-->
        </tr>
    </c:forEach>
</table>
</form>
</t:pageTemplate>