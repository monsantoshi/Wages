package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;
import com.ss.tp.dao.RwVinaiDAO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwVinaiVO;
import com.ss.tp.service.RwVinaiService;

public class RwVinaiServiceImpl implements RwVinaiService, Serializable {

	private RwVinaiDAO RwVinaiDAO;

	public RwVinaiDAO getRwVinaiDAO() {
		return RwVinaiDAO;
	}

	public void setRwVinaiDAO(RwVinaiDAO RwVinaiDAO) {
		this.RwVinaiDAO = RwVinaiDAO;
	}

	public void insertRwVinai(RwVinaiVO rwvinaivo) throws Exception {
		this.RwVinaiDAO.insertRwVinai(rwvinaivo);
	}

	public void updateRwVinai(RwVinaiVO rwvinaivo) throws Exception {
		this.RwVinaiDAO.updateRwVinai(rwvinaivo);
	}

	public void deleteRwVinai(RwVinaiVO rwvinaivo) throws Exception {
		this.RwVinaiDAO.deleteRwVinai(rwvinaivo);
	}

	public void insertRwVinais(List rwvinaivolist) throws Exception {
		this.RwVinaiDAO.insertRwVinais(rwvinaivolist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord) {
		return this.RwVinaiDAO.findByCriteria(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo, count,
				countRecord);
	}

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo) {
		return this.RwVinaiDAO.countData(userId, evaOuCode, new Long(evaYear),
				new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo);
	}

	public PayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		return this.RwVinaiDAO.findByEmpCodeDetail(empCode, ouCode, year,
				period);
	}

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode) {
		return this.RwVinaiDAO.findByEmpCodeList(userId, ouCode, new Long(
				yearPr), new Long(periodPr), empCode);
	}

	public void addList(RwVinaiVO rpVo, boolean isSave) {
		this.RwVinaiDAO.addList(rpVo);

		if (isSave) {
			try {
				this.RwVinaiDAO.insertAndUpdateRwVinais();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.RwVinaiDAO.insertAndUpdateRwVinais();
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.RwVinaiDAO.isConfirmFlag(ouCode, year, period, userId);
	}

	public List rwVinaiReport(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaFlag) {
		return this.RwVinaiDAO.rwVinaiReport(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaFlag);
	}

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.RwVinaiDAO.canDelete(ouCode, year, period, userId);
	}
}
