package com.restaurantic.pedido;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Transactional
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    private PedidoServiceImpl pedidoService;
    private String fechaInicio;
    private AtomicInteger contador;

    public PedidoController (PedidoServiceImpl pedidoService){
        this.pedidoService = pedidoService;
        this.fechaInicio = "2019-04-06";
        this.contador = new AtomicInteger(0);
    }

    @GetMapping("/{codigo}")
    public Pedido findByCodigo (@PathVariable String codigo){
        return this.pedidoService.findByCodigo(codigo);
    }

    @GetMapping
    public List<Pedido> filter (@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha, @RequestParam(required = false) Boolean pago) {
        if(fecha != null){
            LocalDateTime fechaInicial = fecha.withHour(0).withMinute(0).withSecond(0);
            LocalDateTime fechaFinal = fecha.withHour(23).withMinute(59).withSecond(59);
            return this.pedidoService.findByFechaBetween(fechaInicial, fechaFinal);
        }

        else if(pago != null) {
          return this.pedidoService.findByPago(pago);
        }

        return null;
    }

    @GetMapping("/codigo/next")
    public String getCode() {
        String fechaHoy = LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toString().split("T")[0];
        if (!this.fechaInicio.equals(fechaHoy)) {
            this.contador.set(0);
            this.fechaInicio = fechaHoy;
        }

        return "PED-" + String.format("%04d", this.contador.incrementAndGet());
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
