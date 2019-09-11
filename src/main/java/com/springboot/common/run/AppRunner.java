package com.springboot.common.run;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.springboot.bcode.dao.IDepartmentDao;
import com.springboot.bcode.domain.auth.Department;
import com.springboot.common.LocalCache;
import com.springboot.common.constant.Constant;

/**
 * 服务启动后执行的代码
 * <p>
 * 用来初始化一些全局数据
 * </p>
 * 
 * @ClassName: AppRunner
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author link
 * @date 2019年1月11日 下午2:02:30
 *
 */
@Component
public class AppRunner implements ApplicationRunner {
	@Autowired
	private IDepartmentDao departmentDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		loadAllDept();

	}

	/**
	 * 将数据库中所有部门加载到内存中
	 *
	 * @param 设定文件
	 * @return void 返回类型
	 *
	 */
	private void loadAllDept() {
		List<Department> deptList = departmentDao.selectAll();
		LocalCache.put(Constant.caceh_all_dept_key, deptList);
	}


}
