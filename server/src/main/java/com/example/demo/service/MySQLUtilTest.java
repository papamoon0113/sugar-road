package com.example.demo.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLUtilTest {

	public static void main(String[] args) {
		try(Connection conn = MySQLUtil.connect();) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM store");
			System.out.println("\n==================SQL 출력====================");
			while (rs.next()) {
				System.out.printf("%s\t%s\t%s\n", rs.getString("store_name"), rs.getString("address"), rs.getString("store_desc"));
			}
			System.out.println("==================SQL 출력====================\n");
		} catch (Exception e){
			e.printStackTrace();
		}
		return;
	}

}
