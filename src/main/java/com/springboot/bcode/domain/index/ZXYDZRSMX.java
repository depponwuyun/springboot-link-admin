package com.springboot.bcode.domain.index;

/**
 * 咨询员到诊部门人数
 * 
 * @ClassName: ZXYDZRS
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author link
 * @date 2019年1月19日 上午9:02:05
 *
 */
public class ZXYDZRSMX {
	private String vserName;// 咨询员姓名
	private String deptName;// 到诊部门
	private Integer peopleNum;// 到诊人数

	public String getVserName() {
		return vserName;
	}

	public void setVserName(String vserName) {
		this.vserName = vserName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}

}
