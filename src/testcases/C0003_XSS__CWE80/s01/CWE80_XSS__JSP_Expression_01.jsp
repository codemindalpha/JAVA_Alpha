<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
  // 파라미터(name)에 <script>alert(document.cookie);</script>와 같은
  // 스크립트 코드가 입력되면 공격자에게 피해자의 쿠키정보가 전송될 수 있다.
  String name = request.getParameter("name");
  String addr = request.getParameter("addr");
  if ( addr != null ) {
	    // 스크립트 생성에 사용되는 문자열을 치환하여 입력값에 악성 코드가 포함되더라도 실행되지 않는다.
	    addr = addr.replaceAll("<", "&lt;");
	    addr = addr.replaceAll(">", "&gt;");
	    addr = addr.replaceAll("&", "&amp;");
	    addr = addr.replaceAll("\"","&quot;");
	  }
%>
<!-- Flaw: -->
<p>Name:<%=name%></p>
<!-- Safe -->
<p>Addr:<%=addr%></p>

</body>
</html>