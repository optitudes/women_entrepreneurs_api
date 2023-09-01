package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Favorite;

public interface FavoriteService {
    Favorite registerFavorite(Favorite f) throws Exception;

    Favorite updateFavorite(Favorite f) throws Exception;

    void deleteFavorite(int id) throws Exception;
}
