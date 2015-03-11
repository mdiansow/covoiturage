package fr.istic.m2gla.server.services;

import fr.istic.m2gla.server.dao.DaoImpl;
import fr.istic.m2gla.server.dao.IDao;
import fr.istic.m2gla.shared.Ville;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by mds on 10/03/15.
 * Class ${CLASS}
 */
@Path("/city")
public class CityServiceImpl implements ICityService {
    private IDao personIDao;

    public CityServiceImpl() {
        this.personIDao = new DaoImpl(Ville.class);
    }

    @Override
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Ville> findAll() {

        return personIDao.findAll();
    }
}
