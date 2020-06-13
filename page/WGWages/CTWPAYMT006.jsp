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
	
	java.util.Date dd = new java.util.Date();
	java.text.SimpleDateFormat fmd = new java.text.SimpleDateFormat("dd/MM/yyyy",new java.util.Locale("th","TH"));
	String date = fmd.format(dd);
	String pageEdit = request.getParameter("pageEdit")==null?"-1":request.getParameter("pageEdit");
	com.ss.tp.dto.DefaultYearSectionVO defaltYear = (com.ss.tp.dto.DefaultYearSectionVO)request.getSession().getAttribute("DefaultYearAndSection");
	String periodSession = defaltYear.getPeriod();
	
	java.util.Calendar cc = new java.util.GregorianCalendar();
	int x = cc.get(java.util.Calendar.MONTH)+1 ;
	int y = cc.get(java.util.Calendar.YEAR);
	int xx = (Integer.parseInt(periodSession))/2;
	String periodWork = String.valueOf(xx-1);
	
	
	
	if(periodWork.equals("0")){
		periodWork = "12";
		String year = String.valueOf(y-1);
	}else{
	    String year = String.valueOf(y);
	}
	
%>
<html>
<head>
<title>บันทึกเงินบำรุงสุขภาพ</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="script/payroll_util.js"></script>
<!-- Javascript Script File -->
<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrPeriodLineService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPnEmployeeService.js"></SCRIPT>
<script type="text/javascript" src="dwr/interface/FeeWpayRwHealthService.js"></script>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrIncomeDeductService.js"></SCRIPT>

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
		$("confirm").value = "<c:out value='${ConfirmRwHealth}' /> "  ;//data.confirm;
		chkMainClose();
	   
		//alert($("year").value +' ::: '+<c:out value='${DefaultYearAndSection.year}' /> );	
		//PnEmployeeService.findPrEmpBySecurity('<%=userId%>','<%=ouCode%>',$("year").value,$("period").value, {callback:onLoadEmployeeCallback});
		
		 if(<c:out value='${ConfirmRwHealth}' />){
					document.forms['searchForm'].elements['insertData'].disabled = true;
					document.forms['searchForm'].elements['deleteData'].disabled = true;
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
//dojo.addOnLoad(onLoadOrganization);
	dojo.addOnLoad(onLoadOrganizationCallback);
	dojo.addOnLoad(onLoadYearSectionCallback);
	dojo.addOnLoad(onLoadEmployeeCallback);
	//dojo.addOnLoad(check);
	//dojo.addOnLoad(onLoadIncDecCallback);
	
	
	
	// =========================== End LOV ===========================
	
	
		
	function whenShowDataTable()
	{	
		myUpdate = new Array();
	
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
			var page = '';
			if( parseInt( $("page").value ) == -1 ){
				page = 0;
				$("page").value = parseInt($("page").value) + 1;
			}else
				page = $("page").value;
			
	
	
			FeeWpayRwHealthService.findByCriteriaList
			(
				'<%=userId%>',
				'<%=ouCode%>',
				parseInt(DWRUtil.getValue("year")),
				parseInt(DWRUtil.getValue("period")),
				orgFromCbo,
				orgToCbo,
				empFromCbo,
				empToCbo,
				page,
				DWRUtil.getValue("dataPerPage"),
				{callback:whenListDataTableHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}}
			);
		
	}
	
	
	function countData()
	{	
		DWRUtil.useLoadingMessage("Loading ...");
     	var	orgFromCbo = dojo.widget.byId("orgFromCbo");
		var	orgToCbo = dojo.widget.byId("orgToCbo");
     	var	empFromCbo = dojo.widget.byId("empFromCbo");
		var	empToCbo = dojo.widget.byId("empToCbo");
		
	
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
		
		
			FeeWpayRwHealthService.countDataList
			(
				'<%=userId%>',
				'<%=ouCode%>',
				parseInt(DWRUtil.getValue("year")),
				parseInt(DWRUtil.getValue("period")),
				orgFromCbo,
				orgToCbo,
				empFromCbo,
				empToCbo,
				{callback:whenCountDataCallBack}
			);
		
	}
	  function whenCountDataCallBack(data){
	    	DWRUtil.setValue("countData",data);
			onCheckButt("searchForm");
	    }
	
	var cellFuncs = [
		function(data) { return writeCheckBox();},
		function(data) { return writeSelect("flagPr",data.flagPr,data.keySeq);},
		function(data) { return writeTextID("empCode",data.empCode,10,"left",data.keySeq);},
		function(data) { return writeTextDisplay("name",data.name,200,"left","codeSeq",data.codeSeq);},
		function(data) { return writeTextDisplay("salary",data.salary,100,"left","codeSeq",data.codeSeq);},
		function(data) { return writeTextYearWork("yearWork",data.yearWork,4,"center",data.keySeq);},
		function(data) { return writeSelectMonth("periodWork",data.periodWork,data.keySeq);},
		function(data) { return writeText("totalHour",data.totalHour,9,"right",data.keySeq);},
		function(data) { return writeHidden("seqData",data.seqData,4,3,"right","keySeq",data.keySeq);}
	];
	
	function writeCheckBox()
	{	if(<c:out value='${ConfirmRwHealth}' />){
			return "<div align='center'><input type='checkbox'disabled='true' name ='chk'  /></div>";
		}else{
			return "<div align='center'><input type='checkbox' name ='chk'  /></div>";
		}
		
	}
	
	function writeSelect(inname,emp,key)
	{
		var temp1 = '';
		var temp2 = '';
		var temp3 = '';
		var temp4 = '';
		var temp5 = '';
		if(emp=='N'){
			temp1 = 'selected';
		} else if(emp=='A'){
			temp2 = 'selected';		
		}else if(emp=='R'){
			temp3 = 'selected';		
		}
		if(<c:out value='${ConfirmRwHealth}' />){
		return "<div align='center' style='background-color:#CCCCCC;'><select name='"+inname+"' disabled='true' style='background-color:transparent;color:#000000'><option value='N' "+temp1+" >ปกติ</option>"+
												"<option value='A' "+temp2+">ปรับปรุงรายการรับ</option>" +
												"<option value='R' "+temp3+">รายการรับเรียกคืน</option></select></div>";
		}else {
		return "<div align='center' ><select onchange='addListUpdate("+key+")' name='"+inname+"'><option value='N' "+temp1+" >ปกติ</option>"+
												"<option value='A' "+temp2+">ปรับปรุงรายการรับ</option>" +
												"<option value='R' "+temp3+">รายการรับเรียกคืน</option></select></div>";
		
		
		}
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
		
		if(<c:out value='${ConfirmRwHealth}' />){
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
	
	function writeText(inname,emp,maxlength,textalign,key)
	{
		//กรณี display not edit
		if(<c:out value='${ConfirmRwHealth}' />){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}else{
			return "<div align='center' ><input type='text' name = '"+inname+"' onChange='addListUpdate("+key+");checkValueInRowUpdate("+key+");'  value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}
	}
	
	function writeStart(startDate,key){
		
		//alert(startDate.substr(3,2));
		// split startDate month year
		var arr ;
		//alert( arr );
		//alert(arr[0]+' ::: '+arr[1]);
		var emp ;
		var year ;
		if(startDate != null && startDate != ''){
			//arr = convertDateToArray(startDate);
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
		
		if(<c:out value='${ConfirmRwHealth}' />){
			//alert('x1');
			return "<div align='center' style='background-color:#CCCCCC;'>"+
						"<select name='startMonth' disabled='true' style='background-color:transparent;color:#000000' onChange='addListUpdate("+key+");validateMonthYear(this);'>" +
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
						"/<input type='text'  name='startYear'  maxlength='4' value='"+year+"' align='center' onchange='addListUpdate("+key+");validateMonthYear(this);' style='background-color:#CCCCCC;text-align:right;width:40px;' onkeyup='if(this.value < 0) this.value = \"\";if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}else {
			//alert('x2');
			var str = 	"<div align='center' >"+
						"<select name='startMonth' onChange='addListUpdate("+key+");validateMonthYear(this);'>" +
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
						"/<input type='text'  name='startYear'  maxlength='4' value='"+year+"' align='center' onchange='addListUpdate("+key+");'  style='text-align:right;width:40px;' onkeyup='if(this.value < 0) this.value = \"\";if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);validateMonthYear(this);'/></div>";
			//alert( str );
			return str;
		
		}
	
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
		
		if(<c:out value='${ConfirmRwHealth}' />){
		return "<div align='center' style='background-color:#CCCCCC;'>"+
					"<select name='endMonth' disabled='true' style='background-color:transparent;color:#000000' onChange='addListUpdate("+key+");validateMonthYear(this);'>" +
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
		}else {
		return 	"<div align='center' >"+
				"<select name='endMonth' onChange='addListUpdate("+key+");validateMonthYear(this);'>" +
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
				"/<input type='text'  name='endYear'  maxlength='4' value='"+year+"' align='center' onchange='addListUpdate("+key+");'  style='text-align:right;width:40px;' onkeyup='if(this.value < 0) this.value = \"\";if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);validateMonthYear(this);'/>"+
				"</div>";
		
		
		}
	
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
	
	function writeTextDisplay(inname,emp,maxlength,textalign,nameSeq,codeseq)
	{
		return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' /><input type='hidden' name = '"+nameSeq+"' value='"+codeseq+"'  /></div>";
	}
	
	function writeTextID(inname,emp,maxlength,textalign,key)
	{
		//กรณี display not edit
		if(<c:out value='${ConfirmRwHealth}' />){																	
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onchange='whenSelectEmpOptionInRow();'/></div>";
		}else{
			return "<div align='center' ><input type='text' name = '"+inname+"' maxlength='6' onChange='whenSelectEmpOptionInRowUpdate("+key+");addListUpdate("+key+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;'  onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}
	}
	
	function writeTextYearWork(inname,emp,maxlength,textalign,key)
	{
		//กรณี display not edit
		if(<c:out value='${ConfirmRwHealth}' />){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' size='6' maxlength='"+maxlength+"' style='text-align:"+textalign+";background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}else{
			return "<div align='center' ><input type='text' name = '"+inname+"' onchange='addListUpdate("+key+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' size='6' style='text-align:"+textalign+";' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}
	}
	
	function writeHidden(inname,emp,size,maxlength,textalign,nameHide,empHide)
	{
		if(<c:out value='${ConfirmRwHealth}' />){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		}else{
			return "<div align='center'><input type='text' name = '"+inname+"' onchange='addListUpdate("+empHide+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		}
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
	
	
	var rwHealth = {keySeq:null, ouCode:null, yearPr:null, periodPr:null,empCode:null,salary:null,
					yearWork:null,periodWork:null,codeSeq:null,totalHour:null,confirmFlag:null,
					seqData:null,flagPr:null,updBy:null,updDate:null,creBy:null,creDate:null,approveFlag:null};
	
	var allRowUpdate = 0;
	
	function onUpdate(){
		
		var table = document.getElementById("table");
		var aRows = table.rows;
		var num = 1+ parseInt(DWRUtil.getValue("dataLength"));
		
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
			var empCode 	= frm.elements["empCode"];
			var name		= frm.elements["name"];
			var codeSeq 	= frm.elements["codeSeq"];
			var flagPr 		= frm.elements["flagPr"];
			var yearWork 	= frm.elements["yearWork"];
			var periodWork 	= frm.elements["periodWork"];
			var totalHour 	= frm.elements["totalHour"];
			var seqData 	= frm.elements["seqData"];
			
			
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
						rwHealth.keySeq = parseInt(keySeq[i].value);
						
						
						
						rwHealth.codeSeq = codeSeq[i].value;
						
						if(tab.rows.length == 1){
							rwHealth.flagPr = flagPr.value;
						}else{
							rwHealth.flagPr = flagPr[i].value;
						}
						
						rwHealth.periodWork  = periodWork[i].value;
						
						if (yearWork[i].value != ''){
							rwHealth.yearWork  = yearWork[i].value;
						}
						else{
							rwHealth.yearWork  = null;
						}
						
						if (empCode[i].value != ''){
							rwHealth.empCode  = empCode[i].value;
						}
						else{
							rwHealth.empCode  = null;
						}
						
						if (name[i].value != ''){
							rwHealth.name  = name[i].value;
						}
						else{
							rwHealth.name  = null;
						}
						
			
						if (totalHour[i].value !=''){
							rwHealth.totalHour  = parseInt(totalHour[i].value);
						}
						else{
							rwHealth.totalHour  = null;
						}
						
						
						if (seqData[i].value !=''){
							rwHealth.seqData  = parseInt(seqData[i].value);
						}
						else{
							rwHealth.seqData  = null;
						}
						
						rwHealth.updBy = '<%=userId%>';
						
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
									FeeWpayRwHealthService.addList(rwHealth, false, {callback:onInsertCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
								else
									FeeWpayRwHealthService.addList(rwHealth, false);
					
								}else{
								if( allRowUpdate == myUpdate.length )
									FeeWpayRwHealthService.addList(rwHealth, true, {callback:ClearData,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้');}});
								else
									FeeWpayRwHealthService.addList(rwHealth, false);
								
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
					alert('เลขประจำตัวไม่ถูกต้อง');
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
				if (oRows[c].cells["empCode"].childNodes[0].value == null || oRows[c].cells["empCode"].childNodes[0].value == '' || oRows[c].cells["name"].childNodes[0].value == null || oRows[c].cells["name"].childNodes[0].value == ''){
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
					rwHealth.keySeq = null;
					rwHealth.ouCode = '<%=ouCode%>';
					rwHealth.yearPr = parseInt($("year").value);
					rwHealth.periodPr = parseInt($("period").value);
					rwHealth.empCode = oRows[i].cells["empCode"].childNodes[0].value;
					rwHealth.name = oRows[i].cells["name"].childNodes[0].value;
					rwHealth.codeSeq = oRows[i].cells["name"].childNodes[1].value;
					rwHealth.flagPr = oRows[i].cells["flagPr"].childNodes[0].value;
					rwHealth.yearWork = oRows[i].cells["yearWork"].childNodes[0].value;
					rwHealth.periodWork = oRows[i].cells["periodWork"].childNodes[0].value;
					rwHealth.totalHour = oRows[i].cells["totalHour"].childNodes[0].value;
					rwHealth.seqData = oRows[i].cells["seqData"].childNodes[0].value;
					
					//alert('start : ' + oRows[i].cells["start"].childNodes[0].childNodes.length);
					
					
					
					
					rwHealth.confirmFlag = 'N';
					rwHealth.approveFlag = 'N';
					rwHealth.updBy = '<%=userId%>';
					rwHealth.creBy = '<%=userId%>';
					rwHealth.creDate = getDateFromFormat(<%=date%>,"dd/MM/yyyy");
					//Service.addList(RwHealth, {callback:onInsertCallback});
					
						if( i == (num + 1) )
							FeeWpayRwHealthService.addList(rwHealth, true, {callback:ClearData,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้');}});
						else
							FeeWpayRwHealthService.addList(rwHealth, false);
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
		
		var x=document.getElementById("incDecCbo")
		//alert(x.selectedIndex)
		
		//alert(document.forms['searchForm'].elements['incDecCbo'].options[x.selectedIndex].text);
		var str = document.forms['searchForm'].elements['incDecCbo'].value;
		
		//if(str == 'เงินเพิ่มพิเศษวิชาชีพเฉพาะ'){
		if(str == '08' && !<c:out value='${ConfirmRwHealth}' />){
			document.forms['searchForm'].elements['generate'].disabled = false;
		}else{
			document.forms['searchForm'].elements['generate'].disabled = true;
		}
		
		
		
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
		
		if(amt != null && amt != ''){
			if(strIncDec == '08'){
				if(amt == '800'){
					PrIncomeDeductService.findMaxIncDecCode('<%=ouCode%>','1',strIncDec, {callback:whenCheckAmtTotalCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
				}else if(amt == '1600'){
					PrIncomeDeductService.findMaxIncDecCode('<%=ouCode%>','1',strIncDec, {callback:whenCheckAmtTotalCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
				}else if(amt == '2400'){
					PrIncomeDeductService.findMaxIncDecCode('<%=ouCode%>','1',strIncDec, {callback:whenCheckAmtTotalCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
				}else if(amt == '3200'){
					PrIncomeDeductService.findMaxIncDecCode('<%=ouCode%>','1',strIncDec, {callback:whenCheckAmtTotalCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
				}else if(amt == '0'){
					PrIncomeDeductService.findMaxIncDecCode('<%=ouCode%>','1',strIncDec, {callback:whenCheckAmtTotalCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
				}else{
					alert('จำนวนเงินไม่ถูกต้อง');
					oRows[lRowNumber].cells["totalAmt"].childNodes[0].value = '';
					oRows[lRowNumber].cells["totalAmt"].childNodes[0].focus();
				
				}
			
			}else{
			
					FeeWpayPrIncomeDeductService.findMaxIncDecCode('<%=ouCode%>','1',strIncDec, {callback:whenCheckAmtTotalCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
	
			}
		
		}else{
			alert('กรุณากรอกจำนวนเงิน');
			oRows[lRowNumber].cells["totalAmt"].childNodes[0].focus();
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
	
	function whenCheckAmtTotalCallback(data){
		
		if(data != null && data != '' && data != '0'){
		
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
		
		var totalAmtValue = totalAmt[rowModifyAMT].value;
		
		var strIncDec = document.forms['searchForm'].elements['incDecCbo'].value;

		
		if(totalAmtValue != null && totalAmtValue != ''){
			if(strIncDec == '08'){
				if(totalAmtValue == '800'){
					PrIncomeDeductService.findMaxIncDecCode('<%=ouCode%>','1',strIncDec, {callback:whenCheckAmtTotalInRowUpdateCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
				}else if(totalAmtValue == '1600'){
					PrIncomeDeductService.findMaxIncDecCode('<%=ouCode%>','1',strIncDec, {callback:whenCheckAmtTotalInRowUpdateCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
				}else if(totalAmtValue == '2400'){
					PrIncomeDeductService.findMaxIncDecCode('<%=ouCode%>','1',strIncDec, {callback:whenCheckAmtTotalInRowUpdateCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
				}else if(totalAmtValue == '3200'){
					PrIncomeDeductService.findMaxIncDecCode('<%=ouCode%>','1',strIncDec, {callback:whenCheckAmtTotalInRowUpdateCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
				}else if(totalAmtValue == '0'){
					PrIncomeDeductService.findMaxIncDecCode('<%=ouCode%>','1',strIncDec, {callback:whenCheckAmtTotalInRowUpdateCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
				
				}else{
					alert('จำนวนเงินไม่ถูกต้อง');
					totalAmt[rowModifyAMT].value = '';
					totalAmt[rowModifyAMT].focus();
				
				}
			
			}else{
				FeeWpayPrIncomeDeductService.findMaxIncDecCode('<%=ouCode%>','1',strIncDec, {callback:whenCheckAmtTotalInRowUpdateCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
	
			}
		
		}else{
			alert('กรุณากรอกจำนวนเงิน');
			totalAmt[rowModifyAMT].focus();
		}
		
		 }
	}
	
	function whenCheckAmtTotalInRowUpdateCallback(data){
		
		if(data != null && data != '' && data != '0'){
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
			oRows[lRowNumber].cells["salary"].childNodes[0].value = '';
			oRows[lRowNumber].cells["salary"].childNodes[1].value = '';
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
			var salary      = frm.elements["salary"];
			var codeSeq 	= frm.elements["codeSeq"];
			var flagPr 		= frm.elements["flagPr"];
			var yearWork 	= frm.elements["yearWork"];
			var periodWork 	= frm.elements["periodWork"];
			var totalHour 	= frm.elements["totalHour"];
			var seqData 	= frm.elements["seqData"];
			
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
				salary[rowModify].value = '';
				codeSeq[rowModify].value = '';
			}
			
		}
	 	
	 }
	 
	 function whenFetchEmployeeDetailInRowUpdate(empCode)
	 {
		DWRUtil.useLoadingMessage("Loading ...");
		//alert(empCode+' : '+<%=ouCode%>+' : ' + DWRUtil.getValue("year")+' : '+ DWRUtil.getValue("period"))
		FeeWpayPnEmployeeService.findByEmpCodeDetail('<%=userId%>',empCode, '<%=ouCode%>', parseInt(DWRUtil.getValue("year")), parseInt(DWRUtil.getValue("period")), {callback:whenFetchEmployeeDetailInRowUpdateCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร555');}});
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
			var salary      = frm.elements["salary"];
			var codeSeq 	= frm.elements["codeSeq"];
			var flagPr 		= frm.elements["flagPr"];
			var yearWork 	= frm.elements["yearWork"];
			var periodWork 	= frm.elements["periodWork"];
			var totalHour	= frm.elements["totalHour"];
			var seqData 	= frm.elements["seqData"];
			
			if(data.empCode != null && data.empCode != ''){
				name[rowModify].value = data.name;
				salary[rowModify].value = data.salary;
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
		FeeWpayPnEmployeeService.findByEmpCodeDetail('<%=userId%>',empCode, '<%=ouCode%>', parseInt(DWRUtil.getValue("year")), parseInt(DWRUtil.getValue("period")), {callback:whenFetchEmployeeDetailInRowCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร111');}});
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
					oRows[lRowNumber].cells["salary"].childNodes[0].value = data.salary
					oRows[lRowNumber].cells["salary"].childNodes[1].value = data.codeSeqWork
		}else{
			oRows[lRowNumber].cells["name"].childNodes[0].value = '';
			oRows[lRowNumber].cells["name"].childNodes[1].value = '';
			oRows[lRowNumber].cells["salary"].childNodes[0].value = '';
			oRows[lRowNumber].cells["salary"].childNodes[1].value = '';
			alert('เลขประจำตัวไม่ถูกต้อง');
			oRows[lRowNumber].cells["empCode"].childNodes[0].focus();	
		}
	 }
	 function backForm(){
		 window.history.back();
	 }
	
	
</script>
<%
	Calendar now = Calendar.getInstance(Locale.US);
	
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
			[ CTWPAYMT006 ] บันทึกเงินบำรุงสุขภาพ
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

<table  width="800" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td>
		<div style="height:320px;width:800;overflow:auto;vertical-align: top;" align="center" >
		<table id="table" width="750"  border="1" bordercolor="#6699CC" cellpadding="0" cellspacing="0">
			<thead style="text-align: center">
				<tr CLASS="TABLEBULE2" style="height: 30px;">
					<th CLASS="TABLEBULE2" style="width:40px"  align="center">ลบ</th>
					<th CLASS="TABLEBULE2" align="center">ประเภทรายการ</th>
					<th CLASS="TABLEBULE2" style="width:100px" align="center">เลขประจำตัว</th>
					<th CLASS="TABLEBULE2" style="width:200px" align="center">ชื่อ - นามสกุล</th>
					<th CLASS="TABLEBULE2" style="width:100px" align="center">ค่าจ้าง</th>
					<th CLASS="TABLEBULE2" colspan="2" align="center">ปี/งวดที่ทำงาน</th>
					<th CLASS="TABLEBULE2" style="width:100px" align="center"><center>จำนวนชั่วโมง</center></th>
					<th CLASS="TABLEBULE2" style="width:50px" align="center">ลำดับที่</th>
				</tr>
				
			</thead>
			<tbody id="dataTable">
			</tbody>
			<tr id="temprow" style="visibility:hidden;position:absolute">
				<td id="flag"  ><input type="checkbox" name="chk"  style="width:100%"/></td>
				<td id="flagPr" width="50" >
					<select  name="type" >
						<option value="N"  >ปกติ</option>
						<option value="A" >ปรับปรุงรายการรับ</option>
						<option value="R"  >รายการรับเรียกคืน</option>
				</select>
			</td>
			<td id="empCode" align="center"><input type="text" maxlength="6" name="empCode" style="width:100%" onchange="whenSelectEmpOptionInRow(this);"  onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"></td>
			<td id="name" align="center"><input type="text"  name="name" readonly="readonly" style="width:100%;background-color:silver;"/><input type="hidden"  name="codeSeq" /></td>
			<td id="salary" align="center"><input type="text"  name="salary" readonly="readonly" style="width:100%;background-color:silver;"/><input type="hidden"  name="codeSeq" /></td>
			<td id="yearWork" align="center"><input type="text" size="6" name="yearWork" value="<c:out value='${DefaultYearAndSection.year}' />" maxlength="4" align="center" style="text-align:center;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
			<td id="periodWork" align="center" ><select name="periodWork">
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
			
			</td>
			<td id="totalHour" ><input type="text"  name="totalHour"  maxlength="3" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
			<td id="seqData" align="center"><input type="text"  name="seqData"  maxlength="3" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/><input type="hidden"  name="keySeq" /></td>
		</tr>
	</table>
	</div>
	</td>
  </tr>
</table>
<table border="0" width="800" align="center">
	<tr>
		<td>
			<table border="0" width="" align="left">
				<tr>
					
				</tr>
			</table>
		</td>	
	</tr>
</table>
	<BR/>
				<table width="770" align="center"  cellpadding="2" cellspacing="0" >
					<tr>
						<td align="right">
							<input type="hidden" name="page" value="<%=pageEdit%>">
							<input type="hidden" name="maxPage">
							<input type="hidden" name="countData" >
							<input type="hidden" name="dataPerPage" value="10">
							<input type="button" disabled="disabled" class=" button " value="First" name="first" onclick="onFirst(whenShowDataTable);"/>
							<input type="button" disabled="disabled" class=" button " value="<<" name="previous" onclick="onPrevious(whenShowDataTable);"/>
							<input type="text"  name="showPage" style="text-align:right;width: 40;" onkeyup="onCheckPageNAN(this.value);" onchange="onChangeGoPage(whenShowDataTable);" onkeypress="onKeyGoPage(event,whenShowDataTable);"/>
							/
							<input type="text"  name="showMaxPage" readonly="readonly" style="width: 40;border-style : none;background-color : transparent;text-align:right;font-weight:bold;"/>
							<input type="button" disabled="disabled" class=" button " value=">>" name="next" onclick="onNext(whenShowDataTable);" />
							<input type="button" disabled="disabled" class=" button " value="Last" name="last" onclick="onLast(whenShowDataTable);"/>
						</td>
					</tr>
				</table>
<table width="100%" CLASS="TABLEBULE2" >
	<tr CLASS="TABLEBULE2" >
		<td align="left" >&nbsp;
			<input type="Button" class=" button " value="เพิ่มข้อมูล" id="insertData" name="insertData" onclick="addVisualRow();"/>						
			<input type="Button" class=" button " value="ลบข้อมูล" id="deleteData" name="deleteData" onclick="removeVisualRow();"/>						
			<input type="Button" class=" button " value="ตกลง" id="confirmData" name="confirmData" onclick="onUpdate();"/>
			<input type="Button" class=" button " value="ออก" id="gotoTree" name="gotoTree" onclick="backForm();"/>
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
									rwHealth.keySeq = parseInt(frm.elements["keySeq"][i].value);
									FeeWpayRwHealthService.deleteRwHealth(rwHealth, {callback:onDeleteCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
									chDelete = true;
								}
							
								
						}
					}else{
						if(tab.rows.length==1){
							row = tab.rows[0];	
							if (chk[0].checked){
									
									//alert('CC'+rowDelete);
									rwHealth.keySeq = parseInt(frm.elements["keySeq"][0].value);
									FeeWpayRwHealthService.deleteRwHealth(rwHealth, {callback:onDeleteCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
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
		whenShowDataTable();
	}
	
	function DeletCompleaseData(){
		if(parseInt(DWRUtil.getValue("dataLength")) != null && parseInt(DWRUtil.getValue("dataLength")) > 0){
			whenShowDataTable();
		}
	}
	
	

//-->
</SCRIPT>
