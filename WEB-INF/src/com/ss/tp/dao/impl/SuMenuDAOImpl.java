/*
 * Created on 9 ?.?. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.SuMenuDAO;
import com.ss.tp.model.SuMenu;

/**
 * @author Kiet
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SuMenuDAOImpl extends HibernateDaoSupport implements SuMenuDAO,
		Serializable {
	public List findSuMenu(String UserGroup) {
		List listTemp = new ArrayList();
		List listDetail = new ArrayList();

		String sql = "select A.menuId,A.menuName,A.mainMenu,A.linkName from SuMenu A , SuUserMenu B ";
		sql = sql + "where A.menuId LIKE 'WEB%' ";
		sql = sql + "and A.menuId = B.pk.menuId ";
		sql = sql + "and B.pk.userGroup = '" + UserGroup + "'";
		sql = sql + "and B.useFlag = 'Y'";
		sql = sql + " order by A.menuId, main_menu";

		try {
			Session ss = this.getSession();
			System.out.println(sql);
			Query qq = ss.createQuery(sql);

			listDetail = qq.list();

			for (Iterator iter = listDetail.iterator(); iter.hasNext();) {
				Object[] ob = (Object[]) iter.next();
				SuMenu sm = new SuMenu();
				sm.setMenuId((String) ob[0]);
				sm.setMenuName((String) ob[1]);
				sm.setMainMenu((String) ob[2]);
				sm.setLinkName((String) ob[3]);

				listTemp.add(sm);
			}

			System.out.println("\n size List is ==" + listDetail.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listTemp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ss.tp.dao.SuMenuDAO#findSuMenu()
	 */
	public List findSuMenu() {
		// TODO Auto-generated method stub
		return null;
	}

}
