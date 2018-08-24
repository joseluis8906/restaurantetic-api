package com.restaurantic.producto;

import java.util.List;

public interface ProductoService {
    Producto findByCodigo(String codigo);
    List<Producto> filterByNombre(String nombre);
    List<Producto> findAll();
    Producto create(Producto producto);
    void update(String codigo, Producto producto);
    void delete(String codigo);
}
