package com.restaurantic.pedido;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Transactional
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    private PedidoServiceImpl pedidoService;

    public PedidoController (PedidoServiceImpl pedidoService){
        this.pedidoService = pedidoService;
    }

    @GetMapping("/{codigo}")
    public Pedido findByCodigo (@PathVariable String codigo){
        return this.pedidoService.findByCodigo(codigo);
    }

    @GetMapping
    public List<Pedido> filter (@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha) {
        if(fecha != null){
            LocalDateTime fechaInicial = fecha.withHour(0).withMinute(0).withSecond(0);
            LocalDateTime fechaFinal = fecha.withHour(23).withMinute(59).withSecond(59);
            return this.pedidoService.findByFechaBetween(fechaInicial, fechaFinal);
        }
        return null;
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
