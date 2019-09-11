package com.springboot.core.web.mvc;

/**
 * jqGrid请求参数
 * 
 * @ClassName: JqGridParam
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author link
 * @date 2018年12月14日 下午1:51:17
 *
 */
public class JqGridParam {
	private Integer page;// 当前页
	private Integer rows;// 分页步长
	private String sidx; // 排序 字段
	private String sord; // asc 或 desc

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

}
