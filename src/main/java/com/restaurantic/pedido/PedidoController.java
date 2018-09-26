package com.restaurantic.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/v1/pedidos")
public class PedidoController {
    @Autowired
    PedidoServiceImpl pedidoService;

    @GetMapping("/{codigo}")
    public Pedido findByCodigo (@PathVariable String codigo){
        return this.pedidoService.findByCodigo(codigo);
    }

    @GetMapping
    public List<Pedido> filter (@RequestParam(required = false) LocalDateTime fecha) {
        if(fecha != null){
            return this.pedidoService.findByFecha(fecha);
        }
        return new ArrayList<>();
    }

    @PostMapping
    public Pedido create (@RequestBody Pedido pedido) {
        return this.pedidoService.create(pedido);
    }

    @PutMapping
    public void update (String codigo, @RequestBody Pedido pedido){
        this.pedidoService.update(codigo, pedido);
    }

    @DeleteMapping
    public void delete (String codigo) {
        this.pedidoService.delete(codigo);
    }
}
