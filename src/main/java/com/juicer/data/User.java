package com.juicer.data;
import com.google.gson.annotations.SerializedName;

public class User {
	
		@SerializedName("id_str")
		private String id;

		@SerializedName("name")
		private String userName;

		@SerializedName("screen_name")
		private String screenName;

		@SerializedName("followers_count")
		private int followersCount;

		@SerializedName("friends_count")
		private int friendsCount;

		@SerializedName("created_at")
		private String createdAt;

		@SerializedName("statuses_count")
		private int statusesCount;

		private boolean verified;
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
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
		
		public int getFollowersCount() {
			return followersCount;
		}

		public void setFollowersCount(int followersCount) {
			this.followersCount = followersCount;
		}
		
		public int getFriendsCount() {
			return friendsCount;
		}

		public void setFriendsCount(int friendsCount) {
			this.friendsCount = friendsCount;
		}
		
		public String getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}
		
		public int getStatusesCount() {
			return statusesCount;
		}

		public void setStatusesCount(int statusesCount) {
			this.statusesCount = statusesCount;
		}
		
		public boolean getVerified() {
			return verified;
		}
		
		public void setVerified(boolean verified) {
			this.verified = verified;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", userName=" + userName
					+ ", screenName=" + screenName + ", followersCount="
					+ followersCount + ", friendsCount=" + friendsCount
					+ ", createdAt=" + createdAt + ", statusesCount="
					+ statusesCount + ", verified=" + verified + "]";
		}

	}