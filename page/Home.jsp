<%@ page contentType="text/html; charset=TIS-620" %>

<%@ page import="com.ss.tp.security.UserInfo" %>

<%@ page import="com.ss.tp.model.SuMenu" %>

<%@ page import="com.ss.tp.service.SuMenuService" %>
<%@ page import="com.ss.tp.service.impl.SuMenuServiceImpl" %>

<%@ page import="com.ss.tp.dao.SuMenuDAO" %>
<%@ page import="com.ss.tp.dao.impl.SuMenuDAOImpl" %>

<%@ page import="java.util.List, java.util.ArrayList" %>

<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>


<%!

	String items; 
	 
	private static String chgNullVal(String str1, String str2)	{
		if (str1 == null) {
			return str2;
		}
		else	{
			return str1;
		}
	}
%>

<HTML>
<HEAD>
<title>Tree Menu</title>

<style>
	a, A:link, a:visited, a:active, A:hover
		{color: #000000; text-decoration: none; font-family: Tahoma, Verdana; font-size: 12px}
</style>
</HEAD>
<script language="JavaScript" src="script/tree.js"></script>
<script language="JavaScript" src="script/tree_tpl.js"></script>

<%		
		UserInfo uf =  (UserInfo)request.getSession().getAttribute("UserLogin");
		String sessionTree = "TreeItems"+uf.getUserId();
		if (session.getAttribute(sessionTree) == null)	{
			System.out.println("++++++++++++NO SESSION+++++++++++++"+sessionTree);
			ApplicationContext ctx;
			ctx = WebApplicationContextUtils.getWebApplicationContext(application);
			SuMenuService  smService = (SuMenuService)ctx.getBean("suMenuService");

			List suMenuLv1 = new ArrayList();
			List suMenuLv2 = new ArrayList();
			List suMenuLv3 = new ArrayList();

			suMenuLv1 = smService.findSuMenu(uf.getUserGroup());
			suMenuLv2.addAll(suMenuLv1);
			suMenuLv3.addAll(suMenuLv1);

			boolean bl = false;

			String mId = "";	

			for (int m=0; m<suMenuLv1.size(); m++)	{
				SuMenu suMenu1 = (SuMenu)suMenuLv1.get(m);	
				if (suMenu1.getMainMenu()== null && suMenu1.getLinkName() == null)	{//root
					items = "[" + "' " + suMenu1.getMenuName() + "', null,";
					mId = suMenu1.getMenuId();
					bl = true;
				}	
				else	{
					if ((suMenu1.getLinkName() == null) && chgNullVal(suMenu1.getMainMenu(),"").equals(mId))	{
						items = items + "[" + "' " + suMenu1.getMenuName() + "'," + suMenu1.getLinkName() + ",";		//Level1
						for (int n=0; n<suMenuLv1.size(); n++)	{
							SuMenu suMenu2 = (SuMenu) suMenuLv2.get(n);
							if (chgNullVal(suMenu2.getMainMenu(),"").equals(suMenu1.getMenuId()))	{
								items = items + "[" + "' " + suMenu2.getMenuName() + "'," + suMenu2.getLinkName() + ",";		//Level2
								for (int i=0; i<suMenuLv1.size(); i++)	{
									SuMenu suMenu3 = (SuMenu) suMenuLv3.get(i);
									if (chgNullVal(suMenu3.getMainMenu(),"").equals(suMenu2.getMenuId()))	{           
										items = items + "[" + "' " + suMenu3.getMenuName() + "', 'security.htm?reqCode=" + suMenu3.getLinkName() + "'],";		//Level3
									}
								}

								items = items + "],";
							}
						}
						items = items + "],";
					}
				}		
			}
			
			if (bl == true)	{
				items = items +	"]"	;	
			}
			session.setAttribute(sessionTree, items);
		}
		else	{
			System.out.println("++++++++++++SESSION+++++++++++++"+sessionTree);
			items = (String)session.getAttribute(sessionTree);
		}

		//out.println("item=" + items);
		
%>

<body  bottommargin="0" topmargin="0" leftmargin="0" rightmargin="0" marginheight="0" marginwidth="0" bgcolor="white" >
<table border="0" width="100%" height="100%">
<tr>
<td  background="images/menu-crop.jpg">

<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" onclick="expand_or_collapse_level('menu_tree');"><b><img src="images/music_button.jpg" border="0" ></b></a> | 
			<a href="#" onclick="expand_or_collapse_level('menu_tree',true);"><b><img src="images/music_collapse.jpg" border="0" ></b></a>
<br><br>
	<div style="height:450px;overflow:auto;">
	<table cellspacing="0" cellpadding="0" align="left" width="100%" >
	  <tr>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	  	<td width="100px">&nbsp;</td>
	    <td>
			<table cellpadding="5" cellspacing="0" border="0" width="100%">
			<tr>
				<td>
					<script language="JavaScript">
						<!--//	
							var TREE_ITEMS = [
								<%=items%>
							];	
							menu_tree = new tree (TREE_ITEMS, TREE_TPL);
							expand_or_collapse_level('menu_tree');
						//-->
					</script>
				</td>
			</tr>
			</table>
		</td>
	  </tr>
	</table>
	</div>
</td>
</tr>
</table>
</BODY>
</HTML>
