package com.ss.tp.service;

import com.ss.tp.dto.PnEmpSetPlaceVO;
import java.util.List;

public interface PnEmpSetPlaceService {
	public List findByCriteriaList(String ouCode, String moveStatus,
			String empCode, String year);

	public void insertPnEmpSetPlace(PnEmpSetPlaceVO pnempsetplacevo)
			throws Exception;

	public void updatePnEmpSetPlace(PnEmpSetPlaceVO pnempsetplacevo)
			throws Exception;

	public void deletePnEmpSetPlace(PnEmpSetPlaceVO pnempsetplacevo)
			throws Exception;

	public void addList(PnEmpSetPlaceVO pnempsetplacevo);
}
