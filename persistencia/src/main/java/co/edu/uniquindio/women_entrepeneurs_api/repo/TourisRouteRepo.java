package co.edu.uniquindio.women_entrepeneurs_api.repo;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.MicroSite;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.Profile;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.TouristRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourisRouteRepo extends JpaRepository<TouristRoute, Integer>{

    //long countByLocationSiteList_MicroSite(MicroSite microSite);
    long countByLocationSiteList(MicroSite microsite);
}
