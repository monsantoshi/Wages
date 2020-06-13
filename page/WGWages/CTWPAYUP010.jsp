<%@ page language="java" contentType="text/html;charset=TIS-620" %>

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
<title>บันทึกรายการหักโดยได้รับค่าจ้างไม่เต็มเดือน</title>
<!-- Include -->
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<!-- Javascript Script File -->
<script type="text/javascript" src="dwr/interface/FeeWpayRwVinai2Service.js"></script>
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
<script type="text/javascript">
<!--

	var myUpdate = new Array();
	var count = 0;
	
	function whenQueryData()
	{
		DWRUtil.useLoadingMessage("Loading ...");
		FeeWpayRwVinai2Service.findByEmpCodeDetail('<%=empID%>','<%=ouCodeEdit%>','<%=yearEdit%>','<%=periodEdit%>',{callback:whenQueryDetailHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		FeeWpayRwVinai2Service.findByEmpCodeList('<%=userId%>','<%=ouCodeEdit%>','<%=yearEdit%>','<%=periodEdit%>','<%=empID%>',{callback:whenQueryListDataHandler,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
		
		
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
		function(data) { return writeTextYearWork("yearWork",data.yearWork,4,"center",data.keySeq);},
		function(data) { return writeSelectMonth("periodWork",data.periodWork,data.keySeq);},
		function(data) { return writeText("decDay",data.decDay,6,"center",data.keySeq);},
		function(data) { 
						if(data.remark != null && data.remark != ''){
							return writeHidden("remark",data.remark,200,"left","keySeq",data.keySeq);
						}else{
							return writeHidden("remark","",200,"left","keySeq",data.keySeq);
						}		
						}
	];
	
	function whenQueryListDataHandler(data){
		$("dataLength").value = data.length;
		if(data.length > 0)
		{
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
		var temp1 = '';
		var temp2 = '';
		var temp3 = '';
		var temp4 = '';
		var temp5 = '';
		if(emp=='A'){
			temp1 = 'selected';
		} else if(emp=='R'){
			temp2 = 'selected';		
		}
		if(<%=confirm%>){
		return "<div align='center' style='background-color:#CCCCCC;'><select name='"+inname+"' disabled='true' style='background-color:transparent;color:#000000'><option value='A' "+temp1+" >เพิ่มวันทำงาน</option>"+
												"<option value='R' "+temp2+">ลดวันทำงาน</option>" +
												"<!--<option value='R' "+temp3+">ปรับปรุงรายการรับเรียกคืน</option>" +
												"<option value='B' "+temp4+">ตกเบิกปรับปรุงรายการรับ</option>" +
												"<option value='S' "+temp5+">ตกเบิกรายการรับเรียกคืน</option>--></select></div>";
		}else {
		return "<div align='center' ><select onchange='addListUpdate("+key+")' name='"+inname+"'><option value='A' "+temp1+" >เพิ่มวันทำงาน</option>"+
												"<option value='R' "+temp2+">ลดวันทำงาน</option>" +
												"<!--<option value='R' "+temp3+">ปรับปรุงรายการรับเรียกคืน</option>" +
												"<option value='B' "+temp4+">ตกเบิกปรับปรุงรายการรับ</option>" +
												"<option value='S' "+temp5+">ตกเบิกรายการรับเรียกคืน</option>--></select></div>";
		
		
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
		return "<div align='center' ><select onchange='addListUpdate("+key+");bypassRowUpdate("+key+");' name='"+inname+"'>" +
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
	
	function writeText(inname,emp,maxlength,textalign,key)
	{
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}else{
			return "<div align='center' ><input type='text' onchange='addListUpdate("+key+");checkDayRowUpdate("+key+");' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}
	}
	
	function writeTextYearWork(inname,emp,maxlength,textalign,key)
	{
		//กรณี display not edit
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' size='6' maxlength='"+maxlength+"' style='text-align:"+textalign+";background-color:transparent;' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}else{
			return "<div align='center' ><input type='text' onchange='addListUpdate("+key+")' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' size='6' style='text-align:"+textalign+";' onkeyup='if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);'/></div>";
		}
	}
	
	function writeHidden(inname,emp,maxlength,textalign,nameHide,empHide)
	{
		if(<%=confirm%>){
			return "<div align='center' style='background-color:#CCCCCC;'><input type='text' readonly='true' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%;background-color:transparent;' /><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		}else{
			return "<div align='center'><input type='text' onchange='addListUpdate("+empHide+")' name = '"+inname+"' value='"+emp+"' align='center' maxlength='"+maxlength+"' style='text-align:"+textalign+";width:100%' /><input type='hidden' name = '"+nameHide+"' value='"+empHide+"'  /></div>";
		}
	}
	
	
	var rwVinai2 = {keySeq:null, ouCode:null, yearPr:null, periodPr:null,empCode:null,confirmFlag:null,
					yearWork:null,periodWork:null,codeSeq:null,decDay:null,remark:null,
					flagPr:null,updBy:null,updDate:null,creBy:null,creDate:null};
					
	var allRowUpdate = 0;
	
	function onUpdate()
	{
		var table = document.getElementById("table");
		var aRows = table.rows;
		var num = 1 + parseInt(DWRUtil.getValue("dataLength"));
		
		var tab = $('dataTable');
		var update ;
		var row;
		var empList=[];
		var frm   = document.forms["formEdit"];
		
		var keySeq 		= frm.elements["keySeq"];
		var flagPr 		= frm.elements["flagPr"];
		var yearWork 	= frm.elements["yearWork"];
		var periodWork 	= frm.elements["periodWork"];
		var decDay 		= frm.elements["decDay"];
		var remark 		= frm.elements["remark"];
		
		DWREngine.beginBatch();
        
		for(var i=0; i<tab.rows.length; i++)
		{  
	     var periodWWork = parseInt(periodWork[i].value);
	     var yearWWork = yearWork[i].value;
		 var dec = decDay[i].value;
		 var section = '<%=sectionEdit%>';
		 var yearEdit = '<%=yearEdit%>';
		 var monthWork = parseInt(section.substr(0, 2));	
		 if (yearWWork==yearEdit && periodWWork <= monthWork) {
			update = null;
			row = tab.rows[i];
			rwVinai2.keySeq = parseInt(keySeq[i].value);
			
			if(tab.rows.length == 1){
				rwVinai2.flagPr = flagPr.value;
				//rwVinai2.periodWork  = periodWork.value;
			}else{
				rwVinai2.flagPr = flagPr[i].value;
				//rwVinai2.periodWork  = periodWork[i].value;
			}
			rwVinai2.periodWork  = periodWork[i].value;
			//if (yearWork[i].value != ''){
			//	rwVinai2.yearWork  = yearWork[i].value;
			//}
			//else{
			//	rwVinai2.yearWork  = null;
			//}
			rwVinai2.yearWork  = '<%=yearEdit%>';
			if (decDay[i].value !=''){
				//rwVinai2.decDay  = parseInt(decDay[i].value);
				rwVinai2.decDay  = decDay[i].value;
			}
			else{
				rwVinai2.decDay  = null;
			}
			
			if (remark[i].value !=''){
				rwVinai2.remark  = remark[i].value;
			}
			else{
				rwVinai2.remark  = null;
			}
			rwVinai2.updBy = '<%=userId%>';
			
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
						FeeWpayRwVinai2Service.addList(rwVinai2, false, {callback:onInsertCallback,errorHandler:function(message) { alert('เกิดข้อผิดพลาดเกี่ยวกับการติดต่อสื่อสาร');}});
					else
						FeeWpayRwVinai2Service.addList(rwVinai2, false);
					
				}else{
					if( allRowUpdate == myUpdate.length )
							FeeWpayRwVinai2Service.addList(rwVinai2, true, {callback:ClearData,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้');}});
						else
							FeeWpayRwVinai2Service.addList(rwVinai2, false);
					
				}
			}
		  }
		  else {
		     alert("ปีต้องเป็นปัจจุบัน เดือนต้องไม่เกินเดือนปัจจุบัน ระบบยกเลิกการบันทึก");
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
		var num = 1 + parseInt(DWRUtil.getValue("dataLength"));
		
		var oRows = table.rows;
		if(tdName == null)tdName="flag";
		if(chkName == null)chkName="chk";
		
		DWREngine.beginBatch();
			for(var i=oRows.length-1;i > num;i--)
			{
				rwVinai2.keySeq = null;
				rwVinai2.ouCode = '<%=ouCodeEdit%>';
				rwVinai2.yearPr = '<%=yearEdit%>';
				rwVinai2.periodPr = '<%=periodEdit%>';
				rwVinai2.empCode = '<%=empID%>';
				rwVinai2.codeSeq = $("codeSeq").value;
				rwVinai2.yearWork = oRows[i].cells["yearWork"].childNodes[0].value;
				rwVinai2.periodWork = oRows[i].cells["periodWork"].childNodes[0].value;
				rwVinai2.decDay = oRows[i].cells["decDay"].childNodes[0].value;
				rwVinai2.remark = oRows[i].cells["remark"].childNodes[0].value;
				rwVinai2.flagPr= oRows[i].cells["flagPr"].childNodes[0].value;
				rwVinai2.confirmFlag = 'N';
				rwVinai2.updBy = '<%=userId%>';
				rwVinai2.creBy = '<%=userId%>';
				rwVinai2.creDatw = getDateFromFormat(<%=date%>,"dd/MM/yyyy");
				//RwVinai2Service.addList(rwVinai2, {callback:onInsertCallback});
				
				if( i == (num + 1) )
					FeeWpayRwVinai2Service.addList(rwVinai2, true, {callback:ClearData,errorHandler:function(message) { alert('ไม่สามารถบันทึกได้');}});
				else
					FeeWpayRwVinai2Service.addList(rwVinai2, false);
			}
		DWREngine.endBatch();	
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
		document.getElementById("mustQuery").value = 'true';
		frm.submit();
	}
	
	function RevertData(){
		
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
	
		var month = oRows[rowToCompare].cells["periodWork"].childNodes[0].value;
		var day = oRows[rowToCompare].cells["decDay"].childNodes[0].value;
		
		
		
		if(day != null && day != '' && day != 0){
		
			switch (month) {
				  case "1":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "2":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "3":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "4":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "5":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "6":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "7":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "8":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "9":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "10":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "11":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
				    }
				    break;
				  case "12":
				    if(day > 5){
				    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
				    	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
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
	
		
	 	
	 	oRows[rowToCompare].cells["decDay"].childNodes[0].value = '';
	 	
	 	
	 	
	 
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
			var monthCheck 	= frm.elements["periodWork"];
			var dayCheck 		= frm.elements["decDay"];
		
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
						    if(day > 5){
						    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "2":
						    if(day > 5){
						    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "3":
						    if(day > 5){
						    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "4":
						    if(day > 5){
						    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "5":
						    if(day > 5){
						    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "6":
						    if(day > 5){
						    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "7":
						    if(day > 5){
						    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "8":
						    if(day > 5){
						    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "9":
						    if(day > 5){
						    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "10":
						    if(day > 5){
						    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "11":
						    if(day > 5){
						    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
						    	dayCheck[rowModify].value = '';
						    }
						    break;
						  case "12":
						    if(day > 5){
						    	alert('จำนวนวันปรับปรุงไม่เกิน 5 วัน');
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
			var monthCheck 	= frm.elements["periodWork"];
			var dayCheck 		= frm.elements["decDay"];
			
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
-->
</script>
</head>

<body>
<form name="formEdit"   action="security.htm?reqCode=CTWPAYMT010" method="post">
<input type="hidden" name="dataLength">
<input type="hidden" name="codeSeq">
<input type="hidden" name="period">
<table width="100%">
	<tr>
		<td class="font-head">
			[ CTWPAYUP010 ] บันทึกรายการเพิ่ม ลด วันทำงานที่ผิดพลาด
		</td>
	</tr>
</table>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">

      <tr>
          <td  align="center">
				<table width="750" border="1" cellpadding="1" bordercolor="#6699CC" cellspacing="1"  align="center">
        			<tr>
          				<td class="font-field"  align="right" width="100">ประจำปี</td>
          				<td align="left"><input type="text" name="year" value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/></td>
          				<td colspan="3" class="font-field" align="left" width="250">
          					&nbsp;&nbsp;งวด&nbsp;&nbsp;
          					<input type="text" name="section" value="" readonly="readonly" style="width:70;text-align:center;background-color:silver;"/>
          				</td>
        			</tr>
        			<tr>
         				<td class="font-field"  align="right">เลขประจำตัว</td>
          				<td align="left" width="15"><input type="text" name="empCode" readonly="readonly" style="width:150;text-align:center;background-color:silver;"/></td>
          				<td align="left"><input type="text" name="name" readonly="readonly" style="width:260;background-color:silver;"/></td>
         				
       			 	</tr>
       			 	<tr>
          				<td class="font-field"  align="right">สังกัดปฎิบัติงาน</td>
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
				<div style="height:345px;width:750;overflow:auto;">
				 &nbsp;
	  			&nbsp;
	  			&nbsp;
				<table id="table"  bordercolor="#4A4A4A" align="center" style="text-align: center;width:700px" border="1" cellpadding="0" cellspacing="0" >
					<thead style="text-align: center">
						<tr CLASS="TABLEBULE2" style="height:30px;">
							<th CLASS="TABLEBULE2" style="width:50px" align="center">ลบ</th>
							<th CLASS="TABLEBULE2" style="width:100px" align="center">ประเภทวันทำงาน</th>
							<th CLASS="TABLEBULE2" colspan="2" align="center">ปี/เดือนที่ทำงาน</th>
							<th CLASS="TABLEBULE2" style="width:100px" align="center"><center>จำนวนวัน</center></th>
							<th CLASS="TABLEBULE2" style="width:250px" align="center">หมายเหตุ</th>
						</tr>						
					</thead>
					<tbody id="dataTable">
					</tbody>
											
						<tr id="temprow" style="visibility:hidden;position:absolute">
							<td id="flag"><input type="checkbox" name="chk" /></td>
							<td id="flagPr" width="50">
								<select name="type">
										<option value="A" >เพิ่มวันทำงาน</option>
										<option value="R" >ลดวันทำงาน</option>
										<!-- <option value="R" >ปรับปรุงรายการรับเรียกคืน</option>
										<option value="B" >ตกเบิกปรับปรุงรายการรับ</option>
										<option value="S" >ตกเบิกรายการรับเรียกคืน</option> -->
								</select>
							</td>
							<td id="yearWork"><input type="text" value="<%=yearEdit%>" name="yearWork" size="6" maxlength="4" align="center" style="text-align:center;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="periodWork" align="center" ><select name="periodWork" style="width:80;" onchange="bypass(this);">
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
							<td id="decDay"><input type="text"  name="decDay"  maxlength="6" onchange="checkDay(this);" style="text-align:right;width:100%;" onkeyup="if(this.value < 0) this.value = '';if(isNaN(Number(this.value))) this.value = this.value.substring(0,this.value.length - 1);"/></td>
							<td id="remark"><input type="text"  name="remark"  maxlength="100" align="center" style="text-align:left;width:100%;" /><input type="hidden"  name="keySeq" /></td>
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
							<input type="Button" class=" button " value="ตกลง" name="confirmData" onclick="onUpdate();"/>
							<input type="Button" class=" button " value="  ออก  " name="back" onclick="backMain();"/>
						</td>
					</tr>
				</table>
</form>
	<form name="backForm" action="security.htm?reqCode=CTWPAYMT010" method="post">
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
		//alert(oRows.length+" : "+num);
		
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
			var answer = confirm("ต้องการลบข้อมูล หรือไม่?");
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
									
									//alert('BB'+rowDelete);
									rwVinai2.keySeq = parseInt(frm.elements["keySeq"][i].value);
									FeeWpayRwVinai2Service.deleteRwVinai2(rwVinai2, {callback:onDeleteCallback});
									chDelete = true;
								}
							
								
						}
					}else{
						if(tab.rows.length==1){
							row = tab.rows[0];	
							if (chk[0].checked){
									
									//alert('CC'+rowDelete);
									rwVinai2.keySeq = parseInt(frm.elements["keySeq"][0].value);
									FeeWpayRwVinai2Service.deleteRwVinai2(rwVinai2, {callback:onDeleteCallback});
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
		whenQueryData();
	}

//-->
</SCRIPT>