package ui.controller;

import java.sql.Timestamp;

import api.User;

public class UserWrapper {

	private User user;

	public UserWrapper() {

	}

	public UserWrapper(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getUserId() {
		return user.getUserId();
	}
	public void setUserId(Integer userId) {
		user.setUserId(userId);
	}
	public String getName() {
		return user.getName();
	}
	public void setName(String userName) {
		user.setName(userName);
	}
	public String getUserName() {
		return user.getUsername();
	}
	public void setUserName(String userName) {
		user.setUsername(userName);
	}
	public Timestamp getcTs() {
		return user.getcTs();
	}
	public void setcTs(Timestamp cTs) {
		user.setcTs(cTs);
	}
	public Timestamp getmTs() {
		return user.getmTs();
	}
	public void setmTs(Timestamp mTs) {
		user.setmTs(mTs);
	}
	public String getcUser() {
		return user.getcUser();
	}
	public void setcUser(String cUser) {
		user.setcUser(cUser);
	}
	public Timestamp getCreateTime() {
		return user.getCreateTime();
	}
	public void setCreateTime(Timestamp createTime) {
		user.setCreateTime(createTime);
	}
	public String getEmail() {
		return user.getcUser();
	}
	public void setEmail(String Email) {
		user.setEmail(Email);
	}
	public String getFirstname() {
		return user.getFirstname();
	}
	public void setFirstname(String Firstname) {
		user.setFirstname(Firstname);
	}
	public String getmUser() {
		return user.getmUser();
	}
	public void setmUser(String mUser) {
		user.setcUser(mUser);
	}
	public String getPassword() {
		return user.getPassword();
	}
	public void setPassword(String password) {
		user.setPassword(password);
	}
	public String getRole() {
		return user.getRole();
	}
	public void setRole(String role) {
		user.setRole(role);
	}
}	