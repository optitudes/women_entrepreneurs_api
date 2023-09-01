package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.LevelAccess;

public interface LevelAccessService {
    LevelAccess registerLevelAccess(LevelAccess l) throws Exception;

    LevelAccess updateLevelAccess(LevelAccess l) throws Exception;

    void deleteLevelAccess(int id) throws Exception;
}
