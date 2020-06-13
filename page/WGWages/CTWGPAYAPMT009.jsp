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
	
	//System.out.println("ConfirmRwVinai : " + session.getAttribute("ConfirmRwVinai").getClass().getName());
	//String confirm = (String)session.getAttribute("ConfirmRwVinai");
	//System.out.println("ConfirmRwVinai : " + confirm);
	
	String periodWork = String.valueOf(x);
%>
<html>
<head>
<title>�ѹ�֡��¡���ѡ�ó��Թ��</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="script/payroll_util.js"></script>
<!-- Javascript Script File -->
<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWgPrPeriodLineService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWgPnEmployeeService.js"></SCRIPT>
<script type="text/javascript" src="dwr/interface/FeeWgRwVinaiService.js"></script>


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
		$("confirm").value = "<c:out value='${ConfirmRwVinai}' /> "  ;//data.confirm;
		chkMainClose();
	
		//alert($("confirm").value +' ::: '+<c:out value='${DefaultYearAndSection.confirm}' /> );	
		//PnEmployeeService.findPrEmpBySecurity('<%=userId%>','<%=ouCode%>',$("year").value,$("period").value, {callback:onLoadEmployeeCallback});
		
		 if(<c:out value='${ConfirmRwVinai}' />){
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
				FeeWgPrPeriodLineService.findPeriodLine('<%=ouCode%>',$("year").value,$("period").value, '<%=userId%>', {callback:whenchkMainCloseCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������1');}});
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
			FeeWgRwVinaiService.findByCriteriaListApprove
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
				{callback:whenListDataTableHandler,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������2');}}
			);
		}
	
	
	var cellFuncs = [
		function(data) { return writeSelect("flagPr",data.flagPr,data.keySeq);},
		function(data) { return writeTextID("empCode",data.empCode,13,"left",data.keySeq);},
		function(data) { return writeTextDisplay("name",data.name,200,"left","codeSeq",data.codeSeq);},
		function(data) { return writeTextYearWork("yearWork",data.yearWork,4,"center",data.keySeq);},
		function(data) { return writeSelectMonth("periodWork",data.periodWork,data.keySeq);},
		function(data) { 
							if(data.cutSalYear != null ){ 
								return writeText("cutSalYear",data.cutSalYear,4,"center",data.keySeq);
							}else{
			                 	return writeText("cutSalYear","",4,"center",data.keySeq);
			                } 
						},
		function(data) {    if(data.cutSalMonth != null ){ 
							return writeSelectMonthCut("cutSalMonth",data.cutSalMonth,data.keySeq);
							}else{
         					return writeSelectMonthCut("cutSalMonth","",data.keySeq);
        					}	
						},
		function(data) { 
							if(data.cutSalPercent != null ){ 
								return writeHidden2("keySeq",data.keySeq)+" "+writeTextDay("cutSalPercent",data.cutSalPercent,6,"right",data.keySeq);
							}else{
			                 	return writeHidden2("keySeq",data.keySeq)+" "+writeTextDay("cutSalPercent","",6,"right",data.keySeq);
			                } 
						},
		function(data) { 
							if(data.absYear1 != null ){ 
								return writeText("absYear1",data.absYear1,4,"center",data.keySeq);
							}else{
			                 	return writeText("absYear1","",4,"center",data.keySeq);
			                } 
						},
		function(data) { return writeSelectMonth("absMonth1",data.absMonth1,data.keySeq);},
		function(data) { 
							if(data.absDay1 != null ){ 
								return writeHidden2("keySeq",data.keySeq)+" "+writeTextDay("absDay1",data.absDay1,6,"right",data.keySeq);
							}else{
			                 	return writeHidden2("keySeq",data.keySeq)+" "+writeTextDay("absDay1","",6,"right",data.keySeq);
			                } 
						},
		function(data) { 
						if(data.startDateQut != null && data.startDateQut != ''){
							return writeStTextDate("startDateQut",formatDate(data.startDateQut,"dd/MM/yyyy"),10,"center",data.keySeq,12);
						}else{
							return writeStTextDate("startDateQut","",10,"center",data.keySeq,12);
						}	
						},
		function(data) { 
						if(data.endDateQut != null && data.endDateQut != ''){
							return writeEndTextDate("endDateQut",formatDate(data.endDateQut,"dd/MM/yyyy"),10,"center",data.keySeq,12);
						}else{
							return writeEndTextDate("endDateQut","",10,"center",data.keySeq,12);
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
													"<option value='Y' "+mm1+" >�Ѻ�ͧ</option>" +
												    "<option value='N' "+mm2+" >����Ѻ�ͧ</option>" +																							 							
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
		//if(<c:out value='${ConfirmRwVinaiDeduct}' />){
		return "<div align='center' style='background-color:#CCCCCC;'><select name='"+inname+"'  disabled='true' background-color:transparent;color:#000000' ;><option value='N' "+temp1+" >����</option>"+
												"<option value='A' "+temp2+">��Ѻ��ا��¡���ѡ</option></select></div>";
		//}else {
		//return "<div align='center' ><select onchange='addListUpdate("+key+")' name='"+inname+"'><option value='N' "+temp1+" >����</option>"+
					//							"<option value='A' "+temp2+">��Ѻ��ا��¡���ѡ</option></select></div>";
		
		
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
		
		//if(<c:out value='${ConfirmRwVinaiDeduct}' />){
			//alert('x1'); 
			return "<div align='center' style='background-color:#CCCCCC;'>"+
						"<select name='startMonth' disabled='true' background-color:#b2b2b2;color:#000000; onChange='addListUpdate("+key+");validateMonthYear(this);'>" +
							"<option value='' "+mm0+" ></option>" +
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
						"</select>"+
						"/<input type='text'  name='startYear'  maxlength='4' value='"+year+"' align='center' onchange='addListUpdate("+key+");validateMonthYear(this);'style='background-color:#CCCCCC;text-align:right;width:40px;' onkeyup='if(this.value < 0) this.value = \"\";if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		// }else {
			//alert('x2');
		//	var str = 	"<div align='center' >"+
			//			"<select name='startMonth' onChange='addListUpdate("+key+");validateMonthYear(this);'>" +
			//				"<option value='' "+mm0+" ></option>" +
			//				"<option value='1' "+mm1+" >���Ҥ�</option>" +
			//				"<option value='2' "+mm2+">����Ҿѹ��</option>" +
			//				"<option value='3' "+mm3+">�չҤ�</option>" +
			//				"<option value='4' "+mm4+">����¹</option>" +
			//				"<option value='5' "+mm5+">����Ҥ�</option>" +
			//				"<option value='6' "+mm6+" >�Զع�¹</option>" +
			//				"<option value='7' "+mm7+">�á�Ҥ�</option>" +
			//				"<option value='8' "+mm8+">�ԧ�Ҥ�</option>" +
			///				"<option value='9' "+mm9+">�ѹ��¹</option>" +
			//				"<option value='10' "+mm10+" >���Ҥ�</option>" +
			//				"<option value='11' "+mm11+">��Ȩԡ�¹</option>" +
		//					"<option value='12' "+mm12+" >�ѹ�Ҥ�</option>" +
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
		
		//if(<c:out value='${ConfirmRwVinaiDeduct}' />){
		return "<div align='center' style='background-color:#CCCCCC;'>"+
					"<select name='endMonth' disabled='true' background-color:transparent;color:#000000; onChange='addListUpdate("+key+");validateMonthYear(this);'>" +
						"<option value='' "+mm0+" ></option>" +
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
					"</select>"+
					"/<input type='text'  name='endYear'  maxlength='4' value='"+year+"' align='center' onchange='addListUpdate("+key+");validateMonthYear(this);' style='background-color:#CCCCCC;text-align:right;width:40px;' onkeyup='if(this.value < 0) this.value = \"\";if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		//}else {
		//return 	"<div align='center' >"+
			//	"<select name='endMonth' onChange='addListUpdate("+key+");validateMonthYear(this);'>" +
			//		"<option value='' "+mm0+" ></option>" +
			//		"<option value='1' "+mm1+" >���Ҥ�</option>" +
			//		"<option value='2' "+mm2+">����Ҿѹ��</option>" +
			//		"<option value='3' "+mm3+">�չҤ�</option>" +
			//		"<option value='4' "+mm4+">����¹</option>" +
			//		"<option value='5' "+mm5+">����Ҥ�</option>" +
			//		"<option value='6' "+mm6+" >�Զع�¹</option>" +
			//		"<option value='7' "+mm7+">�á�Ҥ�</option>" +
			//		"<option value='8' "+mm8+">�ԧ�Ҥ�</option>" +
			//		"<option value='9' "+mm9+">�ѹ��¹</option>" +
			//		"<option value='10' "+mm10+" >���Ҥ�</option>" +
			//		"<option value='11' "+mm11+">��Ȩԡ�¹</option>" +
			////		"<option value='12' "+mm12+" >�ѹ�Ҥ�</option>" +
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
		//�ó� display not edit
		//if(<c:out value='${ConfirmRwVinaiDeduct}' />){
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
		//�ó� display not edit
		//if(<c:out value='${ConfirmRwVinaiDeduct}' />){																	
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onchange='whenSelectEmpOption();'/></div>";
		//}else{
		//	return "<div align='center' ><input type='text' maxlength='6' name = '"+inname+"' onchange='whenSelectEmpOptionInRowUpdate("+key+");addListUpdate("+key+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		//}
	}
	
	
	function writeTextYearWork(inname,emp,maxlength,textalign,key)
	{
		//�ó� display not edit
		if(<c:out value='${ConfirmRwVinai}' />){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' size='6' maxlength='"+maxlength+"' style='text-align:"+textalign+";background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}else{
			return "<div align='center' ><input type='text' readonly='true' name = '"+inname+"' onchange='addListUpdate("+key+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' size='6' style='text-align:"+textalign+";' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}
	}
	
	function writeHidden(inname,emp,size,maxlength,textalign,nameHide,empHide)
	{
		if(<c:out value='${ConfirmRwVinai}' />){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		}else{
			return "<div align='center'><input type='text' name = '"+inname+"' onchange='addListUpdate("+empHide+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		}
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
			alert('��辺������');
			DWRUtil.removeAllRows("dataTable");
			if(DWRUtil.getValue("showMaxPage") == ''){
				countData();
			}else{
				onCheckButt("searchForm");
			}
		}
	}
	
	
	var feeWgRwVinai = {	keySeq:null,ouCode:null,yearPr:null,periodPr:null,empCode:null,
					yearWork:null,periodWork:null,codeSeq:null,
					cutSalYear:null,cutSalMonth:null,cutSalPercent:null,confirmFlag:null,
					absYear1:null,absMonth1:null,absDay1:null,startDateQut:null,endDateQut:null,flagPr:null,
					updBy:null,updDate:null,creBy:null,creDate:null,approveFlag:null,approveBy:null,approveDate:null};
	
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
				if (aRows[a].cells["empCode"].childNodes[0].value == null || aRows[a].cells["empCode"].childNodes[0].value == ''){
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
						feeWgRwVinai.keySeq = parseInt(keySeq[i].value);
						feeWgRwVinai.codeSeq = codeSeq[i].value;
						if(tab.rows.length == 1){
							feeWgRwVinai.flagPr = flagPr.value;
						}else{
							feeWgRwVinai.flagPr = flagPr[i].value;
						}
						if (empCode[i].value != '' && empCode[i].value != null){
							feeWgRwVinai.empCode  = empCode[i].value;
						}
						else{
							feeWgRwVinai.empCode  = null;
						}
						
						if (name[i].value != '' && name[i].value != null){
							feeWgRwVinai.name  = name[i].value;
						}
						else{
							feeWgRwVinai.name  = null;
						}
						if (yearWork[i].value != ''&& yearWork[i].value != null){
							feeWgRwVinai.yearWork  = yearWork[i].value;
						}
						else{
							feeWgRwVinai.yearWork  = null;
						}
						
						if (periodWork[i].value != '' && periodWork[i].value != null){
							feeWgRwVinai.periodWork  = periodWork[i].value;
						}
						else{
							feeWgRwVinai.periodWork  = null;
						}
						
						
						feeWgRwVinai.approveFlag = approveF[i].value;
						feeWgRwVinai.approveBy = '<%=userId%>';
						//feeWgRwVinai.updBy = '<%=userId%>';
						
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
									FeeWgRwVinaiService.addListApprove(feeWgRwVinai, false, {callback:onInsertCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
								else
									FeeWgRwVinaiService.addListApprove(feeWgRwVinai, false);
					
								}else{
								if( allRowUpdate == myUpdate.length )
									FeeWgRwVinaiService.addListApprove(feeWgRwVinai, true, {callback:ClearData,errorHandler:function(message) { alert('�������ö�ѹ�֡��');}});
								else
									FeeWgRwVinaiService.addListApprove(feeWgRwVinai, false);
								
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
			num = 2 + parseInt(DWRUtil.getValue("dataLength"));
		}else{
			num = 2 ;
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
					feeWgRwVinai.keySeq = null;
					feeWgRwVinai.ouCode = '<%=ouCode%>';
					feeWgRwVinai.yearPr = parseInt($("year").value);
					feeWgRwVinai.periodPr = parseInt($("period").value);
					feeWgRwVinai.empCode = oRows[i].cells["empCode"].childNodes[0].value;
					feeWgRwVinai.name = oRows[i].cells["name"].childNodes[0].value;
					feeWgRwVinai.codeSeq = oRows[i].cells["name"].childNodes[1].value;
					feeWgRwVinai.flagPr = oRows[i].cells["flagPr"].childNodes[0].value;
					feeWgRwVinai.yearWork = oRows[i].cells["yearWork"].childNodes[0].value;
					feeWgRwVinai.periodWork = oRows[i].cells["periodWork"].childNodes[0].value;
					
					
					
					
					feeWgRwVinai.approveFlag = oRows[i].cells["approveF"].childNodes[0].value;
					feeWgRwVinai.approveBy = '<%=userId%>';
					//feeWgRwVinai.approveDate = getDateFromFormat(<%=date%>,"dd/MM/yyyy");
					//feeWgRwVinai.confirmFlag = 'N';
					//feeWgRwVinai.updBy = '<%=userId%>';
					//feeWgRwVinai.creBy = '<%=userId%>';
					//feeWgRwVinai.creDate = getDateFromFormat(<%=date%>,"dd/MM/yyyy");
					//RwIncDecOtherService.addList(rwIncDecOther, {callback:onInsertCallback});
					
						if( i == (num + 1) )
							FeeWgRwVinaiService.addListApprove(feeWgRwVinai, true, {callback:ClearData,errorHandler:function(message) { alert('�������ö�ѹ�֡��');}});
						else
							FeeWgRwVinaiService.addListApprove(feeWgRwVinai, false);
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
				alert("�ѹ���������鹵�ͧ���¡��� �ѹ�������ش");
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
				alert("�ѹ���������鹵�ͧ���¡��� �ѹ�������ش");
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
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "2":
				    if(day > 29){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "3":
				    if(day > 31){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "4":
				    if(day > 30){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "5":
				    if(day > 31){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "6":
				    if(day > 30){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "7":
				    if(day > 31){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "8":
				    if(day > 31){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "9":
				    if(day > 30){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "10":
				    if(day > 31){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "11":
				    if(day > 30){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "12":
				    if(day > 31){
				    	alert('�ӹǹ�ѹ���١��ͧ');
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
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "2":
						    if(day > 29){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "3":
						    if(day > 31){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "4":
						    if(day > 30){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "5":
						    if(day > 31){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "6":
						    if(day > 30){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "7":
						    if(day > 31){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "8":
						    if(day > 31){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "9":
						    if(day > 30){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "10":
						    if(day > 31){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "11":
						    if(day > 30){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "12":
						    if(day > 31){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						} 
				
				
				}
		//alert(month+' ::: '+day)
		
	
	 	}
	 	
	 
	 }
	 
	 function bypassRowUpdate(data){
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
			
			//alert(dayCheck[rowModify].value);
			dayCheck[rowModify].value = '';
		}
	 	
	 	
	 	
	 
	 }
	 
	 function bypassRowUpdateCut(data){
	 	  var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["formEdit"];
		//alert('leng  :'+tab.rows.length);	
		
		if(tab.rows.length > 0){
			var keySeq 		= frm.elements["keySeq"];
			var monthCheck 	= frm.elements["cutSalMonth"];
			var percentCheck 		= frm.elements["cutSalPercent"];
			
			for(var c=0; c<tab.rows.length; c++){
				//alert(keySeq[c].value + ':::'+ data);
				if(keySeq[c].value == data ){
					rowModify = c;
				//	alert('value in row :'+keySeq[c].value+' : '+flagPr[c].value+' : '+yearWork[c].value+' : '+periodWork[c].value+' : '+fiscalYear[c].value+' : '+startDate[c].value+' : '+endDate[c].value+' : '+totDay1[c].value+' : '+totDay15[c].value+' : '+totDay3[c].value+' : '+seqData[c].value+' : '+codeSeqNew[c].value+' : '+flagWork[c].value+' : '+orgCode[c+1].value);
					break;
				}
			}
			
			//alert(dayCheck[rowModify].value);
			percentCheck[rowModify].value = '';
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
		FeeWgPnEmployeeService.findByEmpCodeDetail('<%=userId%>',empCode, '<%=ouCode%>', parseInt(DWRUtil.getValue("year")), parseInt(DWRUtil.getValue("period")), {callback:whenFetchEmployeeDetailInRowUpdateCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������4');}});
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
				alert('�Ţ��Шӵ�����١��ͧ');
				empCode[rowModify].focus();	
			}
			
		}
	
	 }
	 
	 function whenFetchEmployeeDetailInRow(empCode)
	 {
		DWRUtil.useLoadingMessage("Loading ...");
		//alert(empCode+' : '+<%=ouCode%>+' : ' + DWRUtil.getValue("year")+' : '+ DWRUtil.getValue("period"))
		FeeWgPnEmployeeService.findByEmpCodeDetail('<%=userId%>',empCode, '<%=ouCode%>', parseInt(DWRUtil.getValue("year")), parseInt(DWRUtil.getValue("period")), {callback:whenFetchEmployeeDetailInRowCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������5');}});
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
				FeeWgRwVinaiService.countDataApprove
				(
					'<%=userId%>',
					'<%=ouCode%>',
					parseInt(DWRUtil.getValue("year")),
					parseInt(DWRUtil.getValue("period")),
					orgFromCbo,
					orgToCbo,
					empFromCbo,
					empToCbo,
					{callback:countDataHandler,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������6');}}
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
			[ CTWGPAYAPMT009] �ѹ�֡�Ѻ�ͧ��¡���ѡ�ó��Թ�� (�١��ҧ����)
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
	    <td><input type="Button" value="����" class=" button " onclick="whenShowDataTable();" /></td>
  	</tr>
 	

</table>
<br/>

<table  width="1100" border="0" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td>
		<div style="height:320px;width:1100;overflow:auto;vertical-align: top;" align="center" >
		<table id="table" width="1100"  border="1" bordercolor="#6699CC" cellpadding="0" cellspacing="0">
			<thead style="text-align: center">
				<tr CLASS="TABLEBULE2" style="height: 30px;">
					<th CLASS="TABLEBULE2" rowspan="2" style="width:90px">��������¡��</th>
					<th CLASS="TABLEBULE2" rowspan="2" style="width:120px">�Ţ��Шӵ��</th>
					<th CLASS="TABLEBULE2" rowspan="2" style="width:250px">���� - ���ʡ��</th>
					<th CLASS="TABLEBULE2" colspan="2" rowspan="2" align="center">��/�Ǵ���ӧҹ</th>
					<th CLASS="TABLEBULE2" style="width:200px" colspan="3" rowspan="1" align="center"><center>�Ѵ�Թ��͹</center></th>
					<th CLASS="TABLEBULE2" style="width:200px" colspan="3" rowspan="1" align="center"><center>�Ҵ�ҹ</center></th>
					<th CLASS="TABLEBULE2" colspan="2" rowspan="1" align="center"><center>�ѡ�ҹ</center></th>
					<th CLASS="TABLEBULE2" rowspan="2" style="width:100px" >�Ѻ�ͧ</th>
				</tr>
				<tr CLASS="TABLEBULE2"  >
							<th CLASS="TABLEBULE2" colspan="2" align="center">��/��͹</th>
							<th CLASS="TABLEBULE2" style="width:75px" align="center">�ӹǹ (%)</th>
							<th CLASS="TABLEBULE2" colspan="2" align="center">��/��͹</th>
							<th CLASS="TABLEBULE2" style="width:100px"align="center">�ӹǹ(�ѹ)</th>
							<th CLASS="TABLEBULE2" align="center">������ѹ���</th>
							<th CLASS="TABLEBULE2" align="center">�֧�ѹ���</th>
						</tr>
			</thead>
			<tbody id="dataTable">
			</tbody>
			<tr id="temprow" style="visibility:hidden;position:absolute">
				<td id="flagPr" readonly="readonly" width="50" >
					<select  name="type" >
						<option value="N"  >����</option>
						<option value="A" >��Ѻ��ا��¡���Ѻ</option>
						<option value="R"  >��¡���Ѻ���¡�׹</option>
				</select>
			</td>
			<td id="empCode" align="center"><input type="text" maxlength="13" name="empCode" readonly="readonly" style="width:100%" onchange="whenSelectEmpOptionInRow(this);"  onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"></td>
			<td id="name" align="center"><input type="text"  name="name" readonly="readonly" style="width:100%;background-color:silver;"/><input type="hidden"  name="codeSeq" /></td>
			<td id="yearWork" align="center"><input type="text" size="6" name="yearWork" readonly="readonly" value="<c:out value='${DefaultYearAndSection.year}' />" maxlength="4" align="center" style="text-align:center;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
			<td id="periodWork" align="center" ><select name="periodWork" disabled="disable">
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
			<td id="cutSalYear" ><input type="text" name=cutSalYear size="6" maxlength="4" style="text-align:center;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="cutSalMonth" ><select name="cutSalMonth" onchange="bypassCut(this);">
							                        <option value=""  ></option>
													<option value="1" >���Ҥ�</option>
													<option value="2" >����Ҿѹ��</option>
													<option value="3" >�չҤ�</option>
													<option value="4" >����¹</option>
													<option value="5" >����Ҥ�</option>
													<option value="6" >�Զع�¹</option>
													<option value="7" >�á�Ҥ�</option>
													<option value="8" >�ԧ�Ҥ�</option>
													<option value="9" >�ѹ��¹</option>
													<option value="10" >���Ҥ�</option>
													<option value="11" >��Ȩԡ�¹</option>
													<option value="12" >�ѹ�Ҥ�</option>
												</select></td>
							<td id="cutSalPercent" ><input type="text"  name=cutSalPercent  maxlength="5" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="absYear1" ><input type="text" name=absYear1 size="6" maxlength="4" style="text-align:center;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="absMonth1" ><select name="absMonth1" onchange="bypass(this);">
							                        <option value=""  ></option>
													<option value="1">���Ҥ�</option>
													<option value="2">����Ҿѹ��</option>
													<option value="3">�չҤ�</option>
													<option value="4">����¹</option>
													<option value="5">����Ҥ�</option>
													<option value="6">�Զع�¹</option>
													<option value="7">�á�Ҥ�</option>
													<option value="8">�ԧ�Ҥ�</option>
													<option value="9">�ѹ��¹</option>
													<option value="10">���Ҥ�</option>
													<option value="11">��Ȩԡ�¹</option>
													<option value="12">�ѹ�Ҥ�</option>
												</select></td>
							<td id="absDay1" ><input type="text"  name=absDay1  maxlength="6" onchange="checkDay(this);" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="startDateQut" ><input type="text"   name="startDateQut" size="12" maxlength="10" align="center" style="text-align:center;" onchange="return validateDate(this) && compareStDate(this);"></td>
							<td id="endDateQut" ><input type="text"   name="endDateQut" size="12" maxlength="10" align="center" style="text-align:center;" onchange="return validateDate(this) && compareEndDate(this);"><input type="hidden"  name="keySeq" /></td>
		       <td id="approveF" align="center" width="40">
						<select name="approveF" style="width:100%;"" >
						                            <option value="Y" <% if(approveF.equals("Y")){%> selected="selected"  <%} %> >�Ѻ�ͧ</option>
													<option value="N" <% if(approveF.equals("N")){%> selected="selected"  <%} %>  >����Ѻ�ͧ</option>													
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
			<input type="Button" class=" button " value="��ŧ" id="confirmData" name="confirmData" onclick="onUpdate();"/>
			<input type="Button" class=" button " value="�͡" id="gotoTree" name="gotoTree" onclick="backForm();"/>
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
									rwVinai.keySeq = parseInt(frm.elements["keySeq"][i].value);
									FeeWgRwVinaiService.deleteRwVinai(rwVinai, {callback:onDeleteCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������7');}});
									chDelete = true;
								}
							
								
						}
					}else{
						if(tab.rows.length==1){
							row = tab.rows[0];	
							if (chk[0].checked){
									
									//alert('CC'+rowDelete);
									rwVinai.keySeq = parseInt(frm.elements["keySeq"][0].value);
									FeeWgRwVinaiService.deleteRwVinai(rwVinai, {callback:onDeleteCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������8');}});
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
					alert("�ѹ���������鹵�ͧ���¡��� �ѹ�������ش");
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
					alert("�ѹ���������鹵�ͧ���¡��� �ѹ�������ش");
					endDateQut[rowModify].focus();
					startDateQut[rowModify].value = '';
				}
		 	}
		 	
		}
	 }
	

//-->
</SCRIPT>