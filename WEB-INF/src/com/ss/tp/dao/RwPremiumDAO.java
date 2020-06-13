package com.ss.tp.dao;

import java.util.List;
import com.ss.tp.dto.PayRollEmployeeVO;
import com.ss.tp.dto.RwPremiumVO;
import com.ss.tp.model.RwPremium;

public interface RwPremiumDAO {
	public void insertRwPremium(RwPremiumVO rwpremiumvo) throws Exception;

	public void updateRwPremium(RwPremiumVO rwpremiumvo) throws Exception;

	public void deleteRwPremium(RwPremiumVO rwpremiumvo) throws Exception;

	public void insertRwPremiums(List rwpremiumvolist) throws Exception;

	public List findByCriteria(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo, int count, int countRecord);

	public Integer countData(String userId, String evaOuCode, Long evaYear,
			Long evaPeriod, String evaCodeSeqFrom, String evaCodeSeqTo,
			String evaEmpCodeFrom, String evaEmpCodeTo, String evaFlagFrom,
			String evaFlagTo);

	public PayRollEmployeeVO findByEmpCodeDetail(String empCode, String ouCode,
			Long year, Long period);

	public List findByEmpCodeList(String userId, String ouCode, Long yearPr,
			Long periodPr, String empCode);

	public RwPremium loadRwPremium(RwPremiumVO rpVo);

	public List rwPremiumReportByOrg(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaFlag);

	public void addList(RwPremiumVO rwpremiumvo);

	public void insertAndUpdateRwPremiums() throws Exception;

	public boolean isConfirmFlag(String ouCode, String year, String period,
			String userId) throws Exception;

	public boolean canDelete(String ouCode, String year, String period,
			String userId) throws Exception;

	public void generate(String userId, String ouCode, Long year, Long period);

	public List findByCriteriaList(String userId, String evaOuCode,
			Long evaYear, Long evaPeriod, String evaCodeSeqFrom,
			String evaCodeSeqTo, String evaEmpCodeFrom, String evaEmpCodeTo);
}