<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<html lang="${sessionScope.lang}">
<c:set var="title" value="Tours" scope="page" />

<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="container text-center bg-light p-0">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <jsp:include page="/WEB-INF/jsp/allTours.jsp"/>
    <c:if test="${empty user}">
        <div>
            <p><fmt:message key="tours.warning.registered" /></p>
            <p><fmt:message key="tours.please" /> <a href="login.jsp"><i><fmt:message key="tours.login" /> </i>
                </a> <fmt:message key="tours.or" />
                <a href="controller?command=redirect&pageName=signIn"><i><fmt:message key="tours.signIn" /></i></a></p>
        </div>

    </c:if>
    <c:if test="${not empty user}">
        <c:choose>

            <%--===========================================================================
            This way we define the ADMIN sub MENU.
            ===========================================================================--%>
            <c:when test="${userRole.name == 'admin' }">
                <%--            delete tour form--%>
                <hr>
                <div id="form_container">
                <p><fmt:message key="tours.admin.delete" /></p>
                <form action="controller" method="post" class="pb-4">
                    <input type="hidden" name="command" value="deleteTour"/>
                    <fmt:message key="orders.tourId" var="tour_id" />
                    <input type="number" name="tourId" placeholder="${tour_id}"/>
                    <fmt:message key="tours.delete" var="delete" />
                    <input type="submit" value="${delete}">
                </form>
                </div>
                <%--            add tour form--%>
                <hr>
                <div id="form_container">
                    <p><fmt:message key="tours.admin.addOrUpdate" /></p>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="addOrUpdateTour"/><br>
                        <fmt:message key="tours.country" var="country"/>
                        <input type="text" name="country" placeholder="${country}"/><br>
                        <fmt:message key="tours.city" var="city"/>
                        <input type="text" name="city" placeholder="${city}"/><br>
                        <fmt:message key="tours.hotel" var="hotel"/>
                        <input type="text" name="hotelName" placeholder="${hotel}"/><br>
                        <fmt:message key="tours.hotel.type" var="hotel_type"/>
                        <input type="number" name="hotelType" placeholder="${hotel_type}"/><br>
                        <fmt:message key="tours.duration" var="duration"/>
                        <input type="number" name="duration" placeholder="${duration}"/><br>
                        <fmt:message key="tours.people.quantity" var="people_quantity"/>
                        <input type="number" name="peopleQuantity" placeholder="${people_quantity}" value="true"/><br>
                        <fmt:message key="tours.price" var="price"/>
                        <input type="number" name="price" placeholder="${price}"/><br>
                        <fmt:message key="tours.sale" var="sale"/>
                        <input type="number" name="sale" placeholder="${sale}"/><br>
                        <span class="p-3 d-inline-block"><fmt:message key="tours.lastMinute" /></span><input type="checkbox" name="lastMinute" value="true"/><br>
                        <p>
                            <select name="type" size="1">
                                <option disabled selected><fmt:message key="tours.type" /></option>
                                <option value="rest"><fmt:message key="tours.rest" /></option>
                                <option value="excursion"><fmt:message key="tours.excursion" /></option>
                                <option value="shopping"><fmt:message key="tours.shopping" /></option>
                            </select>

                            <span><fmt:message key="tours.tourStatus" />: </span>
                            <select name="status" size="1">
                                <option disabled selected><fmt:message key="tours.status" /></option>
                                <option value="free"><fmt:message key="tours.free" /></option>
                                <option value="registered"><fmt:message key="tours.registered" /></option>
                                <option value="paid"><fmt:message key="tours.paid" /></option>
                                <option value="canceled"><fmt:message key="tours.canceled" /></option>
                            </select>
                        </p>
                        <fmt:message key="tours.add" var="add"/>
                        <input type="submit" value="${add}">
                        <fmt:message key="orders.tourId" var="tour_id" />
                        <input type="number" name="tourId" placeholder="${tour_id}"/>
                        <fmt:message key="tours.update" var="update"/>
                        <input type="submit" value="${update}">
                    </form>
                </div>
            </c:when>

            <%--===========================================================================
            This way we define the MANAGER sub MENU.
            ===========================================================================--%>

            <c:when test="${userRole.name == 'manager' }">
                <%--            change tour form--%>
                <form action="controller" method="post">
                    <p><fmt:message key="tours.change.tourInfo" /></p>
                    <input type="hidden" name="command" value="changeTour"/>
                    <fmt:message key="orders.tourId" var="tour_id" />
                    <input type="number" name="tourId" placeholder="${tour_id}"/>
                    <fmt:message key="tours.sale" var="sale"/>
                    <input type="number" name="sale" placeholder="${sale}"/>
                    <span><fmt:message key="tours.lastMinute" /></span><input type="checkbox" name="lastMinute" value="true"/>
                    <span><fmt:message key="tours.tourStatus" />: </span>
                    <select name="status" size="1">
                        <option disabled selected><fmt:message key="tours.status" /></option>
                        <option value="free"><fmt:message key="tours.free" /></option>
                        <option value="registered"><fmt:message key="tours.registered" /></option>
                        <option value="paid"><fmt:message key="tours.paid" /></option>
                        <option value="canceled"><fmt:message key="tours.canceled" /></option>
                    </select>
                    <fmt:message key="tours.update" var="update"/>
                    <input type="submit" value="${update}">
                </form>
            </c:when>
            <%--===========================================================================
            This way we define the USER sub MENU.
            ===========================================================================--%>
            <c:when test="${userRole.name == 'customer'}">
                <%--            order tour form--%>
                <c:choose>
                    <c:when test="${user.status == false}">
                        <hr>
                        <div id="form_container">
                            <h5 class="p-4"><fmt:message key="tours.book.tour" /></h5>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="order"/>
                                <fmt:message key="orders.tourId" var="tour_id" />
                                <input type="number" name="tourId" placeholder="${tour_id}"/>
                                <fmt:message key="tours.book" var="book" />
                                <input type="submit" value="book">
                            </form>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p><fmt:message key="tours.banned.user" /></p>
                    </c:otherwise>
                </c:choose>

            </c:when>
        </c:choose>

        <div>
            <a href="controller?command=logout"><fmt:message key="header.logout" /></a>
        </div>
    </c:if>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
