package fr.istic.m2gla.shared;

import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by mds on 08/02/15.
 */
public interface IEvent extends Serializable {
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

    void addTraveller(Person trav);
}
