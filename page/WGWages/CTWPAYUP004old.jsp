<%@ page language="java" contentType="text/html;charset=TIS-620" %>
<%@ page import="com.ss.tp.security.UserInfo" %>
<html>
<head>
<title>Update taMonthEmpWork</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<!-- Javascript Script File -->
<script type="text/javascript" src="dwr/interface/FeeWpayPrWorkDayService.js"></script>
<script type="text/javascript" src="script/gridScript.js"></script>
<script type="text/javascript">	
</script>
</head>
<body>
<%
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
<table>
	<tr>
		<td class="font-head">[ CTWPAYUP004 ]  แก้ไขจำนวนวันทำงานในแต่ละเดือนของลูกจ้าง</td>
	</tr>
</table>
<form name="hidForm" method="post">		
	<input type="hidden" name="hidOuCode" value="<%=ouCode%>"/>
	<input type="hidden" name="hidWorkYear" value="<%=request.getParameter("workYear")%>" />
	<input type="hidden" name="hidWorkMonth" value="<%=request.getParameter("workMonth")%>" />
	<input type="hidden" name="hidEmpCode" value="<%=request.getParameter("hidEmpCode")%>" />
	<input type="hidden" name="hidPage" value="update"/>
	<input type="hidden" name="pageEdit" value="<%=request.getParameter("page")%>"/>
	<input type="hidden" name="hidUserId" value="<%=userId%>"/><br>
	<input type="hidden" name="areaCode" value="<%=areaCode%>"/>
	<input type="hidden" name="secCode" value="<%=secCode%>"/>
	<input type="hidden" name="workCode" value="<%=workCode%>"/>
	<input type="hidden" name="areaLabel" value="<%=areaLabel%>"/>
	<input type="hidden" name="secLabel" value="<%=secLabel%>"/>
	<input type="hidden" name="workLabel" value="<%=workLabel%>"/>
	<input type="hidden" name="hidPage" value="update"/>
</form>
<form name="form1" action="security.htm" method="post">
<table width="480" border="0" align="center" cellspacing="1">
	<tr>
		<td class="font-field">ประจำปี</td>
		<td><input type="text" name="workYear" size="2" readonly="readonly" style="width: 100;text-align: center;background-color: silver;"/></td>
		<td class="font-field">เดือนที่อนุญาติให้แก้ตั้งแต่</td>
		<td><input type="text" name="workMonth" size="5" readonly="readonly" style="width: 100;text-align: center;background-color: silver;"/></td>
	</tr>
	<tr>
		<td class="font-field">เลขประจำตัว</td>
		<td><input type="text" name="empCode" size="10" readonly="readonly" style="width: 100;text-align: center;background-color: silver;"/></td>
		<td class="font-field">ชื่อ</td>
		<td><input type="text" name="empName" size="20" readonly="readonly" style="width: 150;text-align: left;background-color: silver;"/><td>
</table>
<br/>
<div style="height:390px;overflow:auto;">
<table bordercolor="#6699CC" cellpadding="2" cellspacing="0"  width="600" align="center" border="1">
	<thead>
		<tr CLASS="TABLEBULE2">
		<th><input type="checkbox" name="selectAll" onclick="doSelectAll(document.forms['form1']);"/></th>
		<th>เดือนที่</th>
		<th>เดือน</th>
		<th>จำนวนวันทำงาน</th>
		<th>หมายเหตุ</th>
	</thead>
	<tbody id="dataTable">
	</tbody>
</table>
</div>
<table width="100%" CLASS="TABLEBULE2">
	<tr CLASS="TABLEBULE2">
		<td>
			<input type="button" value="บันทึกข้อมูล" name="update" class="button" onclick="if (validateForm()) whenClickUpdate();"/>
			<input type="button" value="ลบข้อมูล" class="button" name="delete" onclick="if(checkBefore(document.forms['form1'])) onDelete();"/>
			<input type="button" value="   ออก   " name="exit" class="button" onclick="whenCallback('goBack');"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<script>
	onInitPage()
	function onInitPage(){
		var frm = document.forms[0]
		var hidWorkYear = frm.elements["hidWorkYear"].value;
 		var hidWorkMonth = frm.elements["hidWorkMonth"].value;
		var hidEmpCode = frm.elements["hidEmpCode"].value;
		var hidOuCode = frm.elements["hidOuCode"].value;
		DWRUtil.setValue("workYear", hidWorkYear);
		DWRUtil.setValue("workMonth", convertMonth(hidWorkMonth));
		DWRUtil.setValue("empCode", hidEmpCode);
		FeeWpayPrWorkDayService.findEmpByKey(hidWorkYear, hidWorkMonth, hidEmpCode, {callback:whenShowEmpName});
		FeeWpayPrWorkDayService.findByKey(hidWorkYear, hidWorkMonth, hidEmpCode, hidOuCode, {callback:whenListDataTableHandler});		
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
	function whenShowEmpName(data){
		DWRUtil.setValue("empName", data.prefixName + data.firstName + ' ' + data.lastName);
		DWRUtil.setValue("hidOuCode", data.ouCode);
	}
	function whenListDataTableHandler(data){
		DWRUtil.removeAllRows("dataTable");		
		DWRUtil.addRows("dataTable", data, cellFuncs);
	}
	
	function writeCheckBox(){		
		return "<div align='center'><input type='checkbox' name ='chk' /></div>";	
	}

	function writeTextDays(days, workSeq,workCode){
		return "<div align='center'><input type='text' workSeq='" + workSeq + "' workCode='" + workCode + "' name ='days' value='" + days + "' style='width: 70px;text-align: right;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);' onchange='vlidateDay(this)'/></div>";
	}
	
	
	function writeTextNotes(notes, workSeq,workCode){
	  	return "<div align='center'><input type='text' workSeq='" + workSeq + "' workCode='" + workCode + "' name ='notes' value='" + notes + "' style='width: 300px;text-align: left;'/></div>";
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
	         // alert("yo"+ yo);
  			  return m >= 0 && m < 12 && d >= 0 && d <= daysInMonth(m, yo);
	}
	
	
	
	function vlidateDay(element){
		var val = element.value;
		var index = val.indexOf(".");
		if (index != -1){
			element.value = val.substring(0, index) + ".5";
		}
	}
	
	var cellFuncs = [	
		function(data) { return writeCheckBox();},
		function(data) { return "<div align='center'>"+data.workCode+ "</div>";},
		function(data) { return data.workDesc;},
		function(data) { return writeTextDays(data.totalDays, data.workSeq,data.workCode);},
		function(data)  { 
						if(data.note != null && data.note != ''){
							return writeTextNotes(data.note, data.workSeq,data.workCode);
						}else{
							return writeTextNotes("","","");
						}
						}];

	function whenClickUpdate(){
		var tab = $('dataTable');
		var row;
		var empList=[];
		var ouCode = DWRUtil.getValue("hidOuCode");
		var workYear = DWRUtil.getValue("hidWorkYear");
		var workMonth = DWRUtil.getValue("hidWorkMonth");
		var empCode = DWRUtil.getValue("hidEmpCode");
		var userId = DWRUtil.getValue("hidUserId");	
		var arrDay=[];
		var arrWorkCode=[];
		var arrWorkSeq=[];	
		var arrNote=[];
		DWREngine.setErrorHandler(ehUpdate); 
		/*if(tab.rows.length>1){	 
			for(i=0; i<tab.rows.length; i++){
				row = tab.rows[i];			
				arrWorkSeq.push(document.all.days[i].workSeq);	
				arrWorkCode.push(document.all.days[i].workCode);	
				arrDay.push(document.all.days[i].value);
				arrNote.push(document.all.notes[i].value);
			}
		}
		else{
			if(tab.rows.length==1){
				arrWorkSeq.push(document.all.days.workSeq);	
				arrWorkCode.push(document.all.days.workCode);	
				arrDay.push(document.all.days.value);
				arrNote.push(document.all.notes.value);
			}
		}*/
		
		if(tab.rows.length>1){	 
			for(i=0; i<tab.rows.length; i++){
				row = tab.rows[i];		
			   
				if (!isValid(document.all.days[i].value,document.all.days[i].workCode,workYear)){
						alert("จำนวนวันเกินวันในเดือนนั้น ๆ โปรดตรวจสอบอีกครั้ง");
						document.all.days[i].select();
						return false;
				}else {
				arrDay.push(document.all.days[i].value);
				arrWorkCode.push(document.all.days[i].workCode);
				arrWorkSeq.push(document.all.days[i].workSeq);
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
						arrWorkSeq.push(document.all.days.workSeq);
						arrNote.push(document.all.notes.value);
				}
			 
			}
		}
		
		
		FeeWpayPrWorkDayService.updateTaMonthEmpWork(ouCode, workYear, workMonth, empCode, userId, arrDay, arrWorkCode, arrWorkSeq,arrNote, {callback:whenUpdateComplete});
	}
	
	function whenUpdateComplete(){
		alert("แก้ไขข้อมูลเรียบร้อย");
		whenCallback("goback")
	}
	function ehUpdate() {
  		alert("เกิดปัญหาในการบันทึกข้อมูลา ระบบจะทำการลบข้อมูลที่ถูกบันทึกไปแล้วออกจากฐานข้อมูลและกู้ข้อมูลเดิมกลับมา");
	}
	
	function whenCallback(val){
		var frm = document.forms[0];
		frm.action = "security.htm?reqCode=CTWPAYMT004";
		frm.hidPage.value = val;
		frm.submit();		
	}
	
	function validateForm(){
		var frm = document.forms["form1"];
		var tab = $('dataTable');		
		var row;
		var totalDays;
		for(i=0; i<tab.rows.length; i++){
			row = tab.rows[i];			
			if (!isDouble(document.all.days[i].value)){
				alert("จำนวนวันต้องเป็นตัวเลขทศนิยม");
				return false;
			}else if (!isTwoPostion(document.all.days[i].value)){
				alert("จำนวนวันต้องเป็นตัวเลขทศนิยมสองตำแหน่ง");
				return false;
			}else if (document.all.days[i].value > 366){
				alert("จำนวนวันต้องไม่เกิน 366 วัน");
				document.all.days[i].select();
				return false;
			}else if (document.all.days[i].value == ''){
				alert("กรอกจำนวนวันไม่ถูกต้อง");
				return false;
			}
		}
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

	function onDelete(){
		var tab = $('dataTable');
		var row;
		var empList=[];
		var frm = document.forms["form1"];
		var chk = frm.elements["chk"];
		var arrWorkSeq = [];
		DWREngine.setErrorHandler(ehDelete); 
		DWREngine.beginBatch();
		if(tab.rows.length>1){	 
			for(i=0; i<tab.rows.length; i++){
				row = tab.rows[i];	
				if (chk[i].checked){
					arrWorkSeq.push(document.all.days[i].workSeq);
				}
			}
		}
		else{
			if(tab.rows.length==1){
				if (chk.checked){
					arrWorkSeq.push(document.all.days.workSeq);
				}
			}
		}
		FeeWpayPrWorkDayService.deleteEmpDtl(DWRUtil.getValue("hidOuCode"), 
										   DWRUtil.getValue("hidWorkYear"), 
										   DWRUtil.getValue("hidWorkMonth"), 
										   DWRUtil.getValue("hidEmpCode"), 
										   arrWorkSeq, {callback:onDeleteCallback});
		DWREngine.endBatch();
	}
	
	function onDeleteCallback(){		
		alert("ลบข้อมูลเรียบร้อย");
		var frm = document.forms[0];
		var hidWorkYear = frm.elements["hidWorkYear"].value;
 		var hidWorkMonth = frm.elements["hidWorkMonth"].value;
		var hidEmpCode = frm.elements["hidEmpCode"].value;
		var ouCode = DWRUtil.getValue("hidOuCode");
		FeeWpayPrWorkDayService.findByKey(hidWorkYear, hidWorkMonth, hidEmpCode, ouCode, {callback:whenListDataTableHandler});		
	}
	
	function ehDelete() {
  		alert("เกิดปัญหาในการลบข้อมูล ระบบจะทำการกู้ข้อมูลเดิมกลับมา");
	}
</script>