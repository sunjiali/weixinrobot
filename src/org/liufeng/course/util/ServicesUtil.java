package org.liufeng.course.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServicesUtil {
	
	/**
	 * ≈–∂œ «∑Ò «≤È—ØøÏµ›
	 * @param content
	 * @return
	 */
	
	public boolean service(String content){
		String regx = "+";
		if(content.contains(regx)){
			return true;
		}
		
		return false;
		
	}
	
	public boolean expressList(String content){
		if(content.equals("∞Ô÷˙")){
			return true;
		}else{
		return false;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new ServicesUtil().expressList("H"));
	}
	
	public static String timeToString(Long time){
		
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		 Long stringTime=new Long(time)*1000;
		  String d = format.format(stringTime);
		return d;
		
	}

}
