package com.zsoft.service;

import com.zsoft.entity.User;

public interface UserService {
	public User save(String name,String pwd,String loc);
}
