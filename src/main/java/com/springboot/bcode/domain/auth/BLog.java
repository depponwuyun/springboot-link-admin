package com.springboot.bcode.domain.auth;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.core.jdbc.annotation.Columns;
import com.springboot.core.jdbc.annotation.Tables;
import com.springboot.core.web.mvc.JqGridParam;

@Tables(table = "t_web_logs")
public class BLog extends JqGridParam {
	@Columns(column = "id", primaryKey = true, autoIncrement = false)
	private String id;
	@Columns(column = "loginuser")
	private String loginuser;
	@Columns(column = "vsername")
	private String vsername;
	@Columns(column = "hisname")
	private String hisname;
	@Columns(column = "ksname")
	private String ksname;
	@Columns(column = "title")
	private String title;
	@Columns(column = "url")
	private String url;
	@Columns(column = "requestmethod")
	private String requestmethod;
	@Columns(column = "contenttype")
	private String contentType;
	@Columns(column = "requestparams")
	private Object requestparams;
	@Columns(column = "ip")
	private String ip;
	@Columns(column = "cratetime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.sql.Timestamp cratetime;
	@Columns(column = "duration")
	private Long duration;// 持续时间
	@Columns(column = "tcsj")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private String tcsj;
	private Date starttime;
	private Date endtime;

	private Long timestamp;// Span创建时的时间戳，使用的单位是微秒（而不是毫秒），所有时间戳都有错误，包括主机之间的时钟偏差以及时间服务重新设置时钟的可能性，
	// 客户端ip
	private String clientIp;
	// 服务器ip
	private String serverIp;
	// 结果
	private Object result;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginuser() {
		return loginuser;
	}

	public void setLoginuser(String loginuser) {
		this.loginuser = loginuser;
	}

	public String getVsername() {
		return vsername;
	}

	public void setVsername(String vsername) {
		this.vsername = vsername;
	}

	public String getHisname() {
		return hisname;
	}

	public void setHisname(String hisname) {
		this.hisname = hisname;
	}

	public String getKsname() {
		return ksname;
	}

	public void setKsname(String ksname) {
		this.ksname = ksname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public java.sql.Timestamp getCratetime() {
		return cratetime;
	}

	public void setCratetime(java.sql.Timestamp cratetime) {
		this.cratetime = cratetime;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getTcsj() {
		return tcsj;
	}

	public void setTcsj(String tcsj) {
		this.tcsj = tcsj;
	}

	public String getRequestmethod() {
		return requestmethod;
	}

	public void setRequestmethod(String requestmethod) {
		this.requestmethod = requestmethod;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Object getRequestparams() {
		return requestparams;
	}

	public void setRequestparams(Object requestparams) {
		this.requestparams = requestparams;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
