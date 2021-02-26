<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="messages" />

<form action="controller" method="get">
    <button class="btn btn-md btn-dark btn-block"
            type="Submit" name="command" value="logout"><fmt:message key="label.logout" /></button>
</form>
