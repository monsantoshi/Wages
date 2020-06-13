<%@ page language="java" contentType="text/html;charset=TIS-620" pageEncoding="tis-620"%>
<%@ taglib prefix="tiles" uri="/WEB-INF/tld/struts-tiles-el.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<tiles:importAttribute ignore="true"/>
<jsp:useBean id="now" class="java.util.Date" />
<HTML>
<HEAD>
<TITLE>บริษัท ไปรษณีย์ไทย จำกัด</TITLE>

<link rel="stylesheet" href="stylesheet/tableStyle.css" type="text/css">
<link rel="stylesheet" href="stylesheet/style.css" type="text/css">
<link rel="stylesheet" href="stylesheet/inputStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" media="all" href="./script/skins/aqua/theme.css" title="Aqua" />
<script language="javascript" src="script/tree_menu.js"></script>
<script language="JavaScript" type="text/javascript" src="script/pnpScript.js"></script>
<script language="JavaScript" type="text/javascript" src="script/gridScript.js"></script>
<script type="text/javascript" src="script/calendar.js"></script>
<script type="text/javascript" src="script/lang/calendar-en.js"></script>
<script type="text/javascript" src="script/calendar-setup.js"></script>
</HEAD>
<script language="JavaScript">
<!--
	//setTimeout("alertMe()", 900000); // 15 minutes. 
<c:if test='${UserLogin==null}'>
	 alertMe();
</c:if>	
	function alertMe(){
		alert("ระบบไม่มีการใช้งานเป็นเวลานาน กรุณาเข้าระบบอีกครั้ง");
		document.location.href="security.htm?reqCode=doLogout";
	}

	function callHelp(){
		var Fwin_Wid = 700;
		var Fwin_Hgt = 600;	
		var Fwin_Left = (screen) ? screen.width/2 - Fwin_Wid/2 : 100;
		var Fwin_Top =  (screen) ? screen.availHeight/2 - Fwin_Hgt/2 : 100;
		
		URL ='<%=request.getContextPath()%>/page/help/helpTemplate.html';
		var win = window.open(URL, "lov", 'fullscreen=no,toolbar=no,status=1,menubar=no,scrollbars=1,resizable=yes,directories=no,location=no,width=700,height=600,left='+Fwin_Left+',top='+Fwin_Top+'');				
	}
	var scrl = " @ บริษัท ไปรษณีย์ไทย จำกัด ";
function scrlsts() {
 scrl = scrl.substring(1, scrl.length) + scrl.substring(0, 1);
 document.title = scrl;
 setTimeout("scrlsts()", 300);
 }
	
//-->
</script>
<!--<BODY onload="setFocus();">-->
<BODY onload="scrlsts();">  
	<table border="0" cellpadding="0" cellspacing="0" >
		<tr id="header">
			<td height="10px" width="1000">
				<img src="images/menu-header.jpg" alt="logo"> 
			</td>
		</tr>
		<tr class="hide_on_print">
			<td class="BG-sub-header">
			<!-- navigator -->
			<span class="left">
				<span id="nav">
					<a href="security.htm?reqCode=doMenu"><img src="images/iconConProcess.gif" border="0" >เมนู</a><img src="images/pipe.gif">
				</span>
				
			</span>
			<span class="left">
				<span id="logout"><a href="security.htm?reqCode=doLogout"> ออกจากระบบ</a></span>
				<!--page-no-->
				<span id="page-no"><a href="javascript:callHelp();"><img src="images/ecf_intro.gif" border="0"></a><%--<c:out value="${screenId}"/>--%> <img src="images/pipe.gif"></span>
				<!--login name-->
				<span id="user-login"><c:out value="${UserLogin.userName}" /></span>
				
				<!--<span id="postman"><a href="http://10.253.13.25:8080/EMPLOYEE/" target="_blank"><img src="images/find_man.jpg" border="0">ค้นหาพนักงาน</a><img src="images/pipe.gif"></span>-->
				<!--login name-->
				<!--<span id="postoffice"><a href="http://10.253.13.25:8080/ORGANIZATION/" target="_blank"><img src="images/find.jpg" border="0">ค้นหารหัสหน่วยงาน</a></span>-->
				
			</span>
			</td>
		</tr>
		<!--content-->
		<tr height="498px" valign="top">
			<td>
			<tiles:insert name="content"/>
			</td>
		</tr>
<!--<tr class="hide_on_print">	
			<td id="BG-footer" valign="middle" width="100%" height="16" bgcolor="#FFFFFF">
 			   <table id="bottom" width="100%" height="16" border="0" align="center" cellpadding="0" cellspacing="0" class="bottom">
					<tr valign="top" bgcolor="#3366FF" class="style5">
        				<td>
        					<table width="0" height="0" cellpadding="0" cellspacing="0" class="colorBox">
        						<tr>
			       					<td bgcolor="#ff6501"><img src="images/clearpixel.gif" width="25" height="16"></td>
						    	    <td bgcolor="#9999cc"><img src="images/clearpixel.gif" width="25" height="16"></td>
						    	    <td bgcolor="#ff3366"><img src="images/clearpixel.gif" width="25" height="16"></td>
						    	    <td bgcolor="#FFCC00"><img src="images/clearpixel.gif" width="25" height="16"></td>
						    	    <td bgcolor="#d7d729"><img src="images/clearpixel.gif" width="25" height="16"></td>
						    	    <td bgcolor="#3366FF"><img src="images/clearpixel.gif" width="25" height="16"></td>
						    	    <td bgcolor="#FF99CC"><img src="images/clearpixel.gif" width="25" height="16"></td>
						    	</tr>
		    		    	</table>
		    		 	</td>
					</tr>
				</table>
			</td>
		</tr>
 -->
	</table>
</BODY>
</HTML>
<c:if test="${! empty processResult}">
<script type="text/javascript">
var dialogPage = "page/infoDialog.jsp";
<c:if test="${processResult.type=='ERROR'}">
	dialogPage = "page/ErrorDialog.jsp";
</c:if>
var arguments = new Array();
    arguments[0] = '<c:out value="${processResult.resultSize}" />';
	<c:forEach items="${processResult.results}" var="result" varStatus="idx">
		<c:out value="arguments[${idx.count}]='${result.message}';"  escapeXml="false"/>
	</c:forEach>
	<c:forEach items="${processResult.results}" var="result" varStatus="idx">
		<c:out value="arguments[${idx.count+processResult.resultSize}]=\"${result.exception}\";"  escapeXml="false"/>
	</c:forEach>
var windowStyle = " center:yes;dialogWidth:400px; dialogHeight:250px; status:no; scroll:no";    
<c:if test="${processResult.type=='ERROR'}">
	result = window.showModalDialog(dialogPage, arguments, windowStyle);
</c:if>
</script>
 <% request.getSession().removeAttribute("processResult"); %>
</c:if>