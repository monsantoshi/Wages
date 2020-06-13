<%@ page language="java" contentType="text/html;charset=TIS-620"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%
	UserInfo userInfo = (UserInfo)session.getAttribute("UserLogin");
	String userId = userInfo.getUserId();
	String ouCode = userInfo.getOuCode();

	String orgFromEdit = request.getParameter("orgFromEdit");
	String orgToEdit = request.getParameter("orgToEdit");
	String empCodeFromEdit = request.getParameter("empCodeFromEdit");
	String empCodeToEdit = request.getParameter("empCodeToEdit");
	
		// ***** declare for paging  **********
	String pageEdit = request.getParameter("pageEdit")==null?"-1":request.getParameter("pageEdit");
	
	
%>
<html>
	<head>
		<title>ข้อมูลประจำเดือนของลูกจ้าง</title>
		<!-- Include -->
		<script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript" src="dwr/util.js"></script>
		<script type="text/javascript" src="script/payroll_util.js"></script>
		
		<!-- Javascript Script File -->
		<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrPeriodLineService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPnEmployeeService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrEmployeeService.js"></SCRIPT>
		<script type="text/javascript" src="dwr/interface/FeeWpayRwConfirmDataService.js"></script>

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
			 var statusConfirm = 'N';
			function onLoadYearSectionCallback(){
				$("year").value =   "<c:out value='${DefaultYearAndSection.year}' /> "  ;
				$("section").value = "<c:out value='${DefaultYearAndSection.section}' /> "  ;//data.section;
				$("period").value = ""+"<c:out value='${DefaultYearAndSection.period}' /> "  ;//data.period;
				
				//onLoadOrganization();
			}
			
			
			/****** Begin  EmpCodeFrom Select ********/
			function whenEmpFromSelectOption(){
				DWRUtil.useLoadingMessage("Loading ...");
				var cbo = dojo.widget.byId("empFromCbo");
				whenFetchEmployeeTo(cbo.textInputNode.value);
			}
			function whenFetchEmployeeTo(empCode){
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
		     	
		     	
		     	//PnEmployeeService.findPrToEmpBySecurity('<%=userId%>','<%=ouCode%>',empCode,$("year").value,$("period").value,{callback:whenFetchEmployeeToCallback});
		    }
		    function whenFetchEmployeeToCallback(data){
		    	try{
			     	var cboSource = [];
			     	var cboTo = dojo.widget.byId("empToCbo");
			     	for(var i=0; i<data.length; i++){
			     		var emp = data[i];
			     		cboSource.push([emp.empCode, emp.empCode]);
			     	}
			     	cboTo.dataProvider.setData(cboSource);
		     	}catch(e){
		     		alert(e.message);
		     	}
		    }
		    /******  End EmpCodeFrom Select ********/
			
			function onLoadEmployeeCallback(){
		     	try{
		     	//	DWRUtil.useLoadingMessage("Loading ...");
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
			     	
			     	<%
			     		if( (empCodeFromEdit != null && !empCodeFromEdit.trim().equals("")) 
			     			|| (empCodeToEdit != null && !empCodeToEdit.trim().equals("")) 
			     			 ){
							
							
			     	%>
			     	
			     		
			     			var cboBankIdFrom = dojo.widget.byId("bankIdFromCbo");
			     			var cboBankIdTo = dojo.widget.byId("bankIdToCbo");
			     			var cboEmpFrom = dojo.widget.byId("empFromCbo");
			     			var cboEmpTo = dojo.widget.byId("empToCbo");
			     			
			     			
			     			
			     			cboEmpFrom.textInputNode.value = '<%= empCodeFromEdit %>';
			     			cboEmpTo.textInputNode.value = '<%= empCodeToEdit %>';
			     			$("page").value = '<%= pageEdit %>';
			     			
			     			whenQueryEmp();
			     			
			     	<%
			     		}
			     	%>
			     	
		     	}catch(e){
		     		alert(e.message);
		     	}
		     } 
		     
		    function whenQueryEmpBefore()
		    {
		    	$("page").value = 0;
		    	whenQueryEmp();
		    } 
		     
		    function whenQueryEmp(){
		    	DWRUtil.useLoadingMessage("โปรดรอสักครู่ ...");
		    	
		 		
				var	bankIdFromCbo = dojo.widget.byId("bankIdFromCbo");
				var	bankIdToCbo = dojo.widget.byId("bankIdToCbo");
				
		     	var	empFromCbo = dojo.widget.byId("empFromCbo");
				var	empToCbo = dojo.widget.byId("empToCbo");
				
				
				var bankIdFromVal = '';
				var bankIdToVal = '';
				
				var empFromVal = '';
				var empToVal = '';
				
				
				if(bankIdFromCbo.textInputNode.value != ''){
					bankIdFromVal = bankIdFromCbo.textInputNode.value;
				}else{
					bankIdFromVal = '';
				}
				
				if(bankIdToCbo.textInputNode.value != ''){
					bankIdToVal = bankIdToCbo.textInputNode.value;
				}else{
					bankIdToVal = '';
				}
				
				if(empFromCbo.textInputNode.value != ''){
					empFromVal = empFromCbo.textInputNode.value;
				}else{
					empFromVal = '';
				}
				
				if(empToCbo.textInputNode.value != ''){
					empToVal = empToCbo.textInputNode.value;
				}else{
					empToVal = '';
				}
				
				var page = '';
				if( parseInt( $("page").value ) == -1 ){
					page = 0;
					$("page").value = parseInt($("page").value) + 1;
				}else
					page = $("page").value;
				
				
				
				FeeWpayPrEmployeeService.findByCriteriaIntBrk001(
		     		'<%= ouCode %>', 
		     		$("year").value, 
		     		$("period").value,
		     		'<%= userId %>',
		     		bankIdFromVal,
					bankIdToVal,
					empFromVal,
					empToVal,
					page,
					DWRUtil.getValue("dataPerPage"),
					{callback:whenQueryEmpCallBack}
				);
				
		    }
		    function whenQueryEmpCallBack(data){
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
		    	DWRUtil.useLoadingMessage("กำลังดำเนินการ ...");
				
				var	bankIdFromCbo = dojo.widget.byId("bankIdFromCbo");
				var	bankIdToCbo = dojo.widget.byId("bankIdToCbo");
				
		     	var	empFromCbo = dojo.widget.byId("empFromCbo");
				var	empToCbo = dojo.widget.byId("empToCbo");
				
				
				var bankIdFromVal = '';
				var bankIdToVal = '';
				
				var empFromVal = '';
				var empToVal = '';
				
				
				
				if(bankIdFromCbo.textInputNode.value != ''){
					bankIdFromVal = bankIdFromCbo.textInputNode.value;
				}else{
					bankIdFromVal = '';
				}
				
				if(bankIdToCbo.textInputNode.value != ''){
					bankIdToVal = bankIdToCbo.textInputNode.value;
				}else{
					bankIdToVal = '';
				}
				
				if(empFromCbo.textInputNode.value != ''){
					empFromVal = empFromCbo.textInputNode.value;
				}else{
					empFromVal = '';
				}
				
				if(empToCbo.textInputNode.value != ''){
					empToVal = empToCbo.textInputNode.value;
				}else{
					empToVal = '';
				}
				
				FeeWpayPrEmployeeService.getCountByCriteriaIntBrk001(
		     		'<%= ouCode %>', 
		     		$("year").value, 
		     		$("period").value,
		     		'<%= userId %>',
		  			bankIdFromVal,
					bankIdToVal,
					empFromVal,
					empToVal,
					{callback:whenCountDataCallBack}
				);
		    }
		    function whenCountDataCallBack(data){
		    	DWRUtil.setValue("countData",data);
				onCheckButt("searchForm");
		    }
			
			var cellFuncs = [
				function(data) { return "<div align='center'>"+data.empCode+"</div>";},
				function(data) { return data.name;},
				function(data) { 
								if(data.orgCode != null && data.orgDesc != null){
									return "<div align='left'>"+data.orgCode+" "+data.orgDesc+"</div>";
								}else if(data.orgCode != null && data.orgDesc == null){
									return "<div align='left'>"+data.orgCode+"</div>";
								}else{
									return "<div align='left'>&nbsp;</div>";
								}
								},
				function(data) { return writeButton("edit",data.empCode);}
			];
			
		
			function writeButton(inname,emp)
	{
	    
		return "<div align='center'><input type='button' class='button' name = '"+inname+"' value='คลิกเพิ่มลูกจ้างจ่ายเงินไม่ได้' onclick='preEdit(this.empId);' empId='"+emp+"' /></div>";
		
	}
	




	var emp = {keySeq:null};
	
			
	function preEdit(empId){ 
		if(confirm("ยืนยันการเพิ่มข้อมูลลูกจ้าง ไม่สามารถจ่ายเงินผ่านธนาคาร ?")){
		   statusConfirm = 'Y';
		
		}
		
		
		if (statusConfirm == 'Y'){
		    DWRUtil.useLoadingMessage("กำลังเพิ่มข้อมูล ...");
			FeeWpayRwConfirmDataService.insertEmpToPrBreakPay
		(
			'<%= userId %>',
			empId,
			'<%=ouCode%>',
			DWRUtil.getValue("year"),
			DWRUtil.getValue("period"),
		    {callback:whenReApproveHandler,errorHandler:function(message) {}}
		);
		}
		whenQueryEmp();
	}
	
	function whenReApproveHandler(data){
	    alert("เพิ่มข้อมูลแล้ว");
		//var btn = document.getElementById("edit");
		//btn.disabled = true;
		whenQueryEmp();
	}
			function init(){
				var cboEmpFrom = dojo.widget.byId("empFromCbo");
				var cboBankIdFrom = dojo.widget.byId("bankIdFromCbo");
				dojo.event.connect(cboEmpFrom, "selectOption", "whenEmpFromSelectOption");
				dojo.event.connect(cboBankIdFrom, "selectOption", "whenBankIdFromSelectOption");
			}
		    
		    
		    function onLoadBankId(){
		     	try{
			     	var cboSource = [];
			     	var cboFrom = dojo.widget.byId("bankIdFromCbo");
			     	var cboTo = dojo.widget.byId("bankIdToCbo");
			     	
			     	<c:forEach items="${BankIdInSecurity}" var="result" >		 
						cboSource.push(["<c:out value='${result.bankId}' />","<c:out value='${result.bankId}' />"]);
					</c:forEach>
					
			     	cboFrom.dataProvider.setData(cboSource);
			     	cboTo.dataProvider.setData(cboSource);
			     	
			     
			 
			   }catch(e){
		     		alert(e.message);
		     	}
		     }
		     
		      // Even ComboBox valueChange
		    function whenSelectBankIdOption(){
		    	DWRUtil.useLoadingMessage("Loading ...");
				var cbo = dojo.widget.byId("bankIdFromCbo");
				whenFetchBankIdTo(splitCombo(cbo.textInputNode.value));
			}
			function whenFetchBankIdTo(bankId){
		     	DWRUtil.useLoadingMessage("Loading ...");
		     	var cboTo = dojo.widget.byId("bankIdToCbo");
		     	
		     	if( bankId > cboTo.comboBoxSelectionValue.value ){
			     	cboTo.textInputNode.value = '';
			     	cboTo.comboBoxSelectionValue.value = '';
		     	}
		     	
		     	var cboSource = [];
		     	
		     	<c:forEach items="${BankIdInSecurity}" var="result" >	
		     		 
		     		var tmp = '<c:out value="${result.bankId}" />'+'.00';
		     		if( parseFloat(tmp.toString()) >= parseFloat(bankId) )
						cboSource.push(["<c:out value='${result.bankId}' />","<c:out value='${result.bankId}' />"]);
				</c:forEach>	
		     	
		     	cboTo.dataProvider.setData(cboSource);
		     	
		     	
		     	//PnEmployeeService.findPrToEmpBySecurity('<%=userId%>','<%=ouCode%>',empCode,$("year").value,$("period").value,{callback:whenFetchEmployeeToCallback});
		    }
		    dojo.addOnLoad(init);
		    dojo.addOnLoad(onLoadYearSectionCallback);
		    dojo.addOnLoad(onLoadEmployeeCallback);
		    dojo.addOnLoad(onLoadBankId);
		
		
		</script>

	</head>
	<body>
		<form name="searchForm" action="" method="post">
		<table width="100%">
			<tr>
				<td class="font-head">
					[ CTWPAYINBRK001 ] บันทึกลูกจ้างที่ไม่สามารถจ่ายค่าจ้างผ่านธนาคารได้
				</td>
			</tr>
		</table>
			<table border="0" align="center" width="650px" cellspacing="1">
				<tr>
					<td colspan="2">
						<table align="center">
							<tr>
								<td class="font-field" align="right">ประจำปี&nbsp;</td>
								<td align="left" width="150px">&nbsp;<input type="text" name="year"  value="" readonly="readonly" style="width: 70px;text-align: center;background-color:silver;"/></td>
							</tr>
						</table>
					</td>
				    <td class="font-field" align="left" width="120px">งวด</td>
				    <td align="left" width="150px">
				    	&nbsp;<input type="text" name="section"  value="" readonly="readonly" style="width: 70px;text-align: center;background-color:silver;"/>
				    	<input type="hidden" name="period" value="" />
				    </td>
				    <td>&nbsp;</td>
				</tr>
				
			</table>
			<table border="1" align="center" width="650px" cellspacing="1">
				<tr>
    				<td class="font-field" align="left">ตั้งแต่เลขประจำตัว&nbsp;</td>
    				<td align="left" width="150px"><SELECT  dojoType="ComboBox" widgetId="empFromCbo" style="width:150" onBlurInput="whenEmpFromSelectOption();"></td>
				    <td class="font-field" align="right" width="120px">ถึงเลขประจำตัว</td>
				    <td align="left" width="150px"><SELECT  dojoType="ComboBox" widgetId="empToCbo" style="width:150"></td>				    
				</tr>
				<tr>
    				<td class="font-field" align="left">ตั้งแต่เลขบัญชี&nbsp;</td>
    				<td align="left" width="150px"><SELECT  dojoType="ComboBox" widgetId="bankIdFromCbo" style="width:150" onBlurInput="whenBankIdSelectOption();"></td>
				    <td class="font-field" align="right" width="120px">ถึงเลขบัญชี</td>
				    <td align="left" width="150px"><SELECT  dojoType="ComboBox" widgetId="bankIdToCbo" style="width:150"></td>
				    <td width="55px"><INPUT type="button" class="button" value="ค้นหา" onclick="whenQueryEmpBefore();"></td>
				</tr>
			</table>
			<br>
			<div style="height:300px;">
			<table style="width:900px"  border="1" bordercolor="#6699CC"  align="center"  cellpadding="2" cellspacing="0">
			<thead>
				<tr CLASS="TABLEBULE2">
				<th CLASS="TABLEBULE2" style="width:100px">เลขประจำตัว</th>
				<th CLASS="TABLEBULE2" style="width:200px">ชื่อ - นามสกุล</th>
				<th CLASS="TABLEBULE2" style="width:450px">สังกัดปฏิบัติงานจริง</th>
				<th CLASS="TABLEBULE2" style="width:70px">คลิก</th>
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
						<input type="button" disabled="disabled" class=" button " value="First" name="first" onclick="onFirst(whenQueryEmp);"/>
						<input type="button" disabled="disabled" class=" button " value="<<" name="previous" onclick="onPrevious(whenQueryEmp);"/>
						<input type="text"  name="showPage" style="text-align:right;width: 40;" 
							    onkeyup="onCheckPageNAN(this.value);" onchange="onChangeGoPage(whenQueryEmp);" onkeypress="onKeyGoPage(event,whenQueryEmp);" 
						/>
						/
						<input type="text"  name="showMaxPage" readonly="readonly" style="width:40;border-style : none;background-color : transparent;text-align:right;font-weight:bold;"/>
						<input type="button" disabled="disabled" class=" button " value=">>" name="next" onclick="onNext(whenQueryEmp);" />
						<input type="button" disabled="disabled" class=" button " value="Last" name="last" onclick="onLast(whenQueryEmp);"/>
					</td>
				</tr>
			</table>
			<!-- End Declare Paging -->
			<table width="100%" CLASS="TABLEBULE2">
				<tr CLASS="TABLEBULE2" >
					<td>&nbsp;</td>
				</tr>
			</table>
		</form>
	
	</body>
	<script type="text/javascript">
		if (DWRUtil.getValue("page") >= 0){
			//whenQueryEmp(DWRUtil.getValue("page"));
		}
	</script>
</html>
