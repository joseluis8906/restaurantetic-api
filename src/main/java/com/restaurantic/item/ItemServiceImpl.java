package com.restaurantic.item;

import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    public ItemServiceImpl (ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public Item create(Item item) {
        return this.itemRepository.save(item);
    }

    @Override
    public void delete(Item item) {
        this.itemRepository.delete(item);
    }
}
