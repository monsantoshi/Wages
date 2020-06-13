package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;
import com.ss.tp.dao.RwVinai2DAO;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwVinai2VO;
import com.ss.tp.service.RwVinai2Service;

public class RwVinai2ServiceImpl implements RwVinai2Service, Serializable {

	private RwVinai2DAO RwVinai2DAO;

	public RwVinai2DAO getRwVinai2DAO() {
		return RwVinai2DAO;
	}

	public void setRwVinai2DAO(RwVinai2DAO RwVinai2DAO) {
		this.RwVinai2DAO = RwVinai2DAO;
	}

	public void insertRwVinai2(RwVinai2VO rwvinai2vo) throws Exception {
		this.RwVinai2DAO.insertRwVinai2(rwvinai2vo);
	}

	public void updateRwVinai2(RwVinai2VO rwvinai2vo) throws Exception {
		this.RwVinai2DAO.updateRwVinai2(rwvinai2vo);
	}

	public void deleteRwVinai2(RwVinai2VO rwvinai2vo) throws Exception {
		this.RwVinai2DAO.deleteRwVinai2(rwvinai2vo);
	}

	public void insertRwVinai2s(List rwvinai2volist) throws Exception {
		this.RwVinai2DAO.insertRwVinai2s(rwvinai2volist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord) {
		return this.RwVinai2DAO.findByCriteria(userId, evaOuCode, new Long(
				evaYear), new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo, count,
				countRecord);
	}

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo) {
		return this.RwVinai2DAO.countData(userId, evaOuCode, new Long(evaYear),
				new Long(evaPeriod), evaCodeSeqFrom, evaCodeSeqTo,
				evaEmpCodeFrom, evaEmpCodeTo, evaFlagFrom, evaFlagTo);
	}

	public PayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period) {
		return this.RwVinai2DAO.findByEmpCodeDetail(empCode, ouCode, year,
				period);
	}

	public List findByEmpCodeList(String userId, String ouCode, long yearPr,
			long periodPr, String empCode) {
		return this.RwVinai2DAO.findByEmpCodeList(userId, ouCode, new Long(
				yearPr), new Long(periodPr), empCode);
	}

	public void addList(RwVinai2VO rpVo, boolean isSave) {
		this.RwVinai2DAO.addList(rpVo);

		if (isSave) {
			try {
				// this.insertAndUpdate();
				this.RwVinai2DAO.insertAndUpdateRwVinai2s();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.RwVinai2DAO.insertAndUpdateRwVinai2s();
	}

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.RwVinai2DAO.isConfirmFlag(ouCode, year, period, userId);
	}

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception {
		return this.RwVinai2DAO.canDelete(ouCode, year, period, userId);
	}
}
