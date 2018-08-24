package com.restaurantic.producto;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService{

    private ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto findByCodigo(String codigo) {
        return this.productoRepository.findByCodigo(codigo);
    }

    public List<Producto> filterByNombre(String nombre) {
        return this.productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> findAll() {
        return this.productoRepository.findAll();
    }

    public Producto create(Producto producto){
        return this.productoRepository.save(producto);
    }

    public void update(String codigo, Producto producto){
        Producto tmp = this.productoRepository.findByCodigo(codigo);
        if (tmp != null){
            tmp.setNombre(producto.getNombre());
            tmp.setDescripcion(producto.getDescripcion());
            tmp.setIngredientes(producto.getIngredientes());
            tmp.setPrecio(producto.getPrecio());
            this.productoRepository.save(tmp);
        }
    }

    public void delete(String codigo){
        Producto tmp = this.productoRepository.findByCodigo(codigo);
        if(tmp != null) {
            this.productoRepository.delete(tmp);
        }
    }

}