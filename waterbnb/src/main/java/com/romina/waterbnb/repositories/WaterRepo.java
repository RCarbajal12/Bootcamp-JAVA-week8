package com.romina.waterbnb.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.romina.waterbnb.models.WaterModel;

public interface WaterRepo extends CrudRepository<WaterModel, Long>{
	
	List<WaterModel> findByDireccion(String location);
	
	@Query(value="SELECT AVG(rating) FROM comentarios \r\n"
			+ "WHERE pool_id= :idPool", nativeQuery=true)
	Optional<Double> obtenerPromedioRatings(Long idPool);

}