package com.restaurantic.Producto;

import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.restaurantic.producto.*;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    static String URL = "/api/v1/productos";

    @MockBean
    private ProductoServiceImpl service;

    @Autowired
    private MockMvc mvc;


    @Test
    public void givenOneProducto_whenSendProducto_thenCreateAndReturnJsonObject () throws Exception {
        // given
        Producto producto = new ProductoBuilder()
                .withCodigo("HDG001")
                .withNombre("HOGDOG SENCILLO")
                .withDescripcion("Delicioso hogdog")
                .withIngredientes("Cebolla,Tomate,Carne,Pan")
                .withPrecio(5000.0)
                .build();

        JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
        Mockito.when(service.create(ArgumentMatchers.any(Producto.class))).thenReturn(producto);

        // when
        mvc.perform(MockMvcRequestBuilders.post(URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonProvider.toJson(producto)))
                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.codigo", Matchers.is(producto.getCodigo())));
    }

    @Test
    public void givenProductos_whenGetProductos_thenReturnJsonArray () throws Exception {
        // given
        Producto producto = new ProductoBuilder()
                .withCodigo("HAM001")
                .withNombre("HAMBURGUESA CON QUESO")
                .withDescripcion("Deliciosa hamburguesa")
                .withIngredientes("Cebolla,Tomate,Carne,Pan")
                .withPrecio(10000.0)
                .build();

        List<Producto> allProducts = Arrays.asList(producto);
        Mockito.when(service.findAll()).thenReturn(allProducts);

        // when
        mvc.perform(MockMvcRequestBuilders.get(URL)

                // then
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].codigo", Matchers.is(producto.getCodigo())));
    }
}
