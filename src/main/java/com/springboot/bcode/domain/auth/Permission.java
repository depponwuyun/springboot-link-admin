package com.springboot.bcode.domain.auth;

import java.util.List;

import com.springboot.core.jdbc.annotation.Columns;
import com.springboot.core.jdbc.annotation.Tables;

@Tables(table = "t_web_right")
public class Permission implements Comparable<Permission> {

	@Columns(column = "id", primaryKey = true)
	private Integer id;
	@Columns(column = "parent_id")
	private Integer parentId;
	@Columns(column = "name")
	private String name;
	@Columns(column = "types")
	private Integer types; // '0:菜单，1:页面权限',
	@Columns(column = "state")
	private Integer state;
	@Columns(column = "description")
	private String description;
	@Columns(column = "css")
	private String css;
	@Columns(column = "url")
	private String url;
	@Columns(column = "levels")
	private Integer levels;
	// 菜单顺序
	@Columns(column = "sorts")
	private Integer sorts;
	@Columns(column = "dataScope")
	private Integer dataScope; // 数据权限，1设置，0不设置
	// 子权限集合
	private List<Permission> childrens;

	public boolean isMenu() {
		if (this.types == 0) {
			return true;
		}
		return false;
	}

	public boolean isRoot() {
		if (this.parentId == 0) {
			return true;
		}
		return false;
	}

	public boolean isPermission() {
		if (this.types == 1) {
			return true;
		}
		return false;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Permission> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Permission> childrens) {
		this.childrens = childrens;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getTypes() {
		return types;
	}

	public void setTypes(Integer types) {
		this.types = types;
	}

	public Integer getLevels() {
		return levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	public Integer getSorts() {
		return sorts;
	}

	public void setSorts(Integer sorts) {
		this.sorts = sorts;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((types == null) ? 0 : types.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Permission other = (Permission) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (types == null) {
			if (other.types != null)
				return false;
		} else if (!types.equals(other.types))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	public Integer getDataScope() {
		return dataScope;
	}

	public void setDataScope(Integer dataScope) {
		this.dataScope = dataScope;
	}

	@Override
	public int compareTo(Permission o) {
		if (this.getSorts() != null && o.getSorts() != null) {
			return this.getSorts().compareTo(o.getSorts());
		}
		return -1;
	}
}
