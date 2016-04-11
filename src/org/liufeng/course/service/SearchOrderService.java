package org.liufeng.course.service;

import org.liufeng.course.kuaidi.Search;
import org.liufeng.course.util.MySQLUtil;
import org.liufeng.course.util.ServicesUtil;

public class SearchOrderService {
	
	public static String searchOrder(String openId, String createTime, String content){
		
		String answer = null;
		Search search = new Search();
		if(content==null||"".equals(content)){
			answer = "�������Ӧ����Ϣ������������ظ��������֣������ѯ�����ظ� �������+��ݵ��� ���磺zhaijisong+4913588568��";
		}else if(search.search(content)==null||"".equals(search.search(content))){
			answer = "�Բ������������Ϣ����ȷ����˶���Ϣ������������ظ��������֣�������Ҫ��ѯ��������룺zhaijisong+4913588568  ";
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
