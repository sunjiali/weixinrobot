package org.liufeng.course.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.liufeng.course.pojo.Knowledge;
import org.liufeng.course.service.ChatService;
import org.liufeng.course.service.CoreService;
import org.liufeng.course.util.MySQLUtil;
import org.liufeng.course.util.SignUtil;

/**
 * ������ĺ�����
 * 
 */
public class ExpInitServlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;

	/**
	 * ����У�飨ȷ����������΢�ŷ�������
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MySQLUtil mySQLUtil = new MySQLUtil();
		mySQLUtil.addExp(null, null);
	}

	/**
	 * ����У���봦��
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MySQLUtil mySQLUtil = new MySQLUtil();
		mySQLUtil.addExp(null, null);
	}

	
}
