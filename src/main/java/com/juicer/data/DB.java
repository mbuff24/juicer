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
    private String url;
    private String user;
    private String password;
    
    private String insertTweet = "INSERT INTO tweets"
    		+ "(id, created_at, text, source, geo, user_id, user_name, screen_name, followers_count, friends_count, statuses_count, verified, retweet_count, favorite_count) VALUES"
    		+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private String exists = "SELECT COUNT(*) FROM tweets WHERE ID = ?";
	
	public DB(String url, String user, String pw) {
		this.url = url;
		this.user = user;
		this.password = pw;
		con = null;
	}
	
	public void connect() throws SQLException {
		con = DriverManager.getConnection(url, user, password);
	}
	
	public boolean exists(String id) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(exists);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int exists = rs.getInt("COUNT(*)");
			if(exists == 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void save(Tweet t) {
		try {
			
			//store the original tweet if it's a retweet
			if(t.getRetweet() != null) {
				t = t.getRetweet();
			}
						
			PreparedStatement ps = con.prepareStatement(insertTweet);
			User u = t.getUser();
			
			Coordinates c = t.getCoordinates();
			String coordsStr = null;
			if(c != null) {
				coordsStr = c.getCoordsAsString();
			}
			
			ps.setString(1, t.getId());
			ps.setString(2, t.getCreatedAt());
			ps.setString(3, t.getText());
			ps.setString(4, t.getSource());
			ps.setString(5, coordsStr);
			ps.setString(6, u.getId());
			ps.setString(7, u.getUserName());
			ps.setString(8, u.getScreenName());
			ps.setInt(9, u.getFollowersCount());
			ps.setInt(10, u.getFriendsCount());
			ps.setInt(11, u.getStatusesCount());
			ps.setBoolean(12, u.getVerified());
			ps.setInt(13, t.getRetweetCount());
			ps.setInt(14, t.getFavoriteCount());
			ps.executeUpdate();
			System.out.println("Saved: "+ t.toString());
			
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Failed to add: " + t.toString());
		}
		
	}
	
}