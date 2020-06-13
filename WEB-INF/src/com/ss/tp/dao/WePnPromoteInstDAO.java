package com.ss.tp.dao;

import java.util.List;
import com.ss.tp.dto.WeEmployeeVO;
import com.ss.tp.dto.WePnPromoteInstVO;
import com.ss.tp.model.WePnPromoteInst;

public interface WePnPromoteInstDAO {
	public void insertWePnPromoteInst(WePnPromoteInstVO wepnpromoteinstvo)
			throws Exception;

	public void updateWePnPromoteInst(WePnPromoteInstVO wepnpromoteinstvo)
			throws Exception;

	public void deleteWePnPromoteInst(WePnPromoteInstVO wepnpromoteinstvo)
			throws Exception;

	public void insertWePnPromoteInsts(List wepnpromoteinstvolist)
			throws Exception;

	public List findByCriteria(String userId, String evaOuCode, Long evaYear,
			Long evaMonth, String evaEmpCodeFrom, String evaEmpCodeTo);

	public Integer countData(String userId, String evaOuCode, Long evaYear,
			Long evaMonth, Long evaVolume);

	public WeEmployeeVO findByEmpCodeDetail(String empCode, String ouCode);

	public List findByEmpCodeList(String userId, String ouCode, Long yearPn,
			Long monthPn, String empCode);

	public WePnPromoteInst loadWePnPromoteInst(WePnPromoteInstVO rpVo);

	// public List rwPremiumReportByOrg(String userId, String evaOuCode, Long
	// evaYear,Long evaPeriod,String evaFlag);
	public void addList(WePnPromoteInstVO wepnpromoteinstvo);

	public void insertAndUpdateWePnPromoteInsts() throws Exception;

	public boolean canDelete(String ouCode, String year, String month,
			String userId) throws Exception;

	public List findByCriteriaList(String userId, String evaOuCode,
			Long evaYear, Long evaMonth, Long evaVolume, int count,
			int countRecord);

	public List findByCriteriaReport(String userId, String evaOuCode,
			String evaYear, String evaMonth, String evaVolume);

	public List findByCriteriaPromoteLevelReport(String userId,
			String evaOuCode, Long evaYear, Long evaMonth, Long evaVolume);

	public List findVolumeBySecurity(String userId, String ouCode)
			throws Exception;
}