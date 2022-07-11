package com;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CreateUser {
	public static JSONObject CreateUser(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		
		Connection con = DBConnection.initialise();
		StringBuffer sb = new StringBuffer();
		String line;
		String userName, password, name, empType;
		int empCode, salary;
		JSONObject jo = new JSONObject();
		PreparedStatement st = null;
		try {
			BufferedReader bufferedReader = request.getReader();
			while((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			
			line = sb.toString();
			//System.out.println(line);
			JSONParser jp = new JSONParser();
			JSONObject jo1 = new JSONObject();
			jo = (JSONObject) jp.parse(line);
			
			userName = jo.get("userName").toString();
			password = jo.get("password").toString();
			name = jo.get("name").toString();
			empType = jo.get("empType").toString();
			
			empCode = Integer.parseInt(jo.get("empCode").toString());
			salary = Integer.parseInt(jo.get("salary").toString());
			
			if(empType.equals("permanent")) {
				String insertQuery = "INSERT INTO Permanent_Employee VALUES(?,?,?,?,?)";
				st = con.prepareStatement(insertQuery);
				st.setInt(1, empCode);
				st.setString(2, userName);
				st.setString(3, password);
				st.setString(4, name);
				st.setInt(5, salary);
				st.setString(6, empType);
			}else if(empType.equals("partTime")) {
				String insertQuery = "INSERT INTO partTime_Employee VALUES(?,?,?,?,?)";
				st = con.prepareStatement(insertQuery);
				st.setInt(1, empCode);
				st.setString(2, userName);
				st.setString(3, password);
				st.setString(4, name);
				st.setInt(5, salary);
				st.setString(6, empType);
			}else if(empType.equals("contract")) {
				String insertQuery = "INSERT INTO Contract_Employee VALUES(?,?,?,?,?)";
				st = con.prepareStatement(insertQuery);
				st.setInt(1, empCode);
				st.setString(2, userName);
				st.setString(3, password);
				st.setString(4, name);
				st.setInt(5, salary);
				st.setString(6, empType);
			}else {
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
