package com.ss.tp.service;

import java.util.Date;
import java.util.List;

public interface WgEmpDataStatusService {

	public List findByCriteria(String ouCode, String empCode, Date statusDate);
}
