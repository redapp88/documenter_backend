package net.letapp.documenter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import net.letapp.documenter.services.UserService;

@RestController
public class MainController {
	@Autowired
	UserService userService;

}
