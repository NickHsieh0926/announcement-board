package com.hcy.board.service.announcement.dao;

import java.util.List;

import com.hcy.board.service.announcement.entity.Announcement;

public interface AnnouncementDao {

	List<Announcement> findPage(int page, int size);

	long countAll();

	Announcement findById(Long id);

	void save(Announcement announcement);

	void update(Announcement announcement);

	void delete(Long id);

}
