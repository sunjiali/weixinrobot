package org.liufeng.course.service;

import org.liufeng.course.kuaidi.Search;
import org.liufeng.course.util.MySQLUtil;
import org.liufeng.course.util.ServicesUtil;

public class SearchOrderService {
	
	public static String searchOrder(String openId, String createTime, String content){
		
		String answer = null;
		Search search = new Search();
		if(content==null||"".equals(content)){
			answer = "请输入对应的信息：如需闲聊请回复任意文字，如需查询快递请回复 快递名称+快递单号 （如：zhaijisong+4913588568）";
		}else if(search.search(content)==null||"".equals(search.search(content))){
			answer = "对不起！您输入的信息不正确！请核对信息！如需闲聊请回复任意文字，如您需要查询快递请输入：zhaijisong+4913588568  ";
		}else{
		answer =  search.search(content);
		}
		String[] value = content.split("\\+");
		String reqExpress = value[0];
		String reqOrder = value[1];
		ServicesUtil util = new ServicesUtil();
		Long ctime = Long.parseLong(createTime);
		String time = util.timeToString(ctime);

		MySQLUtil.saveSearchRecord(openId, time, reqExpress, reqOrder);
		return answer;
		
	}
	
}
