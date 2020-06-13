<%@ page language="java" contentType="text/html;charset=TIS-620" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%
	String yearInsert = (String)request.getParameter("yearInsert");
	
	String periodInsert = (String)request.getParameter("periodInsert");
	String userIdInsert = (String)request.getParameter("userIdInsert");
	String ouCodeInsert = (String)request.getParameter("ouCodeInsert");
	String sectionInsert = (String)request.getParameter("sectionInsert");
	
	System.out.println("**********************"+periodInsert);
	java.util.Date dd = new java.util.Date();
	java.text.SimpleDateFormat fmd = new java.text.SimpleDateFormat("dd/MM/yyyy",new java.util.Locale("th","TH"));
	String date = fmd.format(dd);
	
	
	String orgFromIns = request.getParameter("orgFromIns");
	String orgToIns = request.getParameter("orgToIns");
	String empCodeFromIns = request.getParameter("empCodeFromIns");
	String empCodeToIns = request.getParameter("empCodeToIns");
	String pageIns = request.getParameter("pageIns");
	
	String orderFromCboIns = request.getParameter("orderFromCboIns");
	String orderToCboIns = request.getParameter("orderToCboIns");
	
	String refNoFromIns = request.getParameter("refNoFromIns");
	String refNoToIns = request.getParameter("refNoToIns");
	String otTypeIns = request.getParameter("otTypeIns");
	
	if( pageIns.trim().equals("") )
		pageIns = "-1";
	
	java.util.Calendar cc = new java.util.GregorianCalendar();
	int x = cc.get(java.util.Calendar.MONTH) +1;
	//System.out.println("#$#$#$#$$#$#$# :::"+x);
	
	//String periodWork = String.valueOf(x);
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
<title>�ѹ�֡�����ǧ����/��ҷӧҹ��ѹ��ش�ѡ��͹</title>
<style>
.inputStyle{
	background-color: white ;
}

.displayStyle{
	background-color: silver ;
}


</style>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<!-- Javascript Script File -->

<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayRwOvertimeService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPnEmployeeService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/VPnOrganizationSecurityService.js"></SCRIPT>
<script type="text/javascript" src="script/gridScript.js"></script>
<script type="text/javascript" src="script/dojo.js"></script>
<script type="text/javascript" src="script/dateCalendar.js"></script>
<script type="text/javascript" src="script/payroll_util.js"></script>
<script type="text/javascript" src="script/moment.js"></script>

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
	 var countKey = 0;
	 var lRowNumber;
	var rwOvertime = {keySeq:null, ouCode:null, yearPr:null, periodPr:null,
						empCode:null,yearWork:null,periodWork:null,codeSeq:null,
						otType:null,refNo:null,fiscalYear:null,startDate:null,endDate:null,flagWork:null,confirmFlag:null,approveFlag:null,approveClose:null,
					 	totDay15:null,totDay1:null,totDay3:null,amtDay15:null,amtDay1:null,amtDay3:null,flagPr:null,seqData:null,updBy:null,updDate:null,creBy:null,creDate:null,workHour:null};
					 	
					 	
	function ClearData(){
		alert("�ѹ�֡���������º����");
		
		var table = document.getElementById("table");
		var tdName;
		var chkName;
		
		var oRows = table.rows;
		if(tdName == null)tdName="flag";
		if(chkName == null)chkName="chk";
		if(oRows.length > 3){
			for(i=oRows.length-1;i>2;i--){
				table.deleteRow(i);			
				
			}
		}
		
		$("refNo").value = '';
		$("workFrom").value = '1';
		document.forms['formInsert'].elements['workFrom'].disabled = false;
		oRows[2].cells["spsDay1"].childNodes[0].disabled = false;
		oRows[2].cells["spsDay15"].childNodes[0].disabled = false;
		oRows[2].cells["spsDay3"].childNodes[0].disabled = false;	
				
		oRows[2].cells["spsDay1"].childNodes[0].style.backgroundColor = '';
		oRows[2].cells["spsDay15"].childNodes[0].style.backgroundColor = '';
		oRows[2].cells["spsDay3"].childNodes[0].style.backgroundColor = '';
			
		
	}
	
	function onInsertCallback(data){
		//alert('test');
	}
	
	function insertNewData()
    {
    
    		var table = document.getElementById("table");
			var tdName;
			var chkName;
			
			var oRows = table.rows;
			if(oRows.length > 3){
				var i = oRows.length-1;
				//alert(oRows[i].cells["empCode"].childNodes[0].value);
				var orgCode = oRows[i].cells["orgCode"].childNodes[0].value;
			
	    		VPnOrganizationSecurityService.findByKeyPK('<%=ouCodeInsert%>','<%=userIdInsert%>', orgCode,{callback:whenCheckCanSaveCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
			}
		
    }
	
	function whenCheckCanSaveCallback(data)
    {
    	if(data.length==0)
    	{
    		alert("�ѧ�Ѵ��Ժѵԧҹ���١��ͧ");
    	}else
    	{
    		
			var table = document.getElementById("table");
			var tdName;
			var chkName;
			
			var oRows = table.rows;
			if(tdName == null)tdName="flag";
			if(chkName == null)chkName="chk";
			
			if(oRows.length > 3)
			{
				if(checkRefNo()){
					if(compareDateBeforSave()){
					DWREngine.beginBatch();
						for(var i=oRows.length-1;i > 2;i--)
						{
							
								rwOvertime.ouCode = '<%=ouCodeInsert%>';
								rwOvertime.yearPr = '<%=yearInsert%>';
								rwOvertime.periodPr = '<%=periodInsert%>';
								rwOvertime.empCode = oRows[i].cells["empCode"].childNodes[0].value;
								rwOvertime.codeSeq = oRows[i].cells["orgCode"].childNodes[1].value;
								rwOvertime.flagPr = oRows[i].cells["flagPr"].childNodes[0].value;
								rwOvertime.yearWork = oRows[i].cells["yearWork"].childNodes[0].value;
								rwOvertime.periodWork = oRows[i].cells["periodWork"].childNodes[0].value;
								rwOvertime.otType = document.forms['formInsert'].elements['workFrom'].value;
								rwOvertime.refNo = document.forms['formInsert'].elements['refNo'].value;
								rwOvertime.fiscalYear = oRows[i].cells["fiscalYear"].childNodes[0].value;
								if(oRows[i].cells["startDate"].childNodes[0].value != null && oRows[i].cells["startDate"].childNodes[0].value != ''){
									rwOvertime.startDate= getDateFromFormat(oRows[i].cells["startDate"].childNodes[0].value,"dd/MM/yyyy");
								}else{
									rwOvertime.startDate= null;
								}
								if(oRows[i].cells["endDate"].childNodes[0].value != null && oRows[i].cells["endDate"].childNodes[0].value != ''){
									rwOvertime.endDate = getDateFromFormat(oRows[i].cells["endDate"].childNodes[0].value,"dd/MM/yyyy");
								}else{
									rwOvertime.endDate = null;
								}
								rwOvertime.workHour = oRows[i].cells["workHour"].childNodes[0].value;
								rwOvertime.totDay15 = oRows[i].cells["totDay15"].childNodes[0].value;
								rwOvertime.totDay1 = oRows[i].cells["totDay1"].childNodes[0].value;
								rwOvertime.totDay3 = oRows[i].cells["totDay3"].childNodes[0].value;
								rwOvertime.amtDay15 = oRows[i].cells["spsDay15"].childNodes[0].value;
								rwOvertime.amtDay1 = oRows[i].cells["spsDay1"].childNodes[0].value;
								rwOvertime.amtDay3 = oRows[i].cells["spsDay3"].childNodes[0].value;								
								rwOvertime.seqData = oRows[i].cells["seqData"].childNodes[0].value;
								if(oRows[i].cells["nameOrg"].childNodes[1].value == '㹡ͧ'){
									rwOvertime.flagWork = 'N';
								}else if(oRows[i].cells["nameOrg"].childNodes[1].value == '��ҧ�ͧ'){
									rwOvertime.flagWork = 'Y';
								}
								rwOvertime.confirmFlag = 'N';
								rwOvertime.approveFlag = 'N';
								rwOvertime.approveClose = 'N';
								rwOvertime.creBy = '<%=userIdInsert%>';
								rwOvertime.updBy = '<%=userIdInsert%>';
								rwOvertime.creDatw = getDateFromFormat(<%=date%>,"dd/MM/yyyy");
								//RwOvertimeService.insertRwOvertime(rwOvertime, {callback:onInsertCallback});
								//RwOvertimeService.addList(rwOvertime, {callback:onInsertCallback});
								
								if( i == 3 ){
									
									FeeWpayRwOvertimeService.addList(rwOvertime, true, {callback:ClearData,errorHandler:function(message) { alert('�������ö�ѹ�֡��');}});
								}else{
									
									FeeWpayRwOvertimeService.addList(rwOvertime, false, {callback:onInsertCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
							}
						}
						DWREngine.endBatch();
			
				//RwOvertimeService.insertAndUpdate({callback:ClearData});
					}
				}
				
			}else
			{
				alert('��辺�����ŷ��кѹ�֡');
			}
		}
	}
	
    function checkRefNo(){
    		var result = true;
    		
    		if(document.forms['formInsert'].elements['workFrom'].value == '1' && (document.forms['formInsert'].elements['refNo'].value == null || document.forms['formInsert'].elements['refNo'].value == '')){
    			result = false;
    			alert('��س��к��Ţ��� �.1');
    		}
    		return result;
    
    }
 
	function whenFindOrganization(object)
	 {
	 	 while (object.tagName !=  'TR')
		 {
		 object = object.parentNode
		 }
	
		 lRowNumber = object.rowIndex
		 
	 	DWRUtil.useLoadingMessage("Loading ...");
	 	
	 	var table = document.getElementById("table");
		var tdName;
		var chkName;
		
		var oRows = table.rows;
		var i = oRows.length-1;
		//alert(oRows[i].cells["empCode"].childNodes[0].value);
		var orgCode = oRows[lRowNumber].cells["orgCode"].childNodes[0].value;
		//alert(empCode);
		//var cbo = dojo.widget.byId("empCbo");
		//alert(orgCode);
		if(orgCode != null && orgCode != ''){
		//SuUserOrganizationService.findOrganizationByCriteria('<%=userIdInsert%>','<%=ouCodeInsert%>',dojo.widget.byId("orgCode").textInputNode.value, {callback:whenFindOrganizationCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
			SuUserOrganizationService.findPrOrganizationByCriteria('<%=userIdInsert%>','<%=ouCodeInsert%>',orgCode, $("year").value, $("period").value, {callback:whenFindOrganizationCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
		}
	 }
			    
	 function whenFindOrganizationCallback(data)
	 {
	 
	 	var table = document.getElementById("table");
		var tdName;
		var chkName;
		
		var oRows = table.rows;
		var i = oRows.length-1;
		
		//	cboSource.push(["<c:out value='${result.orgCode} ${result.divShort} ${result.areaDesc} ${result.secDesc} ${result.workDesc}' />","<c:out value='${result.orgCode}' />"]);
					
		var stringDesc = checkNull(data.divShort,'STRING')+' '+checkNull(data.areaDesc,'STRING')+' '+checkNull(data.secDesc,'STRING')+' '+checkNull(data.workDesc,'STRING');
	 	oRows[lRowNumber].cells["nameOrg"].childNodes[0].value = stringDesc;
		oRows[lRowNumber].cells["orgCode"].childNodes[1].value = data.codeSeq;
		var orgDesc = Trim(oRows[lRowNumber].cells["nameOrg"].childNodes[0].value);
    	
		if( orgDesc == '' )
		{
			document.forms['formInsert'].elements['insertData'].disabled = true;
			document.forms['formInsert'].elements['deleteData'].disabled = true;
			document.forms['formInsert'].elements['confirmData'].disabled = true;
		}else
		{
			$("codeSeqNew").value = data.codeSeq;
			
			var codeSeqOld = Trim($("codeSeq").value);
			var codeSeqNew = Trim($("codeSeqNew").value);
			
			if(codeSeqOld!=codeSeqNew)
			{
				oRows[lRowNumber].cells["nameOrg"].childNodes[1].value = '��ҧ�ͧ';
			}else
			{
				oRows[lRowNumber].cells["nameOrg"].childNodes[1].value = '㹡ͧ';
			}
			//alert(oRows[lRowNumber].cells["nameOrg"].childNodes[1].value);
			document.forms['formInsert'].elements['insertData'].disabled = false;
			document.forms['formInsert'].elements['deleteData'].disabled = false;
			document.forms['formInsert'].elements['confirmData'].disabled = false;
		}
	 }
	
     
	function init()
	{
		$("year").value = '<%=yearInsert%>';
	 	$("section").value = '<%=sectionInsert%>';
		$("period").value = '<%=periodInsert%>';
		//alert("!!!! ��Ѻ��ͧ�ӹǹ��������ҧ 1 ��� �Ѻ 1.5 ��� �ô��Ǩ�ͺ��͹����Ф�Ѻ ��ԡ ok ���ͷӧҹ��� !!!!");
	 	//document.forms['formInsert'].elements['orgCode'].disabled = true;	
	 	//document.forms['formInsert'].elements['orgCode'].className = 'displayStyle';

	}

	function whenSelectEmpOptionInRow(object)
     {
     	 while (object.tagName !=  'TR')
		 {
		 object = object.parentNode
		 }
	
		 lRowNumber = object.rowIndex
		 //alert(lRowNumber)

     	
		DWRUtil.useLoadingMessage("Loading ...");
		var table = document.getElementById("table");
		//alert(table.);
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
		FeeWpayPnEmployeeService.findByEmpCodeDetailNotSecurityCTRW03('<%=userIdInsert%>', empCode, '<%=ouCodeInsert%>', '<%=yearInsert%>', '<%=periodInsert%>', {callback:whenFetchEmployeeDetailInRowCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
		
	 
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
	 		oRows[lRowNumber].cells["name"].childNodes[0].value = data.name;
			oRows[lRowNumber].cells["orgCode"].childNodes[0].value = checkNull(data.orgCode,'STRING');
			oRows[lRowNumber].cells["nameOrg"].childNodes[0].value = checkNull(data.orgDesc,'STRING');
			oRows[lRowNumber].cells["nameOrg"].childNodes[1].value = '㹡ͧ';
			//alert(data.codeSeqWork);
			$("codeSeq").value = data.codeSeqWork;
			
			document.forms['formInsert'].elements['insertData'].disabled = false;
			document.forms['formInsert'].elements['deleteData'].disabled = false;
			document.forms['formInsert'].elements['confirmData'].disabled = false;
			
				SuUserOrganizationService.findPrOrganizationByCriteria('<%=userIdInsert%>','<%=ouCodeInsert%>',checkNull(data.orgCode,'STRING'), $("year").value, $("period").value, {callback:whenFindOrganizationCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
	
				VPnOrganizationSecurityService.findByKeyPK('<%=ouCodeInsert%>','<%=userIdInsert%>', checkNull(data.orgCode,'STRING'),{callback:checkOrgCode,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
			
					
		}else{
			oRows[lRowNumber].cells["name"].childNodes[0].value = '';
			oRows[lRowNumber].cells["orgCode"].childNodes[0].value = '';
			oRows[lRowNumber].cells["nameOrg"].childNodes[0].value = '';
			alert('�Ţ��Шӵ�����١��ͧ');
			oRows[lRowNumber].cells["empCode"].childNodes[0].focus();
			oRows[lRowNumber].cells["empCode"].childNodes[0].value = '';
			
			document.forms['formInsert'].elements['insertData'].disabled = true;
			document.forms['formInsert'].elements['deleteData'].disabled = true;
			document.forms['formInsert'].elements['confirmData'].disabled = true;	
		}
	 }
	 
	 function checkOrgCode(data){
	 	var table = document.getElementById("table");
		var tdName;
		var chkName;
		
		var oRows = table.rows;
	 	if(data.length==0)
    	{
    		alert("�ѧ�Ѵ��Ժѵԧҹ���١��ͧ");
    		oRows[lRowNumber].cells["nameOrg"].childNodes[0].value = '';
    		oRows[lRowNumber].cells["orgCode"].childNodes[0].value = '';
    		document.forms['formInsert'].elements['insertData'].disabled = true;
			document.forms['formInsert'].elements['deleteData'].disabled = true;
			document.forms['formInsert'].elements['confirmData'].disabled = true;
			oRows[lRowNumber].cells["orgCode"].childNodes[0].focus();
    	}
	 
	 }
	 
	 function whenNewEmpNo()
	{
		var table = document.getElementById("table");
		var tdName;
		var chkName;
		
		var oRows = table.rows;
		var i = oRows.length-1;
		if(oRows.length > 3){
			var empCode = oRows[i].cells["empCode"].childNodes[0].value;
			if(empCode != null && empCode != '' && $("empHide").value != null && $("empHide").value != ''){
				if(empCode != $("empHide").value){
					oRows[lRowNumber].cells["name"].childNodes[0].value = '';
					oRows[lRowNumber].cells["orgCode"].childNodes[0].value = '';
					oRows[lRowNumber].cells["nameOrg"].childNodes[0].value = ''
					$("codeSeq").value = '';
					$("codeSeqNew").value ='';		
					document.forms['formInsert'].elements['insertData'].disabled = true;
					document.forms['formInsert'].elements['deleteData'].disabled = true;
					document.forms['formInsert'].elements['confirmData'].disabled = true;
				}
			}
		}
			
		//document.forms['formInsert'].elements['orgCode'].disabled = true;	
		//document.forms['formInsert'].elements['orgCode'].className = 'displayStyle';
	}
	
	function whenCheck()
	{
		var table = document.getElementById("table");
		var tdName;
		var chkName;
		
		var oRows = table.rows;
		var i = oRows.length-1;
		if(oRows.length > 3){
			var empCode = oRows[i].cells["empCode"].childNodes[0].value;
			if(empCode != null && empCode != '' && $("empHide").value != null && $("empHide").value != ''){
				if(empCode != $("empHide").value){
					oRows[lRowNumber].cells["name"].childNodes[0].value = '';
					oRows[lRowNumber].cells["orgCode"].childNodes[0].value = '';
					oRows[lRowNumber].cells["nameOrg"].childNodes[0].value = ''
					$("codeSeq").value = '';
					$("codeSeqNew").value ='';		
					document.forms['formInsert'].elements['insertData'].disabled = true;
					document.forms['formInsert'].elements['deleteData'].disabled = true;
					document.forms['formInsert'].elements['confirmData'].disabled = true;
				}else
				{
					whenFetchEmployeeDetailInRow(empCode);
				}
			}
		}		
		//document.forms['formInsert'].elements['orgCode'].disabled = true;	
		//document.forms['formInsert'].elements['orgCode'].className = 'displayStyle';
	}

	function backFormMain(){
		var frm=document.forms['backForm'];
		//alert(<%= orgFromIns %>,<%= orgToIns %>,<%= refNoFromIns %>,<%= refNoToIns %>);
		document.getElementById("orgFromEdit").value = '<%= orgFromIns %>';
		document.getElementById("orgToEdit").value = '<%= orgToIns %>';
		document.getElementById("empCodeFromEdit").value = '<%= empCodeFromIns %>';
		document.getElementById("empCodeToEdit").value = '<%= empCodeToIns %>';
		document.getElementById("pageEdit").value = '<%= Integer.parseInt(pageIns) - 1 %>';
		
		document.getElementById("orderFromCboEdit").value = '<%= orderFromCboIns %>';
		document.getElementById("orderToCboEdit").value = '<%= orderToCboIns %>';
		
		document.getElementById("refNoFromEdit").value = '<%= refNoFromIns %>';
		document.getElementById("refNoToEdit").value = '<%= refNoToIns %>';
		document.getElementById("otTypeEdit").value = '<%= otTypeIns %>';
		document.getElementById("mustQuery").value = 'true';
		frm.submit();
	}
 	 
	 dojo.addOnLoad(init);
	 
	 function compareStDate(object){
	 	//document.forms['formInsert'].elements['refNo'].focus();
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
	
	 	
					var stDate = oRows[rowToCompare].cells["startDate"].childNodes[0].value;
					var endDate = oRows[rowToCompare].cells["endDate"].childNodes[0].value;
					var periodWork = oRows[rowToCompare].cells["periodWork"].childNodes[0].value;
					var yearWork = oRows[rowToCompare].cells["yearWork"].childNodes[0].value;
					var fiscalYear = oRows[rowToCompare].cells["fiscalYear"].childNodes[0].value;
					var flagPr = oRows[rowToCompare].cells["flagPr"].childNodes[0].value;
					var section = '<%=sectionInsert%>';
					
					//alert(fiscalYear);
					
					if(periodWork.length == 1){
						periodWork = '0'+periodWork;
					}
					//alert(periodWork);
					var strDay = stDate.substr(0, 2);
					var strMonth = stDate.substr(3, 2);
					var strYear = stDate.substr(6);
					var monthWork = section.substr(0, 2);
					//alert(strYear);
					//alert(strMonth);
					//alert(strDay);
					//alert(monthWork);
					//alert(fiscalYear);
					
					if(stDate != null && stDate != '' && endDate != null && endDate != ''){
					 	//if(monthWork < strMonth){
						//	alert("��͹������������㹪�ǧ��÷ӧҹ");
							//alert("��͹����ͧ����Թ��͹ 9");
						//	document.forms['formInsert'].elements['insertData'].disabled = true;
						//	document.forms['formInsert'].elements['deleteData'].disabled = true;
						//	document.forms['formInsert'].elements['confirmData'].disabled = true;
						//	oRows[rowToCompare].cells["startDate"].childNodes[0].focus();
						//	oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
						//	oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
						//}else 
						if(flagPr=='N' && fiscalYear == strYear && (monthWork == strMonth) && (strDay > 15)){
							alert("�ѹ���������� �����ԡ�Թ�ѹ��� 15 �ͧ��͹");
							document.forms['formInsert'].elements['insertData'].disabled = true;
							document.forms['formInsert'].elements['deleteData'].disabled = true;
							document.forms['formInsert'].elements['confirmData'].disabled = true;
							oRows[rowToCompare].cells["startDate"].childNodes[0].focus();
							oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
							//oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
						}else if(fiscalYear != strYear){
							alert("�ѹ���������� �����ç�Ѻ�է�����ҳ");
							document.forms['formInsert'].elements['insertData'].disabled = true;
							document.forms['formInsert'].elements['deleteData'].disabled = true;
							document.forms['formInsert'].elements['confirmData'].disabled = true;
							oRows[rowToCompare].cells["startDate"].childNodes[0].focus();
							oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
							//oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
						}else if (compareDates(stDate,"dd/MM/yyyy",endDate,"dd/MM/yyyy") == 1) {
							alert("�ѹ���������� ��ͧ���¡��� �ѹ�������ش");
							document.forms['formInsert'].elements['insertData'].disabled = true;
							document.forms['formInsert'].elements['deleteData'].disabled = true;
							document.forms['formInsert'].elements['confirmData'].disabled = true;
							//oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
							oRows[rowToCompare].cells["startDate"].childNodes[0].focus();
							//oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
						}else{
							oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
							document.forms['formInsert'].elements['insertData'].disabled = false;
							document.forms['formInsert'].elements['deleteData'].disabled = false;
							document.forms['formInsert'].elements['confirmData'].disabled = false;
						//	alert('in this');
						//	oRows[rowToCompare].cells["endDate"].childNodes[0].onblur = "alert('xxxxx')";
						}
				 	}
				 	
				 	if(stDate != null && stDate != '' && (endDate == null || endDate == '')){
				 		//if(monthWork < strMonth){
						//	alert("��͹������������㹪�ǧ��÷ӧҹ");
							//alert("��͹����ͧ����Թ��͹ 9");
						//	document.forms['formInsert'].elements['insertData'].disabled = true;
						//	document.forms['formInsert'].elements['deleteData'].disabled = true;
						//	document.forms['formInsert'].elements['confirmData'].disabled = true;
						//	oRows[rowToCompare].cells["startDate"].childNodes[0].focus();
						//	oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
						//	oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
						//}else 
						if(flagPr== 'N' && yearWork == strYear && fiscalYear == strYear && (monthWork == strMonth) && (strDay > 15)){
							alert("�ѹ���������� �����ԡ�Թ�ѹ��� 15 �ͧ��͹");
							document.forms['formInsert'].elements['insertData'].disabled = true;
							document.forms['formInsert'].elements['deleteData'].disabled = true;
							document.forms['formInsert'].elements['confirmData'].disabled = true;
							oRows[rowToCompare].cells["startDate"].childNodes[0].focus();
							oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
							//oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
						}else if(fiscalYear != strYear){
							alert("�ѹ���������� �����ç�Ѻ�է�����ҳ");
							document.forms['formInsert'].elements['insertData'].disabled = true;
							document.forms['formInsert'].elements['deleteData'].disabled = true;
							document.forms['formInsert'].elements['confirmData'].disabled = true;
							oRows[rowToCompare].cells["startDate"].childNodes[0].focus();
							oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
							//oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
						}else{
							oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
							document.forms['formInsert'].elements['insertData'].disabled = false;
							document.forms['formInsert'].elements['deleteData'].disabled = false;
							document.forms['formInsert'].elements['confirmData'].disabled = false;
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
	
		var stDate = oRows[rowToCompare].cells["startDate"].childNodes[0].value;
		var endDate = oRows[rowToCompare].cells["endDate"].childNodes[0].value;
		var periodWork = oRows[rowToCompare].cells["periodWork"].childNodes[0].value;
		var yearWork = oRows[rowToCompare].cells["yearWork"].childNodes[0].value;
		var fiscalYear = oRows[rowToCompare].cells["fiscalYear"].childNodes[0].value;
		var flagPr = oRows[rowToCompare].cells["flagPr"].childNodes[0].value;
		var section = '<%=sectionInsert%>';
		var yearInsert = '<%=yearInsert%>';
		
		if(periodWork.length == 1){
			periodWork = '0'+periodWork;
		}
		
		var endDay = endDate.substr(0, 2);
		var endMonth = endDate.substr(3, 2);
		var endYear = endDate.substr(6);
		
		
		var monthWork = section.substr(0, 2);
		//alert(monthWork);
		//alert(endMonth);
		if(stDate != null && stDate != '' && endDate != null && endDate != ''){
			 	
			if(flagPr=='N' && endYear == yearInsert && endMonth > monthWork){
					//alert("��͹�����Թ��ǧ��÷ӧҹ 9");
					alert("��͹�����Թ��ǧ��÷ӧҹ");
					//alert("��͹����ͧ����Թ��͹ 9");
					document.forms['formInsert'].elements['insertData'].disabled = true;
					document.forms['formInsert'].elements['deleteData'].disabled = true;
					document.forms['formInsert'].elements['confirmData'].disabled = true;
					oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
					oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
					//oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
			}else if(endYear == yearInsert && (monthWork == endMonth) && (endDay > 15)){
					alert("�ѹ�������ش �����ԡ�Թ�ѹ��� 15 �ͧ��͹");
					document.forms['formInsert'].elements['insertData'].disabled = true;
					document.forms['formInsert'].elements['deleteData'].disabled = true;
					document.forms['formInsert'].elements['confirmData'].disabled = true;
					oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
					//oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
					oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
			}else if(fiscalYear != endYear){
			//if(endYear > yearInsert){
				alert("�ѹ�������ش �����ç�Ѻ�է�����ҳ");
				document.forms['formInsert'].elements['insertData'].disabled = true;
				document.forms['formInsert'].elements['deleteData'].disabled = true;
				document.forms['formInsert'].elements['confirmData'].disabled = true;
				oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
				oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
				//oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
			}else if (compareDates(stDate,"dd/MM/yyyy",endDate,"dd/MM/yyyy") == 1) {
				alert("�ѹ���������� ��ͧ���¡��� �ѹ�������ش");
				document.forms['formInsert'].elements['insertData'].disabled = true;
				document.forms['formInsert'].elements['deleteData'].disabled = true;
				document.forms['formInsert'].elements['confirmData'].disabled = true;
				//oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
				oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
				oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
			}else{
				document.forms['formInsert'].elements['insertData'].disabled = false;
				document.forms['formInsert'].elements['deleteData'].disabled = false;
				document.forms['formInsert'].elements['confirmData'].disabled = false;
			
			}
	 	}
	 	
	 	if(endDate != null && endDate != '' && (stDate == null || stDate == '')){
	 		if(flagPr == 'N' && endYear == yearInsert){	
			 	if(endMonth > monthWork){
					alert("�ѹ�������ش ��͹�����Թ��ǧ��÷ӧҹ");
					document.forms['formInsert'].elements['insertData'].disabled = true;
					document.forms['formInsert'].elements['deleteData'].disabled = true;
					document.forms['formInsert'].elements['confirmData'].disabled = true;
					oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
					oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
					//oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
				}else if((monthWork == endMonth) && (endDay > 15)){
					alert("�ѹ�������ش �����ԡ�Թ�ѹ��� 15 �ͧ��͹");
					document.forms['formInsert'].elements['insertData'].disabled = true;
					document.forms['formInsert'].elements['deleteData'].disabled = true;
					document.forms['formInsert'].elements['confirmData'].disabled = true;
					oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
					oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
					//oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
				}	
			
			}else if(fiscalYear != endYear){
			//)if(endYear > yearInsert){
				alert("�ѹ�������ش �����ç�Ѻ�է�����ҳ");
				document.forms['formInsert'].elements['insertData'].disabled = true;
				document.forms['formInsert'].elements['deleteData'].disabled = true;
				document.forms['formInsert'].elements['confirmData'].disabled = true;
				oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
				oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
			   // oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
			}else{
				document.forms['formInsert'].elements['insertData'].disabled = false;
				document.forms['formInsert'].elements['deleteData'].disabled = false;
				document.forms['formInsert'].elements['confirmData'].disabled = false;
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
		if(yearWork <= '<%=yearInsert%>'){
		
			//oRows[rowToCompare].cells["fiscalYear"].childNodes[0].value = yearWork;
			//oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
			//oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
		}else{
			alert('�էǴ����ԡ���١��ͧ');
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
	 	//	alert('��س��к��ѹ��軮Ժѵԧҹ����');
	 	
	 	//}
	 
	 	//oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
	 	//oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
	 	
	 	
	 
	 }
	 
	 function compareDateBeforSave(){
	 	//document.forms['formInsert'].elements['refNo'].focus();
	  			
					var ss = true ;
			     	DWRUtil.useLoadingMessage("Loading ...");
					var table = document.getElementById("table");
				
					var tdName;
					var chkName;
					
					var oRows = table.rows;
					for(var i=oRows.length-1;i > 2;i--){
							
								var stDate = oRows[i].cells["startDate"].childNodes[0].value;
								var endDate = oRows[i].cells["endDate"].childNodes[0].value;
								var periodWork = oRows[i].cells["periodWork"].childNodes[0].value;
								var yearWork = oRows[i].cells["yearWork"].childNodes[0].value;
								
								var fiscalYear = oRows[i].cells["fiscalYear"].childNodes[0].value;
								
								var totone = oRows[i].cells["totDay1"].childNodes[0].value;
								var tot15 = oRows[i].cells["totDay15"].childNodes[0].value;
								var tot3 = oRows[i].cells["totDay3"].childNodes[0].value;
								var spsone = oRows[i].cells["spsDay1"].childNodes[0].value;
								var sps15 = oRows[i].cells["spsDay15"].childNodes[0].value;
								var sps3 = oRows[i].cells["spsDay3"].childNodes[0].value;
								
								var sumtot = totone+tot15+tot3+spsone+sps15+sps3;
								var a = moment(stDate, 'DD/MM/YYYY');
								var b = moment(endDate, 'DD/MM/YYYY');
								var days = b.diff(a, 'days');
								
								if (days == 0) {
								       days=6;      
								}else{
								    days = days+1;
								    days = days*6;
								}
								
								
								//alert(a);
								//alert(b);
								//alert(days);
								
								
								if(periodWork.length == 1){
									periodWork = '0'+periodWork;
								}
								
								var strDay = stDate.substr(0, 2);
								var strMonth = stDate.substr(3, 2);
								var strYear = stDate.substr(6);
								
								var endDay = endDate.substr(0, 2);
								var endMonth = endDate.substr(3, 2);
								var endYear = endDate.substr(6);
								
								var section = '<%=sectionInsert%>';
								var yearInsert = '<%=yearInsert%>';
								var monthWork = section.substr(0, 2);
								
								if(stDate != null && stDate != '' && endDate != null && endDate != ''){
								
										if(endYear == yearInsert && endMonth > monthWork){
												 	ss = false;
													alert("��͹�����Թ��ǧ��÷ӧҹ");
													//alert("��͹����ͧ����Թ��͹ 9");
													document.forms['formInsert'].elements['insertData'].disabled = true;
													document.forms['formInsert'].elements['deleteData'].disabled = true;
													document.forms['formInsert'].elements['confirmData'].disabled = true;
													//oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
													break;
											}else if(endYear == yearInsert && (monthWork == endMonth) && (endDay > 15)){
													ss = false;
													alert("�����ԡ�Թ�ѹ��� 15 �ͧ��͹");
													document.forms['formInsert'].elements['insertData'].disabled = true;
													document.forms['formInsert'].elements['deleteData'].disabled = true;
													document.forms['formInsert'].elements['confirmData'].disabled = true;
													//oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
													break;
											}else if(fiscalYear != strYear){
												//alert('ASDF');
													ss = false;
													alert("���ѹ�������������ç�Ѻ�է�����ҳ");
													oRows[i].cells["startDate"].childNodes[0].value = '';
													document.forms['formInsert'].elements['insertData'].disabled = true;
													document.forms['formInsert'].elements['deleteData'].disabled = true;
													document.forms['formInsert'].elements['confirmData'].disabled = true;
													break;
											}else if(fiscalYear != endYear){
												//if(endYear < yearInsert){
													ss = false;
													alert("���ѹ�������ش���ç�Ѻ�է�����ҳ");
													oRows[i].cells["endDate"].childNodes[0].value = '';
													document.forms['formInsert'].elements['insertData'].disabled = true;
													document.forms['formInsert'].elements['deleteData'].disabled = true;
													document.forms['formInsert'].elements['confirmData'].disabled = true;
													break;
											}else if (compareDates(stDate,"dd/MM/yyyy",endDate,"dd/MM/yyyy") == 1) {
													ss = false;
													alert("�ѹ���������鹵�ͧ���¡��� �ѹ�������ش");
													document.forms['formInsert'].elements['insertData'].disabled = true;
													document.forms['formInsert'].elements['deleteData'].disabled = true;
													document.forms['formInsert'].elements['confirmData'].disabled = true;
													break;
											}else if (sumtot > days) {
													ss = false;
													alert("�ӹǹ���������� �Թ��˹� 6 ��. ����ѹ");
													document.forms['formInsert'].elements['insertData'].disabled = true;
													document.forms['formInsert'].elements['deleteData'].disabled = true;
													document.forms['formInsert'].elements['confirmData'].disabled = true;
													break;
												}
											
									
							 	}
							 	
							 if(ss){
								 	if((stDate == null || stDate == '') || (endDate == null || endDate == '')){
								 		
											ss = false;
											alert("��س��к��ѹ���������� ����ѹ�������ش");
											
											break;
										
								 	}
							 	}
							
				 	}
				 	
		return ss;
	 }
	 
	 function changeValue(){
	 	var table = document.getElementById("table");
			var tdName;
			var chkName;
			
			var oRows = table.rows;
			if(tdName == null)tdName="flag";
			if(chkName == null)chkName="chk";
			if(document.forms['formInsert'].elements['workFrom'].value == '2'){
				oRows[2].cells["spsDay1"].childNodes[0].disabled = true;
				oRows[2].cells["spsDay15"].childNodes[0].disabled = true;
				oRows[2].cells["spsDay3"].childNodes[0].disabled = true;
				
				oRows[2].cells["spsDay1"].childNodes[0].style.backgroundColor = 'silver';
				oRows[2].cells["spsDay15"].childNodes[0].style.backgroundColor = 'silver';
				oRows[2].cells["spsDay3"].childNodes[0].style.backgroundColor = 'silver';	
			}else{
				oRows[2].cells["spsDay1"].childNodes[0].disabled = false;
				oRows[2].cells["spsDay15"].childNodes[0].disabled = false;
				oRows[2].cells["spsDay3"].childNodes[0].disabled = false;
				
				
				oRows[2].cells["spsDay1"].childNodes[0].style.backgroundColor = '';
				oRows[2].cells["spsDay15"].childNodes[0].style.backgroundColor = '';
				oRows[2].cells["spsDay3"].childNodes[0].style.backgroundColor = '';	
					
			}
			
									
			
		
	 }
	 
	 function ChkType(obj){
	 	var val = obj.value;
	 	var table = document.getElementById("table");
			var tdName;
			var chkName;
			
			var oRows = table.rows;
	 	
	 	while (obj.tagName !=  'TR')
		 {
		 obj = obj.parentNode
		 }
		 
	 	 var rowChk = obj.rowIndex;
	 	
	 	 if(val == 'N'){
	 	 
	 	 		oRows[rowChk].cells["yearWork"].childNodes[0].value = '<%=yearInsert%>';
				oRows[rowChk].cells["periodWork"].childNodes[0].value = '<%=periodWork%>';
				oRows[rowChk].cells["startDate"].childNodes[0].value = '';
				oRows[rowChk].cells["endDate"].childNodes[0].value = '';
				
				
	 	 		oRows[rowChk].cells["yearWork"].childNodes[0].readOnly = true;
				oRows[rowChk].cells["periodWork"].childNodes[0].disabled = true;
				
				oRows[rowChk].cells["yearWork"].childNodes[0].style.backgroundColor = 'silver';
				oRows[rowChk].cells["periodWork"].childNodes[0].style.backgroundColor = 'silver';
				
	 	 
	 	 }else{
	 	 
	 	 		oRows[rowChk].cells["yearWork"].childNodes[0].readOnly = false;
				oRows[rowChk].cells["periodWork"].childNodes[0].disabled = false;
				
				oRows[rowChk].cells["yearWork"].childNodes[0].style.backgroundColor = '';
				oRows[rowChk].cells["periodWork"].childNodes[0].style.backgroundColor = '';
	 	 }
	 }
	 
	 function ChkFiscal(object){
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
	
		var fiscal = oRows[rowToCompare].cells["fiscalYear"].childNodes[0].value;
		if(fiscal <= '<%=yearInsert%>'){
		
			//oRows[rowToCompare].cells["fiscalYear"].childNodes[0].value = yearWork;
			oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
			oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
		}else{
			alert('�է�����ҳ���١��ͧ');
			oRows[rowToCompare].cells["fiscalYear"].childNodes[0].value = '';
			//oRows[rowToCompare].cells["fiscalYear"].childNodes[0].value = '' ;
		}
	 
	 }
	 
		function preIns(){ 
			var frm=document.forms["insertForm"];
			var cboOrgFrom = dojo.widget.byId("orgFromCbo");
			var cboOrgTo = dojo.widget.byId("orgToCbo")
			
			$("yearIns").value = $("year").value;
			$("sectionIns").value = $("section").value;
			$("periodIns").value = $("period").value;
			$("isConfirmIns").value = $("isConfirm").value;
			
			$("orgFromIns").value = cboOrgFrom.textInputNode.value;
			$("orgToIns").value = cboOrgTo.textInputNode.value;

			$("empCodeFromIns").value = $("empFrom").value;
		 	$("empCodeToIns").value = $("empTo").value;					
			$("pageIns").value = $("showPage").value;
			
			frm.submit();
		}
 	 function checkHour(object){
 	     
 	    document.forms['formInsert'].elements['insertData'].disabled = false;
		document.forms['formInsert'].elements['deleteData'].disabled = false;
		document.forms['formInsert'].elements['confirmData'].disabled = false;
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
	
	
		
		if((totone%1)!= 0 ){
			alert('�ӹǹ���������ͧ�繨ӹǹ�����ҹ��');
			oRows[rowToCompare].cells["totDay1"].childNodes[0].value = '';
		
		}
		if((tot15%1)!= 0 ){
			alert('�ӹǹ���������ͧ�繨ӹǹ�����ҹ��');
			oRows[rowToCompare].cells["totDay15"].childNodes[0].value = '';
		
		}
		if((tot3%1)!= 0 ){
			alert('�ӹǹ���������ͧ�繨ӹǹ�����ҹ��');
			oRows[rowToCompare].cells["totDay3"].childNodes[0].value = '';
		
		}
		if((spsone%1)!= 0 ){
			alert('�ӹǹ���������ͧ�繨ӹǹ�����ҹ��');
			oRows[rowToCompare].cells["spsDay1"].childNodes[0].value = '';
		
		}
		if((sps15%1)!= 0 ){
			alert('�ӹǹ���������ͧ�繨ӹǹ�����ҹ��');
			oRows[rowToCompare].cells["spsDay15"].childNodes[0].value = '';
		
		}
		if((sps3%1)!= 0 ){
			alert('�ӹǹ���������ͧ�繨ӹǹ�����ҹ��');
			oRows[rowToCompare].cells["spsDay3"].childNodes[0].value = '';
		
		}
		
	 
	 }
-->
</script>
</head>

<body>
<form name="formInsert"  action="security.htm?reqCode=CTWPAYMT003" method="post">
<input type="hidden" name="dataLength">
<input type="hidden" name="codeSeq">
<input type="hidden" name="codeSeqNew">
<input type="hidden" name="period">
<input type="hidden" name="empHide">
<table width="100%">
	<tr>
		<td class="font-head">
			[ CTWPAYIN003 ] �ѹ�֡�����ǧ����/��ҷӧҹ��ѹ��ش�ѡ��͹
		</td>
	</tr>
</table>
<table width="950" border="0" cellspacing="0" cellpadding="0" align="center">
      <tr>
          <td  align="center">
				<table width="750" border="1" cellpadding="1" bordercolor="#6699CC" cellspacing="1"  align="center">
        			<tr>
          				<td class="font-field"  align="right" width="100">��Шӻ�</td>
          				<td align="left"><input type="text" name="year" value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/></td>
          				<td class="font-field" align="left" width="250">
          					&nbsp;&nbsp;�Ǵ&nbsp;&nbsp;
          					<input type="text" name="section" value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/>
          				</td>
        			</tr>
        			
        			<tr>
         				<td class="font-field"  align="right" width="100">�Ţ��� �.1</td>
          				<td colspan="3"  align="left"><input type="text" name="refNo"  style="width:638"/></td>
          			</tr>
        			<tr>
        				<td class="font-field" align="right" >�������ͧ�ҹ&nbsp;</td>
					    <td align="left" colspan="3">
					    	<select name="workFrom" style="width:200" onchange="changeValue();">
									<option value="1" >�����ǧ����</option>
									<option value="2" >��ҷӧҹ��ѹ��ش�ѡ��͹</option>
							</select>
						</td>
		        	</tr>
     		 </table>
          </td>
      </tr>
    	&nbsp;
	 	&nbsp;
	  	&nbsp;
	  <tr>
          <td align="center">
				<div style="height:315px;width:1000;overflow:auto;">
				 &nbsp;
	  			&nbsp;
	  			&nbsp;
				<table id="table"  bordercolor="#0f1011" align="center" style="text-align: center;width:2200px;" border="1" cellpadding="0" cellspacing="0" >
					<thead style="text-align: center">
						<tr CLASS="TABLEBULE2">
							<th CLASS="TABLEBULE2" style="width:50px" rowspan="2" align="center">ź</th>
							<th CLASS="TABLEBULE2"  rowspan="2" align="center">��������¡��</th>
							<th CLASS="TABLEBULE2" style="width:100px" rowspan="2">�Ţ��Шӵ��</th>
							<th CLASS="TABLEBULE2" style="width:200px" rowspan="2">���� - ���ʡ��</th>
							<th CLASS="TABLEBULE2" style="width:500px" rowspan="2" colspan="2">�ѧ�Ѵ��Ժѵԧҹ</th>
							<th CLASS="TABLEBULE2" style="width:150px"  colspan="2" rowspan="2" align="center">��/�Ǵ����ԡ</th>
							<th CLASS="TABLEBULE2" style="width:100px"  rowspan="2" align="center">�է�����ҳ</th>
							<th CLASS="TABLEBULE2" style="width:100px"  rowspan="2" align="center">�ӹǹ��������ӧҹ����</th>
							<th CLASS="TABLEBULE2" style="width:200px"  colspan="2" rowspan="1" align="center"><center>�ѹ��軮Ժѵԧҹ</center></th>
							<th CLASS="TABLEBULE2" style="width:180px"  colspan="3" align="center">�ӹǹ�������</th>
							<th CLASS="TABLEBULE2" style="width:180px"  colspan="3" align="center">�ӹǹ������� �ѹ��ش��Шӻ�</th>
							<th CLASS="TABLEBULE2" style="width:40px"  rowspan="2" align="center">�ӴѺ���</th>
						</tr>
						<tr CLASS="TABLEBULE2"  >
							<th CLASS="TABLEBULE2" style="width:100px"  align="center">������� </th>
							<th CLASS="TABLEBULE2" style="width:100px"  align="center">����ش</th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">1.5 ��� </th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">1 ��� </th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">2 ��� </th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">1.5 ��� </th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">1 ��� </th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">2 ��� </th>
						
						</tr>
					</thead>
					<tbody>			
						<tr id="temprow" style="visibility:hidden;position:absolute">
							<td id="flag"><input type="checkbox" name="chk" /></td>
							<td id="flagPr" width="50" >
								<select name="type" onchange="ChkType(this);">
									<option value="N" >����</option>
									<option value="A" >��Ѻ��ا��¡���Ѻ</option>
									<option value="R" >��¡���Ѻ���¡�׹</option>
									<!-- <option value="B" >���ԡ��Ѻ��ا��¡���Ѻ</option>
									<option value="S" >���ԡ��¡���Ѻ���¡�׹</option> -->
								</select>
							</td>
							
							<td id="empCode" align="center"><input type="text" maxlength="6" name="empCode" style="width:100%" onblur="whenSelectEmpOptionInRow(this);" onkeydown="whenNewEmpNo();"  onkeyup="if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);whenCheck();"></td>
							<td id="name" align="center"><input type="text"  name="name" readonly="readonly" style="width:100%;background-color:silver;"/><input type="hidden"  name="codeSeq" /></td>
							<td id="orgCode" align="center" style="width:150px"><input type="text" maxlength="20" name="orgCode" style="width:100%" onchange="whenFindOrganization(this);"  onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"><input type="hidden"  name="codeSeqNew" /></td>
							<td id="nameOrg" align="center" style="width:350px"><input type="text"  name="nameOrg" readonly="readonly" style="width:80%;background-color:silver;"/><input type="text"  name="inKong" readonly="readonly" style="width:20%;background-color:silver;"/></td>
							<td id="yearWork" align="center" width="60" ><input type="text" value="<%=yearInsert%>" name="yearWork" onchange="bypass(this);"  maxlength="4" align="center" readonly="readonly" style="text-align:center;width:100%;background-color:silver;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="periodWork" align="center" width="60"><select name="periodWork" style="width:100%;background-color:silver;" onchange="bypass(this);" disabled="disabled">
													<option value="1" <% if(periodWork.equals("1")){%> selected="selected"  <%} %> >���Ҥ�</option>
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
							<td id="fiscalYear" align="center" ><input type="text" value="<%=yearInsert%>"  name="fiscalYear" onchange="ChkFiscal(this)"  maxlength="4" align="center" style="text-align:center;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<!--<td id="startDate" align="center" ><input type="text" value="<%=date%>"  name="startDate"  maxlength="10" align="center" style="text-align:center;width:100%;" onchange="return validateDate(this) && compareStDate(this);" ></td>
							<td id="endDate" align="center" ><input type="text"  value="<%=date%>" name="endDate"  maxlength="10" align="center" style="text-align:center;width:100%;" onchange="return validateDate(this) && compareEndDate(this);"  ></td>-->
							<td id="workHour" width="100" >
								<select name="type" ;">
									<option value="6" >6</option>
									<option value="6.5" >6.5</option>
									<option value="7" >7</option>
									<!-- <option value="B" >���ԡ��Ѻ��ا��¡���Ѻ</option>
									<option value="S" >���ԡ��¡���Ѻ���¡�׹</option> -->
								</select>
							</td>
							<td id="startDate" align="center" ><input type="text"  name="startDate"  maxlength="10" align="center" style="text-align:center;width:100%;" onchange="return validateDate(this) && compareStDate(this);" ></td>
							<td id="endDate" align="center" ><input type="text"   name="endDate"  maxlength="10" align="center" style="text-align:center;width:100%;" onchange="return validateDate(this) && compareEndDate(this);"  ></td>
							<td id="totDay15" align="center" ><input type="text"  name="totDay15"  maxlength="3" align="center" style="text-align:right;width:100%;"  onchange="return checkHour(this);"  onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="totDay1" align="center" ><input type="text"  name="totDay1"  maxlength="3" align="center" style="text-align:right;width:100%;"   onchange="return checkHour(this);" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" /></td>
							<td id="totDay3" align="center" ><input type="text"  name="totDay3"  maxlength="3" align="center" style="text-align:right;width:100%;"  onchange="return checkHour(this);" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="spsDay15" align="center" ><input type="text"  name="spsDay15"  maxlength="3" align="center" style="text-align:right;width:100%;"   onchange="return checkHour(this);" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="spsDay1" align="center" ><input type="text"  name="spsDay1"  maxlength="3" align="center" style="text-align:right;width:100%;" onchange="return checkHour(this);" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" /></td>
							<td id="spsDay3" align="center" ><input type="text"  name="spsDay3"  maxlength="3" align="center" style="text-align:right;width:100%;"   onchange="return checkHour(this);" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="seqData" align="center" ><input type="text"  name="seqData"  maxlength="3" align="center" style="text-align:right;width:100%;"   onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/><input type="hidden"  name="keySeq" /></td>
						</tr>
						</tbody>		
					</table>
				</div>
           </td>
      </tr>
          
</table>&nbsp;

<input type="hidden" name="pageEdit" value="">
<table width="100%" CLASS="TABLEBULE2" >
					<tr CLASS="TABLEBULE2" >
						<td align="left" >&nbsp;
							<input type="Button" class=" button " value="����������" name="insertData" onclick="addRow();" />						
							<input type="Button" class=" button " value="ź������" name="deleteData" onclick="removeVisualRow();" />						
							<input type="Button" class=" button " value=" ��ŧ  " name="confirmData" onclick="insertNewData();" />
							<input type="Button" class=" button " value=" �͡  " name="back" onclick="backFormMain();"/>
						</td>
					</tr>
				</table>
</form>
<form name="backForm" action="security.htm?reqCode=CTWPAYMT003" method="post">
		<input type="hidden" name="orgFromEdit" />
		<input type="hidden" name="orgToEdit" />
		<input type="hidden" name="empCodeFromEdit" />
		<input type="hidden" name="empCodeToEdit" />
		<input type="hidden" name="pageEdit" />
		<input type="hidden" name="orderFromCboEdit" />
		<input type="hidden" name="orderToCboEdit" />
		<input type="hidden" name="refNoFromEdit" />
		<input type="hidden" name="refNoToEdit" />
		<input type="hidden" name="otTypeEdit" />
		<input type="hidden" name="mustQuery" />
	</form>

</body>
<script type="text/javascript">
		
</script>
</html>

<SCRIPT LANGUAGE="JavaScript">
<!--



function addVisualRow(data)
{
	if(data.length==0)
    	{
    		alert("�ѧ�Ѵ��Ժѵԧҹ���١��ͧ");
    	}else
    	{
	
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
			
			var oRows = table.rows;
			var i = oRows.length-1;
			if(oRows.length > 3){
				countKey++;
				oRows[i].cells["seqData"].childNodes[1].value = countKey; 
			}
			
	}
}

function addRow(){

	document.forms['formInsert'].elements['workFrom'].disabled = true;
	var table = document.getElementById("table");
		var tdName;
		var chkName;
		
		var oRows = table.rows;
		if(oRows.length > 3){
			var i = oRows.length-1;
			//alert(oRows[i].cells["empCode"].childNodes[0].value);
			var orgCode = oRows[i].cells["orgCode"].childNodes[0].value;
		
	    	VPnOrganizationSecurityService.findByKeyPK('<%=ouCodeInsert%>','<%=userIdInsert%>', orgCode,{callback:addVisualRow,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
		
		}else{
		
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
			
			var oRows = table.rows;
			var i = oRows.length-1;
			if(oRows.length > 3){
				countKey++;
				oRows[i].cells["seqData"].childNodes[1].value = countKey; 
			}
		
		}


}
function removeVisualRow(){
	var table = document.getElementById("table");
	var tdName;
	var chkName;
	
	var oRows = table.rows;
	if(tdName == null)tdName="flag";
	if(chkName == null)chkName="chk";
	if(oRows.length > 3){
		var answer = confirm("��ͧ���ź������ �������?");
		if( answer ){
			for(i=oRows.length-1;i>1;i--){
				if (oRows[i].cells[tdName].childNodes[0].checked){
						table.deleteRow(i);			
				}
			}
			alert('ź���������º����');
		}
	}
}



//-->
</SCRIPT>