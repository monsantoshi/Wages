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
	
	
	if(periodWork.equals("0")){
		periodWork = "12";
		String year = String.valueOf(y-1);
	}else{
	    String year = String.valueOf(y);
	}
	
%>
<html>
<head>
<title>�ѹ�֡�Թ����������繡�</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="script/payroll_util.js"></script>
<!-- Javascript Script File -->
<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrPeriodLineService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPnEmployeeService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayRwPremiumService.js"></SCRIPT>
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
		$("confirm").value = "<c:out value='${ConfirmRwPremiem}' /> "  ;//data.confirm;
		chkMainClose();
	
		//alert($("confirm").value +' ::: '+<c:out value='${DefaultYearAndSection.confirm}' /> );	
		//PnEmployeeService.findPrEmpBySecurity('<%=userId%>','<%=ouCode%>',$("year").value,$("period").value, {callback:onLoadEmployeeCallback});
		
		 if(<c:out value='${ConfirmRwPremiem}' />){
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
     	//SuUserOrganizationService.findOrganizationByUserIdAndOuCodeToOrgCode('<%=userId%>','<%=ouCode%>',orgCode , {callback:whenFetchOrganizationToCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
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
     	//PnEmployeeService.findPrToEmpBySecurity('<%=userId%>','<%=ouCode%>',empCode,parseInt($("year").value),parseInt($("period").value) , {callback:whenFetchEmployeeToCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
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
				FeeWpayPrPeriodLineService.findPeriodLine('<%=ouCode%>',$("year").value,$("period").value,'<%=userId%>' , {callback:whenchkMainCloseCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
		    }
			
			function whenchkMainCloseCallback(data){
				if(data.mainClose == 'Y'){
					alert('�������ö���Թ��õ����');
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
			FeeWpayRwPremiumService.findByCriteriaListApprove
			(
				'<%=userId%>',
				'<%=ouCode%>',
				parseInt(DWRUtil.getValue("year")),
				parseInt(DWRUtil.getValue("period")),
				orgFromCbo,
				orgToCbo,
				empFromCbo,
				empToCbo,
				DWRUtil.getValue("page"),,
				DWRUtil.getValue("dataPerPage"),
				{callback:whenListDataTableHandler,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}}
			);
		
	}
	
	var cellFuncs = [
		function(data) { return writeApp("approveF",data.approveFlag,data.keySeq);},
		function(data) { return writeSelect("flagPr",data.flagPr,data.keySeq);},
		function(data) { return writeTextID("empCode",data.empCode,10,"left",data.keySeq);},
		function(data) { return writeTextDisplay("name",data.name,200,"left","codeSeq",data.codeSeq);},
		function(data) { return writeTextYearWork("yearWork",data.yearWork,4,"center",data.keySeq);},
		function(data) { return writeSelectMonth("periodWork",data.periodWork,data.keySeq);},
		function(data) { return writeText("morDay",data.morDay,4,"right",data.keySeq);},
		function(data) { return writeText("morHour",data.morHour,4,"right",data.keySeq);},
		function(data) { return writeText("aftDay",data.aftDay,4,"right",data.keySeq);},
		function(data) { return writeText("aftHour",data.aftHour,4,"right",data.keySeq);},
		function(data) { return writeText("evnDay",data.evnDay,4,"right",data.keySeq);},
		function(data) { return writeText("evnHour",data.evnHour,4,"right",data.keySeq);},
		function(data) { return writeHidden("seqData",data.seqData,4,3,"right","keySeq",data.keySeq);}
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
													"<option value='Y' "+mm1+" >�Ѻ�ͧ</option>" +
												    "<option value='N' "+mm2+" >����Ѻ�ͧ</option>" +																							 							
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
		return "<div align='center' style='background-color:#CCCCCC;'><select name='"+inname+"' disabled='true' style='background-color:transparent;color:#000000'><option value='N' "+temp1+" >����</option>"+
												"<option value='A' "+temp2+">��Ѻ��ا��¡���Ѻ</option>" +
												"<option value='R' "+temp3+">��¡���Ѻ���¡�׹</option></select></div>";
		//}else {
		//return "<div align='center' ><select onchange='addListUpdate("+key+")' name='"+inname+"'><option value='N' "+temp1+" >����</option>"+
		//										"<option value='A' "+temp2+">��Ѻ��ا��¡���Ѻ</option>" +
		//										"<option value='R' "+temp3+">��¡���Ѻ���¡�׹</option></select></div>";
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
													"<option value='1' "+mm1+" >���Ҥ�</option>" +
													"<option value='2' "+mm2+">����Ҿѹ��</option>" +
													"<option value='3' "+mm3+">�չҤ�</option>" +
													"<option value='4' "+mm4+">����¹</option>" +
													"<option value='5' "+mm5+">����Ҥ�</option>" +
													"<option value='6' "+mm6+" >�Զع�¹</option>" +
													"<option value='7' "+mm7+">�á�Ҥ�</option>" +
													"<option value='8' "+mm8+">�ԧ�Ҥ�</option>" +
													"<option value='9' "+mm9+">�ѹ��¹</option>" +
													"<option value='10' "+mm10+" >���Ҥ�</option>" +
													"<option value='11' "+mm11+">��Ȩԡ�¹</option>" +
													"<option value='12' "+mm12+" >�ѹ�Ҥ�</option>" +
												"</select></div>";
		
	
	
	}
	
	function writeText(inname,emp,maxlength,textalign,key)
	{
		//�ó� display not edit
		//if(<c:out value='${ConfirmRwPremiem}' />){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		//}else{
		//	return "<div align='center' ><input type='text' name = '"+inname+"' onChange='addListUpdate("+key+");'  value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
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
		//�ó� display not edit
		//if(<c:out value='${ConfirmRwPremiem}' />){																	
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onchange='whenSelectEmpOptionInRow();'/></div>";
		//}else{
		//	return "<div align='center' ><input type='text' name = '"+inname+"' maxlength='6' onChange='whenSelectEmpOptionInRowUpdate("+key+");addListUpdate("+key+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;'  onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		//}
	}
	
	function writeTextYearWork(inname,emp,maxlength,textalign,key)
	{
		//�ó� display not edit
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
			//countData();
			if(DWRUtil.getValue("showMaxPage") == ''){
				countData();
			}else{
				onCheckButt("searchForm");
			}
		}else{
			alert('��辺������');
			DWRUtil.removeAllRows("dataTable");
			if(DWRUtil.getValue("showMaxPage") == ''){
				countData();
			}else{
				onCheckButt("searchForm");
			}
		}
	}
	
	
	var rwPremium = {keySeq:null, ouCode:null, yearPr:null, periodPr:null,empCode:null,yearWork:null,periodWork:null,codeSeq:null,morDay:null,aftDay:null,evnDay:null,morHour:null,aftHour:null,
					 evnHour:null,amtMor:null,amtAft:null,amtEvn:null,flagPr:null,confirmFlag:null,seqData:null,updBy:null,updDate:null,creBy:null,creDate:null,approveFlag:null,approveBy:null,approveDate:null};
	
	var allRowUpdate = 0;
	
	function onUpdate(){
		
		var table = document.getElementById("table");
		var aRows = table.rows;
		var num = 2 + parseInt(DWRUtil.getValue("dataLength"));
		
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
			var morDay 		= frm.elements["morDay"];
			var aftDay 		= frm.elements["aftDay"];
			var evnDay 		= frm.elements["evnDay"];
			var morHour 	= frm.elements["morHour"];
			var aftHour 	= frm.elements["aftHour"];
			var evnHour 	= frm.elements["evnHour"];
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
						rwPremium.keySeq = parseInt(keySeq[i].value);
						if(tab.rows.length == 1){
							rwPremium.flagPr = flagPr.value;
						}else{
							rwPremium.flagPr = flagPr[i].value;
						}
						if (empCode[i].value != '' && empCode[i].value != null){
							rwPremium.empCode  = empCode[i].value;
						}
						else{
							rwPremium.empCode  = null;
						}
						
						if (name[i].value != '' && name[i].value != null){
							rwPremium.name  = name[i].value;
						}
						else{
							rwPremium.name  = null;
						}
						if (yearWork[i].value != ''&& yearWork[i].value != null){
							rwPremium.yearWork  = yearWork[i].value;
						}
						else{
							rwPremium.yearWork  = null;
						}
						
						if (periodWork[i].value != '' && periodWork[i].value != null){
							rwPremium.periodWork  = periodWork[i].value;
						}
						else{
							rwPremium.periodWork  = null;
						}
						
						if (morDay[i].value !=''){
							rwPremium.morDay  = parseInt(morDay[i].value);
						}
						else{
							rwPremium.morDay  = null;
						}
			
						if (morHour[i].value !=''){
							rwPremium.morHour = parseInt(morHour[i].value);
						}
						else{
							rwPremium.morHour  = null;
						}
			
						if (aftDay[i].value !=''){
							rwPremium.aftDay  = parseInt(aftDay[i].value);
						}
						else{
							rwPremium.aftDay  = null;
						}
			
						if (aftHour[i].value !=''){
							rwPremium.aftHour  = parseInt(aftHour[i].value);
						}
						else{
							rwPremium.aftHour  = null;
						}
			
						if (evnDay[i].value !=''){
							rwPremium.evnDay  = parseInt(evnDay[i].value);
						}
						else{
							rwPremium.evnDay  = null;
						}
			
						if (evnHour[i].value !=''){
							rwPremium.evnHour  = parseInt(evnHour[i].value);
						}
						else{
							rwPremium.evnHour  = null;
						}
						
						
						if (seqData[i].value !='' && seqData[i].value != null){
							rwPremium.seqData  = parseInt(seqData[i].value);
						}
						else{
							rwPremium.seqData  = null;
						}
						
						if (approveF[i].value != '' && approveF[i].value != null){
							rwPremium.approveFlag  = approveF[i].value;
						}
						else{
							rwPremium.approveFlag  = null;
						}
						
						rwPremium.approveBy = '<%=userId%>';
						rwPremium.approveDate = getDateFromFormat(sed,"dd/MM/yyyy");
						
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
									FeeWpayRwPremiumService.addListApprove(rwPremium, false, {callback:onInsertCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
								else
									FeeWpayRwPremiumService.addListApprove(rwPremium, false);
					
								}else{
								if( allRowUpdate == myUpdate.length )
									FeeWpayRwPremiumService.addListApprove(rwPremium, true, {callback:ClearData,errorHandler:function(message) { alert('�������ö�ѹ�֡��');}});
								else
									FeeWpayRwPremiumService.addListApprove(rwPremium, false);
								
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
					alert('�Ţ��Шӵ�����١��ͧ cansave');
				}
	}
	

	function ClearData(){
		alert("�ѹ�֡���������º����");
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
			num = 2 + parseInt(DWRUtil.getValue("dataLength"));
		}else{
			num = 2 ;
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
					rwPremium.keySeq = null;
					rwPremium.ouCode = '<%=ouCode%>';
					rwPremium.yearPr = parseInt($("year").value);
					rwPremium.periodPr = parseInt($("period").value);
					rwPremium.empCode = oRows[i].cells["empCode"].childNodes[0].value;
					rwPremium.name = oRows[i].cells["name"].childNodes[0].value;
					rwPremium.codeSeq = oRows[i].cells["name"].childNodes[1].value;
					rwPremium.flagPr = oRows[i].cells["flagPr"].childNodes[0].value;
					rwPremium.yearWork = oRows[i].cells["yearWork"].childNodes[0].value;
					rwPremium.periodWork = oRows[i].cells["periodWork"].childNodes[0].value;
					
					if(oRows[i].cells["morDay"].childNodes[0].value != ''){
						rwPremium.morDay = oRows[i].cells["morDay"].childNodes[0].value;
					}else{
						rwPremium.morDay = null;
					}
					if(oRows[i].cells["morHour"].childNodes[0].value != ''){
						rwPremium.morHour = oRows[i].cells["morHour"].childNodes[0].value;
					}else{
						rwPremium.morHour = null;
					}	
					if(oRows[i].cells["aftDay"].childNodes[0].value != ''){
						rwPremium.aftDay = oRows[i].cells["aftDay"].childNodes[0].value;
					}else{
						rwPremium.aftDay = null;
					}	
					if(oRows[i].cells["aftHour"].childNodes[0].value != ''){
						rwPremium.aftHour = oRows[i].cells["aftHour"].childNodes[0].value;
					}else{
						rwPremium.aftHour = null;
					}	
					if(oRows[i].cells["evnDay"].childNodes[0].value != ''){
						rwPremium.evnDay = oRows[i].cells["evnDay"].childNodes[0].value;
					}else{
						rwPremium.evnDay = null;
					}	
					if(oRows[i].cells["evnHour"].childNodes[0].value != ''){
						rwPremium.evnHour = oRows[i].cells["evnHour"].childNodes[0].value;
					}else{
						rwPremium.evnHour = null;
					}	
										
					rwPremium.seqData = oRows[i].cells["seqData"].childNodes[0].value;
					
					//alert('start : ' + oRows[i].cells["start"].childNodes[0].childNodes.length);
					
					
					
					
					rwPremium.approveFlag = oRows[i].cells["approveF"].childNodes[0].value;
					rwPremium.approveBy = '<%=userId%>';
					rwPremium.approveDate = getDateFromFormat(<%=date%>,"dd/MM/yyyy");
					//RwIncDecOtherService.addList(rwIncDecOther, {callback:onInsertCallback});
					
						if( i == (num + 1) )
							FeeWpayRwPremiumService.addListApprove(rwPremium, true, {callback:ClearData,errorHandler:function(message) { alert('�������ö�ѹ�֡��');}});
						else
							FeeWpayRwPremiumService.addListApprove(rwPremium, false);
			}
			
			DWREngine.endBatch();
			if (!insertStatus){alert("�ѹ�֡���������º����");}
			
		}else{
			alert('�Ţ��Шӵ�����١��ͧ');
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
						alert('��͹/�� ������� ��ù��¡��� ��͹/�� ����ش');
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
			var yearWork 	= frm.elements["yearWork"];
			var periodWork 	= frm.elements["periodWork"];
		
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
		FeeWpayPnEmployeeService.findByEmpCodeDetail('<%=userId%>',empCode, '<%=ouCode%>', parseInt(DWRUtil.getValue("year")), parseInt(DWRUtil.getValue("period")), {callback:whenFetchEmployeeDetailInRowUpdateCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
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
			
			if(data.empCode != null && data.empCode != ''){
				name[rowModify].value = data.name;
				codeSeq[rowModify].value = data.codeSeqWork;
			}else{
				name[rowModify].value = '';
				codeSeq[rowModify].value = '';
				alert('�Ţ��Шӵ�����١��ͧ');
				empCode[rowModify].focus();	
			}
			
		}
	
	 }
	 
	 function whenFetchEmployeeDetailInRow(empCode)
	 {
		DWRUtil.useLoadingMessage("Loading ...");
		//alert(empCode+' : '+<%=ouCode%>+' : ' + DWRUtil.getValue("year")+' : '+ DWRUtil.getValue("period"))
		FeeWpayPnEmployeeService.findByEmpCodeDetail('<%=userId%>',empCode, '<%=ouCode%>', parseInt(DWRUtil.getValue("year")), parseInt(DWRUtil.getValue("period")), {callback:whenFetchEmployeeDetailInRowCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
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
			alert('�Ţ��Шӵ�����١��ͧ');
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
				FeeWpayRwPremiumService.countDataApprove
				(
					'<%=userId%>',
					'<%=ouCode%>',
					parseInt(DWRUtil.getValue("year")),
					parseInt(DWRUtil.getValue("period")),
					orgFromCbo,
					orgToCbo,
					empFromCbo,
					empToCbo,
					{callback:whenListDataTableHandler,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}}
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
			[ CTWPAYAPMT005 ] �ѹ�֡�Ѻ�ͧ�Թ����������繡�
		</td>
	</tr>
</table>
<table width="770" border="0" align="center" cellspacing="1">

  	<tr>
	    <td class="font-field" align="right">��Шӻ�&nbsp;</td>
	    <td align="left"><input type="text" name="year"   value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/></td>
	    <td  class="font-field" align="right">�Ǵ&nbsp;</td>
	    <td align="left"><input type="text" name="section"  value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/></td>
  	</tr>
  	<tr>
	    <td class="font-field" align="right">������ѧ�Ѵ��Ժѵԧҹ��ԧ&nbsp;</td>
	    <td align="left" colspan="3"><SELECT  dojoType="ComboBox"  widgetId="orgFromCbo" style="width:94%" onBlurInput="whenSelectOrgOption();"></SELECT></td>
	  </tr>
	  <tr>
	    <td  class="font-field" align="right">�֧�ѧ�Ѵ��Ժѵԧҹ��ԧ&nbsp;</td>
	    <td align="left" colspan="3"><SELECT  dojoType="ComboBox" widgetId="orgToCbo" style="width:94%"></SELECT></td>
  	</tr>
   	<tr>
	    <td class="font-field" align="right">������Ţ��Шӵ��&nbsp;</td>
	    <td align="left"><SELECT  dojoType="ComboBox" widgetId="empFromCbo" style="width:200"  onBlurInput="whenSelectEmpOption();"></SELECT></td>
	    <td  class="font-field" align="right">�֧�Ţ��Шӵ��&nbsp;</td>
	    <td align="left"><SELECT  dojoType="ComboBox" widgetId="empToCbo" style="width:200"></SELECT></td>
  	 </tr>
  	 <tr>
	    <td class="font-field" align="right">������Ţ��Шӵ��&nbsp;</td>
	    <td align="left"><SELECT  dojoType="ComboBox" widgetId="empFromCbo" style="width:200"  onBlurInput="whenSelectEmpOption();"></SELECT></td>
	    <td  class="font-field" align="right">�֧�Ţ��Шӵ��&nbsp;</td>
	    <td align="left"><SELECT  dojoType="ComboBox" widgetId="empToCbo" style="width:200"></SELECT></td>
  	    <td><input type="Button" value="����" class=" button " onclick="whenShowDataTable();" /></td>
  	</tr>
 	
</table>

<br/>

<table  width="800" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td>
		<div style="height:320px;width:950;overflow:auto;vertical-align: top;" align="center" >
		<table id="table" width="900"  border="1" bordercolor="#6699CC" cellpadding="0" cellspacing="0">
			<thead style="text-align: center">
				<tr CLASS="TABLEBULE2" style="height: 30px;">
					<th CLASS="TABLEBULE2" style="width:100px" rowspan="2" align="center">�Ѻ�ͧ</th>
					<th CLASS="TABLEBULE2" rowspan="2">��������¡��</th>
					<th CLASS="TABLEBULE2" style="width:100px" rowspan="2">�Ţ��Шӵ��</th>
					<th CLASS="TABLEBULE2" style="width:150px" rowspan="2">���� - ���ʡ��</th>
					<th CLASS="TABLEBULE2" colspan="2" align="center" rowspan="2">��/�Ǵ���ӧҹ</th>
					<th CLASS="TABLEBULE2" style="width:120px" colspan="2" rowspan="1" align="center"><center>�ӹǹ����� <br><font size="1">(08.00 - 16.00)</font></center></th>
					<th CLASS="TABLEBULE2" style="width:120px" colspan="2" rowspan="1" align="center"><center>�ӹǹ�к��� <br><font size="1">(16.00 - 24.00)</font></center></th>
					<th CLASS="TABLEBULE2" style="width:120px" colspan="2" rowspan="1" align="center"><center>�ӹǹ�С�ҧ�׹ <br><font size="1">(24.00 - 08.00)</font></center></th>
					<th CLASS="TABLEBULE2" style="width:50px" rowspan="2" align="center">�ӴѺ���</th>
				</tr>
				<tr CLASS="TABLEBULE2"  >
					<th CLASS="TABLEBULE2" style="width:60px" align="center">�ѹ </th>
					<th CLASS="TABLEBULE2" style="width:60px" align="center">��.</th>
					<th CLASS="TABLEBULE2" style="width:60px" align="center">�ѹ</th>
					<th CLASS="TABLEBULE2" style="width:60px" align="center">��.</th>
					<th CLASS="TABLEBULE2" style="width:60px" align="center">�ѹ</th>
					<th CLASS="TABLEBULE2" style="width:60px" align="center">��.</th>
				</tr>
			</thead>
			<tbody id="dataTable">
			</tbody>
			<tr id="temprow" style="visibility:hidden;position:absolute">
				<td id="approveF" align="center" width="40">
						<select name="approveF" style="width:100%;"" >
						                            <option value="Y" <% if(approveF.equals("Y")){%> selected="selected"  <%} %> >�Ѻ�ͧ</option>
													<option value="N" <% if(approveF.equals("N")){%> selected="selected"  <%} %>  >����Ѻ�ͧ</option>													
						</select></td>	
				<td id="flagPr" width="50" >
					<select  name="type" >
						<option value="N"  >����</option>
						<option value="A" >��Ѻ��ا��¡���Ѻ</option>
						<option value="R"  >��¡���Ѻ���¡�׹</option>
				</select>
			</td>
			<td id="empCode" align="center"><input type="text" maxlength="6" name="empCode" style="width:100%" onchange="whenSelectEmpOptionInRow(this);"  onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"></td>
			<td id="name" align="center"><input type="text"  name="name" readonly="readonly" style="width:100%;background-color:silver;"/><input type="hidden"  name="codeSeq" /></td>
			<td id="yearWork" align="center"><input type="text" size="6" name="yearWork" value="<c:out value='${DefaultYearAndSection.year}' />" maxlength="4" align="center" style="text-align:center;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
			<td id="periodWork" align="center" ><select name="periodWork">
													<option value="1" <% if ( periodWork.equals("1")){%> selected="selected"  <%} %> >���Ҥ�</option>
													<option value="2" <% if(periodWork.equals("2")){%> selected="selected"  <%} %>  >����Ҿѹ��</option>
													<option value="3" <% if(periodWork.equals("3")){%> selected="selected"  <%} %>  >�չҤ�</option>
													<option value="4" <% if(periodWork.equals("4")){%> selected="selected"  <%} %>  >����¹</option>
													<option value="5" <% if(periodWork.equals("5")){%> selected="selected"  <%} %>  >����Ҥ�</option>
													<option value="6"  <% if(periodWork.equals("6")){%> selected="selected"  <%} %>  >�Զع�¹</option>
													<option value="7" <% if(periodWork.equals("7")){%> selected="selected"  <%} %>  >�á�Ҥ�</option>
													<option value="8" <% if(periodWork.equals("8")){%> selected="selected"  <%} %>  >�ԧ�Ҥ�</option>
													<option value="9" <% if(periodWork.equals("9")){%> selected="selected"  <%} %>  >�ѹ��¹</option>
													<option value="10" <% if(periodWork.equals("10")){%> selected="selected"  <%} %>  >���Ҥ�</option>
													<option value="11" <% if(periodWork.equals("11")){%> selected="selected"  <%} %>  >��Ȩԡ�¹</option>
													<option value="12" <% if(periodWork.equals("12")){%> selected="selected"  <%} %>  >�ѹ�Ҥ�</option>
													</select></td>
			<td id="morDay" align="center" ><input type="text"  name="morDay"  maxlength="3" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
			<td id="morHour" align="center" ><input type="text"  name="morHour"  maxlength="3" align="center" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
			<td id="aftDay" align="center" ><input type="text"  name="aftDay"  maxlength="3" align="center" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
			<td id="aftHour"  align="center" ><input type="text"  name="aftHour"  maxlength="3" align="center" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
			<td id="evnDay" align="center" ><input type="text"  name="evnDay"  maxlength="3" align="center" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
			<td id="evnHour" align="center" w><input type="text"  name="evnHour"  maxlength="3" align="center" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
			<td id="seqData" align="center"><input type="text"  name="seqData"  maxlength="3" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/><input type="hidden"  name="keySeq" /></td>
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
			<input type="Button" class=" button " value="��ŧ" id="confirmData" name="confirmData" onclick="onUpdate();"/>
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
			if(idx > (3+tab.rows.length)){
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
					alert('��سҡ�͡�Ţ��Шӵ�ǡ�͹���������ŵ�ǶѴ�');
				
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
			if(idx > 3){
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
					alert('��سҡ�͡�Ţ��Шӵ�ǡ�͹���������ŵ�ǶѴ�');
				
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
			var answer = confirm("��ͧ���ź������ �������?");
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
									rwPremium.keySeq = parseInt(frm.elements["keySeq"][i].value);
									FeeWpayRwPremiumService.deleteRwPremium(rwPremium, {callback:onDeleteCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
									chDelete = true;
								}
							
								
						}
					}else{
						if(tab.rows.length==1){
							row = tab.rows[0];	
							if (chk[0].checked){
									
									//alert('CC'+rowDelete);
									rwPremium.keySeq = parseInt(frm.elements["keySeq"][0].value);
									FeeWpayRwPremiumService.deleteRwPremium(rwPremium, {callback:onDeleteCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
									chDelete = true;
								}
						  
						}	
					}
					
				DWREngine.endBatch();
					
		
			}
		}
		
		if(chDelete){
			alert('ź���������º����');
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
