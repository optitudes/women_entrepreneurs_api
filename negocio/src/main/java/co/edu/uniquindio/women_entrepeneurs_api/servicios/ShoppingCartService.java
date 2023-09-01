package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart registerShoppingCart(ShoppingCart s) throws Exception;

    ShoppingCart updateShoppingCart(ShoppingCart u) throws Exception;

    void deleteShoppingCart(int id) throws Exception;
}
