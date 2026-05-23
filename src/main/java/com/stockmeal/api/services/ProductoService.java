package com.stockmeal.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmeal.api.dto.ProductoDTO;
import com.stockmeal.api.models.TipoProducto;
import com.stockmeal.api.repositories.ProductoRepository;

@Service
public class ProductoService {
	@Autowired
	ProductoRepository productoRepository;
	
	public List<ProductoDTO> obtenerProductos() {
	    return productoRepository.findAll()
	            .stream()
	            .map(ProductoDTO::fromEntity)
	            .toList();
	}
	
	public List<ProductoDTO> getPlatos() {
	    return productoRepository.findByTipo(TipoProducto.plato)
	            .stream()
	            .map(ProductoDTO::fromEntity)
	            .toList();
	}
	
	public List<ProductoDTO> getIngredientes() {
	    return productoRepository.findByTipo(TipoProducto.ingrediente)
	            .stream()
	            .map(ProductoDTO::fromEntity)
	            .toList();
	}
}
