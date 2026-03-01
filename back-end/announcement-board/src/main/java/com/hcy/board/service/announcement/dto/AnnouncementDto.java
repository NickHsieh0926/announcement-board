package com.hcy.board.service.announcement.dto;

public class AnnouncementDto {
	private Long id;
	private String title;
	private String publisher;
	private String publishDate;
	private String expiryDate;
	private String content;

	public AnnouncementDto() {
		super();
	}

	public AnnouncementDto(Long id, String title, String publisher, String publishDate, String expiryDate,
			String content) {
		super();
		this.id = id;
		this.title = title;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.expiryDate = expiryDate;
		this.content = content;
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

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
