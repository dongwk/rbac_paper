package com.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	
	public static String getRequestBody(HttpServletRequest request){
        StringBuilder builder = new StringBuilder();
        BufferedReader in = null;
		try {
 			InputStream stream = request.getInputStream();
			in = new BufferedReader(new InputStreamReader(stream));
	        String line = "";
	        while ((line = in.readLine()) != null){
	        	builder.append(line); 
	        }
	        builder.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return builder.toString();
	}
}
