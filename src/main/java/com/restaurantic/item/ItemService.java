package com.restaurantic.item;

import java.util.List;

public interface ItemService {
    Item findByPedidoAndNumero(String pedido, String numero);
    List<Item> filterByPedido(String pedido);
    Item create(Item item);
    void delete(String pedido, String numero);
}
