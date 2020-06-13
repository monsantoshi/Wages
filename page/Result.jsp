<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<c:if test="${! empty processResult && processResult.type!='ERROR'}">
	<table cellspacing="0" width="100%">
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td style="background-color:#99CCFF;padding:3px;">
			<c:forEach items="${processResult.results}" var="result" varStatus="idx">
				<img src="images/check.gif">
				<c:out value="${result.message}" escapeXml="false"/><br>
			</c:forEach>
		</td>
	</tr>
	</table>
</c:if>