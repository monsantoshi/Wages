package com.ss.tp.dao;

import java.util.List;
import com.ss.tp.dto.WeEmployeeVO;
import com.ss.tp.dto.WeWgEmployeeTextVO;
import com.ss.tp.model.WeWgEmployeeText;

public interface WeWgEmployeeTextDAO {
	public void insertWeWgEmployeeText(WeWgEmployeeTextVO wepnemployeetextvo)
			throws Exception;

	public void updateWeWgEmployeeText(WeWgEmployeeTextVO wepnemployeetextvo)
			throws Exception;

	public void deleteWeWgEmployeeText(WeWgEmployeeTextVO wepnemployeetextvo)
			throws Exception;

	public void insertWeWgEmployeeTexts(List wepnemployeetextvolist)
			throws Exception;

	public List findByCriteria(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo, int count, int countRecord);

	public Integer countData(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo);

	public WeEmployeeVO findByEmpCodeDetail(String empCode, String ouCode);

	public List findByEmpCodeList(String userId, String ouCode, String empCode);

	public WeWgEmployeeText loadWeWgEmployeeText(WeWgEmployeeTextVO rpVo);

	public void addList(WeWgEmployeeTextVO wepnemployeetextvo);

	public void insertAndUpdateWeWgEmployeeTexts() throws Exception;

	public boolean canDelete(String ouCode, String userId) throws Exception;

	public List findByCriteriaList(String userId, String evaOuCode,
			String evaCodeSeqFrom, String evaCodeSeqTo, String evaEmpCodeFrom,
			String evaEmpCodeTo, int count, int countRecord);

}