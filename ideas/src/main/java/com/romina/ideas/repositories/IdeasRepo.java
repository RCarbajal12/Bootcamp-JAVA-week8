package com.romina.ideas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.romina.ideas.models.IdeasModel;

public interface IdeasRepo extends CrudRepository<IdeasModel, Long>{
	
	//CONSULTA PARA OBTENER IDEAS ORDENADAS POR LA CANTIDAD DE LIKES DE FORMA DESCENDENTE
    @Query(value = "SELECT i.* FROM ideas i LEFT JOIN likes l ON i.id = l.ideas_id GROUP BY i.id, i.idea ORDER BY COUNT(l.user_id) DESC", nativeQuery = true)
    List<IdeasModel> findIdeasOrderByLikesDesc();
    
    //CONSULTA PARA OBTENES IDEAS ORDENADAS POR LA CANTIDAD DE LIKES DE FORMA ASCENDENTE
    @Query(value = "SELECT i.* FROM ideas i LEFT JOIN likes l ON i.id = l.ideas_id GROUP BY i.id, i.idea ORDER BY COUNT(l.user_id) ASC", nativeQuery = true)
    List<IdeasModel> findIdeasOrderByLikesAsc();


}
