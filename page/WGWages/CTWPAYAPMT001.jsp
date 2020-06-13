<%@ page language="java" contentType="text/html;charset=TIS-620" %>
<%@page import="java.text.DecimalFormat"%>
<%@page import="javax.swing.text.Document"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Locale"%>
<%@ page import="com.ss.tp.security.UserInfo" %>

<%
	UserInfo userInfo = (UserInfo)session.getAttribute("UserLogin");
	String userId = userInfo.getUserId();
	String ouCode = userInfo.getOuCode();
	
	String orgFromEdit = request.getParameter("orgFromEdit");
	String orgToEdit = request.getParameter("orgToEdit");
	
	String orderFromCboEdit = request.getParameter("orderFromCboEdit");
	
	String orderFlag3CboEdit = request.getParameter("orderFlag3CboEdit");
	
	
	String pageEdit = request.getParameter("pageEdit")==null?"-1":request.getParameter("pageEdit");
	
	String mustQuery = request.getParameter("mustQuery");
%>
<html>
<head>
<title>��Ǩ�ͺ�׹�ѹ������</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="script/payroll_util.js"></script>
<!-- Javascript Script File -->
<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPrPeriodLineService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/FeeWpayPnEmployeeService.js"></SCRIPT>
<script type="text/javascript" src="dwr/interface/FeeWpayRwConfirmDataService.js"></script>



<script type="text/javascript" src="script/gridScript.js"></script>
<script type="text/javascript" src="script/payrollComboBox.js"></script>
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
	//Begin load page**********************
		// Load ComboBox From base
      		 
		    var statusConfirm = 'N';
		   
		    
		    
		     function onLoadUserCallback(){
		     	try{
		     	  
			     	
			     	var cboSource = [];
			     	var cboFrom = dojo.widget.byId("orgFromCbo");
			     	var cboTo = dojo.widget.byId("orgToCbo");
			    
			     	
			     	<c:forEach items="${UserInSecurity}" var="result" >		 
						cboSource.push(["<c:out value='${result.userId}' />","<c:out value='${result.userId}' />"]);
					</c:forEach>
					
			     	cboFrom.dataProvider.setData(cboSource);
			     	cboTo.dataProvider.setData(cboSource);
			     	<%
			     		if(mustQuery != null && mustQuery.endsWith("true") ){
														
			     	%>
			     	whenShowDataTable();
			     	<%
			     		mustQuery = "false";
			     		}
			     	%>
			     			
			     
			   }catch(e){
		     		alert(e.message);
		     	}
		     }
		    
		     function onLoadZipCodeCallback(){
			     	try{
			     	  
				     	
				     	var cboSource = [];
				     	var cboFrom = dojo.widget.byId("zipFromCbo");
				     	var cboTo = dojo.widget.byId("zipToCbo");
				    
				     	
				     	<c:forEach items="${ZipCodeInSecurity}" var="result" >		 
							cboSource.push(["<c:out value='${result.zipCode}' />","<c:out value='${result.zipCode}' />"]);
						</c:forEach>
						
				     	cboFrom.dataProvider.setData(cboSource);
				     	cboTo.dataProvider.setData(cboSource);
				     	<%
				     		if(mustQuery != null && mustQuery.endsWith("true") ){
															
				     	%>
				     	//whenShowDataTable();
				     	<%
				     		mustQuery = "false";
				     		}
				     	%>
				     			
				     
				   }catch(e){
			     		alert(e.message);
			     	}
			     }
		     
		     function onLoadYearSectionCallback(){
				
				$("year").value =   "<c:out value='${DefaultYearAndSection.year}' /> "  ;
				$("section").value = "<c:out value='${DefaultYearAndSection.section}' /> "  ;//data.section;
				$("period").value = ""+"<c:out value='${DefaultYearAndSection.period}' /> "  ;//data.period;
				$("confirm").value = "<c:out value='${ConfirmSuUser}' /> "  ;//data.confirm;
				//PnEmployeeService.findPrEmpBySecurity('<%=userId%>','<%=ouCode%>',$("year").value,$("period").value, {callback:onLoadEmployeeCallback});
		     	chkMainClose();
				//if(<c:out value='${ConfirmSuUser}' />){
				//	document.forms['searchForm'].elements['add'].disabled = true;
				//}
				//alert(data.period);
				
				
			}
		     
		     
		    function whenSelectUserOption(){
		    	DWRUtil.useLoadingMessage("Loading ...");
				var cbo = dojo.widget.byId("orgFromCbo");
				whenFetchUserTo(splitCombo(cbo.textInputNode.value));
			}
			function whenFetchUserTo(orgCode){
		     	DWRUtil.useLoadingMessage("Loading ...");
		     	var cboTo = dojo.widget.byId("orgToCbo");
		     	
		     	if( orgCode > splitCombo(cboTo.textInputNode.value) ){
			     	cboTo.textInputNode.value = '';
			     	cboTo.comboBoxSelectionValue.value = '';
		     	}
		     	
		     	var cboSource = [];
		     	
		     	<c:forEach items="${UserInSecurity}" var="result" >		 
					if( <c:out value='${result.userId}' /> >= userId )
						cboSource.push(["<c:out value='${result.userId}' />","<c:out value='${result.userId}' />"]);
				</c:forEach>
		     	
		     	cboTo.dataProvider.setData(cboSource);
		     	//SuUserOrganizationService.findOrganizationByUserIdAndOuCodeToOrgCode('<%=userId%>','<%=ouCode%>',orgCode , {callback:whenFetchOrganizationToCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
		    }
		    function whenFetchUserToCallback(data){
		    	try{
			     	var cboSource = [];
			     	var cboTo = dojo.widget.byId("orgToCbo");
			     	for(var i=0; i<data.length; i++){
			     		var org = data[i];
			     		cboSource.push([org.userId, org.userId]);
			     	}
			     	cboTo.dataProvider.setData(cboSource);
		     	}catch(e){
		     		alert(e.message);
		     	}
		    }
		   
		    function whenZipCodeFromSelectOption(){
		    	DWRUtil.useLoadingMessage("Loading ...");
				var cbo = dojo.widget.byId("zipFromCbo");
				whenFetchZipCodeTo( splitCombo(cbo.textInputNode.value) );
			}
		
		    
		    function whenSelectZipCodeOption(){
		    	DWRUtil.useLoadingMessage("Loading ...");
				var cbo = dojo.widget.byId("zipFromCbo");
				whenFetchZipCodeTo(splitCombo(cbo.textInputNode.value));
			}
			function whenFetchZipCodeTo(zipCode){
		     	DWRUtil.useLoadingMessage("Loading ...");
		     	var cboTo = dojo.widget.byId("zipToCbo");
		     	
		     	if( zipCode > splitCombo(cboTo.textInputNode.value) ){
			     	cboTo.textInputNode.value = '';
			     	cboTo.comboBoxSelectionValue.value = '';
		     	}
		     	
		     	var cboSource = [];
		     	
		     	<c:forEach items="${ZipCodeInSecurity}" var="result" >		 
					if( <c:out value='${result.zipCode}' /> >= zipCode )
						cboSource.push(["<c:out value='${result.zipCode}' />","<c:out value='${result.zipCode}' />"]);
				</c:forEach>
		     	
		     	cboTo.dataProvider.setData(cboSource);
		     	//SuUserOrganizationService.findOrganizationByUserIdAndOuCodeToOrgCode('<%=userId%>','<%=ouCode%>',orgCode , {callback:whenFetchOrganizationToCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
		    }
		    function whenFetchZipCodeToCallback(data){
		    	try{
			     	var cboSource = [];
			     	var cboTo = dojo.widget.byId("zipToCbo");
			     	for(var i=0; i<data.length; i++){
			     		var zip = data[i];
			     		cboSource.push([zip.zipCode, zip.zipCode]);
			     	}
			     	cboTo.dataProvider.setData(cboSource);
		     	}catch(e){
		     		alert(e.message);
		     	}
		    }
		   
		    function whenZipCodeFromSelectOption(){
		    	DWRUtil.useLoadingMessage("Loading ...");
				var cbo = dojo.widget.byId("zipFromCbo");
				whenFetchZipCodeTo( splitCombo(cbo.textInputNode.value) );
			}
		
			
			
			function init(){
				var cboOrgFrom = dojo.widget.byId("orgFromCbo");
				var cboZipFrom = dojo.widget.byId("zipFromCbo");
				
				dojo.event.connect(cboOrgFrom, "selectOption", "whenSelectOrgOption");
				dojo.event.connect(cboZipFrom, "selectOption", "whenSelectZipCodeOption");
				
			}
			
			function chkMainClose(){
				FeeWpayPrPeriodLineService.findPeriodLine('<%=ouCode%>',$("year").value,$("period").value , {callback:whenchkMainCloseCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
		    }
			
			function whenchkMainCloseCallback(data){
				if(data.mainClose == 'Y'){
					alert('�������ö���Թ��õ����');
					window.history.back()
				}
			}
			
			// Load page
		  	dojo.addOnLoad(init);
			dojo.addOnLoad(onLoadUserCallback);
			dojo.addOnLoad(onLoadZipCodeCallback);
			dojo.addOnLoad(onLoadYearSectionCallback);
		
	
	
	
	
	//End Load page ***********************
	function countData(){
		var orgFromCbo = dojo.widget.byId("orgFromCbo");
		var orgToCbo = dojo.widget.byId("orgToCbo");
		
		var zipFromCbo = dojo.widget.byId("zipFromCbo");
		var zipToCbo = dojo.widget.byId("zipToCbo");
		
	
		var orgFromVal = '';
		var orgToVal = '';
		
		var zipFromVal = '';
		var zipToVal = '';
	
		
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
		
		
		if(zipFromCbo.textInputNode.value != ''){
			zipFromVal = splitCombo(zipFromCbo.textInputNode.value );
		}else{
			zipFromVal = '';
		}
		
		if(zipToCbo.textInputNode.value != ''){
			zipToVal = splitCombo(zipToCbo.textInputNode.value );
		}else{
			zipToVal = '';
		}
		
		FeeWpayRwConfirmDataService.countDataUserConfirm(
		    '<%=userId%>',
		    parseInt(DWRUtil.getValue("year")),
			parseInt(DWRUtil.getValue("period")),
			orgFromVal,
			orgToVal,
			zipFromVal,
			zipToVal,
			document.forms['searchForm'].elements['orderFromCbo'].value,
			document.forms['searchForm'].elements['orderFlag3Cbo'].value,
			{callback:countDataHandler,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}}
		);
		
		
	}
	
	function countDataHandler(data)
	{
		DWRUtil.setValue("countData",data);
		onCheckButt("searchForm");
	    
	}
	
	function whenShowDataTable()
	{
		var orgFromCbo = dojo.widget.byId("orgFromCbo");
		var orgToCbo = dojo.widget.byId("orgToCbo");
		var zipFromCbo = dojo.widget.byId("zipFromCbo");
		var zipToCbo = dojo.widget.byId("zipToCbo");
		
	
		var orgFromVal = '';
		var orgToVal = '';
		var zipFromVal = '';
		var zipToVal = '';
	
	
		
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
		
		if(zipFromCbo.textInputNode.value != ''){
			zipFromVal = splitCombo( zipFromCbo.textInputNode.value );
		}else{
			zipFromVal = '';
		}
		
		if(zipToCbo.textInputNode.value != ''){
			zipToVal = splitCombo( zipToCbo.textInputNode.value );
		}else{
			zipToVal = '';
		}
		
		FeeWpayRwConfirmDataService.findByCriteriaUserConfirm
		(
			'<%=userId%>',
			parseInt(DWRUtil.getValue("year")),
			parseInt(DWRUtil.getValue("period")),
			orgFromVal,
			orgToVal,
			zipFromVal,
			zipToVal,
			document.forms['searchForm'].elements['orderFromCbo'].value,
			document.forms['searchForm'].elements['orderFlag3Cbo'].value,
			DWRUtil.getValue("page"),
			DWRUtil.getValue("dataPerPage"),
			{callback:whenListDataTableHandler,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}}
		);
	

	
	}
	
	var cellFuncs = [
		function(data) { return "<div align='center'>"+data.userId+"</div>";},
		function(data) { return data.userName;},
		function(data) { return data.department;},
		function(data) { return "<div align='center'>"+data.zipCode+"</div>";},
		function(data) { 
							var str = '' ;
							if(data.flag2 != null && data.flag2 != ''){
								if(data.flag2 == 0){
									str = '����׹�ѹ';
								}else if(data.flag2 == 1){
									str = '�׹�ѹ����';
								}
							}
							return "<div>"+str+"</div>";
						},	
		function(data) { 
							var str = '' ;
							if(data.flag3 != null && data.flag3 != ''){
								if(data.flag3 == 0){
									str = '����׹�ѹ';
								}else if(data.flag3 == 1){
									str = '�׹�ѹ����';
								}
							}
							return "<div>"+str+"</div>";
						},	
		function(data) { return writeButtonMaster("clear",data.userId,data.flag2)},
		function(data) { return writeButtonDetail("edit",data.userId,data.flag3);}
	];
	
	function whenListDataTableHandler(data)
	{
		//alert('dataSize ' + data.length);
		if(data.length > 0){
			DWRUtil.removeAllRows("dataTable");
			DWRUtil.addRows("dataTable",data,cellFuncs);
			if(DWRUtil.getValue("showMaxPage") == ''){
				countData();
			}else{
				onCheckButt("searchForm");
			}
		}else{
			alert('��辺������');
			DWRUtil.removeAllRows("dataTable");
			if(DWRUtil.getValue("showMaxPage") == ''){
				countData();
			}else{
				onCheckButt("searchForm");
			}
		}
	}
	
	function writeCheckBox()
	{
		return "<div align='center'><input type='checkbox' name ='chk' onclick='doCheck(document.forms[\"form1\"]);' /></div>";
	}
	
	function writeButtonMaster(inname,emp,flag)
	{
	    if(<c:out value='${ConfirmSuUser}' /> || flag == 0 ){
			return "<div align='center'><input type='button' disabled = 'true' class='button' name = '"+inname+"' value='��ԡ�Ŵ��ͤ��������ѡ'; ' empId='"+emp+"'  flag='"+flag+"'  /></div>";
		}else {
			return "<div align='center'><input type='button' class='button' name = '"+inname+"' value='��ԡ�Ŵ��ͤ��������ѡ' onclick='preClear(this.empId,this.flag);' empId='"+emp+"'  flag='"+flag+"' /></div>";
	    }
	}
	
	
	function writeButtonDetail(inname,emp,flag)
	{
	    
	    if(<c:out value='${ConfirmSuUser}' /> || flag == 0 ) {
	        
	      	return "<div align='center'><input type='button' disabled = 'true' class='button' name = '"+inname+"' value='��ԡ�Ŵ��ͤ�������Թ��/�Թ�ѡ'; ' empId='"+emp+"' flag='"+flag+"'/></div>";
	      	
	    } else {
			return "<div align='center'><input type='button' class='button' name = '"+inname+"' value='��ԡ�Ŵ��ͤ�������Թ��/�Թ�ѡ' onclick='preEdit(this.empId,this.flag);' empId='"+emp+"' flag='"+flag+"' /></div>";
		}
	}
	




	var emp = {keySeq:null};
    function preEdit(empId,flag3){ 
		if(confirm("��س��׹�ѹ��ûš��ͤ?")){
		   statusConfirm = 'Y';
		
		}
		
		
		if (statusConfirm == 'Y'){
		    DWRUtil.useLoadingMessage("���ѧ�Ŵ��ͤ ...");
			FeeWpayRwConfirmDataService.convertConfirmRwDataByUser
		(
			empId,
			'<%=ouCode%>',
			DWRUtil.getValue("year"),
			DWRUtil.getValue("period"),
		    {callback:whenReApproveHandler,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������5');}}
		);
		}
		whenShowDataTable();
	}
	
	function whenReApproveHandler(data){
	   
	    alert("�Ŵ��ͤ�������Թ�� �Թ�ѡ ����");
		var btn = document.getElementById("edit");
		btn.disabled = true;
		whenShowDataTable();
		
	}
	
	function preClear(empId,flag2){ 
		if(confirm("��س��׹�ѹ��ûš��ͤ?")){
		   statusConfirm = 'Y';
		
		}
		
		
		if (statusConfirm == 'Y'){
		    DWRUtil.useLoadingMessage("���ѧ�Ŵ��ͤ ...");
			FeeWpayRwConfirmDataService.convertConfirmPrTxtByUser
		(
			empId,
			'<%=ouCode%>',
			DWRUtil.getValue("year"),
			DWRUtil.getValue("period"),
		    {callback:whenReClearHandler,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������5');}}
		);
		}
		whenShowDataTable();
		
	}
	
	function whenReClearHandler(data){
	    alert("�Ŵ��ͤ�������Թ�� �Թ�ѡ ����");
	   
		var btn = document.getElementById("clear");
		btn.disabled = true;
		whenShowDataTable();
	
	}

		
	
</script>
<%
	Calendar now = Calendar.getInstance(Locale.US);
	String year = ((now.get(Calendar.YEAR)+543)+"");
	String keySeq  = request.getParameter("keySeq");
%>
</head>
<body>
<form name="searchForm" action="" method="post">
<input type="hidden" name="period"> 
<input type="hidden" name="confirm">
<table width="100%">
	<tr>
		<td class="font-head">
			[ CTWPAYAPMT001 ] ��Ǩ�ͺ�׹�ѹ��¡�� 
		</td>
	</tr>
</table>
<table width="770" border="0" align="center" cellspacing="1">
  <tr>
    <td class="font-field" align="right">��Шӻ�&nbsp;</td>
    <td align="left"><input type="text" name="year"   value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/></td>
    <td  class="font-field" align="right">�Ǵ&nbsp;</td>
    <td align="left"><input type="text" name="section"  value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/></td>
  </tr>
  <tr>
    <td class="font-field" align="right">��������ʼ����&nbsp;</td>
    <td align="left"><SELECT  dojoType="ComboBox" widgetId="orgFromCbo" style="width:200" onBlurInput="whenUserFromSelectOption();"></SELECT></td>
    <td  class="font-field" align="right">�֧���ʼ����&nbsp;</td>
    <td align="left"><SELECT  dojoType="ComboBox" widgetId="orgToCbo" style="width:200"></SELECT></td>
  </tr>
   <tr>
    <td class="font-field" align="right">�����������ɳ���&nbsp;</td>
    <td align="left"><SELECT  dojoType="ComboBox" widgetId="zipFromCbo" style="width:200" onBlurInput="whenZipCodeFromSelectOption();"></SELECT></td>
    <td  class="font-field" align="right">�֧������ɳ���&nbsp;</td>
    <td align="left"><SELECT  dojoType="ComboBox" widgetId="zipToCbo" style="width:200"></SELECT></td>
  </tr>
 	<tr>
    <td class="font-field" align="right">ʶҹТ�������ѡ&nbsp;</td>
    <td align="left"><select name="orderFromCbo" style="width:200">
    				<option value="" ></option>
    				<option value="0" >����׹�ѹ</option>
					<option value="1" >�׹�ѹ</option>				
			</select></td>
    </tr>
    <tr>
	<td class="font-field" align="right">ʶҹТ������Թ��/�Թ�ѡ&nbsp;</td>
    <td align="left"><select name="orderFlag3Cbo" style="width:200">
    				<option value="" ></option>
    				<option value="0" >����׹�ѹ</option>
					<option value="1" >�׹�ѹ</option>				
			</select></td>
    <td align="center"><input type="Button" class=" button " value="����" onclick="onQuery(whenShowDataTable);" /></td>
  </tr>

</table>
<br/>
<div style="height:320px;">
<table width="1000"  border="1" bordercolor="#6699CC"  align="center"  cellpadding="2" cellspacing="0">
	<thead>
		<tr CLASS="TABLEBULE2">
		<th CLASS="TABLEBULE2" width="100">���ʼ����ҹ</th>
		<th CLASS="TABLEBULE2">���� - ���ʡ��</th>
		<th CLASS="TABLEBULE2">�ѧ�Ѵ</th>
		<th CLASS="TABLEBULE2">������ɳ���</th>
		<th CLASS="TABLEBULE2">��������ѡ</th>
		<th CLASS="TABLEBULE2">�������Թ��/�Թ�ѡ</th>
		<th CLASS="TABLEBULE2" >��������ѡ</th>
		<th CLASS="TABLEBULE2" >�������Թ��/�Թ�ѡ</th>
		<tr>
	</thead>
	<tbody id="dataTable">
	</tbody>
</table>
</div>
<div>
<table width="770" align="center"  cellpadding="2" cellspacing="0" >
	<tr>
		<td align="right">
			<input type="hidden" name="page" value="<%=pageEdit%>">
			<input type="hidden" name="maxPage">
			<input type="hidden" name="countData" >
			<input type="hidden" name="dataPerPage" value="10">
			<input type="button" disabled="disabled" class=" button " value="First" name="first" onclick="onFirst(whenShowDataTable);"/>
			<input type="button" disabled="disabled" class=" button " value="<<" name="previous" onclick="onPrevious(whenShowDataTable);"/>
			<input type="text"  name="showPage" style="text-align:right;width: 40;" 
				    onkeyup="onCheckPageNAN(this.value);" onchange="onChangeGoPage(whenShowDataTable);" onkeypress="onKeyGoPage(event,whenShowDataTable);" 
			/>
			/
			<input type="text"  name="showMaxPage" readonly="readonly" style="width: 40;border-style : none;background-color : transparent;text-align:right;font-weight:bold;"/>
			<input type="button" disabled="disabled" class=" button " value=">>" name="next" onclick="onNext(whenShowDataTable);" />
			<input type="button" disabled="disabled" class=" button " value="Last" name="last" onclick="onLast(whenShowDataTable);"/>
		</td>
	</tr>
</table>
</div>
	<table width="100%" CLASS="TABLEBULE2">
		<tr CLASS="TABLEBULE2" >
			<td align="left" >&nbsp;
				
			</td>
		</tr>
	</table>
</form>
	<form name="insertForm" action="security.htm?reqCode=CTWPAYIN009" method="post">
		<input type="hidden" name="pageInsert">
		<input type="hidden" name="yearInsert">
		<input type="hidden" name="periodInsert">
		<input type="hidden" name="userIdInsert">
		<input type="hidden" name="ouCodeInsert">
		<input type="hidden" name="sectionInsert">
		
		<input type="hidden" name="orgFromIns" />
		<input type="hidden" name="orgToIns" />
		<input type="hidden" name="empCodeFromIns" />
		<input type="hidden" name="empCodeToIns" />
		<input type="hidden" name="pageIns" />
		<input type="hidden" name="orderFromCboIns" />
		<input type="hidden" name="orderToCboIns" />
	</form>
	
	<form name="editForm" action="security.htm?reqCode=CTWPAYUP009" method="post">
		<input type="hidden" name="empCodeEdit">
		<input type="hidden" name="periodEdit">
		<input type="hidden" name="pageEdit">
		<input type="hidden" name="yearEdit">
		<input type="hidden" name="periodEdit">
		<input type="hidden" name="ouCodeEdit">
		<input type="hidden" name="confirmEdit">
		<input type="hidden" name="userIdEdit">
		<input type="hidden" name="sectionEdit">
		
		<input type="hidden" name="orgFromEdit" />
		<input type="hidden" name="orgToEdit" />
		<input type="hidden" name="empCodeFromEdit" />
		<input type="hidden" name="empCodeToEdit" />
		<input type="hidden" name="pageEdit" />
		<input type="hidden" name="orderFromCboEdit" />
		<input type="hidden" name="orderToCboEdit" />
	</form>
</body>

<script type="text/javascript">
	if (DWRUtil.getValue("page") >= 0){
		//whenShowDataTable(DWRUtil.getValue("page"));
	}
</script>
</html>