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
	String approve = "";
	
	if (request.getSession().getAttribute("processResult") != null){
		ProcessResult processResult = (ProcessResult) request.getSession().getAttribute("processResult");
		year =  request.getParameter("hidYear")==null?String.valueOf(now.get(Calendar.YEAR)+543):request.getParameter("hidYear");
		month =  request.getParameter("hidMonth")==null?String.valueOf(now.get(Calendar.MONTH)+1):request.getParameter("hidMonth");
		
	}
%>
<html>
<head>
<title>รายงานโอนข้อมูลเข้าระบบบัญชี ประจำเดือน</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="script/payroll_util.js"></script>
<!-- Javascript Script File -->
<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/PnEmployeeService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/ApTableService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/ApConfirmDataService.js"></SCRIPT>


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
     //var statusConfirm = 'N';

	// =========================== Start LOV ===========================
   
    function onLoadVolumeCallback()
    {
    	try
    	{
	    	var cboSource = [];
	     	var cbo = dojo.widget.byId("volumeCbo");
	     	//var workYear = DWRUtil.getValue("workYear");
			//var workMonth = DWRUtil.getValue("workMonth");
	     
	     
			     	
			     	<c:forEach items="${VolumeInSecurity}" var="result" >		 
						cboSource.push(["<c:out value='${result}' />","<c:out value='${result}' />"]);
					</c:forEach>
	     	
	     	cbo.dataProvider.setData(cboSource);
	     
	   
     	}catch(e)
     	{
     		alert(e.message);
    	}
	} 
     
   
 
     
   
   function whenSelectVolumeOption()
	{
    	DWRUtil.useLoadingMessage("Loading ...");
		var cbo = dojo.widget.byId("volumeCbo");
		whenFetchVolumeTo(cbo.textInputNode.value);
	}
	
	function whenFetchVolumeTo(volume)
	{
     	DWRUtil.useLoadingMessage("Loading ...");
     	var cboTo = dojo.widget.byId("volumeCbo");
		     	
		    
		     	
		     	var cboSource = [];
		     	
		     	<c:forEach items="${VolumeInSecurity}" var="result" >			     	
						cboSource.push(["<c:out value='${result}' />","<c:out value='${result}' />"]);
				</c:forEach>	
		     	
		     	cboTo.dataProvider.setData(cboSource);
		     	
		   
     	
    }
    
  
    
	
    
   

 
	function init()
	{
		
		//var cboEmpFrom = dojo.widget.byId("empFromCbo");
		var cboVolume = dojo.widget.byId("volumeCbo");
	
		//dojo.event.connect(cboEmpFrom, "selectOption", "whenSelectEmpOption");
		dojo.event.connect(cboVolume, "selectOption", "whenSelectVolumeOption");
		
		
		//whenCountDataAll();
		
	}
	
  	//dojo.addOnLoad(init);

    //dojo.addOnLoad(onLoadVolumeCallback);
	//dojo.addOnLoad(onLoadEmployeeCallback);

	
	// =========================== End LOV ===========================
	
	
	function query(){
		//if(confirm("กรุณายืนยันการปิดข้อมูล?")){
		  // statusConfirm = 'Y';
			queryData();
		//}
	}
	function queryData()
	{
		$("allData").value = '';
		$("allMoney").value = '';
		$("empMoney").value = '';
		$("othMoney").value = '';
		
	
		document.forms['searchForm'].elements['add'].disabled = true;
		DWRUtil.useLoadingMessage("Loading ...");
		
		whenCountDataAll();
	}
	function whenInsertApConfirmDataHandler(data){
	}
	
	var allOrder = 0;
	
	function whenCountDataAll()
	{
		allOrder = 0;
		var workYear = DWRUtil.getValue("workYear");
		var workMonth = DWRUtil.getValue("workMonth");
		var volumeCbo = '';
		
		
		
		
		if(dojo.widget.byId("volumeCbo").textInputNode.value != '')
		{
			volumeCbo = dojo.widget.byId("volumeCbo").textInputNode.value;
		}else
		{
			volumeCbo = '';
		}
		ApTableService.confirmData
		(
			'<%=ouCode%>',
			workYear,
			workMonth,
			volumeCbo,
			'<%= userId %>',
			{callback:whenCountDataAllHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}}
		);
	}
	
	function whenCountDataAllHandler(data)
	{
		DWRUtil.useLoadingMessage("Loading ...");
		
		allOrder += parseInt(data);
		$("allData").value = allOrder;
		
		whenCountDataMoney();
	}
	
	function whenCountDataMoney()
	{
		
		var workYear = DWRUtil.getValue("workYear");
		var workMonth = DWRUtil.getValue("workMonth");
		var volumeCbo = '';
		
		
		
		
		
		if(dojo.widget.byId("volumeCbo").textInputNode.value != '')
		{
			volumeCbo = dojo.widget.byId("volumeCbo").textInputNode.value;
		}else
		{
			volumeCbo = '';
		}
		ApTableService.sumAllMoneyData
		(
			'<%=ouCode%>',
			workYear,
			workMonth,
			volumeCbo,
			'<%= userId %>',
			{callback:whenCountDataMoneyHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร 7');}}
		);
	}
	
	function whenCountDataMoneyHandler(data)
	{
		var allMon = 0.00;
		DWRUtil.useLoadingMessage("Loading ...");
		allMon += data;
		$("allMoney").value =formatCurrency(allMon);
		//allOrder += parseInt(data);
		
		whenCountDataEmpMoney();
	}
	
	function whenCountDataEmpMoney()
	{
		
		var workYear = DWRUtil.getValue("workYear");
		var workMonth = DWRUtil.getValue("workMonth");
		var volumeCbo = '';
		
		
		
		
		
		if(dojo.widget.byId("volumeCbo").textInputNode.value != '')
		{
			volumeCbo = dojo.widget.byId("volumeCbo").textInputNode.value;
		}else
		{
			volumeCbo = '';
		}
		
		ApTableService.sumMoneyData
		(
			'<%=ouCode%>',
			workYear,
			workMonth,
			volumeCbo,
			'<%= userId %>',
			'1',
			{callback:whenCountDataEmpMoneyHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร 6');}}
		);
	}
	
	function whenCountDataEmpMoneyHandler(data)
	{
		var empM = 0.00;
		DWRUtil.useLoadingMessage("Loading ...");
		empM += data;
		$("empMoney").value = formatCurrency(empM);
		//allOrder += parseInt(data);
		
		whenCountDataOthMoney();
	}
	function whenCountDataOthMoney()
	{
		
		var workYear = DWRUtil.getValue("workYear");
		var workMonth = DWRUtil.getValue("workMonth");
		var volumeCbo = '';
		
		
		
		
		
		if(dojo.widget.byId("volumeCbo").textInputNode.value != '')
		{
			volumeCbo = dojo.widget.byId("volumeCbo").textInputNode.value;
		}else
		{
			volumeCbo = '';
		}
		
		ApTableService.sumMoneyData
		(
			'<%=ouCode%>',
			workYear,
			workMonth,
			volumeCbo,
			'<%= userId %>',
			'2',
			{callback:whenCountDataOthMoneyHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร 5');}}
		);
	}
	
	function whenCountDataOthMoneyHandler(data)
	{
		var othM = 0.00;
		var volumeCbo = '';
		
		DWRUtil.useLoadingMessage("Loading ...");
		othM += data;
		$("othMoney").value = formatCurrency(othM);
		
		
		if(dojo.widget.byId("volumeCbo").textInputNode.value != '')
		{
			volumeCbo = dojo.widget.byId("volumeCbo").textInputNode.value;
		}else
		{
			volumeCbo = '';
		}
		whenShowReport();

	}
	
	
	

	
	
	

	function addCommas(nStr)
	{
		nStr += '';
		x = nStr.split('.');
		x1 = x[0];
		x2 = x.length > 1 ? '.' + x[1] : '';
		var rgx = /(\d+)(\d{3})/;
		while (rgx.test(x1)) {
			x1 = x1.replace(rgx, '$1' + ',' + '$2');
		}
		return x1 + x2;
	}
	
	function formatCurrency(num) {
	    num = isNaN(num) || num === '' || num === null ? 0.00 : num;
	    return addCommas(parseFloat(num).toFixed(2));
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
			[ CTWPAYGLRP001 ] รายงานส่งข้อมูลค่าจ้าง เข้าระบบ GL กบ.
		</td>
	</tr>
</table>
<form name="searchForm" action="" method="post">
<input type="hidden" name="hidMonth" value="<%=month%>"/>
<input type="hidden" name="dataLength"> 
<br/><br/><br/><br/>
<table align="center" border="1" bordercolor="#6699CC">
<tr><td>
<BR/> 
<table width="330" border="0" align="center" cellspacing="1">

  		<tr>
    	<td class="font-field" align="right">ประจำปี</td>
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
		<td>&nbsp</td>
  	</tr>  
 	<tr>
  
</table>
 <center><FONT color="#6699CC">__________________________________________________________________</FONT></center>
 <BR/>
 <table width="370" border="0" bordercolor="#6699CC" align="center" cellspacing="0" cellpadding="1">
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<CENTER><input type="button" class="button" style="width:80px" value="ตกลง" name="add" onclick="whenShowReport();"/></CENTER>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</table>
<BR/>
</td></tr>
</table>
<br/></form>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
function whenShowReport(){
	var frm = document.forms[0];
	var workYear = DWRUtil.getValue("workYear");
	var workMonth = DWRUtil.getValue("workMonth");
	//var comB1=dojo.widget.byId("volumeCbo").textInputNode.value;
	//var workApprove = DWRUtil.getValue("workApprove");
		//alert(workApprove);
	//if(comB1!=''){
	//	dojo.widget.byId("volumeCbo").comboBoxSelectionValue.value = splitCombo(dojo.widget.byId("volumeCbo").textInputNode.value);
	//	volumeCbo = splitCombo(dojo.widget.byId("volumeCbo").textInputNode.value);
		
	//}

	//forController();
	frm.action="apTransferCheckReport.htm?reqCode=doPrintReport";
	frm.target = "_blank";
	frm.submit();
	frm.target = "_self";
}
function forController(){
	//DWRUtil.getValue("workYear");
	//DWRUtil.getValue("workMonth");
	//DWRUtil.setValue("volumeSet",volumeCbo);

}



</SCRIPT>
