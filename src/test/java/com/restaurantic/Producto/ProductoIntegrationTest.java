package com.restaurantic.Producto;

import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.restaurantic.Application;
import com.restaurantic.producto.Producto;
import com.restaurantic.producto.ProductoBuilder;
import com.restaurantic.producto.ProductoRepository;
import com.restaurantic.producto.ProductoServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ProductoIntegrationTest {
    static String URL = "/api/v1/productos";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductoRepository repository;

    @Autowired
    private ProductoServiceImpl service;

    @Test
    public void givenOneProducto_whenPostProducto_thenStatus200AndReturnJsonObject () throws Exception {

        Producto producto = new ProductoBuilder()
                .withCodigo("HDG001")
                .withNombre("HOGDOG SENCILLO")
                .withDescripcion("Delicioso hogdog")
                .withIngredientes("Cebolla,Tomate,Carne,Pan")
                .withPrecio(5000.0)
                .build();

        JacksonJsonProvider jsonProvider = new JacksonJsonProvider();

        // when
        mvc.perform(MockMvcRequestBuilders.post(URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonProvider.toJson(producto)))

                //then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.codigo", Matchers.is(producto.getCodigo())));
    }
}
