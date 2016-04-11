package org.liufeng.course.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.liufeng.course.kuaidi.ExpressImp;
import org.liufeng.course.pojo.Chat;
import org.liufeng.course.pojo.Express;
import org.liufeng.course.pojo.Knowledge;
import org.liufeng.course.pojo.OrderSearch;

/**
 * Mysql数据库操作类
 * 
 */
public class MySQLUtil {
	/**
	 * 获取Mysql数据库连接
	 * 
	 * @return Connection
	 */
	private Connection getConn() {
		String databaseName = "svUwUCloeePCTSmaPnqV";
		String host = "sqld.duapp.com";
		String port = "4050";
		String url = "jdbc:mysql://";
		String username = "RtasXBU0UWRlSNnSxApOekIP";
		String password = "smg3jsy8MCotlGZL238tFp5f84hmFcH9";
		String serverName = host + ":" + port + "/";
		String connName = url + serverName + databaseName;
		Connection conn = null;
		try {
			// 加载MySQL驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取数据库连接
			System.out.println(connName);
			conn = DriverManager.getConnection(connName, username, password);
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 释放JDBC资源
	 * 
	 * @param conn
	 *            数据库连接
	 * @param ps
	 * @param rs
	 *            记录集
	 */
	private void releaseResources(Connection conn, PreparedStatement ps,
			ResultSet rs) {
		try {
			if (null != rs)
				rs.close();
			if (null != ps)
				ps.close();
			if (null != conn)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取问答知识表中所有记录
	 * 
	 * @return List<Knowledge>
	 */
	public static List<Knowledge> findAllKnowledge() {
		List<Knowledge> knowledgeList = new ArrayList<Knowledge>();
		String sql = "select * from knowledge";
		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Knowledge knowledge = new Knowledge();
				knowledge.setId(rs.getInt("id"));
				knowledge.setQuestion(rs.getString("question"));
				knowledge.setAnswer(rs.getString("answer"));
				knowledge.setCategory(rs.getInt("category"));
				knowledgeList.add(knowledge);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return knowledgeList;
	}

	/**
	 * 获取上一次的聊天类别
	 * 
	 * @param openId
	 *            用户的OpenID
	 * @return chatCategory
	 */
	public static int getLastCategory(String openId) {
		int chatCategory = -1;
		String sql = "select chat_category from chat_log where open_id=? order by id desc limit 0,1";

		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, openId);
			rs = ps.executeQuery();
			if (rs.next()) {
				chatCategory = rs.getInt("chat_category");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return chatCategory;
	}

	/**
	 * 根据知识id随机获取一个答案
	 * 
	 * @param knowledgeId
	 *            问答知识id
	 * @return
	 */
	public static String getKnowledSub(int knowledgeId) {
		String knowledgeAnswer = "";
		String sql = "select answer from knowledge_sub where pid=? order by rand() limit 0,1";

		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, knowledgeId);
			rs = ps.executeQuery();
			if (rs.next()) {
				knowledgeAnswer = rs.getString("answer");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return knowledgeAnswer;
	}

	public static List<Chat> findAllChat() {
		List<Chat> chatList = new ArrayList<Chat>();
		String sql = "select * from chat_log";
		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Chat chat = new Chat();
				chat.setId(rs.getInt("id"));
				chat.setOpen_id((rs.getString("open_id")));
				chat.setCreate_time((rs.getString("create_time")));
				chat.setReq_msg(rs.getString("req_msg"));
				chat.setResp_msg(rs.getString("resp_msg"));
				
				chatList.add(chat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return chatList;
	}
	
	public static List<OrderSearch> findAllSearchList() {
		List<OrderSearch> orderSearchs = new ArrayList<OrderSearch>();
		String sql = "select * from chat_log";
		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				OrderSearch orderSearch = new OrderSearch();
				orderSearch.setId(rs.getInt("id"));
				orderSearch.setOpen_id((rs.getString("open_id")));
				orderSearch.setCreate_time((rs.getString("create_time")));
				orderSearch.setReq_express((rs.getString("req_express")));
				orderSearch.setReq_order((rs.getString("req_order")));
				
				orderSearchs.add(orderSearch);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return orderSearchs;
	}
	
	/**
	 * 随机获取一条笑话
	 * 
	 * @return String
	 */
	public static String getJoke() {
		String jokeContent = "";
		String sql = "select joke_content from joke order by rand() limit 0,1";

		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				jokeContent = rs.getString("joke_content");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return jokeContent;
	}

	/**
	 * 保存聊天记录
	 * 
	 * @param openId
	 *            用户的OpenID
	 * @param createTime
	 *            消息创建时间
	 * @param reqMsg
	 *            用户上行的消息
	 * @param respMsg
	 *            公众账号回复的消息
	 * @param chatCategory
	 *            聊天类别
	 */
	public static void saveChatLog(String openId, String createTime,
			String reqMsg, String respMsg, int chatCategory) {
		String sql = "insert into chat_log(open_id, create_time, req_msg, resp_msg, chat_category) values(?, ?, ?, ?, ?)";

		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, openId);
			ps.setString(2, createTime);
			ps.setString(3, reqMsg);
			ps.setString(4, respMsg);
			ps.setInt(5, chatCategory);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
	}
	
	public static void saveSearchRecord(String openId, String createTime,
			String reqExpress, String reqOrder){
		String sql = "insert into Search_Record(open_id, create_time, req_express, req_order) values(?, ?, ?, ?)";
		
		MySQLUtil mysqlUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = mysqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, openId);
			ps.setString(2, createTime);
			ps.setString(3, reqExpress);
			ps.setString(4, reqOrder);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		
	}

	public static void addExp(String exp_id, String expName) {

		String sql = "insert into exp (exp_id,expName)values(?,?) ";
		MySQLUtil mySQLUtil = new MySQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ExpressImp imp = new ExpressImp();
		List<Express> expresses = imp.list();
		
			try {
				for (Express express : expresses) {

				conn = mySQLUtil.getConn();
				ps = conn.prepareStatement(sql);
				System.out.println(express.getExp_id());
				ps.setString(1, express.getExp_id());
				System.out.println(express.getExpName());
				ps.setString(2, express.getExpName());
				ps.executeUpdate();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				mySQLUtil.releaseResources(conn, ps, rs);
			}
	

	}
	

}
