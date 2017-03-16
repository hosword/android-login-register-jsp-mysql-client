<%@ page language="java" import="com.example.server.data.JDBC,com.example.server.user.User,com.example.server.user.UserDo,java.sql.*" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>JDBCTEST</title>
</head>
<body>
<h1>JDBC  TEST</h1>
<%

	Connection conn = null;
    Statement  stat = null; 
	ResultSet  rs   = null;
	
	//加载数据库驱动类
    Class.forName("com.mysql.jdbc.Driver");
    //数据库连接URL
    String url = "jdbc:mysql://wenxiaoba.cn:3306/coursedesign";
    //数据库用户名
    String user = "root";
    //数据库密码
    String password = "wenxiaoba";
   
	try{
		
		conn = JDBC.getConn();
		 //根据数据库参数取得一个数据库连接
		//conn = DriverManager.getConnection(url, user, password);
		stat = conn.createStatement();
		String sql = "select * from person";
		rs = stat.executeQuery(sql); 
		out.print("<table border=2>");
	    out.print("<tr>");
	    out.print("<th width=100>"+"id");
	    out.print("<th width=100>"+"name");
	    out.print("<th width=100>"+"password");
	    out.print("</tr>");
	    while(rs.next()){
	        out.print("<tr>");
	        out.print("<td>"+rs.getInt(1)+"</td>");
	        out.print("<td>"+rs.getString(2)+"</td>");
	        out.print("<td>"+rs.getString(3)+"</td>");
	        out.print("</tr>");
	    }
	    out.print("</table>");
	    JDBC.close(rs, stat, conn);
	    //conn.close();
	}catch (SQLException e) {
		e.printStackTrace();
	}
	

%>

<p>Login</p>
<form action="index.jsp"method="get">
username:<input type="text" name="phone"/><br/>
password:<input type="password" name="password"/><br/>
<input type="submit" value="submit"/>
</form>
<p>Register</p>
<form action="register.jsp"method="get">
username:<input type="text" name="phone"/><br/>
password:<input type="password" name="password"/><br/>
<input type="submit" value="submit"/>
</form>

</body>
</html>