package com.romina.waterbnb.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.romina.waterbnb.models.ComentarioModel;
import com.romina.waterbnb.models.User;
import com.romina.waterbnb.models.WaterModel;
import com.romina.waterbnb.services.CommentService;
import com.romina.waterbnb.services.UserService;
import com.romina.waterbnb.services.WaterService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PoolController {

	private final UserService userService;
	private final WaterService waterService;
	private final CommentService commentService;

	public PoolController(UserService uServ, WaterService wServ, CommentService cServ) {
		this.userService = uServ;
		this.waterService = wServ;
		this.commentService = cServ;
	}

	@GetMapping("/")
	public String raiz() {
		return "index.jsp";
	}

	@GetMapping("/search")
	public String index(HttpSession sesion, Model viewModel) {
		Long userLog = (Long) sesion.getAttribute("userID");
		if (userLog == null) {
			return "redirect:/";
		}
		User usuario = userService.encontrarUserPorId(userLog);
		viewModel.addAttribute("usuario", usuario);
		return "index.jsp";
	}

	@PostMapping("/search")
	public String search(@RequestParam(value = "location") String location, Model viewModel, HttpSession sesion,
			RedirectAttributes resultado) {
		Long userLog = (Long) sesion.getAttribute("userID");
		List<WaterModel> eventosWater = waterService.encontrarPorLocacion(location);
		if (userLog == null) {
			if (location.equals("")) {
				
				viewModel.addAttribute("pools", eventosWater);
				viewModel.addAttribute("usuario", userLog);
				return "searchForm.jsp";
			}
			return "redirect:/guest/signin";
		}
		viewModel.addAttribute("usuario", userLog);
		viewModel.addAttribute("pools", eventosWater);

		return "searchForm.jsp";
	}

	@GetMapping("/host/dashboard")
	public String index(@ModelAttribute("newpool") WaterModel pool, HttpSession sesion, Model viewModel) {
		Long userLog = (Long) sesion.getAttribute("userID");
		if (userLog == null) {
			return "redirect:/guest/signin";
		}
		User usuario = userService.encontrarUserPorId(userLog);
		if (usuario.getRol().equals(2)) {
			return "redirect:/search";
		}
		viewModel.addAttribute("usuario", usuario);
		return "/host/dashboard.jsp";
	}

	@PostMapping("/new/pool")
	public String nuevaPool(@Valid @ModelAttribute("newpool") WaterModel pool, BindingResult resultado) {
		if (resultado.hasErrors()) {
			return "/host/dashboard.jsp";
		}
		waterService.crearPool(pool);
		return "redirect:/host/dashboard";

	}
	
	@GetMapping("/pools/{idPool}")
	public String encontrarPool(@PathVariable("idPool")Long idPool,
			Model viewModel, HttpSession sesion) {
		Long userLog = (Long) sesion.getAttribute("userID");
		if (userLog == null) {
			return "redirect:/guest/signin";
		}
		User usuario = userService.encontrarUserPorId(userLog);
		WaterModel unaPool = waterService.encontrarPollPorId(idPool);
		viewModel.addAttribute("usuario", usuario);
		viewModel.addAttribute("pool", unaPool);
		
		return "showPool.jsp";
	}
	
	// RUTAS PARA AGREGAR COMENTARIO
	@GetMapping("/pools/{idPool}/review")
	public String formComentario(@PathVariable("idPool")Long idPool,
			Model viewModel, HttpSession sesion, @ModelAttribute("newcomment") ComentarioModel newcomment ) {
		Long userLog = (Long) sesion.getAttribute("userID");
		if (userLog == null) {
			return "redirect:/guest/signin";
		}
		User usuario = userService.encontrarUserPorId(userLog);
		WaterModel unaPool = waterService.encontrarPollPorId(idPool);
		viewModel.addAttribute("usuario", usuario);
		viewModel.addAttribute("pool", unaPool);
		
		return "newReview.jsp";
	}
	
	@PostMapping("/pools/{idPool}/review")
	public String nuevoComment(@Valid @ModelAttribute("newcomment") ComentarioModel newcomment, 
			BindingResult resultado, HttpSession sesion, Model viewModel, @PathVariable("idPool") Long idPool) {
		Long userLog = (Long) sesion.getAttribute("userID");
		User usuario = userService.encontrarUserPorId(userLog);
		WaterModel unaPool = waterService.encontrarPollPorId(idPool);
		if (userLog == null) {
			return "redirect:/guest/signin";
		}
		if(resultado.hasErrors()) {
			viewModel.addAttribute("usuario", usuario);
			viewModel.addAttribute("pool", unaPool);
			return "newReview.jsp";
		}
		
		commentService.agregarComentario(newcomment);
		unaPool.setRating(waterService.obtenerPromedio(idPool));
		waterService.actualizarPool(unaPool);
		
		
		return "redirect:/pools/"+idPool;
	}
	
	
	// RUTAS DE EDICION POOL
	@GetMapping("/host/pool/{idPool}")
	public String showEditPool(@PathVariable("idPool")Long idPool,
			Model viewModel, HttpSession sesion ) {
		Long userLog = (Long) sesion.getAttribute("userID");
		if (userLog == null) {
			return "redirect:/guest/signin";
		}
		User usuario = userService.encontrarUserPorId(userLog);
		WaterModel unaPool = waterService.encontrarPollPorId(idPool);
		viewModel.addAttribute("usuario", usuario);
		viewModel.addAttribute("pool", unaPool);
		return "/host/editPool.jsp";
	}
	
	@PutMapping("/host/pool/{id}")
	public String editarPool(@PathVariable("id")Long idPool,
			Model viewModel, HttpSession sesion, 
			@Valid @ModelAttribute("pool") WaterModel pool, BindingResult resultado) {
		Long userLog = (Long) sesion.getAttribute("userID");
		User usuario = userService.encontrarUserPorId(userLog);
		WaterModel unaPool = waterService.encontrarPollPorId(idPool);
		if (userLog == null) {
			return "redirect:/guest/signin";
		}
		if(resultado.hasErrors()) {
			viewModel.addAttribute("usuario", usuario);
			viewModel.addAttribute("pool", unaPool);
			return "/host/editPool.jsp";
		}
		//Primero setamos rating despues actualizamos pool
		pool.setRating(unaPool.getRating());
		waterService.actualizarPool(pool);
		return "redirect:/host/dashboard";
	}

}