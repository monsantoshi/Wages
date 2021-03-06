<%@ page language="java" contentType="text/html;charset=TIS-620" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<%
	String empID = (String)request.getParameter("empCodeEdit");
	String yearEdit = (String)request.getParameter("yearEdit");
	String periodEdit = (String)request.getParameter("periodEdit");
	String ouCodeEdit = (String)request.getParameter("ouCodeEdit");
	String confirm = (String)request.getParameter("confirmEdit");
	String userId = (String)request.getParameter("userIdEdit");
	String sectionEdit = (String)request.getParameter("sectionEdit");
	
	String orgFromEdit = request.getParameter("orgFromEdit");
	String orgToEdit = request.getParameter("orgToEdit");
	String empCodeFromEdit = request.getParameter("empCodeFromEdit");
	String empCodeToEdit = request.getParameter("empCodeToEdit");
	String pageEdit = request.getParameter("pageEdit");
	
	String orderFromCboEdit = request.getParameter("orderFromCboEdit");
	String orderToCboEdit = request.getParameter("orderToCboEdit");
	
	if( pageEdit.trim().equals("") )
		pageEdit = "-1";	
	
	java.util.Date dd = new java.util.Date();
	java.text.SimpleDateFormat fmd = new java.text.SimpleDateFormat("dd/MM/yyyy",new java.util.Locale("th","TH"));
	String date = fmd.format(dd);
	
	java.util.Calendar cc = new java.util.GregorianCalendar();
	int x = cc.get(java.util.Calendar.MONTH) +1;
	//System.out.println("#$#$#$#$$#$#$# :::"+x);
	
	String periodWork = String.valueOf(x);
	
	
%>
<html>
<head>
<title>�ѹ�֡��¡���ѡ�ó��Թ��</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<!-- Javascript Script File -->
<script type="text/javascript" src="dwr/interface/FeeWpayRwVinaiService.js"></script>
<script type="text/javascript" src="script/gridScript.js"></script>
<script type="text/javascript" src="script/dojo.js"></script>
<script type="text/javascript" src="script/dateCalendar.js"></script>

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
<script type="text/javascript"><!--


	var myUpdate = new Array();
	var count = 0;
	var specRow = 0;

	var rowModify ;
	
	function whenQueryData()
	{
		DWRUtil.useLoadingMessage("Loading ...");
		FeeWpayRwVinaiService.findByEmpCodeDetail('<%=empID%>','<%=ouCodeEdit%>','<%=yearEdit%>','<%=periodEdit%>',{callback:whenQueryDetailHandler,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
		
		FeeWpayRwVinaiService.findByEmpCodeList('<%=userId%>','<%=ouCodeEdit%>','<%=yearEdit%>','<%=periodEdit%>','<%=empID%>',{callback:whenQueryListDataHandler,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
	}
	
	function whenQueryDetailHandler(data)
	{
		$("year").value = '<%=yearEdit%>';
		$("section").value = '<%=sectionEdit%>';
		$("period").value = '<%=periodEdit%>';
		$("empCode").value = data.empCode;
		$("name").value = data.name;
		//$("positionShort").value = data.positionShort;
		$("orgCode").value = data.orgCode;
		$("orgDesc").value = data.orgDesc;
		$("codeSeq").value = data.codeSeqWork;
		
		if(<%=confirm%>){
			
			document.forms['formEdit'].elements['deleteData'].disabled = true;
			document.forms['formEdit'].elements['confirmData'].disabled = true;
		}
	}
	

	
	
	var cellFuncs = [
		function(data) { return writeCheckBox();},
		function(data) { return writeSelect("flagPr",data.flagPr,data.keySeq);},
		function(data) { 
							if(data.cutSalYear != null ){ 
								return writeText("cutSalYear",data.cutSalYear,4,"center",data.keySeq);
							}else{
			                 	return writeText("cutSalYear","",4,"center",data.keySeq);
			                } 
						},
		function(data) {    if(data.cutSalMonth != null ){ 
							return writeSelectMonthCut("cutSalMonth",data.cutSalMonth,data.keySeq);
							}else{
         					return writeSelectMonthCut("cutSalMonth","",data.keySeq);
        					}	
						},
		function(data) { 
							if(data.cutSalPercent != null ){ 
								return writeHidden2("keySeq",data.keySeq)+" "+writeTextDay("cutSalPercent",data.cutSalPercent,6,"right",data.keySeq);
							}else{
			                 	return writeHidden2("keySeq",data.keySeq)+" "+writeTextDay("cutSalPercent","",6,"right",data.keySeq);
			                } 
						},
		function(data) { 
							if(data.absYear1 != null ){ 
								return writeText("absYear1",data.absYear1,4,"center",data.keySeq);
							}else{
			                 	return writeText("absYear1","",4,"center",data.keySeq);
			                } 
						},
		function(data) { return writeSelectMonth("absMonth1",data.absMonth1,data.keySeq);},
		function(data) { 
							if(data.absDay1 != null ){ 
								return writeHidden2("keySeq",data.keySeq)+" "+writeTextDay("absDay1",data.absDay1,6,"right",data.keySeq);
							}else{
			                 	return writeHidden2("keySeq",data.keySeq)+" "+writeTextDay("absDay1","",6,"right",data.keySeq);
			                } 
						}];
	
	function whenQueryListDataHandler(data){
		$("dataLength").value = data.length;
		if(data.length > 0)
		{
			DWRUtil.removeAllRows("dataTable");
			DWRUtil.addRows("dataTable",data,cellFuncs);
			
			
			
		
		}else{
			DWRUtil.removeAllRows("dataTable");
			alert('��辺������');
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
		return "<div align='center' style='background-color:#CCCCCC;'><select name='"+inname+"' disabled='true' style='background-color:transparent;color:#000000'><option value='N' "+temp1+" >����</option>"+
												"<option value='A' "+temp2+">��Ѻ��ا��¡���ѡ</option>" +
												"<option value='R' "+temp3+">��Ѻ��ا��¡���Ѻ���¡�׹</option>" +
												"<option value='B' "+temp4+">���ԡ��Ѻ��ا��¡���Ѻ</option>" +
												"<option value='S' "+temp5+">���ԡ��¡���Ѻ���¡�׹</option></select></div>";
		}else {
		return "<div align='center' ><select onchange='addListUpdate("+key+")' name='"+inname+"'><option value='N' "+temp1+" >����</option>"+
												"<option value='A' "+temp2+">��Ѻ��ا��¡���ѡ</option>" +
												"<option value='R' "+temp3+">��Ѻ��ا��¡���Ѻ���¡�׹</option>" +
												"<option value='B' "+temp4+">���ԡ��Ѻ��ا��¡���Ѻ</option>" +
												"<option value='S' "+temp5+">���ԡ��¡���Ѻ���¡�׹</option></select></div>";
		
		
		}
	}
	
	
	
	function writeSelectMonth(inname,emp,key){
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
													"<option value='1' "+mm1+" >���Ҥ�</option>" +
													"<option value='2' "+mm2+">����Ҿѹ��</option>" +
													"<option value='3' "+mm3+">�չҤ�</option>" +
													"<option value='4' "+mm4+">����¹</option>" +
													"<option value='5' "+mm5+">����Ҥ�</option>" +
													"<option value='6' "+mm6+" >�Զع�¹</option>" +
													"<option value='7' "+mm7+">�á�Ҥ�</option>" +
													"<option value='8' "+mm8+">�ԧ�Ҥ�</option>" +
													"<option value='9' "+mm9+">�ѹ��¹</option>" +
													"<option value='10' "+mm10+" >���Ҥ�</option>" +
													"<option value='11' "+mm11+">��Ȩԡ�¹</option>" +
													"<option value='12' "+mm12+" >�ѹ�Ҥ�</option>" +
												"</select></div>";
		}else {
		return "<div align='center' ><select onchange='addListUpdate("+key+");bypassRowUpdate("+key+");' name='"+inname+"'>" +
		                                            "<option value=''  ></option>"+
												    "<option value='1' "+mm1+" >���Ҥ�</option>" +
													"<option value='2' "+mm2+">����Ҿѹ��</option>" +
													"<option value='3' "+mm3+">�չҤ�</option>" +
													"<option value='4' "+mm4+">����¹</option>" +
													"<option value='5' "+mm5+">����Ҥ�</option>" +
													"<option value='6' "+mm6+" >�Զع�¹</option>" +
													"<option value='7' "+mm7+">�á�Ҥ�</option>" +
													"<option value='8' "+mm8+">�ԧ�Ҥ�</option>" +
													"<option value='9' "+mm9+">�ѹ��¹</option>" +
													"<option value='10' "+mm10+" >���Ҥ�</option>" +
													"<option value='11' "+mm11+">��Ȩԡ�¹</option>" +
													"<option value='12' "+mm12+" >�ѹ�Ҥ�</option>" +
												"</select></div>";
		
		
		}
	}
	
	function writeSelectMonthCut(inname,emp,key){
		var mm = '';
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
		
		if(emp== ''){
			mm = 'selected';
		}else if(emp=='1'){
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
													"<option value='1' "+mm1+" >���Ҥ�</option>" +
													"<option value='2' "+mm2+">����Ҿѹ��</option>" +
													"<option value='3' "+mm3+">�չҤ�</option>" +
													"<option value='4' "+mm4+">����¹</option>" +
													"<option value='5' "+mm5+">����Ҥ�</option>" +
													"<option value='6' "+mm6+" >�Զع�¹</option>" +
													"<option value='7' "+mm7+">�á�Ҥ�</option>" +
													"<option value='8' "+mm8+">�ԧ�Ҥ�</option>" +
													"<option value='9' "+mm9+">�ѹ��¹</option>" +
													"<option value='10' "+mm10+" >���Ҥ�</option>" +
													"<option value='11' "+mm11+">��Ȩԡ�¹</option>" +
													"<option value='12' "+mm12+" >�ѹ�Ҥ�</option>" +
												"</select></div>";
		}else {
		return "<div align='center' ><select onchange='addListUpdate("+key+");bypassRowUpdateCut("+key+");' name='"+inname+"'>" +
		                                            "<option value=''  ></option>"+
												    "<option value='1' "+mm1+" >���Ҥ�</option>" +
													"<option value='2' "+mm2+">����Ҿѹ��</option>" +
													"<option value='3' "+mm3+">�չҤ�</option>" +
													"<option value='4' "+mm4+">����¹</option>" +
													"<option value='5' "+mm5+">����Ҥ�</option>" +
													"<option value='6' "+mm6+" >�Զع�¹</option>" +
													"<option value='7' "+mm7+">�á�Ҥ�</option>" +
													"<option value='8' "+mm8+">�ԧ�Ҥ�</option>" +
													"<option value='9' "+mm9+">�ѹ��¹</option>" +
													"<option value='10' "+mm10+" >���Ҥ�</option>" +
													"<option value='11' "+mm11+">��Ȩԡ�¹</option>" +
													"<option value='12' "+mm12+" >�ѹ�Ҥ�</option>" +
												"</select></div>";
		
		
		}
	}
	
	function writeText(inname,emp,maxlength,textalign,key)
	{
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}else{
			return "<div align='center' ><input type='text' onchange='addListUpdate("+key+")' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}
	}
	
	function writeTextDay(inname,emp,maxlength,textalign,key)
	{
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}else{
			return "<div align='center' ><input type='text' onchange='addListUpdate("+key+");checkDayRowUpdate("+key+");' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}
	}
	
	function writeTextDate(inname,emp,maxlength,textalign,key,size)
	{
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' size='"+size+"' maxlength='"+maxlength+"' style='text-align:"+textalign+";background-color:transparent;' onblur='validateDate(this);'/></div>";
		}else{
			return "<div align='center' ><input type='text' onchange='addListUpdate("+key+")' name = '"+inname+"' value='"+emp+"' align='center' size='"+size+"' maxlength='"+maxlength+"' style='text-align:"+textalign+";' onblur='validateDate(this);'/></div>";
		}
	}
	
	
	
	function writeHidden(inname,emp,maxlength,textalign,nameHide,empHide)
	{
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		}else{
			return "<div align='center'><input type='text' name = '"+inname+"' onchange='addListUpdate("+empHide+")' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		}
	}
	
	function writeHidden2(nameHide,empHide)
	{
		if(<%=confirm%>){
			return "<div align='center'><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		}else{
			return "<div align='center'><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		}
	}
	function writeTextRemark(inname,emp,maxlength,textalign,key)
	{
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='left' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' /></div>";
		}else{
			return "<div align='center' ><input type='text' onchange='addListUpdate("+key+")' name = '"+inname+"' value='"+emp+"' align='left' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;' /></div>";
		}
	}
	var rwVinai = {	keySeq:null,ouCode:null,yearPr:null,periodPr:null,empCode:null,
					yearWork:null,periodWork:null,codeSeq:null,
					decSal:null,newSalary:null,decSalMonth:null,
					cutSalYear:null,cutSalMonth:null,cutSalPercent:null,confirmFlag:null,
					absYear1:null,absMonth1:null,absDay1:null,
					absYear2:null,absMonth2:null,absDay2:null,
					startDateQut:null,endDateQut:null,flagPr:null,
					updBy:null,updDate:null,creBy:null,creDate:null};
	
	var allRowUpdate = 0;
	
	function onUpdate()
	{
		var table = document.getElementById("table");
		var aRows = table.rows;
		//var num = 2 + parseInt(DWRUtil.getValue("dataLength"));
		var num = 2 + parseInt(DWRUtil.getValue("dataLength"));
		
		var tab = $('dataTable');
		var update ;
		var row;
		var empList=[];
		var frm   = document.forms["formEdit"];
		
		var keySeq 			= frm.elements["keySeq"];
		var flagPr 			= frm.elements["flagPr"];
		var yearWork 		= frm.elements["yearWork"];
		var periodWork 		= frm.elements["periodWork"];
	
		var cutSalYear 		= frm.elements["cutSalYear"];
		var cutSalMonth 	= frm.elements["cutSalMonth"];
		var cutSalPercent   = frm.elements["cutSalPercent"]
		var absYear1 		= frm.elements["absYear1"];
		var absMonth1 		= frm.elements["absMonth1"];
		var absDay1 		= frm.elements["absDay1"];
		

		//alert( absMonth2 );
		
		DWREngine.beginBatch();

		for(var i=0; i<tab.rows.length; i++)
		{	
		
		  var periodWWork = parseInt(cutSalMonth[i].value);
	      var yearWWork = cutSalYear[i].value;
	      var periodPWork = parseInt(absMonth1[i].value);
	      var yearPWork = absYear1[i].value;
		  var section = '<%=sectionEdit%>';
		  var yearInt = '<%=yearEdit%>';
		  var monthWork = parseInt(section.substr(0, 2));	
		  if ((yearWWork <=yearInt && periodWWork != null) && (yearPWork<=yearInt && periodPWork != null)){
			update = false;
			row = tab.rows[i];
			rwVinai.keySeq = parseInt(keySeq[i].value);
			if(tab.rows.length == 1){
				rwVinai.flagPr = flagPr.value;
			
			}else{
				rwVinai.flagPr = flagPr[i].value;
				
				
			}
			

				rwVinai.periodWork  = '<%=periodWork%>';
			
				
		
				
				
			
			rwVinai.yearWork  = '<%=yearEdit%>';
			
			

		
			
			if (cutSalYear[i].value !=''){
				rwVinai.cutSalYear  = cutSalYear[i].value;
			}
			else{
				rwVinai.cutSalYear  = null;
			}
			
			if (cutSalMonth[i].value !=''){
				rwVinai.cutSalMonth  = cutSalMonth[i].value;
			}
			else{
				rwVinai.cutSalMonth  = null;
			}
			if (cutSalPercent[i].value !=''){
				rwVinai.cutSalPercent  = cutSalPercent[i].value;
			}
			else{
				rwVinai.cutSalPercent  = null;
			}
			
			if (absYear1[i].value !=''){
				rwVinai.absYear1  = absYear1[i].value;
			}
			else{
				rwVinai.absYear1  = null;
			}
			
			if (absMonth1[i].value !=''){
				rwVinai.absMonth1  = absMonth1[i].value;
			}
			else{
				rwVinai.absMonth1  = null;
			}
			
			
			if (absDay1[i].value !=''){
				rwVinai.absDay1  = absDay1[i].value;
			}
			else{
				rwVinai.absDay1  = null;
			}
			
		
			
			rwVinai.updBy = '<%=userId%>';
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
						FeeWpayRwVinaiService.addList(rwVinai, false, {callback:onInsertCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
					else
						FeeWpayRwVinaiService.addList(rwVinai, false);
					
				}else{
					if( allRowUpdate == myUpdate.length )
							FeeWpayRwVinaiService.addList(rwVinai, true, {callback:ClearData,errorHandler:function(message) { alert('�������ö�ѹ�֡��');}});
						else
							FeeWpayRwVinaiService.addList(rwVinai, false);
					
				}
			}
		}else if ((cutSalYear[i].value=='' && cutSalMonth[i].value == '') && (yearPWork<=yearInt && periodPWork != null)){
			update = false;
			row = tab.rows[i];
			rwVinai.keySeq = parseInt(keySeq[i].value);
			if(tab.rows.length == 1){
				rwVinai.flagPr = flagPr.value;
			
			}else{
				rwVinai.flagPr = flagPr[i].value;
				
				
			}
			

				rwVinai.periodWork  = '<%=periodWork%>';
			
				
		
				
				
			
			rwVinai.yearWork  = '<%=yearEdit%>';
			
			

		
			
			if (cutSalYear[i].value !=''){
				rwVinai.cutSalYear  = cutSalYear[i].value;
			}
			else{
				rwVinai.cutSalYear  = null;
			}
			
			if (cutSalMonth[i].value !=''){
				rwVinai.cutSalMonth  = cutSalMonth[i].value;
			}
			else{
				rwVinai.cutSalMonth  = null;
			}
			if (cutSalPercent[i].value !=''){
				rwVinai.cutSalPercent  = cutSalPercent[i].value;
			}
			else{
				rwVinai.cutSalPercent  = null;
			}
			
			if (absYear1[i].value !=''){
				rwVinai.absYear1  = absYear1[i].value;
			}
			else{
				rwVinai.absYear1  = null;
			}
			
			if (absMonth1[i].value !=''){
				rwVinai.absMonth1  = absMonth1[i].value;
			}
			else{
				rwVinai.absMonth1  = null;
			}
			
			
			if (absDay1[i].value !=''){
				rwVinai.absDay1  = absDay1[i].value;
			}
			else{
				rwVinai.absDay1  = null;
			}
			
		
			
			rwVinai.updBy = '<%=userId%>';
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
						FeeWpayRwVinaiService.addList(rwVinai, false, {callback:onInsertCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
					else
						FeeWpayRwVinaiService.addList(rwVinai, false);
					
				}else{
					if( allRowUpdate == myUpdate.length )
							FeeWpayRwVinaiService.addList(rwVinai, true, {callback:ClearData,errorHandler:function(message) { alert('�������ö�ѹ�֡��');}});
						else
							FeeWpayRwVinaiService.addList(rwVinai, false);
					
				}
			}
		}else if ((yearWWork<=yearInt && periodWWork != null ) && (absYear1[i].value=='' && absMonth1[i].value == '')){
			update = false;
			row = tab.rows[i];
			rwVinai.keySeq = parseInt(keySeq[i].value);
			if(tab.rows.length == 1){
				rwVinai.flagPr = flagPr.value;
			
			}else{
				rwVinai.flagPr = flagPr[i].value;
				
				
			}
			

				rwVinai.periodWork  = '<%=periodWork%>';
			
				
		
				
				
			
			rwVinai.yearWork  = '<%=yearEdit%>';
			
			

		
			
			if (cutSalYear[i].value !=''){
				rwVinai.cutSalYear  = cutSalYear[i].value;
			}
			else{
				rwVinai.cutSalYear  = null;
			}
			
			if (cutSalMonth[i].value !=''){
				rwVinai.cutSalMonth  = cutSalMonth[i].value;
			}
			else{
				rwVinai.cutSalMonth  = null;
			}
			if (cutSalPercent[i].value !=''){
				rwVinai.cutSalPercent  = cutSalPercent[i].value;
			}
			else{
				rwVinai.cutSalPercent  = null;
			}
			
			if (absYear1[i].value !=''){
				rwVinai.absYear1  = absYear1[i].value;
			}
			else{
				rwVinai.absYear1  = null;
			}
			
			if (absMonth1[i].value !=''){
				rwVinai.absMonth1  = absMonth1[i].value;
			}
			else{
				rwVinai.absMonth1  = null;
			}
			
			
			if (absDay1[i].value !=''){
				rwVinai.absDay1  = absDay1[i].value;
			}
			else{
				rwVinai.absDay1  = null;
			}
			
		
			
			rwVinai.updBy = '<%=userId%>';
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
						FeeWpayRwVinaiService.addList(rwVinai, false, {callback:onInsertCallback,errorHandler:function(message) { alert('�Դ��ͼԴ��Ҵ����ǡѺ��õԴ����������');}});
					else
						FeeWpayRwVinaiService.addList(rwVinai, false);
					
				}else{
					if( allRowUpdate == myUpdate.length )
							FeeWpayRwVinaiService.addList(rwVinai, true, {callback:ClearData,errorHandler:function(message) { alert('�������ö�ѹ�֡��');}});
						else
							FeeWpayRwVinaiService.addList(rwVinai, false);
					
				}
			}
		}
			
		
		else{
			    alert("�������١��ͧ ���� ��͹��ͧ����Թ��͹�Ѩ�غѹ �к�¡��ԡ��úѹ�֡");
			    RevertData();
			 }
        
        
       
        
        }	
        
      
        
		DWREngine.endBatch();
		if(myUpdate.length == 0){
			insertNewData();
		}
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
				rwVinai.keySeq = null;
				rwVinai.ouCode = '<%=ouCodeEdit%>';
				rwVinai.yearPr = '<%=yearEdit%>';
				rwVinai.periodPr = '<%=periodEdit%>';
				rwVinai.empCode = '<%=empID%>';
				rwVinai.codeSeq = $("codeSeq").value;
				rwVinai.flagPr= oRows[i].cells["flagPr"].childNodes[0].value;
				rwVinai.yearWork = '<%=yearEdit%>';
				rwVinai.periodWork = '<%=periodWork%>';
			
				if(oRows[i].cells["cutSalYear"].childNodes[0].value != null && oRows[i].cells["cutSalYear"].childNodes[0].value != ''){
					rwVinai.cutSalYear = oRows[i].cells["cutSalYear"].childNodes[0].value;
				}else{
					rwVinai.cutSalYear = null;
				}
				if(oRows[i].cells["cutSalMonth"].childNodes[0].value != null && oRows[i].cells["cutSalMonth"].childNodes[0].value != ''){
					rwVinai.cutSalMonth = oRows[i].cells["cutSalMonth"].childNodes[0].value;
				}else{
					rwVinai.cutSalMonth = null;
				}
				if(oRows[i].cells["cutSalPercent"].childNodes[0].value != null && oRows[i].cells["cutSalPercent"].childNodes[0].value != ''){
					rwVinai.cutSalPercent = oRows[i].cells["cutSalPercent"].childNodes[0].value;
				}else{
					rwVinai.cutSalPercent = null;
				}
				if(oRows[i].cells["absYear1"].childNodes[0].value != null && oRows[i].cells["absYear1"].childNodes[0].value != ''){
					rwVinai.absYear1 = oRows[i].cells["absYear1"].childNodes[0].value;
				}else{
					rwVinai.absYear1 = null;
				}
				if(oRows[i].cells["absMonth1"].childNodes[0].value != null && oRows[i].cells["absMonth1"].childNodes[0].value != ''){
					rwVinai.absMonth1 = oRows[i].cells["absMonth1"].childNodes[0].value;
				}else{
					rwVinai.absMonth1 = null;
				}
				if(oRows[i].cells["absDay1"].childNodes[0].value != null && oRows[i].cells["absDay1"].childNodes[0].value != ''){
					rwVinai.absDay1 = oRows[i].cells["absDay1"].childNodes[0].value;
				}else{
					rwVinai.absDay1 = null;
				}
				
		
				rwVinai.confirmFlag = 'N';
				rwVinai.updBy = '<%=userId%>';
				rwVinai.creBy = '<%=userId%>';
				rwVinai.creDatw = getDateFromFormat(<%=date%>,"dd/MM/yyyy");
				//FeeWpayRwVinaiService.addList(rwVinai, {callback:onInsertCallback});
				
				if( i == (num + 1) )
					FeeWpayRwVinaiService.addList(rwVinai, true, {callback:ClearData,errorHandler:function(message) { alert('�������ö�ѹ�֡��');}});
				else
					FeeWpayRwVinaiService.addList(rwVinai, false);
			
		}
		DWREngine.endBatch();
		
	}
	
	function ClearData(){
		alert("�ѹ�֡���������º����");
		var frm=document.forms["backForm"];
		
		document.getElementById("orgFromEdit").value = '<%= orgFromEdit %>';
		document.getElementById("orgToEdit").value = '<%= orgToEdit %>';
		document.getElementById("empCodeFromEdit").value = '<%= empCodeFromEdit %>';
		document.getElementById("empCodeToEdit").value = '<%= empCodeToEdit %>';
		document.getElementById("pageEdit").value = '<%= Integer.parseInt(pageEdit) - 1 %>';
		
		document.getElementById("orderFromCboEdit").value = '<%= orderFromCboEdit %>';
		document.getElementById("orderToCboEdit").value = '<%= orderToCboEdit %>';
		document.getElementById("mustQuery").value = 'true';
		frm.submit();
	}
	
	function RevertData(){
		//alert("�ѹ�֡���������º����");
		var frm=document.forms["backForm"];
		
		document.getElementById("orgFromEdit").value = '<%= orgFromEdit %>';
		document.getElementById("orgToEdit").value = '<%= orgToEdit %>';
		document.getElementById("empCodeFromEdit").value = '<%= empCodeFromEdit %>';
		document.getElementById("empCodeToEdit").value = '<%= empCodeToEdit %>';
		document.getElementById("pageEdit").value = '<%= Integer.parseInt(pageEdit) - 1 %>';
		
		document.getElementById("orderFromCboEdit").value = '<%= orderFromCboEdit %>';
		document.getElementById("orderToCboEdit").value = '<%= orderToCboEdit %>';
		document.getElementById("mustQuery").value = 'true';
		frm.submit();
	}
	
	function onInsertCallback(){
	
		insertNewData();
		allRowUpdate = 0;
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
		document.getElementById("mustQuery").value = 'true';
		frm.submit();
	}
	
	dojo.addOnLoad(whenQueryData);
	
	
	
	 
	 function checkDay(object){
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
	
		var month = oRows[rowToCompare].cells["absMonth1"].childNodes[0].value;
		var day = oRows[rowToCompare].cells["absDay1"].childNodes[0].value;
		
		
		
		if(day != null && day != '' && day != 0){
		
			switch (month) {
				  case "1":
				    if(day > 31){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "2":
				    if(day > 29){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "3":
				    if(day > 31){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "4":
				    if(day > 30){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "5":
				    if(day > 31){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "6":
				    if(day > 30){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "7":
				    if(day > 31){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "8":
				    if(day > 31){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "9":
				    if(day > 30){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "10":
				    if(day > 31){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "11":
				    if(day > 30){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				  case "12":
				    if(day > 31){
				    	alert('�ӹǹ�ѹ���١��ͧ');
				    	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
				    }
				    break;
				} 
		
		
		}
		//alert(month+' ::: '+day)
		
	
	 	
	 	
	 
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
	
		
	 	
	 	oRows[rowToCompare].cells["absDay1"].childNodes[0].value = '';
	 	
	 	
	 	
	 
	 }
	 
	 function bypassCut(object){
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
	
		
	 	
	 	oRows[rowToCompare].cells["cutSalPercent"].childNodes[0].value = '';
	 	
	 	
	 	
	 
	 }
	 
	 function checkDayRowUpdate(data){
	 	 var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["formEdit"];
		//alert('leng  :'+tab.rows.length);	
		
		if(tab.rows.length > 0){
			var keySeq 		= frm.elements["keySeq"];
			var monthCheck 	= frm.elements["absMonth1"];
			var dayCheck 		= frm.elements["absDay1"];
		
			for(var c=0; c<tab.rows.length; c++){
				//alert(keySeq[c].value + ':::'+ data);
				if(keySeq[c].value == data ){
					rowModify = c;
				//	alert('value in row :'+keySeq[c].value+' : '+flagPr[c].value+' : '+yearWork[c].value+' : '+periodWork[c].value+' : '+fiscalYear[c].value+' : '+startDate[c].value+' : '+endDate[c].value+' : '+totDay1[c].value+' : '+totDay15[c].value+' : '+totDay3[c].value+' : '+seqData[c].value+' : '+codeSeqNew[c].value+' : '+flagWork[c].value+' : '+orgCode[c+1].value);
					break;
				}
			}
			
		
			var month = monthCheck[rowModify].value;
			var day = dayCheck[rowModify].value;
		
			
			
			
		 	
		
		
				if(day != null && day != '' && day != 0){
				
					switch (month) {
						  case "1":
						    if(day > 31){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "2":
						    if(day > 29){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "3":
						    if(day > 31){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "4":
						    if(day > 30){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "5":
						    if(day > 31){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "6":
						    if(day > 30){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "7":
						    if(day > 31){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "8":
						    if(day > 31){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "9":
						    if(day > 30){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "10":
						    if(day > 31){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "11":
						    if(day > 30){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "12":
						    if(day > 31){
						    	alert('�ӹǹ�ѹ���١��ͧ');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						} 
				
				
				}
		//alert(month+' ::: '+day)
		
	
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
			var monthCheck 	= frm.elements["absMonth1"];
			var dayCheck 		= frm.elements["absDay1"];
			
			for(var c=0; c<tab.rows.length; c++){
				//alert(keySeq[c].value + ':::'+ data);
				if(keySeq[c].value == data ){
					rowModify = c;
				//	alert('value in row :'+keySeq[c].value+' : '+flagPr[c].value+' : '+yearWork[c].value+' : '+periodWork[c].value+' : '+fiscalYear[c].value+' : '+startDate[c].value+' : '+endDate[c].value+' : '+totDay1[c].value+' : '+totDay15[c].value+' : '+totDay3[c].value+' : '+seqData[c].value+' : '+codeSeqNew[c].value+' : '+flagWork[c].value+' : '+orgCode[c+1].value);
					break;
				}
			}
			
			//alert(dayCheck[rowModify].value);
			dayCheck[rowModify].value = '';
		}
	 	
	 	
	 	
	 
	 }
	 
	 function bypassRowUpdateCut(data){
	 	  var tab = $('dataTable');
		var row;
		var update ;
		var empList=[];
		var frm   = document.forms["formEdit"];
		//alert('leng  :'+tab.rows.length);	
		
		if(tab.rows.length > 0){
			var keySeq 		= frm.elements["keySeq"];
			var monthCheck 	= frm.elements["cutSalMonth"];
			var percentCheck 		= frm.elements["cutSalPercent"];
			
			for(var c=0; c<tab.rows.length; c++){
				//alert(keySeq[c].value + ':::'+ data);
				if(keySeq[c].value == data ){
					rowModify = c;
				//	alert('value in row :'+keySeq[c].value+' : '+flagPr[c].value+' : '+yearWork[c].value+' : '+periodWork[c].value+' : '+fiscalYear[c].value+' : '+startDate[c].value+' : '+endDate[c].value+' : '+totDay1[c].value+' : '+totDay15[c].value+' : '+totDay3[c].value+' : '+seqData[c].value+' : '+codeSeqNew[c].value+' : '+flagWork[c].value+' : '+orgCode[c+1].value);
					break;
				}
			}
			
			//alert(dayCheck[rowModify].value);
			percentCheck[rowModify].value = '';
		}
	 	
	 	
	 	
	 
	 }
	
	

--></script>
</head>

<body>
<form name="formEdit"  action="security.htm?reqCode=CTWPAYMT009" method="post">
<input type="hidden" name="dataLength">
<input type="hidden" name="codeSeq">
<input type="hidden" name="period">
<table width="100%">
	<tr>
		<td class="font-head">
			[ CTRWUP009 ] �ѹ�֡��¡���ѡ�ó��Թ�� �Ѵ��Ҩ�ҧ ��ТҴ�ҹ
		</td>
	</tr>
</table>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">

      <tr>
          <td  align="center">
				<table width="750" border="1" cellpadding="1" bordercolor="#6699CC" cellspacing="1"  align="center">
        			<tr>
          				<td class="font-field"  align="right" width="100">��Шӻ�</td>
          				<td align="left"><input type="text" name="year" value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/></td>
          				<td colspan="3" class="font-field" align="left" width="250">
          					&nbsp;&nbsp;�Ǵ&nbsp;&nbsp;
          					<input type="text" name="section" value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/>
          				</td>
        			</tr>
        			<tr>
         				<td class="font-field"  align="right">�Ţ��Шӵ��</td>
          				<td align="left" width="15"><input type="text" name="empCode" readonly="readonly" style="width:150;text-align:center;background-color:silver;"/></td>
          				<td align="left"><input type="text" name="name" readonly="readonly" style="width:260;background-color:silver;"/></td>
          				
       			 	</tr>
       			 	<tr>
          				<td class="font-field"  align="right">�ѧ�Ѵ��Ժѵԧҹ</td>
          				<td align="left" width="30"><input type="text" name="orgCode" value="" readonly="readonly" style="width: 150;background-color: silver;text-align: center;"  /></td>
          				<td colspan="3" align="left"><input type="text" name="orgDesc" readonly="readonly" style="width:485;background-color: silver;"/></td>
       			 	</tr>
     		 </table>
          </td>
      </tr>
    	&nbsp;
	 	&nbsp;
	  	&nbsp;
	  <tr>
          <td align="center">
				<div style="height:345px;width:800;overflow:auto;">
				 &nbsp;
	  			&nbsp;
	  			&nbsp;
				<table id="table"  bordercolor="#4A4A4A" align="center" style="text-align: center;width:800px" border="1" cellpadding="0" cellspacing="0" >
					<thead style="text-align: center">
						<tr CLASS="TABLEBULE2">
							<th CLASS="TABLEBULE2" style="width:50px" rowspan="2" align="center">ź</th>
							<th CLASS="TABLEBULE2"  rowspan="2" align="center">��������¡��</th>
							<th CLASS="TABLEBULE2" style="width:250px" colspan="3" rowspan="1" align="center"><center>�Ѵ��Ҩ�ҧ</center></th>
							<th CLASS="TABLEBULE2" style="width:300px" colspan="3" rowspan="1" align="center"><center>�Ҵ�ҹ</center></th>
							
						</tr>
						<tr CLASS="TABLEBULE2"  >
							<th CLASS="TABLEBULE2" colspan="2" align="center">��/��͹</th>
							<th CLASS="TABLEBULE2" style="width:75px" align="center">�ӹǹ (%)</th>
							<th CLASS="TABLEBULE2" colspan="2" align="center">��/��͹</th>
							<th CLASS="TABLEBULE2" style="width:100px"align="center">�ӹǹ(�ѹ)</th>							
						</tr>
					</thead>
					<tbody id="dataTable">
					</tbody>	
						<tr id="temprow" style="visibility:hidden;position:absolute">
							<td id="flag"><input type="checkbox" name="chk" /></td>
							<td id="flagPr">
										<select name="type">
												<option value="N" >����</option>
												<option value="A" >��Ѻ��ا��¡���ѡ</option>
												<!-- <option value="R" >��Ѻ��ا��¡���Ѻ���¡�׹</option>
												<option value="B" >���ԡ��Ѻ��ا��¡���Ѻ</option>
												<option value="S" >���ԡ��¡���Ѻ���¡�׹</option>-->
										</select>
							</td>
							<td id="cutSalYear" ><input type="text" name=cutSalYear size="6" maxlength="4" style="text-align:center;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="cutSalMonth" ><select name="cutSalMonth" onchange="bypassCut(this);">
							                        <option value=""  ></option>
													<option value="1" >���Ҥ�</option>
													<option value="2" >����Ҿѹ��</option>
													<option value="3" >�չҤ�</option>
													<option value="4" >����¹</option>
													<option value="5" >����Ҥ�</option>
													<option value="6" >�Զع�¹</option>
													<option value="7" >�á�Ҥ�</option>
													<option value="8" >�ԧ�Ҥ�</option>
													<option value="9" >�ѹ��¹</option>
													<option value="10" >���Ҥ�</option>
													<option value="11" >��Ȩԡ�¹</option>
													<option value="12" >�ѹ�Ҥ�</option>
												</select></td>
							<td id="cutSalPercent" ><input type="text"  name=cutSalPercent  maxlength="5" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="absYear1" ><input type="text" name=absYear1 size="6" maxlength="4" style="text-align:center;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="absMonth1" ><select name="absMonth1" onchange="bypass(this);">
							                        <option value=""  ></option>
													<option value="1">���Ҥ�</option>
													<option value="2">����Ҿѹ��</option>
													<option value="3">�չҤ�</option>
													<option value="4">����¹</option>
													<option value="5">����Ҥ�</option>
													<option value="6">�Զع�¹</option>
													<option value="7">�á�Ҥ�</option>
													<option value="8">�ԧ�Ҥ�</option>
													<option value="9">�ѹ��¹</option>
													<option value="10">���Ҥ�</option>
													<option value="11">��Ȩԡ�¹</option>
													<option value="12">�ѹ�Ҥ�</option>
												</select></td>
							<td id="absDay1" ><input type="text"  name=absDay1  maxlength="6" onchange="checkDay(this);" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
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
							<input type="Button" class=" button " value="ź������" name="deleteData" onclick="removeVisualRow();"/>						
							<input type="Button" class=" button " value="��ŧ" name="confirmData" onclick="onUpdate();"/>
							<input type="Button" class=" button " value="  �͡  " name="back" onclick="backMain();"/>
						</td>
					</tr>
				</table>
</form>
	<form name="backForm" action="security.htm?reqCode=CTWPAYMT009" method="post">
		<input type="hidden" name="orgFromEdit" />
		<input type="hidden" name="orgToEdit" />
		<input type="hidden" name="empCodeFromEdit" />
		<input type="hidden" name="empCodeToEdit" />
		<input type="hidden" name="pageEdit" />
		<input type="hidden" name="orderFromCboEdit" />
		<input type="hidden" name="orderToCboEdit" />
		<input type="hidden" name="mustQuery" />
	</form>
</body>
<script type="text/javascript">
		
</script>
</html>

<SCRIPT LANGUAGE="JavaScript">
<!--
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

//this function removeVisualRow() used page UPDATE ONLY!!!
	
		
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
		var num = 1 + parseInt(DWRUtil.getValue("dataLength"));
		
		var oRows = table.rows;
		if(tdName == null)tdName="flag";
		if(chkName == null)chkName="chk";

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
					

		if(chcCon){
			var answer = confirm("��ͧ���ź������ �������?");
			if( answer ){

				for(i=oRows.length-1;i > num;i--){
					if (oRows[i].cells[tdName].childNodes[0].checked){
							table.deleteRow(i);	
							chDelete = true;		
						}
				}
					
				DWREngine.beginBatch();
					//alert('123435  :'+tab.rows.length);
				    if(tab.rows.length>1){
						for(i=0; i<tab.rows.length; i++){
							row = tab.rows[i];	
							if (chk[i].checked){
									
									rwVinai.keySeq = parseInt(frm.elements["keySeq"][i].value);
									FeeWpayRwVinaiService.deleteRwVinai(rwVinai, {callback:onDeleteCallback});
									chDelete = true;
								}
							
								
						}
					}else{
						if(tab.rows.length==1){
							row = tab.rows[0];	
							if (chk[0].checked){
									
									rwVinai.keySeq = parseInt(frm.elements["keySeq"][0].value);
									FeeWpayRwVinaiService.deleteRwVinai(rwVinai, {callback:onDeleteCallback});
									chDelete = true;
								}
						  
						}	
					}
					
					DWREngine.endBatch();
			}
		}
				
		if(chDelete){
			alert('ź���������º����');
			whenQueryData();
		}
		
	}
	
	function onDeleteCallback()
	{
		whenQueryData();
	}

//-->
</SCRIPT>