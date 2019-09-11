package com.springboot.bcode.domain.auth;

import com.springboot.core.jdbc.annotation.Columns;
import com.springboot.core.jdbc.annotation.Tables;

@Tables(table = "t_web_dept")
public class Department {

	// Fields
	@Columns(column = "id", primaryKey = true)
	private Integer id;
	@Columns(column = "name")
	private String name;
	@Columns(column = "parent_id")
	private Integer parentId;
	@Columns(column = "sorts")
	private Integer sorts;
	@Columns(column = "levels")
	private Integer levels;// 等级0 集团，1医院 2部门
	@Columns(column = "for_service")
	private Integer forService;// 1医院服务部门，0行政后勤部门
	@Columns(column = "deleted")
	private Integer deleted; // 1已删除，0未删除
	private Boolean isParent;
	private Boolean checked;
	private Boolean open;
	// 用户判断是否isParent
	private String tmpChildName;

	private Integer nodeDeptId;//
	private String nodeDeptName;// 如果部门是集团直属部门，nodeDeptName为集团；如果为分公司下属部门，nodeDeptName为分公司

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getSorts() {
		return sorts;
	}

	public void setSorts(Integer sorts) {
		this.sorts = sorts;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getTmpChildName() {
		return tmpChildName;
	}

	public void setTmpChildName(String tmpChildName) {
		this.tmpChildName = tmpChildName;
	}

	public Integer getNodeDeptId() {
		return nodeDeptId;
	}

	public void setNodeDeptId(Integer nodeDeptId) {
		this.nodeDeptId = nodeDeptId;
	}

	public String getNodeDeptName() {
		return nodeDeptName;
	}

	public void setNodeDeptName(String nodeDeptName) {
		this.nodeDeptName = nodeDeptName;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public Integer getForService() {
		return forService;
	}

	public void setForService(Integer forService) {
		this.forService = forService;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

}