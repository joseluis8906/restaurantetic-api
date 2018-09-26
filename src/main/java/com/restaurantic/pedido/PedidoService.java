package com.restaurantic.pedido;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoService {
    Pedido findByCodigo(String codigo);
    List<Pedido> findByFecha(LocalDateTime fecha);
    Pedido create(Pedido pedido);
    void update(String codigo, Pedido pedido);
    void delete(String codigo);
}