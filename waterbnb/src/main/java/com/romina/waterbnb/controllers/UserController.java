package com.romina.waterbnb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.romina.waterbnb.models.LogReg;
import com.romina.waterbnb.models.User;
import com.romina.waterbnb.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	private final UserService userServ;
	public UserController(UserService uSer) {
		this.userServ = uSer;

	}
	
	@GetMapping("/guest/signin")
	public String raiz(Model viewModel) {
		viewModel.addAttribute("user", new User());
		viewModel.addAttribute("login", new LogReg());
		return "loginreg.jsp";
	}
	
	
	@PostMapping("/registration")
	public String registro(@Valid @ModelAttribute("user") User usuario,
			BindingResult resultado, Model viewModel ) {
		if(resultado.hasErrors()) {
			viewModel.addAttribute("login", new LogReg());
			
			return "loginreg.jsp";
		}
		User usuarioRegistrado = userServ.registroUsuario(usuario, resultado);
		viewModel.addAttribute("login", new LogReg());
		if(usuarioRegistrado != null) {
			viewModel.addAttribute("registro", "Gracias por registrarte, ahora login por favor");
		}
		return "loginreg.jsp";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("login") LogReg loginuser,
			BindingResult resultado, Model viewModel, HttpSession sesion) {
		if (resultado.hasErrors()) {
			viewModel.addAttribute("user", new User());
			return "loginreg.jsp";
		}
		
		if(userServ.authenthicateUser(
				loginuser.getEmail(), 
				loginuser.getPassword(), 
				resultado )) {
			User usuarioLog = userServ.encontrarPorEmail(loginuser.getEmail());
			sesion.setAttribute("userID",usuarioLog.getId());
			if(usuarioLog.getRol().equals(1)) {
				return "redirect:/host/dashboard";
			}
			return "redirect:/";
		}else {
			viewModel.addAttribute("errorLog", "Por favor intenta de nuevo");
			viewModel.addAttribute("user", new User());
			return "loginreg.jsp";
		}
		
	}
	
	
	
	@GetMapping("/logout")
	public String logout(HttpSession sesion) {
		sesion.setAttribute("userID", null);
		return "redirect:/";
	}

}