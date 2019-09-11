package com.springboot.bcode.domain.index;

/**
 * 回访提醒
 * 
 * @ClassName: ReturnVisit
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author link
 * @date 2019年1月19日 上午8:58:54
 *
 */
public class ReturnVisitRemind {
	private Integer advisoryId;// 咨询id
	private String customerName;// 客户姓名
	private String hfsj;// 回访时间
	private String hfnote;// 回访日期

	public Integer getAdvisoryId() {
		return advisoryId;
	}

	public void setAdvisoryId(Integer advisoryId) {
		this.advisoryId = advisoryId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getHfsj() {
		return hfsj;
	}

	public void setHfsj(String hfsj) {
		this.hfsj = hfsj;
	}

	public String getHfnote() {
		return hfnote;
	}

	public void setHfnote(String hfnote) {
		this.hfnote = hfnote;
	}

}
