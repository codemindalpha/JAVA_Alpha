<%@ page import="testcases.C0003_XSS__CWE80.s01.CWE80_XSS__Combi"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
  <jsp:useBean id="test" class="CWE80_XSS__Combi" />
  <%
   CWE80_XSS__Combi combi = new CWE80_XSS__Combi();
   String param = request.getParameter("param");
   combi.responseWrite(response, param);
  %>
</body>
</html>
