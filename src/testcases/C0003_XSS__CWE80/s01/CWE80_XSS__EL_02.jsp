<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="java.sql.*, java.text.*" %>
<%@ page import="util.NullUtil" %>
<%request.setCharacterEncoding("utf-8");%>
<%//--세션 가져오기--%>

<%@ include file="/session/session_chk.jsp" %>

<%
	final String prog_nm = "shpark_" + request.getRequestURI();
	final int LOG_FLAG = 0; // 1: log 출력 0: log 출력안함.
	
	String pFormNum        = NullUtil.checkNull(request.getParameter("form_num"));
	
	String[][] __grid_info1   = new String[][]{ 
			{"주문번호"        ,   "100","center", "str","ro"},
			{"완료일자"        ,   "80","center", "str","ro"},
			{"수량"        ,   "60","right", "str","ro"},
			{"잔량"       ,   "60","center", "str","ro"},
			{"단가"        ,   "100","right", "str","ro"},
			{"금액"        ,   "100","right", "str","ro"},
			{"품명"        ,   "200","left", "str","ro"},
			{"규격"        ,   "200","left", "str","ro"},
			{"고객발주번호"        ,   "130","center", "str","ro"},
			{"고객상품코드"        ,   "110","center", "str","ro"}
		};
	
	String[][] __grid_info2   = new String[][]{ 
			{"No."        ,   "50","right", "str","ro"},
			{"파일종류"       ,   "120","center", "str","ro"},
			{"파일명"        ,   "300","left", "str","ro"}
		};
%>



<!doctype html>
<html class="no-js">
<head>
<title>첨부파일</title>

<%@include file="/Common.jsp"%>

<script type="text/javascript">

function resizeMain() {
	var winHeight   			= document.getElementById("Wrap").offsetHeight;
	var titleHeight   		= document.getElementById("title_area").offsetHeight;
	var contentHeight   	= 0;
	var btnHeight   			= document.getElementById("grid_header_btn").offsetHeight;
	
	document.getElementById("content_body").style.height = (winHeight-titleHeight-contentHeight-btnHeight-15)+"px";
}


function gridOnXLE(){
	
}

function gridOnXLE2(){
	
}

function newGridList(){
	div_grid.clearAll(); 
	<!-- // Flaw: HAP-->
	div_grid.loadXML("/delivery_form_mng_view_popup_01_XML.jsp?xml_file_key=xml_file_name&form_num=<%=pFormNum%>");
}

function f_close(){
	window.close();
}

function f_down_file(file_num, seq){
	document.getElementById("file_num").value = file_num;
	document.getElementById("seq").value = seq;
	
	var fm = document.form1;
	fm.action="/delivery_form_mng_view_DOWN.jsp";
	fm.method = "POST";
	fm.target = "ifrm";
	fm.submit();
}

</script>


</head>
<body topmargin="0"  leftmargin="0" rightmargin="0" bottommargin="0">
<form name="form1" method="post" style="height:100%">   

<input type="hidden" id="xml_file_key" name="xml_file_key" value="xml_file_name" size="30">
<input type="hidden" id="xml_file_name" name="xml_file_name" value="xml_file_name" size="40">
<input type="hidden" id="xml_file_name_02" name="xml_file_name_02" value="">
<input type="hidden" id="xml_file_name_old_02" name="xml_file_name_old_02" value="">

<div id="Wrap"> 
    <div id="Container_entry" class="no_vpad"> 
        <div id="Content_entry">
			
			<div id="title_area" class="title_area">
				<div class="row">
					<div class="subNav">
					    <span><img alt="home" src="/homeButton.jpg" /></span>&nbsp;&nbsp;
					    <span class="currentPage">품목 확인</span>&nbsp;
				    </div>
				</div>	  
			</div>

			
			<div id = "grid_header_btn" class="grid_header_btn">
			    <a class="btn white ml5 fl" onClick="javascript:f_close();"><img src="/iconCloseButton.png" class="icon_btn" onError="this.src = '/iconBasicButton.png';" />닫 기</a>
			</div>			
		
			<div id = "content_body" class="content_body">
				<div id="div_grid" style="height:55%;width:100%;display:block;position:relative;top:0;left:0;"></div>
				<%=makeGridInfo("div_grid",__grid_info1)%>
				<script>
					div_grid.attachEvent("onXLE", gridOnXLE); 
				</script>				
				
				<div style="height:10px;"></div>

				<div id="div_grid_02" style="height:40%;width:100%;display:block;position:relative;top:0;left:0;"></div>
				<%=makeGridInfo("div_grid_02",__grid_info2)%>
				<script>
					div_grid_02.attachEvent("onXLE", gridOnXLE2); 
				</script>				
			</div>
		</div>
		
	</div>
</div>


<iframe id="ifrm" name="ifrm" width="0" height="0" style="display:none"></iframe>
<input type="hidden" id="file_num" name="file_num" value="">
<input type="hidden" id="seq" name="seq" value="">
	
</form>

<script>
newGridList();
</script>
</body>

</html>
