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
 * 核心服务类
 * 
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = "发送任意文本，我们开始聊天吧！";
		String respContentOrder = "发送任意文本，我们开始聊天吧！";

		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 消息创建时间
			String createTime = requestMap.get("CreateTime");

	
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				
				 // 事件类型  
                String eventType = requestMap.get("Event");  
                // 订阅  
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
					respContent = "感谢您的关注！如需闲聊请回复任意文字或发送语音，如需查询快递请回复 快递名称+快递单号 （如：韵达快递+3100365726452) 回复：'帮助' 获取帮助信息！";
					
					// 关注回复语

					TextMessage textMessage = new TextMessage();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage.setContent(respContent);
					// 将文本消息对象转换成xml
					respXml = MessageUtil.messageToXml(textMessage);

                }  
                // 取消订阅  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                }  
				//接收图片消息
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){
				// 回复文本消息  
	            TextMessage textMessage = new TextMessage();  
	            textMessage.setToUserName(fromUserName);  
	            textMessage.setFromUserName(toUserName);  
	            textMessage.setCreateTime(new Date().getTime());  
	            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT); 
				
				// 取得图片地址  
                String picUrl = requestMap.get("PicUrl");  
                // 人脸检测  
                String detectResult = FaceService.detect(picUrl);  
                textMessage.setContent(detectResult);  
                respXml = MessageUtil.messageToXml(textMessage);
				
				//接收语音消息
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){
				
				String content = requestMap.get("Recognition");
				respContent = ChatService.chatting(fromUserName, createTime,
						content);
				
			// 回复文本消息 （闲聊）
//			    TextMessage textMessage = new TextMessage();
//			    textMessage.setToUserName(fromUserName);
//				textMessage.setFromUserName(toUserName);
//				textMessage.setCreateTime(new Date().getTime());
//				textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//				textMessage.setContent(respContent);
				// 将文本消息对象转换成xml
//				respXml = MessageUtil.messageToXml(textMessage);
				
				// 回复文本消息 （闲聊）
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
						// 将文本消息对象转换成xml
						respXml = MessageUtil.messageToXml(textMessage);
						
					}else if(resp.getString("code").equals("200000")){
						respContent = resp.getString("text")+"<a href="+'"'+resp.getString("url")+'"'+">"+resp.getString("url")+"</a> ";
						TextMessage textMessage = new TextMessage();
						textMessage.setToUserName(fromUserName);
						textMessage.setFromUserName(toUserName);
						textMessage.setCreateTime(new Date().getTime());
						textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
						textMessage.setContent(respContent);
						// 将文本消息对象转换成xml
						respXml = MessageUtil.messageToXml(textMessage);
						//查询新闻
					}else if(resp.getString("code").equals("302000")){
						List<Article> articles = new ArrayList<Article>();
						// 创建图文消息  
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
						//查询列车
					}else if(resp.getString("code").equals("305000")){
						
						List<Article> articles = new ArrayList<Article>();
						// 创建图文消息  
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
							article.setTitle("车次："+iObj.getString("trainnum")+"-"+iObj.getString("start")+"["+iObj.getString("starttime")+"开]"+"-"+iObj.getString("terminal")+"["+iObj.getString("endtime")+"到]");
							article.setDescription("");
							article.setPicUrl(iObj.getString("icon"));
							article.setUrl(iObj.getString("detailurl"));
							articles.add(article);
							}
						}
						newsMessage.setArticleCount(articles.size());
						newsMessage.setArticles(articles);
						respXml = MessageUtil.messageToXml(newsMessage);
						//查询航班
					}else if(resp.getString("code").equals("306000")){
						List<Article> articles = new ArrayList<Article>();
						// 创建图文消息  
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
						//查询菜谱
					}else if(resp.getString("code").equals("308000")){
						List<Article> articles = new ArrayList<Article>();
						// 创建图文消息  
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
					
				} catch (Exception e) {// 抛错 说明JSON字符不是数组或根本就不是JSON
					e.printStackTrace();
				}
			
				// 文本消息
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content");
				ServicesUtil servicesUtil = new ServicesUtil();
				ExpressList expressList = new ExpressList();
				//获取帮助信息
				if(servicesUtil.expressList(content)){
					respContentOrder = expressList.expressList();
					
					// 创建图文消息  
	                NewsMessage newsMessage = new NewsMessage();  
	                newsMessage.setToUserName(fromUserName);  
	                newsMessage.setFromUserName(toUserName);  
	                newsMessage.setCreateTime(new Date().getTime());  
	                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
	                newsMessage.setFuncFlag(0);  
	  
	                List<Article> articleList = new ArrayList<Article>();
	                
	                Article article = new Article();  
                    article.setTitle("微信公众平台帮助信息");  
                    article.setDescription("如需闲聊请回复任意文字，如需查询快递请回复 快递名称+快递单号 （如：韵达快递+3100365726452) 回复：'帮助' 获取帮助信息！");  
                    article.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/EE933PZfJlliak7ZRbbRguPria9vGcWWkv0teRSArekY3W6sLD7QtOx18fjFc5akqfHMNKawseA7lzIjeYbicPkkQ/0?wx_fmt=jpeg");  
                    article.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5NTUyMTY2MQ==&mid=204737772&idx=1&sn=f46e04471423669976ff19da77b4b81e#rd");  
                    articleList.add(article);  
                    // 设置图文消息个数  
                    newsMessage.setArticleCount(articleList.size());  
                    // 设置图文消息包含的图文集合  
                    newsMessage.setArticles(articleList);  
                    // 将图文消息对象转换成xml字符串  
					respXml = MessageUtil.messageToXml(newsMessage);
					//查快递
				}else if (servicesUtil.service(content)) {
					respContentOrder = SearchOrderService.searchOrder(
							fromUserName, createTime, content);

					// 图文快递消息
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
					// 回复快递查询信息

					TextMessage textMessage = new TextMessage();
					textMessage.setToUserName(fromUserName);
					textMessage.setFromUserName(toUserName);
					textMessage.setCreateTime(new Date().getTime());
					textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
					textMessage.setContent(respContentOrder);
					// 将文本消息对象转换成xml
					respXml = MessageUtil.messageToXml(textMessage);
				} else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE) || msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
					if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){
						 content = requestMap.get("Recognition");
					}
					respContent = ChatService.chatting(fromUserName, createTime,
							content);
					
					// 回复文本消息 （闲聊）
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
							// 将文本消息对象转换成xml
							respXml = MessageUtil.messageToXml(textMessage);
							
						}else if(resp.getString("code").equals("200000")){
							respContent = resp.getString("text")+"<a href="+'"'+resp.getString("url")+'"'+">"+resp.getString("url")+"</a> ";
							TextMessage textMessage = new TextMessage();
							textMessage.setToUserName(fromUserName);
							textMessage.setFromUserName(toUserName);
							textMessage.setCreateTime(new Date().getTime());
							textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
							textMessage.setContent(respContent);
							// 将文本消息对象转换成xml
							respXml = MessageUtil.messageToXml(textMessage);
							//查询新闻
						}else if(resp.getString("code").equals("302000")){
							List<Article> articles = new ArrayList<Article>();
							// 创建图文消息  
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
							//查询列车
						}else if(resp.getString("code").equals("305000")){
							
							List<Article> articles = new ArrayList<Article>();
							// 创建图文消息  
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
								article.setTitle("车次："+iObj.getString("trainnum")+"-"+iObj.getString("start")+"["+iObj.getString("starttime")+"]开"+"-"+iObj.getString("terminal")+"["+iObj.getString("endtime")+"]到");
								article.setDescription("");
								article.setPicUrl(iObj.getString("icon"));
								article.setUrl(iObj.getString("detailurl"));
								articles.add(article);
								}
							}
							newsMessage.setArticleCount(articles.size());
							newsMessage.setArticles(articles);
							respXml = MessageUtil.messageToXml(newsMessage);
							//查询航班
						}else if(resp.getString("code").equals("306000")){
							List<Article> articles = new ArrayList<Article>();
							// 创建图文消息  
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
							//查询菜谱
						}else if(resp.getString("code").equals("308000")){
							List<Article> articles = new ArrayList<Article>();
							// 创建图文消息  
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
						
					} catch (Exception e) {// 抛错 说明JSON字符不是数组或根本就不是JSON
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