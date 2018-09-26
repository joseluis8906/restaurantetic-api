package com.restaurantic.item;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;


    @Override
    public Item findByPedidoAndNumero(String pedido, String numero) {
        return this.itemRepository.findByPedidoAndNumero(pedido, numero);
    }

    @Override
    public List<Item> filterByPedido(String pedido) {
        return this.itemRepository.filterByPedido(pedido);
    }

    @Override
    public Item create(Item item) {
        return this.itemRepository.save(item);
    }

    @Override
    public void delete(String pedido, String numero) {
        this.itemRepository.delete(pedido, numero);
    }
}
