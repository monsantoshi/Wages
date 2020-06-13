package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.WePnPromoteInstDAO; //import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.WeEmployeeVO;
import com.ss.tp.dto.WePnPromoteInstVO;

import com.ss.tp.service.WePnPromoteInstService;

public class WePnPromoteInstServiceImpl implements WePnPromoteInstService,
		Serializable {

	private WePnPromoteInstDAO WePnPromoteInstDAO;

	public WePnPromoteInstDAO getWePnPromoteInstDAO() {
		return WePnPromoteInstDAO;
	}

	public void setWePnPromoteInstDAO(WePnPromoteInstDAO WePnPromoteInstDAO) {
		this.WePnPromoteInstDAO = WePnPromoteInstDAO;
	}

	public void insertWePnPromoteInst(WePnPromoteInstVO wepnpromoteinstvo)
			throws Exception {
		this.WePnPromoteInstDAO.insertWePnPromoteInst(wepnpromoteinstvo);
	}

	public void updateWePnPromoteInst(WePnPromoteInstVO wepnpromoteinstvo)
			throws Exception {
		this.WePnPromoteInstDAO.updateWePnPromoteInst(wepnpromoteinstvo);
	}

	public void deleteWePnPromoteInst(WePnPromoteInstVO wepnpromoteinstvo)
			throws Exception {
		this.WePnPromoteInstDAO.deleteWePnPromoteInst(wepnpromoteinstvo);
	}

	public void insertWePnPromoteInsts(List wepnpromoteinstvolist)
			throws Exception {
		this.WePnPromoteInstDAO.insertWePnPromoteInsts(wepnpromoteinstvolist);
	}

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaMonth, String evaEmpCodeFrom, String evaEmpCodeTo) {
		return this.WePnPromoteInstDAO.findByCriteria(userId, evaOuCode,
				new Long(evaYear), new Long(evaMonth), evaEmpCodeFrom,
				evaEmpCodeTo);
	}

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaMonth, long evaVolume) {
		return this.WePnPromoteInstDAO.countData(userId, evaOuCode, new Long(
				evaYear), new Long(evaMonth), new Long(evaVolume));
	}

	public WeEmployeeVO findByEmpCodeDetail(String empCode, String ouCode) {
		return this.WePnPromoteInstDAO.findByEmpCodeDetail(empCode, ouCode);
	}

	public List findByEmpCodeList(String userId, String ouCode, long yearPn,
			long monthPn, String empCode) {
		return this.WePnPromoteInstDAO.findByEmpCodeList(userId, ouCode,
				new Long(yearPn), new Long(monthPn), empCode);
	}

	// public List rwPremiumReportByOrg(String userId, String evaOuCode, long
	// evaYear,long evaPeriod,String evaFlag){
	// return this.WePnPromoteInstDAO.rwPremiumReportByOrg(userId, evaOuCode,new
	// Long (evaYear), new Long (evaPeriod), evaFlag);
	// }

	public void addList(WePnPromoteInstVO rpVo, boolean isSave) {
		this.WePnPromoteInstDAO.addList(rpVo);

		if (isSave) {
			try {
				this.WePnPromoteInstDAO.insertAndUpdateWePnPromoteInsts();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void insertAndUpdate() throws Exception {
		this.WePnPromoteInstDAO.insertAndUpdateWePnPromoteInsts();
	}

	public boolean canDelete(String ouCode, String year, String month,
			String userId) throws Exception {
		return this.WePnPromoteInstDAO.canDelete(ouCode, year, month, userId);
	}

	public List findByCriteriaList(String userId, String evaOuCode,
			long evaYear, long evaMonth, long evaVolume, int count,
			int countRecord) {
		return this.WePnPromoteInstDAO.findByCriteriaList(userId, evaOuCode,
				new Long(evaYear), new Long(evaMonth), new Long(evaVolume),
				count, countRecord);
	}

	public List findByCriteriaReport(String userId, String evaOuCode,
			String evaYear, String evaMonth, String evaVolume) {
		return this.WePnPromoteInstDAO.findByCriteriaReport(userId, evaOuCode,
				evaYear, evaMonth, evaVolume);
	}

	public List findByCriteriaPromoteLevelReport(String userId,
			String evaOuCode, long evaYear, long evaMonth, long evaVolume) {
		return this.WePnPromoteInstDAO.findByCriteriaPromoteLevelReport(userId,
				evaOuCode, new Long(evaYear), new Long(evaMonth), new Long(
						evaVolume));
	}

	public List findVolumeBySecurity(String userId, String ouCode)
			throws Exception {
		return this.WePnPromoteInstDAO.findVolumeBySecurity(userId, ouCode);
	}

}
