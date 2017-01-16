package com.zsoft.service;

import com.zsoft.entity.AccountInfo;

public interface UserService {
	public AccountInfo createNewAccount(String user,String pwd,Integer init);
}
