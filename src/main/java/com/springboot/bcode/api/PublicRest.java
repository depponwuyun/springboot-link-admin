package com.springboot.bcode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.bcode.domain.video.Video;
import com.springboot.bcode.service.IVideoService;
import com.springboot.common.exception.SystemException;
import com.springboot.core.web.mvc.BaseRest;
import com.springboot.core.web.mvc.ResponseResult;

/**
 * 开放不需要权限验证api
 * 
 * - TODO(描述类的职责) 
 * 
 * @version V1.0
 *          <p style="display:none">
 *          modifyRecord
 *          </p>
 *          <p style="display:none">
 *          version:V1.0,author:252956,date:2019年10月4日下午6:19:37,content:TODO
 *          </p>
 * @author 252956
 * @date 2019年10月4日下午6:19:37
 * @since
 *
 */
@RestController
@RequestMapping(value = "/public/rest")
public class PublicRest extends BaseRest {

	@Autowired
	private IVideoService videoService;

	@RequestMapping(value = "/video/tiktok/list", method = RequestMethod.POST)
	public ResponseResult list(@RequestBody Video vo) {
		ResponseResult rep = new ResponseResult();
		try {
			rep.setResult(videoService.queryPage(vo));
		} catch (SystemException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;

	}


	@RequestMapping(value = "/tiktok/view")
	public void video(@RequestParam("path") String path) {
		videoService.view(path);
	}

}
