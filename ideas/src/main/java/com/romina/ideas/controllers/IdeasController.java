package com.romina.ideas.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.romina.ideas.models.IdeasModel;
import com.romina.ideas.models.User;
import com.romina.ideas.services.IdeasService;
import com.romina.ideas.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class IdeasController {

	// INYECCION DE DEPENDENCIAS
	private final IdeasService ideasService;
	private final UserService userServ;

	public IdeasController(IdeasService iSer, UserService uSer) {
		this.ideasService = iSer;
		this.userServ = uSer;
	}

	// NUEVA IDEA
	@GetMapping("/ideas/new")
	public String mostrarFormularioNuevaIdea(@ModelAttribute("ideas") IdeasModel ideas, HttpSession sesion,
			Model viewModel) {

		// VALIDAR SI LA SESION DEL USUARIO ESTÁ ACTIVA
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		User usuario = userServ.encontrarUserPorId(userId);
		viewModel.addAttribute("usuario", usuario);

		return "newidea.jsp";
	}

	@PostMapping("/ideas/new")
	public String crearIdea(@Valid @ModelAttribute("ideas") IdeasModel idea, BindingResult resultado,
			HttpSession sesion, Model viewModel) {
		// VALIDAR SI LA SESION DEL USUARIO ESTÁ ACTIVA
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		if (resultado.hasErrors()) {
			User usuario = userServ.encontrarUserPorId(userId);
			viewModel.addAttribute("usuario", usuario);
			return "newidea.jsp";
		}
		ideasService.crearIdea(idea);
		return "redirect:/ideas";
	}


	// EDITAR IDEA
	@GetMapping("/ideas/{idIdea}/edit")
	public String formEdicionIdea(@PathVariable("idIdea") Long idIdea, @ModelAttribute("ideaEdit") IdeasModel idea,
			HttpSession sesion, Model viewModel) {
		// VALIDAR SI LA SESION DEL USUARIO ESTÁ ACTIVA
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		IdeasModel unaIdea = ideasService.unaIdea(idIdea);
		if (unaIdea == null) {
			return "redirect:/";
		}
		User usuario = userServ.encontrarUserPorId(userId);
		viewModel.addAttribute("usuario", usuario);
		viewModel.addAttribute("idea", unaIdea);

		return "edit.jsp";
	}

	@PutMapping("/ideas/{id}/edit")
	public String actualizarIdea(@Valid @ModelAttribute("ideaEdit") IdeasModel idea, BindingResult resultado,
			@PathVariable("id") Long idIdea, HttpSession sesion, Model viewModel) {
		// VALIDAR SI LA SESION DEL USUARIO ESTÁ ACTIVA
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		User usuario = userServ.encontrarUserPorId(userId);
		if (resultado.hasErrors()) {
			viewModel.addAttribute("usuario", usuario);

			return "edit.jsp";
		}
		IdeasModel unaIdea = ideasService.unaIdea(idIdea);
		unaIdea.setIdea(idea.getIdea());
		ideasService.actualizarIdea(unaIdea);
		return "redirect:/ideas";
	}

	// BORRAR IDEA
	@DeleteMapping("/ideas/{id}/delete")
	public String borrarIdea(@PathVariable("id") Long idIdea) {
		ideasService.borrarIdea(idIdea);
		return "redirect:/ideas";
	}

	// LIKE/DISLIKE A LA IDEA
	@GetMapping("/ideas/{idIdeas}/{idUsuario}/{opcion}")
	public String likeDislikeIdea(@PathVariable("idIdeas") Long idIdea, @PathVariable("idUsuario") Long idUsuario,
			@PathVariable("opcion") String opcion, HttpSession sesion) {
		// VALIDAR SI LA SESION DEL USUARIO ESTA ACTIVA
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		IdeasModel unaIdea = ideasService.unaIdea(idIdea);
		boolean likeDislike = (opcion.equals("like"));
		User unUsuario = userServ.encontrarUserPorId(userId);

		ideasService.likeDislikeIdea(unaIdea, unUsuario, likeDislike);

		return "redirect:/ideas";
	}

	// MOSTRAR INFORMACIÓN DE LA IDEA
	@GetMapping("/ideas/{idIdea}")
	public String mostrarIdea(Model viewModel, @PathVariable("idIdea") Long idIdea, HttpSession sesion) {
		// VALIDAR SI LA SESION DEL USUARIO ESTÁ ACTIVA
		Long userId = (Long) sesion.getAttribute("userID");
		if (userId == null) {
			return "redirect:/";
		}
		IdeasModel ideaSeleccionada = ideasService.unaIdea(idIdea);
		User unUsuario = userServ.encontrarUserPorId(userId);

		if (ideaSeleccionada == null) {
			return "error"; 
		}
		List<IdeasModel> ideasAll = ideasService.obtenerTodasLasIdeas();
		List<User> personasQueDieronLike = ideasService.obtenerPersonasQueDieronLike(idIdea);
		viewModel.addAttribute("listaIdeas", ideasAll);
		viewModel.addAttribute("ideaSeleccionada", ideasService.unaIdea(idIdea));
		viewModel.addAttribute("personasQueDieronLike", personasQueDieronLike);
		viewModel.addAttribute("loginUser", unUsuario);
		return "show.jsp";
	}
	
	//MOSTRAR IDEAS ORDENADAS CON MAS LIKES
	@GetMapping("/ideas/hight")
	public String listarIdeasHight(Model viewModel,  HttpSession sesion) {
	    List<IdeasModel> ideasOrdenadasHight = ideasService.obtenerIdeasOrdenadasPorLikes();
	    viewModel.addAttribute("ideasHight", ideasOrdenadasHight);
	    return "hightLikes.jsp"; 
	}
	//MOSTRAR IDEAS ORDENADAS CON MAS LIKES
	@GetMapping("/ideas/low")
	public String listarIdeasLow(Model viewModel,  HttpSession sesion) {
	    List<IdeasModel> ideasOrdenadasLow = ideasService.obtenerIdeasOrdenadasPorLikesAsc();
	    viewModel.addAttribute("ideasLow", ideasOrdenadasLow);
	    return "lowLikes.jsp"; 
	}


}
