<%@ page language="java" contentType="text/html;charset=TIS-620" %>
<%@page import="java.text.DecimalFormat"%>
<%@page import="javax.swing.text.Document"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>

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
	
	String refNoToEdit = request.getParameter("refNoToEdit");
	String refNoFromEdit = request.getParameter("refNoFromEdit");
	String otTypeEdit = request.getParameter("otTypeEdit");
	
	String mustQuery = request.getParameter("mustQuery");
	
	String pageEdit = request.getParameter("pageEdit")==null?"-1":request.getParameter("pageEdit");
%>


<html>
<head>
<title>บันทึกค่าล่วงเวลา/ค่าทำงานในวันหยุดพักผ่อน</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="script/payroll_util.js"></script>
<!-- Javascript Script File -->
<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWgPrPeriodLineService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWgPnEmployeeService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWgRwOvertimeService.js"></SCRIPT>


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
			     		if(mustQuery != null && mustQuery.endsWith("true") ){
														
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
			     			
			     			document.forms['searchForm'].elements['workFrom'].value = '<%= otTypeEdit %>';
							$("refNoFrom").value = '<%= refNoFromEdit %>';
							$("refNoTo").value = '<%= refNoToEdit %>';
							
			     			//alert(cboOrgTo.textInputNode.value);
			     			whenShowDataTable();
			     			
			     	<%
			     		mustQuery = "false";
			     		}
			     	%>
			     	//alert(' !!! ให้บันทึกแยกข้อมูลการเบิกค่าล่วงเวลา/ค่าทำงานในวันหยุดพักผ่อน \n ก่อนวันที่ 8 ม.ค. 2557 และหลังวันที่ 8 ม.ค. 2557 ออกจากกัน !!! ');
			     	
		     	}catch(e){
		     		alert(e.message);
		     	}
		     } 
		     
		     function onLoadYearSectionCallback(){
		    // alert(data.year + ' ' + data.section);
				$("year").value =   "<c:out value='${DefaultYearAndSection.year}' /> "  ;
				$("section").value = "<c:out value='${DefaultYearAndSection.section}' /> "  ;//data.section;
				$("period").value = ""+"<c:out value='${DefaultYearAndSection.period}' /> "  ;//data.period;
				$("confirm").value = "<c:out value='${ConfirmRwOverTime}' />"  ;//data.confirm;
				//alert($("confirm").value);
				//PnEmployeeService.findPrEmpBySecurity('<%=userId%>','<%=ouCode%>',$("year").value,$("period").value, {callback:onLoadEmployeeCallback});
		     	chkMainClose();
		    if(<c:out value='${ConfirmRwOverTime}' />){
					document.forms['searchForm'].elements['add'].disabled = true;
				}
				
				 /*	if(data.confirm){
					document.forms['searchForm'].elements['add'].disabled = true;
				}*/
			//	alert(data.confirm);
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
				FeeWgPrPeriodLineService.findPeriodLine('<%=ouCode%>',$("year").value,$("period").value , {callback:whenchkMainCloseCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		    }
			
			function whenchkMainCloseCallback(data){
				if(data.mainClose == 'Y'){
					alert('ไม่สามารถดำเนินการต่อได้');
					window.history.back()
				}
			}
			
			// Load page
		  	//dojo.addOnLoad(init);
			
			dojo.addOnLoad(onLoadOrganizationCallback);
			dojo.addOnLoad(onLoadYearSectionCallback);
			dojo.addOnLoad(onLoadEmployeeCallback);
			//dojo.addOnLoad(chkMainClose);
			
	//Query		
	
	function countData(){
		var orgFromCbo = '';
		var orgToCbo = '';
		var	empFromCbo = '';
		var	empToCbo = '';
		var	workFromCbo = '';
		var	workToCbo = '';
		var	orderFromCbo = '';
		var	orderToCbo = '';
		if(dojo.widget.byId("orgFromCbo").textInputNode.value != ''){
			orgFromCbo = splitCombo( dojo.widget.byId("orgFromCbo").textInputNode.value );
		}else{
			orgFromCbo = '';
		}
		
		if(dojo.widget.byId("orgToCbo").textInputNode.value != ''){
			orgToCbo = splitCombo( dojo.widget.byId("orgToCbo").textInputNode.value );
		}else{
			orgToCbo = '';
		}
		
		if(dojo.widget.byId("empFromCbo").textInputNode.value != ''){
			empFromCbo = dojo.widget.byId("empFromCbo").textInputNode.value;
		}else{
			empFromCbo = '';
		}
		
		if(dojo.widget.byId("empToCbo").textInputNode.value != ''){
			empToCbo = dojo.widget.byId("empToCbo").textInputNode.value;
		}else{
			empToCbo = '';
		}
		
		
		FeeWgRwOvertimeService.findCountOverTimeByCriteria
		(
			'<%=userId%>',
			orgFromCbo,
			orgToCbo,
			empFromCbo,
			empToCbo,
			document.forms['searchForm'].elements['workFrom'].value,
			document.forms['searchForm'].elements['orderFromCbo'].value,
			document.forms['searchForm'].elements['orderToCbo'].value,
			document.forms['searchForm'].elements['refNoFrom'].value,
			document.forms['searchForm'].elements['refNoTo'].value,
			'<%=ouCode%>',
			parseInt(DWRUtil.getValue("year")),
			parseInt(DWRUtil.getValue("period")),
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
	
		var orgFromCbo = '';
		var orgToCbo = '';
		var	empFromCbo = '';
		var	empToCbo = '';
		var	workFromCbo = '';
		var	workToCbo = '';
		var	orderFromCbo = '';
		var	orderToCbo = '';
	
		if(dojo.widget.byId("orgFromCbo").textInputNode.value != ''){
			orgFromCbo = splitCombo( dojo.widget.byId("orgFromCbo").textInputNode.value );
		}else{
			orgFromCbo = '';
		}
		
		if(dojo.widget.byId("orgToCbo").textInputNode.value != ''){
			orgToCbo = splitCombo( dojo.widget.byId("orgToCbo").textInputNode.value ); 
		}else{
			orgToCbo = '';
		}
		
		if(dojo.widget.byId("empFromCbo").textInputNode.value != ''){
			empFromCbo = dojo.widget.byId("empFromCbo").textInputNode.value;
		}else{
			empFromCbo = '';
		}
		
		if(dojo.widget.byId("empToCbo").textInputNode.value != ''){
			empToCbo = dojo.widget.byId("empToCbo").textInputNode.value;
		}else{
			empToCbo = '';
		}
		//alert(document.forms['searchForm'].elements['workFromCbo'].value + ' ::: ' +document.forms['searchForm'].elements['workToCbo'].value );
		
		FeeWgRwOvertimeService.findOverTimeByCriteria
		(
			'<%=userId%>',
			orgFromCbo,
			orgToCbo,
			empFromCbo,
			empToCbo,
			document.forms['searchForm'].elements['workFrom'].value,
			document.forms['searchForm'].elements['orderFromCbo'].value,
			document.forms['searchForm'].elements['orderToCbo'].value,
			document.forms['searchForm'].elements['refNoFrom'].value,
			document.forms['searchForm'].elements['refNoTo'].value,
			'<%=ouCode%>',
			parseInt(DWRUtil.getValue("year")),
			parseInt(DWRUtil.getValue("period")),
			DWRUtil.getValue("page"),
			DWRUtil.getValue("dataPerPage"),
			{callback:whenDataTableHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}}
		);
		
	}
	
	var cellFuncs = [
		function(data) { return "<div align='center'>"+data.empCode+"</div>";},
		function(data) { return data.name;},
		function(data) { var strDD = '&nbsp;' ;
							if(data.refNo != null && data.refNo != ''){
								strDD = data.refNo;
							}
							return "<div align='center'>"+strDD+"</div>";
						},
		function(data) { 
							var str = '' ;
							if(data.otType != null && data.otType != ''){
								if(data.otType == '1'){
									str = 'ค่าล่วงเวลา';
								}else if(data.otType == '2'){
									str = 'ค่าทำงานในวันหยุดพักผ่อน';
								}
							}
							return "<div>"+str+"</div>";
						},
		function(data) { return writeButton("edit",data.empCode,data.refNo,data.otType);}
	];
	
	function whenDataTableHandler(data)
	{
		if(data.length > 0){
			DWRUtil.removeAllRows("dataTable");
			DWRUtil.addRows("dataTable",data,cellFuncs);
			if(DWRUtil.getValue("showMaxPage") == ''){
				countData();
			}else{
				onCheckButt("searchForm");
			}
		}else{
			DWRUtil.removeAllRows("dataTable");
			alert('ไม่พบข้อมูล');
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
	
	function writeButton(inname,emp,refno,ottype)
	{
		
		return "<div align='center'><input type='button' class='button' name = '"+inname+"' value='...' onclick='preEdit(this.empId,this.refNo,this.otType);' empId='"+emp+"' refNo='"+refno+"' otType='"+ottype+"'/></div>";
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
		$("refNoFromIns").value = $("refNoFrom").value;
	 	$("refNoToIns").value = $("refNoTo").value;
		$("otTypeIns").value = document.forms['searchForm'].elements['workFrom'].value;
		
		
		frm.submit();
	}
		
	function preEdit(empId,refNo,otType){ 
		var frm=document.forms["editForm"];
		DWRUtil.setValue("pageEdit",DWRUtil.getValue("page"));
		DWRUtil.setValue("periodEdit",parseInt(DWRUtil.getValue("period")));
		DWRUtil.setValue("empCodeEdit",empId);
		DWRUtil.setValue("refNoEdit",refNo);
		DWRUtil.setValue("otTypeEdit",otType);
		DWRUtil.setValue("yearEdit",parseInt(DWRUtil.getValue("year")));
		DWRUtil.setValue("ouCodeEdit",'<%=ouCode%>');
		DWRUtil.setValue("userIdEdit",'<%=userId%>');
		DWRUtil.setValue("sectionEdit",DWRUtil.getValue("section"));
		DWRUtil.setValue("confirmEdit",DWRUtil.getValue("confirm"));
		
		
		
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
	 	$("refNoFromEdit").value = $("refNoFrom").value;
	 	$("refNoToEdit").value = $("refNoTo").value;
		$("otTypeForQuery").value = document.forms['searchForm'].elements['workFrom'].value;
		
		
		frm.submit();
	}
	
</script>
<%
	Calendar now = Calendar.getInstance(Locale.US);
	String year = ((now.get(Calendar.YEAR)+543)+"");
	String keySeq  = request.getParameter("keySeq");
	//String pageEdit = request.getParameter("pageEdit")==null?"-1":request.getParameter("pageEdit");
%>
</head>
<body >
<form name="searchForm" action="" method="post">
<input type="hidden" name="period">
<input type="hidden" name="confirm"> 
<input type="hidden" name="otType"> 
<table width="100%">
	<tr>
		<td class="font-head">
			[ CTWGPAYMT003 ] บันทึกค่าล่วงเวลา/ค่าทำงานในวันหยุดพักผ่อน จ้างเหมา
		</td>
	</tr>
</table>
<table  width="770" border="0" align="center" cellspacing="1">

  <tr>
  	 <td colspan="4">
		  <table border="0" width="100%"><tr>
		    <td class="font-field" align="right" width="146px">ประจำปี&nbsp;</td>
		    <td align="left">&nbsp;<input type="text" name="year"   value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/></td>
		    
		   
				    <td  class="font-field" align="right" width="75px">งวด&nbsp;</td>
				    <td><input type="text" name="section"  value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/></td>
		    		<td class="font-field" align="right" colspan="2">ประเภทของงาน&nbsp;</td>
					    <td align="left" colspan="2">
					    	<select name="workFrom" style="width:200">
									<option value="1" >ค่าล่วงเวลา</option>
									<option value="2" >ค่าทำงานในวันหยุดพักผ่อน</option>
									<option value="3" >ทั้งหมด</option>
							</select>
			</td>
    	</tr></table>
  </tr>
  <tr>
    <td class="font-field" align="right">ตั้งแต่สังกัดปฎิบัติงานจริง&nbsp;</td>
    <td align="left" colspan="3"><SELECT  dojoType="ComboBox"  widgetId="orgFromCbo"  style="width:96%" onBlurInput="whenSelectOrgOption();"></SELECT>
	</td>
	</tr>
	<tr>
    <td  class="font-field" align="right">ถึงสังกัดปฎิบัติงานจริง&nbsp;</td>
    <td align="left"  colspan="3"><SELECT  dojoType="ComboBox" widgetId="orgToCbo" style="width:96%"></SELECT></td>
  </tr>
   <tr>
    <td class="font-field" align="right">ตั้งแต่เลขประจำตัว&nbsp;</td>
    <td align="left"><SELECT  dojoType="ComboBox" widgetId="empFromCbo" style="width:200"  onBlurInput="whenSelectEmpOption();"></SELECT></td>
    <td  class="font-field" align="right">ถึงเลขประจำตัว&nbsp;</td>
    <td align="left"><SELECT  dojoType="ComboBox" widgetId="empToCbo" style="width:200"></SELECT></td>
  </tr>
	<tr>
			   <td class="font-field" align="right">ตั้งแต่ประเภทรายการ&nbsp;</td>
			    <td align="left">
			    		<select name="orderFromCbo" style="width:200">
								<option value="">ทั้งหมด</option>
								<option value="N" >ปกติ</option>
								<option value="A" >ปรับปรุงรายการรับ</option>
								<option value="R" >รายการรับเรียกคืน</option>
													<!-- <option value="B" >ตกเบิกปรับปรุงรายการรับ</option>
													<option value="S" >ตกเบิกรายการรับเรียกคืน</option> -->
						</select>
			    </td>
			    <td  class="font-field" align="right">ถึงประเภทรายการ&nbsp;</td>
			    <td align="left">
			    		<select name="orderToCbo" style="width:200">
			    		        <option value="">ทั้งหมด</option>
								<option value="N" >ปกติ</option>
								<option value="A" >ปรับปรุงรายการรับ</option>
								<option value="R" >รายการรับเรียกคืน</option>
								<!-- <option value="B" >ับปรุงรายการรับ</option>
													<option value="S" >ตกเบิกรายการรับเรียกคืน</option> -->
						</select>
				</td>
	</tr>
   <tr>
    <td class="font-field" align="right">ตั้งแต่เลขที่ ล.1&nbsp;</td>
    <td align="left">
    		<input type="text" name="refNoFrom"   value=""  style="width:200;"/>
    </td>
    <td  class="font-field" align="right">ถึงเลขที่ ล.1&nbsp;</td>
    <td align="left">
    		<input type="text" name="refNoTo"   value=""  style="width:200;"/>
	</td>
    <td align="center"><input type="Button" class=" button " value="ค้นหา" onclick="onQuery(whenShowDataTable);"/></td>
  </tr>
 
</table>
<br/>
<div style="height:290px;">
<table width="770"  border="1" bordercolor="#6699CC"  align="center"  cellpadding="2" cellspacing="0">
	<thead>
		<tr CLASS="TABLEBULE2">
		<th CLASS="TABLEBULE2" width="100">เลขประจำตัว</th>
		<th CLASS="TABLEBULE2">ชื่อ - นามสกุล</th>
		<th CLASS="TABLEBULE2" width="150">เลขที่ ล.1</th>
		<th CLASS="TABLEBULE2" width="150">ประเภทของงาน</th>
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
<form name="insertForm" action="security.htm?reqCode=CTWGPAYIN003" method="post">
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
		
		<input type="hidden" name="otTypeIns"/>
		<input type="hidden" name="refNoFromIns"/>
		<input type="hidden" name="refNoToIns"/>
</form>
<form name="editForm" action="security.htm?reqCode=CTWGPAYUP003" method="post">
	<input type="hidden" name="empCodeEdit">
	<input type="hidden" name="periodEdit">
	<input type="hidden" name="pageEdit">
	<input type="hidden" name="yearEdit">
	<input type="hidden" name="ouCodeEdit">
	<input type="hidden" name="userIdEdit">
	<input type="hidden" name="confirmEdit">
	<input type="hidden" name="sectionEdit">
	<input type="hidden" name="refNoEdit">
	<input type="hidden" name="otTypeEdit">
	
	
		<input type="hidden" name="orgFromEdit" />
		<input type="hidden" name="orgToEdit" />
		<input type="hidden" name="empCodeFromEdit" />
		<input type="hidden" name="empCodeToEdit" />
		<input type="hidden" name="pageEdit" />
		<input type="hidden" name="orderFromCboEdit" />
		<input type="hidden" name="orderToCboEdit" />
		
		<input type="hidden" name="otTypeForQuery"/>
		<input type="hidden" name="refNoFromEdit"/>
		<input type="hidden" name="refNoToEdit"/>
</form>
</body>


</html>

<SCRIPT LANGUAGE="JavaScript">
<!--

//-->
</SCRIPT>