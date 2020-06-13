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
%>
<html>
<head>
<title>ยืนยันการเปลี่ยนแปลงข้อมูลหลัก</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<!-- Javascript Script File -->
<SCRIPT type="text/javascript" src="dwr/interface/FeeWgPrPeriodLineService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWgPrEmployeeTextService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWgConfirmDataService.js"></SCRIPT>
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
		$("isConfirm").value = '<%= defaultYear.isConfirm() %>';
	
		if( $("isConfirm").value == 'true' ){
			document.forms['searchForm'].elements['add'].disabled = true;
	    }
		//FeeWgPrEmployeeTextService.deletePrEmployeeTextError
		//(
		//	'<%=ouCode%>',
		//	DWRUtil.getValue("year"),
			//DWRUtil.getValue("period"),
			//'<%= userId %>',
			//{callback:onLoadEmpTextCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}}
		//);
		whenCountDataA();
	}
	function onLoadEmpTextCallback(data)
	{
		DWRUtil.useLoadingMessage("Loading ...");
		
		//queryData();
		
		//whenCountDataD();
	}
	
    /*function onLoadYearSectionCallback(data)
	{
		$("year").value = data.year;
		$("section").value = data.section;
		$("period").value = data.period;
	}*/
	
	function query(){
		if(confirm("กรุณายืนยันการปิดข้อมูล?")){
		   statusConfirm = 'Y';
			queryData();
		}
	}
	function queryData()
	{
		$("editData").value = '';
		$("delData").value = '';
		$("insertData").value = '';
		$("allData").value = '';
	
		document.forms['searchForm'].elements['add'].disabled = true;
		DWRUtil.useLoadingMessage("Loading ...");
		
		whenCountDataA();
	}
	
	function whenInsertRwConfirmDataHandler(data){
	
	   // FeeWgConfirmDataService.addPrTextToPr
		//			(
		//			    '<%= userId %>',
		//				'<%=ouCode%>',
		//				DWRUtil.getValue("year"),
		//				DWRUtil.getValue("period"),
		//				{callback:whenUpdateConfirmDataHandler,errorHandler:function(message) { }}
		//			);
	}
	
	
	
	var allOrder = 0;
	
	function whenCountDataA()
	{
		allOrder = 0;
		FeeWgPrEmployeeTextService.confirmData
		(
			'<%=ouCode%>',
			DWRUtil.getValue("year"),
			DWRUtil.getValue("period"),
			'A',
			'<%= userId %>',
			{callback:whenCountDataAHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสารa');}}
		);
	}
	
	function whenCountDataAHandler(data)
	{
		DWRUtil.useLoadingMessage("Loading ...");
		
		$("editData").value = data;
		allOrder += parseInt(data);
		
		whenCountDataD();
	}
	
	function whenCountDataD()
	{
		FeeWgPrEmployeeTextService.confirmData
		(
			'<%=ouCode%>',
			DWRUtil.getValue("year"),
			DWRUtil.getValue("period"),
			'D',
			'<%= userId %>',
			{callback:whenCountDataDHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสารd');}}
		);
	}
	
	function whenCountDataDHandler(data)
	{
		DWRUtil.useLoadingMessage("Loading ...");
	
		$("delData").value = data;
		allOrder += parseInt(data);
		
		whenCountDataI();
	}
	
	function whenCountDataI()
	{
		FeeWgPrEmployeeTextService.confirmData
		(
			'<%=ouCode%>',
			DWRUtil.getValue("year"),
			DWRUtil.getValue("period"),
			'I',
			'<%= userId %>',
			{callback:whenCountDataIHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสารi');}}
		);
	}
	
	function whenCountDataIHandler(data)
	{

			$("insertData").value = data;
			allOrder += parseInt(data);
			$("allData").value = allOrder;
		if (statusConfirm == 'Y'){		
			if(allOrder==0)
			{
				alert('ข้อมูลได้ทำการยืนยันเรียบร้อย');
				document.forms['searchForm'].elements['add'].disabled = true;
				var confirmDataVo = { 	ouCode:'<%=ouCode%>',
									year:DWRUtil.getValue("year"),
									period:DWRUtil.getValue("period"),
									userId:'<%=userId%>',
									flag:'2' 
								};
				FeeWgConfirmDataService.insertRwConfirmData
					(
						confirmDataVo,
						{callback:whenInsertRwConfirmDataHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสารin');}}
					);
			}else
			{
				DWRUtil.useLoadingMessage("Loading ...");
					
					FeeWgPrEmployeeTextService.updateConfirmData
					(
						'<%=ouCode%>',
						DWRUtil.getValue("year"),
						DWRUtil.getValue("period"),
						'<%= userId %>',
						{callback:whenUpdateConfirmDataHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสารu');}}
					);
					
					var confirmDataVo = { 	ouCode:'<%=ouCode%>',
									year:DWRUtil.getValue("year"),
									period:DWRUtil.getValue("period"),
									userId:'<%=userId%>',
									flag:'2' 
								};
								
					FeeWgConfirmDataService.insertRwConfirmData
					(
						confirmDataVo,
						{callback:whenInsertRwConfirmDataHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสารin1');}}
					);

			}
		}
	}
	
	function whenUpdateConfirmDataHandler(data)
	{
		allOrder = 0;
		alert('ได้ทำการยืนยันข้อมูลเรียบร้อย');
		//document.forms['searchForm'].elements['add'].disabled = false;
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
			[ CTRWMT002 ] ยืนยันการเปลี่ยนแปลงข้อมูลหลัก
		</td>
	</tr>
</table>
<form name="searchForm" action="" method="post">
<input type="hidden" name="period"> 
<input type="hidden" name="confirm"> 
<br/><br/><br/><br/>
<table align="center" border="1" bordercolor="#6699CC">
<tr><td>
<BR/>
<table width="330" border="0" align="center" cellspacing="1">
  <tr>
    <td class="font-field" align="right">ประจำปี&nbsp;</td>
    <td align="left"><input type="text" name="year"   value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/></td>
    <td  class="font-field" align="right">งวด&nbsp;</td>
    <td align="left">
    	<input type="text" name="section"  value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/>
    	<INPUT type="hidden" name="isConfirm" />
    </td>
  </tr>
 </table>
 <center><FONT color="#6699CC">__________________________________________________________________</FONT></center>
 <BR/>
 <table width="370" border="0" bordercolor="#6699CC" align="center" cellspacing="0" cellpadding="1">
  <tr>
    <td class="font-field" align="right">จำนวนข้อมูลทั้งหมด&nbsp;&nbsp;</td>
    <td align="left"><input type="text" name="allData"   value="" readonly="readonly" style="width:100;text-align:center;background-color:silver;"/></td>
    <td class="font-field" align="left">รายการ&nbsp;</td>
  </tr>
  
  <tr>
    <td class="font-field" align="right">จำนวนข้อมูลที่แก้ไข (A)&nbsp;&nbsp;</td>
    <td align="left"><input type="text" name="editData"   value="" readonly="readonly" style="width:100;text-align:center;background-color:silver;"/></td>
    <td class="font-field" align="left">รายการ&nbsp;</td>
  </tr>
  
  <tr>
    <td class="font-field" align="right">จำนวนข้อมูลที่ลบ (D)&nbsp;&nbsp;</td>
    <td align="left"><input type="text" name="delData"   value="" readonly="readonly" style="width:100;text-align:center;background-color:silver;"/></td>
    <td class="font-field" align="left">รายการ&nbsp;</td>
  </tr>
  
  <tr>
    <td class="font-field" align="right">จำนวนข้อมูลที่เพิ่มเติม (I)&nbsp;&nbsp;</td>
    <td align="left"><input type="text" name="insertData"   value="" readonly="readonly" style="width:100;text-align:center;background-color:silver;"/></td>
    <td class="font-field" align="left">รายการ&nbsp;</td>
  </tr>
</table>
<BR/>
</td></tr>
</table>
<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<CENTER><input type="button" class="button" style="width:80px" value="ตกลง" name="add" onclick="query();"/></CENTER>
</form>
</body>
</html>