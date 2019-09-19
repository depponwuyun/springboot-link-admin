package com.springboot.common.run;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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

	@Override
	public void run(ApplicationArguments args) throws Exception {

	}

}
