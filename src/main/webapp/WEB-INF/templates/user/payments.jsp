<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="messages" />
<c:set var="prevpage" value="payments" />
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" type="text/css" href="../../../static/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../../static/css/home.css"/>
    <link rel="stylesheet" type="text/css" href="../../../static/css/header.css"/>
    <title><fmt:message key="payments.list" /></title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../fragments/nav-language.jsp">
        <jsp:param name="page" value="payments"/>
    </jsp:include>

    <jsp:include page="../fragments/nav-logout.jsp"/>


    <div class="container-fluid" style="margin-top:40px;">
        <span><fmt:message key="text.welcome" /> <c:out value="${sessionScope.activeUserName}" /></span>
        <hr>
        <c:if test="${requestScope.errorMessage != null}">
            <div class="container-fluid">
                <h3 style="color:darkred"><fmt:message key="${requestScope.errorMessage}"/></h3>
                <c:remove var="errorMessage" scope="request" />
            </div>
        </c:if>
        <c:if test="${requestScope.infoMessage != null}">
            <div class="container-fluid">
                <p><fmt:message key="${requestScope.infoMessage}"/></p>
                <c:remove var="infoMessage" scope="request" />
            </div>
        </c:if>
        <c:if test="${requestScope.successMessage != null}">
            <div class="container-fluid">
                <p class="text-success"><fmt:message key="${requestScope.successMessage}"/></p>
                <c:remove var="infoMessage" scope="request" />
            </div>
        </c:if>
    </div>

    <div class="container-fluid">
        <ul class="nav nav-pills">
            <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/controller?command=userhome">
                <fmt:message key="accounts.list" />
            </a></li>
            <li class="nav-item"><a class="nav-link active" href="${pageContext.servletContext.contextPath}/controller?command=payments">
                <fmt:message key="payments.list" />
            </a></li>
        </ul>
        <hr>
    </div>

    <div class="container-fliud">
        <table class = "table table-bordered table-striped table-responsive-md table-hover">
            <thead class="thead-dark">
            <tr>
                <th><a href="${pageContext.servletContext.contextPath}/controller?command=payments&currentpage=${requestScope.currentPage}&sortfield=payment_id&sortdir=${requestScope.reverseSortDir}"
                       ><fmt:message key="payment.paymentId"/></a>
                </th>
                <th><fmt:message key="payment.from"/></th>
                <th><fmt:message key="payment.to"/></th>
                <th><a href="${pageContext.servletContext.contextPath}/controller?command=payments&currentpage=${requestScope.currentPage}&sortfield=payment_date&sortdir=${requestScope.reverseSortDir}"
                       ><fmt:message key="payment.paymentDate"/></a>
                </th>
                <th><a href="${pageContext.servletContext.contextPath}/controller?command=payments&currentpage=${requestScope.currentPage}&sortfield=payment_amount&sortdir=${requestScope.reverseSortDir}"
                       ><fmt:message key="payment.paymentAmount"/></a>
                </th>
                <th><fmt:message key="payment.paymentStatus"/></th>
                <th><fmt:message key="label.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="payment" items="${requestScope.payments}" varStatus = "status">
            <tr ${((requestScope.payments.size() > requestScope.pageSize) and requestScope.sortDir.equalsIgnoreCase("asc") and status.last)
                    or ((requestScope.payments.size() > requestScope.pageSize) and requestScope.sortDir.equalsIgnoreCase("desc") and status.last)
                    ? 'style = "display:none"' : ''}>
                <td>${payment.paymentId}</td>
                <td>${payment.bankAccount.bankAccountNumber}</td>
                <td>${payment.toBankAccount}</td>
                <td>${f:formatLocalDateTime(payment.paymentDate, 'dd-MM-yyyy HH:mm')}</td>
                <td style="text-align: right;">${payment.paymentAmount} ${payment.bankAccount.currency}</td>
                <td><c:choose>
                    <c:when test="${payment.sent}"><fmt:message key="payment.sent"/></c:when>
                    <c:otherwise><fmt:message key="payment.notsent"/></c:otherwise>
                </c:choose></td>
                <td>
                    <c:if test="${!payment.sent}">
                        <c:set var="msg"><fmt:message key="message.confirm.payment"/></c:set>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=confirmpayment&paymentid=${payment.paymentId}&id=${payment.bankAccountId}&paymentamount=${payment.paymentAmount}&currency=${payment.bankAccount.currency}&bankaccountfrom=${payment.bankAccount.bankAccountNumber}&tobankaccount=${payment.toBankAccount}"
                       onclick="return confirm('${msg}')"
                       class="btn btn-info"><fmt:message key="payment.sent.payment"/></a>
                    </c:if>
                    <c:if test="${!payment.sent}">
                        <c:set var="msg2"><fmt:message key="message.confirm.delete"/></c:set>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=deletepayment&paymentid=${payment.paymentId}"
                       onclick="return confirm('${msg2}')"
                       class="btn btn-danger"><fmt:message key="payment.delete.payment"/></a>
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
                               href="${pageContext.servletContext.contextPath}/controller?command=payments&currentpage=<c:out value="${requestScope.currentPage - 1}"/>&sortfield=<c:out value="${requestScope.sortField}"/>&sortdir=<c:out value="${requestScope.sortDir}" />"><fmt:message key="label.previouspage"/></a>
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
                               href="${pageContext.servletContext.contextPath}/controller?command=payments&currentpage=<c:out value="${requestScope.currentPage + 1}"/>&sortfield=<c:out value="${requestScope.sortField}"/>&sortdir=<c:out value="${requestScope.sortDir}" />">
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
</body>
</html>