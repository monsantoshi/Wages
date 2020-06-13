<%@ page language="java" contentType="text/html;charset=TIS-620" %>
<%@page import="java.text.DecimalFormat"%>
<%@page import="javax.swing.text.Document"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@ page import="com.ss.tp.security.UserInfo" %>

<%
	UserInfo userInfo = (UserInfo)session.getAttribute("UserLogin");
	String userId = userInfo.getUserId();
	String ouCode = userInfo.getOuCode();
	
	String orgFromEdit = request.getParameter("orgFromEdit");
	String orgToEdit = request.getParameter("orgToEdit");
	String empCodeFromEdit = request.getParameter("empCodeFromEdit");
	String empCodeToEdit = request.getParameter("empCodeToEdit");
	
	String orderFromCboEdit = request.getParameter("orderFromCboEdit");
	String orderToCboEdit = request.getParameter("orderToCboEdit");
	
	String pageEdit = request.getParameter("pageEdit")==null?"-1":request.getParameter("pageEdit");
	String mustQuery = request.getParameter("mustQuery");
%>
<html>
<head>
<title>บันทึกรายการปรับปรุงวันทำงาน</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="script/payroll_util.js"></script>
<!-- Javascript Script File -->
<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrPeriodLineService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPnEmployeeService.js"></SCRIPT>
<script type="text/javascript" src="dwr/interface/FeeWpayRwVinai2Service.js"></script>


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
	//Begin load page**********************
		// Load ComboBox From base
		     function onLoadOrganizationCallback(){
		     	try{
			     	var cboSource = [];
			     	var cboFrom = dojo.widget.byId("orgFromCbo");
			     	var cboTo = dojo.widget.byId("orgToCbo");
			     	/*for(var i=0; i<data.length; i++){
			     		var org = data[i];
			     		cboSource.push([org.orgCode, org.orgCode]);
			     	}*/
			     	
			     	<c:forEach items="${OrganizationInSecurity}" var="result" >		 
						cboSource.push(["<c:out value='${result.orgCode} ${result.divShort} ${result.areaDesc} ${result.secDesc} ${result.workDesc}' />","<c:out value='${result.orgCode}' />"]);
					</c:forEach>
			     	cboFrom.dataProvider.setData(cboSource);
			     	cboTo.dataProvider.setData(cboSource);
			 
			   }catch(e){
		     		alert(e.message);
		     	}
		     }
		     
		    
		     function onLoadEmployeeCallback(){
		     	try{
		     	//	DWRUtil.useLoadingMessage("Loading ...");
			     	var cboSource = [];
			     	var cboFrom = dojo.widget.byId("empFromCbo");
			     	var cboTo = dojo.widget.byId("empToCbo");
			     	/*for(var i=0; i<data.length; i++){
			     		var emp = data[i];
			     		cboSource.push([emp.empCode, emp.empCode]);
			     	}*/
			     	
			     	<c:forEach items="${PrEmployeeInSecurity}" var="result" >		 
						cboSource.push(["<c:out value='${result.empCode}' />","<c:out value='${result.empCode}' />"]);
					</c:forEach>
			     	cboFrom.dataProvider.setData(cboSource);
			     	cboTo.dataProvider.setData(cboSource);
			     	
			     	<%
			     		if( mustQuery != null && mustQuery.endsWith("true")){
														
			     	%>
			     	
			     			var cboOrgFrom = dojo.widget.byId("orgFromCbo");
			     			var cboOrgTo = dojo.widget.byId("orgToCbo");
			     			var cboEmpFrom = dojo.widget.byId("empFromCbo");
			     			var cboEmpTo = dojo.widget.byId("empToCbo");
			     			
			     			var orderFrom = document.getElementById("orderFromCbo");
			     			var orderTo= document.getElementById("orderToCbo");
			     			
			     			cboOrgFrom.textInputNode.value = '<%= orgFromEdit %>';
			     			cboOrgTo.textInputNode.value = '<%= orgToEdit %>';
			     			cboEmpFrom.textInputNode.value = '<%= empCodeFromEdit %>';
			     			cboEmpTo.textInputNode.value = '<%= empCodeToEdit %>';
			     			$("page").value = '<%= pageEdit %>';
			     			
			     			orderFrom.value = '<%= orderFromCboEdit%>';
			     			orderTo.value = '<%= orderToCboEdit %>';
			     			
			     			whenShowDataTable();
			     			
			     	<%
			     	mustQuery = "false";
			     		}
			     	%>		
			     	
		     	}catch(e){
		     		alert(e.message);
		     	}
		     } 
		     
		     function onLoadYearSectionCallback(){
					$("year").value =   "<c:out value='${DefaultYearAndSection.year}' /> "  ;
				$("section").value = "<c:out value='${DefaultYearAndSection.section}' /> "  ;//data.section;
				$("period").value = ""+"<c:out value='${DefaultYearAndSection.period}' /> "  ;//data.period;
				$("confirm").value = "<c:out value='${ConfirmRwVinai2}' /> "  ;//data.confirm;
				//PnEmployeeService.findPrEmpBySecurity('<%=userId%>','<%=ouCode%>',$("year").value,$("period").value, {callback:onLoadEmployeeCallback});
		     	chkMainClose();
				if(<c:out value='${ConfirmRwVinai2}' />){
					document.forms['searchForm'].elements['add'].disabled = true;
				}
				//alert(data.period);
			}
		     
		     
		     // Even ComboBox valueChange
		    function whenSelectOrgOption(){
		    	DWRUtil.useLoadingMessage("Loading ...");
				var cbo = dojo.widget.byId("orgFromCbo");
				whenFetchOrganizationTo(splitCombo(cbo.textInputNode.value));
			}
			function whenFetchOrganizationTo(orgCode){
		     	DWRUtil.useLoadingMessage("Loading ...");
		     	var cboTo = dojo.widget.byId("orgToCbo");
		     	
		     	if( orgCode > splitCombo(cboTo.textInputNode.value) ){
			     	cboTo.textInputNode.value = '';
			     	cboTo.comboBoxSelectionValue.value = '';
		     	}
		     	
		     	var cboSource = [];
		     	
		     	<c:forEach items="${OrganizationInSecurity}" var="result" >		 
					if( <c:out value='${result.orgCode}' /> >= orgCode )
						cboSource.push(["<c:out value='${result.orgCode} ${result.divShort} ${result.areaDesc} ${result.secDesc} ${result.workDesc}' />","<c:out value='${result.orgCode}' />"]);
				</c:forEach>
		     	
		     	cboTo.dataProvider.setData(cboSource);
		     	//SuUserOrganizationService.findOrganizationByUserIdAndOuCodeToOrgCode('<%=userId%>','<%=ouCode%>',orgCode , {callback:whenFetchOrganizationToCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		    }
		    function whenFetchOrganizationToCallback(data){
		    	try{
			     	var cboSource = [];
			     	var cboTo = dojo.widget.byId("orgToCbo");
			     	for(var i=0; i<data.length; i++){
			     		var org = data[i];
			     		cboSource.push([org.orgCode, org.orgCode]);
			     	}
			     	cboTo.dataProvider.setData(cboSource);
		     	}catch(e){
		     		alert(e.message);
		     	}
		    }
			 function whenSelectEmpOption(){
		    	DWRUtil.useLoadingMessage("Loading ...");
				var cbo = dojo.widget.byId("empFromCbo");
				whenFetchEmployeeTo(cbo.textInputNode.value);
			}
			function whenFetchEmployeeTo(empCode){
		     	DWRUtil.useLoadingMessage("Loading ...");
		     	var cboTo = dojo.widget.byId("empToCbo");
		     	
		     	if( empCode > cboTo.comboBoxSelectionValue.value ){
			     	cboTo.textInputNode.value = '';
			     	cboTo.comboBoxSelectionValue.value = '';
		     	}
		     	
		     	var cboSource = [];
		     	
		     	<c:forEach items="${PrEmployeeInSecurity}" var="result" >	
		     		 
		     		var tmp = '<c:out value="${result.empCode}" />'+'.00';
		     		if( parseFloat(tmp.toString()) >= parseFloat(empCode) )
						cboSource.push(["<c:out value='${result.empCode}' />","<c:out value='${result.empCode}' />"]);
				</c:forEach>	
		     	
		     	cboTo.dataProvider.setData(cboSource);
		     	//PnEmployeeService.findPrToEmpBySecurity('<%=userId%>','<%=ouCode%>',empCode,$("year").value,$("period").value , {callback:whenFetchEmployeeToCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		    }
		    function whenFetchEmployeeToCallback(data){
		    	try{
			     	var cboSource = [];
			     	var cboTo = dojo.widget.byId("empToCbo");
			     	for(var i=0; i<data.length; i++){
			     		var emp = data[i];
			     		cboSource.push([emp.empCode, emp.empCode]);
			     	}
			     	cboTo.dataProvider.setData(cboSource);
		     	}catch(e){
		     		alert(e.message);
		     	}
		    }
			function init(){
				var cboOrgFrom = dojo.widget.byId("orgFromCbo");
				var cboEmpFrom = dojo.widget.byId("empFromCbo");
				dojo.event.connect(cboOrgFrom, "selectOption", "whenSelectOrgOption");
				dojo.event.connect(cboEmpFrom, "selectOption", "whenSelectEmpOption");
			}
			
			function chkMainClose(){
				FeeWpayPrPeriodLineService.findPeriodLine('<%=ouCode%>',$("year").value,$("period").value , {callback:whenchkMainCloseCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		    }
			
			function whenchkMainCloseCallback(data){
				if(data.mainClose == 'Y'){
					alert('ไม่สามารถดำเนินการต่อได้');
					window.history.back()
				}
			}
			
			// Load page
		  	dojo.addOnLoad(init);
			dojo.addOnLoad(onLoadOrganizationCallback);
			dojo.addOnLoad(onLoadYearSectionCallback);
			dojo.addOnLoad(onLoadEmployeeCallback);
	
	
	
	
	
	//End Load page ***********************
	function countData(){
		var orgFromCbo = dojo.widget.byId("orgFromCbo");
		var orgToCbo = dojo.widget.byId("orgToCbo");
		
		var empFromCbo = dojo.widget.byId("empFromCbo");
		var empToCbo = dojo.widget.byId("empToCbo");
		
		var orgFromVal = '';
		var orgToVal = '';
		var empFromVal = '';
		var empToVal = '';
		
		if(orgFromCbo.textInputNode.value != ''){
			orgFromVal = splitCombo( orgFromCbo.textInputNode.value );
		}else{
			orgFromVal = '';
		}
		
		if(orgToCbo.textInputNode.value != ''){
			orgToVal = splitCombo( orgToCbo.textInputNode.value );
		}else{
			orgToVal = '';
		}
		
		if(empFromCbo.textInputNode.value != ''){
			empFromVal = splitCombo( empFromCbo.textInputNode.value );
		}else{
			empFromVal = '';
		}
		
		if(empToCbo.textInputNode.value != ''){
			empToVal = splitCombo( empToCbo.textInputNode.value );
		}else{
			empToVal = '';
		}
		
		
		FeeWpayRwVinai2Service.countData
		(
			'<%=userId%>',
			'<%=ouCode%>',
			parseInt(DWRUtil.getValue("year")),
			parseInt(DWRUtil.getValue("period")),
			orgFromVal,
			orgToVal,
			empFromVal,
			empToVal,
			document.forms['searchForm'].elements['orderFromCbo'].value,
			document.forms['searchForm'].elements['orderToCbo'].value,
			{callback:countDataHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}}
		);
	}
	
	function countDataHandler(data)
	{
		DWRUtil.setValue("countData",data);
		onCheckButt("searchForm");
	    
	}
	
	function whenShowDataTable()
	{
		var orgFromCbo = dojo.widget.byId("orgFromCbo");
		var orgToCbo = dojo.widget.byId("orgToCbo");
		
		var empFromCbo = dojo.widget.byId("empFromCbo");
		var empToCbo = dojo.widget.byId("empToCbo");
		
		var orgFromVal = '';
		var orgToVal = '';
		var empFromVal = '';
		var empToVal = '';
		
		if(orgFromCbo.textInputNode.value != ''){
			orgFromVal = splitCombo( orgFromCbo.textInputNode.value );
		}else{
			orgFromVal = '';
		}
		
		if(orgToCbo.textInputNode.value != ''){
			orgToVal = splitCombo( orgToCbo.textInputNode.value );
		}else{
			orgToVal = '';
		}
		
		if(empFromCbo.textInputNode.value != ''){
			empFromVal = splitCombo( empFromCbo.textInputNode.value );
		}else{
			empFromVal = '';
		}
		
		if(empToCbo.textInputNode.value != ''){
			empToVal = splitCombo( empToCbo.textInputNode.value );
		}else{
			empToVal = '';
		}

		FeeWpayRwVinai2Service.findByCriteria
		(
			'<%=userId%>',
			'<%=ouCode%>',
			parseInt(DWRUtil.getValue("year")),
			parseInt(DWRUtil.getValue("period")),
			orgFromVal,
			orgToVal,
			empFromVal,
			empToVal,
			document.forms['searchForm'].elements['orderFromCbo'].value,
			document.forms['searchForm'].elements['orderToCbo'].value,
			DWRUtil.getValue("page"),
			DWRUtil.getValue("dataPerPage"),
			{callback:whenListDataTableHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสารddd');}}
		);
	}
	
	var cellFuncs = [
		function(data) { return "<div align='center'>"+data.empCode+"</div>";},
		function(data) { return data.name;},
		function(data) { return writeButton("edit",data.empCode);}
	];
	
	function whenListDataTableHandler(data)
	{
		//alert('dataSize ' + data.length);
		if(data.length > 0){
			DWRUtil.removeAllRows("dataTable");
			DWRUtil.addRows("dataTable",data,cellFuncs);
			if(DWRUtil.getValue("showMaxPage") == ''){
				countData();
			}else{
				onCheckButt("searchForm");
			}
		}else{
			alert('ไม่พบข้อมูล');
			DWRUtil.removeAllRows("dataTable");
			if(DWRUtil.getValue("showMaxPage") == ''){
				countData();
			}else{
				onCheckButt("searchForm");
			}
		}
	}
	
	function writeCheckBox()
	{
		return "<div align='center'><input type='checkbox' name ='chk' onclick='doCheck(document.forms[\"form1\"]);' /></div>";
	}
	
	function writeButton(inname,emp)
	{
		return "<div align='center'><input type='button' class='button' name = '"+inname+"' value='...' onclick='preEdit(this.empId);' empId='"+emp+"' /></div>";
	}
	




	var emp = {keySeq:null};
	
	function onDelete(){
		var tab = $('dataTable');
		var row;
		var empList=[];
		var frm = document.forms["form1"];
		var chk = frm.elements["chk"];
		DWREngine.beginBatch();
	    if(tab.rows.length>1){
			for(i=0; i<tab.rows.length; i++){
				row = tab.rows[i];	
				if (chk[i].checked){
						emp.keySeq  = DWRUtil.getValue("keySeq"); 
						emp.empCode = row.cells[1].innerHTML;
						FeeWpayRwVinai2Service.deleteRwVinai2(emp, {callback:onDeleteCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
					}
				
					
			}
		}else{
			if(tab.rows.length==1){
				row = tab.rows[0];	
				if (chk.checked){
						emp.keySeq  = DWRUtil.getValue("keySeq");
						emp.empCode = row.cells[1].innerHTML;
						FeeWpayRwVinai2Service.deleteRwVinai2(emp, {callback:onDeleteCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
					}
			  
			}	
		}
		
		DWREngine.endBatch();
	}
	function onDeleteCallback(){
		alert("Delete Complete");
		whenShowDataTable();
	}
	
	function preIns(){ 
		var frm=document.forms["insertForm"];
		DWRUtil.setValue("pageInsert",DWRUtil.getValue("page"));
		DWRUtil.setValue("periodInsert",parseInt(DWRUtil.getValue("period")));
		DWRUtil.setValue("yearInsert",parseInt(DWRUtil.getValue("year")));
		DWRUtil.setValue("userIdInsert",'<%=userId%>');
		DWRUtil.setValue("ouCodeInsert",'<%=ouCode%>');
		DWRUtil.setValue("sectionInsert",DWRUtil.getValue("section"));
		
		var cboOrgFrom = dojo.widget.byId("orgFromCbo");
		var cboOrgTo = dojo.widget.byId("orgToCbo");
		var cboEmpFrom = dojo.widget.byId("empFromCbo");
		var cboEmpTo = dojo.widget.byId("empToCbo");
		
		var orderFrom = document.getElementById("orderFromCbo");
		var orderTo = document.getElementById("orderToCbo");
		
		$("orgFromIns").value = cboOrgFrom.textInputNode.value;
		$("orgToIns").value =cboOrgTo.textInputNode.value;
		$("empCodeFromIns").value = cboEmpFrom.textInputNode.value;
	 	$("empCodeToIns").value = cboEmpTo.textInputNode.value;
	 	
	 	$("orderFromCboIns").value = orderFrom.value;
	 	$("orderToCboIns").value = orderTo.value;
		
		$("pageIns").value = $("showPage").value;
		
		frm.submit();
	}
		
	function preEdit(empId){ 
		var frm=document.forms["editForm"];
		DWRUtil.setValue("pageEdit",DWRUtil.getValue("page"));
		DWRUtil.setValue("periodEdit",parseInt(DWRUtil.getValue("period")));
		DWRUtil.setValue("empCodeEdit",empId);
		DWRUtil.setValue("yearEdit",parseInt(DWRUtil.getValue("year")));
		DWRUtil.setValue("ouCodeEdit",'<%=ouCode%>');
		DWRUtil.setValue("userIdEdit",'<%=userId%>');
		DWRUtil.setValue("confirmEdit",DWRUtil.getValue("confirm"));
		DWRUtil.setValue("sectionEdit",DWRUtil.getValue("section"));
		
		var cboOrgFrom = dojo.widget.byId("orgFromCbo");
		var cboOrgTo = dojo.widget.byId("orgToCbo");
		var cboEmpFrom = dojo.widget.byId("empFromCbo");
		var cboEmpTo = dojo.widget.byId("empToCbo");
		
		var orderFrom = document.getElementById("orderFromCbo");
		var orderTo = document.getElementById("orderToCbo");
		
		$("orgFromEdit").value = cboOrgFrom.textInputNode.value;
		$("orgToEdit").value =cboOrgTo.textInputNode.value;
		$("empCodeFromEdit").value = cboEmpFrom.textInputNode.value;
	 	$("empCodeToEdit").value = cboEmpTo.textInputNode.value;
	 	
	 	$("orderFromCboEdit").value = orderFrom.value;
	 	$("orderToCboEdit").value = orderTo.value;
	 	
	 	$("pageEdit").value = $("showPage").value;		
		
	//	alert('page :'+DWRUtil.getValue("pageEdit")+ ' empID:'+DWRUtil.getValue("empCodeEdit")+ ' period:'+DWRUtil.getValue("periodEdit"));
		frm.submit();
	}
</script>
<%
	Calendar now = Calendar.getInstance(Locale.US);
	String year = ((now.get(Calendar.YEAR)+543)+"");
	String keySeq  = request.getParameter("keySeq");
%>
</head>
<body>
<form name="searchForm" action="" method="post">
<input type="hidden" name="period"> 
<input type="hidden" name="confirm">
<table width="100%">
	<tr>
		<td class="font-head">
			[ CTWPAYMT010 ] รายการเพิ่ม ลด วันทำงานที่ผิดพลาด
		</td>
	</tr>
</table>
<table width="770" border="0" align="center" cellspacing="1">

  <tr>
    <td class="font-field" align="right">ประจำปี&nbsp;</td>
    <td align="left"><input type="text" name="year"   value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/></td>
    <td  class="font-field" align="right">งวด&nbsp;</td>
    <td align="left"><input type="text" name="section"  value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/></td>
  </tr>
  <tr>
    <td class="font-field" align="right">ตั้งแต่สังกัดปฎิบัติงานจริง&nbsp;</td>
    <td align="left" colspan="3"><SELECT  dojoType="ComboBox"  widgetId="orgFromCbo" style="width:96%" onBlurInput="whenSelectOrgOption();"></SELECT>
</td>
</tr>
<tr>
    <td  class="font-field" align="right">ถึงสังกัดปฎิบัติงานจริง&nbsp;</td>
    <td align="left" colspan="3"><SELECT  dojoType="ComboBox" widgetId="orgToCbo" style="width:96%"></SELECT></td>
  </tr>
   <tr>
    <td class="font-field" align="right">ตั้งแต่เลขประจำตัว&nbsp;</td>
    <td align="left"><SELECT  dojoType="ComboBox" widgetId="empFromCbo" style="width:200" onBlurInput="whenSelectEmpOption();"></SELECT></td>
    <td  class="font-field" align="right">ถึงเลขประจำตัว&nbsp;</td>
    <td align="left"><SELECT  dojoType="ComboBox" widgetId="empToCbo" style="width:200"></SELECT></td>
  </tr>
 	<tr>
    <td class="font-field" align="right">ตั้งแต่ประเภทรายการ&nbsp;</td>
    <td align="left"><select name="orderFromCbo" style="width:200">
    				<option value="" >ทั้งหมด</option>
					<option value="A" >เพิ่มวันทำงาน</option>
					<option value="R" >ลดวันทำงาน</option>
					<!-- <option value="R" >ปรับปรุงรายการรับเรียกคืน</option>
					<option value="B" >ตกเบิกปรับปรุงรายการรับ</option>
					<option value="S" >ตกเบิกรายการรับเรียกคืน</option> -->
			</select></td>
    <td  class="font-field" align="right">ถึงประเภทรายการ&nbsp;</td>
    <td align="left"><select name="orderToCbo" style="width:200">
    				<option value="" >ทั้งหมด</option>
					<option value="A" >เพิ่มวันทำงาน</option>
					<option value="R" >ลดวันทำงาน</option>
					<!-- <option value="R" >ปรับปรุงรายการรับเรียกคืน</option>
					<option value="B" >ตกเบิกปรับปรุงรายการรับ</option>
					<option value="S" >ตกเบิกรายการรับเรียกคืน</option> -->
			</select></td>
    <td align="center"><input type="Button" class=" button " value="ค้นหา" onclick="onQuery(whenShowDataTable);" /></td>
  </tr>

</table>
<br/>
<div style="height:320px;">
<table width="770"  border="1" bordercolor="#6699CC"  align="center"  cellpadding="2" cellspacing="0">
	<thead>
		<tr CLASS="TABLEBULE2">
		<th CLASS="TABLEBULE2" width="100">เลขประจำตัว</th>
		<th CLASS="TABLEBULE2">ชื่อ - นามสกุล</th>
		<th CLASS="TABLEBULE2" width="50">แก้ไข</th>
		<tr>
	</thead>
	<tbody id="dataTable">
	</tbody>
</table>
</div>
<div>
<table width="770" align="center"  cellpadding="2" cellspacing="0" >
	<tr>
		<td align="right">
			<input type="hidden" name="page" value="<%=pageEdit%>">
			<input type="hidden" name="maxPage">
			<input type="hidden" name="countData" >
			<input type="hidden" name="dataPerPage" value="10">
			<input type="button" disabled="disabled" class=" button " value="First" name="first" onclick="onFirst(whenShowDataTable);"/>
			<input type="button" disabled="disabled" class=" button " value="<<" name="previous" onclick="onPrevious(whenShowDataTable);"/>
			<input type="text"  name="showPage" style="text-align:right;width: 40;" 
				    onkeyup="onCheckPageNAN(this.value);" onchange="onChangeGoPage(whenShowDataTable);" onkeypress="onKeyGoPage(event,whenShowDataTable);" 
			/>
			/
			<input type="text"  name="showMaxPage" readonly="readonly" style="width: 40;border-style : none;background-color : transparent;text-align:right;font-weight:bold;"/>
			<input type="button" disabled="disabled" class=" button " value=">>" name="next" onclick="onNext(whenShowDataTable);" />
			<input type="button" disabled="disabled" class=" button " value="Last" name="last" onclick="onLast(whenShowDataTable);"/>
		</td>
	</tr>
</table>
</div>
	<table width="100%" CLASS="TABLEBULE2">
		<tr CLASS="TABLEBULE2" >
			<td align="left" >&nbsp;
				<input type="button" class=" button "  value="เพิ่มข้อมูล" name="add" onclick="preIns();"/>
			</td>
		</tr>
	</table>
</form>
	<form name="insertForm" action="security.htm?reqCode=CTWPAYIN010" method="post">
		<input type="hidden" name="pageInsert">
		<input type="hidden" name="yearInsert">
		<input type="hidden" name="periodInsert">
		<input type="hidden" name="userIdInsert">
		<input type="hidden" name="ouCodeInsert">
		<input type="hidden" name="sectionInsert">
		
		<input type="hidden" name="orgFromIns" />
		<input type="hidden" name="orgToIns" />
		<input type="hidden" name="empCodeFromIns" />
		<input type="hidden" name="empCodeToIns" />
		<input type="hidden" name="pageIns" />
		<input type="hidden" name="orderFromCboIns" />
		<input type="hidden" name="orderToCboIns" />		
	</form>
	
	<form name="editForm" action="security.htm?reqCode=CTWPAYUP010" method="post">
		<input type="hidden" name="empCodeEdit">
		<input type="hidden" name="pageEdit">
		<input type="hidden" name="yearEdit">
		<input type="hidden" name="periodEdit">
		<input type="hidden" name="ouCodeEdit">
		<input type="hidden" name="confirmEdit">
		<input type="hidden" name="userIdEdit">
		<input type="hidden" name="sectionEdit">
		
		<input type="hidden" name="orgFromEdit" />
		<input type="hidden" name="orgToEdit" />
		<input type="hidden" name="empCodeFromEdit" />
		<input type="hidden" name="empCodeToEdit" />
		<input type="hidden" name="pageEdit" />
		<input type="hidden" name="orderFromCboEdit" />
		<input type="hidden" name="orderToCboEdit" />
	</form>
</body>

<script type="text/javascript">
	if (DWRUtil.getValue("page") >= 0){
		//whenShowDataTable(DWRUtil.getValue("page"));
	}
</script>
</html>