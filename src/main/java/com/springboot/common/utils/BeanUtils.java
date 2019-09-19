package com.springboot.common.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * - TODO(描述类的职责)
 * 
 * @version V1.0
 *          <p style="display:none">
 *          modifyRecord
 *          </p>
 *          <p style="display:none">
 *          version:V1.0,author:252956,date:2017年8月30日上午10:13:14,content:TODO
 *          </p>
 * @author
 * @date 2017年8月30日上午10:13:14
 * @since
 *
 */
public class BeanUtils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7296292510912148429L;

	/**
	 * 基本类型、包装类型、String类型、date
	 */
	private static final String[] TYPES = { "java.lang.Integer",
			"java.lang.Double", "java.lang.Float", "java.lang.Long",
			"java.lang.Short", "java.lang.Byte", "java.lang.Boolean",
			"java.lang.Character", "java.lang.String", "java.math.BigDecimal",
			"java.util.Date", "java.sql.Date", "int", "double", "long",
			"short", "byte", "boolean", "char", "float" };

	/**
	 * 
	 * 
	 * <p>
	 * TODO(方法详细描述说明、方法参数的具体涵义)isNull
	 * </p>
	 * 
	 * @author 252956
	 * @date 2017年8月30日上午10:13:48
	 * @param object
	 * @return
	 *
	 */
	public static boolean isNull(Object object) {
		return object == null ? true : false;
	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO(方法详细描述说明、方法参数的具体涵义)isEmpty
	 * </p>
	 * 
	 * @author 252956
	 * @date 2017年8月30日上午10:13:50
	 * @param list
	 * @return
	 *
	 */
	public static boolean isEmpty(List<?> list) {
		if (isNull(list) || list.isEmpty())
			return true;

		return false;
	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO将sourc对象属性转换为target【属性字段支持普通java类型字段、 实体对象 、list集合】
	 * </p>
	 * 
	 * @param source
	 * @param target
	 *
	 */
	public static void copyObject(Object target, Object source) {
		if (source == null || target == null) {
			return;
		}
		Class<?> targetClass = target.getClass();
		Class<?> sourceClass = source.getClass();
		for (Field field : targetClass.getDeclaredFields()) {
			String name = field.getName().substring(0, 1).toUpperCase()
					+ field.getName().substring(1);
			String getMethodName = "get" + name;
			String setMethodName = "set" + name;
			try {
				Method getMethod = sourceClass.getMethod(getMethodName,
						new Class[] {});
				Method setMethod = targetClass.getMethod(setMethodName,
						new Class[] { field.getType() });
				Object value = getMethod.invoke(source, new Object[] {});
				if (value != null) {
					// 判断是否是能转换的数据类型【常用数据类型】
					if (commonDataTypes(field.getType())) {
						setMethod.invoke(target, new Object[] { value });
						// list 类型
					} else if (List.class.isAssignableFrom(field.getType())) {
						List list = new ArrayList();
						for (Object valueOfLst : (ArrayList) value) {
							Type type = field.getGenericType();
							if (!(type instanceof ParameterizedType)) {
								continue;
							}
							Class tmpClass = (Class) ((ParameterizedType) type)
									.getActualTypeArguments()[0];
							Object tmp = tmpClass.newInstance();
							copyObject(valueOfLst, tmp);
							list.add(tmp);
						}
						setMethod.invoke(target, new Object[] { list });
					} else {
						Object tmp = field.getType().newInstance();
						copyObject(value, tmp);
						setMethod.invoke(target, new Object[] { tmp });
					}
				}
			} catch (Exception e) {
				continue;
			}
		}
	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO(方法详细描述说明、方法参数的具体涵义)commonDataTypes
	 * </p>
	 * 
	 * @author 252956
	 * @date 2017年8月30日上午10:14:04
	 * @param fieldType
	 * @return
	 *
	 */
	private static boolean commonDataTypes(Class<?> fieldType) {
		for (int i = 0; i < TYPES.length; i++) {
			if (TYPES[i].equals(fieldType.getName())) {
				return true;
			}
		}
		return false;

	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO将fromObject对象属性值copy到targetClazz对象属性值【不支持对象里面存在集合、实例对象copy；只支持单个属性字段，
	 * 字段名一致，字段类型可以不一致】
	 * </p>
	 */
	public static <T> T copyAttributes(Class<T> targetClazz, Object fromObject) {
		T object = null;

		if (fromObject == null) {
			return object;
		}

		try {
			object = targetClazz.newInstance();

			Class<?> fromType = fromObject.getClass();

			for (Field field : getFields(targetClazz)) {
				String name = field.getName().substring(0, 1).toUpperCase()
						+ field.getName().substring(1);
				String getMethodName = "get" + name;
				String setMethodName = "set" + name;

				try {
					Method getMethod = fromType.getMethod(getMethodName,
							new Class[] {});
					Method setMethod = targetClazz.getMethod(setMethodName,
							new Class[] { field.getType() });

					Object value = getMethod
							.invoke(fromObject, new Object[] {});
					if (value != null)
						setMethod.invoke(object, new Object[] { value });
				} catch (Exception e) {
					continue;
				}
			}

		} catch (Exception e) {
			return object;
		}

		return object;
	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO(方法详细描述说明、方法参数的具体涵义)getFields
	 * </p>
	 * 
	 * @author 252956
	 * @date 2017年8月30日上午10:14:12
	 * @param targetClazz
	 * @return
	 *
	 */
	private static <T> List<Field> getFields(Class<T> targetClazz) {

		List<Field> list = new ArrayList<>();

		for (Field field : targetClazz.getDeclaredFields()) {
			list.add(field);
		}

		getSuperFields(list, targetClazz.getSuperclass());

		return list;

	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO(方法详细描述说明、方法参数的具体涵义)getSuperFields
	 * </p>
	 * 
	 * @author 252956
	 * @date 2017年8月30日上午10:14:18
	 * @param list
	 * @param targetClazz
	 *
	 */
	@SuppressWarnings("rawtypes")
	private static void getSuperFields(List<Field> list, Class targetClazz) {

		for (Field field : targetClazz.getDeclaredFields()) {
			list.add(field);
		}
		if (targetClazz.getSuperclass() != null) {
			if (!"java.lang.Object".equals(targetClazz.getSuperclass()
					.getName()) && targetClazz.getSuperclass() != null) {
				getSuperFields(list, targetClazz.getSuperclass());
			}
		}

	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO(方法详细描述说明、方法参数的具体涵义)copyListEntAttributes
	 * </p>
	 * 
	 * @author 252956
	 * @date 2017年8月30日上午10:14:24
	 * @param targetClazz
	 * @param fromList
	 * @return
	 *
	 */
	public static <T> List<T> copyListEntAttributes(Class<T> targetClazz,
			List<?> fromList) {

		List<T> list = new ArrayList<T>();

		if (isEmpty(fromList))
			return list;

		for (Object fromObject : fromList) {
			T object = copyAttributes(targetClazz, fromObject);
			list.add(object);
		}
		return list;
	}

	/**
	 * 相同list合并
	 * 
	 * @param list1
	 * @param list2
	 * @param lists
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<?> addList(List<?> list, List<?>... lists) {
		List listAll = new ArrayList();
		if (!isEmpty(list))
			listAll.addAll(list);

		if (lists == null || lists.length == 0)
			return listAll;

		for (List list_ : lists) {
			if (!isEmpty(list_))
				listAll.addAll(list_);
		}
		return listAll;
	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO将source对象属性值copy到target对象属性值【不支持对象里面存在集合、实例对象copy；只支持单个属性字段，字段名、
	 * 字段类型要一致】
	 * </p>
	 */
	public static void copyProperties(Object source, Object target,
			boolean nullValueCopy) {
		Method[] methods = source.getClass().getMethods();
		for (Method method : methods) {
			if (!method.getName().startsWith("get"))
				continue;
			try {
				Object value = method.invoke(source, new Object[0]);

				if ((value == null) && (!nullValueCopy)) {
					continue;
				}
				String setMethodName = method.getName().replaceFirst("get",
						"set");
				Method setMethod = target.getClass().getMethod(setMethodName,
						new Class[] { method.getReturnType() });
				setMethod.invoke(target, new Object[] { value });
			} catch (Exception e) {
				continue;
			}
		}
	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO将source对象属性值copy到target对象属性值【不支持对象里面存在集合、实例对象copy；只支持单个属性字段，字段名、
	 * 字段类型要一致】
	 * </p>
	 */
	public static void copyProperties(Object source, Object target) {
		copyProperties(source, target, true);
	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO(方法详细描述说明、方法参数的具体涵义)getPropertyValue
	 * </p>
	 * 
	 * @author 252956
	 * @date 2017年8月30日上午10:14:33
	 * @param beanObj
	 * @param property
	 * @return
	 *
	 */
	public static Object getPropertyValue(Object beanObj, String property) {
		try {
			int index = property.indexOf('.');
			if (index > -1) {
				Object subObj = getPropertyValue(beanObj,
						property.substring(0, index));
				return getPropertyValue(subObj, property.substring(index + 1));
			}
			String methodName = "get"
					+ Character.toUpperCase(property.charAt(0))
					+ property.substring(1);

			Method method = beanObj.getClass().getMethod(methodName,
					new Class[0]);
			Object value = method.invoke(beanObj, new Object[0]);
			return value;
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 
	 * 
	 * <p>
	 * TODO(方法详细描述说明、方法参数的具体涵义)setPropertyValue
	 * </p>
	 * 
	 * @author 252956
	 * @date 2017年8月30日上午10:14:39
	 * @param beanObj
	 * @param property
	 * @param value
	 *
	 */
	public static void setPropertyValue(Object beanObj, String property,
			Object value) {
		try {
			int index = property.lastIndexOf('.');
			if (index > -1) {
				beanObj = getPropertyValue(beanObj,
						property.substring(0, index));
				property = property.substring(index + 1);
			}
			String methodName = "set"
					+ Character.toUpperCase(property.charAt(0))
					+ property.substring(1);

			Method setMethod = null;
			Method[] methods = beanObj.getClass().getMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					setMethod = method;
					break;
				}
			}
			if (setMethod != null)
				setMethod.invoke(beanObj, new Object[] { value });
		} catch (Exception e) {
		}
	}

	// public static void main(String[] args) {
	// User user = new User();
	// user.setId(132);
	// Company company = new Company();
	// company.setName("334343");
	// user.setCompany(company);
	// user.setEmail("12345");
	// List<Role> roles = new ArrayList<Role>();
	// user.setRoles(roles);
	//
	// CProcessDefinitionEntity user2 = (CProcessDefinitionEntity)
	// copyAttributes(CProcessDefinitionEntity.class, user);
	// // System.out.println(user2.getId());
	// // System.out.println(user2.getCompany().getName());
	// // System.out.println(user2.getEmail());
	// // System.out.println(user2.getRoles());
	// }

}
