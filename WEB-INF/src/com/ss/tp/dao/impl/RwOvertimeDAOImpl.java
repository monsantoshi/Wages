package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.RwOvertimeDAO;
import com.ss.tp.dto.*;
import com.ss.tp.model.RwOvertime;

public class RwOvertimeDAOImpl extends HibernateDaoSupport implements
		RwOvertimeDAO, Serializable {
	FastDateFormat fmd = FastDateFormat.getInstance("yyyy/MM/dd");
	List rwList = new ArrayList();

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public RwOvertimeDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	public void insertRwOvertime(RwOvertimeVO rwovertimevo) throws Exception {
		RwOvertime rw = new RwOvertime();
		try {
			BeanUtils.copyProperties(rw, rwovertimevo);
			rw.setCreDate(new Date());
			rw.setUpdDate(new Date());
			this.getHibernateTemplate().save(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public RwOvertime loadRwOvertime(RwOvertimeVO rpVo) {
		RwOvertime rwp = new RwOvertime();
		try {
			// rwp = (RwOvertime)
			// this.getHibernateTemplate().load(RwOvertime.class,rpVo.getKeySeq());
			Criteria c = this.getSessionFactory().openSession()
					.createCriteria(RwOvertime.class);
			c.add(Restrictions.idEq(rpVo.getKeySeq()));
			rwp = (RwOvertime) c.list().get(0);
			// rwp = this.getHibernateTemplate().findByCriteria(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rwp;
	}

	public void updateRwOvertime(RwOvertimeVO rwovertimevo) throws Exception {
		RwOvertime rw = new RwOvertime();
		boolean checkUpdate = false;
		try {
			rw = this.loadRwOvertime(rwovertimevo);
			rw.setYearWork(rwovertimevo.getYearWork());
			rw.setFlagPr(rwovertimevo.getFlagPr());
			rw.setPeriodWork(rwovertimevo.getPeriodWork());
			// rw.setOtType(rwovertimevo.getOtType());
			// rw.setRefNo(rwovertimevo.getRefNo());
			rw.setFiscalYear(rwovertimevo.getFiscalYear());
			rw.setStartDate(rwovertimevo.getStartDate());
			rw.setEndDate(rwovertimevo.getEndDate());
			rw.setTotDay1(rwovertimevo.getTotDay1());
			rw.setTotDay15(rwovertimevo.getTotDay15());
			rw.setTotDay3(rwovertimevo.getTotDay3());
			rw.setAmtDay1(rwovertimevo.getAmtDay1());
			rw.setAmtDay15(rwovertimevo.getAmtDay15());
			rw.setAmtDay3(rwovertimevo.getAmtDay3());
			rw.setSeqData(rwovertimevo.getSeqData());
			rw.setFlagWork(rwovertimevo.getFlagWork());
			rw.setCodeSeq(rwovertimevo.getCodeSeq());
			rw.setUpdBy(rwovertimevo.getUpdBy());
			rw.setUpdDate(new Date());
			rw.setWorkHour(rwovertimevo.getWorkHour());
			this.getHibernateTemplate().update(rw);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void deleteRwOvertime(RwOvertimeVO rwovertimevo) throws Exception {
		RwOvertime rw = new RwOvertime();
		try {
			BeanUtils.copyProperties(rw, rwovertimevo);
			this.getHibernateTemplate().delete(rw);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void addList(RwOvertimeVO rwovertimevo) {
		this.rwList.add(rwovertimevo);
	}

	public void clearList() {
		this.rwList = new ArrayList();
	}

	public void insertAndUpdateRwOvertimes() throws Exception {
		try {
			for (int i = 0; i < this.rwList.size(); i++) {
				System.out.println(i);
				RwOvertimeVO rwovertimevo = (RwOvertimeVO) this.rwList.get(i);
				if ((rwovertimevo.getKeySeq() != new Long(0))
						&& rwovertimevo.getKeySeq() != null
						&& !rwovertimevo.getKeySeq().equals("")) {
					this.updateRwOvertime(rwovertimevo);
				} else {
					this.insertRwOvertime(rwovertimevo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.rwList = new ArrayList();
			throw e;
		} finally {
			this.clearList();
		}
	}

	public void insertRwOvertimes(List rwovertimevolist) throws Exception {
		RwOvertime rw = new RwOvertime();
		try {
			for (int i = 0; i < rwovertimevolist.size(); i++) {
				RwOvertimeVO rwovertimevo = (RwOvertimeVO) rwovertimevolist
						.get(i);

				BeanUtils.copyProperties(rw, rwovertimevo);
				rw.setCreDate(new Date());
				rw.setUpdDate(new Date());
				this.getHibernateTemplate().save(rw);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List findOverTimeByCriteria(String userId, String orgCodeFrom,
			String orgCodeTo, String empCodeFrom, String empCodeTo,
			String otTypeFrom, String flagPrFrom, String flagPrTo,
			String refNoFrom, String refNoTo, String ouCode, Long year,
			Long period, int pageRecord, int totalRecord) {
		try {

			// StringBuffer criteria = new StringBuffer(0);
			//
			// if (orgCodeFrom != null && !orgCodeFrom.equals(""))
			// criteria.append("and org.orgCode >= " + orgCodeFrom);
			//
			// if (orgCodeTo != null && !orgCodeTo.equals(""))
			// criteria.append("and org.orgCode <= " + orgCodeTo);
			//
			// if (empCodeFrom != null && !empCodeFrom.equals(""))
			// criteria.append("and ov.empCode >= '" + empCodeFrom + "' ");
			//
			// if (empCodeTo != null && !empCodeTo.equals(""))
			// criteria.append("and ov.empCode <= '" + empCodeTo + "' ");
			//
			// if (otTypeFrom != null && !otTypeFrom.equals("")){
			// if(!otTypeFrom.equals("3")){
			// criteria.append("and ov.otType = '" + otTypeFrom + "' ");
			// }
			// }
			//
			//
			//
			// if (flagPrFrom != null && !flagPrFrom.equals(""))
			// {
			// criteria.append(" and
			// decode(ov.flagPr,'N',1,'A',2,'R',3,'B',4,'S',5,6) >= decode('");
			// criteria.append(flagPrFrom);
			// criteria.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
			// }
			//
			// if (flagPrTo != null && !flagPrTo.equals(""))
			// {
			// criteria.append(" and
			// decode(ov.flagPr,'N',1,'A',2,'R',3,'B',4,'S',5,6) <= decode('");
			// criteria.append(flagPrTo);
			// criteria.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
			// }
			//
			// if(refNoFrom != null && !refNoFrom.equals("")){
			// criteria.append(" and ov.refNo >= '"+refNoFrom+"' ");
			// }
			//
			// if(refNoTo != null && !refNoTo.equals("")){
			// criteria.append(" and ov.refNo <= '"+refNoTo+"' ");
			// }
			//
			// StringBuffer hql = new StringBuffer(0);
			// hql.append(" select distinct ov.refNo, sf.prefixName,
			// pnEm.firstName, pnEm.lastName, pnEm.pk.empCode
			// ,ov.otType,org.orgCode");
			// hql.append(" from RwOvertime ov, PnEmployee pnEm, ");
			// hql.append(" PnPosition ps, DbPreSuff sf, PrEmployee prEm,
			// PnOrganization org ");
			// hql.append(" where ov.ouCode = '" + ouCode + "' ");
			// hql.append(" and ov.yearPr = " + year);
			// hql.append(" and ov.periodPr = " + period);
			//
			// // hql.append(" and pnEm.pk.empCode in ( ");
			// // hql.append(" select pk.empCode ");
			// // hql.append(" from VPrEmployeeSecurity ");
			// // hql.append(" where pk.userId = '" + userId + "' ");
			// // hql.append(" and pk.ouCode = '" + ouCode + "' ");
			// // hql.append(" and pk.year = " + year );
			// // hql.append(" and pk.period = " + period );
			// // hql.append(" ) ");
			//
			// hql.append(" and ov.codeSeq in ( ");
			// hql.append(" select vpn.pk.codeSeq ");
			// hql.append(" from VPnOrganizationSecurity vpn ");
			// hql.append(" where vpn.pk.userId = '" + userId + "' ");
			// hql.append(" and ov.ouCode = vpn.pk.ouCode ");
			// hql.append(" and ov.codeSeq = vpn.pk.codeSeq ");
			// hql.append(" ) ");
			//
			// hql.append(criteria.toString());
			// hql.append(" and ov.empCode = pnEm.pk.empCode ");
			// hql.append(" and ov.ouCode = pnEm.pk.ouCode ");
			// hql.append(" and ov.ouCode = ps.pk.ouCode ");
			// hql.append(" and ov.ouCode = prEm.key.ouCode ");
			// hql.append(" and ov.yearPr = prEm.key.year ");
			// hql.append(" and ov.periodPr = prEm.key.period ");
			// hql.append(" and ov.empCode = prEm.key.empCode ");
			// hql.append(" and ov.ouCode = org.pk.ouCode ");
			// hql.append(" and prEm.codeSeqWork = org.pk.codeSeq ");
			// hql.append(" and pnEm.preName = sf.preSuffCode ");
			// hql.append(" and pnEm.gworkCode = ps.pk.gworkCode ");
			// hql.append(" and pnEm.positionCode = ps.pk.positionCode ");
			// hql.append(" order by
			// org.orgCode,pnEm.pk.empCode,ov.refNo,ov.otType ");
			//
			// System.out.println("************* ::: "+hql.toString());
			//
			// Query q = this.getSession().createQuery(hql.toString());
			//
			//
			//
			// List empList = q.setFirstResult(pageRecord * totalRecord)
			// .setMaxResults(totalRecord).list();

			StringBuffer hql = new StringBuffer(0);
			hql.append(" select distinct rwovertime0_.REF_NO as col_0_0_, dbpresuff3_.PREFIX_NAME as col_1_0_, pnemployee1_.FIRST_NAME as col_2_0_, ");
			hql.append("pnemployee1_.LAST_NAME as col_3_0_, pnemployee1_.EMP_CODE as col_4_0_, rwovertime0_.OT_TYPE as col_5_0_, ");
			hql.append("pnorganiza5_.ORG_CODE as col_6_0_,nvl(pnemployee1_.ADMIN_CODE,chr(250)),pnemployee1_.LEVEL_CODE  ");
			hql.append(" from RW_OVERTIME rwovertime0_, PN_EMPLOYEE pnemployee1_, PN_POSITION pnposition2_, DB_PRE_SUFF dbpresuff3_, PR_EMPLOYEE premployee4_, ");
			hql.append("PN_ORGANIZATION pnorganiza5_ ");
			hql.append("where rwovertime0_.OU_CODE='" + ouCode
					+ "' and rwovertime0_.YEAR_PR=" + year
					+ " and rwovertime0_.PERIOD_PR=" + period + " ");
			hql.append("and (rwovertime0_.CODE_SEQ in (");
			hql.append("select vpnorganiz6_.CODE_SEQ ");
			hql.append("from V_PN_ORGANIZATION_SECURITY vpnorganiz6_");
			hql.append(" where vpnorganiz6_.USER_ID='" + userId
					+ "' and rwovertime0_.OU_CODE=vpnorganiz6_.OU_CODE ");
			hql.append(" and rwovertime0_.CODE_SEQ=vpnorganiz6_.CODE_SEQ");
			hql.append(")");
			hql.append(")");

			if (orgCodeFrom != null && !orgCodeFrom.equals(""))
				hql.append(" and pnorganiza5_.ORG_CODE >= " + orgCodeFrom);

			if (orgCodeTo != null && !orgCodeTo.equals(""))
				hql.append("and pnorganiza5_.ORG_CODE <= " + orgCodeTo);

			if (empCodeFrom != null && !empCodeFrom.equals(""))
				hql.append("and rwovertime0_.EMP_CODE >= '" + empCodeFrom
						+ "' ");

			if (empCodeTo != null && !empCodeTo.equals(""))
				hql.append("and rwovertime0_.EMP_CODE <= '" + empCodeTo + "' ");

			if (otTypeFrom != null && !otTypeFrom.equals("")) {
				if (!otTypeFrom.equals("3")) {
					hql.append("and rwovertime0_.OT_TYPE = '" + otTypeFrom
							+ "' ");
				}
			}

			if (flagPrFrom != null && !flagPrFrom.equals("")) {
				hql.append(" and decode(rwovertime0_.FLAG_PR,'N',1,'A',2,'R',3,'B',4,'S',5,6) >= decode('");
				hql.append(flagPrFrom);
				hql.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
			}

			if (flagPrTo != null && !flagPrTo.equals("")) {
				hql.append(" and decode(rwovertime0_.FLAG_PR,'N',1,'A',2,'R',3,'B',4,'S',5,6) <= decode('");
				hql.append(flagPrTo);
				hql.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
			}

			if (refNoFrom != null && !refNoFrom.equals("")) {
				hql.append(" and NVL(rwovertime0_.REF_NO,'0') >= '" + refNoFrom
						+ "' ");
			}

			if (refNoTo != null && !refNoTo.equals("")) {
				hql.append(" and NVL(rwovertime0_.REF_NO,'0') <= '" + refNoTo
						+ "' ");
			}

			hql.append("and rwovertime0_.EMP_CODE=pnemployee1_.EMP_CODE ");
			hql.append("and rwovertime0_.OU_CODE=pnemployee1_.OU_CODE and rwovertime0_.OU_CODE=pnposition2_.OU_CODE and rwovertime0_.OU_CODE=premployee4_.OU_CODE ");
			hql.append("and rwovertime0_.YEAR_PR=premployee4_.YEAR and rwovertime0_.PERIOD_PR=premployee4_.PERIOD and rwovertime0_.EMP_CODE=premployee4_.EMP_CODE ");
			hql.append("and rwovertime0_.OU_CODE=pnorganiza5_.OU_CODE and premployee4_.CODE_SEQ_WORK=pnorganiza5_.CODE_SEQ ");
			hql.append("and pnemployee1_.PRE_NAME=dbpresuff3_.PRE_SUFF_CODE and pnemployee1_.GWORK_CODE=pnposition2_.GWORK_CODE ");
			hql.append("and pnemployee1_.POSITION_CODE=pnposition2_.POSITION_CODE ");
			hql.append("order by pnorganiza5_.ORG_CODE, nvl(pnemployee1_.ADMIN_CODE,chr(250)),pnemployee1_.LEVEL_CODE desc,pnemployee1_.EMP_CODE, rwovertime0_.REF_NO, rwovertime0_.OT_TYPE ");

			System.out.println("============= :" + hql.toString());

			List empList = this.jdbcTemplate.queryForList(hql.toString());

			System.out.println(" ++++++++++++++++++++++++ :" + empList.size());

			// System.out.println("************************");
			// List empList = q.setFirstResult(page * recPerPage).setMaxResults(
			// recPerPage).list();
			// System.out.println("************************");

			List result = new ArrayList();
			int startRecord = pageRecord * totalRecord;

			for (int i = startRecord, count = 0; i < empList.size()
					&& count < totalRecord; i++, count++) {
				ListOrderedMap map = (ListOrderedMap) empList.get(i);
				// Object[] r = (Object[]) empList.get(i);
				String empCode = (String) map.getValue(4);
				String prefixName = (String) map.getValue(1);
				String firstName = (String) map.getValue(2);
				String lastName = (String) map.getValue(3);
				String positionShort = (String) map.getValue(0);
				String otType = (String) map.getValue(5);

				PayRollEmployeeVO vo = new PayRollEmployeeVO();
				vo.setEmpCode(empCode);
				vo.setName(prefixName + " " + firstName + " " + lastName);
				vo.setPositionShort(positionShort);
				vo.setOtType(otType);

				result.add(vo);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Integer findCountOverTimeByCriteria(String userId,
			String orgCodeFrom, String orgCodeTo, String empCodeFrom,
			String empCodeTo, String otTypeFrom, String flagPrFrom,
			String flagPrTo, String refNoFrom, String refNoTo, String ouCode,
			Long year, Long period) {

		StringBuffer criteria = new StringBuffer(0);
		//
		// if (orgCodeFrom != null && !orgCodeFrom.equals(""))
		// criteria.append("and org.orgCode >= " + orgCodeFrom);
		//
		// if (orgCodeTo != null && !orgCodeTo.equals(""))
		// criteria.append("and org.orgCode <= " + orgCodeTo);
		//
		// if (empCodeFrom != null && !empCodeFrom.equals(""))
		// criteria.append("and ov.empCode >= '" + empCodeFrom + "' ");
		//
		// if (empCodeTo != null && !empCodeTo.equals(""))
		// criteria.append("and ov.empCode <= '" + empCodeTo + "' ");
		//
		// if (otTypeFrom != null && !otTypeFrom.equals("")){
		// if(!otTypeFrom.equals("3")){
		// criteria.append("and ov.otType >= '" + otTypeFrom + "' ");
		// }
		// }
		//
		// if (flagPrFrom != null && !flagPrFrom.equals(""))
		// {
		// criteria.append(" and
		// decode(ov.flagPr,'N',1,'A',2,'R',3,'B',4,'S',5,6) >= decode('");
		// criteria.append(flagPrFrom);
		// criteria.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
		// }
		//
		// if (flagPrTo != null && !flagPrTo.equals(""))
		// {
		// criteria.append(" and
		// decode(ov.flagPr,'N',1,'A',2,'R',3,'B',4,'S',5,6) <= decode('");
		// criteria.append(flagPrTo);
		// criteria.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
		// }
		//
		// if(refNoFrom != null && !refNoFrom.equals("")){
		// criteria.append(" and ov.refNo >= '"+refNoFrom+"' ");
		// }
		//
		// if(refNoTo != null && !refNoTo.equals("")){
		// criteria.append(" and ov.refNo <= '"+refNoTo+"' ");
		// }
		//
		// StringBuffer hql = new StringBuffer(0);
		// hql.append(" select count(distinct pnEm.pk.empCode) ");
		// hql.append(" from RwOvertime ov, PnEmployee pnEm, ");
		// hql.append(" PnPosition ps, DbPreSuff sf, PrEmployee prEm,
		// PnOrganization org ");
		// hql.append(" where ov.ouCode = '" + ouCode + "' ");
		// hql.append(" and ov.yearPr = " + year);
		// hql.append(" and ov.periodPr = " + period);
		// hql.append(" and pnEm.pk.empCode in ( ");
		// hql.append(" select pk.empCode ");
		// hql.append(" from VPrEmployeeSecurity ");
		// hql.append(" where pk.userId = '" + userId + "' ");
		// hql.append(" and pk.ouCode = '" + ouCode + "' ");
		// hql.append(" and pk.year = " + year );
		// hql.append(" and pk.period = " + period );
		// hql.append(" ) ");
		// hql.append(criteria.toString());
		// hql.append(" and ov.empCode = pnEm.pk.empCode ");
		// hql.append(" and ov.ouCode = pnEm.pk.ouCode ");
		// hql.append(" and ov.ouCode = ps.pk.ouCode ");
		// hql.append(" and ov.ouCode = prEm.key.ouCode ");
		// hql.append(" and ov.yearPr = prEm.key.year ");
		// hql.append(" and ov.periodPr = prEm.key.period ");
		// hql.append(" and ov.empCode = prEm.key.empCode ");
		// hql.append(" and ov.ouCode = org.pk.ouCode ");
		// hql.append(" and prEm.codeSeqWork = org.pk.codeSeq ");
		// hql.append(" and pnEm.preName = sf.preSuffCode ");
		// hql.append(" and pnEm.gworkCode = ps.pk.gworkCode ");
		// hql.append(" and pnEm.positionCode = ps.pk.positionCode ");
		// hql.append(" order by pnEm.pk.empCode ");
		//
		// System.out.println(hql.toString());
		// List empList = this.getSession().createQuery(hql.toString()).list();
		StringBuffer hql = new StringBuffer(0);
		hql.append(" select distinct rwovertime0_.REF_NO as col_0_0_, dbpresuff3_.PREFIX_NAME as col_1_0_, pnemployee1_.FIRST_NAME as col_2_0_, ");
		hql.append("pnemployee1_.LAST_NAME as col_3_0_, pnemployee1_.EMP_CODE as col_4_0_, rwovertime0_.OT_TYPE as col_5_0_, ");
		hql.append("pnorganiza5_.ORG_CODE as col_6_0_ ");
		hql.append(" from RW_OVERTIME rwovertime0_, PN_EMPLOYEE pnemployee1_, PN_POSITION pnposition2_, DB_PRE_SUFF dbpresuff3_, PR_EMPLOYEE premployee4_, ");
		hql.append("PN_ORGANIZATION pnorganiza5_ ");
		hql.append("where rwovertime0_.OU_CODE='" + ouCode
				+ "' and rwovertime0_.YEAR_PR=" + year
				+ " and rwovertime0_.PERIOD_PR=" + period + " ");
		hql.append("and (rwovertime0_.CODE_SEQ in (");
		hql.append("select vpnorganiz6_.CODE_SEQ ");
		hql.append("from V_PN_ORGANIZATION_SECURITY vpnorganiz6_");
		hql.append(" where vpnorganiz6_.USER_ID='" + userId
				+ "' and rwovertime0_.OU_CODE=vpnorganiz6_.OU_CODE ");
		hql.append(" and rwovertime0_.CODE_SEQ=vpnorganiz6_.CODE_SEQ");
		hql.append(")");
		hql.append(")");

		if (orgCodeFrom != null && !orgCodeFrom.equals(""))
			hql.append(" and pnorganiza5_.ORG_CODE >= " + orgCodeFrom);

		if (orgCodeTo != null && !orgCodeTo.equals(""))
			hql.append("and pnorganiza5_.ORG_CODE <= " + orgCodeTo);

		if (empCodeFrom != null && !empCodeFrom.equals(""))
			hql.append("and rwovertime0_.EMP_CODE >= '" + empCodeFrom + "' ");

		if (empCodeTo != null && !empCodeTo.equals(""))
			hql.append("and rwovertime0_.EMP_CODE <= '" + empCodeTo + "' ");

		if (otTypeFrom != null && !otTypeFrom.equals("")) {
			if (!otTypeFrom.equals("3")) {
				hql.append("and rwovertime0_.OT_TYPE = '" + otTypeFrom + "' ");
			}
		}

		if (flagPrFrom != null && !flagPrFrom.equals("")) {
			hql.append(" and decode(rwovertime0_.FLAG_PR,'N',1,'A',2,'R',3,'B',4,'S',5,6) >= decode('");
			hql.append(flagPrFrom);
			hql.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
		}

		if (flagPrTo != null && !flagPrTo.equals("")) {
			hql.append(" and decode(rwovertime0_.FLAG_PR,'N',1,'A',2,'R',3,'B',4,'S',5,6) <= decode('");
			hql.append(flagPrTo);
			hql.append("','N',1,'A',2,'R',3,'B',4,'S',5,6) ");
		}

		if (refNoFrom != null && !refNoFrom.equals("")) {
			hql.append(" and NVL(rwovertime0_.REF_NO,'0') >= '" + refNoFrom
					+ "' ");
		}

		if (refNoTo != null && !refNoTo.equals("")) {
			hql.append(" and NVL(rwovertime0_.REF_NO,'0') <= '" + refNoTo
					+ "' ");
		}

		hql.append("and rwovertime0_.EMP_CODE=pnemployee1_.EMP_CODE ");
		hql.append("and rwovertime0_.OU_CODE=pnemployee1_.OU_CODE and rwovertime0_.OU_CODE=pnposition2_.OU_CODE and rwovertime0_.OU_CODE=premployee4_.OU_CODE ");
		hql.append("and rwovertime0_.YEAR_PR=premployee4_.YEAR and rwovertime0_.PERIOD_PR=premployee4_.PERIOD and rwovertime0_.EMP_CODE=premployee4_.EMP_CODE ");
		hql.append("and rwovertime0_.OU_CODE=pnorganiza5_.OU_CODE and premployee4_.CODE_SEQ_WORK=pnorganiza5_.CODE_SEQ ");
		hql.append("and pnemployee1_.PRE_NAME=dbpresuff3_.PRE_SUFF_CODE and pnemployee1_.GWORK_CODE=pnposition2_.GWORK_CODE ");
		hql.append("and pnemployee1_.POSITION_CODE=pnposition2_.POSITION_CODE ");
		hql.append("order by pnorganiza5_.ORG_CODE,pnemployee1_.EMP_CODE, rwovertime0_.REF_NO, rwovertime0_.OT_TYPE ");

		System.out.println("============= :" + hql.toString());

		List empList = this.jdbcTemplate.queryForList(hql.toString());

		System.out.println(" ++++++++++++++++++++++++ :" + empList.size());

		return new Integer(String.valueOf(empList.size()));
	}

	public PayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		StringBuffer hql = new StringBuffer();

		hql.append(" select rw.empCode, ");
		hql.append(" pnEmp.refDbPreSuff.prefixName, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
		hql.append(" pnPos.positionShort, ");
		hql.append(" pnOrg.orgCode, ");
		hql.append(" pnOrg.divShort || ' ' || pnOrg.areaDesc || ' ' || pnOrg.secDesc || ' ' || pnOrg.workDesc , ");
		// hql.append(" prEmp.codeSeqWork, ");
		hql.append(" prEmp.newCodeSeq ,");
		hql.append(" rw.flagWork ");
		hql.append(" from RwOvertime rw, ");
		hql.append(" PnEmployee pnEmp, ");
		hql.append(" PrEmployee prEmp, ");
		hql.append(" PnPosition pnPos, ");
		hql.append(" PnOrganization pnOrg ");
		hql.append(" where rw.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		hql.append(" and prEmp.key.ouCode = '");
		hql.append(ouCode);
		hql.append("' ");
		hql.append(" and prEmp.key.year = ");
		hql.append(year);
		hql.append(" and prEmp.key.period = ");
		hql.append(period);
		hql.append(" and prEmp.key.empCode = '");
		hql.append(empCode);
		hql.append("' ");
		hql.append(" and rw.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and rw.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnEmp.pk.ouCode = pnPos.pk.ouCode ");
		hql.append(" and pnEmp.gworkCode = pnPos.pk.gworkCode ");
		hql.append(" and pnEmp.positionCode = pnPos.pk.positionCode ");
		hql.append(" and rw.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rw.codeSeq = pnOrg.pk.codeSeq ");

		System.out.println("HQL findByEmpCodeDetail ==> " + hql.toString());

		List empList = this.getSession().createQuery(hql.toString()).list();
		PayRollEmployeeVO ret = new PayRollEmployeeVO();

		if (empList != null && empList.size() > 0) {
			Object[] r = (Object[]) empList.get(0);
			String empcode = (String) r[0];
			String prefixName = (String) r[1];
			String firstName = (String) r[2];
			String lastName = (String) r[3];
			String positionShort = (String) r[4];
			String orgCode = (String) r[5];
			String orgDesc = (String) r[6];
			Double codeSeqWork = (Double) r[7];
			String flagWork = (String) r[8];

			ret.setEmpCode(empcode);
			ret.setName(prefixName + " " + firstName + " " + lastName);
			ret.setPositionShort(positionShort);
			ret.setOrgCode(orgCode);
			ret.setOrgDesc(orgDesc);
			ret.setCodeSeqWork(codeSeqWork);
			ret.setFlagWork(flagWork);
		}

		return ret;
	}

	public List findByEmpCodeList(String userId, String ouCode, Long yearPr,
			Long periodPr, String empCode, String refNo, String otType)
			throws UnsupportedEncodingException {
		StringBuffer hql = new StringBuffer();
		String strendCode = refNo;// new
		// String(refNo.getBytes("TIS-620"),"ISO8859-1");
		System.out.println(" ___________REF NO______________ :" + refNo);
		// hql.append(" from RwOvertime rw ");
		// hql.append(" where rw.ouCode = '");
		// hql.append(ouCode);
		// hql.append("' ");
		// hql.append(" and rw.yearPr = ");
		// hql.append(yearPr);
		// hql.append(" and rw.periodPr = ");
		// hql.append(periodPr);
		// hql.append(" and rw.empCode = '");
		// hql.append(empCode);
		// hql.append("' ");
		// hql.append(" and rw.refNo Like '%");
		// hql.append(strendCode);
		// hql.append("%' ");
		// hql.append(" and rw.otType = '");
		// hql.append(otType);
		// hql.append("' ");
		// hql.append(" and rw.codeSeq in ( ");
		// hql.append(" select vpn.pk.codeSeq ");
		// hql.append(" from VPnOrganizationSecurity vpn ");
		// hql.append(" where vpn.pk.userId = '");
		// hql.append(userId);
		// hql.append("' ");
		// hql.append(" and rw.ouCode = vpn.pk.ouCode ");
		// hql.append(" and rw.codeSeq = vpn.pk.codeSeq ) ");
		//
		// System.out.println("HQL findByEmpCodeList ==> " + hql.toString());
		//
		// List empList = this.getSession().createQuery(hql.toString()).list();
		// List retList = new ArrayList();
		//
		// System.out.println(" SIZE FOR LIST ----------------- :::
		// "+empList.size());
		//
		// if(empList!=null&&empList.size()>0)
		// {
		// for(int i=0;i<empList.size();i++)
		// {
		// RwOvertimeVO rw = new RwOvertimeVO();
		//
		// try{
		// BeanUtils.copyProperties(rw ,empList.get(i));
		// retList.add(i,rw);
		// }catch (Exception e){
		// e.printStackTrace();
		// }
		// }
		// }
		String ss = "";
		if (refNo != null && !refNo.trim().equals("")
				&& !refNo.trim().equals("null")) {
			ss = " and (rwov.REF_NO like '%" + refNo + "%') ";
		} else {
			ss = " and (rwov.REF_NO is null) ";
		}
		hql.append(" select * ");
		hql.append(" from rw_overtime rwov ,PN_ORGANIZATION org");
		hql.append(" where rwov.OU_CODE='" + ouCode + "' and rwov.YEAR_PR="
				+ yearPr + " and rwov.PERIOD_PR=" + periodPr
				+ " and rwov.EMP_CODE='" + empCode + "' ");

		hql.append(ss);

		hql.append("and rwov.CODE_SEQ = org.CODE_SEQ ");
		hql.append("and rwov.OU_CODE = org.OU_CODE ");
		hql.append("and rwov.OT_TYPE='" + otType + "'  ");
		hql.append(" and (rwov.CODE_SEQ in ( ");
		hql.append(" select vpnorganiz1_.CODE_SEQ ");
		hql.append(" from V_PN_ORGANIZATION_SECURITY vpnorganiz1_ ");
		hql.append(" where vpnorganiz1_.USER_ID='"
				+ userId
				+ "' and rwov.OU_CODE=vpnorganiz1_.OU_CODE and rwov.CODE_SEQ=vpnorganiz1_.CODE_SEQ) ");
		hql.append(" ) ");

		System.out.println("--------------- :: " + hql.toString());
		List ls = this.jdbcTemplate.queryForList(hql.toString());
		System.out.println(" %%%%%%%%%%%%%% :: " + ls.size());

		List retList = new ArrayList();
		RwOvertimeVO rw = new RwOvertimeVO();
		String dh;
		String ad;
		String sd;
		String wd;
		if (ls != null && ls.size() > 0) {
			for (int i = 0; i < ls.size(); i++) {
				dh = "";
				ad = "";
				sd = "";
				wd = "";
				rw = new RwOvertimeVO();
				ListOrderedMap element = (ListOrderedMap) ls.get(i);
				rw.setTotDay1((element.get("TOT_DAY1") != null) ? new Double(
						element.get("TOT_DAY1").toString()) : null);
				rw.setTotDay15((element.get("TOT_DAY15") != null) ? new Double(
						element.get("TOT_DAY15").toString()) : null);
				rw.setTotDay3((element.get("TOT_DAY3") != null) ? new Double(
						element.get("TOT_DAY3").toString()) : null);
				rw.setAmtDay1((element.get("AMT_DAY1") != null) ? new Double(
						element.get("AMT_DAY1").toString()) : null);
				rw.setAmtDay15((element.get("AMT_DAY15") != null) ? new Double(
						element.get("AMT_DAY15").toString()) : null);
				rw.setAmtDay3((element.get("AMT_DAY3") != null) ? new Double(
						element.get("AMT_DAY3").toString()) : null);

				rw.setCodeSeq((element.get("CODE_SEQ") != null) ? new Long(
						element.get("CODE_SEQ").toString()) : null);
				rw.setWorkHour((element.get("WORK_HOUR") != null) ? new Double(
						element.get("WORK_HOUR").toString()) : null);
				rw.setConfirmFlag((element.get("CONFIRM_FLAG") != null) ? (String) element
						.get("CONFIRM_FLAG") : null);
				rw.setEmpCode((element.get("EMP_CODE") != null) ? (String) element
						.get("EMP_CODE") : null);
				rw.setEndDate((element.get("END_DATE") != null) ? (Date) element
						.get("END_DATE") : null);
				rw.setFiscalYear((element.get("FISCAL_YEAR") != null) ? new Double(
						element.get("FISCAL_YEAR").toString()) : null);
				rw.setFlagPr((element.get("FLAG_PR") != null) ? (String) element
						.get("FLAG_PR") : null);
				rw.setKeySeq((element.get("KEY_SEQ") != null) ? new Long(
						element.get("KEY_SEQ").toString()) : null);
				rw.setOtType((element.get("OT_TYPE") != null) ? (String) element
						.get("OT_TYPE") : null);
				rw.setYearWork((element.get("YEAR_WORK") != null) ? new Double(
						element.get("YEAR_WORK").toString()) : null);
				rw.setPeriodWork((element.get("PERIOD_WORK") != null) ? new Double(
						element.get("PERIOD_WORK").toString()) : null);
				rw.setRefNo((element.get("REF_NO") != null) ? (String) element
						.get("REF_NO") : null);
				rw.setSeqData((element.get("SEQ_DATA") != null) ? new Double(
						element.get("SEQ_DATA").toString()) : null);
				rw.setStartDate((element.get("START_DATE") != null) ? (Date) element
						.get("START_DATE") : null);
				rw.setFlagWork((element.get("FLAG_WORK") != null) ? (String) element
						.get("FLAG_WORK") : null);
				rw.setOrgCode((element.get("ORG_CODE") != null) ? (String) element
						.get("ORG_CODE") : null);
				dh = ((element.get("DIV_SHORT") != null) ? (String) element
						.get("DIV_SHORT") : "");
				ad = ((element.get("AREA_DESC") != null) ? (String) element
						.get("AREA_DESC") : "");
				sd = ((element.get("SEC_DESC") != null) ? (String) element
						.get("SEC_DESC") : "");
				wd = ((element.get("WORK_DESC") != null) ? (String) element
						.get("WORK_DESC") : "");
				rw.setOrgDesc(dh + " " + ad + " " + sd + " " + wd);
				retList.add(rw);
			}
		}
		return retList;
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select count(*) ");
		sql.append(" from RwOvertime p, SuUserOrganization e ");
		sql.append(" where p.ouCode = '" + ouCode + "' ");
		sql.append(" and p.yearPr = " + year);
		sql.append(" and p.periodPr = " + period);
		sql.append(" and p.confirmFlag = 'Y' ");
		sql.append(" and e.pk.userId = '" + userId + "' ");
		sql.append(" and p.codeSeq = e.pk.codeSeq ");
		sql.append(" and p.ouCode = e.pk.ouCode ");
		// sql.append(" and p.yearPr = e.pk.year ");
		// sql.append(" and p.periodPr = e.pk.period ");

		List ls = this.getHibernateTemplate().find(sql.toString());

		if (ls != null && ls.size() > 0) {
			Integer i = (Integer) ls.get(0);

			// System.out.println("i : " + i.intValue() + " j : " + j.intValue()
			// );

			if (i.intValue() > 0)
				return true;
			else
				return false;
		} else
			return true;
	}

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		StringBuffer sql = new StringBuffer(0);
		sql.append(" select count(*) ");
		sql.append(" from RwOvertime p, VPrEmployeeSecurity e ");
		sql.append(" where p.ouCode = '" + ouCode + "' ");
		sql.append(" and p.yearPr = " + year);
		sql.append(" and p.periodPr = " + period);
		sql.append(" and p.confirmFlag = 'N' ");
		sql.append(" and e.pk.userId = '" + userId + "' ");
		sql.append(" and p.empCode = e.pk.empCode ");
		sql.append(" and p.ouCode = e.pk.ouCode ");
		sql.append(" and p.yearPr = e.pk.year ");
		sql.append(" and p.periodPr = e.pk.period ");

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

	public List findRwOvertimeReport(String ouCode, String userId, int year,
			int period, String otType, String otStatus) {

		StringBuffer criteria = new StringBuffer(0);

		System.out.println(year);
		if (year != 0)
			criteria.append(" and rw.yearPr = " + year);
		System.out.println(period);
		if (period != 0)
			criteria.append(" and rw.periodPr = " + period);
		System.out.println(otType);
		if (otType != null && !otType.equals(""))
			criteria.append(" and rw.otType = '" + otType + "' ");
		System.out.println(otStatus);
		if (otStatus != null && !otStatus.equals("")) {
			criteria.append(" and rw.flagPr = '");
			criteria.append(otStatus);
			criteria.append("' ");
		}

		StringBuffer hql = new StringBuffer();

		hql.append(" select rw.ouCode, ");
		hql.append(" rw.yearPr, ");
		hql.append(" rw.periodPr, ");
		hql.append(" rw.otType, ");
		hql.append(" replace(TRIM(nvl(rw.refNo,' - ')),' ',''), ");
		hql.append(" pnOrg.areaCode, ");
		hql.append(" pnOrg.secCode, ");
		hql.append(" pnOrg.areaDesc, ");
		hql.append(" pnOrg.secDesc, ");
		hql.append(" rw.empCode, ");
		hql.append(" pnEmp.firstName, ");
		hql.append(" pnEmp.lastName, ");
		hql.append(" preSuff.prefixName, ");
		hql.append(" rw.totDay15, ");
		hql.append(" rw.totDay1, ");
		hql.append(" rw.totDay3, ");
		hql.append(" rw.flagPr, ");
		hql.append(" rw.flagWork, ");
		hql.append(" rw.yearWork, ");
		hql.append(" rw.periodWork, ");
		hql.append(" rw.fiscalYear, ");
		hql.append(" nvl(rw.workHour,0), ");
		hql.append(" rw.startDate, ");
		hql.append(" rw.endDate, ");
		hql.append(" rw.amtDay1, ");
		hql.append(" rw.amtDay15, ");
		hql.append(" rw.amtDay3, ");
		hql.append(" rw.amtDay3 ");
		hql.append(" from RwOvertime rw, ");
		hql.append(" PnEmployee pnEmp, ");
		hql.append(" DbPreSuff preSuff, ");
		hql.append(" VPnOrganizationSecurity vPnOrgSec, ");
		hql.append(" PnOrganization pnOrg ");
		hql.append(" where 1=1 ");
		if (ouCode != null && !ouCode.equals("")) {
			hql.append(" and rw.ouCode = '");
			hql.append(ouCode);
			hql.append("' ");
		}
		if (userId != null && !userId.equals("")) {
			hql.append(" and vPnOrgSec.pk.userId = '");
			hql.append(userId);
			hql.append("' ");
		}

		hql.append(" and rw.ouCode = pnOrg.pk.ouCode ");
		hql.append(" and rw.codeSeq = pnOrg.pk.codeSeq ");
		hql.append(" and rw.empCode = pnEmp.pk.empCode ");
		hql.append(" and pnOrg.pk.ouCode = pnEmp.pk.ouCode ");
		hql.append(" and pnEmp.preName = preSuff.preSuffCode ");
		// hql.append(" and vPrEmpSec.pk.ouCode = rw.ouCode ");
		// hql.append(" and vPrEmpSec.pk.empCode = rw.empCode ");
		// hql.append(" and vPrEmpSec.pk.year = rw.yearPr ");
		// hql.append(" and vPrEmpSec.pk.period = rw.periodPr ");
		hql.append(" and vPnOrgSec.pk.ouCode = rw.ouCode ");
		hql.append(" and vPnOrgSec.pk.codeSeq = pnOrg.pk.codeSeq ");

		hql.append(criteria.toString());

		hql.append(" order by rw.otType, replace(TRIM(nvl(rw.refNo,' - ')),' ',''), pnOrg.divCode, nvl(pnOrg.secCode,nvl(pnOrg.areaCode,pnOrg.divCode)),nvl(pnEmp.adminCode,chr(250)),pnEmp.levelCode desc, rw.empCode,rw.yearWork,rw.startDate ");

		System.out.println("HQL findByEmpCodeList ==>  " + hql.toString());

		List otList = this.getSession().createQuery(hql.toString()).list();
		RwOvertimeVO ret = null;
		List retList = new ArrayList();

		Double yearWork;
		Double periodWork, fiscalYear;
		Date startDate;
		Date endDate;
		
		String tempYearWork = "";
		String tempPeriodWork = "";
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy",
				new java.util.Locale("th", "TH"));
		if (otList.size() > 0) {

			try {
				for (Iterator itt = otList.iterator(); itt.hasNext();) {
					ret = new RwOvertimeVO();
					Object[] r = (Object[]) itt.next();

					ouCode = (String) r[0];
					Double yearPr = (Double) r[1];
					Double periodPr = (Double) r[2];
					otType = (String) r[3];
					String refNo = (String) r[4];
					String divCode = (String) r[5];
					String secCode = (String) r[6];
					String divDesc = (String) r[7];
					String secDesc = (String) r[8];
					String empcode = (String) r[9];
					String firstName = (String) r[10];
					String lastName = (String) r[11];
					String prefixName = (String) r[12];
					Double totDay15 = (Double) r[13];
					Double totDay1 = (Double) r[14];
					Double totDay3 = (Double) r[15];
					String flagPr = (String) r[16];
					String flagWork = (String) r[17];
					yearWork = (Double) r[18];
					periodWork = (Double) r[19];
					fiscalYear = (Double) r[20];
					Double workHour = (Double) r[21];
					startDate = (Date) r[22];
					endDate = (Date) r[23];
					Double amtDay1 = (Double) r[24];
					Double amtDay15 = (Double) r[25];
					Double amtDay3 = (Double) r[26];
				

					ret.setOuCode(ouCode);
					ret.setYearPr(yearPr);
					ret.setPeriodPr(periodPr);
					ret.setOtType(otType);
					ret.setRefNo(refNo);
					ret.setDivCode(divCode);
					ret.setSecCode(secCode);
					ret.setDivDesc(divDesc);
					ret.setSecDesc(secDesc);
					ret.setEmpCode(empcode);
					ret.setFirstName(firstName);
					ret.setLastName(lastName);
					ret.setPrefixName(prefixName);
					ret.setTotDay15(totDay15);
					ret.setTotDay1(totDay1);
					ret.setTotDay3(totDay3);
					ret.setFlagPr(flagPr);
					ret.setFlagWork(flagWork);
					ret.setAmtDay1(amtDay1);
					ret.setAmtDay15(amtDay15);
					ret.setAmtDay3(amtDay3);
					ret.setFiscalYear(fiscalYear);
					ret.setWorkHour(workHour);
					if (yearWork != null) {
						tempYearWork = yearWork.toString().substring(0, 4);
					}
					if (periodWork != null) {
						if (periodWork.toString().length() > 3) {
							tempPeriodWork = periodWork.toString().substring(0,
									2);
						} else {
							tempPeriodWork = periodWork.toString().substring(0,
									1);
						}

					}
					if (!tempYearWork.equals("") && !tempPeriodWork.equals("")) {
						ret.setYearPeriod(tempYearWork + "/" + tempPeriodWork);
					}
					if (startDate != null) {
						ret.setStartDateTemp(df.format(startDate));
					}
					if (endDate != null) {
						ret.setEndDateTemp(df.format(endDate));
					}
					retList.add(ret);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Empty--------------------");
		}

		return retList;
	}

}