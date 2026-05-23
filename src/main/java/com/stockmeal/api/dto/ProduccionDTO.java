package com.stockmeal.api.dto;

import java.time.LocalDate;

import com.stockmeal.api.models.Produccion;

import lombok.Data;

@Data
public class ProduccionDTO {

    private Integer idProduccion;
    private String plato;
    private Integer cantidad;
    private LocalDate fecha;

    public ProduccionDTO(Integer idProduccion, String plato, Integer cantidad, LocalDate fecha) {
        this.idProduccion = idProduccion;
        this.plato = plato;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public static ProduccionDTO fromEntity(Produccion produccion) {
        return new ProduccionDTO(
            produccion.getIdProduccion(),
            produccion.getProducto().getNombre(),
            produccion.getCantidad(),
            produccion.getFecha()
        );
    }
}
