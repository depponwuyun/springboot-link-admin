package com.springboot.bcode.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springboot.bcode.dao.IVideoDao;
import com.springboot.bcode.domain.video.Video;
import com.springboot.common.utils.StringUtils;
import com.springboot.core.jdbc.BaseDaoImpl;
import com.springboot.core.web.mvc.JqGridPage;

@Repository
public class VideoDao extends BaseDaoImpl implements IVideoDao {
	@Override
	public JqGridPage<Video> selectPage(Video video) {
		List<Video> list = super.select(
				getSqlPageHandle().handlerPagingSQL(pageSql(video, 0),
						video.getPage(), video.getLimit()), null, Video.class);
		int count = super.jdbcTemplate.queryForObject(pageSql(video, 1), null,
				Integer.class);
		JqGridPage<Video> page = new JqGridPage<Video>(list, count,
				video.getLimit(), video.getPage());
		return page;
	}

	private String pageSql(Video role, int type) {
		StringBuilder sql = new StringBuilder();
		if (type == 0) {
			sql.append("select * from t_video");
		} else {
			sql.append("select count(*) from t_video");
		}
		sql.append(" where 1=1");

		if (StringUtils.isNotBlank(role.getName())) {
			sql.append(" and name like '%").append(
					role.getName().trim() + "%' ");
		}
		if (type == 0) {
			sql.append(" order by createtime desc");
		}
		return sql.toString();
	}

	@Override
	public int insert(Video video) {
		return super.insert(video);
	}

	@Override
	public int delete(Video video) {
		return super.delete(video);
	}

	@Override
	public Video select(Video video) {
		return super.selectOne(video);
	}

}
