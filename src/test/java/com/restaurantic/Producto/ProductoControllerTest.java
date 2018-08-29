package com.restaurantic.Producto;

import com.restaurantic.producto.*;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
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
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductoServiceImpl service;

    @Test
    public void givenProductos_whenGetProductos_thenReturnJsonArray () throws Exception {
        Producto producto = new ProductoBuilder()
                .withCodigo("HAM001")
                .withNombre("HAMBURGUESA CON QUESO")
                .withDescripcion("Deliciosa hamburguesa")
                .withIngredientes("Cebolla,Tomate,Carne,Pan")
                .withPrecio(10000.0)
                .build();

        List<Producto> productos = Arrays.asList(producto);

        BDDMockito.given(service.findAll()).willReturn(productos);

        mvc.perform(MockMvcRequestBuilders.get("/api/v1/productos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].codigo", Matchers.is(producto.getCodigo())));
    }
}
