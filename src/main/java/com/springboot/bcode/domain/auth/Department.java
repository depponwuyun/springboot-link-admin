package com.springboot.bcode.domain.auth;

import java.util.List;

import com.springboot.core.jdbc.annotation.Columns;
import com.springboot.core.jdbc.annotation.Tables;

@Tables(table = "t_web_dept")
public class Department implements Comparable<Department> {

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

	private String parentName;

	// 子
	private List<Department> childrens;
	private Integer nodeDeptId;//
	private String nodeDeptName;// 如果部门是集团直属部门，nodeDeptName为集团；如果为分公司下属部门，nodeDeptName为分公司

	public boolean isRoot() {
		if (this.parentId == 0) {
			return true;
		}
		return false;
	}

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

	public Integer getNodeDeptId() {
		return nodeDeptId;
	}

	public void setNodeDeptId(Integer nodeDeptId) {
		this.nodeDeptId = nodeDeptId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
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

	public List<Department> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Department> childrens) {
		this.childrens = childrens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((levels == null) ? 0 : levels.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (levels == null) {
			if (other.levels != null)
				return false;
		} else if (!levels.equals(other.levels))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		return true;
	}

	@Override
	public int compareTo(Department o) {
		if (this.getSorts() != null && o.getSorts() != null) {
			return this.getSorts().compareTo(o.getSorts());
		}
		return -1;
	}

}