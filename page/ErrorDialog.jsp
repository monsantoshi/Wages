<%@ page language="java" contentType="text/html;charset=TIS-620" %>
<html>
<head>
<title>Error</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="../stylesheet/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" type="text/javascript" src="../script/popup_util.js"></script>
</head>
<body>
 <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed">
    <tr>
      <td height="40px">
        <div id="dialog-header" style="width: 100%; height: 100%">
          <img src="../images/error.gif"/>
          <span id="dialog-title" class="bold">Exception</span>
        </div>
      </td>
    </tr>
    <tr>
      <td height="100%" valign="top">
		<div id="dialog-body" style="overflow-y:auto; height:140px;">
    		<span id="message_span">
			<script>var data = window.dialogArguments;</script>
    		<script>
				for(i=0;i<data[0]*1;i++) {
					document.write("<br />"+data[i+1]);
				}
			</script>
    		</span>
  		</div>
      </td>
    </tr>
    <tr>
      <td height="40px" align="center" valign="middle">
        <div id="dialog-footer" style="width: 100%; height: 100%">
          <input type="button" id="ok_button" class="button"  style="width:70px " value="Ok" onClick="window.close()"/> 
        </div>
      </td>
    </tr>
  </table>
</body>
</html>