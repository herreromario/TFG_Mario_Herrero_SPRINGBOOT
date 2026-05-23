package com.stockmeal.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class RecetaDetalleDTO {

    private Integer idReceta;
    private String nombre;
    private List<IngredienteDTO> ingredientes;

    public RecetaDetalleDTO(Integer idReceta, String nombre, List<IngredienteDTO> ingredientes) {
        this.idReceta = idReceta;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }
}
