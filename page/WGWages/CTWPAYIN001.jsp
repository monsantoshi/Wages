<%@ page language="java" contentType="text/html;charset=TIS-620"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html"%>
<%
	UserInfo userInfo = (UserInfo)session.getAttribute("UserLogin");
	String userId = userInfo.getUserId();
	String ouCode = userInfo.getOuCode();
	
	String year = (String)request.getParameter("yearIns");
	String period = (String)request.getParameter("periodIns");
	String section = (String)request.getParameter("sectionIns");
	String isConfirm = (String)request.getParameter("isConfirmIns");
	
	String orgFromIns = request.getParameter("orgFromIns");
	String orgToIns = request.getParameter("orgToIns");
	String empCodeFromIns = request.getParameter("empCodeFromIns");
	String empCodeToIns = request.getParameter("empCodeToIns");
	String pageIns = request.getParameter("pageIns");
	
	if( pageIns.trim().equals("") )
		pageIns = "-1";
	
%>
<html>
	<head>
		<title>บันทึกข้อมูลหลักในการจัดทำค่าจ้างลูกจ้าง</title>
		<!-- Include -->
		<script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript" src="dwr/util.js"></script>
		<!-- Javascript Script File -->
		<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrPeriodLineService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPnEmployeeService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrEmployeeService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrEmployeeTextService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrIncomeDeductService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/VPnOrganizationSecurityService.js"></SCRIPT>
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
			
			/**  this variable use for check checkbox checked ? */
			var chkGundan = false;
		
			
			function onGundanChecked(){
				chkGundan = true;
				//alert( chkGundan );
			}
			
	
			
			/**** BEGIN When Employee Select ****/
		    function whenEmpBlur()
		    {
		    	if( $("empCode").value != '' ){
			    	DWRUtil.useLoadingMessage("Loading ...");
			    	//var cboEmp = dojo.widget.byId("empCode");
			    	FeeWpayPnEmployeeService.findPnEmpDetailNotInSecue('<%=userId%>', '<%=ouCode%>', $("empCode").value, $("year").value, $("period").value,{callback:whenPnEmployeeDeatilCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		    	}
		    }
		    
		    function whenPnEmployeeDeatilCallback(data)
		    {
		    	if( data.firstName != null )
		    	{
			    	$("empName").value = checkNull(data.prefixName,'STRING') + ' ' + checkNull(data.firstName,'STRING') + ' ' + checkNull(data.lastName,'STRING');
			 
			    	$("codeSeq").value = checkNull(data.codeSeq,'STRING');
			    	$("codeSeqName").value = checkNull(data.orgActDesc,'STRING');
			    	
			    	dojo.widget.byId("orgCode").textInputNode.value = '';//checkNull(data.orgCode,'STRING') + ' ' + checkNull(data.orgDesc,'STRING');
			    	$("hOrgCode").value = checkNull(data.orgCode,'STRING');
			    	
			    	//$("orgDesc").value = checkNull(data.orgDesc,'STRING'); // + ' ' + checkNull(data.orgDesc,'STRING');
			    	
			    	$("codeSeqAct").value = checkNull(data.codeSeqAct,'STRING');
			    	$("hCodeSeqAct").value = checkNull(data.codeSeqAct,'STRING');
			    	// call this because of empName lose at sometime
			    	FeeWpayPrEmployeeService.findPrEmployee('<%=ouCode%>', $("year").value, $("period").value, $("empCode").value, '<%=userId%>',{callback:whenPrEmployeeBlurCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		    	}else{
		    		alert("ไม่พบข้อมูลรหัสพนักงาน");
		    	}
		    }
		    function whenPrEmployeeBlurCallback(data){
		    
		    	var empName = Trim($("empName").value);
		    	//alert($("empName").value);
		    	if( empName != '' ){
			    	
			    	$("ok").disabled = false;
			    	//alert("test");
			    	
			    	// เลขประจำตัวผู้เสียภาษี
			    	$("taxId").value = checkNull(data.taxId,'STRING');
			    	$("hTaxId").value = checkNull(data.taxId,'STRING');
			    	
			    	//$("bankBranch").value = checkNull(data.bankBranch,'STRING');
			    	//$("hBankBranch").value = checkNull(data.bankBranch,'STRING');
			    	
			    	//$("bankId").value = checkNull(data.bankId,'STRING');
			    	//$("hBankId").value = checkNull(data.bankId,'STRING');
			    	
			  
			    	
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
	 		    	}
	 		    	
	 		    	// สถานะภาพลดหย่อน
	 		    	//var marriedStatus = document.getElementById("marriedStatus");
	 		    	//alert("marriedStatus : " + data.marriedStatus);
	 		    	//if( data.marriedStatus == '1' ){
	 		    	//	marriedStatus.selectedIndex = 0;
	 		    	//	$("hMarriedStatus").value = data.marriedStatus;
	 		    	
	 		    	//}else if( data.marriedStatus == '2' ){
	 		    	//	marriedStatus.selectedIndex = 1;
	 		    	///	$("hMarriedStatus").value = data.marriedStatus;
	 		    	//}else if( data.marriedStatus == '3' ){
	 		    	//	marriedStatus.selectedIndex = 2;
	 		    	//	$("hMarriedStatus").value = data.marriedStatus;
	 		    	
	 		    	//}else if( data.marriedStatus == '4' ){
	 		    	//	marriedStatus.selectedIndex = 3;
	 		    	//	$("hMarriedStatus").value = data.marriedStatus;
	 		    	//
	 		    	}
			    	//
			    	$("costChild").value = checkNull(data.costChild,'STRING');
			    	$("hCostChild").value = checkNull(data.costChild,'STRING');
	    	
			   	//var gundanFlag = document.getElementById("gundanFlag");
		    	//	if( data.gundanFlag == 'Y' )
		    	//		gundanFlag.checked = true;
		    	//	else
		    	//		gundanFlag.checked = false;
			   // }
		    }
		    function clearScreen(){
		    
		    	$("empName").value = '';
		    
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
 		    	$("bankCode").value = '';
 		    	//var bankCode = document.getElementById("bankCode");
 		    	//bankCode.selectedIndex = 0;
 		    	
 		    	// สถานะการจ่ายเงินเดือน
 		    	var flagPr = document.getElementById("flagPr");
 		    	flagPr.selectedIndex = 0;
 		    	
 		    	// สถานะภาพลดหย่อน
 		    	//var marriedStatus = document.getElementById("marriedStatus");
 		    	//marriedStatus.selectedIndex = 0;
 		    	
 		    	$("costChild").value = '';
		      	
		    	//var gundanFlag = document.getElementById("gundanFlag");
		    	//gundanFlag.checked = false;
		    	$("ok").disabled = true;
		    }
		    /**** END When Employee Select ****/
		    
		    
		    function whenFindOrganization()
		    {
		    	DWRUtil.useLoadingMessage("Loading ...");
		    	SuUserOrganizationService.findPrOrganizationByCriteria('<%=userId%>','<%=ouCode%>', splitCombo( dojo.widget.byId("orgCode").textInputNode.value ), $("year").value, $("period").value, {callback:whenFindOrganizationCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		    }
		    
		    function whenFindOrganizationCallback(data)
		    {
	    		$("codeSeqAct").value = data.codeSeq;
	    		//$("orgDesc").value = checkNull(data.divDesc,'STRING') + ' ' + checkNull(data.orgDesc,'STRING');

				//var orgDesc = Trim($("orgDesc").value);

				//if( orgDesc == '' )
		    	//{
			    //	alert("โปรดกรอกสังกัดปฏิบัติงานให้ถูกต้อง");
		    	//}
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
								flagStatus:'I',
								seqData:null,
								creBy:'<%=userId%>',
								updBy:'<%=userId%>',
								confirmFlag:'N',
								bankBranch:null,
								bankCode:null
							  };
		    
		    function save()
		    {
		    	var answer = confirm("ต้องการบันทึกข้อมูลคลิก OK ถ้าไม่คลิก Cancel แล้วคลิก ออก");
		    	if( answer ){
		    		var empName = Trim($("empName").value);
		    		//var orgDesc = Trim($("orgDesc").value);
		    	
					var cbo = dojo.widget.byId("orgCode");
				
		    	
					if( cbo.textInputNode.value == '' )
		    		{
			   		 	alert("โปรดกรอกสังกัดปฏิบัติงานให้ถูกต้อง");
		    		}else
		    		{
		    			VPnOrganizationSecurityService.findByKeyPK('<%=ouCode%>','<%=userId%>', splitCombo( cbo.textInputNode.value ),{callback:whenCheckCanSaveCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
					}
				}
		    }
		    
		    function whenCheckCanSaveCallback(data)
		    {
		    	var empName = Trim($("empName").value);
		    	var bankBranch = Trim($("bankBranch").value);
		    	var bankId = Trim($("bankId").value);
		    	var bankCode = Trim($("bankCode").value);
		    	//var orgDesc = Trim($("orgDesc").value);
		    	var cbo = dojo.widget.byId("orgCode");
		    	
		    	if(data.length < 1)
		    	{
		    		alert("สังกัดปฏิบัติงานไม่ถูกต้อง");
		    	}else
		    	{
			    	if( empName != '' && cbo.textInputNode.value != '')
			    	{
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
						//	prEmpTextVo.marriedStatus = $("marriedStatus").value;
							
						//if( !isEqualsValue($("payStatus").value, $("hPayStatus").value) )
							prEmpTextVo.payStatus = $("payStatus").value;
	
						//if( !isEqualsValue($("bankId").value, $("hBankId").value) )
						   prEmpTextVo.bankBranch = Trim($("bankBranch").value);
						
							prEmpTextVo.bankId = $("bankId").value;
							prEmpTextVo.bankCode = $("bankCode").value;
						
						//if( !isEqualsValue($("costChild").value, $("hCostChild").value) )
							prEmpTextVo.costChild = $("costChild").value;
						
							//if( !isEqualsValue($("gundanFlag").value, $("hGundanFlag").value) )
						//	prEmpTextVo.gundanFlag = $("gundanFlag").value;
					//if( !isEqualsValue($("flagPr").value, $("hFlagPr").value) )
							prEmpTextVo.flagPr = $("flagPr").value;
							
						//if( !isEqualsValue($("flagPr").value, $("hFlagPr").value) )
						//	prEmpTextVo.seqData = '';
						
						//if( $("gundanFlag").checked ){
							//alert($("hGundanFlag").value);
							//if( !isEqualsValue('Y', $("hGundanFlag").value) && chkGundan )
						//		prEmpTextVo.gundanFlag = 'Y';
						//}else{
							//if( !isEqualsValue('N', $("hGundanFlag").value) && chkGundan )
						//		prEmpTextVo.gundanFlag = 'N';
						//}
						
						prEmpTextVo.flagStatus = 'I';
						
						FeeWpayPrEmployeeTextService.insertPrEmployeeText(prEmpTextVo, {callback:whenInsertPrEmployeeText,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
					}else{
							alert("โปรดกรอก เลขประจำตัว หรือ สังกัดปฏิบัติงาน  ให้ถูกต้อง ");
					}
				}
		    }
		    
		    // comment this because move update to service
		    //function whenInsertPrEmployeeText(data){
		    //	PrEmployeeService.updatePrEmpByPrEmpText(prEmpTextVo, {callback:whenUpdatePrEmployee,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
			//}
		    
		    function whenInsertPrEmployeeText(data){
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
								flagStatus:'I',
								seqData:null,
								creBy:'<%=userId%>',
								updBy:'<%=userId%>',
								confirmFlag:'N',
								bankBranch:null,
								bankCode:null};
					
					document.getElementById("empCode").value = '';
					document.getElementById("empCode").focus();
		    }
		    
		    function gotoMTPage(){
		    	document.getElementById("orgFromEdit").value = '<%= orgFromIns %>';
				document.getElementById("orgToEdit").value = '<%= orgToIns %>';
				document.getElementById("empCodeFromEdit").value = '<%= empCodeFromIns %>';
				document.getElementById("empCodeToEdit").value = '<%= empCodeToIns %>';
				document.getElementById("pageEdit").value = '<%= Integer.parseInt(pageIns) - 1 %>';
		    
		    	document.forms["goMT"].submit();
		    }
			
			// this function use for compare 2 value is Equals ?
			function isEqualsValue(val1, val2){
				if( val1 == val2 )
					return true;
				else
					return false;
			}
			
			function validateCostLife(){
				var costLife = document.getElementById("costLife");
				if( costLife.value != null && costLife.value != '' ){
					if( costLife.value == '800' || costLife.value == '1600' || costLife.value == '2400' || costLife.value == '3200' )
					{
						PrIncomeDeductService.findMaxIncDecCode('<%= ouCode %>', '1', '07', {callback:whenFindMaxAmount,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}})
						//alert(costLife.value);
					}else{
						alert('กรุณาเลือกเงินยังชีพให้ถูกต้อง');
						costLife.focus();
					}
				}
			}
		   function checkID(id) {
		    	 if(id.length != 13) return false;
		    	 	 for(i=0, sum=0; i < 12; i++)
		    	 	 	 sum += parseFloat(id.charAt(i))*(13-i); if((11-sum%11)%10!=parseFloat(id.charAt(12)))
		    	 return false; return true;
		   }
		   
		   function validIdCard() {
		   		var taxId = document.getElementById("taxId");
		   			if(!checkID(taxId))
		   				 alert('เลขบัตรประชาชนไม่ถูกต้อง');
		   			else alert('เลขบัตรประชาชนถูกต้อง');
		   }
		 
			
	
			function whenFindMaxAmount(data){
				//alert(data);
				if( data != null && data != '' ){
					var costLife = document.getElementById("costLife");
					if( costLife.value > data ){
						alert('เลือกเงินยังชีพเกินกำหนด');
						costLife.focus();
					}
				}
			}
			
			
			//function validBankBranch(){
			//	var bankBranch = document.getElementById("bankBranch");
		    //	var bankId = Trim($("bankId").value);
		    	//alert('bankId '+bankId);		    	
		    	
			//	if( bankBranch.value == null && bankBranch.value == '' ){
			//			alert('กรุณาระบุรหัสสาขาธนาคาร');
			//			bankBranch.focus();
			//	}else if(bankBranch.value=='980'){
			//		    alert('รหัสสาขาธนาคาร 980 ผิด');
			//			bankBranch.focus();				
			//	}else if(bankId.substring(0,3) >='980'&& bankBranch.value < 1000){
			//	        alert('รหัสสาขาธนาคารต้องมากกว่า 1000');
			//			bankBranch.focus();				
			//	}else if(bankId.substring(0,3) <'980' && bankBranch.value != bankId.substring(0,3)){
			//	   		alert('รหัสสาขาธนาคารต้องตรงกับ 3 หลักแรกของเลขที่บัญชี');
			//			bankBranch.focus();		
			//	}
		//	}
			
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
				dojo.event.connect(cbo, "selectOption", "whenFindOrganization");
				
				if( '<%= isConfirm %>' == 'true' ){
					document.forms['searchForm'].elements['ok'].disabled = true;
				}
				
			}
		    
		    dojo.addOnLoad(init);
		     function checkChild(obj){
		    	
		    	if(obj.value > 3){
		    		alert('จำนวนบุตรรับค่าช่วยเหลือ ไม่เกิน 3 คน');
		    		document.getElementById("costChild").value = '';
		    	}
		    }
		    function checkMoney(obj){
		    	
		    	if(obj.value > 100000){
		    		alert('จำนวนเงินห้ามเกิน 100000 บาท');
		    		document.getElementById("debtLife").value = '';
		    	}
		    }
            function checkMoneySpouse(obj){
		    	
		    	if(obj.value > 10000){
		    		alert('จำนวนเงินห้ามเกิน 10000 บาท');
		    		document.getElementById("debtLifeSpouse").value = '';
		    	}
		    }
		   
		   function checkDebtLoanMoney(obj){
		    	
		    	if(obj.value > 100000){
		    		alert('จำนวนเงินห้ามเกิน 100000 บาท');
		    		document.getElementById("debtLoan").value = '';
		    	}
		    }
		    
		     function checkHealthFather(obj){
		    	
		    	if(obj.value > 15000){
		    		alert('จำนวนเงินห้ามเกิน 15,000 บาท');
		    		document.getElementById("healthFather").value = '';
		    	}
		    }
		    function checkRMFMoney(obj){
		    	
		    	if(obj.value > 500000){
		    		alert('จำนวนเงินห้ามเกิน 500000 บาท');
		    		document.getElementById("rmf").value = '';
		    	}
		    }
		    function checkLTFMoney(obj){
		    	
		    	if(obj.value > 500000){
		    		alert('จำนวนเงินห้ามเกิน 500000 บาท');
		    		document.getElementById("ltf").value = '';
		    	}
		    }
		</script>

	</head>
	<body>
		<table width="100%">
			<tr>
				<td class="font-head">
					[ CTWPAYIN001 ] เพิ่มข้อมูลหลักลูกจ้างเข้างานค่าจ้าง
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
						<INPUT type="text" id="taxId" maxlength="13" style="width:130px" onblur="validIdCard();" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" />
						<INPUT type="hidden" id="hTaxId">
					</TD>
				 <TD class="font-field" style="text-align:right;" width="400px" >สถานะรับค่าจ้าง<INPUT type="hidden" id="hPayStatus"> </TD>
					<TD width="130px"><INPUT type="radio" value="1" name="payStatus" checked="checked"/><FONT class="font-field">จ่ายผ่านธนาคาร</FONT></TD>
					<TD width="130px"><input type="radio" value="2" name="payStatus"/><FONT class="font-field">เงินสด</FONT></TD>
													
				</TR>
				<TR>
				    		<TD class="font-field" style="text-align:right;" width="450px">สถานะจ่ายค่าจ้าง</TD>
							<TD width="260px">
								<SELECT id="flagPr">
									<OPTION value="0">ปกติ</OPTION>
									<OPTION value="1">ระงับการจ่ายทั้งหมด</OPTION>									
								</SELECT>
								<INPUT type="hidden" id="hFlagPr">
							</TD>						
					<TD class="font-field" style="width:600px;text-align:right;">จำนวนบุตรที่ได้รับค่าช่วยเหลือบุตร </TD>
					<TD width="130px">
						<INPUT type="text" name="costChild" maxlength="5" style="width:123px;text-align:right;" onblur="checkChild(this);" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" />
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
						<input type="text" id="bankId" maxlength="10" style="width=150px;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/>
						<INPUT type="hidden" id="hBankId">
					</TD>
					<TD width="150px" class="font-err" style="text-align:right;">รหัสสาขาธนาคาร</TD>
					<TD width="50px">
						<input type="text" id="bankBranch" maxlength="4" style="width=50px;"  onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/>
						<INPUT type="hidden" id="hBankBranch">
					</TD>
			
				</TR>
			</TABLE>
		</td></tr>
		</TABLE>	
			<BR/>
		
						</FIELDSET>
					</TD>
				</TR>
			</TABLE>
			<TABLE width="100%" CLASS="TABLEBULE2">
				<TR CLASS="TABLEBULE2" >
					<TD align="left" >&nbsp;
						<input type="button" class=" button " style="width: 60px" value="ตกลง" id="ok" name="ok" onclick="save();"/>
						<!-- <input type="button" class=" button "  value="Clear" name="Clear" onclick="clearScreen();"/> -->
						<input type="button" class=" button " style="width: 60px" value="ออก" name="back" onclick="gotoMTPage();"/>
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
