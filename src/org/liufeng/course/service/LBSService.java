package org.liufeng.course.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.liufeng.course.pojo.Location;

public class LBSService {
	
	private static String httpRequest(String requestUrl) {
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("POST");
			httpUrlConn.connect();
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		Location location = new Location(22.539968, 113.954980);
		System.out.println(new LBSService().near(location, ""));
	}
	
	public static String near(Location location ,String name ){
		String json = null;
		String queryUrl = "";
		if(name==""){
		 queryUrl = "http://api.map.baidu.com/place/v2/search?ak=MgBALVVeCd8THVBi6gPdvsvG&output=json&query=美食&page_size=5&page_num=0&scope=2&location="+location.getX()+","+location.getY()+"&radius=2000&filter=sort_name:distance";
		}else{
		 queryUrl = "http://api.map.baidu.com/place/v2/search?ak=MgBALVVeCd8THVBi6gPdvsvG&output=json&query="+name+"&page_size=5&page_num=0&scope=2&location="+location.getX()+","+location.getY()+"&radius=2000&filter=sort_name:distance";
		
		}
		
		json = httpRequest(queryUrl);
		
		return json;
	}

}
