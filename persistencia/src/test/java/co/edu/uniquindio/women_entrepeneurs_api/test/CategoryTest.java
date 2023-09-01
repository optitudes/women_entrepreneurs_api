package co.edu.uniquindio.women_entrepeneurs_api.test;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Category;
import co.edu.uniquindio.women_entrepeneurs_api.repo.CategoryRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryTest {


    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    public void create(){
        Category category = new Category(1,"Terror", "Un terror terrorifico", null);
        Category categorySaved = categoryRepo.save(category);
        Assertions.assertEquals("Terror", categorySaved.getName());
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void updateTest(){
        Optional<Category> category = categoryRepo.findById(1);
        if(category.isPresent()){
            category.get().setDescription("Productos enfocados en el cuidado familiar");
            categoryRepo.save(category.get());
            Optional<Category> categoryUpdated = categoryRepo.findById(1);
            if(categoryUpdated.isPresent()){
                Assertions.assertEquals("Productos enfocados en el cuidado familiar", categoryUpdated.get().getDescription());

            }else{
                Assertions.fail("No se encontró la categoria despues de actualizar");
            }
        }else{
            Assertions.fail("No se encontró la categoria");
        }
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void remove(){

        Optional<Category> category = categoryRepo.findById(1);
        if(category.isPresent()){
            categoryRepo.delete(category.get());
            Assertions.assertFalse(categoryRepo.findById(1).isPresent());
        }else{
            Assertions.fail("No se encontró la categoria");
        }
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void findByName(){

        Optional<Category> category = categoryRepo.findByName("Familiar");
        if(category.isPresent()){
            Assertions.assertEquals("Productos familiares",category.get().getDescription());
        }else{
            Assertions.fail("No se encontró la categoria");
        }
    }

}