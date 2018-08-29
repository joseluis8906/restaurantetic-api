package com.restaurantic.Producto;

import com.restaurantic.producto.Producto;
import com.restaurantic.producto.ProductoBuilder;
import com.restaurantic.producto.ProductoRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductoRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    public void whenFindByCodigo_thenReturnProducto() {
        // given
        Producto producto = new ProductoBuilder()
                .withCodigo("HAM001")
                .build();

        entityManager.persist(producto);
        entityManager.flush();

        // when
        Producto found = productoRepository.findByCodigo(producto.getCodigo());

        // then
        Assertions.assertThat(found.getCodigo()).isEqualTo(producto.getCodigo());
    }

    @Test
    public void whenFindContainingNombre_thenReturnProductosList (){
        // given
        List<Producto> productoList = new ArrayList<>();

        for(int i=0; i<5; i++) {
            Producto producto = new ProductoBuilder()
                    .withNombre("HAM00" + Integer.toString(i))
                    .build();

            entityManager.persist(producto);
            entityManager.flush();

            productoList.add(producto);
        }

        // when
        List<Producto> foundProductoList = productoRepository.findByNombreContainingIgnoreCase("HAM00");


        // then
        Assertions.assertThat(productoList.size()).isEqualTo(foundProductoList.size());
    }

    @Test
    public void whenFindAll_thenReturnAll () {
        // given
        List<Producto> productoList = new ArrayList<>();

        for(int i=0; i<10; ++i){
            Producto producto = new ProductoBuilder()
                    .withCodigo("HAM00" + Integer.toString(i))
                    .withNombre("HAMBURGUESA NUMERO " + Integer.toString(i))
                    .withDescripcion("Es una super hamburguesa")
                    .withIngredientes("Cebolla,Tomate,Carne,Pan")
                    .withPrecio(10000.0 * (i + 1))
                    .build();

            entityManager.persist(producto);
            entityManager.flush();

            productoList.add(producto);
        }

        // when
        List<Producto> foundProductoList = productoRepository.findAll();

        // then
        Assertions.assertThat(productoList.size()).isEqualTo(foundProductoList.size());
    }
}
