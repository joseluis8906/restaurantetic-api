package com.restaurantic.pedido;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PedidoService {
    Pedido findByCodigo(String codigo);
    List<Pedido> findByFechaBetween(LocalDateTime fechaInicial, LocalDateTime fechaFinal);
    List<Pedido> findByPago(Boolean pago);
    Pedido create(Pedido pedido);
    void update(String codigo, Pedido pedido);
    void delete(String codigo);
}
