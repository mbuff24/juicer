package com.juicer.data;
import com.google.gson.*;

public class Tweet {

	private String json;
	
	//TODO
	// don't store retweets
	// maybe store retweet count
	// If it is a retweeted status, store the original
	// Maybe use coordinates property?

	//root level properties
	private String createdAt;
	private String id;
	private String text;
	private String source;
	private String geo;

	//user level properties
	private String userId;
	private String userName;
	private String screenName;
	
	private boolean isRT;

	public Tweet(String json) {
		this.json = json;
	}

	public void parse() {
		JsonParser parser = new JsonParser();
		JsonElement ele = parser.parse(json);

		JsonObject root = ele.getAsJsonObject();
		this.createdAt = getProperty(root, "created_at");
		this.id = getProperty(root, "id_str");
		this.text = getProperty(root, "text");
		this.source = getProperty(root, "source");
		
		this.geo = getGeoProperty(root);

		JsonObject user = root.get("user").getAsJsonObject();
		this.userId = getProperty(user, "id_str");
		this.userName = getProperty(user, "name");
		this.screenName = getProperty(user, "screen_name");
		
		this.isRT = getPropertyAsBoolean(root, "retweeted");
	}

	private String getProperty(JsonObject obj, String member) {
		JsonElement ele = obj.get(member);
		if(!ele.isJsonNull()) {
			return ele.getAsString();
		}
		return "";
	}
	
	private String getGeoProperty(JsonObject obj) {
		JsonElement ele = obj.get("geo");
		if(!ele.isJsonNull()) {
			obj = ele.getAsJsonObject();
			String type = getProperty(obj, "type");
			if(type.equalsIgnoreCase("point")) {
				ele = obj.get("coordinates");
				JsonArray arr = ele.getAsJsonArray();
				double x = arr.get(0).getAsDouble();
				double y = arr.get(1).getAsDouble();
				return x+", "+y;
			}	
		}
		return null;
	}
	
	private boolean getPropertyAsBoolean(JsonObject obj, String member) {
		JsonElement ele = obj.get(member);
		if(!ele.isJsonNull()) {
			return ele.getAsBoolean();
		}
		return false;
	}
	
	private int getPropertyAsInt(JsonObject obj, String member) {
		JsonElement ele = obj.get(member);
		if(!ele.isJsonNull()) {
			return ele.getAsInt();
		}
		return -1;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setTimestamp(String createdAt) {
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

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	@Override
	public String toString() {
		return "Tweet [createdAt=" + createdAt + ", id="
				+ id + ", text=" + text + ", source=" + source + ", geo=" + geo
				+ ", userId=" + userId + ", userName=" + userName
				+ ", screenName=" + screenName + "]";
	}


}
