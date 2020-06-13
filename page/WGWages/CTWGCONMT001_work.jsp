<%@ page language="java" contentType="text/html;charset=TIS-620"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>


<%
	Calendar now = Calendar.getInstance(Locale.US);
	UserInfo userInfo = (UserInfo)session.getAttribute("UserLogin");
	String userId = userInfo.getUserId();
	String ouCode = userInfo.getOuCode();
    java.util.Date dd = new java.util.Date();
	java.text.SimpleDateFormat fmd = new java.text.SimpleDateFormat("dd/MM/yyyy", new java.util.Locale("th", "TH"));
	String date = fmd.format(dd);
	String orgFromEdit = request.getParameter("orgFromEdit");
	String orgToEdit = request.getParameter("orgToEdit");
	String yearFromEdit = request.getParameter("yearFromEdit");
	String yearToEdit = request.getParameter("yearToEdit");
	String conCodeFromEdit = request.getParameter("conCodeFromEdit");
	String conCodeToEdit = request.getParameter("conCodeToEdit");
	String mustQuery = request.getParameter("mustQuery");
	String pageEdit = request.getParameter("pageEdit")==null?"-1":request.getParameter("pageEdit");
	
%>
<html>
	<head>
		<title>สอบถามข้อมูลประจำเดือนของลูกจ้าง</title>
		<!-- Include -->
		<script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript" src="dwr/util.js"></script>
		<script type="text/javascript" src="script/payroll_util.js"></script>
		
		<!-- Javascript Script File -->
		<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrPeriodLineService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPnEmployeeService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrEmployeeService.js"></SCRIPT>


		<script type="text/javascript" src="script/gridScript.js"></script>
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
			

			function onLoadOrganization(){
		     	try{
			     	var cboSource = [];
			     	var cboFrom = dojo.widget.byId("orgFromCbo");
			     	var cboTo = dojo.widget.byId("orgToCbo");
			     	
			     	<c:forEach items="${OrganizationInSecurity}" var="result" >		 
						cboSource.push(["<c:out value='${result.orgCode} ${result.divShort} ${result.areaDesc} ${result.secDesc} ${result.workDesc}' />","<c:out value='${result.orgCode}' />"]);
					</c:forEach>
					
			     	cboFrom.dataProvider.setData(cboSource);
			     	cboTo.dataProvider.setData(cboSource);
			 
			   }catch(e){
		     		alert(e.message);
		     	}
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
		    function onLoadDateFromCallback()
    {
    	try
    	{
	    	var cboSourceFrom = [];
	     	var cboFrom = dojo.widget.byId("dateCboFrom");
	     	
	     
			     	
			     	<c:forEach items="${ResultInSecurity}" var="result" >		 
						cboSourceFrom.push(["<c:out value='${result}' />","<c:out value='${result}' />"]);
					</c:forEach>
	     	
	     	         cboFrom.dataProvider.setData(cboSourceFrom);
	     	     
	     
	   
     	}catch(e)
     	{
     		alert(e.message);
    	}
	}    
   function whenSelectDateFromOption()
	{
    	DWRUtil.useLoadingMessage("Loading ...");
		var cbo = dojo.widget.byId("dateCboFrom");
		whenFetchDateFrom(cbo.textInputNode.value);
	}
	
	function whenFetchDateFrom(dateF)
	{
     	DWRUtil.useLoadingMessage("Loading ...");
     	var cboFrom = dojo.widget.byId("dateCboFrom");
		     	
		    
		     	
		     	var cboSourceFrom = [];
		     	
		     	<c:forEach items="${ResultInSecurity}" var="result" >			     	
						cboSourceFrom.push(["<c:out value='${result}' />","<c:out value='${result}' />"]);
				</c:forEach>	
		     	
		     	cboFrom.dataProvider.setData(cboSourceFrom);
     	
    }
		
		
	        function onLoadYearConCallback(){
		     	try{
			     	var cboSource = [];
			     	var cboFrom = dojo.widget.byId("yearFromCbo");
			     	var cboTo = dojo.widget.byId("yearToCbo");
			     	
			     	<c:forEach items="${YearContactInSecurity}" var="result" >	
			     	    cboSource.push(["<c:out value='${result}' />","<c:out value='${result}' />"]);						
					</c:forEach>
					
			     	cboFrom.dataProvider.setData(cboSource);
			     	cboTo.dataProvider.setData(cboSource);
			 
			   }catch(e){
		     		alert(e.message);
		     	}
		     }
		     
		      // Even ComboBox valueChange
		    function whenSelectYearOption(){
		    	DWRUtil.useLoadingMessage("Loading ...");
				var cbo = dojo.widget.byId("yearFromCbo");
				whenFetchYearContactTo(splitCombo(cbo.textInputNode.value));
			}
			function whenFetchYearContactTo(yearCon){
		     	DWRUtil.useLoadingMessage("Loading ...");
		     	var cboTo = dojo.widget.byId("yearToCbo");
		     	
		     	if( yearCon > splitCombo(cboTo.textInputNode.value) ){
			     	cboTo.textInputNode.value = '';
			     	cboTo.comboBoxSelectionValue.value = '';
		     	}
		     	
		     	var cboSource = [];
		     	
		     	<c:forEach items="${YearContactInSecurity}" var="result" >		 
					if( <c:out value='${result}' /> >= yearCon )
						 cboSource.push(["<c:out value='${result}' />","<c:out value='${result}' />"]);
				</c:forEach>
		     	
		     	cboTo.dataProvider.setData(cboSource);
		    }	
		
		
			/****** Begin  ConCodeFrom Select ********/
			function whenConFromSelectOption(){
				DWRUtil.useLoadingMessage("Loading ...");
				var cbo = dojo.widget.byId("conFromCbo");
				whenFetchContactTo(cbo.textInputNode.value);
			}
			function whenFetchContactTo(contractNo){
		     	DWRUtil.useLoadingMessage("Loading ...");
		     	var cboTo = dojo.widget.byId("conToCbo");
		     	
		     	if( contractNo > cboTo.comboBoxSelectionValue.value ){
			     	cboTo.textInputNode.value = '';
			     	cboTo.comboBoxSelectionValue.value = '';
		     	}
		     	
		     	var cboSource = [];
		     	
		     	<c:forEach items="${ContactInSecurity}" var="result" >	
		     		 
		     		var tmp = '<c:out value="${result}" />'+'.00';
		     		if( parseFloat(tmp.toString()) >= parseFloat(contractNo) )
						cboSource.push(["<c:out value='${result}' />","<c:out value='${result}' />"]);
				</c:forEach>	
		     	
		     	cboTo.dataProvider.setData(cboSource);
		     	
		     	
		     	//PnEmployeeService.findPrToEmpBySecurity('<%=userId%>','<%=ouCode%>',empCode,$("year").value,$("period").value,{callback:whenFetchEmployeeToCallback});
		    }
		    function whenFetchContactToCallback(data){
		    	try{
			     	var cboSource = [];
			     	var cboTo = dojo.widget.byId("conToCbo");
			     	for(var i=0; i<data.length; i++){
			     		var con = data[i];
			     		cboSource.push([con.contractNo, con.contractNo]);
			     	}
			     	cboTo.dataProvider.setData(cboSource);
		     	}catch(e){
		     		alert(e.message);
		     	}
		    }
		    /******  End conCodeFrom Select ********/
			
			function onLoadContactCallback(){
		     	try{
		     	//	DWRUtil.useLoadingMessage("Loading ...");
			     	var cboSource = [];
			     	var cboFrom = dojo.widget.byId("conFromCbo");
			     	var cboTo = dojo.widget.byId("conToCbo");
			     	/*for(var i=0; i<data.length; i++){
			     		var con = data[i];
			     		cboSource.push([con.contractNo, con.contractNo]);
			     	}*/
			     	
			     	<c:forEach items="${ContactInSecurity}" var="result" >		 
						cboSource.push(["<c:out value='${result}' />","<c:out value='${result}' />"]);
					</c:forEach>
					
			     	cboFrom.dataProvider.setData(cboSource);
			     	cboTo.dataProvider.setData(cboSource);
			     	
			     	<%
		     		if(mustQuery != null && mustQuery.endsWith("true") ){
													
		     	%>
			     	
			     	<%
			     		if( (orgFromEdit != null && !orgFromEdit.trim().equals("")) 
			     			|| (orgToEdit != null && !orgToEdit.trim().equals("")) 
			     			|| (yearFromEdit != null && !yearFromEdit.trim().equals("")) 
			     			|| (yearToEdit != null && !yearToEdit.trim().equals("")) 
			     			|| (conCodeFromEdit != null && !conCodeFromEdit.trim().equals("")) 
			     			|| (conCodeToEdit != null && !conCodeToEdit.trim().equals("")) 
			     			 ){
							
							
			     	%>
			     	
			     			var cboOrgFrom = dojo.widget.byId("orgFromCbo");
			     			var cboOrgTo = dojo.widget.byId("orgToCbo");
			     			var cboYearFrom = dojo.widget.byId("yearFromCbo");
			     			var cboYearTo = dojo.widget.byId("yearToCbo");
			     			var cboConFrom = dojo.widget.byId("conFromCbo");
			     			var cboConTo = dojo.widget.byId("conToCbo");
			     			
			     			cboOrgFrom.textInputNode.value = '<%= orgFromEdit %>';
			     			cboOrgTo.textInputNode.value = '<%= orgToEdit %>';
			     			cboYearFrom.textInputNode.value = '<%= yearFromEdit %>';
			     			cboYearTo.textInputNode.value = '<%= yearToEdit %>';
			     			cboConFrom.textInputNode.value = '<%= conCodeFromEdit %>';
			     			cboConTo.textInputNode.value = '<%= conCodeToEdit %>';
			     			$("page").value = '<%= pageEdit %>';
			     			
			     			whenQueryCon();
			     			
			     	<%
			     		}
			     	%>
			     	
			     	<%
		     		mustQuery = "false";
		     		}
		     	%>
			     	
		     	}catch(e){
		     		alert(e.message);
		     	}
		     } 
		     
		    function whenQueryConBefore()
		    {
		    	$("page").value = 0;
		    	whenQueryCon();
		    } 
		     
		    function whenQueryCon(){
		    	DWRUtil.useLoadingMessage("Loading ...");
		    	
		    	var	orgFromCbo = dojo.widget.byId("orgFromCbo");
				var	orgToCbo = dojo.widget.byId("orgToCbo");
				var	yearFromCbo = dojo.widget.byId("yearFromCbo");
				var	yearToCbo = dojo.widget.byId("yearToCbo");
		     	var	conFromCbo = dojo.widget.byId("conFromCbo");
				var	conToCbo = dojo.widget.byId("conToCbo");
				var dateCboFrom = dojo.widget.byId("dateCboFrom");
				
				
				var orgFromVal = '';
				var orgToVal = '';
				var yearFromVal = '';
				var yearToVal = '';
				var conFromVal = '';
				var conToVal = '';
				var dateFromVal = '';
	
							
			
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
				
				if(yearFromCbo.textInputNode.value != ''){
					yearFromVal = splitCombo( yearFromCbo.textInputNode.value );
				}else{
					yearFromVal = '';
				}
				
				if(yearToCbo.textInputNode.value != ''){
					yearToVal = splitCombo( yearToCbo.textInputNode.value );
				}else{
					yearToVal = '';
				}
				
				if(conFromCbo.textInputNode.value != ''){
					conFromVal = conFromCbo.textInputNode.value;
				}else{
					conFromVal = '';
				}
				
				if(conToCbo.textInputNode.value != ''){
					conToVal = conToCbo.textInputNode.value;
				}else{
					conToVal = '';
				}
				
				if(dateCboFrom.textInputNode.value != ''){
					dateFromVal = splitCombo( dateCboFrom.textInputNode.value );
				}else{
					dateFromVal = '';
				}
				var page = '';
				if( parseInt( $("page").value ) == -1 ){
					page = 0;
					$("page").value = parseInt($("page").value) + 1;
				}else
					page = $("page").value;
				
				
				
				//FeeWpayPrEmployeeService.findByCriteriaWGCONMT001(
		     		//'<%= ouCode %>', 
		     	//	'<%= userId %>',
		     	//	orgFromVal,
				//	orgToVal,
				//	yearFromVal,
				//	yearToVal,
				//	conFromVal,
				//	conToVal,
				//	page,
			//		DWRUtil.getValue("dataPerPage"),
				//	{callback:whenQueryConCallBack}
			//	);
				
				FeeWpayPrEmployeeService.findByCriteriaWGCONMT001(
		     		'<%= ouCode %>', 
		     		'<%= userId %>',
		     		orgFromVal,
					orgToVal,
					yearFromVal,
					yearToVal,
					conFromVal,
					conToVal,
					dateFromVal,
					page,
					DWRUtil.getValue("dataPerPage"),
					{callback:whenQueryConCallBack}
				);
				
		    }
		    function whenQueryConCallBack(data){
		     	//alert(data.length);
		     	if(data.length > 0){
					DWRUtil.removeAllRows("dataTable");
					DWRUtil.addRows("dataTable",data,cellFuncs);
					if(DWRUtil.getValue("showMaxPage")==""){
						countData();
					}else{
						onCheckButt("searchForm");
					}
				}else{
					alert('ไม่พบข้อมูล');
					DWRUtil.removeAllRows("dataTable");
					if(DWRUtil.getValue("showMaxPage")==""){
						countData();
					}else{
						onCheckButt("searchForm");
					}
				}
		    }
		    
		    function countData(){
		    	DWRUtil.useLoadingMessage("Loading ...");
		     	var	orgFromCbo = dojo.widget.byId("orgFromCbo");
				var	orgToCbo = dojo.widget.byId("orgToCbo");
				var	yearFromCbo = dojo.widget.byId("yearFromCbo");
				var	yearToCbo = dojo.widget.byId("yearToCbo");
		     	var	conFromCbo = dojo.widget.byId("conFromCbo");
				var	conToCbo = dojo.widget.byId("conToCbo");
				var dateCboFrom = dojo.widget.byId("dateCboFrom");
				
				
				var orgFromVal = '';
				var orgToVal = '';
				var yearFromVal = '';
				var yearToVal = '';
				var conFromVal = '';
				var conToVal = '';
				var dateFromVal = '';
				
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
				
				if(yearFromCbo.textInputNode.value != ''){
					yearFromVal = splitCombo( yearFromCbo.textInputNode.value );
				}else{
					yearFromVal = '';
				}
				
				if(yearToCbo.textInputNode.value != ''){
					yearToVal = splitCombo( yearToCbo.textInputNode.value );
				}else{
					yearToVal = '';
				}
				
				if(conFromCbo.textInputNode.value != ''){
					conFromVal = conFromCbo.textInputNode.value;
				}else{
					conFromVal = '';
				}
				
				if(conToCbo.textInputNode.value != ''){
					conToVal = conToCbo.textInputNode.value;
				}else{
					conToVal = '';
				}
				if(dateCboFrom.textInputNode.value != ''){
					dateFromVal = splitCombo( dateCboFrom.textInputNode.value );
				}else{
					dateFromVal = '';
				}
				
				FeeWpayPrEmployeeService.getCountByCriteriaWGCONMT001(
		     		'<%= ouCode %>', 
		     		'<%= userId %>',
		     		orgFromVal,
					orgToVal,
					yearFromVal,
					yearToVal,
					conFromVal,
					conToVal,
					dateFromVal,
					{callback:whenCountDataCallBack}
				);
		    }
		    function whenCountDataCallBack(data){
		    	DWRUtil.setValue("countData",data);
				onCheckButt("searchForm");
		    }
			
			var cellFuncs = [
				function(data) { return "<div align='center'>"+data.contractNo+"</div>";},
				function(data) { return data.yearCon;},
				//function(data) { return "<div align='center'>"+data.positionShort+"</div>";},
				function(data) { 
								if(data.orgCode != null && data.orgDesc != null){
									return "<div align='left'>"+data.orgCode+" "+data.orgDesc+"</div>";
								}else if(data.orgCode != null && data.orgDesc == null){
									return "<div align='left'>"+data.orgCode+"</div>";
								}else{
									return "<div align='left'>&nbsp;</div>";
								}
								},
				function(data) { return "<div align='center'>"+data.inactive+"</div>";},
				function(data) { return writeEditButton("edit",data.yearCon,data.contractNo,data.orgCode);},
				function(data) { return writeAddButton("add",data.yearCon,data.contractNo,data.orgCode);}
			];
		
			function writeEditButton(inname,ycon,con,code)
			{
				return "<div align='center'><input type='button' class='button' name = '"+inname+"' value='...' onclick='preEdit(this.yconId,this.conId,this.code);' yconId ='"+ycon+"' conId='"+con+"'  code='"+code+"' /></div>";
			}
			
			function writeAddButton(inname,ycon,con,code)
			{
				return "<div align='center'><input type='button' class='button' name = '"+inname+"' value='...' onclick='preAdd(this.yconId,this.conId,this.code);' yconId ='"+ycon+"' conId='"+con+"'  code='"+code+"' /></div>";
			}
			
			
			function preAdd(yconId,conId,codeSeq){ 
				var frm=document.forms["addForm"];
				DWRUtil.setValue("yearConAdd",yconId);
				DWRUtil.setValue("conCodeAdd",conId);
				DWRUtil.setValue("codeSeqAdd",codeSeq);
				
				var cboOrgFrom = dojo.widget.byId("orgFromCbo");
				var cboOrgTo = dojo.widget.byId("orgToCbo");
				var cboYearFrom = dojo.widget.byId("yearFromCbo");
				var cboYearTo = dojo.widget.byId("yearToCbo");
				var cboConFrom = dojo.widget.byId("conFromCbo");
				var cboConTo = dojo.widget.byId("conToCbo");
				
				$("orgFromAdd").value = cboOrgFrom.textInputNode.value;
				$("orgToAdd").value =cboOrgTo.textInputNode.value;
				$("yearFromAdd").value = cboYearFrom.textInputNode.value;
				$("yearToAdd").value =cboYearTo.textInputNode.value;
				
				$("conCodeFromAdd").value = cboConFrom.textInputNode.value;
			 	$("conCodeToAdd").value = cboConTo.textInputNode.value;
			 	
			 	$("flagAdd").value = 'MT';
			 	
			 	$("pageAdd").value = $("showPage").value;
				
				frm.submit();
			}
			
			function preEdit(yconId,conId,codeSeq){ 
				var frm=document.forms["editForm"];
				DWRUtil.setValue("yearConQuery",yconId);
				DWRUtil.setValue("conCodeQuery",conId);
				DWRUtil.setValue("codeSeqQuery",codeSeq);
				
				var cboOrgFrom = dojo.widget.byId("orgFromCbo");
				var cboOrgTo = dojo.widget.byId("orgToCbo");
				var cboYearFrom = dojo.widget.byId("yearFromCbo");
				var cboYearTo = dojo.widget.byId("yearToCbo");
				var cboConFrom = dojo.widget.byId("conFromCbo");
				var cboConTo = dojo.widget.byId("conToCbo");
				
				$("orgFromEdit").value = cboOrgFrom.textInputNode.value;
				$("orgToEdit").value =cboOrgTo.textInputNode.value;
				$("yearFromEdit").value = cboYearFrom.textInputNode.value;
				$("yearToEdit").value =cboYearTo.textInputNode.value;
				
				$("conCodeFromEdit").value = cboConFrom.textInputNode.value;
			 	$("conCodeToEdit").value = cboConTo.textInputNode.value;
			 	
			 	$("flagEdit").value = 'MT';
			 	
			 	$("pageEdit").value = $("showPage").value;
				
				frm.submit();
			}
			
			function preIns(){ 
				var frm=document.forms["insertForm"];
				var cboOrgFrom = dojo.widget.byId("orgFromCbo");
				var cboOrgTo = dojo.widget.byId("orgToCbo");
				var cboYearFrom = dojo.widget.byId("yearFromCbo");
				var cboYearTo = dojo.widget.byId("yearToCbo");
				
				var cboConFrom = dojo.widget.byId("conFromCbo");
				var cboConTo = dojo.widget.byId("conToCbo");
				
				$("orgFromIns").value = cboOrgFrom.textInputNode.value;
				$("orgToIns").value =cboOrgTo.textInputNode.value;
				
				$("yearFromIns").value = cboYearFrom.textInputNode.value;
				$("yearToIns").value =cboYearTo.textInputNode.value;
				
				$("conCodeFromIns").value = cboConFrom.textInputNode.value;
			 	$("conCodeToIns").value = cboConTo.textInputNode.value;
			 	
			 	$("flagIns").value = 'MT';
			 	
			 	$("pageIns").value = $("showPage").value;
					frm.submit();
			}
			
			
			
			function init(){
				var cboConFrom = dojo.widget.byId("conFromCbo");
				var cboYearFrom = dojo.widget.byId("conYearCbo");
				//var cboDateFrom = dojo.widget.byId("dateCboFrom");
				dojo.event.connect(cboConFrom, "selectOption", "whenConFromSelectOption");
				dojo.event.connect(cboYearFrom, "selectOption", "whenYearFromSelectOption");
				//dojo.event.connect(cboDateFrom, "selectOption", "whenSelectDateFromOption");
				onLoadOrganization();
			}
		    
		    dojo.addOnLoad(init);
		    //dojo.addOnLoad(onLoadYearSectionCallback);
		    dojo.addOnLoad(onLoadYearConCallback);
		    dojo.addOnLoad(onLoadContactCallback);
		    dojo.addOnLoad(onLoadDateFromCallback);
		
		</script>

	</head>
	<body>
		<form name="searchForm" action="" method="post">
		<table width="100%">
			<tr>
				<td class="font-head">
					[ CTWGCONMT001 ] บันทึกข้อมูลสัญญาการจ้างเหมา
				</td>
			</tr>
		</table>
			<table border="0" align="center" width="850px" cellspacing="1">
				<tr>
    				<td class="font-field" width="150px" align="right">ตั้งแต่สังกัดปฎิบัติงานจริง&nbsp;</td>
    				<td align="left" colspan="4"><SELECT  dojoType="ComboBox" widgetId="orgFromCbo" style="width:663px" onBlurInput="whenSelectOrgOption();"></td>
				</tr>
				<tr>
					<td class="font-field" width="150px" align="right" >ถึงสังกัดปฎิบัติงานจริง</td>
				    <td align="left" colspan="4"><SELECT  dojoType="ComboBox" widgetId="orgToCbo" style="width:663px"></td>
				</tr>
				<tr>
    				<td class="font-field" align="right">ตั้งแต่ปีเริ่มสัญญา&nbsp;</td>
    				<td align="left" width="150px"><SELECT  dojoType="ComboBox" widgetId="yearFromCbo" style="width:150" onBlurInput="whenSelectYearOption();"></td>
				    <td class="font-field" align="right" width="120px">ถึงปีเริ่มสัญญา</td>
				    <td align="left" width="150px"><SELECT  dojoType="ComboBox" widgetId="yearToCbo" style="width:150"></td>
				</tr>
  				<tr>
    				<td class="font-field" align="right">ตั้งแต่เลขที่สัญญา&nbsp;</td>
    				<td align="left" width="150px"><SELECT  dojoType="ComboBox" widgetId="conFromCbo" style="width:150" onBlurInput="whenConFromSelectOption();"></td>
				    <td class="font-field" align="right" width="120px">ถึงเลขที่สัญญา</td>
				    <td align="left" width="150px"><SELECT  dojoType="ComboBox" widgetId="conToCbo" style="width:150"></td>		
				    <!--<td width="55px"><INPUT type="button" class="button" value="ค้นหา" onclick="whenQueryConBefore();"></td>-->		    
				</tr>
				<tr>
					<td class="font-field" align="right">วันสิ้นสุดสัญญา&nbsp;</td>
					<td align="left"><SELECT dojoType="ComboBox" widgetId="dateCboFrom" style="width: 100" ></SELECT></td>
					<td width="55px"><INPUT type="button" class="button" value="ค้นหา" onclick="whenQueryConBefore();"></td>
				</tr>
				
			</table>
			<br>
			<div style="height:300px;">
			<table style="width:900px"  border="1" bordercolor="#6699CC"  align="center"  cellpadding="2" cellspacing="0">
			<thead>
				<tr CLASS="TABLEBULE2">
				<th CLASS="TABLEBULE2" style="width:100px">เลขที่สัญญา</th>
				<th CLASS="TABLEBULE2" style="width:200px">ประจำปี</th>
				<th CLASS="TABLEBULE2" style="width:450px">สังกัดปฏิบัติงานจริง</th>
				<th CLASS="TABLEBULE2" style="width:80px">สถานะ</th>
				<th CLASS="TABLEBULE2" style="width:70px">แก้ไข</th>
				<th CLASS="TABLEBULE2" style="width:100px">เพิ่มลูกจ้างเหมาภายใต้สัญญานี้</th>
				<tr>
			</thead>
			<tbody id="dataTable">
			</tbody>
			</table>
			</div>
			<!-- Begin Declare Paging -->
			<table width="660" align="center"  cellpadding="2" cellspacing="0" >
				<tr>
					<td align="right">
						<input type="hidden" name="page" value="<%=pageEdit%>">
						<input type="hidden" name="maxPage">
						<input type="hidden" name="countData" >
						<input type="hidden" name="dataPerPage" value="10">
						<input type="button" disabled="disabled" class=" button " value="First" name="first" onclick="onFirst(whenQueryCon);"/>
						<input type="button" disabled="disabled" class=" button " value="<<" name="previous" onclick="onPrevious(whenQueryCon);"/>
						<input type="text"  name="showPage" style="text-align:right;width: 40;" 
							    onkeyup="onCheckPageNAN(this.value);" onchange="onChangeGoPage(whenQueryCon);" onkeypress="onKeyGoPage(event,whenQueryCon);" 
						/>
						/
						<input type="text"  name="showMaxPage" readonly="readonly" style="width:40;border-style : none;background-color : transparent;text-align:right;font-weight:bold;"/>
						<input type="button" disabled="disabled" class=" button " value=">>" name="next" onclick="onNext(whenQueryCon);" />
						<input type="button" disabled="disabled" class=" button " value="Last" name="last" onclick="onLast(whenQueryCon);"/>
					</td>
				</tr>
			</table>
			<!-- End Declare Paging -->
			<table width="100%" CLASS="TABLEBULE2">
				<tr CLASS="TABLEBULE2" >
					<td align="left" >&nbsp;
						<input type="button" class=" button " style="width: 60px" value="เพิ่มข้อมูล" name="add" onclick="preIns();"/>
					</td>
				</tr>
			</table>
		</form>
	<form name="insertForm" action="security.htm?reqCode=CTWGCONIN001" method="post">
			<input type="hidden" name="orgFromIns" />
			<input type="hidden" name="orgToIns" />
			<input type="hidden" name="yearFromIns" />
			<input type="hidden" name="yearToIns" />
			<input type="hidden" name="conCodeFromIns" />
			<input type="hidden" name="conCodeToIns" />
			<input type="hidden" name="pageIns" />
			<input type="hidden" name="flagIns" />
	</form>
	<form name="editForm" action="security.htm?reqCode=CTWGCONUP001" method="post">
			<input type="hidden" name="conCodeQuery" />
			<input type="hidden" name="yearConQuery" />
			<input type="hidden" name="codeSeqQuery" />
			
			<input type="hidden" name="orgFromEdit" />
			<input type="hidden" name="orgToEdit" />
			<input type="hidden" name="yearFromEdit" />
			<input type="hidden" name="yearToEdit" />
			<input type="hidden" name="conCodeFromEdit" />
			<input type="hidden" name="conCodeToEdit" />
			<input type="hidden" name="pageEdit" />
			<input type="hidden" name="flagEdit" />
		</form>
		<form name="addForm" action="security.htm?reqCode=CTWGEMPCONMT001" method="post">
			<input type="hidden" name="conCodeAdd" />
			<input type="hidden" name="yearConAdd" />
			<input type="hidden" name="codeSeqAdd" />
			
			<input type="hidden" name="orgFromAdd" />
			<input type="hidden" name="orgToAdd" />
			<input type="hidden" name="yearFromAdd" />
			<input type="hidden" name="yearToAdd" />
			<input type="hidden" name="conCodeFromAdd" />
			<input type="hidden" name="conCodeToAdd" />
			<input type="hidden" name="pageAdd" />
			<input type="hidden" name="flagAdd" />
		</form>
	</body>
	<script type="text/javascript">
		if (DWRUtil.getValue("page") >= 0){
			//whenQueryEmp(DWRUtil.getValue("page"));
		}
	</script>
</html>
