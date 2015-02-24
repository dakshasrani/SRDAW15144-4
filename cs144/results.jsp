<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CS144 Configuration Test</title>
</head>
<body>
<table cellspacing="2" cellpadding="2">
	<tr><td><b>Property</b></td><td><b>Value</b></td></tr>
	<c:forEach var="m" items="${attributeMap}">
	<tr><td>${m.key}</td><td>${m.value}</td></tr>
	</c:forEach>
</table>
</body>
</html>