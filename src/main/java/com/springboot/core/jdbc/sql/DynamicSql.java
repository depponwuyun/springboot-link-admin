package com.springboot.core.jdbc.sql;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.link.tool.lang.StringUtils;
import com.springboot.core.jdbc.JdbcException;
import com.springboot.core.jdbc.annotation.Columns;
import com.springboot.core.jdbc.annotation.Tables;

public class DynamicSql {

	public static <T> SqlObject insertSql(T entity) {

		SqlUtils.validateTargetClass(entity);
		Class<?> classz = entity.getClass();
		Tables linkTable = (Tables) classz.getAnnotation(Tables.class);
		String tableName = linkTable.table();
		// 获取所有的字段包括private/public，但不包括继承的字段
		Field[] fields = classz.getDeclaredFields();
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		StringBuilder columns = new StringBuilder();
		StringBuilder values = new StringBuilder();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			Columns linkTableColumn = fields[i].getAnnotation(Columns.class);
			if (linkTableColumn == null) {
				continue;
			}
			String column = linkTableColumn.column();
			if (StringUtils.isBlank(column)) {
				continue;
			}
			// 数据库自增列为false,代码插入
			boolean primaryKey = linkTableColumn.primaryKey();
			boolean autoIncrement = linkTableColumn.autoIncrement();
			if (primaryKey && autoIncrement) {
				continue;
			}
			Object filedValue = null;
			try {
				filedValue = fields[i].get(entity);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			columns.append(column);
			columns.append(",");
			values.append("?");
			values.append(",");
			params.add(filedValue);
		}
		String tableColumn = columns.subSequence(0, columns.length() - 1)
				.toString();
		String tableValues = values.subSequence(0, values.length() - 1)
				.toString();
		sql.append("insert into " + tableName + " ");
		sql.append(" (" + tableColumn + ") ");
		sql.append(" values(" + tableValues + ")");
		SqlObject so = new SqlObject();
		so.sql = sql.toString();
		so.params = params;
		return so;
	}

	public static <T> SqlObject deleteSql(T entity) {

		SqlUtils.validateTargetClass(entity);
		Class<?> classz = entity.getClass();
		Tables linkTable = (Tables) classz.getAnnotation(Tables.class);
		String tableName = linkTable.table();
		// 获取所有的字段包括private/public，但不包括继承的字段
		Field[] fields = classz.getDeclaredFields();
		StringBuilder sql = new StringBuilder();

		StringBuilder whereColumn = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		for (int i = 0; i < fields.length; i++) {

			fields[i].setAccessible(true);
			Columns linkTableColumn = fields[i].getAnnotation(Columns.class);
			if (linkTableColumn == null) {
				continue;
			}
			String column = linkTableColumn.column();
			if (StringUtils.isBlank(column)) {
				continue;
			}
			Object filedValue = null;
			try {
				filedValue = fields[i].get(entity);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (filedValue == null) {
				continue;
			}
			// Class<?> type = fields[i].getType();
			// if (!SqlUtils.typeIsNotNullValue(type, filedValue)) {
			// continue;
			// }
			whereColumn.append(" and "+column + "=? ");
			params.add(filedValue);
		}
		if (params == null || params.size() == 0) {
			throw new JdbcException("delete params不能为空");
		}

		String tableWhere = whereColumn
				.subSequence(0, whereColumn.length() - 1).toString();
		
		sql.append("delete from");
		sql.append(" ");
		sql.append(tableName);
		sql.append(" ");
		sql.append("where 1=1 ");
		sql.append(" ");
		sql.append(tableWhere);

		SqlObject so = new SqlObject();
		so.sql = sql.toString();
		so.params = params;
		return so;
	}

	public static <T> SqlObject updateSql(T entity) {

		SqlUtils.validateTargetClass(entity);
		Class<?> classz = entity.getClass();
		Tables linkTable = (Tables) classz.getAnnotation(Tables.class);
		String tableName = linkTable.table();
		// 获取所有的字段包括private/public，但不包括继承的字段
		Field[] fields = classz.getDeclaredFields();
		StringBuilder sql = new StringBuilder();
		List<Object> sqlParams = new ArrayList<Object>();

		StringBuilder columns = new StringBuilder();

		StringBuilder whereColumn = new StringBuilder();
		StringBuilder whereValue = new StringBuilder();
		for (int i = 0; i < fields.length; i++) {

			fields[i].setAccessible(true);
			Columns linkTableColumn = fields[i].getAnnotation(Columns.class);
			if (linkTableColumn == null) {
				continue;
			}
			String column = linkTableColumn.column();
			if (StringUtils.isBlank(column)) {
				continue;
			}
			Object filedValue = null;
			try {
				filedValue = fields[i].get(entity);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			// Class<?> type = fields[i].getType();
			// if (!SqlUtils.typeIsNotNullValue(type, filedValue)) {
			// continue;
			// }

			boolean primaryKey = linkTableColumn.primaryKey();
			if (primaryKey) {
				whereColumn.append(column);
				whereValue.append(filedValue);
				continue;
			}

			columns.append(column + "=?");
			columns.append(",");
			sqlParams.add(filedValue);

		}

		if (StringUtils.isBlank(whereColumn) && StringUtils.isBlank(whereValue)) {
			throw new JdbcException("update primaryKey 不能为空");
		}

		String tableColumn = columns.subSequence(0, columns.length() - 1)
				.toString();
		sql.append("update " + tableName + " set " + tableColumn + "");
		sql.append(" ");
		sql.append("where");
		sql.append(" ");
		sql.append(whereColumn);
		sql.append("=?");

		sqlParams.add(whereValue.toString());

		SqlObject sf = new SqlObject();
		sf.sql = sql.toString();
		sf.params = sqlParams;
		return sf;
	}

	public static <T> SqlObject findSql(T entity) {
		SqlUtils.validateTargetClass(entity);
		Class<?> classz = entity.getClass();
		Tables linkTable = (Tables) classz.getAnnotation(Tables.class);
		String tableName = linkTable.table();
		// 获取所有的字段包括private/public，但不包括继承的字段
		Field[] fields = classz.getDeclaredFields();
		StringBuilder columns = new StringBuilder();
		StringBuilder whereColumn = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		for (int i = 0; i < fields.length; i++) {

			fields[i].setAccessible(true);
			Columns linkTableColumn = fields[i].getAnnotation(Columns.class);
			if (linkTableColumn == null) {
				continue;
			}
			String column = linkTableColumn.column();
			if (StringUtils.isBlank(column)) {
				continue;
			}
			Object filedValue = null;
			try {
				filedValue = fields[i].get(entity);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			Class<?> type = fields[i].getType();
			if (SqlUtils.typeIsNotNullValue(type, filedValue)) {
				whereColumn.append("and");
				whereColumn.append(" ");
				whereColumn.append(column);
				whereColumn.append("=");
				whereColumn.append("?");
				whereColumn.append(" ");
				params.add(filedValue);
			}
			columns.append(column + " " + "as" + " " + fields[i].getName());
			columns.append(",");

		}
		String tableColumn = columns.subSequence(0, columns.length() - 1)
				.toString();

		StringBuilder sql = new StringBuilder();
		sql.append("select " + tableColumn + " from " + tableName);
		sql.append(" ");
		sql.append("where 1=1");
		sql.append(" ");
		sql.append(whereColumn);

		SqlObject sf = new SqlObject();
		sf.sql = sql.toString();
		sf.params = params;
		return sf;
	}

	public static <T> SqlObject findByIdSql(Object id, Class<T> classz) {
		T entity = null;
		try {
			entity = classz.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		SqlUtils.validateTargetClass(entity);
		Tables linkTable = (Tables) classz.getAnnotation(Tables.class);
		String tableName = linkTable.table();
		// 获取所有的字段包括private/public，但不包括继承的字段
		Field[] fields = classz.getDeclaredFields();
		List<Object> sqlParams = new ArrayList<Object>();

		StringBuilder columns = new StringBuilder();

		StringBuilder whereColumn = new StringBuilder();
		StringBuilder WhereValue = new StringBuilder();
		WhereValue.append(id);
		for (int i = 0; i < fields.length; i++) {

			fields[i].setAccessible(true);
			Columns linkTableColumn = fields[i].getAnnotation(Columns.class);
			if (linkTableColumn == null) {
				continue;
			}
			String fieldName = fields[i].getName();
			String column = linkTableColumn.column();
			if (StringUtils.isBlank(column)) {
				continue;
			}

			boolean primaryKey = linkTableColumn.primaryKey();
			if (primaryKey) {
				whereColumn.append(column);
			}
			columns.append(column);
			columns.append(" ");
			columns.append("as");
			columns.append(" ");
			columns.append(fieldName);
			columns.append(",");

		}

		String tableColumn = columns.subSequence(0, columns.length() - 1)
				.toString();

		sqlParams.add(WhereValue.toString());

		StringBuilder sql = new StringBuilder();
		sql.append("select " + tableColumn + " from " + tableName);
		sql.append(" ");
		sql.append("where");
		sql.append(" ");
		sql.append(whereColumn);
		sql.append("=?");
		SqlObject sf = new SqlObject();
		sf.sql = sql.toString();
		sf.params = sqlParams;
		return sf;
	}

}
