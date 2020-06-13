package com.ss.tp.dao;

import java.util.List;

public interface PeEvaluationFormTitleDAO {
	public String findFormName(String ouCode, String formCode);

	public List findTitle(String ouCode, String formCode);

	public List findForm(String ouCode, String formType);

	public Double findFormScore(String ouCode, String formCode);

	public List findFormDefault(String ouCode, String formType);
}
