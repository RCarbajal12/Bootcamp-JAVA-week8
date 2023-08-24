package com.romina.waterbnb.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.romina.waterbnb.models.WaterModel;
import com.romina.waterbnb.repositories.WaterRepo;

@Service
public class WaterService {
	
	private final WaterRepo waterRepo;
	public WaterService(WaterRepo wRe) {
		this.waterRepo = wRe;
	}
	
	public WaterModel crearPool(WaterModel pool) {
		return waterRepo.save(pool);
	}
	
	public List<WaterModel> encontrarPorLocacion(String location){
		return waterRepo.findByDireccion(location);
	}
	
	public WaterModel encontrarPollPorId(Long idPool) {
		return waterRepo.findById(idPool).orElse(null);
	}
	
	public Double obtenerPromedio(Long id) {
		return waterRepo.obtenerPromedioRatings(id).orElse(null);
	}
	
	public WaterModel actualizarPool(WaterModel pool) {
		return waterRepo.save(pool);
	}
	
//	public List<WaterModel> eventosHost(){
//		
//	}
	
	
	

}