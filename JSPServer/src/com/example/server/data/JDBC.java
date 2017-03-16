package com.example.server.data;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class JDBC {
	static String driverClassName="com.mysql.jdbc.Driver";
	static String url="jdbc:mysql://wenxiaoba.cn:3306/coursedesign";
	static String username="root";
	static String password="wenxiaoba";
	static{//��̬�����
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConn(){
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url, username, password);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(ResultSet rs, Statement stmt,Connection conn){
		try {
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(stmt!=null){
				stmt.close();
				stmt=null;
			}
			if(conn!=null){
				conn.close();
				conn=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void  printRs(ResultSet rs){
		try {
			ResultSetMetaData rsmd=rs.getMetaData();
//			System.out.println(rsmd.getColumnName(1)+"\t"+rsmd.getColumnName(2)+"\t"+rsmd.getColumnName(3));
			while(rs.next()){
				for(int i=0;i<rsmd.getColumnCount();i++){
					System.out.println(rsmd.getColumnName(1)+":"+rs.getInt(1)+"\t"+rsmd.getColumnName(2)+":"+rs.getString(2)+"\t"+rsmd.getColumnName(3)+":"+rs.getInt(3));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		Connection conn=JDBC.getConn();
		System.out.println(conn);
		Statement stat=conn.createStatement();
		String sql="select * from person";
		boolean b=stat.execute(sql);
		if(b==true){
			ResultSet rs=stat.getResultSet();
			while(rs.next()){
				int id=rs.getInt("id");
				String name=rs.getString("name");
//				String url=rs.getString("url");
				System.out.println("id:"+id);
				System.out.println("name:"+name);
//				System.out.println("url:"+url);
			}
		}
//		JDBC.printRs(rs);
	}
}
