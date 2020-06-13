<%@ page language="java" contentType="text/html;charset=TIS-620"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%
	UserInfo userInfo = (UserInfo)session.getAttribute("UserLogin");
	String userId = userInfo.getUserId();
	String ouCode = userInfo.getOuCode();
	
	String year = (String)request.getParameter("yearDel");
	String period = (String)request.getParameter("periodDel");
	String section = (String)request.getParameter("sectionDel");
	String isConfirm = (String)request.getParameter("isConfirmDel");
	//String empCode = (String)request.getParameter("empCodeEdit");
	
	String orgFromEdit = request.getParameter("orgFromDel");
	String orgToEdit = request.getParameter("orgToDel");
	String empCodeFromEdit = request.getParameter("empCodeFromDel");
	String empCodeToEdit = request.getParameter("empCodeToDel");
	String pageEdit = request.getParameter("pageDel");
	
	if( pageEdit.trim().equals("") )
		pageEdit = "-1";
	
	//isConfirm = "true";
	System.out.println("isConfirm : " + isConfirm);
%>
<html>
	<head>
		<title>บันทึกข้อมูลหลักในการจัดทำค่าจ้างลูกจ้าง</title>
		<!-- Include -->
		<script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript" src="dwr/util.js"></script>
		<!-- Javascript Script File -->
		<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/VPnOrganizationSecurityService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrPeriodLineService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPnEmployeeService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrEmployeeService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrEmployeeTextService.js"></SCRIPT>
		
		<script type="text/javascript" src="script/gridScript.js"></script>
		<script type="text/javascript" src="page/NavigatePage.jsp"></script>
		<script type="text/javascript" src="script/dojo.js"></script>
		<script type="text/javascript" src="script/json.js"></script>
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
		    
			
			/**** BEGIN When Employee Select ****/
		    function onLoad(){
		    	DWRUtil.useLoadingMessage("Loading ...");
		    	$("year").value = '<%=year%>';
		    	$("period").value = '<%=period%>';
		    	$("section").value = '<%=section%>';
		    	$("isConfirm").value = '<%=isConfirm%>';
		    	
		    	
		    }
		    
		    function whenEmpBlur()
		    {
		    	//alert("test");
		    	if( $("empCode").value != '' ){
			    	DWRUtil.useLoadingMessage("Loading ...");
			    	//var cboEmp = dojo.widget.byId("empCode");
			    	FeeWpayPnEmployeeService.findByCriteriaInSecueEmpText('<%=userId%>', '<%=ouCode%>', $("empCode").value, $("year").value, $("period").value,{callback:whenLoadPnEmployeeDeatilCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		    	    //alert("test2");
		    	}
		    }
		    
		    function whenLoadPnEmployeeDeatilCallback(data){
		    //alert( data.toJSONString() );
		    
		    	if( data.firstName != null )
		    	{
			    	$("empName").value = checkNull(data.prefixName,'STRING') + ' ' + checkNull(data.firstName,'STRING') + ' ' + checkNull(data.lastName,'STRING');
			    	//$("account").value = checkNull(data.account,'STRING');
			    	//$("pDate").value = checkNull(data.PDate,'STRING');
			    	$("codeSeq").value = checkNull(data.codeSeq,'STRING');
			    	$("codeSeqName").value = checkNull(data.orgActDesc,'STRING');
			    	
			    	dojo.widget.byId("orgCode").textInputNode.value = checkNull(data.orgCode,'STRING') + ' ' + checkNull(data.orgDesc,'STRING');
			    	$("hOrgCode").value = checkNull(data.orgCode,'STRING');
			    	
			    	//$("orgDesc").value = checkNull(data.orgDesc,'STRING'); // + ' ' + checkNull(data.orgDesc,'STRING');
			    	
			    	$("codeSeqAct").value = checkNull(data.codeSeqAct,'STRING');
			    	$("hCodeSeqAct").value = checkNull(data.codeSeqAct,'STRING');
			    	
			    	FeeWpayPrEmployeeService.findPrEmployee('<%=ouCode%>', $("year").value, $("period").value, $("empCode").value, '<%=userId%>',{callback:whenPrEmployeeLoadCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสารพพพ');}});
		    	}else
		    		alert("ไม่พบข้อมูลรหัสพนักงาน");
		    }
		    
		    function whenPrEmployeeLoadCallback(data){
		        // alert( data.toJSONString() );
		    	var empName = $("empName").value.replace(' ','space');
		    	if( empName != 'space' ){
			    	
			    	$("delete").disabled = false;
			    	
			    	// เลขประจำตัวผู้เสียภาษี
			    	$("taxId").value = checkNull(data.taxId,'STRING');
			    	$("hTaxId").value = checkNull(data.taxId,'STRING');
			    	
			        $("bankBranch").value = checkNull(data.bankBranch,'STRING');
			    	$("hBankBranch").value = checkNull(data.bankBranch,'STRING');
			    	
			    	$("bankId").value = checkNull(data.bankId,'STRING');
			    	$("hBankId").value = checkNull(data.bankId,'STRING');
    	            
    	            // เลือกธนาคาร
	 		    	var bankCode = document.getElementById("bankCode");
	 		    	if( data.bankCode == '001' ){
	 		    		bankCode.selectedIndex = 0;
	 		    		$("hBankCode").value = data.bankCode;
	 		    	}else if( data.bankCode == '002' ){
	 		    		bankCode.selectedIndex = 1;
	 		    		$("hBankCode").value = data.bankCode;
	 		    	}
			    				    	
			    	// สถานะการรับเงินเดือน
	 		    	var payStatus = document.forms["searchForm"].elements["payStatus"];
	 		    	//alert("payStatus : " + data.payStatus );
	 		    	if( data.payStatus == '1' ){
	 		    		payStatus[0].checked = true;
	 		    		$("hPayStatus").value = data.payStatus;
	 		    	}else if( data.payStatus == '2' ){
	 		    		payStatus[1].checked = true;
	 		    		$("hPayStatus").value = data.payStatus;
	 		    	}else{
	 		    		payStatus[0].checked = true;
	 		    	}
	 		    	
	 		    	// สถานะการจ่ายเงินเดือน
	 		    	var flagPr = document.getElementById("flagPr");
	 		    	if( data.flagPr == '0' ){
	 		    		flagPr.selectedIndex = 0;
	 		    		$("hFlagPr").value = data.flagPr;
	 		    	}else if( data.flagPr == '1' ){
	 		    		flagPr.selectedIndex = 1;
	 		    		$("hFlagPr").value = data.flagPr;
	 		    	}else if( data.flagPr == '2' ){
	 		    		flagPr.selectedIndex = 2;
	 		    		$("hFlagPr").value = data.flagPr;
	 		    	}
	 		    	
	 		    	// สถานะภาพลดหย่อน
	 		    	var marriedStatus = document.getElementById("marriedStatus");
	 		    	//alert("marriedStatus : " + data.marriedStatus);
	 		    	if( data.marriedStatus == '1' ){
	 		    		marriedStatus.selectedIndex = 0;
	 		    		$("hMarriedStatus").value = data.marriedStatus;
	 		    	}else if( data.marriedStatus == '2' ){
	 		    		marriedStatus.selectedIndex = 1;
	 		    		$("hMarriedStatus").value = data.marriedStatus;
	 		    	}else if( data.marriedStatus == '3' ){
	 		    		marriedStatus.selectedIndex = 2;
	 		    		$("hMarriedStatus").value = data.marriedStatus;
	 		    	}else if( data.marriedStatus == '4' ){
	 		    		marriedStatus.selectedIndex = 3;
	 		    		$("hMarriedStatus").value = data.marriedStatus;
	 		    	}
			    	
			    	$("costChild").value = checkNull(data.costChild,'STRING');
			    	$("hCostChild").value = checkNull(data.costChild,'STRING');
			    	//alert($("costChild").value);
			    	
			    
			    
			    	
			    	var gundanFlag = document.getElementById("gundanFlag");
			    	if( data.gundanFlag == 'Y' ){
			    		gundanFlag.checked = true;
			    		$("hGundanFlag").value = data.gundanFlag;
			    	}else{
			    		gundanFlag.checked = false;
			    		$("hGundanFlag").value = data.gundanFlag;
			    	}
			    }
		    }
		     function clearScreen(){
		    
		    	$("empName").value = '';
		    	//$("pDate").value = '';
		    	$("codeSeq").value = '';
		    	$("codeSeqName").value = '';
		    	var cbo = dojo.widget.byId("orgCode");
		    	cbo.textInputNode.value = '';
		    	//$("orgDesc").value = '';
		    
		    	$("taxId").value = '';
		    	
 		    	var payStatus = document.forms["searchForm"].elements["payStatus"];
 		    	payStatus[0].checked = false;
 		    	payStatus[1].checked = false;
 		    	$("bankBranch").value = '';
 		    	$("bankId").value = '';
 		    	
 		    	
 		    	// ธนาคาร
 		    	var bankCode = document.getElementById("bankCode");
 		    	bankCode.selectedIndex = 0;
 		    	
 		    	// สถานะการจ่ายเงินเดือน
 		    	var flagPr = document.getElementById("flagPr");
 		    	flagPr.selectedIndex = 0;
 		    	
 		    	
 		    	
 		    	// สถานะภาพลดหย่อน
 		    	var marriedStatus = document.getElementById("marriedStatus");
 		    	marriedStatus.selectedIndex = 0;
 		    	
 		    	$("costChild").value = '';
		    
		    	var gundanFlag = document.getElementById("gundanFlag");
		    	gundanFlag.checked = false;
		    
		    	$("delete").disabled = true;
		    }
		    /**** END When Employee Select ****/
		    
		    
		    
		    function whenFindOrganization(){
		    	DWRUtil.useLoadingMessage("Loading ...");
		    	SuUserOrganizationService.findPrOrganizationByCriteria('<%=userId%>','<%=ouCode%>', splitCombo( dojo.widget.byId("orgCode").textInputNode.value ), $("year").value, $("period").value, {callback:whenFindOrganizationCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		    }
		    function whenFindOrganizationCallback(data){
		    	$("codeSeqAct").value = data.codeSeq;
		    	//alert(data.toJSONString());
		    	//$("orgDesc").value = checkNull(data.divDesc,'STRING') + ' ' + checkNull(data.secDesc,'STRING');
		    }
		    	
		    var prEmpTextVo = {	keySeq:null, 
		    					ouCode:null,
								year:null,
								period:null,
								empCode:null,
								codeSeqWork:null,
								taxId:null,
								marriedStatus:null,
								payStatus:null,
								bankId:null,
								costChild:null,
								gundanFlag:null,
								flagPr:null,
								flagStatus:null,
								seqData:null,
								creBy:'<%=userId%>',
								updBy:'<%=userId%>',
								confirmFlag:'N',
								bankBranch:null,
								bankCode:null};
		    
		    function save()
		    {
		    	var empName = Trim($("empName").value);
		    	//var orgDesc = Trim($("orgDesc").value);
		    	
		    	var cbo = dojo.widget.byId("orgCode");
		    	
				if( cbo.textInputNode.value == '' )
		    	{
			    	alert("โปรดกรอกสังกัดปฏิบัติงานให้ถูกต้อง");
		    	}else
		    	{
		    		VPnOrganizationSecurityService.findByKeyPK('<%=ouCode%>','<%=userId%>', splitCombo( dojo.widget.byId("orgCode").textInputNode.value ),{callback:whenCheckCanSaveCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
				}
		    }
		    
		    function whenCheckCanSaveCallback(data)
		    {
		    	var cbo = dojo.widget.byId("orgCode");
		    	
		    	if(data == null)
		    	{
		    		alert("สังกัดปฏิบัติงานไม่ถูกต้อง");
		    	}else
		    	{
		    		var empName = Trim($("empName").value);
		    		//alert($("empName").value);
		    		if( empName != '' && cbo.textInputNode.value != '' )
			    	{
				    	prEmpTextVo.keySeq = '';
				    	prEmpTextVo.ouCode = '<%=ouCode%>';
						prEmpTextVo.year = $("year").value; 
						prEmpTextVo.period = $("period").value;
						prEmpTextVo.empCode = $("empCode").value;
						
						//if( !isEqualsValue($("codeSeqAct").value, $("hCodeSeqAct").value) )
						prEmpTextVo.codeSeqWork = $("codeSeqAct").value;
						
						if( !isEqualsValue($("taxId").value, $("hTaxId").value) )
							prEmpTextVo.taxId = $("taxId").value;
						
						if( !isEqualsValue($("marriedStatus").value, $("hMarriedStatus").value) )
							prEmpTextVo.marriedStatus = $("marriedStatus").value;
							
						if( !isEqualsValue($("payStatus").value, $("hPayStatus").value) )
							prEmpTextVo.payStatus = $("payStatus").value;
							
						
	
						if( !isEqualsValue($("bankId").value, $("hBankId").value) )
							prEmpTextVo.bankId = $("bankId").value;
						 
						if( !isEqualsValue($("costChild").value, $("hCostChild").value) )
							prEmpTextVo.costChild = $("costChild").value;
						
					
						if( !isEqualsValue($("gundanFlag").value, $("hGundanFlag").value) )
							prEmpTextVo.gundanFlag = $("gundanFlag").value;
						
				
						if( !isEqualsValue($("flagPr").value, $("hFlagPr").value) )
							prEmpTextVo.flagPr = $("flagPr").value;
							
					    if( !isEqualsValue($("bankCode").value, $("hBankCode").value) )
							prEmpTextVo.bankCode = $("bankCode").value;
	
	
					
						prEmpTextVo.flagStatus = '';
						
						//if( !isEqualsValue($("flagPr").value, $("hFlagPr").value) )
						//	prEmpTextVo.seqData = '';
	
						if( $("gundanFlag").checked ){
							//alert($("hGundanFlag").value);
							if( !isEqualsValue('Y', $("hGundanFlag").value) )
								prEmpTextVo.gundanFlag = 'Y';
						}else{
							if( !isEqualsValue('N', $("hGundanFlag").value) )
								prEmpTextVo.gundanFlag = 'N';
						}
	
						prEmpTextVo.flagStatus = 'A';
						
						FeeWpayPrEmployeeTextService.insertPrEmployeeText(prEmpTextVo, {callback:whenInsertPrEmployeeText,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้');}});
					}else{
						alert("โปรดกรอก เลขประจำตัว หรือ สังกัดปฏิบัติงานให้ถูกต้อง");
					}
			    }
		    }
		    
		   
		    
		    function whenInsertPrEmployeeText(data)
		    {
		    	alert("บันทึกข้อมูลเรียบร้อย");
		    	//gotoMTPage();
		    	
		    	clearScreen();
		    	
		     prEmpTextVo = {	keySeq:null, 
		    					ouCode:null,
								year:null,
								period:null,
								empCode:null,
								codeSeqWork:null,
								taxId:null,
								marriedStatus:null,
								payStatus:null,
								bankId:null,
								costChild:null,
								gundanFlag:null,							
								flagPr:null,
								flagStatus:null,
								seqData:null,
								creBy:'<%=userId%>',
								updBy:'<%=userId%>',
     							confirmFlag:'N',
								bankBranch:null,
								bankCode:null};
							  
					document.getElementById("empCode").value = '';
					document.getElementById("empCode").focus();
		    }
		    
		    function onDelete(){
		    	var answer = confirm("ต้องการลบข้อมูล หรือไม่?");
		    	if( answer ){
			    	var empName = $("empName").value.replace(' ','space');
			    	//alert( empName );
			    	if( empName != 'space' ){
				    	prEmpTextVo.keySeq = '';
				    	prEmpTextVo.ouCode = '<%=ouCode%>';
						prEmpTextVo.year = $("year").value; 
						prEmpTextVo.period = $("period").value;
						prEmpTextVo.empCode = $("empCode").value;
						
						//if( !isEqualsValue($("codeSeqAct").value, $("hCodeSeqAct").value) )
						prEmpTextVo.codeSeqWork = $("codeSeqAct").value;
						
						//if( !isEqualsValue($("taxId").value, $("hTaxId").value) )
							prEmpTextVo.taxId = $("taxId").value;
						
						//if( !isEqualsValue($("marriedStatus").value, $("hMarriedStatus").value) )
							prEmpTextVo.marriedStatus = $("marriedStatus").value;
							
						//if( !isEqualsValue($("payStatus").value, $("hPayStatus").value) )
							prEmpTextVo.payStatus = $("payStatus").value;
	
						//if( !isEqualsValue($("bankId").value, $("hBankId").value) )
						
						    prEmpTextVo.bankBranch = $("bankBranch").value;
							prEmpTextVo.bankId = $("bankId").value;
							prEmpTextVo.bankCode = $("bankCode").value;
						
						 
						//if( !isEqualsValue($("costChild").value, $("hCostChild").value) )
							prEmpTextVo.costChild = $("costChild").value;
						
						
	
						//if( !isEqualsValue($("gundanFlag").value, $("hGundanFlag").value) )
							prEmpTextVo.gundanFlag = $("gundanFlag").value;
						
		
						//if( !isEqualsValue($("flagPr").value, $("hFlagPr").value) )
							prEmpTextVo.flagPr = $("flagPr").value;
						
	
						if( $("gundanFlag").checked ){
							//alert($("hGundanFlag").value);
							//if( !isEqualsValue('Y', $("hGundanFlag").value) )
								prEmpTextVo.gundanFlag = 'Y';
						}else{
							//if( !isEqualsValue('N', $("hGundanFlag").value) )
								prEmpTextVo.gundanFlag = 'N';
						}
	
							
						prEmpTextVo.flagStatus = 'D';
						
						//alert( prEmpTextVo.toJSONString() );
						
						FeeWpayPrEmployeeTextService.insertPrEmployeeText(prEmpTextVo, {callback:whenDeletePrEmployeeText,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้');}});
					}else{
						alert("โปรดกรอก เลขประจำตัว หรือ สังกัดปฏิบัติงานให้ถูกต้อง");
					}
				}
		    }
		    
		    
		    function whenDeletePrEmployeeText(data){
		    	alert("บันทึกข้อมูลเรียบร้อย");
		    	
		    	clearScreen();
		    	
		    	 prEmpTextVo = {	keySeq:null, 
		    					ouCode:null,
								year:null,
								period:null,
								empCode:null,
								codeSeqWork:null,
								taxId:null,
								marriedStatus:null,
								payStatus:null,
								bankId:null,
								costChild:null,
								gundanFlag:null,			
								flagPr:null,			
                    			flagStatus:null,
								seqData:null,
								creBy:'<%=userId%>',
								updBy:'<%=userId%>',
								confirmFlag:'N',			
                            	bankBranch:null,
                            	bankCode:null};
							  
					document.getElementById("empCode").value = '';
					document.getElementById("empCode").focus();
		    	
		    	//gotoMTPage();
		    }		    
		    
		    function gotoMTPage(){
		    	document.getElementById("orgFromEdit").value = '<%= orgFromEdit %>';
				document.getElementById("orgToEdit").value = '<%= orgToEdit %>';
				document.getElementById("empCodeFromEdit").value = '<%= empCodeFromEdit%>';
				document.getElementById("empCodeToEdit").value = '<%= empCodeToEdit %>';
				document.getElementById("pageEdit").value = '<%= Integer.parseInt(pageEdit) - 1 %>';
				
				document.forms["goMT"].submit();
		    }
			
			// this function use for compare 2 value is Equals ?
			function isEqualsValue(val1, val2){
				if( val1 == val2 )
					return true;
				else
					return false;
			}
			
			function initOrgCodeCbo()
			{
				try
				{
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
				//var cboEmp = dojo.widget.byId("empCode");
				//dojo.event.connect(cboEmp, "selectOption", "whenEmpSelectOption");
				//initOrgCodeCbo(); BY AMORN
				
				//var cbo = dojo.widget.byId("orgCode"); BY AMORN
				//dojo.event.connect(cbo, "selectOption", "whenFindOrganization"); BY AMORN
				
				onLoad();
				// ADD BY AMORN
				var cbo = dojo.widget.byId("orgCode");
				cbo.setDisable(true);
				document.forms['searchForm'].elements['empCode'].focus();
				document.forms['searchForm'].elements['taxId'].readOnly = true;
				document.forms['searchForm'].elements['taxId'].style.background = 'silver';
					document.forms['searchForm'].elements['flagPr'].disabled = true;
					document.forms['searchForm'].elements['flagPr'].style.background = 'silver';
				document.forms['searchForm'].elements['marriedStatus'].disabled = true;
					document.forms['searchForm'].elements['marriedStatus'].style.background = 'silver';
					
					
					document.forms['searchForm'].elements['payStatus'][0].disabled = true;
					document.forms['searchForm'].elements['payStatus'][1].disabled = true;
					document.forms['searchForm'].elements['bankBranch'].readOnly = true;
					document.forms['searchForm'].elements['bankBranch'].style.background = 'silver';
					
					document.forms['searchForm'].elements['bankId'].readOnly = true;
					document.forms['searchForm'].elements['bankId'].style.background = 'silver';
					document.forms['searchForm'].elements['costChild'].readOnly = true;
					document.forms['searchForm'].elements['costChild'].style.background = 'silver';
					document.forms['searchForm'].elements['gundanFlag'].disabled = true;
					document.forms['searchForm'].elements['gundanFlag'].style.background = 'silver';
	
					
				if( <%= isConfirm %> ){ 
					//document.forms['searchForm'].elements['empCode'].readOnly = true;
					//document.forms['searchForm'].elements['empCode'].style.background = 'silver';
					
					document.forms['searchForm'].elements['taxId'].focus();
					
					document.forms['searchForm'].elements['ok'].disabled = true;
					//document.forms['searchForm'].elements['delete'].disabled = true;
					
					var cbo = dojo.widget.byId("orgCode");
					cbo.setDisable(true);
					
					document.forms['searchForm'].elements['taxId'].readOnly = true;
					document.forms['searchForm'].elements['taxId'].style.background = 'silver';
					
					document.forms['searchForm'].elements['flagPr'].disabled = true;
					document.forms['searchForm'].elements['flagPr'].style.background = 'silver';
					//document.forms['searchForm'].elements['flagPr'].style.color = 'black';
										
					document.forms['searchForm'].elements['marriedStatus'].disabled = true;
					document.forms['searchForm'].elements['marriedStatus'].style.background = 'silver';
					
					document.forms['searchForm'].elements['payStatus'][0].disabled = true;
					document.forms['searchForm'].elements['payStatus'][1].disabled = true;
					
					document.forms['searchForm'].elements['bankId'].readOnly = true;
					document.forms['searchForm'].elements['bankId'].style.background = 'silver';
					document.forms['searchForm'].elements['bankCode'].disabled = true;
					document.forms['searchForm'].elements['bankCode'].style.background = 'silver';
					
					document.forms['searchForm'].elements['costChild'].readOnly = true;
					document.forms['searchForm'].elements['costChild'].style.background = 'silver';
					document.forms['searchForm'].elements['gundanFlag'].disabled = true;
					document.forms['searchForm'].elements['gundanFlag'].style.background = 'silver';
					
				}
			}
		    
		    dojo.addOnLoad(init);
		    
		    function checkMoney(obj){
		    	
		    	if(obj.value > 100000){
		    		alert('จำนวนเงินห้ามเกิน 100,000 บาท');
		    		document.getElementById("debtLife").value = '';
		    	}
		    }
		    
           function checkMoneySpouse(obj){
		    	
		    	if(obj.value > 10000){
		    		alert('จำนวนเงินห้ามเกิน 10,000 บาท');
		    		document.getElementById("debtLifeSpouse").value = '';
		    	}
		    }
		
		</script>

	</head>
	<body>
		<table width="100%">
			<tr>
				<td class="font-head">
					[ CTWPAYDL001 ] ลบข้อมูลหลักลูกจ้างออกจากงานค่าจ้าง
				</td>
			</tr>
		</table>
				<form name="searchForm" action="" method="post">
			<!-- Begin Declare Paging -->
			<!-- End Declare Paging -->
		<table width="100%" align="center">
		<tr><td align="center">
			<TABLE border="0" align="left" cellspacing="1">
				<TR>
					<TD width="354px" class="font-field" align="right">ประจำปี&nbsp;</TD>
					<TD align="left"><INPUT type="text" name="year" value="<%= year %>" readonly="readonly" style="width: 70px;text-align: center;background-color:silver;"></TD>
				    <TD width="46px" class="font-field" align="right">งวด&nbsp;</TD>
				    <TD align="left">
				    	<INPUT type="text" name="section" value="<%= section %>" readonly="readonly" style="width: 70px;text-align: center;background-color:silver;">
				    	<INPUT type="hidden" name="period" value="<%= period %>">
				    	<INPUT type="hidden" name="isConfirm" value="<%=isConfirm %>">
				    </TD>
				</TR>
			</TABLE>
		</td></tr>
		<tr><td align="center">
			<TABLE border="0" align="center" width="920px" cellspacing="1">
				<TR>
					<TD class="font-field" style="text-align:right;" width="200px">เลขประจำตัว</TD>
					<TD width="130px"><INPUT type="text" id="empCode" style="width:130px" maxlength="6" onblur="whenEmpBlur();" onkeypress="clearScreen();" onkeydown="clearScreen();" /></TD>
					<TD colspan="4"><INPUT type="text" id="empName" readonly="readonly" style="width:385px;text-align: left;background-color:silver;" /></TD>
					</TR>
				<TR>
					<TD class="font-field" style="text-align:right;" width="200px">สังกัดแต่งตั้ง</TD>
					<TD colspan="7" width="800px">
						<INPUT type="hidden" id="codeSeq">
						<INPUT type="text" id="codeSeqName" readonly="readonly" style="width:100%;text-align: left;background-color:silver;" />
					</TD>
				</TR>
				<TR>
					<TD class="font-field" style="text-align:right;" width="200px">สังกัดปฏิบัติงาน</TD>
					<TD width="780px" colspan="8">
						<table border="0" style="width:100%;border:none" cellpadding="0" cellspacing="0"><tr>
							<TD>
								<INPUT type="text" widgetId="orgCode" maxlength="20" dojoType="ComboBox" style="width:730px" onBlurInput="whenFindOrganization();" />
								<INPUT type="hidden" id="codeSeqAct" />
								
								<INPUT type="hidden" id="hOrgCode" />
            <INPUT type="hidden" id="hCodeSeqAct" />
							</TD>
							<!-- <TD  width="620px"><INPUT type="text" id="orgDesc" readonly="readonly" style="width:100%;text-align: left;background-color:silver;" /></TD> -->
						</tr></table>
					</TD>
				
				</TR>
			
				<TABLE border="1" align="center" width="950px" cellspacing="1">
				<TR>
						<TD colspan="8">
						<FIELDSET><LEGEND class="font-field">รายละเอียดการจ่ายเงิน
     
       </LEGEND>
						<TABLE border="01" width="100%" colspan="3">
							<TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR>
					<TD class="font-field" style="text-align:right;" width="400px">เลขบัตรประชาชน</TD>
					<TD width="130px">
						<INPUT type="text" id="taxId" maxlength="13" style="width:130px" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" />
						<INPUT type="hidden" id="hTaxId">
					</TD>
				 <TD class="font-field" style="text-align:right;" width="400px" >สถานะรับค่าจ้าง<INPUT type="hidden" id="hPayStatus"> </TD>
					<TD width="130px"><INPUT type="radio" value="1" name="payStatus" checked="checked"/><FONT class="font-field">จ่ายผ่านธนาคาร</FONT></TD>
					<TD width="130px"><input type="radio" value="2" name="payStatus"/><FONT class="font-field">เงินสด</FONT></TD>
					<TD width="240px" class="font-field" style="text-align:right;">เบี้ยกันดาร</TD>
					<TD width="30px">
						<input type="checkbox" name="gundanFlag" style="width:30px;disabled ="true" onclick="onGundanChecked();" />
						<INPUT type="hidden" id="hGundanFlag">
					</TD>
					
				</TR>
			
				<TR>
						<TD class="font-field" style="text-align:right;" width="450px">สถานะจ่ายค่าจ้าง</TD>
							<TD width="260px">
								<SELECT id="flagPr">
									<OPTION value="0">ปกติ</OPTION>
									<OPTION value="1">ระงับการจ่ายทั้งหมด</OPTION>
									<OPTION value="2">จ่ายให้เฉพาะเงินได้</OPTION>
								</SELECT>
								<INPUT type="hidden" id="hFlagPr">
							</TD>						
						<TD class="font-field" style="text-align:right;width:159px">สถานะการลดหย่อน</TD>
							<TD align="right">
								<SELECT id="marriedStatus" style="width:150px">
									<OPTION value="1">โสด</OPTION>
									<OPTION value="2">คู่สมรสไม่มีเงินได้</OPTION>
									<OPTION value="3">คู่สมรสมีเงินได้</OPTION>
									<OPTION value="4">หม้าย-หย่า-โสดมีบุตรบุญธรรม</OPTION>
								</SELECT>
								<INPUT type="hidden" id="hMarriedStatus">
							</TD>
				<TD class="font-field" style="width:600px;text-align:right;">บุตรที่ได้รับค่าช่วยเหลือบุตร </TD>
					<TD width="130px">
						<INPUT type="text" name="costChild" maxlength="5" style="width:123px;text-align:right;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" />
						<INPUT type="hidden" id="hCostChild">
					</TD>
					</TR>
				<TR>
				    	<TD class="font-field" style="text-align:right;" width="450px">เลือกธนาคาร</TD>
							<TD width="260px">
								<SELECT id="bankCode">
									<OPTION value="001">กรุงไทย</OPTION>
									<OPTION value="002">ไทยพาณิชย์</OPTION>
								</SELECT>
								<INPUT type="hidden" id="hBankCode">
							</TD>			
						<TD width="150px" class="font-err" style="text-align:right;">เลขที่บัญชีธนาคาร</TD>
					<TD width="150px">
						<input type="text" id="bankId" maxlength="10" style="width=150px;"onblur="validBankBranch();" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/>
						<INPUT type="hidden" id="hBankId">
					</TD>
					<TD width="150px" class="font-err" style="text-align:right;">รหัสสาขาธนาคาร</TD>
					<TD width="50px">
						<input type="text" id="bankBranch" maxlength="4" style="width=50px;" onblur="validBankBranch();" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/>
						<INPUT type="hidden" id="hBankBranch">
					</TD>
				</TR>
			</TABLE>
		</td></tr>
		</TABLE>	
		</TABLE>
		</td>
		</tr>
		</table>
		
			<BR/>
			<TABLE width="100%" CLASS="TABLEBULE2">
				<TR CLASS="TABLEBULE2" >
					<TD align="left" >&nbsp;
						<input type="button" class=" button " style="width: 60px" value="ลบ" id="delete" name="delete" onclick="onDelete();"/>
						<!-- <input type="button" class=" button " style="width: 60px" value="ตกลง" name="ok" onclick="save();"/> -->
						<input type="button" class=" button " style="width: 60px" value="ออก" name="back" onclick="gotoMTPage();"/>
						<!-- <input type="button" class=" button "  value="Clear" name="Clear" onclick="clearScreen();"/> -->
					</TD>
				</TR>
			</TABLE>
		</form>
		<FORM name="goMT" action="security.htm?reqCode=CTWPAYMT001" method="post">
			<input type="hidden" name="orgFromEdit" />
			<input type="hidden" name="orgToEdit" />
			<input type="hidden" name="empCodeFromEdit" />
			<input type="hidden" name="empCodeToEdit" />
			<input type="hidden" name="pageEdit" />
		</FORM>
	</body>
</html>
