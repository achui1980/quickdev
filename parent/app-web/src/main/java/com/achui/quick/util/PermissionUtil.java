package com.achui.quick.util;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.shiro.SecurityUtils;

public class PermissionUtil {

	public static boolean checkPermission(String permission){
		return SecurityUtils.getSubject().isPermitted(permission);
	}
	
	 public static void main(String[] args){
			try {
				URL url = new URL("https://api-qa.ehealth.com/v1/cem/user/benefits");
				HttpURLConnection connection = (HttpURLConnection)url.openConnection();
				connection.setRequestMethod("DELETE");
				connection.setRequestProperty("Authorization", "Bearer 75OGot4iNWnthWvxOpjT9tizMgGb");
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setRequestProperty("apikey", "SdAIUTza8ftAAMAN4VebkFNGSeDGHXGM");
				connection.setRequestProperty("Accept", "application/json");
				BufferedInputStream in = new BufferedInputStream(connection.getInputStream());  
				Reader r = new InputStreamReader(in); 
				StringBuffer source = new StringBuffer("");
				int i;  
				 while ((i = r.read()) != -1) {  
				      source.append((char) i);  
				 } 
				 System.out.println(source.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
