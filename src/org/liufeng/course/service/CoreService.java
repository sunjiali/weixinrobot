package org.liufeng.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.liufeng.course.message.resp.Article;
import org.liufeng.course.message.resp.NewsMessage;
import org.liufeng.course.message.resp.TextMessage;
import org.liufeng.course.pojo.Location;
import org.liufeng.course.util.MessageUtil;
import org.liufeng.course.util.ServicesUtil;

/**
 * ���ķ�����
 * 
 */
public class CoreService {
	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml��ʽ����Ϣ����
		String respXml = null;
		// Ĭ�Ϸ��ص��ı���Ϣ����
		String respContent = "���������ı������ǿ�ʼ����ɣ�";
		String respContentOrder = "���������ı������ǿ�ʼ����ɣ�";

		try {
			// ����parseXml��������������Ϣ
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// ���ͷ��ʺ�
			String fromUserName = requestMap.get("FromUserName");
			// ������΢�ź�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");
			// ��Ϣ����ʱ��
			String createTime = requestMap.get("CreateTime");

	
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				
				 // �¼�����  
                String eventType = requestMap.get("Event");  
                // ����  
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
					respContent = "��л���Ĺ�ע������������ظ��������ֻ��������������ѯ�����ظ� �������+��ݵ��� ���磺�ϴ���+3100365726452) �ظ���'����' ��ȡ������Ϣ��";
					
					// ��ע�ظ���

					TextMessage textMessage = new TextMessage();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage.setContent(respContent);
					// ���ı���Ϣ����ת����xml
					respXml = MessageUtil.messageToXml(textMessage);

                }  
                // ȡ������  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    // TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ  
                }  
				//����ͼƬ��Ϣ
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){
				// �ظ��ı���Ϣ  
	            TextMessage textMessage = new TextMessage();  
	            textMessage.setToUserName(fromUserName);  
	            textMessage.setFromUserName(toUserName);  
	            textMessage.setCreateTime(new Date().getTime());  
	            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT); 
				
				// ȡ��ͼƬ��ַ  
                String picUrl = requestMap.get("PicUrl");  
                // �������  
                String detectResult = FaceService.detect(picUrl);  
                textMessage.setContent(detectResult);  
                respXml = MessageUtil.messageToXml(textMessage);
				
				//����������Ϣ
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){
				
				String content = requestMap.get("Recognition");
				respContent = ChatService.chatting(fromUserName, createTime,
						content);
				
			// �ظ��ı���Ϣ �����ģ�
//			    TextMessage textMessage = new TextMessage();
//			    textMessage.setToUserName(fromUserName);
//				textMessage.setFromUserName(toUserName);
//				textMessage.setCreateTime(new Date().getTime());
//				textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//				textMessage.setContent(respContent);
				// ���ı���Ϣ����ת����xml
//				respXml = MessageUtil.messageToXml(textMessage);
				
				// �ظ��ı���Ϣ �����ģ�
				try {
					JSONObject resp = new JSONObject(respContent);
					if(resp.getString("code").equals("100000")){
						respContent = resp.getString("text");
						TextMessage textMessage = new TextMessage();
						textMessage.setToUserName(fromUserName);
						textMessage.setFromUserName(toUserName);
						textMessage.setCreateTime(new Date().getTime());
						textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
						textMessage.setContent(respContent);
						// ���ı���Ϣ����ת����xml
						respXml = MessageUtil.messageToXml(textMessage);
						
					}else if(resp.getString("code").equals("200000")){
						respContent = resp.getString("text")+"<a href="+'"'+resp.getString("url")+'"'+">"+resp.getString("url")+"</a> ";
						TextMessage textMessage = new TextMessage();
						textMessage.setToUserName(fromUserName);
						textMessage.setFromUserName(toUserName);
						textMessage.setCreateTime(new Date().getTime());
						textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
						textMessage.setContent(respContent);
						// ���ı���Ϣ����ת����xml
						respXml = MessageUtil.messageToXml(textMessage);
						//��ѯ����
					}else if(resp.getString("code").equals("302000")){
						List<Article> articles = new ArrayList<Article>();
						// ����ͼ����Ϣ  
		                NewsMessage newsMessage = new NewsMessage();  
		                newsMessage.setToUserName(fromUserName);  
		                newsMessage.setFromUserName(toUserName);  
		                newsMessage.setCreateTime(new Date().getTime());  
		                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
		                newsMessage.setFuncFlag(0);
		                Article articletext = new Article();
		                articletext.setTitle(resp.getString("text"));
		                articletext.setPicUrl("");
		                articletext.setUrl("http://news.163.com/");
		                articletext.setDescription("");
		                articles.add(articletext);
						JSONArray array = new JSONArray(resp.get("list").toString());
						for (int i = 0; i < array.length(); i++) {
							if(i < 9){
							JSONObject iObj = array.getJSONObject(i);
							Article article = new Article();
							article.setTitle(iObj.getString("article"));
							article.setDescription("");
							article.setPicUrl(iObj.getString("icon"));
							article.setUrl(iObj.getString("detailurl"));
							articles.add(article);
							}
						}
						newsMessage.setArticleCount(articles.size());
						newsMessage.setArticles(articles);
						respXml = MessageUtil.messageToXml(newsMessage);
						//��ѯ�г�
					}else if(resp.getString("code").equals("305000")){
						
						List<Article> articles = new ArrayList<Article>();
						// ����ͼ����Ϣ  
		                NewsMessage newsMessage = new NewsMessage();  
		                newsMessage.setToUserName(fromUserName);  
		                newsMessage.setFromUserName(toUserName);  
		                newsMessage.setCreateTime(new Date().getTime());  
		                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
		                newsMessage.setFuncFlag(0);
		                Article articletext = new Article();
		                articletext.setTitle(resp.getString("text"));
		                articletext.setPicUrl("");
		                articletext.setUrl("");
		                articletext.setDescription("");
		                articles.add(articletext);
						JSONArray array = new JSONArray(resp.get("list").toString());
						for (int i = 0; i < array.length(); i++) {
							if(i < 9){
							JSONObject iObj = array.getJSONObject(i);
							Article article = new Article();
							article.setTitle("���Σ�"+iObj.getString("trainnum")+"-"+iObj.getString("start")+"["+iObj.getString("starttime")+"��]"+"-"+iObj.getString("terminal")+"["+iObj.getString("endtime")+"��]");
							article.setDescription("");
							article.setPicUrl(iObj.getString("icon"));
							article.setUrl(iObj.getString("detailurl"));
							articles.add(article);
							}
						}
						newsMessage.setArticleCount(articles.size());
						newsMessage.setArticles(articles);
						respXml = MessageUtil.messageToXml(newsMessage);
						//��ѯ����
					}else if(resp.getString("code").equals("306000")){
						List<Article> articles = new ArrayList<Article>();
						// ����ͼ����Ϣ  
		                NewsMessage newsMessage = new NewsMessage();  
		                newsMessage.setToUserName(fromUserName);  
		                newsMessage.setFromUserName(toUserName);  
		                newsMessage.setCreateTime(new Date().getTime());  
		                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
		                newsMessage.setFuncFlag(0);
		                Article articletext = new Article();
		                articletext.setTitle(resp.getString("text"));
		                articletext.setPicUrl("");
		                articletext.setUrl("");
		                articletext.setDescription("");
		                articles.add(articletext);
						JSONArray array = new JSONArray(resp.get("list").toString());
						for (int i = 0; i < array.length(); i++) {
							if(i < 9){
							JSONObject iObj = array.getJSONObject(i);
							Article article = new Article();
							article.setTitle(iObj.getString("flight"));
							article.setDescription("");
							article.setPicUrl(iObj.getString("icon"));
//							article.setUrl(iObj.getString("detailurl"));
							articles.add(article);
							}
						}
						newsMessage.setArticleCount(articles.size());
						newsMessage.setArticles(articles);
						respXml = MessageUtil.messageToXml(newsMessage);
						//��ѯ����
					}else if(resp.getString("code").equals("308000")){
						List<Article> articles = new ArrayList<Article>();
						// ����ͼ����Ϣ  
		                NewsMessage newsMessage = new NewsMessage();  
		                newsMessage.setToUserName(fromUserName);  
		                newsMessage.setFromUserName(toUserName);  
		                newsMessage.setCreateTime(new Date().getTime());  
		                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
		                newsMessage.setFuncFlag(0);
		                Article articletext = new Article();
		                articletext.setTitle(resp.getString("text"));
		                articletext.setPicUrl("");
		                articletext.setUrl("");
		                articletext.setDescription("");
		                articles.add(articletext);
						JSONArray array = new JSONArray(resp.get("list").toString());
						for (int i = 0; i < array.length(); i++) {
							if(i < 9){
							JSONObject iObj = array.getJSONObject(i);
							Article article = new Article();
							article.setTitle(iObj.getString("name"));
							article.setDescription(iObj.getString("info"));
							article.setPicUrl(iObj.getString("icon"));
							article.setUrl(iObj.getString("detailurl"));
							articles.add(article);
							}
						}
						newsMessage.setArticleCount(articles.size());
						newsMessage.setArticles(articles);
						respXml = MessageUtil.messageToXml(newsMessage);
					}
					
				} catch (Exception e) {// �״� ˵��JSON�ַ��������������Ͳ���JSON
					e.printStackTrace();
				}
			
				// �ı���Ϣ
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content");
				ServicesUtil servicesUtil = new ServicesUtil();
				ExpressList expressList = new ExpressList();
				//��ȡ������Ϣ
				if(servicesUtil.expressList(content)){
					respContentOrder = expressList.expressList();
					
					// ����ͼ����Ϣ  
	                NewsMessage newsMessage = new NewsMessage();  
	                newsMessage.setToUserName(fromUserName);  
	                newsMessage.setFromUserName(toUserName);  
	                newsMessage.setCreateTime(new Date().getTime());  
	                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
	                newsMessage.setFuncFlag(0);  
	  
	                List<Article> articleList = new ArrayList<Article>();
	                
	                Article article = new Article();  
                    article.setTitle("΢�Ź���ƽ̨������Ϣ");  
                    article.setDescription("����������ظ��������֣������ѯ�����ظ� �������+��ݵ��� ���磺�ϴ���+3100365726452) �ظ���'����' ��ȡ������Ϣ��");  
                    article.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/EE933PZfJlliak7ZRbbRguPria9vGcWWkv0teRSArekY3W6sLD7QtOx18fjFc5akqfHMNKawseA7lzIjeYbicPkkQ/0?wx_fmt=jpeg");  
                    article.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5NTUyMTY2MQ==&mid=204737772&idx=1&sn=f46e04471423669976ff19da77b4b81e#rd");  
                    articleList.add(article);  
                    // ����ͼ����Ϣ����  
                    newsMessage.setArticleCount(articleList.size());  
                    // ����ͼ����Ϣ������ͼ�ļ���  
                    newsMessage.setArticles(articleList);  
                    // ��ͼ����Ϣ����ת����xml�ַ���  
					respXml = MessageUtil.messageToXml(newsMessage);
					//����
				}else if (servicesUtil.service(content)) {
					respContentOrder = SearchOrderService.searchOrder(
							fromUserName, createTime, content);

					// ͼ�Ŀ����Ϣ
					NewsMessage newsMessage = new NewsMessage();

					Article article = new Article();
					article.setTitle(content);
					article.setDescription(respContentOrder);
					article.setPicUrl("");
					article.setUrl("");
					List<Article> articles = new ArrayList<Article>();

					articles.add(article);
					newsMessage.setArticleCount(0);
					newsMessage.setArticles(articles);
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setFromUserName(fromUserName);
					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					newsMessage.setToUserName(toUserName);
					// �ظ���ݲ�ѯ��Ϣ

					TextMessage textMessage = new TextMessage();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage.setContent(respContentOrder);
					// ���ı���Ϣ����ת����xml
					respXml = MessageUtil.messageToXml(textMessage);
				} else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE) || msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
					if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){
						 content = requestMap.get("Recognition");
					}
					respContent = ChatService.chatting(fromUserName, createTime,
							content);
					
					// �ظ��ı���Ϣ �����ģ�
					try {
						JSONObject resp = new JSONObject(respContent);
						if(resp.getString("code").equals("100000")){
							respContent = resp.getString("text");
							TextMessage textMessage = new TextMessage();
							textMessage.setToUserName(fromUserName);
							textMessage.setFromUserName(toUserName);
							textMessage.setCreateTime(new Date().getTime());
							textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
							textMessage.setContent(respContent);
							// ���ı���Ϣ����ת����xml
							respXml = MessageUtil.messageToXml(textMessage);
							
						}else if(resp.getString("code").equals("200000")){
							respContent = resp.getString("text")+"<a href="+'"'+resp.getString("url")+'"'+">"+resp.getString("url")+"</a> ";
							TextMessage textMessage = new TextMessage();
							textMessage.setToUserName(fromUserName);
							textMessage.setFromUserName(toUserName);
							textMessage.setCreateTime(new Date().getTime());
							textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
							textMessage.setContent(respContent);
							// ���ı���Ϣ����ת����xml
							respXml = MessageUtil.messageToXml(textMessage);
							//��ѯ����
						}else if(resp.getString("code").equals("302000")){
							List<Article> articles = new ArrayList<Article>();
							// ����ͼ����Ϣ  
			                NewsMessage newsMessage = new NewsMessage();  
			                newsMessage.setToUserName(fromUserName);  
			                newsMessage.setFromUserName(toUserName);  
			                newsMessage.setCreateTime(new Date().getTime());  
			                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
			                newsMessage.setFuncFlag(0);
			                Article articletext = new Article();
			                articletext.setTitle(resp.getString("text"));
			                articletext.setPicUrl("");
			                articletext.setUrl("http://news.163.com/");
			                articletext.setDescription("");
			                articles.add(articletext);
							JSONArray array = new JSONArray(resp.get("list").toString());
							for (int i = 0; i < array.length(); i++) {
								if(i < 9){
								JSONObject iObj = array.getJSONObject(i);
								Article article = new Article();
								article.setTitle(iObj.getString("article"));
								article.setDescription("");
								article.setPicUrl(iObj.getString("icon"));
								article.setUrl(iObj.getString("detailurl"));
								articles.add(article);
								}
							}
							newsMessage.setArticleCount(articles.size());
							newsMessage.setArticles(articles);
							respXml = MessageUtil.messageToXml(newsMessage);
							//��ѯ�г�
						}else if(resp.getString("code").equals("305000")){
							
							List<Article> articles = new ArrayList<Article>();
							// ����ͼ����Ϣ  
			                NewsMessage newsMessage = new NewsMessage();  
			                newsMessage.setToUserName(fromUserName);  
			                newsMessage.setFromUserName(toUserName);  
			                newsMessage.setCreateTime(new Date().getTime());  
			                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
			                newsMessage.setFuncFlag(0);
			                Article articletext = new Article();
			                articletext.setTitle(resp.getString("text"));
			                articletext.setPicUrl("");
			                articletext.setUrl("");
			                articletext.setDescription("");
			                articles.add(articletext);
							JSONArray array = new JSONArray(resp.get("list").toString());
							for (int i = 0; i < array.length(); i++) {
								if(i < 9){
								JSONObject iObj = array.getJSONObject(i);
								Article article = new Article();
								article.setTitle("���Σ�"+iObj.getString("trainnum")+"-"+iObj.getString("start")+"["+iObj.getString("starttime")+"]��"+"-"+iObj.getString("terminal")+"["+iObj.getString("endtime")+"]��");
								article.setDescription("");
								article.setPicUrl(iObj.getString("icon"));
								article.setUrl(iObj.getString("detailurl"));
								articles.add(article);
								}
							}
							newsMessage.setArticleCount(articles.size());
							newsMessage.setArticles(articles);
							respXml = MessageUtil.messageToXml(newsMessage);
							//��ѯ����
						}else if(resp.getString("code").equals("306000")){
							List<Article> articles = new ArrayList<Article>();
							// ����ͼ����Ϣ  
			                NewsMessage newsMessage = new NewsMessage();  
			                newsMessage.setToUserName(fromUserName);  
			                newsMessage.setFromUserName(toUserName);  
			                newsMessage.setCreateTime(new Date().getTime());  
			                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
			                newsMessage.setFuncFlag(0);
			                Article articletext = new Article();
			                articletext.setTitle(resp.getString("text"));
			                articletext.setPicUrl("");
			                articletext.setUrl("");
			                articletext.setDescription("");
			                articles.add(articletext);
							JSONArray array = new JSONArray(resp.get("list").toString());
							for (int i = 0; i < array.length(); i++) {
								if(i < 9){
								JSONObject iObj = array.getJSONObject(i);
								Article article = new Article();
								article.setTitle(iObj.getString("flight"));
								article.setDescription("");
								article.setPicUrl(iObj.getString("icon"));
//								article.setUrl(iObj.getString("detailurl"));
								articles.add(article);
								}
							}
							newsMessage.setArticleCount(articles.size());
							newsMessage.setArticles(articles);
							respXml = MessageUtil.messageToXml(newsMessage);
							//��ѯ����
						}else if(resp.getString("code").equals("308000")){
							List<Article> articles = new ArrayList<Article>();
							// ����ͼ����Ϣ  
			                NewsMessage newsMessage = new NewsMessage();  
			                newsMessage.setToUserName(fromUserName);  
			                newsMessage.setFromUserName(toUserName);  
			                newsMessage.setCreateTime(new Date().getTime());  
			                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
			                newsMessage.setFuncFlag(0);
			                Article articletext = new Article();
			                articletext.setTitle(resp.getString("text"));
			                articletext.setPicUrl("");
			                articletext.setUrl("");
			                articletext.setDescription("");
			                articles.add(articletext);
							JSONArray array = new JSONArray(resp.get("list").toString());
							for (int i = 0; i < array.length(); i++) {
								if(i < 9){
								JSONObject iObj = array.getJSONObject(i);
								Article article = new Article();
								article.setTitle(iObj.getString("name"));
								article.setDescription(iObj.getString("info"));
								article.setPicUrl(iObj.getString("icon"));
								article.setUrl(iObj.getString("detailurl"));
								articles.add(article);
								}
							}
							newsMessage.setArticleCount(articles.size());
							newsMessage.setArticles(articles);
							respXml = MessageUtil.messageToXml(newsMessage);
						}
						
					} catch (Exception e) {// �״� ˵��JSON�ַ��������������Ͳ���JSON
						e.printStackTrace();
					}
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}