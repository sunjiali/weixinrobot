package org.liufeng.course.pojo;


/**
 *��ݵ�model
 *
 */
public class Order {
	
	private int status;  //���״̬
	private int errCode;// �������
	private String message;//������Ϣ
	private String[] data;  //����
	private String html;//����HTML
	private	String mailNo;//��ݵ���
	private String expSpellName;//��ݹ�˾Ӣ�Ĵ���

	private String expTextName;//��ݹ�˾������
	private int update;//������ʱ�䣨unix ʱ�����
	private int cache;//����ʱ�䣬��ǰʱ���� update ֮��Ĳ�ֵ����λΪ����
	private String ord;//����ASC | DESC
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getErrCode() {
		return errCode;
	}
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getMailNo() {
		return mailNo;
	}
	public void setMailNo(String mailNo) {
		this.mailNo = mailNo;
	}
	public String getExpSpellName() {
		return expSpellName;
	}
	public void setExpSpellName(String expSpellName) {
		this.expSpellName = expSpellName;
	}
	public String getExpTextName() {
		return expTextName;
	}
	public void setExpTextName(String expTextName) {
		this.expTextName = expTextName;
	}
	public int getUpdate() {
		return update;
	}
	public void setUpdate(int update) {
		this.update = update;
	}
	public int getCache() {
		return cache;
	}
	public void setCache(int cache) {
		this.cache = cache;
	}
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
	

}
