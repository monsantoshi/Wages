package com.ss.tp.service;

import java.util.List;
import com.ss.tp.dto.WeEmployeeVO;
import com.ss.tp.dto.WePnEmployeeTextVO;

public interface WePnEmployeeTextService {
	public void insertWePnEmployeeText(WePnEmployeeTextVO rwpremiumvo)
			throws Exception;

	public void updateWePnEmployeeText(WePnEmployeeTextVO rwpremiumvo)
			throws Exception;

	public void deleteWePnEmployeeText(WePnEmployeeTextVO rwpremiumvo)
			throws Exception;

	public void insertWePnEmployeeTexts(List rwpremiumvolist) throws Exception;

	public List findByCriteria(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo, int count, int countRecord);

	public Integer countData(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo);

	public WeEmployeeVO findByEmpCodeDetail(String empCode, String ouCode);

	public List findByEmpCodeList(String userId, String ouCode, String empCode);

	public void addList(WePnEmployeeTextVO rpVo, boolean isSave);

	public void insertAndUpdate() throws Exception;

	public boolean canDelete(String ouCode, String userId) throws Exception;

	public List findByCriteriaList(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo, int count, int countRecord);

}