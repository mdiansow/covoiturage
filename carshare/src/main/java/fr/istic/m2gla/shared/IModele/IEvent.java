package fr.istic.m2gla.shared.IModele;

import fr.istic.m2gla.shared.Person;
import fr.istic.m2gla.shared.Ville;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by mds on 09/03/15.
 * Class ${CLASS}
 */
public interface IEvent {
    @Id
    @GeneratedValue
    long getId();

    void setId(long id);

    Date getDate();

    void setDate(Date date);

    int getPrix();

    void setPrix(int prix);

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "VILLES_ETAPES")
    Set<Ville> getVillesEtapes();

    void setVillesEtapes(Set villesEtapes);

    @OneToMany
    @JoinTable(name = "TRAVELLERS")
    List<Person> getTravellers();

    void setTravellers(List<Person> travellers);

    @ManyToOne
    Person getOwner();

    @ManyToOne
    Ville getArrivee();

    void setArrivee(Ville arrivee);

    @ManyToOne
    Ville getDepart();

    void setDepart(Ville depart);

    void setOwner(Person owner);

}
