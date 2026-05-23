package com.stockmeal.api.services;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmeal.api.dto.CapacidadProduccionDTO;
import com.stockmeal.api.dto.IngredienteDTO;
import com.stockmeal.api.dto.RecetaDTO;
import com.stockmeal.api.dto.RecetaDetalleDTO;
import com.stockmeal.api.models.Producto;
import com.stockmeal.api.models.Receta;
import com.stockmeal.api.models.TipoProducto;
import com.stockmeal.api.repositories.ProductoRepository;
import com.stockmeal.api.repositories.RecetaRepository;

@Service
public class RecetaService {
	@Autowired
	RecetaRepository recetaRepository;

    @Autowired
    ProductoRepository productoRepository;

	public List<RecetaDTO> getRecetas(){
	    return recetaRepository.obtenerRecetas();
	}

	public RecetaDetalleDTO getRecetaDetalle(Integer id){
	    Producto plato = productoRepository.findById(id).orElseThrow();
	    List<IngredienteDTO> ingredientes = recetaRepository.obtenerIngredientesReceta(id);
	    return new RecetaDetalleDTO(
	        plato.getIdProducto(),
	        plato.getNombre(),
	        ingredientes
	    );
	}

	public List<CapacidadProduccionDTO> getCapacidadProduccion() {
	    return productoRepository.findByTipo(TipoProducto.plato).stream()
	        .map(plato -> {
	            List<Receta> lineas = recetaRepository.findByProductoIdProducto(plato.getIdProducto());
	            if (lineas.isEmpty()) return null;

	            int unidades = lineas.stream()
	                .mapToInt(r -> (int) Math.floor(
	                    r.getIngrediente().getStockDisponible() / r.getCantidad()
	                ))
	                .min()
	                .orElse(0);

	            return new CapacidadProduccionDTO(plato.getIdProducto(), plato.getNombre(), unidades);
	        })
	        .filter(c -> c != null && c.getUnidadesPosibles() > 0)
	        .sorted(Comparator.comparingInt(CapacidadProduccionDTO::getUnidadesPosibles).reversed())
	        .toList();
	}
}
