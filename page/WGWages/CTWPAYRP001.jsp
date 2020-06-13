<%@ page language="java" contentType="text/html;charset=TIS-620"%>
<%@ page import="com.ss.tp.security.UserInfo" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%
	UserInfo userInfo = (UserInfo)session.getAttribute("UserLogin");
	String userId = userInfo.getUserId();
	String ouCode = userInfo.getOuCode();
%>
<html>
<head>
<title>รายงานการเปลี่ยนแปลงข้อมูลหลักประจำเดือน</title>
<!-- You have to include these two JavaScript files from DWR -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="script/dojo.js"></script>
<!-- This JavaScript file is generated specifically for your application -->
<script type="text/javascript" src="dwr/interface/FeeWpayPrEmployeeService.js"></script>
<script type="text/javascript" src="dwr/interface/FeeWpayPrPeriodLineService.js"></script>
<script type="text/javascript">
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
	var periodNameData  = [];
	var periodValueData = [];

	function onLoadYearSection(){
		onLoadYearSectionCallback();
	}
	function onLoadYearSectionCallback(data){
		$("year").value =   "<c:out value='${DefaultYearAndSection.year}' />"  ;
		$("section").value = "<c:out value='${DefaultYearAndSection.section}' />"  ;//data.section;
		$("period").value = "<c:out value='${DefaultYearAndSection.period}' />"  ;//data.period;
		$("isConfirm").value = "<c:out value='${DefaultYearAndSection.confirm}' />"  ;//data.confirm;	
			
	}
	
	dojo.addOnLoad(onLoadYearSection);
		
	function onLoadPeriodSection(){
		FeeWpayPrPeriodLineService.findPeriodInPeriodLine(DWRUtil.getValue("ouCode"),DWRUtil.getValue("year"),'<%=userId%>',{callback:onLoadPeriodSectionCallback});
	}
	function onLoadPeriodSectionCallback(data){
		var comB1=dojo.widget.byId("periodTmp");
		var arrData=[];
		if(data!= null)
		for(i=0;i<data.length;i++){
		    var tmp = data[i];
		 
			arrData.push([tmp.periodName,tmp.period]);
			periodNameData.push(tmp.periodName);
			periodValueData.push(tmp.period);
		}
		comB1.dataProvider.setData(arrData);
		dojo.widget.byId("periodTmp").textInputNode.value = DWRUtil.getValue("section");
		dojo.widget.byId("periodTmp").comboBoxSelectionValue.value = DWRUtil.getValue("period");
	}
	dojo.addOnLoad(onLoadPeriodSection);

	function onChangePeriodSection(){
		FeeWpayPrPeriodLineService.findPeriodInPeriodLine(DWRUtil.getValue("ouCode"),DWRUtil.getValue("year"),'<%=userId%>',{callback:onChangePeriodSectionCallback});
	}
	function onChangePeriodSectionCallback(data){
		var comB1=dojo.widget.byId("periodTmp");
		var arrData=[];
		if(data!= null)
		for(i=0;i<data.length;i++){
		    var tmp = data[i];
		 
			arrData.push([tmp.periodName,tmp.period]);
			periodNameData.push(tmp.periodName);
			periodValueData.push(tmp.period);
		}
		comB1.dataProvider.setData(arrData);
		dojo.widget.byId("periodTmp").textInputNode.value = "";
		dojo.widget.byId("periodTmp").comboBoxSelectionValue.value = "";
	}
	
	function whenLoadPayroll(){
		DWRUtil.setValue("section",dojo.widget.byId("periodTmp").textInputNode.value);
		dojo.widget.byId("periodTmp").comboBoxSelectionValue.value = "";
		if (DWRUtil.getValue("section")!=""){
			for (i=0;i<periodNameData.length;i++){
				if (periodNameData[i] == dojo.widget.byId("periodTmp").textInputNode.value ){
					dojo.widget.byId("periodTmp").comboBoxSelectionValue.value = periodValueData[i];
					break;
				}
			}
		}
		DWRUtil.setValue("period",dojo.widget.byId("periodTmp").comboBoxSelectionValue.value);
		checkAndRunReport();
	}

	function checkAndRunReport(){
		var frm = document.forms["mainform"];
		if (DWRUtil.getValue("year").length == 4){
			if (DWRUtil.getValue("period") != "" ){
				frm.target = "_blank";
				DWRUtil.setValue("periodName", dojo.widget.byId("periodTmp").textInputNode.value);
				frm.submit();
			}
			else{
				alert("ประจำงวด ไม่ถูกต้อง");
			}
		}
		else{
			alert("ประจำปี ไม่ถูกต้อง");
		}
	}	

</script>
</head>
<body>
<table width="100%"   >
<tr>
	<td height="70" align="left" valign="top" class="font-head">[ CTWPAYRP001 ]  รายงานการเปลี่ยนแปลงข้อมูลหลักลูกจ้าง เป็นรายเดือน</td>
</tr>
<tr>
	<td  align="center" valign="middle" >
	<form action="wpayPr001Rpt.htm?reqCode=doPrintReport" method="post" name="mainform"  >
		<input type="hidden" name="ouCode" value="<%=ouCode%>">
		<input type="hidden" name="userId" value="<%=userId%>">
			<table width="470" align="center" cellpadding="2" cellspacing="2"  border="1" bordercolor="#6699CC" >
				<tr><td height="80" align="center" valign="middle">
 					<table width="350" align="center"  cellpadding="1" cellspacing="1"  border="0">
					<tr>
						<td class="font-field"  align="right" width="50">ประจำปี</td>
						<td  width="100">
							<input type="text" name="year" style="width: 70px;text-align: center;" onchange="onChangePeriodSection();" 
								onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/>
						</td>
						<td class="font-field"  align="right" width="70">ประจำงวด</td>
						<td  width="70">
							<select widgetId="periodTmp" dojoType="combobox"  style="width:70px;" ></select>
							<input type="hidden" name="period" value="" />
							<input type="hidden" name="periodName" value="" />
							<input type="hidden" name="section" value="" />
				    		<input type="hidden" name="isConfirm" value="" />
						</td>
					</tr>							
				
 					</table>
					</td>
				</tr>
			</table>
	</form>
	</td>
</tr>
<tr>
	<td height="70" align="center" valign="middle" >
		<input type="button" class=" button "  value=" แสดงรายงาน " onclick="whenLoadPayroll();" />
	</td>				
</tr>
</table>
</body>
</html>