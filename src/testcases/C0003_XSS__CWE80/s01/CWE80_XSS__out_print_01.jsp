
<%@ page contentType="text/xml;charset=euc-kr"  %>
<%@ page import="java.sql.*,java.util.*,java.text.*,java.io.*"%>
<%@ page import="util.MUtil" %>


<%  final String prog_nm = "ehBang"+request.getRequestURI();
    final int LOG_FLAG = 0;
%>

<%

String pFormNum        = MUtil.checkNull(request.getParameter("form_num"));


qry = "" +
	    " select a.ords_num||'-'||a.list_num as ord_num " + 
	    "  ,to_char(nvl((select max(nvl(x.comp_dati_cust, x.comp_dati)) from tra_dtl x where x.ords_num = a.ords_num and x.list_num = a.list_num),a.ords_mk_dati), 'yyyy-mm-dd') as comp_dati_cust " +
	    "  ,f_point2(nvl(a.cnt,'')) as cnt " +
	    "  ,f_point2(nvl(a.rem_qty,'')) as rem_qty " +
	    "  ,f_point2(a.upri) as upri " +
	    "  ,f_point2(a.pri) as pri " +
	    "  ,a.plis_nm " +
	    "  ,a.spec " +
	    "  ,a.cust_ord_num " +
	    "  ,a.cust_prd_cd " +
	    " from ords_dtl a " +
	    " where 1=1 " +
	    " and a.cust_ord_num like ?||'%' " +
	    " order by a.ords_num, a.list_num " +
	    "";
//System.out.println(qry);
	db.prepareStatement(qry);
	db.PsetString(1,pFormNum);
	db.PexecuteQuery();

	int cnt=0;
	while(db.prs.next()) {

		sbXML.append("<row id=\""+((++cnt))+"\">");
		
		sbXML.append("<cell><![CDATA["+(MUtil.checkNull(db.prs.getString("ord_num"))).replace(System.getProperty("line.separator"), " ")+"]]></cell>");
		sbXML.append("<cell><![CDATA["+(MUtil.checkNull(db.prs.getString("comp_dati_cust"))).replace(System.getProperty("line.separator"), " ")+"]]></cell>");
		sbXML.append("<cell><![CDATA["+(MUtil.checkNull(db.prs.getString("cnt"))).replace(System.getProperty("line.separator"), " ")+"]]></cell>");
		sbXML.append("<cell><![CDATA["+(MUtil.checkNull(db.prs.getString("rem_qty"))).replace(System.getProperty("line.separator"), " ")+"]]></cell>");
		sbXML.append("<cell><![CDATA["+(MUtil.checkNull(db.prs.getString("upri"))).replace(System.getProperty("line.separator"), " ")+"]]></cell>");
		sbXML.append("<cell><![CDATA["+(MUtil.checkNull(db.prs.getString("pri"))).replace(System.getProperty("line.separator"), " ")+"]]></cell>");
		sbXML.append("<cell><![CDATA["+(MUtil.checkNull(db.prs.getString("plis_nm"))).replace(System.getProperty("line.separator"), " ")+"]]></cell>");
		sbXML.append("<cell><![CDATA["+(MUtil.checkNull(db.prs.getString("spec"))).replace(System.getProperty("line.separator"), " ")+"]]></cell>");
		sbXML.append("<cell><![CDATA["+(MUtil.checkNull(db.prs.getString("cust_ord_num"))).replace(System.getProperty("line.separator"), " ")+"]]></cell>");
		sbXML.append("<cell><![CDATA["+(MUtil.checkNull(db.prs.getString("cust_prd_cd"))).replace(System.getProperty("line.separator"), " ")+"]]></cell>");
		
		sbXML.append("</row>");	

	}

	if(cnt <= 0) {
		sbXML.append("<row id=\"1\">");
		sbXML.append("<cell colspan=\"10\" align=\"center\"><![CDATA[<span style=\"color:red;\">"+"조회내역이 없습니다."+"</span>]]></cell>");
		sbXML.append("</row>");	
	}
	sbXML.append("</rows>"); 
	db.DB_DisConn();
	//System.out.println(xml_file_name_new);	

	out.clear();
	// Flaw:
	out.print(sbXML.toString());
%>
