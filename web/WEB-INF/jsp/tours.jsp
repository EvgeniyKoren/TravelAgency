<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<html lang="${sessionScope.lang}">
<c:set var="title" value="Tours" scope="page" />

<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<div class="mx-auto text-center" style="width: 1200px">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <jsp:include page="/WEB-INF/jsp/allTours.jsp"/>
    <c:if test="${empty user}">
        <div>
            <p>Only registered users can book a tour.</p>
            <p>Please, <a href="login.jsp"><i>log in </i></a> or
                <a href="controller?command=redirect&pageName=signIn"><i>sign in</i></a></p>
        </div>
    </c:if>
    <c:if test="${not empty user}">
        <c:choose>

            <%--===========================================================================
            This way we define the ADMIN sub MENU.
            ===========================================================================--%>
            <c:when test="${userRole.name == 'admin' }">
                <%--            delete tour form--%>
                <div id="form_container"></div>
                <p>Delete tour:</p>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="deleteTour"/>
                    <input type="number" name="tourId" placeholder="Tour id"/>
                    <input type="submit" value="Delete">
                </form>
                </div>
                <%--            add tour form--%>
                <div id="form_container">
                    <p>Add tour or update an existing one</p>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="addOrUpdateTour"/><br>
                        <input type="text" name="country" placeholder="country"/><br>
                        <input type="text" name="city" placeholder="city"/><br>
                        <input type="text" name="hotelName" placeholder="hotel's name"/><br>
                        <input type="number" name="hotelType" placeholder="hotel's type"/><br>
                        <input type="number" name="duration" placeholder="tour's duration"/><br>
                        <input type="number" name="peopleQuantity" placeholder="people quantity" value="true"/><br>
                        <input type="number" name="price" placeholder="price"/><br>
                        <input type="number" name="sale" placeholder="sale"/><br>
                        <span>Is last Minute</span><input type="checkbox" name="lastMinute" value="true"/><br>
                        <p>
                            <select name="type" size="1">
                                <option disabled selected>Select type</option>
                                <option value="rest">Rest</option>
                                <option value="excursion">Excursion</option>
                                <option value="shopping">Shopping</option>
                            </select>

                            <span>Tour status: </span>
                            <select name="status" size="1">
                                <option disabled selected>Status</option>
                                <option value="free">Free</option>
                                <option value="registered">Registered</option>
                                <option value="paid">Paid</option>
                                <option value="canceled">Canceled</option>
                            </select>
                        </p>
                        <input type="submit" value="Add">
                        <input type="number" name="tourId" placeholder="Tour id"/>
                        <input type="submit" value="Update">
                    </form>
                </div>
            </c:when>

            <%--===========================================================================
            This way we define the MANAGER sub MENU.
            ===========================================================================--%>

            <c:when test="${userRole.name == 'manager' }">
                <%--            change tour form--%>
                <form action="controller" method="post">
                    <p>Change information about tour:</p>
                    <input type="hidden" name="command" value="changeTour"/>
                    <input type="number" name="tourId" placeholder="Tour id"/>
                    <input type="number" name="sale" placeholder="sale"/>
                    <span>Is last Minute</span><input type="checkbox" name="lastMinute" value="true"/>
                    <span>Tour status: </span>
                    <select name="status" size="1">
                        <option disabled selected>Status</option>
                        <option value="free">Free</option>
                        <option value="registered">Registered</option>
                        <option value="paid">Paid</option>
                        <option value="canceled">Canceled</option>
                    </select>
                    <input type="submit" value="Change info">
                </form>
            </c:when>
            <%--===========================================================================
            This way we define the USER sub MENU.
            ===========================================================================--%>
            <c:when test="${userRole.name == 'customer'}">
                <%--            order tour form--%>
                <c:choose>
                    <c:when test="${user.status == false}">
                        <div id="form_container">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="order"/>
                                <input type="number" name="tourId" placeholder="Tour id"/>
                                <input type="submit" value="Booking...">
                            </form>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p>You are banned! Banned user can't book a tour.</p>
                    </c:otherwise>
                </c:choose>

            </c:when>
        </c:choose>

        <div>
            <a href="controller?command=logout">Logout</a>
        </div>
    </c:if>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
