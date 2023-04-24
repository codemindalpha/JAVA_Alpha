<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.net.URLDecoder"%>
<title>회원가입</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script  type='text/javascript'>try {	document.domain="<s:text name='domain'/>"; } catch(e){}</script><noscript></noscript>
<s:if test="%{message=='site'}">

<script type='text/javascript' >
		alert("회원가입 완료.");
		window.opener.document.location.href=window.opener.document.URL; 
		top.window.close();
</script><noscript></noscript>
</s:if>
<%
String name = URLDecoder.decode(request.getParameter("RealName"),"UTF-8");
%>

</script><noscript></noscript>
<form name="user_form_check" method="post" action="JoinExecute.action" onsubmit="return jf_form_check1();">
	<input type="hidden" id="idchk" name="idchk" value="1" />
	<input type="hidden" id="site_id" name="siteId" value="<s:property value='%{siteId}'/>" />
	<input type="hidden" name="dupInfo" value="<s:property value='%{dupInfo}'/>" />
	<input type="hidden" name="virtualNo" value="<s:property value='%{virtualNo}'/>" />
	<input type="hidden" name="sex" value="<s:property value='%{sex}'/>" />
	<input type="hidden" name="nationalInfo" value="<s:property value='%{nationalInfo}'/>" />
	<input type="hidden" name="authInfo" value="<s:property value='%{authInfo}'/>" />
	<!-- Safe: -->
	<input type="hidden" name="realName" value="<%=name %>" />
     <div id="join">
       <p class="join">
        <h2>회원가입</h2>
       <fieldset>
         <legend>회원가입</legend>   
          <p class="join_home">    
          <span class="comen">+ 회원님의 정보를 입력후 "확인"버튼을 클릭하세요</span>    
            <label for="id">아이디 :</label>
            <input id='user_id' name='userId' type="text"  class="log_input5" onkeyUp="if(event.keyCode==13) id_chk2();" title="아이디" /><img src="<s:property value='rootpath'/>/Web-home/cms-utf8/img/btn_reject.gif" alt="중복체크" width="55" height="18" onClick="jf_Usercheck();"/><span class="ad_add2">+ 한글 아이디는 사용할 수 없습니다.</span></p>
  <p class="join_home">
        <label for="pass">비밀번호 :</label>
        <input id="pwd1" name="userPw" type="password"  class="log_input5" title="비밀번호"/><span class="ad_add2" >+ 영문이나 숫자의 조합으로 네자리 이상 입력해주세요.</span></p>
         <p class="join_home">
        <label for="passre">비밀번호 재입력 :</label>
        <input name="pwd2" id="pwd2" type="password"  class="log_input5" title="비밀번호확인"/></p>
        
         <p class="join_home">        
          <label for="name">이름 :</label>
		  <!-- Safe: -->
          <%=name %>
         </p> 
        <p class="join_home">        
          <label for="mail">이메일 :</label>
          <input id="user_mail" name="userMail" type="text"  class="log_input5" title="이메일"/></p>           
        <p class="join_home">        
          <label for="phone">연락처 :</label>
          <input id="user_tel" name="userTel" type="text"  class="log_input5" title="연락처"/></p>
           
       </fieldset>     
      
    </div>
</form>
