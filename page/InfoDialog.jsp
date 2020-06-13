<%@ page language="java" contentType="text/html;charset=TIS-620" %>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="../stylesheet/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
 <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout: fixed">
    <tr>
      <td height="40px">
        <div id="dialog-header" style="width: 100%; height: 100%">
          <img src="../images/info.gif"/>
          <span id="dialog-title" class="bold">Status</span>
        </div>
      </td>
    </tr>
    <tr>
      <td height="100%" valign="top">
		<div id="dialog-body">
    		<span id="message_span">
			<script>var data = window.dialogArguments;</script>
    		<script>
				for(i=0;i<data[0];i++) {
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
          <input type="button" id="ok_button" class="button"  style="width:70px " value=" OK " onClick="window.close()"/> 
        </div>
      </td>
    </tr>
  </table>
</body>
</html>