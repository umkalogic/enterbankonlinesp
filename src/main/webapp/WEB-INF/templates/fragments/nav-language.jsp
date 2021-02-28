<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="messages" />

<header class="container">
    <div class="header-logo">
        <a href="../../../index.jsp"><img src="../../../static/images/logo.png" class="logo" alt="logo"></a>
        <a href="${pageContext.servletContext.contextPath}/controller?command=changelang&lang=uk_UA&page=${param.page}<c:if
        test="${param.id != null}">&id=${param.id}</c:if><c:if
        test="${param.bankaccountfrom != null}">&bankaccountfrom=${param.bankaccountfrom}</c:if><c:if
        test="${param.currency != null}">&currency=<c:out value="${param.currency}"/></c:if>"
           class="margin-left"><img src="../../../static/images/ua.svg" class="ua" alt="Українська"></a>
        <a href="${pageContext.servletContext.contextPath}/controller?command=changelang&lang=en_GB&page=${param.page}<c:if
        test="${param.id != null}">&id=${param.id}</c:if><c:if
        test="${param.bankaccountfrom != null}">&bankaccountfrom=${param.bankaccountfrom}</c:if><c:if
        test="${param.currency != null}">&currency=<c:out value="${param.currency}"/></c:if>">
            <img src="../../../static/images/en.svg" class="en" alt="English"></a>

    </div>
</header>

