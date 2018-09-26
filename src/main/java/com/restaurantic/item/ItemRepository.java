package com.restaurantic.item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByPedidoAndNumero(String pedido, String numero);
    List<Item> filterByPedido(String pedido);
    void delete(String pedido, String numero);
}
