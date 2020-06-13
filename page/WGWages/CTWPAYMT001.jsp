<%@ page language="java" contentType="text/html;charset=TIS-620"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@ page import="com.ss.tp.service.SuUserOrganizationService" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%
	UserInfo userInfo = (UserInfo)session.getAttribute("UserLogin");
	String userId = userInfo.getUserId();
	String ouCode = userInfo.getOuCode();
	
	String orgFromEdit = request.getParameter("orgFromEdit");
	String orgToEdit = request.getParameter("orgToEdit");
	String empCodeFromEdit = request.getParameter("empCodeFromEdit");
	String empCodeToEdit = request.getParameter("empCodeToEdit");
	String mustQuery = request.getParameter("mustQuery");
	// ***** declare for paging  **********
	String pageEdit = request.getParameter("pageEdit")==null?"-1":request.getParameter("pageEdit");
	SuUserOrganizationService userOrgService = (SuUserOrganizationService)session.getAttribute("userOrganizationSevice");
	
	if( session.getAttribute("OrganizationInSecurity") == null ){
		List orgList = userOrgService.findOrganizationByUserIdAndOuCode(userId, ouCode);
		session.setAttribute("OrganizationInSecurity", orgList);
	}
	
%>
<html>
	<head>
		<title>บันทึกข้อมูลหลักในการจัดทำค่าจ้างลูกจ้าง</title>
		<!-- Include -->
		<script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript" src="dwr/util.js"></script>
		<script type="text/javascript" src="script/payroll_util.js"></script>
		
		<!-- Javascript Script File -->
		<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrPeriodLineService.js"></SCRIPT>
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
			function onLoadYearSection(){
				//PrPeriodLineService.getDefaultYearAndSection('<%=ouCode%>', {callback:onLoadYearSectionCallback});
				onLoadYearSectionCallback();
			}
			//function onLoadYearSectionCallback(data){
			function onLoadYearSectionCallback(){
				//$("year").value = data.year;
				//$("section").value = data.section;
				//$("period").value = data.period;
				//$("isConfirm").value = data.confirm;
				
				$("year").value =   "<c:out value='${DefaultYearAndSection.year}' /> "  ;
				$("section").value = "<c:out value='${DefaultYearAndSection.section}' /> "  ;//data.section;
				$("period").value = ""+"<c:out value='${DefaultYearAndSection.period}' /> "  ;//data.period;
				$("isConfirm").value = "<c:out value='${DefaultYearAndSection.confirm}' /> "  ;
				//$("confirm").value = "<c:out value='${ConfirmMasterData}' />"  ;//data.confirm;
				//$("confirm").value = "<c:out value='${ConfirmMasterData}' />"  ;//data.confirm;
				
				//if(<c:out value='${ConfirmMaserData}' />)
					//if( $("isConfirm").value == 'true' ){
				//	document.forms['searchForm'].elements['add'].disabled = true;
				//	document.forms['searchForm'].elements['edit'].disabled = true;
				//	document.forms['searchForm'].elements['del'].disabled = true;
				//}
				if(<c:out value='${DefaultYearAndSection.confirm}' />){
					document.forms['searchForm'].elements['add'].disabled = true;
					document.forms['searchForm'].elements['edit'].disabled = true;
					document.forms['searchForm'].elements['del'].disabled = true;
				}
				chkMainClose();
			
				onLoadOrganization();
				
			}
			function chkMainClose(){
				FeeWpayPrPeriodLineService.findPeriodLine(
						'<%= ouCode %>',
						$("year").value,
						$("period").value,
								'<%= userId %>',
						{callback:whenchkMainCloseCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}}
					);	
			}
			
			function whenchkMainCloseCallback(data){
				if(data.mainClose == 'Y'){
					alert('ไม่สามารถดำเนินการต่อได้');
					window.history.back()
				}
			}
			function onLoadOrganization(){
		     	DWRUtil.useLoadingMessage("Loading ...");
		     	//SuUserOrganizationService.findOrganizationByUserIdAndOuCode('<%=userId%>','<%=ouCode%>' , {callback:onLoadOrganizationCallback});
		     	
		     	onLoadOrganizationCallback();
		    }
		    function onLoadOrganizationCallback(){
		     	try{
		     		//alert("bbb");
			     	var cboSource = [];
			     	var cboFrom = dojo.widget.byId("orgFromCbo");
			     	var cboTo = dojo.widget.byId("orgToCbo");
			     	
			     	//alert("aaa");
			     	
			     	/*for(var i=0; i<data.length; i++){
			     		var org = data[i];
			     		cboSource.push([org.orgCode, org.orgCode]);
			     	}*/
			     	
			     	<c:forEach items="${OrganizationInSecurity}" var="result" >		 
						cboSource.push(["<c:out value='${result.orgCode} ${result.divShort} ${result.areaDesc} ${result.secDesc} ${result.workDesc}' />","<c:out value='${result.orgCode}' />"]);
					</c:forEach>
					
			     	cboFrom.dataProvider.setData(cboSource);
			     	cboTo.dataProvider.setData(cboSource);
			     	
			     	//var tmp = data[0];
			     	//cboTo.textInputNode.value = tmp.orgCode;
			     	//cboTo.comboBoxSelectionValue.value = tmp.codeSeq;
			     	//alert("test");
			     	onLoadEmployee();
			     	
			     	
		     	}catch(e){
		     		alert(e.message);
		     	}
		    }
		    
		    function onLoadEmployee(){
				//PnEmployeeService.findPrEmpBySecurity('<%=userId%>','<%=ouCode%>', $("year").value, $("period").value, {callback:onLoadEmployeeCallback});
				
				onLoadEmployeeCallback();
			}
			function onLoadEmployeeCallback(){
		     	try{
		     	
		     	
		     		DWRUtil.useLoadingMessage("Loading ...");
			   	
			     	
			     	<%
			     		if( (orgFromEdit != null && !orgFromEdit.trim().equals("")) 
			     			|| (orgToEdit != null && !orgToEdit.trim().equals("")) 
			     			|| (empCodeFromEdit != null && !empCodeFromEdit.trim().equals("")) 
			     			|| (empCodeToEdit != null && !empCodeToEdit.trim().equals("")) 
			     			 ){
							System.out.println("orgFromEdit : " + orgFromEdit);
							System.out.println("orgToEdit : " + orgToEdit);
							System.out.println("empCodeFromEdit : " + empCodeFromEdit);
							System.out.println("empCodeToEdit : " + empCodeToEdit);
							System.out.println("pageEdit : " + pageEdit);
							
			     	%>
			     	
			     			var cboOrgFrom = dojo.widget.byId("orgFromCbo");
			     			var cboOrgTo = dojo.widget.byId("orgToCbo");
			     	
			     			
			     			cboOrgFrom.textInputNode.value = '<%= orgFromEdit %>';
			     			cboOrgTo.textInputNode.value = '<%= orgToEdit %>';
			     			
			     		
			     			$("empFrom").value = '<%= empCodeFromEdit %>';
			 				$("empTo").value = '<%= empCodeToEdit %>';
			 				
			     			$("page").value = '<%= pageEdit %>';
			     			
			     			whenQueryEmpBefore();
			     			
			     	<%
			     		}
			     	%>
			     	
			     	
			
		     	}catch(e){
		     		alert(e.message);
		     	}
		    }
		    
		    /****** Begin  OrgCodeFrom Select ********/
		    function whenOrgFromSelectOption(){
		    	DWRUtil.useLoadingMessage("Loading ...");
				var cbo = dojo.widget.byId("orgFromCbo");
				whenFetchOrganizationTo( splitCombo(cbo.textInputNode.value) );
			}
			function whenFetchOrganizationTo(orgCode){
		     	DWRUtil.useLoadingMessage("Loading ...");
		     	var cboTo = dojo.widget.byId("orgToCbo");
		     	//alert( orgCode + " " + cboTo.comboBoxSelectionValue.value + " " + (orgCode > cboTo.comboBoxSelectionValue.value));
		     	if( orgCode > splitCombo( cboTo.textInputNode.value ) ){
			     	cboTo.textInputNode.value = '';
		     	}
		     	
		     	var cboSource = [];
		     	
		     	<c:forEach items="${OrganizationInSecurity}" var="result" >		 
					if( <c:out value='${result.orgCode}' /> >= orgCode )
						cboSource.push(["<c:out value='${result.orgCode} ${result.divShort} ${result.areaDesc} ${result.secDesc} ${result.workDesc}' />","<c:out value='${result.orgCode}' />"]);
				</c:forEach>
		     	
		     	cboTo.dataProvider.setData(cboSource);
		     	
		
		    }
		  
		    /****** End  OrgCodeFrom Select ********/
			
			/****** Begin  EmpCodeFrom Select ********/
		
		
		    
		    function whenQueryEmpBefore(){
		    	DWRUtil.useLoadingMessage("Loading ...");
		     	var orgFromCbo = dojo.widget.byId("orgFromCbo");
				var orgToCbo = dojo.widget.byId("orgToCbo");
	
				
				var orgFromVal = '';
				var orgToVal = '';
				var empFromVal = '';
				var empToVal = '';
				
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
				
				empFromVal = $("empFrom").value;
			 	empToVal = $("empTo").value;	
			 	
		
				var page = '';
				if( parseInt( $("page").value ) == -1 ){
					page = 0;
					$("page").value = parseInt($("page").value) + 1;
				}else
					page = $("page").value;
				
		
				
				FeeWpayPrEmployeeService.findByCriteria(
		     		'<%= ouCode %>', 
		     		$("year").value, 
		     		$("period").value,
		     		'<%= userId %>',
		     		orgFromVal,
					orgToVal,
					empFromVal,
					empToVal,
					page,
					DWRUtil.getValue("dataPerPage"),
					{callback:whenQueryEmpCallBack,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}}
				);
				
		    }
		    function whenQueryEmpCallBack(data){
		     	//alert(data.length);
		     	if(data.length > 0){
					DWRUtil.removeAllRows("dataTable");
					DWRUtil.addRows("dataTable",data,cellFuncs);
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
		    
		    function countData(){
		    	DWRUtil.useLoadingMessage("Loading ...");
		     	var orgFromCbo = dojo.widget.byId("orgFromCbo");
				var orgToCbo = dojo.widget.byId("orgToCbo");
				//var	empFromCbo = dojo.widget.byId("empFromCbo");
				//var	empToCbo = dojo.widget.byId("empToCbo");
				
				var orgFromVal = '';
				var orgToVal = '';
				var empFromVal = '';
				var empToVal = '';
				
				//var empFrom = document.getElementById("empFrom");
				//var empTo = document.getElementById("empTo");
				
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
				
				empFromVal = $("empFrom").value;
			 	empToVal = $("empTo").value;	
			 	
		
				FeeWpayPrEmployeeService.getCountByCriteria(
		     		'<%= ouCode %>', 
		     		$("year").value, 
		     		$("period").value,
		     		'<%= userId %>',
		     		orgFromVal,
					orgToVal,
					empFromVal,
					empToVal,
					{callback:whenCountDataCallBack,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}}
				);
		    }
		    function whenCountDataCallBack(data){
		    	DWRUtil.setValue("countData",data);
				onCheckButt("searchForm");
		    }
			
			var cellFuncs = [
				function(data) { return "<div align='center' >"+data.empCode+"</div>";},
				function(data) { return data.name;},
				function(data) { return data.orgDesc;},
				function(data) { return "<div ><table border='0' width='100%'><tr><td align='center'>"+data.flagStatus+"</td></tr></table></div>";}
				//function(data) { return writeButton("edit",data.empCode);}
			];
			
			function writeButton(inname,emp)
			{
				return "<div align='center'><input type='button' class='button' name = '"+inname+"' value='...' onclick='preEdit(this.empId);' empId='"+emp+"' /></div>";
			}
			
			function preIns(){ 
				var frm=document.forms["insertForm"];
				var cboOrgFrom = dojo.widget.byId("orgFromCbo");
				var cboOrgTo = dojo.widget.byId("orgToCbo")
				
				$("yearIns").value = $("year").value;
				$("sectionIns").value = $("section").value;
				$("periodIns").value = $("period").value;
				//$("isConfirmIns").value = $("isConfirm").value;
				
				$("orgFromIns").value = cboOrgFrom.textInputNode.value;
				$("orgToIns").value = cboOrgTo.textInputNode.value;
	
				$("empCodeFromIns").value = $("empFrom").value;
			 	$("empCodeToIns").value = $("empTo").value;					
				$("pageIns").value = $("showPage").value;
				
				frm.submit();
			}
			
			function preEdit(){ 
				var frm=document.forms["editForm"];
				
				var cboOrgFrom = dojo.widget.byId("orgFromCbo");
				var cboOrgTo = dojo.widget.byId("orgToCbo");

				$("yearEdit").value = $("year").value;
				$("sectionEdit").value = $("section").value;
				$("periodEdit").value = $("period").value;
				
				$("isConfirmEdit").value = $("isConfirm").value;
		
				
				$("orgFromEdit").value = cboOrgFrom.textInputNode.value;
				$("orgToEdit").value =cboOrgTo.textInputNode.value;
		

				$("empCodeFromEdit").value = $("empFrom").value;
			 	$("empCodeToEdit").value = $("empTo").value;	
			 				 	
			 	$("pageEdit").value = $("showPage").value;
				
				frm.submit();
			}
			
			function preDelete(){ 
				var frm=document.forms["deleteForm"];
				
				var cboOrgFrom = dojo.widget.byId("orgFromCbo");
				var cboOrgTo = dojo.widget.byId("orgToCbo");
		
				
				$("yearDel").value = $("year").value;
				$("sectionDel").value = $("section").value;
				$("periodDel").value = $("period").value;
				
				$("isConfirmDel").value = $("isConfirm").value;
				//$("empCodeEdit").value = empId;
				
				$("orgFromDel").value = cboOrgFrom.textInputNode.value;
				$("orgToDel").value =cboOrgTo.textInputNode.value;
	
		
				$("empCodeFromDel").value = $("empFrom").value;
			 	$("empCodeToDel").value = $("empTo").value;	
			 		 	
			 	$("pageDel").value = $("showPage").value;
				
				frm.submit();
			}
			
			function init(){
				var cboOrgFrom = dojo.widget.byId("orgFromCbo");
				dojo.event.connect(cboOrgFrom, "selectOption", "whenOrgFromSelectOption");
			
				//var cboEmpFrom = dojo.widget.byId("empFromCbo");
				//dojo.event.connect(cboEmpFrom, "selectOption", "whenEmpFromSelectOption");
				
			}
		    
		    dojo.addOnLoad(init);
		    dojo.addOnLoad(onLoadYearSection);

		
		</script>

	</head>
	<body>
		<table width="100%">
			<tr>
				<td class="font-head">
					[ CTWPAYMT001 ] บันทึกข้อมูลหลักในการจัดทำค่าจ้างลูกจ้าง
				</td>
			</tr>
		</table>
		<form name="searchForm" action="" method="post">
			<table width="750" border="0" align="center" cellspacing="0">
				<tr>
					<td class="font-field" align="right">ประจำปี&nbsp;</td>
					<td align="left"><input type="text" name="year"  value="" readonly="readonly" style="width: 70px;text-align: center;background-color:silver;"/></td>
				    <td  class="font-field" align="right">งวด&nbsp;</td>
				    <td align="left">
				    	<input type="text" name="section"  value="" readonly="readonly" style="width: 70px;text-align: center;background-color:silver;"/>
				    	<input type="hidden" name="period" value="" />
				    	<INPUT type="hidden" name="isConfirm" value="" />
				    </td>
				    <td>&nbsp;</td>
				</tr>
			  	<tr>
			    	<td class="font-field" align="right">ตั้งแต่สังกัดปฎิบัติงานจริง&nbsp;</td>
			    	<td align="left" colspan="4"><SELECT  dojoType="ComboBox" widgetId="orgFromCbo" style="width:521px" onBlurInput="whenOrgFromSelectOption();"></SELECT></td>
			  	</tr>
			  	<tr>
			  		<td  class="font-field" align="right">ถึงสังกัดปฎิบัติงานจริง&nbsp;</td>
			    	<td align="left" colspan="4"><SELECT  dojoType="ComboBox" widgetId="orgToCbo" style="width:521px"></td>
			  	</tr>
  				<tr>
    				<td class="font-field" align="right">ตั้งแต่เลขประจำตัว&nbsp;</td>
    				<td align="left">
    					<input type="text" Id="empFrom" maxlength="6"  /> 
    					<!--<SELECT  dojoType="ComboBox" widgetId="empFromCbo" style="width:200" onBlurInput="whenEmpFromSelectOption();">   					
    					 <input type="text" id="empFrom" maxlength="6" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" /> -->
    				</td>
				    <td  class="font-field" align="right">ถึงเลขประจำตัว&nbsp;</td>
				    <td align="left">
				    	<input type="text" Id="empTo" maxlength="6"  /> 
				    		<!--  <SELECT  dojoType="ComboBox" widgetId="empToCbo" style="width:200">
				    			<input type="text" id="empTo" maxlength="6" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" /> -->
				    </td>
				    <td><INPUT type="button" class="button" value="ค้นหา" onclick="onQuery(whenQueryEmpBefore);"></td>
				</tr>
			</table>
			<div style="height:320px;">
			<table width="770"  border="1" bordercolor="#6699CC"  align="center"  cellpadding="2" cellspacing="0">
			<thead>
				<tr CLASS="TABLEBULE2">
				<th CLASS="TABLEBULE2" width="100">เลขประจำตัว</th>
				<th CLASS="TABLEBULE2">ชื่อ - นามสกุล</th>
				<th CLASS="TABLEBULE2">สังกัด</th>
				<th CLASS="TABLEBULE2" width="50">Status</th>
				<tr>
			</thead>
			<tbody id="dataTable" valign="middle">
			</tbody>
			</table>
			</div>
			<!-- Begin Declare Paging -->
			<table width="770" align="center"  cellpadding="2" cellspacing="0" >
				<tr>
					<td align="right">
						<input type="hidden" name="page" value="<%=pageEdit%>">
						<input type="hidden" name="maxPage">
						<input type="hidden" name="countData" >
						<input type="hidden" name="dataPerPage" value="10">
						<input type="button" disabled="disabled" class=" button " value="First" name="first" onclick="onFirst(whenQueryEmpBefore);"/>
						<input type="button" disabled="disabled" class=" button " value="<<" name="previous" onclick="onPrevious(whenQueryEmpBefore);"/>
						<input type="text"  name="showPage" style="text-align:right;width: 40;" 
							    onkeyup="onCheckPageNAN(this.value);" onchange="onChangeGoPage(whenQueryEmpBefore);" onkeypress="onKeyGoPage(event,whenQueryEmpBefore);" 
						/>
						/
						<input type="text"  name="showMaxPage" readonly="readonly" style="width: 40;border-style : none;background-color : transparent;text-align:right;font-weight:bold;"/>
						<input type="button" disabled="disabled" class=" button " value=">>" name="next" onclick="onNext(whenQueryEmpBefore);" />
						<input type="button" disabled="disabled" class=" button " value="Last" name="last" onclick="onLast(whenQueryEmpBefore);"/>
					</td>
				</tr>
			</table>
			<!-- End Declare Paging -->
			<table width="100%" CLASS="TABLEBULE2">
				<tr CLASS="TABLEBULE2" >
					<td align="left" >&nbsp;
						<input type="button" class=" button " style="width: 60px" value="เพิ่มข้อมูล" name="add" onclick="preIns();"/>
						<input type="button" class=" button " style="width: 60px" value="แก้ไขข้อมูล" name="edit" onclick="preEdit();"/>
						<input type="button" class=" button " style="width: 60px" value="ลบข้อมูล" name="del" onclick="preDelete();"/>
					</td>
				</tr>
			</table>
		</form>
		<form name="insertForm" action="security.htm?reqCode=CTWPAYIN001" method="post">
			<input type="hidden" name="yearIns" />
			<input type="hidden" name="sectionIns" />
			<input type="hidden" name="periodIns" />
			<input type="hidden" name="isConfirmIns" />
			
			<input type="hidden" name="orgFromIns" />
			<input type="hidden" name="orgToIns" />
			<input type="hidden" name="empCodeFromIns" />
			<input type="hidden" name="empCodeToIns" />
			<input type="hidden" name="pageIns" />
		</form>
		<form name="editForm" action="security.htm?reqCode=CTWPAYUP001" method="post">
			<input type="hidden" name="yearEdit" />
			<input type="hidden" name="periodEdit" />
			<input type="hidden" name="sectionEdit" />
			<input type="hidden" name="isConfirmEdit" />
			<input type="hidden" name="empCodeEdit" />
			
			<input type="hidden" name="orgFromEdit" />
			<input type="hidden" name="orgToEdit" />
			<input type="hidden" name="empCodeFromEdit" />
			<input type="hidden" name="empCodeToEdit" />
			<input type="hidden" name="pageEdit" />
		</form>
		<form name="deleteForm" action="security.htm?reqCode=CTWPAYDL001" method="post">
			<input type="hidden" name="yearDel" />
			<input type="hidden" name="periodDel" />
			<input type="hidden" name="sectionDel" />
			<input type="hidden" name="isConfirmDel" />
			<input type="hidden" name="empCodeDel" />
			
			<input type="hidden" name="orgFromDel" />
			<input type="hidden" name="orgToDel" />
			<input type="hidden" name="empCodeFromDel" />
			<input type="hidden" name="empCodeToDel" />
			<input type="hidden" name="pageDel" />
		</form>
	</body>
	<script type="text/javascript">
		if (DWRUtil.getValue("page") >= 0){
	
		}
	</script>
</html>
