package com.springboot.bcode.domain.logs;

import java.util.Date;

import com.springboot.core.jdbc.annotation.Columns;
import com.springboot.core.jdbc.annotation.Tables;

@Tables(table = "t_web_logs")
public class BLog {
	@Columns(column = "id", primaryKey = true)
	private String id;
	@Columns(column = "loginuser")
	private String loginuser;
	@Columns(column = "vsername")
	private String vsername;

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
	private Date cratetime;
	@Columns(column = "duration")
	private Long duration;// 持续时间

	private Long timestamp;// Span创建时的时间戳，使用的单位是微秒（而不是毫秒），所有时间戳都有错误，包括主机之间的时钟偏差以及时间服务重新设置时钟的可能性，
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

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Date getCratetime() {
		return cratetime;
	}

	public void setCratetime(Date cratetime) {
		this.cratetime = cratetime;
	}

}
