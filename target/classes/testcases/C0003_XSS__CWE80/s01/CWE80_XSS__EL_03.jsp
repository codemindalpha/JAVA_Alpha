<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="abs" uri="/WEB-INF/tlds/abs-component.tld" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- Flaw: 1. maybe.. conservatively caught -->
<meta name="decorator" content="${SESSION_STAY_THEME}.layout">
<!-- Safe: 1. -->
<script type="text/javascript" src="<c:url value='${STATICS_PATH}/biz/ecm_old/consult_outbound_handle.js' />"></script>
<!-- Safe: 2. -->
<script type="text/javascript" src="<c:url value='${STATICS_PATH}/biz/ecm_old/consult_outbound_hanaaefefefefefefefefdle.js' />"></script>
<script type="text/javascript">
function setBizCd(val, form_id) {
	var form = $("<form/>");
	form.append("<input name=\"submit_id\" value=\"common.Common.reqGetBizList\"/>")
		<!-- Flaw: 2. maybe.. conservatively caught -->
		.append("<input name=\"biz_cd\" value=\"${KHYDI_SESSION.biz_cd}\" />")
		<!-- Flaw: 3. maybe.. conservatively caught -->
		.append("<input name=\"biz_move_agreement_yn\" value=\"${KHYDI_SESSION.biz_move_agreement_yn}\" />");
}
</script>
<!-- Safe: 3. -->
<c:if test="${fn:length(eduCenterList) eq 1}" >
	<!-- Safe: 4. -->
	<c:forEach var="eduCenterList" items="${eduCenterList}">
		<!-- Safe: 5. -->
		<input type="hidden" name="edu_center_cd_bak" value="<c:out value="${eduCenterList.value}"/>"/>
	</c:forEach>
</c:if>
<!-- Flaw: 7. maybe.. conservatively caught -->
<input type="hidden" id="currentUserNm" value="${KHYDI_SESSION.user_nm}">
</body>
</html>
