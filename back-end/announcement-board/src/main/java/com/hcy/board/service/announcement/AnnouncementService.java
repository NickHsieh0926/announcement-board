package com.hcy.board.service.announcement;

import org.springframework.web.multipart.MultipartFile;

import com.hcy.board.service.announcement.dto.AnnouncementDto;
import com.hcy.board.service.announcement.entity.Announcement;
import com.hcy.board.service.util.PageInfo;

public interface AnnouncementService {
	
	PageInfo<Announcement> getPage(int page, int size);

	Announcement findById(Long id);

	void create(AnnouncementDto dto, MultipartFile file);

	void update(AnnouncementDto dto, MultipartFile file);

	void delete(Long id);
	
}
