package com.springboot.bcode.dao;


import com.springboot.bcode.domain.video.Video;
import com.springboot.core.web.mvc.JqGridPage;

public interface IVideoDao {
	JqGridPage<Video> selectPage(Video video);
	
	Video select(Video video);

	int insert(Video video);

	int delete(Video video);

	

}
