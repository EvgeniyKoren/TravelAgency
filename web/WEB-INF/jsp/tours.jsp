<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:set var="title" value="Tours" scope="page" />

<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<jsp:include page="/WEB-INF/jsp/allTours.jsp" />
<c:if test="${empty user}">
    <div>
        <p>Only registered users can book a tour.</p>
        <p>Please, <a href="login.jsp"><i>log in </i></a> or
            <a href="controller?command=redirect&pageName=signIn"><i>sign in</i></a> </p>
    </div>
</c:if>
<c:if test="${not empty user}">
    <c:choose>

        <%--===========================================================================
        This way we define the ADMIN sub MENU.
        ===========================================================================--%>
        <c:when test="${userRole.name == 'admin' }">
<%--            delete tour form--%>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="deleteTour"/>
                <input type="number" name="tourId" placeholder="Tour id"/>
                <input type="submit" value="Delete">
            </form>
<%--            add tour form--%>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="addTour"/><br>
                <input type="text" name="country" placeholder="country"/><br>
                <input type="text" name="city" placeholder="city"/><br>
                <input type="text" name="hotelName" placeholder="hotel's name"/><br>
                <input type="number" name="hotelType" placeholder="hotel's type"/><br>
                <input type="number" name="duration" placeholder="tour's duration"/><br>
                <input type="number" name="peopleQuantity" placeholder="people quantity"/><br>
                <input type="number" name="price" placeholder="price"/><br>
                <span>Is last Minute</span><input type="checkbox" name="lastMinute" /><br>
                <p>
                    <select name="type" size="1">
<%--                        <option disabled selected>Select type</option>--%>
                        <option value="rest">Rest</option>
                        <option value="excursion">Excursion</option>
                        <option value="shoping">Shopping</option>
                    </select>
                </p>
                <input type="submit" value="Add">
            </form>
            <%--            change tour form--%>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="changeTour"/>
                <input type="number" name="tourId" placeholder="Tour id"/>
                <span>Is last Minute</span><input type="checkbox" name="lastMinute" /><br><p>
                <select name="status" size="1">
                        <%--                        <option disabled selected>Select type</option>--%>
                    <option value="free">Free</option>
                    <option value="registered">Registered</option>
                    <option value="paid">Paid</option>
                    <option value="canceled">Canceled</option>
                </select>
            </p>
                <input type="submit" value="Change info">
            </form>
        </c:when>

        <%--===========================================================================
        This way we define the MANAGER sub MENU.
        ===========================================================================--%>

        <c:when test="${userRole.name == 'manager' }">
<%--            change tour form--%>
            <form action="controller" method="post">
                <input type="hidden" name="command" value="changeTour"/>
                <input type="number" name="tourId" placeholder="Tour id"/>
                <span>Is last Minute</span><input type="checkbox" name="lastMinute" /><br><p>
                <select name="status" size="1">
                        <%--                        <option disabled selected>Select type</option>--%>
                    <option value="free">Free</option>
                    <option value="registered">Registered</option>
                    <option value="paid">Paid</option>
                    <option value="canceled">Canceled</option>
                </select>
            </p>
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
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
