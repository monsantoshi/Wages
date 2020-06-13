<%@ page language="java" contentType="text/html;charset=TIS-620"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="javax.swing.text.Document"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@ page import="sun.security.krb5.internal.i" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
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
		<title>สอบถามข้อมูลประจำเดือนของลูกจ้างเหมา (ข้อมูลหลัก)</title>
		<!-- Include -->
		<script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript" src="dwr/util.js"></script>
		<!-- Javascript Script File -->
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWgPrPeriodLineService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWgPnEmployeeService.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="dwr/interface/FeeWgPrEmployeeService.js"></SCRIPT>
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
			
		    function onLoadYearSectionCallback()
			{
				$("year").value =   "<c:out value='${DefaultYearAndSection.year}' /> "  ;
				$("section").value = "<c:out value='${DefaultYearAndSection.section}' /> "  ;//data.section;
				$("period").value = ""+"<c:out value='${DefaultYearAndSection.period}' /> "  ;//data.period;
				$("empCode").value = '<%=empCode%>';
				var cbo = dojo.widget.byId("orgCode");
				cbo.setDisable(true);
			
				whenEmpBlur();
			}

			function whenEmpBlur()
			{
		    	if( '<%=empCode%>' != '' ){
			    	DWRUtil.useLoadingMessage("Loading ...");
			    	FeeWgPnEmployeeService.findPnEmpDetailInSecue('<%=userId%>', '<%=ouCode%>', '<%=empCode%>',{callback:whenPnEmployeeDeatilCallback});			    	
		    	}
		    }
		    
		    function whenPnEmployeeDeatilCallback(data)
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
			    
		    	
		    	FeeWgPrEmployeeService.findPrEmployee('<%=ouCode%>', $("year").value, $("period").value, '<%=empCode%>', '<%=userId%>',{callback:whenPrEmployeeBlurCallback});
		    }
		    
		    function whenPrEmployeeBlurCallback(data)
		    {
		    	var empName = $("empName").value.replace(' ','space');
		    	if( empName != 'space' )
		    	{
			    	// เลขประจำตัวผู้เสียภาษี
			    	$("taxId").value = checkNull(data.taxId,'STRING');
			    	$("bankId").value = checkNull(data.bankId,'STRING');
			    	$("bankBranch").value = checkNull(data.bankBranch,'STRING');
			    	// สถานะการรับเงินเดือน
	 		    	/* var payStatus = document.forms["searchForm"].elements["payStatus"];
	 		    	if( data.payStatus == '1' ){
	 		    		payStatus[0].checked = true;
	 		    	}else if( data.payStatus == '2' ){
	 		    		payStatus[1].checked = true;
	 		    	}else{
	 		    		payStatus[0].checked = true;
	 		    	} */
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
	 		    	/* var flagPr = document.getElementById("flagPr");
	 		    	if( data.flagPr == '0' ){
	 		    		$("flagPr").value = 'ปกติ';
	 		    	}else if( data.flagPr == '1' ){
	 		    		$("flagPr").value = 'ระงับการจ่ายทั้งหมด';
	 		    	}else if( data.flagPr == '2' ){
	 		    		$("flagPr").value = 'จ่ายให้เฉพาะเงินได้';
	 		    	} */
	 		    	
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
	 		    	/* // สถานะภาพลดหย่อน
	 		    	var marriedStatus = document.getElementById("marriedStatus");
	 		    	if( data.marriedStatus == '1' ){
	 		    		$("marriedStatus").value = 'โสด';
	 		    	}else if( data.marriedStatus == '2' ){
	 		    		$("marriedStatus").value = 'คู่สมรสไม่มีเงินได้';
	 		    	}else if( data.marriedStatus == '3' ){
	 		    		$("marriedStatus").value = 'คู่สมรสมีเงินได้';
	 		    	}else if( data.marriedStatus == '4' ){
	 		    		$("marriedStatus").value = 'หม้าย-หย่า-โสดมีบุตรบุญธรรม';
	 		    	} */
			    	
			    	$("costChild").value = checkNull(data.costChild,'STRING');

			    	var gundanFlag = document.getElementById("gundanFlag");
			    	if( data.gundanFlag == 'Y' ){
			    		gundanFlag.checked = true;
			    		$("hGundanFlag").value = data.gundanFlag;
			    	}else{
			    		gundanFlag.checked = false;
			    		$("hGundanFlag").value = data.gundanFlag;
			    	}
			    	
			  	
			    	var gundanFlag = document.getElementById("gundanFlag");
		    		if( data.gundanFlag == 'Y' )
		    			gundanFlag.checked = true;
		    		else
		    			gundanFlag.checked = false;
		    		
			    	/* var gundanFlag = document.getElementById("gundanFlag");
			    	if( data.gundanFlag == 'Y' ){
			    		gundanFlag.checked = true;
			    		$("hGundanFlag").value = data.gundanFlag;
			    	}else{
			    		gundanFlag.checked = false;
			    		$("hGundanFlag").value = data.gundanFlag;
			    	} */
			    }
		    }
		    
		    function gotoCTWPAYQY001DT02Page()
		    { 
				var frm=document.forms["editForm"];
				DWRUtil.setValue("empCodeQuery",'<%=empCode%>');
				
				document.getElementById("orgFromEdit2").value = '<%= orgFromEdit %>';
				document.getElementById("orgToEdit2").value = '<%= orgToEdit %>';
				document.getElementById("empCodeFromEdit2").value = '<%= empCodeFromEdit %>';
				document.getElementById("empCodeToEdit2").value = '<%= empCodeToEdit %>';
				document.getElementById("pageEdit2").value = '<%= Integer.parseInt(pageEdit) - 1 %>';
				
				frm.submit();
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
			
		    dojo.addOnLoad(onLoadYearSectionCallback);
		
		</script>

	</head>
	<body>
		<table width="100%">
			<tr>
				<td class="font-head">
					[ CTWGPAYQY001DT01 ] สอบถามข้อมูลประจำเดือนของลูกจ้างเหมา (ข้อมูลหลัก)
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
					<TD align="left"><INPUT type="text" name="year" value="" readonly="readonly" style="width: 70px;text-align: center;background-color:silver;"></TD>
				    <TD width="46px" class="font-field" align="right">งวด&nbsp;</TD>
				    <TD align="left">
				    	<INPUT type="text" name="section" value="" readonly="readonly" style="width: 70px;text-align: center;background-color:silver;">
				    	<INPUT type="hidden" name="period" value="">
				    </TD>
				</TR>
			</TABLE>
		</td></tr>
		<tr><td align="center">
		<TABLE border="0" align="center" width="920px" cellspacing="1">
				<TR>
					<TD class="font-field" style="text-align:right;" width="200px">เลขประจำตัว</TD>
					<TD width="130px"><INPUT type="text" id="empCode" readonly="readonly" style="width:130px;background-color:silver;" maxlength="13" onblur="whenEmpBlur();" onkeypress="clearScreen();" onkeydown="clearScreen();" /></TD>
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
						<INPUT type="text" id="taxId" readonly="readonly" maxlength="13" style="width:130px;background-color:silver;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" />
						<INPUT type="hidden" id="hTaxId">
					</TD>
				 <TD class="font-field" style="text-align:right;" width="400px" >สถานะรับเงินเดือน<INPUT type="hidden" id="hPayStatus" > </TD>
					<TD width="130px"><INPUT type="radio" value="1" name="payStatus" disabled="disabled" checked="checked"/><FONT class="font-field">จ่ายผ่านธนาคาร</FONT></TD>
					<TD width="130px"><input type="radio" value="2" name="payStatus" disabled="disabled" /><FONT class="font-field">เงินสด</FONT></TD>
					<TD width="240px" class="font-field" style="text-align:right;">เบี้ยกันดาร</TD>
					<TD width="30px">
						<input type="checkbox" disabled="disabled" name="gundanFlag" style="width:30px;disabled ="true" onclick="onGundanChecked();" />
						<INPUT type="hidden" id="hGundanFlag">
					</TD>
					
				</TR>
			
				<TR>
						<TD class="font-field" style="text-align:right;" width="450px">สถานะจ่ายเงินเดือน</TD>
							<TD width="260px">
								<SELECT id="flagPr"  >
									<OPTION value="0">ปกติ</OPTION>
									<OPTION value="1">ระงับการจ่ายทั้งหมด</OPTION>
									<OPTION value="2">จ่ายให้เฉพาะเงินได้</OPTION>
								</SELECT>
								<INPUT type="hidden" id="hFlagPr">
							</TD>						
						<TD class="font-field" style="text-align:right;width:159px">สถานะการลดหย่อน</TD>
							<TD align="right">
								<SELECT id="marriedStatus"  style="width:150px">
									<OPTION value="1">โสด</OPTION>
									<OPTION value="2">คู่สมรสไม่มีเงินได้</OPTION>
									<OPTION value="3">คู่สมรสมีเงินได้</OPTION>
									<OPTION value="4">หม้าย-หย่า-โสดมีบุตรบุญธรรม</OPTION>
								</SELECT>
								<INPUT type="hidden" id="hMarriedStatus">
							</TD>
				<TD class="font-field" style="width:600px;text-align:right;">บุตรที่ได้รับค่าช่วยเหลือบุตร </TD>
					<TD width="130px">
						<INPUT type="text" name="costChild" readonly="readonly" maxlength="5" style="width:123px;text-align:right;background-color:silver;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" />
						<INPUT type="hidden" id="hCostChild">
					</TD>
					</TR>
				<TR>
						<TD width="150px" class="font-err" style="text-align:right;">เลขที่บัญชีธนาคาร</TD>
					<TD width="150px">
						<input type="text" id="bankId" readonly="readonly" maxlength="10" style="width=150px;background-color:silver;" onblur="validBankBranch();" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/>
						<INPUT type="hidden" id="hBankId">
					</TD>
					<TD width="150px" class="font-err" style="text-align:right;">รหัสสาขาธนาคาร</TD>
					<TD width="50px">
						<input type="text" id="bankBranch" readonly="readonly" maxlength="4" style="width=50px;background-color:silver;"  onblur="validBankBranch();" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/>
						<INPUT type="hidden" id="hBankBranch">
					</TD>
				</TR>
			</TABLE>
			<BR/>
			
						<hr/>
				
						</FIELDSET>
					</TD>
				</TR>
			</TABLE>
		<br><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" class=" button "   value="Pay Slip" name="paySlip" onclick="gotoCTWPAYQY001DT02Page();" />
		&nbsp;&nbsp;
		<input type="button" class=" button "  value="หน้าแรก" name="gotoQyPage" onclick="gotoQYPage();"/>
		<br><br>				
	</form>
	<FORM name="goQY" action="security.htm?reqCode=CTWGPAYQY001" method="post">
		<input type="hidden" name="orgFromEdit" />
		<input type="hidden" name="orgToEdit" />
		<input type="hidden" name="empCodeFromEdit" />
		<input type="hidden" name="empCodeToEdit" />
		<input type="hidden" name="pageEdit" />
		
	</FORM>
	<form name="editForm" action="security.htm?reqCode=CTWGPAYQY001DT02" method="post">
		<input type="hidden" name="empCodeQuery" />
		
		<input type="hidden" name="orgFromEdit2" />
		<input type="hidden" name="orgToEdit2" />
		<input type="hidden" name="empCodeFromEdit2" />
		<input type="hidden" name="empCodeToEdit2" />
		<input type="hidden" name="pageEdit2" />
		
	</form>
	</body>
</html>
