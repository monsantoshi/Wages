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
	//String approveF ="";
	if (request.getSession().getAttribute("processResult") != null){
		ProcessResult processResult = (ProcessResult) request.getSession().getAttribute("processResult");
		//year =  request.getParameter("hidYear")==null?String.valueOf(now.get(Calendar.YEAR)+543):request.getParameter("hidYear");
		//month =  request.getParameter("hidMonth")==null?String.valueOf(now.get(Calendar.MONTH)+1):request.getParameter("hidMonth");
	}
	java.util.Calendar cc = new java.util.GregorianCalendar();
	int x = cc.get(java.util.Calendar.MONTH) +1;
	//System.out.println("#$#$#$#$$#$#$# :::"+x);
	
	System.out.println("ConfirmRwVinai2 : " + session.getAttribute("ConfirmRwVinai2").getClass().getName());
	String confirm = (String)session.getAttribute("ConfirmRwVinai2");
	System.out.println("ConfirmRwVinai2 : " + confirm);
	
	String periodWork = String.valueOf(x);
%>
<html>
<head>
<title>บันทึกรายการหักโดยได้รับค่าจ้างไม่เต็มเดือน</title>
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
		$("period").value = "<c:out value='${DefaultYearAndSection.period}' /> "  ;//data.period;
		$("confirm").value = "<c:out value='${ConfirmRwVinai2}' /> "  ;//data.confirm;
		chkMainClose();
	
		//alert($("confirm").value +' ::: '+<c:out value='${DefaultYearAndSection.confirm}' /> );	
		//PnEmployeeService.findPrEmpBySecurity('<%=userId%>','<%=ouCode%>',$("year").value,$("period").value, {callback:onLoadEmployeeCallback});
		
		 if(<c:out value='${ConfirmRwVinai2}' />){
					document.forms['searchForm'].elements['confirmData'].disabled = true;
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
    
   /* function whenFetchEmployeeToCallback(data)
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
    }*/

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
				FeeWpayPrPeriodLineService.findPeriodLine('<%=ouCode%>',$("year").value,$("period").value, '<%=userId%>', {callback:whenchkMainCloseCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร1');}});
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
	
	//dojo.addOnLoad(onLoadIncDecCallback);
	
	// =========================== End LOV ===========================
	
	
	
	function whenShowDataTable()
	{	
		myUpdate = new Array();
		//alert(document.forms['searchForm'].elements['incDecCbo'].value);
		
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
			
			
			//alert('query');
			// Query by Criteria
			FeeWpayRwVinai2Service.findByCriteriaListApprove
			(
				'<%=userId%>',
				'<%=ouCode%>',
				parseInt(DWRUtil.getValue("year")),
				parseInt(DWRUtil.getValue("period")),
				orgFromCbo,
				orgToCbo,
				empFromCbo,
				empToCbo,
				DWRUtil.getValue("page"),
				DWRUtil.getValue("dataPerPage"),
				{callback:whenListDataTableHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร2');}}
			);
		}
	
	
	var cellFuncs = [
		function(data) { return writeSelect("flagPr",data.flagPr,data.keySeq);},
		function(data) { return writeTextID("empCode",data.empCode,10,"left",data.keySeq);},
		function(data) { return writeTextDisplay("name",data.name,200,"left","codeSeq",data.codeSeq);},
		function(data) { return writeTextYearWork("yearWork",data.yearWork,4,"center",data.keySeq);},
		function(data) { return writeSelectMonth("periodWork",data.periodWork,data.keySeq);},
		function(data) { return writeText("decDay",data.decDay,6,"right",data.keySeq);},
		function(data) { 
						if(data.remark != null && data.remark != ''){
							return writeHidden("remark",data.remark,200,"left","keySeq",data.keySeq);
						}else{
							return writeHidden("remark","",200,"left","keySeq",data.keySeq);
						}		
						},
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
		//if(<c:out value='${ConfirmRwVinai2Deduct}' />){
		return "<div align='center' style='background-color:#CCCCCC;'><select name='"+inname+"'  disabled='true' background-color:transparent;color:#000000' ;><option value='N' "+temp1+" >ปกติ</option>"+
												"<option value='A' "+temp2+">ปรับปรุงรายการหัก</option></select></div>";
		//}else {
		//return "<div align='center' ><select onchange='addListUpdate("+key+")' name='"+inname+"'><option value='N' "+temp1+" >ปกติ</option>"+
					//							"<option value='A' "+temp2+">ปรับปรุงรายการหัก</option></select></div>";
		
		
		//}
	}
	
	function writeTextDay(inname,emp,maxlength,textalign,key)
	{
		return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		
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
		}
	
	
	
	function writeSelectMonthCut(inname,emp,key){
		var mm = '';
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
		
		if(emp== ''){
			mm = 'selected';
		}else if(emp=='1'){
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
		
		//if(<c:out value='${ConfirmRwVinai2Deduct}' />){
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
		
		//if(<c:out value='${ConfirmRwVinai2Deduct}' />){
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
	
	function writeText(inname,emp,maxlength,textalign,key)
	{
		//กรณี display not edit
		//if(<c:out value='${ConfirmRwVinai2Deduct}' />){
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
		//if(<c:out value='${ConfirmRwVinai2Deduct}' />){																	
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onchange='whenSelectEmpOption();'/></div>";
		//}else{
		//	return "<div align='center' ><input type='text' maxlength='6' name = '"+inname+"' onchange='whenSelectEmpOptionInRowUpdate("+key+");addListUpdate("+key+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		//}
	}
	
	
	function writeTextYearWork(inname,emp,maxlength,textalign,key)
	{
				return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' size='6' maxlength='"+maxlength+"' style='text-align:"+textalign+";background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}
	
	function writeHidden(inname,emp,maxlength,textalign,nameHide,empHide)
	{
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' /><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
	}
	
	function writeStTextDate(inname,emp,maxlength,textalign,key,size)
	{
		
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' size='"+size+"' maxlength='"+maxlength+"' style='text-align:"+textalign+";background-color:transparent;' onblur='validateDate(this);'/></div>";
		
	}
	
	function writeEndTextDate(inname,emp,maxlength,textalign,key,size)
	{
		
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' size='"+size+"' maxlength='"+maxlength+"' style='text-align:"+textalign+";background-color:transparent;' onblur='validateDate(this);'/></div>";
		
	}
	function writeHidden2(nameHide,empHide)
	{
		
			return "<div align='center'><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		
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
	
	
	var feeWpayRwVinai2 = {keySeq:null, ouCode:null, yearPr:null, periodPr:null,empCode:null,confirmFlag:null,
					yearWork:null,periodWork:null,codeSeq:null,decDay:null,remark:null,
					flagPr:null,updBy:null,updDate:null,creBy:null,creDate:null,approveFlag:null,approveBy:null,approveDate:null};
	
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
		
		
		
	    //alert("aRows "+aRows.length);
	    //alert("num "+num);
		for(var a=aRows.length-1;a > num;a--){
				if (aRows[a].cells["empCode"].childNodes[0].value == null || aRows[a].cells["empCode"].childNodes[0].value == ''){
					empNull = false;
					//break;
			}
		}
		
		
		if(tab.rows.length > 0){
		
			var keySeq 		= frm.elements["keySeq"];
			var empCode 	= frm.elements["empCode"];
			var name		= frm.elements["name"];
			var codeSeq 	= frm.elements["codeSeq"];
			var flagPr 		= frm.elements["flagPr"];
			var yearWork 	= frm.elements["yearWork"];
			var periodWork 	= frm.elements["periodWork"];
			
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
						feeWpayRwVinai2.keySeq = parseInt(keySeq[i].value);
						feeWpayRwVinai2.codeSeq = codeSeq[i].value;
						if(tab.rows.length == 1){
							feeWpayRwVinai2.flagPr = flagPr.value;
						}else{
							feeWpayRwVinai2.flagPr = flagPr[i].value;
						}
						if (empCode[i].value != '' && empCode[i].value != null){
							feeWpayRwVinai2.empCode  = empCode[i].value;
						}
						else{
							feeWpayRwVinai2.empCode  = null;
						}
						
						if (name[i].value != '' && name[i].value != null){
							feeWpayRwVinai2.name  = name[i].value;
						}
						else{
							feeWpayRwVinai2.name  = null;
						}
						if (yearWork[i].value != ''&& yearWork[i].value != null){
							feeWpayRwVinai2.yearWork  = yearWork[i].value;
						}
						else{
							feeWpayRwVinai2.yearWork  = null;
						}
						
						if (periodWork[i].value != '' && periodWork[i].value != null){
							feeWpayRwVinai2.periodWork  = periodWork[i].value;
						}
						else{
							feeWpayRwVinai2.periodWork  = null;
						}
						
						
						feeWpayRwVinai2.approveFlag = approveF[i].value;
						feeWpayRwVinai2.approveBy = '<%=userId%>';
						//feeWpayRwVinai2.updBy = '<%=userId%>';
						
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
									FeeWpayRwVinai2Service.addListApprove(feeWpayRwVinai2, false, {callback:onInsertCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
								else
									FeeWpayRwVinai2Service.addListApprove(feeWpayRwVinai2, false);
					
								}else{
								if( allRowUpdate == myUpdate.length )
									FeeWpayRwVinai2Service.addListApprove(feeWpayRwVinai2, true, {callback:ClearData,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้');}});
								else
									FeeWpayRwVinai2Service.addListApprove(feeWpayRwVinai2, false);
								
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
			}
		}else{
					alert('เลขประจำตัวไม่ถูกต้อง cansave');
				}
	}
	
	function ClearData(){
		alert("บันทึกข้อมูลเรียบร้อย");
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
		var empNull = true;
		
		var sst = "";
		var sed = "";
		
		if(parseInt(DWRUtil.getValue("dataLength")) != null && parseInt(DWRUtil.getValue("dataLength")) > 0){
			num = 1 + parseInt(DWRUtil.getValue("dataLength"));
		}else{
			num = 1 ;
		}
		var oRows = table.rows;
		var insertStatus = false;
		if(tdName == null)tdName="flag";
		if(chkName == null)chkName="chk";
		
		for(var c=oRows.length-1;c > num;c--){
				if (oRows[c].cells["empCode"].childNodes[0].value == null || oRows[c].cells["empCode"].childNodes[0].value == ''){
					empNull = false;
				}
		}
		
		//alert( empNull );
		
		if(empNull){
		
			DWREngine.beginBatch();
			//alert( oRows.length + ' ' + num );
			for(var i=oRows.length-1;i > num;i--)
			{
					insertStatus = true;
					feeWpayRwVinai2.keySeq = null;
					feeWpayRwVinai2.ouCode = '<%=ouCode%>';
					feeWpayRwVinai2.yearPr = parseInt($("year").value);
					feeWpayRwVinai2.periodPr = parseInt($("period").value);
					feeWpayRwVinai2.empCode = oRows[i].cells["empCode"].childNodes[0].value;
					feeWpayRwVinai2.name = oRows[i].cells["name"].childNodes[0].value;
					feeWpayRwVinai2.codeSeq = oRows[i].cells["name"].childNodes[1].value;
					feeWpayRwVinai2.flagPr = oRows[i].cells["flagPr"].childNodes[0].value;
					feeWpayRwVinai2.yearWork = oRows[i].cells["yearWork"].childNodes[0].value;
					feeWpayRwVinai2.periodWork = oRows[i].cells["periodWork"].childNodes[0].value;
					
					
					
					
					feeWpayRwVinai2.approveFlag = oRows[i].cells["approveF"].childNodes[0].value;
					feeWpayRwVinai2.approveBy = '<%=userId%>';
					feeWpayRwVinai2.approveDate = getDateFromFormat(<%=date%>,"dd/MM/yyyy");
					//feeWpayRwVinai2.confirmFlag = 'N';
					//feeWpayRwVinai2.updBy = '<%=userId%>';
					//feeWpayRwVinai2.creBy = '<%=userId%>';
					//feeWpayRwVinai2.creDate = getDateFromFormat(<%=date%>,"dd/MM/yyyy");
					//RwIncDecOtherService.addList(rwIncDecOther, {callback:onInsertCallback});
					
						if( i == (num + 1) )
							FeeWpayRwVinai2Service.addListApprove(feeWpayRwVinai2, true, {callback:ClearData,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้');}});
						else
							FeeWpayRwVinai2Service.addListApprove(feeWpayRwVinai2, false);
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
			//alert('loop ::'+i);
			//alert(startMonth.value+' :: '+ startYear.value + ' ::  '+endMonth.value+' ::: ' + endYear.value );
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
	 function checkDayRowUpdate(data){
	 	 var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["formEdit"];
		//alert('leng  :'+tab.rows.length);	
		
		if(tab.rows.length > 0){
			var keySeq 		= frm.elements["keySeq"];
			var monthCheck 	= frm.elements["absMonth1"];
			var dayCheck 		= frm.elements["absDay1"];
		
			for(var c=0; c<tab.rows.length; c++){
				//alert(keySeq[c].value + ':::'+ data);
				if(keySeq[c].value == data ){
					rowModify = c;
				//	alert('value in row :'+keySeq[c].value+' : '+flagPr[c].value+' : '+yearWork[c].value+' : '+periodWork[c].value+' : '+fiscalYear[c].value+' : '+startDate[c].value+' : '+endDate[c].value+' : '+totDay1[c].value+' : '+totDay15[c].value+' : '+totDay3[c].value+' : '+seqData[c].value+' : '+codeSeqNew[c].value+' : '+flagWork[c].value+' : '+orgCode[c+1].value);
					break;
				}
			}
			
		
			var month = monthCheck[rowModify].value;
			var day = dayCheck[rowModify].value;
		
			
			
			
		 	
		
		
				if(day != null && day != '' && day != 0){
				
					switch (month) {
						  case "1":
						    if(day > 31){
						    	alert('จำนวนวันไม่ถูกต้อง');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "2":
						    if(day > 29){
						    	alert('จำนวนวันไม่ถูกต้อง');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "3":
						    if(day > 31){
						    	alert('จำนวนวันไม่ถูกต้อง');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "4":
						    if(day > 30){
						    	alert('จำนวนวันไม่ถูกต้อง');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "5":
						    if(day > 31){
						    	alert('จำนวนวันไม่ถูกต้อง');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "6":
						    if(day > 30){
						    	alert('จำนวนวันไม่ถูกต้อง');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "7":
						    if(day > 31){
						    	alert('จำนวนวันไม่ถูกต้อง');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "8":
						    if(day > 31){
						    	alert('จำนวนวันไม่ถูกต้อง');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "9":
						    if(day > 30){
						    	alert('จำนวนวันไม่ถูกต้อง');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "10":
						    if(day > 31){
						    	alert('จำนวนวันไม่ถูกต้อง');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "11":
						    if(day > 30){
						    	alert('จำนวนวันไม่ถูกต้อง');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "12":
						    if(day > 31){
						    	alert('จำนวนวันไม่ถูกต้อง');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						} 
				
				
				}
		//alert(month+' ::: '+day)
		
	
	 	}
	 	
	 
	 }
	 
	
	 
	 
	function disabledButton(){
		document.getElementById("confirmData").disabled = true;
	}
	
	function enabledButton(){
		document.getElementById("confirmData").disabled = false;
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
			var decDay	= frm.elements["decDay"];
			
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
		FeeWpayPnEmployeeService.findByEmpCodeDetail('<%=userId%>',empCode, '<%=ouCode%>', parseInt(DWRUtil.getValue("year")), parseInt(DWRUtil.getValue("period")), {callback:whenFetchEmployeeDetailInRowUpdateCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร4');}});
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
			var decDay	= frm.elements["decDay"];
			
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
	 
	 function whenFetchEmployeeDetailInRow(empCode)
	 {
		DWRUtil.useLoadingMessage("Loading ...");
		//alert(empCode+' : '+<%=ouCode%>+' : ' + DWRUtil.getValue("year")+' : '+ DWRUtil.getValue("period"))
		FeeWpayPnEmployeeService.findByEmpCodeDetail('<%=userId%>',empCode, '<%=ouCode%>', parseInt(DWRUtil.getValue("year")), parseInt(DWRUtil.getValue("period")), {callback:whenFetchEmployeeDetailInRowCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร5');}});
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
		function countData()
		{
			myUpdate = new Array();
			//if(document.forms['searchForm'].elements['incDecCbo'].value != null && document.forms['searchForm'].elements['incDecCbo'].value != ''){
				var orgFromCbo = '';
				var orgToCbo = '';
				var	empFromCbo = '';
				var	empToCbo = '';
				
				
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
				
				
				// Query by Criteria
				FeeWpayRwVinai2Service.countDataApprove
				(
					'<%=userId%>',
					'<%=ouCode%>',
					parseInt(DWRUtil.getValue("year")),
					parseInt(DWRUtil.getValue("period")),
					orgFromCbo,
					orgToCbo,
					empFromCbo,
					empToCbo,
					{callback:countDataHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร6');}}
				);
			}
			
			function countDataHandler(data)
			{
				DWRUtil.setValue("countData",data);
				onCheckButt("searchForm");
			    
			}
			 function backForm(){
		 window.history.back();
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
			[ CTWPAYAPMT010] บันทึกรับรองรายการหักโดยได้รับค่าจ้างไม่เต็มเดือน
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
	    <td align="left"><SELECT  dojoType="ComboBox" widgetId="empFromCbo" style="width:200"  onBlurInput="whenSelectEmpOption();"></SELECT></td>
	    <td  class="font-field" align="right">ถึงเลขประจำตัว&nbsp;</td>
	    <td align="left"><SELECT  dojoType="ComboBox" widgetId="empToCbo" style="width:200"></SELECT></td>
	    <td><input type="Button" value="ค้นหา" class=" button " onclick="whenShowDataTable();" /></td>
  	</tr>
 	

</table>
<br/>

<table  width="1100" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td>
		<div style="height:320px;width:1000;overflow:auto;vertical-align: top;" align="center" >
		<table id="table" width="1000"  border="1" bordercolor="#6699CC" cellpadding="0" cellspacing="0">
			<thead style="text-align: center">
				<tr CLASS="TABLEBULE2" style="height: 30px;">
					<th CLASS="TABLEBULE2"  style="width:90px">ประเภทรายการ</th>
					<th CLASS="TABLEBULE2"  style="width:120px">เลขประจำตัว</th>
					<th CLASS="TABLEBULE2"   style="width:250px">ชื่อ - นามสกุล</th>
					<th CLASS="TABLEBULE2" colspan="2" align="center">ปี/เดือนที่ทำงาน</th>
					<th CLASS="TABLEBULE2" style="width:120px" align="center"><center>จำนวนวันที่ถูกหัก</center></th>
					<th CLASS="TABLEBULE2" style="width:250px" align="center">หมายเหตุ</th>
					<th CLASS="TABLEBULE2" style="width:100px" >รับรอง</th>
				</tr>
				
			</thead>
			<tbody id="dataTable">
			</tbody>
			<tr id="temprow" style="visibility:hidden;position:absolute">
				<td id="flagPr" readonly="readonly" width="50" >
					<select  name="type" >
						<option value="N"  >ปกติ</option>
						<option value="A" >ปรับปรุงรายการรับ</option>
						<option value="R"  >รายการรับเรียกคืน</option>
				</select>
			</td>
			<td id="empCode" align="center"><input type="text" maxlength="6" name="empCode" readonly="readonly" style="width:100%" onchange="whenSelectEmpOptionInRow(this);"  onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"></td>
			<td id="name" align="center"><input type="text"  name="name" readonly="readonly" style="width:100%;background-color:silver;"/><input type="hidden"  name="codeSeq" /></td>
			<td id="yearWork" align="center"><input type="text" size="6" name="yearWork" readonly="readonly" value="<c:out value='${DefaultYearAndSection.year}' />" maxlength="4" align="center" style="text-align:center;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
			<td id="periodWork" align="center" ><select name="periodWork" disabled="disable">
													<option value="1" <% if ( periodWork.equals("1")){%> selected="selected"  <%} %> >มกราคม</option>
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
			<td id="decDay"><input type="text"  name="decDay" readonly="readonly"  maxlength="6" onchange="checkDay(this);" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
			<td id="remark"><input type="text"  name="remark" readonly="readonly" maxlength="100" align="center" style="text-align:left;width:100%;" /><input type="hidden"  name="keySeq" /></td>
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
			<input type="Button" class=" button " value="ออก" id="gotoTree" name="gotoTree" onclick="backForm();"/>
		</td>
	</tr>
</table>
</form>
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
									feeWpayRwVinai2.keySeq = parseInt(frm.elements["keySeq"][i].value);
									FeeWpayRwVinai2Service.deleteRwVinai2(feeWpayRwVinai2, {callback:onDeleteCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร7');}});
									chDelete = true;
								}
							
								
						}
					}else{
						if(tab.rows.length==1){
							row = tab.rows[0];	
							if (chk[0].checked){
									
									//alert('CC'+rowDelete);
									feeWpayRwVinai2.keySeq = parseInt(frm.elements["keySeq"][0].value);
									FeeWpayRwVinai2Service.deleteRwVinai2(feeWpayRwVinai2, {callback:onDeleteCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร8');}});
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
	function compareStDateRowUpdate(data){
	 	
	 	var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["formEdit"];
		//alert('leng  :'+tab.rows.length);	
		
		if(tab.rows.length > 0){
			var keySeq 		= frm.elements["keySeq"];
			var startDateQut 	= frm.elements["startDateQut"];
			var endDateQut 		= frm.elements["endDateQut"];
		
			
			for(var c=0; c<tab.rows.length; c++){
				//alert(keySeq[c].value + ':::'+ data);
				if(keySeq[c].value == data ){
					rowModify = c;
				//	alert('value in row :'+keySeq[c].value+' : '+flagPr[c].value+' : '+yearWork[c].value+' : '+periodWork[c].value+' : '+fiscalYear[c].value+' : '+startDate[c].value+' : '+endDate[c].value+' : '+totDay1[c].value+' : '+totDay15[c].value+' : '+totDay3[c].value+' : '+seqData[c].value+' : '+codeSeqNew[c].value+' : '+flagWork[c].value+' : '+orgCode[c+1].value);
					break;
				}
			}
			
			var stDate = startDateQut[rowModify].value;
			var enDate = endDateQut[rowModify].value;
			
			var stDay = stDate.substr(0, 2);
			var stMonth = stDate.substr(3, 2);
			var stYear = stDate.substr(6);
			
			if(stDate != null && stDate != '' && enDate != null && enDate != ''){
			 	 if (compareDates(stDate,"dd/MM/yyyy",endDate,"dd/MM/yyyy") == 1) {
					alert("วันที่เริ่มต้นต้องน้อยกว่า วันที่สิ้นสุด");
					endDateQut[rowModify].value = '';
					startDateQut[rowModify].focus();
				}
		 	}
		  
		}
	 }
	 
	 function compareEndDateRowUpdate(data){
	 	var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["formEdit"];
		//alert('leng  :'+tab.rows.length);	
		
		if(tab.rows.length > 0){
			var keySeq 		= frm.elements["keySeq"];
			var startDateQut 	= frm.elements["startDateQut"];
			var endDateQut 		= frm.elements["endDateQut"];
		
			for(var c=0; c<tab.rows.length; c++){
				//alert(keySeq[c].value + ':::'+ data);
				if(keySeq[c].value == data ){
					rowModify = c;
				//	alert('value in row :'+keySeq[c].value+' : '+flagPr[c].value+' : '+yearWork[c].value+' : '+periodWork[c].value+' : '+fiscalYear[c].value+' : '+startDate[c].value+' : '+endDate[c].value+' : '+totDay1[c].value+' : '+totDay15[c].value+' : '+totDay3[c].value+' : '+seqData[c].value+' : '+codeSeqNew[c].value+' : '+flagWork[c].value+' : '+orgCode[c+1].value);
					break;
				}
			}
			
		
			var stDate = startDateQut[rowModify].value;
			var enDate = endDateQut[rowModify].value;
		
			var endDay = enDate.substr(0, 2);
			var endMonth = enDate.substr(3, 2);
			var endYear = enDate.substr(6);
			
			if(stDate != null && stDate != '' && enDate != null && enDate != ''){
			 	if(compareDates(stDate,"dd/MM/yyyy",endDate,"dd/MM/yyyy") == 1){
					alert("วันที่เริ่มต้นต้องน้อยกว่า วันที่สิ้นสุด");
					endDateQut[rowModify].focus();
					startDateQut[rowModify].value = '';
				}
		 	}
		 	
		}
	 }
	

//-->
</SCRIPT>