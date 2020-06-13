<%@ page language="java" contentType="text/html;charset=TIS-620" %>
<%@page import="java.text.DecimalFormat"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<%
	UserInfo userInfo = (UserInfo)session.getAttribute("UserLogin");
	String userId = userInfo.getUserId();
	String ouCode = userInfo.getOuCode();
	String empCode = (String)request.getParameter("empCodeQuery");
	
	String orgFromEdit = request.getParameter("orgFromEdit2");
	String orgToEdit = request.getParameter("orgToEdit2");
	String empCodeFromEdit = request.getParameter("empCodeFromEdit2");
	String empCodeToEdit = request.getParameter("empCodeToEdit2");
	
	String yearEdit = (String)request.getParameter("yearEdit2");
	String periodEdit = (String)request.getParameter("periodEdit2");
	String sectionEdit = (String)request.getParameter("sectionEdit2");
	
	String pageEdit = request.getParameter("pageEdit2");
	
	
	if( pageEdit.trim().equals("") )
		pageEdit = "-1";	
	
%>
<html>
<head>
<title>�ͺ��������Ż�Ш���͹�ͧ�١��ҧ (Pay Slip)</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<!-- Javascript Script File -->
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrPeriodLineService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrMonthlyLineService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPnEmployeeService.js"></SCRIPT>
<script type="text/javascript" src="script/gridScript.js"></script>
<script type="text/javascript" src="script/payrollComboBox.js"></script>
<script type="text/javascript" src="page/NavigatePage.jsp"></script>
<script type="text/javascript" src="script/dojo.js"></script>
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

	function addCommas(fieldName)
	{
	    var Num = fieldName.value;
	    var newNum = "";
	    var newNum2 = "";
	    var count = 0;
	    var uSign = false;
	    
	    if( parseFloat(Num) < parseFloat(0))
		{
			Num = Num.substring(1,Num.length) ;
			uSign = true ;
		}
	    
	    //check for decimal number
	    if (Num.indexOf('.') != -1){  //number ends with a decimal point
	        if (Num.indexOf('.') == Num.length-1){
	            Num += "00";
	        }
	        if (Num.indexOf('.') == Num.length-2){ //number ends with a single digit
	            Num += "0";
	        }
	        
	        var a = Num.split("."); 
	        Num = a[0];   //the part we will commify
	        var end = a[1] //the decimal place we will ignore and add back later
	    }
	    else {var end = "00";}  
	 
	    //this loop actually adds the commas   
	    for (var k = Num.length-1; k >= 0; k--){
	      var oneChar = Num.charAt(k);
	      if (count == 3){
	        newNum += ",";
	        newNum += oneChar;
	        count = 1;
	        continue;
	      }
	      else {
	        newNum += oneChar;
	        count ++;
	      }
	   }  //but now the string is reversed!
	   
	  //re-reverse the string
	  for (var k = newNum.length-1; k >= 0; k--){
	      var oneChar = newNum.charAt(k);
	      newNum2 += oneChar;
	  }
	   
	   // add dollar sign and decimal ending from above
	   newNum2 = newNum2 + "." + end;
	   if(uSign)
			newNum2 = "-"+newNum2 ;
	   fieldName.value = newNum2;
	}

	function onLoadYearSectionCallback()
	{
		$("year").value =  '<%=yearEdit%>';
		$("section").value = '<%=sectionEdit%>';
		$("period").value = '<%=periodEdit%>';
		$("empCode").value = '<%=empCode%>';

		whenEmpBlur();
	}

	function whenEmpBlur()
	{
    	if( '<%=empCode%>' != '' )
    	{
    		DWRUtil.useLoadingMessage("Loading ...");
    		FeeWpayPnEmployeeService.findPnEmpDetailInSecue('<%=userId%>', '<%=ouCode%>', '<%=empCode%>', '<%=yearEdit%>', '<%=periodEdit%>',{callback:whenPnEmployeeDeatilCallback});			    	
    	}
    }
    
    function whenPnEmployeeDeatilCallback(data)
    {
    	$("empName").value = checkNull(data.prefixName,'STRING') + ' ' + checkNull(data.firstName,'STRING') + ' ' + checkNull(data.lastName,'STRING');
    
    	$("pDate").value = checkNull(data.PDate,'STRING');
    	$("codeSeqName").value = checkNull(data.orgDesc,'STRING');
    	$("orgCode").value = checkNull(data.orgCode,'STRING');
    	$("orgDesc").value = checkNull(data.orgDesc,'STRING');
    	
    	whenShowDataTable();
    }
    
  
	
	function whenShowDataTable()
	{
		FeeWpayPrMonthlyLineService.findPaySlipNew
		(
			'<%=ouCode%>',
			DWRUtil.getValue("year"),
			DWRUtil.getValue("period"),
			'<%=empCode%>', 
			'1', 
			{callback:whenListDataTableHandler}
		);
	}
	
	var allOrder1 = 0;
	var allOrder2 = 0;
	var id1 = 0;
	var id2 = 0;
	
	var cellFuncs = 
	[
		function(data) { return "<div align='center'>"+data.incDecCode+"</div>";},
		function(data) { return "<div align='left'>"+data.incDecName+"</div>";},
		function(data) { allOrder1 += parseFloat(data.totAmt);
						 id1++;
						 return "<div align='right'><INPUT type='text' id='group1id"+id1+"' value='"+data.totAmt+"' readonly='readonly' style='width:100%;text-align:right;border:none;' /></div>";
					   }
	];
	
	id1 = 0;
	
	function whenListDataTableHandler(data)
	{
		//alert('dataSize ' + data.length);
		if(data.length > 0){
			DWRUtil.removeAllRows("dataTable1");
			DWRUtil.addRows("dataTable1",data,cellFuncs);
		}else{
			//alert('No Data');
			DWRUtil.removeAllRows("dataTable1");
		}
		addCommasG1();
		whenShowDataTable2();
	}
	
	function whenShowDataTable2()
	{
		FeeWpayPrMonthlyLineService.findPaySlipNew
		(
			'<%=ouCode%>',
			DWRUtil.getValue("year"),
			DWRUtil.getValue("period"),
			'<%=empCode%>', 
			'2', 
			{callback:whenListDataTable2Handler}
		);
	}
	
	var cellFuncs2 = [
		function(data) { return "<div align='center'>"+data.incDecCode+"</div>";},
		function(data) { return "<div align='left'>"+data.incDecName+"</div>";},
		function(data) { allOrder2 += parseFloat(data.totAmt); 
						 id2++;
						 return "<div align='right'><INPUT type='text' id='group2id"+id2+"' value='"+data.totAmt+"' readonly='readonly' style='width:100%;text-align:right;border:none;' /></div>";
					   }
	];
	
	id2 = 0;
	
	function whenListDataTable2Handler(data)
	{
		//alert('dataSize ' + data.length);
		if(data.length > 0){
			DWRUtil.removeAllRows("dataTable2");
			DWRUtil.addRows("dataTable2",data,cellFuncs2);
		}else{
			//alert('No Data');
			DWRUtil.removeAllRows("dataTable2");
		}
		
		addCommasG2();
		
		var totalAllOrder = allOrder1-allOrder2;
		
		
		$("total1").value = allOrder1.toFixed(2);
		$("total2").value = allOrder2.toFixed(2);
		$("totalAmt").value = totalAllOrder.toFixed(2);
		if ($("totalAmt").value <= 0) {
		    $("totalAmt").value = 0.00;
		}
		
		FeeWpayPrMonthlyLineService.findIncomeAndTaxPrAccumulate
		(
			'<%=ouCode%>',
			DWRUtil.getValue("year"),
			DWRUtil.getValue("period"),
			'<%=empCode%>',
			{callback:whenListIncomeAndTaxCallback}
		);
	}
	
	function whenListIncomeAndTaxCallback(data)
	{
		if( data.length > 0 && data[0].INCOMEAMT != null )
			$("incomeAmt").value = data[0].INCOMEAMT;
		else
			$("incomeAmt").value = '0.0';
			
		if( data.length > 0 && data[0].TAXAMT != null )
			$("taxAmt").value = data[0].TAXAMT;
		else
			$("taxAmt").value = '0.0';

		addCommas($("total1"));
		addCommas($("total2"));
		addCommas($("totalAmt"));
		addCommas($("incomeAmt"));
		addCommas($("taxAmt"));
	}
	
	function gotoCTWPAYQY001DT01Page()
    { 
		var frm=document.forms["editForm"];
		DWRUtil.setValue("empCodeQuery",'<%=empCode%>');
		
		document.getElementById("orgFromEdit2").value = '<%= orgFromEdit %>';
		document.getElementById("orgToEdit2").value = '<%= orgToEdit %>';
		document.getElementById("empCodeFromEdit2").value = '<%= empCodeFromEdit %>';
		document.getElementById("empCodeToEdit2").value = '<%= empCodeToEdit %>';
		document.getElementById("yearEdit2").value = '<%= yearEdit %>';
		document.getElementById("periodEdit2").value = '<%= periodEdit %>';
		document.getElementById("sectionEdit2").value = '<%= sectionEdit %>';
		
		document.getElementById("pageEdit2").value = '<%= Integer.parseInt(pageEdit) + 1 %>';
		
		document.getElementById("flagEdit").value = 'DT2';
		
		frm.submit();
	}
	
	function gotoQYPage()
    {
    	document.getElementById("orgFromEdit").value = '<%= orgFromEdit %>';
		document.getElementById("orgToEdit").value = '<%= orgToEdit %>';
		document.getElementById("empCodeFromEdit").value = '<%= empCodeFromEdit %>';
		document.getElementById("empCodeToEdit").value = '<%= empCodeToEdit %>';
	    document.getElementById("yearEdit").value = '<%= yearEdit %>';
		document.getElementById("periodEdit").value = '<%= periodEdit %>';
		document.getElementById("sectionEdit").value = '<%= sectionEdit %>';
		document.getElementById("pageEdit").value = '<%= pageEdit %>';
    
    	document.forms["goQY"].submit();
    }		
	
	function addCommasG1()
	{
		for(var i=1; i<=id1 ; i++)
		{
			addCommas(document.getElementById('group1id'+i));
		}
	}
	
	function addCommasG2()
	{
		for(var j=1; j<=id2; j++)
		{
			addCommas(document.getElementById('group2id'+j));
		}
	}
	
	// Load page
  	dojo.addOnLoad(onLoadYearSectionCallback);
  	//dojo.addOnLoad(whenShowDataTable);
	
</script>
<%
	Calendar now = Calendar.getInstance(Locale.US);
	String year = ((now.get(Calendar.YEAR)+543)+"");
	String keySeq  = request.getParameter("keySeq");
%>
</head>
<body>
<table width="100%">
	<tr>
		<td class="font-head">
		   ��������´��è��¤�Ҩ�ҧ��Ш���͹ ����ѷ ��ɳ����� �ӡѴ
		</td>
	</tr>
</table>
<form name="searchForm" action="" method="post">
<input type="hidden" name="period"> 
<input type="hidden" name="confirm"> 
<br>
	<table width="100%" align="center">
		<tr><td align="center">	
			<TABLE border="0" align="left" cellspacing="1">
				<TR>
					<TD width="354px" class="font-field" align="right">��Шӻ�</TD>
					<TD align="right"><INPUT type="text" name="year" value="" readonly="readonly" style="width: 70px;text-align: center;background-color:silver;"></TD>
				    <TD width="55px" class="font-field" align="right">�Ǵ</TD>
				    <TD align="right">
				    	<INPUT type="text" name="section" value="" readonly="readonly" style="width: 70px;text-align: center;background-color:silver;">
				    	<input type="hidden" name="period">
				    </TD>
				</TR>
			</TABLE>
		</td></tr>
		<tr><td align="center">
			<TABLE border="0" align="center" width="920px" cellspacing="1">
				<TR>
					<TD class="font-field" style="text-align:right;" width="120px">�Ţ��Шӵ��</TD>
					<TD width="130px"><INPUT type="text" id="empCode" readonly="readonly" style="width:130px;text-align: left;background-color:silver;" /></TD>
					<TD colspan="4" width="470px"><INPUT type="text" id="empName" readonly="readonly" style="width:100%;text-align: left;background-color:silver;" /></TD>
					
				</TD>
					<TD class="font-field" style="text-align:right;">�ѹ����è�</TD>
					<TD><INPUT type="text" id="pDate" readonly="readonly" style="width: 100px;text-align: left;background-color:silver;" /></TD>
				</TR>
				<TR>
					<TD class="font-field" style="text-align:right;" width="120px">�ѧ�Ѵ�觵��</TD>
					<TD colspan="7" width="800px">
						<INPUT type="text" id="codeSeqName" readonly="readonly" style="width:100%;text-align: left;background-color:silver;" />
					</TD>
				</TR>
				<TR>
					<TD class="font-field" style="text-align:right;" width="120px">�ѧ�Ѵ��Ժѵԧҹ</TD>
					<TD width="130px">
						<INPUT type="text" id="orgCode" readonly="readonly" style="width:130px;text-align: left;background-color:silver;" />
					</TD>
					<TD colspan="6" width="670px"><INPUT type="text" id="orgDesc" readonly="readonly" style="width:100%;text-align: left;background-color:silver;" /></TD>
				</TR>
		</TABLE>
	</td></tr>
	</TABLE>
<table width="920" align="center" cellpadding="2" cellspacing="2">
<tr>
	<td valign="top">
	<FIELDSET>
    		<LEGEND class="font-field">�������ط��</LEGEND>
    		<br>
    		<table width="820" cellpadding="2" cellspacing="0">
			  	<tr>
			  	<td class="font-field" >
					�Թ�������֧��͹���
					&nbsp;&nbsp;<INPUT type="text" id="incomeAmt" readonly="readonly" style="width:100px;text-align: right;background-color:silver;" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���������֧��͹���
					&nbsp;&nbsp;<INPUT type="text" id="taxAmt" readonly="readonly" style="width:100px;text-align: right;background-color:silver;" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�Թ���ط�Ի�Ш���͹
					&nbsp;&nbsp;<INPUT type="text" id="totalAmt" readonly="readonly" style="width:100px;text-align: right;background-color:silver;" />
				</td>
				</tr>
			</table>
			<br>
    </FIELDSET>
	</td>
</tr>
</table>

	<BR/>
<table width="920" align="center" cellpadding="2" cellspacing="2">
<tr>
	<td valign="top">
	<FIELDSET>
    		<LEGEND class="font-field">�Թ��</LEGEND>
    		<br>
    		<table width="420"  border="1" bordercolor="#6699CC"  align="center"  cellpadding="2" cellspacing="0">
			  	<thead>
					<tr CLASS="TABLEBULE2">
						<th CLASS="TABLEBULE2">����</th>
						<th CLASS="TABLEBULE2">��¡���Թ��</th>
						<th CLASS="TABLEBULE2">�ӹǹ�Թ</th>
					<tr>
				</thead>
				<tbody id="dataTable1">
				</tbody>
				<tr>
					<td colspan="2" align="right">����ӹǹ�Թ</td>
					<td><INPUT type="text" id="total1" readonly="readonly" style="width:100%;text-align: right;border:none;" /></td>
				<tr>
			</table>
			<br>
    </FIELDSET>
	</td>
	<td>&nbsp;&nbsp;</td>
	<td valign="top">
	<FIELDSET>
    		<LEGEND class="font-field">�Թ�ѡ</LEGEND>
    		<br>
    		<table width="420"  border="1" bordercolor="#6699CC"  align="center"  cellpadding="2" cellspacing="0">
			  	<thead>
					<tr CLASS="TABLEBULE2">
						<th CLASS="TABLEBULE2">����</th>
						<th CLASS="TABLEBULE2">��¡���Թ�ѡ</th>
						<th CLASS="TABLEBULE2">�ӹǹ�Թ</th>
					<tr>
				</thead>
				<tbody id="dataTable2">
				</tbody>
				<tr>
					<td colspan="2" align="right">����ӹǹ�Թ</td>
					<td><INPUT type="text" id="total2" readonly="readonly" style="width:100%;text-align: right;border:none;" /></td>
				<tr>
			</table>
			<br>
    </FIELDSET>	
	</td>
</tr>
 
</table>
  
<br><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" class=" button "  value="��������ѡ" name="buttonDT01" onclick="gotoCTWPAYQY001DT01Page();"/>
&nbsp;&nbsp;
<input type="button" class=" button "  value="˹���á" name="gotoQyPage" onclick="gotoQYPage();"/>

<br><br>
</form>
<FORM name="goQY" action="security.htm?reqCode=CTWPAYQY001" method="post">
	<input type="hidden" name="orgFromEdit" />
	<input type="hidden" name="orgToEdit" />
	<input type="hidden" name="empCodeFromEdit" />
	<input type="hidden" name="empCodeToEdit" />
	<input type="hidden" name="yearEdit" />
	<input type="hidden" name="periodEdit" />
	<input type="hidden" name="sectionEdit" />
	<input type="hidden" name="pageEdit" />
	
</FORM>
<form name="editForm" action="security.htm?reqCode=CTWPAYQY001DT01" method="post">
	<input type="hidden" name="empCodeQuery" />
	
	<input type="hidden" name="orgFromEdit2" />
	<input type="hidden" name="orgToEdit2" />
	<input type="hidden" name="empCodeFromEdit2" />
	<input type="hidden" name="empCodeToEdit2" />
	<input type="hidden" name="yearEdit2" />
	<input type="hidden" name="periodEdit2" />
	<input type="hidden" name="sectionEdit2" />
	<input type="hidden" name="pageEdit2" />
	
	<input type="hidden" name="flagEdit" />
</form>
</body>

</html>