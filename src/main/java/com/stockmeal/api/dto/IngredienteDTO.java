package com.stockmeal.api.dto;

import lombok.Data;

@Data
public class IngredienteDTO {

    private String nombre;
    private Double cantidad;
    private String unidad;

    public IngredienteDTO(String nombre, Double cantidad, String unidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.unidad = unidad;
    }
}
