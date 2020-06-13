<%@ page language="java" contentType="text/html;charset=TIS-620"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<HTML>
<HEAD>
<!--<TITLE>฿ ระบบงานค่ารักษาพยาบาล   ฿</TITLE>-->
<!-- <TITLE>฿ ระบบงานเงินเดือน  ฿</TITLE>-->
<!-- <TITLE>ระบบงานปรับค่าจ้างลูกจ้างประจำปี </TITLE>-->
<!-- <TITLE>ระบบงานข้อมูลวันลามาสาย พนักงาน ลูกจ้างประจำ และลูกจ้าง</TITLE>-->
<!-- <TITLE>ระบบงานแต่งตั้ง</TITLE> -->
<TITLE>ระบบงานค่าจ้าง ลูกจ้าง </TITLE>
<link rel="stylesheet" href="default.css" type="text/css">
<style>
A          {color : navy;  text-decoration : none;font-weight:bold;}
A:Visited  {color : navy; }
A:Active   {color : navy; }
A:Hover    {color : navy; text-decoration:underline;}


.linkLargeFont{  
	font-size:15pt;
	font-weight:bold;
}
.ntext	{ 
    color: red;
	font-size:x-small;
	font-weight:bold;
    vertical-align:right; text-align: middle; 
}
.stext {	 font-family: "MS sans serif",arial, geneva, helvetica; 
			border-style : solid;	
			border-color : #45789F;
			border-width : 2px;
		}
</style>
<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>

<link rel="stylesheet" href="stylesheet/style.css" type="text/css">
<link rel="stylesheet" href="stylesheet/inputStyle.css" type="text/css">
<script language="javascript" src="script/tree_menu.js"></script>
<script language="JavaScript" type="text/javascript" src="script/pnpScript.js"></script>
<script type="text/javascript" src="dwr/interface/SuUserService.js"></script>
<script type="text/javascript" src="dwr/interface/PnEmployeeService.js"></script>




</HEAD>

<script type="text/javascript">



function findOuCode(){	
		if(loginForm.userId.value!=''){
				SuUserService.findByCriteria(DWRUtil.getValue("userId"),{callback:whenfindOuCode});
			}

	}
function whenfindOuCode(data){
			var  temp = document.all.ouCode.options.length;
	    	for (i=0; i< temp; i++){
	    		document.all.ouCode.remove(0);
	    		
	    	}
	
			for(i=0;i<data.length;i++){
				
				var objOption = document.createElement("OPTION");
				objOption.value = data[i].ouCode;
				objOption.text = data[i].ouCode;
				document.all.ouCode.add(objOption);
			}
}

function changePassword(){
		SuUserService.checkPasswd(DWRUtil.getValue("userId"),DWRUtil.getValue("userPassword"),{callback:whenChangePassword});
}

function whenChangePassword(data){
  
	if(data==null){
		alert('User หรือ Password ไม่ถูกต้อง กรุณากรอกใหม่');
		loginForm.userPassword.select();
	}else {
		loginForm.checkchPss.value = 'true';
		loginForm.submit();
	}

}
function SubmitForm(){   
         loginForm.action = 'security.htm?reqCode=doLogin';
	     loginForm.submit();
	    // alert(' !!! กรุณาคีย์ข้อมูลปรับวุฒิ พร้อมระบุวุฒิที่ปรับ \n ทุกกรณีไม่ว่าจะมีการเปลี่ยนแปลงเงินเดือนหรือไม่ !!! ');
	    // alert(' !!! ตั้งแต่ 1 ก.ค. 2557 ยกเลิกแก้ไขเลขที่บัญชีธนาคารผ่านระบบงานเงินเดือน \n ให้ปฏิบัติตามบันทึกที่ ปณท ทบ(ขบ)/1667 ลว. 20 มิ.ย. 2557 !!! ');
       }
function backMenu(){
	loginForm.checkchPss.value = '';
	loginForm.submit();
}
function savaPassword(){
	if(loginForm.newPassd.value != "" && loginForm.newPassd.value == loginForm.connewPassd.value){
		  if(!checkPassword(loginForm.newPassd.value)) {
			  alert("กรุณาตั้งรหัสผ่านดังนี้ . \n\n* 1. มีความยาว 8-12 ตัว \n* 2. ประกอบด้วยตัวอักษรอังกฤษอย่างน้อย 1 ตัว \n* 3. ประกอบด้วยตัวเลขอย่างน้อย 1 ตัว \n* 4. ประกอบด้วยอักขระพิเศษ @#$% อย่างน้อย 1 ตัว ");
		      loginForm.newPassd.focus();
		     // return false;
		    }
		  else {
			  SuUserService.updatePassword(DWRUtil.getValue("userId"),DWRUtil.getValue("newPassd"),{callback:whenSavaPassword});
		  }
		//SuUserService.updatePassword(DWRUtil.getValue("userId"),DWRUtil.getValue("newPassd"),{callback:whenSavaPassword});
	}else{
		alert('New Password  ไม่ตรงกับ Confirm New Confirm Password \n กรุณากรอกใหม่ อีกครั้ง');
		loginForm.newPassd.value = '';
		loginForm.connewPassd.value = '';
		loginForm.newPassd.focus();
	}
}

function lockUserPassword(){
  alert("!!!! lock !!!!");
	
}
function whenSavaPassword(data){ 
	if(data=='true'){
		alert('แก้ไขเรียบร้อย');
 		backMenu();
 	}else{
 		alert('ไม่สามารถแก้ไขได้ กรุณากรอกใหม่');
 	}

}

function checkPassword(str)
{
  // at least one number, one lowercase and one uppercase letter
  // at least six characters that are letters, numbers or the underscore
  var re = /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[@#$%]).{7,12}$/;
  //"^((?=.{8,}$)(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).*|(?=.{8,}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!\u0022#$%&'()*+,./:;<=>?@[\]\^_`{|}~-]).*)"
  //var re =/^((?=.{8,}$)(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).*|(?=.{8,}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!\u0022#$%&'()*+,./:;<=>?@[\]\^_`{|}~-]).*$/
  //var re =((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{5,20});
  //var re = (?=^.{5,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[a-zA-Z]).*$;
  return re.test(str);
}

function checkForm(form)
{
 
  if(form.newPassd.value != "" && form.newPassd.value == form.connewPassd.value) {
    if(!checkPassword(form.newPassd.value)) {
      alert("กรุณาตั้งรหัสผ่านดังนี้ . \n\n* 1. มีความยาว 8-12 ตัว \n* 2. ประกอบด้วยตัวอักษรอังกฤษอย่างน้อย 1 ตัว \n* 3. ประกอบด้วยตัวเลขอย่างน้อย 1 ตัว \n* 4. ประกอบด้วยอักขระพิเศษ @#$% อย่างน้อย 1 ตัว ");
      form.newPassd.focus();
      return false;
    }
  } else {
    alert("Error: Please check that you've entered and confirmed your password!");
    form.newPassd.focus();
    return false;
  }
  return true;
}

//var scrl = "฿  ระบบงานค่ารักษาพยาบาล จัดทำโดย  ทบ. (สค.) โทร 3766 ฿ ";
//var scrl = "฿  ระบบงานเงินเดือน ข้อมูลส่งธนาคาร ทบ. ver. 2.6 ";
//var scrl = "฿  ระบบงานปรับค่าจ้างลูกจ้าง  ";
//var scrl = "#  ระบบงานข้อมูลวันลามาสาย พนักงาน ลูกจ้างประจำ และลูกจ้าง   ทบ. : โทร. 3391-2 ";
var scrl = "#ระบบงานค่าจ้างลูกจ้าง #";
//var scrl = "#  ระบบงานแต่งตั้งพนักงาน/ลูกจ้าง   ทบ.(สค.) ver. 1.5";
//var scrl = "#  ระบบงานสอบบรรจุพนักงาน/ลูกจ้าง   ทบ.(สค.) ver. 1";
//var scrl = "%% ระบบงานข้อมูลบุคคล   %%";

function scrlsts() {
 scrl = scrl.substring(1, scrl.length) + scrl.substring(0, 1);
 document.title = scrl;
 setTimeout("scrlsts()", 300);
 //document.getElementById("userId").focus();
 //setFocus();
 }
function setFocus()
{
	scrlsts()	
	document.getElementById("userId").focus();	
	
}




</script>
<%
		String checkchPss = request.getParameter("checkchPss")==null?"":request.getParameter("checkchPss");
		String userId = request.getParameter("userId")==null?"":request.getParameter("userId");
		String userPassword = request.getParameter("userPassword")==null?"":request.getParameter("userPassword");
		String ouCode = request.getParameter("ouCode")==null?"":request.getParameter("ouCode");
%>
<BODY onload="setFocus();">
<!--<BODY onLoad="scrlsts()">-->
<center>
<form method="POST" action="" name="loginForm" >
<input type="hidden" value="" name="checkchPss">
<table width="1024" height="768" border="0" vspace="0" hspace="0" cellspacing="0" cellpadding="0">
	<tr valign="top" height="15"  bgcolor=""><%-- header --%>
		<td background="images/footer.png" align="center" valign="bottom">
			&nbsp;
		</td>
	</tr>
    
    <tr >
		<td align="center">
			<%-- Login --%>
	<% if(checkchPss.equals("")){ %>	
	<div style="position:absolute; left: 550px; top: 300px;" >
					<table width="70%" border="0" vspace="0" hspace="0" cellspacing="0" cellpadding="0" >
						<tr>
							<td width="25%" class="ntext">
								ผู้ใช้ระบบ&nbsp;&nbsp;
							</td>
							<td>
								<input type="text" name="userId" value="<%=userId %>" onblur="findOuCode();" />
							</td>
						</tr>
						<tr>
							<td class="ntext" height="28px" valign="bottom">
								รหัสผ่าน&nbsp;&nbsp;
							</td>
							<td>
								<input type="password" name="userPassword" value="">
							</td>
						</tr>
						<tr>
							<td class="ntext">
								รหัสบริษัท&nbsp;&nbsp;
							</td>
							<td height="28px">
								<select  id="ouCode" name="ouCode" style="width:100;">
								</select>
							</td>
						</tr>
	
  	
						
						<tr>
							<td align="right"><b>&nbsp;</b>
							</td>
						
							<td>		
							    <input type="submit" name="Submit" value="เข้าระบบ "  class="stext" onclick="SubmitForm();return false;" >							    
								<input type="button" name="cancel" value=" ยกเลิก " onclick="window.close();"  class="stext">
								<input type="button" name="chpssd" value="เปลี่ยนรหัสผ่าน"  class="stext" style="width:120px" onclick="changePassword();">
							</td>
							
						</tr>
		</table>
	 
										
		</div>

	<%}else{ %>
				<div style="position:absolute; left: 300px; top: 250px;" >
					<table width="70%" border="0" vspace="0" hspace="0" cellspacing="0" cellpadding="0" >
						<tr>
							<td width="10%" class="ntext">
								ผู้ใช้ระบบ&nbsp;&nbsp;
							</td>
							<td>
								<input type="text" name="userId" value="<%=userId %>" readonly="readonly" />
							</td>
						</tr>
						<tr>
						    <td class="ntext" height="28px" valign="bottom">
								รหัสผ่าน&nbsp;&nbsp;
							</td>
							<td>
								<input type="password" name="userPassword" value="<%=userPassword %>" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td class="ntext">
								รหัสบริษัท&nbsp;&nbsp;
							</td>
							<td height="28px">
								<input id="ouCode" type="text" name="ouCode" value="<%=ouCode %>" readonly="readonly">
							</td>
							
						</tr>
						<tr>
							<td colspan="2" class="ntext">
								<br>
								กรุณาเปลี่ยน รหัสผ่าน หรือ กด ยกเลิก กลับสู่หน้า เข้าระบบ
							</td>

						</tr>
						<tr>
							<td width="32%" class="ntext">
								รหัสผ่านใหม่&nbsp;&nbsp;
							</td>
							<td>
								<input type="password" name="newPassd" value=""/>
							</td>
						</tr>
						<tr>
							<td class="ntext" height="28px" valign="bottom">
								ยืนยันรหัสผ่านใหม่&nbsp;&nbsp;
							</td>
							<td>
								<input type="password" name="connewPassd" value="" >
							</td>
						</tr>
						<tr>
							<td align="right"><b>&nbsp;</b>
							</td>
							<td>
								<input type="button" name="Submit" value=" ตกลง "  class="stext" onclick="savaPassword();">
								<input type="button" name="cancel" value=" ยกเลิก "  class="stext" onclick="backMenu();">
							</td>
							
						</tr>				
					</table>		
				</div>
			<%} %>
			<%-- endLogin --%>
			<%-- Body --%>
			<table width="100%" height="100%" border="0" vspace="0" hspace="0" cellspacing="0" cellpadding="0" >
			<%--
				<tr>
					<td  rowspan="2" width="70%" background="images/login.png" align="center" >&nbsp;</td>
					<td  background=""  align="center">&nbsp;</td>
				</tr>
				--%>
				<tr>
					<td background="images/Login.jpg" >&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
	<%-- footer --%>
	<tr height="10">
		<td background="images/footer.png" align="center" valign="top">
			<%--<hr width="90%">--%>&nbsp;
		</td>		
	</tr>
</table>
</form>
</center>
</BODY>
</HTML>
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->

</script>
<c:if test="${! empty processResult}">
<script type="text/javascript">

<c:if test="${processResult.type=='ERROR'}">
    alert("ไม่สามารถ ล็อกอิน เข้าสู่ระบบได้ \n กรุณาใส่ ผู้ใช้ระบบ , รหัสผ่าน ใหม่อีกครั้ง");
   
</c:if>
<c:if test="${processResult.type=='ULOCK'}">
    alert("ท่านใส่รหัสผิดครบสามครั้งแล้ว ติดต่อ 3380,3766");
   
   
</c:if>
</script>
 <% request.getSession().removeAttribute("processResult"); %>
</c:if>
