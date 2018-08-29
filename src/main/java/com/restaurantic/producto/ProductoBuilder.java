package com.restaurantic.producto;

public class ProductoBuilder {
    private Producto producto;

    public ProductoBuilder () {
        producto = new Producto();
    }

    public ProductoBuilder withCodigo(String codigo) {
        producto.setCodigo(codigo);
        return this;
    }

    public ProductoBuilder withNombre(String nombre){
        producto.setNombre(nombre);
        return this;
    }

    public ProductoBuilder withDescripcion(String descripcion){
        producto.setDescripcion(descripcion);
        return this;
    }

    public ProductoBuilder withIngredientes(String ingredientes){
        producto.setIngredientes(ingredientes);
        return this;
    }

    public ProductoBuilder withPrecio(Double precio){
        producto.setPrecio(precio);
        return this;
    }

    public Producto build() {
        return producto;
    }
}
