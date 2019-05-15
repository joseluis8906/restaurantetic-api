package com.restaurantic.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Pedido findByCodigoAndFecha(String codigo, LocalDateTime fecha);
    List<Pedido> findByFechaBetween(LocalDateTime fechaInicial, LocalDateTime fechaFinal);
    List<Pedido> findAll();
    List<Pedido> findByPago(Boolean pago);
    void delete(Pedido pedido);
}
