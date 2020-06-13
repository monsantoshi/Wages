<%@ page language="java" contentType="text/html;charset=TIS-620" %>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<html>
<head>
<title>Add feeWpayMonthEmpWork</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="script/dojo.js"></script>
<script type="text/javascript" src="script/dateCalendar.js"></script>
<!-- Javascript Script File -->
<script type="text/javascript" src="dwr/interface/FeeWgPrWorkDayService.js"></script>
<script type="text/javascript" src="dwr/interface/FeeWpayPrWorkDayService.js"></script>
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
<%
	Calendar now = Calendar.getInstance(Locale.US);
	String year = request.getParameter("workYear");
	String month = request.getParameter("workMonth");
	UserInfo uf =  (UserInfo)request.getSession().getAttribute("UserLogin");
	String ouCode =  uf.getOuCode();
	String userId =  uf.getUserId();
	String areaCode = request.getParameter("areaCode");
	String secCode = request.getParameter("secCode");
	String workCode = request.getParameter("workCode");
	String areaLabel = request.getParameter("areaLabel");
	String secLabel = request.getParameter("secLabel");
	String workLabel = request.getParameter("workLabel");
%>
<body>
<table>
	<tr>
		<td class="font-head">[ CTWPAYIN004 ]  เพิ่มข้อมูลวันทำงานประจำเดือนของลูกจ้าง</td>
	</tr>
</table>

<form name="hidForm" method="post">		
	<input type="hidden" name="hidWorkYear" value="<%=year%>" />
	<input type="hidden" name="hidWorkMonth" value="<%=month%>" />
	<input type="hidden" name="areaCode" value="<%=areaCode%>"/>
	<input type="hidden" name="secCode" value="<%=secCode%>"/>
	<input type="hidden" name="workCode" value="<%=workCode%>"/>
	<input type="hidden" name="areaLabel" value="<%=areaLabel%>"/>
	<input type="hidden" name="secLabel" value="<%=secLabel%>"/>
	<input type="hidden" name="workLabel" value="<%=workLabel%>"/>
	<input type="hidden" name="hidPage" value="insert"/>
</form>

<form name="form1" action="security.htm" method="post">
<table width="400" border="0" align="center" cellspacing="1">
	<tr>
		<td class="font-field">ประจำปี&nbsp&nbsp</td>
		<td>
			<input type="text" name="year" value="<%=year%>" readonly="readonly" style="width: 100;text-align: center;background-color: silver;" size="2" maxLength="4" onkeyup="if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" />
		</td>
		<td class="font-field">เดือนเริ่มต้น&nbsp&nbsp</td>
		 <td><input type="text" name="month" size="5" readonly="readonly" style="width: 100;text-align: center;background-color: silver;"/></td>
	</tr>
	<tr>
		<td class="font-field">เลขประจำตัว&nbsp&nbsp</td>
		<td >
			<select widgetId="empCode" dojoType="combobox" style="width:100;">
          	</select>
		</td>
		<td colspan="2">
			<input type="text" name="empName" readonly="readonly" style="width: 143;text-align: left;background-color: silver;"/>
		</td>
	</tr>
	<input type="hidden" name="hidEmpCode" />
	<input type="hidden" name="hidOuCode" value="<%=ouCode%>"/>
	<input type="hidden" name="workMonth" value="<%=month%>"/>
	<input type="hidden" name="workYear" value="<%=year%>"/>
	<input type="hidden" name="userId" value="<%=userId%>"/>
	<input type="hidden" name="areaCode" value="<%=areaCode%>"/>
	<input type="hidden" name="secCode" value="<%=secCode%>"/>
	<input type="hidden" name="workCode" value="<%=workCode%>"/>
	<input type="hidden" name="areaLabel" value="<%=areaLabel%>"/>
	<input type="hidden" name="secLabel" value="<%=secLabel%>"/>
	<input type="hidden" name="workLabel" value="<%=workLabel%>"/>
</table>
<br/>
<div style="height:400px;overflow:auto;">
<table bordercolor="#6699CC" cellpadding="2" cellspacing="0"  width="600" align="center" border="1">
	<thead>
		<tr CLASS="TABLEBULE2">
			<th>เดือนที่</th>
			<th>เดือน</th>
			<th>จำนวนวันทำงาน</th>
			<th>หมายเหตุ</th>
		</tr>
	</thead>
	<tbody id="dataTable">
	</tbody>
</table>
</div>
<table width="100%" CLASS="TABLEBULE2">
	<tr CLASS="TABLEBULE2">
		<td>
			<input type="button" value="บันทึกข้อมูล" class="button" name="add" onclick="if(validateForm()) whenClickAdd();"/>
			<input type="button" value="   ออก   " class="button" name="exit" onclick="whenCallback();"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<script>	
	onLoadPage();
	function onLoadPage(){
		var frm = document.forms[1];
		frm.month.value = convertMonth(frm.workMonth.value);
		dojo.addOnLoad(whenFindListEmpIsNotMonth);
	}

	var arrCheck = [];
	function whenFindListEmpIsNotMonth(data){
		var frm=document.forms[0];
		var comB1=dojo.widget.byId("empCode");
		var arrData=[];
		<c:forEach items="${empCodeList}" var="result" >		 
			arrData.push(["<c:out value='${result}' />","<c:out value='${result}' />"]);
			arrCheck.push(["<c:out value='${result}' />"]);
		</c:forEach>
		comB1.dataProvider.setData(arrData);
		dojo.event.connect(comB1, "onBlurInput", "checkEmpName2");
		dojo.event.connect(comB1, "selectOption", "checkEmpName1");
	}
	
	function checkEmpName2(){
		var id = dojo.widget.byId("empCode").getValue();
		if (id != DWRUtil.getValue("hidEmpCode")){
			checkEmpName1();
		}
	}
	function checkEmpName1(){
		dojo.widget.byId("empCode").comboBoxSelectionValue.value = "";
		var check = false;
		var id = dojo.widget.byId("empCode").getValue();
		DWRUtil.setValue("hidEmpCode", id);
		for(i=0;i<arrCheck.length;i++){
			if (arrCheck[i] == id){
				check = true;
				break;
			}
		}
		if (check){
			dojo.widget.byId("empCode").comboBoxSelectionValue.value = id;
			findEmpName();
		}else{
			DWRUtil.setValue("empName","");
			dojo.widget.byId("empCode").setValue("");
			DWRUtil.removeAllRows("dataTable");
		}
		
	}

	function findEmpName(){		
		FeeWpayPrWorkDayService.findEmpName(DWRUtil.getValue("hidOuCode"), dojo.widget.byId("empCode").comboBoxSelectionValue.value, {callback:whenShowEmpName});
	}
	
	function whenShowEmpName(data){				
		DWRUtil.setValue("empName", data);
		FeeWpayPrWorkDayService.findEmpByKey(DWRUtil.getValue("hidWorkYear"), DWRUtil.getValue("hidWorkMonth"), dojo.widget.byId("empCode").comboBoxSelectionValue.value, {callback:whenFindEmpName});
	}
	
	var cellFuncs = [	
		function(data) { return "<div align='center'>"+data.workCode+ "</div>";},
		function(data) { return data.workDesc;},
		function(data) { return writeTextDays(data.workSeq,data.workCode);},
		function(data) { return writeTextNotes(data.workSeq,data.workCode);}					
	];

	function whenFindEmpName(data){	
		FeeWpayPrWorkDayService.findStatusWork(DWRUtil.getValue("hidOuCode"),DWRUtil.getValue("hidWorkMonth") ,{callback:whenShowTable});
	}
	
	function whenShowTable(data){	
		DWRUtil.removeAllRows("dataTable");		
		DWRUtil.addRows("dataTable", data, cellFuncs);
	}
	
	function writeTextDays(workSeq,workCode){
		return "<div align='center'><input type='text' name ='days' workSeq='" + workSeq + "' workCode='" + workCode + "' style='width: 70px;text-align: right;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);' onchange='vlidateDay(this)'/></div>";
	  
	}
	
	function writeTextNotes(workSeq,workCode){
		return "<div align='center'><input type='text' name ='notes' workSeq='" + workSeq + "' workCode='" + workCode + "' style='width: 300px;text-align: left;'/></div>";
	}
	
	function daysInMonth(m, y) { // m is 0 indexed: 0-11
   			 switch (m) {
        	 case 1 :
                 return (y % 4 == 0 && y % 100) || y % 400 == 0 ? 29 : 28;
             case 8 : case 3 : case 5 : case 10 :
                 return 30;
             default :
               return 31
         }
    }

	function isValid(d, m, y) {
	          
	          if (m == '01'){
	              m=0;
	          }else if (m == '02'){
	              m=1;
	          }else if (m == '03'){
	              m=2;
	          }else if (m == '04'){
	              m=3;
	          }else if (m == '05'){
	              m=4;
	          }else if (m == '06'){
	              m=5;
	          }else if (m == '07'){
	              m=6;
	          }else if (m == '08'){
	              m=7;
	          }else if (m == '09'){
	              m=8;
	          }else if (m == '10'){
	              m=9;
	          }else if (m == '11'){
	              m=10;
	          }else if (m == '12'){
	              m=11;
	          }
	          
	          if (d== null){
	            d=0;
	          }
	          var yo = y-543;
	          //alert("m"+ m);
	          //alert("yo"+ yo);
	          //alert(daysInMonth(m,yo))
  			  return m >= 0 && m < 12 && d >= 20 && d <= daysInMonth(m, yo);
	}
	
	
	function vlidateDay(element){
		var val = element.value;
		var index = val.indexOf(".");
		if (index != -1){
			element.value = val.substring(0, index) + ".5";
		}
	}
	
	function validateTime(element){
		var val = element.value;
		var index = val.indexOf(".");
		if (index != -1){
			element.value = val.substring(0, index);
		}
	}
	
	function whenClickAdd(){
		var row;
		var tab = $('dataTable');		
		var ouCode = DWRUtil.getValue("hidOuCode");
		var year = DWRUtil.getValue("hidWorkYear");
		var month = DWRUtil.getValue("hidWorkMonth");
		var empCode = dojo.widget.byId("empCode").comboBoxSelectionValue.value;
		var creby = DWRUtil.getValue("userId");
		var arrDay=[];
		var arrTime=[];
		var arrWorkCode=[];
		var arrWorkSeq=[];	
		var arrNote=[];
		if(tab.rows.length>1){	 
			for(i=0; i<tab.rows.length; i++){
				row = tab.rows[i];		
			   
				if (!isValid(document.all.days[i].value,document.all.days[i].workCode,year)){
						alert("จำนวนวันเกินวันในเดือนนั้น ๆ โปรดตรวจสอบอีกครั้ง");
						document.all.days[i].select();
						return false;
				}else {
				arrDay.push(document.all.days[i].value);
				arrWorkCode.push(document.all.days[i].workCode);
				arrWorkSeq.push(document.all.days[i].workCode);
				arrNote.push(document.all.notes[i].value);
				}
			  
			}
		}
		else{
			if(tab.rows.length==1){
			  
			    if (!isValid(document.all.days.value,document.all.days.workCode,year)){
						alert("จำนวนวันเกินวันในเดือนนั้น ๆ โปรดตรวจสอบอีกครั้ง");
						document.all.days[i].select();
						return false;
				}else {
				  		arrDay.push(document.all.days.value);
						arrWorkCode.push(document.all.days.workCode);
						arrWorkSeq.push(document.all.days.workCode);
						arrNote.push(document.all.notes.value);
				}
			 
			}
		}	 
		DWREngine.setErrorHandler(eh);
		FeeWpayPrWorkDayService.addPrWorkDay(ouCode, year, month, empCode, creby, arrDay, arrWorkCode, arrWorkSeq,arrNote, {callback:whenAddComplete});
	}
	
	function eh() {
		alert("เกิดปัญหาในการบันทึกข้อมูล ระบบจะทำการลบข้อมูลที่ถูกบันทึกไปแล้วออกจากฐานข้อมูล");
	}
	
	function whenAddComplete(data){
		DWRUtil.removeAllRows("dataTable");
		dojo.widget.byId("empCode").setValue("");
		dojo.widget.byId("empCode").textInputNode.value = "";
		DWRUtil.setValue("empName", "");
		var frm = document.forms["form1"];
		frm.action = "security.htm?reqCode=CTWPAYIN004";
		frm.submit();
		alert("บันทึกข้อมูลเรียบร้อย");
	}
	function whenCallback(){
		var frm = document.forms[0];
		frm.action = "security.htm?reqCode=CTWPAYMT004";
		frm.submit();		
	}
	
	function validateForm(){		
		var frm = document.forms["form1"];
		var workYear = frm.workYear.value;
		
		var tab = $('dataTable');		
		if (!isNumber(workYear) || workYear == ''){
			alert("ปีต้องเป็นตัวเลข");
			return false;
		}else if (workYear.length < 4){
			alert("ปีพ.ศ.ต้องมี 4 หลัก");
			return false;
		}else if (frm.workMonth.value == 0){
			alert("กรุณาเลือกเดือน");
			return false;
		}else if (dojo.widget.byId("empCode").comboBoxSelectionValue.value == ''){
			alert("กรุณาเลือกเลขประจำตัว");
			return false;
		}
		
		/*for(i=0; i<tab.rows.length; i++){
			row = tab.rows[i];		
			//var workM  = parseInt(document.all.days[i].workCode);
			alert(document.all.days[i].workCode);
			if (!isValid(document.all.days[i].value,document.all.days[i].workCode,workYear)){
				alert("จำนวนวันเกินวันในเดือนนั้น ๆ โปรดตรวจสอบอีกครั้ง");
				document.all.days[i].select();
				return false;
			}else  if (document.all.days[i].value > 366){
				alert("จำนวนวันต้องไม่เกิน 366 วัน");
				document.all.days[i].select();
				return false;
			}
		
		}*/
		/* for(i=0; i<tab.rows.length; i++){
			row = tab.rows[i];		
			if (!isDouble(document.all.days[i].value)){
				alert("จำนวนวันต้องเป็นตัวเลขทศนิยม");
				return false;
			}else if (!isTwoPostion(document.all.days[i].value)){
				alert("จำนวนวันต้องเป็นตัวเลขทศนิยมหนึ่งตำแหน่ง");
				return false;
			}else if (document.all.days[i].value > 366){
				alert("จำนวนวันต้องไม่เกิน 366 วัน");
				document.all.days[i].select();
				return false;
			}
		}
		 */
		/* var check = false;
		for(i=0; i<tab.rows.length; i++){
			row = tab.rows[i];
			if (document.all.days[i].value != ''){
				check = true;
				break;
			}
		}
		
		if (!check){
			alert("ต้องกรอกข้อมูลอย่างน้อย 1 record");
			return false;
		} */
		return true;
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
	function isDouble(num){
		var realNumber = "1234567890.";
		var length = num.length;		
		var temp;
		var check = true;
		if (num.indexOf(".") == -1){
			check = isNumber(num);
		}else{
			for(var i=0;i < length;i++){
				temp = num.substring(i,i+1);
				if ( realNumber.indexOf(temp) == -1 ){
					check = false;
					break;
				}
			}
		}
		return check;
	}
	
	function isTwoPostion(num){
		var length = num.length;
		var pos = num.indexOf(".") + 1;
		if (pos == 0)
			return true;
		else if (length - pos > 2)
			return false;
		else 
			return true;		
	}
	
	function convertMonth(month){
		if (month == 1)
			return "มกราคม";
		else if (month == 2)
			return "กุมภาพันธ์";
		else if (month == 3)
			return "มีนาคม";
		else if (month == 4)
			return "เมษายน";
		else if (month == 5)
			return "พฤษภาคม";
		else if (month == 6)
			return "มิถุนายน";
		else if (month == 7)
			return "กรกฎาคม";
		else if (month == 8)
			return "สิงหาคม";
		else if (month == 9)
			return "กันยายน";
		else if (month == 10)
			return "ตุลาคม";
		else if (month == 11)
			return "พฤศจิกายน";
		else if (month == 12)
			return "ธันวาคม";
	}
</script>