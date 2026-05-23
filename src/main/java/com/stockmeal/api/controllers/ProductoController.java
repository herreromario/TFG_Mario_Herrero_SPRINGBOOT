package com.stockmeal.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockmeal.api.dto.ProductoDTO;
import com.stockmeal.api.services.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	@Autowired
	ProductoService productoService;
	
	@GetMapping
	public List<ProductoDTO> obtenerProductos(){
		return productoService.obtenerProductos();
	}
	
	@GetMapping("/platos")
	public List<ProductoDTO> obtenerPlatos(){
		return productoService.getPlatos();
	}
	
	@GetMapping("/ingredientes")
	public List<ProductoDTO> obtenerIngredientes(){
		return productoService.getIngredientes();
	}
}
