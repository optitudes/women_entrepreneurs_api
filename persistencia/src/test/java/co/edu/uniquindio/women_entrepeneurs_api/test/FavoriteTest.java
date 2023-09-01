package co.edu.uniquindio.women_entrepeneurs_api.test;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Favorite;
import co.edu.uniquindio.women_entrepeneurs_api.repo.FavoriteRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FavoriteTest {

    @Autowired
    private FavoriteRepo favoriteRepo;

    @Test
    public void create(){
        Favorite favorite = new Favorite(1,null,null);
        Favorite favoriteSaved = favoriteRepo.save(favorite);
        Assertions.assertEquals(null, favoriteSaved.getUser());
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void remove(){

        Optional<Favorite> favorite = favoriteRepo.findById(1);
        if(favorite.isPresent()){
            favoriteRepo.delete(favorite.get());
            Assertions.assertFalse(favoriteRepo.findById(1).isPresent());
        }else{
            Assertions.fail("No se encontró los favoritos");
        }
    }

}