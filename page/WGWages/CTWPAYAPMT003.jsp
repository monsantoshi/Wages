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
	String pageEdit = request.getParameter("pageEdit")==null?"0":request.getParameter("pageEdit");
	java.util.Date dd = new java.util.Date();
	java.text.SimpleDateFormat fmd = new java.text.SimpleDateFormat("dd/MM/yyyy",new java.util.Locale("th","TH"));
	String date = fmd.format(dd);
	
	com.ss.tp.dto.DefaultYearSectionVO defaltYear = (com.ss.tp.dto.DefaultYearSectionVO)request.getSession().getAttribute("DefaultYearAndSection");
	String periodSession = defaltYear.getPeriod();
	
	java.util.Calendar cc = new java.util.GregorianCalendar();
	int x = cc.get(java.util.Calendar.MONTH)+1 ;
	int y = cc.get(java.util.Calendar.YEAR);
	int xx = (Integer.parseInt(periodSession))/2;
	String periodWork = String.valueOf(xx-1);
	String approveF ="";
	
	if (request.getSession().getAttribute("processResult") != null){
		ProcessResult processResult = (ProcessResult) request.getSession().getAttribute("processResult");
	}
	System.out.println("ConfirmRwOvertime : " + session.getAttribute("ConfirmRwOvertime").getClass().getName());
	String confirm = (String)session.getAttribute("ConfirmRwOvertime");
	System.out.println("ConfirmRwOvertime : " + confirm);
	
	if(periodWork.equals("0")){
		periodWork = "12";
		String year = String.valueOf(y-1);
	}else{
	    String year = String.valueOf(y);
	}
	
%>
<html>
<head>
<title>บันทึกเงินเพิ่มพิเศษเป็นกะ</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="script/payroll_util.js"></script>
<!-- Javascript Script File -->
<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrPeriodLineService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPnEmployeeService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayRwOvertimeService.js"></SCRIPT>
<script type="text/javascript" src="dwr/interface/FeeWpayRwIncDecOtherService.js"></script>
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
		$("confirm").value = "<c:out value='${ConfirmRwOvertime}' /> "  ;//data.confirm;
		chkMainClose();
		
		 if(<c:out value='${ConfirmRwOvertime}' />){
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
			    //alert("chkmainclose");
			    //alert($("year").value);
			    //alert($("period").value);
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
			
	
			//alert('query');
			// Query by Criteria
			FeeWpayRwOvertimeService.findByCriteriaListApprove
			(
					'<%=userId%>',
					orgFromCbo,
					orgToCbo,
					empFromCbo,
					empToCbo,
					document.forms['searchForm'].elements['otType'].value,
					document.forms['searchForm'].elements['orderFromCbo'].value,
					document.forms['searchForm'].elements['orderToCbo'].value,
					document.forms['searchForm'].elements['refNoFrom'].value,
					document.forms['searchForm'].elements['refNoTo'].value,
					'<%=ouCode%>',
					parseInt(DWRUtil.getValue("year")),
					parseInt(DWRUtil.getValue("period")),
					page,
					DWRUtil.getValue("dataPerPage"),
				{callback:whenListDataTableHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}}
			);
		
	}
	
	var cellFuncs = [
		function(data) { return writeSelect("flagPr",data.flagPr,data.keySeq);},
		function(data) { return writeTextID("empCode",data.empCode,10,"left",data.keySeq);},
		function(data) { return writeTextDisplay("name",data.name,200,"left","codeSeq",data.codeSeq);},
		function(data) { return writeTextDisplay("orgDesc",data.orgDesc,200,"left","orgDesc",data.orgDesc);},
		function(data) { return writeTextDisplay("refNo",data.refNo,200,"left","refNo",data.refNo);},
		function(data) { return writeSelectWorkHour("workHour",data.workHour,data.keySeq);},
		function(data) { return writeTextDisplay("startDate",data.startDateTemp,200,"center","startDate",data.startDateTemp);},
		function(data) { return writeTextDisplay("endDate",data.endDateTemp,200,"center","endDate",data.endDateTemp);},
		function(data) { return writeText("totDay1",data.totDay1,4,"center",data.keySeq);},
		function(data) { return writeText("totDay15",data.totDay15,4,"center",data.keySeq);},
		function(data) { return writeText("totDay3",data.totDay3,4,"center",data.keySeq);},
		function(data) { return writeText("amtDay1",data.amtDay1,4,"center",data.keySeq);},
		function(data) { return writeText("amtDay15",data.amtDay15,4,"center",data.keySeq);},
		function(data) { return writeText("amtDay3",data.amtDay3,4,"center",data.keySeq);},
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
		//if(<c:out value='${ConfirmRwPremiem}' />){
		return "<div align='center' style='background-color:#CCCCCC;'><select name='"+inname+"' disabled='true' style='background-color:transparent;color:#000000'><option value='N' "+temp1+" >ปกติ</option>"+
												"<option value='A' "+temp2+">ปรับปรุงรายการรับ</option>" +
												"<option value='R' "+temp3+">รายการรับเรียกคืน</option></select></div>";
		//}else {
		//return "<div align='center' ><select onchange='addListUpdate("+key+")' name='"+inname+"'><option value='N' "+temp1+" >ปกติ</option>"+
		//										"<option value='A' "+temp2+">ปรับปรุงรายการรับ</option>" +
		//										"<option value='R' "+temp3+">รายการรับเรียกคืน</option></select></div>";
		//
		
		//}
	}
	
	function writeSelectWorkHour(inname,emp,key)
	{
		var temp1 = '';
		var temp2 = '';
		var temp3 = '';
	
		if(emp=='6'){
			temp1 = 'selected';
		} else if(emp=='6.5'){
			temp2 = 'selected';		
		}else if(emp=='7'){
			temp3 = 'selected';		
		}
		//if(<c:out value='${ConfirmRwPremiem}' />){
		return "<div align='center' style='background-color:#CCCCCC;'><select name='"+inname+"' disabled='true' style='background-color:transparent;color:#000000'><option value='6' "+temp1+" >6</option>"+
												"<option value='6.5' "+temp2+">6.5</option>" +
												"<option value='7' "+temp3+" >7</option></select></div>";
		//}else {
		//return "<div align='center' ><select onchange='addListUpdate("+key+")' name='"+inname+"'><option value='N' "+temp1+" >ปกติ</option>"+
		//										"<option value='A' "+temp2+">ปรับปรุงรายการรับ</option>" +
		//										"<option value='R' "+temp3+">รายการรับเรียกคืน</option></select></div>";
		//
		
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
		
		//if(<c:out value='${ConfirmRwPremiem}' />){
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
	
	function writeText(inname,emp,maxlength,textalign,key)
	{
		//กรณี display not edit
		//if(<c:out value='${ConfirmRwPremiem}' />){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		//}else{
		//	return "< align='center' ><input type='text' name = '"+inname+"' onChange='addListUpdate("+key+");'  value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
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
	
	function writeTextDisplay(inname,emp,maxlength,textalign,nameSeq,codeseq)
	{
		return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' /><input type='hidden' name = '"+nameSeq+"' value='"+codeseq+"'  /></div>";
	}
	
	function writeTextID(inname,emp,maxlength,textalign,key)
	{
		//กรณี display not edit
		//if(<c:out value='${ConfirmRwPremiem}' />){																	
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onchange='whenSelectEmpOptionInRow();'/></div>";
		//}else{
		//	return "<div align='center' ><input type='text' name = '"+inname+"' maxlength='6' onChange='whenSelectEmpOptionInRowUpdate("+key+");addListUpdate("+key+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;'  onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		//}
	}
	
	function writeTextYearWork(inname,emp,maxlength,textalign,key)
	{
		//กรณี display not edit
		//if(<c:out value='${ConfirmRwPremiem}' />){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' size='6' maxlength='"+maxlength+"' style='text-align:"+textalign+";background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		//}else{
		//	return "<div align='center' ><input type='text' name = '"+inname+"' onchange='addListUpdate("+key+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' size='6' style='text-align:"+textalign+";' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		//}
	}
	
	function writeHidden(inname,emp,size,maxlength,textalign,nameHide,empHide)
	{
		//if(<c:out value='${ConfirmRwPremiem}' />){
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

	
	
	var feeWpayRwOvertime = {keySeq:null, ouCode:null, yearPr:null, periodPr:null,
			empCode:null,yearWork:null,periodWork:null,codeSeq:null,
			otType:null,refNo:null,fiscalYear:null,startDate:null,endDate:null,flagWork:null,confirmFlag:null,
		 	totDay15:null,totDay1:null,totDay3:null,amtDay15:null,amtDay1:null,amtDay3:null,flagPr:null,seqData:null,updBy:null,updDate:null,creBy:null,creDate:null,workHour:null,approveFlag:null};
		 
	var allRowUpdate = 0;
	
	function onUpdate(){
		
		var table = document.getElementById("table");
		var aRows = table.rows;
		var num =  2 + parseInt(DWRUtil.getValue("dataLength"));
		
		var empNull = true;
		canSave = true ;
		var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["searchForm"];
		
		
		
		if(parseInt(DWRUtil.getValue("dataLength")) != null && parseInt(DWRUtil.getValue("dataLength")) > 0){
			num = 2 + parseInt(DWRUtil.getValue("dataLength"));
		}else{
			num = 2 ;
		}
		
	
		for(var a=aRows.length-1;a > num;a--){
				if (aRows[a].cells["empCode"].childNodes[0].value == null || aRows[a].cells["empCode"].childNodes[0].value == '' ){
					empNull = false;
					break;
				}
						
		}
		
		
		if(tab.rows.length > 0){
		
			var keySeq 		= frm.elements["keySeq"];
			var empCode 	= frm.elements["empCode"];
			var name		= frm.elements["name"];
			var codeSeq 	= frm.elements["codeSeq"];
			var otType      = frm.elements["otType"];
			var refNo       = frm.elements["refNo"];
			var flagPr 		= frm.elements["flagPr"];
			var startDate   = frm.startDate["startDate"];
			var endDate     = frm.endDate["endDate"];
			var amtDay1 	= frm.elements["amtDay1"];
			var amtDay15 	= frm.elements["amtDay15"];
			var amtDay3 	= frm.elements["amtDay3"];
			var totDay1 	= frm.elements["totDay1"];
			var totDay15 	= frm.elements["totDay15"];
			var totDay3 	= frm.elements["totDay3"];
			var workHour    = frm.elements["workHour"];
			var seqData 	= frm.elements["seqData"];
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
						feeWpayRwOvertime.keySeq = parseInt(keySeq[i].value);
						if(tab.rows.length == 1){
							feeWpayRwOvertime.flagPr = flagPr.value;
						}else{
							feeWpayRwOvertime.flagPr = flagPr[i].value;
						}
						if (flagPr[i].value != '' && flagPr[i].value != null){
							feeWpayRwOvertime.flagPr = flagPr.value;
						}else{
							feeWpayRwOvertime.flagPr = flagPr[i].value;
						}
						if (empCode[i].value != '' && empCode[i].value != null){
							feeWpayRwOvertime.empCode  = empCode[i].value;
						}
						else{
							feeWpayRwOvertime.empCode  = null;
						}
						
						if (name[i].value != '' && name[i].value != null){
							feeWpayRwOvertime.name  = name[i].value;
						}
						else{
							feeWpayRwOvertime.name  = null;
						}
						feeWpayRwOvertime.codeSeq = codeSeq[i].value;
					
						if (workHour[i].value != ''&& workHour[i].value != null){
							feeWpayRwOvertime.workHour  = workHour[i].value;
						}
						else{
							feeWpayRwOvertime.workHour  = null;
						}
						
						if (refNo[i].value != ''&& refNo[i].value != null){
							feeWpayRwOvertime.refNo  = refNo[i].value;
						}
						else{
							feeWpayRwOvertime.refNo  = null;
						}
						
						if (amtDay1[i].value !=''){
							feeWpayRwOvertime.amtDay1  = parseInt(amtDay1[i].value);
						}
						else{
							feeWpayRwOvertime.amtDay1  = null;
						}
			
						if (amtDay15[i].value !=''){
							feeWpayRwOvertime.amtDay15 = parseInt(amtDay15[i].value);
						}
						else{
							feeWpayRwOvertime.amtDay15  = null;
						}
			
						if (amtDay3[i].value !=''){
							feeWpayRwOvertime.amtDay3  = parseInt(amtDay3[i].value);
						}
						else{
							feeWpayRwOvertime.amtDay3  = null;
						}
			
						if (totDay1[i].value !=''){
							feeWpayRwOvertime.totDay1  = parseInt(totDay1[i].value);
						}
						else{
							feeWpayRwOvertime.totDay1  = null;
						}
			
						if (totDay15[i].value !=''){
							feeWpayRwOvertime.totDay15  = parseInt(totDay15[i].value);
						}
						else{
							feeWpayRwOvertime.totDay15  = null;
						}
			
						if (totDay3[i].value !=''){
							feeWpayRwOvertime.totDay3  = parseInt(totDay3[i].value);
						}
						else{
							feeWpayRwOvertime.totDay3  = null;
						}
						
						if (seqData[i].value !='' && seqData[i].value != null){
							feeWpayRwOvertime.seqData  = parseInt(seqData[i].value);
						}
						else{
							feeWpayRwOvertime.seqData  = null;
						}
						
						
						//if (approveF[i].value != '' && approveF[i].value != null){
							feeWpayRwOvertime.approveFlag  = approveF[i].value;
						//}
						//else{
						//	feeWpayRwOvertime.approveFlag  = null;
						//}
						
						feeWpayRwOvertime.approveBy = '<%=userId%>';
						feeWpayRwOvertime.approveDate = getDateFromFormat(sed,"dd/MM/yyyy");
						
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
									FeeWpayRwOvertimeService.addListApprove(feeWpayRwOvertime, false, {callback:onInsertCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
								else
									FeeWpayRwOvertimeService.addListApprove(feeWpayRwOvertime, false);
					
								}else{
								if( allRowUpdate == myUpdate.length )
									FeeWpayRwOvertimeService.addListApprove(feeWpayRwOvertime, true, {callback:ClearData,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้');}});
								else
									FeeWpayRwOvertimeService.addListApprove(feeWpayRwOvertime, false);
								
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
				num = 2+parseInt(DWRUtil.getValue("dataLength"));
			}else{
				num = 2;
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
			num = 2+ parseInt(DWRUtil.getValue("dataLength"));
		}else{
			num = 2 ;
		}
		var oRows = table.rows;
		var insertStatus = false;
		if(tdName == null)tdName="flag";
		if(chkName == null)chkName="chk";
		
		for(var c=oRows.length-1;c > num;c--){
				if (oRows[c].cells["empCode"].childNodes[0].value == null || oRows[c].cells["empCode"].childNodes[0].value == '' ){
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
					feeWpayRwOvertime.keySeq = null;
					feeWpayRwOvertime.ouCode = '<%=ouCode%>';
					feeWpayRwOvertime.yearPr = parseInt($("year").value);
					feeWpayRwOvertime.periodPr = parseInt($("period").value);
					feeWpayRwOvertime.empCode = oRows[i].cells["empCode"].childNodes[0].value;
					feeWpayRwOvertime.name = oRows[i].cells["name"].childNodes[0].value;
					//feeWpayRwOvertime.codeSeq = oRows[i].cells["orgCode"].childNodes[1].value;
					feeWpayRwOvertime.flagPr = oRows[i].cells["flagPr"].childNodes[0].value;
					feeWpayRwOvertime.otType = parseInt($("otType").value);
					feeWpayRwOvertime.refNo =  oRows[i].cells["refNo"].childNodes[0].value;
					feeWpayRwOvertime.workHour =  oRows[i].cells["workHour"].childNodes[0].value;
					feeWpayRwOvertime.startDate= getDateFromFormat(oRows[i].cells["startDate"].childNodes[0].value,"dd/MM/yyyy");
					feeWpayRwOvertime.endDate = getDateFromFormat(oRows[i].cells["endDate"].childNodes[0].value,"dd/MM/yyyy");
					if(oRows[i].cells["amtDay1"].childNodes[0].value != ''){
						feeWpayRwOvertime.amtDay1 = oRows[i].cells["amtDay1"].childNodes[0].value;
					}else{
						feeWpayRwOvertime.amtDay1 = null;
					}
					if(oRows[i].cells["amtDay15"].childNodes[0].value != ''){
						feeWpayRwOvertime.amtDay15 = oRows[i].cells["amtDay15"].childNodes[0].value;
					}else{
						feeWpayRwOvertime.amtDay15 = null;
					}	
					if(oRows[i].cells["amtDay3"].childNodes[0].value != ''){
						feeWpayRwOvertime.amtDay3 = oRows[i].cells["amtDay3"].childNodes[0].value;
					}else{
						feeWpayRwOvertime.amtDay3 = null;
					}	
					if(oRows[i].cells["totDay1"].childNodes[0].value != ''){
						feeWpayRwOvertime.totDay1 = oRows[i].cells["totDay1"].childNodes[0].value;
					}else{
						feeWpayRwOvertime.totDay1 = null;
					}	
					if(oRows[i].cells["totDay15"].childNodes[0].value != ''){
						feeWpayRwOvertime.totDay15 = oRows[i].cells["totDay15"].childNodes[0].value;
					}else{
						feeWpayRwOvertime.totDay15 = null;
					}	
					if(oRows[i].cells["totDay3"].childNodes[0].value != ''){
						feeWpayRwOvertime.totDay3 = oRows[i].cells["totDay3"].childNodes[0].value;
					}else{
						feeWpayRwOvertime.totDay3 = null;
					}	
										
					feeWpayRwOvertime.seqData = oRows[i].cells["seqData"].childNodes[0].value;
					
					//alert('start : ' + oRows[i].cells["start"].childNodes[0].childNodes.length);
					
					
					
					
					feeWpayRwOvertime.approveFlag = oRows[i].cells["approveF"].childNodes[0].value;
					feeWpayRwOvertime.approveBy = '<%=userId%>';
					feeWpayRwOvertime.approveDate = getDateFromFormat(<%=date%>,"dd/MM/yyyy");
					//RwIncDecOtherService.addList(rwIncDecOther, {callback:onInsertCallback});
					
						if( i == (num + 1) )
							FeeWpayRwOvertimeService.addListApprove(feeWpayRwOvertime, true, {callback:ClearData,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้');}});
						else
							FeeWpayRwOvertimeService.addListApprove(feeWpayRwOvertime, false);
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
	
	function disabledButton(){
		document.getElementById("confirmData").disabled = true;
		//document.getElementById("insertData").disabled = true;
		//document.getElementById("deleteData").disabled = true;
	}
	
	function enabledButton(){
		document.getElementById("confirmData").disabled = false;
		//document.getElementById("insertData").disabled = false;
		//document.getElementById("deleteData").disabled = false;
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
			
			var seqData 	= frm.elements["seqData"];
			
			if(data.empCode != null && data.empCode != ''){
				name[rowModify].value = data.name;
				codeSeq[rowModify].value = data.codeSeq;
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
					oRows[lRowNumber].cells["nameOrg"].childNodes[0].value = checkNull(data.orgDesc,'STRING');
					oRows[lRowNumber].cells["nameOrg"].childNodes[1].value = 'ในกอง';
					$("codeSeq").value = data.codeSeq;
		}else{
			oRows[lRowNumber].cells["name"].childNodes[0].value = '';
			oRows[lRowNumber].cells["name"].childNodes[1].value = '';
			oRows[lRowNumber].cells["nameOrg"].childNodes[0].value = '';
			alert('เลขประจำตัวไม่ถูกต้อง');
			oRows[lRowNumber].cells["empCode"].childNodes[0].focus();	
		}
	 }
	 function countData()
		{	
			myUpdate = new Array();
		
				var orgFromCbo = '';
				var orgToCbo = '';
				var	empFromCbo = '';
				var	empToCbo = '';
				var	workFromCbo = '';
				var	workToCbo = '';
				var	orderFromCbo = '';
				var	orderToCbo = '';
				
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
				FeeWpayRwOvertimeService.countDataApprove
				(
						'<%=userId%>',
						orgFromCbo,
						orgToCbo,
						empFromCbo,
						empToCbo,
						document.forms['searchForm'].elements['otType'].value,
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
	 function changeValue(){
		 	var table = document.getElementById("table");
				var tdName;
				var chkName;
				
				var oRows = table.rows;
				if(tdName == null)tdName="flag";
				if(chkName == null)chkName="chk";
				if(document.forms['searchForm'].elements['otType'].value == '2'){
					oRows[2].cells["amtDay1"].childNodes[0].disabled = true;
					oRows[2].cells["amtDay15"].childNodes[0].disabled = true;
					oRows[2].cells["amtDay3"].childNodes[0].disabled = true;
					
					oRows[2].cells["amtDay1"].childNodes[0].style.backgroundColor = 'silver';
					oRows[2].cells["amtDay15"].childNodes[0].style.backgroundColor = 'silver';
					oRows[2].cells["amtDay3"].childNodes[0].style.backgroundColor = 'silver';	
				}else{
					oRows[2].cells["amtDay1"].childNodes[0].disabled = false;
					oRows[2].cells["amtDay15"].childNodes[0].disabled = false;
					oRows[2].cells["amtDay3"].childNodes[0].disabled = false;
					
					
					oRows[2].cells["amtDay1"].childNodes[0].style.backgroundColor = '';
					oRows[2].cells["amtDay15"].childNodes[0].style.backgroundColor = '';
					oRows[2].cells["amtDay3"].childNodes[0].style.backgroundColor = '';	
						
				}
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
			[ CTWPAYAPMT003 ] บันทึกรับรองค่าล่วงเวลา ค่าทำงานในวันหยุดพักผ่อน (ลูกจ้าง)
		</td>
	</tr>
</table>
<table width="770" border="0" align="center" cellspacing="1">

  	 <tr>
  	 <td colspan="4">
		  <table border="0" width="100%"><tr>
		    <td class="font-field" align="right" width="146px">ประจำปี&nbsp;</td>
		    <td align="left">&nbsp;<input type="text" name="year"   value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/></td>
		    
		   
				    <td  class="font-field" align="right" width="75px">งวด&nbsp;</td>
				    <td><input type="text" name="section"  value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/></td>
		    		<td class="font-field" align="right" colspan="2">ประเภทของงาน&nbsp;</td>
					    <td align="left" colspan="2">
					    	<select name="otType" style="width:200">
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
    <td><input type="Button" value="ค้นหา" class=" button " onclick="whenShowDataTable();" /></td>
  </tr>
</table>

<br/>

<table  width="800" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td>
		<div style="height:320px;width:1500;overflow:auto;vertical-align: top;" align="center" >
		<table id="table" lign="center" style="text-align: center;width:1500px;"  border="1" bordercolor="#6699CC" cellpadding="0"  cellspacing="0">
			<thead style="text-align: center">
				<tr CLASS="TABLEBULE2" style="height: 30px;">
					<th CLASS="TABLEBULE2" style="width:80px" rowspan="2">ประเภทรายการ</th>
					<th CLASS="TABLEBULE2" style="width:80px" rowspan="2">เลขประจำตัว</th>
					<th CLASS="TABLEBULE2" style="width:150px" rowspan="2">ชื่อ - นามสกุล</th>
					<th CLASS="TABLEBULE2" style="width:250px" rowspan="2" >สังกัดปฎิบัติงาน</th>
					<th CLASS="TABLEBULE2" style="width:150px" rowspan="2" >เลขที่ ล.1</th>
					<th CLASS="TABLEBULE2" style="width:80px"  rowspan="2" align="center">จำนวนชั่วโมงทำงานปกติ</th>
					<th CLASS="TABLEBULE2" style="width:150px"  colspan="2" rowspan="1" align="center"><center>วันที่ปฎิบัติงาน</center></th>
					<th CLASS="TABLEBULE2" style="width:180px"  colspan="3" align="center">จำนวนชั่วโมง</th>
					<th CLASS="TABLEBULE2" style="width:180px"  colspan="3" align="center">จำนวนชั่วโมง วันหยุดประจำปี</th>
					<th CLASS="TABLEBULE2" style="width:50px" rowspan="2" align="center">ลำดับที่</th>
					<th CLASS="TABLEBULE2" style="width:80px" rowspan="2" align="center">รับรอง</th>
				</tr>
				<tr CLASS="TABLEBULE2"  >
							<th CLASS="TABLEBULE2" style="width:100px"  align="center">เริ่มต้น </th>
							<th CLASS="TABLEBULE2" style="width:100px"  align="center">สิ้นสุด</th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">1.5 เท่า </th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">1 เท่า </th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">2 เท่า </th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">1.5 เท่า </th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">1 เท่า </th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">2 เท่า </th>
						
				</tr>
			</thead>
			<tbody id="dataTable">
			</tbody>
			<tr id="temprow" style="visibility:hidden;position:absolute">
				<td id="flagPr" width="30" >
					<select  name="type" >
						<option value="N"  >ปกติ</option>
						<option value="A" >ปรับปรุงรายการรับ</option>
						<option value="R"  >รายการรับเรียกคืน</option>
				</select>
			</td>
			<td id="empCode" align="center"><input type="text" maxlength="6" name="empCode" style="width:100%" onchange="whenSelectEmpOptionInRow(this);"  onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/><input type="hidden" align="center" name="keySeq" /></td>
			<td id="name" align="center"><input type="text"  name="name" readonly="readonly" style="width:100%;background-color:silver;"/><input type="hidden"  name="codeSeq" /></td>
			<td id="nameOrg" align="center" ><input type="text"  name="nameOrg" readonly="readonly" style="width:100%;background-color:silver;"/><input type="text"  name="inKong" readonly="readonly" style="width:20%;background-color:silver;"/></td>
			<td id="refNo" align="center" ><input type="text"  name="refNo" readonly="readonly" style="width:100%;background-color:silver;"/><input type="text"  name="refNo" readonly="readonly" style="width:20%;background-color:silver;"/></td>
			<td id="workHour" width="100" >
					<select name="type" ;">
						<option value="6" >6</option>
						<option value="6.5" >6.5</option>
						<option value="7" >7</option>
					</select>
			</td>
			<td id="startDate" align="center" ><input type="text"  name="startDate"  maxlength="10" align="center" style="text-align:center;width:100%;" onchange="return validateDate(this) && compareStDate(this);" ></td>
			<td id="endDate" align="center" ><input type="text"   name="endDate"  maxlength="10" align="center" style="text-align:center;width:100%;" onchange="return validateDate(this) && compareEndDate(this);"  ></td>
			<td id="totDay15" align="center" ><input type="text"  name="totDay15"  maxlength="3" align="center" style="text-align:right;width:100%;"  onchange="return checkHour(this);"  onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
			<td id="totDay1" align="center" ><input type="text"  name="totDay1"  maxlength="3" align="center" style="text-align:right;width:100%;"   onchange="return checkHour(this);" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" /></td>
			<td id="totDay3" align="center" ><input type="text"  name="totDay3"  maxlength="3" align="center" style="text-align:right;width:100%;"  onchange="return checkHour(this);" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
			<td id="amtDay15" align="center" ><input type="text"  name="amtDay15"  maxlength="3" align="center" style="text-align:right;width:100%;"   onchange="return checkHour(this);" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
			<td id="amtDay1" align="center" ><input type="text"  name="amtDay1"  maxlength="3" align="center" style="text-align:right;width:100%;" onchange="return checkHour(this);" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" /></td>
			<td id="amtDay3" align="center" ><input type="text"  name="amtDay3"  maxlength="3" align="center" style="text-align:right;width:100%;"   onchange="return checkHour(this);" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
			<td id="seqData" align="center"><input type="text"  name="seqData" readonly="readonly"  maxlength="3" align="center" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/><input type="hidden"  name="keySeq" /></td>
			<td id="approveF" align="center" width="40">
				<select  name="type" >
						<option value="Y"  >รับรอง</option>
						<option value="N" >ไม่รับรอง</option>
				</select></td>	
		</tr>
	</table>
	</div>
	</td>
  </tr>
</table>
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
<table width="100%" CLASS="TABLEBULE2" >
	<tr CLASS="TABLEBULE2" >
		<td align="left" >&nbsp;
			<input type="Button" class=" button " value="ตกลง" id="confirmData" name="confirmData" onclick="onUpdate();"/>
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
			num = 2 + parseInt(DWRUtil.getValue("dataLength"));
		}else{
			num = 2;
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
									feeWpayRwOvertime.keySeq = parseInt(frm.elements["keySeq"][i].value);
									FeeWpayOvertimeService.deleteRwOvertime(feeWpayRwOvertime, {callback:onDeleteCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
									chDelete = true;
								}
							
								
						}
					}else{
						if(tab.rows.length==1){
							row = tab.rows[0];	
							if (chk[0].checked){
									
									//alert('CC'+rowDelete);
									feeWpayRwOvertime.keySeq = parseInt(frm.elements["keySeq"][0].value);
									FeeWpayRwOvertimeService.deleteRwOvertime(feeWpayRwOvertime, {callback:onDeleteCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
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
