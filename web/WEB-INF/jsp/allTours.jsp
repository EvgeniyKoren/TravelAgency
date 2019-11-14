<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="fields"/>

<html lang="${sessionScope.lang}">

<body>
<c:if test="${not empty allTours}">
    <div>
        <h2 class="text-info"><fmt:message key="tours.welcome" /></h2>
        <table id="list" border="1" class="mx-auto mb-5 table table-striped">
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
                <td>Sale, %</td>
            </tr>
            </thead>

            <c:forEach var="item" items="${allTours}">
                <tr>
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
                    <c:choose>
                        <c:when test="${item.sale != 0}">
                            <b><c:set var="sale" value="${item.sale}"/></b>
                        </c:when>
                        <c:otherwise>
                            <b><c:set var="sale" value=""/></b>
                        </c:otherwise>
                    </c:choose>
                    <td>${sale}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
<c:if test="${empty allTours}">
    <p>No tours are available by such criteria</p>
</c:if>
<div id="form_container">
    <form action="controller">
        <p>Select tour by:</p>
        <input type="hidden" name="command" value="showTours"/>
        <select name="filterType" size="1">
            <option disabled selected>type of tour</option>
            <option value="rest">rest</option>
            <option value="excursion">excursion</option>
            <option value="shopping">shopping</option>
        </select>
        <input type="number" name="filterPrice" placeholder="price"/>
        <input type="number" name="filterPeopleQuantity" placeholder="people quantity"/>
        <input type="number" name="filterHotelType" placeholder="hotel's type"/>
        <input type="submit" value="Select">
    </form>
</div>
<div>
    <p>All available tours: <a href="controller?command=showTours">all tours</a></p>
</div>
</body>
</html>
