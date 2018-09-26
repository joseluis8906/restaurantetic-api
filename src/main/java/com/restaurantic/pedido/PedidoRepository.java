package com.restaurantic.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Pedido findByCodigo(String codigo);
    List<Pedido> findByFecha(LocalDateTime fecha);
    List<Pedido> findAll();
}
