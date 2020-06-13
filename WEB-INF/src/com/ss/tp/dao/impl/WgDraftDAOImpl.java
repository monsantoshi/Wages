package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.hibernate.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.WgDraftDAO;
import com.ss.tp.dto.WgDraftReportVO;
import com.ss.tp.dto.WgDraftVO;
import com.ss.tp.dto.WgPrEmployeeVO;
import com.ss.tp.model.WgDraft;
import com.ss.tp.model.WgDraftPk;

public class WgDraftDAOImpl extends HibernateDaoSupport implements WgDraftDAO,
		Serializable {
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insertWgDraft(WgDraftVO wgDraftVO) throws Exception {
		WgDraft wg = new WgDraft();
		WgDraftPk wgPk = new WgDraftPk();
		try {
			BeanUtils.copyProperties(wgPk, wgDraftVO);
			wg.setPk(wgPk);
			BeanUtils.copyProperties(wg, wgDraftVO);
			wg.setCreDate(new Date());
			wg.setUpdDate(new Date());
			this.getHibernateTemplate().save(wg);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public WgDraft loadWgDraft(WgDraftVO wgDraftVO) {
		WgDraft wg = new WgDraft();
		WgDraftPk wgPk = new WgDraftPk();
		try {
			BeanUtils.copyProperties(wgPk, wgDraftVO);
			wg = (WgDraft) this.getHibernateTemplate()
					.load(WgDraft.class, wgPk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wg;
	}

	public void updateWgDraft(WgDraftVO wgDraftVO) throws Exception {
		WgDraft wg = new WgDraft();
		try {
			wg = this.loadWgDraft(wgDraftVO);

			System.out.println("Draft Status : " + wg.getDraftStatus());

			if (wgDraftVO.getDraftStatus().equals("Y")) {
				if (wgDraftVO.getHisSeq().intValue() > 0)
					wg.setHisSeq(wgDraftVO.getHisSeq());
				else
					wg.setHisSeq(null);
				wg.setScore(wgDraftVO.getScore());
				wg.setPerDraft(wgDraftVO.getPerDraft());
				wg.setDraftStatus(wgDraftVO.getDraftStatus());
				wg.setPunishStatus(wgDraftVO.getPunishStatus());
				wg.setNoDraft(null);
				wg.setRemark(wgDraftVO.getRemark());
			} else {
				wg.setHisSeq(null);
				// wg.setScore(null);
				wg.setScore(wgDraftVO.getScore());
				wg.setPerDraft(null);
				wg.setDraftStatus(wgDraftVO.getDraftStatus());
				wg.setPunishStatus(wgDraftVO.getPunishStatus());
				wg.setNoDraft(wgDraftVO.getNoDraft());
				// wg.setRemark(wgDraftVO.getRemark());
				wg.setRemark(null);
			}
			wg.setUpdBy(wgDraftVO.getUpdBy());
			wg.setUpdDate(new Date());
			this.getHibernateTemplate().update(wg);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteWgDraft(WgDraftVO wgDraftVO) throws Exception {
		WgDraft wg = new WgDraft();
		WgDraftPk wgPk = new WgDraftPk();
		try {
			BeanUtils.copyProperties(wgPk, wgDraftVO);
			wg.setPk(wgPk);
			BeanUtils.copyProperties(wg, wgDraftVO);
			this.getHibernateTemplate().delete(wg);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void confirmWgDraft(String ouCode, String userId, Integer year)
			throws Exception {
		List empList = new ArrayList();
		empList = this
				.getSession()
				.createQuery(
						" from WgDraft e " + " where e.pk.ouCode ='" + ouCode
								+ "' " + " and e.pk.year = " + year
								+ " and e.codeSeq in ( "
								+ " select v.pk.codeSeq"
								+ " from VPnOrganizationSecurity v"
								+ " where  v.pk.ouCode = '" + ouCode + "' "
								+ " and  v.pk.userId = '" + userId + "' "
								+ " )" + " order by e.pk.empCode").list();
		for (Iterator iter = empList.iterator(); iter.hasNext();) {
			WgDraft emp = (WgDraft) iter.next();
			try {
				emp.setConfirmFlag("Y");
				emp.setUpdDate(new Date());
				emp.setUpdBy(userId);
				this.getHibernateTemplate().update(emp);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
	}

	public String findMaxYear() throws Exception {
		try {
			List ls = this.getHibernateTemplate().find(
					"select max(w.pk.year) from WgDraft w");

			if (ls != null && ls.size() > 0)
				return ls.get(0).toString();
			else
				return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public List findAllYear() throws Exception {
		try {
			List ls = this.getHibernateTemplate().find(
					"select distinct w.pk.year from WgDraft w");

			return ls;

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public List findEmployee(String year, String ouCode, String userId)
			throws Exception {
		List rs = new ArrayList();

		StringBuffer hql = new StringBuffer(0);
		hql.append(" select w.pk.empCode ");
		hql.append(" from WgDraft w, VPnOrganizationSecurity v ");
		hql.append(" where w.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");
		hql.append(" and w.pk.year = " + year);
		hql.append(" and w.pk.ouCode = v.pk.ouCode ");
		hql.append(" and w.codeSeq = v.pk.codeSeq ");
		hql.append(" order by w.pk.empCode ");

		List ls = this.getHibernateTemplate().find(hql.toString());

		for (int i = 0; ls != null && i < ls.size(); i++) {
			WgPrEmployeeVO vo = new WgPrEmployeeVO();
			String empCode = (String) ls.get(i);
			vo.setEmpCode(empCode);

			rs.add(vo);
		}
		return rs;
	}

	public List findFromEmployee(String year, String ouCode, String userId,
			String empCode) throws Exception {
		List rs = new ArrayList();

		StringBuffer hql = new StringBuffer(0);
		hql.append(" select w.pk.empCode ");
		hql.append(" from WgDraft w, VPnOrganizationSecurity v ");
		hql.append(" where w.pk.ouCode = '" + ouCode + "' ");
		hql.append(" and v.pk.userId = '" + userId + "' ");

		if (year != null && !year.trim().equals(""))
			hql.append(" and w.pk.year = " + year);

		hql.append(" and w.pk.empCode >= '" + empCode + "' ");
		hql.append(" and w.pk.ouCode = v.pk.ouCode ");
		hql.append(" and w.codeSeq = v.pk.codeSeq ");
		hql.append(" order by w.pk.empCode ");

		List ls = this.getHibernateTemplate().find(hql.toString());
		for (int i = 0; ls != null && i < ls.size(); i++) {
			WgPrEmployeeVO vo = new WgPrEmployeeVO();
			String tmpEmpCode = (String) ls.get(i);
			vo.setEmpCode(tmpEmpCode);

			rs.add(vo);
		}
		return rs;
	}

	public String checkConfirmWgDraft(String ouCode, String userId, Integer year) {
		List empList = new ArrayList();
		empList = this
				.getSession()
				.createQuery(
						" select distinct e.pk.ouCode " + " from WgDraft e "
								+ " where e.pk.ouCode ='" + ouCode + "' "
								+ " and e.pk.year = " + year
								+ " and e.codeSeq in ( "
								+ " select v.pk.codeSeq"
								+ " from VPnOrganizationSecurity v"
								+ " where  v.pk.ouCode = '" + ouCode + "' "
								+ " and  v.pk.userId = '" + userId + "' "
								+ " )" + " and e.confirmFlag = 'Y' ").list();
		if (empList == null || empList.size() == 0) {
			return null;
		} else {
			return (String) empList.get(0);
		}
	}

	public List findWgDraftByCriteria(String year, String ouCode,
			String userId, String fCodeSeq, String tCodeSeq, String fEmpCode,
			String tEmpCode, int count, int countRecord) throws Exception {
		try {
			StringBuffer hql = new StringBuffer(0);

			hql.append(" from WgDraft w ");
			hql.append(" where w.pk.ouCode = '" + ouCode + "' ");
			hql.append(" and w.pk.year = " + year);

			if (fEmpCode != null && !fEmpCode.equals(""))
				hql.append(" and w.pk.empCode >= '" + fEmpCode + "' ");

			if (tEmpCode != null && !tEmpCode.equals(""))
				hql.append(" and w.pk.empCode <= '" + tEmpCode + "' ");

			hql.append(" and w.codeSeq in ( ");
			hql.append("					select v.pk.codeSeq ");
			hql.append("					from VPnOrganizationSecurity v ");
			hql.append("					where v.pk.userId = '" + userId + "' ");

			if (fCodeSeq != null && !fCodeSeq.equals(""))
				hql.append(" 				and v.orgCode >= '" + fCodeSeq + "' ");

			if (tCodeSeq != null && !tCodeSeq.equals(""))
				hql.append("				and v.orgCode <= '" + tCodeSeq + "' ");

			hql.append("					and v.pk.ouCode = w.pk.ouCode ");
			hql.append(" ) ");
			hql.append(" order by  w.refPnOrganization.orgCode,w.pk.empCode ");

			Query q = this.getSession().createQuery(hql.toString());
			List ls = q.setFirstResult(countRecord * count)
					.setMaxResults(countRecord).list();

			System.out.println(ls.size());

			for (int i = 0; ls != null && ls.size() > i; i++) {
				// System.out.println("i : " + i);
				WgDraft draft = (WgDraft) ls.get(i);
				WgDraftVO vo = new WgDraftVO();
				BeanUtils.copyProperties(vo, draft);
				BeanUtils.copyProperties(vo, draft.getPk());

				ls.set(i, vo);
			}

			return ls;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public Integer countWgDraftByCriteria(String year, String ouCode,
			String userId, String fCodeSeq, String tCodeSeq, String fEmpCode,
			String tEmpCode) throws Exception {
		try {
			StringBuffer hql = new StringBuffer(0);

			hql.append(" select count(*) ");
			hql.append(" from WgDraft w ");
			hql.append(" where w.pk.ouCode = '" + ouCode + "' ");
			hql.append(" and w.pk.year = " + year);

			if (fEmpCode != null && !fEmpCode.equals(""))
				hql.append(" and w.pk.empCode >= '" + fEmpCode + "' ");

			if (tEmpCode != null && !tEmpCode.equals(""))
				hql.append(" and w.pk.empCode <= '" + tEmpCode + "' ");

			hql.append(" and w.codeSeq in ( ");
			hql.append("	select v.pk.codeSeq ");
			hql.append("	from VPnOrganizationSecurity v ");
			hql.append("	where v.pk.userId = '" + userId + "' ");

			if (fCodeSeq != null && !fCodeSeq.equals(""))
				hql.append(" and v.orgCode >= '" + fCodeSeq + "' ");

			if (tCodeSeq != null && !tCodeSeq.equals(""))
				hql.append(" and v.orgCode <= '" + tCodeSeq + "' ");

			hql.append("	and v.pk.ouCode = w.pk.ouCode ");
			hql.append(" ) ");
			hql.append(" order by w.pk.empCode ");

			List ls = this.getHibernateTemplate().find(hql.toString());

			if (ls != null && ls.size() > 0)
				return (Integer) ls.get(0);
			else
				return new Integer(0);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public WgDraftVO findWgDraft(String ouCode, String year, String empCode)
			throws Exception {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append(" select w.pk.empCode, w.idate, pre.prefixName, w.firstName, ");
			hql.append(" w.lastName, o.areaDesc, o.secDesc, o.workDesc, w.draftStatus, ");
			hql.append(" w.punishStatus, w.score, w.hisSeq, w.perDraft, w.remark, ");
			hql.append(" w.noDraft ");
			hql.append(" from WgDraft w, PnOrganization o, DbPreSuff pre ");
			hql.append(" where w.pk.ouCode = '" + ouCode + "' ");
			hql.append(" and w.pk.year = " + year);
			hql.append(" and w.pk.empCode = '" + empCode + "' ");
			hql.append(" and w.pk.ouCode = o.pk.ouCode ");
			hql.append(" and w.codeSeq = o.pk.codeSeq ");
			hql.append(" and w.preName = pre.preSuffCode ");

			List ls = this.getHibernateTemplate().find(hql.toString());

			WgDraftVO vo = new WgDraftVO();
			if (ls != null && ls.size() > 0) {
				Object[] objs = (Object[]) ls.get(0);
				String emp = (String) objs[0];
				Date iDate = (Date) objs[1];
				String prefix = (String) objs[2];
				String firstName = (String) objs[3];
				String lastName = (String) objs[4];
				String areaDesc = (String) objs[5];
				String secDesc = (String) objs[6];
				String workDesc = (String) objs[7];
				String draftStatus = (String) objs[8];
				String punishStatus = (String) objs[9];
				Double score = (Double) objs[10];
				Long hisSeq = (Long) objs[11];
				Double perDraft = (Double) objs[12];
				String remark = (String) objs[13];
				String noDraft = (String) objs[14];

				vo.setEmpCode(emp);

				SimpleDateFormat smdf = new SimpleDateFormat("dd/MM/yyyy",
						new Locale("TH", "th"));
				vo.setIDateStr(smdf.format(iDate));
				vo.setFirstName(prefix + " " + firstName);
				vo.setLastName(lastName);
				vo.setAreaDesc(areaDesc);
				vo.setSecDesc(secDesc);
				vo.setWorkDesc(workDesc);
				vo.setDraftStatus(draftStatus);
				vo.setPunishStatus(punishStatus);
				vo.setScore(score);
				vo.setHisSeq(hisSeq);
				vo.setPerDraft(perDraft);
				vo.setRemark(remark);

				// System.out.println("noDraft : " + noDraft);

				String tmp = "";
				if (noDraft != null && !noDraft.trim().equals("")) {
					char[] tmpNoDraft = noDraft.toCharArray();
					for (int i = 0; i < tmpNoDraft.length; i++) {
						tmp += String.valueOf(tmpNoDraft[i]);

						// System.out.println("tmp : " + tmp);

						if (i < tmpNoDraft.length - 1)
							tmp += ",";
					}
				}
				vo.setNoDraft(tmp);

				System.out.println("NO DRAFT : " + vo.getNoDraft());
				System.out.println("DRAFT : " + vo.getDraftStatus());
			}

			return vo;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	public List wgDraftReport(String userId, String ouCode, Integer year) {
		StringBuffer hql = new StringBuffer();

		hql.append(" select wgDr.pk.empCode,wgDr.gworkCode,");
		hql.append(" wgDr.refDbPreSuff.prefixName||' '||wgDr.firstName||' '||wgDr.lastName,");
		hql.append(" pnOrg.divDesc,pnOrg.areaDesc,pnOrg.secDesc,pnOrg.workDesc,");
		hql.append(" wgDr.wage,wgDr.hisSeq,wgDr.perDraft,nvl(wgDr.punishStatus,'N'),wgDr.score,nvl(wgDr.draftStatus,'N'), ");
		hql.append(" pnOrg.divCode,wgDr.noDraft ");
		hql.append(" from WgDraft wgDr ,PnOrganization pnOrg");
		hql.append(" where wgDr.pk.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");

		hql.append(" and wgDr.codeSeq in ( ");
		hql.append(" 	select pk.codeSeq ");
		hql.append(" 	from VPnOrganizationSecurity ");
		hql.append(" 	where pk.userId = '" + userId + "' ");
		hql.append(" 	and pk.ouCode = '" + ouCode + "' ");
		hql.append(" ) ");

		hql.append(" and wgDr.pk.year = ");
		hql.append(year);
		hql.append(" and wgDr.pk.ouCode  = pnOrg.pk.ouCode ");
		hql.append(" and wgDr.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" order by pnOrg.divDesc,decode(wgDr.perDraft,5,9999,4,9999,NVL(wgDr.score,0)) desc,");
		hql.append(" NVL(wgDr.perDraft,0) desc,wgDr.hisSeq asc,wgDr.pk.empCode asc");

		System.out.println("HQL findByCriteria ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		List retList = new ArrayList();

		for (Iterator it = empList.iterator(); it.hasNext();) {
			Object[] r = (Object[]) it.next();
			String empCode = (String) r[0];
			String gworkCode = (String) r[1];
			String empName = (String) r[2];
			String divDesc = (String) r[3];
			String areaDesc = (String) r[4];
			String secDesc = (String) r[5];
			String workDesc = (String) r[6];
			Double wage = (Double) r[7];
			Long hisSeq = (Long) r[8];
			Double perDraft = (Double) r[9];
			String punishStatus = (String) r[10];
			Double score = (Double) r[11];
			String draftStatus = (String) r[12];
			String divCode = (String) r[13];
			String noDraft = (String) r[14];

			WgDraftReportVO ret = new WgDraftReportVO();
			ret.setEmpCode(empCode);
			ret.setGworkCode(gworkCode);
			ret.setEmpName(empName);
			ret.setDivDesc(divDesc);
			ret.setAreaDesc(areaDesc);
			ret.setSecDesc(secDesc);
			ret.setWorkDesc(workDesc);
			ret.setWage(wage);
			ret.setHisSeq(hisSeq);
			ret.setPerDraft(perDraft);
			ret.setPunishStatus(punishStatus);
			ret.setScore(score);
			ret.setDraftStatus(draftStatus);
			ret.setDivCode(divCode);
			ret.setNoDraft(noDraft);
			retList.add(ret);
		}
		return retList;
	}

	public String findDupHisSeq(String ouCode, String year, String perDraft,
			String hisSeq, String draftStatus, String empCode) throws Exception {
		if (draftStatus.equals("Y") && Integer.parseInt(perDraft) > 3) {
			StringBuffer hql = new StringBuffer();
			hql.append(" select 'x' ");
			hql.append(" from WgDraft w, PnOrganization o");
			hql.append(" where w.pk.ouCode = '" + ouCode + "' ");
			hql.append(" and w.pk.year = " + year);
			hql.append(" and w.perDraft = " + perDraft);
			hql.append(" and w.hisSeq = " + hisSeq);
			hql.append(" and w.pk.empCode <> " + empCode);
			hql.append(" and o.divCode in ( ");
			hql.append(" select pno.divCode from  WgDraft wd, PnOrganization pno ");
			hql.append(" where wd.pk.ouCode = '" + ouCode + "' ");
			hql.append(" and wd.pk.year = " + year);
			hql.append(" and wd.pk.empCode = " + empCode);
			hql.append(" and wd.pk.ouCode = pno.pk.ouCode ");
			hql.append(" and wd.codeSeq = pno.pk.codeSeq ");
			hql.append(" ) ");
			hql.append(" and w.pk.ouCode = o.pk.ouCode ");
			hql.append(" and w.codeSeq = o.pk.codeSeq ");

			List ls = this.getHibernateTemplate().find(hql.toString());
			if (ls != null && ls.size() > 0)
				return "Y";
			else
				return "N";
		} else
			return "N";
	}

	public boolean isConfirmFlag(String ouCode, String year, String userId)
			throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select count(*) ");
		sql.append(" from WgDraft d ");
		sql.append(" where d.pk.ouCode = '" + ouCode + "' ");
		sql.append(" and d.pk.year = " + year);
		sql.append(" and d.confirmFlag = 'Y' ");
		sql.append(" and d.codeSeq in ( ");
		sql.append(" 	select pk.codeSeq ");
		sql.append(" 	from VPnOrganizationSecurity ");
		sql.append(" 	where pk.userId = '" + userId + "' ");
		sql.append(" 	and pk.ouCode = '" + ouCode + "' ");
		sql.append(" ) ");

		List ls = this.getHibernateTemplate().find(sql.toString());

		if (ls != null && ls.size() > 0) {
			Integer i = (Integer) ls.get(0);

			if (i.intValue() > 0)
				return true;
			else
				return false;
		} else
			return true;
	}

	public Integer findAdjustNotOver(String ouCode, String empCode, String year) {
		StringBuffer sql = new StringBuffer(0);
		StringBuffer sql2 = new StringBuffer(0);
		sql.append(" select punish_code ");
		sql.append(" from wg_emp_punish_his ");
		sql.append(" where ou_Code = '" + ouCode + "' ");
		sql.append(" and emp_code = '" + empCode + "' ");
		sql.append(" and to_char(instruct_date,'RRRR','NLS_CALENDAR=''Thai Buddha') = '"
				+ year + "' ");

		sql2.append(" select min(adjust_not_over) ");
		sql2.append(" from wg_punish_type ");
		sql2.append(" where ou_Code = '" + ouCode + "' ");
		sql2.append(" and punish_code in (");
		sql2.append(sql);
		sql2.append(")");
		sql2.append(" and inactive = 'N' ");
		int lsAdjust = this.jdbcTemplate.queryForInt(sql2.toString());
		return new Integer(lsAdjust);

	}
}
