<%@page import="javax.xml.namespace.*"%>
<%@page import="gov.mogaha.ntis.web.frs.gis.cmm.util.*" %>

<%!
// JSP에서 String 필드들이 멤버 변수로 선언됨
String commonPath = "/";
String imagePath = commonPath + "img/";
String imagePath_gis = imagePath + "gis/cmm/btn/";
%>
<% 
	/* FLAW: CWE-488 */
	commonPath = request.getParameter("path"); %>