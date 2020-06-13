<%@ page language="java" contentType="text/html;charset=TIS-620" pageEncoding="tis-620"%>
<%@ taglib prefix="tiles" uri="/WEB-INF/tld/struts-tiles-el.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<tiles:importAttribute ignore="true"/>
<jsp:useBean id="now" class="java.util.Date" />
<HTML>
<HEAD>
<TITLE>Carrefour</TITLE>
<link rel="stylesheet" href="stylesheet/style.css" type="text/css">
<link rel="stylesheet" href="stylesheet/inputStyle.css" type="text/css">
<script language="javascript" src="script/tree_menu.js"></script>
<script language="JavaScript" type="text/javascript" src="script/pnpScript.js"></script>
<script language="JavaScript" type="text/javascript" src="script/gridScript.js"></script>
</HEAD>

<BODY onload="setFocus();">
	<!--content-->
	<tiles:insert name="content"/>	
</BODY>
</HTML>