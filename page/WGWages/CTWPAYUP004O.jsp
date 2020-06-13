<%@ page language="java" contentType="text/html;charset=TIS-620" %>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<html>
<head>
<title>บันทึกจำนวนวันมาทำงานเพื่อคิดค่าแรง</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="script/dojo.js"></script>
<!-- Javascript Script File -->
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
	String year = request.getParameter("yearEdit")==null?"":request.getParameter("yearEdit");
	String period = request.getParameter("periodEdit")==null?"":request.getParameter("periodEdit");
	String section = request.getParameter("periodText")==null?"":request.getParameter("periodText");
	String codeSeqEdit = request.getParameter("codeSeqEdit")==null?"":request.getParameter("codeSeqEdit");
	String codeSeqText = request.getParameter("codeSeqText")==null?"":request.getParameter("codeSeqText");
	String secCodeEdit = request.getParameter("secCodeEdit")==null?"":request.getParameter("secCodeEdit");
	String secCodeText = request.getParameter("secCodeText")==null?"":request.getParameter("secCodeText");
	String workCodeEdit = request.getParameter("workCodeEdit")==null?"":request.getParameter("workCodeEdit");
	String workCodeText = request.getParameter("workCodeText")==null?"":request.getParameter("workCodeText");
	String divCodeEdit = request.getParameter("divCodeEdit")==null?"":request.getParameter("divCodeEdit");
	String hidPage = request.getParameter("pageEdit")==null?"":request.getParameter("pageEdit");
	UserInfo uf =  (UserInfo)request.getSession().getAttribute("UserLogin");
	String ouCode =  uf.getOuCode();
	String userId =  uf.getUserId();
%>
<body>
<table>
	<tr>
		<td class="font-head">[ CTWPAYUP004 ]  เพิ่ม/แก้ไขข้อมูลจำนวนวันมาทำงานเพื่อคิดค่าแรง</td>
	</tr>
</table>

<form name="form1" action="security.htm" method="post">
<table width="450" border="0" align="center" cellspacing="1">
	<tr>
		<td colspan="2" align="center"><font class="font-field">ประจำปี&nbsp;</font>
			<input type="text" name="year1" value="<%=year%>" readonly="readonly" style="width: 100;text-align: center;background-color: silver;" size="2" maxLength="4" />
			<font class="font-field">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;งวด&nbsp;</font>
			<input type="text" name="period1" value="<%=section%>" size="5" readonly="readonly" style="width: 100;text-align: center;background-color: silver;"/>
		</td>
	</tr>
	<tr>
  	<td class="font-field" align="right">สพ./ปจ.&nbsp;</td>
  	<td >
  		<input type="text" name="codesect" value="<%=codeSeqText%>" readonly="readonly" style="width: 350;text-align: left;background-color: silver;" size="160" maxLength="160" />
  	</td>

  </tr>
	<%
		if (!"".equals(secCodeText) && secCodeText !=null){
	%>
  <tr>
    <td class="font-field" align="right">ส่วน/ที่ทำการ&nbsp;</td>
	<td >
  		<input type="text" name="seccodet" value="<%=secCodeText%>" readonly="readonly" style="width: 350;text-align: left;background-color: silver;" size="160" maxLength="160" />
  	</td>
  </tr>
	<%
		}
		if (!"".equals(workCodeText) && workCodeText !=null){
	%>
   <tr>
    <td class="font-field" align="right">แผนก&nbsp;</td>
	<td >
  		<input type="text" name="workcodet" value="<%=workCodeText%>" readonly="readonly" style="width: 350;text-align: left;background-color: silver;" size="160" maxLength="160" />
  	</td>
  </tr>
	<%}%>
</table>
	<input type="hidden" name="ouCode" value="<%=ouCode%>"/>
	<input type="hidden" name="period" value="<%=period%>"/>
	<input type="hidden" name="year" value="<%=year%>"/>
	<input type="hidden" name="userId" value="<%=userId%>"/>
	<input type="hidden" name="codeSeqEdit" value="<%=codeSeqEdit%>"/>
	<input type="hidden" name="codeSeqText" value="<%=codeSeqText%>" />
	<input type="hidden" name="secCodeEdit" value="<%=secCodeEdit%>"/>
	<input type="hidden" name="secCodeText" value="<%=secCodeText%>" />
	<input type="hidden" name="workCodeEdit" value="<%=workCodeEdit%>"/>
	<input type="hidden" name="workCodeText" value="<%=workCodeText%>" />
	<input type="hidden" name="divCodeEdit" value="<%=divCodeEdit%>"/>
	<input type="hidden" name="hidPage" value="<%=hidPage%>"/>
	<input type="hidden" name="countFlag" >
	<input type="hidden" name="goback" >
<div style="height:350px;overflow:auto;" align="center" >
<table bordercolor="#6699CC" cellpadding="2" cellspacing="0"  width="770"  border="1">
	<thead>
		<tr CLASS="TABLEBULE2">
			<th CLASS="TABLEBULE2" width="130" align="center">เลขประจำตัว</th>
			<th CLASS="TABLEBULE2" width="250" align="center">ชื่อ - นามสกุล</th>
			<th CLASS="TABLEBULE2" width="130" align="center">ค่าแรง/วัน</th>
			<th CLASS="TABLEBULE2" width="130" align="center">จำนวนวันที่ทำงาน</th>
			<th CLASS="TABLEBULE2" width="130" align="center">จำนวนเงิน</th>
		<tr>
	</thead>
	<tbody id="dataTable">
	</tbody>
</table>
</div>
<br/>
<table width="100%" CLASS="TABLEBULE2">
	<tr CLASS="TABLEBULE2">
		<td>
			<input type="button" value="บันทึกข้อมูล" class="button" name="add" onclick="if(validateForm()) whenClickAdd();"/>
			<input type="button" value="   ออก   " class="button" name="exit" onclick="whenCallback('goback');"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>
<script type="text/javascript">
	var rownum = 0;
	var hvCon = "N";
	var conNum = 0;
	onLoadPage();
/*
	onLoadPage();
	function onLoadPage(){	
		WgPrWorkDayService.findWgEmployeeAddOrUpdatePage(DWRUtil.getValue("ouCode"),DWRUtil.getValue("userId"), DWRUtil.getValue("year"), DWRUtil.getValue("period"), DWRUtil.getValue("codeSeqEdit"), DWRUtil.getValue("secCodeEdit"), DWRUtil.getValue("workCodeEdit"), {callback:whenShowTable});
	}
*/

	function onLoadPage(){	
		FeeWpayPrWorkDayService.countConfirmFlag(DWRUtil.getValue("ouCode"),DWRUtil.getValue("userId"),DWRUtil.getValue("year"),DWRUtil.getValue("period"),{callback:onLoadPageHandler});
	}

	function onLoadPageHandler(data){
		if(data>=1){
			hvCon = "Y";	
			document.forms["form1"].add.disabled = "disabled";
		}	
		FeeWpayPrWorkDayService.findWgEmployeeAddOrUpdatePage(DWRUtil.getValue("ouCode"),DWRUtil.getValue("userId"), DWRUtil.getValue("year"), DWRUtil.getValue("period"), DWRUtil.getValue("codeSeqEdit"), DWRUtil.getValue("secCodeEdit"), DWRUtil.getValue("workCodeEdit"), {callback:whenShowTable});
	}


	var cellFuncs = [	
		function(data) { return "<div align='center'>"+writeEmpCode(data.empCode,data.codeSeqWork)+"</div>";},
		function(data) { return "<div align='left'>"+data.prefixName+" "+data.firstName+" "+data.lastName+"</div>";},
		function(data) { return "<div align='center'>"+writeWageAmt(data.newSalary)+"</div>";},
		function(data) { return "<div align='center'>"+writeWorkDay(data.workDay)+"</div>";},
		function(data) { return "<div align='center'>"+writeWorkAmt(data.newSalary*data.workDay)+"</div>";}		
	];

	function whenShowTable(data){
		DWRUtil.removeAllRows("dataTable");	
		DWRUtil.addRows("dataTable", data, cellFuncs);
		if (data == ""){
			alert("ไม่พบข้อมูล");
		}
	}

	function writeEmpCode(workSeq,codeSeq){
		if(workSeq == null)
			workSeq = "";
		if(codeSeq == null)
			codeSeq = "";
		return "<div align='center'><input type='text' name ='empC' value='"+workSeq+"' rownum='"+rownum+"' codeS='"+codeSeq+"' readonly='readonly' style='width: 100px;text-align: center;border-style : none;background-color : transparent;' /></div>";
	}
	
	function writeWageAmt(workSeq){
		if(workSeq == null)
			workSeq = 0;
		return "<div align='center'><input type='text' name ='wageA' value='"+workSeq+"' rownum='"+rownum+"' readonly='readonly' style='width: 120px;text-align: right;border-style : none;background-color : transparent;' /></div>";
	}

	function writeWorkDay(workSeq){
		if(workSeq == null)
			workSeq = 0;

		if(hvCon == "Y"){
			return "<div align='center'><input type='text' name ='workD' value='"+workSeq+"' rownum='"+rownum+"' readonly='readonly' style='width: 120px;text-align: right;border-style : none;background-color : transparent;' /></div>";
		}else{
			return "<div align='center'><input type='text' name ='workD' value='"+workSeq+"' rownum='"+rownum+"' style='width: 120px;text-align: right;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);' onchange='vlidateDay(this)'/></div>";
		}
	}

	function writeWorkAmt(workSeq){
		if(workSeq == null)
			workSeq = 0;
		rownum++;
		return "<div align='center'><input type='text' name ='workA' value='"+workSeq+"' rownum='"+rownum+"' readonly='readonly' style='width: 120px;text-align: right;border-style : none;background-color : transparent;' /></div>";
	}
	
	function vlidateDay(element){
		var frm = document.forms["form1"];
	    var tab = $('dataTable');
		var val = element.value;
		var rnum = element.rownum;
		var sal = 0;
		if(tab.rows.length==1){
			sal = frm.wageA.value;
			frm.workA.value = val*sal;
		}
		else{
			sal = frm.wageA[rnum].value;
			frm.workA[rnum].value = val*sal;
		}
	}

	
	function whenClickAdd(){
		var frm = document.forms["form1"];
		var tab = $('dataTable');		
		var ouCode = DWRUtil.getValue("ouCode");
		var year = DWRUtil.getValue("year");
		var period = DWRUtil.getValue("period");
		var creby = DWRUtil.getValue("userId");
		var arrEmpCode=[];
		var arrWorkDay=[];
		var arrWageAmt=[];
		var arrWorkAmt=[];	
	 	var arrCodeSeq=[];	
		if(tab.rows.length==1){	
			arrWorkDay.push(frm.workD.value);
			arrWageAmt.push(frm.wageA.value);
			arrWorkAmt.push(frm.workA.value);
			arrEmpCode.push(frm.empC.value);
			arrCodeSeq.push(frm.empC.codeS);
		}
		else{
			for(i=0; i<tab.rows.length; i++){
				row = tab.rows[i];			
				arrWorkDay.push(frm.workD[i].value);
				arrWageAmt.push(frm.wageA[i].value);
				arrWorkAmt.push(frm.workA[i].value);
				arrEmpCode.push(frm.empC[i].value);
				arrCodeSeq.push(frm.empC[i].codeS);
			}
		}

		DWREngine.setErrorHandler(eh);
		//alert(arrWorkDay.length);
		FeeWpayPrWorkDayService.addOrUpdateWgPrWorkDay(ouCode, year, period, arrEmpCode, arrCodeSeq, arrWorkDay, arrWorkAmt, arrWageAmt, creby, {callback:whenAddComplete});
	}
	
	function eh() {
		alert("เกิดปัญหาในการบันทึกข้อมูล ระบบจะทำการลบข้อมูลที่ถูกบันทึกไปแล้วออกจากฐานข้อมูล");
	}
	
	function whenAddComplete(data){
		DWRUtil.removeAllRows("dataTable");
		var frm = document.forms["form1"];
		DWRUtil.setValue("goback","goback");
		frm.action = "security.htm?reqCode=CTWGMT004";
		//DWRUtil.setValue("hidPage",1);
		frm.submit();
		alert("บันทึกข้อมูลเรียบร้อย");
	}
	function whenCallback(val){
		var frm = document.forms[0];
		DWRUtil.setValue("goback", val);
		frm.action = "security.htm?reqCode=CTWPAYMT004";
		frm.submit();		
	}
	
	function validateForm(){		
		var frm = document.forms["form1"];
		var tab = $('dataTable');		
		if(tab.rows.length==1){			
				if (!isDouble(frm.workD.value)){
					alert("จำนวนวันต้องเป็นตัวเลขทศนิยม");
					return false;
				}else if (!isTwoPostion(frm.workD.value)){
					alert("จำนวนวันต้องเป็นตัวเลขทศนิยมหนึ่งตำแหน่ง");
					return false;
				}else if (frm.workD.value >= 100000){
					alert("จำนวนวันต้องไม่เกิน 100000 วัน");
					return false;
				}else if (frm.workD.value == ''){
					alert("บาง record ยังไม่ได้กรอกจำนวนวัน");
					return false;
				}
		}
		else{
			for(i=0; i<tab.rows.length; i++){
				row = tab.rows[i];		
				if (!isDouble(frm.workD[i].value)){
					alert("จำนวนวันต้องเป็นตัวเลขทศนิยม");
					return false;
				}else if (!isTwoPostion(frm.workD[i].value)){
					alert("จำนวนวันต้องเป็นตัวเลขทศนิยมหนึ่งตำแหน่ง");
					return false;
				}else if (frm.workD[i].value >= 100000){
					alert("จำนวนวันต้องไม่เกิน 100000 วัน");
					return false;
				}else if (frm.workD[i].value == ''){
					alert("บาง record ยังไม่ได้กรอกจำนวนวัน");
					return false;
				}
			}
		}
		
		var check = false;
		if(tab.rows.length==1){	
				if (frm.workD.value != ''){
					check = true;
				}
		}
		else{
			for(i=0; i<tab.rows.length; i++){
				row = tab.rows[i];
				if (frm.workD[i].value != ''){
					check = true;
					break;
				}
			}		
		}
		
		if (!check){
			alert("ต้องกรอกข้อมูลอย่างน้อย 1 record");
			return false;
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
	
</script>