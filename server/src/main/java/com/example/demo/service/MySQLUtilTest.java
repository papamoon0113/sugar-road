package com.example.demo.service;

import java.sql.Connection;

public class MySQLUtilTest {

	public static void main(String[] args) {
		try(Connection conn = MySQLUtil.connect();) {

		} catch (Exception e){
			e.printStackTrace();
		}
		return;
	}

}
