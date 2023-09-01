package co.edu.uniquindio.women_entrepeneurs_api.test;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Comment;
import co.edu.uniquindio.women_entrepeneurs_api.repo.CommentRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommentTest {

    @Autowired
    private CommentRepo commentRepo;

    @Test
    public void create(){
        Comment comment = new Comment(1,"pan de yuca es mejor",null,null);

        Comment couponSaved = commentRepo.save(comment);
        Assertions.assertEquals("pan de yuca es mejor", couponSaved.getComment());
        System.out.println(couponSaved);
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void updateTest(){
        Optional<Comment> comment = commentRepo.findById(1);
        if(comment.isPresent()){
            comment.get().setComment("comentario modificado");
            commentRepo.save(comment.get());
            Optional<Comment> commentUpdated = commentRepo.findById(1);
            if(commentUpdated.isPresent()){
                Assertions.assertEquals("comentario modificado", commentUpdated.get().getComment());

            }else{
                Assertions.fail("No se encontró el comentario despues de actualizar");
            }
        }else{
            Assertions.fail("No se encontró el comentario");
        }
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void remove(){

        Optional<Comment> comment = commentRepo.findById(1);
        if(comment.isPresent()){
            commentRepo.delete(comment.get());
            Assertions.assertFalse(commentRepo.findById(1).isPresent());
        }else{
            Assertions.fail("No se encontró el comentario");
        }
    }
    /*
    @Test
    //@Sql("classpath:dataset.sql")
    public void obtenerNombreUserTest(){
        String nombre = commentRepo.obtenerNombreUser(1);
        Assertions.assertEquals("pablo" , nombre);
    }
    */

    @Test
    //@Sql("classpath:dataset.sql")
    public void obtenerNombreProductoTest(){
        String nombre = commentRepo.obtenerNombreProducto(1);
        Assertions.assertEquals("pan" , nombre);
    }
    }
/*
    @Test
    @Sql("classpath:dataset.sql")
    public void findBySecurityCode(){

        Optional<Coupon> coupon = couponRepo.findBySecurityCode("555-5678");
        if(coupon.isPresent()){
            Assertions.assertEquals("Cupon generado por que si",coupon.get().getDescription());
        }else{
            Assertions.fail("No se encontró el cupon");
        }
    }
*/
