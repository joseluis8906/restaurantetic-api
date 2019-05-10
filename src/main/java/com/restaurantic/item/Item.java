package com.restaurantic.item;

import com.restaurantic.producto.Producto;
import com.restaurantic.pedido.Pedido;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pedido_id", "numero"})
})
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;
    @OneToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    private String sinIngredientes;
    private Double precio;
    private String estado;

    public Long getId() {
        return id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setSinIngredientes(String sinIngredientes) {
        this.sinIngredientes = sinIngredientes;
    }

    public String getSinIngredientes() {
        return this.sinIngredientes;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }
}
