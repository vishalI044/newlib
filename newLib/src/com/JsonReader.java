package com;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {
	public static String main(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		String line;
		String returnString = null;
		
		try {
			BufferedReader bufferedReader = request.getReader();
			while((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			
			line = sb.toString();
			//System.out.println(line);
			JSONParser jp = new JSONParser();
			JSONObject jo = new JSONObject();
			jo = (JSONObject) jp.parse(line);
			returnString = jo.get("empCode").toString();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}
}
