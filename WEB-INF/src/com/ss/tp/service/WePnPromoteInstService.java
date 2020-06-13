package com.ss.tp.service;

import java.util.List;

import com.ss.tp.dto.WeEmployeeVO;
import com.ss.tp.dto.WePnPromoteInstVO;

public interface WePnPromoteInstService {
	public void insertWePnPromoteInst(WePnPromoteInstVO wepnpromoteinstvo)
			throws Exception;

	public void updateWePnPromoteInst(WePnPromoteInstVO wepnpromoteinstvo)
			throws Exception;

	public void deleteWePnPromoteInst(WePnPromoteInstVO wepnpromoteinstvo)
			throws Exception;

	public void insertWePnPromoteInsts(List wepnpromoteinstvolist)
			throws Exception;

	public List findByCriteria(String userId, String evaOuCode, long evaYear,
			long evaMonth, String evaEmpCodeFrom, String evaEmpCodeTo);

	public Integer countData(String userId, String evaOuCode, long evaYear,
			long evaMonth, long evaVolume);

	public WeEmployeeVO findByEmpCodeDetail(String empCode, String ouCode);

	public List findByEmpCodeList(String userId, String ouCode, long yearPn,
			long monthPn, String empCode);

	// public List rwPremiumReportByOrg(String userId, String evaOuCode, long
	// evaYear,long evaPeriod,String evaFlag);
	public void addList(WePnPromoteInstVO rpVo, boolean isSave);

	public void insertAndUpdate() throws Exception;

	public boolean canDelete(String ouCode, String year, String month,
			String userId) throws Exception;

	public List findByCriteriaList(String userId, String evaOuCode,
			long evaYear, long evaMonth, long evaVolume, int count,
			int countRecord);

	public List findByCriteriaReport(String userId, String evaOuCode,
			String evaYear, String evaMonth, String evaVolume);

	public List findByCriteriaPromoteLevelReport(String userId,
			String evaOuCode, long evaYear, long evaMonth, long evaVolume);

	public List findVolumeBySecurity(String userId, String evaOuCode)
			throws Exception;
}