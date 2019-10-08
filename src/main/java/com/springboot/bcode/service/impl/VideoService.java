package com.springboot.bcode.service.impl;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.bcode.dao.IVideoDao;
import com.springboot.bcode.domain.video.Video;
import com.springboot.bcode.service.IVideoService;
import com.springboot.common.exception.SystemException;
import com.springboot.common.utils.DateUtils;
import com.springboot.common.utils.HttpUtils;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.web.mvc.JqGridPage;

@Service
public class VideoService implements IVideoService {

	@Value("${store.tiktok.path}")
	private String basePath;

	@Autowired
	private IVideoDao videoDao;

	@Override
	public JqGridPage<Video> queryPage(Video video) {
		return videoDao.selectPage(video);
	}

	@Override
	public void upload(MultipartFile file) {
		if (file == null) {
			throw new SystemException("文件不能为空");
		}
		// 获取文件名
		String fileName = file.getOriginalFilename();
		fileName = DateUtils.getDate() + "_" + fileName;
		String path = basePath + "/" + fileName;

		// 创建文件路径
		File dest = new File(path);
		// 判断文件是否已经存在
		if (dest.exists()) {
			throw new SystemException("文件已经存在");
		}
		// 判断文件父目录是否存在
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdir();
		}

		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdir();
		}
		try {
			FileUtils.writeByteArrayToFile(dest, file.getBytes());

			Video video = new Video();
			video.setName(fileName);
			video.setPath(path);
			video.setCreatetime(new Date());
			videoDao.insert(video);
		} catch (Exception e) {
			throw new SystemException("文件上传失败");
		}

	}

	@Override
	public void view(String path) {
		if (StringUtils.isBlank(path)) {
			return;
		}
		try {
			String url = basePath + "/" + path;
			File file = new File(url);
			byte[] bytes = FileUtils.readFileToByteArray(file);
			HttpServletResponse response = HttpUtils.getResponse();
			OutputStream out = response.getOutputStream();
			out.write(bytes);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(int id) {
		Video video = new Video();
		video.setId(id);
		Video info = videoDao.select(video);
		if(info==null){
			throw new SystemException("删除失败");
		}
		FileUtils.deleteQuietly(new File(info.getPath()));
		int ret = videoDao.delete(video);
		if (ret <= 0) {
			throw new SystemException("删除失败");
		}
		return true;
	}

}
