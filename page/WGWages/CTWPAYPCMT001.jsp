<%@ page language="java" contentType="text/html;charset=TIS-620" %>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@ page import="com.ss.tp.dto.DefaultYearSectionVO" %>

<%
	UserInfo userInfo = (UserInfo)session.getAttribute("UserLogin");
	String userId = userInfo.getUserId();
	String ouCode = userInfo.getOuCode();
	
	DefaultYearSectionVO defaultYear = (DefaultYearSectionVO)session.getAttribute("DefaultYearAndSection");
	boolean isCloseTranClose = ((Boolean)session.getAttribute("isCloseTranClose")).booleanValue();
	boolean isConfirm = ((Boolean)session.getAttribute("isConfirm")).booleanValue();
	boolean isConfirmApprove = ((Boolean)session.getAttribute("isConfirmApprove")).booleanValue();
	
	System.out.println("isCloseTranClose : " + isCloseTranClose);
	System.out.println("isConfirm : " + isConfirm);
	System.out.println("isConfirmApprove : " + isConfirmApprove);
%>
<html>
<head>
<title>คำนวณเงินค่าจ้างทั้งหมดและปิดงวด</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<!-- Javascript Script File -->
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrPeriodLineService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrEmployeeTextService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayRwConfirmDataService.js"></SCRIPT>
<script type="text/javascript" src="script/gridScript.js"></script>
<script type="text/javascript" src="script/payrollComboBox.js"></script>
<script type="text/javascript" src="page/NavigatePage.jsp"></script>
<script type="text/javascript" src="script/dojo.js"></script>

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
var statusConfirm = 'N';
	function init()
	{
		$("year").value = '<%= defaultYear.getYear() %>';
		$("section").value = '<%= defaultYear.getSection() %>';
		$("period").value = '<%= defaultYear.getPeriod() %>';
		$("isConfirm").value = '<%= isConfirm %>';
		$("isConfirmApprove").value = '<%= isConfirmApprove %>';
		
		chkMainClose();
		if( $("isConfirmApprove").value == 'true' ){
			document.forms['searchForm'].elements['add'].disabled = true;
			alert("!!! มีต้นสังกัดยังไม่ยืนยันการอนุมัติ กรุณาไปหน้า ตรวจสอบสถานะการอนุมัติ !!!");
			window.history.back()
		}
		if( $("isConfirm").value == 'true' )
			document.forms['searchForm'].elements['add'].disabled = true;
			
		if( '<%= isCloseTranClose %>' == 'true' )
			document.forms['searchForm'].elements['add'].disabled = true;
	
		
		
		whenShowDataTable();
	}
	
    /*function onLoadYearSectionCallback(data)
	{
		$("year").value = data.year;
		$("section").value = data.section;
		$("period").value = data.period;
	}*/
	
	
	function onLoadIncDecCallback(data)
	{
		DWRUtil.useLoadingMessage("Loading ...");
		
		//whenShowDataTable();
		
		//queryData();
		
		//whenCountDataD();
	}
	
	function query(){
		if(confirm("กรุณายืนยันการปิดข้อมูล?")){
		   statusConfirm = 'Y';
			whenShowDataTable();
		}
	}
	function whenShowDataTable()
	{

		
		allOrder = 0;
		
		DWRUtil.useLoadingMessage("กำลังประมวลผล รอสักครู่ ...");
		//alert("test");
		FeeWpayPrEmployeeTextService.submitIncomeTransClose
		(
			'<%=userId%>',
			'<%=ouCode%>',
			DWRUtil.getValue("year"),
			DWRUtil.getValue("period"),
			statusConfirm,
			{callback:whenListDataTableHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร3');}}
		);
		
		var confirmDataVo = { 	ouCode:'<%=ouCode%>',
								year:DWRUtil.getValue("year"),
								period:DWRUtil.getValue("period"),
								userId:'<%=userId%>',
								flag:'2' 
							};
			
		if (statusConfirm == 'Y'){
		     alert("กรุณารอจนกว่าระบบจะบอกว่าดำเนินการเรียบร้อย ถึงออกจากหน้าจอนี้");
			FeeWpayRwConfirmDataService.insertRwConfirmDataPcMt001
			(
				confirmDataVo,
				{callback:whenInsertRwConfirmDataHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร4');}}
			);
		}
	}
	
	function whenInsertRwConfirmDataHandler(data){
		var btn = document.getElementById("add");
		btn.disabled = true;
		alert("ระบบดำเนินการเรียบร้อย");
	}
	
	var allOrder = 0;
	
	var cellFuncs = [
		function(data) { return "<div align='left'>"+data.incDecName+"</div>";},
		function(data) { allOrder += parseInt(data.countOt); return "<div align='right'>"+data.countOt+"&nbsp;</div>";}
	];
	
	function whenListDataTableHandler(data)
	{
		//alert('data1Size ' + data.length);
		if(data.length > 0){
			DWRUtil.removeAllRows("dataTable1");
			DWRUtil.addRows("dataTable1",data,cellFuncs);
		}else{
			//alert('No Data');
			//document.forms['searchForm'].elements['add'].disabled = true;
			DWRUtil.removeAllRows("dataTable1");
		}
		
		$("allData").value = allOrder;
		
		//var btn = document.getElementById("add");
		//btn.disabled = false;
		
		whenShowDataTable2();
	}
	
	function whenShowDataTable2()
	{
		DWRUtil.useLoadingMessage("กำลังประมวลผล รอสักครู่ ...");
		
		FeeWpayPrEmployeeTextService.submitDeductTransClose
		(
			'<%=userId%>',
			'<%=ouCode%>',
			DWRUtil.getValue("year"),
			DWRUtil.getValue("period"),
			{callback:whenListDataTable2Handler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร5');}}
		);
	}
	
	var cellFuncs2 = [
		function(data) { return "<div align='left'>"+data.incDecName+"</div>";},
		function(data) { allOrder += parseInt(data.countOt); return "<div align='right'>"+data.countOt+"&nbsp;</div>";}
	];
	
	function whenListDataTable2Handler(data)
	{
		//alert('data2Size ' + data.length);
		if(data.length > 0){
			DWRUtil.removeAllRows("dataTable2");
			DWRUtil.addRows("dataTable2",data,cellFuncs2);
		}else{
			var tab = $('dataTable2');
			if(tab.rows.length == 0){
				alert('ไม่พบข้อมูล');
				//document.forms['searchForm'].elements['add'].disabled = true;
			}
			//
			DWRUtil.removeAllRows("dataTable2");
		}
		
		$("allData").value = allOrder;
		
		//var btn = document.getElementById("add");
		//btn.disabled = false;
	}
	
	function chkMainClose(){
				FeeWpayPrPeriodLineService.findPeriodLine('<%=ouCode%>',$("year").value,$("period").value ,'<%=userId%>', {callback:whenchkMainCloseCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร6');}});
		    }
			
			function whenchkMainCloseCallback(data){
				if(data.tranClose == 'Y'){
					alert('ไม่สามารถดำเนินการต่อได้');
					window.history.back()
				}
			}
			
	// Load page
  	dojo.addOnLoad(init);
	
</script>
<%
	Calendar now = Calendar.getInstance(Locale.US);
	String year = ((now.get(Calendar.YEAR)+543)+"");
	String keySeq  = request.getParameter("keySeq");
%>
</head>
<body>
<table width="100%">
	<tr>
		<td class="font-head">
			[ CTWPAYPCMT001 ] คำนวณค่าจ้างลูกจ้างทั้ง ปณท. และปิดงวด
		</td>
	</tr>
</table>
<form name="searchForm" action="" method="post">
<input type="hidden" name="period"> 
<input type="hidden" name="confirm"> 
<br><br>
		<table border="1" align="center" cellpadding="1" bordercolor="#6699CC" cellspacing="1">
			<tr>
				<td>
					<table width="350" border="0" align="center" cellspacing="1">
					  <tr>
					    <td class="font-field" align="right">ประจำปี&nbsp;&nbsp;&nbsp;</td>
					    <td align="left"><input type="text" name="year"   value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/></td>
					    <td  class="font-field" align="right">งวด&nbsp;&nbsp;&nbsp;</td>
					    <td align="left">
					    	<input type="text" name="section"  value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/>
					    	<INPUT type="hidden" name="isConfirm" />
					    	<INPUT type="hidden" name="isConfirmApprove" />
					    </td>
					  </tr>
					</table>
					<center>__________________________________________________________________ </center>
					<br>
					<table width="400" border="0" align="left" cellspacing="1">
					  <tr>
					    <td class="font-field" align="right" >จำนวนข้อมูลทั้งหมด&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					    <td align="left" colspan="0"><input type="text" name="allData"   value="" readonly="readonly" style="width:100;text-align:center;background-color:silver;"/></td>
					    <td class="font-field" align="left">รายการ</td>
					  </tr>
					</table>
				</td>
			</tr>
		</table>
<br><br>
<table width="500" align="center" cellpadding="2" cellspacing="2">
<tr>
	<td valign="top">
	<FIELDSET>
    		<LEGEND>รายการคำนวณเงิน</LEGEND>
    		<br>
    		<table width="350"  border="1" bordercolor="#6699CC"  align="center"  cellpadding="2" cellspacing="0">
			  	<thead>
					<tr CLASS="TABLEBULE2">
						<th CLASS="TABLEBULE2">รหัส</th>
						<th CLASS="TABLEBULE2">จำนวนรายการ</th>
					<tr>
				</thead>
				<tbody id="dataTable1">
				</tbody>
			</table>
			<br>
    </FIELDSET>
	</td>
	
	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<td valign="top">
	<FIELDSET>
    		<LEGEND>เงินหัก</LEGEND>
    		<br>
    		<table width="350"  border="1" bordercolor="#6699CC"  align="center"  cellpadding="2" cellspacing="0">
			  	<thead>
					<tr CLASS="TABLEBULE2">
						<th CLASS="TABLEBULE2">รหัส</th>
						<th CLASS="TABLEBULE2">จำนวนรายการ</th>
					<tr>
				</thead>
				<tbody id="dataTable2">
				</tbody>
			</table>
			<br>
    </FIELDSET>	
	</td>
	
</tr>
</table>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" class=" button "  value="ตกลง" id="add" onclick="query();"/>

</body>
</html>