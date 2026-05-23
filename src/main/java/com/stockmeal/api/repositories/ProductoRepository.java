package com.stockmeal.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockmeal.api.models.Producto;
import com.stockmeal.api.models.TipoProducto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	List<Producto> findByTipo(TipoProducto tipo);
	
}
