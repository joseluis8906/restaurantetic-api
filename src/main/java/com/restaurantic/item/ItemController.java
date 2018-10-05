package com.restaurantic.item;

import com.restaurantic.pedido.Pedido;
import com.restaurantic.pedido.PedidoServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/v1/pedidos")
public class ItemController {

    private PedidoServiceImpl pedidoService;

    public ItemController (PedidoServiceImpl pedidoService){
        this.pedidoService = pedidoService;
    }

    @GetMapping("/{codigo}/items")
    public List<Item> filterByPedido (@PathVariable String codigo) {
        Pedido tmp = this.pedidoService.findByCodigo(codigo);

        if(tmp != null) {
            return tmp.getItems();
        }

        return new ArrayList<>();
    }

    @GetMapping("/items")
    public Item findByPedidoAndNumero (@RequestParam(required = false) String codigo, @RequestParam(required = false) Integer numero) {
        if(codigo != null && numero != null) {
            Pedido pedido = this.pedidoService.findByCodigo(codigo);

            if(pedido != null){
                List<Item> items = pedido.getItems();

                for(Item tmpItem: items){
                    if(tmpItem.getNumero() == numero){
                        return tmpItem;
                    }
                }
            }
        }

        return null;
    }

    @PostMapping("/{codigo}/items")
    public Item create (@PathVariable String codigo, @RequestBody Item item) {
        Pedido tmp = this.pedidoService.findByCodigo(codigo);

        if(tmp != null) {
            tmp.getItems().add(item);
            this.pedidoService.update(codigo, tmp);

            return item;
        }

        return null;
    }


    @DeleteMapping("/{codigo}/items/{numero}")
    public void delete (@PathVariable String codigo, @PathVariable String numero) {
        Pedido tmp = this.pedidoService.findByCodigo(codigo);

        if( tmp != null ) {
            List<Item> items = tmp.getItems();

            for(int i = 0; i < items.size(); i++ ){
                if (items.get(i).getNumero().equals(numero)){
                    tmp.getItems().remove(i);
                    this.pedidoService.update(codigo, tmp);
                    break;
                }
            }
        }
    }

}
