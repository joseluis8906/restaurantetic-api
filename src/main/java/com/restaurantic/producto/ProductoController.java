package com.restaurantic.producto;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Transactional
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private ProductoServiceImpl productoService;

    public ProductoController (ProductoServiceImpl productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/{codigo}")
    public Producto findByCodigo(@PathVariable String codigo){
        return this.productoService.findByCodigo(codigo);
    }

    @GetMapping
    public List<Producto> filter (@RequestParam(required = false) String nombre) {
        if(nombre == null) {
            return this.productoService.findAll();
        }
        return this.productoService.filterByNombre(nombre);

    }

    @PostMapping
    public Producto create(@RequestBody Producto producto) {
        return this.productoService.create(producto);
    }

    @PutMapping("/{codigo}")
    public void update(@PathVariable String codigo, @RequestBody Producto producto){
        this.productoService.update(codigo, producto);
    }
    
    @DeleteMapping("/{codigo}")
    public Long delete(@PathVariable String codigo){
        return this.productoService.delete(codigo);
    }
}
