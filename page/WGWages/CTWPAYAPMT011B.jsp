<%@ page language="java" contentType="text/html;charset=TIS-620" %>
<%@page import="java.text.DecimalFormat"%>
<%@page import="javax.swing.text.Document"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@ page import="com.ss.tp.security.ProcessResult" %>
<%@ page import="com.ss.tp.dto.DefaultYearSectionVO" %>

<%
	UserInfo userInfo = (UserInfo)session.getAttribute("UserLogin");
	String userId = userInfo.getUserId();
	String ouCode = userInfo.getOuCode();
	
	java.util.Date dd = new java.util.Date();
	java.text.SimpleDateFormat fmd = new java.text.SimpleDateFormat("dd/MM/yyyy",new java.util.Locale("th","TH"));
	String date = fmd.format(dd);
	String pageEdit = request.getParameter("pageEdit")==null?"0":request.getParameter("pageEdit");
	com.ss.tp.dto.DefaultYearSectionVO defaltYear = (com.ss.tp.dto.DefaultYearSectionVO)request.getSession().getAttribute("DefaultYearAndSection");
	String periodSession = defaltYear.getPeriod();
	String approveF ="";
	
	if (request.getSession().getAttribute("processResult") != null){
		ProcessResult processResult = (ProcessResult) request.getSession().getAttribute("processResult");
	}
	java.util.Calendar cc = new java.util.GregorianCalendar();
	int x = cc.get(java.util.Calendar.MONTH) +1;
	//System.out.println("#$#$#$#$$#$#$# :::"+x);
	
	String periodWork = String.valueOf(x);
%>
<html>
<head>
<title>บันทึกเงินหัก</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="script/payroll_util.js"></script>
<!-- Javascript Script File -->
<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrPeriodLineService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPnEmployeeService.js"></SCRIPT>
<script type="text/javascript" src="dwr/interface/FeeWpayRwIncDecOtherService.js"></script>
<SCRIPT type="text/javascript" src="dwr/interface/PrIncomeDeductService.js"></SCRIPT>

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

	// =========================== Start LOV ===========================
    var myUpdate = new Array();
	var count = 0;
	var rowModify ;
	var rowModifyAMT ;
	var lRowNumber;
	var canSave = true;
	
    function onLoadOrganizationCallback()
    {
		try
		{
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
	   	}catch(e)
	   	{
     		alert(e.message);
     	}
	}
     
    
    function onLoadEmployeeCallback()
    {
    	try
    	{
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
	   
     	}catch(e)
     	{
     		alert(e.message);
    	}
	} 
     
    function onLoadYearSectionCallback()
    {
		$("year").value =   "<c:out value='${DefaultYearAndSection.year}' /> "  ;
		$("section").value = "<c:out value='${DefaultYearAndSection.section}' /> "  ;//data.section;
		$("period").value = ""+"<c:out value='${DefaultYearAndSection.period}' /> "  ;//data.period;
		$("confirm").value = "<c:out value='${ConfirmRwIncDecOtherDeduct}' /> "  ;//data.confirm;
		
		chkMainClose();
		//alert($("confirm").value +' ::: '+<c:out value='${DefaultYearAndSection.confirm}' /> );	
		//PnEmployeeService.findPrEmpBySecurity('<%=userId%>','<%=ouCode%>',$("year").value,$("period").value, {callback:onLoadEmployeeCallback});
		
		 if(<c:out value='${ConfirmRwIncDecOtherDeduct}' />){
					document.forms['searchForm'].elements['insertData'].disabled = true;
					document.forms['searchForm'].elements['deleteData'].disabled = true;
					document.forms['searchForm'].elements['confirmData'].disabled = true;
		}
     		
	}
     
    function onLoadIncDecCallback()
    {
     	try
     	{
	     	var cboSource = [];
	     	var cboFrom = dojo.widget.byId("incDecCbo");
	     	
	     /*	for(var i=0; i<data.length; i++)
	     	{
	     		var prInc = data[i];
	     		cboSource.push([prInc.incDecName, prInc.incDecCode]);
	     	} */
	     	
	     	<c:forEach items="${PrIncomeDeduct}" var="result" >		 
						cboSource.push(["<c:out value='${result.incDecName}' />","<c:out value='${result.incDecCode}' />"]);
			</c:forEach>
	     	
			cboFrom.dataProvider.setData(cboSource);	 
	   	}catch(e)
	   	{
     		alert(e.message);
		}
	} 
     
     // Even ComboBox valueChange
    function whenSelectOrgOption()
    {
    	DWRUtil.useLoadingMessage("Loading ...");
		var cbo = dojo.widget.byId("orgFromCbo");
		whenFetchOrganizationTo(splitCombo(cbo.textInputNode.value));
	}
	
	function whenFetchOrganizationTo(orgCode)
	{
     	DWRUtil.useLoadingMessage("Loading ...");
     	var cboTo = dojo.widget.byId("orgToCbo");
		     	
		     	if( orgCode > cboTo.comboBoxSelectionValue.value ){
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
    
    function whenFetchOrganizationToCallback(data)
    {
    	try
    	{
	     	var cboSource = [];
	     	var cboTo = dojo.widget.byId("orgToCbo");
	     	
	     	for(var i=0; i<data.length; i++)
	     	{
	     		var org = data[i];
	     		cboSource.push([org.orgCode, org.orgCode]);
	     	}
	     	
	     	cboTo.dataProvider.setData(cboSource);
     	}catch(e)
     	{
     		alert(e.message);
     	}
    }
	 
	function whenSelectEmpOption()
	{
    	DWRUtil.useLoadingMessage("Loading ...");
		var cbo = dojo.widget.byId("empFromCbo");
		whenFetchEmployeeTo(cbo.textInputNode.value);
	}
	
	function whenFetchEmployeeTo(empCode)
	{
	
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
		     	
     	//PnEmployeeService.findPrToEmpBySecurity('<%=userId%>','<%=ouCode%>',empCode,parseInt($("year").value),parseInt($("period").value) , {callback:whenFetchEmployeeToCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
    }
    
    function whenFetchEmployeeToCallback(data)
    {
    	try
    	{
	     	var cboSource = [];
	     	var cboTo = dojo.widget.byId("empToCbo");
	     	
	     	for(var i=0; i<data.length; i++)
	     	{
	     		var emp = data[i];
	     		cboSource.push([emp.empCode, emp.empCode]);
	     	}
	     	
	     	cboTo.dataProvider.setData(cboSource);
     	}catch(e)
     	{
     		alert(e.message);
     	}
    }

	function init()
	{
		var cboOrgFrom = dojo.widget.byId("orgFromCbo");
		var cboEmpFrom = dojo.widget.byId("empFromCbo");
		//var incDecCbo = dojo.widget.byId("incDecCbo");
		dojo.event.connect(cboOrgFrom, "selectOption", "whenSelectOrgOption");
		dojo.event.connect(cboEmpFrom, "selectOption", "whenSelectEmpOption");
		//dojo.event.connect(incDecCbo, "selectOption", "whenChengIncDec");
	}
	
	function chkMainClose(){
				FeeWpayPrPeriodLineService.findPeriodLine('<%=ouCode%>',$("year").value,$("period").value,'<%=userId%>' , {callback:whenchkMainCloseCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		    }
			
			function whenchkMainCloseCallback(data){
				if(data.mainClose == 'Y'){
					alert('ไม่สามารถดำเนินการต่อได้');
					window.history.back()
				}
			}
	
	// Load page
  	dojo.addOnLoad(init);
	//dojo.addOnLoad(onLoadOrganization);
	dojo.addOnLoad(onLoadOrganizationCallback);
	dojo.addOnLoad(onLoadYearSectionCallback);
	dojo.addOnLoad(onLoadEmployeeCallback);
	
	
	// =========================== End LOV ===========================
	
	
	
	function whenShowDataTable()
	{
		myUpdate = new Array();
		if(document.forms['searchForm'].elements['incDecCbo'].value != null && document.forms['searchForm'].elements['incDecCbo'].value != ''){
			var orgFromCbo = '';
			var orgToCbo = '';
			var	empFromCbo = '';
			var	empToCbo = '';
			var	incDecCbo = '';
			
			if(dojo.widget.byId("orgFromCbo").textInputNode.value != '')
			{
				orgFromCbo = splitCombo( dojo.widget.byId("orgFromCbo").textInputNode.value );
			}else
			{
				orgFromCbo = '';
			}
			
			if(dojo.widget.byId("orgToCbo").textInputNode.value != '')
			{
				orgToCbo = splitCombo( dojo.widget.byId("orgToCbo").textInputNode.value );
			}else
			{
				orgToCbo = '';
			}
			
			if(dojo.widget.byId("empFromCbo").textInputNode.value != '')
			{
				empFromCbo = dojo.widget.byId("empFromCbo").textInputNode.value;
			}else
			{
				empFromCbo = '';
			}
			
			if(dojo.widget.byId("empToCbo").textInputNode.value != '')
			{
				empToCbo = dojo.widget.byId("empToCbo").textInputNode.value;
			}else
			{
				empToCbo = '';
			}
			
			if(document.forms['searchForm'].elements['incDecCbo'].value != '')
			{
				incDecCbo = document.forms['searchForm'].elements['incDecCbo'].value;
			}else
			{
				incDecCbo = '';
			}
			
			// Query by Criteria
			FeeWpayRwIncDecOtherService.findByCriteriaListApprove
			(
				'<%=userId%>',
				'<%=ouCode%>',
				parseInt(DWRUtil.getValue("year")),
				parseInt(DWRUtil.getValue("period")),
				orgFromCbo,
				orgToCbo,
				empFromCbo,
				empToCbo,
				incDecCbo,
				'2',
				DWRUtil.getValue("page"),
				DWRUtil.getValue("dataPerPage"),
				{callback:whenListDataTableHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}}
			);
		}else{
			alert('กรุณาระบุประเภทเงินได้');
		}
	}
	
	var cellFuncs = [
		function(data) { return writeSelect("flagPr",data.flagPr,data.keySeq);},
		function(data) { return writeTextID("empCode",data.empCode,10,"left",data.keySeq);},
		function(data) { return writeTextDisplay("name",data.name,200,"left","codeSeq",data.codeSeq);},
		function(data) { return writeTextDisplay("orgDesc",data.orgDesc,200,"left");},
		function(data) { return writeText("totalAmt",data.totalAmt,9,"right",data.keySeq);},
		function(data) { 
						if(data.startDate != null && data.startDate != ''){
							return writeStart(formatDate(data.startDate,"dd/MM/yyyy"),data.keySeq);
						}else{
							return writeStart("",data.keySeq);
						}
						},
		function(data) { 
						if(data.endDate != null && data.endDate != ''){
							return writeEnd(formatDate(data.endDate,"dd/MM/yyyy"),data.keySeq);
						}else{
							return writeEnd("",data.keySeq);
						}
						},
		function(data) { return writeHidden("seqData",data.seqData,4,3,"right","keySeq",data.keySeq);},
		function(data) { return writeApp("approveF",data.approveFlag,data.keySeq);}
	];
	
	function writeApp(inname,emp,key)
	{
			
		var mm1 = '';
		var mm2 = '';
		
	
		
		if(emp=='Y'){
		mm1 = 'selected';
		}else if(emp=='N'){
		mm2 = 'selected';
		}
		
		return "<div align='center' ><select onchange='addListUpdate("+key+")' name='"+inname+"'>" +
													"<option value='Y' "+mm1+" >รับรอง</option>" +
												    "<option value='N' "+mm2+" >ไม่รับรอง</option>" +																							 							
							"</select></div>";
	
	
	}
	
	
	function writeSelect(inname,emp,key)
	{
		var temp1 = '';
		var temp2 = '';
		
		if(emp=='N'){
			temp1 = 'selected';
		} else if(emp=='A'){
			temp2 = 'selected';		
		}
		//if(<c:out value='${ConfirmRwIncDecOtherDeduct}' />){
		return "<div align='center' style='background-color:#CCCCCC;'><select name='"+inname+"'  disabled='true' background-color:transparent;color:#000000' ;><option value='N' "+temp1+" >ปกติ</option>"+
												"<option value='A' "+temp2+">ปรับปรุงรายการหัก</option></select></div>";
		//}else {
		//return "<div align='center' ><select onchange='addListUpdate("+key+")' name='"+inname+"'><option value='N' "+temp1+" >ปกติ</option>"+
					//							"<option value='A' "+temp2+">ปรับปรุงรายการหัก</option></select></div>";
		
		
		//}
	}
	
	function writeSelectMonth(inname,emp,key){
		var mm1 = '';
		var mm2 = '';
		var mm3 = '';
		var mm4 = '';
		var mm5 = '';
		var mm6 = '';
		var mm7 = '';
		var mm8 = '';
		var mm9 = '';
		var mm10 = '';
		var mm11 = '';
		var mm12 = '';
		
		if(emp=='1'){
			mm1 = 'selected';
		} else if(emp=='2'){
			mm2 = 'selected';		
		}else if(emp=='3'){
			mm3 = 'selected';		
		}else if(emp=='4'){
			mm4 = 'selected';		
		}else if(emp=='5'){
			mm5 = 'selected';		
		}else if(emp=='6'){
			mm6 = 'selected';		
		}else if(emp=='7'){
			mm7 = 'selected';		
		}else if(emp=='8'){
			mm8 = 'selected';		
		}else if(emp=='9'){
			mm9 = 'selected';		
		}else if(emp=='10'){
			mm10 = 'selected';		
		}else if(emp=='11'){
			mm11 = 'selected';		
		}else if(emp=='12'){
			mm12 = 'selected';		
		}
		
		if(<c:out value='${ConfirmRwIncDecOtherDeduct}' />){
		return "<div align='center' style='background-color:#CCCCCC;'><select name='"+inname+"' disabled='true' style='background-color:transparent;color:#000000'>" +
													"<option value='1' "+mm1+" >มกราคม</option>" +
													"<option value='2' "+mm2+">กุมภาพันธ์</option>" +
													"<option value='3' "+mm3+">มีนาคม</option>" +
													"<option value='4' "+mm4+">เมษายน</option>" +
													"<option value='5' "+mm5+">พฤษภาคม</option>" +
													"<option value='6' "+mm6+" >มิถุนายน</option>" +
													"<option value='7' "+mm7+">กรกฏาคม</option>" +
													"<option value='8' "+mm8+">สิงหาคม</option>" +
													"<option value='9' "+mm9+">กันยายน</option>" +
													"<option value='10' "+mm10+" >ตุลาคม</option>" +
													"<option value='11' "+mm11+">พฤศจิกายน</option>" +
													"<option value='12' "+mm12+" >ธันวาคม</option>" +
												"</select></div>";
		}else {
		return "<div align='center' ><select onchange='addListUpdate("+key+")' name='"+inname+"'>" +
												"<option value='1' "+mm1+" >มกราคม</option>" +
													"<option value='2' "+mm2+">กุมภาพันธ์</option>" +
													"<option value='3' "+mm3+">มีนาคม</option>" +
													"<option value='4' "+mm4+">เมษายน</option>" +
													"<option value='5' "+mm5+">พฤษภาคม</option>" +
													"<option value='6' "+mm6+" >มิถุนายน</option>" +
													"<option value='7' "+mm7+">กรกฏาคม</option>" +
													"<option value='8' "+mm8+">สิงหาคม</option>" +
													"<option value='9' "+mm9+">กันยายน</option>" +
													"<option value='10' "+mm10+" >ตุลาคม</option>" +
													"<option value='11' "+mm11+">พฤศจิกายน</option>" +
													"<option value='12' "+mm12+" >ธันวาคม</option>" +
												"</select></div>";
		
		
		}
	
	
	
	}
	
	function writeStart(startDate,key){
		//alert(startDate);
		// split startDate month year
		
		var arr ;
		//alert( arr );
		//alert(arr[0]+' ::: '+arr[1]);
		var emp ;
		var year ;
		if(startDate != null && startDate != ''){
			if(startDate.substr(3,1) == '0'){
				emp = startDate.substr(4,1);
			}else{
				emp = startDate.substr(3,2);
			}
			
			year = startDate.substr(6,4);
		}else{
			emp = '';
			year = '';
		}
		
		var mm0 = '';
		var mm1 = '';
		var mm2 = '';
		var mm3 = '';
		var mm4 = '';
		var mm5 = '';
		var mm6 = '';
		var mm7 = '';
		var mm8 = '';
		var mm9 = '';
		var mm10 = '';
		var mm11 = '';
		var mm12 = '';
		
		if(emp==''){
			mm0 = 'selected';
		} else if(emp=='1'){
			mm1 = 'selected';
		} else if(emp=='2'){
			mm2 = 'selected';		
		}else if(emp=='3'){
			mm3 = 'selected';		
		}else if(emp=='4'){
			mm4 = 'selected';		
		}else if(emp=='5'){
			mm5 = 'selected';		
		}else if(emp=='6'){
			mm6 = 'selected';		
		}else if(emp=='7'){
			mm7 = 'selected';		
		}else if(emp=='8'){
			mm8 = 'selected';		
		}else if(emp=='9'){
			mm9 = 'selected';		
		}else if(emp=='10'){
			mm10 = 'selected';		
		}else if(emp=='11'){
			mm11 = 'selected';		
		}else if(emp=='12'){
			mm12 = 'selected';		
		}
		
		//if(<c:out value='${ConfirmRwIncDecOtherDeduct}' />){
			//alert('x1'); 
			return "<div align='center' style='background-color:#CCCCCC;'>"+
						"<select name='startMonth' disabled='true' background-color:#b2b2b2;color:#000000; onChange='addListUpdate("+key+");validateMonthYear(this);'>" +
							"<option value='' "+mm0+" ></option>" +
							"<option value='1' "+mm1+" >มกราคม</option>" +
							"<option value='2' "+mm2+">กุมภาพันธ์</option>" +
							"<option value='3' "+mm3+">มีนาคม</option>" +
							"<option value='4' "+mm4+">เมษายน</option>" +
							"<option value='5' "+mm5+">พฤษภาคม</option>" +
							"<option value='6' "+mm6+" >มิถุนายน</option>" +
							"<option value='7' "+mm7+">กรกฏาคม</option>" +
							"<option value='8' "+mm8+">สิงหาคม</option>" +
							"<option value='9' "+mm9+">กันยายน</option>" +
							"<option value='10' "+mm10+" >ตุลาคม</option>" +
							"<option value='11' "+mm11+">พฤศจิกายน</option>" +
							"<option value='12' "+mm12+" >ธันวาคม</option>" +
						"</select>"+
						"/<input type='text'  name='startYear'  maxlength='4' value='"+year+"' align='center' onchange='addListUpdate("+key+");validateMonthYear(this);'style='background-color:#CCCCCC;text-align:right;width:40px;' onkeyup='if(this.value < 0) this.value = \"\";if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		// }else {
			//alert('x2');
		//	var str = 	"<div align='center' >"+
			//			"<select name='startMonth' onChange='addListUpdate("+key+");validateMonthYear(this);'>" +
			//				"<option value='' "+mm0+" ></option>" +
			//				"<option value='1' "+mm1+" >มกราคม</option>" +
			//				"<option value='2' "+mm2+">กุมภาพันธ์</option>" +
			//				"<option value='3' "+mm3+">มีนาคม</option>" +
			//				"<option value='4' "+mm4+">เมษายน</option>" +
			//				"<option value='5' "+mm5+">พฤษภาคม</option>" +
			//				"<option value='6' "+mm6+" >มิถุนายน</option>" +
			//				"<option value='7' "+mm7+">กรกฏาคม</option>" +
			//				"<option value='8' "+mm8+">สิงหาคม</option>" +
			///				"<option value='9' "+mm9+">กันยายน</option>" +
			//				"<option value='10' "+mm10+" >ตุลาคม</option>" +
			//				"<option value='11' "+mm11+">พฤศจิกายน</option>" +
		//					"<option value='12' "+mm12+" >ธันวาคม</option>" +
		//				"</select>"+
		//				"/<input type='text'  name='startYear'  maxlength='4' value='"+year+"' align='center' onchange='addListUpdate("+key+");'  style='text-align:right;width:40px;' onkeyup='if(this.value < 0) this.value = \"\";if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);validateMonthYear(this);'/></div>";
			//alert( str ); 
			//return str;
		
		//}
	
	}
	
	function writeEnd(endDate,key){
	
		// split endDate month year
		//alert('endDate : ' + endDate);
		var arr ;
		//alert( arr );
		var emp ;
		var year ;
		
		if(endDate != null && endDate != ''){
			if(endDate.substr(3,1) == '0'){
				emp = endDate.substr(4,1);
			}else{
				emp = endDate.substr(3,2);
			}
			
			year = endDate.substr(6,4);
		}else{
			emp = '';
			year = '';
		}
		
		var mm0 = '';
		var mm1 = '';
		var mm2 = '';
		var mm3 = '';
		var mm4 = '';
		var mm5 = '';
		var mm6 = '';
		var mm7 = '';
		var mm8 = '';
		var mm9 = '';
		var mm10 = '';
		var mm11 = '';
		var mm12 = '';
		
		if(emp==''){
			mm0 = 'selected';
		} else if(emp=='1'){
			mm1 = 'selected';
		} else if(emp=='2'){
			mm2 = 'selected';		
		}else if(emp=='3'){
			mm3 = 'selected';		
		}else if(emp=='4'){
			mm4 = 'selected';		
		}else if(emp=='5'){
			mm5 = 'selected';		
		}else if(emp=='6'){
			mm6 = 'selected';		
		}else if(emp=='7'){
			mm7 = 'selected';		
		}else if(emp=='8'){
			mm8 = 'selected';		
		}else if(emp=='9'){
			mm9 = 'selected';		
		}else if(emp=='10'){
			mm10 = 'selected';		
		}else if(emp=='11'){
			mm11 = 'selected';		
		}else if(emp=='12'){
			mm12 = 'selected';		
		}
		
		//if(<c:out value='${ConfirmRwIncDecOtherDeduct}' />){
		return "<div align='center' style='background-color:#CCCCCC;'>"+
					"<select name='endMonth' disabled='true' background-color:transparent;color:#000000; onChange='addListUpdate("+key+");validateMonthYear(this);'>" +
						"<option value='' "+mm0+" ></option>" +
						"<option value='1' "+mm1+" >มกราคม</option>" +
						"<option value='2' "+mm2+">กุมภาพันธ์</option>" +
						"<option value='3' "+mm3+">มีนาคม</option>" +
						"<option value='4' "+mm4+">เมษายน</option>" +
						"<option value='5' "+mm5+">พฤษภาคม</option>" +
						"<option value='6' "+mm6+" >มิถุนายน</option>" +
						"<option value='7' "+mm7+">กรกฏาคม</option>" +
						"<option value='8' "+mm8+">สิงหาคม</option>" +
						"<option value='9' "+mm9+">กันยายน</option>" +
						"<option value='10' "+mm10+" >ตุลาคม</option>" +
						"<option value='11' "+mm11+">พฤศจิกายน</option>" +
						"<option value='12' "+mm12+" >ธันวาคม</option>" +
					"</select>"+
					"/<input type='text'  name='endYear'  maxlength='4' value='"+year+"' align='center' onchange='addListUpdate("+key+");validateMonthYear(this);' style='background-color:#CCCCCC;text-align:right;width:40px;' onkeyup='if(this.value < 0) this.value = \"\";if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		//}else {
		//return 	"<div align='center' >"+
			//	"<select name='endMonth' onChange='addListUpdate("+key+");validateMonthYear(this);'>" +
			//		"<option value='' "+mm0+" ></option>" +
			//		"<option value='1' "+mm1+" >มกราคม</option>" +
			//		"<option value='2' "+mm2+">กุมภาพันธ์</option>" +
			//		"<option value='3' "+mm3+">มีนาคม</option>" +
			//		"<option value='4' "+mm4+">เมษายน</option>" +
			//		"<option value='5' "+mm5+">พฤษภาคม</option>" +
			//		"<option value='6' "+mm6+" >มิถุนายน</option>" +
			//		"<option value='7' "+mm7+">กรกฏาคม</option>" +
			//		"<option value='8' "+mm8+">สิงหาคม</option>" +
			//		"<option value='9' "+mm9+">กันยายน</option>" +
			//		"<option value='10' "+mm10+" >ตุลาคม</option>" +
			//		"<option value='11' "+mm11+">พฤศจิกายน</option>" +
			////		"<option value='12' "+mm12+" >ธันวาคม</option>" +
			//	"</select>"+
			//	"/<input type='text'  name='endYear'  maxlength='4' value='"+year+"' align='center' onchange='addListUpdate("+key+");'  style='text-align:right;width:40px;' onkeyup='if(this.value < 0) this.value = \"\";if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);validateMonthYear(this);'/>"+
			//	"</div>";
		
		
		//}
	
	}
	
	function convertDateToArray(date){
		if( date != null ){
			//alert(date.getDate());
			//alert(date.getMonth());
			//alert(date.getFullYear());
			var arr = new Array()
			arr[0] = date.getMonth()+1;
			arr[1] = date.getFullYear();
			return arr;
		}else{
			var arr = new Array()
			arr[0] = 1;
			arr[1] = '';
			return arr;
		}
	}
	
	function writeText(inname,emp,maxlength,textalign,key)
	{
		//กรณี display not edit
		//if(<c:out value='${ConfirmRwIncDecOtherDeduct}' />){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		//}else{
		//	return "<div align='center' ><input type='text' name = '"+inname+"' onchange='addListUpdate("+key+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		//}
	}
	
	function writeTextDisplay(inname,emp,maxlength,textalign,nameSeq,codeseq)
	{
		return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' /><input type='hidden' name = '"+nameSeq+"' value='"+codeseq+"'  /></div>";
	}
	
	function writeTextID(inname,emp,maxlength,textalign,key)
	{
		//กรณี display not edit
		//if(<c:out value='${ConfirmRwIncDecOtherDeduct}' />){																	
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onchange='whenSelectEmpOption();'/></div>";
		//}else{
		//	return "<div align='center' ><input type='text' maxlength='6' name = '"+inname+"' onchange='whenSelectEmpOptionInRowUpdate("+key+");addListUpdate("+key+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		//}
	}
	
	function writeTextYearWork(inname,emp,maxlength,textalign,key)
	{
		//กรณี display not edit
		if(<c:out value='${ConfirmRwIncDecOtherDeduct}' />){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' size='6' maxlength='"+maxlength+"' style='text-align:"+textalign+";background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}else{
			return "<div align='center' ><input type='text' name = '"+inname+"' onchange='addListUpdate("+key+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' size='6' style='text-align:"+textalign+";' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}
	}
	
	function writeHidden(inname,emp,size,maxlength,textalign,nameHide,empHide)
	{
		//if(<c:out value='${ConfirmRwIncDecOtherDeduct}' />){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		//}else{
		//	return "<div align='center'><input type='text' name = '"+inname+"' onchange='addListUpdate("+empHide+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		//}
	}
	
	function whenListDataTableHandler(data)
	{
		
		
		$("dataLength").value = data.length;
		countData();
		
		if(data.length > 0){
			DWRUtil.removeAllRows("dataTable");
			DWRUtil.addRows("dataTable",data,cellFuncs);
			//countData();
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
	
	var rwIncDecOther = {keySeq:null,ouCode:null,yearPr:null,periodPr:null,empCode:null,groupCode:null,incDecCode:null,
						 yearWork:null,periodWork:null,codeSeq:null,totalAmt:null,flagPr:null,
						 seqData:null,confirmFlag:null,approveFlag:null,creBy:null,updBy:null,creDate:null,updDate:null,approveBy:null,approveDate:null};
	var allRowUpdate = 0;
	
	function onUpdate(){
	
		var table = document.getElementById("table");
		var aRows = table.rows;
		var num = 1 + parseInt(DWRUtil.getValue("dataLength"));
		
		var empNull = true;
		canSave = true ;
		var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["searchForm"];
		
		if(parseInt(DWRUtil.getValue("dataLength")) != null && parseInt(DWRUtil.getValue("dataLength")) > 0){
			num = 1 + parseInt(DWRUtil.getValue("dataLength"));
		}else{
			num = 1 ;
		}
		
		for(var a=aRows.length-1;a > num;a--){
				if (aRows[a].cells["empCode"].childNodes[0].value == null || aRows[a].cells["empCode"].childNodes[0].value == '' || aRows[a].cells["name"].childNodes[0].value == null || aRows[a].cells["name"].childNodes[0].value == ''){
					empNull = false;
					break;
				}
				
				
		}
		
		if(tab.rows.length > 0){
			var keySeq 		= frm.elements["keySeq"];
			var codeSeq 	= frm.elements["codeSeq"];
			var empCode 	= frm.elements["empCode"];
			var name		= frm.elements["name"];
			var flagPr 		= frm.elements["flagPr"];
			var yearWork 	= frm.elements["yearWork"];
			var periodWork 	= frm.elements["periodWork"];
			//var yearWork 	=  $("year").value;
			//var periodWork  =  $("period).value;
			var seqData 	= frm.elements["seqData"];
			var totalAmt	= frm.elements["totalAmt"];
			var startMonth 	= frm.elements["startMonth"];
			var startYear	= frm.elements["startYear"];
			var endMonth	= frm.elements["endMonth"];
			var endYear		= frm.elements["endYear"];
			var approveF    = frm.elements["approveF"];
			
			var sst = "";
			var sed = "";
			
			for(var c=0; c<tab.rows.length; c++){
				if (empCode[c].value == null || empCode[c].value == ''){
					empNull = false;
				}
				
				
			}
			
			if(canSave){
				if(empNull){
					DWREngine.beginBatch();
					//alert('UPDATE '+tab.rows.length);
					for(var i=0; i<tab.rows.length; i++){
							update = false;
						row = tab.rows[i];
						rwIncDecOther.keySeq = parseInt(keySeq[i].value);
						
						rwIncDecOther.groupCode = 2;
						rwIncDecOther.incDecCode = document.forms['searchForm'].elements['incDecCbo'].value;
						rwIncDecOther.codeSeq = codeSeq[i].value;
						
						if(tab.rows.length == 1){
							rwIncDecOther.flagPr = flagPr.value;
						}else{
							rwIncDecOther.flagPr = flagPr[i].value;
						}
						
					rwIncDecOther.periodWork  =  $("period").value/2;
						
					rwIncDecOther.yearWork  = $("year").value;
						
				//		rwIncDecOther.periodWork  = periodWork[i].value/2;
				//		
				//		rwIncDecOther.yearWork  = yearWork[i].value;
						
						if (empCode[i].value != ''){
							rwIncDecOther.empCode  = empCode[i].value;
						}
						else{
							rwIncDecOther.empCode  = null;
						}
						
						if (name[i].value != ''){
							rwIncDecOther.name  = name[i].value;
						}
						else{
							rwIncDecOther.name  = null;
						}
						
						if (totalAmt[i].value != ''){
							rwIncDecOther.totalAmt  = totalAmt[i].value;
						}
						else{
							rwIncDecOther.totalAmt  = null;
						}
						
						if (seqData[i].value !=''){
							rwIncDecOther.seqData  = parseInt(seqData[i].value);
						}
						else{
							rwIncDecOther.seqData  = null;
						}
						
						if(startMonth[i].value != '' && startYear[i].value != '' && endMonth[i].value != '' && endYear[i].value != '' ){
							var arrStart = new Array();
							arrStart[0] = startMonth[i].value;
							arrStart[1] = startYear[i].value;
							
							
							if(arrStart[0].length == 1){
								sst = "01/0"+startMonth[i].value+"/"+startYear[i].value;
							}else{
								sst = "01/"+startMonth[i].value+"/"+startYear[i].value;
							}
							rwIncDecOther.startDate = getDateFromFormat(sst,"dd/MM/yyyy");
							
							
							//alert('x month : ' + convertArrayToDate(arrStart) );
							//rwIncDecOther.startDate = convertArrayToDate(arrStart);
							//alert('x year : ' + rwIncDecOther.startDate );
							
							var arrEnd = new Array();
							arrEnd[0] = endMonth[i].value;
							arrEnd[1] = endYear[i].value;
							
							
							if(arrEnd[0].length == 1){
								sed = "01/0"+endMonth[i].value+"/"+endYear[i].value;
							}else{
								sed = "01/"+endMonth[i].value+"/"+endYear[i].value;
							}
							rwIncDecOther.endDate = getDateFromFormat(sed,"dd/MM/yyyy");
							
							
							//alert('x month : ' + convertArrayToDate(arrEnd) );
							//rwIncDecOther.endDate = convertArrayToDate(arrEnd);
							//alert('x year : ' + rwIncDecOther.endDate );
						}else{
							rwIncDecOther.startDate = null;
							rwIncDecOther.endDate = null;
							rwIncDecOther.yearWork = $("year").value;
							rwIncDecOther.periodWork  = $("period").value/2;
						}
						
						if (approveF[i].value != '' && approveF[i].value != null){
							rwIncDecOther.approveFlag  = approveF[i].value;
						}
						else{
							rwIncDecOther.approveFlag  = null;
						}
						
						rwIncDecOther.approveBy = '<%=userId%>';
						rwIncDecOther.approveDate = getDateFromFormat(sed,"dd/MM/yyyy");
						
						for(var x = 0 ; x < myUpdate.length ;x++){
							if(myUpdate[x] == parseInt(keySeq[i].value)){
								update = true;
								break;
							}
						}
						if(update){
							allRowUpdate++;
							if(aRows.length  > num + 1 ){
								if( allRowUpdate == myUpdate.length )
									FeeWpayRwIncDecOtherService.addListApprove(rwIncDecOther, false, {callback:onInsertCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
								else
									FeeWpayRwIncDecOtherService.addListApprove(rwIncDecOther, false);
								
							}else{
								if( allRowUpdate == myUpdate.length )
										FeeWpayRwIncDecOtherService.addListApprove(rwIncDecOther, true, {callback:ClearData,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้');}});
									else
										FeeWpayRwIncDecOtherService.addListApprove(rwIncDecOther, false);
								
							}
						}
						
					}
					
					DWREngine.endBatch();
				 }
				}
			}
		
		
		if(empNull){
			if(canSave){
				if(myUpdate.length == 0){
						insertNewData();
				}
			}else{
					alert('กรุณากรอกจำนวนเงินให้ครบถ้วน');
				
				}
		}else{
					alert('เลขประจำตัวไม่ถูกต้อง');
				}
	}
	
	function ClearData(){
		alert("บันทึกข้อมูลเรียบร้อย");
			myUpdate = new Array();
			var table = document.getElementById("table");
			var tdName;
			var chkName;
			var num;
			if(parseInt(DWRUtil.getValue("dataLength")) != null && parseInt(DWRUtil.getValue("dataLength")) > 0){
				num = 1 + parseInt(DWRUtil.getValue("dataLength"));
			}else{
				num = 1;
			}
			
			var oRows = table.rows;
			if(tdName == null)tdName="flag";
			if(chkName == null)chkName="chk";
			for(i=oRows.length-1;i > num;i--){
					table.deleteRow(i);		
			}
		DWRUtil.removeAllRows("dataTable");
		whenShowDataTable();
	}
	
	function onInsertCallback(){
		insertNewData();
		allRowUpdate = 0;
	}
	
	function insertNewData()
	{
		var table = document.getElementById("table");
		var tdName;
		var chkName;
		var num;
		
		var sst = "";
		var sed = "";
		
		
		var empNull = true;
		
		if(parseInt(DWRUtil.getValue("dataLength")) != null && parseInt(DWRUtil.getValue("dataLength")) > 0){
			num = 1 + parseInt(DWRUtil.getValue("dataLength"));
		}else{
			num = 1 ;
		}
		var oRows = table.rows;
		if(tdName == null)tdName="flag";
		if(chkName == null)chkName="chk";

		for(var c=oRows.length-1;c > num;c--){
				if (oRows[c].cells["empCode"].childNodes[0].value == null || oRows[c].cells["empCode"].childNodes[0].value == '' || oRows[c].cells["name"].childNodes[0].value == null || oRows[c].cells["name"].childNodes[0].value == ''){
					empNull = false;
				}
		}
		
		if(empNull){
			DWREngine.beginBatch();
		
			for(var i=oRows.length-1;i > num;i--)
			{
					rwIncDecOther.keySeq = null;
					rwIncDecOther.ouCode = '<%=ouCode%>';
					//rwIncDecOther.groupCode = 2;
					rwIncDecOther.incDecCode = document.forms['searchForm'].elements['incDecCbo'].value;
					rwIncDecOther.yearPr = parseInt($("year").value);
					rwIncDecOther.periodPr = parseInt($("period").value);
					rwIncDecOther.empCode = oRows[i].cells["empCode"].childNodes[0].value;
					rwIncDecOther.name = oRows[i].cells["name"].childNodes[0].value;
					rwIncDecOther.codeSeq = oRows[i].cells["name"].childNodes[1].value;
					rwIncDecOther.flagPr = oRows[i].cells["flagPr"].childNodes[0].value;
					rwIncDecOther.yearWork = $("year").value;
					rwIncDecOther.periodWork =  Math.round(parseInt($("period").value)/2);
					rwIncDecOther.totalAmt = oRows[i].cells["totalAmt"].childNodes[0].value;
					rwIncDecOther.seqData = oRows[i].cells["seqData"].childNodes[0].value;
					rwIncDecOther.approveFlag = oRows[i].cells["approveF"].childNodes[0].value;
					rwIncDecOther.approveBy = '<%=userId%>';
					rwIncDecOther.approveDate = getDateFromFormat(<%=date%>,"dd/MM/yyyy");
					
					
					
						if( i == (num + 1) )
							FeeWpayRwIncDecOtherService.addListApprove(rwIncDecOther, true, {callback:ClearData,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้');}});
						else
							FeeWpayRwIncDecOtherService.addListApprove(rwIncDecOther, false);
				
			}
			
			DWREngine.endBatch();
			if (!insertStatus){alert("บันทึกข้อมูลเรียบร้อย");}
		}else{
			alert('เลขประจำตัวไม่ถูกต้อง');
		}
	}
	
	function onDeleteCallback()
	{
		alert("Delete Complete");
		whenShowDataTable();
	}
	

	 
	 function addListUpdate(data){
		var add = true;
		for(var i = 0 ; i < myUpdate.length ;i++){
			if(myUpdate[i] == data){
				add = false;
				break;
			}
		}
		if(add){
			myUpdate[count] = data;
			count++;
		}
	}
	
	function whenChengIncDec(){
		
		var table = document.getElementById("table");
		var tdName;
		var chkName;
		var num;
		if(parseInt(DWRUtil.getValue("dataLength")) != null && parseInt(DWRUtil.getValue("dataLength")) > 0){
			num = 1 + parseInt(DWRUtil.getValue("dataLength"));
		}else{
			num = 1;
		}
		
		var oRows = table.rows;
		if(tdName == null)tdName="flag";
		if(chkName == null)chkName="chk";
		//alert(oRows.length+" : "+num);
		for(i=oRows.length-1;i > num;i--){
				table.deleteRow(i);		
		}
		
		// clear global variable
		$("dataLength").value = 0;
		
		DWRUtil.removeAllRows("dataTable");
	}
	
	function checkValue(object){
		//if(object.value == null || object.value < 0){
		//	alert('จำนวนเงินต้องมากกว่า 0');
		//	object.focus();
		//}
		var strIncDec = document.forms['searchForm'].elements['incDecCbo'].value;
		 while (object.tagName !=  'TR')
		 {
		 	object = object.parentNode;
		 }
	
		 lRowNumber = object.rowIndex;
		 
		 var table = document.getElementById("table");
		var tdName;
		var chkName;
		
		var oRows = table.rows;
		//alert(oRows[i].cells["empCode"].childNodes[0].value);
		var amt = oRows[lRowNumber].cells["totalAmt"].childNodes[0].value;
		
		if(amt != null && amt != '' && amt >= 0){
			PrIncomeDeductService.findMaxIncDecCode('<%=ouCode%>','2',strIncDec, {callback:whenCheckAmtTotalCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		}else{
			alert('กรุณากรอกจำนวนเงิน');
			oRows[lRowNumber].cells["totalAmt"].childNodes[0].focus();
		}
		
		 
	}
	
	function convertArrayToDate(arr){
		var tmpDate = '';
		var tmp = arr[0];
		if( tmp.length < 2 ){
			tmpDate = tmpDate + '0' + arr[0] + '/';
		}else{
			tmpDate = tmpDate + arr[0] + '/';
		}
		
		tmpDate = tmpDate + '01/';
		
		tmp = arr[1];
		if( tmp.lenght < 2 ){
			tmpDate = tmpDate + '0' + arr[1];
		}else{
			tmpDate = tmpDate + arr[1];
		}
		//alert( tmpDate );
		// format MM/dd/yyyy
		return new Date( tmpDate );
	}
	
	function whenCheckAmtTotalCallback(data){
		
		if(data != null && data != '' && data != 0){
			var table = document.getElementById("table");
			var tdName;
			var chkName;
			
			var oRows = table.rows;
			//alert(oRows[i].cells["empCode"].childNodes[0].value);
			var amt = oRows[lRowNumber].cells["totalAmt"].childNodes[0].value;
			if(amt > data){
				alert('จำนวนเงินมากกว่าที่กำหนด');
				oRows[lRowNumber].cells["totalAmt"].childNodes[0].value = '';
				oRows[lRowNumber].cells["totalAmt"].childNodes[0].focus();
			}
		}
	}
	
	function checkValueInRowUpdate(data){
		//if(object.value == null || object.value < 0){
		//	alert('จำนวนเงินต้องมากกว่า 0');
		//	object.focus();
		//}
		
		
		var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["searchForm"];
		
		
		if(tab.rows.length > 0){
			var keySeq 		= frm.elements["keySeq"];
		
			var totalAmt	= frm.elements["totalAmt"];
			
			for(var c=0; c<tab.rows.length; c++){
				if(keySeq[c].value == data ){
					rowModifyAMT = c;
					break;
				}
			}
		
		var totalAmt = totalAmt[rowModifyAMT].value;
		
		var strIncDec = document.forms['searchForm'].elements['incDecCbo'].value;

		
		if(totalAmt != null && totalAmt != '' && totalAmt >= 0){
			
				PrIncomeDeductService.findMaxIncDecCode('<%=ouCode%>','2',strIncDec, {callback:whenCheckAmtTotalInRowUpdateCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		}else{
			alert('กรุณากรอกจำนวนเงิน');
			totalAmt[rowModifyAMT].focus();
		}
		
		 }
	}
	
	function whenCheckAmtTotalInRowUpdateCallback(data){
		if(data != null && data != '' && data != 0){
			var tab = $('dataTable');
			var row;
			var update ;
			var empList=[];
			var frm   = document.forms["searchForm"];
			
			if(tab.rows.length > 0){
				var keySeq 		= frm.elements["keySeq"];
			
				var totalAmt	= frm.elements["totalAmt"];
				
				var totalAmtCheck = totalAmt[rowModifyAMT].value;
				
				if(totalAmtCheck > data){
					alert('จำนวนเงินมากกว่าที่กำหนด');
					totalAmt[rowModifyAMT].value = '';
					totalAmt[rowModifyAMT].focus();
				}
				
			}
		}
		
	}
	
	function whenSelectEmpOptionInRow(object)
     {
     
     
     	 while (object.tagName !=  'TR')
		 {
		 object = object.parentNode;
		 }
	
		 lRowNumber = object.rowIndex;
		 
		 
		DWRUtil.useLoadingMessage("Loading ...");
		var table = document.getElementById("table");
		var tdName;
		var chkName;
		
		var oRows = table.rows;
		var i = oRows.length-1;
		//alert(oRows[i].cells["empCode"].childNodes[0].value);
		var empCode = oRows[lRowNumber].cells["empCode"].childNodes[0].value;
		//alert(empCode);
		//var cbo = dojo.widget.byId("empCbo");
		
		if(empCode != null && empCode != ''){
			whenFetchEmployeeDetailInRow(empCode);
		}else{
			oRows[lRowNumber].cells["name"].childNodes[0].value = '';
			oRows[lRowNumber].cells["name"].childNodes[1].value = '';
		}
	 } 
	 
	 function whenFetchEmployeeDetailInRow(empCode)
	 {
		DWRUtil.useLoadingMessage("Loading ...");
		//alert(empCode+' : '+<%=ouCode%>+' : ' + DWRUtil.getValue("year")+' : '+ DWRUtil.getValue("period"))
		FeeWpayPnEmployeeService.findByEmpCodeDetail('<%=userId%>',empCode, '<%=ouCode%>', parseInt(DWRUtil.getValue("year")), parseInt(DWRUtil.getValue("period")), {callback:whenFetchEmployeeDetailInRowCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
	 }
	 
	 function whenFetchEmployeeDetailInRowCallback(data)
	 {
		// alert(data.empCode);
		var table = document.getElementById("table");
		var tdName;
		var chkName;
		
		var oRows = table.rows;
		var i = oRows.length-1;
	 	if(data.empCode != null && data.empCode != ''){
					oRows[lRowNumber].cells["name"].childNodes[0].value = data.name
					oRows[lRowNumber].cells["name"].childNodes[1].value = data.codeSeqWork
		}else{
			oRows[lRowNumber].cells["name"].childNodes[0].value = '';
			oRows[lRowNumber].cells["name"].childNodes[1].value = '';
			alert('เลขประจำตัวไม่ถูกต้อง');
			oRows[lRowNumber].cells["empCode"].childNodes[0].focus();
				
		}
	 }
	 
	  function whenSelectEmpOptionInRowUpdate(data){
	 	
	 	var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["searchForm"];
		
		
		if(tab.rows.length > 0){
			var keySeq 		= frm.elements["keySeq"];
			var empCode 	= frm.elements["empCode"];
			var name		= frm.elements["name"];
			var codeSeq 	= frm.elements["codeSeq"];
			var flagPr 		= frm.elements["flagPr"];
			var yearWork 	= frm.elements["yearWork"];
			var periodWork 	= frm.elements["periodWork"];
			var seqData 	= frm.elements["seqData"];
			var totalAmt	= frm.elements["totalAmt"];
			
			for(var c=0; c<tab.rows.length; c++){
				if(keySeq[c].value == data ){
					rowModify = c;
					break;
				}
			}
			var empCode = empCode[rowModify].value;
			//alert(empCode);
			if(empCode != null && empCode != ''){
				whenFetchEmployeeDetailInRowUpdate(empCode)
			}else{
				name[rowModify].value = '';
				codeSeq[rowModify].value = '';
			}
			
		}
	 	
	 }
	 
	 function whenFetchEmployeeDetailInRowUpdate(empCode)
	 {
		DWRUtil.useLoadingMessage("Loading ...");
		//alert(empCode+' : '+<%=ouCode%>+' : ' + DWRUtil.getValue("year")+' : '+ DWRUtil.getValue("period"))
		FeeWpayPnEmployeeService.findByEmpCodeDetail('<%=userId%>',empCode, '<%=ouCode%>', parseInt(DWRUtil.getValue("year")), parseInt(DWRUtil.getValue("period")), {callback:whenFetchEmployeeDetailInRowUpdateCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
	 }
	 
	 function whenFetchEmployeeDetailInRowUpdateCallback(data)
	 {
		// alert(data.empCode);
		
		var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["searchForm"];
		var oRows = table.rows;
		
		if(tab.rows.length > 0){
			var keySeq 		= frm.elements["keySeq"];
			var empCode 	= frm.elements["empCode"];
			var name		= frm.elements["name"];
			var codeSeq 	= frm.elements["codeSeq"];
			var flagPr 		= frm.elements["flagPr"];
			var yearWork 	= frm.elements["yearWork"];
			var periodWork 	= frm.elements["periodWork"];
			var seqData 	= frm.elements["seqData"];
			var totalAmt	= frm.elements["totalAmt"];
			
			if(data.empCode != null && data.empCode != ''){
				name[rowModify].value = data.name;
				codeSeq[rowModify].value = data.codeSeqWork;
			}else{
				name[rowModify].value = '';
				codeSeq[rowModify].value = '';
				alert('เลขประจำตัวไม่ถูกต้อง');
				empCode[rowModify].focus();	
			}
			
		}
	
	 }
	 
	 function validateMonthYear(object){
	
		var table = document.getElementById("table");
		var oRows = table.rows;
		
		for(var i=0; i<document.getElementsByName("startMonth").length; i++){
			var startMonth = document.getElementsByName("startMonth")[i];
			var startYear = document.getElementsByName("startYear")[i];
			var endMonth = document.getElementsByName("endMonth")[i];
			var endYear = document.getElementsByName("endYear")[i];
			var startDate = '01';
			var endDate = '01';
			
			//alert( startYear.value + ' ' + endYear.value );
			if(startMonth.value != '' && startYear.value != '' &&  endMonth.value != '' && endYear.value != '' ){
				if(startYear.value.length == 4 && endYear.value.length == 4){
					if( startMonth.value.length < 2 ){
						startDate += '/' + '0' + startMonth.value;
					}else{
						startDate += '/' + startMonth.value;
					}
					
					startDate += '/' + startYear.value;
					
					if( endMonth.value.length < 2 ){
						endDate += '/' + '0' + endMonth.value;
					}else{
						endDate += '/' + endMonth.value;
					}
					
					endDate += '/' + endYear.value;
					
					//alert( startDate + ' ' + endDate );
					//alert( compareDates(startDate,"dd/MM/yyyy",endDate,"dd/MM/yyyy") );
					if( compareDates(startDate,"dd/MM/yyyy",endDate,"dd/MM/yyyy") == 1 ){
						alert('เดือน/ปี เริ่มต้น ควรน้อยกว่า เดือน/ปี สิ้นสุด');
						disabledButton();
						return;
					}
				}else{
					disabledButton();
					return;
				
				}
				
			}else if(startMonth.value == '' && startYear.value == '' &&  endMonth.value == '' && endYear.value == '' ){
				continue;
			}else{
				disabledButton();
				return;
			}
		}
		
		enabledButton();
	}
	
	function disabledButton(){
		document.getElementById("confirmData").disabled = true;
		document.getElementById("insertData").disabled = true;
		document.getElementById("deleteData").disabled = true;
	}
	
	function enabledButton(){
		document.getElementById("confirmData").disabled = false;
		document.getElementById("insertData").disabled = false;
		document.getElementById("deleteData").disabled = false;
	}
	function countData()
	{
		myUpdate = new Array();
		//if(document.forms['searchForm'].elements['incDecCbo'].value != null && document.forms['searchForm'].elements['incDecCbo'].value != ''){
			var orgFromCbo = '';
			var orgToCbo = '';
			var	empFromCbo = '';
			var	empToCbo = '';
			var	incDecCbo = '';
			
			if(dojo.widget.byId("orgFromCbo").textInputNode.value != '')
			{
				orgFromCbo = splitCombo( dojo.widget.byId("orgFromCbo").textInputNode.value );
			}else
			{
				orgFromCbo = '';
			}
			
			if(dojo.widget.byId("orgToCbo").textInputNode.value != '')
			{
				orgToCbo = splitCombo( dojo.widget.byId("orgToCbo").textInputNode.value );
			}else
			{
				orgToCbo = '';
			}
			
			if(dojo.widget.byId("empFromCbo").textInputNode.value != '')
			{
				empFromCbo = dojo.widget.byId("empFromCbo").textInputNode.value;
			}else
			{
				empFromCbo = '';
			}
			
			if(dojo.widget.byId("empToCbo").textInputNode.value != '')
			{
				empToCbo = dojo.widget.byId("empToCbo").textInputNode.value;
			}else
			{
				empToCbo = '';
			}
			
			if(document.forms['searchForm'].elements['incDecCbo'].value != '')
			{
				incDecCbo = document.forms['searchForm'].elements['incDecCbo'].value;
			}else
			{
				incDecCbo = '';
			}
			
			// Query by Criteria
			FeeWpayRwIncDecOtherService.countDataApprove
			(
				'<%=userId%>',
				'<%=ouCode%>',
				parseInt(DWRUtil.getValue("year")),
				parseInt(DWRUtil.getValue("period")),
				orgFromCbo,
				orgToCbo,
				empFromCbo,
				empToCbo,
				incDecCbo,
				'2',
				{callback:countDataHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}}
			);
		}
		
		function countDataHandler(data)
		{
			DWRUtil.setValue("countData",data);
			onCheckButt("searchForm");
		    
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
<input type="hidden" name="dataLength"> 
<table width="100%">
	<tr>
		<td class="font-head">
			[ CTWPAYAPMT011 ] บันทึกรับรองเงินหัก
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
	    <td align="left" colspan="3"><SELECT  dojoType="ComboBox"  widgetId="orgFromCbo" style="width:94%" onBlurInput="whenSelectOrgOption();"></SELECT></td>
	  </tr>
	  <tr>
	    <td  class="font-field" align="right">ถึงสังกัดปฎิบัติงานจริง&nbsp;</td>
	    <td align="left" colspan="3"><SELECT  dojoType="ComboBox" widgetId="orgToCbo" style="width:94%"></SELECT></td>
  	</tr>
   	<tr>
	    <td class="font-field" align="right">ตั้งแต่เลขประจำตัว&nbsp;</td>
	    <td align="left"><SELECT  dojoType="ComboBox" widgetId="empFromCbo" style="width:200" onBlurInput="whenSelectEmpOption();"></SELECT></td>
	    <td  class="font-field" align="right">ถึงเลขประจำตัว&nbsp;</td>
	    <td align="left"><SELECT  dojoType="ComboBox" widgetId="empToCbo" style="width:200"></SELECT></td>
  	</tr>
 	<tr>
		<td class="font-field" align="right">ประเภทเงินหัก&nbsp;</td>
		<td align="left">
			<select name="incDecCbo" onchange="whenChengIncDec();"  style="width:200">
				<c:forEach items="${PrIncomeDeduct}" var="result">
					<option value="<c:out value='${result.incDecCode}' />"><c:out value='${result.incDecCode}' />&nbsp;<c:out value='${result.incDecName}' /></option>
				</c:forEach>
			</select>
		</td>
		<td><input type="Button" value="ค้นหา" class=" button " onclick="whenShowDataTable();" /></td>
		<td></td>
		<td></td>
	</tr>

</table>
<br/>

<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td>
		<div style="height:320px;width:900;overflow:auto;" align="center" >
		<table id="table" width="865"  border="1" bordercolor="#6699CC"   cellpadding="0" cellspacing="0">
			<thead style="text-align: center">
				<tr CLASS="TABLEBULE2" style="height: 30px;">
					<th CLASS="TABLEBULE2">ประเภทรายการ</th>
					<th CLASS="TABLEBULE2" style="width:100px">เลขประจำตัว</th>
					<th CLASS="TABLEBULE2" style="width:200px">ชื่อ - นามสกุล</th>
					<th CLASS="TABLEBULE2" style="width:350px">สังกัด</th>
					<th CLASS="TABLEBULE2" style="width:80px" align="center"><center>จำนวนเงิน</center></th>
					<th CLASS="TABLEBULE2" align="center"><center>เดือน/ปีเริ่มต้น</center></th>
					<th CLASS="TABLEBULE2" align="center"><center>เดือน/ปีสิ้นสุด</center></th>
					<th CLASS="TABLEBULE2" style="width:50px" align="center">ลำดับที่</th>
					<th CLASS="TABLEBULE2" style="width:100px" >รับรอง</th>
				</tr>
			</thead>
			<tbody id="dataTable">
			</tbody>
			<tr id="temprow" style="visibility:hidden;position:absolute">
				<td id="flagPr" width="50" readonly="readonly" align="center">
					<select  name="type" >
						<option value="N"  >ปกติ</option>
						<option value="A" >ปรับปรุงรายการหัก</option>
				</select>
			</td>
			<td id="empCode" align="center"><input type="text" name="empCode" readonly="readonly" maxlength="6" style="width:100%" onchange="whenSelectEmpOptionInRow(this);"  onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"></td>
			<td id="name" align="center"><input type="text"  name="name" readonly="readonly" style="width:100%;background-color:silver;"/><input type="hidden"  name="codeSeq" /></td>
			<td id="orgDesc" align="center"><input type="text"  name="orgDesc" readonly="readonly" style="width:100%;background-color:silver;"/></td>
			<td id="totalAmt" align="center"><input type="text"  name="totalAmt" readonly="readonly" onchange="checkValue(this);"  maxlength="9" align="center" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
			<td  align="center" id="start">
				<div align="center">
					<select name="startMonth" onchange="validateMonthYear(this);">
					<option value="" disabled="disabled" selected="selected"  ></option>
					<option value="1" >มกราคม</option>
					<option value="2">กุมภาพันธ์</option>
					<option value="3" >มีนาคม</option>
					<option value="4" >เมษายน</option>
					<option value="5" >พฤษภาคม</option>
					<option value="6" >มิถุนายน</option>
					<option value="7" >กรกฏาคม</option>
					<option value="8" >สิงหาคม</option>
					<option value="9" >กันยายน</option>
					<option value="10" >ตุลาคม</option>
					<option value="11" >พฤศจิกายน</option>
					<option value="12" >ธันวาคม</option>
					</select>
					/
					<input type="text"  name="startYear"  readonly="readonly" maxlength="4"   align="center" style="text-align:right;width:40px;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);validateMonthYear(this);"/>
				</div>
				</td>
			<td  align="center" id="end">
				<div align="center">
					<select name="endMonth" onchange="validateMonthYear(this);">
					<option value="" disabled="disabled" selected="selected"  ></option>
					<option value="1" >มกราคม</option>
					<option value="2">กุมภาพันธ์</option>
					<option value="3" >มีนาคม</option>
					<option value="4" >เมษายน</option>
					<option value="5" >พฤษภาคม</option>
					<option value="6" >มิถุนายน</option>
					<option value="7" >กรกฏาคม</option>
					<option value="8" >สิงหาคม</option>
					<option value="9" >กันยายน</option>
					<option value="10" >ตุลาคม</option>
					<option value="11" >พฤศจิกายน</option>
					<option value="12" >ธันวาคม</option>
					</select>				
					/
					<input type="text"  name="endYear" readonly="readonly"  maxlength="4"  align="center" style="text-align:right;width:40px;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);validateMonthYear(this);"/>
				</div>
			</td>
			<td id="seqData" align="center"><input type="text"  name="seqData" readonly="readonly"  maxlength="3" align="center" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/><input type="hidden"  name="keySeq" /></td>
		     <td id="approveF" align="center" width="40">
						<select name="approveF" style="width:100%;"" >
						                            <option value="Y" <% if(approveF.equals("Y")){%> selected="selected"  <%} %> >รับรอง</option>
													<option value="N" <% if(approveF.equals("N")){%> selected="selected"  <%} %>  >ไม่รับรอง</option>													
						</select></td>	
		</tr>
	</table>
	</div>
	</td>
  </tr>
</table>
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
	</td>
  </tr>
</table>


<table width="100%" CLASS="TABLEBULE2" >
	<tr CLASS="TABLEBULE2" >
		<td align="left" >&nbsp;
			<input type="Button" class=" button " value="ตกลง" id="confirmData" name="confirmData" onclick="onUpdate();"/>
		</td>
	</tr>
</table>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--
function addVisualRow(){
		var tab = $('dataTable');
	
		var table = document.getElementById("table");
		var tempRow = document.getElementById("temprow");
	
		// Insert two rows.
	   	var oTable = table;
	   	var oRowsCheck = table.rows;
	   	var rrCheck = oRowsCheck.length - 1;
	   	var idx = oTable.rows.length;
	   
	   	var str=" " ;
		var from = "[0]";
		
		if(tab.rows.length > 0){
			if(idx > (2+tab.rows.length)){
				if(oRowsCheck[rrCheck].cells["empCode"].childNodes[0].value != null && oRowsCheck[rrCheck].cells["empCode"].childNodes[0].value != ''){
					// Insert cells into row.
					var oRow1=oTable.insertRow(idx);
					var to = "("+(oRow1.rowIndex+tempRow.rowIndex)+")";
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
				}else{
					alert('กรุณากรอกเลขประจำตัวก่อนเพิ่มข้อมูลตัวถัดไป');
				
				}
			}else{
					var oRow1=oTable.insertRow(idx);
					var to = "("+(oRow1.rowIndex+tempRow.rowIndex)+")";
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
		}else{
			if(idx > 2){
				if(oRowsCheck[rrCheck].cells["empCode"].childNodes[0].value != null && oRowsCheck[rrCheck].cells["empCode"].childNodes[0].value != ''){
					// Insert cells into row.
					var oRow1=oTable.insertRow(idx);
					var to = "("+(oRow1.rowIndex+tempRow.rowIndex)+")";
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
				}else{
					alert('กรุณากรอกเลขประจำตัวก่อนเพิ่มข้อมูลตัวถัดไป');
				
				}
			}else{
					var oRow1=oTable.insertRow(idx);
					var to = "("+(oRow1.rowIndex+tempRow.rowIndex)+")";
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
		}
	
}

//this function removeVisualRow() used page UPDATE ONLY!!!
	var chDelete = false;
	var chcCon = false;
	function removeVisualRow(){
		var tab = $('dataTable');
		var row;
		var empList=[];
		var frm = document.forms["searchForm"];
		var chk = frm.elements["chk"];
		
		var table = document.getElementById("table");
		var tdName;
		var chkName;
		var num;
		if(parseInt(DWRUtil.getValue("dataLength")) != null && parseInt(DWRUtil.getValue("dataLength")) > 0){
			num = 1 + parseInt(DWRUtil.getValue("dataLength"));
		}else{
			num = 1;
		}
		
		var oRows = table.rows;
		if(tdName == null)tdName="flag";
		if(chkName == null)chkName="chk";
		//alert(oRows.length+" : "+num);
		
		
		
		for(x=oRows.length-1;x > num;x--){
				if (oRows[x].cells[tdName].childNodes[0].checked){
						chcCon = true;
				}
			
			}
		 if(tab.rows.length>1){
				for(i=0; i<tab.rows.length; i++){
					row = tab.rows[i];	
					if (chk[i].checked){
							chcCon = true;
						}
					
						
				}
			}else{
				if(tab.rows.length==1){
					row = tab.rows[0];	
					if (chk[0].checked){
							chcCon = true;
						}
				  
				}	
			}
		
		
		
		
		
		
		if(chcCon){
			var answer = confirm("ต้องการลบข้อมูล หรือไม่?");
			if( answer ){
		
				for(i=oRows.length-1;i > num;i--){
						if (oRows[i].cells[tdName].childNodes[0].checked){
								table.deleteRow(i);		
								chDelete = true;	
						}
					
					}
					
				DWREngine.beginBatch();
					//alert('123435  :'+tab.rows.length);
				    if(tab.rows.length>1){
						for(i=0; i<tab.rows.length; i++){
							row = tab.rows[i];	
							if (chk[i].checked){
									
									//alert('BB'+rowDelete);
									rwIncDecOther.keySeq = parseInt(frm.elements["keySeq"][i].value);
									FeeWpayRwIncDecOtherService.deleteRwIncDecOther(rwIncDecOther, {callback:onDeleteCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
									chDelete = true;
								}
							
								
						}
					}else{
						if(tab.rows.length==1){
							row = tab.rows[0];	
							if (chk[0].checked){
									
									//alert('CC'+rowDelete);
									rwIncDecOther.keySeq = parseInt(frm.elements["keySeq"][0].value);
									FeeWpayRwIncDecOtherService.deleteRwIncDecOther(rwIncDecOther, {callback:onDeleteCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
									chDelete = true;
								}
						  
						}	
					}
			
				DWREngine.endBatch();
			
				}
		}
		
		
		
		if(chDelete){
			alert('ลบข้อมูลเรียบร้อย');
			DeletCompleaseData();
		}
	}
//***********************

	function onDeleteCallback(){
		//whenQueryData();
	}
	
	function DeletCompleaseData(){
		if(parseInt(DWRUtil.getValue("dataLength")) != null && parseInt(DWRUtil.getValue("dataLength")) > 0){
			whenShowDataTable();
		}
	}
	
	

//-->
</SCRIPT>