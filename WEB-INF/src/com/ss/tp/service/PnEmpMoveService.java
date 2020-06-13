package com.ss.tp.service;

import java.util.List;

//import com.ss.tp.dto.PayRollEmployeeVO
//import com.ss.tp.dto.PnEmployeeVO;
//import com.ss.tp.dto.PnEmpMoveVO;
//import com.ss.tp.dto.PnEmpSetPlaceVO;
//import com.ss.tp.dto.RwOvertimeVO;
//import com.ss.tp.model.PnEmpMove;

public interface PnEmpMoveService {

	public List findByCriteriaReport1(String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, String choiceStatus,
			String choiceGroup);

	public List findByCriteriaReport2(String orgCodeFrom, String orgCodeTo,
			String empCodeFrom, String empCodeTo, String choiceStatus,
			String choiceGroup);
	// public String findFormCode(String ouCode,long evaYear,long evaTime,String
	// empCode);
	// public Double findFormScore(String ouCode,String formCode);

}
