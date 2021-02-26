<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://example.com/functions" prefix="f" %>
<%@ taglib prefix="fmr" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <jsp:param name="page" value="confirmpayment"/>
        <jsp:param name="id" value="${requestScope.payment.paymentId}" />
    </jsp:include>

    <jsp:include page="../fragments/nav-logout.jsp"/>

    <div class="container-fluid" style="margin-top:40px;">
        <span><fmt:message key="text.welcome" /> <c:out value="${sessionScope.activeUserName}" /></span>
        <hr>
    </div>

    <div class="container-fluid">
        <h1><fmt:message key="title.payments"/></h1>
        <hr>
        <h2><fmt:message key="title.send.payment"/></h2>

        <div class = "container">
            <form autocomplete="off" action="controller" method="POST" role="form" class="form-group"
            style="border:1px dashed #383d41; padding: 20px;">
                <input type="hidden" name="id" value="${param.id}">
                <input type="hidden" name="paymentid" value="${requestScope.payment.paymentId}">
                <input type="hidden" name="bankaccountfrom" value="${requestScope.payment.bankAccount.bankAccountNumber}">
                <span class="col-6 form-control-plaintext"><fmt:message key="payment.from"/>:    ${requestScope.payment.bankAccount.bankAccountNumber}</span><br>
                <br>
                <span class="col-2 form-control-plaintext"><fmt:message key="payment.to"/>:    </span>
                <input type="hidden" name="tobankaccount" value="${requestScope.payment.toBankAccount}">
                <span  class="form-control mb-4 col-4">${requestScope.payment.toBankAccount}</span>
                <br>
                <span class="col-2 form-control-plaintext"><fmt:message key="payment.paymentAmount"/>:    </span>
                <input type="hidden" name="paymentamount" value="${requestScope.payment.paymentAmount}">
                <span class="form-control mb-4 col-4">${requestScope.payment.paymentAmount} ${requestScope.currency}</span>
                <br>
                <button type="submit" name="command" value="confirmpayment" class="btn btn-info col-2"><fmt:message key="payment.confirm"/></button>
                <c:if test="${!requestScope.payment.sent}">
                <form action="controller" method="post" class="btn">
                    <input type="hidden" name="id" value="${requestScope.payment.paymentId}">
                    <c:set var="msg"><fmt:message key="message.confirm.delete"/></c:set>
                    <button type="submit" name="command" value="deletepayment" onclick="return confirm(this.getAttribute(${msg}))" class="btn btn-danger">
                        <fmt:message key="payment.delete.payment"/></button>
                </form>
                </c:if>
                <br>
                <br>
                <c:if test="${requestScope.errorMessage != null}">
                    <h2 class="text-danger"><fmt:message key="error.message"/></h2>
                    <p class="text-info">${requestScope.infoMessage}</p>
                </c:if>

            </form>


        </div>
        <hr>
        <a href = "${pageContext.servletContext.contextPath}/controller?command=userhome"><fmt:message key="label.backtouserlist" /></a>
    </div>
</div>
<script src="../../../static/js/jquery-3.2.1.slim.min.js"></script>
<script src="../../../static/js/popper.min.js"></script>
<script src="../../../static/bootstrap/bootstrap.min.js"></script>
<script src="../../../static/js/enabledisable.js"></script>
<script src="../../../static/js/sark-decimal.js"></script>
</body>
</html>
