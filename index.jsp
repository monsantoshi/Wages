<%@ page language="java" contentType="text/html;charset=TIS-620"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>

<HTML>
<HEAD>
<!--<TITLE>� �к��ҹ����ѡ�Ҿ�Һ��   �</TITLE>-->
<!-- <TITLE>� �к��ҹ�Թ��͹  �</TITLE>-->
<!-- <TITLE>�к��ҹ��Ѻ��Ҩ�ҧ�١��ҧ��Шӻ� </TITLE>-->
<!-- <TITLE>�к��ҹ�������ѹ������� ��ѡ�ҹ �١��ҧ��Ш� ����١��ҧ</TITLE>-->
<!-- <TITLE>�к��ҹ�觵��</TITLE> -->
<TITLE>�к��ҹ��Ҩ�ҧ �١��ҧ </TITLE>
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
		alert('User ���� Password ���١��ͧ ��سҡ�͡����');
		loginForm.userPassword.select();
	}else {
		loginForm.checkchPss.value = 'true';
		loginForm.submit();
	}

}
function SubmitForm(){   
         loginForm.action = 'security.htm?reqCode=doLogin';
	     loginForm.submit();
	    // alert(' !!! ��سҤ�������Ż�Ѻ�ز� ������к��زԷ���Ѻ \n �ء�ó������Ҩ��ա������¹�ŧ�Թ��͹������� !!! ');
	    // alert(' !!! ����� 1 �.�. 2557 ¡��ԡ����Ţ���ѭ�ո�Ҥ�ü�ҹ�к��ҹ�Թ��͹ \n ��黯ԺѵԵ���ѹ�֡��� ��� ��(��)/1667 ��. 20 ��.�. 2557 !!! ');
       }
function backMenu(){
	loginForm.checkchPss.value = '';
	loginForm.submit();
}
function savaPassword(){
	if(loginForm.newPassd.value != "" && loginForm.newPassd.value == loginForm.connewPassd.value){
		  if(!checkPassword(loginForm.newPassd.value)) {
			  alert("��سҵ�����ʼ�ҹ�ѧ��� . \n\n* 1. �դ������ 8-12 ��� \n* 2. ��Сͺ���µ���ѡ���ѧ������ҧ���� 1 ��� \n* 3. ��Сͺ���µ���Ţ���ҧ���� 1 ��� \n* 4. ��Сͺ�����ѡ��о���� @#$% ���ҧ���� 1 ��� ");
		      loginForm.newPassd.focus();
		     // return false;
		    }
		  else {
			  SuUserService.updatePassword(DWRUtil.getValue("userId"),DWRUtil.getValue("newPassd"),{callback:whenSavaPassword});
		  }
		//SuUserService.updatePassword(DWRUtil.getValue("userId"),DWRUtil.getValue("newPassd"),{callback:whenSavaPassword});
	}else{
		alert('New Password  ���ç�Ѻ Confirm New Confirm Password \n ��سҡ�͡���� �ա����');
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
		alert('������º����');
 		backMenu();
 	}else{
 		alert('�������ö����� ��سҡ�͡����');
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
      alert("��سҵ�����ʼ�ҹ�ѧ��� . \n\n* 1. �դ������ 8-12 ��� \n* 2. ��Сͺ���µ���ѡ���ѧ������ҧ���� 1 ��� \n* 3. ��Сͺ���µ���Ţ���ҧ���� 1 ��� \n* 4. ��Сͺ�����ѡ��о���� @#$% ���ҧ���� 1 ��� ");
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

//var scrl = "�  �к��ҹ����ѡ�Ҿ�Һ�� �Ѵ����  ��. (ʤ.) �� 3766 � ";
//var scrl = "�  �к��ҹ�Թ��͹ �������觸�Ҥ�� ��. ver. 2.6 ";
//var scrl = "�  �к��ҹ��Ѻ��Ҩ�ҧ�١��ҧ  ";
//var scrl = "#  �к��ҹ�������ѹ������� ��ѡ�ҹ �١��ҧ��Ш� ����١��ҧ   ��. : ��. 3391-2 ";
var scrl = "#�к��ҹ��Ҩ�ҧ�١��ҧ #";
//var scrl = "#  �к��ҹ�觵�駾�ѡ�ҹ/�١��ҧ   ��.(ʤ.) ver. 1.5";
//var scrl = "#  �к��ҹ�ͺ��èؾ�ѡ�ҹ/�١��ҧ   ��.(ʤ.) ver. 1";
//var scrl = "%% �к��ҹ�����źؤ��   %%";

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
								������к�&nbsp;&nbsp;
							</td>
							<td>
								<input type="text" name="userId" value="<%=userId %>" onblur="findOuCode();" />
							</td>
						</tr>
						<tr>
							<td class="ntext" height="28px" valign="bottom">
								���ʼ�ҹ&nbsp;&nbsp;
							</td>
							<td>
								<input type="password" name="userPassword" value="">
							</td>
						</tr>
						<tr>
							<td class="ntext">
								���ʺ���ѷ&nbsp;&nbsp;
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
							    <input type="submit" name="Submit" value="����к� "  class="stext" onclick="SubmitForm();return false;" >							    
								<input type="button" name="cancel" value=" ¡��ԡ " onclick="window.close();"  class="stext">
								<input type="button" name="chpssd" value="����¹���ʼ�ҹ"  class="stext" style="width:120px" onclick="changePassword();">
							</td>
							
						</tr>
		</table>
	 
										
		</div>

	<%}else{ %>
				<div style="position:absolute; left: 300px; top: 250px;" >
					<table width="70%" border="0" vspace="0" hspace="0" cellspacing="0" cellpadding="0" >
						<tr>
							<td width="10%" class="ntext">
								������к�&nbsp;&nbsp;
							</td>
							<td>
								<input type="text" name="userId" value="<%=userId %>" readonly="readonly" />
							</td>
						</tr>
						<tr>
						    <td class="ntext" height="28px" valign="bottom">
								���ʼ�ҹ&nbsp;&nbsp;
							</td>
							<td>
								<input type="password" name="userPassword" value="<%=userPassword %>" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td class="ntext">
								���ʺ���ѷ&nbsp;&nbsp;
							</td>
							<td height="28px">
								<input id="ouCode" type="text" name="ouCode" value="<%=ouCode %>" readonly="readonly">
							</td>
							
						</tr>
						<tr>
							<td colspan="2" class="ntext">
								<br>
								��س�����¹ ���ʼ�ҹ ���� �� ¡��ԡ ��Ѻ���˹�� ����к�
							</td>

						</tr>
						<tr>
							<td width="32%" class="ntext">
								���ʼ�ҹ����&nbsp;&nbsp;
							</td>
							<td>
								<input type="password" name="newPassd" value=""/>
							</td>
						</tr>
						<tr>
							<td class="ntext" height="28px" valign="bottom">
								�׹�ѹ���ʼ�ҹ����&nbsp;&nbsp;
							</td>
							<td>
								<input type="password" name="connewPassd" value="" >
							</td>
						</tr>
						<tr>
							<td align="right"><b>&nbsp;</b>
							</td>
							<td>
								<input type="button" name="Submit" value=" ��ŧ "  class="stext" onclick="savaPassword();">
								<input type="button" name="cancel" value=" ¡��ԡ "  class="stext" onclick="backMenu();">
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
    alert("�������ö ��͡�Թ �������к��� \n ��س���� ������к� , ���ʼ�ҹ �����ա����");
   
</c:if>
<c:if test="${processResult.type=='ULOCK'}">
    alert("��ҹ������ʼԴ�ú����������� �Դ��� 3380,3766");
   
   
</c:if>
</script>
 <% request.getSession().removeAttribute("processResult"); %>
</c:if>
