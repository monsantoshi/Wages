<%@ page language="java" contentType="text/html;charset=TIS-620"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="javax.swing.text.Document"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@ page import="com.ss.tp.security.ProcessResult"%>
<%@ page import="com.ss.tp.security.UserInfo"%>
<%@ page import="com.ss.tp.dto.DefaultYearSectionVO" %>

<%
	Calendar now = Calendar.getInstance(Locale.US);
	UserInfo uf = (UserInfo) request.getSession().getAttribute(
			"UserLogin");
	DefaultYearSectionVO defaultYear = (DefaultYearSectionVO)session.getAttribute("DefaultYearAndSection");
	java.util.Date dd = new java.util.Date();
	java.text.SimpleDateFormat fmd = new java.text.SimpleDateFormat(
			"dd/MM/yyyy", new java.util.Locale("th", "TH"));
	String date = fmd.format(dd);
	String userId = uf.getUserId();
	String ouCode = uf.getOuCode();
	String hidPage = request.getParameter("hidPage") == null
			? ""
			: request.getParameter("hidPage");
	String pageEdit = request.getParameter("pageEdit") == null
			? "0"
			: request.getParameter("pageEdit");
	

	if (request.getSession().getAttribute("processResult") != null) {
		ProcessResult processResult = (ProcessResult) request
				.getSession().getAttribute("processResult");
	
	}
%>
<html>
<head>
<title>�觢����Ť�Ҩ�ҧ�١��ҧ����к� GL ERP</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="script/payroll_util.js"></script>
<!-- Javascript Script File -->
<SCRIPT type="text/javascript"
	src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>

<SCRIPT type="text/javascript" src="dwr/interface/FeeWgPrEmployeeTextService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWgConfirmDataService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWgRwConfirmDataService.js"></SCRIPT>

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
     var statusConfirm = 'N';

	// =========================== Start LOV ===========================
   
    
 
	function init()
	{		
		$("workYear").value = '<%= defaultYear.getYear() %>';
		$("workMonth").value = '<%= defaultYear.getMonth() %>';
		
	
		//whenCountDataAll();
		
	}
	
  	dojo.addOnLoad(init);

 
	
	// =========================== End LOV ===========================
	
	
	function query(){
		if(confirm("�����ż�����к��ѭ�� GL ERP?")){
		   statusConfirm = 'Y';
		    whenApToGl();
			
			
		}
	}
	
	function whenApToGl()
	{
		
		var workYear = DWRUtil.getValue("workYear");
		var workMonth = DWRUtil.getValue("workMonth");
		var stdDate = DWRUtil.getValue("startDate");
		
		
		
		
		
	
		
		
		FeeWgConfirmDataService.addFeeWpayDailyToGl
		(
			'<%=userId%>',
			'<%=ouCode%>',
			workYear,
			workMonth,
			stdDate,
			{callback:whenApToGlHandler,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ���������� apToGl');}}
		);
	}
	
	function whenApToGlHandler(data)
	{
		
	
		
		DWRUtil.useLoadingMessage("Loading ...");
		
		
		
	
		
		if (statusConfirm == 'Y'){		
			
				DWRUtil.useLoadingMessage("Loading ...");
			    //alert('ApTableService.updateConfirmData');
			   // alert(volumeCbo);
			   
			  	FeeWgPrEmployeeTextService.updateTransferFlag
				(
					'<%=ouCode%>',
					DWRUtil.getValue("workYear"),
					DWRUtil.getValue("workMonth"),
					'<%= userId %>',
					{callback:whenUpdateConfirmDataHandler,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ���������� 2');}}
				);
				
				var confirmDataVo = { 	ouCode:'<%=ouCode%>',
								year:DWRUtil.getValue("year"),
								period:DWRUtil.getValue("period"),
								userId:'<%=userId%>',
								flag:'3' 
							};
			
							
				FeeWgRwConfirmDataService.insertRwConfirmData
				(
					confirmDataVo,
					{callback:whenInsertApConfirmDataHandler,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ���������� 3');}}
				);

			}
		
	}
	
	function whenInsertApConfirmDataHandler(data){
	}

	function whenUpdateConfirmDataHandler(data) {
		
		alert('��ӡ�� �觢�������� GL ���º����');
		//document.forms['searchForm'].elements['add'].disabled = false;
	}

	
	
	
	

	function whenUpdateConfirmDataHandler(data) {
		//allOrder = 0;
		alert('��ӡ�� �觢�������� GL ���º����');
		//document.forms['searchForm'].elements['add'].disabled = false;
	}

	function addCommas(nStr) {
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
		num = isNaN(num) ||
num === '' || num === null ? 0.00 : num;
	    return addCommas(parseFloat(num).toFixed(2));
	}

	
	
</script>
<%
	String keySeq = request.getParameter("keySeq");
%>
</head>
<body>
	<table width="100%">
		<tr>
			<td class="font-head">[ CTWGPAYGL001 ] �觢����Ť�Ҩ�ҧ �١��ҧ���� ����к� GL ERP ��.
			</td>
		</tr>
	</table>
	<form name="searchForm" action="" method="post">
		<!-- <input type="hidden" name="workMonth"  />--> 
		<inputtype="hidden" name="dataLength"> <br />
		<br />
		<br />
		<br />
		<table align="center" border="1" bordercolor="#6699CC">
			<tr>
				<td><BR />
					<table width="430" border="0" align="center" cellspacing="1">

						<tr>
							<td class="font-field" align="right">��Шӻ�</td>
							<td align="left"><input type="text" name="workYear"
								value=""  size="2" maxlength="4"
								style="width: 70px; text-align: center;"
								onkeyup="if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" />
							</td>
							<td class="font-field" align="right">��͹</td>
							<td align="left"><select name="workMonth">
									<option value="0">- - - - - - - - - -</option>
									<option value="1">���Ҥ�</option>
									<option value="2">����Ҿѹ��</option>
									<option value="3">�չҤ�</option>
									<option value="4">����¹</option>
									<option value="5">����Ҥ�</option>
									<option value="6">�Զع�¹</option>
									<option value="7">�á�Ҥ�</option>
									<option value="8">�ԧ�Ҥ�</option>
									<option value="9">�ѹ��¹</option>
									<option value="10">���Ҥ�</option>
									<option value="11">��Ȩԡ�¹</option>
									<option value="12">�ѹ�Ҥ�</option>
							</select> <script>
								document.forms["searchForm"].workMonth.value = document.forms["searchForm"].workMonth.value;
							</script></td>
							<td>&nbsp</td>
						</tr>
					
						<tr>
						<td class="font-field" align="right">�ѹ���Դ������  ��� �.�. </td>
							<td align="left"><input type="text" name="startDate"
								value=""  size="100" maxlength="10"
								style="width: 100px; text-align: center;"
								 onchange="return validateDate(this);" />
							</td>
						<!-- <td id="startDate" align="center" ><input type="text"  name="startDate"  maxlength="100" align="center" style="text-align:center;width:100%;" onchange="return validateDate(this);" ></td>-->
					    </tr>
					</table>
					<center>
						<FONT color="#6699CC">__________________________________________________________________</FONT>
			
		<CENTER>
			<input type="button" class="button" style="width: 80px"
				value="�����ż�" name="add" onclick="query();" />
		</CENTER>
	</form>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
	
</SCRIPT>
