package com.ss.tp.dao;

import com.ss.tp.model.SuUser;

public interface SuUserDAO {
	public SuUser findUserId(String UserId);

	public SuUser checkPasswd(String UserId, String UserPasswd);

	public String updatePassword(String UserId, String UserPasswd);

	public String lockUser(String UserId);
}
