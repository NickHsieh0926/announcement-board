package com.hcy.board.service.announcement.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "announcements")
public class Announcement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 200)
	private String title;

	@Column(nullable = false, length = 50)
	private String publisher;

	@Column(nullable = false)
	private LocalDate publishDate;

	@Column(nullable = false)
	private LocalDate expiryDate;

	@Lob
	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;

	private String attachmentOriginalName;
	private String attachmentStoredName;
	private String attachmentPath;
	private String attachmentSize;

	@Column(length = 100)
	private String attachmentMimeType;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	public Announcement() {
		super();
	}

	public Announcement(Long id, String title, String publisher, LocalDate publishDate, LocalDate expiryDate,
			String content, String attachmentOriginalName, String attachmentStoredName, String attachmentPath,
			String attachmentSize, String attachmentMimeType, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.title = title;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.expiryDate = expiryDate;
		this.content = content;
		this.attachmentOriginalName = attachmentOriginalName;
		this.attachmentStoredName = attachmentStoredName;
		this.attachmentPath = attachmentPath;
		this.attachmentSize = attachmentSize;
		this.attachmentMimeType = attachmentMimeType;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAttachmentOriginalName() {
		return attachmentOriginalName;
	}

	public void setAttachmentOriginalName(String attachmentOriginalName) {
		this.attachmentOriginalName = attachmentOriginalName;
	}

	public String getAttachmentStoredName() {
		return attachmentStoredName;
	}

	public void setAttachmentStoredName(String attachmentStoredName) {
		this.attachmentStoredName = attachmentStoredName;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public String getAttachmentSize() {
		return attachmentSize;
	}

	public void setAttachmentSize(String attachmentSize) {
		this.attachmentSize = attachmentSize;
	}

	public String getAttachmentMimeType() {
		return attachmentMimeType;
	}

	public void setAttachmentMimeType(String attachmentMimeType) {
		this.attachmentMimeType = attachmentMimeType;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
