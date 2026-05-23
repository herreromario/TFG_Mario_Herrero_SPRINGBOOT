package com.stockmeal.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmeal.api.dto.CapacidadProduccionDTO;
import com.stockmeal.api.dto.RecetaDTO;
import com.stockmeal.api.dto.RecetaDetalleDTO;
import com.stockmeal.api.services.RecetaService;

@RestController
@RequestMapping("/recetas")
public class RecetaController {
	@Autowired
	RecetaService recetaService;

	@GetMapping
	public List<RecetaDTO> obtenerRecetas(){
	    return recetaService.getRecetas();
	}

	@GetMapping("/{id}")
	public RecetaDetalleDTO obtenerReceta(@PathVariable Integer id){
	    return recetaService.getRecetaDetalle(id);
	}

	@GetMapping("/capacidad")
	public List<CapacidadProduccionDTO> obtenerCapacidadProduccion(){
	    return recetaService.getCapacidadProduccion();
	}
}
