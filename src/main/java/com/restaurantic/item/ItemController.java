package com.restaurantic.item;

import com.restaurantic.pedido.Pedido;
import com.restaurantic.pedido.PedidoServiceImpl;
import com.restaurantic.producto.Producto;
import com.restaurantic.producto.ProductoServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/api/v1/pedidos")
public class ItemController {

    private ItemServiceImpl itemService;
    private PedidoServiceImpl pedidoService;
    private ProductoServiceImpl productoService;

    public ItemController (ItemServiceImpl itemService, PedidoServiceImpl pedidoService, ProductoServiceImpl productoService){
        this.itemService = itemService;
        this.pedidoService = pedidoService;
        this.productoService = productoService;
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

    @PostMapping("/{codigoPedido}/items/productos/{codigoProducto}")
    public Pedido create (@PathVariable String codigoPedido, @PathVariable String codigoProducto, @RequestBody Item item) {

        Pedido tmpPedido = this.pedidoService.findByCodigo(codigoPedido);
        Producto tmpProducto = this.productoService.findByCodigo(codigoProducto);

        if(tmpPedido != null && tmpProducto != null) {
            item.setProducto(tmpProducto);
            item.setNumero(tmpPedido.getItems().size() + 1);
            tmpPedido.setTotal(tmpPedido.getTotal() + item.getPrecio());    
            tmpPedido.getItems().add(item);
            this.pedidoService.create(tmpPedido);
            return tmpPedido;
        }

        return null;
    }


    @DeleteMapping("/{codigo}/items/{numero}")
    public Pedido delete (@PathVariable String codigo, @PathVariable Integer numero) {
        Pedido tmpPedido = this.pedidoService.findByCodigo(codigo);

        if( tmpPedido != null ) {
            List<Item> items = tmpPedido.getItems();

            for(int i = 0; i < items.size(); i++ ){
                Item item = items.get(i);
                if (item.getNumero() == numero){
                    tmpPedido.getItems().remove(item);
                    tmpPedido.setTotal(tmpPedido.getTotal() - item.getPrecio());
                    this.pedidoService.create(tmpPedido);
                    tmpPedido = this.pedidoService.findByCodigo(codigo);
                    List<Item> newItems = tmpPedido.getItems();
                    int length = newItems.size();
                    for (int j = numero - 1; j < length; j++) {
                        newItems.get(j).setNumero(j + 1);
                    }
                    return tmpPedido;
                }
            }
        }

        return null;
    }

}
