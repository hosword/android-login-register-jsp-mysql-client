package com.example.server.user;


public class User {
	
//	private int userId;
	private String username;
	private String password;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String password) {
		super();
//		this.userId = userId;
		this.username = username;
		this.password = password;
	}
//	public int getUserId() {
//		return userId;
//	}
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//json-----���ݽ�����ʽ
	@Override
	public String toString() {
		return  " username:" + username+ ", password:" + password + "}  ";
	}
	
	
//	public static void main(String[] args) {
//		User user=new User(1,"wade","1234567");
//		System.out.println(user);
//	}

}
