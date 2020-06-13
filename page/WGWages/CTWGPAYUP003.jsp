<%@ page language="java" contentType="text/html;charset=TIS-620" %>

<%
	String empID = (String)request.getParameter("empCodeEdit");
	String yearEdit = (String)request.getParameter("yearEdit");
	String periodEdit = (String)request.getParameter("periodEdit");
	String ouCodeEdit = (String)request.getParameter("ouCodeEdit");
	String userId = (String)request.getParameter("userIdEdit");
	String confirm = (String)request.getParameter("confirmEdit");
	String sectionEdit = (String)request.getParameter("sectionEdit");
	String refNoEditToEndCode = (String)request.getParameter("refNoEdit");
	String refNoEdit = new String(refNoEditToEndCode.getBytes("TIS-620"),"TIS-620");
	
	
	String otTypeEdit = (String)request.getParameter("otTypeEdit");

	
	java.util.Date dd = new java.util.Date();
	java.text.SimpleDateFormat fmd = new java.text.SimpleDateFormat("dd/MM/yyyy",new java.util.Locale("th","TH"));
	String date = fmd.format(dd);
	
	
	String orgFromEdit = request.getParameter("orgFromEdit");
	String orgToEdit = request.getParameter("orgToEdit");
	String empCodeFromEdit = request.getParameter("empCodeFromEdit");
	String empCodeToEdit = request.getParameter("empCodeToEdit");
	String pageEdit = request.getParameter("pageEdit");
	String otTypeForQuery = (String)request.getParameter("otTypeForQuery");
	
	String orderFromCboEdit = request.getParameter("orderFromCboEdit");
	String orderToCboEdit = request.getParameter("orderToCboEdit");
	String refNoFromEdit = request.getParameter("refNoFromEdit");
	String refNoToEdit = request.getParameter("refNoToEdit");
	
	if( pageEdit.trim().equals("") )
		pageEdit = "-1";	
	
	java.util.Calendar cc = new java.util.GregorianCalendar();
	int x = cc.get(java.util.Calendar.MONTH) +1;
	//System.out.println("#$#$#$#$$#$#$# :::"+x);
	
	String periodWork = String.valueOf(x);
%>
<html>
<head>
<title>บันทึกค่าล่วงเวลา/ค่าทำงานในวันหยุดพักผ่อน</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<!-- Javascript Script File -->
<SCRIPT type="text/javascript" src="dwr/interface/FeeWgRwOvertimeService.js"></SCRIPT>
<SCRIPT type="text/javascript" src="dwr/interface/SuUserOrganizationService.js"></SCRIPT>
<script type="text/javascript" src="script/gridScript.js"></script>
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
<!--
	var myUpdate = new Array();
	var count = 0;
	var rowModify ;
	function whenQueryData()
	{
		var sstr = '<%=refNoEdit%>';
		if(sstr != null && sstr != '' && sstr != 'null'){
			$("refNo").value = sstr;
		}
			
		
		
		if(<%=otTypeEdit%> == '1'){
			$("workFrom").value = 'ค่าล่วงเวลา';
		}else if(<%=otTypeEdit%> == '2'){
			$("workFrom").value = 'ค่าทำงานในวันหยุดพักผ่อน';
		
		}
		
		
		DWRUtil.useLoadingMessage("Loading ...");
		FeeWgRwOvertimeService.findByEmpCodeDetail('<%=empID%>','<%=ouCodeEdit%>','<%=yearEdit%>','<%=periodEdit%>',{callback:whenQueryDetailHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
	}
	
	function whenQueryDetailHandler(data){
		$("year").value = '<%=yearEdit%>';
		$("period").value = '<%=periodEdit%>';
		$("section").value = '<%=sectionEdit%>';
		$("empCode").value = data.empCode;
		$("name").value = data.name;
		//$("positionShort").value = data.positionShort;
		//$("orgCode").value = data.orgCode;
		//$("orgDesc").value = data.orgDesc;
		$("codeSeq").value = data.codeSeqWork;
		
		$("flagWork").value = data.flagWork;
		
		//if(data.flagWork == 'N'){
		//	document.forms['formEdit'].elements['flagWork'][0].checked = true;
	//	}else if(data.flagWork == 'Y'){
		//	document.forms['formEdit'].elements['flagWork'][1].checked = true;
	//	}
		if(<%=confirm%>){
			//document.forms['formEdit'].elements['insertData'].disabled = true;
			document.forms['formEdit'].elements['deleteData'].disabled = true;
			document.forms['formEdit'].elements['confirmData'].disabled = true;
		}		
		
		FeeWgRwOvertimeService.findByEmpCodeList('<%=userId%>','<%=ouCodeEdit%>','<%=yearEdit%>','<%=periodEdit%>','<%=empID%>','<%=refNoEdit%>','<%=otTypeEdit%>',{callback:whenQueryListDataHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
	
	}
	
	var cellFuncs = [
		function(data) { 
						
					return writeCheckBox();},
		function(data) { return writeSelect("flagPr",data.flagPr,data.keySeq);},
		function(data) { return writeOrgCode("orgCode",data.orgCode,"left",data.keySeq,"codeSeqNew",data.codeSeq);},
		function(data) { 
							var strFlag = '';
							if(data.flagWork != null && data.flagWork != ''){
								if(data.flagWork == 'Y'){
									strFlag = 'ต่างกอง';
								}else if(data.flagWork == 'N'){
									strFlag = 'ในกอง';
								}
							
							}
		
		
		return writeOrgDetail("orgDesc",data.orgDesc,"left",data.keySeq,"flagWork",strFlag);},
		//function(data) { return writeFlagWork("flagWork",data.flagWork,"left",data.keySeq);},
		function(data) { return writeTextYearWork("yearWork",data.yearWork,4,"center",data.keySeq,data.flagPr);},
		function(data) { return writeSelectMonth("periodWork",data.periodWork,data.keySeq,data.flagPr);},
		function(data) { return writeTextFiscal("fiscalYear",data.fiscalYear,4,"center",data.keySeq);},
		function(data) { return writeSelect("workHour",data.workHour,data.keySeq);},
		function(data) { 
						if(data.startDate != null && data.startDate != ''){
							return writeTextStDate("startDate",formatDate(data.startDate,"dd/MM/yyyy"),10,"center",data.keySeq);
						}else{
							return writeTextStDate("startDate","",10,"center",data.keySeq);
						}	
						},
		function(data) { 
						if(data.endDate != null && data.endDate != ''){
							return writeTextEndDate("endDate",formatDate(data.endDate,"dd/MM/yyyy"),10,"center",data.keySeq);
						}else{
							return writeTextEndDate("endDate","",10,"center",data.keySeq);
						}	
						},
		function(data) { return writeText("totDay15",data.totDay15,4,"right",data.keySeq);},
		function(data) { return writeText("totDay1",data.totDay1,4,"right",data.keySeq);},
		function(data) { return writeText("totDay3",data.totDay3,4,"right",data.keySeq);},	
		function(data) { return writeTextSps("spsDay15",data.amtDay15,4,"right",data.keySeq);},
		function(data) { return writeTextSps("spsDay1",data.amtDay1,4,"right",data.keySeq);},
		function(data) { return writeTextSps("spsDay3",data.amtDay3,4,"right",data.keySeq);},	
		function(data) { return writeHidden("seqData",data.seqData,4,3,"right","keySeq",data.keySeq);}
	];
	
	function whenQueryListDataHandler(data){
		
		$("dataLength").value = data.length;
		if(data.length > 0){
			DWRUtil.removeAllRows("dataTable");
			DWRUtil.addRows("dataTable",data,cellFuncs);
			
		}else{
			DWRUtil.removeAllRows("dataTable");
			alert('ไม่พบข้อมูล');
		}
	}
	
	function writeCheckBox()
	{
		if(<%=confirm%>){
			return "<div align='center'><input type='checkbox'disabled='true' name ='chk'  /></div>";
		}else{
			return "<div align='center'><input type='checkbox' name ='chk'  /></div>";
		}
		
	}
	
	function writeButton(inname,emp)
	{
		return "<div align='center'><input type='button' name = '"+inname+"' value='"+emp+"'  /></div>";
	}
	
	function writeSelect(inname,emp,key)
	{
		if(inname=="flagPr")
		{
			var temp1 = '';
			var temp2 = '';
			var temp3 = '';
			var temp4 = '';
			var temp5 = '';
			if(emp=='N'){
				temp1 = 'selected';
			} else if(emp=='A'){
				temp2 = 'selected';		
			}else if(emp=='R'){
				temp3 = 'selected';		
			}else if(emp=='B'){
				temp4 = 'selected';		
			}else if(emp=='S'){
				temp5 = 'selected';		
			}
			if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><select name='"+inname+"' disabled='true' style='background-color:transparent;color:#000000'><option value='N' "+temp1+" >ปกติ</option>"+
													"<option value='A' "+temp2+">ปรับปรุงรายการรับ</option>" +
													"<option value='R' "+temp3+">รายการรับเรียกคืน</option></select></div>";
			}else {
			return "<div align='center' ><select onchange='addListUpdate("+key+");ChkType("+key+",this);' name='"+inname+"' ><option value='N' "+temp1+" >ปกติ</option>"+
													"<option value='A' "+temp2+">ปรับปรุงรายการรับ</option>" +
													"<option value='R' "+temp3+">รายการรับเรียกคืน</option></select></div>";
			
			
			}
		}
		if(inname=="otType")
		{
			var temp6 = '';
			var temp7 = '';
			if(emp=='1'){
				temp6 = 'selected';
			} else if(emp=='2'){
				temp7 = 'selected';		
			}
			if(<%=confirm%>){
				return "<div align='center'><select name='"+inname+"' disabled='true' style='background-color:transparent;color:#000000'><option value='1' "+temp6+" >ค่าล่วงเวลา</option>"+
													"<option value='2' "+temp7+">ค่าทำงานในวันหยุดพักผ่อน</option></select></div>";
			}else{
				return "<div align='center'><select onchange='addListUpdate("+key+")' name='"+inname+"'><option value='1' "+temp6+" >ค่าล่วงเวลา</option>"+
													"<option value='2' "+temp7+">ค่าทำงานในวันหยุดพักผ่อน</option></select></div>";
			}
		}
		if(inname=="workHour")
		{
			var temp8 = '';
			var temp9 = '';
			var temp10 = '';
			
			if(emp=='6'){
				temp8 = 'selected';
			} else if(emp=='7'){
				temp9 = 'selected';		
			} else if(emp=='6.5'){
				temp10 = 'selected';
			}
			if(<%=confirm%>){
				return "<div align='center'><select name='"+inname+"' disabled='true' style='background-color:transparent;color:#000000'><option value='6' "+temp8+" >6</option>"+
													"<option value='7' "+temp9+">7</option>"+"<option value='6.5' "+temp10+">6.5</option></select></div>";
			}else{
				return "<div align='center'><select onchange='addListUpdate("+key+")' name='"+inname+"'><option value='6' "+temp8+" >6</option>"+
													"<option value='7' "+temp9+">7</option>"+"<option value='6.5' "+temp10+">6.5</option></select></div>";
			}
		}
		
	}
	
	function writeSelectMonth(inname,emp,key,type){
		var mm1 = '';
		var mm2 = '';
		var mm3 = '';
		var mm4 = '';
		var mm5 = '';
		var mm6 = '';
		var mm7 = '';
		var mm8 = '';
		var mm9 = '';
		var mm10 = '';
		var mm11 = '';
		var mm12 = '';
		
		if(emp=='1'){
			mm1 = 'selected';
		} else if(emp=='2'){
			mm2 = 'selected';		
		}else if(emp=='3'){
			mm3 = 'selected';		
		}else if(emp=='4'){
			mm4 = 'selected';		
		}else if(emp=='5'){
			mm5 = 'selected';		
		}else if(emp=='6'){
			mm6 = 'selected';		
		}else if(emp=='7'){
			mm7 = 'selected';		
		}else if(emp=='8'){
			mm8 = 'selected';		
		}else if(emp=='9'){
			mm9 = 'selected';		
		}else if(emp=='10'){
			mm10 = 'selected';		
		}else if(emp=='11'){
			mm11 = 'selected';		
		}else if(emp=='12'){
			mm12 = 'selected';		
		}
		
		if(<%=confirm%>){
		return "<div align='center' style='background-color:#CCCCCC;'><select name='"+inname+"' disabled='true' style='background-color:transparent;color:#000000'>" +
													"<option value='1' "+mm1+" >มกราคม</option>" +
													"<option value='2' "+mm2+">กุมภาพันธ์</option>" +
													"<option value='3' "+mm3+">มีนาคม</option>" +
													"<option value='4' "+mm4+">เมษายน</option>" +
													"<option value='5' "+mm5+">พฤษภาคม</option>" +
													"<option value='6' "+mm6+" >มิถุนายน</option>" +
													"<option value='7' "+mm7+">กรกฏาคม</option>" +
													"<option value='8' "+mm8+">สิงหาคม</option>" +
													"<option value='9' "+mm9+">กันยายน</option>" +
													"<option value='10' "+mm10+" >ตุลาคม</option>" +
													"<option value='11' "+mm11+">พฤศจิกายน</option>" +
													"<option value='12' "+mm12+" >ธันวาคม</option>" +
												"</select></div>";
		}else {
			if(type == 'N'){
				return "<div align='center' ><select onchange='addListUpdate("+key+");bypassRowUpdate("+key+");' name='"+inname+"' disabled='disabled' style='background-color:silver;'>" +
													"<option value='1' "+mm1+" >มกราคม</option>" +
														"<option value='2' "+mm2+">กุมภาพันธ์</option>" +
														"<option value='3' "+mm3+">มีนาคม</option>" +
														"<option value='4' "+mm4+">เมษายน</option>" +
														"<option value='5' "+mm5+">พฤษภาคม</option>" +
														"<option value='6' "+mm6+" >มิถุนายน</option>" +
														"<option value='7' "+mm7+">กรกฏาคม</option>" +
														"<option value='8' "+mm8+">สิงหาคม</option>" +
														"<option value='9' "+mm9+">กันยายน</option>" +
														"<option value='10' "+mm10+" >ตุลาคม</option>" +
														"<option value='11' "+mm11+">พฤศจิกายน</option>" +
														"<option value='12' "+mm12+" >ธันวาคม</option>" +
													"</select></div>";
			
			}else{
				return "<div align='center' ><select onchange='addListUpdate("+key+");bypassRowUpdate("+key+");' name='"+inname+"'  >" +
													"<option value='1' "+mm1+" >มกราคม</option>" +
														"<option value='2' "+mm2+">กุมภาพันธ์</option>" +
														"<option value='3' "+mm3+">มีนาคม</option>" +
														"<option value='4' "+mm4+">เมษายน</option>" +
														"<option value='5' "+mm5+">พฤษภาคม</option>" +
														"<option value='6' "+mm6+" >มิถุนายน</option>" +
														"<option value='7' "+mm7+">กรกฏาคม</option>" +
														"<option value='8' "+mm8+">สิงหาคม</option>" +
														"<option value='9' "+mm9+">กันยายน</option>" +
														"<option value='10' "+mm10+" >ตุลาคม</option>" +
														"<option value='11' "+mm11+">พฤศจิกายน</option>" +
														"<option value='12' "+mm12+" >ธันวาคม</option>" +
													"</select></div>";
			}
			
		
		}
	
	
	
	}
	
	
	function writeTextSps(inname,emp,maxlength,textalign,key)
	{
		//กรณี display not edit
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}else{
			if(<%=otTypeEdit%> == '1'){
					return "<div align='center' ><input type='text' name = '"+inname+"' onchange='addListUpdate("+key+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
			}else if(<%=otTypeEdit%> == '2'){
						return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
			}
		}
	}
	
	function writeText(inname,emp,maxlength,textalign,key)
	{
		//กรณี display not edit
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}else{
			return "<div align='center' ><input type='text' name = '"+inname+"' onchange='addListUpdate("+key+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}
	}
	
	function writeTextFiscal(inname,emp,maxlength,textalign,key)
	{
		//กรณี display not edit
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}else{
			return "<div align='center' ><input type='text' name = '"+inname+"' onchange='addListUpdate("+key+");ChkFiscalRowUpdate("+key+");' value='"+emp+"'  align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}
	}
	
	function writeOrgCode(inname,emp,textalign,key,nameHide,empHide)
	{
		//กรณี display not edit
	
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center'  style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		}else{
			return "<div align='left' ><input type='text' name = '"+inname+"' onchange='addListUpdate("+key+");whenSelectOrgCodeOptionInRowUpdate("+key+");' value='"+emp+"' align='left'  style='text-align:"+textalign+";width:100%;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		}
	}
	
	function writeOrgDetail(inname,emp,textalign,key,nameHide,empHide)
	{
		//กรณี display not edit
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center'  style='text-align:"+textalign+";width:80%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/><input type='text' readonly='true' style='width:20%;background-color:silver;' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		}else{
			return "<div align='left' ><input type='text' name = '"+inname+"' onchange='addListUpdate("+key+")' value='"+emp+"' align='left' readonly='readonly'   style='text-align:"+textalign+";width:80%;background-color:silver;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/><input type='text' readonly='true' style='width:20%;background-color:silver;' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		}
	}
	
	function writeFlagWork(inname,emp,textalign,key)
	{
		//กรณี display not edit
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}else{
			return "<div align='left' ><input type='text' name = '"+inname+"' onchange='addListUpdate("+key+")' value='"+emp+"' align='left'  style='text-align:"+textalign+";width:100%;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}
	}
	
	
	
	function writeTextInput(inname,emp,size,maxlength,textalign,key)
	{
		//กรณี display not edit
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"'  style='text-align:"+textalign+";width:100%;background-color:transparent;' /></div>";
		}else{
			return "<div align='center' ><input type='text' name = '"+inname+"' onchange='addListUpdate("+key+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;' /></div>";
		}
	}
	
	function writeTextYearWork(inname,emp,maxlength,textalign,key,type)
	{
		//กรณี display not edit
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' size='6' maxlength='"+maxlength+"' style='text-align:"+textalign+";background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}else{
			if(type == 'N'){
				return "<div align='center' ><input type='text' name = '"+inname+"' onchange='addListUpdate("+key+");bypassRowUpdate("+key+");' value='"+emp+"' readonly='readonly' align='center' maxlength='"+maxlength+"' size='6' style='text-align:center;background-color:silver;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
			}else{
				return "<div align='center' ><input type='text' name = '"+inname+"' onchange='addListUpdate("+key+");bypassRowUpdate("+key+");' value='"+emp+"' align='center' maxlength='"+maxlength+"' size='6' style='text-align:center;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
			}
		}
	}
	
	function writeHidden(inname,emp,size,maxlength,textalign,nameHide,empHide)
	{
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		}else{
			return "<div align='center'><input type='text' name = '"+inname+"' onchange='addListUpdate("+empHide+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);' /><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		}
	}
	
	function writeTextStDate(inname,emp,maxlength,textalign,key)
	{
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";background-color:transparent;;width:100%' onblur='validateDate(this);'/></div>";
		}else{
			return "<div align='center' ><input type='text' name = '"+inname+"' onchange='addListUpdate("+key+");return validateDate(this) && compareStDateRowUpdate("+key+");' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";;width:100%' /></div>";
		}
	}
	
	function writeTextEndDate(inname,emp,maxlength,textalign,key)
	{
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";background-color:transparent;;width:100%' onblur='validateDate(this);'/></div>";
		}else{
			return "<div align='center' ><input type='text' name = '"+inname+"' onchange='addListUpdate("+key+");return validateDate(this) && compareEndDateRowUpdate("+key+");' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";;width:100%' /></div>";
		}
	}
	
	
	var rwOvertime = {keySeq:null, ouCode:null, yearPr:null, periodPr:null,
						empCode:null,yearWork:null,periodWork:null,codeSeq:null,
						otType:null,refNo:null,fiscalYear:null,workHour:null,startDate:null,endDate:null,confirmFlag:null,
					 	totDay15:null,totDay1:null,totDay3:null,amtDay15:null,amtDay1:null,amtDay3:null,flagPr:null,flagWork:null,seqData:null,updBy:null,updDate:null,creBy:null,creDate:null};
	
	var allRowUpdate = 0;
	
	function onInsertCallback(data){
		insertNewData();
		allRowUpdate = 0;
	}
		
	function onUpdate(){
	if(compareDateBeforSave()){
			var table = document.getElementById("table");
			var aRows = table.rows;
			var num = 2 + parseInt(DWRUtil.getValue("dataLength"));
			
			var update ;
			var tab = $('dataTable');
			var row;
			var empList=[];
			var frm   = document.forms["formEdit"];
			
			var keySeq 		= frm.elements["keySeq"];
			var flagPr 		= frm.elements["flagPr"];
			var yearWork 	= frm.elements["yearWork"];
			var periodWork 	= frm.elements["periodWork"];
			var fiscalYear	= frm.elements["fiscalYear"];
			var workHour   = frm.elements["workHour"];
			var startDate 	= frm.elements["startDate"];
			var endDate 	= frm.elements["endDate"];
			var totDay15 	= frm.elements["totDay15"];
			var totDay1 	= frm.elements["totDay1"];
			var totDay3 	= frm.elements["totDay3"];
			var spsDay15 	= frm.elements["spsDay15"];
			var spsDay1 	= frm.elements["spsDay1"];
			var spsDay3 	= frm.elements["spsDay3"];
			var seqData 	= frm.elements["seqData"];
			var codeSeqNew  = frm.elements["codeSeqNew"];
			var flagWork    = frm.elements["flagWork"];
			var orgCode    = frm.elements["orgCode"];
			
			
			DWREngine.beginBatch();
			//alert('UPDATE '+tab.rows.length);
			for(var i=0; i<tab.rows.length; i++){
					//alert('value in row :'+keySeq[i].value+' : '+flagPr[i].value+' : '+yearWork[i].value+' : '+periodWork[i].value+' : '+fiscalYear[i].value+' : '+startDate[i].value+' : '+endDate[i].value+' : '+totDay1[i].value+' : '+totDay15[i].value+' : '+totDay3[i].value+' : '+seqData[i].value+' : '+codeSeqNew[i].value+' : '+flagWork[i].value+' : '+orgCode[i+1].value);
							
					update = false;
					row = tab.rows[i];
					rwOvertime.keySeq = parseInt(keySeq[i].value);
					
					if(tab.rows.length == 1){
						rwOvertime.flagPr = flagPr.value;
					}else{
						rwOvertime.flagPr = flagPr[i].value;
					}
					
					rwOvertime.periodWork  = periodWork[i].value;
					if(startDate[i].value != ''){
						rwOvertime.startDate = getDateFromFormat(startDate[i].value,"dd/MM/yyyy");
					}else{
						rwOvertime.startDate  = null;
					}
					
					if(endDate[i].value != ''){
						rwOvertime.endDate = getDateFromFormat(endDate[i].value,"dd/MM/yyyy");
					}else{
						rwOvertime.endDate  = null;
					}
					
					if (yearWork[i].value != ''){
						rwOvertime.yearWork  = yearWork[i].value;
					}
					else{
						rwOvertime.yearWork  = null;
					}
					
					
					if (fiscalYear[i].value !=''){
						rwOvertime.fiscalYear  = fiscalYear[i].value;
					}
					else{
						rwOvertime.fiscalYear  = null;
					}
					
					if(workHour[i].value !=''){
						rwOvertime.workHour = parseInt(workHour[i].value);
					}else{
						rwOvertime.workHour = null;
					}
					
					if (totDay15[i].value !=''){
						rwOvertime.totDay15  = totDay15[i].value;
					}
					else{
						rwOvertime.totDay15  = null;
					}
					
					if (totDay1[i].value !=''){
						rwOvertime.totDay1  = totDay1[i].value;
					}
					else{
						rwOvertime.totDay1  = null;
					}
					
					if (totDay3[i].value !=''){
						rwOvertime.totDay3  = totDay3[i].value;
					}
					else{
						rwOvertime.totDay3  = null;
					}
					if (spsDay15[i].value !=''){
						rwOvertime.amtDay15  = spsDay15[i].value;
					}
					else{
						rwOvertime.amtDay15  = null;
					}
					
					if (spsDay1[i].value !=''){
						rwOvertime.amtDay1  = spsDay1[i].value;
					}
					else{
						rwOvertime.amtDay1  = null;
					}
					
					if (spsDay3[i].value !=''){
						rwOvertime.amtDay3  = spsDay3[i].value;
					}
					else{
						rwOvertime.amtDay3  = null;
					}
					
					if (seqData[i].value !=''){
						rwOvertime.seqData  = parseInt(seqData[i].value);
					}
					else{
						rwOvertime.seqData  = null;
					}
						
						//alert('codeSeqNew[i].value'+codeSeqNew[i].value);
						if (codeSeqNew[i].value !=''){
							rwOvertime.codeSeq  = codeSeqNew[i].value;
						}
						else{
							rwOvertime.codeSeq  = null;
						}
						
						//alert('flag ww'+flagWork[i+1].value);
						if(flagWork[i+1].value == 'ต่างกอง'){
							rwOvertime.flagWork  = 'Y';
						}else{
							rwOvertime.flagWork  = 'N';
						}
				
					
					rwOvertime.updBy = '<%=userId%>';
					//RwOvertimeService.updateRwOvertime(rwOvertime, {callback:onInsertCallback});
					for(var x = 0 ; x < myUpdate.length ;x++){
						if(myUpdate[x] == parseInt(keySeq[i].value)){
							update = true;
							break;
						}
					}
					
					if(update){
						allRowUpdate++;
						if(aRows.length  > num + 1 ){
							if( allRowUpdate == myUpdate.length )
								FeeWgRwOvertimeService.addList(rwOvertime, false, {callback:onInsertCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
							else
								FeeWgRwOvertimeService.addList(rwOvertime, false);
							
							
							
							///RwOvertimeService.addList(rwOvertime, false, {callback:onInsertCallback});
							//if(allRowUpdate == myUpdate.length){
							//	insertNewData();
							//}
						}else{
							if( allRowUpdate == myUpdate.length )
									FeeWgRwOvertimeService.addList(rwOvertime, true, {callback:ClearData,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้');}});
								else
									FeeWgRwOvertimeService.addList(rwOvertime, false);
							
						}
					}
				}
				
			DWREngine.endBatch();
			if(myUpdate.length == 0){
				insertNewData();
			}
	}		
			//ClearData();
	}
	
	function ClearData(){
		alert("บันทึกข้อมูลเรียบร้อย");
		var frm=document.forms["backForm"];
		
		document.getElementById("orgFromEdit").value = '<%= orgFromEdit %>';
		document.getElementById("orgToEdit").value = '<%= orgToEdit %>';
		document.getElementById("empCodeFromEdit").value = '<%= empCodeFromEdit %>';
		document.getElementById("empCodeToEdit").value = '<%= empCodeToEdit %>';
		document.getElementById("pageEdit").value = '<%= Integer.parseInt(pageEdit) - 1 %>';
		
		document.getElementById("orderFromCboEdit").value = '<%= orderFromCboEdit %>';
		document.getElementById("orderToCboEdit").value = '<%= orderToCboEdit %>';
		
		document.getElementById("refNoFromEdit").value = '<%= refNoFromEdit %>';
		document.getElementById("refNoToEdit").value = '<%= refNoToEdit %>';
		document.getElementById("otTypeEdit").value = '<%= otTypeForQuery %>';
		
		document.getElementById("mustQuery").value = 'true';
		
		frm.submit();
	}

	
	function insertNewData()
	{
		var table = document.getElementById("table");
		var tdName;
		var chkName;
		var num = 2 + parseInt(DWRUtil.getValue("dataLength"));
		
		var oRows = table.rows;
		if(tdName == null)tdName="flag";
		if(chkName == null)chkName="chk";
		
		DWREngine.beginBatch();
			for(var i=oRows.length-1;i > num;i--)
			{
				
					rwOvertime.keySeq = null;
					rwOvertime.ouCode = '<%=ouCodeEdit%>';
					rwOvertime.yearPr = '<%=yearEdit%>';
					rwOvertime.periodPr = '<%=periodEdit%>';
					rwOvertime.empCode = '<%=empID%>';
					rwOvertime.codeSeq = $("codeSeqNew").value;
					rwOvertime.flagPr = oRows[i].cells["flagPr"].childNodes[0].value;
					rwOvertime.yearWork = oRows[i].cells["yearWork"].childNodes[0].value;
					rwOvertime.periodWork = oRows[i].cells["periodWork"].childNodes[0].value;
					if($("workFrom").value == 'ค่าล่วงเวลา'){
						rwOvertime.otType = '1';
					}else if($("workFrom").value == 'ค่าทำงานในวันหยุดพักผ่อน'){
						rwOvertime.otType = '2';
					
					}
					
					rwOvertime.refNo = $("refNo").value;
					rwOvertime.fiscalYear = oRows[i].cells["fiscalYear"].childNodes[0].value;
					rwOvertime.workHour = oRows[i].cells["workHour"].childNodes[0].value;
					
					
					if(oRows[i].cells["startDate"].childNodes[0].value != null && oRows[i].cells["startDate"].childNodes[0].value != ''){
						rwOvertime.startDate= getDateFromFormat(oRows[i].cells["startDate"].childNodes[0].value,"dd/MM/yyyy");
					}else{
						rwOvertime.startDate= null;
					}
					if(oRows[i].cells["endDate"].childNodes[0].value != null && oRows[i].cells["endDate"].childNodes[0].value != ''){
						rwOvertime.endDate = getDateFromFormat(oRows[i].cells["endDate"].childNodes[0].value,"dd/MM/yyyy");
					}else{
						rwOvertime.endDate = null;
					}
					
					rwOvertime.totDay1 = oRows[i].cells["totDay1"].childNodes[0].value;
					rwOvertime.totDay15 = oRows[i].cells["totDay15"].childNodes[0].value;
					rwOvertime.totDay3 = oRows[i].cells["totDay3"].childNodes[0].value;								
					rwOvertime.seqData = oRows[i].cells["seqData"].childNodes[0].value;
					if(oRows[i].cells["nameOrg"].childNodes[1].value = 'ต่างกอง'){
						rwOvertime.flagWork = 'Y';
					}else if(oRows[i].cells["nameOrg"].childNodes[1].value = 'ในกอง'){
						rwOvertime.flagWork = 'N';
					}
					rwOvertime.confirmFlag = 'N';
					rwOvertime.updBy = '<%=userId%>';
					rwOvertime.creBy = '<%=userId%>';
					rwOvertime.creDate = getDateFromFormat(<%=date%>,"dd/MM/yyyy");
					//RwOvertimeService.insertRwOvertime(rwOvertime, {callback:onInsertCallback});
					//RwOvertimeService.addList(rwOvertime, {callback:onInsertCallback});
					if( i == (num + 1) )
						FeeWgRwOvertimeService.addList(rwOvertime, true, {callback:ClearData,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้');}});
					else
						FeeWgRwOvertimeService.addList(rwOvertime, false);
			}
		DWREngine.endBatch();
		
	}
	
	function addListUpdate(data){
		var add = true;
		for(var i = 0 ; i < myUpdate.length ;i++){
			if(myUpdate[i] == data){
				add = false;
				break;
			}
		}
		if(add){
			myUpdate[count] = data;
			count++;
		}
	}
	
	function backMain(){
		var frm=document.forms["backForm"];
		
		document.getElementById("orgFromEdit").value = '<%= orgFromEdit %>';
		document.getElementById("orgToEdit").value = '<%= orgToEdit %>';
		document.getElementById("empCodeFromEdit").value = '<%= empCodeFromEdit %>';
		document.getElementById("empCodeToEdit").value = '<%= empCodeToEdit %>';
		document.getElementById("pageEdit").value = '<%= Integer.parseInt(pageEdit) - 1 %>';
		
		document.getElementById("orderFromCboEdit").value = '<%= orderFromCboEdit %>';
		document.getElementById("orderToCboEdit").value = '<%= orderToCboEdit %>';
		
		document.getElementById("refNoFromEdit").value = '<%= refNoFromEdit %>';
		document.getElementById("refNoToEdit").value = '<%= refNoToEdit %>';
		document.getElementById("otTypeEdit").value = '<%= otTypeForQuery %>';
		document.getElementById("mustQuery").value = 'true';
		
		frm.submit();
	}
	
	function whenFindOrganization(object)
	 {
	 	 while (object.tagName !=  'TR')
		 {
		 object = object.parentNode
		 }
	
		 lRowNumber = object.rowIndex
		 
	 	DWRUtil.useLoadingMessage("Loading ...");
	 	
	 	var table = document.getElementById("table");
		var tdName;
		var chkName;
		
		var oRows = table.rows;
		var i = oRows.length-1;
		//alert(oRows[i].cells["empCode"].childNodes[0].value);
		var orgCode = oRows[lRowNumber].cells["orgCode"].childNodes[0].value;
		//alert(empCode);
		//var cbo = dojo.widget.byId("empCbo");
		//alert(orgCode);
		if(orgCode != null && orgCode != ''){
		//SuUserOrganizationService.findOrganizationByCriteria('<%=userId%>','<%=ouCodeEdit%>',dojo.widget.byId("orgCode").textInputNode.value, {callback:whenFindOrganizationCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
			SuUserOrganizationService.findPrOrganizationByCriteria('<%=userId%>','<%=ouCodeEdit%>',orgCode, $("year").value, $("period").value, {callback:whenFindOrganizationCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		}
	 }
			    
	 function whenFindOrganizationCallback(data)
	 {
	 
	 	var table = document.getElementById("table");
		var tdName;
		var chkName;
		
		var oRows = table.rows;
		var i = oRows.length-1;
		
		//	cboSource.push(["<c:out value='${result.orgCode} ${result.divShort} ${result.areaDesc} ${result.secDesc} ${result.workDesc}' />","<c:out value='${result.orgCode}' />"]);
					
		var stringDesc = checkNull(data.divShort,'STRING')+' '+checkNull(data.areaDesc,'STRING')+' '+checkNull(data.secDesc,'STRING')+' '+checkNull(data.workDesc,'STRING');
	 	oRows[lRowNumber].cells["nameOrg"].childNodes[0].value = stringDesc;
		oRows[lRowNumber].cells["orgCode"].childNodes[1].value = data.codeSeq;
		var orgDesc = Trim(oRows[lRowNumber].cells["nameOrg"].childNodes[0].value);
    	
		if( orgDesc == '' )
		{
			alert('สังกัดปฎิบัติงานไม่ถูกต้อง');
			//document.forms['formEdit'].elements['insertData'].disabled = true;
			document.forms['formEdit'].elements['deleteData'].disabled = true;
			document.forms['formEdit'].elements['confirmData'].disabled = true;
		}else
		{
			$("codeSeqNew").value = data.codeSeq;
			//alert($("codeSeqNew").value +' :::: '+$("codeSeq").value);
			var codeSeqOld = Trim($("codeSeq").value);
			var codeSeqNew = Trim($("codeSeqNew").value);
			
			if(codeSeqOld!=codeSeqNew)
			{	
				oRows[lRowNumber].cells["nameOrg"].childNodes[1].value = 'ต่างกอง';
			}else
			{
				oRows[lRowNumber].cells["nameOrg"].childNodes[1].value = 'ในกอง';
			}
			//alert(oRows[lRowNumber].cells["nameOrg"].childNodes[1].value);
			//document.forms['formEdit'].elements['insertData'].disabled = false;
			document.forms['formEdit'].elements['deleteData'].disabled = false;
			document.forms['formEdit'].elements['confirmData'].disabled = false;
		}
	 }
	 
	 function whenSelectOrgCodeOptionInRowUpdate(data){
	 	
	 	var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["formEdit"];
		//alert('leng  :'+tab.rows.length);	
		
		if(tab.rows.length > 0){
			var keySeq 		= frm.elements["keySeq"];
			var flagPr 		= frm.elements["flagPr"];
			var yearWork 	= frm.elements["yearWork"];
			var periodWork 	= frm.elements["periodWork"];
			var fiscalYear	= frm.elements["fiscalYear"];
			var workHour    = frm.elements["workHour"];
			var startDate 	= frm.elements["startDate"];
			var endDate 	= frm.elements["endDate"];
			var totDay1 	= frm.elements["totDay1"];
			var totDay15 	= frm.elements["totDay15"];
			var totDay3 	= frm.elements["totDay3"];
			var seqData 	= frm.elements["seqData"];
			var codeSeqNew  = frm.elements["codeSeqNew"];
			var flagWork    = frm.elements["flagWork"];
			var orgCode    = frm.elements["orgCode"];
			var oorg;
			
			for(var c=0; c<tab.rows.length; c++){
				//alert(keySeq[c].value + ':::'+ data);
				if(keySeq[c].value == data ){
					rowModify = c;
				//	alert('value in row :'+keySeq[c].value+' : '+flagPr[c].value+' : '+yearWork[c].value+' : '+periodWork[c].value+' : '+fiscalYear[c].value+' : '+startDate[c].value+' : '+endDate[c].value+' : '+totDay1[c].value+' : '+totDay15[c].value+' : '+totDay3[c].value+' : '+seqData[c].value+' : '+codeSeqNew[c].value+' : '+flagWork[c].value+' : '+orgCode[c+1].value);
					break;
				}
			}
				
		//	alert('ROW  :'+rowModify);	
			oorg = orgCode[rowModify+1].value;
				
		//	alert(oorg);
			
			
			whenFetchOrgCodeDetailInRowUpdate(oorg)
			
		}
	 	
	 }
	 
	function whenFetchOrgCodeDetailInRowUpdate(object){
		
	 	DWRUtil.useLoadingMessage("Loading ...");
	 //	alert(object);
		if(object != null && object != ''){
		//SuUserOrganizationService.findOrganizationByCriteria('<%=userId%>','<%=ouCodeEdit%>',dojo.widget.byId("orgCode").textInputNode.value, {callback:whenFindOrganizationCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
			SuUserOrganizationService.findPrOrganizationByCriteria('<%=userId%>','<%=ouCodeEdit%>',object, $("year").value, $("period").value, {callback:whenFindOrganizationForUpdateCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		}
	
	
	}
	
	 function whenFindOrganizationForUpdateCallback(data)
	 {
	 	//alert();
	 	var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["formEdit"];
		var oRows = table.rows;
		
		if(tab.rows.length > 0){
			//var keySeq 		= frm.elements["keySeq"];
		//	var orgCode 	= frm.elements["orgCode"];
			var orgDesc		= frm.elements["orgDesc"];
		//	var codeSeq 	= frm.elements["codeSeqNew"];
			
			var keySeq 		= frm.elements["keySeq"];
			var flagPr 		= frm.elements["flagPr"];
			var yearWork 	= frm.elements["yearWork"];
			var periodWork 	= frm.elements["periodWork"];
			var fiscalYear	= frm.elements["fiscalYear"];
			var workHour    = frm.elements["workHour"];
			var startDate 	= frm.elements["startDate"];
			var endDate 	= frm.elements["endDate"];
			var totDay1 	= frm.elements["totDay1"];
			var totDay15 	= frm.elements["totDay15"];
			var totDay3 	= frm.elements["totDay3"];
			var seqData 	= frm.elements["seqData"];
			var codeSeqNew  = frm.elements["codeSeqNew"];
			var flagWork    = frm.elements["flagWork"];
			var orgCode    = frm.elements["orgCode"];
			
			
			var stringDesc = checkNull(data.divShort,'STRING')+' '+checkNull(data.areaDesc,'STRING')+' '+checkNull(data.secDesc,'STRING')+' '+checkNull(data.workDesc,'STRING');
	 	
			 if(data.codeSeq != null && data.codeSeq != ''){
			 			if($("codeSeq").value ==  data.codeSeq){
			 				flagWork[rowModify+1].value = 'ในกอง';
			 			}else{
			 				flagWork[rowModify+1].value = 'ต่างกอง';
			 			}
						orgDesc[rowModify+1].value = stringDesc;
						codeSeqNew[rowModify].value = data.codeSeq;
						//document.forms['formEdit'].elements['insertData'].disabled = false;
						document.forms['formEdit'].elements['deleteData'].disabled = false;
						document.forms['formEdit'].elements['confirmData'].disabled = false;
						//alert('codeSeqNew :::'+codeSeqNew[rowModify].value);
					//	alert('value in row if ::::'+keySeq[rowModify].value+' : '+flagPr[rowModify].value+' : '+yearWork[rowModify].value+' : '+periodWork[rowModify].value+' : '+fiscalYear[rowModify].value+' : '+startDate[rowModify].value+' : '+endDate[rowModify].value+' : '+totDay1[rowModify].value+' : '+totDay15[rowModify].value+' : '+totDay3[rowModify].value+' : '+seqData[rowModify].value+' : '+codeSeqNew[rowModify].value+' : '+flagWork[rowModify].value+' : '+orgCode[rowModify+1].value);
					
					}else{
						orgDesc[rowModify+1].value = '';
						codeSeqNew[rowModify].value = '';
						alert('รหัสสังกัดไม่ถูกต้อง');
						//document.forms['formEdit'].elements['insertData'].disabled = true;
						document.forms['formEdit'].elements['deleteData'].disabled = true;
						document.forms['formEdit'].elements['confirmData'].disabled = true;
						orgCode[rowModify+1].focus();	
				//	alert('value in row else ::::'+keySeq[rowModify].value+' : '+flagPr[rowModify].value+' : '+yearWork[rowModify].value+' : '+periodWork[rowModify].value+' : '+fiscalYear[rowModify].value+' : '+startDate[rowModify].value+' : '+endDate[rowModify].value+' : '+totDay1[rowModify].value+' : '+totDay15[rowModify].value+' : '+totDay3[rowModify].value+' : '+seqData[rowModify].value+' : '+codeSeqNew[rowModify].value+' : '+flagWork[rowModify].value+' : '+orgCode[rowModify+1].value);
					
					}
			
			
		}
	 }
	
	dojo.addOnLoad(whenQueryData);
	
	
	function compareStDate(object){
	 	 while (object.tagName !=  'TR')
		 {
		 object = object.parentNode
		 }
	
		 var rowToCompare = object.rowIndex
		
     	DWRUtil.useLoadingMessage("Loading ...");
		var table = document.getElementById("table");
		//alert(table.);
		var tdName;
		var chkName;
		
		var oRows = table.rows;
	
		var stDate = oRows[rowToCompare].cells["startDate"].childNodes[0].value;
		var endDate = oRows[rowToCompare].cells["endDate"].childNodes[0].value;
		var periodWork = oRows[rowToCompare].cells["periodWork"].childNodes[0].value;
		var yearWork = oRows[rowToCompare].cells["yearWork"].childNodes[0].value;
		
		if(periodWork.length == 1){
			periodWork = '0'+periodWork;
		}
		
		var strDay = stDate.substr(0, 2);
		var strMonth = stDate.substr(3, 2);
		var strYear = stDate.substr(6);
		
		if(stDate != null && stDate != '' && endDate != null && endDate != ''){
		 	/*if(periodWork != strMonth){
				alert("เดือนนี้ไม่ได้อยู่ในช่วงการทำงาน");
				//document.forms['formEdit'].elements['insertData'].disabled = true;
				document.forms['formEdit'].elements['deleteData'].disabled = true;
				document.forms['formEdit'].elements['confirmData'].disabled = true;
				oRows[rowToCompare].cells["startDate"].childNodes[0].focus();
			}else*/ 
			if(yearWork != strYear){
				alert("ปีไม่ได้อยู่ในช่วงการทำงาน");
				//document.forms['formEdit'].elements['insertData'].disabled = true;
				document.forms['formEdit'].elements['deleteData'].disabled = true;
				document.forms['formEdit'].elements['confirmData'].disabled = true;
				oRows[rowToCompare].cells["startDate"].childNodes[0].focus();
			}else if (compareDates(stDate,"dd/MM/yyyy",endDate,"dd/MM/yyyy") == 1) {
				alert("วันที่เริ่มต้นต้องน้อยกว่า วันที่สิ้นสุด");
				//document.forms['formEdit'].elements['insertData'].disabled = true;
				document.forms['formEdit'].elements['deleteData'].disabled = true;
				document.forms['formEdit'].elements['confirmData'].disabled = true;
				oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
				oRows[rowToCompare].cells["startDate"].childNodes[0].focus();
			}else{
				oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
				//document.forms['formEdit'].elements['insertData'].disabled = false;
				document.forms['formEdit'].elements['deleteData'].disabled = false;
				document.forms['formEdit'].elements['confirmData'].disabled = false;
			}
	 	}
	 	
	 	if(stDate != null && stDate != '' && (endDate == null || endDate == '')){
	 		/*if(periodWork != strMonth){
				alert("เดือนนี้ไม่ได้อยู่ในช่วงการทำงาน");
				//document.forms['formEdit'].elements['insertData'].disabled = true;
				document.forms['formEdit'].elements['deleteData'].disabled = true;
				document.forms['formEdit'].elements['confirmData'].disabled = true;
				oRows[rowToCompare].cells["startDate"].childNodes[0].focus();
			}else*/ 
			if(yearWork != strYear){
				alert("ปีไม่ได้อยู่ในช่วงการทำงาน");
				//document.forms['formEdit'].elements['insertData'].disabled = true;
				document.forms['formEdit'].elements['deleteData'].disabled = true;
				document.forms['formEdit'].elements['confirmData'].disabled = true;
				oRows[rowToCompare].cells["startDate"].childNodes[0].focus();
			}else{
				oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
				//document.forms['formEdit'].elements['insertData'].disabled = false;
				document.forms['formEdit'].elements['deleteData'].disabled = false;
				document.forms['formEdit'].elements['confirmData'].disabled = false;
			}
	 	}
	 
	 }
	 
	 function compareEndDate(object){
	 	 while (object.tagName !=  'TR')
		 {
		 object = object.parentNode
		 }
	
		 var rowToCompare = object.rowIndex
		
     	DWRUtil.useLoadingMessage("Loading ...");
		var table = document.getElementById("table");
		//alert(table.);
		var tdName;
		var chkName;
		
		var oRows = table.rows;
	
		var stDate = oRows[rowToCompare].cells["startDate"].childNodes[0].value;
		var endDate = oRows[rowToCompare].cells["endDate"].childNodes[0].value;
		var periodWork = oRows[rowToCompare].cells["periodWork"].childNodes[0].value;
		var yearWork = oRows[rowToCompare].cells["yearWork"].childNodes[0].value;
		var section = '<%=sectionEdit%>';
		
		var monthWork = section.substr(0, 2);
		
		if(periodWork.length == 1){
			periodWork = '0'+periodWork;
		}
		
		var endDay = endDate.substr(0, 2);
		var endMonth = endDate.substr(3, 2);
		var endYear = endDate.substr(6);
		
		if(stDate != null && stDate != '' && endDate != null && endDate != ''){
		 	/*if(periodWork != endMonth){
				alert("เดือนนี้ไม่ได้อยู่ในช่วงการทำงาน");
				//document.forms['formEdit'].elements['insertData'].disabled = true;
				document.forms['formEdit'].elements['deleteData'].disabled = true;
				document.forms['formEdit'].elements['confirmData'].disabled = true;
				oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
			}else*/ 
			if(yearWork != endYear){
				alert("ปีไม่ได้อยู่ในช่วงการทำงาน");
				//document.forms['formEdit'].elements['insertData'].disabled = true;
				document.forms['formEdit'].elements['deleteData'].disabled = true;
				document.forms['formEdit'].elements['confirmData'].disabled = true;
				oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
			}else if (compareDates(stDate,"dd/MM/yyyy",endDate,"dd/MM/yyyy") == 1) {
				alert("วันที่เริ่มต้นต้องน้อยกว่า วันที่สิ้นสุด");
				//document.forms['formEdit'].elements['insertData'].disabled = true;
				document.forms['formEdit'].elements['deleteData'].disabled = true;
				document.forms['formEdit'].elements['confirmData'].disabled = true;
				oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
				oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
			}else{
			
				//document.forms['formEdit'].elements['insertData'].disabled = false;
				document.forms['formEdit'].elements['deleteData'].disabled = false;
				document.forms['formEdit'].elements['confirmData'].disabled = false;
			}
	 	}
	 	
	 	if(endDate != null && endDate != '' && (stDate == null || stDate == '')){
	 		/*if(periodWork != endMonth){
				alert("เดือนนี้ไม่ได้อยู่ในช่วงการทำงาน");
				//document.forms['formEdit'].elements['insertData'].disabled = true;
				document.forms['formEdit'].elements['deleteData'].disabled = true;
				document.forms['formEdit'].elements['confirmData'].disabled = true;
				oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
			}else*/ 
			if(yearWork != endYear){
				alert("ปีไม่ได้อยู่ในช่วงการทำงาน");
				//document.forms['formEdit'].elements['insertData'].disabled = true;
				document.forms['formEdit'].elements['deleteData'].disabled = true;
				document.forms['formEdit'].elements['confirmData'].disabled = true;
				oRows[rowToCompare].cells["endDate"].childNodes[0].focus();
			}else{
				//document.forms['formEdit'].elements['insertData'].disabled = false;
				document.forms['formEdit'].elements['deleteData'].disabled = false;
				document.forms['formEdit'].elements['confirmData'].disabled = false;
			
			}
	 	}
	 
	 }
	 
	 function bypass(object){
	 	 while (object.tagName !=  'TR')
		 {
		 object = object.parentNode
		 }
	
		 var rowToCompare = object.rowIndex
		
     	DWRUtil.useLoadingMessage("Loading ...");
		var table = document.getElementById("table");
		//alert(table.);
		var tdName;
		var chkName;
		
		var oRows = table.rows;
	
		if(yearWork <= '<%=yearEdit%>'){
		
			//oRows[rowToCompare].cells["fiscalYear"].childNodes[0].value = yearWork;
		}else{
			alert('ปีงวดที่เบิกไม่ถูกต้อง');
			oRows[rowToCompare].cells["yearWork"].childNodes[0].value = '';
			//oRows[rowToCompare].cells["fiscalYear"].childNodes[0].value = '' ;
		}
	 	
	 	var stDate = oRows[rowToCompare].cells["startDate"].childNodes[0].value;
		var endDate = oRows[rowToCompare].cells["endDate"].childNodes[0].value;
		
		//if(oRows[rowToCompare].cells["startDate"].childNodes[0].value != null && oRows[rowToCompare].cells["startDate"].childNodes[0].value != '' && oRows[rowToCompare].cells["endDate"].childNodes[0].value != null && oRows[rowToCompare].cells["endDate"].childNodes[0].value != ''){
		//	alert('กรุณาระบุวันที่ปฎิบัติงานใหม่');
		//}
	 
	 	//oRows[rowToCompare].cells["startDate"].childNodes[0].value = '';
	 	//oRows[rowToCompare].cells["endDate"].childNodes[0].value = '';
	 	
	 	
	 
	 }
	 
	 	function compareStDateRowUpdate(data){
	 	
	 	var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["formEdit"];
		//alert('leng  :'+tab.rows.length);	
		
		if(tab.rows.length > 0){
			var keySeq 		= frm.elements["keySeq"];
			var yearWork 	= frm.elements["yearWork"];
			var periodWork 	= frm.elements["periodWork"];
			var fiscalYear	= frm.elements["fiscalYear"];
			var startDate 	= frm.elements["startDate"];
			var endDate 	= frm.elements["endDate"];
		
			for(var c=0; c<tab.rows.length; c++){
				//alert(keySeq[c].value + ':::'+ data);
				if(keySeq[c].value == data ){
					rowModify = c;
				//	alert('value in row :'+keySeq[c].value+' : '+flagPr[c].value+' : '+yearWork[c].value+' : '+periodWork[c].value+' : '+fiscalYear[c].value+' : '+startDate[c].value+' : '+endDate[c].value+' : '+totDay1[c].value+' : '+totDay15[c].value+' : '+totDay3[c].value+' : '+seqData[c].value+' : '+codeSeqNew[c].value+' : '+flagWork[c].value+' : '+orgCode[c+1].value);
					break;
				}
			}
			
		
			var stDate = startDate[rowModify].value;
			var enDate = endDate[rowModify].value;
			var periodWork = periodWork[rowModify].value;
			var yearWork = yearWork[rowModify].value;
			var fisYear = fiscalYear[rowModify].value;
			
			if(periodWork.length == 1){
				periodWork = '0'+periodWork;
			}
		
			
			var stDay = stDate.substr(0, 2);
			var stMonth = stDate.substr(3, 2);
			var stYear = stDate.substr(6);
			
			if(stDate != null && stDate != '' && enDate != null && enDate != ''){
			 	/*if(periodWork != stMonth){
					alert("เดือนนี้ไม่ได้อยู่ในช่วงการทำงาน");
					//document.forms['formEdit'].elements['insertData'].disabled = true;
					document.forms['formEdit'].elements['deleteData'].disabled = true;
					document.forms['formEdit'].elements['confirmData'].disabled = true;
					startDate[rowModify].focus();
				}else*/
				if(fisYear != stYear){
					alert("ปีไม่ได้อยู่ในช่วงการทำงาน");
					//document.forms['formEdit'].elements['insertData'].disabled = true;
					document.forms['formEdit'].elements['deleteData'].disabled = true;
					document.forms['formEdit'].elements['confirmData'].disabled = true;
					startDate[rowModify].focus();
				}else if (compareDates(stDate,"dd/MM/yyyy",endDate,"dd/MM/yyyy") == 1) {
					alert("วันที่เริ่มต้นต้องน้อยกว่า วันที่สิ้นสุด");
					//document.forms['formEdit'].elements['insertData'].disabled = true;
					document.forms['formEdit'].elements['deleteData'].disabled = true;
					document.forms['formEdit'].elements['confirmData'].disabled = true;
					endDate[rowModify].value = '';
					startDate[rowModify].focus();
				}else{
					endDate[rowModify].focus();
					//document.forms['formEdit'].elements['insertData'].disabled = false;
					document.forms['formEdit'].elements['deleteData'].disabled = false;
					document.forms['formEdit'].elements['confirmData'].disabled = false;
				
				}
		 	}
		 	
		 	if(stDate != null && stDate != '' && (enDate == null || enDate == '')){
		 		/*if(periodWork != stMonth){
					alert("เดือนนี้ไม่ได้อยู่ในช่วงการทำงาน");
					//document.forms['formEdit'].elements['insertData'].disabled = false;
					document.forms['formEdit'].elements['deleteData'].disabled = false;
					document.forms['formEdit'].elements['confirmData'].disabled = false;
					startDate[rowModify].focus();
				}else*/ 
				if(fisYear != stYear){
					alert("ปีไม่ได้อยู่ในช่วงการทำงาน");
					//document.forms['formEdit'].elements['insertData'].disabled = true;
					document.forms['formEdit'].elements['deleteData'].disabled = true;
					document.forms['formEdit'].elements['confirmData'].disabled = true;
					startDate[rowModify].focus();
				}else{
					endDate[rowModify].focus();
					//document.forms['formEdit'].elements['insertData'].disabled = false;
					document.forms['formEdit'].elements['deleteData'].disabled = false;
					document.forms['formEdit'].elements['confirmData'].disabled = false;
				
				}
		 	}
		}
	 }
	 
	 function compareEndDateRowUpdate(data){
	 	var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["formEdit"];
		//alert('leng  :'+tab.rows.length);	
		
		if(tab.rows.length > 0){
			var keySeq 		= frm.elements["keySeq"];
			var yearWork 	= frm.elements["yearWork"];
			var periodWork 	= frm.elements["periodWork"];
			var fiscalYear	= frm.elements["fiscalYear"];
			var startDate 	= frm.elements["startDate"];
			var endDate 	= frm.elements["endDate"];
		
			for(var c=0; c<tab.rows.length; c++){
				//alert(keySeq[c].value + ':::'+ data);
				if(keySeq[c].value == data ){
					rowModify = c;
				//	alert('value in row :'+keySeq[c].value+' : '+flagPr[c].value+' : '+yearWork[c].value+' : '+periodWork[c].value+' : '+fiscalYear[c].value+' : '+startDate[c].value+' : '+endDate[c].value+' : '+totDay1[c].value+' : '+totDay15[c].value+' : '+totDay3[c].value+' : '+seqData[c].value+' : '+codeSeqNew[c].value+' : '+flagWork[c].value+' : '+orgCode[c+1].value);
					break;
				}
			}
			
		
			var stDate = startDate[rowModify].value;
			var enDate = endDate[rowModify].value;
			var periodWork = periodWork[rowModify].value;
			var yearWork = yearWork[rowModify].value;
			
			var fisYear = fiscalYear[rowModify].value;
			var flagPr = flagPr[rowModify].value;
			
			var section = '<%=sectionEdit%>';
			var yearEdit = '<%=yearEdit%>';
			var monthWork = section.substr(0, 2);
			
			if(periodWork.length == 1){
				periodWork = '0'+periodWork;
			}
		
			var endDay = enDate.substr(0, 2);
			var endMonth = enDate.substr(3, 2);
			var endYear = enDate.substr(6);
			
			if(stDate != null && stDate != '' && enDate != null && enDate != ''){
				if(flagPr == 'N' && endYear == yearEdit && endMonth > monthWork ){
						alert("เดือนที่ระบุไม่ถูกต้อง");
						//document.forms['formEdit'].elements['insertData'].disabled = true;
						document.forms['formEdit'].elements['deleteData'].disabled = true;
						document.forms['formEdit'].elements['confirmData'].disabled = true;
						endDate[rowModify].focus();
				}else if(yearWork == endYear && endYear == yearEdit && (monthWork == endMonth) && (endDay > 10)){
						alert("ห้ามเบิกเกินวันที่ 10 ของเดือน");
						//document.forms['formEdit'].elements['insertData'].disabled = true;
						document.forms['formEdit'].elements['deleteData'].disabled = true;
						document.forms['formEdit'].elements['confirmData'].disabled = true;
						endDate[rowModify].focus();
				}else 
				if(endYear > yearEdit){
				//if(fisYear != endYear){
					alert("ปีไม่ได้อยู่ในช่วงการทำงาน");
					//document.forms['formEdit'].elements['insertData'].disabled = true;
					document.forms['formEdit'].elements['deleteData'].disabled = true;
					document.forms['formEdit'].elements['confirmData'].disabled = true;
					endDate[rowModify].focus();
				}else if (compareDates(stDate,"dd/MM/yyyy",endDate,"dd/MM/yyyy") == 1) {
					alert("วันที่เริ่มต้นต้องน้อยกว่า วันที่สิ้นสุด");
					//document.forms['formEdit'].elements['insertData'].disabled = true;
					document.forms['formEdit'].elements['deleteData'].disabled = true;
					document.forms['formEdit'].elements['confirmData'].disabled = true;
					startDate[rowModify].value = '';
					endDate[rowModify].focus();
				}else{
					//document.forms['formEdit'].elements['insertData'].disabled = false;
					document.forms['formEdit'].elements['deleteData'].disabled = false;
					document.forms['formEdit'].elements['confirmData'].disabled = false;
				
				}
		 	}
		 	
		 	if(enDate != null && enDate != '' && (stDate == null || stDate == '')){
		 		 if(flagPr == 'N' && endYear == yearEdit){	
			 		if(endMonth > monthWork ){
						alert("เดือนที่ระบุไม่ถูกต้อง");
						//document.forms['formEdit'].elements['insertData'].disabled = true;
						document.forms['formEdit'].elements['deleteData'].disabled = true;
						document.forms['formEdit'].elements['confirmData'].disabled = true;
						endDate[rowModify].focus();
					}else if((monthWork == endMonth) && (endDay > 10)){
						alert("ห้ามเบิกเกินวันที่ 10 ของเดือน");
						//document.forms['formEdit'].elements['insertData'].disabled = true;
						document.forms['formEdit'].elements['deleteData'].disabled = true;
						document.forms['formEdit'].elements['confirmData'].disabled = true;
						endDate[rowModify].focus();
					}
				
				}
				else 
				if(endYear > yearEdit){
				//if(fisYear != endYear){
					alert("ปีไม่ได้อยู่ในช่วงการทำงาน");
					//document.forms['formEdit'].elements['insertData'].disabled = true;
					document.forms['formEdit'].elements['deleteData'].disabled = true;
					document.forms['formEdit'].elements['confirmData'].disabled = true;
					endDate[rowModify].focus();
				}else{
					//document.forms['formEdit'].elements['insertData'].disabled = false;
					document.forms['formEdit'].elements['deleteData'].disabled = false;
					document.forms['formEdit'].elements['confirmData'].disabled = false;
		
				}
		 	}
	 
	 }
	 }
	 
	 function bypassRowUpdate(data){
	 	 var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["formEdit"];
		//alert('leng  :'+tab.rows.length);	
		
		if(tab.rows.length > 0){
				var keySeq 		= frm.elements["keySeq"];
				var yearWork 	= frm.elements["yearWork"];
				var periodWork 	= frm.elements["periodWork"];
				var fiscalYear	= frm.elements["fiscalYear"];
				var startDate 	= frm.elements["startDate"];
				var endDate 	= frm.elements["endDate"];
			
				for(var c=0; c<tab.rows.length; c++){
					//alert(keySeq[c].value + ':::'+ data);
					if(keySeq[c].value == data ){
						rowModify = c;
					//	alert('value in row :'+keySeq[c].value+' : '+flagPr[c].value+' : '+yearWork[c].value+' : '+periodWork[c].value+' : '+fiscalYear[c].value+' : '+startDate[c].value+' : '+endDate[c].value+' : '+totDay1[c].value+' : '+totDay15[c].value+' : '+totDay3[c].value+' : '+seqData[c].value+' : '+codeSeqNew[c].value+' : '+flagWork[c].value+' : '+orgCode[c+1].value);
						break;
					}
				}
				
			
				var stDate = startDate[rowModify].value;
				var enDate = endDate[rowModify].value;
				var periodValWork = periodWork[rowModify].value;
				var yearValWork = yearWork[rowModify].value;
		
				if(yearValWork <= '<%=yearEdit%>'){
		
					//fiscalYear[rowModify].value = yearValWork;
					//startDate[rowModify].value = '';
					//endDate[rowModify].value = '';
				}else{
					alert('ปีงวดที่เบิกไม่ถูกต้อง');
					yearWork[rowModify].value = '';
					//fiscalYear[rowModify].value = '' ;
				}
			
		 		
		 	
		 	//if(startDate[rowModify].value != null && startDate[rowModify].value != '' && endDate[rowModify].value != null && endDate[rowModify].value != ''){
		 	//	alert('กรุณาระบุวันที่ปฎิบัติงานใหม่');
		 	
		 	//}
			// 	startDate[rowModify].value = '';
			// 	endDate[rowModify].value = '';
			 	
			 	
	 
	 }
	 }
	 
	 function compareDateBeforSave(){
	 	//document.forms['formInsert'].elements['refNo'].focus();
	  			
	  			
	  		var ss = true ;	
	  		var table = document.getElementById("table");
			var aRows = table.rows;
			var num = 2 + parseInt(DWRUtil.getValue("dataLength"));
			
			var update ;
			var tab = $('dataTable');
			var row;
			var empList=[];
			var frm   = document.forms["formEdit"];
			
			var keySeq 		= frm.elements["keySeq"];
			var flagPr 		= frm.elements["flagPr"];
			var yearWWork 	= frm.elements["yearWork"];
			var periodWWork 	= frm.elements["periodWork"];
			var fiscalYear	= frm.elements["fiscalYear"];
			var startDate 	= frm.elements["startDate"];
			var endDDate 	= frm.elements["endDate"];
			var workHHour    = frm.elements["workHour"];
			var totDay1 	= frm.elements["totDay1"];
			var totDay15 	= frm.elements["totDay15"];
			var totDay3 	= frm.elements["totDay3"];
			var spsDay1 	= frm.elements["spsDay1"];
			var spsDay15 	= frm.elements["spsDay15"];
			var spsDay3 	= frm.elements["spsDay3"];
			var seqData 	= frm.elements["seqData"];
			var codeSeqNew  = frm.elements["codeSeqNew"];
			var flagWork    = frm.elements["flagWork"];
			var orgCode    = frm.elements["orgCode"];
			
			
			//alert('UPDATE '+tab.rows.length);
			for(var i=0; i<tab.rows.length; i++){
			//alert('value in row :'+keySeq[i].value+' : '+flagPr[i].value+' : '+yearWork[i].value+' : '+periodWork[i].value+' : '+fiscalYear[i].value+' : '+startDate[i].value+' : '+endDate[i].value+' : '+totDay1[i].value+' : '+totDay15[i].value+' : '+totDay3[i].value+' : '+seqData[i].value+' : '+codeSeqNew[i].value+' : '+flagWork[i].value+' : '+orgCode[i+1].value);
					
					
					
				var stDate = startDate[i].value;
				var endDate = endDDate[i].value;
				var periodWork = periodWWork[i].value;
				var yearWork = yearWWork[i].value;
				var fisYear = fiscalYear[i].value;
				var workH = workHHour[i].value;
								
				if(periodWork.length == 1){
						periodWork = '0'+periodWork;
					}
					
				var section = '<%=sectionEdit%>';
				var yearEdit = '<%=yearEdit%>';
				var monthWork = section.substr(0, 2);
								
				var strDay = stDate.substr(0, 2);
				var strMonth = stDate.substr(3, 2);
				var strYear = stDate.substr(6);
								
				var endDay = endDate.substr(0, 2);
				var endMonth = endDate.substr(3, 2);
				var endYear = endDate.substr(6);
				
				if(stDate != null && stDate != '' && endDate != null && endDate != ''){
						if(endYear == yearEdit && endMonth > monthWork ){
							ss = false;
							alert("เดือนที่ระบุไม่ถูกต้อง");
							endDDate[i].value = '';
							//document.forms['formEdit'].elements['insertData'].disabled = true;
							document.forms['formEdit'].elements['deleteData'].disabled = true;
							document.forms['formEdit'].elements['confirmData'].disabled = true;
							break;
						}else if(fisYear != strYear){
										ss = false;
										alert("ปีไม่ได้อยู่ในช่วงการทำงาน");
										startDate[i].value = '';
										//document.forms['formEdit'].elements['insertData'].disabled = true;
										document.forms['formEdit'].elements['deleteData'].disabled = true;
										document.forms['formEdit'].elements['confirmData'].disabled = true;
										break;
						}else if(endYear > yearEdit){
						//}else if(endYear != fisYear){
										ss = false;
										alert("ปีวันที่สิ้นสุดไม่ได้อยู่ในช่วงการทำงาน");
										endDDate[i].value = '';
										//document.forms['formEdit'].elements['insertData'].disabled = true;
										document.forms['formEdit'].elements['deleteData'].disabled = true;
										document.forms['formEdit'].elements['confirmData'].disabled = true;
										break;
						}else if (compareDates(stDate,"dd/MM/yyyy",endDate,"dd/MM/yyyy") == 1) {
										ss = false;
										alert("วันที่เริ่มต้นต้องน้อยกว่า วันที่สิ้นสุด");
										//document.forms['formEdit'].elements['insertData'].disabled = true;
										document.forms['formEdit'].elements['deleteData'].disabled = true;
										document.forms['formEdit'].elements['confirmData'].disabled = true;
										break;
						}else if(endYear == yearEdit && (endMonth == monthWork) && (endDay > 10)){
										ss = false;
										alert("ห้ามเบิกเกินวันที่ 10 ของเดือน");
										endDDate[i].value = '';
										//document.forms['formEdit'].elements['insertData'].disabled = true;
										document.forms['formEdit'].elements['deleteData'].disabled = true;
										document.forms['formEdit'].elements['confirmData'].disabled = true;
										break;
									}
							 	}
					if(ss){
							if((stDate == null || stDate == '') || (endDate == null || endDate == '')){
								 ss = false;
								 alert("กรุณาระบุวันที่เริ่มต้น และวันที่สิ้นสุด");
								break;
							}
						}
							
				 	}
		return ss;
	 }
	 
	 function ChkType(obj,kTwo){
	 	
	 
		var rowEdit = '';
	 	var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["formEdit"];
		//alert('leng  :'+tab.rows.length);	
		
		//alert(tab.rows.length);
		
		
		
		if(tab.rows.length > 0){
			var keySeq 		= frm.elements["keySeq"];
			var flagPr 		= frm.elements["flagPr"];
			var yearWork 	= frm.elements["yearWork"];
			var periodWork 	= frm.elements["periodWork"];
			var fiscalYear	= frm.elements["fiscalYear"];
			var startDate 	= frm.elements["startDate"];
			var endDate 	= frm.elements["endDate"];
			var workHour    = frm.elements["workHour"];
		
			
			for(var c=0; c<tab.rows.length; c++){
				//alert(keySeq[c].value + ':::'+ obj);
				if(keySeq[c].value == obj ){
					rowEdit = c;
				//	alert('value in row :'+keySeq[c].value+' : '+flagPr[c].value+' : '+yearWork[c].value+' : '+periodWork[c].value+' : '+fiscalYear[c].value+' : '+startDate[c].value+' : '+endDate[c].value+' : '+totDay1[c].value+' : '+totDay15[c].value+' : '+totDay3[c].value+' : '+seqData[c].value+' : '+codeSeqNew[c].value+' : '+flagWork[c].value+' : '+orgCode[c+1].value);
					break;
				}
			}
				
			//alert(rowEdit);
			//alert(frm.elements["flagPr"][rowEdit].value);
			
			if(kTwo.value == 'N'){
	 	 
	 	 		frm.elements["yearWork"][rowEdit].value = '<%=yearEdit%>';
	 	 		frm.elements["periodWork"][rowEdit].value = '<%=periodWork%>';
	 	 		frm.elements["fiscalYear"][rowEdit].value = '<%=yearEdit%>';
	 	 		frm.elements["startDate"][rowEdit].value = '';
	 	 		frm.elements["endDate"][rowEdit].value = '';
	 	 	
				
				
	 	 		frm.elements["yearWork"][rowEdit].readOnly = true;
	 	 		frm.elements["periodWork"][rowEdit].disabled = true;
	 	 		
	 	 		
				
				
				frm.elements["yearWork"][rowEdit].style.backgroundColor = 'silver';
				frm.elements["periodWork"][rowEdit].style.backgroundColor = 'silver';
				
	 	 
	 	 }else{
	 	 		frm.elements["yearWork"][rowEdit].readOnly = false;
				frm.elements["periodWork"][rowEdit].disabled= false;
				
				frm.elements["yearWork"][rowEdit].style.backgroundColor = '';
				frm.elements["periodWork"][rowEdit].style.backgroundColor = '';
	 	 }
		
			
		}
	 	
	 	 
	 }
	 
	 function ChkFiscalRowUpdate(data){
	 
	 	 var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["formEdit"];
		//alert('leng  :'+tab.rows.length);	
		
		if(tab.rows.length > 0){
				var keySeq 		= frm.elements["keySeq"];
				var yearWork 	= frm.elements["yearWork"];
				var periodWork 	= frm.elements["periodWork"];
				var fiscalYear	= frm.elements["fiscalYear"];
				var startDate 	= frm.elements["startDate"];
				var endDate 	= frm.elements["endDate"];
			
				for(var c=0; c<tab.rows.length; c++){
					//alert(keySeq[c].value + ':::'+ data);
					if(keySeq[c].value == data ){
						rowModify = c;
					//	alert('value in row :'+keySeq[c].value+' : '+flagPr[c].value+' : '+yearWork[c].value+' : '+periodWork[c].value+' : '+fiscalYear[c].value+' : '+startDate[c].value+' : '+endDate[c].value+' : '+totDay1[c].value+' : '+totDay15[c].value+' : '+totDay3[c].value+' : '+seqData[c].value+' : '+codeSeqNew[c].value+' : '+flagWork[c].value+' : '+orgCode[c+1].value);
						break;
					}
				}
				
			
				var stDate = startDate[rowModify].value;
				var enDate = endDate[rowModify].value;
				var periodValWork = periodWork[rowModify].value;
				var yearValWork = yearWork[rowModify].value;
				var fisYear = fiscalYear[rowModify].value;
		
				if(fisYear <= '<%=yearEdit%>'){
		
					//fiscalYear[rowModify].value = yearValWork;
					startDate[rowModify].value = '';
					endDate[rowModify].value = '';
				}else{
					alert('ปีงบประมาณไม่ถูกต้อง');
					//yearWork[rowModify].value = '';
					fiscalYear[rowModify].value = '' ;
				}

	 }
	 
	 
	 }
-->
</script>
</head>

<body>
<form name="formEdit"  action="security.htm?reqCode=CTWGPAYMT003" method="post">
<input type="hidden" name="dataLength">
<input type="hidden" name="codeSeq">
<input type="hidden" name="period">
<input type="hidden" name="orgCode">
<input type="hidden" name="orgDesc">
<input type="hidden" name="flagWork">


<table width="100%">
	<tr>
		<td class="font-head">
			[ CTWGPAYUP003 ] บันทึกค่าล่วงเวลา/ค่าทำงานในวันหยุดพักผ่อน จ้างเหมา
		</td>
	</tr>
</table>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">
      <tr>
           <td  align="center">
				<table width="750" border="1" cellpadding="1" bordercolor="#6699CC" cellspacing="1" align="center">
        			<tr>
        			
        				<td class="font-field"  align="right" width="100">ประจำปี</td>
          				<td align="left"><input type="text" name="year" value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/></td>
          				<td class="font-field" align="left" width="100" colspan="2">งวด
          				<input type="text" name="section" value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/>
          				</td>
        			</tr>
        			<tr>
         				<td class="font-field"  align="right" width="100">เลขประจำตัว</td>
          				<td align="left" width="30"><input type="text" name="empCode" readonly="readonly" style="width:150;text-align:center;background-color:silver;" /></td>
          				<td align="left" colspan="2"><input type="text" name="name" readonly="readonly" style="width:485;background-color:silver;"/></td>
        			</tr>
       			 	<tr>	
		       			 <td class="font-field" align="right"  width="100" >ประเภทของงาน</td>
						<td colspan="4"  align="left"><input type="text" name="workFrom" style="width:250;background-color:silver;" readonly="readonly"/></td>
					</tr>
       			 
       			 	<tr>
         				<td class="font-field"  align="right" width="100">เลขที่ ล.1</td>
          				<td colspan="4"  align="left"><input type="text" name="refNo"  style="width:638;background-color:silver;" readonly="readonly"/></td>
          			</tr>
        		
     		 </table>
          </td>
      </tr>
    	&nbsp;
	 	&nbsp;
	  	&nbsp;
	  <tr>
          <td align="center">
				<div style="height:315px;width:900;overflow:auto;align:center;" >
				 &nbsp;
	  			&nbsp;
	  			&nbsp;
				<table id="table"  bordercolor="#6699CC"  align="center" style="text-align: center;width:1550px;" border="1" cellpadding="0" cellspacing="0" >
					<thead style="text-align: center">
						<tr CLASS="TABLEBULE2">
							<th CLASS="TABLEBULE2" style="width:50px" rowspan="2" align="center">ลบ</th>
							<th CLASS="TABLEBULE2"  rowspan="2" align="center">ประเภทรายการ</th>
							<th CLASS="TABLEBULE2" style="width:500px" rowspan="2" colspan="2">สังกัดปฎิบัติงาน</th>
							<th CLASS="TABLEBULE2" style="width:150px"  colspan="2" rowspan="2" align="center">ปี/งวดที่เบิก</th>
							<th CLASS="TABLEBULE2" style="width:100px"  rowspan="2" align="center">ปีงบประมาณ</th>
							<th CLASS="TABLEBULE2" style="width:100px"  rowspan="2" align="center">จำนวนชั่วโมงทำงานปกติ</th>
							<th CLASS="TABLEBULE2" style="width:200px"  colspan="2" rowspan="1" align="center"><center>วันที่ปฎิบัติงาน</center></th>
							<th CLASS="TABLEBULE2" style="width:180px"  colspan="3" align="center">จำนวนชั่วโมง</th>
							<th CLASS="TABLEBULE2" style="width:180px"  colspan="3" align="center">จำนวนชั่วโมง วันหยุดประจำปี</th>
							<th CLASS="TABLEBULE2" style="width:40px"  rowspan="2" align="center">ลำดับที่</th>
						</tr>
						<tr CLASS="TABLEBULE2"  >
							<th CLASS="TABLEBULE2" style="width:100px"  align="center">เริ่มต้น </th>
							<th CLASS="TABLEBULE2" style="width:100px"  align="center">สิ้นสุด</th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">1.5 เท่า </th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">1 เท่า </th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">2 เท่า </th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">1.5 เท่า </th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">1 เท่า </th>
							<th CLASS="TABLEBULE2" style="width:60px"  align="center">2 เท่า </th>
						</tr>
					</thead>
					<tbody id="dataTable">
					</tbody>	
							<tr id="temprow" style="visibility:hidden;position:absolute">
							<td id="flag"><input type="checkbox" name="chk" /></td>
							<td id="flagPr" width="50">
								<select name="type" >
									<option value="N" >ปกติ</option>
									<option value="A" >ปรับปรุงรายการรับ</option>
									<option value="R" >รายการรับเรียกคืน</option>
									<!--<option value="B" >ตกเบิกปรับปรุงรายการรับ</option>
									<option value="S" >ตกเบิกรายการรับเรียกคืน</option>-->
								</select>
							</td>
							
							<td id="orgCode" align="center" style="width:150px"><input type="text" maxlength="20" name="orgCode" style="width:100%" onchange="whenFindOrganization(this);"  onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"><input type="hidden"  name="codeSeqNew" /></td>
							<td id="nameOrg" align="center" style="width:350px"><input type="text"  name="nameOrg" readonly="readonly" style="width:80%;background-color:silver;"/><input type="text"  name="inKong" readonly="readonly" style="width:20%;background-color:silver;"/></td>
							
							<td id="yearWork" align="center" width="60"><input type="text" value="<%=yearEdit%>" name="yearWork" onchange="bypass(this);" maxlength="4" align="center" style="text-align:center;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="periodWork" align="center" width="60"><select  name="periodWork" onchange="bypass(this);">
													<option value="1" <% if(periodWork.equals("1")){%> selected="selected"  <%} %> >มกราคม</option>
													<option value="2" <% if(periodWork.equals("2")){%> selected="selected"  <%} %>  >กุมภาพันธ์</option>
													<option value="3" <% if(periodWork.equals("3")){%> selected="selected"  <%} %>  >มีนาคม</option>
													<option value="4" <% if(periodWork.equals("4")){%> selected="selected"  <%} %>  >เมษายน</option>
													<option value="5" <% if(periodWork.equals("5")){%> selected="selected"  <%} %>  >พฤษภาคม</option>
													<option value="6"  <% if(periodWork.equals("6")){%> selected="selected"  <%} %>  >มิถุนายน</option>
													<option value="7" <% if(periodWork.equals("7")){%> selected="selected"  <%} %>  >กรกฏาคม</option>
													<option value="8" <% if(periodWork.equals("8")){%> selected="selected"  <%} %>  >สิงหาคม</option>
													<option value="9" <% if(periodWork.equals("9")){%> selected="selected"  <%} %>  >กันยายน</option>
													<option value="10" <% if(periodWork.equals("10")){%> selected="selected"  <%} %>  >ตุลาคม</option>
													<option value="11" <% if(periodWork.equals("11")){%> selected="selected"  <%} %>  >พฤศจิกายน</option>
													<option value="12" <% if(periodWork.equals("12")){%> selected="selected"  <%} %>  >ธันวาคม</option>
												</select></td>
							<td id="fiscalYear" align="center" ><input type="text" value="<%=yearEdit%>" name="fiscalYear" readonly="readonly"   maxlength="4" align="center" style="text-align:center;width:100%;background-color:silver;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="workHour" width="100">
								<select name="workHour" >
									<option value="6" >6</option>
									<option value="6.5" >6.5</option>
									<option value="7" >7</option>
									<!--<option value="B" >ตกเบิกปรับปรุงรายการรับ</option>
									<option value="S" >ตกเบิกรายการรับเรียกคืน</option>-->
								</select>
							</td>
							<td id="startDate" align="center" ><input type="text" value="<%=date%>"  name="startDate" maxlength="10" align="center" style="text-align:center;width:100%;" onchange="return validateDate(this) && compareStDate(this);" ></td>
							<td id="endDate" align="center" ><input type="text" value="<%=date%>"  name="endDate"  maxlength="10" align="center" style="text-align:center;width:100%;" onchange="return validateDate(this) && compareEndDate(this);" ></td>
							<td id="totDay15" align="center" ><input type="text"  name="totDay15"  maxlength="2" align="center" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="totDay1" align="center" ><input type="text"  name="totDay1"  maxlength="2" align="center" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="totDay3" align="center" ><input type="text"  name="totDay3"  maxlength="2" align="center" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="spsDay15" align="center" ><input type="text"  name="spsDay15"  maxlength="3" align="center" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="spsDay1" align="center" ><input type="text"  name="spsDay1"  maxlength="3" align="center" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);" /></td>
							<td id="spsDay3" align="center" ><input type="text"  name="spsDay3"  maxlength="3" align="center" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="seqData" align="center" ><input type="text"  name="seqData"  maxlength="3" align="center" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/><input type="hidden"  name="keySeq" /></td>
						</tr>		
						
					</table>
				</div>
			</td>
		</tr>
         
</table>&nbsp;

<input type="hidden" name="pageEdit" value="">
<table width="100%" CLASS="TABLEBULE2" >
					<tr CLASS="TABLEBULE2" >
						<td align="left" >&nbsp;
							<input type="Button" class=" button " value="ลบข้อมูล" name="deleteData" onclick="removeVisualRow();"/>						
							<input type="Button" class=" button " value="  ตกลง " name="confirmData" onclick="onUpdate();"/>
							<input type="Button" class=" button " value="  ออก  " name="back" onclick="backMain();"/>
						</td>
					</tr>
				</table>
</form>
<form name="backForm" action="security.htm?reqCode=CTWGPAYMT003" method="post">
		<input type="hidden" name="orgFromEdit" />
		<input type="hidden" name="orgToEdit" />
		<input type="hidden" name="empCodeFromEdit" />
		<input type="hidden" name="empCodeToEdit" />
		<input type="hidden" name="pageEdit" />
		<input type="hidden" name="orderFromCboEdit" />
		<input type="hidden" name="orderToCboEdit" />
		<input type="hidden" name="refNoFromEdit" />
		<input type="hidden" name="refNoToEdit" />
		<input type="hidden" name="otTypeEdit" />
		<input type="hidden" name="mustQuery" />
</form>
</body>
</html>

<SCRIPT LANGUAGE="JavaScript">
<!--
	var allRowDelete = 0;
	var rowDelete = 0;
	function addVisualRow(){
	
		var table = document.getElementById("table");
		var tempRow = document.getElementById("temprow");
	
		// Insert two rows.
	   	var oTable = table;
	   	var idx = oTable.rows.length;
	   	var oRow1=oTable.insertRow(idx);
	   	var str=" " ;
		var from = "[0]";
		var to = "("+(oRow1.rowIndex+tempRow.rowIndex)+")";
	
		// Insert cells into row.
		oRow1.id = tempRow.id;
		for(i=0;i<tempRow.cells.length;i++){
			var oCell=oRow1.insertCell(i);
			// Add regular HTML values to the cells.
			
			
			oCell.id = tempRow.cells[i].id;
			oCell.innerHTML=tempRow.cells[i].innerHTML;  
			str = oCell.innerHTML;
			if(str.indexOf(from)>0){
				oCell.innerHTML=str.replace(from, to);
			}
		}
		
	}

//this function removeVisualRow() and removeRow in database used page UPDATE ONLY!!!
	var chDelete = false;
	var chcCon = false;
	function removeVisualRow(){
			
			var tab = $('dataTable');
			var row;
			var empList=[];
			var frm = document.forms["formEdit"];
			var chk = frm.elements["chk"];
			
			
			var table = document.getElementById("table");
			var tdName;
			var chkName;
			var num = 2 + parseInt(DWRUtil.getValue("dataLength"));
		
			var oRows = table.rows;
			if(tdName == null)tdName="flag";
			if(chkName == null)chkName="chk";
	
		//*****Count all row to delete********//
			
			for(x=oRows.length-1;x > num;x--){
				
				if (oRows[x].cells[tdName].childNodes[0].checked){
						chcCon = true;
							
				}
				
			}
				
			if(tab.rows.length>1){
				for(i=0; i<tab.rows.length; i++){
					row = tab.rows[i];	
					if (chk[i].checked){
							chcCon = true;
						}
					
						
				}
			}else{
				if(tab.rows.length==1){
					row = tab.rows[0];	
					if (chk[0].checked){
							chcCon = true;
						}
				  
				}	
			}
			
			
			
			
			
			
		
		//*****End count all row to delete********//
		if(chcCon){
			var answer = confirm("ต้องการลบข้อมูล หรือไม่?");
			if( answer ){
				//*****Delete row in visualTable********//
					for(i=oRows.length-1;i > num;i--){
						
						if (oRows[i].cells[tdName].childNodes[0].checked){
								chDelete = true;
								table.deleteRow(i);			
						}
						
					}
				
				//*****End Delete row in visualTable********//
				
				//************Delete row in database***************//
					
				DWREngine.beginBatch();
				//alert('123435  :'+tab.rows.length);
			    if(tab.rows.length>1){
					for(i=0; i<tab.rows.length; i++){
						row = tab.rows[i];	
						if (chk[i].checked){
								
								//alert('BB'+rowDelete);
								rwOvertime.keySeq = parseInt(frm.elements["keySeq"][i].value);
								FeeWgRwOvertimeService.deleteRwOvertime(rwOvertime, {callback:onDeleteCallback});
								chDelete = true;
							}
						
							
					}
				}else{
					if(tab.rows.length==1){
						row = tab.rows[0];	
						if (chk[0].checked){
								
								//alert('CC'+rowDelete);
								rwOvertime.keySeq = parseInt(frm.elements["keySeq"][0].value);
								FeeWgRwOvertimeService.deleteRwOvertime(rwOvertime, {callback:onDeleteCallback});
								chDelete = true;
							}
					  
					}	
				}
				
				
				DWREngine.endBatch();
			}
		}
		if(chDelete){
			alert('ลบข้อมูลเรียบร้อย');
			whenQueryData();
		}
	}

	function onDeleteCallback()
	{
		//whenQueryData();
	}
	
	
	
	

//-->
</SCRIPT>