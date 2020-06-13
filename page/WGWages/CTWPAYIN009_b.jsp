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
	
	//String periodWork = String.valueOf(x);
	
	int xx = (Integer.parseInt(periodInsert)+1)/2;
	String periodWork = String.valueOf(xx-1);
	
	String year = yearInsert;
	if(periodWork.equals("0")){
		periodWork = "12";
		year =  String.valueOf(Integer.parseInt(yearInsert)-1);
	}
	//String[] salary = {"500", "5000", "50000","123","456","234","500", "5000", "50000","123","456","234","500", "5000", "50000","123","456","234","500", "5000", "50000","123","456","234","11","11","11","11","11","11","11","11","11","11","11","11","11","11","11","55","44","33","22"};
	//System.out.println("___________________LAST___________________________________"+salary.size());
%>
<html>
<head>
<title>บันทึกรายการหักกรณีวินัย</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<!-- Javascript Script File -->
<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
<script type="text/javascript" src="dwr/interface/FeeWpayRwVinaiService.js"></script>
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
	
	var rwVinai = {	keySeq:null,ouCode:null,yearPr:null,periodPr:null,empCode:null,
					yearWork:null,periodWork:null,codeSeq:null,
					decSal:null,newSalary:null,decSalMonth:null,
					cutSalYear:null,cutSalMonth:null,cutSalPercent:null,confirmFlag:null,
					absYear1:null,absMonth1:null,absDay1:null,
					absYear2:null,absMonth2:null,absDay2:null,
					startDateQut:null,endDateQut:null,confirmFlag:null,
					updBy:null,updDate:null,creBy:null,creDate:null,approveFlag:null};
	
	function ClearData(){
		alert("บันทึกข้อมูลเรียบร้อย");
		var table = document.getElementById("table");
		var tdName;
		var chkName;
		
		
		var oRows = table.rows;
		if(tdName == null)tdName="flag";
		if(chkName == null)chkName="chk";
		//alert(oRows.length);
		for(i=oRows.length-1;i > 2;i--){
			table.deleteRow(i);			
		}
		
		$("empCbo").value = '';
		$("name").value = '';
		//$("positionShort").value = '';
		$("orgCode").value = '';
		$("orgDesc").value = '';
	}
	
	function insertNewData()
	{
		
			var table = document.getElementById("table");
			var tdName;
			var chkName;
			
			var oRows = table.rows;
			
			if(tdName == null)tdName="flag";
			if(chkName == null)chkName="chk";
			if(oRows.length > 3)
			{
				DWREngine.beginBatch();
				for(var i=oRows.length-1;i > 2;i--)
				{
					rwVinai.ouCode = '<%=ouCodeInsert%>';
					rwVinai.yearPr = '<%=yearInsert%>';
					rwVinai.periodPr = '<%=periodInsert%>';
					rwVinai.empCode = DWRUtil.getValue("empCbo");
					rwVinai.codeSeq = $("codeSeq").value;
					rwVinai.flagPr= oRows[i].cells["flagPr"].childNodes[0].value;
					rwVinai.yearWork = '<%=yearInsert%>';
					rwVinai.periodWork = '<%=periodWork%>';
					
					if(oRows[i].cells["cutSalYear"].childNodes[0].value != null && oRows[i].cells["cutSalYear"].childNodes[0].value != ''){
						rwVinai.cutSalYear = oRows[i].cells["cutSalYear"].childNodes[0].value;
					}else{
						rwVinai.cutSalYear = null;
					}
					if(oRows[i].cells["cutSalMonth"].childNodes[0].value != null && oRows[i].cells["cutSalMonth"].childNodes[0].value != ''){
						rwVinai.cutSalMonth = oRows[i].cells["cutSalMonth"].childNodes[0].value;
					}else{
						rwVinai.cutSalMonth = null;
					}
					if(oRows[i].cells["cutSalPercent"].childNodes[0].value != null && oRows[i].cells["cutSalPercent"].childNodes[0].value != ''){
						rwVinai.cutSalPercent = oRows[i].cells["cutSalPercent"].childNodes[0].value;
					}else{
						rwVinai.cutSalPercent = null;
					}
					if(oRows[i].cells["absYear1"].childNodes[0].value != null && oRows[i].cells["absYear1"].childNodes[0].value != ''){
						rwVinai.absYear1 = oRows[i].cells["absYear1"].childNodes[0].value;
					}else{
						rwVinai.absYear1 = null;
					}
					if(oRows[i].cells["absMonth1"].childNodes[0].value != null && oRows[i].cells["absMonth1"].childNodes[0].value != ''){
						rwVinai.absMonth1 = oRows[i].cells["absMonth1"].childNodes[0].value;
					}else{
						rwVinai.absMonth1 = null;
					}
					if(oRows[i].cells["absDay1"].childNodes[0].value != null && oRows[i].cells["absDay1"].childNodes[0].value != ''){
						rwVinai.absDay1 = oRows[i].cells["absDay1"].childNodes[0].value;
					}else{
						rwVinai.absDay1 = null;
					}
					if(oRows[i].cells["startDateQut"].childNodes[0].value != null && oRows[i].cells["startDateQut"].childNodes[0].value != ''){
						rwVinai.startDateQut = getDateFromFormat(oRows[i].cells["startDateQut"].childNodes[0].value,"dd/MM/yyyy");
					}else{
						rwVinai.startDateQut = null;
					}
					if(oRows[i].cells["endDateQut"].childNodes[0].value != null && oRows[i].cells["endDateQut"].childNodes[0].value != ''){
						rwVinai.endDateQut = getDateFromFormat(oRows[i].cells["endDateQut"].childNodes[0].value,"dd/MM/yyyy");
					}else{
						rwVinai.endDateQut = null;
					}
					rwVinai.confirmFlag = 'N';
					rwVinai.approveFlag = 'N';
					rwVinai.updBy = '<%=userIdInsert%>';
					rwVinai.creBy = '<%=userIdInsert%>';
					rwVinai.creDate = getDateFromFormat(<%=date%>,"dd/MM/yyyy");
					//RwVinaiService.insertRwVinai(rwVinai, {callback:onInsertCallback});
					//RwVinaiService.addList(rwVinai, {callback:onInsertCallback});
			
					if( i == 3 )
						FeeWpayRwVinaiService.addList(rwVinai, true, {callback:ClearData,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้');}});
					else
						FeeWpayRwVinaiService.addList(rwVinai, false, {callback:onInsertCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
				}
				DWREngine.endBatch();
				//RwVinaiService.insertAndUpdate({callback:ClearData});
				
			}else
			{
				alert('ไม่พบข้อมูลที่จะบันทึก');
			}
		
	}
	
	function onInsertCallback(data){
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
					document.forms['formInsert'].elements['insertData'].disabled = false;
					document.forms['formInsert'].elements['deleteData'].disabled = false;
					document.forms['formInsert'].elements['confirmData'].disabled = false;	
		}else{
			$("name").value = '';
			//$("positionShort").value = '';
			$("orgCode").value = '';
	    	$("orgDesc").value = '';
			$("codeSeq").value = '';
		
					document.forms['formInsert'].elements['insertData'].disabled = true;
					document.forms['formInsert'].elements['deleteData'].disabled = true;
					document.forms['formInsert'].elements['confirmData'].disabled = true;
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
 	 
 	 
 	 function compareStDate(object){
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
	
		var stDate = oRows[rowToCompare].cells["startDateQut"].childNodes[0].value;
		var endDate = oRows[rowToCompare].cells["endDateQut"].childNodes[0].value;
		
		
		var strDay = stDate.substr(0, 2);
		var strMonth = stDate.substr(3, 2);
		var strYear = stDate.substr(6);
		
		if(stDate != null && stDate != '' && endDate != null && endDate != ''){
		 	if (compareDates(stDate,"dd/MM/yyyy",endDate,"dd/MM/yyyy") == 1) {
				alert("วันที่เริ่มต้นต้องน้อยกว่า วันที่สิ้นสุด");
				oRows[rowToCompare].cells["endDateQut"].childNodes[0].value = '';
				oRows[rowToCompare].cells["startDateQut"].childNodes[0].focus();
			}
	 	}
	 	
	 	
	 
	 }
	 
	 function compareEndDate(object){
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
	
		var stDate = oRows[rowToCompare].cells["startDateQut"].childNodes[0].value;
		var endDate = oRows[rowToCompare].cells["endDateQut"].childNodes[0].value;
	
		
		var endDay = endDate.substr(0, 2);
		var endMonth = endDate.substr(3, 2);
		var endYear = endDate.substr(6);
		
		if(stDate != null && stDate != '' && endDate != null && endDate != ''){
		 	if (compareDates(stDate,"dd/MM/yyyy",endDate,"dd/MM/yyyy") == 1) {
				alert("วันที่เริ่มต้นต้องน้อยกว่า วันที่สิ้นสุด");
				oRows[rowToCompare].cells["startDateQut"].childNodes[0].value = '';
				oRows[rowToCompare].cells["endDateQut"].childNodes[0].focus();
			}
	 	}
	}
	
	function checkPercent(object){
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
	
		//var month = oRows[rowToCompare].cells["absMonth1"].childNodes[0].value;
		var day = oRows[rowToCompare].cells["cutSalPercent"].childNodes[0].value;
		
		
		
		if(day != null && day != '' && day != 0){
		
				if(day > 100){
				    	alert('จำนวนเปอร์เซนต์ไม่ถูกต้อง');
				    	oRows[rowToCompare].cells["cutSalPercent"].childNodes[0].value = '';
				    }
				    break;
			
				} 
		
		
		}
	 function checkPercentRowUpdate(data){
	 	 var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["formEdit"];
		//alert('leng  :'+tab.rows.length);	
		
		if(tab.rows.length > 0){
			var keySeq 		= frm.elements["keySeq"];
			//var monthCheck 	= frm.elements["absMonth1"];
			var dayCheck 		= frm.elements["cutSalPercent"];
		
			for(var c=0; c<tab.rows.length; c++){
				//alert(keySeq[c].value + ':::'+ data);
				if(keySeq[c].value == data ){
					rowModify = c;
				//	alert('value in row :'+keySeq[c].value+' : '+flagPr[c].value+' : '+yearWork[c].value+' : '+periodWork[c].value+' : '+fiscalYear[c].value+' : '+startDate[c].value+' : '+endDate[c].value+' : '+totDay1[c].value+' : '+totDay15[c].value+' : '+totDay3[c].value+' : '+seqData[c].value+' : '+codeSeqNew[c].value+' : '+flagWork[c].value+' : '+orgCode[c+1].value);
					break;
				}
			}
			
		
			
			var day = dayCheck[rowModify].value;
		
			
			
			
		 	
		
		
				if(day != null && day != '' && day != 0){
				
					    if(day > 100){
						    	alert('จำนวนเปอร์เซนต์ไม่ถูกต้อง');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						
						} 
				
				
				}
	
		
	
	 	}
	 	
	 
	 
	
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
	
		var month = oRows[rowToCompare].cells["absMonth1"].childNodes[0].value;
		var day = oRows[rowToCompare].cells["absDay1"].childNodes[0].value;
		
		if(day != null && day != '' && day != 0){
		
			switch (month) {
				  case "1":
				    if(day > 31){
				    	alert('จำนวนวันไม่ถูกต้อง');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "2":
				    if(day > 29){
				    	alert('จำนวนวันไม่ถูกต้อง');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "3":
				    if(day > 31){
				    	alert('จำนวนวันไม่ถูกต้อง');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "4":
				    if(day > 30){
				    	alert('จำนวนวันไม่ถูกต้อง');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "5":
				    if(day > 31){
				    	alert('จำนวนวันไม่ถูกต้อง');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "6":
				    if(day > 30){
				    	alert('จำนวนวันไม่ถูกต้อง');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "7":
				    if(day > 31){
				    	alert('จำนวนวันไม่ถูกต้อง');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "8":
				    if(day > 31){
				    	alert('จำนวนวันไม่ถูกต้อง');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "9":
				    if(day > 30){
				    	alert('จำนวนวันไม่ถูกต้อง');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "10":
				    if(day > 31){
				    	alert('จำนวนวันไม่ถูกต้อง');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "11":
				    if(day > 30){
				    	alert('จำนวนวันไม่ถูกต้อง');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "12":
				    if(day > 31){
				    	alert('จำนวนวันไม่ถูกต้อง');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				} 
		
		
		}
		//alert(month+' ::: '+day)
		
	
	 	
	 	
	 
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
	
		
	 	
	 	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
	 	
	 	
	 	
	 
	 }
	 function bypassCut(object){
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
	
		
	 	
	 	oRows[rowToCompare].cells["cutSalPercent"].childNodes[0].value = '';
	 	
	 	
	 	
	 
	 }
 	
-->
</script>
</head>

<body>
<form name="formInsert"  action="security.htm?reqCode=CTWPAYMT009" method="post">
<input type="hidden" name="dataLength">
<input type="hidden" name="codeSeq">
<input type="hidden" name="period">
<table width="100%">
	<tr>
		<td class="font-head">
			[ CTWPAYIN009 ] บันทึกรายการหักกรณีวินัย
		</td>
	</tr>
</table>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">

      <tr>
          <td  align="center">
				<table width="750" border="1" cellpadding="1" bordercolor="#4A4A4A" cellspacing="1"  align="center">
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
				<div style="height:345px;width:1000;overflow:auto;">
				 &nbsp;
	  			&nbsp;
	  			&nbsp;
				<table id="table"  bordercolor="#4A4A4A" align="center" style="text-align: center;width:1000px" border="1" cellpadding="0" cellspacing="0" >
					<thead style="text-align: center">
						<tr CLASS="TABLEBULE2">
							<th CLASS="TABLEBULE2" style="width:50px" rowspan="2" align="center">ลบ</th>
							<th CLASS="TABLEBULE2"  rowspan="2" align="center">ประเภทรายการ</th>
							<th CLASS="TABLEBULE2" style="width:250px" colspan="3" rowspan="1" align="center"><center>ตัดเงินเดือน</center></th>
							<th CLASS="TABLEBULE2" style="width:250px" colspan="3" rowspan="1" align="center"><center>ขาดงาน</center></th>
							<th CLASS="TABLEBULE2" style="width:225px" colspan="2" rowspan="1" align="center"><center>พักงาน</center></th>
						</tr>
						<tr CLASS="TABLEBULE2"  >
							<th CLASS="TABLEBULE2" colspan="2" align="center">ปี/เดือน</th>
							<th CLASS="TABLEBULE2" style="width:50px" align="center">จำนวน (%)</th>
							<th CLASS="TABLEBULE2" colspan="2" align="center">ปี/เดือน</th>
							<th CLASS="TABLEBULE2" style="width:50px"align="center">จำนวน(วัน)</th>
							<th CLASS="TABLEBULE2" align="center">ตั้งแต่วันที่</th>
							<th CLASS="TABLEBULE2" align="center">ถึงวันที่</th>
						</tr>
					</thead>
						<tr id="temprow" style="visibility:hidden;position:absolute">
							<td id="flag"><input type="checkbox" name="chk" /></td>
							<td id="flagPr">
										<select name="type">
												<option value="N" >ปกติ</option>
												<option value="A" >ปรับปรุงรายการหัก</option>
												<!-- <option value="R" >ปรับปรุงรายการรับเรียกคืน</option>
												<option value="B" >ตกเบิกปรับปรุงรายการรับ</option>
												<option value="S" >ตกเบิกรายการรับเรียกคืน</option>-->
										</select>
							</td>
							<td id="cutSalYear" ><input type="text"  name=cutSalYear  size="6" maxlength="4" style="text-align:center;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="cutSalMonth" ><select name="cutSalMonth" onchange="bypassCut(this);">
													<option value="" >-- เลือก --</option>
													<option value="1">มกราคม</option>
													<option value="2">กุมภาพันธ์</option>
													<option value="3">มีนาคม</option>
													<option value="4">เมษายน</option>
													<option value="5">พฤษภาคม</option>
													<option value="6">มิถุนายน</option>
													<option value="7">กรกฏาคม</option>
													<option value="8">สิงหาคม</option>
													<option value="9">กันยายน</option>
													<option value="10">ตุลาคม</option>
													<option value="11">พฤศจิกายน</option>
													<option value="12">ธันวาคม</option>
												</select>
							</td>
							<td id="cutSalPercent" ><input type="text"  name=cutSalPercent  maxlength="9" onchange="checkPercent(this);" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<!-- <td id="absYear1" ><input type="text" value="<%=yearInsert%>" name=absYear1 size="6" maxlength="4" style="text-align:center;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>  -->
							<td id="absYear1" ><input type="text"  name=absYear1 size="6" maxlength="4" style="text-align:center;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="absMonth1" ><select name="absMonth1" onchange="bypass(this);">
													<option value="" >-- เลือก --</option>
													<option value="1">มกราคม</option>
													<option value="2">กุมภาพันธ์</option>
													<option value="3">มีนาคม</option>
													<option value="4">เมษายน</option>
													<option value="5">พฤษภาคม</option>
													<option value="6">มิถุนายน</option>
													<option value="7">กรกฏาคม</option>
													<option value="8">สิงหาคม</option>
													<option value="9">กันยายน</option>
													<option value="10">ตุลาคม</option>
													<option value="11">พฤศจิกายน</option>
													<option value="12">ธันวาคม</option>
												</select>
							</td>
							<!--  
													<option value="" <% if(periodWork.equals("")){%> selected="selected"  <%} %> >-- เลือก --</option>
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
							-->
												
							<td id="absDay1" ><input type="text"  name=absDay1  maxlength="6" style="text-align:right;width:100%;" onchange="checkDay(this);" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="startDateQut" ><input type="text"  name="endDateJob" size="12" maxlength="10" align="center" style="text-align:center;" onchange="return validateDate(this) && compareStDate(this);"></td>
							<td id="endDateQut" ><input type="text"  name="endDateJob" size="12" maxlength="10" align="center" style="text-align:center;" onchange="return validateDate(this) && compareEndDate(this);"><input type="hidden"  name="keySeq" /></td>
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
	<form name="backForm" action="security.htm?reqCode=CTWPAYMT009" method="post">
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
		for(i=oRows.length-1;i > 2;i--){
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

//-->
</SCRIPT>