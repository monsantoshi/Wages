<%@ page language="java" contentType="text/html;charset=TIS-620"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="javax.swing.text.Document"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@ page import="sun.security.krb5.internal.i" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
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
	String flagQY = request.getParameter("flagAdd");
	
	
	String orgFromEdit = ""; 
	String orgToEdit = "";
	String yearFromEdit = ""; 
	String yearToEdit = "";
	String conCodeFromEdit = ""; 
	String conCodeToEdit = "";
	String pageEdit =  "";
	
	//System.out.println("flag : " + flagQY);
	
	if( flagQY != null && flagQY.equals("MT") ){
		orgFromEdit = request.getParameter("orgFromAdd");
		orgToEdit = request.getParameter("orgToAdd");
		yearFromEdit = request.getParameter("yearFromAdd");
		yearToEdit = request.getParameter("yearToAdd");
		conCodeFromEdit = request.getParameter("conCodeFromAdd");
		conCodeToEdit = request.getParameter("conCodeToAdd");
		pageEdit = request.getParameter("pageAdd");
	}
	if( pageEdit.trim().equals("1") )
		pageEdit = "0";
		
	//System.out.println("pageEdit : " + pageEdit);
		
	//String contractNo = "";
	String contractNo = (String)request.getParameter("conCodeAdd");
	String yearCon = (String)request.getParameter("yearConAdd");
	String orgCodeQue = (String)request.getParameter("codeSeqAdd");
	
	//System.out.println("contractNo : " + contractNo);
	//System.out.println("yearCon : " + yearCon);
	//System.out.println("orgCodeQue : " + orgCodeQue);
%>

<html>
	<head>
		<title>เพิ่มข้อมูลลูกจ้างเหมา</title>
		<!-- Include -->
		<script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript" src="dwr/util.js"></script>
		<!-- Javascript Script File -->
		<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/VPnOrganizationSecurityService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrPeriodLineService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPnEmployeeService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrEmployeeService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWgPnEmployeeService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeContractDetailsService.js"></SCRIPT>
		<script type="text/javascript" src="script/gridScript.js"></script>
		<script type="text/javascript" src="page/NavigatePage.jsp"></script>
		<script type="text/javascript" src="script/dojo.js"></script>
		<script type="text/javascript" src="script/json.js"></script>
		<script type="text/javascript" src="script/dateCalendar.js"></script>
		<script type="text/javascript" src="script/payroll_util.js"></script>

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
		var myUpdate = new Array();
		var count = 0;
		var rowModify ;
		var rowModifyAMT ;
		var lRowNumber;
		var canSave = true;
		    function onLoadYearSectionCallback()
			{
				$("year").value =   "<c:out value='${DefaultYearAndSection.year}' /> "  ;
				$("section").value = "<c:out value='${DefaultYearAndSection.section}' /> "  ;//data.section;
				$("period").value = ""+"<c:out value='${DefaultYearAndSection.period}' /> "  ;//data.period;
				
				whenContractBlur();
			}

			function whenContractBlur()
			{
		    	if( '<%=contractNo%>' != '' ){
			    	DWRUtil.useLoadingMessage("Loading ...");
			    	FeeWgPnEmployeeService.findFeeContractInSecue('<%=userId%>', '<%=ouCode%>', '<%=contractNo%>','<%=yearCon%>', '<%=orgCodeQue%>',{callback:whenContractCallback});			    	
		    	}
		    }
		    
		    function whenContractCallback(data)
		   {
		    	
		    	$("contractNo").value = checkNull(data.contractNo,'STRING');
		    	$("yearCon").value = checkNull(data.yearCon,'STRING');
			    $("conDate").value = checkNull(data.contractDateStr,'STRING');
			    $("instructNo").value = checkNull(data.instructNo,'STRING');
			    $("instructDate").value = checkNull(data.instructDateStr,'STRING');
			  
			    $("promoteDate").value  = checkNull(data.promoteDateStr,'STRING');
			    $("scontactDate").value  = checkNull(data.scontactDateStr,'STRING');
			    $("econtactDate").value  = checkNull(data.econtactDateStr,'STRING');
			    $("note").value = checkNull(data.note,'STRING');
			    
			  
			
		    	dojo.widget.byId("orgCode").textInputNode.value = checkNull(data.orgCode,'STRING') + ' ' + checkNull(data.orgDesc,'STRING');
		    	$("codeSeqAct").value = checkNull(data.codeSeq,'STRING');
		    	$("hCodeSeqAct").value = checkNull(data.codeSeq,'STRING');
			 
		    	$("hOrgCode").value = checkNull(data.orgCode,'STRING');
		    
		    
		    
		    	var dutyCode = document.getElementById("dutyCode");
 		    	//alert("marriedStatus : " + data.prefixName);
 		    	if( data.dutyCode == '800' ){
 		    		dutyCode.selectedIndex = 0;
 		    		$("hDutyCode").value = data.dutyCode;
 		    	}else if( data.dutyCode == '801' ){
 		    		dutyCode.selectedIndex = 1;
 		    		$("hDutyCode").value = data.dutyCode;
 		    	}
		    	var inactive = document.getElementById("inactive");
 		    	//alert("marriedStatus : " + data.prefixName);
 		    	if( data.inactive == 'N' ){
 		    		inactive.selectedIndex = 0;
 		    		$("hInactive").value = data.inactive;
 		    	}else if( data.inactive == 'Y' ){
 		    		inactive.selectedIndex = 1;
 		    		$("hInactive").value = data.inactive;
 		    	}
 		    	whenShowDataTable()
 		    }
		    
		   
		
		   
		    function whenFindOrganization()
		    {
		    	DWRUtil.useLoadingMessage("Loading ...");
		    	SuUserOrganizationService.findPrOrganizationByCriteria('<%=userId%>','<%=ouCode%>', splitCombo( dojo.widget.byId("orgCode").textInputNode.value ), $("year").value,$("period").value, {callback:whenFindOrganizationCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		    }
		    
		    function whenFindOrganizationCallback(data)
		    {
	    		$("codeSeqAct").value = data.codeSeq;
	    		//alert(data.codeSeq);
	 	    }
		 
		    function whenShowDataTable()
			{	
				myUpdate = new Array();
			    //alert()
				//var frm = document.forms["0"];
				FeeContractDetailsService.findByCriteriaList('<%=userId%>', '<%=ouCode%>', '<%=contractNo%>','<%=yearCon%>','<%=orgCodeQue%>',DWRUtil.getValue("page"),DWRUtil.getValue("dataPerPage"),{callback:whenListDataTableHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
			}
			
		    var cellFuncs = [
			             		function(data) { return writeTextID("empCode",data.empCode,10,"left",data.keySeq);},
			             		function(data) { return writeTextDisplay("name",data.name,200,"left","codeSeqAct",data.codeSeq);},
			             		function(data) { return writeText("warfee",data.warfee,4,"right",data.keySeq);},
			             		function(data) { return writeText("salary",data.salary,9,"right",data.keySeq);},
			             		function(data) { return writeText("extraIncome",data.extraIncome,6,"right",data.keySeq);}];
			
			function writeSelect(inname,emp,key)
			{
				var temp1 = '';
				var temp2 = '';
				var temp3 = '';
				var temp4 = '';
			
				if(emp=='N'){
					temp1 = 'selected';
				} else if(emp=='X'){
					temp2 = 'selected';		
				}else if(emp=='Y'){
					temp3 = 'selected';		
				}else if(emp=='Z'){
					temp4 = 'selected';		
				}
				
				return "<div align='center' ><select onchange='addListUpdate("+key+")' name='"+inname+"'><option value='N' "+temp1+" >ปกติ</option>"+
														"<option value='X' "+temp2+">พ้นสภาพ</option>"+"<option value='Y' "+temp3+">เลิกจ้างมีโทษ</option>"+"<option value='Z' "+temp4+">ลบบันทึกผิด</option></select></div>";
				//
				
				//}
			}
			
			function writeApp(inname,emp,key)
			{
					
				var mm1 = '';
				var mm2 = '';
				var mm3 = '';
				var mm4 = '';
				
			
				
				if(emp=='N'){
				mm1 = 'selected';
				}else if(emp=='X'){
				mm2 = 'selected';
				}else if(emp=='Y'){
				mm3 = 'selected';
				}else if(emp=='Z'){
				mm4 = 'selected';
				}
				
				
				return "<div align='center' ><select onchange='addListUpdate("+key+")' name='"+inname+"'>" +
															"<option value='N' "+mm1+" >ปกติ</option>" +
														    "<option value='X' "+mm2+" >พ้นสภาพ</option>" +
														    "<option value='Y' "+mm3+" >เลิกจ้างมีโทษ</option>" +
														    "<option value='Z' "+mm4+" >ลบบันทึกผิด</option>" +
									"</select></div>";
			
			
			}
			
			
			
			
			function writeText(inname,emp,maxlength,textalign,key)
			{
				
					return "<div align='center' ><input type='text' name = '"+inname+"' onChange='addListUpdate("+key+");'  value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
				
			}
			function writeTextDisplay(inname,emp,maxlength,textalign,nameSeq,codeseq)
			{
				return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' /><input type='hidden' name = '"+nameSeq+"' value='"+codeseq+"'  /></div>";
			}
			
			function writeTextNote(inname,emp,maxlength,textalign,nameSeq,codeseq)
			{
				return "<div align='center' ><input type='text'  name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' /><input type='hidden' name = '"+nameSeq+"' value='"+codeseq+"'  /></div>";
			}
			
			function writeTextID(inname,emp,maxlength,textalign,key)
			{
			
					return "<div align='center' ><input type='text' name = '"+inname+"' maxlength='6' onChange='whenSelectEmpOptionInRowUpdate("+key+");addListUpdate("+key+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;'  onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
				
			}
			
			
			
			 function whenQueryConBefore()
			    {
			    	$("page").value = 0;
			    	whenShowDataTable();
			    } 
			    
			    function whenShowDataTable()
				{	
					myUpdate = new Array();
				
					var frm = document.forms["searchForm"];
						FeeContractDetailsService.findByCriteriaList
						(
							'<%=userId%>', '<%=ouCode%>', '<%=contractNo%>','<%=yearCon%>','<%=orgCodeQue%>',
							DWRUtil.getValue("page"),
							DWRUtil.getValue("dataPerPage"),
							{callback:whenListDataTableHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร01');}}
						);
					
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
			    
			    var feeContractDetails = {keySeq:null,ouCode:null,yearCon:null,codeSeq:null,contractNo:null,empCode:null,
						 salary:null,extraIncome:null,warfee:null,creBy:null,updBy:null,creDate:null,updDate:null};

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
					num = 1;
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
					var contractNo	= frm.elements["contractNo"];
					var yearCon 	= frm.elements["yearCon"];
					var salary 		= frm.elements["salary"];
					var warfee 		= frm.elements["warfee"];
					var extraIncome 	= frm.elements["extraIncome"];
					
					
					
					
					
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
							
							for(var i=0; i<tab.rows.length; i++){
								update = false;
								row = tab.rows[i];
								feeContractDetails.keySeq = parseInt(keySeq[i].value);
							
								
							    //alert(empCode[i].value);
								
								if (empCode[i].value != ''){
									feeContractDetails.empCode  = empCode[i].value;
								}
								else{
									feeContractDetails.empCode  = null;
								}
								
								if (name[i].value != ''){
									feeContractDetails.name  = name[i].value;
								}
								else{
									feeContractDetails.name  = null;
								}
						 
							
								if (salary[i].value !=''){
									feeContractDetails.salary  = salary[i].value;
								}
								else{
									feeContractDetails.salary  = null;
								}
								
								if (warfee[i].value !=''){
									feeContractDetails.warfee  = warfee[i].value;
								}
								else{
									feeContractDetails.warfee  = null;
								}
								if (extraIncome[i].value !=''){
									feeContractDetails.extraIncome  = extraIncome[i].value;
								}
								else{
									feeContractDetails.extraIncome  = null;
								}
								
							
								feeContractDetails.contractNo = $("contractNo").value;
								feeContractDetails.codeSeq = codeSeq[i].value;
								feeContractDetails.updBy = '<%=userId%>';
								feeContractDetails.updDate = getDateFromFormat(sed,"dd/MM/yyyy");
								
								
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
											FeeContractDetailsService.addList(feeContractDetails, false, {callback:onInsertCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร1');}});
										else
											FeeContractDetailsService.addList(feeContractDetails, false);
							
										}else{
										if( allRowUpdate == myUpdate.length )
											FeeContractDetailsService.addList(feeContractDetails, true, {callback:ClearData,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้');}});
										else
											FeeContractDetailsService.addList(feeContractDetails, false);
										
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
					document.getElementById("mustQuery").value = 'true';
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
								feeContractDetails.keySeq = null;
								feeContractDetails.ouCode = '<%=ouCode%>';
								feeContractDetails.yearCon = parseInt($("yearCon").value);
								feeContractDetails.contractNo = $("contractNo").value;
								feeContractDetails.codeSeq = $("codeSeqAct").value;
								feeContractDetails.empCode = oRows[i].cells["empCode"].childNodes[0].value;
								feeContractDetails.name = oRows[i].cells["name"].childNodes[0].value;
								
							    
								
								if(oRows[i].cells["salary"].childNodes[0].value != ''){
									feeContractDetails.salary = oRows[i].cells["salary"].childNodes[0].value;
								}else{
									feeContractDetails.salary = null;
								}
								if(oRows[i].cells["warfee"].childNodes[0].value != ''){
									feeContractDetails.warfee = oRows[i].cells["warfee"].childNodes[0].value;
								}else{
									feeContractDetails.warfee = null;
								}	
								
								if(oRows[i].cells["extraIncome"].childNodes[0].value != ''){
									feeContractDetails.extraIncome = oRows[i].cells["extraIncome"].childNodes[0].value;
								}else{
									feeContractDetails.extraIncome = null;
								}	
							
							    
								
								
								feeContractDetails.creBy = '<%=userId%>';
								feeContractDetails.creDate = getDateFromFormat(<%=date%>,"dd/MM/yyyy");
								
								
									if( i == (num + 1) )
										FeeContractDetailsService.addList(feeContractDetails, true, {callback:ClearData,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้');}});
									else
										FeeContractDetailsService.addList(feeContractDetails, false);
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
					//var startD = $("scontactDate").value;
					//var empCode 	= frm.elements["empCode"];
					DWRUtil.useLoadingMessage("Loading ...");
					//alert(startD);
					//alert("empCode"+ empCode);
					
					FeeWgPnEmployeeService.findByEmpCodeCanAdd('<%=userId%>',empCode, '<%=ouCode%>', {callback:whenFetchEmployeeDetailInRowUpdateCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร2');}});
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
						
					
						
						if(data.empCode != null && data.empCode != ''){
							name[rowModify].value = data.name;
							//codeSeq[rowModify].value = data.codeSeq;
						}else{
							name[rowModify].value = '';
							//codeSeq[rowModify].value = '';
							alert('เลขประจำตัวไม่ถูกต้อง');
							empCode[rowModify].focus();	
						}
						
					}
				
				 }
				 
				 function whenFetchEmployeeDetailInRow(empCode)
				 {
					//var startD = $("scontactDate").value;
					DWRUtil.useLoadingMessage("Loading ...");
					
					FeeWgPnEmployeeService.findByEmpCodeCanAdd('<%=userId%>',empCode, '<%=ouCode%>', {callback:whenFetchEmployeeDetailInRowCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร3');}});
				 }
				 
				 function whenFetchEmployeeDetailInRowCallback(data)
				 {
					//alert(data.empCode);
					var table = document.getElementById("table");
					var tdName;
					var chkName;
					
					var oRows = table.rows;
					var i = oRows.length-1;
				 	if(data.empCode != null && data.empCode != ''){	 		
								oRows[lRowNumber].cells["name"].childNodes[0].value = data.name
								//oRows[lRowNumber].cells["name"].childNodes[1].value = data.codeSeq
					}else{
						oRows[lRowNumber].cells["name"].childNodes[0].value = '';
						//oRows[lRowNumber].cells["name"].childNodes[1].value = '';
						alert('เลขประจำตัวไม่ถูกต้อง');
						oRows[lRowNumber].cells["empCode"].childNodes[0].focus();	
					}
				 }
				 function countData()
					{	
						myUpdate = new Array();
						var frm = document.forms["searchForm"];
						FeeContractDetailsService.countDataList('<%=userId%>', '<%=ouCode%>', '<%=contractNo%>','<%=yearCon%>','<%=orgCodeQue%>',{callback:countDataHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร4');}});
						
					}
				 function countDataHandler(data)
					{
						DWRUtil.setValue("countData",data);
						onCheckButt("searchForm");
					    
					}
				
				
			function gotoQYPage() {
				document.getElementById("orgFromEdit").value = '<%= orgFromEdit %>';
				document.getElementById("orgToEdit").value = '<%= orgToEdit %>';
				document.getElementById("yearFromEdit").value = '<%= yearFromEdit %>';
				document.getElementById("yearToEdit").value = '<%= yearToEdit %>';
				document.getElementById("conCodeFromEdit").value = '<%= conCodeFromEdit %>';
				document.getElementById("conCodeToEdit").value = '<%= conCodeToEdit %>';
				document.getElementById("pageEdit").value = '<%= Integer.parseInt(pageEdit) - 1 %>';
				document.getElementById("mustQuery").value = 'true';
		    
		    	document.forms["goQY"].submit();
		    }
		    function initOrgCodeCbo(){
				try{
			     	var cboSource = [];
			     	var orgCodeCbo = dojo.widget.byId("orgCode");
			     	
			     	<c:forEach items="${OrganizationInSecurity}" var="result" >		 
						cboSource.push(["<c:out value='${result.orgCode} ${result.divShort} ${result.areaDesc} ${result.secDesc} ${result.workDesc}' />","<c:out value='${result.orgCode}' />"]);
					</c:forEach>
			     	
			     	orgCodeCbo.dataProvider.setData(cboSource);
			     	
		     	}catch(e){
		     		alert(e.message);
		     	}
			}
			
			function init(){
				initOrgCodeCbo();
				var cbo = dojo.widget.byId("orgCode");
				//var cbo = dojo.widget.byId("orgCode");
				cbo.setDisable(true);
			    whenContractBlur();
				//dojo.event.connect(cbo, "selectOption", "whenFindOrganization");
				}
			
			dojo.addOnLoad(init);
			
		    //dojo.addOnLoad(onLoadYearSectionCallback);
		
		</script>
			<%
	Calendar now = Calendar.getInstance(Locale.US);
    //String year = ((now.get(Calendar.YEAR)+543)+"");
	
	String keySeq  = request.getParameter("keySeq");
%>
	</head>
	<body>
		<table width="100%">
			<tr>
				<td class="font-head">
					[ CTWGEMPCONMT001 ] เพิ่มข้อมูลลูกจ้าง ในสัญญานี้	</td>
			</tr>
		</table>
		<form name="searchForm" action="" method="post">
		<input type="hidden" name="dataLength">
		
		<!-- Begin Declare Paging -->
			<!-- End Declare Paging -->
		<table width="100%" align="center">
		<tr><td align="center">	
			<TABLE border="0" align="left" cellspacing="1">
			
			</TABLE>
		</td></tr>
		<tr><td align="center">
			<TABLE border="0" align="center" width="920px" cellspacing="1">
				<TR>
					<TD class="font-field" style="text-align:right;" width="200px">เลขที่สัญญา</TD>
					<TD width="130px"><INPUT type="text" id="contractNo" readonly="readonly" maxlength="6" style="width:130px;text-align: left;background-color:silver;"/></TD>
					<TD class="font-field" style="text-align:right;" >ปี พ.ศ.</TD>
					<TD width="130px"><INPUT type="text" id="yearCon" readonly="readonly" size="6"  maxlength="4" align="center" style="text-align:center;background-color:silver;" /></TD>
				</TR>
				<TR>
					<TD class="font-field" style="text-align:right;" width="250px">สังกัดการจ้างเหมา</TD>
					<TD width="780px" colspan="10">
						<table border="0" style="width:100%;border:none" cellpadding="0" cellspacing="0"><tr>
							<TD>
								<INPUT type="text" widgetId="orgCode" maxlength="20" dojoType="ComboBox" style="width:730px"  />
								<INPUT type="hidden" id="codeSeqAct" />
								<INPUT type="hidden" id="hOrgCode" />
            					<INPUT type="hidden" id="hCodeSeqAct" />
							</TD>
						</tr></table>
					</TD>
				</TR>
				<TABLE border="1" align="center" width="920px" cellspacing="1">
				<TR>
						<TD colspan="8">
						<FIELDSET><LEGEND class="font-field">รายละเอียด</LEGEND>
						<TABLE border="01" width="100%" colspan="3">
							<TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR>
						<TD class="font-field" style="text-align:right;" width="100px">วันที่ทำสัญญา</TD>
					<TD><INPUT type="text" id="conDate" size="12" maxlength="10" readonly="readonly" align="center"  style="text-align:center;background-color:silver;"/></TD>
				    	<TD class="font-field" style="text-align:right;width:100px">จ้างเหมาเพื่อ</TD>
							<TD width="100px">
								<SELECT id="dutyCode" disabled="disabled" style="width:150px" ">
									<OPTION value="800">ช่วยงานไปรษณีย์</OPTION>
									<OPTION value="801">ช่วยงาน LOGISTIC</OPTION>
								</SELECT>
								<INPUT type="hidden" id="hDutyCode">
							</TD>
				    	<TD class="font-field" style="text-align:right;width:100px">สถานะสัญญา</TD>
							<TD width="100px">
								<SELECT id="inactive" disabled="disabled" style="width:100px" ">
									<OPTION value="N">ปกติ</OPTION>
									<OPTION value="Y">สิ้นสุด</OPTION>
								</SELECT>
								<INPUT type="hidden" id="hInactive">
							</TD>		
				</TR>
				</TABLE>
				<TABLE border="01" width="100%" colspan="3">
							<TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR>
						<TD class="font-field" style="text-align:right;" width="100px">คำสั่งที่</TD>
					<TD width="130px"><INPUT type="text" id="instructNo" readonly="readonly" style="width:150px;text-align: left;background-color:silver;"  /></TD>
						<TD class="font-field" style="text-align:right;" width="100px">วันที่ลงนามคำสั่ง</TD>
					<TD><INPUT type="text" id="instructDate" readonly="readonly" size="12" maxlength="10" align="center"  style="text-align:center;background-color:silver;" /></TD>
				<TD class="font-field" style="text-align:right;" width="100px">วันที่คำสั่งมีผล</TD>
					<TD><INPUT type="text" id="promoteDate" readonly="readonly" size="12" maxlength="10" align="center"  style="text-align:center;background-color:silver;" /></TD>
				</TABLE>
					<TABLE border="01" width="100%" colspan="3">
							<TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR>
						<TD class="font-field" style="text-align:right;" width="100px">วันเริ่มต้นสัญญา</TD>
					<TD><INPUT type="text" id="scontactDate" readonly="readonly" size="12" maxlength="10"  align="center" style="text-align:center;background-color:silver;"  /></TD>
				<TD class="font-field" style="text-align:right;" width="100px">วันสิ้นสุดสัญญา</TD>
					<TD><INPUT type="text" id="econtactDate" readonly="readonly" size="12" maxlength="10" align="center" style="text-align:center;background-color:silver;" /></TD>
					<TD class="font-field" style="text-align:right;" width="100px">หมายเหตุ</TD>
					<TD width="130px"><INPUT type="text" id="note" readonly="readonly" style="width:250px;text-align: left;background-color:silver;" /></TD>
				    <td><input type="Button" value="ค้นหา" class=" button " onclick="whenShowDataTable();" /></td>
				<td></td>
				</TABLE>
			</td></tr>
		</TABLE>	
		<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
				<td><div style="height: 320px; width: 1100; overflow: auto; vertical-align: top;" align="center">
				<table id="table" width="1050" border="1" bordercolor="#6699CC" cellpadding="0" cellspacing="0">
					<thead style="text-align: center">
					<tr CLASS="TABLEBULE2" style="height: 30px;">
						<th CLASS="TABLEBULE2" style="width: 120px">เลขประจำตัว</th>	<th CLASS="TABLEBULE2" style="width: 200px">ชื่อ - นามสกุล</th>
						<th CLASS="TABLEBULE2" style="width: 80px" align="center"><center>ค่าจ้างรายวัน</center></th>
						<th CLASS="TABLEBULE2" style="width: 80px" align="center"><center>เงินเดือน</center></th>
						<th CLASS="TABLEBULE2" style="width: 80px" align="center"><center>เงินเพิ่มพิเศษ</center></th>
					</tr>
					</thead>
					<tbody id="dataTable"></tbody>
						<tr id="temprow" style="visibility: hidden; position: absolute">
							<td id="empCode" align="center"><input type="text" maxlength="13" name="empCode" style="width: 120%" onchange="whenSelectEmpOptionInRow(this);"	onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="name" align="center"><input type="text"	name="name" readonly="readonly"	style="width: 100%; background-color: silver;"/><input type="hidden" name="codeSeqAct" /></td>
							<td id="warfee" align="center"><input type="text" name="warfee" maxlength="4" align="center" onchange="checkValue(this);" style="text-align: right; width: 100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" /></td>
							<td id="salary" align="center"><input type="text" name="salary" maxlength="9" align="center" onchange="checkValue(this);" style="text-align: right; width: 100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" /></td>
							<td id="extraIncome" align="center"><input type="text" name="extraIncome" maxlength="9" align="center" onchange="checkValue(this);" style="text-align: right; width: 100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" /></td>
						</tr>
				</table>
				</div></td>
				</tr>
		</table>
		</TABLE>
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
		</td>
		</tr>
		<table width="1080" CLASS="TABLEBULE2" >
			<tr CLASS="TABLEBULE2" >
			<td align="left" >&nbsp;
			<input type="Button" class=" button " value="เพิ่มข้อมูล" id="insertData" name="insertData" onclick="addVisualRow();"/>											
			<input type="Button" class=" button " value="ตกลง" id="confirmData" name="confirmData" onclick="onUpdate();"/>
			<input type="Button" class=" button " value="ออก" id="goMT" name="goMT" onclick="gotoQYPage();"/>
			</td>
			</tr>
		</table>
		</table>
	</form>
	<FORM name="goQY" action="security.htm?reqCode=CTWGCONMT001" method="post">
		<input type="hidden" name="orgFromEdit" />
		<input type="hidden" name="orgToEdit" />
		<input type="hidden" name="yearFromEdit" />
		<input type="hidden" name="yearToEdit" />
		<input type="hidden" name="conCodeFromEdit" />
		<input type="hidden" name="conCodeToEdit" />
		<input type="hidden" name="pageEdit" />
		<input type="hidden" name="mustQuery" />
	</FORM>
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
								feeContractDetails.keySeq = parseInt(frm.elements["keySeq"][i].value);
									FeeContractDetailsService.deleteFeeContractDetails(feeContractDetails, {callback:onDeleteCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
									chDelete = true;
								}
							
								
						}
					}else{
						if(tab.rows.length==1){
							row = tab.rows[0];	
							if (chk[0].checked){
									
									//alert('CC'+rowDelete);
									feeContractDetails.keySeq = parseInt(frm.elements["keySeq"][0].value);
									FeeContractDetailsService.deleteFeeContractDetails(feeContractDetails, {callback:onDeleteCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
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
