package com.stockmeal.api.dto;

import com.stockmeal.api.models.Producto;

import lombok.Data;

@Data
public class ProductoDTO {

    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private String unidad;
    private Integer stockActual;
    private Integer stockMinimo;

    public ProductoDTO(Integer idProducto, String nombre, String descripcion, String unidad, Integer stockActual, Integer stockMinimo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
    }

    public static ProductoDTO fromEntity(Producto producto) {
        return new ProductoDTO(
            producto.getIdProducto(),
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getUnidad(),
            producto.getStockDisponible(),
            producto.getStockMinimo()
        );
    }
}
