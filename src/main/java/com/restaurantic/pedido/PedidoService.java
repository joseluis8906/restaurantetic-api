package com.restaurantic.pedido;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PedidoService {
    Pedido findByCodigoAndFecha(String codigo, LocalDateTime fecha);
    List<Pedido> findByFechaBetween(LocalDateTime fechaInicial, LocalDateTime fechaFinal);
    List<Pedido> findByPago(Boolean pago);
    Pedido create(Pedido pedido);
    void update(String codigo, LocalDateTime fecha, Pedido pedido);
    void delete(String codigo, LocalDateTime fecha);
}
