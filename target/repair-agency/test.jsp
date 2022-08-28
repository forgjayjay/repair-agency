<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>

<fmt:setLocale value="${param.lang}" />
<fmt:setBundle basename="messages" />

<html lang="${param.lang}">
<head>
<title>jstl</title>
</head>
<fmt:message key="label.newuser" />
<body>
	<ul>
		<li><a href="?lang=ru"><fmt:message key="label.ru" /></a></li>
		<li><a href="?lang=en"><fmt:message key="label.en" /></a></li>
	</ul>
</body>
</html>