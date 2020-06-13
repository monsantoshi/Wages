<%@ page language="java" contentType="text/html;charset=TIS-620" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<%
	String yearInsert = (String)request.getParameter("yearInsert");
	String periodInsert = (String)request.getParameter("periodInsert");
	String userIdInsert = (String)request.getParameter("userIdInsert");
	String ouCodeInsert = (String)request.getParameter("ouCodeInsert");
	String sectionInsert = (String)request.getParameter("sectionInsert");
	
	String orgFromIns = request.getParameter("orgFromIns");
	String orgToIns = request.getParameter("orgToIns");
	String empCodeFromIns = request.getParameter("empCodeFromIns");
	String empCodeToIns = request.getParameter("empCodeToIns");
	String pageIns = request.getParameter("pageIns");
	
	String orderFromCboIns = request.getParameter("orderFromCboIns");
	String orderToCboIns = request.getParameter("orderToCboIns");
	
	if( pageIns.trim().equals("") )
		pageIns = "-1";
	
	java.util.Date dd = new java.util.Date();
	java.text.SimpleDateFormat fmd = new java.text.SimpleDateFormat("dd/MM/yyyy",new java.util.Locale("th","TH"));
	String date = fmd.format(dd);
	
	java.util.Calendar cc = new java.util.GregorianCalendar();
	int x = cc.get(java.util.Calendar.MONTH) +1;
	//System.out.println("#$#$#$#$$#$#$# :::"+x);
	
	int xx = (Integer.parseInt(periodInsert)+1)/2;
	String periodWork = String.valueOf(xx);
	
	String year = yearInsert;
	if(periodWork.equals("0")){
		periodWork = "12";
		year =  String.valueOf(Integer.parseInt(yearInsert)-1);
	}
%>
<html>
<head>
<title>บันทึกรายการปรับปรุงวันทำงาน</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<!-- Javascript Script File -->
<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
<script type="text/javascript" src="dwr/interface/FeeWpayRwVinai2Service.js"></script>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPnEmployeeService.js"></SCRIPT>
<script type="text/javascript" src="script/gridScript.js"></script>
<script type="text/javascript" src="script/dojo.js"></script>
<script type="text/javascript" src="script/dateCalendar.js"></script>
<script type="text/javascript" src="script/payroll_util.js"></script>

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
<!--
	
	var rwVinai2 = {keySeq:null, ouCode:null, yearPr:null, periodPr:null,empCode:null,
					yearWork:null,periodWork:null,codeSeq:null,decDay:null,remark:null,confirmFlag:null,
					confirmFlag:null,updBy:null,updDate:null,creBy:null,creDate:null,approveFlag:null};
	
	
	function ClearData(){
		alert("บันทึกข้อมูลเรียบร้อย");
		var table = document.getElementById("table");
		var tdName;
		var chkName;
		
		
		var oRows = table.rows;
		if(tdName == null)tdName="flag";
		if(chkName == null)chkName="chk";
		//alert(oRows.length);
		if(oRows.length > 2){
			for(i=oRows.length-1;i > 1;i--){
				table.deleteRow(i);			
			}
		}
		
		$("empCbo").value = '';
		$("name").value = '';
		//$("positionShort").value = '';
		$("orgCode").value = '';
		$("orgDesc").value = '';
	}
	
	function onInsertCallback(data){
		
	}
	
	function insertNewData()
	{
		 if(compareMonthBeforSave()){
			var cbo = dojo.widget.byId("empCbo");
			var table = document.getElementById("table");
			var tdName;
			var chkName;
			
			var oRows = table.rows;
			if(tdName == null)tdName="flag";
			if(chkName == null)chkName="chk";
			
			if(oRows.length > 2)
			{
			  
				DWREngine.beginBatch();
				for(var i=oRows.length-1;i >1;i--)
				{
					rwVinai2.ouCode = '<%=ouCodeInsert%>';
					rwVinai2.yearPr = '<%=yearInsert%>';
					rwVinai2.periodPr = '<%=periodInsert%>';
					rwVinai2.empCode = DWRUtil.getValue("empCbo");
					rwVinai2.codeSeq = $("codeSeq").value;
					rwVinai2.yearWork = oRows[i].cells["yearWork"].childNodes[0].value;
					rwVinai2.periodWork = oRows[i].cells["periodWork"].childNodes[0].value;
					rwVinai2.decDay = oRows[i].cells["decDay"].childNodes[0].value;
					rwVinai2.remark = oRows[i].cells["remark"].childNodes[0].value;
					rwVinai2.flagPr= oRows[i].cells["flagPr"].childNodes[0].value;
					rwVinai2.confirmFlag = 'N';
					rwVinai2.approveFlag = 'N';
					rwVinai2.updBy = '<%=userIdInsert%>';
					rwVinai2.creBy = '<%=userIdInsert%>';
					rwVinai2.creDatw = getDateFromFormat(<%=date%>,"dd/MM/yyyy");
					//RwVinai2Service.insertRwVinai2(rwVinai2, {callback:onInsertCallback});
					
					if( i == 2 )
						FeeWpayRwVinai2Service.addList(rwVinai2, true, {callback:ClearData,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้ อาจมีข้อมูลซ้ำ');}});
					else
						FeeWpayRwVinai2Service.addList(rwVinai2, false, {callback:onInsertCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
				}
				DWREngine.endBatch();	
				//RwVinai2Service.insertAndUpdate({callback:ClearData});
				
			}else
			{
				alert('ไม่พบข้อมูลที่จะบันทึก');
			}
		}
	}
	function bypass(object){
	 	 while (object.tagName !=  'TR')
		 {
		 object = object.parentNode
		 }
	
		 var rowToCompare = object.rowIndex
		
     	DWRUtil.useLoadingMessage("Loading ...");
		var table = document.getElementById("table");
		//alert(table.);
		var tdName;
		var chkName;
		
		var oRows = table.rows;
	
		var yearWork = oRows[rowToCompare].cells["yearWork"].childNodes[0].value;
		if(yearWork == '<%=yearInsert%>'){
		
			//oRows[rowToCompare].cells["fiscalYear"].childNodes[0].value = yearWork;
			//oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
			//oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
		}else{
			alert('ปีที่ทำงานไม่ถูกต้อง');
			oRows[rowToCompare].cells["yearWork"].childNodes[0].value = '';
			//oRows[rowToCompare].cells["fiscalYear"].childNodes[0].value = '' ;
		}
	
	
		
	 	
	 	
	 	//var stDate = oRows[rowToCompare].cells["startDate"].childNodes[0].value;
		//var endDate = oRows[rowToCompare].cells["endDate"].childNodes[0].value;
		
		//var strMonth = stDate.substr(3, 2);
		//var strYear = stDate.substr(6);
		
		//var endMonth = endDate.substr(3, 2);
		//var endYear = endDate.substr(6);
	 	
	 	//if(oRows[rowToCompare].cells["startDate"].childNodes[0].value != null && oRows[rowToCompare].cells["startDate"].childNodes[0].value != '' && oRows[rowToCompare].cells["endDate"].childNodes[0].value != null && oRows[rowToCompare].cells["endDate"].childNodes[0].value != ''){
	 	//	alert('กรุณาระบุวันที่ปฎิบัติงานใหม่');
	 	
	 	//}
	 
	 	//oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
	 	//oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
	 	
	 	
	 
	 }
	 
	 function compareMonthBeforSave(){
	 	//document.forms['formInsert'].elements['refNo'].focus();
	  			    
					var ss = true ;
					
			     	DWRUtil.useLoadingMessage("Loading ...");
					var table = document.getElementById("table");
				
					var tdName;
					var chkName;
					
					var oRows = table.rows;
					for(var i=oRows.length-1;i > 1;i--){
							
								var periodWork = parseInt(oRows[i].cells["periodWork"].childNodes[0].value);
								var yearWork = oRows[i].cells["yearWork"].childNodes[0].value;
								var dec = oRows[i].cells["decDay"].childNodes[0].value;
								var section = '<%=sectionInsert%>';
								var yearInsert = '<%=yearInsert%>';
								var monthWork = parseInt(section.substr(0, 2));
							    
								if(yearWork != null && yearWork != '' && periodWork != null && periodWork != '' ){
								
										if(yearWork == yearInsert && periodWork > monthWork){
												 	ss = false;
													alert("เดือนห้ามเกินช่วงการทำงาน เลือกเดือนใหม่");
													//alert("เดือนนี้ต้องไม่เกินเดือน 9");
													oRows[i].cells["periodWork"].childNodes[0].value = '';
													oRows[i].cells["periodWork"].childNodes[0].focus();
													document.forms['formEdit'].elements['insertData'].disabled = true;
													document.forms['formEdit'].elements['deleteData'].disabled = true;
													document.forms['formEdit'].elements['confirmData'].disabled = true;
													
													break;
											}else if(yearWork != yearInsert){
												//alert('ASDF');
													ss = false;
													alert("ปีไม่ตรงกับปีงบประมาณ");
													oRows[i].cells["yearWork"].childNodes[0].value = '';
													oRows[i].cells["yearWork"].childNodes[0].focus();
													document.forms['formEdit'].elements['insertData'].disabled = true;
													document.forms['formEdit'].elements['deleteData'].disabled = true;
													document.forms['formEdit'].elements['confirmData'].disabled = true;
													break;
											}
											
									
							 	}
							 	
							 if(ss){
								 	if((yearWork == null || yearWork == '') || (periodWork == null || periodWork == '')|| (dec == null || dec == '')){
								 		
											ss = false;
											alert("กรุณาระบุปี  เดือนที่ทำงาน หรือ จำนวนวัน");
											
											break;
										
								 	}
							 	}
							
				 	}
				 	
		return ss;
	 }
	 
	
	
	function onLoadEmployee()
	{
	   	FeeWpayPnEmployeeService.findEmpBySecurity('<%=userIdInsert%>','<%=ouCodeInsert%>', {callback:onLoadEmployeeCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
	}
	
    function onLoadEmployeeCallback(data)
    {
     	try
     	{
	     	var cboSource = [];
	     	var cbo = dojo.widget.byId("empCbo");
	     	
	     	for(var i=0; i<data.length; i++)
	     	{
	     		var emp = data[i];
	     		cboSource.push([emp.empCode, emp.codeSeq]);
	     	}
	     	
	     	cbo.dataProvider.setData(cboSource);
	     	
     	}catch(e){
     		alert(e.message);
     	}
     }
     
     function whenSelectEmpOption()
     {
		DWRUtil.useLoadingMessage("Loading ...");
		var empCode = DWRUtil.getValue("empCbo")
		//alert(empCode);
		//var cbo = dojo.widget.byId("empCbo");
		
		whenFetchEmployeeDetail(empCode);
	 } 
	 
	 function whenFetchEmployeeDetail(empCode)
	 {
		DWRUtil.useLoadingMessage("Loading ...");
		
		FeeWpayPnEmployeeService.findByEmpCodeDetail('<%=userIdInsert%>', empCode, '<%=ouCodeInsert%>', '<%=yearInsert%>', '<%=periodInsert%>', {callback:whenFetchEmployeeDetailCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
	 }

	 function whenFetchEmployeeDetailCallback(data)
	 {
	 	
		if(data.empCode != null && data.empCode != ''){
			$("name").value = data.name;
			//$("positionShort").value = data.positionShort;
			$("orgCode").value = data.orgCode;
			$("orgDesc").value = checkNull(data.divDesc,'STRING') + ' ' + checkNull(data.orgDesc,'STRING');
			$("codeSeq").value = data.codeSeqWork;
				document.forms['formEdit'].elements['insertData'].disabled = false;
				document.forms['formEdit'].elements['deleteData'].disabled = false;
				document.forms['formEdit'].elements['confirmData'].disabled = false;	
			
			
		}else{
			$("name").value = '';
			//$("positionShort").value = '';
			$("orgCode").value = '';
	    	$("orgDesc").value = '';
			$("codeSeq").value = '';
				document.forms['formEdit'].elements['insertData'].disabled = true;
				document.forms['formEdit'].elements['deleteData'].disabled = true;
				document.forms['formEdit'].elements['confirmData'].disabled = true;
				
			alert('เลขประจำตัวไม่ถูกต้อง');	
		}
	 }
	
	
	
	function init()
	{
		$("year").value = '<%=yearInsert%>';
	 	$("section").value = '<%=sectionInsert%>';
		$("period").value = '<%=periodInsert%>';
	 	
	}
	
	function backFormMain(){
		var frm=document.forms['backForm'];
		
		document.getElementById("orgFromEdit").value = '<%= orgFromIns %>';
		document.getElementById("orgToEdit").value = '<%= orgToIns %>';
		document.getElementById("empCodeFromEdit").value = '<%= empCodeFromIns %>';
		document.getElementById("empCodeToEdit").value = '<%= empCodeToIns %>';
		document.getElementById("pageEdit").value = '<%= Integer.parseInt(pageIns) - 1 %>';
		
		document.getElementById("orderFromCboEdit").value = '<%= orderFromCboIns %>';
		document.getElementById("orderToCboEdit").value = '<%= orderToCboIns %>';
		document.getElementById("mustQuery").value = 'true';
		frm.submit();
	}
 	 dojo.addOnLoad(init);
 	// dojo.addOnLoad(onLoadEmployee);
	
	function checkDay(object){
	 	 while (object.tagName !=  'TR')
		 {
		 object = object.parentNode
		 }
	
		 var rowToCompare = object.rowIndex
		
     	DWRUtil.useLoadingMessage("Loading ...");
		var table = document.getElementById("table");
		//alert(table.);
		var tdName;
		var chkName;
		
		var oRows = table.rows;
	
		var month = oRows[rowToCompare].cells["periodWork"].childNodes[0].value;
		var day = oRows[rowToCompare].cells["decDay"].childNodes[0].value;
		
		if(day != null && day != '' && day != 0){
		
			switch (month) {
				  case "1":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "2":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "3":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "4":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "5":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "6":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "7":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "8":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "9":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "10":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "11":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "12":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				} 
		
		
		}
	
		//alert(month+' ::: '+day)
		
	}
	
	 function bypassMonth(object){
	 	 while (object.tagName !=  'TR')
		 {
		 object = object.parentNode
		 }
	
		 var rowToCompare = object.rowIndex
		
     	DWRUtil.useLoadingMessage("Loading ...");
		var table = document.getElementById("table");
		//alert(table.);
		var tdName;
		var chkName;
		
		var oRows = table.rows;
	
		
	 	
	 	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
	 	document.forms['formEdit'].elements['insertData'].disabled = false;
		document.forms['formEdit'].elements['deleteData'].disabled = false;
		document.forms['formEdit'].elements['confirmData'].disabled = false;	
	 	
	 	
	 
	 }
-->
</script>
</head>

<body>
<form name="formEdit"  action="security.htm?reqCode=CTWPAYMT010" method="post">
<input type="hidden" name="dataLength">
<input type="hidden" name="period">
<input type="hidden" name="codeSeq">
<table width="100%">
	<tr>
		<td class="font-head">
			[ CTWPAYIN010 ] บันทึกรายการเพิ่ม ลด วันทำงานที่ผิดพลาด
		</td>
	</tr>
</table>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">

      <tr>
          <td  align="center">
				<table width="750" border="1" cellpadding="1" bordercolor="#6699CC" cellspacing="1"  align="center">
        			<tr>
          				<td class="font-field"  align="right" width="100">ประจำปี</td>
          				<td align="left"><input type="text" name="year" value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/></td>
          				<td colspan="3" class="font-field" align="left" width="250">
          					&nbsp;&nbsp;งวด&nbsp;&nbsp;
          					<input type="text" name="section" value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/>
          				</td>
        			</tr>
        			<tr>
         				<td class="font-field"  align="right" width="100">เลขประจำตัว</td>
          				<td align="left" width="15"><input type="text" name="empCbo" maxlength="6" style="width:150" onchange="whenSelectEmpOption();"></td>
          				<td align="left"><input type="text" name="name" readonly="readonly" style="width:260;background-color:silver;"/></td>
        				
       			 	</tr>
       			 	<tr>
          				<td  class="font-field"  align="right" width="100">สังกัดปฎิบัติงาน</td>
          				<td  align="left" width="30"><INPUT type="text" name="orgCode" maxlength="20" readonly="readonly" style="width:150;background-color: silver;" onBlurInput="whenFindOrganization();" /></td>
          				<td colspan="3"align="left"><input type="text" name="orgDesc" readonly="readonly" style="width:485;background-color: silver;"/></td>
       			 	</tr>
     		 </table>
          </td>
      </tr>
    	&nbsp;
	 	&nbsp;
	  	&nbsp;
	  <tr>
          <td align="center">
				<div style="height:345px;width:800;overflow:auto;">
				 &nbsp;
	  			&nbsp;
	  			&nbsp;
				<table id="table"  bordercolor="#4A4A4A" align="center" style="text-align: center;width:750px" border="1" cellpadding="0" cellspacing="0" >
					<thead style="text-align: center">
						<tr CLASS="TABLEBULE2" style="height:30px;">
							<th CLASS="TABLEBULE2" style="width:50px" align="center">ลบ</th>
							<th CLASS="TABLEBULE2" style="width:150px" align="center">ประเภทของวันทำงาน</th>
							<th CLASS="TABLEBULE2" colspan="2" align="center">ปี/เดือนที่ทำงาน</th>
							<th CLASS="TABLEBULE2" style="width:150px" align="center"><center>จำนวนวัน</center></th>
							<th CLASS="TABLEBULE2" style="width:250px" align="center">หมายเหตุ</th>
						</tr>						
					</thead>
					
											
						<tr id="temprow" style="visibility:hidden;position:absolute">
							<td id="flag"><input type="checkbox" name="chk" /></td>
							<td id="flagPr" width="150">
								<select name="type">
										<option value="A" >เพิ่มวันทำงาน</option>
										<option value="R" >ลดวันทำงาน</option>
										<!-- <option value="R" >ปรับปรุงรายการรับเรียกคืน</option>
										<option value="B" >ตกเบิกปรับปรุงรายการรับ</option>
										<option value="S" >ตกเบิกรายการรับเรียกคืน</option> -->
								</select>
							</td>
							<td id="yearWork"><input type="text" value="<%=yearInsert%>"  name="yearWork" size="6" maxlength="4" align="center" style="text-align:center;" onchange="bypass(this);" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="periodWork" align="center" ><select name="periodWork" style="width:100;" onchange="bypassMonth(this);">
													<option value="1" <% if(periodWork.equals("1")){%> selected="selected"  <%} %> >มกราคม</option>
													<option value="2" <% if(periodWork.equals("2")){%> selected="selected"  <%} %>  >กุมภาพันธ์</option>
													<option value="3" <% if(periodWork.equals("3")){%> selected="selected"  <%} %>  >มีนาคม</option>
													<option value="4" <% if(periodWork.equals("4")){%> selected="selected"  <%} %>  >เมษายน</option>
													<option value="5" <% if(periodWork.equals("5")){%> selected="selected"  <%} %>  >พฤษภาคม</option>
													<option value="6"  <% if(periodWork.equals("6")){%> selected="selected"  <%} %>  >มิถุนายน</option>
													<option value="7" <% if(periodWork.equals("7")){%> selected="selected"  <%} %>  >กรกฏาคม</option>
													<option value="8" <% if(periodWork.equals("8")){%> selected="selected"  <%} %>  >สิงหาคม</option>
													<option value="9" <% if(periodWork.equals("9")){%> selected="selected"  <%} %>  >กันยายน</option>
													<option value="10" <% if(periodWork.equals("10")){%> selected="selected"  <%} %>  >ตุลาคม</option>
													<option value="11" <% if(periodWork.equals("11")){%> selected="selected"  <%} %>  >พฤศจิกายน</option>
													<option value="12" <% if(periodWork.equals("12")){%> selected="selected"  <%} %>  >ธันวาคม</option>
												</select></td>
							<td id="decDay"><input type="text"  name="decDay"  maxlength="6" onchange="checkDay(this);"  style="text-align:center;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="remark"><input type="text"  name="remark"  maxlength="100" align="center" style="text-align:left;width:100%;" /><input type="hidden"  name="keySeq" /></td>
					</tr>
					</table>
				</div>
           </td>
      </tr>
          
</table>&nbsp;

<input type="hidden" name="pageEdit" value="">
<table width="100%" CLASS="TABLEBULE2" >
					<tr CLASS="TABLEBULE2" >
						<td align="left" >&nbsp;
							<input type="Button" class=" button " value="เพิ่มข้อมูล" name="insertData" onclick="addVisualRow();" disabled="disabled"/>						
							<input type="Button" class=" button " value="ลบข้อมูล" name="deleteData" onclick="removeVisualRow();" disabled="disabled"/>						
							<input type="Button" class=" button " value="ตกลง" name="confirmData" onclick="insertNewData();" disabled="disabled"/>
							<input type="Button" class=" button " name="back" value=" ออก " onclick="backFormMain();"/>
						</td>
					</tr>
				</table>
</form>
	<form name="backForm" action="security.htm?reqCode=CTWPAYMT010" method="post">
		<input type="hidden" name="orgFromEdit" />
		<input type="hidden" name="orgToEdit" />
		<input type="hidden" name="empCodeFromEdit" />
		<input type="hidden" name="empCodeToEdit" />
		<input type="hidden" name="pageEdit" />
		<input type="hidden" name="orderFromCboEdit" />
		<input type="hidden" name="orderToCboEdit" />	
		<input type="hidden" name="mustQuery" />	
	</form>
</body>
<script type="text/javascript">
		
</script>
</html>

<SCRIPT LANGUAGE="JavaScript">
<!--
function addVisualRow(){

	var table = document.getElementById("table");
	var tempRow = document.getElementById("temprow");

	// Insert two rows.
   	var oTable = table;
   	var idx = oTable.rows.length;
   	var oRow1=oTable.insertRow(idx);
   	var str=" " ;
	var from = "[0]";
	var to = "("+(oRow1.rowIndex+tempRow.rowIndex)+")";

	// Insert cells into row.
	oRow1.id = tempRow.id;
	for(i=0;i<tempRow.cells.length;i++){
		var oCell=oRow1.insertCell(i);
		// Add regular HTML values to the cells.
		
		
		oCell.id = tempRow.cells[i].id;
		oCell.innerHTML=tempRow.cells[i].innerHTML;  
		str = oCell.innerHTML;
		if(str.indexOf(from)>0){
			oCell.innerHTML=str.replace(from, to);
		}
	}
	
}

	function removeVisualRow(){
		//alert('delete');
		var table = document.getElementById("table");
		var tdName;
		var chkName;
		
		
		var oRows = table.rows;
		if(tdName == null)tdName="flag";
		if(chkName == null)chkName="chk";
		//alert(oRows.length);
		if(oRows.length > 2){
			for(i=oRows.length-1;i > 1;i--){
				var answer = confirm("ต้องการลบข้อมูล หรือไม่?");
				if( answer ){
					if (oRows[i].cells[tdName].childNodes[0].checked){
							//alert('delete'+i);
							table.deleteRow(i);			
					}
					alert('ลบข้อมูลเรียบร้อย');	
				}
			}
		}
		
		
	}

//-->
</SCRIPT>