package com.stockmeal.api.dto;

import lombok.Data;

@Data
public class RecetaDTO {

    private Integer idReceta;
    private String nombre;
    private Long numIngredientes;

    public RecetaDTO(Integer idReceta, String nombre, Long numIngredientes) {
        this.idReceta = idReceta;
        this.nombre = nombre;
        this.numIngredientes = numIngredientes;
    }
}
