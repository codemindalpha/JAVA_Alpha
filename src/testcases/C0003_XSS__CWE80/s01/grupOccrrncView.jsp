<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="abs" uri="/WEB-INF/tlds/abs-component.tld" %>
<%@ taglib prefix="tids" uri="/WEB-INF/tlds/tids-component.tld" %>
<%@ taglib prefix="select" tagdir="/WEB-INF/tags/tids/select"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    /**
	 * @FileName : grupOccrrncView.jsp
	 * @상세설명 : 성홍열 집단발생관리 팝업화면
	 * @작성일시 : 2018. 12. 20.
	 * @작 성 자 : 박윤상
	 * @수 정 이 력
	 * @
	 * @  수정일                                수정자                                      수정내용
	 * @ -------          --------    ---------------------------
	 * @ 2018. 12. 20.        박윤상                                      최초등록
     *
     */
%>
<link rel="stylesheet" type="text/css" href="<c:url value='${STATICS_PATH}/abs/common/css/abs-nation.css'/>">
<script src="<c:url value='${STATICS_PATH}/abs/common/js/abs-nation.js'/>"></script>
<script src="<c:url value='${STATICS_PATH}/js/biz/eo/eoUtil.js'/>"></script>
<style>
    .selWidth{
        width: 95px;
    }
</style>
<script type="text/javascript">
    var id = "popup_layer";
    var bgId = "Graybg";

    var flag = "<c:out value='${paramMap.flag}'/>";
    var reprtApprvSttusCd = "<c:out value='${reportDtl.ocReport.reprtApprvSttusCd}'/>";
    var ieVer = fn_getBrowserInfo()["version"];
    $(document).ready(function() {

        //최초 로딩시 스크롤 가장위로
        setTimeout(function(){
            $(".pop_conWrap > div").scrollTop(0);
        }, 5);

        //수정화면 일 경우 탭 클릭 이벤트
        if(flag == "U"){
            <c:choose>
            <c:when test="${sessionVO.instttyId ne '4' }">fn_tabClick();
            </c:when>
            <c:otherwise>
            if (reprtApprvSttusCd != "010") { //신고보고_미확인이 아닌 경우
                //tab 클릭 이벤트
                fn_tabClick();
            } else {
                $('.psub1').click(function() {
                    alert('시도 승인이 필요합니다.');
                });
            }
            </c:otherwise>
            </c:choose>

			var ocReportArChkList = $("#ocReportArChkList").val().split('');
			var pcmbArTableChkBoxList = $("#pcmbArTable").find(":checkbox");

			if(ocReportArChkList.length) {
				for(var i = 0; i < ocReportArChkList.length; i++){
					pcmbArTableChkBoxList[ocReportArChkList[i]-1].checked = true;
				}
			}			            

			var resultDtlArChkList = $("#resultDtlArChkList").val().split('');
			var rcmbArTableChkBoxList = $("#rcmbArTable").find(":checkbox");

			if(resultDtlArChkList.length){
				for(var i = 0; i < resultDtlArChkList.length; i++){
					rcmbArTableChkBoxList[resultDtlArChkList[i]-1].checked = true;
				}
			}			            						
        }

        //주소 검색 버튼(발생장소)
        $("#pbtnSearchRdnmadrPest").click(function(){
            window.fnRetNewPostSetN = function(rtvdata, arrIdx) {
                if (rtvdata && !arrIdx) {
                    $("#ptxtZipcode").val(rtvdata.zip);
                    $("#ptxtJiAddr1").val(rtvdata.oldAdres1);
                    $("#ptxtJiAddr2").val(rtvdata.oldAdres2);
                    $("#ptxtDoAddr1").val(rtvdata.newAdres1);
                    $("#ptxtDoAddr2").val(rtvdata.newAdres2);
                    $("#ptxtDoAddr3").val(rtvdata.newAdres3);
                    $("#phidDoNo").val(rtvdata.newFlag);
                }
            };

            fnCallAddr();
        });

        //숫자 입력 이벤트 등록
        fn_bindNumberEvent(["registForm #ptxtExSymptom"
            , "registForm #ptxtExReprtSymptom"
            , "registForm #ptxtExConfirmSymptom"
            , "registForm #ptxtExSuspSymptom"
            , "registForm #ptxtFcAge"
            , "registForm #ptxtInptCs"
            , "registForm #ptxtCompliCs"
            , "registForm #ptxtHeadDupCs"
            , "registForm #ptxtOthersCs"
            , "registForm #rtxtExSymptom"
            , "registForm #rtxtExReprtSymptom"
            , "registForm #rtxtExConfirmSymptom"
            , "registForm #rtxtExSuspSymptom"
            , "registForm #rtxtFcAge"
            , "registForm #rtxtInptCs"
            , "registForm #rtxtCompliCs"
            , "registForm #rtxtHeadDupCs"
            , "registForm #rtxtOthersCs"
        ]);

        //숫자 하이픈 괄호 입력 이벤트 등록
        fn_bindNumberHyphenEvent(["registForm #ptxtRegTel"
        ]);

        //보고버튼 이벤트
        $("#pbtnReport").click(function(){
            fn_report();
        });

        //결과보고서 제출 버튼 이벤트
        $("#pbtnResultReport").click(function(){
            fn_reportResultReport();
        });
        
        $("#pbtnResultTempReport").click(function() {
        	fn_reportTempResultReport();
        });        

        //승인버튼
        $("#pbtnConfm").click(function(){
            fn_apprvAjax("CONFM");
        });

        //반려버튼
        $("#pbtnReturn").click(function(){
            fn_apprvAjax("RETURN");
        });
                
        $(':checkbox').not('.ar').on('click', function() {
			$(this).parent().find(":checkbox").each(function () { 
				this.checked = false;
			});
			this.checked = true;
		});
        
      	//첨부파일 버튼
        $("#rbtnAddAtchFile").click(function() {
            addFileUploadEvent();
        });
      	
        $("#rtbFileListResultReport").click(function() {
            addResultReportFileUploadEvent();
        });
    });

    //탭 클릭 이벤트
    function fn_tabClick(){
        $(".psub1").click(function(){
            //탭 선택 클래스 초기화 및 지정
            $(".poptab li").prop("class", "");
            $(this).closest("li").prop("class", "sel");

            //클릭된 탭 영역 활성화 및 나머지 탭 영역 숨김
            var tabId = $(this).data("tabid");
            $("[id^=mtab]").hide();
            $("#" + tabId).show();

            //검사결과 탭 클릭시 그리드 조회 및 버튼 활성화 컨트롤
            $(".pbtnSave").hide();
            $('.pbtn10').hide();
            $('.pbtn01').hide();
            
            if(tabId == "mtab01"){
                $("#pbtnReport").parent().show();
                $('.pbtn10').show();
            }
            if(tabId == "mtab02"){
            	$("#pbtnResultReport").parent().show();
            	$('.pbtn01').show();
            }
            $("#pbtnClose").parent().show();
        });
    }

    //보고 버튼 함수
    function fn_report(){
        
        if(!chkForm(this.registForm)) {
        	return;
        }

        if(reprtApprvSttusCd == "002" || reprtApprvSttusCd == "004"){
            if($("#ptxtModReason").val() == ""){
                alert("수정사유를 입력해주세요.");
                return;
            }
        }

        if(reprtApprvSttusCd == "003" || reprtApprvSttusCd == "005"){
            if($("#ptxtReportReason").val() == ""){
                alert("보고사유를 입력해주세요.");
                return;
            }
        }

        if(!confirm("보고하시겠습니까?")){
            return;
        }

        //저장 ajax
        fn_registAjax();
    }

    //저장 데이터 세팅
    function fn_setJsonData(){
		var arChk = "";
        var paramMap = { flag : flag
            , areaCtprvnCd : $("#pcmbAreaCtprvnCd").val()
            , helthinsttCd : $("#pcmbHelthinsttCd").val()
            , regTel : $("#ptxtRegTel").val()
            , mscReprtSn : $("#phidMscReprtSn").val()
            , reprtApprvSttusCd : $("#phidReprtApprvSttusCd").val()
            , kcnEmplyrkey : $("#mhidKcnEmplyrkey").val()
            , rptOdr : $("#phidRptOdr").val()
            , modReason : $("#ptxtModReason").val()
            , reportReason : $("#ptxtReportReason").val()
        };
        
        $('#mtab01 :input').not(':checkbox').each(function(index, value) {
        	if($(this).prop('name')) {
        		var objNm = $(this).prop('name');
        		var objVal = $(this).val();
        		
        		paramMap[objNm] = objVal;
        	}
        });
        
        $('#mtab01 :input').each(function(index, value) {
        	
        	if(!$(this).prop('checked')) {
        		return;
        	}        		
        	
        	if($(this).prop('name')) {
        		var objNm = $(this).prop('name');
        		var objVal = $(this).val();
        		
        		paramMap[objNm] = objVal;
        	}
        });

        for(var i = 0; i < $("#pcmbArTable").find(":checkbox").length; i++) {
        	$("#pcmbArTable").find(":checkbox")[i].checked ? arChk += (i+1) : "";
		}

		paramMap.arChk = arChk;

        return paramMap;
    }

    function fn_registAjax(){
        var paramMap = fn_setJsonData();

        $.ajax({
            url: "<c:url value='/biz/sf/grup/registGrupOccrrncAjax.do'/>",
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data: JSON.stringify(paramMap),
            success: function(data) {
                if(data.code == "R.CMMN_E0001") {
                    alert(data.message);
                    return;
                }

                if(flag == "I"){
                    $("#phidMscReprtSn").val(data.data.mscReprtSn);
                    $("#pspnReprtNo").text(data.data.mscReprtSn);
                    $("#pspnReprtUpdNo").text("1");
                    $("#pspnUpdtDt").text($("#pcalReprtReportDt").val());
                    alert("정상적으로 보고되었습니다.");

                    fn_close();
                }else{
                    alert("정상적으로 수정되었습니다.");
                    fn_close();
                }
            },
            /*오류 시*/
            error : function(xhr, status, error) {
                alert("등록 하는 중 에러가 발생했습니다.\n관리자에게 문의해주세요.");
            },
            beforeSend:absBeforeJob,
            complete :absCompleteJob
        });
    }

    //반려 버튼 함수
    function fn_apprvAjax(flagApprv){

        var confirmMsg = "승인하시겠습니까?";
        if(flagApprv == "RETURN"){
            confirmMsg = "반려하시겠습니까?";
        }

        if(!confirm(confirmMsg)){
            return;
        }

        var paramMap = { mscReprtSn : $("#phidMscReprtSn").val()
            , reprtApprvSttusCd : $("#phidReprtApprvSttusCd").val()
            , flag : flagApprv
        };

        if(flagApprv == "RETURN"){ //반려일 때 반려사유
            if($("#ptxtRejectReason").val() == ""){
                alert("반려사유를 입력해주세요.");
                return;
            }
            paramMap["rejectReason"] =  $("#ptxtRejectReason").val();
        }

        $.ajax({
            url: "<c:url value='/biz/sf/grup/updateGrupOccrrncApprvSttusAjax.do'/>",
            type:"POST",
            data : paramMap,
            success: function(data) {
                if(data.code == "R.CMMN_E0001") {
                    alert(data.message);
                    return;
                }

                if(flagApprv == "RETURN"){
                    alert("반려되었습니다.");
                }else{
                    alert("승인되었습니다.");
                }

                fn_close();
            },
            /* 오류 시*/
            error : function(xhr, status, error) {
                alert("보고하는 중 에러가 발생했습니다.\n관리자에게 문의해주세요.");
            },
            beforeSend:absBeforeJob,
            complete :absCompleteJob
        });

    }

    //권한 및 화면에 따른 selectbox option 제거
    function fn_selectRemove(){
        var instttyId = "<c:out value='${sessionVO.instttyId }' />";

        if(flag == "U" || instttyId == "4"){
            //지역, 보건소 셀렉트 박스 옵션 삭제
            $("#pcmbAreaCtprvnCd option:not(:selected)").remove();
            $("#pcmbHelthinsttCd option:not(:selected)").remove();
        }
    }
    
    // 결과보고서 제출
    function fn_reportResultReport() {
    	
        if(!confirm("결과보고 하시겠습니까?")){
            return;
        }

        //저장 ajax
        fn_registResultReportAjax();
    }
    
    function fn_registResultReportAjax() {
    	
    	var paramMap = fn_setJsonDataResultReport();
    	
    	$.ajax({
            url: "<c:url value='/biz/sf/grup/registGrupOccrrncResultReportAjax.do'/>",
            type: "POST",
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data: JSON.stringify(paramMap),
            success: function(data) {
                if(data.code == "R.CMMN_E0001") {
                    alert(data.message);
                    return;
                }

                alert("정상적으로 보고되었습니다.");

                fn_close();
            },
            /*오류 시*/
            error : function(xhr, status, error) {
                alert("등록 하는 중 에러가 발생했습니다.\n관리자에게 문의해주세요.");
            },
            beforeSend:absBeforeJob,
            complete :absCompleteJob
        });
    }
    
  	//저장 데이터 세팅
	function fn_setJsonDataResultReport() {
		var arChk = "";
		var paramMap = {
			mscReprtSn : $("#phidMscReprtSn").val(),
			reprtApprvSttusCd : $("#phidReprtApprvSttusCd").val(),
			psitnNm : $("#rtxtPsitnNm").val(),
			wrter : $("#rtxtWrter").val(),
			wrterCttpc : $("#rtxtWrterCttpc").val()
		};
		
		$('#mtab02 :input').not(':checkbox').each(function(index, value) {
        	if($(this).prop('name')) {
        		var objNm = $(this).prop('name');
        		var objVal = $(this).val();
        		
        		paramMap[objNm] = objVal;
        	}
        });
        
        $('#mtab02 :input').each(function(index, value) {
        	
        	if(!$(this).prop('checked')) {
        		return;
        	}        		
        	
        	if($(this).prop('name')) {
        		var objNm = $(this).prop('name');
        		var objVal = $(this).val();
        		
        		paramMap[objNm] = objVal;
        	}
        });

        for(var i = 0; i < $("#rcmbArTable").find(":checkbox").length; i++) {
        	$("#rcmbArTable").find(":checkbox")[i].checked ? arChk += (i+1) : "";
		}

		paramMap.arChk = arChk;
        
        return paramMap;
	}
  	
	/* 집단발생정보 첨부파일 시작 */
    function addFileUploadEvent() {
        var loginId = '<c:out value="${sessionVO.loginId}"/>';
        var fileKey = $("#rhdnFileKey").val();

        if(fileKey == '') {
            absFile.component2.register("sf/grup", loginId, "", "fileUpDownKey");
        } else {
            absFile.component2.modify(fileKey, "sf/grup", loginId, "", "fileUpDownKey");
        }
    }
    function fileUpDownKey(fileKey) {

        /* fileSeq  1:집단발생정보, 2:결과보고서*/
        var paramMap = {
            mscReprtSn : $("#phidMscReprtSn").val()
            , fileKey : fileKey
            , fileSeq : "1"
        };
        $.ajax({
            url: "<c:url value='/biz/sf/grup/fileUploadGrupOccrrncAjax.do'/>",
            type: "POST",
            async: false,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data: JSON.stringify(paramMap),
            success: function(data) {
                if(data.code == "R.CMMN_I0001") {

                    if(data.data.length > 0) {
                        $("#rtbFileList tbody").empty();
                        $(data.data).each(function(index, value) {
                            var apdTr = '<tr>';
                            if(index == 0) {
                                apdTr += '<th class="search_th_c" rowspan="'+data.data.length+'">첨부파일 <input type="hidden" id="rhdnFileKey" value='+this.FILE_KEY +'>';
                                apdTr += '<input type="button" id="rbtnAddAtchFile" class="input_btn_plus" /></th>';
                            }
                            apdTr += '<td class="search_td" style="height: 20px;">' + this.FILE_NM+ '</td></tr>';
                            $('#rtbFileList > tbody:last').append(apdTr);
                        });

                        //첨부파일 버튼
                        $("#rbtnAddAtchFile").click(function() {
                            addFileUploadEvent();
                        });
                    }
                }
            },
            error : function(xhr, status, error) {
                alert("파일 업로드 하는 중 에러가 발생했습니다.\n관리자에게 문의해주세요.");
            }
        });
    }
    /* 집단발생정보 첨부파일 끝 */
    
   /* 결과보고서 첨부파일 시작 */
    function addResultReportFileUploadEvent() {
        var loginId = '<c:out value="${sessionVO.loginId}"/>';
        var fileKey = $("#rhdnFileKeyResultReport").val();

        if(fileKey == '') {
            absFile.component2.register("sf/grup", loginId, "", "resultReportfileUpDownKey");
        } else {
            absFile.component2.modify(fileKey, "sf/grup", loginId, "", "resultReportfileUpDownKey");
        }
    }
    function resultReportfileUpDownKey(fileKey) {

        /* fileSeq  1:집단발생정보, 2:결과보고서*/
        var paramMap = {
            mscReprtSn : $("#phidMscReprtSn").val()
            , fileKey : fileKey
            , fileSeq : "2"
        };
        $.ajax({
            url: "<c:url value='/biz/sf/grup/fileUploadGrupOccrrncAjax.do'/>",
            type: "POST",
            async: false,
            contentType: "application/json; charset=UTF-8",
            dataType: "json",
            data: JSON.stringify(paramMap),
            success: function(data) {
                if(data.code == "R.CMMN_I0001") {

                    if(data.data.length > 0) {
                        $("#rtbFileListResultReport tbody").empty();
                        $(data.data).each(function(index, value) {
                            var apdTr = '<tr>';
                            if(index == 0) {
                                apdTr += '<th class="search_th_c" rowspan="'+data.data.length+'">첨부파일 <input type="hidden" id="rhdnFileKeyResultReport" value='+this.FILE_KEY +'>';
                                apdTr += '<input type="button" id="rbtnAddAtchFileResultReport" class="input_btn_plus" /></th>';
                            }
                            apdTr += '<td class="search_td" style="height: 20px;">' + this.FILE_NM+ '</td></tr>';
                            $('#rtbFileListResultReport > tbody:last').append(apdTr);
                        });

                        //첨부파일 버튼
                        $("#rtbFileListResultReport").click(function() {
                            addResultReportFileUploadEvent();
                        });
                    }
                }
            },
            error : function(xhr, status, error) {
                alert("파일 업로드 하는 중 에러가 발생했습니다.\n관리자에게 문의해주세요.");
            }
        });
    }
    /* 결과보고서 첨부파일 끝 */

	function fn_close() {
		fn_layerPopup_close(id, bgId);
		fn_gridSearch();
	}
</script>

<form method="post" id="registForm" name="registForm" enctype="multipart/form-data">
    <input type="hidden" id="phidFlag" name="flag" value="<c:out value='${paramMap.flag }' />">
    <input type="hidden" id="phidMscReprtSn" name="mscReprtSn" value="<c:out value='${reportDtl.ocReport.mscReprtSn }' />">
    <input type="hidden" id="phidReprtApprvSttusCd" name="reprtApprvSttusCd" value="<c:out value='${reportDtl.ocReport.reprtApprvSttusCd }' />">
    <input type="hidden" id="phidRptOdr" name="rptOdr" value="<c:out value='${reportDtl.ocReport.rptOdr }' />">
    <input type="hidden" id="ocReportArChkList" name="ocReportArChkList" value="<c:out value='${reportDtl.ocReport.arChk }' />">
    <input type="hidden" id="resultDtlArChkList" name="resultDtlArChkList" value="<c:out value='${reportDtl.resultDtl.arChk}' />">
    <c:if test="${paramMap.iFrameAt ne 'Y' }">
    <div id="regist_pop" style="width: 1200px; left: 5%; top: 10%;" class="pop_areaWrap">
        <h3>집단발생 관리</h3>
        </c:if>
        <div class="pop_conWrap">
            <!-- tab 시작 -->
            <ul class="poptab">
                <li class="sel"><a href="#" class="psub1" data-tabid="mtab01">집단발생정보</a></li>
                <li><a href="#" class="psub1" data-tabid="mtab02">결과보고서</a></li>
            </ul>
            <div class="m_t5"><!-- 빈공간 --></div>
            <!-- tab 끝 -->
            <div style="height: 655px; overflow:hidden; overflow-y:scroll;  position:relative;">
                <div class="pop_box ie7_popwrap_97">
                    <!-- tab영역01(집단발생정보) 시작 -->
                    <div id="mtab01">
                        <!-- 보고정보 시작 -->
                    <input type="hidden" id="currentDay" value="<c:out value='${sysDate}'/>">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td class="search_tbl">
									<table class="search_tbl_box" width="100%" border="0"
										cellspacing="0" cellpadding="0">
										<colgroup>
                                            <col width="10%">
                                            <col width="30%">
                                            <col width="10%">
                                            <col width="15%">
                                            <col width="10%">
                                            <col width="15%">
                                        </colgroup>
                                        <tbody>
	                                        <tr>
	                                            <c:choose>
	                                                <c:when test="${paramMap.flag eq 'I' }">
	                                                    <th class="search_th_c">지역<span class="f_r01 bold"> *</span></th>
	                                                    <td class="search_td">
	                                                        <c:set var="sidoGubun"><fmt:formatNumber pattern="00" value="${sessionVO.sidoGubun}" /></c:set>
	                                                        <tids:select id="pcmbAreaCtprvnCd" name="areaCtprvnCd" showValues="true" initOption="" style="width:85px;" cssClass="" value="${sidoGubun }" type="CTRD"/>
	                                                        <tids:select id="pcmbHelthinsttCd" name="helthinsttCd" pId="pcmbAreaCtprvnCd" showValues="true" initOption="" style="width:200px;" cssClass="" value="${sessionVO.absInsttid }" type="PBHLTH" callback="fn_selectRemove"/>
	                                                    </td>
	                                                    <th class="search_th_c">보고자<span class="f_r01 bold"> *</span></th>
	                                                    <td class="search_td">
	                                                        <c:out value="${sessionVO.emplyrNm }"></c:out>
	                                                        <input type="hidden" id="mhidKcnEmplyrkey" name="kcnEmplyrkey" value="<c:out value='${sessionVO.emplyrKey }'></c:out>">
	                                                    </td>
	                                                </c:when>
	                                                <c:otherwise>
	                                                    <th class="search_th_c">지역<span class="f_r01 bold"> *</span></th>
	                                                    <td class="search_td">
	                                                        <tids:select id="pcmbAreaCtprvnCd" name="areaCtprvnCd" showValues="true" initOption="" style="width:85px;" cssClass="" value="${reportDtl.ocReport.areaCtprvnCd }" type="CTRD"/>
	                                                        <tids:select id="pcmbHelthinsttCd" name="helthinsttCd" pId="pcmbAreaCtprvnCd" showValues="true" initOption="" style="width:200px;" cssClass="" value="${reportDtl.ocReport.helthinsttCd }" type="PBHLTH" callback="fn_selectRemove"/>
	                                                    </td>
	                                                    <th class="search_th_c">보고자<span class="f_r01 bold"> *</span></th>
	                                                    <td class="search_td">
	                                                        <c:out value="${reportDtl.ocReport.kcnEmplyrnm }"></c:out>
	                                                        <input type="hidden" id="mhidKcnEmplyrkey" name="kcnEmplyrkey" value="<c:out value='${sessionVO.emplyrKey }'></c:out>">
	                                                    </td>
	                                                </c:otherwise>
	                                            </c:choose>
	                                            <th class="search_th_c">보고번호</th>
	                                            <td class="search_td">
	                                                <span id="pspnReprtNo"><c:out value="${reportDtl.ocReport.reprtNo}"/></span>
	                                            </td>
	                                        </tr>
	                                        <tr>
	                                            <th class="search_th_c">보고일<span class="f_r01 bold"> *</span></th>
	                                            <td class="search_td">
	                                                <c:choose>
	                                                    <c:when test="${paramMap.flag eq 'I' && sessionVO.instttyId eq '1' }">
	                                                        <abs:Calendar calId="pcalReprtReportDt" name="reprtReportDt" type="img" readonly="true" value="${sysDate}"/>
	                                                    </c:when>
	                                                    <c:when test="${paramMap.flag eq 'I' }">
	                                                        <input type="text" id="pcalReprtReportDt" name="reprtReportDt" value="<c:out value='${sysDate}'/>" style="width: 70px;" maxlength="10" readonly="readonly">
	                                                    </c:when>
	                                                    <c:otherwise>
	                                                        <c:out value="${reportDtl.ocReport.reprtReportDt}"/>
	                                                    </c:otherwise>
	                                                </c:choose>
	                                            </td>
	                                            <th class="search_th_c">연락처<span class="f_r01 bold"> *</span></th>
	                                            <td class="search_td" colspan="3">
	                                                <c:choose>
	                                                    <c:when test="${paramMap.flag eq 'I'}">
	                                                        <input type="text" id="ptxtRegTel" name="regTel" value="<c:out value='${reportrCttpc.TELNO}'/>" maxlength="14">
	                                                    </c:when>
	                                                    <c:otherwise>
	                                                        <input type="text" id="ptxtRegTel" name="regTel" value="<c:out value='${reportDtl.ocReport.regTel}'/>" maxlength="14">
	                                                    </c:otherwise>
	                                                </c:choose>
	                                            </td>
	                                        </tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="m_t15">
						<!-- 빈공간 -->
					</div>
					
					
					<div class="pop_title_area">
						<h4 class="p_b10">인지경위</h4>
					</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td class="search_tbl">
									<table id="rtbCogpro" class="search_tbl_box" width="100%" border="0"
										cellspacing="0" cellpadding="0">
										<colgroup>
											<col width="20%">
											<col width="*">
										</colgroup>
										<tbody>
											<tr>
												<th class="search_th_c">인지일시</th>
												<td class="search_td">
													<abs:Calendar calId="pcalNotifyDe" name="notifyDe" type="img" readonly="true" refEnId="currentDay" value="${reportDtl.ocReport.notifyDe}"/>
													<select id="pcalNotifyTime" name="notifyTime">
														<c:forEach begin="0" end="24" varStatus="status">
							                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.notifyTime eq status.index }"> selected="selected"</c:if>>
							                                    <fmt:formatNumber pattern="00" value="${status.index}" />
							                                </option>
							                            </c:forEach>
													</select> 시
													<select id="pcalNotifyMin" name="notifyMin">
														<c:forEach begin="0" end="24" varStatus="status">
							                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.notifyMin eq status.index }"> selected="selected"</c:if>>
							                                    <fmt:formatNumber pattern="00" value="${status.index}" />
							                                </option>
							                            </c:forEach>
													</select> 분
												</td>
											</tr>
											<tr>
												<th class="search_th_c">인지경위</th>
												<td class="search_td">
													<input type="checkbox" id="pcmbCogProChk1" name="cogProChk" value="1" <c:if test="${reportDtl.ocReport.cogProChk == '1'}">checked</c:if>/>
													<label for="pcmbCogProChk1">자체인지</label>
													<input type="checkbox" id="pcmbCogProChk2" name="cogProChk" value="2" <c:if test="${reportDtl.ocReport.cogProChk == '2'}">checked</c:if>/>
													<label for="pcmbCogProChk2">환자 보호자</label>
													<input type="checkbox" id="pcmbCogProChk3" name="cogProChk" value="3" <c:if test="${reportDtl.ocReport.cogProChk == '3'}">checked</c:if>/>
													<label for="pcmbCogProChk3">보건의료기관</label>
													<input type="checkbox" id="pcmbCogProChk4" name="cogProChk" value="4" <c:if test="${reportDtl.ocReport.cogProChk == '4'}">checked</c:if>/>
													<label for="pcmbCogProChk4">기타</label>
													<input type="text" id="ptxtCogProRmk" name="cogProRmk" value="<c:out value='${reportDtl.ocReport.cogProRmk}'/>" style="width:22.7em;"/>
												</td>
											</tr>
											<tr>
												<th class="search_th_c">인지개요</th>
												<td class="search_td">
													<input type="text" id="ptxtCogProSumry" name="cogProSumry" value="<c:out value='${reportDtl.ocReport.cogProSumry}'/>" style="width:47em;" maxlength="1500"/>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
					<!-- 인지 경위 종료 -->
					<div class="m_t15">
						<!-- 빈공간 -->
					</div>
					
					<div class="pop_title_area">
						<h4 class="p_b10">발생개요</h4>
					</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td class="search_tbl">
									<table id="rtbSumry" class="search_tbl_box" width="100%" border="0"
										cellspacing="0" cellpadding="0">
										<colgroup>
											<col width="10%">
											<col width="10%">
						                    <col width="10%">
						                    <col width="15%">
						                    <col width="10%">
						                    <col width="15%">
						                    <col width="10%">
						                    <col width="20%">
										</colgroup>
										<tbody>
											<tr>
	                                            <th class="search_th_c" rowspan="3">발생지역<span class="f_r01 bold"> *</span></th>
	                                            <th class="search_th_c">우편번호</th>
	                                            <td class="search_td" colspan="6">
	                                                <input type="text" id="ptxtZipcode" name="zipcode" value="<c:out value='${reportDtl.ocReport.zipcode}'/>" style="width: 60px;" maxlength="10" readonly="readonly" required="true" msg="발생지역을 ">
	                                                <!-- <input type="text" id="ptxtZipcode" name="zipcode" value="28165" style="width: 60px;" maxlength="10" readonly="readonly" required="true" msg="발생지역을 "> -->
	                                                <input type="button" id="pbtnSearchRdnmadrPest" class="input_btn" value="검색">
	                                            </td>
	                                        </tr>
	                                        <tr>
	                                            <th class="search_th_c">지번</th>
	                                            <td class="search_td" colspan="6">
	                                                <input type="text" id="ptxtJiAddr1" name="jiAddr1" value="<c:out value='${reportDtl.ocReport.jiAddr1}'/>" style="width: 40%;" readonly="readonly">
	                                                <input type="text" id="ptxtJiAddr2" name="jiAddr2" value="<c:out value='${reportDtl.ocReport.jiAddr2}'/>" style="width: 20%;" maxlength="65">
	                                                <!-- <input type="text" id="ptxtJiAddr1" name="jiAddr1" value="충청북도 청주시 흥덕구 오송읍 연제리" style="width: 40%;" readonly="readonly"> -->
	                                                <!-- <input type="text" id="ptxtJiAddr2" name="jiAddr2" value="715" style="width: 20%;" maxlength="65"> -->
	                                            </td>
	                                        </tr>
	                                        <tr>
	                                            <th class="search_th_c">도로명</th>
	                                            <td class="search_td" colspan="6">
	                                                <input type="text" id="ptxtDoAddr1" name="doAddr1" value="<c:out value='${reportDtl.ocReport.doAddr1}'/>" style="width: 40%;" readonly="readonly">
	                                                <input type="text" id="ptxtDoAddr2" name="doAddr2" value="<c:out value='${reportDtl.ocReport.doAddr2}'/>" style="width: 40%;" maxlength="65">
	                                                <input type="text" id="ptxtDoAddr3" name="doAddr3" value="<c:out value='${reportDtl.ocReport.doAddr3}'/>" style="width: 12%;" readonly="readonly">
	                                                <input type="hidden" id="phidDoNo" name="doNo" value="<c:out value='${reportDtl.ocReport.doNo}'/>">
	                                                <%-- <input type="text" id="ptxtDoAddr1" name="doAddr1" value="충청북도 청주시 흥덕구 오송읍 오송생명로" style="width: 40%;" readonly="readonly">
	                                                <input type="text" id="ptxtDoAddr2" name="doAddr2" value="186" style="width: 40%;" maxlength="65">
	                                                <input type="text" id="ptxtDoAddr3" name="doAddr3" value="" style="width: 12%;" readonly="readonly">
	                                                <input type="hidden" id="phidDoNo" name="doNo" value="<c:out value='${reportDtl.ocReport.doNo}'/>"> --%>
	                                            </td>
	                                        </tr>
											<tr>
												<th class="search_th_c" colspan="2">발생장소(집단명)</th>
												<td class="search_td" colspan="6">
							                        <input type="text" id="ptxtOccrrncPlace" name="occrrncPlace" value="<c:out value='${reportDtl.ocReport.occrrncPlace}'/>" style="width: 50%;" maxlength="25">
												</td>
											</tr>
											<tr>
							                    <th class="search_th_c" rowspan="2" colspan="2">발생규모</th>
							                    <th class="search_th_c" rowspan="2">유증상자</th>
							                    <td class="search_td" rowspan="2">
							                        <input type="text" id="ptxtExSymptom" name="exSymptom" value="<c:out value='${reportDtl.ocReport.exSymptom}'/>" class="align_r inputNum"  style="width: 30%;" maxlength="10" required="true" msg="발생규모 유증상자를 "/> 명
							                    </td>
							                    <th class="search_th_c" rowspan="2">신고사례</th>
							                    <td class="search_td" rowspan="2">
							                        <input type="text" id="ptxtExReprtSymptom" name="exReprtSymptom" value="<c:out value='${reportDtl.ocReport.exReprtSymptom}'/>" class="align_r inputNum" style="width: 30%;" maxlength="10" required="true" msg="발생규모 신고사례를 "/> 명
							                    </td>
							                    <th class="search_th_c">확진사례</th>
							                    <td class="search_td">
							                        <input type="text" id="ptxtExConfirmSymptom" name="exConfirmSymptom" value="<c:out value='${reportDtl.ocReport.exConfirmSymptom}'/>" class="align_r inputNum" style="width: 30%;" maxlength="10" required="true" msg="발생규모 확진사례를 "/> 명
							                    </td>
							                </tr>
							                <tr>
							                    <th class="search_th_c">의심사례</th>
							                    <td class="search_td">
							                        <input type="text" id="ptxtExSuspSymptom" name="exSuspSymptom" value="<c:out value='${reportDtl.ocReport.exSuspSymptom}'/>" class="align_r inputNum" style="width: 30%;" maxlength="10" required="true" msg="발생규모 의심사례를 "/> 명
							                    </td>
							                </tr>
							                <tr>
							                    <th class="search_th_c" rowspan="6">최초사례</th>
							                    <th class="search_th_c">성별</th>
							                    <td class="search_td" colspan="3">
							                        <input type="checkbox" id="pcmbFcSex1" name="fcSex" value="1" <c:if test="${reportDtl.ocReport.fcSex == '1'}">checked</c:if>/>
							                        <label for="pcmbFcSex1">남</label>
							                        <input type="checkbox" id="pcmbFcSex2" name="fcSex" value="2" <c:if test="${reportDtl.ocReport.fcSex == '2'}">checked</c:if>/>
							                        <label for="pcmbFcSex2">여</label>
							                    </td>
							                    <th class="search_th_c">연령</th>
							                    <td class="search_td" colspan="2">
							                        <input type="text" id="ptxtFcAge" name="fcAge" value="<c:out value='${reportDtl.ocReport.fcAge}'/>" class="align_r inputNum" style="width: 15%;" maxlength="10"/> 세
							                    </td>
							                </tr>
							                <tr>
							                    <th class="search_th_c">증상발생일</th>
							                    <td class="search_td" colspan="3">
							                        <abs:Calendar calId="pcalFcOccrDe" name="fcOccrDe" value="${reportDtl.ocReport.fcOccrDe}" type="img" readonly="true" refEnId="currentDay"/>
													<select id="pcalFcOccrTime" name="fcOccrTime">
														<c:forEach begin="0" end="24" varStatus="status">
							                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcOccrTime eq status.index }"> selected="selected"</c:if>>
							                                    <fmt:formatNumber pattern="00" value="${status.index}" />
							                                </option>
							                            </c:forEach>
													</select> 시
													<select id="pcalFcOccrMin" name="fcOccrMin">
														<c:forEach begin="0" end="24" varStatus="status">
							                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcOccrMin eq status.index }"> selected="selected"</c:if>>
							                                    <fmt:formatNumber pattern="00" value="${status.index}" />
							                                </option>
							                            </c:forEach>
													</select> 분
							                    </td>
							                    <th class="search_th_c">증 상</th>
							                    <td class="search_td" colspan="2">
							                    	<input type="text" id="ptxtFcMajSymptom" name="fcMajSymptom" value="<c:out value='${reportDtl.ocReport.fcMajSymptom}'/>" style="width: 80%;" maxlength="100">
							                    </td>
							                </tr>
							                <tr>
							                    <th class="search_th_c">환자구분</th>
							                    <td class="search_td" colspan="3">
							                        <input type="checkbox" id="pcmbFcPatiClassi1" name="fcPatiClassi" value="1" <c:if test="${reportDtl.ocReport.fcPatiClassi == '1'}">checked</c:if>/>
													<label for="pcmbFcPatiClassi1">환자</label>
													<input type="checkbox" id="pcmbFcPatiClassi2" name="fcPatiClassi" value="2" <c:if test="${reportDtl.ocReport.fcPatiClassi == '2'}">checked</c:if>/>
													<label for="pcmbFcPatiClassi2">의사환자</label>
							                    </td>
							                    <th class="search_th_c">신고일</th>
							                    <td class="search_td" colspan="2">
							                        <abs:Calendar calId="pcalFcNotifyDe" name="fcNotifyDe" value="${reportDtl.ocReport.fcNotifyDe}" type="img" readonly="true" refEnId="currentDay"/>
													<select id="pcalFcNotifyTime" name="fcNotifyTime">
														<c:forEach begin="0" end="24" varStatus="status">
							                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcNotifyTime eq status.index }"> selected="selected"</c:if>>
							                                    <fmt:formatNumber pattern="00" value="${status.index}" />
							                                </option>
							                            </c:forEach>
													</select> 시
													<select id="pcalFcNotifyMin" name="fcNotifyMin">
														<c:forEach begin="0" end="24" varStatus="status">
							                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcNotifyMin eq status.index }"> selected="selected"</c:if>>
							                                    <fmt:formatNumber pattern="00" value="${status.index}" />
							                                </option>
							                            </c:forEach>
													</select> 분
							                    </td>
							                </tr>
							                <tr>
							                    <th class="search_th_c" rowspan="3">검사정보</th>
							                    <th class="search_th_c" colspan="2">인후도찰 배양검사</th>
							                    <td class="search_td" colspan="4">
													시행일:
													<abs:Calendar calId="pcalFcThroatInspctDe" name="fcThroatInspctDe" value="${reportDtl.ocReport.fcThroatInspctDe}" type="img" readonly="true" refEnId="currentDay"/>
													<select id="pcalFcThroatInspctTime" name="fcThroatInspctTime">
														<c:forEach begin="0" end="24" varStatus="status">
							                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcThroatInspctTime eq status.index }"> selected="selected"</c:if>>
							                                    <fmt:formatNumber pattern="00" value="${status.index}" />
							                                </option>
							                            </c:forEach>
													</select> 시
													<select id="pcalFcThroatInspctMin" name="fcThroatInspctMin">
														<c:forEach begin="0" end="24" varStatus="status">
							                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcThroatInspctMin eq status.index }"> selected="selected"</c:if>>
							                                    <fmt:formatNumber pattern="00" value="${status.index}" />
							                                </option>
							                            </c:forEach>
													</select> 분
													<br/>
													결과 : 
													<input type="checkbox" id="pcmbFcThroatInspctChk1" name="fcThroatInspctChk" value="1" <c:if test="${reportDtl.ocReport.fcThroatInspctChk == '1'}">checked</c:if>/>
													<label for="pcmbFcThroatInspctChk1">양성</label>
													<input type="checkbox" id="pcmbFcThroatInspctChk2" name="fcThroatInspctChk" value="2" <c:if test="${reportDtl.ocReport.fcThroatInspctChk == '2'}">checked</c:if>/>
													<label for="pcmbFcThroatInspctChk2">음성</label>
													<input type="checkbox" id="pcmbFcThroatInspctChk3" name="fcThroatInspctChk" value="3" <c:if test="${reportDtl.ocReport.fcThroatInspctChk == '3'}">checked</c:if>/>
													<label for="pcmbFcThroatInspctChk3">미시행</label>
							                    </td>
							                </tr>
							                <tr>
							                	<th class="search_th_c" colspan="2">신속항원검출검사</th>
							                    <td class="search_td" colspan="4">
					                        		시행일:
													<abs:Calendar calId="pcmbFcRadtDe" name="fcRadtDe" type="img" value="${reportDtl.ocReport.fcRadtDe}" readonly="true" refEnId="currentDay"/>
													<select id="pcmbFcRadtTime" name="fcRadtTime">
														<c:forEach begin="0" end="24" varStatus="status">
							                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcRadtTime eq status.index }"> selected="selected"</c:if>>
							                                    <fmt:formatNumber pattern="00" value="${status.index}" />
							                                </option>
							                            </c:forEach>
													</select> 시
													<select id="pcmbFcRadtMin" name="fcRadtMin">
														<c:forEach begin="0" end="24" varStatus="status">
							                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcRadtMin eq status.index }"> selected="selected"</c:if>>
							                                    <fmt:formatNumber pattern="00" value="${status.index}" />
							                                </option>
							                            </c:forEach>
													</select> 분
													<br/>
													결과 : 
													<input type="checkbox" id="pcmbFcRadtChk1" name="fcRadtChk" value="1" <c:if test="${reportDtl.ocReport.fcRadtChk == '1'}">checked</c:if>/>
													<label for="pcmbFcRadtChk1">양성</label>
													<input type="checkbox" id="pcmbFcRadtChk2" name="fcRadtChk" value="2" <c:if test="${reportDtl.ocReport.fcRadtChk == '2'}">checked</c:if>/>
													<label for="pcmbFcRadtChk2">음성</label>
													<input type="checkbox" id="pcmbFcRadtChk3" name="fcRadtChk" value="3" <c:if test="${reportDtl.ocReport.fcRadtChk == '3'}">checked</c:if>/>
													<label for="pcmbFcRadtChk3">미시행</label>
							                    </td>
							                </tr>
							                <tr>
							                	<th class="search_th_c" colspan="2">기타 검사</th>
							                    <td class="search_td" colspan="4">
					                        		시행일:
													<abs:Calendar calId="pcmbFcEtcDe" name="fcEtcDe" type="img" value="${reportDtl.ocReport.fcEtcDe}" readonly="true" refEnId="currentDay"/>
													<select id="pcmbFcEtcTime" name="fcEtcTime">
														<c:forEach begin="0" end="24" varStatus="status">
							                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcEtcTime eq status.index }"> selected="selected"</c:if>>
							                                    <fmt:formatNumber pattern="00" value="${status.index}" />
							                                </option>
							                            </c:forEach>
													</select> 시
													<select id="pcmbFcEtcMin" name="fcEtcMin">
														<c:forEach begin="0" end="24" varStatus="status">
							                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcEtcMin eq status.index }"> selected="selected"</c:if>>
							                                    <fmt:formatNumber pattern="00" value="${status.index}" />
							                                </option>
							                            </c:forEach>
													</select> 분
													<br/>
													결과 : 
													<input type="checkbox" id="pcmbFcEtcChk1" name="fcEtcChk" value="1" <c:if test="${reportDtl.ocReport.fcEtcChk == '1'}">checked</c:if>/>
													<label for="pcmbFcEtcChk1">양성</label>
													<input type="checkbox" id="pcmbFcEtcChk2" name="fcEtcChk" value="2" <c:if test="${reportDtl.ocReport.fcEtcChk == '2'}">checked</c:if>/>
													<label for="pcmbFcEtcChk2">음성</label>
													<input type="checkbox" id="pcmbFcEtcChk3" name="fcEtcChk" value="3" <c:if test="${reportDtl.ocReport.fcEtcChk == '3'}">checked</c:if>/>
													<label for="pcmbFcEtcChk3">미시행</label>
							                    </td>
							                </tr>
							                <tr>
							                	<th class="search_th_c" colspan="2">예방･관리 시작일</th>
							                    <td class="search_td" colspan="6">
							                        <abs:Calendar calId="pcmbFcPrevCrDe" name="fcPrevCrDe" value="${reportDtl.ocReport.fcPrevCrDe}" type="img" readonly="true" refEnId="currentDay"/>
													<select id="pcmbFcPrevCrTime" name="fcPrevCrTime">
														<c:forEach begin="0" end="24" varStatus="status">
							                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcPrevCrTime eq status.index }"> selected="selected"</c:if>>
							                                    <fmt:formatNumber pattern="00" value="${status.index}" />
							                                </option>
							                            </c:forEach>
													</select> 시
													<select id="pcmbFcPrevCrMin" name="fcPrevCrMin">
														<c:forEach begin="0" end="24" varStatus="status">
							                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcPrevCrMin eq status.index }"> selected="selected"</c:if>>
							                                    <fmt:formatNumber pattern="00" value="${status.index}" />
							                                </option>
							                            </c:forEach>
													</select> 분
							                    </td>
							                </tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
					<!-- 인지 경위 종료 -->
					<div class="m_t15">
						<!-- 빈공간 -->
					</div>
					
					<div class="pop_title_area">
						<h4 class="p_b10">중증사례</h4>
					</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td class="search_tbl">
									<table id="" class="search_tbl_box" width="100%" border="0"
										cellspacing="0" cellpadding="0">
										<colgroup>
											<col width="25%">
											<col width="25%">
						                    <col width="25%">
						                    <col width="25%">
										</colgroup>
										<tbody>
											<tr>
												<th class="search_th_c">입원사례(명)</th>
							                    <th class="search_th_c">합병증사례(명)</th>
							                    <th class="search_th_c">수두 중복감염(명)</th>
							                    <th class="search_th_c">기타 특이 사항(명)</th>
											</tr>
											<tr>
												<td class="search_td">
													<input type="text" id="ptxtInptCs" name="inptCs" value="<c:out value='${reportDtl.ocReport.inptCs}'/>" style="width:96%; margin: 5px 0;"/>
							                    </td>
							                    <td class="search_td">
													<input type="text" id="ptxtCompliCs" name="compliCs" value="<c:out value='${reportDtl.ocReport.compliCs}'/>" style="width:96%; margin: 5px 0;"/>
							                    </td>    
							                    <td class="search_td">
													<input type="text" id="ptxtHeadDupCs" name="headDupCs" value="<c:out value='${reportDtl.ocReport.headDupCs}'/>" style="width:96%; margin: 5px 0;"/>
							                    </td>
							                    <td class="search_td">
													<input type="text" id="ptxtOthersCs" name="othersCs" value="<c:out value='${reportDtl.ocReport.othersCs}'/>" style="width:96%; margin: 5px 0;"/>
							                    </td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
					<!-- 중증사례 종료 -->
					<div class="m_t15">
						<!-- 빈공간 -->
					</div>
					
					<div class="pop_title_area">
						<h4 class="p_b10">조치사항 및 계획</h4>
					</div>
					<table id="pcmbArTable" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td class="search_tbl">
									<table id="" class="search_tbl_box" width="100%" border="0"
										cellspacing="0" cellpadding="0">
										<tbody>
											<input type="checkbox" id="pcmbArChk1" name="arChk" class="ar" value="1"/>
											<label for="pcmbArChk1">성홍열 환자는 항생제 치료 시작 후 최소 24시간까지 등교(등원) 중지 교육</label><br/>
											<input type="checkbox" id="pcmbArChk2" name="arChk" class="ar" value="2"/>
											<label for="pcmbArChk2">손씻기 강조 (등교(등원) 시, 화장실 다녀온 후, 놀이 후, 식사 전･후, 하교(하원) 시)</label><br/>
											<input type="checkbox" id="pcmbArChk3" name="arChk" class="ar" value="3"/>
											<label for="pcmbArChk3">학부모와 직원들에게 성홍열 발생 주의 안내문 발송</label><br/>
											<input type="checkbox" id="pcmbArChk4" name="arChk" class="ar" value="4"/>
											<label for="pcmbArChk4">기타 조치사항 및 계획 기술</label><br/>
											<textarea id="ptxtArRmk" name="arRmk" rows="4" style="width: 99%; margin: 5px 0;" maxlength="1300"><c:out value='${reportDtl.ocReport.arRmk}'/></textarea>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
					
					<div class="m_t15">
						<!-- 빈공간 -->
					</div>
					
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
				        <tbody>
				        <tr>
				            <td class="search_tbl">
			                <table id="rtbFileList" class="search_tbl_box" width="100%" border="0" cellspacing="0" cellpadding="0">
				                <colgroup>
				                    <col width="20%">
				                    <col width="*">
				                </colgroup>
				                <tbody>
				                <c:if test="${fn:length(reportDtl.fileList) == 0}">
				                    <tr>
				                        <th class="search_th_c">첨부파일
				                            <input type="button" id="rbtnAddAtchFile" class="input_btn_plus" />
				                            <input type="hidden" id="rhdnFileKey">
				                        </th>
				                        <td class="search_td" style="height: 20px;">
				                        </td>
				                    </tr>
				                </c:if>
				                <c:if test="${fn:length(reportDtl.fileList) > 0}">
				                    <c:forEach var="fileList" items="${reportDtl.fileList }" varStatus="status">
				                        <tr>
				                            <c:if test="${ status.index eq 0 }">
				                                <th class="search_th_c" rowspan="${fn:length(reportDtl.fileList)}">첨부파일
				                                    <input type="button" id="rbtnAddAtchFile" class="input_btn_plus" />
				                                    <input type="hidden" id="rhdnFileKey" value="${fileList.FILE_KEY }">
				                                </th>
				                            </c:if>
				                            <td class="search_td" style="height: 20px;">
				                                    ${fileList.FILE_NM }
				                            </td>
				                        </tr>
				                    </c:forEach>
				                </c:if>
				                </tbody>
				            </table>
				            </td>
				        </tr>
				        </tbody>
				    </table>
				    
				    <div class="m_t15">
						<!-- 빈공간 -->
					</div>

                        <c:if test="${paramMap.flag eq 'U' }">
                            <div class="m_t15"><!-- 빈공간 --></div>
                            <c:if test="${sessionVO.instttyId ne '4' }"><!-- 시도, 본부 -->
                                <!-- 반려사유 시작 -->
                                <div class="pop_title_area">
                                    <h4 class="p_b10">반려사유</h4>
                                </div>
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tbody>
                                    <tr>
                                        <td class="search_tbl">
                                            <table class="search_tbl_box" width="100%" border="0" cellspacing="0" cellpadding="0">
                                                <colgroup>
                                                    <col width="10%">
                                                    <col >
                                                </colgroup>
                                                <tbody>
                                                <tr>
                                                    <th class="search_th_c">반려사유</th>
                                                    <td class="search_td">
                                                        <textarea id="ptxtRejectReason" name="rejectReason" rows="4" style="width: 99%; margin: 5px 0;" maxlength="660"><c:out value="${reportDtl.ocReport.rejectReason }"/></textarea>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                                <!-- 반려사유 종료 -->
                                <div class="m_t15"><!-- 빈공간 --></div>
                                <!-- 수정사유 시작 -->
                                <div class="pop_title_area">
                                    <h4 class="p_b10">수정사유</h4>
                                </div>
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tbody>
                                    <tr>
                                        <td class="search_tbl">
                                            <table class="search_tbl_box" width="100%" border="0" cellspacing="0" cellpadding="0">
                                                <colgroup>
                                                    <col width="10%">
                                                    <col >
                                                </colgroup>
                                                <tbody>
                                                <tr>
                                                    <th class="search_th_c">수정사유</th>
                                                    <td class="search_td">
                                                        <textarea id="ptxtModReason" name="modReason" rows="4" style="width: 99%; margin: 5px 0;" maxlength="660"><c:out value="${reportDtl.ocReport.modReason }"/></textarea>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                                <!-- 수정사유 종료 -->
                                <c:if test="${reportDtl.ocReport.rptOdr eq '2' || reportDtl.ocReport.rptOdr eq '3'}">  <!-- 보고차수가 2 또는 3인경우 -->
                                    <div class="m_t15"><!-- 빈공간 --></div>
                                    <!-- 보고사유 시작 -->
                                    <div class="pop_title_area">
                                        <h4 class="p_b10">보고사유</h4>
                                    </div>
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tbody>
                                        <tr>
                                            <td class="search_tbl">
                                                <table class="search_tbl_box" width="100%" border="0" cellspacing="0" cellpadding="0">
                                                    <colgroup>
                                                        <col width="10%">
                                                        <col >
                                                    </colgroup>
                                                    <tbody>
                                                    <tr>
                                                        <th class="search_th_c">보고사유</th>
                                                        <td class="search_td">
                                                            <textarea id="ptxtReportReason" name="reportReason" rows="4" style="width: 99%; margin: 5px 0;" maxlength="660"><c:out value="${reportDtl.ocReport.reportReason }"/></textarea>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <!-- 보고사유 종료 -->
                                </c:if>
                            </c:if>

                            <c:if test="${sessionVO.instttyId eq '4' }"><!-- 보건소 -->
                                <c:if test="${reportDtl.ocReport.reprtApprvSttusCd eq '002' || reportDtl.ocReport.reprtApprvSttusCd eq '003' || reportDtl.ocReport.reprtApprvSttusCd eq '004' || reportDtl.ocReport.reprtApprvSttusCd eq '005'}">
                                    <!-- 반려사유 시작 -->
                                    <div class="pop_title_area">
                                        <h4 class="p_b10">반려사유</h4>
                                    </div>
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tbody>
                                        <tr>
                                            <td class="search_tbl">
                                                <table class="search_tbl_box" width="100%" border="0" cellspacing="0" cellpadding="0">
                                                    <colgroup>
                                                        <col width="10%">
                                                        <col >
                                                    </colgroup>
                                                    <tbody>
                                                    <tr>
                                                        <th class="search_th_c">반려사유</th>
                                                        <td class="search_td">
                                                            <textarea id="ptxtRejectReason" name="rejectReason" rows="4" style="width: 99%; margin: 5px 0;" maxlength="660"><c:out value="${reportDtl.ocReport.rejectReason }"/></textarea>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <!-- 반려사유 종료 -->
                                    <div class="m_t15"><!-- 빈공간 --></div>
                                    <!-- 수정사유 시작 -->
                                    <div class="pop_title_area">
                                        <h4 class="p_b10">수정사유</h4>
                                    </div>
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tbody>
                                        <tr>
                                            <td class="search_tbl">
                                                <table class="search_tbl_box" width="100%" border="0" cellspacing="0" cellpadding="0">
                                                    <colgroup>
                                                        <col width="10%">
                                                        <col >
                                                    </colgroup>
                                                    <tbody>
                                                    <tr>
                                                        <th class="search_th_c">수정사유</th>
                                                        <td class="search_td">
                                                            <textarea id="ptxtModReason" name="modReason" rows="4" style="width: 99%; margin: 5px 0;" maxlength="660"><c:out value="${reportDtl.ocReport.modReason }"/></textarea>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <!-- 수정사유 종료 -->
                                    <div class="m_t15"><!-- 빈공간 --></div>
                                    <!-- 보고사유 시작 -->
                                    <div class="pop_title_area">
                                        <h4 class="p_b10">보고사유</h4>
                                    </div>
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tbody>
                                        <tr>
                                            <td class="search_tbl">
                                                <table class="search_tbl_box" width="100%" border="0" cellspacing="0" cellpadding="0">
                                                    <colgroup>
                                                        <col width="10%">
                                                        <col >
                                                    </colgroup>
                                                    <tbody>
                                                    <tr>
                                                        <th class="search_th_c">보고사유</th>
                                                        <td class="search_td">
                                                            <textarea id="ptxtReportReason" name="reportReason" rows="4" style="width: 99%; margin: 5px 0;" maxlength="660"><c:out value="${reportDtl.ocReport.reportReason }"/></textarea>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <!-- 보고사유 종료 -->
                                </c:if>
                            </c:if>
                        </c:if>

                        <!-- tab영역01(집단발생정보) 끝 -->
                    </div>					
                    <!-- tab영역05(결과보고서) 시작 -->
                    <div id="mtab02" style="display: none;">
                        <jsp:include page="grupOccrrncResultReportView.jsp" flush="true"/>
                    </div>
                    <!-- tab영역05(결과보고서) 끝 -->
                </div>
            </div>
            <div class="m_t20"><!-- 빈공간 --></div>
            <!-- 버튼 영역 시작 -->
            <div class="pop_btn_Wrap1">
                <div class="pop_btn_Wrap2">
                    <!--
                       .보건소
                          - 시도승인 || 본부승인 : 반려사유, 수정사유, 보고사유 표시
                          - 본부승인 일 때 수정 가능
                          - 신고보고 || 신고보고확인 || 결과보고(미승인) 일 때 수정 가능
                          - 시도반려 || 본부반려 : 반려사유, 수정사유, 보고사유(RPT_ODR 2 || 3 일때) 표시 및 수정 가능

                        .시도
                          - 반려사유, 수정사유 무조건 표시 & (RPT_ODR 2 || 3 일 때) 보고 사유 표시
                          - 신고보도 단순 확인(승인)과  결과보고(미승인) 일 때 승인 || 반려 가능

                        .본부
                          - 반려사유, 수정사유 무조건 표시 & (RPT_ODR 2 || 3 일 때) 보고 사유 표시
                          - 본부 승인일 때 수정 가능
                          - 결과보고(미승인) || 시도승인(결과보고) 일 때 승인 || 반려 가능
                    -->
                    <c:if test="${paramMap.histAt ne 'Y'}">
                        <c:if test="${paramMap.deleteAt ne 'Y'}">
                            <c:choose>
                                <c:when test="${sessionVO.instttyId eq '4' && sessionVO.absAuthReadOnly eq 'N'}">
                                    <c:if test="${empty reportDtl.ocReport.rptOdr || reportDtl.ocReport.rptOdr eq '1' || reportDtl.ocReport.rptOdr eq '2' || reportDtl.ocReport.rptOdr eq '3'}">
                                        <span class="btnpack btnpack_o pbtnSave"><a href="#" id="pbtnReport">신고보고</a></span>
                                        <span class="btnpack btnpack_o pbtnSave" style="display: none;"><a href="#" id="pbtnResultReport">결과보고서제출</a></span>
                                    </c:if>
                                </c:when>
                                <c:when test="${sessionVO.instttyId eq '3' && sessionVO.absAuthReadOnly eq 'N'}">
                                	<c:choose>
                                		<c:when test="${reportDtl.ocReport.reprtApprvSttusCd eq '010'}">
	                                		<span class="btnpack btnpack_o pbtn10"><a href="#" id="pbtnConfm">신고보고 승인</a></span>
                                		</c:when>
                                		<c:when test="${reportDtl.ocReport.reprtApprvSttusCd eq '001'}">
                                			<span class="btnpack btnpack_o pbtn01" style="display: none;"><a href="#" id="pbtnConfm">결과보고 승인</a></span>
                                            <span class="btnpack btnpack_o pbtn01" style="display: none;"><a href="#" id="pbtnReturn">반려</a></span>
                                		</c:when>
                                	</c:choose>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${paramMap.deleteAt ne 'Y'}"><!-- 삭제이력관리가 아닌 경우 -->
                                        <c:if test="${reportDtl.ocReport.reprtApprvSttusCd eq '001' || reportDtl.ocReport.reprtApprvSttusCd eq '003'}">
                                            <span class="btnpack btnpack_o pbtn01" style="display: none;"><a href="#" id="pbtnConfm">승인</a></span>
                                            <span class="btnpack btnpack_o pbtn01" style="display: none;"><a href="#" id="pbtnReturn">반려</a></span>
                                        </c:if>
                                        <c:if test="${empty reportDtl.ocReport.reprtApprvSttusCd || reportDtl.ocReport.reprtApprvSttusCd eq '005'}">
                                            <span class="btnpack btnpack_o pbtnSave"><a href="#" id="pbtnReport">신고보고</a></span>
                                        </c:if>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </c:if>
                    <span class="btnpack btnpack_b"><a id="pbtnClose" href="javascript:fn_close();">닫기</a></span>
                </div>
            </div>
            <!-- 버튼 영역 종료 -->
        </div>
        <c:if test="${paramMap.iFrameAt ne 'Y' }">
    </div>
    </c:if>
</form>