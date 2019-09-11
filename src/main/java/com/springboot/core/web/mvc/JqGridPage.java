package com.springboot.core.web.mvc;

import java.util.ArrayList;
import java.util.Collection;

/**
 * jqGrid表格响应实体类
 * 
 * @ClassName: JqGridPage
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author link
 * @date 2018年12月14日 下午1:50:52
 * 
 * @param <T>
 */
public class JqGridPage<T> {

	private int total; // 总页数

	private int pageSize; // 分页步长

	private int page; // 当前页

	private int records; // 总记录数

	private Collection<T> rows; // 数据

	public JqGridPage(Collection<T> rows, int records, int pageSize, int page) {
		this.rows = (rows == null ? new ArrayList<T>(0) : rows);
		this.records = records;
		this.page = page;
		this.pageSize = pageSize;
		this.total = calcTotalPage();
	}

	private int calcTotalPage() {
		int t = getRecords();
		int p = getPageSize();
		if (t == 0 || p == 0)
			return 0;
		int r = t % p;
		int pages = (t - r) / p;
		if (r > 0)
			pages += 1;
		return pages;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public Collection<T> getRows() {
		return rows;
	}

	public void setRows(Collection<T> rows) {
		this.rows = rows;
	}

}
