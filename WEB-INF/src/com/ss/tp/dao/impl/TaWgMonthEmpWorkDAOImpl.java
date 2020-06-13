/*
 * Created on 27 ?.?. 2549
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

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ss.tp.dao.TaWgMonthEmpWorkDAO;
import com.ss.tp.dto.TaMonthEmpWorkVO;
import com.ss.tp.dto.TaWgMonthEmpWorkVO;
import com.ss.tp.model.TaWgControl;
import com.ss.tp.model.TaWgControlPk;
import com.ss.tp.model.TaWgMonthEmpWork;
import com.ss.tp.model.TaWgMonthEmpWorkPK;
import com.ss.tp.model.TaWgStatusWork;
import com.ss.tp.model.WgEmployee;
import com.ss.tp.model.WgEmployeePK;

/**
 * @author airsenal
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class TaWgMonthEmpWorkDAOImpl extends HibernateDaoSupport implements
		TaWgMonthEmpWorkDAO, Serializable {
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * method ����Ѻ Query �����Ũҡ Table TA_WG_MONTH_EMP_WORK
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
		WgEmployee pnEmp;
		String sql = "";
		try {
			/* start create sql */
			sql = " select distinct tam.wgEmployee, tam.wgEmployee.refPnOrganization.orgCode "
					+ " from TaWgMonthEmpWork tam, VWgEmployeeSecurity vorg "
					+ " where tam.taWgMonthEmpWorkPK.workYear = "
					+ workYear
					+ " and tam.taWgMonthEmpWorkPK.workMonth = "
					+ workMonth
					+ " and vorg.pk.ouCode = '"
					+ ouCode
					+ "'"
					+ " and vorg.pk.userId = '" + userId + "'";
			if (!"".equals(areaCode)) {
				sql += " and tam.wgEmployee.refPnOrganization.areaCode = '"
						+ areaCode + "'";
			}
			if (!"".equals(secCode)) {
				sql += " and tam.wgEmployee.refPnOrganization.secCode = '"
						+ secCode + "'";
			}
			if (!"".equals(workCode)) {
				sql += " and tam.wgEmployee.refPnOrganization.workCode = '"
						+ workCode + "'";
			}
			sql += " and vorg.pk.codeSeq = tam.wgEmployee.refPnOrganization.pk.codeSeq "
					+ " order by tam.wgEmployee.refPnOrganization.orgCode, tam.wgEmployee.wgEmployeePK.empCode ";
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
				pnEmp = (WgEmployee) arrObj[0];
				tamVo = new TaMonthEmpWorkVO();
				BeanUtils.copyProperties(tamVo, pnEmp);
				BeanUtils.copyProperties(tamVo, pnEmp.getWgEmployeePK());
				BeanUtils.copyProperties(tamVo, pnEmp.getRefDbPreSuff());
				lstEmp.add(tamVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstEmp;
	}

	/**
	 * method ����Ѻ Count �ӹǹ��ѡ�ҹ Table TA_WG_MONTH_EMP_WORK
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
			sql = " select count(distinct tam.wgEmployee.wgEmployeePK.empCode) "
					+ " from TaWgMonthEmpWork tam, VWgEmployeeSecurity vorg "
					+ " where tam.taWgMonthEmpWorkPK.workYear = "
					+ workYear
					+ " and tam.taWgMonthEmpWorkPK.workMonth = "
					+ workMonth
					+ " and vorg.pk.ouCode = '"
					+ ouCode
					+ "'"
					+ " and vorg.pk.userId = '" + userId + "'";
			if (!"".equals(areaCode)) {
				sql += " and tam.wgEmployee.refPnOrganization.areaCode = '"
						+ areaCode + "'";
			}
			if (!"".equals(secCode)) {
				sql += " and tam.wgEmployee.refPnOrganization.secCode = '"
						+ secCode + "'";
			}
			if (!"".equals(workCode)) {
				sql += " and tam.wgEmployee.refPnOrganization.workCode = '"
						+ workCode + "'";
			}
			sql += " and vorg.pk.codeSeq = tam.wgEmployee.refPnOrganization.pk.codeSeq "
					+ " order by tam.wgEmployee.refPnOrganization.orgCode, tam.wgEmployee.wgEmployeePK.empCode ";
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
	 * method ����Ѻ check �����͹��лչ���¶١�Դ�����������
	 * 
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @return
	 */
	public int getCheckCloseThisMonth(String ouCode, Integer workYear,
			Integer workMonth) {
		int rlst = 0;
		try {
			String sql = " from TaWgControl con "
					+ " where con.taWgControlPk.ouCode = '" + ouCode + "'"
					+ " and con.taWgControlPk.taYear = " + workYear
					+ " and con.taWgControlPk.taPeriod = " + workMonth;
			List tmpLst = this.getHibernateTemplate().find(sql);
			Iterator itt = tmpLst.iterator();
			if (itt.hasNext()) {
				rlst = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlst; // return 1 ����� �բ�������͹��лչ�鹶١�Դ���� ��
		// 0
		// �����ѧ���١�Դ
	}

	/**
	 * method ����Ѻ���Ҫ��;�ѡ�ҹ
	 * 
	 * @param ouCode
	 * @param empCode
	 * @return
	 */
	public String findEmpName(String ouCode, String empCode) {
		WgEmployeePK pk = new WgEmployeePK();
		pk.setOuCode(ouCode);
		pk.setEmpCode(empCode);
		WgEmployee emp = (WgEmployee) this.getHibernateTemplate().load(
				WgEmployee.class, pk);
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
	 * method ����Ѻ Query ���;�ѡ�ҹ�ҡ Table WG_EMPLOYEE �����Ѻ�����ŷ�� query ��ҡ
	 * class TaWgMonthEmpWorkDAOImpl
	 * 
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 * @return
	 */
	public TaWgMonthEmpWorkVO findEmpByKey(Integer workYear, Integer workMonth,
			String empCode) {
		TaWgMonthEmpWorkVO tamVo = new TaWgMonthEmpWorkVO();
		TaWgMonthEmpWork tam = null;
		try {
			List tmpLst = null;
			Session sss = this.getSession();
			tmpLst = sss
					.createCriteria(TaWgMonthEmpWork.class)
					.add(Restrictions.eq("taWgMonthEmpWorkPK.workYear",
							workYear))
					.add(Restrictions.eq("taWgMonthEmpWorkPK.workMonth",
							workMonth))
					.add(Restrictions.eq("taWgMonthEmpWorkPK.empCode", empCode))
					.list();
			Iterator itt = tmpLst.iterator();
			if (itt.hasNext()) {
				tam = (TaWgMonthEmpWork) itt.next();
				BeanUtils.copyProperties(tamVo, tam.getWgEmployee());
				BeanUtils.copyProperties(tamVo, tam.getTaWgMonthEmpWorkPK());
				BeanUtils.copyProperties(tamVo, tam.getWgEmployee()
						.getRefDbPreSuff());
				BeanUtils.copyProperties(tamVo, tam);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tamVo;
	}

	/**
	 * method ����Ѻ�֧������ ������ Table TA_STATUS_WORK
	 * 
	 * @param ouCode
	 * @return
	 */
	public List findStatusWork(String ouCode) {
		TaWgMonthEmpWorkVO tamVo = null;
		TaWgStatusWork tas = null;
		List rlst = new ArrayList();
		try {
			String sql = " from TaWgStatusWork tas "
					+ " where tas.taWgStatusWorkPk.ouCode = '" + ouCode + "'"
					+ " and tas.workType = 'A' " + " or tas.workType = 'W' "
					+ " order by tas.taWgStatusWorkPk.workCode ";
			List tmpList = this.getHibernateTemplate().find(sql);
			int i = 1;
			for (Iterator itt = tmpList.iterator(); itt.hasNext(); i++) {
				tamVo = new TaWgMonthEmpWorkVO();
				tas = (TaWgStatusWork) itt.next();
				BeanUtils.copyProperties(tamVo, tas.getTaWgStatusWorkPk());
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
	 * method ����Ѻ���b�����ŧ Table TA_WG_MONTH_EMP_WORK
	 * 
	 * @param ouCode
	 * @param year
	 * @param month
	 * @param creby
	 * @param arrDay
	 * @param arrTime
	 * @param arrWorkCode
	 * @param arrWorkSeq
	 */
	public void addTaMonthEmpWork(String ouCode, Integer year, Integer month,
			String empCode, String creby, List arrDay, List arrWorkCode,
			List arrWorkSeq) {
		int size = arrDay.size();
		Object objDay = null;
		Object objTime = null;
		TaWgMonthEmpWork tam = null;
		TaWgMonthEmpWorkPK tamPk = null;
		for (int i = 0; i < size; i++) {
			objDay = arrDay.get(i);
			if (!"".equals(objDay) && !"".equals(objTime)) {
				tam = new TaWgMonthEmpWork();
				tamPk = new TaWgMonthEmpWorkPK();
				tamPk.setOuCode(ouCode);
				tamPk.setWorkYear(year);
				tamPk.setWorkMonth(month);
				tamPk.setEmpCode(empCode);
				tamPk.setWorkSeq(new Integer(arrWorkSeq.get(i).toString()));
				tam.setTaWgMonthEmpWorkPK(tamPk);
				tam.setCreBy(creby);
				tam.setCreDate(new Date());
				tam.setTotalDays(new Double(arrDay.get(i).toString()));
				tam.setWorkCode(arrWorkCode.get(i).toString());
				this.getHibernateTemplate().save(tam);
			}
		}
	}

	/**
	 * method ����Ѻ���Ҩӹǹ��ѡ�ҹ Table WG_EMPLOYEE ����ѧ����¶١�ѹ�֡���͹ ��лջѨ�غѹ
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
			String sql = " select emp.wgEmployeePK.empCode from WgEmployee emp "
					+ " where emp.wgEmployeePK.ouCode = '" + ouCode + "'";
			if (!"".equals(areaCode)) {
				sql += " and emp.refPnOrganization.areaCode = '" + areaCode
						+ "'";
			}
			if (!"".equals(secCode)) {
				sql += " and emp.refPnOrganization.secCode = '" + secCode + "'";
			}
			if (!"".equals(workCode)) {
				sql += " and emp.refPnOrganization.workCode = '" + workCode
						+ "'";
			}
			sql += " and emp.wgEmployeePK.empCode in ( "
					+ " select vemp.pk.empCode "
					+ " from VWgEmployeeSecurity vemp "
					+ " where vemp.pk.userId = '" + userId + "'"
					+ " and vemp.pk.ouCode = '" + ouCode + "'" + " )"
					+ " and emp.wgEmployeePK.empCode not in ( "
					+ " select tam.wgEmployee.wgEmployeePK.empCode"
					+ " from TaWgMonthEmpWork tam "
					+ " where tam.taWgMonthEmpWorkPK.workYear = " + year
					+ " and tam.taWgMonthEmpWorkPK.workMonth = " + month
					+ " and tam.taWgMonthEmpWorkPK.ouCode = '" + ouCode + "'"
					+ " ) " + " order by emp.wgEmployeePK.empCode ";
			rlstList = this.getHibernateTemplate().find(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlstList;
	}

	/**
	 * method ����Ѻ� Delete ������ Table TA_WG_MONTH_EMP_WORK
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
		TaWgMonthEmpWork tam = null;
		List tmpLst = null;
		String sql = "";
		Object tmpObj = null;
		List tmpEmpList = new ArrayList();
		int size = empCode.size();
		Iterator tmpItt = null;
		for (int i = 0; i < size; i++) {
			/*
			 * sql = " select con.flagClose " + " from VWgEmployeeSecurity vemp,
			 * TaWgControl con " + " where vemp.pk.ouCode = '" + ouCode + "'" +
			 * " and vemp.pk.userId = '" + userId + "'" +
			 * " and vemp.pk.empCode = '" + empCode.get(i).toString() + "'"+
			 * " and con.taWgControlPk.ouCode = '" + ouCode + "'" +
			 * " and con.taWgControlPk.taPeriod = " + workMonth + " and
			 * con.taWgControlPk.taYear = " + workYear + " and vemp.pk.codeSeq =
			 * con.taWgControlPk.codeSeq " ; tmpLst =
			 * this.getHibernateTemplate().find(sql); tmpItt =
			 * tmpLst.iterator(); if (tmpItt.hasNext()){ tmpObj = tmpItt.next();
			 * if ("Y".equals(tmpObj.toString())){ rlst += empCode.get(i) +
			 * "\n"; continue; } sql = " from TaWgMonthEmpWork as tam " + "
			 * where tam.taWgMonthEmpWorkPK.ouCode = '" + ouCode + "'" + " and
			 * tam.taWgMonthEmpWorkPK.workYear = " + workYear + " and
			 * tam.taWgMonthEmpWorkPK.workMonth = " + workMonth + " and
			 * tam.taWgMonthEmpWorkPK.empCode = '" + empCode.get(i).toString() +
			 * "'"; tmpLst = this.getHibernateTemplate().find(sql); for
			 * (Iterator itt=tmpLst.iterator();itt.hasNext();){ tam =
			 * (TaWgMonthEmpWork) itt.next();
			 * this.getHibernateTemplate().delete(tam); } }else
			 * if(!tmpItt.hasNext()){ sql = " from TaWgMonthEmpWork as tam " + "
			 * where tam.taWgMonthEmpWorkPK.ouCode = '" + ouCode + "'" + " and
			 * tam.taWgMonthEmpWorkPK.workYear = " + workYear + " and
			 * tam.taWgMonthEmpWorkPK.workMonth = " + workMonth + " and
			 * tam.taWgMonthEmpWorkPK.empCode = '" + empCode.get(i).toString() +
			 * "'"; tmpLst = this.getHibernateTemplate().find(sql); for
			 * (Iterator itt=tmpLst.iterator();itt.hasNext();){ tam =
			 * (TaWgMonthEmpWork) itt.next();
			 * this.getHibernateTemplate().delete(tam); } }
			 */
			sql = " from TaWgMonthEmpWork as tam "
					+ " where tam.taWgMonthEmpWorkPK.ouCode = '" + ouCode + "'"
					+ " and tam.taWgMonthEmpWorkPK.workYear = " + workYear
					+ " and tam.taWgMonthEmpWorkPK.workMonth = " + workMonth
					+ " and tam.taWgMonthEmpWorkPK.empCode = '"
					+ empCode.get(i).toString() + "'";
			tmpLst = this.getHibernateTemplate().find(sql);
			for (Iterator itt = tmpLst.iterator(); itt.hasNext();) {
				tam = (TaWgMonthEmpWork) itt.next();
				this.getHibernateTemplate().delete(tam);
			}
		}
		return rlst;
	}

	/**
	 * method ����Ѻ Query �����ž�ѡ�ҹ�������¨ҡ Table TA_WG_MONTH_EMP_WORK
	 * �����Ѻ�����ŷ�� query ��ҡ class TaWgMonthEmpWorkDAOImpl
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
		TaWgMonthEmpWorkVO tamVo = null;
		TaWgMonthEmpWork tam;
		TaWgMonthEmpWorkPK tamPk = new TaWgMonthEmpWorkPK();
		TaWgStatusWork tas = new TaWgStatusWork();
		try {
			List tmpLst = null;
			Session sss = this.getSession();
			String sql = " from TaWgStatusWork tas "
					+ " where tas.taWgStatusWorkPk.ouCode = '" + ouCode + "'"
					+ " and tas.workType = 'A' " + " or tas.workType = 'W' "
					+ " order by tas.taWgStatusWorkPk.workCode";
			tmpLst = this.getHibernateTemplate().find(sql);
			for (Iterator itt = tmpLst.iterator(); itt.hasNext();) {
				tas = (TaWgStatusWork) itt.next();
				tamVo = new TaWgMonthEmpWorkVO();
				BeanUtils.copyProperties(tamVo, tas.getTaWgStatusWorkPk());
				BeanUtils.copyProperties(tamVo, tas);
				lstEmp.add(tamVo);
			}
			if (lstEmp.size() > 0) {
				tmpLst = sss
						.createCriteria(TaWgMonthEmpWork.class)
						.add(Restrictions.eq("taWgMonthEmpWorkPK.workYear",
								workYear))
						.add(Restrictions.eq("taWgMonthEmpWorkPK.workMonth",
								workMonth))
						.add(Restrictions.eq("taWgMonthEmpWorkPK.empCode",
								empCode)).list();
				if (tmpLst.size() == 1) {
					Iterator tmpItt = tmpLst.iterator();
					tam = (TaWgMonthEmpWork) tmpItt.next();
					int i = 1;
					for (Iterator itt = lstEmp.iterator(); itt.hasNext(); i++) {
						tamVo = (TaWgMonthEmpWorkVO) itt.next();
						if ((tamVo.getWorkCode()).equals(tam.getWorkCode())) {
							BeanUtils.copyProperties(tamVo,
									tam.getTaWgMonthEmpWorkPK());
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
						tamVo = (TaWgMonthEmpWorkVO) itt1.next();
						for (Iterator itt2 = tmpLst.iterator(); itt2.hasNext();) {
							tam = (TaWgMonthEmpWork) itt2.next();
							if (tamVo.getWorkCode().equals(tam.getWorkCode())) {
								BeanUtils.copyProperties(tamVo,
										tam.getTaWgMonthEmpWorkPK());
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
	 * method ����Ѻ Update �����ž�ѡ�ҹ�������¨ҡ Table TA_WG_MONTH_EMP_WORK
	 * �����Ѻ���Ѿ���á�� Update �����Ũҡ class TaWgMonthEmpWorkDAOImpl
	 * 
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @param empCode
	 * @param userId
	 * @param arrDay
	 * @param arrTime
	 * @param arrWorkCode
	 * @param arrWorkSeq
	 */
	public void updateTaMonthEmpWork(String ouCode, Integer workYear,
			Integer workMonth, String empCode, String userId, List arrDay,
			List arrWorkCode, List arrWorkSeq) {
		TaWgMonthEmpWork tam = null;
		TaWgMonthEmpWorkVO tamVo = null;
		TaWgMonthEmpWorkPK tamPk = null;
		int size = arrDay.size();
		String check = "";
		for (int i = 0; i < size; i++) {
			tamVo = new TaWgMonthEmpWorkVO();
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
				tam = new TaWgMonthEmpWork();
				tamPk = new TaWgMonthEmpWorkPK();
				tamPk.setOuCode(ouCode);
				tamPk.setWorkYear(workYear);
				tamPk.setWorkMonth(workMonth);
				tamPk.setEmpCode(empCode);
				tamPk.setWorkSeq(new Integer(arrWorkSeq.get(i).toString()));
				tam.setTaWgMonthEmpWorkPK(tamPk);
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
	 * Update ���� Delete �����ŷ�� Table TA_WG_MONTH_EMP_WORK
	 * 
	 * @param tamPk
	 * @return
	 */
	public String checkBeforeUpdate(TaWgMonthEmpWorkVO tamVo) {
		String check = "";
		TaWgMonthEmpWork tam = null;
		try {
			Session sss = this.getSession();
			List tmpLst = sss
					.createCriteria(TaWgMonthEmpWork.class)
					.add(Restrictions.eq("taWgMonthEmpWorkPK.ouCode",
							tamVo.getOuCode()))
					.add(Restrictions.eq("taWgMonthEmpWorkPK.workYear",
							tamVo.getWorkYear()))
					.add(Restrictions.eq("taWgMonthEmpWorkPK.workMonth",
							tamVo.getWorkMonth()))
					.add(Restrictions.eq("taWgMonthEmpWorkPK.empCode",
							tamVo.getEmpCode()))
					.add(Restrictions.eq("taWgMonthEmpWorkPK.workSeq",
							tamVo.getWorkSeq())).list();
			Iterator itt = tmpLst.iterator();

			if (tamVo.getTotalDays().doubleValue() == 0) {
				check = itt.hasNext() ? "delete" : "noProcess";
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
	 * method ����Ѻ� Load ������ Table TA_WG_MONTH_EMP_WORK
	 * 
	 * @param tamVo
	 * @return
	 */
	public TaWgMonthEmpWork loadTaMonthEmpWork(TaWgMonthEmpWorkVO tamVo) {
		TaWgMonthEmpWork tam = null;
		TaWgMonthEmpWorkPK tamPk = new TaWgMonthEmpWorkPK();
		try {
			BeanUtils.copyProperties(tamPk, tamVo);
			tam = (TaWgMonthEmpWork) this.getHibernateTemplate().load(
					TaWgMonthEmpWork.class, tamPk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tam;
	}

	/**
	 * method ����Ѻ Delete ������ Table TA_WG_MONTH_EMP_WORK �˹�Ҩ� Update Status
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
		TaWgMonthEmpWork tam = null;
		Iterator itt = null;
		String sql = "";
		for (int i = 0; i < size; i++) {
			sql = " from TaWgMonthEmpWork tam "
					+ " where tam.taWgMonthEmpWorkPK.ouCode = '" + ouCode + "'"
					+ " and tam.taWgMonthEmpWorkPK.workYear = " + workYear
					+ " and tam.taWgMonthEmpWorkPK.workMonth = " + workMonth
					+ " and tam.taWgMonthEmpWorkPK.empCode = '" + empCode + "'"
					+ " and tam.taWgMonthEmpWorkPK.workSeq = "
					+ arrWorkSeq.get(i);
			tmpLst = sss.createQuery(sql).list();
			itt = tmpLst.iterator();
			if (itt.hasNext()) {
				tam = (TaWgMonthEmpWork) itt.next();
				this.getHibernateTemplate().delete(tam);
			}
		}
	}

	/**
	 * method ����Ѻ��÷ӡ�ûԴ�����Ż�Ш���͹�ͧ��ѡ�ҹ �� Insert ������ŧ Table
	 * TA_WG_CONTROL
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
						" select distinct p.taWgControlPk.ouCode "
								+ " from TaWgControl p "
								+ " where p.taWgControlPk.ouCode ='" + ouCode
								+ "' " + " and p.taWgControlPk.taYear ="
								+ workYear + " "
								+ " and p.taWgControlPk.taPeriod =" + workMonth
								+ " " + " and p.taWgControlPk.userId = '"
								+ userId + "' "
				// " and p.taWgControlPk.codeSeq ="+codeSeq+" "
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
						" select distinct p.taWgControlPk.ouCode "
								+ " from TaWgControl p "
								+ " where p.taWgControlPk.ouCode ='" + ouCode
								+ "' " + " and p.taWgControlPk.taYear ="
								+ workYear + " "
								+ " and p.taWgControlPk.taPeriod =" + workMonth
								+ " " + " and p.taWgControlPk.codeSeq ="
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
		TaWgControl con = new TaWgControl();
		TaWgControlPk conPk = new TaWgControlPk();
		boolean insertData = this.CheckDataForInsert(ouCode, workYear,
				workMonth, userId);
		if (insertData) {
			conPk.setOuCode(ouCode);
			conPk.setTaPeriod(workMonth);
			conPk.setTaYear(workYear);
			conPk.setUserId(userId);
			// conPk.setCodeSeq(new Integer(intTmp));
			con.setTaWgControlPk(conPk);
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
			con = (TaWgControl) this.getHibernateTemplate().load(
					TaWgControl.class, conPk);
			con.setFlagClose("Y");
			con.setUpdBy(userId);
			con.setUpdDate(new Date());
			this.getHibernateTemplate().update(con);
		}
		rlst = "�����Ţͧ��͹��лչ��١�Դ���º��������";
		return rlst;
	}

	/*
	 * 
	 * public String addCloseMonthEmpWork(Integer workYear, Integer workMonth,
	 * String ouCode, String userId){ String rlst = ""; String sql = "";
	 * 
	 * sql = " select vorg.pk.codeSeq " + " from VPnOrganizationSecurity vorg "
	 * + " where vorg.pk.userId = '" + userId + "'" + " and vorg.pk.ouCode = '"
	 * + ouCode + "'"; List tmpList = this.getHibernateTemplate().find(sql);
	 * Iterator itt = tmpList.iterator(); TaWgControl con = null; TaWgControlPk
	 * conPk =null; int intTmp; for (itt=tmpList.iterator();itt.hasNext();){
	 * conPk = new TaWgControlPk(); con = new TaWgControl(); intTmp =
	 * (int)Double.parseDouble(itt.next().toString()); boolean insertData =
	 * this.CheckDataForInsert(ouCode, workYear, workMonth,new Integer(intTmp));
	 * if (insertData) { conPk.setOuCode(ouCode); conPk.setTaPeriod(workMonth);
	 * conPk.setTaYear(workYear); conPk.setCodeSeq(new Integer(intTmp));
	 * con.setTaWgControlPk(conPk); con.setFlagClose("Y"); con.setCreBy(userId);
	 * con.setCreDate(new Date()); con.setUpdBy(userId); con.setUpdDate(new
	 * Date()); this.getHibernateTemplate().save(con); } else { boolean
	 * updateData = this.CheckDataForUpdate(ouCode, workYear, workMonth,new
	 * Integer(intTmp)); if (updateData) { conPk.setOuCode(ouCode);
	 * conPk.setTaPeriod(workMonth); conPk.setTaYear(workYear);
	 * conPk.setCodeSeq(new Integer(intTmp)); con =
	 * (TaWgControl)this.getHibernateTemplate().load(TaWgControl.class, conPk);
	 * con.setFlagClose("Y"); con.setUpdBy(userId); con.setUpdDate(new Date());
	 * this.getHibernateTemplate().update(con); } } } rlst =
	 * "�����Ţͧ��͹��лչ��١�Դ���º��������";
	 * 
	 * return rlst; }
	 */
	/**
	 * method ����Ѻ�ʴ���áͧ�ӹѡ�ҹ Table WG_EMPLOYEE
	 * 
	 * @param ouCode
	 * @param userId
	 * @param workMonth
	 * @param workYear
	 * @param codeSeq
	 */
	public List findListDivReportEmpMonth(String ouCode, String userId,
			Integer workMonth, Integer workYear, String areaCode,
			String secCode, String workCode) {
		List listEmp = new ArrayList();
		List list = new ArrayList();
		TaMonthEmpWorkVO tamVo = null;
		String sql = "";
		try {

			sql = "  select distinct nvl(tam.wgEmployee.refPnOrganization.areaCode,tam.wgEmployee.refPnOrganization.divCode) as areaCode ,"
					+ " nvl(tam.wgEmployee.refPnOrganization.areaDesc,tam.wgEmployee.refPnOrganization.divDesc) as areaDesc,"
					+ " tam.wgEmployee.refPnOrganization.divCode,"
					+ " tam.wgEmployee.refPnOrganization.divDesc "
					+ " from TaWgMonthEmpWork tam "
					+ " where tam.taWgMonthEmpWorkPK.workYear = "
					+ workYear
					+ " and tam.taWgMonthEmpWorkPK.workMonth = "
					+ workMonth
					+ " and tam.wgEmployee.codeSeq in ( "
					+ " select vorg.pk.codeSeq "
					+ " from VWgEmployeeSecurity vorg "
					+ " where vorg.pk.ouCode = '"
					+ ouCode
					+ "'"
					+ "		and vorg.pk.userId = '" + userId + "'" + " ) ";
			if (!"".equals(areaCode)) {
				sql += " and tam.wgEmployee.refPnOrganization.areaCode = '"
						+ areaCode + "'";
			}
			if (!"".equals(secCode)) {
				sql += " and tam.wgEmployee.refPnOrganization.secCode = '"
						+ secCode + "'";
			}
			if (!"".equals(workCode)) {
				sql += " and tam.wgEmployee.refPnOrganization.workCode = '"
						+ workCode + "'";
			}
			sql += " order by nvl(tam.wgEmployee.refPnOrganization.areaCode,tam.wgEmployee.refPnOrganization.divCode))";

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
	 * method ����Ѻ�ʴ���§ҹ��÷ӧ�û�Ш���͹�ͧ��ѡ�ҹ Table WG_EMPLOYEE
	 * 
	 * @param ouCode
	 * @param month
	 * @param year
	 * @return
	 */
	public List findListReportEmpMonth(String ouCode, String userId,
			Integer workMonth, Integer workYear, String areaCode,
			String secCode, String workCode) {
		List listEmp = new ArrayList();
		List list = new ArrayList();
		TaMonthEmpWorkVO tamVo = null;
		String sql = "";
		try {

			sql = " select nvl(tam.wgEmployee.refPnOrganization.areaCode,tam.wgEmployee.refPnOrganization.divCode) as areaCode ,"
					+ " nvl(tam.wgEmployee.refPnOrganization.areaDesc,tam.wgEmployee.refPnOrganization.divDesc) as areaDesc, "
					+ " nvl(tam.wgEmployee.refPnOrganization.secCode,nvl(tam.wgEmployee.refPnOrganization.areaCode,tam.wgEmployee.refPnOrganization.divCode)) as secCode,"
					+ " nvl(tam.wgEmployee.refPnOrganization.secDesc,nvl(tam.wgEmployee.refPnOrganization.areaDesc,tam.wgEmployee.refPnOrganization.divDesc)) as secDesc,"
					+ " tam.wgEmployee.refPnOrganization.workCode as orgWorkCode,"
					+ " tam.wgEmployee.refPnOrganization.workDesc as orgWorkDesc,"
					+ " tam.wgEmployee.wgEmployeePK.empCode,"
					+ " tam.wgEmployee.refDbPreSuff.prefixName,"
					+ " tam.wgEmployee.firstName,"
					+ " tam.wgEmployee.lastName,"
					+ " tam.workCode,"
					+ " tam.taWgStatusWork.workDesc,"
					+ " tam.totalDays,"
					+ " tam.totalTime, "
					+ " tam.wgEmployee.refPnOrganization.divCode,"
					+ " tam.wgEmployee.refPnOrganization.divDesc "
					+ " from TaWgMonthEmpWork tam "
					+ " where tam.taWgMonthEmpWorkPK.workYear = "
					+ workYear
					+ " and tam.taWgMonthEmpWorkPK.workMonth = "
					+ workMonth
					+ " and tam.wgEmployee.codeSeq in ( "
					+ " select vorg.pk.codeSeq "
					+ " from VWgEmployeeSecurity vorg "
					+ " where vorg.pk.ouCode = '"
					+ ouCode
					+ "'"
					+ "  and vorg.pk.userId = '" + userId + "'" + " ) ";
			if (!"".equals(areaCode)) {
				sql += " and tam.wgEmployee.refPnOrganization.areaCode = '"
						+ areaCode + "'";
			}
			if (!"".equals(secCode)) {
				sql += " and tam.wgEmployee.refPnOrganization.secCode = '"
						+ secCode + "'";
			}
			if (!"".equals(workCode)) {
				sql += " and tam.wgEmployee.refPnOrganization.workCode = '"
						+ workCode + "'";
			}
			sql += " order by nvl(tam.wgEmployee.refPnOrganization.workCode,nvl(tam.wgEmployee.refPnOrganization.secCode,nvl(tam.wgEmployee.refPnOrganization.areaCode,nvl(tam.wgEmployee.refPnOrganization.divCode,nvl(tam.wgEmployee.refPnOrganization.partCode,''))))),"
					+ " tam.wgEmployee.refPnOrganization.orgCode,tam.wgEmployee.wgEmployeePK.empCode,tam.workCode ";

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
	 * method ����Ѻ��Ǩ�ͺ��͹�ӡ�� Insert �������١��ҧ �µ�Ǩ�ͺ��Ң����Ż�
	 * �Ǵ������ʡͧ�ӹѡ�ҹ����¶١�Դ�������
	 * 
	 * @param ouCode
	 * @param workYear
	 * @param workMonth
	 * @param codeSeq
	 */
	public String getStatusBeforeAddEmployee(String ouCode, Integer workYear,
			Integer workMonth, String areaCode, String secCode,
			String workCode, String userId) {
		String rlst = "";
		try {
			int tmpInt;
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
			 * select con.taWgControlPk.codeSeq " + " from TaWgControl con" + "
			 * where con.taWgControlPk.ouCode = '" + ouCode + "'" + " and
			 * con.taWgControlPk.taYear = " + workYear + " and
			 * con.taWgControlPk.taPeriod = " + workMonth + " and con.flagClose
			 * = 'Y' " + " ) "; tmpList = this.getHibernateTemplate().find(hql);
			 * if (tmpList == null || tmpList.size() == 0){ rlst = "Y"; }else{
			 * rlst = "N"; } }else{ rlst = "NOT_FOUND"; }
			 */
			String hql = " select con.taWgControlPk.userId "
					+ " from TaWgControl con"
					+ " where con.taWgControlPk.ouCode = '" + ouCode + "'"
					+ " and con.taWgControlPk.taYear = " + workYear
					+ " and con.taWgControlPk.taPeriod = " + workMonth
					+ " and con.taWgControlPk.userId   = '" + userId + "'"
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

	/**
	 * method ����Ѻ��Ǩ�ͺ��͹�ӡ�� Update �������١��ҧ �µ�Ǩ�ͺ��Ң����Ż�
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
			 * VWgEmployeeSecurity vemp, TaWgControl con " + " where
			 * vemp.pk.empCode = '" + empCode + "'" + " and vemp.pk.ouCode = '"
			 * + ouCode + "'" + " and vemp.pk.userId = '" + userId + "'" + " and
			 * con.taWgControlPk.ouCode = '" + ouCode + "'" + " and
			 * con.taWgControlPk.taPeriod = " + workMonth + " and
			 * con.taWgControlPk.taYear = " + workYear + " and vemp.pk.codeSeq =
			 * con.taWgControlPk.codeSeq "; List tmpList =
			 * this.getHibernateTemplate().find(hql); Iterator itt =
			 * tmpList.iterator(); if (itt.hasNext()){ String tmpStr =
			 * itt.next().toString(); if ("Y".equals(tmpStr)){ rlst = tmpStr;
			 * }else{ rlst = "N"; } }else{ rlst= "N"; }
			 */
			String hql = " select con.taWgControlPk.userId "
					+ " from TaWgControl con"
					+ " where con.taWgControlPk.ouCode = '" + ouCode + "'"
					+ " and con.taWgControlPk.taYear = " + workYear
					+ " and con.taWgControlPk.taPeriod = " + workMonth
					+ " and con.taWgControlPk.userId   = '" + userId + "'"
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
}
