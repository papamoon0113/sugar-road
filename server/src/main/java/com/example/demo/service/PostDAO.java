//package com.example.demo.service;
//
//import com.example.demo.domain.PostDTO;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.Statement;
//
//public class PostDAO {
//	static String TABLE = "post";
//	public PostDTO createPost(PostDTO dto){
//		try(Connection conn = MySQLUtil.connect()){
//			PreparedStatement pstmt = conn.prepareStatement("insert into " + TABLE + " values (?, ?)");
//
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//
//	}
//
//}
