package com.example.demo.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLUtil {
	static final String USER = "test";
	static final String PASSWD = "test";

	public static Connection connect() throws SQLException {
		String url = "jdbc:mysql://192.168.3.113:3306/sugar_road?characterEncoding=UTF-8&serverTimezone=UTC";
		Connection conn = DriverManager.getConnection(url, USER, PASSWD);
		DatabaseMetaData md = conn.getMetaData();
		System.out.println("DBMS 서버 명 : "+md.getDatabaseProductName());
		System.out.println("사용자명 : "+md.getUserName());
		return conn;
	}
}
