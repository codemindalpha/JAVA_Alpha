<%@ page contentType="text/html;charset=UTF-8"%>
<%
	Integer firstCa  = 0;
	Integer secondCa = 0;
	Integer thirdCa  = 0;
	Integer caId  	 = 0;
	String firstCaStr = "";
	if(request.getParameter("sFirstCa")!=null){
		firstCaStr = request.getParameter("sFirstCa");
		firstCa 	= Integer.parseInt(firstCaStr);
	}
	if(request.getParameter("sSecondCa")!=null){
		secondCa 	= Integer.parseInt(request.getParameter("sSecondCa"));
	}
	if(request.getParameter("sThirdCa")!=null){
		thirdCa 	= Integer.parseInt(request.getParameter("sThirdCa"));
	}
	if(request.getParameter("caId")!=null){
		caId 		= Integer.parseInt(request.getParameter("caId"));
	}
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>

<body>
<!-- FLAW: XSS -->
<%=firstCaStr%>
<table class="UItable list" summary="이미지셋 파일목록입니다">
					<caption class="h2 hide">이미지셋 파일목록</caption>
					<colgroup>
						<col width="*"></col>
						<col width="100"></col>
					</colgroup>
					<thead>
						<tr>
							<th>파 일 명</th>
							<th>미리보기</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<!-- Safe: 4 safes -->
							<td class="noPd" colspan="2"><iframe marginwidth="0"
									marginheight="0" name="imglist" class="W100p"
									style="height: 397px;" frameborder="0"
									src='imgList.action?caId=<%=caId %>&firstCa=<%=firstCa %>&secondCa=<%=secondCa %>&thirdCa=<%=thirdCa %>'></iframe>
							</td>
						</tr>
					</tbody>
				</table>
</body>
</html>