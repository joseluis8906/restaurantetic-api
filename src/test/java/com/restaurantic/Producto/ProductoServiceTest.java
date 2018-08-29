package com.restaurantic.Producto;

import com.restaurantic.producto.*;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class ProductoServiceTest {

    public static Integer listLength = 3;

    @TestConfiguration
    static class ProductoServiceImplTestContextConfiguration {
        @Bean
        public ProductoService productoService() {
            return new ProductoServiceImpl();
        }
    }

    @Autowired
    private ProductoService productoService;

    @MockBean
    private ProductoRepository productoRepository;

    @Before
    public void setup () {
        Producto producto1 = new ProductoBuilder()
                .withCodigo("HAM001")
                .build();

        Mockito.when(productoRepository.findByCodigo("HAM001"))
                .thenReturn(producto1);

        List<Producto> productoList = new ArrayList<>();
        for(int i=0; i<listLength; ++i){
            Producto producto2 = new ProductoBuilder()
                    .withNombre("HAM00" + Integer.toString(i))
                    .build();

            productoList.add(producto2);
        }

        Mockito.when(productoRepository.findByNombreContainingIgnoreCase("HAM00"))
                .thenReturn(productoList);


        Mockito.when(productoRepository.findAll()).thenReturn(productoList);
    }

    @Test
    public void whenValidCodigo_thenProductoShouldBeFound () {
        // given
        String codigo = "HAM001";

        // when
        Producto found = productoService.findByCodigo(codigo);

        // then
        Assertions.assertThat(found.getCodigo()).isEqualTo(codigo);
    }

    @Test
    public void whenValidNombre_thenProductoListShouldBeFound () {
        // given
        String nombre = "HAM00";

        // when
        List<Producto> foundProductoList = productoService.filterByNombre(nombre);

        // then
        Assertions.assertThat(foundProductoList.size()).isEqualTo(listLength);
    }

    @Test
    public void whenFindAll_thenReturnAll () {
        // given nothing

        // when
        List<Producto> foundProductoList = productoService.findAll();

        // then
        Assertions.assertThat(foundProductoList.size()).isEqualTo(listLength);
    }

}
