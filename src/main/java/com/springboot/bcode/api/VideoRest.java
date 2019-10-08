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

@RestController
@RequestMapping(value = "/rest/video")
public class VideoRest extends BaseRest {

	@Autowired
	private IVideoService videoService;

	@RequestMapping(value = "/tiktok/list", method = RequestMethod.POST)
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

	@RequestMapping(value = "/tiktok/upload", method = RequestMethod.POST)
	public ResponseResult upload(MultipartFile file) {
		ResponseResult rep = new ResponseResult();
		try {
			videoService.upload(file);
			rep.setResult(file.getBytes());
		} catch (SystemException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		}
		return rep;
	}

	@RequestMapping(value = "/tiktok/view")
	public void video(@RequestParam("path") String path) {
		videoService.view(path);
	}

	/**
	 * 移除角色
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/tiktok/delete")
	public ResponseResult delete(@RequestParam("id") Integer id) {
		ResponseResult rep = new ResponseResult();
		try {
			videoService.delete(id);
		} catch (SystemException e) {
			rep.setCode(CODE_500);
			rep.setMsg(e.getMessage());
		} catch (Exception e) {
			rep.setCode(CODE_500);
			rep.setMsg("系统异常.请稍后再试");
		}
		return rep;
	}

}
