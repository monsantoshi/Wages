<%@ page language="java" contentType="text/html;charset=TIS-620" pageEncoding="tis-620"%>
<%@ taglib prefix="tiles" uri="/WEB-INF/tld/struts-tiles-el.tld" %>
<%@ taglib prefix="h" uri="/WEB-INF/tld/struts-html-el.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="trail" uri="http://www.osjava.org/taglibs/trail-1.0" %>
<%--
@param menuId
@param form
@param searchForm
@param columns
@param rows
@param columnString
@param tabs
--%>
<tiles:importAttribute ignore="true"/>
<c:set var="user" value="${PNP_USER_INFO}" />
<table cellspacing="0" cellpadding="0" width="100%">
	<!-- content -->	
	<tr id="wrapper-ContentInIframe">
		<td class="center">
			<!--field set -->
			<br>
			<table id="fieldset" cellpadding="0" cellspacing="0" border="0" class="full_on_print" width="97%" style="margin-bottom:15px">
				<!-- header -->
				<tr id="head-field">
					<td width="11px" class="hide_on_print">
						<img src="images/img_topBar1.gif">
					</td>
					<td width="60%" id="head-text">
						<tiles:insert name="module" ignore="true" /> - <tiles:insert name="progName" ignore="true" />
					</td>
					<td width="40%" id="head-text" align="right">
				<%-- 	[<tiles:insert name="progId" ignore="true" />] --%>
					</td>
					<td width="3px" class="hide_on_print">
						<img src="images/img_topBar3.gif">
					</td>
				</tr>
				<!--body -->
				<tr height="448px" valign="top">
					<td colspan="4" id="cont-field">
					<table width="100%" >
					<tr>
						<td>
							<tiles:insert name="content-1" ignore="true" />
						</td>
					</tr>
					<tr>
						<td>
							<tiles:insert name="content-2" ignore="true" />
						</td>
					</tr>
					<tr>
						<td>
							<tiles:insert name="content-3" ignore="true" />
						</td>
					</tr>
					<tr>
						<td>
							<tiles:insert name="content-4" ignore="true" />
						</td>
					</tr>

					</table>
					</td>
				</tr>
			</table>
		</td> 
	</tr>
</table>