package co.edu.uniquindio.women_entrepeneurs_api.test;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.ShoppingCart;
import co.edu.uniquindio.women_entrepeneurs_api.repo.ShoppingCartRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ShoppingCartTest {

    @Autowired
    private ShoppingCartRepo shoppingCartRepo;

    @Test
    //@Sql("classpath:dataset.sql")
    public void updateTest(){
        Optional<ShoppingCart> shoppingCart = shoppingCartRepo.findById(1);
        if(shoppingCart.isPresent()){
            shoppingCart.get().setFullValue(66.0);
            shoppingCartRepo.save(shoppingCart.get());
            Optional<ShoppingCart> shoppingCartUpdated = shoppingCartRepo.findById(1);
            if(shoppingCartUpdated.isPresent()){
                Assertions.assertEquals(66.0, shoppingCartUpdated.get().getFullValue());

            }else{
                Assertions.fail("No se encontró el carrito de compras despues de actualizar");
            }
        }else{
            Assertions.fail("No se encontró el carrito de compras");
        }
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void remove(){

        Optional<ShoppingCart> shoppingCart = shoppingCartRepo.findById(1);
        if(shoppingCart.isPresent()){
            shoppingCartRepo.delete(shoppingCart.get());
            Assertions.assertFalse(shoppingCartRepo.findById(1).isPresent());
        }else{
            Assertions.fail("No se encontró el carrito de compras");
        }
    }
    /*
    @Test
    //@Sql("classpath:dataset.sql")
    public void obtenerNombreUserTest(){
        String nombre = shoppingCartRepo.obtenerNombreUser(1);
        Assertions.assertEquals("pablo" , nombre);
    }
    */


}