package com.juicer.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {
	
	private Connection con = null;
    private String url = "jdbc:mysql://seeknay.com:3306/seeknay_martino";
    private String user = "seeknay_mb";
    private String password = "mysql4Martino24";
    
    private String insertTweet = "INSERT INTO tweets"
    		+ "(id, created_at, text, source, geo, user_id, user_name, screen_name) VALUES"
    		+ "(?,?,?,?,?,?,?,?)";
	
	public DB(String url, String user, String pw) {
		this.url = url;
		this.user = user;
		this.password = pw;
		con = null;
	}
	
	public void connect() throws SQLException {
		con = DriverManager.getConnection(url, user, password);
	}
	
	public void save(Tweet t) {
		try {
			PreparedStatement ps = con.prepareStatement(insertTweet);
			ps.setString(1, t.getId());
			ps.setString(2, t.getCreatedAt());
			ps.setString(3, t.getText());
			ps.setString(4, t.getSource());
			ps.setString(5, t.getGeo());
			ps.setString(6, t.getUserId());
			ps.setString(7, t.getUserName());
			ps.setString(8, t.getScreenName());
			
			ps.executeUpdate();
			System.out.println("Added: " + t.toString());
			
		} catch (SQLException e) {
			System.out.println("Failed to add: " + t.toString());
			e.printStackTrace();
		}
		
	}
	
}