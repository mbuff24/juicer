package com.juicer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.juicer.data.DB;
import com.juicer.utils.Properties;


public class App {
	
	//Put your twitter credentials, db info, tracking terms here..
	private static final String file = "./properties.json";
	
	public static void main(String[] args) {
		
		FilterStream fse = null;
		FileInputStream fip;
		Properties props;
		Reader reader;
		DB db;
		Gson gson = new Gson();
		
		try {
			
			fip = new FileInputStream(file);
			reader = new InputStreamReader(fip);
			props = gson.fromJson(reader, Properties.class);
			
			db = new DB(props.getDbProp("url"),
					props.getDbProp("user"),
					props.getDbProp("pw"));
			
			fse = new FilterStream(db);
			
			fse.setTerms(props.getTerms());			
			fse.addLanguage("en");
			fse.oauth(props.getTwitterProp("consumerKey"), 
					props.getTwitterProp("consumerSecret"), 
					props.getTwitterProp("token"),
					props.getTwitterProp("secret"));
			
			fse.run();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
			fse.close();
		} catch (SQLException e) {
			e.printStackTrace();
			fse.close();
		}
		
	}
}
