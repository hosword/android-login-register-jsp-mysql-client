<%@ page language="java" import="com.example.server.data.JDBC,com.example.server.user.User,com.example.server.user.UserDo,java.sql.*" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>

<% 

/* User user=new User(1,"wade","1234567");

out.print(user); */


String name=request.getParameter("phone");
String password=request.getParameter("password");

UserDo userDo=new UserDo();
User user=userDo.register(name, password);


%>

<%=user %>
