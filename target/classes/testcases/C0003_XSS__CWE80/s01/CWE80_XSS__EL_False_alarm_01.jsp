<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Localized Dates</title></head>
<body bgcolor="white">

<!-- 4 false alarms -->
<!-- Safe: 1. -->
<%-- <c:when test="${empty param.abc}"> --%>
<!-- Safe: 2. -->
<%-- <c:when test="${empty param.p_id}"> --%>
<!-- Safe: 3. -->
<%-- <c:when test="${param.abc == ''}"> --%>
<!-- Safe: 4. -->
<%-- <c:when test="${param.p_id == ''}"> --%>
<!-- Safe: 5. -->
<%-- <c:when test="${param.p_id}"> --%>
<!-- Safe: 5. -->
<%-- "${param.p_id}" --%>
</body>
</html>
