/**
 * Copyright 2013 Twitter, Inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package com.juicer;

import com.juicer.data.DB;
import com.juicer.data.Tweet;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FilterStream {
	
	private DB db;
	private BlockingQueue<String> queue;
	private StatusesFilterEndpoint endpoint;
	private List<String> terms;
	private List<String> langs;
	private Client client;
	
	public FilterStream(DB db) {
		this.db = db;
		queue = new LinkedBlockingQueue<String>(10000);
		endpoint = new StatusesFilterEndpoint();
		terms = new ArrayList<String>();
		langs = new ArrayList<String>();
		client = null;
	}
	
	public List<String> getTerms() {
		return terms;
	}

	public void setTerms(List<String> terms) {
		this.terms = terms;
	}

	public void addTerm(String s) {
		terms.add(s);
	}
	
	public void addTerms(List<String> list) {
		terms.addAll(list);
	}
	
	public void addLanguage(String s) {
		langs.add(s);
	}

	public void oauth(String consumerKey, String consumerSecret, String token, String secret) {
		
		// add some track terms
		endpoint.trackTerms(terms);
		endpoint.languages(langs);
		
		System.out.println(endpoint.getURI());
		
		Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);
		StringDelimitedProcessor sdp = new StringDelimitedProcessor(queue);

		// Create a new BasicClient. By default gzip is enabled.
		client = new ClientBuilder()
		.hosts(Constants.STREAM_HOST)
		.endpoint(endpoint)
		.authentication(auth)
		.processor(sdp)
		.build();

		// Establish a connection
		client.connect();
	}
	
	public void run() throws InterruptedException, SQLException {
		String msg = "";
		Tweet tweet;
		
		db.connect();
		
		for (;;) {
			msg = queue.take();
			tweet = new Tweet(msg);
			tweet.parse();
			db.save(tweet);
		}
	}
	
	public void close() {
		client.stop();
	}
}