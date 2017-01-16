package com.zsoft.service;

import org.springframework.stereotype.Service;

import com.zsoft.dao.UserDAO;
import com.zsoft.dao.UserDAOImpl;
import com.zsoft.entity.AccountInfo;
import com.zsoft.entity.UserInfo;
@Service

public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO = new UserDAOImpl();
	public AccountInfo createNewAccount(String user, String pwd, Integer init) {
		AccountInfo accountInfo = new AccountInfo();
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(user);
		userInfo.setPwd(pwd);
		accountInfo.setBalance(init);
		accountInfo.setUserInfo(userInfo);
		return userDAO.save(accountInfo);
	}

}
