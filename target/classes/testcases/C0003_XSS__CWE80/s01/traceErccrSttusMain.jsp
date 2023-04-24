<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="abs" uri="/WEB-INF/tlds/abs-component.tld" %>
<%@ taglib prefix="tids" uri="/WEB-INF/tlds/tids-component.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
 /**
  * @Jsp Name : traceErccrSttusMain.jsp
  * @상세설명 : 입국자현황관리 페이지
  * @작성일시 : 2017. 11. 3.
  * @작 성  자 :  곽순철
  * @수 정 이 력
  * @
  * @ 수정일                       수정자                 수정내용
  * @ -----------   --------    ---------------------------
  * @ 2017. 11. 3.   곽순철            최초등록
  * @
*/
%>
<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="${theme}.layout">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<c:url value='${STATICS_PATH}/js/biz/se/seUtil.js'/>"></script>
<script type="text/javascript">
var id = "popup_layer";
var bgId = "Graybg";
var myWorkYn = "N";
$(window).load(function(){

    $("#searchFromDt").prop("disabled",true);
    $("#searchToDt").prop("disabled",true);
    $(".ui-datepicker-trigger").prop("disabled",true);

    $("#qrdr_div1").hide();
    $("#qrdr_div2").hide();
    $("#qrdr_div3").hide();


    $("#dtSearchAt").prop("checked",true);
    fn_dtSearchAt();
    
    if ($("#fromMyWorkYn").val() == "Y") {
        
        // 2018.09.06 나의할일에서 온 경우 날짜를 셋팅함(시작)
        
        var instttyId = "<c:out value='${sessionVO.instttyId }' />";
        
        $('#searchFromDt').val($("#strtSearchDt").val());
        $('#searchToDt').val($("#endSearchDt").val());
        
        $("#epiSttus").val($("#processStatus").val());
        
        myWorkYn = "Y";
        
        if(instttyId == '1'){
        	fn_gridSearch();
        }
        
    }

});

//그리드 색상 넣기
function gridLoadComplete() {
    var ids = $("#traceErccrSttusList").getDataIDs() ;
    $.each( ids, function(idx, rowId) {
                   rowData = $("#traceErccrSttusList").getRowData(rowId) ;
                 if ( rowData.EPI_STTUS == "대기(미실시)"){
                	 //$("#traceErccrSttusList").setRowData(rowId, false, {background:"#FFF5EE"}) ;
                	 $("#traceErccrSttusList").setRowData(rowId, false, {background:"#FFE4E1"}) ;
                	 //$("#traceErccrSttusList").setRowData(rowId, false, {color:"#FF1493"}) ;
                 } else if ( rowData.EPI_STTUS == "추적완료"){
                      //$("#traceErccrSttusList").setRowData(rowId, false, {background:"#F5F5F5"}) ;
                      $("#traceErccrSttusList").setRowData(rowId, false, {background:"#DCDCDC"}) ;
                 }
/*                  else {
                     $("#traceErccrSttusList").setRowData(rowId, false, {background:"#F5FFFA"}) ;
                 } */
              }
        ) ;

    var searchFromDt = $("#searchFromDt").val().replace(/-/g,"");
    var searchToDt = $("#searchToDt").val().replace(/-/g,"");
    var s_guesPatNm = $("#guesPatNm").val();
    s_guesPatNm = s_guesPatNm.toUpperCase();
    //alert(s_guesPatNm);
    
    var param = {
         sidoCode : $("#sidoCode").val()
        ,insttsn : $("#insttsn").val()
        ,repDiv : $("#id_repDiv").val()
        , repGrpDiv : $("#id_repGrpDiv").val()
        ,guesPatNm : s_guesPatNm
        ,searchDt : $("#searchDt").val()
        ,searchFromDt : searchFromDt
        ,searchToDt : searchToDt
        ,epiSttus : $("#epiSttus").val()
        ,epiNo : $("#epiNo").val()
        ,deleteYn : 'N'
    };

    $.ajax({
        url: "<c:url value='/biz/se/trace/selectTraceerccrsttusListCnt002Ajax.do'/>",
        type:"POST",
        data : param,
        success: function(data) {
            if(data.code == "R.CMMN_E0001") {
                alert(data.message);
                return;
            }

            $("#cnt002").html(data.data.CNT_002); //미실시 건수
        },
        /*오류 시*/
        error : function(xhr, status, error) {
            alert("미실시 건수 정보를 불러오는 중 에러가 발생했습니다.\n관리자에게 문의해주세요.");
        },
        beforeSend:absBeforeJob,
        complete :absCompleteJob
    });
}

//보고그룹 상위그룹 변경 시 로직
function changeGrpRepDivEvent(id,code,codeName)
{
    $("#id_repDivSlct1").val(""); //그룹 초기화
    $("#id_repDivSlct2").val("");
    $("#id_repDivSlct3").val("");

    $("#id_repDiv").val("");

    $("#ptdGrpRecv").html("<div>조건에 해당하는 입력된 내용이 없습니다.</div>"); //SMS보고 초기화

    if(code == "A1") {
        $("#qrdr_div1").show();
        $("#qrdr_div2").hide();
        $("#qrdr_div3").hide();
    } else if(code == "B1") {
        $("#qrdr_div1").hide();
        $("#qrdr_div2").show();
        $("#qrdr_div3").hide();
    } else {
        $("#qrdr_div1").hide();
        $("#qrdr_div2").hide();
        $("#qrdr_div3").show();
    }
}

//보고그룹 선택
function changeRepDivEvent(id,code,codeName)
{
    $("#id_repDiv").val("");
    $("#id_repDiv").val(code);

/*     var option=$("#"+id+" :selected");
    alert("code : "+code+"\n"
            +"codeName : "+codeName+"\n"
            +"codeId : "+option.attr("codeId")+"\n"
            +"pcodeId : "+option.attr("pcodeId")+"\n"
            +"refCodeId : "+option.attr("refCodeId")+"\n"
            +"extendCol1 : "+option.attr("extendCol1")+"\n"
            +"extendCol2 : "+option.attr("extendCol2")+"\n"
            +"extendCol3 : "+option.attr("extendCol3")+"\n"
            +"linkId : "+option.attr("linkId")+"\n") */
    //alert( $("#id_repDiv").val());
}

function ondbClickRow(rowid, iRow, iCol) {
    fn_openViewPopup();
}
function fn_gridSearch() {
    var searchFromDt = $("#searchFromDt").val().replace(/-/g,"");
    var searchToDt = $("#searchToDt").val().replace(/-/g,"");
    
    var s_guesPatNm = $("#guesPatNm").val();
    s_guesPatNm = s_guesPatNm.toUpperCase();
    
    //alert(s_guesPatNm);
    var param = {
    	directquery:"traceErccrSttus.SELECT_TRACEERCCRSTTUS_LIST"
    	,sidoCode : $("#sidoCode").val()
        ,insttsn : $("#insttsn").val()
        ,repDiv : $("#id_repDiv").val()
        , repGrpDiv : $("#id_repGrpDiv").val()
        ,guesPatNm : s_guesPatNm
        ,searchDt : $("#searchDt").val()
        ,searchFromDt : searchFromDt
        ,searchToDt : searchToDt
        ,epiSttus : $("#epiSttus").val()
        ,epiNo : $("#epiNo").val()
        ,deleteYn : 'N'
    };
    $("#traceErccrSttusList").doGridSearch(param);
    
    myWorkYn = "N";
    
    $("#mbtnReload").trigger("click");	// 미처리현황 새로고침
    
}

function fn_openRegistPopup(){

    var url = "<c:url value='/biz/se/trace/traceErccrSttusErccrView.do'/>";
    fn_layerPopup_open(id,url, bgId);
}

function fn_openViewPopup(){
	/* var idx = $("#traceErccrSttusList").getGridParam("selarrrow");

	var rowCount = idx.length;
	alert(rowCount); */

    var selrow = $("#traceErccrSttusList").getGridParam("selrow");  //선택 행 번호 조회

    if(!selrow) {
        alert('행이 선택되지 않았습니다.');
        return;
    }

    var data = $("#traceErccrSttusList").jqGrid('getRowData', selrow);
    var paramMap = {
    	 epiNo : data.EPI_NO
        ,repNotiNo : data.REP_NOTI_NO
        ,pgTyp : data.PG_TYP
        ,guesDiv : data.GUES_DIV      // 의심환자 밀접접촉자 구분_G 의심_C 밀접촉자
        ,deleteYn : 'N'
    };

    var url = "<c:url value='/biz/se/trace/traceErccrSttusDspthView.do'/>";
    fn_layerPopup_open(id, url, bgId, paramMap);
}

function fn_delete() {

	if(!confirm("정말 삭제하시겠습니까?")){
		return;
	}
	
    var rowid = $("#traceErccrSttusList").jqGrid('getGridParam','selrow');
    var data = $("#traceErccrSttusList").jqGrid('getRowData', rowid);

    var paramMap = {
        epiNo : data.EPI_NO
       ,deleteYn : 'Y'
       ,modReason : '삭제'
    };

    $.ajax({
    	url: "<c:url value='/biz/se/trace/traceErccrSttusDspthDelete.do'/>",
        type:"POST",
        data : paramMap,
        success: function(data) {
            alert(data.message);

            if(data.code == 'R.CMMN_I0004') {
                fn_gridSearch();
            }
        },
        /*오류 시*/
        error : function(xhr, status, error) {
            alert("삭제하는 중 에러가 발생했습니다.\n관리자에게 문의해주세요.");
        },
        beforeSend:absBeforeJob,
        complete :absCompleteJob
    });
}

//excel down
function commonExcelDown()
{
    var searchFromDt = $("#searchFromDt").val().replace(/-/g,"");
    var searchToDt = $("#searchToDt").val().replace(/-/g,"");

    var param = {
		checkAreaCode :"se"
  		,sidoCode : $("#sidoCode").val()
        ,insttsn : $("#insttsn").val()
        ,repDiv : $("#id_repDiv").val()
        , repGrpDiv : $("#id_repGrpDiv").val()
        ,guesPatNm : $("#guesPatNm").val()
        ,searchDt : $("#searchDt").val()
        ,searchFromDt : searchFromDt
        ,searchToDt : searchToDt
        ,epiSttus : $("#epiSttus").val()
        ,epiNo : $("#epiNo").val()
        ,deleteYn : 'N'
    };
    //setTimeout($.unblockUI, 2000);

    absFile.externalFileDownload("/biz/se/excel/traceErccrSttusExcelDataListAjax.do", param);
    //absFile.externalFileDownload("/biz/se/excel/guideExcelDown.do", param);
    /* $.ajax({ url        : '<c:url value="/biz/se/excel/traceErccrSttusExcelDataListAjax.do"/>'
           , type       : "POST"
           , dataType   : "json"
           , data       : param
           , error      : seComm.fn_ajaxError
           , beforeSend : seComm.blockUIForDownload
           , success    : function(data) {
                 setTimeout($.unblockUI, 2000);

                fn_downExcelCallback(data);
             }
    }); */


}

//권한 및 화면에 따른 selectbox option 제거
function fn_selectRemove(){
    var instttyId = "<c:out value='${sessionVO.instttyId }' />";

    if(instttyId == "3" || instttyId == "4"){
        //시도 셀렉트 박스 옵션 삭제
        $("#sidoCode option:not(:selected)").remove();
    }

    if(instttyId == "4"){
        //보건소 셀렉트 박스 옵션 삭제
        $("#insttsn option:not(:selected)").remove();
        
    }
    
    if(myWorkYn == "Y"){
		fn_gridSearch();
    }
}

function fn_dtSearchAt() {
	//2017.12.30 기본기간 추가
	//현재 년월일
	var today = new Date(); //
    var year  = today.getFullYear();
    var month = today.getMonth() + 1; // 0부터 시작하므로 1더함 더함
    var day   = today.getDate();
//    var dayAgo = today.getDate()-14;
/*     if(String(month) == '1'){ //1월이면
    	if(day < '14'){
    		dayAgo = '1';
    	}
    } */
    //2주전 년월일
    var agoDate = new Date();
    var dayOfMonth = agoDate.getDate();
    agoDate.setDate(dayOfMonth - 14);
    //alert(agoDate);
    var yearAgo  = agoDate.getFullYear();
    var monthAgo = agoDate.getMonth() + 1; // 0부터 시작하므로 1더함 더함
    var dayAgo   = agoDate.getDate();

    // 두글자 포멧 맞추기
    if(String(month).length == '1'){
        month = '0' + month;
    }

    if(String(monthAgo).length == '1'){
    	monthAgo = '0' + monthAgo;
    }

    if(String(day).length == '1'){
    	day = '0' + day;
    }

    if(String(dayAgo).length == '1'){
    	dayAgo = '0' + dayAgo;
    }

	var checkAt = $("#dtSearchAt").prop("checked");
	if(!checkAt) {
		$("#searchFromDt").val("");
        $("#searchToDt").val("");
		$("#searchFromDt").prop("disabled",true);
		$("#searchToDt").prop("disabled",true);
		$(".ui-datepicker-trigger").prop("disabled",true);
	} else {
		$("#searchFromDt").prop("disabled",false);
        $("#searchToDt").prop("disabled",false);
        $(".ui-datepicker-trigger").prop("disabled",false);
        //2017.12.30 기본기간 추가
        $("#searchFromDt").val(yearAgo+'-'+  monthAgo +'-'+ dayAgo);
        $("#searchToDt").val(year+'-'+  month +'-'+ day);
	}
}

</script>

<title>입국자현황관리 조회</title>
</head>
<body>
<div id="popup_layer"></div>
<div id="Graybg" class="dis_none"></div>
<form method="post" id="traceForm" name="traceForm">
    <input type="hidden" name="fileSeq" id="fileSeq"/>
    <input type="hidden" name="sheetName" id="sheetName"/>
   
    <!-- contents 시작-->
    <table id="contetnsWrap" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td>
				<!-- 상단 메뉴영역-->
			    <c:import url="/biz/se/common/seUnprocessStatusImport.do" var="unprocessSttus"/>
			    <c:out value="${unprocessSttus}" escapeXml="false" />
			    <!-- 상단 메뉴영역-->
			    <!-- START:::::나의할일 파라미터 추가 -->
				<input type="hidden" id="fromMyWorkYn" name="fromMyWorkYn" value="${param.fromMyWorkYn}" />
				<input type="hidden" id="strtSearchDt" name="strtSearchDt" value="${param.strtSearchDt}" />
				<input type="hidden" id="endSearchDt" name="endSearchDt" value="${param.endSearchDt}" />
				<input type="hidden" id="processStatus" name="processStatus" value="${param.processStatus}" />
				<!-- END:::::나의할일 파라미터 추가 -->
				
                <!-- 검색테이블 시작 -->
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td class="search_tbl">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td class="search_tbl_box">
                                        <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                            <caption class="hidden">검색 테이블</caption>
                                            <colgroup>
                                                <col width="10%" />
                                                <col width="30%" />
                                                <col width="10%" />
                                                <col width="25%" />
                                                <col width="10%" />
                                                <col width="*" />
                                            </colgroup>
                                            <tr>
                                                <th class="search_th_c">지역</th>
                                                <td class="search_td">
											        <c:choose>
                                                        <c:when test="${sessionVO.instttyId eq '3' }">
                                                            <c:set var="sidoGubun"><fmt:formatNumber pattern="00" value="${sessionVO.sidoGubun}" /></c:set>
                                                        </c:when>
                                                        <c:when test="${sessionVO.instttyId eq '4' }">
                                                            <c:set var="sidoGubun"><fmt:formatNumber pattern="00" value="${sessionVO.sidoGubun}" /></c:set>
                                                            <c:set var="absInsttid" value="${sessionVO.absInsttid }" />
                                                        </c:when>
                                                        <c:otherwise>
                                                        </c:otherwise>
                                                    </c:choose>

                                                    <tids:select id="sidoCode" name="sidoCode" showValues="false" initOption="" style="width:85px;" cssClass="" type="CTRD" value="${sidoGubun}"/>
                                                    <tids:select id="insttsn" name="insttsn" pId="sidoCode" showValues="false" initOption="" style="width:200px;" cssClass=""  type="PBHLTH" value="${absInsttid}" callback="fn_selectRemove"/>
                                                </td>
                                                <th class="search_th_c">보고그룹</th>
                                                <td class="search_td">
                                                   <!--  <select id="rep_div" name="rep_div">
                                                        <option value="">선택</option>
                                                    </select> -->
                                                    <%-- <abs:select id="repDiv" name="repDiv" groupCode="MSE_D03"  showValues="false" initOption="" cssClass=""/> --%>
                                                    <div style="float:left; margin-right: 5px;">
                                                       <abs:select id="id_repGrpDiv" name="repGrpDiv" groupCode="MSE_Q07" value="" style="width:100px;" showValues="false" initOption="" cssClass="" changeCallback="changeGrpRepDivEvent"/>
                                                    </div>

                                                    <div id="qrdr_div1" style="float:left;">
                                                       <abs:select id="id_repDivSlct1" name="repDivSlct1" groupCode="MSE_Q12" value="" style="width:120px;" showValues="true" initOption="" cssClass="" changeCallback="changeRepDivEvent"/>
                                                    </div>
                                                    <div id="qrdr_div2" style="float:left;">
                                                       <abs:select id="id_repDivSlct2" name="repDivSlct2" groupCode="MSE_Q13" value="" style="width:120px;" showValues="true" initOption="" cssClass="" changeCallback="changeRepDivEvent"/>
                                                    </div>
                                                    <div id="qrdr_div3" style="float:left;">
                                                       <abs:select id="id_repDivSlct3" name="repDivSlct3" groupCode="MSE_Q14" value="" style="width:120px;" showValues="true" initOption="" cssClass="" changeCallback="changeRepDivEvent"/>
                                                    </div>

                                                    <input type="hidden" id="id_repDiv" name="repDiv" value="">
                                                </td>
                                                <th class="search_th_c">환자명</th>
                                                <td class="search_td">
                                                    <input id="guesPatNm" name= "guesPatNm">
                                                </td>
                                            </tr>
                                            <tr>
                                                <th class="search_th_c">
                                                    <select id="searchDt" name="searchDt">
                                                        <option value="repNotiDt">통보일자</option>
                                                        <option value="quaDt">검역일자</option>
                                                    </select>
                                                </th>
                                                <td class="search_td">
                                                    <abs:Calendar calId="searchFromDt" name="searchFromDt" type="img" showButtonPanel="true" clean="true" readonly="true" refEnId="searchToDt" value="${dateAgo}"/>
                                                    <abs:Calendar calId="searchToDt"   name="searchToDt"   type="img" showButtonPanel="true" clean="true" readonly="true" refStId="searchFromDt" value="${sysDate}"/>

                                                    <input type="checkbox" id="dtSearchAt" name="dtSearchAt" onclick="fn_dtSearchAt();" checked="checked"/> 기간조회여부
                                                </td>
                                                <th class="search_th_c">추적상태</th>
                                                <td class="search_td">
                                                    <abs:select id="epiSttus" name="epiSttus" groupCode="MSE_R02"  showValues="false" initOption="" cssClass=""/>
                                                </td>
                                                <th class="search_th_c">추적번호</th>
                                                <td class="search_td">
                                                    <input id="epiNo" name= "epiNo">
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <!-- 검색테이블 종료 -->
                <div class="m_t10"><!-- 빈공간 --></div>
                <!-- 버튼영역 시작 -->
                <div class="con_title_area">
                    <div class="list_resultcount">
                        검색건수 총 <span class="bold" id="recordsCount">0</span>&nbsp;건
                        ( 미실시 <span class="bold" id="cnt002" style="color: red;">0</span>&nbsp;건 )
                    </div>
                    <div class="btn_area float_right">
                        <dl class="btn_bg_b" onclick="javascript:fn_gridSearch(); return false;">
                            <dd class="btnbg_left"></dd>
                            <dd class="btnbg_center" >조회</dd>
                            <dd class="btnbg_right"></dd>
                        </dl>
                        <dl class="btn_bg_lb" onclick="javascript:fn_openViewPopup(); return false;">
                            <dd class="btnbg_left"></dd>
                            <dd class="btnbg_center" >상세보기</dd>
                            <dd class="btnbg_right"></dd>
                        </dl>
<c:if test="${sessionScope.SSKCDC_INSTTTYID eq '1' }">  <%--6:검역소 --%>
<c:if test="${sessionScope.ABS_AUTH_READONLY eq 'N'}">  <!-- 감염병관리사업단-시도 여부 -->
                        <dl class="btn_bg_g" onclick="javascript:fn_delete(); return false;">
                            <dd class="btnbg_left"></dd>
                            <dd class="btnbg_center" style="height:19px">삭제</dd>
                            <dd class="btnbg_right"></dd>
                        </dl>
</c:if>
</c:if>
                        <dl class="btn_bg_gr" onclick="javascript:commonExcelDown(); return false;">
                            <dd class="btnbg_left"></dd>
                            <dd class="btnbg_center" >엑셀 다운로드</dd>
                            <dd class="btnbg_right"></dd>
                        </dl>
                    </div>
                </div>
                <!-- 버튼영역 시작 -->
                <div class="m_t10"><!-- 빈공간 --></div>
                <abs:Grid   id="traceErccrSttusList"
                            width="1198"
                            height="400"
                            directquery="traceErccrSttus.SELECT_TRACEERCCRSTTUS_LIST"
                            componentclass="TraceAreaGridComponent"
                            colNames ="[
                                'NO'
                                ,'추적번호'
                                ,'시도'
                                ,'추적기관'
                                ,'보고그룹'

                                ,'검역일자'
                                ,'통보일자'
                                ,'출발지'
                                ,'경유지'
                                ,'구분'

                                ,'환자구분'
                                ,'성명'
                                ,'상태'
                                ,'사유'
                                ,'1차 추적 조사일자'

                                ,'증상'
                                ,'2차 추적 조사일자'
                                ,'증상'
                                ,'수정보건소'
                                ,'이관여부'

                                ,'해제여부'
                                ,'키'
                                ,'환자구분코드'
                                ]"
                            colModel ="[
                                            {name:'RNUM',        width:'40px',   align:'center'},
                                            {name:'EPI_NO',         width:'50px',  align:'center'},
                                            {name:'SIDO_CODE',       width:'80px',  align:'center'},
                                            {name:'INSTTSN',      width:'150px',  align:'center'},
                                            {name:'REP_DIV',    width:'150px',   align:'center'},

                                            {name:'QUA_DT',    width:'100px',   align:'center'},
                                            {name:'REP_NOTI_DE_8',    width:'70px',   align:'center'},
                                            {name:'PORT_NM',    width:'100px',   align:'center'},
                                            {name:'RID_NAT_NM',    width:'100px',   align:'center'},
                                            {name:'PG_TYP',    width:'50px',   align:'center'},

                                            {name:'GUES_DIV_NM',    width:'60px',   align:'center'},
                                            {name:'GUES_PAT_NM',    width:'80px',   align:'center'},
                                            {name:'EPI_STTUS',    width:'80px',   align:'center'},
                                            {name:'EPI_IMPRTY_RESN',    width:'150px',   align:'center'},
                                            {name:'EPI_DATE1',   width:'120px',   align:'center'},

                                            {name:'EPI_RSLT1',   width:'50px',   align:'center'},
                                            {name:'EPI_DATE2',   width:'120px',   align:'center'},
                                            {name:'EPI_RSLT2',   width:'50px',   align:'center'},
                                            {name:'INSTTSN_MOD',   width:'150px',   align:'center'},
                                            {name:'STAUTS',   width:'50px',   align:'center'},

								            {name : 'RELIS_AT', index: 'RELIS_AT', width: 80, align: 'center'},
                                            {name:'REP_NOTI_NO',   width:'60px',   align:'center', hidden:'true'},
                                            {name:'GUES_DIV',   width:'60px',   align:'center', hidden:'true'}
                                        ]"
                            caption="입국자 추적 리스트"
                            rowNum="10"
                            rowList="[10,20,30]"
                            sortingYN="yes"
                            ondblClickRow="ondbClickRow"
                            totalRowCount="recordsCount"
                            singleselect="true"
                            loadComplete="gridLoadComplete"
                            
                />
            </td>
        </tr>
    </table>

</form>

</body>
</html>
