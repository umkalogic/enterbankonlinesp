<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://example.com/functions" prefix="f" %>
<%@ taglib prefix="fmr" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="messages" />
<c:set var="prevpage" value="userhome" />
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" type="text/css" href="../../../static/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../../static/css/home.css"/>
    <link rel="stylesheet" type="text/css" href="../../../static/css/header.css"/>
    <title><fmt:message key="user.page" /></title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="../fragments/nav-language.jsp">
        <jsp:param name="page" value="userhome"/>
    </jsp:include>

    <form action="controller" method="get">
        <button class="btn btn-md btn-dark btn-block"
                type="Submit" name="command" value="logout"><fmt:message key="label.logout" /></button>
    </form>

    <div class="container-fluid" style="margin-top:40px;">
        <span><fmt:message key="text.welcome" /> <c:out value="${sessionScope.activeUserName}" /></span>
        <hr>
    </div>
    <c:if test="${requestScope.errorMessage != null}">
        <div class="container-fluid">
            <h3 style="color:darkred"><c:out value="${requestScope.errorMessage}"/></h3>
        </div>
        <br>
    </c:if>
    <div class="container-fluid">
        <ul class="nav nav-pills">
            <li class="nav-item"><a class="nav-link active" href="${pageContext.servletContext.contextPath}/controller?command=userhome">
                <fmt:message key="accounts.list" />
            </a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/controller?command=payments">
                <fmt:message key="payments.list" />
            </a></li>
        </ul>
        <hr>
    </div>

    <div class="container-fliud">
        <table class = "table table-bordered table-striped table-responsive-md table-hover">
            <thead class="thead-dark">
            <tr>
                <th><a href="${pageContext.servletContext.contextPath}/controller?command=userhome&sortfield=bank_account_number&sortdir=<c:out value="${requestScope.reverseSortDir}" />"
                       ><fmt:message key="account.number"/></a>
                </th>
                <th><a href="${pageContext.servletContext.contextPath}/controller?command=userhome&sortfield=account_type&sortdir=<c:out value="${requestScope.reverseSortDir}" />"
                       ><fmt:message key="account.type"/></a>
                </th>
                <th><fmt:message key="account.showAccountCreditCards"/></th>
                <th><a href="${pageContext.servletContext.contextPath}/controller?command=userhome&sortfield=account_amount&sortdir=<c:out value="${requestScope.reverseSortDir}" />"
                       ><fmt:message key="account.amount"/></a>
                </th>
                <th><fmt:message key="account.enabled"/></th>
                <th><fmt:message key="label.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="account" items="${requestScope.listBankAccounts}" varStatus = "status">
            <tr>
                <td>${account.bankAccountNumber}</td>
                <td>${account.accountType}</td>
                <td>
                    <c:forEach var="card" items="${account.bankAccountCreditCards}" varStatus = "status">
                    <div>
                        <span class="cards">${card.cardNumber}</span>
                        <span>  ${card.cardName}</span>
                        <span>, ${f:formatLocalDateTime(card.expireDate, 'MM/yyyy')}</span>
                        <span>, ${card.ownerName}</span>
                    </div>
                    </c:forEach>
                </td>
                <td style="text-align: right;">${account.accountAmount} ${account.currency}</td>
                <td><fmt:message key="${account.active ? 'account.enabled' : 'account.disabled'}"/></td>
                <td>
                    <c:if test="${account.active and !account.enableRequest}">
                        <form class="btn" action="controller" method="post">
                            <input type="hidden" name="id" value="${account.bankAccountId}" />
                            <input type="hidden" name="bankaccountfrom" value="${account.bankAccountNumber}">
                            <input type="hidden" name="currency" value="${account.currency}">
                            <c:set var="theaccount" value="${account}"/>
                            <button name="command" value="showformforpayment" class="btn btn-warning" type="Submit">
                                <fmt:message key="account.payment" /></button>
                        </form>
                    </c:if>
                    <c:if test="${account.active and !account.enableRequest}">
                        <form class="btn" action="controller?command=disableaccount" method="post">
                            <input type="hidden" name="id" value="${account.bankAccountId}" />
                            <c:set var="msg"><fmt:message key="message.disable.bank.account"/></c:set>
                            <button class="btn btn-danger" type="Submit"
                                    onclick="return confirm(${msg})">
                                <fmt:message key="account.disable" /></button>
                        </form>
                    </c:if>
                    <c:if test="${!account.active and !account.enableRequest}">
                        <form class="btn" action="${pageContext.servletContext.contextPath}/controller?command=enableaccountrequest" method="post">
                            <input type="hidden" name="id" value="${account.bankAccountId}" />
                            <button class="btn btn-secondary" type="Submit"><fmt:message key="account.enablerequest" /></button>
                        </form>
                    </c:if>
                    <c:if test="${account.enableRequest}">
                        <span class="btn btn-secondary">  <fmr:message key="account.request.sent"/></span>
                    </c:if>

                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>

        <br><br><br><br>

    </div>

</div>

<script src="../../../static/js/jquery-3.2.1.slim.min.js"></script>
<script src="../../../static/js/popper.min.js"></script>
<script src="../../../static/bootstrap/bootstrap.min.js"></script>
</body>
</html>