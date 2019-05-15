package com.restaurantic.pedido;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoServiceImpl implements  PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoServiceImpl (PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido findByCodigoAndFecha(String codigo, LocalDateTime fecha) {
        return pedidoRepository.findByCodigoAndFecha(codigo, fecha);
    }

    @Override
    public List<Pedido> findByPago(Boolean pago) {
        return pedidoRepository.findByPago(pago);
    }

    @Override
    public List<Pedido> findByFechaBetween(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        return pedidoRepository.findByFechaBetween(fechaInicial, fechaFinal);
    }

    @Override
    public Pedido create(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public void update(String codigo, LocalDateTime fecha, Pedido pedido) {
        Pedido tmp = this.pedidoRepository.findByCodigoAndFecha(codigo, fecha);
        if (tmp != null){
            tmp.setTotal(pedido.getTotal());
            tmp.setPago(pedido.getPago());
            this.pedidoRepository.save(tmp);
        }
    }

    @Override
    public void delete(String codigo, LocalDateTime fecha) {
        Pedido tmp = this.pedidoRepository.findByCodigoAndFecha(codigo, fecha);
        if (tmp != null) {
            this.pedidoRepository.delete(tmp);
        }
    }
}
