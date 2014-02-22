package com.juicer.data;
import com.google.gson.annotations.SerializedName;

public class Tweet {
	
	//root level properties
	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id_str")
	private String id;

	private String text;
	private String source;
	private boolean retweeted;

	@SerializedName("retweet_count")
	private int retweetCount;

	@SerializedName("favorite_count")
	private int favoriteCount;

	private User user;
	
	private Coordinates coordinates;
	
	@SerializedName("retweeted_status")
	private Tweet retweet;

	public Tweet() {}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isRetweeted() {
		return retweeted;
	}

	public void setRetweeted(boolean retweeted) {
		this.retweeted = retweeted;
	}

	public int getRetweetCount() {
		return retweetCount;
	}

	public void setRetweetCount(int retweetCount) {
		this.retweetCount = retweetCount;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Tweet getRetweet() {
		return retweet;
	}

	public void setRetweet(Tweet retweet) {
		this.retweet = retweet;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String toString() {
		return "Tweet [createdAt=" + createdAt + ", id=" + id + ", text="
				+ text + ", source=" + source + ", retweeted=" + retweeted
				+ ", retweetCount=" + retweetCount + ", favoriteCount="
				+ favoriteCount + ", user=" + user + ", coordinates="
				+ coordinates + ", retweet=" + retweet + "]";
	}


}
