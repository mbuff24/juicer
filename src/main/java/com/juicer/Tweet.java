package com.juicer;
import com.google.gson.*;

public class Tweet {

	private String json;

	//root level properties
	private String timestamp;
	private String id;
	private String text;
	private String source;
	private String geo;

	//user level properties
	private String userId;
	private String userName;
	private String screenName;

	public Tweet(String json) {
		this.json = json;
	}

	public void parse() {
		JsonParser parser = new JsonParser();
		JsonElement ele = parser.parse(json);

		JsonObject root = ele.getAsJsonObject();
		this.timestamp = getProperty(root, "created_at");
		this.id = getProperty(root, "id_str");
		this.text = getProperty(root, "text");
		this.source = getProperty(root, "source");
		this.geo = getProperty(root, "geo");

		JsonObject user = root.get("user").getAsJsonObject();
		this.userId = getProperty(user, "id_str");
		this.userName = getProperty(user, "name");
		this.screenName = getProperty(user, "screen_name");
	}

	private String getProperty(JsonObject obj, String member) {
		JsonElement ele = obj.get(member);
		if(!ele.isJsonNull()) {
			return ele.getAsString();
		}
		return "";
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
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
		return "Tweet [timestamp=" + timestamp + ", id="
				+ id + ", text=" + text + ", source=" + source + ", geo=" + geo
				+ ", userId=" + userId + ", userName=" + userName
				+ ", screenName=" + screenName + "]";
	}


}
