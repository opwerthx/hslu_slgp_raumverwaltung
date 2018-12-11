package api;

import java.io.Serializable;
import java.rmi.Remote;
import java.sql.Timestamp;

public class User implements Serializable, Remote {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6645951805985378352L;
	private Integer userId;
	private String username;
	private Timestamp cTs;
	private String cUser;
	private Timestamp createTime;
	private String email;
	private String firstname;
	private Timestamp mTs;
	private String mUser;
	private String name;
	private String password;
	private String role;
	private String rowFlag;

    public User(Integer userId) {
        this.userId = userId;
    }

    public User( 
    		Integer userId
    		, String username
    		, String mUser
    		, String name
    		, String password
    		, String role
    		, String rowFlag
    		, String cUser
    		, String email
    		, String firstname
    		, Timestamp cTs
    		, Timestamp createTime
    		, Timestamp mTs
    		) 
    {	
		 this.userId = userId;
		 this.username = username;
		 this.mUser = mUser;
		 this.name = name;
		 this.password = password;
		 this.role = role;
		 this.rowFlag = rowFlag;
		 this.cUser = cUser;
		 this.email = email;
		 this.firstname =firstname; 
		 this.cTs = cTs;
		 this.createTime = createTime;
		 this.mTs =mTs;
    }

	public User(
    		 String username
    		, String name
    		, String password
    		, String role
    		, String email
			) {
		 this.username = username;
		 this.name = name;
		 this.password = password;
		 this.role = role;
		 this.email = email;
	}

	public User() {
		userId = -1;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getcTs() {
		return cTs;
	}

	public void setcTs(Timestamp cTs) {
		this.cTs = cTs;
	}

	public String getcUser() {
		return cUser;
	}

	public void setcUser(String cUser) {
		this.cUser = cUser;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Timestamp getmTs() {
		return mTs;
	}

	public void setmTs(Timestamp mTs) {
		this.mTs = mTs;
	}

	public String getmUser() {
		return mUser;
	}

	public void setmUser(String mUser) {
		this.mUser = mUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRowFlag() {
		return rowFlag;
	}

	public void setRowFlag(String rowFlag) {
		this.rowFlag = rowFlag;
	}


}