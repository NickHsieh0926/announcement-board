package com.hcy.board.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hcy.board.service.announcement.AnnouncementService;
import com.hcy.board.service.announcement.dto.AnnouncementDto;
import com.hcy.board.service.announcement.entity.Announcement;
import com.hcy.board.service.auth.model.LoginUser;
import com.hcy.board.service.util.DebugTrace;
import com.hcy.board.service.util.PageInfo;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnnouncementController.class);
	private static final DebugTrace TRACE = new DebugTrace(LOGGER, LOGGER.isDebugEnabled());

	@Autowired
	private AnnouncementService announcementService;

	@GetMapping("/list")
	public String list(@RequestParam(defaultValue = "1") int page, Model model) {
		LOGGER.debug("list: page={}", page);
		PageInfo<Announcement> pageInfo = announcementService.getPage(page, 10);
		model.addAttribute("pageInfo", pageInfo);
		return "announcement/list";
	}

	@GetMapping("/add")
	public String addForm(Model model) {
		LOGGER.debug("addForm");
		model.addAttribute("dto", new AnnouncementDto());
		return "announcement/form";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute AnnouncementDto dto, @RequestParam String tabToken, @RequestParam(required = false) MultipartFile file,
			HttpServletRequest request) {
		LoginUser currentUser = (LoginUser) request.getAttribute("currentUser");
		LOGGER.info("save: user={}, title={}", currentUser.getDisplayName(), dto.getTitle());
		dto.setPublisher(currentUser.getDisplayName());
		announcementService.create(dto, file);
		return "redirect:/announcement/list?tabToken=" + tabToken;
	}

	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable Long id, Model model) {
		LOGGER.debug("editForm: id={}", id);
		Announcement announcement = announcementService.findById(id);
		AnnouncementDto dto = new AnnouncementDto();
		dto.setId(announcement.getId());
		dto.setTitle(announcement.getTitle());
		dto.setPublisher(announcement.getPublisher());
		dto.setPublishDate(announcement.getPublishDate().toString());
		dto.setExpiryDate(announcement.getExpiryDate().toString());
		dto.setContent(announcement.getContent());
		model.addAttribute("dto", dto);
		return "announcement/form";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute AnnouncementDto dto, @RequestParam String tabToken, @RequestParam(required = false) MultipartFile file, HttpServletRequest request) {
		LoginUser currentUser = (LoginUser) request.getAttribute("currentUser");
		LOGGER.info("update: user={}, title={}", currentUser.getDisplayName(), dto.getTitle());
		dto.setPublisher(currentUser.getDisplayName());
		announcementService.update(dto, file);
		return "redirect:/announcement/list?tabToken=" + tabToken;
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Long id, @RequestParam String tabToken) {
		LOGGER.info("delete: id={}", id);
		announcementService.delete(id);
		return "redirect:/announcement/list?tabToken=" + tabToken;
	}

}
