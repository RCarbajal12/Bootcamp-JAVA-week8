package com.romina.ideas.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.romina.ideas.models.IdeasModel;
import com.romina.ideas.models.User;
//import com.romina.ideas.models.User;
import com.romina.ideas.repositories.IdeasRepo;


@Service
public class IdeasService {

	//INYECCION DE DEPENDENCIA
	private final IdeasRepo ideasRepo;
	
	//CONSTRUCTOR
	public IdeasService(IdeasRepo iRe) {
		this.ideasRepo = iRe;
	}
	
	//CREAR IDEA
	public IdeasModel crearIdea(IdeasModel idea) {
		return ideasRepo.save(idea);
	}
	
    //OBTENER TODAS LAS IDEAS
    public List<IdeasModel> obtenerTodasLasIdeas() {
        return (List<IdeasModel>) ideasRepo.findAll();
    }
	
	//EDITAR IDEA
	public IdeasModel actualizarIdea(IdeasModel idea) {
		return ideasRepo.save(idea);
	}
	
	//ELIMINAR IDEA
	public void borrarIdea(Long id) {
		ideasRepo.deleteById(id);
	}
	
	//LIKE/DISLIKE A LA IDEA
	public void likeDislikeIdea(IdeasModel ideas, User usuario, boolean like) {
		if(like) {
			ideas.getLikes().add(usuario);
		}else {
			ideas.getLikes().remove(usuario);
		}
		ideasRepo.save(ideas);
	}
	
    // OBTENER TODAS LAS PERSONAS QUE LE DIERON LIKE A UNA IDEA
    public List<User> obtenerPersonasQueDieronLike(Long ideaId) {
        IdeasModel idea = ideasRepo.findById(ideaId).orElse(null);
        if (idea == null) {
            // Manejar el caso cuando la idea no existe
            return Collections.emptyList();
        }
        return new ArrayList<>(idea.getLikes());
    }
	
	//OBTENER IDEAS ORDENADAS POR CANTIDAD DE LIKES DESCENDENTE
	public List<IdeasModel> obtenerIdeasOrdenadasPorLikes() {
	    return ideasRepo.findIdeasOrderByLikesDesc();
	}
	
	//OBTENER IDEAS ORDENADAS POR CANTIDAD DE LIKES ASCENDENTE
	public List<IdeasModel> obtenerIdeasOrdenadasPorLikesAsc(){
		return ideasRepo.findIdeasOrderByLikesAsc();
	}
	

	public IdeasModel unaIdea(Long id) {
		return ideasRepo.findById(id).orElse(null);
	}
	
}
