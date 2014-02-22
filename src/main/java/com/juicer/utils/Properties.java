package com.juicer.utils;
import java.util.List;
import java.util.Map;
import com.google.gson.annotations.SerializedName;

public class Properties {
	
		private Map<String, String> db;
		private Map<String, String> twitter;
		
		@SerializedName("trackingTerms")
		private List<String> terms;

		public Properties() {}

		public Map<String, String> getDb() {
			return db;
		}

		public void setDb(Map<String, String> db) {
			this.db = db;
		}

		public Map<String, String> getTwitter() {
			return twitter;
		}

		public void setTwitter(Map<String, String> twitter) {
			this.twitter = twitter;
		}
		
		public String getDbProp(String key) {
			String val = null;
			if(db != null) {
				val = db.get(key);
			}
			return val;
		}
		
		public String getTwitterProp(String key) {
			String val = null;
			if(twitter != null) {
				val = twitter.get(key);
			}
			return val;
		}

		public List<String> getTerms() {
			return terms;
		}

		public void setTerms(List<String> terms) {
			this.terms = terms;
		}

		@Override
		public String toString() {
			return "Properties [db=" + db + ", twitter=" + twitter + ", terms="
					+ terms + "]";
		}
		
		
}
