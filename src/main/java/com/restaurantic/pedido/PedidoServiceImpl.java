package com.restaurantic.pedido;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoServiceImpl implements  PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoServiceImpl (PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido findByCodigo(String codigo) {
        return pedidoRepository.findByCodigo(codigo);
    }

    @Override
    public List<Pedido> findByFecha(LocalDateTime fecha) {
        return pedidoRepository.findByFecha(fecha);
    }

    @Override
    public Pedido create(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public void update(String codigo, Pedido pedido) {
        Pedido tmp = this.pedidoRepository.findByCodigo(codigo);
        if (tmp != null){
            tmp.setFecha(pedido.getFecha());
            tmp.setCliente(pedido.getCliente());
            tmp.setMesa(pedido.getMesa());
            tmp.setIva(pedido.getIva());
            tmp.setSubtotal(pedido.getSubtotal());
            tmp.setTotal(pedido.getSubtotal());
            this.pedidoRepository.save(tmp);
        }
    }

    @Override
    public void delete(String codigo) {
        Pedido tmp = this.pedidoRepository.findByCodigo(codigo);
        if (tmp != null) {
            this.pedidoRepository.delete(tmp);
        }
    }
}
