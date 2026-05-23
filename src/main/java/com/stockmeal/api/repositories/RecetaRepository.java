package com.stockmeal.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stockmeal.api.dto.IngredienteDTO;
import com.stockmeal.api.dto.RecetaDTO;
import com.stockmeal.api.models.Receta;
import com.stockmeal.api.models.RecetaId;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, RecetaId> {

	@Query("SELECT r FROM Receta r WHERE r.producto.idProducto = :idProducto")
	List<Receta> findByProductoIdProducto(@Param("idProducto") Integer idProducto);

	@Query("""
			SELECT new com.stockmeal.api.dto.RecetaDTO(
			    p.idProducto,
			    p.nombre,
			    COUNT(r)
			)
			FROM Receta r
			JOIN r.producto p
			GROUP BY p.idProducto, p.nombre
			""")
	List<RecetaDTO> obtenerRecetas();

	@Query("""
			SELECT new com.stockmeal.api.dto.IngredienteDTO(
			    i.nombre,
			    r.cantidad,
			    i.unidad
			)
			FROM Receta r
			JOIN r.ingrediente i
			WHERE r.producto.idProducto = :id
			""")
	List<IngredienteDTO> obtenerIngredientesReceta(Integer id);
}
