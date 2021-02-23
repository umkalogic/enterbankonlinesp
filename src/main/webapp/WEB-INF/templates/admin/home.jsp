<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="messages" />
<c:set var="prevpage" value="adminhome" />
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" type="text/css" href="../../../static/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../../static/css/home.css"/>
    <link rel="stylesheet" type="text/css" href="../../../static/css/header.css"/>
    <title><fmt:message key="admin.page" /></title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../fragments/nav-language.jsp">
        <jsp:param name="page" value="adminhome"/>
    </jsp:include>
    <form action="controller" method="get">
        <button class="btn btn-md btn-danger btn-block"
                type="Submit" name="command" value="logout"><fmt:message key="label.logout" /></button>
    </form>
    <div class="container-fluid" style="margin-top:40px;">
        <span><fmt:message key="text.welcome" /> <c:out value="${sessionScope.activeUserName}" /></span>
    </div>
    <c:if test="${requestScope.errorMessage != null}">
        <div class="container-fluid">
            <h3 style="color:darkred"><c:out value="${requestScope.errorMessage}"/></h3>
        </div>
    </c:if>
    <div class="container-fluid">
        <h1><fmt:message key="users.list" /></h1>
        <table class = "table table-bordered table-striped table-responsive-md table-hover" id="userpersondata">
            <thead class="thead-dark">
            <tr>
                <th><fmt:message key="user.id" /></th>
                <th>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=showusers&currentpage=<c:out value="${requestScope.currentPage}" />&sortfield=user_name&sortdir=<c:out value="${requestScope.reverseSortDir}" />"><fmt:message key="user.username" /></a>
                </th>
                <th><fmt:message key="user.lastname" /></th>
                <th><fmt:message key="user.firstname" /></th>
                <th><fmt:message key="user.middlename" /></th>
                <th><fmt:message key="user.birthdate" /></th>
                <th>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=showusers&currentpage=<c:out value="${requestScope.currentPage}"/>&sortfield=email&sortdir=<c:out value="${requestScope.reverseSortDir}" />"><fmt:message key="user.email" /></a>
                </th>
                <th><fmt:message key="user.phonenumber" /></th>
                <th><fmt:message key="user.enabled" /></th>
                <th><fmt:message key="label.actions" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="userdata" items="${requestScope.listUserPersonData}" varStatus = "status">
            <tr ${((requestScope.listUserPersonData.size() > requestScope.pageSize) and requestScope.sortDir.equalsIgnoreCase("asc") and status.last)
            or ((requestScope.listUserPersonData.size() > requestScope.pageSize) and requestScope.sortDir.equalsIgnoreCase("desc") and status.first)
            ? 'style = "display:none"' : ''}>
                <td>${userdata.userId}</td>
                <td>${userdata.userName}</td>
                <td>${userdata.lastName}</td>
                <td>${userdata.firstName}</td>
                <td>${userdata.middleName}</td>
                <td>${f:formatLocalDateTime(userdata.birthDate, 'dd.MM.yyyy')}</td>
                <td>${userdata.userEmail}</td>
                <td>${userdata.phoneCountryCode} ${userdata.phone}</td>
                <td><fmt:message key="${userdata.active ? 'user.enabled' : 'user.disabled' }" /> </td>
                <td>
                    <form class="btn" action="controller" method="get">
                        <input type="hidden" name="id" value="${userdata.userId}" />
                        <button class="btn btn-success" name="command" value="showuseraccounts" type="Submit">
                            <fmt:message key="user.showFormForUserAccounts" /></button>
                    </form>
                    <form class="btn" action="${pageContext.servletContext.contextPath}/controller?command=showformforuserupdate" method="get">
                        <input type="hidden" name="id" value="${userdata.userId}" />
                        <button class="btn btn-warning" type="Submit">
                            <fmt:message key="user.update" /></button>
                    </form>
                    <c:if test="${userdata.active}">
                    <form class="btn" action="${pageContext.servletContext.contextPath}/controller?command=changeuserstatus" method="post">
                        <input type="hidden" name="id" value="${userdata.userId}" />
                        <input type="hidden" name="isactive" value="false" />
                        <c:set var="msg"><fmt:message key="message.delete"/></c:set>
                        <button class="btn btn-danger" type="Submit"
                                onclick="return confirm(${msg})">
                            <fmt:message key="user.deactivate" /></button>
                    </form>
                    </c:if>
                    <c:if test="${not userdata.active}">
                        <form class="btn" action="${pageContext.servletContext.contextPath}/controller?command=changeuserstatus" method="post">
                            <input type="hidden" name="id" value="${userdata.userId}" />
                            <input type="hidden" name="isactive" value="true" />
                            <c:set var="msg"><fmt:message key="message.enable"/></c:set>
                            <button class="btn btn-secondary" type="Submit"
                                    onclick="return confirm(${msg})">
                                <fmt:message key="user.activate" /></button>
                        </form>
                    </c:if>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>
        <hr>
        <div class="row">
            <div class="pagination">
                <span class = "page-item">
                    <c:choose>
                        <c:when test = "${requestScope.prev}">
                            <a class="page-link"
                            href="${pageContext.servletContext.contextPath}/controller?command=showusers&currentpage=<c:out value="${requestScope.currentPage - 1}"/>&sortfield=<c:out value="${requestScope.sortField}"/>&sortdir=<c:out value="${requestScope.sortDir}" />"><fmt:message key="label.previouspage"/></a>
                        </c:when>
                        <c:otherwise>
                            <span class="page-link"><fmt:message key="label.previouspage"/></span>
                        </c:otherwise>
                    </c:choose>
                </span>
                <span class = "page-item">
                    <c:choose>
                        <c:when test = "${requestScope.next}">
                            <a class="page-link"
                            href="${pageContext.servletContext.contextPath}/controller?command=showusers&currentpage=<c:out value="${requestScope.currentPage + 1}"/>&sortfield=<c:out value="${requestScope.sortField}"/>&sortdir=<c:out value="${requestScope.sortDir}" />">
                            <fmt:message key="label.nextpage"/></a>
                        </c:when>
                        <c:otherwise>
                            <span class="page-link"><fmt:message key="label.nextpage"/></span>
                        </c:otherwise>
                    </c:choose>
                </span>
            </div>
        </div>
            <br><br><br><br>
    </div>
</div>

<script src="../../../static/js/jquery-3.2.1.slim.min.js"></script>
<script src="../../../static/js/popper.min.js"></script>
<script src="../../../static/bootstrap/bootstrap.min.js"></script>
<script src="../../../static/js/enabledisable.js"></script>
</body>
</html>
