package com.ss.tp.dao;

import java.util.Date;
import java.util.List;

public interface WgEmpDataStatusDAO {
	public List findByCriteria(String ouCode, String empCode, Date statusDate);
}
