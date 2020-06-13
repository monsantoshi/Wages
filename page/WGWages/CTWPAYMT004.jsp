<%@ page language="java" contentType="text/html;charset=TIS-620" %>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@ page import="com.ss.tp.security.ProcessResult" %>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<html>
<head>
<title>บันทึกจำนวนวันทำงานในแต่ละเดือนของลูกจ้าง</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="script/dojo.js"></script>
<!-- Javascript Script File -->
<script type="text/javascript" src="dwr/interface/FeeWpayPrWorkDayService.js"></script>
<script type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></script>
<script type="text/javascript" src="script/gridScript.js"></script>
<script type="text/javascript" src="page/NavigatePage.jsp"></script>
<script type="text/javascript" src="script/payroll_util.js"></script>
<script type="text/javascript">
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
</head>
<body>
<%
	Calendar now = Calendar.getInstance(Locale.US);	
	UserInfo uf =  (UserInfo)request.getSession().getAttribute("UserLogin");	
	String userId =  uf.getUserId();
	String ouCode =  uf.getOuCode();
	String type = "";
	String hidPage = request.getParameter("hidPage")==null?"":request.getParameter("hidPage");
	String pageEdit = request.getParameter("pageEdit")==null?"0":request.getParameter("pageEdit");
	String year = request.getParameter("hidWorkYear")==null?String.valueOf(now.get(Calendar.YEAR)+543):request.getParameter("hidWorkYear");
	String month = request.getParameter("hidWorkMonth")==null?String.valueOf(now.get(Calendar.MONTH)+1):request.getParameter("hidWorkMonth");
	String areaCode = request.getParameter("areaCode")==null?"":request.getParameter("areaCode");
	String areaLabel = request.getParameter("areaLabel")==null?"":request.getParameter("areaLabel");	
	String secCode = request.getParameter("secCode")==null?"":request.getParameter("secCode");
	String secLabel = request.getParameter("secLabel")==null?"":request.getParameter("secLabel");	
	String workCode = request.getParameter("workCode")==null?"":request.getParameter("workCode");
	String workLabel = request.getParameter("workLabel")==null?"":request.getParameter("workLabel");
	if (request.getSession().getAttribute("processResult") != null){
		ProcessResult processResult = (ProcessResult) request.getSession().getAttribute("processResult");
		type = processResult.getType();
		year =  request.getParameter("hidYear")==null?String.valueOf(now.get(Calendar.YEAR)+543):request.getParameter("hidYear");
		month =  request.getParameter("hidMonth")==null?String.valueOf(now.get(Calendar.MONTH)+1):request.getParameter("hidMonth");
	}
	boolean isAddWorkDays = ((Boolean)session.getAttribute("isAddWorkDays")).booleanValue();
%>
<table>
	<tr>
		<td class="font-head">[ CTWPAYMT004 ]  บันทึกจำนวนวันทำงานในแต่ละเดือนของลูกจ้าง</td>
	</tr>
</table>
<form name="form1" action="security.htm" method="post">
<input type="hidden" name="hidMonth" value="<%=month%>"/>
<table width="800" border="0" align="center" cellspacing="1">
	<tr>
    	<td class="font-field" align="right">ประจำปี&nbsp</td>
    	<td align="left">
    		<input type="text" name="workYear" value="<%=year%>" size="2" maxlength="4" style="width: 70px;text-align: center;" onkeyup="if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" />
    	</td>
	    <td class="font-field" align="right">เดือนเริ่มต้น</td>
		<td align="left">
			<select name="workMonth" onChange="whenClickShowData();">	
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
				document.forms["form1"].workMonth.value = document.forms["form1"].hidMonth.value;
			</script>
		</td>
		<td>&nbsp</td>
  	</tr>
  	<tr>
  		<td width="200" class="font-field" align="right">สพ./ปจ.&nbsp;</td>
  		<td colspan="4">
  			<select widgetId="areaCode" dojoType="ComboBox"  style="width:450;" onBlurInput="assignSecCode(this);" onChange="assignSecCode(this);"></select>
		</td>
  	</tr>
	<tr>
  		<td width="200" class="font-field" align="right">ส่วน/ที่ทำการ&nbsp;</td>
  		<td colspan="4">
  			<select widgetId="secCode" dojoType="ComboBox"  style="width:450;" onBlurInput="assignWorkCode(this);" onChange="assignWorkCode(this);"></select>
		</td>
  	</tr>
	<tr>
  		<td width="200" class="font-field" align="right">แผนก&nbsp;</td>
  		<td colspan="3">
  			<select widgetId="workCode" dojoType="ComboBox"  style="width:450;" ></select>
		</td>
	  	<td align="left">
	  		<input type="button" value="ค้นหา" class="button" onclick="whenClickShowData();" />
			<input type="hidden" name="hidSearchMonth"/>	
			<input type="hidden" name="hidSearchYear"/>
	  	</td>
  	</tr>
	<input type="hidden" name="hidOuCode" value="<%=ouCode%>"/>	
	<input type="hidden" name="hidEmpCode" />	
	<input type="hidden" name="hidPage" value="<%=hidPage%>"/>
	<input type="hidden" name="userId" value="<%=userId%>"/>
	<input type="hidden" name="hidYear" value="<%=year%>"/>
	<input type="hidden" name="type" value="<%=type%>"/>
	<input type="hidden" name="areaCode" value="<%=areaCode%>"/>
	<input type="hidden" name="areaLabel" value="<%=areaLabel%>"/>
	<input type="hidden" name="secCode" value="<%=secCode%>"/>	
	<input type="hidden" name="secLabel" value="<%=secLabel%>"/>
	<input type="hidden" name="workCode" value="<%=workCode%>"/>
	<input type="hidden" name="workLabel" value="<%=workLabel%>"/>
</table>
<br/>
<div style="height:310px;overflow:auto;">
<table bordercolor="#6699CC" cellpadding="2" cellspacing="0"  width="600" align="center" border="1">
	<thead>
		<tr CLASS="TABLEBULE2">
			<th><input type="checkbox" name="selectAll" onclick="doSelectAll(document.forms['form1']);"/></th>
			<th>เลขประจำตัว</th>
			<th>ชื่อ - นามสกุล</th>
			<th>แก้ไข</th>
		</tr>
	</thead>
	<tbody id="dataTable">
	</tbody>	
</table>
</div>
<div>
<table width="600" align="center"  cellpadding="2" cellspacing="0" >
	<tr>
		<td align="right">
			<input type="hidden" name="page" value="<%=pageEdit%>">
			<input type="hidden" name="maxPage">
			<input type="hidden" name="countData" >
			<input type="hidden" name="dataPerPage" value="10">
			<input type="button" disabled="disabled" class=" button " value="First" name="first" onclick="onFirst(whenShowDataTable);"/>
			<input type="button" disabled="disabled" class=" button " value="<<" name="previous" onclick="onPrevious(whenShowDataTable);"/>
			<input type="text"  name="showPage" style="text-align:right;width: 30;" onkeypress="onKeyGoPage(event,whenShowDataTable);"/>
			/
			<input type="text"  name="showMaxPage" readonly="readonly" style="width: 30;border-style : none;background-color : transparent;text-align:right;font-weight:bold;"/>
			<input type="button" disabled="disabled" class=" button " value=">>" name="next" onclick="onNext(whenShowDataTable);" />
			<input type="button" disabled="disabled" class=" button " value="Last" name="last" onclick="onLast(whenShowDataTable);"/>
		
		</td>
	</tr>
</table>
</div>
<table width="100%" CLASS="TABLEBULE2">
	<tr CLASS="TABLEBULE2">
		<td>
			<input type="button" value="เพิ่มข้อมูล" class="button"  name="add" onclick="whenClickAdd()"/>
			<!--<input type="button" value="ลบข้อมูล" class="button" name="delete" onclick="if(checkBefore(document.forms['form1']))whenClickDel();"/>-->
			<input type="button" value=" แสดงรายงาน " class="button"  name="add" onclick="whenShowReport()"/>			
		</td>
	</tr>
</table>
</form>
</body>
</html>
<script type="text/javascript">	
	onLoadPage();	
dojo.addOnLoad(setSec);
dojo.addOnLoad(setWork);
	function setSec(){
		var ouCode  = DWRUtil.getValue("hidOuCode");
		var userId   = DWRUtil.getValue("userId");
		SuUserOrganizationService.findSecByDiv(ouCode, null,userId, {callback:whenshowSecHandler});
	}
	function setWork(){
		var ouCode  = DWRUtil.getValue("hidOuCode");
		var userId   = DWRUtil.getValue("userId");
		SuUserOrganizationService.findWorkCodeByAreaAndSecCode(ouCode, null, null,userId, {callback:whenShowWorkCodeHandler});
	}	
	function onLoadPage(){
		dojo.addOnLoad(whenFindOrganization);
		if( '<%= isAddWorkDays %>' == 'true' )
			alert("!!! ท่านยังบันทึกวันทำงานให้ไม่ครบทุกคน ให้เปลี่ยนเดือนเป็นเดือนหน้า แล้วคลิกเพิ่มข้อมูล !!!")
	}
	var arrData=[];
	var arrValue=[];
	var arrName=[];
	function whenFindOrganization(){
		var frm=document.forms[0];
		var comB1=dojo.widget.byId("areaCode");
		<c:forEach items="${OrganizationInSecurity31}" var="result" >		 
			arrData.push(["<c:out value='${result.areaCode}' />"+" "+"<c:out value='${result.orgShowDesc}' />","<c:out value='${result.areaCode}' />"]);
			arrValue.push(["<c:out value='${result.areaCode}' />"]);
			arrName.push(["<c:out value='${result.areaCode}' />"+" "+"<c:out value='${result.orgShowDesc}' />"]);
		</c:forEach>
		comB1.dataProvider.setData(arrData);
		var frm = document.forms[0];
		if (frm.hidPage.value != ''){	// check ว่าเคยกดปุ่มค้นหาหรือยัง ก่อหน้าที่ user จะเข้ามาที่หน้าจอ insert, update & delete
			var areaCode = DWRUtil.getValue("areaCode");
			var areaLabel = DWRUtil.getValue("areaLabel");
			var secCode = DWRUtil.getValue("secCode");
			var secLabel = DWRUtil.getValue("secLabel");
			var workCode = DWRUtil.getValue("workCode");
			var workLabel = DWRUtil.getValue("workLabel");
			if (areaCode != ''){ 
				dojo.widget.byId("areaCode").comboBoxSelectionValue.value = areaCode;
				dojo.widget.byId("areaCode").textInputNode.value = areaLabel
				assignSecCode(comB1);
			}
			if(secCode != ''){
				var comB2 = dojo.widget.byId("secCode");
				comB2.comboBoxSelectionValue.value = secCode;
				comB2.textInputNode.value  = secLabel;
				assignWorkCode(comB2);
			}
			if (workCode != ''){
				var comB3 = dojo.widget.byId("workCode");
				comB3.comboBoxSelectionValue.value = workCode;
				comB3.textInputNode.value  = workLabel;
			}
			whenShowDataTable();
		}
		checkSession();
	}
	 function backForm(){
		 window.history.back();
	 }
	function whenClickShowData(){
		DWRUtil.setValue("page", "0");
		whenShowDataTable();
	}
	
	
	
	function whenShowDataTable(){	
		var frm = document.forms[0];
		var workYear = DWRUtil.getValue("workYear");
		var workMonth = DWRUtil.getValue("workMonth");
		var areaCode = splitCombo(dojo.widget.byId("areaCode").textInputNode.value);
		DWRUtil.setValue("hidSearchMonth", workMonth);
		DWRUtil.setValue("hidSearchYear", workYear);
		if (!isNumber(workYear) || workYear == ''){
			alert("ปีต้องเป็นตัวเลข");
			return false;
		}else if (workYear.length < 4){
			alert("ปีพ.ศ.ต้องมี 4 หลัก");
			return false;
		}else if (frm.workMonth.value == 0){
			alert("กรุณาเลือกเดือน");
			return false;
		}else{
			frm.selectAll.checked = false;
			var secCode = splitCombo(dojo.widget.byId("secCode").textInputNode.value);
			var workCode = splitCombo(dojo.widget.byId("workCode").textInputNode.value);
			var userId = DWRUtil.getValue("userId");
			var maxRowPerPage = DWRUtil.getValue("dataPerPage");
			var pageNow = DWRUtil.getValue("page");
			var ouCode = DWRUtil.getValue("hidOuCode");
			FeeWpayPrWorkDayService.listMonthEmpWork(ouCode, workYear, workMonth, areaCode, secCode, workCode, userId, maxRowPerPage, pageNow, {callback:whenListDataTableHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		}
	}
	
	var cellFuncs = [
		function(data) { return writeCheckBox(data.empCode);},
		function(data) { return "<div align='center'>"+data.empCode+ "</div>";},
		function(data) { return (data.prefixName + data.firstName + ' ' + data.lastName) },		
		function(data) { return writeButton(data.empCode);}
	];
		
	function whenListDataTableHandler(data){
	     countData();
		if(data.length > 0){
			DWRUtil.removeAllRows("dataTable");
			DWRUtil.addRows("dataTable",data,cellFuncs);
			if(DWRUtil.getValue("showMaxPage") == ''){
				countData();
			}else{
				onCheckButt("form1");
			}
		}else{
			alert('ไม่พบข้อมูล');
			DWRUtil.removeAllRows("dataTable");
			if(DWRUtil.getValue("showMaxPage") == ''){
				countData();
			}else{
				onCheckButt("form1");
			}
		}
	}
	
	
	function countData(){
		var ouCode = DWRUtil.getValue("hidOuCode");
		var workYear = DWRUtil.getValue("workYear");
		var workMonth = DWRUtil.getValue("workMonth");
		var areaCode = splitCombo(dojo.widget.byId("areaCode").textInputNode.value);
		var secCode = splitCombo(dojo.widget.byId("secCode").textInputNode.value);
		var workCode = splitCombo(dojo.widget.byId("workCode").textInputNode.value);
		var userId = DWRUtil.getValue("userId");
		FeeWpayPrWorkDayService.getCountTaMonthEmpWork(ouCode, workYear, workMonth, areaCode, secCode, workCode, userId, {callback:countDataHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}})
	}

	function countDataHandler(data){
		DWRUtil.setValue("countData", data);
		onCheckButt("form1");	  
	}

	function writeCheckBox(emp){
		return "<div align='center'><input type='checkbox' name ='chk' empId='"+emp+"'  onclick='doCheck(document.forms[\"form1\"]);' /></div>";
	}
	
	function writeButton(empCode){		
		return "<div align='center'><input type='button' class='button' name = '" + empCode + "' value='...' onclick='whenClickEditButton(this)'/></div>";
	}
	
	function whenCheckStausUpdate(data){
		if (data == 'Y'){
			alert("ข้อมูลของงวดและปีนี้ถูก  โอนไประบบค่าจ้างแล้ว !!! ไม่สามารถแก้ไขข้อมูลได้");
			//whenShowDataTable();
		}else{
			var frm = document.forms[0];		
			frm.action = "security.htm?reqCode=CTWPAYUP004";		
			DWRUtil.setValue("hidEmpCode", empNo);
			var comB1 = dojo.widget.byId("areaCode");
			var comB2 = dojo.widget.byId("secCode");
			var comB3 = dojo.widget.byId("workCode");
			frm.areaCode.value = comB1.comboBoxSelectionValue.value;
			frm.areaLabel.value = comB1.textInputNode.value;
			frm.secCode.value = comB2.comboBoxSelectionValue.value;
			frm.secLabel.value = comB2.textInputNode.value;
			frm.workCode.value = comB3.comboBoxSelectionValue.value;
			frm.workLabel.value = comB3.textInputNode.value;
			frm.workMonth.value = DWRUtil.getValue("hidSearchMonth");
			frm.workYear.value  = DWRUtil.getValue("hidSearchYear");
			frm.submit();		
		}
	}
	var empNo;
	function whenClickEditButton(btnVal){
		var frm = document.forms["form1"];
		var workYear = DWRUtil.getValue("hidSearchYear");
		var workMonth = DWRUtil.getValue("hidSearchMonth");
		var workMonth = frm.workMonth.value;
		var userId = frm.userId.value;
		var ouCode = DWRUtil.getValue("hidOuCode");
		empNo = btnVal.name;
		FeeWpayPrWorkDayService.getStatusUpdateEmployee(ouCode, workYear, workMonth, empNo, userId, {callback:whenCheckStausUpdate});
	}
	function whenClickDel(){
		var frm = document.forms["form1"];
		var comB1 = dojo.widget.byId("areaCode");
		var areaCode = splitCombo(comB1.textInputNode.value);
		var areaLabel = dojo.widget.byId("areaCode").textInputNode.value;
		var secCode = splitCombo(dojo.widget.byId("secCode").textInputNode.value);
		var workCode = splitCombo(dojo.widget.byId("workCode").textInputNode.value);
		var workYear = frm.workYear.value;
		var workMonth = frm.workMonth.value;
		var userId = frm.userId.value;
		var ouCode = DWRUtil.getValue("hidOuCode");
		FeeWpayPrWorkDayService.getStatusBeforeAddEmployee(ouCode, workYear, workMonth, areaCode, secCode, workCode, userId, {callback:whenCheckStausDel});
	}
	
	function whenCheckStausDel(data){
		if (data == 'Y'){
			alert("ข้อมูลของงวดและปีนี้ โอนไประบบค่าจ้างแล้ว !!! ไม่สามารถลบข้อมูลได้");
		}else if(data == 'NOT_FOUND'){
			alert("ไม่พบข้อมูล กรุณาเลือกข้อมูลใหม่อีกครั้ง");
		}else{
			onDelete();
		}
	}
	function onDelete(){
			var tab = $('dataTable');
			var row;
			var empList=[];
			var frm = document.forms["form1"];
			var chk = frm.elements["chk"];
			var arrEmpCode = [];
			var userId = frm.userId.value;
			DWREngine.setErrorHandler(eh);
			DWREngine.beginBatch();
			if(tab.rows.length>1){
    			for(i=0; i<tab.rows.length; i++){
					row = tab.rows[i];	
					if (chk[i].checked){
						arrEmpCode.push(chk[i].empId);
					}
				}				
			}else{
				if(tab.rows.length==1){
					row = tab.rows[0];	
					if (chk.checked){
						arrEmpCode.push(chk.empId);
					}
				}	
			}	
			var month = DWRUtil.getValue("hidSearchMonth");
			var year = DWRUtil.getValue("hidSearchYear"); 
			FeeWpayPrWorkDayService.deleteTaMonthEmpWork(DWRUtil.getValue("hidOuCode"), year, month, arrEmpCode, userId, {callback:onDeleteCallback});
			DWREngine.endBatch();
	}
	
	function eh(){
		alert("เกิดปัญหาในการลบข้อมูล  ระบบจะทำการกู้ข้อมูลเดิมกลับมา");
	}
	
	function onDeleteCallback(data){			
		whenShowDataTableAfterDelete();
		if (data == ''){
			alert("ลบข้อมูลเรียบร้อย");
		}else{
			alert("เลขประจำตัว\n" + data + "ไม่สามารถลบได้เนื่องจากข้อมูลถูกปิดแล้ว");
		}
	}
	function validateForm(){
		var frm = document.forms["form1"];
		var workYear = frm.workYear.value;
		var workMonth = frm.workMonth.value;
		if (!isNumber(workYear) || workYear == ''){
			alert("ปีต้องเป็นตัวเลข");
			return false;
		}else if (workYear.length < 4){
			alert("ปีพ.ศ.ต้องมี 4 หลัก");
			return false;
		}else if (workMonth == 0){
			alert("กรุณาเลือกเดือน");
			return false;
		}else{
			frm.action = "security.htm?reqCode=CTWPAYIN004";
			frm.selectAll.checked = false;
			var comB1 = dojo.widget.byId("areaCode");
			var comB2 = dojo.widget.byId("secCode");
			var comB3 = dojo.widget.byId("workCode");
			frm.areaCode.value = comB1.comboBoxSelectionValue.value;
			frm.areaLabel.value = comB1.textInputNode.value;
			frm.secCode.value = comB2.comboBoxSelectionValue.value;
			frm.secLabel.value = comB2.textInputNode.value;
			frm.workCode.value = comB3.comboBoxSelectionValue.value;
			frm.workLabel.value = comB3.textInputNode.value;
			frm.submit();
		}
	}
	function whenCheckStaus(data){
	    //alert("CheckStatus");
		if (data == 'Y'){
			alert("ข้อมูลของงวดและปีนี้ถูก  โอนไประบบค่าจ้างแล้ว !!! ไม่สามารถเพิ่มข้อมูลได้");
		}else if(data == 'NOT_FOUND'){
			alert("ไม่พบข้อมูล กรุณาเลือกข้อมูลใหม่อีกครั้ง");
		}else{
			validateForm();
		}
	}
	function whenClickAdd(){
	    // alert("whenClickAdd");
		var frm = document.forms["form1"];
		var comB1 = dojo.widget.byId("areaCode");
		var areaCode = splitCombo(comB1.textInputNode.value);
		var areaLabel = dojo.widget.byId("areaCode").textInputNode.value;
		var secCode = splitCombo(dojo.widget.byId("secCode").textInputNode.value);
		var workCode = splitCombo(dojo.widget.byId("workCode").textInputNode.value);
		var workYear = frm.workYear.value;
		var workMonth = frm.workMonth.value;
		var userId = frm.userId.value;
		var ouCode = DWRUtil.getValue("hidOuCode");
		FeeWpayPrWorkDayService.getStatusBeforeAddEmployee(ouCode, workYear, workMonth, areaCode, secCode, workCode, userId, {callback:whenCheckStaus});
	}
	
	function isNumber(num){
		var realNumber = "1234567890";
		var length = num.length;		
		var temp;
		var check = true;
		for(var i=0;i<length;i++){
			temp = num.substring(i,i+1);
			if ( realNumber.indexOf(temp) == -1 ){
				check = false;
				break;
			}
		}
		return check;
	}
	
// for Report
	function whenShowReport(){
			var frm = document.forms[0];
		var workYear = DWRUtil.getValue("workYear");
		var workMonth = DWRUtil.getValue("workMonth");
		var areaCode = splitCombo(dojo.widget.byId("areaCode").textInputNode.value);
		if (!isNumber(workYear) || workYear == ''){
			alert("ปีต้องเป็นตัวเลข");
			return false;
		}else if (workYear.length < 4){
			alert("ปีพ.ศ.ต้องมี 4 หลัก");
			return false;
		}else if (frm.workMonth.value == 0){
			alert("กรุณาเลือกเดือน");
			return false;
		}
			
		var secCode = splitCombo(dojo.widget.byId("secCode").textInputNode.value);
		var workCode = splitCombo(dojo.widget.byId("workCode").textInputNode.value);
		var userId = DWRUtil.getValue("userId");
		var ouCode = DWRUtil.getValue("hidOuCode");
		forController(areaCode, secCode, workCode);
		frm.action="wpayPr0041Rpt.htm?reqCode=doPrintReport";
		frm.target = "_blank";
		frm.submit();
		frm.target = "_self";
	}
	function forController(areaCode, secCode, workCode){
		DWRUtil.setValue("areaCode",areaCode);
		DWRUtil.setValue("secCode",secCode);
		DWRUtil.setValue("workCode",workCode);
	}
	function assignSecCode(comBo){
		var ouCode = DWRUtil.getValue("hidOuCode");
		var areaCode = splitCombo(comBo.textInputNode.value);
		var userId   = DWRUtil.getValue("userId");
	//	dojo.widget.byId("secCode").textInputNode.value = "";
	//	dojo.widget.byId("secCode").comboBoxSelectionValue.value = "";
	//	dojo.widget.byId("workCode").textInputNode.value = "";
	//	dojo.widget.byId("workCode").comboBoxSelectionValue.value = "";
		SuUserOrganizationService.findSecByDiv(ouCode, areaCode,userId, {callback:whenshowSecHandler});
	}
	function whenshowSecHandler(data){
		var frm=document.forms[0];
		var comB2=dojo.widget.byId("secCode");
		var arrSecCode = [];
		if (data != ''){
			for(i=0;i<data.length;i++){
				arrSecCode.push([data[i].secCode + " " + data[i].secDesc, data[i].secCode]);
			}	
			comB2.dataProvider.setData(arrSecCode);
		}else{
			var arrTmp = [];
			dojo.widget.byId("secCode").textInputNode.value = "";
			dojo.widget.byId("secCode").comboBoxSelectionValue.value = "";			
			dojo.widget.byId("secCode").dataProvider.setData(arrTmp);
			dojo.widget.byId("workCode").textInputNode.value = "";
			dojo.widget.byId("workCode").comboBoxSelectionValue.value = "";			
			dojo.widget.byId("workCode").dataProvider.setData(arrTmp);
		}
		assignWorkCode(comB2);
	}
	function assignWorkCode(comBo){
			var ouCode = DWRUtil.getValue("hidOuCode");
			var areaCode = splitCombo(dojo.widget.byId("areaCode").textInputNode.value);
			var secCode = splitCombo(dojo.widget.byId("secCode").textInputNode.value);
			var userId   = DWRUtil.getValue("userId");
		//	dojo.widget.byId("workCode").textInputNode.value = "";
		//	dojo.widget.byId("workCode").comboBoxSelectionValue.value = "";
			SuUserOrganizationService.findWorkCodeByAreaAndSecCode(ouCode, areaCode, secCode,userId, {callback:whenShowWorkCodeHandler});
	
	}
	function whenShowWorkCodeHandler(data){
		var frm = document.forms[0];
		var comB2 = dojo.widget.byId("workCode");
		var arrWorkCode = [];
		if (data != ''){
			for(i=0;i<data.length;i++){
				arrWorkCode.push([data[i].workCode+" "+data[i].workDesc, data[i].workCode]);
			}	
			comB2.dataProvider.setData(arrWorkCode);
		}else{
			var arrTmp = [];
			dojo.widget.byId("workCode").textInputNode.value = "";
			dojo.widget.byId("workCode").comboBoxSelectionValue.value = "";			
			dojo.widget.byId("workCode").dataProvider.setData(arrTmp);
		}
	}
	function checkSession(){
		var type = DWRUtil.getValue("type");
		if (type == "INS_CLOSE"){
			alert("ข้อมูลเดือนและปีนี้ถูกปิดแล้วไม่สามารถเพิ่มได้");
		}else if(type == "UPD_CLOSE"){
			alert("ข้อมูลเดือนและปีนี้ถูกปิดแล้วไม่สามารถแก้ไขได้");
		}
	}
	
	function whenShowDataTableAfterDelete(){
		var frm = document.forms[0];
		var workYear = DWRUtil.getValue("hidSearchYear");
		var workMonth = DWRUtil.getValue("hidSearchMonth");
		var areaCode = splitCombo(dojo.widget.byId("areaCode").textInputNode.value);
		frm.selectAll.checked = false;
		var secCode = splitCombo(dojo.widget.byId("secCode").textInputNode.value);
		var workCode = splitCombo(dojo.widget.byId("workCode").textInputNode.value);
		var userId = DWRUtil.getValue("userId");
		var maxRowPerPage = DWRUtil.getValue("dataPerPage");
		var pageNow = DWRUtil.getValue("page");
		var ouCode = DWRUtil.getValue("hidOuCode");
		FeeWpayPrWorkDayService.listMonthEmpWork(ouCode, workYear, workMonth, areaCode, secCode, workCode, userId, maxRowPerPage, pageNow, {callback:whenListDataTableAfeterDeleteHandler});
	}
	
	function whenListDataTableAfeterDeleteHandler(data){
		DWRUtil.removeAllRows("dataTable");
		DWRUtil.addRows("dataTable",data,cellFuncs);
		countDataAfterDelete();
	}
	
	function countDataAfterDelete(){
		var ouCode = DWRUtil.getValue("hidOuCode");
		var workYear = DWRUtil.getValue("hidSearchYear");
		var workMonth = DWRUtil.getValue("hidSearchMonth");
		var areaCode = splitCombo(dojo.widget.byId("areaCode").textInputNode.value);
		var secCode = splitCombo(dojo.widget.byId("secCode").textInputNode.value);
		var workCode = splitCombo(dojo.widget.byId("workCode").textInputNode.value);
		var userId = DWRUtil.getValue("userId");
		FeeWpayPrWorkDayService.getCountTaMonthEmpWork(ouCode, workYear, workMonth, areaCode, secCode, workCode, userId, {callback:countDataAfterDeleteHandler})
	}

	function countDataAfterDeleteHandler(data){
		DWRUtil.setValue("countData", data);
		onCheckButt("form1");	  
	}
</script>

