package com.boritgogae.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class ConnectionTest {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://elrek1991.cafe24.com:3306/elrek1991?serverTimezone=Asia/Seoul&characterEncoding=UTF-8";
	private static final String USER = "elrek1991";
	private static final String PWD = "goott0531";
	
	@Test
	public void testConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		
		Connection con = DriverManager.getConnection(URL, USER, PWD);
		
		System.out.println(con.toString());
	}
}
