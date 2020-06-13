/*
 * Created on 25 �.�. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ss.tp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.sql.DataSource;

import com.ss.tp.dao.TaMonthEmpWorkDAO;
import com.ss.tp.dto.TaMonthEmpWorkVO;
import com.ss.tp.model.FeeWgEmployee;
import com.ss.tp.model.FeeWgEmployeePK;
import com.ss.tp.model.PnEmployee;
import com.ss.tp.model.PnEmployeePK;
import com.ss.tp.model.TaControl;
import com.ss.tp.model.TaControlPk;
import com.ss.tp.model.TaMonthEmpWork;
import com.ss.tp.model.TaMonthEmpWorkPK;
import com.ss.tp.model.TaStatusWork;

/**
 * @author airsenaL
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class TaMonthEmpWorkDAOImpl extends HibernateDaoSupport implements
		TaMonthEmpWorkDAO, Serializable {
	private DataSource dataSource;

	/**
	 * @return Returns the dataSource.
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource
	 *            The dataSource to set.
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public TaMonthEmpWorkDAOImpl() {
		Locale.setDefault(Locale.US);
	}

	/**
	 * method ����Ѻ Query �����Ũҡ Table TA_MONTH_EMP_WORK
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param codeSeq
	 * @param userId
	 * @param maxRowPerPage
	 * @param pageNo
	 * @return
	 */
	public List listMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, String areaCode, String secCode,
			String workCode, String userId, Integer maxRowPerPage,
			Integer pageNo) {
		List lstEmp = new ArrayList();
		TaMonthEmpWorkVO tamVo;
		PnEmployee pnEmp;
		String sql = "";
		try {
			/* start create sql */
			sql = " select distinct tam.pnEmployee, tam.pnEmployee.refPnOrganizationWork.orgCode "
					+ " from TaMonthEmpWork tam, VPnEmployeeSecurityWork vorg "
					+ " where tam.taMonthEmpWorkPK.workYear = "
					+ workYear
					+ " and tam.taMonthEmpWorkPK.workMonth = "
					+ workMonth
					+ " and vorg.pk.ouCode = '"
					+ ouCode
					+ "'"
					+ " and vorg.pk.userId = '" + userId + "'";
			if (!"".equals(areaCode)) {
				sql += " and tam.pnEmployee.refPnOrganizationWork.areaCode = '"
						+ areaCode + "'";
			}
			if (!"".equals(secCode)) {
				sql += " and tam.pnEmployee.refPnOrganizationWork.secCode = '"
						+ secCode + "'";
			}
			if (!"".equals(workCode)) {
				sql += " and tam.pnEmployee.refPnOrganizationWork.workCode = '"
						+ workCode + "'";
			}
			sql += " and vorg.pk.codeSeqAct = tam.pnEmployee.refPnOrganizationWork.pk.codeSeq "
					+ " order by tam.pnEmployee.refPnOrganizationWork.orgCode, tam.pnEmployee.pk.empCode ";
			/* end create sql */
			Session sss = this.getSession();
			Query qry = sss.createQuery(sql);
			qry.setMaxResults(maxRowPerPage.intValue());
			qry.setFirstResult(pageNo.intValue() * maxRowPerPage.intValue());
			List tmpLst = qry.list();
			Object arrObj[] = null;
			for (Iterator itt = tmpLst.iterator(); itt.hasNext();) {
				// pnEmp = (PnEmployee)itt.next();
				arrObj = (Object[]) itt.next();
				pnEmp = (PnEmployee) arrObj[0];
				tamVo = new TaMonthEmpWorkVO();
				BeanUtils.copyProperties(tamVo, pnEmp);
				BeanUtils.copyProperties(tamVo, pnEmp.getPk());
				BeanUtils.copyProperties(tamVo, pnEmp.getRefDbPreSuff());
				lstEmp.add(tamVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstEmp;
	}

	/**
	 * method ����Ѻ Query �����ž�ѡ�ҹ�������¨ҡ Table TA_MONTH_EMP_WORK
	 * �����Ѻ�����ŷ�� query ��ҡ class TaMonthEmpWorkDAOImpl
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 * @return
	 */
	public List findByKey(Integer workYear, Integer workMonth, String empCode,
			String ouCode) {
		List rlst = new ArrayList();
		List lstEmp = new ArrayList();
		TaMonthEmpWorkVO tamVo = null;
		TaMonthEmpWork tam;
		TaMonthEmpWorkPK tamPk = new TaMonthEmpWorkPK();
		TaStatusWork tas = new TaStatusWork();
		try {
			List tmpLst = null;
			Session sss = this.getSession();
			String sql = " from TaStatusWork tas "
					+ " where tas.taStatusWorkPk.ouCode = '" + ouCode + "'"
					+ " and tas.workType = 'A' " + " or tas.workType = 'E' "
					+ " order by tas.taStatusWorkPk.workCode";
			tmpLst = this.getHibernateTemplate().find(sql);
			for (Iterator itt = tmpLst.iterator(); itt.hasNext();) {
				tas = (TaStatusWork) itt.next();
				tamVo = new TaMonthEmpWorkVO();
				BeanUtils.copyProperties(tamVo, tas.getTaStatusWorkPk());
				BeanUtils.copyProperties(tamVo, tas);
				lstEmp.add(tamVo);
			}
			if (lstEmp.size() > 0) {
				tmpLst = sss
						.createCriteria(TaMonthEmpWork.class)
						.add(Restrictions.eq("taMonthEmpWorkPK.workYear",
								workYear))
						.add(Restrictions.eq("taMonthEmpWorkPK.workMonth",
								workMonth))
						.add(Restrictions.eq("taMonthEmpWorkPK.empCode",
								empCode)).list();
				if (tmpLst.size() == 1) {
					Iterator tmpItt = tmpLst.iterator();
					tam = (TaMonthEmpWork) tmpItt.next();
					int i = 1;
					for (Iterator itt = lstEmp.iterator(); itt.hasNext(); i++) {
						tamVo = (TaMonthEmpWorkVO) itt.next();
						if ((tamVo.getWorkCode()).equals(tam.getWorkCode())) {
							BeanUtils.copyProperties(tamVo,
									tam.getTaMonthEmpWorkPK());
							BeanUtils.copyProperties(tamVo, tam);
						} else {
							tamVo.setWorkSeq(new Integer(i));
							tamVo.setTotalDays(new Double(0));
							tamVo.setTotalTime(new Integer(0));
						}
						rlst.add(tamVo);
					}
				} else {
					int i = 1;
					for (Iterator itt1 = lstEmp.iterator(); itt1.hasNext(); i++) {
						tamVo = (TaMonthEmpWorkVO) itt1.next();
						for (Iterator itt2 = tmpLst.iterator(); itt2.hasNext();) {
							tam = (TaMonthEmpWork) itt2.next();
							if (tamVo.getWorkCode().equals(tam.getWorkCode())) {
								BeanUtils.copyProperties(tamVo,
										tam.getTaMonthEmpWorkPK());
								BeanUtils.copyProperties(tamVo, tam);
								break;
							}
						}
						if (tamVo.getTotalDays() == null) {
							tamVo.setWorkSeq(new Integer(i));
							tamVo.setTotalDays(new Double(0));
							tamVo.setTotalTime(new Integer(0));
						}
						rlst.add(tamVo);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlst;
	}

	/**
	 * method ����Ѻ Query ���;�ѡ�ҹ�ҡ Table PN_EMPLOYEE �����Ѻ�����ŷ�� query ��ҡ
	 * class TaMonthEmpWorkDAOImpl
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 */
	public TaMonthEmpWorkVO findEmpByKey(Integer workYear, Integer workMonth,
			String empCode) {
		TaMonthEmpWorkVO tamVo = new TaMonthEmpWorkVO();
		TaMonthEmpWork tam = null;
		try {
			List tmpLst = null;
			Session sss = this.getSession();
			tmpLst = sss
					.createCriteria(TaMonthEmpWork.class)
					.add(Restrictions.eq("taMonthEmpWorkPK.workYear", workYear))
					.add(Restrictions.eq("taMonthEmpWorkPK.workMonth",
							workMonth))
					.add(Restrictions.eq("taMonthEmpWorkPK.empCode", empCode))
					.list();
			Iterator itt = tmpLst.iterator();
			if (itt.hasNext()) {
				tam = (TaMonthEmpWork) itt.next();
				BeanUtils.copyProperties(tamVo, tam.getPnEmployee());
				BeanUtils.copyProperties(tamVo, tam.getTaMonthEmpWorkPK());
				BeanUtils.copyProperties(tamVo, tam.getPnEmployee()
						.getRefDbPreSuff());
				BeanUtils.copyProperties(tamVo, tam);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tamVo;
	}

	/**
	 * method ����Ѻ���b�����ŧ Table TA_MONTH_EMP_WORK
	 * 
	 * @param ouCode
	 * @param year
	 * @param month
	 * @param empCode
	 * @param creby
	 * @param arrDay
	 * @param arrWorkCode
	 * @param arrWorkSeq
	 */
	public void addTaMonthEmpWork(String ouCode, Integer year, Integer month,
			String empCode, String creby, List arrDay, List arrWorkCode,
			List arrWorkSeq) {
		int size = arrDay.size();
		Object objDay = null;
		Object objTime = null;
		TaMonthEmpWork tam = null;
		TaMonthEmpWorkPK tamPk = null;
		for (int i = 0; i < size; i++) {
			objDay = arrDay.get(i);
			if (!"".equals(objDay) && !"".equals(objTime)) {
				tam = new TaMonthEmpWork();
				tamPk = new TaMonthEmpWorkPK();
				tamPk.setOuCode(ouCode);
				tamPk.setWorkYear(year);
				tamPk.setWorkMonth(month);
				tamPk.setEmpCode(empCode);
				tamPk.setWorkSeq(new Integer(arrWorkSeq.get(i).toString()));
				tam.setTaMonthEmpWorkPK(tamPk);
				tam.setCreBy(creby);
				tam.setCreDate(new Date());
				tam.setTotalDays(new Double(arrDay.get(i).toString()));
				tam.setWorkCode(arrWorkCode.get(i).toString());
				this.getHibernateTemplate().save(tam);
			}
		}
	}

	/**
	 * method ����Ѻ Update �����ž�ѡ�ҹ�������¨ҡ Table TA_MONTH_EMP_WORK
	 * �����Ѻ���Ѿ���á�� Update �����Ũҡ class TaMonthEmpWorkDAOImpl
	 * 
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 * @param userId
	 * @param arrDay
	 * @param arrWorkCode
	 * @param arrWorkSeq
	 */
	public void updateTaMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, String empCode, String userId, List arrDay,
			List arrWorkCode, List arrWorkSeq) {
		TaMonthEmpWork tam = null;
		TaMonthEmpWorkVO tamVo = null;
		TaMonthEmpWorkPK tamPk = null;
		int size = arrDay.size();
		String check = "";
		for (int i = 0; i < size; i++) {
			tamVo = new TaMonthEmpWorkVO();
			tamVo.setOuCode(ouCode);
			tamVo.setWorkYear(workYear);
			tamVo.setWorkMonth(workMonth);
			tamVo.setEmpCode(empCode);
			tamVo.setWorkSeq(new Integer(arrWorkSeq.get(i).toString()));
			tamVo.setUpdBy(userId);
			tamVo.setTotalDays(!"".equals(arrDay.get(i).toString()) ? new Double(
					arrDay.get(i).toString()) : new Double("0"));
			tamVo.setWorkCode(arrWorkCode.get(i).toString());
			check = this.checkBeforeUpdate(tamVo);
			if ("save".equals(check)) {
				tam = new TaMonthEmpWork();
				tamPk = new TaMonthEmpWorkPK();
				tamPk.setOuCode(ouCode);
				tamPk.setWorkYear(workYear);
				tamPk.setWorkMonth(workMonth);
				tamPk.setEmpCode(empCode);
				tamPk.setWorkSeq(new Integer(arrWorkSeq.get(i).toString()));
				tam.setTaMonthEmpWorkPK(tamPk);
				tam.setTotalDays(new Double(arrDay.get(i).toString()));
				tam.setWorkCode(arrWorkCode.get(i).toString());
				tam.setCreBy(userId);
				tam.setCreDate(new Date());
				this.getHibernateTemplate().save(tam);
			} else if ("update".equals(check)) {
				tam = this.loadTaMonthEmpWork(tamVo);
				tam.setTotalDays(tamVo.getTotalDays());
				tam.setTotalTime(tamVo.getTotalTime());
				tam.setWorkCode(tamVo.getWorkCode());
				tam.setUpdBy(tamVo.getUpdBy());
				tam.setUpdDate(new Date());
				this.getHibernateTemplate().update(tam);
			} else if ("delete".equals(check)) {
				tam = this.loadTaMonthEmpWork(tamVo);
				this.getHibernateTemplate().delete(tam);
			}
		}

	}

	/**
	 * method ����Ѻ Check ������բ����Ź������������� ���͵�Ǩ�ͺ����Ҥ�è� Insert,
	 * Update ���� Delete �����ŷ�� Table TA_MONTH_EMP_WORK
	 * 
	 * @param tamVo
	 * @return
	 */
	public String checkBeforeUpdate(TaMonthEmpWorkVO tamVo) {
		String check = "";
		TaMonthEmpWork tam = null;
		try {
			Session sss = this.getSession();
			List tmpLst = sss
					.createCriteria(TaMonthEmpWork.class)
					.add(Restrictions.eq("taMonthEmpWorkPK.ouCode",
							tamVo.getOuCode()))
					.add(Restrictions.eq("taMonthEmpWorkPK.workYear",
							tamVo.getWorkYear()))
					.add(Restrictions.eq("taMonthEmpWorkPK.workMonth",
							tamVo.getWorkMonth()))
					.add(Restrictions.eq("taMonthEmpWorkPK.empCode",
							tamVo.getEmpCode()))
					.add(Restrictions.eq("taMonthEmpWorkPK.workSeq",
							tamVo.getWorkSeq())).list();
			Iterator itt = tmpLst.iterator();

			if (tamVo.getTotalDays().doubleValue() == 0) {
				check = itt.hasNext() ? "delete" : "";
			} else if (itt.hasNext()) {
				check = "update";
			} else if (!itt.hasNext()) {
				check = "save";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	/**
	 * method ����Ѻ� Load ������ Table TA_MONTH_EMP_WORK
	 * 
	 * @param tamVo
	 * @return
	 */
	public TaMonthEmpWork loadTaMonthEmpWork(TaMonthEmpWorkVO tamVo) {
		TaMonthEmpWork tam = null;
		TaMonthEmpWorkPK tamPk = new TaMonthEmpWorkPK();
		try {
			BeanUtils.copyProperties(tamPk, tamVo);
			tam = (TaMonthEmpWork) this.getHibernateTemplate().load(
					TaMonthEmpWork.class, tamPk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tam;
	}

	
	/**
	 * method ����Ѻ� Delete ������ Table TA_MONTH_EMP_WORK
	 * 
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 * @return
	 */
	public String deleteTaMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, List empCode, String userId) {
		String rlst = "";
		TaMonthEmpWork tam = null;
		List tmpLst = null;
		String sql = "";
		Object tmpObj = null;
		List tmpEmpList = new ArrayList();
		int size = empCode.size();
		Iterator tmpItt = null;
		for (int i = 0; i < size; i++) {
			/*
			 * sql = " select con.flagClose " + " from VPnEmployeeSecurity vemp,
			 * TaControl con " + " where vemp.pk.ouCode = '" + ouCode + "'" + "
			 * and vemp.pk.userId = '" + userId + "'" +
			 * " and vemp.pk.empCode = '" + empCode.get(i).toString() + "'"+
			 * " and con.taControlPk.ouCode = '" + ouCode + "'" +
			 * " and con.taControlPk.taPeriod = " + workMonth + " and
			 * con.taControlPk.taYear = " + workYear + " and vemp.pk.codeSeq =
			 * con.taControlPk.codeSeq " ; tmpLst =
			 * this.getHibernateTemplate().find(sql); tmpItt =
			 * tmpLst.iterator(); if (tmpItt.hasNext()){ tmpObj = tmpItt.next();
			 * if ("Y".equals(tmpObj.toString())){ rlst += empCode.get(i) +
			 * "\n"; continue; }else if ("N".equals(tmpObj.toString())){ sql = "
			 * from TaMonthEmpWork as tam " + " where
			 * tam.taMonthEmpWorkPK.ouCode = '" + ouCode + "'" + " and
			 * tam.taMonthEmpWorkPK.workYear = " + workYear + " and
			 * tam.taMonthEmpWorkPK.workMonth = " + workMonth + " and
			 * tam.taMonthEmpWorkPK.empCode = '" + empCode.get(i).toString() +
			 * "'"; tmpLst = this.getHibernateTemplate().find(sql); for
			 * (Iterator itt=tmpLst.iterator();itt.hasNext();){ tam =
			 * (TaMonthEmpWork) itt.next();
			 * this.getHibernateTemplate().delete(tam); } } }else if
			 * (!tmpItt.hasNext()){ sql = " from TaMonthEmpWork as tam " + "
			 * where tam.taMonthEmpWorkPK.ouCode = '" + ouCode + "'" + " and
			 * tam.taMonthEmpWorkPK.workYear = " + workYear + " and
			 * tam.taMonthEmpWorkPK.workMonth = " + workMonth + " and
			 * tam.taMonthEmpWorkPK.empCode = '" + empCode.get(i).toString() +
			 * "'"; tmpLst = this.getHibernateTemplate().find(sql); for
			 * (Iterator itt=tmpLst.iterator();itt.hasNext();){ tam =
			 * (TaMonthEmpWork) itt.next();
			 * this.getHibernateTemplate().delete(tam); } }
			 */
			sql = " from TaMonthEmpWork as tam "
					+ " where tam.taMonthEmpWorkPK.ouCode = '" + ouCode + "'"
					+ " and tam.taMonthEmpWorkPK.workYear = " + workYear
					+ " and tam.taMonthEmpWorkPK.workMonth = " + workMonth
					+ " and tam.taMonthEmpWorkPK.empCode = '"
					+ empCode.get(i).toString() + "'";
			tmpLst = this.getHibernateTemplate().find(sql);
			for (Iterator itt = tmpLst.iterator(); itt.hasNext();) {
				tam = (TaMonthEmpWork) itt.next();
				this.getHibernateTemplate().delete(tam);
			}
		}
		return rlst;
	}

	/**
	 * method ����Ѻ�֧������ ������ Table TA_STATUS_WORK
	 * 
	 * @param ouCode
	 * @return
	 */
	public List findStatusWork(String ouCode) {
		TaMonthEmpWorkVO tamVo = null;
		TaStatusWork tas = null;
		List rlst = new ArrayList();
		try {
			String sql = " from TaStatusWork tas "
					+ " where tas.taStatusWorkPk.ouCode = '" + ouCode + "'"
					+ " and tas.workType = 'A' " + " or tas.workType = 'E' "
					+ " order by tas.taStatusWorkPk.workCode ";
			List tmpList = this.getHibernateTemplate().find(sql);
			int i = 1;
			for (Iterator itt = tmpList.iterator(); itt.hasNext(); i++) {
				tamVo = new TaMonthEmpWorkVO();
				tas = (TaStatusWork) itt.next();
				BeanUtils.copyProperties(tamVo, tas.getTaStatusWorkPk());
				BeanUtils.copyProperties(tamVo, tas);
				tamVo.setWorkSeq(new Integer(i));
				rlst.add(tamVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlst;
	}

	/**
	 * method ����Ѻ Count �ӹǹ��ѡ�ҹ Table TA_MONTH_EMP_WORK
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param codeSeq
	 * @param userId
	 * @return
	 */
	public int getCountTaMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, String areaCode, String secCode,
			String workCode, String userId) {
		int rlst = 0;
		try {
			String sql = "";
			/* start create sql */
			sql = " select count(distinct tam.pnEmployee.pk.empCode) "
					+ " from TaMonthEmpWork tam, VPnEmployeeSecurityWork vorg "
					+ " where tam.taMonthEmpWorkPK.workYear = " + workYear
					+ " and tam.taMonthEmpWorkPK.workMonth = " + workMonth
					+ " and vorg.pk.ouCode = '" + ouCode + "'"
					+ " and vorg.pk.userId = '" + userId + "'";
			if (!"".equals(areaCode)) {
				sql += " and tam.pnEmployee.refPnOrganizationWork.areaCode = '"
						+ areaCode + "'";
			}
			if (!"".equals(secCode)) {
				sql += " and tam.pnEmployee.refPnOrganizationWork.secCode = '"
						+ secCode + "'";
			}
			if (!"".equals(workCode)) {
				sql += " and tam.pnEmployee.refPnOrganizationWork.workCode = '"
						+ workCode + "'";
			}
			sql += " and vorg.pk.codeSeqAct = tam.pnEmployee.refPnOrganizationWork.pk.codeSeq "
					+ " order by tam.pnEmployee.refPnOrganizationWork.orgCode, tam.pnEmployee.pk.empCode ";
			/* end create sql */
			Session sss = this.getSession();
			Query qry = sss.createQuery(sql);
			rlst = Integer.parseInt(qry.list().get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlst;
	}

	/**
	 * method ����Ѻ Delete ������ Table TA_MONTH_EMP_WORK �˹�Ҩ� Update Status
	 * Work �ͧ��ѡ�ҹ
	 * 
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 * @param arrWorkSeq
	 * @return
	 */
	public void deleteEmpDtl(String ouCode, Integer workYear,
			Integer workMonth, String empCode, List arrWorkSeq) {
		int size = arrWorkSeq.size();
		List tmpLst = null;
		Session sss = this.getSession();
		TaMonthEmpWork tam = null;
		Iterator itt = null;
		String sql = "";
		for (int i = 0; i < size; i++) {
			sql = " from TaMonthEmpWork tam "
					+ " where tam.taMonthEmpWorkPK.ouCode = '" + ouCode + "'"
					+ " and tam.taMonthEmpWorkPK.workYear = " + workYear
					+ " and tam.taMonthEmpWorkPK.workMonth = " + workMonth
					+ " and tam.taMonthEmpWorkPK.empCode = '" + empCode + "'"
					+ " and tam.taMonthEmpWorkPK.workSeq = "
					+ arrWorkSeq.get(i);
			tmpLst = sss.createQuery(sql).list();
			itt = tmpLst.iterator();
			if (itt.hasNext()) {
				tam = (TaMonthEmpWork) itt.next();
				this.getHibernateTemplate().delete(tam);
			}
		}
	}

	/**
	 * method ����Ѻ���Ҫ��;�ѡ�ҹ
	 * 
	 * @param ouCode
	 * @param empCode
	 * @return
	 */
	public String findEmpName(String ouCode, String empCode) {
		PnEmployeePK pk = new PnEmployeePK();
		pk.setOuCode(ouCode);
		pk.setEmpCode(empCode);
		PnEmployee emp = (PnEmployee) this.getHibernateTemplate().load(
				PnEmployee.class, pk);
		String empName = "";
		try {
			empName = emp.getRefDbPreSuff().getPrefixName() + ""
					+ emp.getFirstName() + " " + emp.getLastName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empName;
	}

	/**
	 * method ����Ѻ Count �ӹǹ��ѡ�ҹ Table TA_MONTH_EMP_WORK
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 * @return
	 */
	public int getCountEmp(Integer workYear, Integer workMonth, String empCode) {
		int count = 0;
		try {
			String sql = " select distinct tam.pnEmployee "
					+ " from TaMonthEmpWork tam "
					+ " where tam.taMonthEmpWorkPK.workYear = " + workYear
					+ " and tam.taMonthEmpWorkPK.workMonth = " + workMonth
					+ " and tam.taMonthEmpWorkPK.empCode = '" + empCode + "'";
			Session sss = this.getSession();
			Query qry = sss.createQuery(sql);
			count = qry.list().size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * method ����Ѻ���Ҩӹǹ��ѡ�ҹ Table PN_EMPLOYEE ����ѧ����¶١�ѹ�֡���͹ ��лջѨ�غѹ
	 * 
	 * @param ouCode
	 * @param month
	 * @param year
	 * @param userId
	 * @param codeSeq
	 * @return
	 */
	public List findListEmpIsNotMonth(String ouCode, Integer month,
			Integer year, String userId, String areaCode, String secCode,
			String workCode) {
		List rlstList = new ArrayList();
		try {
			String sql = "select emp.pk.empCode from PnEmployee emp "
					+ " where emp.pk.ouCode = '" + ouCode + "'";
			if (!"".equals(areaCode)) {
				sql += " and emp.refPnOrganizationWork.areaCode = '" + areaCode
						+ "'";
			}
			if (!"".equals(secCode)) {
				sql += " and emp.refPnOrganizationWork.secCode = '" + secCode
						+ "'";
			}
			if (!"".equals(workCode)) {
				sql += " and emp.refPnOrganizationWork.workCode = '" + workCode
						+ "'";
			}
			sql += " and emp.pk.empCode in ( " + " select vemp.pk.empCode "
					+ " from VPnEmployeeSecurityWork vemp "
					+ " where vemp.pk.userId = '" + userId + "'"
					+ " and vemp.pk.ouCode = '" + ouCode + "'" + " )"
					+ " and emp.pk.empCode not in ( "
					+ " select tam.pnEmployee.pk.empCode"
					+ " from TaMonthEmpWork tam "
					+ " where tam.taMonthEmpWorkPK.workYear = " + year
					+ " and tam.taMonthEmpWorkPK.workMonth = " + month
					+ " and tam.taMonthEmpWorkPK.ouCode = '" + ouCode + "'"
					+ " ) " + " order by emp.pk.empCode ";
			List tmpList = this.getHibernateTemplate().find(sql);
			// TaMonthEmpWorkVO tamVo = null;
			// PnEmployee emp = null;
			// for (Iterator itt=tmpList.iterator();itt.hasNext();){
			// tamVo = new TaMonthEmpWorkVO();
			// emp = (PnEmployee) itt.next();
			// tamVo.setEmpCode(emp.getPk().getEmpCode());
			// rlstList.add(tamVo);
			// }
			System.out.println(tmpList.get(0).getClass() + "@@@@@"
					+ tmpList.size());
			rlstList = tmpList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlstList;
	}

	/**
	 * method ����Ѻ�ʴ���áͧ�ӹѡ�ҹ Table PN_EMPLOYEE
	 * 
	 * @param ouCode
	 * @param userId
	 * @param workMonth
	 * @param workYear
	 * @param codeSeq
	 * @return
	 */
	public List findListDivReportEmpMonth(String ouCode, String userId,
			Integer workMonth, Integer workYear, String areaCode,
			String secCode, String workCode) {
		List listEmp = new ArrayList();
		List list = new ArrayList();
		TaMonthEmpWorkVO tamVo = null;
		String sql = "";
		try {

			sql = "  select distinct nvl(tam.pnEmployee.refPnOrganizationWork.areaCode,tam.pnEmployee.refPnOrganizationWork.divCode) as areaCode ,"
					+ " nvl(tam.pnEmployee.refPnOrganizationWork.areaDesc,tam.pnEmployee.refPnOrganizationWork.divDesc) as areaDesc,"
					+ " tam.pnEmployee.refPnOrganizationWork.divCode,"
					+ " tam.pnEmployee.refPnOrganizationWork.divDesc "
					+ " from TaMonthEmpWork tam "
					+ " where tam.taMonthEmpWorkPK.workYear = "
					+ workYear
					+ " and tam.taMonthEmpWorkPK.workMonth = "
					+ workMonth
					+ " and tam.pnEmployee.codeSeqAct in ( "
					+ " 									select vorg.pk.codeSeq "
					+ " 									from VPnOrganizationSecurity vorg "
					+ " 									where vorg.pk.ouCode = '"
					+ ouCode
					+ "'"
					+ " 									and vorg.pk.userId = '" + userId + "'" +

					"									 ) ";
			if (!"".equals(areaCode)) {
				sql += " and tam.pnEmployee.refPnOrganizationWork.areaCode = '"
						+ areaCode + "'";
			}
			if (!"".equals(secCode)) {
				sql += " and tam.pnEmployee.refPnOrganizationWork.secCode = '"
						+ secCode + "'";
			}
			if (!"".equals(workCode)) {
				sql += " and tam.pnEmployee.refPnOrganizationWork.workCode = '"
						+ workCode + "'";
			}
			sql += " order by nvl(tam.pnEmployee.refPnOrganizationWork.areaCode,tam.pnEmployee.refPnOrganizationWork.divCode))";
			System.out.println("\n\n\n sql==>>" + sql);
			Session ss = this.getSession();
			Query qq = ss.createQuery(sql);
			listEmp = qq.list();

			String arerCode = "";
			String areaDesc = "";
			String divCode = "";
			String divDesc = "";
			for (int i = 0; i < listEmp.size(); i++) {
				Object[] obj = (Object[]) listEmp.get(i);
				arerCode = (String) obj[0];
				areaDesc = (String) obj[1];
				divCode = (String) obj[2];
				divDesc = (String) obj[3];
				tamVo = new TaMonthEmpWorkVO();
				tamVo.setAreaCode(arerCode);
				tamVo.setAreaDesc(areaDesc);
				tamVo.setDivCode(divCode);
				tamVo.setDivDesc(divDesc);
				list.add(tamVo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * method ����Ѻ�ʴ���§ҹ��÷ӧ�û�Ш���͹�ͧ��ѡ�ҹ Table PN_EMPLOYEE
	 * 
	 * @param ouCode
	 * @param userId
	 * @param workMonth
	 * @param workYear
	 * @param codeSeq
	 * @return
	 */
	// XXX
	public List findListReportEmpMonth(String ouCode, String userId,
			Integer workMonth, Integer workYear, String areaCode,
			String secCode, String workCode) {
		List listEmp = new ArrayList();
		List list = new ArrayList();
		TaMonthEmpWorkVO tamVo = null;
		String sql = "";
		try {
			sql = " select nvl(tam.pnEmployee.refPnOrganizationWork.areaCode,tam.pnEmployee.refPnOrganizationWork.divCode) as areaCode ,"
					+ " nvl(tam.pnEmployee.refPnOrganizationWork.areaDesc,tam.pnEmployee.refPnOrganizationWork.divDesc) as areaDesc, "
					+ " nvl(tam.pnEmployee.refPnOrganizationWork.secCode,nvl(tam.pnEmployee.refPnOrganizationWork.areaCode,tam.pnEmployee.refPnOrganizationWork.divCode)) as secCode,"
					+ " nvl(tam.pnEmployee.refPnOrganizationWork.secDesc,nvl(tam.pnEmployee.refPnOrganizationWork.areaDesc,tam.pnEmployee.refPnOrganizationWork.divDesc)) as secDesc,"
					+ " tam.pnEmployee.refPnOrganizationWork.workCode as orgWorkCode,"
					+ " tam.pnEmployee.refPnOrganizationWork.workDesc as orgWorkDesc,"
					+ " tam.pnEmployee.pk.empCode,"
					+ " tam.pnEmployee.refDbPreSuff.prefixName,"
					+ " tam.pnEmployee.firstName,"
					+ " tam.pnEmployee.lastName,"
					+ " tam.workCode,"
					+ " tam.taStatusWork.workDesc,"
					+ " tam.totalDays,"
					+ " tam.totalTime, "
					+ " tam.pnEmployee.refPnOrganizationWork.divCode,"
					+ " tam.pnEmployee.refPnOrganizationWork.divDesc "
					+ " from TaMonthEmpWork tam "
					+ " where tam.taMonthEmpWorkPK.workYear = "
					+ workYear
					+ " and tam.taMonthEmpWorkPK.workMonth = "
					+ workMonth
					+ " and tam.pnEmployee.codeSeqAct in ( "
					+ " 									select vorg.pk.codeSeq "
					+ " 									from VPnOrganizationSecurity vorg "
					+ " 									where vorg.pk.ouCode = '"
					+ ouCode
					+ "'"
					+ " 									and vorg.pk.userId = '"
					+ userId
					+ "'"
					+ "									 ) ";
			if (!"".equals(areaCode)) {
				sql += " and tam.pnEmployee.refPnOrganizationWiork.areaCode = '"
						+ areaCode + "'";
			}
			if (!"".equals(secCode)) {
				sql += " and tam.pnEmployee.refPnOrganizationWork.secCode = '"
						+ secCode + "'";
			}
			if (!"".equals(workCode)) {
				sql += " and tam.pnEmployee.refPnOrganizationWork.workCode = '"
						+ workCode + "'";
			}
			sql += " order by nvl(tam.pnEmployee.refPnOrganizationWork.workCode,nvl(tam.pnEmployee.refPnOrganizationWork.secCode,nvl(tam.pnEmployee.refPnOrganizationWork.areaCode,nvl(tam.pnEmployee.refPnOrganizationWork.divCode,nvl(tam.pnEmployee.refPnOrganizationWork.partCode,''))))),"
					+ " tam.pnEmployee.refPnOrganizationWork.orgCode,tam.pnEmployee.pk.empCode,tam.workCode ";

			Session ss = this.getSession();
			Query qq = ss.createQuery(sql);
			listEmp = qq.list();
			for (int i = 0; i < listEmp.size(); i++) {
				Object[] obj = (Object[]) listEmp.get(i);
				tamVo = new TaMonthEmpWorkVO();
				tamVo.setAreaCode((String) obj[0]);
				tamVo.setAreaDesc((String) obj[1]);
				tamVo.setSecCode((String) obj[2]);
				tamVo.setSecDesc((String) obj[3]);
				tamVo.setOrgWorkCode((String) obj[4]);
				tamVo.setOrgWorkDesc((String) obj[5]);
				tamVo.setEmpCode((String) obj[6]);
				tamVo.setFullName((String) obj[7] + " " + (String) obj[8] + " "
						+ (String) obj[9]);
				tamVo.setWorkCode((String) obj[10]);
				tamVo.setWorkDesc((String) obj[11]);
				tamVo.setTotalDays((Double) obj[12]);
				tamVo.setTotalTime((Integer) obj[13]);
				tamVo.setDivCode((String) obj[14]);
				tamVo.setDivDesc((String) obj[15]);
				list.add(tamVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * method ����Ѻ��÷ӡ�ûԴ�����Ż�Ш���͹�ͧ��ѡ�ҹ �� Insert ������ŧ Table TA_CONTROL
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param ouCode
	 * @param userId
	 * @return
	 */
	public boolean CheckDataForInsert(String ouCode, Integer workYear,
			Integer workMonth, String userId) {
		List tacontrolList = new ArrayList();
		tacontrolList = this
				.getSession()
				.createQuery(
						" select distinct p.taControlPk.ouCode "
								+ " from TaControl p "
								+ " where p.taControlPk.ouCode ='" + ouCode
								+ "' " + " and p.taControlPk.taYear ="
								+ workYear + " "
								+ " and p.taControlPk.taPeriod =" + workMonth
								+ " " + " and p.taControlPk.userId = '"
								+ userId + "' "
				// " and p.taControlPk.codeSeq ="+codeSeq+" "
				).list();
		if (tacontrolList == null || tacontrolList.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean CheckDataForUpdate(String ouCode, Integer workYear,
			Integer workMonth, Integer codeSeq) {
		List tacontrolList = new ArrayList();
		tacontrolList = this
				.getSession()
				.createQuery(
						" select distinct p.taControlPk.ouCode "
								+ " from TaControl p "
								+ " where p.taControlPk.ouCode ='" + ouCode
								+ "' " + " and p.taControlPk.taYear ="
								+ workYear + " "
								+ " and p.taControlPk.taPeriod =" + workMonth
								+ " " + " and p.taControlPk.codeSeq ="
								+ codeSeq + " " + " and p.flagClose ='N' ")
				.list();
		if (tacontrolList == null || tacontrolList.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public String addCloseMonthEmpWork(Integer workYear, Integer workMonth,
			String ouCode, String userId) {
		String rlst = "";
		TaControl con = new TaControl();
		TaControlPk conPk = new TaControlPk();
		boolean insertData = this.CheckDataForInsert(ouCode, workYear,
				workMonth, userId);
		if (insertData) {
			conPk.setOuCode(ouCode);
			conPk.setTaPeriod(workMonth);
			conPk.setTaYear(workYear);
			conPk.setUserId(userId);
			// conPk.setCodeSeq(new Integer(intTmp));
			con.setTaControlPk(conPk);
			con.setFlagClose("Y");
			con.setCreBy(userId);
			con.setUpdBy(userId);
			con.setCreDate(new Date());
			con.setUpdDate(new Date());
			this.getHibernateTemplate().save(con);
		} else {
			conPk.setOuCode(ouCode);
			conPk.setTaPeriod(workMonth);
			conPk.setTaYear(workYear);
			conPk.setUserId(userId);
			// conPk.setCodeSeq(new Integer(intTmp));
			con = (TaControl) this.getHibernateTemplate().load(TaControl.class,
					conPk);
			con.setFlagClose("Y");
			con.setUpdBy(userId);
			con.setUpdDate(new Date());
			this.getHibernateTemplate().update(con);
		}
		rlst = "�����Ţͧ��͹��лչ��١�Դ���º��������";
		return rlst;
	}

	/*
	 * public String addCloseMonthEmpWork(Integer workYear, Integer workMonth,
	 * String ouCode, String userId){ String rlst = ""; String sql = "";
	 * 
	 * sql = " select vorg.pk.codeSeq " + " from VPnOrganizationSecurity vorg "
	 * + " where vorg.pk.userId = '" + userId + "'" + " and vorg.pk.ouCode = '"
	 * + ouCode + "'"; List tmpList = this.getHibernateTemplate().find(sql);
	 * Iterator itt = tmpList.iterator(); TaControl con = null; TaControlPk
	 * conPk =null; int intTmp; for (itt=tmpList.iterator();itt.hasNext();){
	 * conPk = new TaControlPk(); con = new TaControl(); intTmp =
	 * (int)Double.parseDouble(itt.next().toString()); boolean insertData =
	 * this.CheckDataForInsert(ouCode, workYear, workMonth,new Integer(intTmp));
	 * if (insertData) { conPk.setOuCode(ouCode); conPk.setTaPeriod(workMonth);
	 * conPk.setTaYear(workYear); conPk.setCodeSeq(new Integer(intTmp));
	 * con.setTaControlPk(conPk); con.setFlagClose("Y"); con.setCreBy(userId);
	 * con.setUpdBy(userId); con.setCreDate(new Date()); con.setUpdDate(new
	 * Date()); this.getHibernateTemplate().save(con); } else { boolean
	 * updateData = this.CheckDataForUpdate(ouCode, workYear, workMonth,new
	 * Integer(intTmp)); if (updateData) { conPk.setOuCode(ouCode);
	 * conPk.setTaPeriod(workMonth); conPk.setTaYear(workYear);
	 * conPk.setCodeSeq(new Integer(intTmp)); con =
	 * (TaControl)this.getHibernateTemplate().load(TaControl.class, conPk);
	 * con.setFlagClose("Y"); con.setUpdBy(userId); con.setUpdDate(new Date());
	 * this.getHibernateTemplate().update(con); } } } rlst =
	 * "�����Ţͧ��͹��лչ��١�Դ���º��������";
	 * 
	 * return rlst; }
	 */

	/**
	 * method ����Ѻ��Ǩ�ͺ��͹�ӡ�� Insert �����ž�ѡ�ҹ �µ�Ǩ�ͺ��Ң����Ż�
	 * �Ǵ������ʡͧ�ӹѡ�ҹ����¶١�Դ�������
	 * 
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @param areaCode
	 * @param secCode
	 * @param workCode
	 * @param userId
	 */

	public String getStatusBeforeAddEmployee(String ouCode, Integer workYear,
			Integer workMonth, String areaCode, String secCode,
			String workCode, String userId) {
		String rlst = "";
		try {
			int tmpInt;
			List tmpList = new ArrayList();
			/*
			 * String hql = " select distinct vorg.pk.codeSeq " + " from
			 * VPnOrganizationSecurity vorg, PnOrganization org " + " where
			 * vorg.pk.ouCode = '" + ouCode + "'" + " and vorg.pk.userId = '" +
			 * userId + "'" + " and org.pk.ouCode = '" + ouCode + "'" ; if
			 * (!"".equals(areaCode)){ hql += " and org.areaCode = '" + areaCode
			 * + "'"; } if (!"".equals(secCode)){ hql += " and org.secCode = '"
			 * + secCode + "'"; } if (!"".equals(workCode)){ hql += " and
			 * org.workCode = '" + workCode + "'"; } hql += " and org.pk.codeSeq
			 * = vorg.pk.codeSeq "; tmpList =
			 * this.getHibernateTemplate().find(hql); tmpInt = tmpList.size();
			 * if (tmpInt > 0){ hql += " and vorg.pk.codeSeq not in ( " + "
			 * select con.taControlPk.codeSeq " + " from TaControl con" + "
			 * where con.taControlPk.ouCode = '" + ouCode + "'" + " and
			 * con.taControlPk.taYear = " + workYear + " and
			 * con.taControlPk.taPeriod = " + workMonth + " and con.flagClose =
			 * 'Y' " + " ) ";
			 */

			String hql = " select con.taControlPk.userId "
					+ " from TaControl con"
					+ " where con.taControlPk.ouCode = '" + ouCode + "'"
					+ " and con.taControlPk.taYear = " + workYear
					+ " and con.taControlPk.taPeriod = " + workMonth
					+ " and con.taControlPk.userId   = '" + userId + "'"
					+ " and con.flagClose = 'Y' ";
			tmpList = this.getHibernateTemplate().find(hql);
			if (tmpList == null || tmpList.size() == 0) {
				rlst = "N";
			} else {
				rlst = "Y";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlst;
	}

	/**
	 * method ����Ѻ��Ǩ�ͺ��͹�ӡ�� Update �����ž�ѡ�ҹ �µ�Ǩ�ͺ��Ң����Ż�
	 * �Ǵ������ʡͧ�ӹѡ�ҹ����¶١�Դ�������
	 * 
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 */
	public String getStatusUpdateEmployee(String ouCode, Integer workYear,
			Integer workMonth, String empCode, String userId) {
		String rlst = "";
		try {
			/*
			 * String hql = " select con.flagClose " + " from
			 * VPnEmployeeSecurity vemp, TaControl con " + " where
			 * vemp.pk.empCode = '" + empCode + "'" + " and vemp.pk.ouCode = '"
			 * + ouCode + "'" + " and vemp.pk.userId = '" + userId + "'" + " and
			 * con.taControlPk.ouCode = '" + ouCode + "'" + " and
			 * con.taControlPk.taPeriod = " + workMonth + " and
			 * con.taControlPk.taYear = " + workYear + " and vemp.pk.codeSeq =
			 * con.taControlPk.codeSeq ";
			 * 
			 * 
			 * 
			 * List tmpList = this.getHibernateTemplate().find(hql); Iterator
			 * itt = tmpList.iterator(); if (itt.hasNext()){ String tmpStr =
			 * itt.next().toString(); if ("Y".equals(tmpStr)){ rlst = tmpStr;
			 * }else{ rlst = "N"; } }else{ rlst = "N"; }
			 */
			String hql = " select con.taControlPk.userId "
					+ " from TaControl con"
					+ " where con.taControlPk.ouCode = '" + ouCode + "'"
					+ " and con.taControlPk.taYear = " + workYear
					+ " and con.taControlPk.taPeriod = " + workMonth
					+ " and con.taControlPk.userId   = '" + userId + "'"
					+ " and con.flagClose = 'Y' ";

			List tmpList = this.getHibernateTemplate().find(hql);
			if (tmpList == null || tmpList.size() == 0) {
				rlst = "N";
			} else {
				rlst = "Y";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlst;
	}
	
	public void updateTaMonthEmpWork(String ouCode,String empCode,String userId,String prefixName) {
		/*FeeWgEmployee tam = new FeeWgEmployee();
		//FeeWgPnEmployeeVO tamVo =  new FeeWgPnEmployeeVO();
		FeeWgEmployeePK tamPk =new FeeWgEmployeePK();
		
	
		tamPk.setOuCode(ouCode);
		tamPk.setEmpCode(empCode);
		
		// conPk.setCodeSeq(new Integer(intTmp));
		tam = (FeeWgEmployee) this.getHibernateTemplate().load(FeeWgEmployee.class,tamPk);
		//tam.setFlagClose("Y");
		tam.setUpdBy(userId);
		tam.setUpdDate(new Date());
		//tam.setPreName(prefixName);
		//tam.setFirstName(firstName);
		//tam.setLastName(lastName);
		//tam.setMaritalStatus(marritedStatus);
		//tam.setPayFlag(payStatus);
		//tam.setEmpStatus(empStatus);
		//tam.setCodeSeqAct(Double.valueOf(codeSeqAct));
		this.getHibernateTemplate().update(tam);*/
		System.out.println("inside updateTaMonthEmpWork");
		
	}


}
