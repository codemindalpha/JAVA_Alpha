<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="utils" uri="/WEB-INF/tld/utils.tld"%>
<%
   response.setHeader("Cache-Control", "no-store");
   response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", -1);
 %>
 <!DOCTYPE html>
 <html lang="ko">
 <head> 
 <title><c:out value="${surveyEntity.surveyTitle}"/> : 온라인설문조사</title>
 <jsp:include page="/WEB-INF/jsp/form/head.jsp" />
 <%-- 달력 필요할때만 추가 --%>
 <c:if test="${utils:hasDate(pageEntity)}">
 <link rel="stylesheet" href="<c:url value='/static/js/jquery-ui/jquery-ui.css'/>">
 <script type=text/javascript src="<c:url value='/static/js/jquery-ui/jquery-ui.js'/>"></script>
 </c:if>
 </head>
 <body class="bg-gray" oncontextmenu="return false" ondragstart="return false" onselectstart="return false"><%-- 우클릭, 드래그, 선택 차단 --%>
 <div class="wrapper">
     <div class="view">
         <c:if test="${sessionScope.SMM_MODE eq 'PREVIEW'}">
             <div class="preview">미리보기입니다
             <span class="preview_page">P.<c:out value="${pageEntity.pageNo }"/></span>
             <c:if test="${pageEntity.custmzPageYn eq 'Y'}">
             <span class="preview_type">비정형</span>
             </c:if>
             <c:if test="${pageEntity.pageFltrYn eq 'Y'}">
             <span class="preview_logic">페이지필터</span>
             </c:if>
             <c:if test="${pageEntity.quesFltrYn eq 'Y'}">
             <span class="preview_logic">질문필터</span>
             </c:if>
             <c:if test="${pageEntity.pageSkipYn eq 'Y'}">
             <span class="preview_logic">페이지건너뛰기</span>
             </c:if>
             </div>
         </c:if>
         <div class="header">
             <c:choose>
             <c:when test="${not empty surveyEntity.surveyTitleImgSrc && surveyEntity.titleImgUseYn == 'Y'}">
             <img src="<c:url value="${surveyEntity.surveyTitleImgSrc}"/>" alt="<c:out value="${surveyEntity.surveyTitle}"/>"/>
             </c:when>
             <c:otherwise>
             <c:out value="${surveyEntity.surveyTitle}"/>
             </c:otherwise>
             </c:choose>
         </div>

         <c:if test="${distEntity.progbarUseYn eq 'Y'}">
         <c:set var="rate" value="0"/>
         <c:if test="${totalQ > 0 && answerQ > 0}">
         <c:set var="rate"><fmt:formatNumber value="${answerQ / totalQ * 100}" maxFractionDigits="0"/></c:set>
         </c:if>
         <div class="gageWrap">
             <div class="progress">
                 <span style="padding-right:15px;">0%</span>
                 <div class="progress_bar">
                     <div class="progress_on" style="width:${rate}%;">
                         <div class="progress_count">${rate}%</div>
                     </div>
                 </div>
                 <span style="padding-left:15px;">100%</span>
             </div>
         </div>
         </c:if>

         <c:if test="${not empty pageEntity.pageDesc}">
         <div class="description">
             <p><c:out value="${pageEntity.pageDesc}" escapeXml="false"/></p>
         </div>
         </c:if>

         <form name="form1" id="form1" method="post" action="<c:url value='/forms/${distId}/save'/>">
  Event: 이슈 지점입니다


<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="pageId" id="pageId" value="${pageEntity.pageId}"/>
        <input type="hidden" name="pageNo" id="pageNo" value="${pageEntity.pageNo}"/>
        <input type="hidden" name="skipPageId" id="skipPageId" value=""/>

                <c:if test="${pageEntity.custmzPageYn eq 'Y'}">
                    <div> 
                 <c:out value="${custmzPageEntity.pageConts}" escapeXml="false" />
                    </div>
                </c:if>

                <c:if test="${pageEntity.custmzPageYn eq 'N'}">
                    <jsp:include page="/WEB-INF/jsp/form/question.jsp" />
                    <script> 
             function isValidCustmzPage() {
                        return true;
                    }
                    </script>
                </c:if>

                 <div class="fn-btns ac" style="margin-top: 15px;">
                 <c:if test="${pageEntity.pageNo > 1 && distEntity.backUseYn == 'Y'}">
                     <span><button id="btnBack" class="big" title="뒤로">뒤로</button></span>
                 </c:if>

                 <c:choose>
                     <c:when test="${pageEntity.pageId eq lastPageId}">
                         <span><button type="submit" class="filled big" title="응답완료">응답완료</button></span>
                     </c:when>
                     <c:otherwise>
                         <span><button type="submit" class="filled big" title="다음">다음</button></span>
                     </c:otherwise>
                 </c:choose>
             </div>
         </form>
     </div>
 </div>
 <noscript>자바스크립트 실행이 필수입니다.</noscript>
 <script type=text/javascript src="<c:url value='/static/forms/survey-forms.js?v=20190109'/>"></script>
 <script type="text/javascript">

 var filterChain = new FilterChain();

 $(document).ready(function(){
     <c:if test="${utils:hasDate(pageEntity)}">
     $('.datepicker').datepicker({
         closeText: "닫기",
         prevText: "이전달",
         nextText: "다음달",
         currentText: "오늘",
         monthNames: [ "1월","2월","3월","4월","5월","6월",
             "7월","8월","9월","10월","11월","12월" ],
         monthNamesShort: [ "1월","2월","3월","4월","5월","6월",
             "7월","8월","9월","10월","11월","12월" ],
         dayNames: [ "일요일","월요일","화요일","수요일","목요일","금요일","토요일" ],
         dayNamesShort: [ "일","월","화","수","목","금","토" ],
         dayNamesMin: [ "일","월","화","수","목","금","토" ],
         weekHeader: "주",
         dateFormat: "yy-mm-dd",
         firstDay: 0,
         isRTL: false,
         showMonthAfterYear: true,
         showOn: "both",
         yearSuffix: "년",
         changeMonth: true,
         changeYear: true,
         buttonImage: CTX_PATH + "/static/images/input_day.png"
     }); //.mask("9999-99-99"); 
     </c:if>

     <%-- TODO 문항 필터 정보는 (질문ID + 조건 + 응답값) --%>
     <c:forEach var="question" items="${pageEntity.quesEntities}">
      <c:if test="${fn:length(question.quesFltrEntities) > 0 }">
          var quesFltr = new QuestionFilter();

          <c:forEach var="quesFltr" items="${question.quesFltrEntities}">
          quesFltr.addTarget({'quesId':'${question.quesId}', 'fltrQuesId':'${quesFltr.fltrQuesId}', 'condCd':'${quesFltr.condCd}', 'rspnsValue':'${quesFltr.rspnsValue}'});
          </c:forEach>

          filterChain.addFilter(quesFltr);
      </c:if>
      </c:forEach>

      filterChain.addObserver();

     <c:if test="${DUP_RSPNS == 'Y'}">
     showAlert("중복응답이 발생하여 더 이상 설문에 참여할 수 없습니다.");
     </c:if>

     // 뒤로가기 버튼 
     $("#btnBack").click(function() {
         <c:choose>
         <c:when test="${sessionScope.SMM_MODE eq 'USER'}">
         showConfrim("뒤로가기를 할 경우 이전 응답정보는 삭제됩니다.\n뒤로 이동하시겠습니까?", function() {
             location.href = CTX_PATH + "/forms/${distId}/back";
             $("#nprogress").show();
         });
         </c:when>
         <c:otherwise>
             location.href = CTX_PATH + "/forms/${distId}/back";
             $("#nprogress").show();
         </c:otherwise>
         </c:choose>

         return false;
     });
 });
 </script>
 <jsp:include page="/WEB-INF/jsp/form/footer.jsp" />
 </body>
 </html> 
