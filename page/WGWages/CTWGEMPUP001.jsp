<%@ page language="java" contentType="text/html;charset=TIS-620"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="javax.swing.text.Document"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@ page import="sun.security.krb5.internal.i" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%
	UserInfo userInfo = (UserInfo)session.getAttribute("UserLogin");
	String userId = userInfo.getUserId();
	String ouCode = userInfo.getOuCode();
	String flagQY = request.getParameter("flagEdit");
	
	String orgFromEdit = ""; 
	String orgToEdit = "";
	String empCodeFromEdit = ""; 
	String empCodeToEdit = "";
	String pageEdit = "";
	
	System.out.println("flag : " + flagQY);
	
	if( flagQY != null && flagQY.equals("MT") ){
		orgFromEdit = request.getParameter("orgFromEdit");
		orgToEdit = request.getParameter("orgToEdit");
		empCodeFromEdit = request.getParameter("empCodeFromEdit");
		empCodeToEdit = request.getParameter("empCodeToEdit");
		pageEdit = request.getParameter("pageEdit");
	}else if( flagQY != null && flagQY.equals("DT2") ){
		orgFromEdit = request.getParameter("orgFromEdit2");
		orgToEdit = request.getParameter("orgToEdit2");
		empCodeFromEdit = request.getParameter("empCodeFromEdit2");
		empCodeToEdit = request.getParameter("empCodeToEdit2");
		pageEdit = request.getParameter("pageEdit2");
	}
	
	System.out.println("orgFromEdit : " + orgFromEdit);
	System.out.println("orgToEdit : " + orgToEdit);
	System.out.println("empCodeFromEdit : " + empCodeFromEdit);
	System.out.println("empCodeToEdit : " + empCodeToEdit);
	
	if( pageEdit.trim().equals("") )
		pageEdit = "-1";
		
	System.out.println("pageEdit : " + pageEdit);
		
	String empCode = (String)request.getParameter("empCodeQuery");
%>
<html>
	<head>
		<title>สอบถามข้อมูลประจำเดือนของลูกจ้าง (ข้อมูลหลัก)</title>
		<!-- Include -->
		<script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript" src="dwr/util.js"></script>
		<!-- Javascript Script File -->
		<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/VPnOrganizationSecurityService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWgPnEmployeeService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWgEmpWorkService.js"></SCRIPT>
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
			
		   

			function whenEmpBlur()
			{
			    //alert("whenEmpBlue");
		    	if( '<%=empCode%>' != '' ){
			    	DWRUtil.useLoadingMessage("Loading ...");
			    	FeeWgPnEmployeeService.findPnEmpDetailInSecue('<%=userId%>', '<%=ouCode%>', '<%=empCode%>',{callback:whenPnEmployeeDeatilCallback});			    	
		    	}
		    }
		    
		    function whenPnEmployeeDeatilCallback(data)
		   {
		    	
			    //alert("When");
			    $("firstName").value = checkNull(data.firstName,'STRING');
			    $("lastName").value = checkNull(data.lastName,'STRING');
			    $("hFirstName").value = checkNull(data.firstName,'STRING');
			    $("hLastName").value = checkNull(data.lastName,'STRING');
			    
		    	dojo.widget.byId("orgCode").textInputNode.value = checkNull(data.orgCode,'STRING') + ' ' + checkNull(data.orgDesc,'STRING');
			    $("pDate").value = checkNull(data.PDate,'STRING');
			    $("hPDate").value = checkNull(data.PDate,'STRING');
		    	
		    	$("hOrgCode").value = checkNull(data.orgCode,'STRING');
		    
		    	$("codeSeqAct").value = checkNull(data.codeSeqAct,'STRING');
			    $("hCodeSeqAct").value = checkNull(data.codeSeqAct,'STRING');
			
		    	
		    		var payStatus = document.forms["searchForm"].elements["payStatus"];
		    	if( data.payFlag == '1' ){
 		    		payStatus[0].checked = true;
 		    		$("hPayStatus").value = data.payFlag;
 		    	}else if( data.payFlag == '2' ){
 		    		payStatus[1].checked = true;
 		    		$("hPayStatus").value = data.payFlag;
 		    	}else{
 		    		payStatus[0].checked = true;
 		    	}
		    	var empStatus = document.getElementById("empStatus");
 		    	//alert("marriedStatus : " + data.prefixName);
 		    	if( data.empStatus == 'B' ){
 		    		empStatus.selectedIndex = 0;
 		    		$("hEmpStatus").value = data.empStatus;
 		    	}else if( data.empStatus == 'R' ){
 		    		empStatus.selectedIndex = 1;
 		    		$("hEmpStatus").value = data.empStatus;
 		    	}else if( data.empStatus == 'A' ){
 		    		empStatus.selectedIndex = 2;
 		    		$("hEmpStatus").value = data.empStatus;
 		    	}
		    	var prefixName = document.getElementById("prefixName");
 		    	//alert("marriedStatus : " + data.prefixName);
 		    	if( data.prefixName == 'นาย' ){
 		    		prefixName.selectedIndex = 0;
 		    		$("hPrefixName").value = data.prefixName;
 		    	}else if( data.prefixName == 'นาง' ){
 		    		prefixName.selectedIndex = 1;
 		    		$("hPrefixName").value = data.prefixName;
 		    	}else if( data.prefixName == 'น.ส.' ){
 		    		prefixName.selectedIndex = 2;
 		    		$("hPrefixName").value = data.prefixName;
 		    	}
 		    // สถานะภาพลดหย่อน
 		        //var marriedStatus = document.forms["searchForm"].elements["marriedStatus"];
 		    	var marriedStatus = document.getElementById("marriedStatus");
 		    	//alert("marriedStatus : " + data.marriedStatus);
 		    	if( data.maritalStatus == '1' ){
 		    		marriedStatus.selectedIndex = 0;
 		    		$("hMarriedStatus").value = data.maritalStatus;
 		    	}else if( data.maritalStatus == '2' ){
 		    		marriedStatus.selectedIndex = 1;
 		    		$("hMarriedStatus").value = data.maritalStatus;
 		    	}else if( data.maritalStatus == '3' ){
 		    		marriedStatus.selectedIndex = 2;
 		    		$("hMarriedStatus").value = data.maritalStatus;
 		    	}else if( data.maritalStatus == '4' ){
 		    		marriedStatus.selectedIndex = 3;
 		    		$("hMarriedStatus").value = data.maritalStatus;
 		    	}
 		    }
		    
		  
		   
		    function whenFindOrganization()
		    {
		    	DWRUtil.useLoadingMessage("Loading ...");
		    	SuUserOrganizationService.findPrOrganizationByCriteria('<%=userId%>','<%=ouCode%>', splitCombo( dojo.widget.byId("orgCode").textInputNode.value ), $("year").value,null, {callback:whenFindOrganizationCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		    }
		    
		    function whenFindOrganizationCallback(data)
		    {
	    		$("codeSeqAct").value = data.codeSeq;
	 	    }
		 
		
		    function save()
		    {
		    	var answer = confirm("ต้องการบันทึกข้อมูลคลิก OK ถ้าไม่คลิก Cancel แล้วคลิก ออก");
		    	if( answer ){
		    		
					var cbo = dojo.widget.byId("orgCode");
				
		    	
					if( cbo.textInputNode.value == '' )
		    		{
			   		 	alert("โปรดกรอกสังกัดปฏิบัติงานให้ถูกต้อง");
		    		}else
		    		{
		    			VPnOrganizationSecurityService.findByKeyPK('<%=ouCode%>','<%=userId%>', splitCombo( cbo.textInputNode.value ),{callback:whenClickUpdate,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
					}
				}
		    }
		  
		   
		    function whenClickUpdate(data){
	           var cbo = dojo.widget.byId("orgCode");
		    	
		    	if(data == null)
		    	{
		    		alert("สังกัดปฏิบัติงานไม่ถูกต้อง");
		    	}else
		    	{
		    		if(cbo.textInputNode.value != '' )
			    	{	
		    			var prefixName = $("prefixName").value
		        		var empCode = $("empCode").value;
		      			var firstName = $("firstName").value;
						var lastName = $("lastName").value;
						var codeSeqAct = $("codeSeqAct").value;
						//var pDate = $("pDate").value;
						var ouCode =  '<%=ouCode%>';
						var userId = '<%=userId%>';
						//if( !isEqualsValue($("empStatus").value, $("hEmpStatus").value) ){
						var payStatus = $("payStatus").value;
						var empStatus = $("empStatus").value;
						var marriedStatus = $("marriedStatus").value;
					    FeeWgEmpWorkService.updateFeeWgEmployee(ouCode,empCode,userId, prefixName,firstName,lastName,codeSeqAct,empStatus,payStatus,marriedStatus, {callback:whenUpdateComplete});
			    	}else{
						alert("โปรดกรอกสังกัดปฏิบัติงานให้ถูกต้อง");
					}
		    	}
			 }
			
			function whenUpdateComplete(){
				alert("แก้ไขข้อมูลเรียบร้อย");
				gotoQYPage();
			}
			
			
		    function gotoQYPage()
		    {
		    	document.getElementById("orgFromEdit").value = '<%= orgFromEdit %>';
				document.getElementById("orgToEdit").value = '<%= orgToEdit %>';
				document.getElementById("empCodeFromEdit").value = '<%= empCodeFromEdit %>';
				document.getElementById("empCodeToEdit").value = '<%= empCodeToEdit %>';
				document.getElementById("pageEdit").value = '<%= Integer.parseInt(pageEdit) - 1 %>';
		    
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
				dojo.event.connect(cbo, "selectOption", "whenFindOrganization");
				$("empCode").value = '<%=empCode%>';
				
				whenEmpBlur();
				
				
			}
			
			dojo.addOnLoad(init);
			
		   
		
		</script>

	</head>
	<body>
		<table width="100%">
			<tr>
				<td class="font-head">
					[ CTWGEMPUP001 ] แก้ไขข้อมูลบุคคลของลูกจ้างเหมา 	</td>
			</tr>
		</table>
		<form name="searchForm" action="" method="post">
			<!-- Begin Declare Paging -->
			<!-- End Declare Paging -->
		<table width="100%" align="center">
		
		<tr><td align="center">
			<TABLE border="0" align="center" width="920px" cellspacing="1">
				<TR>
					<TD class="font-field" style="text-align:right;" width="200px">เลขประจำตัว</TD>
					<TD width="130px"><INPUT type="text" id="empCode" readonly="readonly" style="width:130px;text-align: left;background-color:silver;" /></TD>
					<TD class="font-field" style="text-align:right;width:80px">คำนำ</TD>
							<TD width="120px">
								<SELECT id="prefixName" style="width:100px">
									<OPTION value="008">นาย</OPTION>
									<OPTION value="007">นาง</OPTION>
									<OPTION value="006">น.ส.</OPTION>
								</SELECT>
								<INPUT type="hidden" id="hPrefixName">
							</TD>		
					<TD class="font-field" style="text-align:right;" width="100px">ชื่อ</TD>
					<TD width="130px"><INPUT type="text" id="firstName" style="width:150px;text-align: left;background-color:transparent;" /><INPUT type="hidden" id="hFirstName" /></TD>
					<TD class="font-field" style="text-align:right;" width="100px">นามสกุล</TD>
					<TD width="130px"><INPUT type="text" id="lastName" style="width:200px;text-align: left;background-color:transparent;" /><INPUT type="hidden" id="hLastName" /></TD>
				</TR>
				<TR>
					<TD class="font-field" style="text-align:right;" width="250px">สังกัดปฏิบัติงาน</TD>
					<TD width="780px" colspan="10">
						<table border="0" style="width:100%;border:none" cellpadding="0" cellspacing="0"><tr>
							<TD>
								<INPUT type="text" widgetId="orgCode" maxlength="20" dojoType="ComboBox" style="width:730px" onBlurInput="whenFindOrganization();" />
								<INPUT type="hidden" id="codeSeqAct" />
								<INPUT type="hidden" id="hOrgCode" />
            					<INPUT type="hidden" id="hCodeSeqAct" />
							</TD>
						</tr></table>
					</TD>
				</TR>
					<TABLE border="1" align="center" width="950px" cellspacing="1">
				<TR>
						<TD colspan="8">
						<FIELDSET><LEGEND class="font-field">รายละเอียด</LEGEND>
						<TABLE border="01" width="100%" colspan="3">
							<TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR>
						<TD class="font-field" style="text-align:right;" width="100px">วันที่บรรจุ</TD>
					<TD><INPUT type="text" id="pDate" readonly="readonly" style="width: 100px;text-align: left;background-color:silver;" /><INPUT type="hidden" id="hPdate" /></TD>
				 <TD class="font-field" style="text-align:right;" width="150px" >สถานะรับเงินเดือน<INPUT type="hidden" id="hPayStatus"> </TD>
					<TD width="130px"><INPUT type="radio" value="1" name="payStatus" checked="checked"/><FONT class="font-field">จ่ายผ่านธนาคาร</FONT></TD>
					<TD width="130px"><input type="radio" value="2" name="payStatus"/><FONT class="font-field">เงินสด</FONT></TD>
				    	<TD class="font-field" style="text-align:right;width:120px">สถานะการลดหย่อน</TD>
							<TD width="120px">
								<SELECT id="marriedStatus" style="width:150px">
									<OPTION value="1">โสด</OPTION>
									<OPTION value="2">คู่สมรสไม่มีเงินได้</OPTION>
									<OPTION value="3">คู่สมรสมีเงินได้</OPTION>
									<OPTION value="4">หม้าย-หย่า-โสดมีบุตรบุญธรรม</OPTION>
								</SELECT>
								<INPUT type="hidden" id="hMarriedStatus">
							</TD>		
				</TR>
				
			
				<TR>
				    	<TD class="font-field" style="text-align:right;width:100px">สภาพการจ้าง</TD>
							<TD width="100px">
								<SELECT id="empStatus" style="width:100px" ">
									<OPTION value="B">ปกติ</OPTION>
									<OPTION value="R">พ้น</OPTION>
									<OPTION value="S">เลิกจ้างมีโทษ</OPTION>
								</SELECT>
								<INPUT type="hidden" id="hEmpStatus">
							</TD>		
				</TR>
				</TABLE>
			
		</td></tr>
		</TABLE>	
		</TABLE>
				<BR/>
		<br><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" class=" button "   value="ตกลง" name="ok" onclick="save();" />
		&nbsp;&nbsp;
		<input type="button" class=" button "  value="ออก" name="gotoQyPage" onclick="gotoQYPage();"/>
		<br><br>				
	</form>
	<FORM name="goQY" action="security.htm?reqCode=CTWGEMPMT001" method="post">
		<input type="hidden" name="orgFromEdit" />
		<input type="hidden" name="orgToEdit" />
		<input type="hidden" name="empCodeFromEdit" />
		<input type="hidden" name="empCodeToEdit" />
		<input type="hidden" name="pageEdit" />
		
	</FORM>
	</body>
</html>
