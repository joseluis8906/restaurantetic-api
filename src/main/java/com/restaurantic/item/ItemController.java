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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/pedidos")
public class ItemController {

    private PedidoServiceImpl pedidoService;
    private ProductoServiceImpl productoService;

    public ItemController (PedidoServiceImpl pedidoService, ProductoServiceImpl productoService){
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
    public Item create (@PathVariable String codigoPedido, @PathVariable String codigoProducto, @RequestBody Item item) {
        Pedido tmpPedido = this.pedidoService.findByCodigo(codigoPedido);
        Producto tmpProducto = this.productoService.findByCodigo(codigoProducto);

        if(tmpPedido != null && tmpProducto != null) {
            item.setProducto(tmpProducto);
            tmpPedido.getItems().add(item);
            this.pedidoService.update(codigoPedido, tmpPedido);
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
