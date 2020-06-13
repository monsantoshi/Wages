<%@ page language="java" contentType="text/html;charset=TIS-620"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%
	UserInfo userInfo = (UserInfo)session.getAttribute("UserLogin");
	String userId = userInfo.getUserId();
	String ouCode = userInfo.getOuCode();
	
	String year = (String)request.getParameter("yearEdit");
	String period = (String)request.getParameter("periodEdit");
	String section = (String)request.getParameter("sectionEdit");
	String isConfirm = (String)request.getParameter("isConfirmEdit");
	//String empCode = (String)request.getParameter("empCodeEdit");
	
	String orgFromEdit = request.getParameter("orgFromEdit");
	String orgToEdit = request.getParameter("orgToEdit");
	String empCodeFromEdit = request.getParameter("empCodeFromEdit");
	String empCodeToEdit = request.getParameter("empCodeToEdit");
	String pageEdit = request.getParameter("pageEdit");
	
	if( pageEdit.trim().equals("") )
		pageEdit = "-1";
	
	//isConfirm = "true";
	System.out.println("isConfirm : " + isConfirm);
%>
<html>
	<head>
		<title>�ѹ�֡��������ѡ㹡�èѴ�Ӥ�Ҩ�ҧ�١��ҧ</title>
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
		<SCRIPT type="text/javascript" src="dwr/interface/PrIncomeDeductService.js"></SCRIPT>
		
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
		    function onLoad(){
		    	DWRUtil.useLoadingMessage("Loading ...");
		    	$("year").value = '<%=year%>';
		    	$("period").value = '<%=period%>';
		    	$("section").value = '<%=section%>';
		    	$("isConfirm").value = '<%=isConfirm%>';
		    	
		    	
		    }
		    
		    function whenEmpBlur()
		    {
		    	if( $("empCode").value != '' ){
			    	DWRUtil.useLoadingMessage("Loading ...");
			    	//var cboEmp = dojo.widget.byId("empCode");
			    	FeeWpayPnEmployeeService.findByCriteriaInSecueEmpText('<%=userId%>', '<%=ouCode%>', $("empCode").value, $("year").value, $("period").value,{callback:whenLoadPnEmployeeDeatilCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
		    	}
		    }
		    
		    function whenLoadPnEmployeeDeatilCallback(data){
		    	//alert( data.toJSONString() );
		    
		    	if( data.firstName != null )
		    	{
			    	$("empName").value = checkNull(data.prefixName,'STRING') + ' ' + checkNull(data.firstName,'STRING') + ' ' + checkNull(data.lastName,'STRING');
			   	$("codeSeq").value = checkNull(data.codeSeq,'STRING');
			    	//alert( data.orgActDesc );
			    	//alert( data.orgDesc );
			    	$("codeSeqName").value = checkNull(data.orgActDesc,'STRING');
			    	
			    	dojo.widget.byId("orgCode").textInputNode.value = checkNull(data.orgCode,'STRING') + ' ' + checkNull(data.orgDesc,'STRING');
			    	$("hOrgCode").value = checkNull(data.orgCode,'STRING');
			    	
			    	//$("orgDesc").value = checkNull(data.orgDesc,'STRING'); // + ' ' + checkNull(data.orgDesc,'STRING');
			    	
			    	$("codeSeqAct").value = checkNull(data.codeSeqAct,'STRING');
			    	$("hCodeSeqAct").value = checkNull(data.codeSeqAct,'STRING');
			    	
			    	FeeWpayPrEmployeeService.findPrEmployee('<%=ouCode%>', $("year").value, $("period").value, $("empCode").value, '<%=userId%>',{callback:whenPrEmployeeLoadCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
			    }else
		    		alert("��辺���������ʾ�ѡ�ҹ");
		    }
		    function whenPrEmployeeLoadCallback(data){
		    
		    	var empName = $("empName").value.replace(' ','space');
		    	if( empName != 'space' ){
			    	
			    	$("ok").disabled = false;
			    	
			    	// �Ţ��Шӵ�Ǽ����������
			    	$("taxId").value = checkNull(data.taxId,'STRING');
			    	$("hTaxId").value = checkNull(data.taxId,'STRING');
			    	
			    	$("bankBranch").value = checkNull(data.bankBranch,'STRING');
			    	$("hBankBranch").value = checkNull(data.bankBranch,'STRING');
			    	
			    	$("bankId").value = checkNull(data.bankId,'STRING');
			    	$("hBankId").value = checkNull(data.bankId,'STRING');
			    	
			    	// ���͸�Ҥ��
	 		    	var bankCode = document.getElementById("bankCode");
	 		    	//alert("bankCode : " + data.bankCode);
	 		    	if( data.bankCode == '001' ){
	 		    		bankCode.selectedIndex = 0;
	 		    		$("hBankCode").value = data.bankCode;
	 		    	}else if( data.bankCode == '002' ){
	 		    		bankCode.selectedIndex = 1;
	 		    		$("hBankCode").value = data.bankCode;
	 		    	}
			    	
			    	// ʶҹС���Ѻ�Թ��͹
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
	 		    	
	 		    	// ʶҹС�è����Թ��͹
	 		    	var flagPr = document.getElementById("flagPr");
	 		    	if( data.flagPr == '0' ){
	 		    		flagPr.selectedIndex = 0;
	 		    		$("hFlagPr").value = data.flagPr;
	 		    	}else if( data.flagPr == '1' ){
	 		    		flagPr.selectedIndex = 1;
	 		    		$("hFlagPr").value = data.flagPr;
	 		    	}
	 		    	
	 		    	// ʶҹ��ҾŴ���͹
	 		    	//var marriedStatus = document.getElementById("marriedStatus");
	 		    	//alert("marriedStatus : " + data.marriedStatus);
	 		    	//if( data.marriedStatus == '1' ){
	 		    	//	marriedStatus.selectedIndex = 0;
	 		    	//	$("hMarriedStatus").value = data.marriedStatus;
	 		    	//}else if( data.marriedStatus == '2' ){
	 		    	//	marriedStatus.selectedIndex = 1;
	 		    	//	$("hMarriedStatus").value = data.marriedStatus;
	 		    	//}else if( data.marriedStatus == '3' ){
	 		    	//	marriedStatus.selectedIndex = 2;
	 		    	//	$("hMarriedStatus").value = data.marriedStatus;
	 		    	//}else if( data.marriedStatus == '4' ){
	 		    	//	marriedStatus.selectedIndex = 3;
	 		    	//	$("hMarriedStatus").value = data.marriedStatus;
	 		    	//}
			    	
			    	$("costChild").value = checkNull(data.costChild,'STRING');
			    	$("hCostChild").value = checkNull(data.costChild,'STRING');
			    	
			  	
			    	//var gundanFlag = document.getElementById("gundanFlag");
			    	//if( data.gundanFlag == 'Y' ){
			    	//	gundanFlag.checked = true;
			    	//	$("hGundanFlag").value = data.gundanFlag;
			    	//}else{
			    	//	gundanFlag.checked = false;
			    	//	$("hGundanFlag").value = data.gundanFlag;
			    	//}
			    	
			  	
			    	//var gundanFlag = document.getElementById("gundanFlag");
		    		//if( data.gundanFlag == 'Y' )
		    		//	gundanFlag.checked = true;
		    		//else
		    		//	gundanFlag.checked = false;
			    
			    }
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
 		    	
 		    	var bankCode = document.getElementById("bankCode");
 		    	bankCode.selectedIndex = 0;
 		    	// ʶҹС�è����Թ��͹
 		    	var flagPr = document.getElementById("flagPr");
 		    	flagPr.selectedIndex = 0;
 		    	
 		    	// ʶҹ��ҾŴ���͹
 		    	//var marriedStatus = document.getElementById("marriedStatus");
 		    	//marriedStatus.selectedIndex = 0;
 		    	
 		    	$("costChild").value = '';
		    	
		    	//var gundanFlag = document.getElementById("gundanFlag");
		    	//gundanFlag.checked = false;
		    	
		    	
		    	$("ok").disabled = true;
		    }
		    /**** END When Employee Select ****/
		    
		    
		    function whenFindOrganization(){
		    	DWRUtil.useLoadingMessage("Loading ...");
		    	SuUserOrganizationService.findPrOrganizationByCriteria('<%=userId%>','<%=ouCode%>', splitCombo( dojo.widget.byId("orgCode").textInputNode.value ), $("year").value, $("period").value, {callback:whenFindOrganizationCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
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
								bankCode:null
							  };
		    
		    function save()
		    {
		        var answer = confirm("��ͧ��úѹ�֡�����Ť�ԡ OK �������ԡ Cancel ���Ǥ�ԡ �͡");
		    	if( answer ){
		    		var empName = Trim($("empName").value);
		    		//var orgDesc = Trim($("orgDesc").value);
		    	
		    		var cbo = dojo.widget.byId("orgCode");
		    	
					if( cbo.textInputNode.value == '' )
		    		{
			   		 	alert("�ô��͡�ѧ�Ѵ��Ժѵԧҹ���١��ͧ");
		    		}else
		    		{
		    			VPnOrganizationSecurityService.findByKeyPK('<%=ouCode%>','<%=userId%>', splitCombo( dojo.widget.byId("orgCode").textInputNode.value ),{callback:whenCheckCanSaveCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
					}
				}
		    }
		    
		    function whenCheckCanSaveCallback(data)
		    {
		    	var cbo = dojo.widget.byId("orgCode");
		    	
		    	if(data == null)
		    	{
		    		alert("�ѧ�Ѵ��Ժѵԧҹ���١��ͧ");
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
						
						//if( !isEqualsValue($("marriedStatus").value, $("hMarriedStatus").value) )
						//	prEmpTextVo.marriedStatus = $("marriedStatus").value;
							
						if( !isEqualsValue($("payStatus").value, $("hPayStatus").value) )
							prEmpTextVo.payStatus = $("payStatus").value;
	
						 if( !isEqualsValue($("bankBranch").value, $("hBankBranch").value) )
							prEmpTextVo.bankBranch = Trim($("bankBranch").value);
							
						if( !isEqualsValue($("bankId").value, $("hBankId").value) ){
							prEmpTextVo.bankId = $("bankId").value;
						    //prEmpTextVo.bankCode = $("bankCode").value;
						}
						
						if( !isEqualsValue($("bankCode").value, $("hBankCode").value) ){
							prEmpTextVo.bankCode = $("bankCode").value;
						    //prEmpTextVo.bankCode = $("bankCode").value;
						}
						
						if( !isEqualsValue($("costChild").value, $("hCostChild").value) )
							prEmpTextVo.costChild = $("costChild").value;
						
					
						if( !isEqualsValue($("flagPr").value, $("hFlagPr").value) )
							prEmpTextVo.flagPr = $("flagPr").value;
	
					
						//if( $("gundanFlag").checked ){
							//alert($("hGundanFlag").value);
						//	if( !isEqualsValue('Y', $("hGundanFlag").value) && chkGundan )
						//		prEmpTextVo.gundanFlag = 'Y';
						//}else{
						//	if( !isEqualsValue('N', $("hGundanFlag").value) && chkGundan )
						//		prEmpTextVo.gundanFlag = 'N';
						//}
	
						prEmpTextVo.flagStatus = 'A';
						
						FeeWpayPrEmployeeTextService.insertPrEmployeeText(prEmpTextVo, {callback:whenInsertPrEmployeeText,errorHandler:function(message) { alert('�������ö�ѹ�֡��');}});
					}else{
						alert("�ô��͡ �Ţ��Шӵ�� ���� �ѧ�Ѵ��Ժѵԧҹ���١��ͧ");
					}
			    }
		    }
		    
		    // comment this because move update to service
		    //function whenInsertPrEmployeeText(data){
		    //	PrEmployeeService.updatePrEmpByPrEmpText(prEmpTextVo,{callback:whenUpdatePrEmployee,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
			//}
		    
		    function whenInsertPrEmployeeText(data)
		    {
		    	alert("�ѹ�֡���������º����");
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
		    	var answer = confirm("��ͧ���ź������ �������?");
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
						
						if( !isEqualsValue($("taxId").value, $("hTaxId").value) )
							prEmpTextVo.taxId = $("taxId").value;
						
						//if( !isEqualsValue($("marriedStatus").value, $("hMarriedStatus").value) )
						//	prEmpTextVo.marriedStatus = $("marriedStatus").value;
							
						if( !isEqualsValue($("payStatus").value, $("hPayStatus").value) )
							prEmpTextVo.payStatus = $("payStatus").value;
						
						 if( !isEqualsValue($("bankBranch").value, $("hBankBranch").value) )
							prEmpTextVo.bankBranch = $("bankBranch").value;
	          
						if( !isEqualsValue($("bankId").value, $("hBankId").value) )
							prEmpTextVo.bankId = $("bankId").value;
							
						if( !isEqualsValue($("bankCode").value, $("hBankCode").value) )
							prEmpTextVo.bankCode = $("bankCode").value;
						 
						if( !isEqualsValue($("costChild").value, $("hCostChild").value) )
							prEmpTextVo.costChild = $("costChild").value;
						
						//if( !isEqualsValue($("gundanFlag").value, $("hGundanFlag").value) )
						//	prEmpTextVo.gundanFlag = $("gundanFlag").value;
						
						
						if( !isEqualsValue($("flagPr").value, $("hFlagPr").value) )
							prEmpTextVo.flagPr = $("flagPr").value;
	
						//alert( prEmpTextVo.toJSONString() );
						
						prEmpTextVo.flagStatus = 'D';
						
						FeeWpayPrEmployeeTextService.insertPrEmployeeText(prEmpTextVo, {callback:whenDeletePrEmployeeText,errorHandler:function(message) { alert('�������ö�ѹ�֡��');}});
					}else{
						alert("�ô��͡ �Ţ��Шӵ�� ���� �ѧ�Ѵ��Ժѵԧҹ���١��ͧ");
					}
				}
		    }
		    function whenDeletePrEmployeeText(data){
		    	alert("�ѹ�֡���������º����");
		    	
		    	gotoMTPage();
		    }		    
		    
		    function gotoMTPage(){
		    	document.getElementById("orgFromEdit").value = '<%= orgFromEdit %>';
				document.getElementById("orgToEdit").value = '<%= orgToEdit %>';
				document.getElementById("empCodeFromEdit").value = '<%= empCodeFromEdit %>';
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
			
			function validateCostLife(){
				var costLife = document.getElementById("costLife");
				if( costLife.value != null && costLife.value != '' ){
					if( costLife.value == '800' || costLife.value == '1600' || costLife.value == '2400' || costLife.value == '3200' )
					{
						PrIncomeDeductService.findMaxIncDecCode('<%= ouCode %>', '1', '07', {callback:whenFindMaxAmount,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}})
						//alert(costLife.value);
					}else{
						alert('��سҡ�͡�Թ�ѧ�վ���١��ͧ');
						costLife.focus();
					}
				}
			}
			
	
			function whenFindMaxAmount(data){
				//alert(data);
				if( data != null && data != '' ){
					var costLife = document.getElementById("costLife");
					//alert( costLife.value + ' ' + data );
					if( costLife.value > data ){
						alert('��͡�Թ�ѧ�վ�Թ��˹�');
						costLife.focus();
					}
				}
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
				//initOrgCodeCbo();
				
				var cbo = dojo.widget.byId("orgCode");
				cbo.setDisable(true);
				//dojo.event.connect(cbo, "selectOption", "whenFindOrganization");
				
				onLoad();
				document.forms['searchForm'].elements['empCode'].focus();
				if( <%= isConfirm %> ){
					//document.forms['searchForm'].elements['empCode'].readOnly = true;
					//document.forms['searchForm'].elements['empCode'].style.background = 'silver';
					document.forms['searchForm'].elements['empCode'].focus();
					//document.forms['searchForm'].elements['taxId'].focus();
					
					document.forms['searchForm'].elements['ok'].disabled = true;
					//document.forms['searchForm'].elements['delete'].disabled = true;
					
					var cbo = dojo.widget.byId("orgCode");
					cbo.setDisable(true);
					
					document.forms['searchForm'].elements['taxId'].readOnly = true;
					document.forms['searchForm'].elements['taxId'].style.background = 'silver';
					
					document.forms['searchForm'].elements['flagPr'].disabled = true;
					document.forms['searchForm'].elements['flagPr'].style.background = 'silver';
					//document.forms['searchForm'].elements['flagPr'].style.color = 'black';
										
					//document.forms['searchForm'].elements['marriedStatus'].disabled = true;
					//document.forms['searchForm'].elements['marriedStatus'].style.background = 'silver';
					
					document.forms['searchForm'].elements['payStatus'][0].disabled = true;
					document.forms['searchForm'].elements['payStatus'][1].disabled = true;
					
				    document.forms['searchForm'].elements['bankBranch'].readOnly = true;
					document.forms['searchForm'].elements['bankBranch'].style.background = 'silver';
					
					document.forms['searchForm'].elements['bankId'].readOnly = true;
					document.forms['searchForm'].elements['bankId'].style.background = 'silver';
					
					document.forms['searchForm'].elements['bankCode'].disabled = true;
					document.forms['searchForm'].elements['bankCode'].style.background = 'silver';
				
					document.forms['searchForm'].elements['costChild'].readOnly = true;
					document.forms['searchForm'].elements['costChild'].style.background = 'silver';
					
				
					//document.forms['searchForm'].elements['gundanFlag'].disabled = true;
					//document.forms['searchForm'].elements['gundanFlag'].style.background = 'silver';
					

				}
			}
		    
		    dojo.addOnLoad(init);
		    
		    function checkMoney(obj){
		    	
		    	if(obj.value > 100000){
		    		alert('�ӹǹ�Թ�����Թ 100000 �ҷ');
		    		document.getElementById("debtLife").value = '';
		    	}
		    }
		    
            function checkMoneySpouse(obj){
		    	
		    	if(obj.value > 10000){
		    		alert('�ӹǹ�Թ�����Թ 10000 �ҷ');
		    		document.getElementById("debtLifeSpouse").value = '';
		    	}
		    }
		    
		    function checkChild(obj){
		    	
		    	if(obj.value > 5){
		    		alert('�ӹǹ�ص��Ѻ��Ҫ�������� ����Թ 3 �� ¡���ὴ');
		    		document.getElementById("costChild").value = '';
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
		   				 alert('�Ţ�ѵû�ЪҪ����١��ͧ');
		   			else alert('�Ţ�ѵû�ЪҪ��١��ͧ');
		   }
		   function checkBankId(obj){
		    	
		    	if(obj.value.length !=10){
		    		alert('�Ţ���ѭ�ո�Ҥ�����ú 10 ��ѡ');
		    		document.getElementById("bankId").value = '';
                                $("ok").disabled = true;

		    	}else
                                 $("ok").disabled = false;

		    }
		 
		</script>

	</head>
	<body>
		<table width="100%">
			<tr>
				<td class="font-head">
					[ CTWPAYUP001 ] ��䢢�������ѡ�١��ҧ��к���Ҩ�ҧ (�������١��ҧ��è�����)
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
					<TD width="354px" class="font-field" align="right">��Шӻ�&nbsp;</TD>
					<TD align="left"><INPUT type="text" name="year" value="<%= year %>" readonly="readonly" style="width: 70px;text-align: center;background-color:silver;"></TD>
				    <TD width="46px" class="font-field" align="right">�Ǵ&nbsp;</TD>
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
					<TD class="font-field" style="text-align:right;" width="200px">�Ţ��Шӵ��</TD>
					<TD width="130px"><INPUT type="text" id="empCode" style="width:130px" maxlength="6" onblur="whenEmpBlur();" onkeypress="clearScreen();" onkeydown="clearScreen();" /></TD>
					<TD colspan="4"><INPUT type="text" id="empName" readonly="readonly" style="width:385px;text-align: left;background-color:silver;" /></TD>
				</TR>
			
				<TR>
					<TD class="font-field" style="text-align:right;" width="200px">�ѧ�Ѵ�觵��</TD>
					<TD colspan="7" width="800px">
						<INPUT type="hidden" id="codeSeq">
						<INPUT type="text" id="codeSeqName" readonly="readonly" style="width:100%;text-align: left;background-color:silver;" />
					</TD>
				</TR>
				<TR>
					<TD class="font-field" style="text-align:right;" width="200px">�ѧ�Ѵ��Ժѵԧҹ</TD>
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
						<FIELDSET><LEGEND class="font-field">��������´��è����Թ
     
       </LEGEND>
						<TABLE border="01" width="100%" colspan="3">
							<TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR>
					<TD class="font-field" style="text-align:right;" width="400px">�Ţ�ѵû�ЪҪ�</TD>
					<TD width="130px">
						<INPUT type="text" id="taxId" maxlength="13" style="width:130px"  onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" />
						<INPUT type="hidden" id="hTaxId">
					</TD>
				 <TD class="font-field" style="text-align:right;" width="400px" >ʶҹ��Ѻ��Ҩ�ҧ<INPUT type="hidden" id="hPayStatus"> </TD>
					<TD width="130px"><INPUT type="radio" value="1" name="payStatus" checked="checked"/><FONT class="font-field">���¼�ҹ��Ҥ��</FONT></TD>
					<TD width="130px"><input type="radio" value="2" name="payStatus"/><FONT class="font-field">�Թʴ</FONT></TD>
                    <!--<TD width="240px" class="font-field" style="text-align:right;">���¡ѹ���</TD>-->
					<!--<TD width="30px">
						<input type="checkbox" name="gundanFlag" style="width:30px;disabled ="true" onclick="onGundanChecked();" />
						<INPUT type="hidden" id="hGundanFlag">
					</TD>-->				
				</TR>		
				<TR>
				   	<TD class="font-field" style="text-align:right;" width="450px">ʶҹШ��¤�Ҩ�ҧ</TD>
							<TD width="260px">
								<SELECT id="flagPr">
									<OPTION value="0">����</OPTION>
									<OPTION value="1">�ЧѺ��è��·�����</OPTION>									
								</SELECT>
								<INPUT type="hidden" id="hFlagPr">
							</TD>		
					<!--<TD class="font-field" style="text-align:right;width:159px">ʶҹС��Ŵ���͹</TD>-->
							<!--<TD align="right">
								<SELECT id="marriedStatus" style="width:150px">
									<OPTION value="1">�ʴ</OPTION>
									<OPTION value="2">�������������Թ��</OPTION>
									<OPTION value="3">����������Թ��</OPTION>
									<OPTION value="4">�����-����-�ʴ�պصúح����</OPTION>
								</SELECT>
								<INPUT type="hidden" id="hMarriedStatus">
							</TD>-->
				
					<TD class="font-field" style="width:600px;text-align:right;">�ӹǹ�ص÷�����Ѻ��Ҫ�������ͺص� </TD>
					<TD width="130px">
						<INPUT type="text" name="costChild" maxlength="5" style="width:123px;text-align:right;" onblur="checkChild(this);" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" />
						<INPUT type="hidden" id="hCostChild">
					</TD>
				
					
				</TR>
				<TR>
				      	<TD class="font-field" style="text-align:right;" width="450px">���͡��Ҥ��</TD>
							<TD width="260px">
								<SELECT id="bankCode">
									<OPTION value="001">��ا��</OPTION>
									<OPTION value="002">�¾ҳԪ��</OPTION>									
								</SELECT>
								<INPUT type="hidden" id="hBankCode">
							</TD>		
			
					<TD width="150px" class="font-err" style="text-align:right;">�Ţ���ѭ�ո�Ҥ��</TD>
					<TD width="150px">
						<input type="text" id="bankId" maxlength="10" style="width=150px;" onblur="checkBankId(this);" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/>
						<INPUT type="hidden" id="hBankId">
					</TD>
					<TD width="150px" class="font-err" style="text-align:right;">�����ҢҸ�Ҥ��</TD>
					<TD width="50px">
						<input type="text" id="bankBranch" maxlength="4" style="width=50px;"  onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/>
						<INPUT type="hidden" id="hBankBranch">
					</TD>
				
				</TR>
			</TABLE>
		</td></tr>
		</TABLE>	
			<BR/>
			
			<TABLE width="100%" CLASS="TABLEBULE2">
				<TR CLASS="TABLEBULE2" >
					<TD align="left" >&nbsp;
						<input type="button" class=" button " style="width: 60px" value="��ŧ" id="ok" name="ok" onclick="save();"/>
						<!-- <input type="button" class=" button "  value="Clear" name="Clear" onclick="clearScreen();"/> -->
						<input type="button" class=" button " style="width: 60px" value="�͡" name="back" onclick="gotoMTPage();"/>
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
