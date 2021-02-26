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
    <title><fmt:message key="title.usermanagementsystem"/></title></head>

<body>
<div class="container-fluid">
    <jsp:include page="../fragments/nav-language.jsp">
        <jsp:param name="page" value="useraccounts"/>
        <jsp:param name="id" value="${requestScope.id}"/>
    </jsp:include>

    <jsp:include page="../fragments/nav-logout.jsp"/>


    <div class="container-fluid" style="margin-top:40px;">
        <span><fmt:message key="text.info.about.user"/> <c:out value="${requestScope.userName}" /></span>
        <hr>
    </div>

    <div class="container-fliud">
        <table class = "table table-bordered table-striped table-responsive-md table-hover">
            <thead class="thead-dark">
            <tr>
                <th><a href="${pageContext.servletContext.contextPath}?command=showuseraccounts&id=${requestScope.id}&sortfield=bank_account_number&sortdir=${requestScope.reverseSortDir}"
                       ><fmt:message key="account.number"/></a>
                </th>
                <th><a href="${pageContext.servletContext.contextPath}?command=showuseraccounts&id=${requestScope.id}&sortfield=account_type&sortdir=${requestScope.reverseSortDir}"
                       ><fmt:message key="account.type"/></a>
                </th>
                <th><fmt:message key="account.showAccountCreditCards"/></th>
                <th><a href="${pageContext.servletContext.contextPath}?command=showuseraccounts&id=${requestScope.id}&sortfield=account_amount&sortdir=${requestScope.reverseSortDir}"
                       ><fmt:message key="account.amount"/></a>
                </th>
                <th><fmt:message key="account.enabled"/></th>
                <th><fmt:message key="label.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${requestScope.listBankAccounts.size() > 0}">
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
                            <c:set var="msg" ><fmt:message key="message.disable.bank.account" /></c:set>
                        <a href="${pageContext.servletContext.contextPath}?command=changeaccountstatus&isactive=false&id=${account.bankAccountId}"
                           onclick="return confirm(this.getAttribute(${msg}))"
                           class="btn btn-danger"><fmt:message key="account.disable"/></a>
                        </c:if>
                        <c:if test="${!account.active and !account.enableRequest}">
                            <c:set var="msg2" ><fmt:message key="message.enable.bank.account" /></c:set>
                            <a href="${pageContext.servletContext.contextPath}?command=changeaccountstatus&isactive=true&id=${account.bankAccountId}"
                           onclick="return confirm(this.getAttribute(${msg2}))"
                           class="btn btn-secondary"><fmt:message key="account.enable"/></a>
                        </c:if>
                        <c:if test="${account.enableRequest}">
                            <c:set var="msg3" ><fmt:message key="message.enable.bank.account" /></c:set>
                            <a href="${pageContext.servletContext.contextPath}?command=changeaccountstatus&isactive=true&id=${account.bankAccountId}"
                           class="btn btn-info"
                           onclick="return confirm(this.getAttribute(${msg3}))"><fmt:message key="account.request.sent"/></a>
                        </c:if>


                    </td>
                </tr>
            </c:forEach>
            </c:if>
            </tbody>

        </table>
        <br>
        <hr>
        <br>
        <a href = "${pageContext.servletContext.contextPath}/controller?command=adminhome"><fmt:message key="label.backtouserlist"/></a>
        <br>
        <br>
    </div>
</div>

<script src="../../../static/js/jquery-3.2.1.slim.min.js"></script>
<script src="../../../static/js/popper.min.js"></script>
<script src="../../../static/bootstrap/bootstrap.min.js"></script>
<script src="../../../static/js/enabledisable.js"></script>
</body>
</html>