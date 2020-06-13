package com.ss.tp.service.impl;

import java.io.Serializable;
import java.util.List;

import com.ss.tp.dao.SuUserDAO;
import com.ss.tp.dao.SuUserOrgDAO;
import com.ss.tp.model.SuUser;
import com.ss.tp.service.SuUserService;

public class SuUserServiceImpl implements SuUserService, Serializable {
	private SuUserDAO suUserDAO;
	private SuUserOrgDAO suUserOrgDAO;

	public SuUserOrgDAO getSuUserOrgDAO() {
		return suUserOrgDAO;
	}

	public void setSuUserOrgDAO(SuUserOrgDAO suUserOrgDAO) {
		this.suUserOrgDAO = suUserOrgDAO;
	}

	public SuUserDAO getSuUserDAO() {
		return suUserDAO;
	}

	public void setSuUserDAO(SuUserDAO suUserDAO) {
		this.suUserDAO = suUserDAO;
	}

	public SuUser findUserId(String UserId) {
		return this.suUserDAO.findUserId(UserId);
	}

	public SuUser checkPasswd(String UserId, String UserPasswd) {
		System.out.println("************");
		return this.suUserDAO.checkPasswd(UserId, UserPasswd);
	}

	public List findByCriteria(String userId) {
		return this.suUserOrgDAO.findByCriteria(userId);
	}

	public String updatePassword(String UserId, String UserPasswd) {
		return this.suUserDAO.updatePassword(UserId, UserPasswd);
	}

	public String lockUser(String UserId) {
		return this.suUserDAO.lockUser(UserId);
	}
}
