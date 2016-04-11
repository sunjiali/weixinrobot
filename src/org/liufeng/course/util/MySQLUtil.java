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
 * Mysql���ݿ������
 * 
 */
public class MySQLUtil {
	/**
	 * ��ȡMysql���ݿ�����
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
			// ����MySQL����
			Class.forName("com.mysql.jdbc.Driver");
			// ��ȡ���ݿ�����
			System.out.println(connName);
			conn = DriverManager.getConnection(connName, username, password);
			System.out.println("���ݿ����ӳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * �ͷ�JDBC��Դ
	 * 
	 * @param conn
	 *            ���ݿ�����
	 * @param ps
	 * @param rs
	 *            ��¼��
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
	 * ��ȡ�ʴ�֪ʶ�������м�¼
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
			// �ͷ���Դ
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return knowledgeList;
	}

	/**
	 * ��ȡ��һ�ε��������
	 * 
	 * @param openId
	 *            �û���OpenID
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
			// �ͷ���Դ
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return chatCategory;
	}

	/**
	 * ����֪ʶid�����ȡһ����
	 * 
	 * @param knowledgeId
	 *            �ʴ�֪ʶid
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
			// �ͷ���Դ
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
			// �ͷ���Դ
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
			// �ͷ���Դ
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return orderSearchs;
	}
	
	/**
	 * �����ȡһ��Ц��
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
			// �ͷ���Դ
			mysqlUtil.releaseResources(conn, ps, rs);
		}
		return jokeContent;
	}

	/**
	 * ���������¼
	 * 
	 * @param openId
	 *            �û���OpenID
	 * @param createTime
	 *            ��Ϣ����ʱ��
	 * @param reqMsg
	 *            �û����е���Ϣ
	 * @param respMsg
	 *            �����˺Żظ�����Ϣ
	 * @param chatCategory
	 *            �������
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
			// �ͷ���Դ
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
			// �ͷ���Դ
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
