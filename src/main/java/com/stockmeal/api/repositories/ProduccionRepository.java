package com.stockmeal.api.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockmeal.api.models.Produccion;

@Repository
public interface ProduccionRepository extends JpaRepository<Produccion, Integer> {

    List<Produccion> findByFecha(LocalDate fecha);

    List<Produccion> findByFechaBetween(LocalDate desde, LocalDate hasta);

    List<Produccion> findByProductoIdProducto(Integer idProducto);
}
