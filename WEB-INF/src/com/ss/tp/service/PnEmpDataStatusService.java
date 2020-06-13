package com.ss.tp.service;

import java.util.Date;
import java.util.List;

public interface PnEmpDataStatusService {

	public List findByCriteria(String ouCode, String empCode, Date statusDate);
}
