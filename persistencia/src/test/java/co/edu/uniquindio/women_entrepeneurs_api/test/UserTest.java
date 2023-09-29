package co.edu.uniquindio.women_entrepeneurs_api.test;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.LevelAccess;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.User;
import co.edu.uniquindio.women_entrepeneurs_api.repo.LevelAccessRepo;
import co.edu.uniquindio.women_entrepeneurs_api.repo.UserRepo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTest {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private LevelAccessRepo levelAccessRepo;

/*
    @Test
    //@Sql("classpath:dataset.sql")

    public void register(){

        String email = "pablo@gmail.com";
        String password = "rootie";
        boolean isActive = true;

        User user = new User(1,"pablo@gmail.com","aadsf",true,false,null,null,null,null, new LevelAccess(),null,null);

        User usuarioGuardado = userRepo.save(user);
        Assertions.assertEquals("pablo@gmail.com", usuarioGuardado.getEmail());
        System.out.println(usuarioGuardado);
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void updateTest(){
        Optional<User> user = userRepo.findById(1);
        if(user.isPresent()){
            user.get().setEmail("dahiana@gmail.com");
            userRepo.save(user.get());
            Optional<User> userUpdated = userRepo.findById(1);
            if(userUpdated.isPresent()){
                Assertions.assertEquals("dahiana@gmail.com", user.get().getEmail());

            }else{
                Assertions.fail("No se encontró el usuario despues de actualizar");
            }
        }else{
            Assertions.fail("No se encontró el usuario");
        }
    }
    @Test
    //@Sql("classpath:dataset.sql")
    public void remove(){

        Optional<User> user = userRepo.findById(1);
        if(user.isPresent()){
            userRepo.delete(user.get());
            Assertions.assertFalse(userRepo.findById(1).isPresent());
        }else{
            Assertions.fail("No se encontró el usuario");
        }
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void findByEmailAndPasswordTest(){
        Optional<User> user = userRepo.findByEmailAndPassword("diego@test.com","password");
        if(user.isPresent()){
            Assertions.assertEquals("diego@test.com", user.get().getEmail());
        }else {
            Assertions.fail("No se encontró el usuario");
        }
    }





    @Test
    //@Sql("classpath:dataset.sql")
    public void findByEmailTest(){
        Optional<User> user = userRepo.findByEmail("diego@test.com");
        if(user.isPresent()){
            Assertions.assertEquals("diego@test.com", user.get().getEmail());
        }else {
            Assertions.fail("No se encontró el usuario");
        }
    }
    @Test
    //@Sql("classpath:dataset.sql")
    public void filtrarEmailTest(){
        Pageable paginador = PageRequest.of(0,2);

        Page<User> list = userRepo.findAll(paginador);
        Assertions.assertNotNull(list, "La lista de usuarios filtrados por email no puede ser nula");
    }


 */
}