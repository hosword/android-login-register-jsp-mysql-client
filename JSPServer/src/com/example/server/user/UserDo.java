package com.example.server.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.server.data.JDBC;

public class UserDo {

	User user;
	Connection conn=null;
	Statement stat=null;
	ResultSet rs=null;
	
	public User login(String phone,String password){
		
		 conn=JDBC.getConn();
		try {
			stat = conn.createStatement();
			rs=stat.executeQuery("select * from person where name='"+phone+"' and password='"+password+"'");
			while(rs.next()){
				//int id=rs.getInt(1);
				String name=rs.getString(2);
				String password_select=rs.getString(3);
				
				 user=new User(name,password_select);
			}
			
			JDBC.close(rs, stat, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(user+"");
		return user;
		
	}
	
	public User register(String phone,String password){
		
		 conn=JDBC.getConn();
		try {
			stat = conn.createStatement();
			int re=stat.executeUpdate("insert into person (name,password) value("+phone+","+password+")");
			if(re != 0){
				user=new User(phone,password);
			}
			JDBC.close(rs, stat, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(user+"");
		return user;
		
	}
	
	
	public static void main(String[] args) {
	}

}
