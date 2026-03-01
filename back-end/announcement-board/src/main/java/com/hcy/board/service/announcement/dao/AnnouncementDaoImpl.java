package com.hcy.board.service.announcement.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hcy.board.service.announcement.entity.Announcement;
import com.hcy.board.service.util.DebugTrace;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class AnnouncementDaoImpl implements AnnouncementDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnnouncementDaoImpl.class);
	private static final DebugTrace TRACE = new DebugTrace(LOGGER, LOGGER.isDebugEnabled());

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Announcement> findPage(int page, int size) {
		LOGGER.debug("findPage: page={}, size={}", page, size);
		return em.createQuery("SELECT a FROM Announcement a ORDER BY a.createdAt DESC", Announcement.class)
				.setFirstResult((page - 1) * size).setMaxResults(size).getResultList();
	}

	@Override
	public long countAll() {
		long count = em.createQuery("SELECT COUNT(a) FROM Announcement a", Long.class).getSingleResult();
		LOGGER.debug("countAll: count={}", count);
		return count;
	}

	@Override
	public Announcement findById(Long id) {
		LOGGER.debug("findById: id={}", id);
		return em.find(Announcement.class, id);
	}

	@Override
	public void save(Announcement announcement) {
		LOGGER.info("save announcement: title={}", announcement.getTitle());
		em.persist(announcement);
	}

	@Override
	public void update(Announcement announcement) {
		LOGGER.info("update announcement: id={}", announcement.getId());
		em.merge(announcement);
	}

	@Override
	public void delete(Long id) {
		LOGGER.info("delete announcement: id={}", id);
		Announcement announcement = em.find(Announcement.class, id);
		if (announcement != null) {
			em.remove(announcement);
		}
	}
}
