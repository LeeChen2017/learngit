package com.zsoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "t_accountinfo")
public class AccountInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6637063721477439436L;
	private Long accountId;
	private Integer balance;
	private UserInfo userInfo;
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
}
