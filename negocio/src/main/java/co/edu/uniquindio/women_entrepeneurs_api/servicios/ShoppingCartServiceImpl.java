package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.ShoppingCart;
import co.edu.uniquindio.women_entrepeneurs_api.repo.ShoppingCartRepo;

public class ShoppingCartServiceImpl implements ShoppingCartService{

    private final ShoppingCartRepo shoppingCartRepo;

    public ShoppingCartServiceImpl(ShoppingCartRepo shoppingCartRepo) {
        this.shoppingCartRepo = shoppingCartRepo;
    }

    @Override
    public ShoppingCart registerShoppingCart(ShoppingCart s) throws Exception {
        return shoppingCartRepo.save(s);
    }

    @Override
    public ShoppingCart updateShoppingCart(ShoppingCart s) throws Exception {
        return shoppingCartRepo.save(s);
    }

    @Override
    public void deleteShoppingCart(int id) throws Exception {
        shoppingCartRepo.deleteById(id);
    }
}
