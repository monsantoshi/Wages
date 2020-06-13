package com.ss.tp.dao;

import com.ss.tp.dto.PnEmpSetPlaceVO;
import java.util.List;

public interface PnEmpSetPlaceDAO {
	public List findByCriteria(String ouCode, String moveStatus,
			String empCode, String year);

	public void insertPnEmpSetPlace(PnEmpSetPlaceVO pnempmovevo)
			throws Exception;

	public void updatePnEmpSetPlace(PnEmpSetPlaceVO pnempmovevo)
			throws Exception;

	public void deletePnEmpSetPlace(PnEmpSetPlaceVO pnempmovevo)
			throws Exception;

	public void addList(PnEmpSetPlaceVO pnempmovevo);

	public void clearList();

	public void insertPnEmpSetPlaceList() throws Exception;

	public void updatePnEmpSetPlaceList() throws Exception;
}
