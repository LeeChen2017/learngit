package com.zsoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zsoft.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

}
