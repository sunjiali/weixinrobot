package org.liufeng.course.pojo;


/**
 *快递单model
 *
 */
public class Order {
	
	private int status;  //快递状态
	private int errCode;// 错误代码
	private String message;//错误消息
	private String[] data;  //进度
	private String html;//其他HTML
	private	String mailNo;//快递单号
	private String expSpellName;//快递公司英文代码

	private String expTextName;//快递公司中文名
	private int update;//最后更新时间（unix 时间戳）
	private int cache;//缓存时间，当前时间与 update 之间的差值，单位为：秒
	private String ord;//排序，ASC | DESC
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
