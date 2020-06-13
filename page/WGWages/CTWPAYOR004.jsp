<%@ page language="java" contentType="text/html;charset=TIS-620" %>
<%@page import="java.text.DecimalFormat"%>
<%@page import="javax.swing.text.Document"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@ page import="com.ss.tp.security.ProcessResult" %>
<%@ page import="com.ss.tp.security.UserInfo" %>

<%
	Calendar now = Calendar.getInstance(Locale.US);	
	UserInfo uf =  (UserInfo)request.getSession().getAttribute("UserLogin");	
	
	
	java.util.Date dd = new java.util.Date();
	java.text.SimpleDateFormat fmd = new java.text.SimpleDateFormat("dd/MM/yyyy",new java.util.Locale("th","TH"));
	String date = fmd.format(dd);
	String userId =  uf.getUserId();
	String ouCode =  uf.getOuCode();
	String hidPage = request.getParameter("hidPage")==null?"":request.getParameter("hidPage");
	String pageEdit = request.getParameter("pageEdit")==null?"0":request.getParameter("pageEdit");
	String year = request.getParameter("hidWorkYear")==null?String.valueOf(now.get(Calendar.YEAR)+543):request.getParameter("hidWorkYear");
	String month = request.getParameter("hidWorkMonth")==null?String.valueOf(now.get(Calendar.MONTH)+1):request.getParameter("hidWorkMonth");
	
	if (request.getSession().getAttribute("processResult") != null){
		ProcessResult processResult = (ProcessResult) request.getSession().getAttribute("processResult");
		year =  request.getParameter("hidYear")==null?String.valueOf(now.get(Calendar.YEAR)+543):request.getParameter("hidYear");
		month =  request.getParameter("hidMonth")==null?String.valueOf(now.get(Calendar.MONTH)+1):request.getParameter("hidMonth");
	}
%>
<html>
<head>
<title>โอนข้อมูลบุคคลเข้าระบบค่าจ้างลูกจ้าง</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="script/payroll_util.js"></script>
<!-- Javascript Script File -->
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayConfirmDataService.js"></SCRIPT>
<script type="text/javascript" src="script/gridScript.js"></script>
<script type="text/javascript" src="script/payrollComboBox.js"></script>
<script type="text/javascript" src="page/NavigatePage.jsp"></script>
<script type="text/javascript" src="script/dojo.js"></script>
<script type="text/javascript" src="script/dateCalendar.js"></script>
<script type="text/javascript" src="script/json.js"></script>

<script type="text/javascript">
	//Load Dojo's code relating to widget managing functions
	dojo.require("dojo.widget.*");
	dojo.require("dojo.widget.Menu2");
	dojo.require("dojo.widget.Button");
	dojo.require("dojo.widget.ComboBox");
	dojo.require("dojo.widget.DropDownButton");
	dojo.require("dojo.widget.SortableTable");
	dojo.require("dojo.widget.ContentPane");
	dojo.require("dojo.widget.LayoutContainer");
	dojo.require("dojo.widget.SortableTable");
	dojo.require("dojo.widget.Toolbar");
	dojo.require("dojo.widget.html.*");
	dojo.require("dojo.widget.Menu2");
	dojo.hostenv.writeIncludes();
	
	//Event
	dojo.require("dojo.event.*");
</script>

<script type="text/javascript">

function init(){
		$("confirm").value = "<c:out value='${CreatedWgPrEmployee}' /> "  ;//data.confirm;
		 if(<c:out value='${CreatedWgPrEmployee}' />){
			document.forms['searchForm'].elements['ok'].disabled = true;
		}
	}

dojo.addOnLoad(init);
function addPnToPr(){
	if(confirm("คุณต้องการนำเข้าข้อมูลหลักตั้งต้นประจำเดือนนี้ ?")){
	   statusConfirm = 'Y';
	    whenPnToPr();
		
		
	}
}
function whenPnToPr()
{
	
	var workYear = DWRUtil.getValue("workYear");
	var workMonth = DWRUtil.getValue("workMonth");
	//alert(workYear);
	//alert(workMonth);
	DWRUtil.useLoadingMessage("Adding ...");
	FeeWpayConfirmDataService.addPnToPr
	(
		'<%=userId%>',
		'<%=ouCode%>',
		workYear,
		workMonth,
		{callback:whenPnToPrHandler,errorHandler:function(message) { }}
	);
	
	DWRUtil.useLoadingMessage("Please Waiting ...");	
	if (statusConfirm == 'Y'){		
		
			DWRUtil.useLoadingMessage("Please Waitting ...");
			var confirmDataVo = { 	ouCode:'<%=ouCode%>',
					             year:DWRUtil.getValue("workYear"),
					             period:workMonth*2,
					             userId:'<%=userId%>',
					             flag:'1' 
						};
						
			FeeWpayConfirmDataService.insertWgConfirmData
			(
				confirmDataVo,
				{callback:whenInsertPeriodPrInYearPrDataHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร 3');}}
			);

		}
	
}

function whenPnToPrHandler(data)
{
}

function whenInsertPeriodPrInYearPrDataHandler(data){
	alert('นำเข้าข้อมูล เรียบร้อย');
	document.forms['searchForm'].elements['ok'].disabled = true;
}
</script>
<%
	
	
	String keySeq  = request.getParameter("keySeq");
%>
</head>
<body>

<table width="100%">
	<tr>
		<td class="font-head">
			[ CTWPAYOR004 ] รับโอนข้อมูลบุคคลเข้าระบบค่าจ้างลูกจ้าง
		</td>
	</tr>
</table>
<form name="searchForm" action="" method="post">
<input type="hidden" name="hidMonth" value="<%=month%>"/>
<input type="hidden" name="confirm">
<table width="770" border="0" align="center" cellspacing="1">

  		<tr>
    	<td class="font-field" align="right">ประจำปี&nbsp</td>
    	<td align="left">
    		<input type="text" name="workYear" value="<%=year%>" size="2" maxlength="4" style="width: 70px;text-align: center;" onkeyup="if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/>
    	</td>
	    <td class="font-field" align="right">เดือน</td>
		<td align="left">
			<select name="workMonth" >	
				<option value="0">- - - - - - - - - -</option>
				<option value="1">มกราคม</option>
				<option value="2">กุมภาพันธ์</option>
				<option value="3">มีนาคม</option>
				<option value="4">เมษายน</option>
				<option value="5">พฤษภาคม</option>
				<option value="6">มิถุนายน</option>
				<option value="7">กรกฎาคม</option>
				<option value="8">สิงหาคม</option>
				<option value="9">กันยายน</option>
				<option value="10">ตุลาคม</option>
				<option value="11">พฤศจิกายน</option>
				<option value="12">ธันวาคม</option>
			</select>
			<script>
				document.forms["searchForm"].workMonth.value = document.forms["searchForm"].hidMonth.value;
			</script>
		</td>
	</tr>
</table>
<br/>
<table width="100%" CLASS="TABLEBULE2" >
	<tr CLASS="TABLEBULE2" >
		<td align="center" >&nbsp;
			<input type="Button" class=" button " value="คลิกโอนข้อมูลบุคคลเข้าระบบค่าจ้าง" id="ok" name="ok" onclick="addPnToPr();"/>
		</td>
	</tr>
</table>
</body>
</html>
