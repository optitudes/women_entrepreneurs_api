package co.edu.uniquindio.women_entrepeneurs_api.test;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Product;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.Users;
import co.edu.uniquindio.women_entrepeneurs_api.repo.LevelAccessRepo;
import co.edu.uniquindio.women_entrepeneurs_api.repo.UserRepo;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.Bill;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTest {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private LevelAccessRepo levelAccessRepo;


    @Test
    //@Sql("classpath:dataset.sql")

    public void register(){

        String email = "pablo@gmail.com";
        String password = "rootie";
        String dni = "119112114";
        Integer phoneNumber = 323323323;
        String address = "calarca";
        boolean isActive = true;
        String name = "pablo";

        Users user = new Users(1,email,name,password,dni,phoneNumber,address,isActive,null,null,null,null,null,null);

        Users usuarioGuardado = userRepo.save(user);
        Assertions.assertEquals("pablo", usuarioGuardado.getName());
        System.out.println(usuarioGuardado);
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void updateTest(){
        Optional<Users> user = userRepo.findById(1);
        if(user.isPresent()){
            user.get().setName("dahiana");
            userRepo.save(user.get());
            Optional<Users> userUpdated = userRepo.findById(1);
            if(userUpdated.isPresent()){
                Assertions.assertEquals("dahiana", user.get().getName());

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

        Optional<Users> user = userRepo.findById(1);
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
        Optional<Users> user = userRepo.findByEmailAndPassword("diego@test.com","password");
        if(user.isPresent()){
            Assertions.assertEquals("diego", user.get().getName());
        }else {
            Assertions.fail("No se encontró el usuario");
        }
    }



    @Test
    //@Sql("classpath:dataset.sql")
    public void findByEmailTest(){
        Optional<Users> user = userRepo.findByEmail("diego@test.com");
        if(user.isPresent()){
            Assertions.assertEquals("diego", user.get().getName());
        }else {
            Assertions.fail("No se encontró el usuario");
        }
    }
    @Test
    //@Sql("classpath:dataset.sql")
    public void filtrarEmailTest(){
        Pageable paginador = PageRequest.of(0,2);

        Page<Users> list = userRepo.findAll(paginador);
        Assertions.assertNotNull(list, "La lista de usuarios filtrados por email no puede ser nula");
    }
   @Test
    //@Sql("classpath:dataset.sql")
    public void obtenerProductosFavoritosUserTest(){
        List<Product> favoritos = userRepo.obtenerProductosFavoritosUser("diego@test.com");
        Assertions.assertEquals(1, favoritos.size());
    }
    @Test
    //@Sql("classpath:dataset.sql")
    public void obtenerProductosCarritoUserTest(){
        List<Product> favoritos = userRepo.obtenerProductosCarritoUser("diego@test.com");
        Assertions.assertEquals(1, favoritos.size());
    }


    @Test
    //@Sql("classpath:dataset.sql")
    public void listarUsuariosYCuponesUserTest(){
        List<String[]> respuesta = userRepo.listarUsuariosYCupones();
        respuesta.forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void listarNombresUsuariosYComentariosUserTest(){
        List<String[]> respuesta = userRepo.listarNombresUsuariosYComentarios();
        respuesta.forEach(arr -> System.out.println(Arrays.toString(arr)));
    }
    @Test
    //@Sql("classpath:dataset.sql")
    public void buscarPatronNombreUserTest(){
        List<Users> usuarios = userRepo.buscarPatronNombre("die");
        Assertions.assertEquals(1, usuarios.size());
    }
    @Test
    //@Sql("classpath:dataset.sql")
    public void getAllBillsAndBillDetails(){
        Optional<Users> user = userRepo.findByEmail("diego@test.com");
        if(user.isPresent()){
            List<Object[]> listBillsAndBillDetails = userRepo.getBIllsAndBillDetails(user.get().getId());
            Map<String, Map<String, Object>> resultMap = listBillsAndBillDetails.stream()
                    .collect(Collectors.groupingBy(obj -> (String) obj[0], // agrupar por billCode
                            Collectors.mapping(obj -> Map.of("amount", (Integer) obj[2], "price", (Double) obj[3]),
                                    Collectors.toList()))) // agregar detalles a una lista
                    .entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, // billCode como clave del mapa
                            e -> Map.of("bill_code", e.getKey(),
                                    "total", ((Double) e.getValue().stream()
                                            .map(d -> (Double) d.get("price") * (Integer) d.get("amount"))
                                            .reduce(0.0, Double::sum)),
                                    "bill_details", e.getValue()))); // lista de detalles como valor del mapa

            System.out.println(resultMap);

            Assertions.assertEquals("diego", user.get().getName());
        }else {
            Assertions.fail("No se encontró el usuario");
        }
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void getAllBills(){
        Optional<Users> user = userRepo.findByEmail("diego@test.com");
        if(user.isPresent()){
            List<Bill> billsFinded = userRepo.getBills(user.get().getEmail());
            for (Bill bill:billsFinded) {
                String billCode = bill.getBillCode();
                Double billTotal = bill.getTotal();
                System.out.println("code: "+billCode+" ; total: "+billTotal);
            }

            Assertions.assertEquals("diego", user.get().getName());
        }else {
            Assertions.fail("No se encontró el usuario");
        }
    }


}