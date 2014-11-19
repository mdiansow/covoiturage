package fr.istic.m2gla.server;

import fr.istic.m2gla.shared.Evenement;

import javax.ws.rs.Path;
import java.util.Collection;

/**
 * Created by mds on 10/11/14.
 */
@Path("/event")
public class EventServiceImpl implements IEventService {
    @Override
    public Evenement findById(long id) {
        return null;
    }

    @Override
    public Collection<Evenement> findAll() {
        return null;
    }

    @Override
    public void create(Evenement entity) {

    }

    @Override
    public void delete(long entity) {

    }

    @Override
    public Evenement update(Evenement entity) {
        return null;
    }
}
