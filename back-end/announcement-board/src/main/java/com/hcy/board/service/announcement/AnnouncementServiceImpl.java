package com.hcy.board.service.announcement;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hcy.board.service.announcement.dao.AnnouncementDao;
import com.hcy.board.service.announcement.dto.AnnouncementDto;
import com.hcy.board.service.announcement.entity.Announcement;
import com.hcy.board.service.exception.BusinessException;
import com.hcy.board.service.util.DebugTrace;
import com.hcy.board.service.util.PageInfo;

@Service
@Transactional(readOnly = true)
public class AnnouncementServiceImpl implements AnnouncementService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnnouncementServiceImpl.class);
	private static final DebugTrace TRACE = new DebugTrace(LOGGER, LOGGER.isDebugEnabled());

    @Autowired
    private AnnouncementDao announcementDao;

    @Override
    public PageInfo<Announcement> getPage(int page, int size) {
        LOGGER.debug("getPage: page={}, size={}", page, size);
        List<Announcement> items = announcementDao.findPage(page, size);
        long total = announcementDao.countAll();
        LOGGER.debug("getPage result: total={}", total);
        return new PageInfo<>(items, page, size, total);
    }

    @Override
    public Announcement findById(Long id) {
        LOGGER.debug("findById: id={}", id);
        Announcement announcement = announcementDao.findById(id);
        if (announcement == null) {
            throw new BusinessException("公告不存在: id=" + id);
        }
        return announcement;
    }

    @Override
    @Transactional
    public void create(AnnouncementDto dto, MultipartFile file) {
        LOGGER.info("create announcement: title={}, publisher={}", dto.getTitle(), dto.getPublisher());
        Announcement announcement = new Announcement();
        announcement.setTitle(dto.getTitle());
        announcement.setPublisher(dto.getPublisher());
        announcement.setPublishDate(LocalDate.parse(dto.getPublishDate()));
        announcement.setExpiryDate(LocalDate.parse(dto.getExpiryDate()));
        announcement.setContent(dto.getContent());

        // 附件處理（待補）

        announcementDao.save(announcement);
        LOGGER.info("create announcement success: id={}", announcement.getId());
    }

    @Override
    @Transactional
    public void update(AnnouncementDto dto, MultipartFile file) {
        LOGGER.info("update announcement: id={}", dto.getId());
        Announcement announcement = announcementDao.findById(dto.getId());
        if (announcement == null) {
            throw new BusinessException("公告不存在: id=" + dto.getId());
        }
        announcement.setTitle(dto.getTitle());
        announcement.setPublisher(dto.getPublisher());
        announcement.setPublishDate(LocalDate.parse(dto.getPublishDate()));
        announcement.setExpiryDate(LocalDate.parse(dto.getExpiryDate()));
        announcement.setContent(dto.getContent());

        // 附件處理（待補）

        announcementDao.update(announcement);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        LOGGER.info("delete announcement: id={}", id);
        announcementDao.delete(id);
    }

}
