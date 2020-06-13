package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.hibernate.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.ss.tp.dao.WePnPromoteInstDAO;

import com.ss.tp.dto.WeEmployeeVO;
import com.ss.tp.dto.WePnPromoteInstVO; //import com.ss.tp.dto.WePnPromoteInstReportByOrgVO;
import com.ss.tp.model.WePnPromoteInst;

public class WePnPromoteInstDAOImpl extends HibernateDaoSupport implements
		WePnPromoteInstDAO, Serializable {
	List rwList = new ArrayList();

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public WePnPromoteInstDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public void insertWePnPromoteInst(WePnPromoteInstVO wepnpromoteinstvo)
			throws Exception {
		WePnPromoteInst rw = new WePnPromoteInst();
		try {
			BeanUtils.copyProperties(rw, wepnpromoteinstvo);
			rw.setCreDate(new Date());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().save(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public WePnPromoteInst loadWePnPromoteInst(WePnPromoteInstVO rpVo) {
		WePnPromoteInst rwp = new WePnPromoteInst();
		try {
			// BeanUtils.copyProperties(rwp, rpVo);
			rwp = (WePnPromoteInst) this.getHibernateTemplate().load(
					WePnPromoteInst.class, rpVo.getKeySeq());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rwp;
	}

	public void updateWePnPromoteInst(WePnPromoteInstVO wepnpromoteinstvo)
			throws Exception {
		WePnPromoteInst rw = new WePnPromoteInst();
		try {
			rw = this.loadWePnPromoteInst(wepnpromoteinstvo);
			rw.setEmpCode(wepnpromoteinstvo.getEmpCode());
			rw.setCodeSeq(wepnpromoteinstvo.getCodeSeq());
			rw.setNewCodeSeq(wepnpromoteinstvo.getNewCodeSeq());
			rw.setNewOldDuty(wepnpromoteinstvo.getNewOldDuty());
			rw.setNewDuty(wepnpromoteinstvo.getNewDuty());
			rw.setNewPositionCode(wepnpromoteinstvo.getNewPositionCode());
			rw.setNewLevelCode(wepnpromoteinstvo.getNewLevelCode());
			rw.setNewGworkCode(wepnpromoteinstvo.getNewGworkCode());
			rw.setNewOrgCode(wepnpromoteinstvo.getNewOrgCode());
			rw.setSeqData(wepnpromoteinstvo.getSeqData());
			rw.setUpdBy(wepnpromoteinstvo.getUpdBy());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().update(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteWePnPromoteInst(WePnPromoteInstVO wepnpromoteinstvo)
			throws Exception {
		WePnPromoteInst rw = new WePnPromoteInst();
		try {
			BeanUtils.copyProperties(rw, wepnpromoteinstvo);
			this.getHibernateTemplate().delete(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addList(WePnPromoteInstVO wepnpromoteinstvo) {
		this.rwList.add(wepnpromoteinstvo);
	}

	public void clearList() {
		this.rwList = new ArrayList();
	}

	public void insertAndUpdateWePnPromoteInsts() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				WePnPromoteInstVO wepnpromoteinstvo = (WePnPromoteInstVO) this.rwList
						.get(i);
				if ((wepnpromoteinstvo.getKeySeq() != new Long(0))
						&& wepnpromoteinstvo.getKeySeq() != null
						&& !wepnpromoteinstvo.getKeySeq().equals("")) {
					this.updateWePnPromoteInst(wepnpromoteinstvo);
				} else {
					this.insertWePnPromoteInst(wepnpromoteinstvo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void insertWePnPromoteInsts(List wepnpromoteinstvolist)
			throws Exception {
		WePnPromoteInst rw = new WePnPromoteInst();
		try {
			for (int i = 0; i < wepnpromoteinstvolist.size(); i++) {
				WePnPromoteInstVO wepnpromoteinstvo = (WePnPromoteInstVO) wepnpromoteinstvolist
						.get(i);

				BeanUtils.copyProperties(rw, wepnpromoteinstvo);
				rw.setCreDate(new Date());
				rw.setUpdDate(new Date());
				this.getHibernateTemplate().save(rw);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List findByCriteria(String userId, String evaOuCode, Long evaYear,
			Long evaMonth, String evaEmpCodeFrom, String evaEmpCodeTo) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwPre.yearPn = ");
			criteria.append(evaYear);
		}

		if (evaMonth != null && !evaMonth.equals("")) {
			criteria.append(" and rwPre.monthPn = ");
			criteria.append(evaMonth);
		}

		if (evaEmpCodeFrom != null && !evaEmpCodeFrom.equals("")) {
			criteria.append(" and rwPre.empCode >= '");
			criteria.append(evaEmpCodeFrom);
			criteria.append("' ");
		}

		if (evaEmpCodeTo != null && !evaEmpCodeTo.equals("")) {
			criteria.append(" and rwPre.empCode <= '");
			criteria.append(evaEmpCodeTo);
			criteria.append("' ");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select distinct rwPre.empCode, pnEmp.refDbPreSuff.prefixName, pnEmp.firstName, pnEmp.lastName, pnPos.positionShort, rwPre.codeSeq,pnEmp.adminCode,pnEmp.levelCode ");
		hql.append(" from WePnPromoteInst rwPre , PnEmployee pnEmp , PnPosition pnPos , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwPre.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwPre.ouCode = v.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rwPre.ouCode   = pnEmp.pk.ouCode ");
		hql.append(" and rwPre.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and rwPre.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by rwPre.codeSeq,pnEmp.adminCode,pnEmp.levelCode desc, rwPre.empCode ");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String empCode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
			// String positionShort = (String)r[4];

			WeEmployeeVO ret = new WeEmployeeVO();
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			// ret.setPositionShort(positionShort);

			retList.add(ret);
		}
		return retList;
	}

	public Integer countData(String userId, String evaOuCode, Long evaYear,
			Long evaMonth, Long evaVolume) {
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwPre.yearPn = ");
			criteria.append(evaYear);
		}

		if (evaMonth != null && !evaMonth.equals("")) {
			criteria.append(" and rwPre.monthPn = ");
			criteria.append(evaMonth);
		}

		if (evaVolume != null && !evaVolume.equals("")) {
			criteria.append(" and rwPre.volumeSet = ");
			criteria.append(evaVolume);
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select count(distinct rwPre.empCode) ");
		hql.append(" from WePnPromoteInst rwPre , PnEmployee pnEmp , PnPosition pnPos , PnOrganization pnOrg, VPnOrganizationSecurity v ");
		hql.append(" where rwPre.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwPre.ouCode = v.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = v.pk.codeSeq ");
		hql.append(criteria);
		hql.append(" and rwPre.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rwPre.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and rwPre.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = pnOrg.pk.codeSeq ");

		List empList = this.getSession().createQuery(hql.toString()).list();

		return (Integer) empList.get(0);
	}

	public WeEmployeeVO findByEmpCodeDetail(String empCode, String ouCode) {

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwPre.empCode, ");
		hql.append(" pnEmp.refDbPreSuff.prefixName, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
		hql.append(" rwInc.oldDuty, ");
		hql.append(" pnPos.positionShort, ");
		hql.append("  pnEmp.levelCode, ");
		hql.append(" pnOrg.orgCode, ");
		hql.append(" pnOrg.divShort || ' ' || pnOrg.areaDesc || ' ' || pnOrg.secDesc || ' ' || pnOrg.workDesc ");
		// hql.append(" prEmp.codeSeqWork ");
		hql.append(" from WePnPromoteInst rwPre, ");
		hql.append(" PnEmployee pnEmp, ");
		hql.append(" PnPosition pnPos, ");
		hql.append(" PnOrganization pnOrg ");
		hql.append(" where rwPre.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		hql.append(" and rwPre.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rwPre.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and rwPre.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rwPre.codeSeq = pnOrg.pk.codeSeq ");

		System.out.println("HQL findByEmpCodeDetail ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		WeEmployeeVO ret = new WeEmployeeVO();

		if (empList != null && empList.size() > 0) {
			Object[] r = (Object[]) empList.get(0);
			String empcode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];

			ret.setEmpCode(empcode);
			ret.setName(prefixName + " " + firstName + " " + lastName);

		}

		return ret;
	}

	public List findByEmpCodeList(String userId, String ouCode, Long yearPn,
			Long monthPn, String empCode) {
		StringBuffer hql = new StringBuffer();

		hql.append(" from WePnPromoteInst rw ");
		hql.append(" where rw.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and rw.yearPn = ");
		hql.append(yearPn);
		hql.append(" and rw.monthPn = ");
		hql.append(monthPn);
		hql.append(" and rw.empCode = '");
		hql.append(empCode);
		hql.append(" and rw.creBy = ");
		hql.append(userId);
		hql.append("' ");
		hql.append(" and rw.empCode in ( ");
		hql.append(" 	select pk.empCode ");
		hql.append(" 	from VPnEmployeeSecurity ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + ouCode + "' ");
		hql.append(" ) ");

		System.out.println("HQL findByEmpCodeList ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		if (empList != null && empList.size() > 0) {
			for (int i = 0; i < empList.size(); i++) {
				WePnPromoteInstVO rw = new WePnPromoteInstVO();

				try {
					BeanUtils.copyProperties(rw, empList.get(i));
					retList.add(i, rw);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return retList;
	}

	public boolean canDelete(String ouCode, String year, String month,
			String userId) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select count(*) ");
		sql.append(" from WePnPromoteInst p, VPnEmployeeSecurity e ");
		sql.append(" where p.ouCode = '" + ouCode + "' ");
		sql.append(" and p.yearPn = " + year);
		sql.append(" and p.monthPn = " + month);
		sql.append(" and p.creBy = '" + userId + "' ");
		sql.append(" and e.pk.userId = '" + userId + "' ");
		sql.append(" and p.empCode = e.pk.empCode ");
		sql.append(" and p.ouCode = e.pk.ouCode ");

		List ls = this.getHibernateTemplate().find(sql.toString());

		if (ls != null && ls.size() > 0) {
			Integer i = (Integer) ls.get(0);

			if (i.intValue() > 0)
				return false;
			else
				return true;
		} else
			return true;
	}

	public List findByCriteriaList(String userId, String evaOuCode,
			Long evaYear, Long evaMonth, Long evaVolume, int count,
			int countRecord)

	{
		StringBuffer criteria = new StringBuffer();

		if (evaYear != null && !evaYear.equals("")) {
			criteria.append(" and rwInc.yearPn = ");
			criteria.append(evaYear);
		}

		if (evaMonth != null && !evaMonth.equals("")) {
			criteria.append(" and rwInc.monthPn = ");
			criteria.append(evaMonth);
		}

		if (evaVolume != null && !evaVolume.equals("")) {
			criteria.append(" and rwInc.volumeSet = ");
			criteria.append(evaVolume);
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select rwInc.keySeq, ");
		hql.append(" rwInc.empCode, ");
		hql.append(" pnEmp.refDbPreSuff.prefixName, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
		hql.append(" nvl(pa.adminDesc,' '), ");
		hql.append(" pnEmp.levelCode, ");
		hql.append(" pnPos.positionShort, ");
		hql.append(" pnOrg.pk.codeSeq, ");
		hql.append(" pnOrg.orgCode, ");
		hql.append(" pnOrg.divShort || ' ' || nvl(pnOrg.secDesc,pnOrg.areaDesc) || ' ' ||pnOrg.workDesc, ");
		hql.append(" rwInc.newOldDuty, ");
		hql.append(" rwInc.newPositionCode, ");
		hql.append(" rwInc.newLevelCode, ");
		hql.append(" rwInc.newGworkCode, ");
		hql.append(" rwInc.newDuty, ");
		hql.append(" rwInc.newOrgCode, ");
		hql.append(" pnOrga.divShort || ' ' || nvl(pnOrga.secDesc,pnOrga.areaDesc) || ' ' ||pnOrga.workDesc, ");
		hql.append(" rwInc.seqData, ");
		hql.append(" rwInc.newCodeSeq ");
		hql.append(" from WePnPromoteInst rwInc , PnEmployee pnEmp ,PnAdmin pa,PnPosition pnPos, PnOrganization pnOrg,PnOrganization pnOrga, VPnOrganizationSecurity v ");
		hql.append(" where rwInc.ouCode = '");
		hql.append(evaOuCode);
		hql.append("' ");
		hql.append(criteria);
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and rwInc.creBy = '" + userId + "' ");
		hql.append(" and rwInc.ouCode = v.pk.ouCode ");
		hql.append(" and rwInc.codeSeq = v.pk.codeSeq ");
		hql.append(" and rwInc.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rwInc.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnEmp.pk.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and pnEmp.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" and pnEmp.pk.ouCode = pa.pk.ouCode ");
		hql.append(" and NVL(pnEmp.adminCode,'N') = pa.pk.adminCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and rwInc.ouCode = pnOrga.pk.ouCode ");
		hql.append(" and rwInc.newCodeSeq = pnOrga.pk.codeSeq ");
		hql.append(" and pnOrg.inactive = 'N' ");
		hql.append(" and pnOrga.inactive = 'N' ");
		hql.append(" order by rwInc.seqData ");
		System.out.println("HQL findByCriteriaList ==> " + hql.toString());

		Query q = this.getSession().createQuery(hql.toString());
		List empList = q.setFirstResult(countRecord * count)
				.setMaxResults(countRecord).list();
		List retList = new ArrayList();

		/*
		 * List empList = this.getSession().createQuery(hql.toString()).list();
		 * // List retList = new ArrayList();
		 */

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();

			Long keySeq = (Long) r[0];
			String empCode = (String) r[1];
			String prefixName = (String) r[2];
			String firstName = (String) r[3];
			String lastName = (String) r[4];
			String oldDuty = (String) r[5];
			String oldLevel = (String) r[6];
			String oldPosition = (String) r[7];
			Long codeSeq = (Long) r[8];
			String orgCode = (String) r[9];
			String orgDesc = (String) r[10];
			String newOldDuty = (String) r[11];
			String newPositionCode = (String) r[12];
			String newLevelCode = (String) r[13];
			String newGworkCode = (String) r[14];
			String newDuty = (String) r[15];
			String newOrgCode = (String) r[16];
			String newOrgDesc = (String) r[17];
			Double seqData = (Double) r[18];
			Long newCodeSeq = (Long) r[19];

			int intLevelCode = Integer.parseInt(oldLevel.trim());

			WeEmployeeVO ret = new WeEmployeeVO();
			ret.setKeySeq(keySeq);
			ret.setEmpCode(empCode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setOldDuty(oldDuty);
			ret.setOldPositionShort(oldPosition + " " + intLevelCode);
			ret.setCodeSeq(codeSeq);
			ret.setOrgCode(orgCode);
			ret.setOrgDesc(orgDesc);
			ret.setNewPositionCode(newPositionCode);
			ret.setNewLevelCode(newLevelCode);
			ret.setNewGworkCode(newGworkCode);
			ret.setNewOldDuty(newOldDuty);
			ret.setNewDuty(newDuty);
			ret.setNewOrgCode(newOrgCode);
			ret.setNewOrgDesc(newOrgDesc);
			ret.setSeqData(seqData);
			ret.setNewCodeSeq(newCodeSeq);

			retList.add(ret);
		}

		return retList;
	}

	public List findVolumeBySecurity(String userId, String ouCode)
			throws Exception {

		StringBuffer hql = new StringBuffer(0);
		hql.append("select distinct(a.volumeSet) from WePnPromoteInst a ");
		hql.append(" where ");
		hql.append(" a.ouCode = '" + ouCode + "' ");
		hql.append(" and a.empCode in  ( ");
		hql.append("	 select b.pk.empCode from VPnEmployeeSecurity b ");
		hql.append("	 where b.pk.userId = '" + userId + "' ");
		hql.append("	 and b.pk.ouCode = '" + ouCode + "' ");
		hql.append("	 and a.creBy  = '" + userId + "' ");
		// hql.append(" and a.codeSeq = b.pk.codeSeq ");
		hql.append(" ) ");
		hql.append(" order by a.volumeSet ");

		// System.out.println("*******************");
		List ls = this.getHibernateTemplate().find(hql.toString());
		// System.out.println("*******************");
		// System.out.println("ls : " + ls.size());
		// if( ls != null && ls.size() > 0 ){
		// for (int i=0; i<ls.size(); i++) {
		// PnEmployee element = (PnEmployee) ls.get(i);
		// PnEmployeeVO vo = new PnEmployeeVO();
		// try {
		// BeanUtils.copyProperties(vo, element);
		// BeanUtils.copyProperties(vo, element.getPk());
		// ls.set(i, vo);
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// throw new Exception("can't copy properties : " + e.getMessage());
		// } catch (InvocationTargetException e) {
		// e.printStackTrace();
		// throw new Exception("can't copy properties : " + e.getMessage());
		// }
		// }
		// }
		return ls;
	}

	public List findByCriteriaReport(String userId, String evaOuCode,
			String evaYear, String evaMonth, String evaVolume) {
		List resultList = new ArrayList();
		List returnList = new ArrayList();
		int level;
		int newLevel;
		String sql = "";
		sql = " SELECT DB.PREFIX_NAME||' '||PN.FIRST_NAME||'   '||PN.LAST_NAME ,"
				+ "	PP.POSITION_SHORT  , "
				+ "   PN.LEVEL_CODE , "
				+ "   NVL(PO.SEC_DESC,PO.AREA_DESC)  , "
				+ "   NVL(PO.WORK_DESC,NULL)  , "
				+ "   PO.DIV_SHORT ,"
				+ "   DECODE(WE.NEW_OLD_DUTY,NULL,NVL(PA.ADMIN_DESC,NULL),'a',NULL,NVL(PAT.ADMIN_DESC,NULL))  , "
				+ "   PN.EMP_CODE ||'/'||PN.GWORK_CODE , "
				+ "   trim(PN.ACCOUNT), "
				+ "   PN_DESC.GET_POSITION_SHORT(WE.OU_CODE,NVL(WE.NEW_GWORK_CODE,PN.GWORK_CODE),NVL(WE.NEW_POSITION_CODE,PN.POSITION_CODE)) ,"
				+ "   NVL(WE.NEW_LEVEL_CODE,PN.LEVEL_CODE) ,"
				+ "   PN.EMP_CODE||'/'||NVL(WE.NEW_GWORK_CODE,PN.GWORK_CODE) ,"
				+ "   NVL(PN_DESC.GET_SEC_DESC(WE.OU_CODE,WE.NEW_CODE_SEQ),PN_DESC.GET_AREA_DESC(WE.OU_CODE,WE.NEW_CODE_SEQ)),"
				+ "   NVL(PN_DESC.GET_WORK_DESC(WE.OU_CODE,WE.NEW_CODE_SEQ),NULL) ,"
				+ "   NVL(PN_DESC.GET_DIV_SHORT_DESC(WE.OU_CODE,WE.NEW_CODE_SEQ),NULL),"
				+ "   trim( NVL(PN_DESC.GET_ADMIN_DESC_TTT(WE.OU_CODE,WE.NEW_DUTY),NULL)) "
				+ "   FROM DB_PRE_SUFF DB, "
				+ "  PN_EMPLOYEE PN, "
				+ "  WE_PN_PROMOTE_INST WE, "
				+ "  PN_ORGANIZATION PO, "
				+ "  PN_POSITION PP, "
				+ "  PN_ADMIN PA, "
				+ "  PN_ADMIN_TT PAT "
				+ "  WHERE DB.PRE_SUFF_CODE = PN.PRE_NAME "
				+ "   AND WE.OU_CODE = '001'"
				+ "   AND WE.CRE_BY ='"
				+ userId
				+ "'"
				+ "   AND PN.OU_CODE = WE.OU_CODE "
				+ "  AND PN.OU_CODE = PO.OU_CODE "
				+ "  AND WE.OU_CODE = PAT.OU_CODE "
				+ "  AND PN.OU_CODE = PP.OU_CODE "
				+ "  AND PN.OU_CODE = PA.OU_CODE "
				+ "  AND PN.EMP_CODE = WE.EMP_CODE "
				+ "  AND PN.EMP_STATUS = 'B' "
				+ "   AND PN.GWORK_CODE = PP.GWORK_CODE "
				+ "  AND PN.POSITION_CODE = PP.POSITION_CODE "
				+ "  AND NVL(PN.ADMIN_CODE,'N')= PA.ADMIN_CODE "
				+ "   AND NVL(WE.NEW_OLD_DUTY,'N') = PAT.ADMIN_CODE"
				+ "  AND PN.CODE_SEQ = PO.CODE_SEQ"
				+ "   AND PP.INACTIVE = 'N'"
				+ "   AND PO.INACTIVE = 'N' "
				+ "   AND PA.INACTIVE = 'N' " + "   AND PAT.INACTIVE = 'N'";
		if (!evaYear.equals("")) {
			sql += "\n and  we.year_pn ='" + evaYear + "'";
		}
		if (!evaMonth.equals("")) {
			sql += "\n and  we.month_pn  = '" + evaMonth + "'";
		}
		if (!evaVolume.equals("")) {
			sql += "\n and  we.volume_set  = '" + evaVolume + "'";
		}
		sql += "\n " + "   ORDER BY WE.SEQ_DATA ";

		resultList = this.jdbcTemplate.queryForList(sql);

		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);

			String name = (String) map.getValue(0);
			String oldPosition = (String) map.getValue(1);
			String oldLevel = (String) map.getValue(2);
			String oldSec = (String) map.getValue(3);
			String oldWork = (String) map.getValue(4);
			String oldDiv = (String) map.getValue(5);
			String oldDuty = (String) map.getValue(6);
			String empCode = (String) map.getValue(7);
			String account = (String) map.getValue(8);
			String newPosition = (String) map.getValue(9);
			String newLevelCode = (String) map.getValue(10);
			String newGworkCode = (String) map.getValue(11);
			String newSec = (String) map.getValue(12);
			String newWork = (String) map.getValue(13);
			String newDiv = (String) map.getValue(14);
			String newDuty = (String) map.getValue(15);

			newLevel = Integer.parseInt(newLevelCode);
			level = Integer.parseInt(oldLevel);
			WeEmployeeVO vo = new WeEmployeeVO();

			vo.setName(name);
			vo.setOldPosition(oldPosition + " " + level);
			vo.setOldSec(oldSec);
			vo.setOldWork(oldWork);
			vo.setOldDiv(oldDiv);
			vo.setOldDuty(oldDuty);
			vo.setEmpCode(empCode);
			vo.setAccount(account);
			vo.setNewPosition(newPosition + " " + newLevel);
			vo.setNewGworkCode(newGworkCode);
			vo.setNewSec(newSec);
			vo.setNewWork(newWork);
			vo.setNewDiv(newDiv);
			vo.setNewDuty(newDuty);

			returnList.add(vo);

		}
		return returnList;
	}

	public List findByCriteriaPromoteLevelReport(String userId,
			String evaOuCode, Long evaYear, Long evaMonth, Long evaVolume) {
		List resultList = new ArrayList();
		List returnList = new ArrayList();
		String sql = "";
		sql = "  SELECT DB.PREFIX_NAME||' '||PN.FIRST_NAME||'  '||PN.LAST_NAME NAME, "
				+ "	PP.POSITION_SHORT||' '||TO_NUMBER(PN.LEVEL_CODE) , "
				+ "	PO.SEC_DESC , "
				+ "	PO.WORK_DESC , "
				+ "	PO.DIV_SHORT , "
				+ "	DECODE(WE.NEW_OLD_DUTY,NULL,PA.ADMIN_DESC,PATT.ADMIN_DESC) , "
				+ "	PN.EMP_CODE||'/'||PN.GWORK_CODE EMP_CODE, "
				+ "	PN.ACCOUNT, "
				+ "	PN_DESC.GET_POSITION_SHORT(WE.OU_CODE,NVL(WE.NEW_GWORK_CODE,PN.GWORK_CODE),NVL(WE.NEW_POSITION_CODE,PN.POSITION_CODE))||' '||TO_NUMBER(NVL(WE.NEW_LEVEL_CODE,PN.LEVEL_CODE)), "
				+ "	POR.SEC_DESC, "
				+ "	POR.WORK_DESC, "
				+ "	POR.DIV_SHORT, "
				+ "	PAT.ADMIN_DESC, "
				+ "	PN.EMP_CODE||'/'||NVL(WE.NEW_GWORK_CODE,PN.GWORK_CODE) "
				+ "	FROM DB_PRE_SUFF DB, "
				+ "	PN_EMPLOYEE PN, "
				+ "	WE_PN_PROMOTE_INST WE, "
				+ "	PN_ORGANIZATION PO, "
				+ "	PN_ORGANIZATION POR, "
				+ "	PN_POSITION PP, "
				+ "	PN_ADMIN PA, "
				+ "	PN_ADMIN_TT PAT, "
				+ "	PN_ADMIN_TT PATT "
				+ "	WHERE DB.PRE_SUFF_CODE = PN.PRE_NAME "
				+ "	AND PN.OU_CODE = WE.OU_CODE "
				+ "	AND PN.OU_CODE = PO.OU_CODE "
				+ "	AND WE.OU_CODE = POR.OU_CODE "
				+ "	AND WE.OU_CODE = PAT.OU_CODE "
				+ "	AND WE.OU_CODE = PATT.OU_CODE "
				+ "	AND PN.OU_CODE = PP.OU_CODE "
				+ "	AND PN.OU_CODE = PA.OU_CODE "
				+ "	AND PN.EMP_CODE = WE.EMP_CODE "
				+ "	AND PN.EMP_STATUS = 'B' "
				+ "	AND PN.GWORK_CODE = PP.GWORK_CODE "
				+ "	AND PN.POSITION_CODE = PP.POSITION_CODE "
				+ "	AND NVL(PN.ADMIN_CODE,'N')= PA.ADMIN_CODE "
				+ "	AND NVL(WE.NEW_DUTY,'N') = PAT.ADMIN_CODE(+) "
				+ "	AND NVL(WE.NEW_OLD_DUTY,'N') = PATT.ADMIN_CODE(+) "
				+ "	AND PN.CODE_SEQ = PO.CODE_SEQ "
				+ "	AND WE.NEW_CODE_SEQ = POR.CODE_SEQ "
				+ "	AND PP.INACTIVE = 'N' "
				+ "	AND PO.INACTIVE = 'N' "
				+ "	AND POR.INACTIVE = 'N' "
				+ "	AND PA.INACTIVE = 'N' "
				+ "	AND PAT.INACTIVE = 'N' " + "	ORDER BY WE.SEQ_DATA ";

		resultList = this.jdbcTemplate.queryForList(sql);
		for (int i = 0; i < resultList.size(); i++) {
			ListOrderedMap map = (ListOrderedMap) resultList.get(i);
			String empName = (String) map.getValue(0);
			String oldPosition = (String) map.getValue(1);
			String oldLevel = (String) map.getValue(2);
			String oldSec = (String) map.getValue(3);
			String oldWork = (String) map.getValue(4);
			String oldDiv = (String) map.getValue(5);
			String oldDuty = (String) map.getValue(6);
			String empCode = (String) map.getValue(7);
			String accout = (String) map.getValue(8);
			String newPosition = (String) map.getValue(9);
			String newLevel = (String) map.getValue(10);
			String newSec = (String) map.getValue(11);
			String newWork = (String) map.getValue(12);
			String newDiv = (String) map.getValue(13);
			String newDuty = (String) map.getValue(14);
			String newEmpCode = (String) map.getValue(15);

			WeEmployeeVO vo = new WeEmployeeVO();

			vo.setName(empName);
			vo.setOldPositionShort(oldPosition);

			System.out.println(vo.getName());

			returnList.add(vo);

		}
		return returnList;
	}
}