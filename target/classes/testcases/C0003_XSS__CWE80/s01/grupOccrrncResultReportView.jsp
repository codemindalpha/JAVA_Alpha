<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="abs" uri="/WEB-INF/tlds/abs-component.tld"%>
<%@ taglib prefix="tids" uri="/WEB-INF/tlds/tids-component.tld"%>
<%@ taglib prefix="select" tagdir="/WEB-INF/tags/tids/select"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	/**
	 * @Jsp Name : grupOccrrncResultReportView.jsp
	 * @상세설명 : 집단발생관리의 상세화면(결과보고서)
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
<!-- Safe: -->
<input type="hidden" id="currentDay" value="<c:out value='${sysDate}'/>">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tbody>
		<tr>
			<td class="search_tbl">
	            <table class="search_tbl_box" width="100%" border="0" cellspacing="0" cellpadding="0">
	                <colgroup>
	                    <col width="10%">
	                    <col width="20%">
	                    <col width="10%">
	                    <col width="20%">
	                    <col width="10%">
	                    <col width="*">
	                </colgroup>
	                <tbody>
					<!-- Safe: 1.-->
	                <c:if test="${empty reportDtl.resultDtl}"><!-- 결과보고서 최초등록인 경우 -->
	                <tr>
	                    <th class="search_th_c">소속</th>
	                    <td class="search_td">
							<!-- Safe: 2.-->
	                        <input type="text" id="rtxtPsitnNm" name="psitnNm" value="<c:out value='${reportDtl.ocReport.helthinsttKcnInsttnm}'/>" style="width: 70%;" maxlength="25">
	                    </td>
	                    <th class="search_th_c">작성자</th>
	                    <td class="search_td">
							<!-- Safe: 3.-->
	                        <input type="text" id="rtxtWrter" name="wrter" value="<c:out value='${reportDtl.ocReport.kcnEmplyrnm}'/>" style="width: 70%;" maxlength="25">
	                    </td>
	                    <th class="search_th_c">연락처</th>
	                    <td class="search_td">
							<!-- Safe: 4.-->
	                        <input type="text" id="rtxtWrterCttpc" name="wrterCttpc" value="<c:out value='${reportDtl.ocReport.regTel}'/>" style="width: 70%;" maxlength="25">
	                    </td>
	                </tr>
	                </c:if>
					<!-- Safe: -->
	                <c:if test="${not empty reportDtl.resultDtl}"><!-- 결과보고서 상세보기 경우 -->
	                <tr>
	                    <th class="search_th_c">소속</th>
	                    <td class="search_td">
							<!-- Safe: 5.-->
	                        <input type="text" id="rtxtPsitnNm" name="psitnNm" value="<c:out value='${reportDtl.resultDtl.psitnNm}'/>" style="width: 70%;" maxlength="25">
	                    </td>
	                    <th class="search_th_c">작성자</th>
	                    <td class="search_td">
							<!-- Safe: 6.-->
	                        <input type="text" id="rtxtWrter" name="wrter" value="<c:out value='${reportDtl.resultDtl.wrter}'/>" style="width: 70%;" maxlength="25">
	                    </td>
	                    <th class="search_th_c">연락처</th>
	                    <td class="search_td">
							<!-- Safe: 7.-->
	                        <input type="text" id="rtxtWrterCttpc" name="wrterCttpc" value="<c:out value='${reportDtl.resultDtl.wrterCttpc}'/>" style="width: 70%;" maxlength="25">
	                    </td>
	                </tr>
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
						<c:choose>
							<!-- Safe: 8.-->
							<c:when test="${empty reportDtl.resultDtl}"><!-- 결과보고서 최초등록인 경우 -->
								<tr>
									<th class="search_th_c">인지일시</th>
									<td class="search_td">
										<!-- Safe: 9.-->
										<abs:Calendar calId="rcalNotifyDe" name="notifyDe" type="img" readonly="true" refEnId="currentDay" value="${reportDtl.ocReport.notifyDe}"/>
										<select id="rcalNotifyTime" name="notifyTime">
											<c:forEach begin="0" end="24" varStatus="status">
												<!-- Safe: -->
				                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" 
												<c:if test="${reportDtl.ocReport.notifyTime eq status.index }"> selected="selected"</c:if>>
													<!-- Safe: 10.-->
				                                    <fmt:formatNumber pattern="00" value="${status.index}" />
				                                </option>
				                            </c:forEach>
										</select> 시
										<select id="rcalNotifyMin" name="notifyMin">
											<c:forEach begin="0" end="24" varStatus="status">
												<!-- Safe: 11.-->
				                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" 
													<!-- Safe: 12.-->
													<c:if test="${reportDtl.ocReport.notifyMin eq status.index }"> selected="selected"</c:if>>
													<!-- Safe: 13.-->
				                                    <fmt:formatNumber pattern="00" value="${status.index}" />
				                                </option>
				                            </c:forEach>
										</select> 분
									</td>
								</tr>
								<tr>
									<th class="search_th_c">인지경위</th>
									<td class="search_td">
										<!-- Safe: 14.-->
										<input type="checkbox" id="rcmbCogProChk1" name="cogProChk" value="1" <c:if test="${reportDtl.ocReport.cogProChk == '1'}">checked</c:if>/>
										<label for="rcmbCogProChk1">자체인지</label>
										<!-- Safe: 15.-->
										<input type="checkbox" id="rcmbCogProChk2" name="cogProChk" value="2" <c:if test="${reportDtl.ocReport.cogProChk == '2'}">checked</c:if>/>
										<label for="rcmbCogProChk2">환자 보호자</label>
										<!-- Safe: 16.-->
										<input type="checkbox" id="rcmbCogProChk3" name="cogProChk" value="3" <c:if test="${reportDtl.ocReport.cogProChk == '3'}">checked</c:if>/>
										<label for="rcmbCogProChk3">보건의료기관</label>
										<!-- Safe: 17.-->
										<input type="checkbox" id="rcmbCogProChk4" name="cogProChk" value="4" <c:if test="${reportDtl.ocReport.cogProChk == '4'}">checked</c:if>/>
										<label for="rcmbCogProChk4">기타</label>
										<!-- Safe: 18.-->
										<input type="text" id="rtxtCogProRmk" name="cogProRmk" value="<c:out value='${reportDtl.ocReport.cogProRmk}'/>" style="width:22.7em;"/>
									</td>
								</tr>
								<tr>
									<th class="search_th_c">인지개요</th>
									<td class="search_td">
										<!-- Safe: 19.-->
										<input type="text" id="rtxtCogProSumry" name="cogProSumry" value="<c:out value='${reportDtl.ocReport.cogProSumry}'/>" style="width:47em;" maxlength="1500"/>
									</td>
								</tr>				
							</c:when>
							<c:otherwise>
								<tr>
									<th class="search_th_c">인지일시</th>
									<td class="search_td">
										<!-- Safe: 20.-->
										<abs:Calendar calId="rcalNotifyDe" name="notifyDe" type="img" readonly="true" refEnId="currentDay" value="${reportDtl.resultDtl.notifyDe}"/>
										<select id="rcalNotifyTime" name="notifyTime">
											<c:forEach begin="0" end="24" varStatus="status">
												<!-- Safe: 21.-->
				                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" 
													<!-- Safe: 22.-->
													<c:if test="${reportDtl.resultDtl.notifyTime eq status.index }"> selected="selected"</c:if>>
													<!-- Safe: 23.-->
				                                    <fmt:formatNumber pattern="00" value="${status.index}" />
				                                </option>
				                            </c:forEach>
										</select> 시
										<select id="rcalNotifyMin" name="notifyMin">
											<c:forEach begin="0" end="24" varStatus="status">
												<!-- Safe: 24.-->
				                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" 
													<!-- Safe: 25.-->
													<c:if test="${reportDtl.resultDtl.notifyMin eq status.index }"> selected="selected"</c:if>>
													<!-- Safe: 26.-->	
				                                    <fmt:formatNumber pattern="00" value="${status.index}" />
				                                </option>
				                            </c:forEach>
										</select> 분
									</td>
								</tr>
								<tr>
									<th class="search_th_c">인지경위</th>
									<td class="search_td">
										<!-- Safe: 27.-->
										<input type="checkbox" id="rcmbCogProChk1" name="cogProChk" value="1" <c:if test="${reportDtl.resultDtl.cogProChk == '1'}">checked</c:if>/>
										<label for="rcmbCogProChk1">자체인지</label>
										<!-- Safe: 28.-->
										<input type="checkbox" id="rcmbCogProChk2" name="cogProChk" value="2" <c:if test="${reportDtl.resultDtl.cogProChk == '2'}">checked</c:if>/>
										<label for="rcmbCogProChk2">환자 보호자</label>
										<!-- Safe: 29.-->
										<input type="checkbox" id="rcmbCogProChk3" name="cogProChk" value="3" <c:if test="${reportDtl.resultDtl.cogProChk == '3'}">checked</c:if>/>
										<label for="rcmbCogProChk3">보건의료기관</label>
										<!-- Safe: 30.-->
										<input type="checkbox" id="rcmbCogProChk4" name="cogProChk" value="4" <c:if test="${reportDtl.resultDtl.cogProChk == '4'}">checked</c:if>/>
										<label for="rcmbCogProChk4">기타</label>
										<!-- Safe: 31.-->
										<input type="text" id="rtxtCogProRmk" name="cogProRmk" value="<c:out value='${reportDtl.resultDtl.cogProRmk}'/>" style="width:22.7em;"/>
									</td>
								</tr>
								<tr>
									<th class="search_th_c">인지개요</th>
									<td class="search_td">
										<!-- Safe: 32.-->
										<input type="text" id="rtxtCogProSumry" name="cogProSumry" value="<c:out value='${reportDtl.resultDtl.cogProSumry}'/>" style="width:47em;" maxlength="1500"/>
									</td>
								</tr>
							</c:otherwise>
						</c:choose>						
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
										<c:choose>
											<!-- Safe: -->
											<c:when test="${empty reportDtl.resultDtl}"><!-- 결과보고서 최초등록인 경우 -->
												<tr>
			                                          <th class="search_th_c" rowspan="3">발생지역<span class="f_r01 bold"> *</span></th>
			                                          <th class="search_th_c">우편번호</th>
			                                          <td class="search_td" colspan="6">
															<!-- Safe: 33.-->
			                                                <input type="text" id="rtxtZipcode" name="zipcode" value="<c:out value='${reportDtl.ocReport.zipcode}'/>" style="width: 60px;" maxlength="10" readonly="readonly" required="true" msg="발생지역을 ">
			                                              <!-- <input type="text" id="rtxtZipcode" name="zipcode" value="28165" style="width: 60px;" maxlength="10" readonly="readonly"> -->
			                                              <input type="button" id="pbtnSearchRdnmadrPest" class="input_btn" value="검색">
			                                          </td>
			                                      </tr>
			                                      <tr>
			                                          <th class="search_th_c">지번</th>
			                                          <td class="search_td" colspan="6">
															<!-- Safe: 34.-->
			                                                <input type="text" id="rtxtJiAddr1" name="jiAddr1" value="<c:out value='${reportDtl.ocReport.jiAddr1}'/>" style="width: 40%;" readonly="readonly">
															<!-- Safe: 35.-->
			                                                <input type="text" id="rtxtJiAddr2" name="jiAddr2" value="<c:out value='${reportDtl.ocReport.jiAddr2}'/>" style="width: 20%;" maxlength="65">
			                                              <!-- <input type="text" id="rtxtJiAddr1" name="jiAddr1" value="충청북도 청주시 흥덕구 오송읍 연제리" style="width: 40%;" readonly="readonly">
			                                              <input type="text" id="rtxtJiAddr2" name="jiAddr2" value="715" style="width: 20%;" maxlength="65"> -->
			                                          </td>
			                                      </tr>
			                                      <tr>
			                                          <th class="search_th_c">도로명</th>
			                                          <td class="search_td" colspan="6">
															<!-- Safe: 36.-->
			                                                <input type="text" id="rtxtDoAddr1" name="doAddr1" value="<c:out value='${reportDtl.ocReport.doAddr1}'/>" style="width: 40%;" readonly="readonly">
															<!-- Safe: 37.-->
			                                                <input type="text" id="rtxtDoAddr2" name="doAddr2" value="<c:out value='${reportDtl.ocReport.doAddr2}'/>" style="width: 40%;" maxlength="65">
															<!-- Safe: 38.-->
			                                                <input type="text" id="rtxtDoAddr3" name="doAddr3" value="<c:out value='${reportDtl.ocReport.doAddr3}'/>" style="width: 12%;" readonly="readonly">
															<!-- Safe: 39.-->
			                                                <input type="hidden" id="phidDoNo" name="doNo" value="<c:out value='${reportDtl.ocReport.doNo}'/>">
			                                      <%--         <input type="text" id="rtxtDoAddr1" name="doAddr1" value="충청북도 청주시 흥덕구 오송읍 오송생명로" style="width: 40%;" readonly="readonly">
			                                              <input type="text" id="rtxtDoAddr2" name="doAddr2" value="186" style="width: 40%;" maxlength="65">
			                                              <input type="text" id="rtxtDoAddr3" name="doAddr3" value="" style="width: 12%;" readonly="readonly">
														  <!-- Safe: -->
			                                              <input type="hidden" id="phidDoNo" name="doNo" value="<c:out value='${reportDtl.ocReport.doNo}'/>"> --%>
			                                          </td>
			                                      </tr>
												<tr>
													<th class="search_th_c" colspan="2">발생장소(집단명)</th>
													<td class="search_td" colspan="6">
														<!-- Safe: 40.-->
								                        <input type="text" id="rtxtOccrrncPlace" name="occrrncPlace" value="<c:out value='${reportDtl.ocReport.occrrncPlace}'/>" style="width: 50%;" maxlength="25">
													</td>
												</tr>
												<tr>
								                    <th class="search_th_c" rowspan="2" colspan="2">발생규모</th>
								                    <th class="search_th_c" rowspan="2">유증상자</th>
								                    <td class="search_td" rowspan="2">
														<!-- Safe: 41.-->
								                        <input type="text" id="rtxtExSymptom" name="exSymptom" value="<c:out value='${reportDtl.ocReport.exSymptom}'/>" class="align_r inputNum"  style="width: 30%;" maxlength="10"/> 명
								                    </td>
								                    <th class="search_th_c" rowspan="2">신고사례</th>
								                    <td class="search_td" rowspan="2">
														<!-- Safe: 42.-->
								                        <input type="text" id="rtxtExReprtSymptom" name="exReprtSymptom" value="<c:out value='${reportDtl.ocReport.exReprtSymptom}'/>" class="align_r inputNum" style="width: 30%;" maxlength="10"/> 명
								                    </td>
								                    <th class="search_th_c">확진사례</th>
								                    <td class="search_td">
														<!-- Safe: 43.-->
								                        <input type="text" id="rtxtExConfirmSymptom" name="exConfirmSymptom" value="<c:out value='${reportDtl.ocReport.exConfirmSymptom}'/>" class="align_r inputNum" style="width: 30%;" maxlength="10"/> 명
								                    </td>
								                </tr>
								                <tr>
								                    <th class="search_th_c">의심사례</th>
								                    <td class="search_td">
														<!-- Safe: 44.-->
								                        <input type="text" id="rtxtExSuspSymptom" name="exSuspSymptom" value="<c:out value='${reportDtl.ocReport.exSuspSymptom}'/>" class="align_r inputNum" style="width: 30%;" maxlength="10"/> 명
								                    </td>
								                </tr>
								                <tr>
								                    <th class="search_th_c" rowspan="6">최초사례</th>
								                    <th class="search_th_c">성별</th>
								                    <td class="search_td" colspan="3">
														<!-- Safe: 45.-->
								                        <input type="checkbox" id="rcmbFcSex1" name="fcSex" value="1" <c:if test="${reportDtl.ocReport.fcSex == '1'}">checked</c:if>/>
								                        <label for="rcmbFcSex1">남</label>
														<!-- Safe: 46.-->
								                        <input type="checkbox" id="rcmbFcSex2" name="fcSex" value="2" <c:if test="${reportDtl.ocReport.fcSex == '2'}">checked</c:if>/>
								                        <label for="rcmbFcSex2">여</label>
								                    </td>
								                    <th class="search_th_c">연령</th>
								                    <td class="search_td" colspan="2">
														<!-- Safe: 47.-->
								                        <input type="text" id="rtxtFcAge" name="fcAge" value="<c:out value='${reportDtl.ocReport.fcAge}'/>" class="align_r inputNum" style="width: 15%;" maxlength="10"/> 세
								                    </td>
								                </tr>
								                <tr>
								                    <th class="search_th_c">증상발생일</th>
								                    <td class="search_td" colspan="3">
														<!-- Safe: 48.-->
								                        <abs:Calendar calId="rcalFcOccrDe" name="fcOccrDe" value="${reportDtl.ocReport.fcOccrDe}" type="img" readonly="true" refEnId="currentDay"/>
														<select id="rcalFcOccrTime" name="fcOccrTime">
															<c:forEach begin="0" end="24" varStatus="status">
																<!-- Safe: -->
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" 
																<!-- Safe: -->
																<c:if test="${reportDtl.ocReport.fcOccrTime eq status.index }"> selected="selected"</c:if>>
																	<!-- Safe: -->
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcalFcOccrMin" name="fcOccrMin">
															<c:forEach begin="0" end="24" varStatus="status">
																<!-- Safe: -->
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" 
																<!-- Safe: -->
																<c:if test="${reportDtl.ocReport.fcOccrMin eq status.index }"> selected="selected"</c:if>>
																	<!-- Safe: -->
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 분
								                    </td>
								                    <th class="search_th_c">증 상</th>
								                    <td class="search_td" colspan="2">
														<!-- Safe: -->
								                    	<input type="text" id="rtxtFcMajSymptom" name="fcMajSymptom" value="<c:out value='${reportDtl.ocReport.fcMajSymptom}'/>" style="width: 80%;" maxlength="100">
								                    </td>
								                </tr>
								                <tr>
								                    <th class="search_th_c">환자구분</th>
								                    <td class="search_td" colspan="3">
														<!-- Safe: -->
								                        <input type="checkbox" id="rcmbFcPatiClassi1" name="fcPatiClassi" value="1" <c:if test="${reportDtl.ocReport.fcPatiClassi == '1'}">checked</c:if>/>
														<label for="rcmbFcPatiClassi1">환자</label>
														<!-- Safe: -->
														<input type="checkbox" id="rcmbFcPatiClassi2" name="fcPatiClassi" value="2" <c:if test="${reportDtl.ocReport.fcPatiClassi == '2'}">checked</c:if>/>
														<label for="rcmbFcPatiClassi2">의사환자</label>
								                    </td>
								                    <th class="search_th_c">신고일</th>
								                    <td class="search_td" colspan="2">
														<!-- Safe: -->
								                        <abs:Calendar calId="rcalFcNotifyDe" name="fcNotifyDe" value="${reportDtl.ocReport.fcNotifyDe}" type="img" readonly="true" refEnId="currentDay"/>
														<select id="rcalFcNotifyTime" name="fcNotifyTime">
															<c:forEach begin="0" end="24" varStatus="status">
																<!-- Safe: -->
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" 
																	<!-- Safe: -->
																	<c:if test="${reportDtl.ocReport.fcNotifyTime eq status.index }"> selected="selected"</c:if>>
																	<!-- Safe: -->
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcalFcNotifyMin" name="fcNotifyMin">
															<c:forEach begin="0" end="24" varStatus="status">
																<!-- Safe: -->
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" 
																	<!-- Safe: -->
																	<c:if test="${reportDtl.ocReport.fcNotifyMin eq status.index }"> selected="selected"</c:if>>
																	<!-- Safe: -->
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
															<!-- Safe: -->
														<abs:Calendar calId="rcalFcThroatInspctDe" name="fcThroatInspctDe" value="${reportDtl.ocReport.fcThroatInspctDe}" type="img" readonly="true" refEnId="currentDay"/>
														<select id="rcalFcThroatInspctTime" name="fcThroatInspctTime">
															<c:forEach begin="0" end="24" varStatus="status">
																<!-- Safe: -->
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcThroatInspctTime eq status.index }"> selected="selected"</c:if>>
																	<!-- Safe: -->
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcalFcThroatInspctMin" name="fcThroatInspctMin">
															<c:forEach begin="0" end="24" varStatus="status">
																<!-- Safe: -->
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcThroatInspctMin eq status.index }"> selected="selected"</c:if>>
																	<!-- Safe: -->
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 분
														<br/>
														결과 : 
														<!-- Safe: -->
														<input type="checkbox" id="rcmbFcThroatInspctChk1" name="fcThroatInspctChk" value="1" <c:if test="${reportDtl.ocReport.fcThroatInspctChk == '1'}">checked</c:if>/>
														<label for="rcmbFcThroatInspctChk1">양성</label>
														<!-- Safe: -->
														<input type="checkbox" id="rcmbFcThroatInspctChk2" name="fcThroatInspctChk" value="2" <c:if test="${reportDtl.ocReport.fcThroatInspctChk == '2'}">checked</c:if>/>
														<label for="rcmbFcThroatInspctChk2">음성</label>
														<!-- Safe: -->
														<input type="checkbox" id="rcmbFcThroatInspctChk3" name="fcThroatInspctChk" value="3" <c:if test="${reportDtl.ocReport.fcThroatInspctChk == '3'}">checked</c:if>/>
														<label for="rcmbFcThroatInspctChk3">미시행</label>
								                    </td>
								                </tr>
								                <tr>
								                	<th class="search_th_c" colspan="2">신속항원검출검사</th>
								                    <td class="search_td" colspan="4">
						                        		시행일:
														<!-- Safe: -->
														<abs:Calendar calId="rcmbFcRadtDe" name="fcRadtDe" type="img" value="${reportDtl.ocReport.fcRadtDe}" readonly="true" refEnId="currentDay"/>
														<select id="rcmbFcRadtTime" name="fcRadtTime">
															<c:forEach begin="0" end="24" varStatus="status">
																<!-- Safe: -->
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcRadtTime eq status.index }"> selected="selected"</c:if>>
																	<!-- Safe: -->
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcmbFcRadtMin" name="fcRadtMin">
															<c:forEach begin="0" end="24" varStatus="status">
																<!-- Safe: -->
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcRadtMin eq status.index }"> selected="selected"</c:if>>
																	<!-- Safe: -->
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 분
														<br/>
														결과 :
															<!-- Safe: -->
														<input type="checkbox" id="rcmbFcRadtChk1" name="fcRadtChk" value="1" <c:if test="${reportDtl.ocReport.fcRadtChk == '1'}">checked</c:if>/>
														<label for="rcmbFcRadtChk1">양성</label>
														<!-- Safe: -->
														<input type="checkbox" id="rcmbFcRadtChk2" name="fcRadtChk" value="2" <c:if test="${reportDtl.ocReport.fcRadtChk == '2'}">checked</c:if>/>
														<label for="rcmbFcRadtChk2">음성</label>
														<!-- Safe: -->
														<input type="checkbox" id="rcmbFcRadtChk3" name="fcRadtChk" value="3" <c:if test="${reportDtl.ocReport.fcRadtChk == '3'}">checked</c:if>/>
														<label for="rcmbFcRadtChk3">미시행</label>
								                    </td>
								                </tr>
								                <tr>
								                	<th class="search_th_c" colspan="2">기타 검사</th>
								                    <td class="search_td" colspan="4">
						                        		시행일:
														<!-- Safe: -->
														<abs:Calendar calId="rcmbFcEtcDe" name="fcEtcDe" type="img" value="${reportDtl.ocReport.fcEtcDe}" readonly="true" refEnId="currentDay"/>
														<select id="rcmbFcEtcTime" name="fcEtcTime">
															<c:forEach begin="0" end="24" varStatus="status">
																<!-- Safe: -->
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcEtcTime eq status.index }"> selected="selected"</c:if>>
																	<!-- Safe: -->
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcmbFcEtcMin" name="fcEtcMin">
															<c:forEach begin="0" end="24" varStatus="status">
																<!-- Safe: -->
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcEtcMin eq status.index }"> selected="selected"</c:if>>
																	<!-- Safe: -->
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 분
														<br/>
														결과 : 
														<!-- Safe: -->
														<input type="checkbox" id="rcmbFcEtcChk1" name="fcEtcChk" value="1" <c:if test="${reportDtl.ocReport.fcEtcChk == '1'}">checked</c:if>/>
														<label for="rcmbFcEtcChk1">양성</label>
														<!-- Safe: -->
														<input type="checkbox" id="rcmbFcEtcChk2" name="fcEtcChk" value="2" <c:if test="${reportDtl.ocReport.fcEtcChk == '2'}">checked</c:if>/>
														<label for="rcmbFcEtcChk2">음성</label>
														<!-- Safe: -->
														<input type="checkbox" id="rcmbFcEtcChk3" name="fcEtcChk" value="3" <c:if test="${reportDtl.ocReport.fcEtcChk == '3'}">checked</c:if>/>
														<label for="rcmbFcEtcChk3">미시행</label>
								                    </td>
								                </tr>
								                <tr>
								                	<th class="search_th_c" colspan="2">예방･관리 시작일</th>
								                    <td class="search_td" colspan="6">
														<!-- Safe: -->
								                        <abs:Calendar calId="rcmbFcPrevCrDe" name="fcPrevCrDe" value="${reportDtl.ocReport.fcPrevCrDe}" type="img" readonly="true" refEnId="currentDay"/>
														<select id="rcmbFcPrevCrTime" name="fcPrevCrTime">
															<c:forEach begin="0" end="24" varStatus="status">
																<!-- Safe: -->
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcPrevCrTime eq status.index }"> selected="selected"</c:if>>
																	<!-- Safe: -->
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcmbFcPrevCrMin" name="fcPrevCrMin">
															<c:forEach begin="0" end="24" varStatus="status">
																<!-- Safe: -->
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.ocReport.fcPrevCrMin eq status.index }"> selected="selected"</c:if>>
																	<!-- Safe: -->
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 분
								                    </td>
								                </tr>
								                <tr>
								                	<th class="search_th_c" colspan="2">마지막사례발생일</th>
								                    <td class="search_td" colspan="2">
														<!-- Safe: -->
								                        <abs:Calendar calId="rcmbFcLastSymptomCsDe" name="fcLastSymptomCsDe" value="${reportDtl.resultDtl.fcLastSymptomCsDe}" type="img" readonly="true" refEnId="currentDay"/>
														<select id="rcmbFcLastSymptomCsTime" name="fcLastSymptomCsTime">
															<c:forEach begin="0" end="24" varStatus="status">
																<!-- Safe: -->
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcLastSymptomCsTime eq status.index }"> selected="selected"</c:if>>
																	<!-- Safe: -->
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcmbFcLastSymptomCsMin" name="fcLastSymptomCsMin">
															<c:forEach begin="0" end="24" varStatus="status">
																<!-- Safe: -->
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcLastSymptomCsMin eq status.index }"> selected="selected"</c:if>>
																	<!-- Safe: -->
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 분
								                    </td>
								                    <th class="search_th_c" colspan="2">관리종료일</th>
								                    <td class="search_td" colspan="2">
								                        <abs:Calendar calId="rcmbFcLastMngtEndDe" name="fcLastMngtEndDe" value="${reportDtl.resultDtl.fcLastMngtEndDe}" type="img" readonly="true" refEnId="currentDay"/>
														<select id="rcmbFcLastMngtEndTime" name="fcLastMngtEndTime">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcLastMngtEndTime eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcmbFcLastMngtEndMin" name="fcLastMngtEndMin">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcLastMngtEndMin eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 분
								                    </td>
								                </tr>
								                <tr>
								                	<th class="search_th_c" colspan="2">관리종료일</th>
								                    <td class="search_td" colspan="6">
								                        <abs:Calendar calId="rcmbFcMngtEndDe" name="fcMngtEndDe" value="${reportDtl.resultDtl.fcMngtEndDe}" type="img" readonly="true" refEnId="currentDay"/>
														<select id="rcmbFcMngtEndTime" name="fcMngtEndTime">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcMngtEndTime eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcmbFcMngtEndMin" name="fcMngtEndMin">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcMngtEndMin eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 분
								                    </td>
								                </tr>
											</c:when>
											<c:otherwise>
												<tr>
			                                          <th class="search_th_c" rowspan="3">발생지역<span class="f_r01 bold"> *</span></th>
			                                          <th class="search_th_c">우편번호</th>
			                                          <td class="search_td" colspan="6">
			<%--                                                 <input type="text" id="rtxtZipcode" name="zipcode" value="<c:out value='${reportDtl.resultDtl.zipcode}'/>" style="width: 60px;" maxlength="10" readonly="readonly" required="true" msg="발생지역을 "> --%>
			                                              <input type="text" id="rtxtZipcode" name="zipcode" value="28165" style="width: 60px;" maxlength="10" readonly="readonly">
			                                              <input type="button" id="pbtnSearchRdnmadrPest" class="input_btn" value="검색">
			                                          </td>
			                                      </tr>
			                                      <tr>
			                                          <th class="search_th_c">지번</th>
			                                          <td class="search_td" colspan="6">
			<%--                                                 <input type="text" id="rtxtJiAddr1" name="jiAddr1" value="<c:out value='${reportDtl.resultDtl.jiAddr1}'/>" style="width: 40%;" readonly="readonly"> --%>
			<%--                                                 <input type="text" id="rtxtJiAddr2" name="jiAddr2" value="<c:out value='${reportDtl.resultDtl.jiAddr2}'/>" style="width: 20%;" maxlength="65"> --%>
			                                              <input type="text" id="rtxtJiAddr1" name="jiAddr1" value="충청북도 청주시 흥덕구 오송읍 연제리" style="width: 40%;" readonly="readonly">
			                                              <input type="text" id="rtxtJiAddr2" name="jiAddr2" value="715" style="width: 20%;" maxlength="65">
			                                          </td>
			                                      </tr>
			                                      <tr>
			                                          <th class="search_th_c">도로명</th>
			                                          <td class="search_td" colspan="6">
			<%--                                                 <input type="text" id="rtxtDoAddr1" name="doAddr1" value="<c:out value='${reportDtl.resultDtl.doAddr1}'/>" style="width: 40%;" readonly="readonly"> --%>
			<%--                                                 <input type="text" id="rtxtDoAddr2" name="doAddr2" value="<c:out value='${reportDtl.resultDtl.doAddr2}'/>" style="width: 40%;" maxlength="65"> --%>
			<%--                                                 <input type="text" id="rtxtDoAddr3" name="doAddr3" value="<c:out value='${reportDtl.resultDtl.doAddr3}'/>" style="width: 12%;" readonly="readonly"> --%>
			<%--                                                 <input type="hidden" id="phidDoNo" name="doNo" value="<c:out value='${reportDtl.resultDtl.doNo}'/>"> --%>
			                                              <input type="text" id="rtxtDoAddr1" name="doAddr1" value="충청북도 청주시 흥덕구 오송읍 오송생명로" style="width: 40%;" readonly="readonly">
			                                              <input type="text" id="rtxtDoAddr2" name="doAddr2" value="186" style="width: 40%;" maxlength="65">
			                                              <input type="text" id="rtxtDoAddr3" name="doAddr3" value="" style="width: 12%;" readonly="readonly">
			                                              <input type="hidden" id="phidDoNo" name="doNo" value="<c:out value='${reportDtl.resultDtl.doNo}'/>">
			                                          </td>
			                                      </tr>
												<tr>
													<th class="search_th_c" colspan="2">발생장소(집단명)</th>
													<td class="search_td" colspan="6">
								                        <input type="text" id="rtxtOccrrncPlace" name="occrrncPlace" value="<c:out value='${reportDtl.resultDtl.occrrncPlace}'/>" style="width: 50%;" maxlength="25">
													</td>
												</tr>
												<tr>
								                    <th class="search_th_c" rowspan="2" colspan="2">발생규모</th>
								                    <th class="search_th_c" rowspan="2">유증상자</th>
								                    <td class="search_td" rowspan="2">
								                        <input type="text" id="rtxtExSymptom" name="exSymptom" value="<c:out value='${reportDtl.resultDtl.exSymptom}'/>" class="align_r inputNum"  style="width: 30%;" maxlength="10"/> 명
								                    </td>
								                    <th class="search_th_c" rowspan="2">신고사례</th>
								                    <td class="search_td" rowspan="2">
								                        <input type="text" id="rtxtExReprtSymptom" name="exReprtSymptom" value="<c:out value='${reportDtl.resultDtl.exReprtSymptom}'/>" class="align_r inputNum" style="width: 30%;" maxlength="10"/> 명
								                    </td>
								                    <th class="search_th_c">확진사례</th>
								                    <td class="search_td">
								                        <input type="text" id="rtxtExConfirmSymptom" name="exConfirmSymptom" value="<c:out value='${reportDtl.resultDtl.exConfirmSymptom}'/>" class="align_r inputNum" style="width: 30%;" maxlength="10"/> 명
								                    </td>
								                </tr>
								                <tr>
								                    <th class="search_th_c">의심사례</th>
								                    <td class="search_td">
								                        <input type="text" id="rtxtExSuspSymptom" name="exSuspSymptom" value="<c:out value='${reportDtl.resultDtl.exSuspSymptom}'/>" class="align_r inputNum" style="width: 30%;" maxlength="10"/> 명
								                    </td>
								                </tr>
								                <tr>
								                    <th class="search_th_c" rowspan="6">최초사례</th>
								                    <th class="search_th_c">성별</th>
								                    <td class="search_td" colspan="3">
								                        <input type="checkbox" id="rcmbFcSex1" name="fcSex" value="1" <c:if test="${reportDtl.resultDtl.fcSex == '1'}">checked</c:if>/>
								                        <label for="rcmbFcSex1">남</label>
								                        <input type="checkbox" id="rcmbFcSex2" name="fcSex" value="2" <c:if test="${reportDtl.resultDtl.fcSex == '2'}">checked</c:if>/>
								                        <label for="rcmbFcSex2">여</label>
								                    </td>
								                    <th class="search_th_c">연령</th>
								                    <td class="search_td" colspan="2">
								                        <input type="text" id="rtxtFcAge" name="fcAge" value="<c:out value='${reportDtl.resultDtl.fcAge}'/>" class="align_r inputNum" style="width: 15%;" maxlength="10"/> 세
								                    </td>
								                </tr>
								                <tr>
								                    <th class="search_th_c">증상발생일</th>
								                    <td class="search_td" colspan="3">
								                        <abs:Calendar calId="rcalFcOccrDe" name="fcOccrDe" value="${reportDtl.resultDtl.fcOccrDe}" type="img" readonly="true" refEnId="currentDay"/>
														<select id="rcalFcOccrTime" name="fcOccrTime">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcOccrTime eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcalFcOccrMin" name="fcOccrMin">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcOccrMin eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 분
								                    </td>
								                    <th class="search_th_c">증 상</th>
								                    <td class="search_td" colspan="2">
								                    	<input type="text" id="rtxtFcMajSymptom" name="fcMajSymptom" value="<c:out value='${reportDtl.resultDtl.fcMajSymptom}'/>" style="width: 80%;" maxlength="100">
								                    </td>
								                </tr>
								                <tr>
								                    <th class="search_th_c">환자구분</th>
								                    <td class="search_td" colspan="3">
								                        <input type="checkbox" id="rcmbFcPatiClassi1" name="fcPatiClassi" value="1" <c:if test="${reportDtl.resultDtl.fcPatiClassi == '1'}">checked</c:if>/>
														<label for="rcmbFcPatiClassi1">환자</label>
														<input type="checkbox" id="rcmbFcPatiClassi2" name="fcPatiClassi" value="2" <c:if test="${reportDtl.resultDtl.fcPatiClassi == '2'}">checked</c:if>/>
														<label for="rcmbFcPatiClassi2">의사환자</label>
								                    </td>
								                    <th class="search_th_c">신고일</th>
								                    <td class="search_td" colspan="2">
								                        <abs:Calendar calId="rcalFcNotifyDe" name="fcNotifyDe" value="${reportDtl.resultDtl.fcNotifyDe}" type="img" readonly="true" refEnId="currentDay"/>
														<select id="rcalFcNotifyTime" name="fcNotifyTime">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcNotifyTime eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcalFcNotifyMin" name="fcNotifyMin">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcNotifyMin eq status.index }"> selected="selected"</c:if>>
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
														<abs:Calendar calId="rcalFcThroatInspctDe" name="fcThroatInspctDe" value="${reportDtl.resultDtl.fcThroatInspctDe}" type="img" readonly="true" refEnId="currentDay"/>
														<select id="rcalFcThroatInspctTime" name="fcThroatInspctTime">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcThroatInspctTime eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcalFcThroatInspctMin" name="fcThroatInspctMin">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcThroatInspctMin eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 분
														<br/>
														결과 : 
														<input type="checkbox" id="rcmbFcThroatInspctChk1" name="fcThroatInspctChk" value="1" <c:if test="${reportDtl.resultDtl.fcThroatInspctChk == '1'}">checked</c:if>/>
														<label for="rcmbFcThroatInspctChk1">양성</label>
														<input type="checkbox" id="rcmbFcThroatInspctChk2" name="fcThroatInspctChk" value="2" <c:if test="${reportDtl.resultDtl.fcThroatInspctChk == '2'}">checked</c:if>/>
														<label for="rcmbFcThroatInspctChk2">음성</label>
														<input type="checkbox" id="rcmbFcThroatInspctChk3" name="fcThroatInspctChk" value="3" <c:if test="${reportDtl.resultDtl.fcThroatInspctChk == '3'}">checked</c:if>/>
														<label for="rcmbFcThroatInspctChk3">미시행</label>
								                    </td>
								                </tr>
								                <tr>
								                	<th class="search_th_c" colspan="2">신속항원검출검사</th>
								                    <td class="search_td" colspan="4">
						                        		시행일:
														<abs:Calendar calId="rcmbFcRadtDe" name="fcRadtDe" type="img" value="${reportDtl.resultDtl.fcRadtDe}" readonly="true" refEnId="currentDay"/>
														<select id="rcmbFcRadtTime" name="fcRadtTime">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcRadtTime eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcmbFcRadtMin" name="fcRadtMin">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcRadtMin eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 분
														<br/>
														결과 : 
														<input type="checkbox" id="rcmbFcRadtChk1" name="fcRadtChk" value="1" <c:if test="${reportDtl.resultDtl.fcRadtChk == '1'}">checked</c:if>/>
														<label for="rcmbFcRadtChk1">양성</label>
														<input type="checkbox" id="rcmbFcRadtChk2" name="fcRadtChk" value="2" <c:if test="${reportDtl.resultDtl.fcRadtChk == '2'}">checked</c:if>/>
														<label for="rcmbFcRadtChk2">음성</label>
														<input type="checkbox" id="rcmbFcRadtChk3" name="fcRadtChk" value="3" <c:if test="${reportDtl.resultDtl.fcRadtChk == '3'}">checked</c:if>/>
														<label for="rcmbFcRadtChk3">미시행</label>
								                    </td>
								                </tr>
								                <tr>
								                	<th class="search_th_c" colspan="2">기타 검사</th>
								                    <td class="search_td" colspan="4">
						                        		시행일:
														<abs:Calendar calId="rcmbFcEtcDe" name="fcEtcDe" type="img" value="${reportDtl.resultDtl.fcEtcDe}" readonly="true" refEnId="currentDay"/>
														<select id="rcmbFcEtcTime" name="fcEtcTime">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcEtcTime eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcmbFcEtcMin" name="fcEtcMin">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcEtcMin eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 분
														<br/>
														결과 : 
														<input type="checkbox" id="rcmbFcEtcChk1" name="fcEtcChk" value="1" <c:if test="${reportDtl.resultDtl.fcEtcChk == '1'}">checked</c:if>/>
														<label for="rcmbFcEtcChk1">양성</label>
														<input type="checkbox" id="rcmbFcEtcChk2" name="fcEtcChk" value="2" <c:if test="${reportDtl.resultDtl.fcEtcChk == '2'}">checked</c:if>/>
														<label for="rcmbFcEtcChk2">음성</label>
														<input type="checkbox" id="rcmbFcEtcChk3" name="fcEtcChk" value="3" <c:if test="${reportDtl.resultDtl.fcEtcChk == '3'}">checked</c:if>/>
														<label for="rcmbFcEtcChk3">미시행</label>
								                    </td>
								                </tr>
								                <tr>
								                	<th class="search_th_c" colspan="2">예방･관리 시작일</th>
								                    <td class="search_td" colspan="6">
								                        <abs:Calendar calId="rcmbFcPrevCrDe" name="fcPrevCrDe" value="${reportDtl.resultDtl.fcPrevCrDe}" type="img" readonly="true" refEnId="currentDay"/>
														<select id="rcmbFcPrevCrTime" name="fcPrevCrTime">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcPrevCrTime eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcmbFcPrevCrMin" name="fcPrevCrMin">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcPrevCrMin eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 분
								                    </td>
								                </tr>
								                <tr>
								                	<th class="search_th_c" colspan="2">마지막사례발생일</th>
								                    <td class="search_td" colspan="2">
								                        <abs:Calendar calId="rcmbFcLastSymptomCsDe" name="fcLastSymptomCsDe" value="${reportDtl.resultDtl.fcLastSymptomCsDe}" type="img" readonly="true" refEnId="currentDay"/>
														<select id="rcmbFcLastSymptomCsTime" name="fcLastSymptomCsTime">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcLastSymptomCsTime eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcmbFcLastSymptomCsMin" name="fcLastSymptomCsMin">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcLastSymptomCsMin eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 분
								                    </td>
								                    <th class="search_th_c" colspan="2">관리종료일</th>
								                    <td class="search_td" colspan="2">
								                        <abs:Calendar calId="rcmbFcLastMngtEndDe" name="fcLastMngtEndDe" value="${reportDtl.resultDtl.fcLastMngtEndDe}" type="img" readonly="true" refEnId="currentDay"/>
														<select id="rcmbFcLastMngtEndTime" name="fcLastMngtEndTime">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcLastMngtEndTime eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcmbFcLastMngtEndMin" name="fcLastMngtEndMin">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcLastMngtEndMin eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 분
								                    </td>
								                </tr>
								                <tr>
								                	<th class="search_th_c" colspan="2">관리종료일</th>
								                    <td class="search_td" colspan="6">
								                        <abs:Calendar calId="rcmbFcMngtEndDe" name="fcMngtEndDe" value="${reportDtl.resultDtl.fcMngtEndDe}" type="img" readonly="true" refEnId="currentDay"/>
														<select id="rcmbFcMngtEndTime" name="fcMngtEndTime">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcMngtEndTime eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 시
														<select id="rcmbFcMngtEndMin" name="fcMngtEndMin">
															<c:forEach begin="0" end="24" varStatus="status">
								                                <option value="<fmt:formatNumber pattern="00" value="${status.index}" />" <c:if test="${reportDtl.resultDtl.fcMngtEndMin eq status.index }"> selected="selected"</c:if>>
								                                    <fmt:formatNumber pattern="00" value="${status.index}" />
								                                </option>
								                            </c:forEach>
														</select> 분
								                    </td>
								                </tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
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
				<c:choose>
					<c:when test="${empty reportDtl.resultDtl}"><!-- 결과보고서 최초등록인 경우 -->
						<tr>
							<td class="search_td">
								<input type="text" id="rtxtInptCs" name="inptCs" value="<c:out value='${reportDtl.ocReport.inptCs}'/>" style="width:96%; margin: 5px 0;"/>
		                    </td>
		                    <td class="search_td">
								<input type="text" id="rtxtCompliCs" name="compliCs" value="<c:out value='${reportDtl.ocReport.compliCs}'/>" style="width:96%; margin: 5px 0;"/>
		                    </td>    
		                    <td class="search_td">
								<input type="text" id="rtxtHeadDupCs" name="headDupCs" value="<c:out value='${reportDtl.ocReport.headDupCs}'/>" style="width:96%; margin: 5px 0;"/>
		                    </td>
		                    <td class="search_td">
								<input type="text" id="rtxtOthersCs" name="othersCs" value="<c:out value='${reportDtl.ocReport.othersCs}'/>" style="width:96%; margin: 5px 0;"/>
		                    </td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td class="search_td">
								<input type="text" id="rtxtInptCs" name="inptCs" value="<c:out value='${reportDtl.resultDtl.inptCs}'/>" style="width:96%; margin: 5px 0;"/>
		                    </td>
		                    <td class="search_td">
								<input type="text" id="rtxtCompliCs" name="compliCs" value="<c:out value='${reportDtl.resultDtl.compliCs}'/>" style="width:96%; margin: 5px 0;"/>
		                    </td>    
		                    <td class="search_td">
								<input type="text" id="rtxtHeadDupCs" name="headDupCs" value="<c:out value='${reportDtl.resultDtl.headDupCs}'/>" style="width:96%; margin: 5px 0;"/>
		                    </td>
		                    <td class="search_td">
								<input type="text" id="rtxtOthersCs" name="othersCs" value="<c:out value='${reportDtl.resultDtl.othersCs}'/>" style="width:96%; margin: 5px 0;"/>
		                    </td>
						</tr>
					</c:otherwise>
				</c:choose>		
				
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
	<h4 class="p_b10">조치사항 및 결과</h4>
</div>
<table id="rcmbArTable" width="100%" border="0" cellspacing="0" cellpadding="0">
<tbody>
	<tr>
	<td class="search_tbl">
		<table id="" class="search_tbl_box" width="100%" border="0"
			cellspacing="0" cellpadding="0">
			<tbody>
				<input type="checkbox" id="rcmbArChk1" name="arChk" class="ar" value="1"/>
				<label for="rcmbArChk1">성홍열 환자는 항생제 치료 시작 후 최소 24시간까지 등교(등원) 중지 교육</label><br/>
				<input type="checkbox" id="rcmbArChk2" name="arChk" class="ar" value="2"/>
				<label for="rcmbArChk2">손씻기 강조 (등교(등원) 시, 화장실 다녀온 후, 놀이 후, 식사 전･후, 하교(하원) 시)</label><br/>
				<input type="checkbox" id="rcmbArChk3" name="arChk" class="ar" value="3"/>
				<label for="rcmbArChk3">학부모와 직원들에게 성홍열 발생 주의 안내문 발송</label><br/>
				<input type="checkbox" id="rcmbArChk4" name="arChk" class="ar" value="4"/>
				<label for="rcmbArChk4">기타 조치사항 및 결과 기술</label><br/>
				<textarea id="rtxtArRmk" name="arRmk" rows="4" style="width: 99%; margin: 5px 0;" maxlength="1300">
				<!-- Safe: -->
				<c:out value='${reportDtl.resultDtl.arRmk}'/></textarea>
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
               <table id="rtbFileListResultReport" class="search_tbl_box" width="100%" border="0" cellspacing="0" cellpadding="0">
                   <colgroup>
                       <col width="20%">
                       <col width="*">
                   </colgroup>
                   <tbody>
				   <!-- Safe: -->
                   <c:if test="${fn:length(reportDtl.fileListResultReport) == 0}">
                       <tr>
                           <th class="search_th_c">첨부파일
                               <input type="button" id="rbtnAddAtchFileResultReport" class="input_btn_plus" />
                               <input type="hidden" id="rhdnFileKeyResultReport">
                           </th>
                           <td class="search_td" style="height: 20px;">
                           </td>
                       </tr>
                   </c:if>
				   <!-- Safe: -->
                   <c:if test="${fn:length(reportDtl.fileListResultReport) > 0}">
						<!-- Safe: -->
                       <c:forEach var="fileListResultReport" items="${reportDtl.fileListResultReport }" varStatus="status">
                           <tr>
							<!-- Safe: -->
                               <c:if test="${ status.index eq 0 }">
									<!-- Safe: -->
                                   <th class="search_th_c" rowspan="${fn:length(reportDtl.fileListResultReport)}">첨부파일
                                       <input type="button" id="rbtnAddAtchFileResultReport" class="input_btn_plus" />
									   <!-- Flaw: -->
                                       <input type="hidden" id="rhdnFileKeyResultReport" value="${fileListResultReport.FILE_KEY }">
                                   </th>
                               </c:if>
                               <td class="search_td" style="height: 20px;">
										<!-- Flaw: -->
                                       ${fileListResultReport.FILE_NM }
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