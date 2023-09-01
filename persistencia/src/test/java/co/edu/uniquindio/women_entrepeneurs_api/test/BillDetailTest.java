package co.edu.uniquindio.women_entrepeneurs_api.test;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.BillDetail;
import co.edu.uniquindio.women_entrepeneurs_api.repo.BillDetailRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BillDetailTest {

    @Autowired
    private BillDetailRepo billDetailRepo;

    @Test
    public void create(){
        BillDetail billDetail = new BillDetail(1,22,22.0,null,null);
        BillDetail billDetailSaved = billDetailRepo.save(billDetail);
        Assertions.assertEquals(22, billDetailSaved.getAmount());
    }

    @Test
    //@Sql("classpath:dataset.sql")
    public void updateTest(){
        Optional<BillDetail> billDetail = billDetailRepo.findById(1);
        if(billDetail.isPresent()){
            billDetail.get().setAmount(2);
            billDetailRepo.save(billDetail.get());
            Optional<BillDetail> billDetailUpdated = billDetailRepo.findById(1);
            if(billDetailUpdated.isPresent()){
                Assertions.assertEquals(2, billDetailUpdated.get().getAmount());

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

        Optional<BillDetail> billDetail = billDetailRepo.findById(1);
        if(billDetail.isPresent()){
            billDetailRepo.delete(billDetail.get());
            Assertions.assertFalse(billDetailRepo.findById(1).isPresent());
        }else{
            Assertions.fail("No se encontró la categoria");
        }
    }
    @Test
    //@Sql("classpath:dataset.sql")
    public void getBillDetailsByBillId(){

        List<BillDetail> billDetailList = billDetailRepo.getDetailsByBillId(1);
        for (BillDetail billDetail: billDetailList) {
            Integer billDetailAmount  = billDetail.getAmount();
            Double billDetailPrice = billDetail.getPrice();
            System.out.println("amount :"+billDetailAmount+" ; price:"+billDetailPrice);
        }

        if(2 == billDetailList.size()){
            Assertions.assertTrue(true);
        }else{
            Assertions.fail("No se encontraron los detalles de factura");
        }
    }


}