package com.stockmeal.api.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockmeal.api.dto.ProduccionDTO;
import com.stockmeal.api.dto.ProduccionRequestDTO;
import com.stockmeal.api.models.Produccion;
import com.stockmeal.api.models.Producto;
import com.stockmeal.api.models.Receta;
import com.stockmeal.api.repositories.ProduccionRepository;
import com.stockmeal.api.repositories.ProductoRepository;
import com.stockmeal.api.repositories.RecetaRepository;

@Service
public class ProduccionService {

    @Autowired
    ProduccionRepository produccionRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    RecetaRepository recetaRepository;

    public List<ProduccionDTO> getHistorico() {
        return produccionRepository.findAll()
                .stream()
                .map(ProduccionDTO::fromEntity)
                .toList();
    }

    public List<ProduccionDTO> getByFecha(LocalDate fecha) {
        return produccionRepository.findByFecha(fecha)
                .stream()
                .map(ProduccionDTO::fromEntity)
                .toList();
    }

    public List<ProduccionDTO> getByRango(LocalDate desde, LocalDate hasta) {
        return produccionRepository.findByFechaBetween(desde, hasta)
                .stream()
                .map(ProduccionDTO::fromEntity)
                .toList();
    }

    public List<ProduccionDTO> getByPlato(Integer idProducto) {
        return produccionRepository.findByProductoIdProducto(idProducto)
                .stream()
                .map(ProduccionDTO::fromEntity)
                .toList();
    }

    @Transactional
    public ProduccionDTO registrar(ProduccionRequestDTO request) {
        Producto producto = productoRepository.findById(request.getIdProducto())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + request.getIdProducto()));

        List<Receta> receta = recetaRepository.findByProductoIdProducto(request.getIdProducto());
        if (receta.isEmpty()) {
            throw new IllegalArgumentException("El producto no tiene receta definida: " + request.getIdProducto());
        }

        for (Receta linea : receta) {
            Producto ingrediente = linea.getIngrediente();
            int consumo = (int) Math.ceil(linea.getCantidad() * request.getCantidad());
            int stockResultante = ingrediente.getStockDisponible() - consumo;
            if (stockResultante < 0) {
                throw new IllegalArgumentException(
                    "Stock insuficiente para el ingrediente '" + ingrediente.getNombre() +
                    "': disponible=" + ingrediente.getStockDisponible() +
                    ", necesario=" + consumo
                );
            }
            ingrediente.setStockDisponible(stockResultante);
            productoRepository.save(ingrediente);
        }

        Produccion produccion = new Produccion();
        produccion.setProducto(producto);
        produccion.setCantidad(request.getCantidad());
        produccion.setFecha(request.getFecha());

        return ProduccionDTO.fromEntity(produccionRepository.save(produccion));
    }
}
