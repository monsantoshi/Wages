<%@ page language="java" contentType="text/html;charset=TIS-620"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<%
	UserInfo userInfo = (UserInfo)session.getAttribute("UserLogin");
	String userId = userInfo.getUserId();
	String ouCode = userInfo.getOuCode();
	Calendar now = Calendar.getInstance(Locale.US);	
	String year =String.valueOf(now.get(Calendar.YEAR)+543);
%>
<html>
<head>
<title>Report Time Attendance</title>
<!-- You have to include these two JavaScript files from DWR -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="script/dojo.js"></script>
<script type="text/javascript" src="script/payroll_util.js"></script>
<!-- This JavaScript file is generated specifically for your application -->
<script type="text/javascript" src="dwr/interface/WpayPrWorkDayReportService.js"></script>
<script type="text/javascript" src="dwr/interface/FeeWgPrWorkDayService.js"></script>
</head>
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
	var empCodeDataFrom=[];
	var empCodeDataTo=[];
	var orgCodeShowDataF=[];
	var orgCodeShowDataT=[];
	var tempWorkCode=[];
	
function init(){
	var cboOrgFrom = dojo.widget.byId("orgFromCbo");
	var cboEmpFrom = dojo.widget.byId("empCodeFrom");
	dojo.event.connect(cboOrgFrom, "selectOption", "whenSelectOrgOption");
	dojo.event.connect(cboEmpFrom, "selectOption", "whenSelectEmpOption");

	}
function onLoadOrganization(){
	var cboOrgFrom = dojo.widget.byId("orgFromCbo");
	var cboOrgTo = dojo.widget.byId("orgToCbo");
	var cboSource = [];
		<c:forEach items="${OrganizationInSecurity}" var="result" >		 
				cboSource.push(["<c:out value='${result.orgCode}' />"+" "+"<c:out value='${result.orgShowDesc}' />","<c:out value='${result.orgCode}' />"]);
				orgCodeShowDataF.push(["<c:out value='${result.orgCode}' />"]);
		</c:forEach>
		cboOrgFrom.dataProvider.setData(cboSource);
		cboOrgTo.dataProvider.setData(cboSource);
	}
function onLoadEmployee(){
	var comB1=dojo.widget.byId("empCodeFrom");
	var comB2=dojo.widget.byId("empCodeTo");
	var arrData=[]
		<c:forEach items="${WgEmployeeInSecurity}" var="result" >		 
			arrData.push(["<c:out value='${result}' />","<c:out value='${result}' />"]);
			empCodeDataFrom.push(["<c:out value='${result}' />"]);
		</c:forEach>
	comB1.dataProvider.setData(arrData);
	comB2.dataProvider.setData(arrData);
	}

 
function initMonth(){
		var workYear = DWRUtil.getValue("workYear");
		var monthFrom='';
		var monthTo='';
		var tempMonth='';
		
		tempMonth=workYear.substring(2,workYear.length);
		monthTo ='1/'+(parseInt(tempMonth));
		monthFrom = '12/'+tempMonth;
		DWRUtil.setValue("monthFrom",monthTo);
		DWRUtil.setValue("monthTo",monthFrom);
}


var cellFuncs = [	
	
		function(data) { var showWorkDesc ='จำนวนวัน';
							 showWorkDesc =showWorkDesc +''+data.workDesc;
							return "<div><input type='text' name='showWorkDesc' value='"+showWorkDesc+"'  readonly='readonly' style='width:160;text-align: right;right;font-weight:bold;font-size:1 em;border-style : none;background-color : transparent;font-family:Tahoma, sans-serif, 'MS Sans Serif';' /></div>";},
		function(data) { var numWorkFrom ='';
						return writeTextNumWorkFrom(data.workCode);
						},
		function(data) {var temp ='ถึง ';
							return "<div><input type='text' name='temp' value='"+temp+"'  readonly='readonly' style='width:50;text-align: right;right;font-weight:bold;font-size:1 em;border-style : none;background-color : transparent;font-family:Tahoma, sans-serif, 'MS Sans Serif';' /></div>";},
		function(data) {var numWorkTo=''; 
						 return writeTextNumworkTo(data.workCode);
						  },
		function(data) {var day='วัน';
							return "<div><input type='day' name='temp' value='"+day+"'  readonly='readonly' style='width:150;text-align: left;left;font-weight:bold;font-size:1 em;border-style : none;background-color : transparent;font-family:Tahoma, sans-serif, 'MS Sans Serif';' /></div>";
						}		
	]; 
function writeTextNumWorkFrom(workCodeFrom){
		return "<div> <input type='text' name ='numWorkFrom"+workCodeFrom+"'     onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);' onchange='vlidateDay(this)' style='width:70px;text-align: right;' /> </div>";
}
function writeTextNumworkTo(workCodeTo){
		return "<div> <input type='text' name ='numWorkTo"+workCodeTo+"'  onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);' onchange='vlidateDay(this)'  style='width: 70px;text-align: right;' /> </div>";
}
function vlidateDay(element){
		var val = element.value;
		var index = val.indexOf(".");
		if (index != -1){
			element.value = val.substring(0, index) + ".5";
		}
}
//  Start DOJO	
//dojo.addOnLoad(init);
dojo.addOnLoad(initMonth);
dojo.addOnLoad(onLoadOrganization);
dojo.addOnLoad(onLoadEmployee);


 // Even ComboBox valueChange
function whenSelectOrgOption(){
		    	DWRUtil.useLoadingMessage("Loading ...");
				var cbo = dojo.widget.byId("orgFromCbo");
				whenFetchOrganizationTo(cbo.textInputNode.value);
	}
function whenFetchOrganizationTo(orgCode){
		     	DWRUtil.useLoadingMessage("Loading ...");
				var cboTo = dojo.widget.byId("orgToCbo");
				var cboSource = [];
				if(orgCode >cboTo.comboBoxSelectionValue.value){
					cboTo.textInputNode.value = '';
					cboTo.comboBoxSelectionValue.value='';
				}
			<c:forEach items="${OrganizationInSecurity}" var="result" >
				if("<c:out value='${result.orgCode}' />"+" "+"<c:out value='${result.orgDesc}' />" >= orgCode){		 
					cboSource.push(["<c:out value='${result.orgCode}' />"+" "+"<c:out value='${result.orgDesc}' />","<c:out value='${result.orgCode}' />"]);
					orgCodeShowDataT.push(["<c:out value='${result.orgCode}' />"]);
				}
			</c:forEach>
				cboTo.dataProvider.setData(cboSource);
		     	//SuUserOrganizationService.findOrganizationByUserIdAndOuCodeToOrgCode('<%=userId%>','<%=ouCode%>',orgCode , {callback:whenFetchOrganizationToCallback});
	}	
function whenFetchOrganizationToCallback(data){
		    	try{
			     	var cboSource = [];
			     	var cboTo = dojo.widget.byId("orgToCbo");
			     	for(var i=0; i<data.length; i++){
			     		var org = data[i];
			     		cboSource.push([org.orgCode, org.codeSeq]);
			     		orgCodeShowDataT.push([org.orgCode]);
						
			     	}
			     	cboTo.dataProvider.setData(cboSource);
		     	}catch(e){
		     		alert(e.message);
		     	}
}
function whenSelectEmpOption(){
	DWRUtil.useLoadingMessage("Loading ...");
	var cbo = dojo.widget.byId("empCodeFrom");
	whenFetchEmployeeTo(cbo.textInputNode.value);
	}
function whenFetchEmployeeTo(empCode){
	DWRUtil.useLoadingMessage("Loading ...");
	var cboTo = dojo.widget.byId("empCodeTo");
	var arrData=[];
	if( empCode > cboTo.comboBoxSelectionValue.value ){
		cboTo.textInputNode.value = '';
		cboTo.comboBoxSelectionValue.value = '';
	}
	<c:forEach items="${WgEmployeeInSecurity}" var="result" >
		if("<c:out value='${result}' />" >= empCode){		 
			arrData.push(["<c:out value='${result}' />","<c:out value='${result}' />"]);
			empCodeDataTo.push(["<c:out value='${result}' />"]);
		}
	</c:forEach>
	cboTo.dataProvider.setData(arrData);
	//PnEmployeeService.findToEmpBySecurity('<%=userId%>','<%=ouCode%>',empCode , {callback:whenFetchEmployeeToCallback});
}
function whenFetchEmployeeToCallback(data){
		    	try{
		    		var arrData=[];
			     	var cboTo = dojo.widget.byId("empCodeTo");
			     	for(i=0;i<data.length;i++){
						arrData.push([data[i].empCode,data[i].empCode]);
						empCodeDataTo.push([data[i].empCode]);
						}
			     	cboTo.dataProvider.setData(arrData);
		     	}catch(e){
		     		alert(e.message);
		     	}
}
</script>
<body>
<table width="100%" >
<tr>
<td class="font-head">[ CTWGPAYRP004 ]  รายงานข้อมูลจำนวนวันทำงานในแต่ละเดือนของลูกจ้าง</td>
</tr>
<tr>  
<td>
<form action="wgpayPr004Rpt.htm?reqCode=doPrintReport" method="post" name="mainform"  ><div style="height:400px;">
	<table  width="100%" align="center" border="0">
	<tr>
		<td aling ="center"> <input type="hidden" name="ouCode" value="<%=ouCode%>"><input type="hidden" name="userId" value="<%=userId%>">
 			<table width="800" align="center" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td align="right" class="font-field">ประจำปี&nbsp;</td>
					<td colspan="3" ><input type="text" name="workYear" style="text-align: center;"  value="<%=year%>" size="4" maxlength="4" onkeyup="if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"  onChange="onChangeMonth(this.value);" /></td>
				</tr>
		
				<tr>
					<td class="font-field"  align="right">ตั้งแต่เดือน/ปี&nbsp;</td>
					<td><input type="text" name="monthFrom" style="text-align: center;"  size="5" maxlength="5"/></td>
					<td class="font-field">ถึงเดือน/ปี</td>
					<td ><input type="text" name="monthTo" style="text-align: center;"  size="5" maxlength="5"/></td>
					<input type="hidden" name="workYearFrom" ><input type="hidden" name="workYearTo" >
					<input type="hidden" name="monthTempFrom" ><input type="hidden" name="monthTempTo" >
				</tr>
				<tr>
            		<td class="font-field"  >ตั้งแต่สังกัดปฎิบัติงาน</td>
            		<td colspan="3" nowarp><select  dojotype="ComboBox"  widgetid="orgFromCbo" style="width:570;" ></select></td>
				</tr>
				<tr>
            		<td class="font-field" >ถึงสังกัดปฎิบัติงาน </td>
            		<td colspan="3" nowarp><select  dojotype="ComboBox" widgetid="orgToCbo" style="width:570;"></select></td>
					<input type="hidden" name="orgCodeFrom" ><input type="hidden" name="orgCodeTo" >
          		</tr>
				<tr >
					<td width="160" class="font-field">ตั้งแต่เลขประจำตัว</td>
					<td width="150" align="left"><SELECT  widgetId="empCodeFrom" dojoType="ComboBox" style="width:140"></SELECT>	</td>
					<td width="100" class="font-field" >ถึงเลขประจำตัว</td>
					<td width="350" align="left"><SELECT  widgetId="empCodeTo" dojoType="ComboBox" style="width:140"></SELECT></td>
					<input type="hidden" name="empF" ><input type="hidden" name="empT" >
				</tr>
				<tr><td colspan="4">
						<table   border="0" cellpadding="2" cellspacing="2">
							<thead>
									<th width="160"></th>
									<th width="70" ></th>
									<th width="50"></th>
									<th width="80" ></th>
									<th width="350"></th>
							</thead>
							<tbody id="dataTable" >
							</tbody>
						</table>
					</td>
				</tr>
			
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<input type="hidden" name="choice" >
					<td colspan="4" align="center"><input type="button" class=" button "  value=" แสดงรายงาน " onclick="whenLoadTaReport();" /></td>
				
				</tr>
 			</table>
		</td>
	</tr>

	<table>
	</table>
		
	</form>
</td>
</tr>
</table>
</body>
<script>
	function onChangeMonth(month){
		var monthFrom='';
		var monthTo='';
		var tempMonth='';
		if (month.length < 4){
			alert("ปีพ.ศ.ต้องมี 4 หลัก");
		}else{
			tempMonth=month.substring(2,month.length);
			monthTo ='1/'+(parseInt(tempMonth));
			monthFrom = '12/'+tempMonth;
			DWRUtil.setValue("monthFrom",monthTo);
			DWRUtil.setValue("monthTo",monthFrom);
			
		}
	}
	function whenLoadTaReport(){
		var frm = document.forms[0];
		var workYear = DWRUtil.getValue("workYear");
		var monthFrom = DWRUtil.getValue("monthFrom");
		var monthTo  =DWRUtil.getValue("monthTo");
		var comB1=dojo.widget.byId("empCodeFrom").textInputNode.value;
		var comB2=dojo.widget.byId("empCodeTo").textInputNode.value;
		var nameOrgFrom = dojo.widget.byId("orgFromCbo").textInputNode.value;
		var nameOrgTo = dojo.widget.byId("orgToCbo").textInputNode.value;
		var workYear=frm.workYear.value;
		var orgTempF="";
		var orgTempT="";
		var empCF="";
		var empCT="";
		
		var orgCodeFrom = "";
		var orgCodeTo = "";
		var check = false;
		var codeFrom="";
		var codeTo="";
//   for check Month
		var tempMonthFrom ='';
		var tempMonthTo = '';
		
		var tempYearFrom;
		var tempYearTo;
		var tempYear ='';
		var tempYearFront ='';
		var calYear = '';
		
		var calMonthFrom =0;
		var calMonthTo =0;
		var sumMonth = 0;
//     fix choice sql
		var choice=''; 	
		
		if(workYear==''){
 			alert("กรุณากรอกปี");
			return false;
		}else if (workYear.length < 4){
			alert("ปีพ.ศ.ต้องมี 4 หลัก");
			return false;
		}
		if(monthFrom == '' || monthTo == ''){
			alert("กรุณากรอกค่า  เดือน/ปี");
			return false;
		}else{
			tempYear=workYear.substring(2,workYear.length);
			tempYearFront = workYear.substring(0,workYear.length-2);
			if( checkMonth(monthFrom)){
				alert("กรุณากรอก เดือน/ปี ใหม่ ");
				return false;
			}
			
			if( checkMonth(monthTo)){
				alert("กรุณากรอก เดือน/ปี ใหม่ ");
				return false;
			}
			
			if(monthFrom.length ==5 ){
				tempYearFrom = monthFrom.substring(3,monthFrom.length);
				tempMonthFrom = monthFrom.substring(0,monthFrom.length-3);
				if(!isNumber(tempMonthFrom)){
					alert("เดือน/ปี ต้องเป็นต้วเลข");
					return false;
				}
				if(!isNumber(tempYearFrom)){
					alert("เดือน/ปี ต้องเป็นต้วเลข");
					return false;
				}
				
			}else if(monthFrom.length == 4){
				tempYearFrom = monthFrom.substring(2,monthFrom.length);
				tempMonthFrom = monthFrom.substring(0,monthFrom.length-3);
				if(!isNumber(tempMonthFrom)){
					alert("เดือน/ปี ต้องเป็นต้วเลข");
					return false;
				}
				if(!isNumber(tempYearFrom)){
					alert("เดือน/ปี ต้องเป็นต้วเลข");
					return false;
				}
			}
			
			if(monthTo.length ==5 ){
				tempYearTo = monthTo.substring(3,monthTo.length);
				tempMonthTo = monthTo.substring(0,monthTo.length-3);
				if(!isNumber(tempMonthTo)){
					alert("เดือน/ปี ต้องเป็นต้วเลข");
					return false;
				}
				if(!isNumber(tempYearTo)){
					alert("เดือน/ปี ต้องเป็นต้วเลข");
					return false;
				}
				
			}else if(monthTo.length == 4){
				tempYearTo = monthTo.substring(2,monthTo.length);
				tempMonthTo = monthTo.substring(0,monthTo.length-3);
				if(!isNumber(tempMonthTo)){
					alert("เดือน/ปี ต้องเป็นต้วเลข");
					return false;
				}
				if(!isNumber(tempYearTo)){
					alert("เดือน/ปี ต้องเป็นต้วเลข");
					return false;
				}
			}
			
			if(tempYearFrom > tempYearTo){
				alert("เดือน/ปี เริ่มต้นต้องน้อยกว่า เดือนปี สิ้นสุด");
				return false;
			}
			if(tempYearFrom == tempYearTo){
				if(tempYearTo != tempYear){
					alert("เดือน/ปี ต้องเป็นปีเดียวกันกับ ประจำปี");
					return false;
				}
				if((tempMonthTo-tempMonthFrom)< 0  ){
					alert("เดือน เริ่มต้นต้องน้อยกว่าเดือนสิ้นสุด");
					return false;
				}else{
					for(var i=tempMonthFrom;i <= tempMonthTo;i++){
						sumMonth++;
						
					}
				}
				choice=1;
			}
			if(tempYearTo > tempYearFrom ){
				calYear = tempYear  - tempYearFrom;
			
				if(calYear>1){
					alert("เดือน/ปี เริ่มต้นต่างกับ ประจำปี ได้ไม่เกิน 1 ปี");	
					return false;
				}
				calYear = tempYearTo - tempYear;
				if(calYear>1){
					alert("เดือน/ปี สิ้นสุดต่างกับ ประจำปี ได้ไม่เกิน 1 ปี");	
					return false;
				}
				calYear = tempYearTo - tempYearFrom;
				if(calYear>1){
					alert("เดือน/ปี ต่างกันได้ไม่เกิน 1 ปี");
					return false;
				}else{
					for(var k = tempMonthFrom; k < 13;k++){
						calMonthFrom ++;
					}
					 for(var y =1 ;y <= tempMonthTo;y++){
						calMonthTo++;
					}
					sumMonth = 	calMonthFrom + calMonthTo;
					if(sumMonth>12){
						alert("จำนวน เดือน รวมแล้วต้องไม่เกิน 12 เดือน");
						return false;
					}
				}
				choice=2;
			}
			tempYearFrom =tempYearFront+tempYearFrom; 
			tempYearTo = tempYearFront+tempYearTo; 
			
		}
		if(nameOrgFrom!=''){
			dojo.widget.byId("orgFromCbo").comboBoxSelectionValue.value = splitCombo(dojo.widget.byId("orgFromCbo").textInputNode.value);
			orgCodeFrom = splitCombo(dojo.widget.byId("orgFromCbo").textInputNode.value);
			
		}
		if(nameOrgTo !=''){
			dojo.widget.byId("orgToCbo").comboBoxSelectionValue.value = splitCombo(dojo.widget.byId("orgToCbo").textInputNode.value);
			orgCodeTo =  splitCombo(dojo.widget.byId("orgToCbo").textInputNode.value);
			
		}
		
		if(dojo.widget.byId("orgToCbo").textInputNode.value!=""){
			if(dojo.widget.byId("orgFromCbo").textInputNode.value==""){
				alert("กรุณากรอก สังกัดปฎิบัติงานเริ่มต้น ");
				return false;
			}
		}
		if(comB1!=''){
			dojo.widget.byId("empCodeFrom").comboBoxSelectionValue.value = splitCombo(dojo.widget.byId("empCodeFrom").textInputNode.value);
			codeFrom = splitCombo(dojo.widget.byId("empCodeFrom").textInputNode.value);
			
		}
		if(comB2 !=''){
			dojo.widget.byId("empCodeTo").comboBoxSelectionValue.value = splitCombo(dojo.widget.byId("empCodeTo").textInputNode.value);
			codeTo =  splitCombo(dojo.widget.byId("empCodeTo").textInputNode.value);
			
		}
		if(dojo.widget.byId("empCodeTo").textInputNode.value!=""){
			if(dojo.widget.byId("empCodeFrom").textInputNode.value==""){
				alert("กรุณากรอก เลขประจำตัวเริ่มต้น ");
				return false;
			}
		}


		forController(tempYearFrom,tempYearTo,tempMonthFrom,tempMonthTo,orgCodeFrom,orgCodeTo,codeFrom,codeTo,choice);
		frm.target = "_blank";
		frm.submit();	
	}
	function forController(tempYearFrom,tempYearTo,tempMonthFrom,tempMonthTo,orgCodeFrom,orgCodeTo,codeFrom,codeTo,choice){
		DWRUtil.setValue("workYearFrom",tempYearFrom);
		DWRUtil.setValue("workYearTo",tempYearTo);
		DWRUtil.setValue("monthTempFrom",tempMonthFrom);
		DWRUtil.setValue("monthTempTo",tempMonthTo);
		DWRUtil.setValue("orgCodeFrom",orgCodeFrom);
		DWRUtil.setValue("orgCodeTo",orgCodeTo);
		DWRUtil.setValue("empF",codeFrom);
		DWRUtil.setValue("empT",codeTo);
		DWRUtil.setValue("choice",choice);
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
	
	function checkMonth(str){
		var tempStr ="/";
		var sizeIs = str.length;
		var temp;
		var check = true;
		for(var i=0;i<sizeIs;i++){
			temp = str.substring(i,i+1);
			if( temp == '/'){
				check = false;
				break;
			}
		}
		
		return check;
	}
	
</script>
</html>