<%@ page language="java" contentType="text/html;charset=TIS-620"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="javax.swing.text.Document"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@ page import="sun.security.krb5.internal.i" %>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>


<%
	UserInfo userInfo = (UserInfo)session.getAttribute("UserLogin");
	String userId = userInfo.getUserId();
	String ouCode = userInfo.getOuCode();
	
	java.util.Date dd = new java.util.Date();
	java.text.SimpleDateFormat fmd = new java.text.SimpleDateFormat("dd/MM/yyyy",new java.util.Locale("th","TH"));
	String date = fmd.format(dd);
	
	String flagQY = request.getParameter("flagIns");
	
	String orgFromEdit = ""; 
	String orgToEdit = "";
	String yearFromEdit = ""; 
	String yearToEdit = "";
	String conCodeFromEdit = ""; 
	String conCodeToEdit = "";
	String pageEdit = "";
	
	System.out.println("flag : " + flagQY);
	
	if( flagQY != null && flagQY.equals("MT") ){
		orgFromEdit = request.getParameter("orgFromIns");
		orgToEdit = request.getParameter("orgToIns");
		yearFromEdit = request.getParameter("yearFromIns");
		yearToEdit = request.getParameter("yearToIns");
		conCodeFromEdit = request.getParameter("conCodeFromIns");
		conCodeToEdit = request.getParameter("conCodeToIns");
		pageEdit = request.getParameter("pageIns");
	}
	if( pageEdit.trim().equals("") )
		pageEdit = "-1";
		
	System.out.println("pageEdit : " + pageEdit);
		
	String contractNo = "";
%>
<html>
	<head>
		<title>เพิ่มข้อมูลสัญญาจ้างของลูกจ้างเหมา</title>
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
			
		 
		
		   
		    function whenFindOrganization()
		    {
		    	DWRUtil.useLoadingMessage("Loading ...");
		    	SuUserOrganizationService.findOrganizationByCriteria('<%=userId%>','<%=ouCode%>', splitCombo( dojo.widget.byId("orgCode").textInputNode.value ), {callback:whenFindOrganizationCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		    }
		    
		    function whenFindOrganizationCallback(data)
		    {
	    		$("codeSeqAct").value = data.codeSeq;
	    		//alert(data.codeSeq);
	 	    }
		 
		
		    function save()
		    {
		    	var answer = confirm("ต้องการบันทึกข้อมูลคลิก OK ถ้าไม่คลิก Cancel แล้วคลิก ออก");
		    	if( answer ){
		    		
					var cbo = dojo.widget.byId("orgCode");
				
		    	
					if( cbo.textInputNode.value == '' )
		    		{
			   		 	alert("โปรดกรอกสังกัดการจ้างเหมาให้ถูกต้อง");
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
		    			//var prefixName = $("prefixName").value
		        		var conCode = $("contractNo").value;
		      			var yearCon = $("yearCon").value;
						var conDate = getDateFromFormat($("conDate").value,"dd/MM/yyyy");
						var ouCode =  '<%=ouCode%>';
						var userId = '<%=userId%>';
						var codeSeq = $("codeSeqAct").value;
						var instructNo = $("instructNo").value;
						var instructDate = getDateFromFormat($("instructDate").value,"dd/MM/yyyy");
						var promoteDate = getDateFromFormat($("promoteDate").value,"dd/MM/yyyy");
						var sContactDate = getDateFromFormat($("sContactDate").value,"dd/MM/yyyy");
						var eContactDate = getDateFromFormat($("eContactDate").value,"dd/MM/yyyy");
						var dutyCode = $("dutyCode").value;
						
						var inactive = $("inactive").value;
						var note = $("note").value;
						
						FeeWgEmpWorkService.insertFeeContract(ouCode,conCode,userId, yearCon,codeSeq,conDate,dutyCode,inactive,instructNo,instructDate,promoteDate,sContactDate,eContactDate,note, {callback:whenUpdateComplete});
							  
					    //FeeWgEmpWorkService.insertFeeContract(ouCode,conCode,userId, yearCon,codeSeq,eContactDate,inactive,note,{callback:whenUpdateComplete});
			    	}else{
						alert("โปรดกรอกสังกัดการจ้างเหมาให้ถูกต้อง");
					}
		    	
		    		}
			 }
			
			function whenUpdateComplete(){
				alert("บันทึก เพิ่ม ข้อมูลเรียบร้อย");
				clearScreen();
			}
			 function clearScreen(){
		    
		    	$("contractNo").value = '';
		    	$("yearCon").value = '';
		    	$("conDate").value = '';
		    	$("instructNo").value = '';
		    	$("instructDate").value = '';
		    	$("promoteDate").value = '';
		    	$("sContactDate").value = '';
		    	$("eContactDate").value = '';
		    	
		    	$("note").value = '';
		    	
		    	
		    	var cbo = dojo.widget.byId("orgCode");
		    	cbo.textInputNode.value = '';
		    	
		    		
		    		
		    	var dutyCode = document.getElementById("dutyCode");
		     	dutyCode.selectedIndex = 0;	
		     	
		     	var inactive = document.getElementById("inactive");
		     	inactive.selectedIndex = 0;
 		    
 		    	//$("ok").disabled = true;
		    }
		    /**** END When Employee Select ****/
		    
	
			function compareStDate(object) {
				//document.forms['formInsert'].elements['refNo'].focus();
				while (object.tagName != 'TR') {
					object = object.parentNode
				}

				var stDate = $("sContactDate").value;
				var endDate = $("eContactDate").value;

				//alert(fiscalYear);

				var strDay = stDate.substr(0, 2);
				var strMonth = stDate.substr(3, 2);
				var strYear = stDate.substr(6);

				if (stDate != null && stDate != '' && endDate != null
						&& endDate != '') {

					if (compareDates(stDate, "dd/MM/yyyy", endDate,"dd/MM/yyyy") == 1) {
						alert("วันที่เริ่มต้น ต้องน้อยกว่า วันที่สิ้นสุด");
						$("ok").disabled = true;
						$("sContactDate").value = '';
						$("sContactDate").focus();
						//oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
					} else {
						$("eContactDate").focus();
						$("ok").disabled = false;
					}
				}

				if (stDate != null && stDate != ''
						&& (endDate == null || endDate == '')) {
					$("eContactDate").focus();
					$("ok").disabled = false;
				}
			}

			function compareEndDate(object) {

				while (object.tagName != 'TR') {
					object = object.parentNode
				}

				var stDate = $("sContactDate").value;
				var endDate = $("eContactDate").value;

				//alert(fiscalYear);

				var endDay = endDate.substr(0, 2);
				var endMonth = endDate.substr(3, 2);
				var endYear = endDate.substr(6);

				if (stDate != null && stDate != '' && endDate != null
						&& endDate != '') {

					if (compareDates(stDate, "dd/MM/yyyy", endDate,"dd/MM/yyyy") == 1) {
						alert("วันที่เริ่มต้น ต้องน้อยกว่า วันที่สิ้นสุด");
						$("ok").disabled = true;
						$("eContactDate").value = '';
						$("eContactDate").focus();

					} else {
						$("ok").disabled = false;

					}
				}

				if (endDate != null && endDate != ''
						&& (stDate == null || stDate == '')) {
					$("ok").disabled = false;
					$("sContactDate").focus();
				}
			}

		
			function gotoQYPage() {
				document.getElementById("orgFromEdit").value = '<%= orgFromEdit %>';
				document.getElementById("orgToEdit").value = '<%= orgToEdit %>';
				document.getElementById("yearFromEdit").value = '<%= yearFromEdit %>';
				document.getElementById("yearToEdit").value = '<%= yearToEdit %>';
				document.getElementById("conCodeFromEdit").value = '<%= conCodeFromEdit %>';
				document.getElementById("conCodeToEdit").value = '<%= conCodeToEdit %>';
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
				}
			
			dojo.addOnLoad(init);
			
		    //dojo.addOnLoad(onLoadYearSectionCallback);
		
		</script>
	</head>
	<body>
		<table width="100%">
			<tr>
				<td class="font-head">
					[ CTWGCONIN001 ] เพิ่มข้อมูลสัญญาจ้างของลูกจ้างเหมา 	</td>
			</tr>
		</table>
		<form name="searchForm" action="" method="post">
		<INPUT type="hidden" id="codeSeqAct" />
			<!-- Begin Declare Paging -->
			<!-- End Declare Paging -->
		<table width="100%" align="center">
		<tr><td align="center">
			<TABLE border="0" align="center" width="920px" cellspacing="1">
				<TR>
					<TD class="font-field" style="text-align:right;" width="200px">เลขที่สัญญา</TD>
					<TD width="130px"><INPUT type="text" id="contractNo" maxlength="6" style="width:130px;text-align: left;background-color:transparent;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/><INPUT type="hidden" id="hConCode"></TD>
					<TD class="font-field" style="text-align:right;" >ปี พ.ศ.</TD>
					<TD width="130px"><INPUT type="text" id="yearCon"  size="6"  maxlength="4" align="center" style="text-align:center;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/><INPUT type="hidden" id="hYearCon" /></TD>
				</TR>
				<TR>
					<TD class="font-field" style="text-align:right;" width="250px">สังกัดการจ้างเหมา</TD>
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
				<TABLE border="1" align="center" width="920px" cellspacing="1">
				<TR>
						<TD colspan="8">
						<FIELDSET><LEGEND class="font-field">รายละเอียด</LEGEND>
						<TABLE border="01" width="100%" colspan="3">
							<TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR>
						<TD class="font-field" style="text-align:right;" width="100px">วันที่ทำสัญญา</TD>
					<TD><INPUT type="text" id="conDate" size="12" maxlength="10" align="center"  style="text-align:center;" onchange="return validateDate(this);" ><INPUT type="hidden" id="hConDate" /></TD>
				    	<TD class="font-field" style="text-align:right;width:100px">จ้างเหมาเพื่อ</TD>
							<TD width="100px">
								<SELECT id="dutyCode" style="width:150px" ">
									<OPTION value="800">ช่วยงานไปรษณีย์</OPTION>
									<OPTION value="801">ช่วยงาน LOGISTIC</OPTION>
								</SELECT>
								<INPUT type="hidden" id="hDutyCode">
							</TD>
				    	<TD class="font-field" style="text-align:right;width:100px">สถานะสัญญา</TD>
							<TD width="100px">
								<SELECT id="inactive" style="width:100px" ">
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
					<TD width="130px"><INPUT type="text" id="instructNo" style="width:150px;text-align: left;background-color:transparent;" /><INPUT type="hidden" id="hInstructNo" /></TD>
						<TD class="font-field" style="text-align:right;" width="100px">วันที่ลงนามคำสั่ง</TD>
					<TD><INPUT type="text" id="instructDate" size="12" maxlength="10" align="center"  style="text-align:center;" onchange="return validateDate(this);" ><INPUT type="hidden" id="hInstructDate" /></TD>
				<TD class="font-field" style="text-align:right;" width="100px">วันที่คำสั่งมีผล</TD>
					<TD><INPUT type="text" id="promoteDate" size="12" maxlength="10" align="center"  style="text-align:center;" onchange="return validateDate(this);" ><INPUT type="hidden" id="hPromoteDate" /></TD>
				</TABLE>
					<TABLE border="01" width="100%" colspan="3">
							<TR><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR>
						<TD class="font-field" style="text-align:right;" width="100px">วันเริ่มต้นสัญญา</TD>
					<TD><INPUT type="text" id="sContactDate" size="12" maxlength="10"  align="center" style="text-align:center;" onchange="return validateDate(this) && compareStDate(this);" ><INPUT type="hidden" id="hsContactDate" /></TD>
				<TD class="font-field" style="text-align:right;" width="100px">วันสิ้นสุดสัญญา</TD>
					<TD><INPUT type="text" id="eContactDate" size="12" maxlength="10" align="center" style="text-align:center;" onchange="return validateDate(this) && compareEndDate(this);" ><INPUT type="hidden" id="heContactDate" /></TD>
					<TD class="font-field" style="text-align:right;" width="100px">หมายเหตุ</TD>
					<TD width="130px"><INPUT type="text" id="note" style="width:250px;text-align: left;background-color:transparent;" /><INPUT type="hidden" id="hNote" /></TD>
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
	<FORM name="goQY" action="security.htm?reqCode=CTWGCONMT001" method="post">
		<input type="hidden" name="orgFromEdit" />
		<input type="hidden" name="orgToEdit" />
		<input type="hidden" name="yearFromEdit" />
		<input type="hidden" name="yearToEdit" />
		<input type="hidden" name="conCodeFromEdit" />
		<input type="hidden" name="conCodeToEdit" />
		<input type="hidden" name="pageEdit" />
	</FORM>
	</body>
</html>

