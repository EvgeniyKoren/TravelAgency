<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="m" uri="http://nure.ua/finalTask" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="fields"/>

<html lang="${sessionScope.lang}">

<body>
<c:if test="${not empty allTours}">
    <div>
        <h2 class="text-info m-4"><fmt:message key="index.tours" /></h2>
        <div class="container-fluid overflow-auto mb-5" style="max-height: 400px;">
            <table id="list" class="mx-auto table table-striped">
                <thead>
                    <tr class="text-center bg-secondary text-light">
                        <td>&#8470;</td>
                        <td><fmt:message key="tours.country" /></td>
                        <td><fmt:message key="tours.city" /></td>
                        <td><fmt:message key="tours.hotel" /></td>
                        <td><fmt:message key="tours.hotel.type" /></td>
                        <td><fmt:message key="tours.duration" /></td>
                        <td><fmt:message key="tours.people.quantity" /></td>
                        <td><fmt:message key="tours.price" /></td>
                        <td><fmt:message key="tours.lastMinute" /></td>
                        <td><fmt:message key="tours.type" /></td>
                        <td><fmt:message key="tours.status" /></td>
                        <td><fmt:message key="tours.sale" />, %</td>
                    </tr>
                </thead>
    
                <c:forEach var="item" items="${allTours}">
                    <tr>
                        <td class="text-center">${item.id}</td>
                        <td>${item.country}</td>
                        <td>${item.city}</td>
                        <td>${item.hotelName}</td>
                        <td class="text-center"><m:reflectStars stars="${item.hotelType}"/></td>
                        <td class="text-center">${item.duration}</td>
                        <td class="text-center">${item.peopleQuantity}</td>
                        <td class="text-center">${item.price}</td>
                        <td class="text-center"><m:showLastMinute lastMinute="${item.lastMinute}"/></td>
                        <td class="text-center">${item.type}</td>
                        <td class="text-center">${item.status}</td>
                        <c:choose>
                            <c:when test="${item.sale != 0}">
                                <b><c:set var="sale" value="${item.sale}"/></b>
                            </c:when>
                            <c:otherwise>
                                <b><c:set var="sale" value=""/></b>
                            </c:otherwise>
                        </c:choose>
                        <td class="text-center">${sale}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</c:if>
<c:if test="${empty allTours}">
    <p><fmt:message key="tours.not.available" /></p>
</c:if>
<hr>
<div id="form_container" class="container-fluid">
    <h5 class="p-4"><fmt:message key="tours.select.tour" /></h5>
    <form action="controller" class="d-flex">
        <input type="hidden" name="command" value="showTours"/>
        <select class='p-2 mx-2 flex-grow-1' name="filterType" size="1">
            <option disabled selected><fmt:message key="tours.type" /></option>
            <option value="rest"><fmt:message key="tours.rest" /></option>
            <option value="excursion"><fmt:message key="tours.excursion" /></option>
            <option value="shopping"><fmt:message key="tours.shopping" /></option>
        </select>
        <fmt:message key="tours.price" var="price"/>
        <input class='p-2 mx-2 flex-grow-1' type="number" min="1" name="filterPrice" placeholder="${price}"/>
        <fmt:message key="tours.people.quantity" var="people_quantity"/>
        <input class='p-2 mx-2 flex-grow-1' type="number" min="1" name="filterPeopleQuantity" placeholder="${people_quantity}"/>
        <fmt:message key="tours.hotel.type" var="hotel_type"/>
        <input class='p-2 mx-2 flex-grow-1' type="number" name="filterHotelType" placeholder="${hotel_type}"/>
        <fmt:message key="tours.select.button" var="select"/>
        <input class='p-2 mx-2 flex-grow-1' type="submit" value="${select}">
    </form>
</div>
<div>
    <p><fmt:message key="tours.all.available" /> <a href="controller?command=showTours"><fmt:message key="tours.all" /></a></p>
</div>
</body>
</html>
