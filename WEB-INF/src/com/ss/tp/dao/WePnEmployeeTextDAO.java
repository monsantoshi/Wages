package com.ss.tp.dao;

import java.util.List;
import com.ss.tp.dto.WeEmployeeVO;
import com.ss.tp.dto.WePnEmployeeTextVO;
import com.ss.tp.model.WePnEmployeeText;

public interface WePnEmployeeTextDAO {
	public void insertWePnEmployeeText(WePnEmployeeTextVO wepnemployeetextvo)
			throws Exception;

	public void updateWePnEmployeeText(WePnEmployeeTextVO wepnemployeetextvo)
			throws Exception;

	public void deleteWePnEmployeeText(WePnEmployeeTextVO wepnemployeetextvo)
			throws Exception;

	public void insertWePnEmployeeTexts(List wepnemployeetextvolist)
			throws Exception;

	public List findByCriteria(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo, int count, int countRecord);

	public Integer countData(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo);

	public WeEmployeeVO findByEmpCodeDetail(String empCode, String ouCode);

	public List findByEmpCodeList(String userId, String ouCode, String empCode);

	public WePnEmployeeText loadWePnEmployeeText(WePnEmployeeTextVO rpVo);

	public void addList(WePnEmployeeTextVO wepnemployeetextvo);

	public void insertAndUpdateWePnEmployeeTexts() throws Exception;

	public boolean canDelete(String ouCode, String userId) throws Exception;

	public List findByCriteriaList(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo, int count, int countRecord);

}