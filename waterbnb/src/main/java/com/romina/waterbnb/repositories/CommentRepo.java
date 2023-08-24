package com.romina.waterbnb.repositories;

import org.springframework.data.repository.CrudRepository;

import com.romina.waterbnb.models.ComentarioModel;

public interface CommentRepo extends CrudRepository<ComentarioModel, Long> {

}
