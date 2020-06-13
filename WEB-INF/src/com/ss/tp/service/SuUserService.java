package com.ss.tp.service;

import java.util.List;

import com.ss.tp.model.SuUser;

public interface SuUserService {
	public SuUser findUserId(String UserId);

	public SuUser checkPasswd(String UserId, String UserPasswd);

	public List findByCriteria(String userId);

	public String updatePassword(String UserId, String UserPasswd);

	public String lockUser(String UserId);
}
