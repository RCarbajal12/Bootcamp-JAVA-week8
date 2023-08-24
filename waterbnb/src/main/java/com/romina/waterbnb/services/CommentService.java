package com.romina.waterbnb.services;

import org.springframework.stereotype.Service;

import com.romina.waterbnb.models.ComentarioModel;
import com.romina.waterbnb.repositories.CommentRepo;

@Service
public class CommentService {
	
	private final CommentRepo commentRepo;
	public CommentService(CommentRepo cRe) {
		this.commentRepo = cRe;
	}
	
	public ComentarioModel agregarComentario(ComentarioModel comentario) {
		return commentRepo.save(comentario);
	}

}