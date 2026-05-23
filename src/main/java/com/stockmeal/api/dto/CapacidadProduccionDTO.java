package com.stockmeal.api.dto;

import lombok.Data;

@Data
public class CapacidadProduccionDTO {

    private Integer idProducto;
    private String nombre;
    private Integer unidadesPosibles;

    public CapacidadProduccionDTO(Integer idProducto, String nombre, Integer unidadesPosibles) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.unidadesPosibles = unidadesPosibles;
    }
}
