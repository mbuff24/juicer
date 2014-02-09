package com.juicer;


public class App 
{
	public static void main( String[] args )
	{
		FilterStream fse = new FilterStream();
		try {
			fse.oauth(args[0], args[1], args[2], args[3]);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
