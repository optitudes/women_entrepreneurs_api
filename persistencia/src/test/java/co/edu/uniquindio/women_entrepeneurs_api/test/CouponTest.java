package co.edu.uniquindio.women_entrepeneurs_api.test;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Coupon;
import co.edu.uniquindio.women_entrepeneurs_api.repo.CouponRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CouponTest {

    @Autowired
    private CouponRepo couponRepo;

    @Test
    public void create(){

        Coupon coupon = new Coupon(1,23000.0,"asdfasdf","Cupom de prueba",true,new Date(),null);
        Coupon couponSaved = couponRepo.save(coupon);
        Assertions.assertEquals("asdfasdf", couponSaved.getSecurityCode());
        System.out.println(couponSaved);
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void updateTest(){
        Optional<Coupon> coupon = couponRepo.findById(1);
        if(coupon.isPresent()){
            coupon.get().setDescription("descripcion modificada");
            couponRepo.save(coupon.get());
            Optional<Coupon> couponUpdated = couponRepo.findById(1);
            if(couponUpdated.isPresent()){
                Assertions.assertEquals("descripcion modificada", couponUpdated.get().getDescription());

            }else{
                Assertions.fail("No se encontró el cupon despues de actualizar");
            }
        }else{
            Assertions.fail("No se encontró el cupom");
        }
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void remove(){

        Optional<Coupon> coupon = couponRepo.findById(1);
        if(coupon.isPresent()){
            couponRepo.delete(coupon.get());
            Assertions.assertFalse(couponRepo.findById(1).isPresent());
        }else{
            Assertions.fail("No se encontró el cupon");
        }
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void findBySecurityCode(){

        Optional<Coupon> coupon = couponRepo.findBySecurityCode("555-5678");
        if(coupon.isPresent()){
            Assertions.assertEquals("Cupon generado por que si",coupon.get().getDescription());
        }else{
            Assertions.fail("No se encontró el cupon");
        }
    }

}