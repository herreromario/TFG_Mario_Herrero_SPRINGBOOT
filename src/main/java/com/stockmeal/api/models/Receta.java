package com.stockmeal.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "receta")
public class Receta {

    @EmbeddedId
    private RecetaId id;

    @ManyToOne
    @MapsId("producto")
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @MapsId("ingrediente")
    @JoinColumn(name = "id_ingrediente")
    private Producto ingrediente;

    @Column(nullable = false)
    private Double cantidad;

    // GETTERS Y SETTERS

    public RecetaId getId() {
        return id;
    }

    public void setId(RecetaId id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Producto getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Producto ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
}
