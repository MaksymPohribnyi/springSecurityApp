package ua.pohribnyi.springsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ua.pohribnyi.springsecurity.security.PersonDetails;
import ua.pohribnyi.springsecurity.services.AdminService;

@Controller
public class HelloController {

	private final AdminService adminService;

	@Autowired
	public HelloController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "hello";
	}

	@GetMapping("/showUserInfo")
	public String showUserInfo() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		PersonDetails personDetails = (PersonDetails) auth.getPrincipal();
		System.out.println(personDetails.getPerson());

		return "hello";
	}

	@GetMapping("/admin")
	public String adminPage() {
		return "admin";
	}

	@GetMapping("/adminStuff")
	public String adminPageStuff() {
		adminService.doAdminStuff();
		return "admin";
	}

}
