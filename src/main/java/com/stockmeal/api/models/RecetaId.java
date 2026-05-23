package com.stockmeal.api.models;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class RecetaId implements Serializable {

    private Integer producto;
    private Integer ingrediente;

    public RecetaId() {}

    public RecetaId(Integer producto, Integer ingrediente) {
        this.producto = producto;
        this.ingrediente = ingrediente;
    }

	@Override
	public int hashCode() {
		return Objects.hash(ingrediente, producto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecetaId other = (RecetaId) obj;
		return Objects.equals(ingrediente, other.ingrediente) && Objects.equals(producto, other.producto);
	}
}
    
    