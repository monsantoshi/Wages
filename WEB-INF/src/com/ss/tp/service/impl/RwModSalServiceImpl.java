package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.RwModSalDAO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwModSalVO;
import com.ss.tp.service.RwModSalService;

public class RwModSalServiceImpl implements RwModSalService, Serializable {

	private RwModSalDAO RwModSalDAO;

	public RwModSalDAO getRwModSalDAO() {
		return RwModSalDAO;
	}

	public void setRwModSalDAO(RwModSalDAO RwModSalDAO) {
		this.RwModSalDAO = RwModSalDAO;
	}

	public void insertRwModSal(RwModSalVO rwmodsalvo) throws Exception {
		this.RwModSalDAO.insertRwModSal(rwmodsalvo);
	}

	public void updateRwModSal(RwModSalVO rwmodsalvo) throws Exception {
		this.RwModSalDAO.updateRwModSal(rwmodsalvo);
	}

	public void deleteRwModSal(RwModSalVO rwmodsalvo) throws Exception {
		this.RwModSalDAO.deleteRwModSal(rwmodsalvo);
	}

	public void insertRwModSals(List rwmodsalvolist) throws Exception {
		this.RwModSalDAO.insertRwModSals(rwmodsalvolist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord) {
		return this.RwModSalDAO.findByCriteria(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo, count,
				countRecord);
	}

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo) {
		return this.RwModSalDAO.countData(userId, evaOuCode, new Long(evaYear),
				new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo);
	}

	public PayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		return this.RwModSalDAO.findByEmpCodeDetail(empCode, ouCode, year,
				period);
	}

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode) {
		return this.RwModSalDAO.findByEmpCodeList(userId, ouCode, new Long(
				yearPr), new Long(periodPr), empCode);
	}

	public void addList(RwModSalVO rpVo, boolean isSave) {
		this.RwModSalDAO.addList(rpVo);

		if (isSave) {
			try {
				this.RwModSalDAO.insertAndUpdateRwModSals();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.RwModSalDAO.insertAndUpdateRwModSals();
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.RwModSalDAO.isConfirmFlag(ouCode, year, period, userId);
	}

	/**
	 * method สำหรับแสดงรายงานข้อมูลปรับปรุงรายการรับ (เงินเดือน)
	 * 
	 * @param ouCode
	 * @param userId
	 * @param year
	 * @param period
	 * @return
	 */
	public List findSalaryUpdateRpt(String ouCode, String userId, int year,
			int period, String flag) throws Exception {
		return this.getRwModSalDAO().findSalaryUpdateRpt(ouCode, userId,
				new Integer(year), new Integer(period), flag);
	}

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.RwModSalDAO.canDelete(ouCode, year, period, userId);
	}
}
