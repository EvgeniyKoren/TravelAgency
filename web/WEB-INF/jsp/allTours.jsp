<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
    <h2>Bookable tours</h2>
    <table id="list" border="1">
        <thead>
        <tr>
            <td>№</td>
            <td>Country</td>
            <td>City</td>
            <td>Hotel</td>
            <td>Hotel type</td>
            <td>Nights(days)</td>
            <td>People Quantity</td>
            <td>Price</td>
            <td>Is Last Minute</td>
            <td>Type of tour</td>
            <td>Status</td>
        </tr>
        </thead>

<%--        <c:set var="k" value="0"/>--%>
        <c:forEach var="item" items="${allTours}">
<%--            <c:set var="k" value="${k+1}"/>--%>
            <tr>
<%--                <td><c:out value="${k}"/></td>--%>
                <td>${item.id}</td>
                <td>${item.country}</td>
                <td>${item.city}</td>
                <td>${item.hotelName}</td>
                <td>${item.hotelType}</td>
                <td>${item.duration}</td>
                <td>${item.peopleQuantity}</td>
                <td>${item.price}</td>
                <td>${item.lastMinute}</td>
                <td>${item.type}</td>
                <td>${item.status}</td>
            </tr>
        </c:forEach>
    </table>
<%--    <form action="controller?command=selectTour">--%>
<%--        <span>Select tour by type</span>--%>
<%--    </form>--%>
</body>
</html>
