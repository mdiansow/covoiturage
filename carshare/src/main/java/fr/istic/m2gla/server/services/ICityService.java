package fr.istic.m2gla.server.services;

import fr.istic.m2gla.shared.Ville;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by mds on 10/03/15.
 * Class ${CLASS}
 */
public interface ICityService {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    Collection<Ville> findAll();
}
