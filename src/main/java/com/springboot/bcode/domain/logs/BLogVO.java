package com.springboot.bcode.domain.logs;

import com.springboot.core.web.mvc.JqGridParam;

public class BLogVO extends JqGridParam {
	private String starttime;
	private String endtime;
	private String loginuser;

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getLoginuser() {
		return loginuser;
	}

	public void setLoginuser(String loginuser) {
		this.loginuser = loginuser;
	}

}
