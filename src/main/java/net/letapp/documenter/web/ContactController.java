package net.letapp.documenter.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.letapp.documenter.domaine.ContactVo;
import net.letapp.documenter.services.MailingService;

@RestController
@RequestMapping(path = "/contact")
public class ContactController {
	@Autowired
	private MailingService mailingService;
	@Autowired
	private Environment environement;
	
	@PostMapping
	public void addDocument(@RequestBody ContactVo vo){
	try {
		this.mailingService.sendEmail(vo.getBody(), "Message from "+vo.getName(), this.environement.getProperty("contact_mail"), vo.getEmail());
	} catch (Exception e) {
		throw new RuntimeException("contact_could_not_be_sent");
	}
	
	}
}
