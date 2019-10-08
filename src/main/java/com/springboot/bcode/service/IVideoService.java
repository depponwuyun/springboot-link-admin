package com.springboot.bcode.service;


import org.springframework.web.multipart.MultipartFile;

import com.springboot.bcode.domain.video.Video;
import com.springboot.core.web.mvc.JqGridPage;

public interface IVideoService {

	JqGridPage<Video> queryPage(Video video);

    void upload(MultipartFile file);
    
    void view(String path);

	boolean delete(int id);


}
