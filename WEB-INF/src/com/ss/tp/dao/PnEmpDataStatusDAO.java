package com.ss.tp.dao;

import java.util.Date;
import java.util.List;

public interface PnEmpDataStatusDAO {
	public List findByCriteria(String ouCode, String empCode, Date statusDate);
}
