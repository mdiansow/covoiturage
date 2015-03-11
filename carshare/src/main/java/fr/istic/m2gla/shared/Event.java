package fr.istic.m2gla.shared;

import fr.istic.m2gla.shared.IModele.IEvent;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.*;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 * @generated
 */
@Entity
public class Event implements IEvent {
    @Override
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public int getPrix() {
        return prix;
    }

    @Override
    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "VILLES_ETAPES")
    public Set<Ville> getVillesEtapes() {
        return villesEtapes;
    }

    @Override
    public void setVillesEtapes(Set villesEtapes) {
        this.villesEtapes = villesEtapes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    private long id;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    private Date date;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    private Ville depart;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    private Ville arrivee;


    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    private int prix;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */
    @JsonIgnore
    private Set<Ville> villesEtapes = new LinkedHashSet<>();

    /**
     * The travellers of the event
     */
    @JsonIgnore
    private List<Person> travellers = new ArrayList<>();

    @Override
    @ManyToMany
    @JoinTable(name = "TRAVELLERS")
    public List<Person> getTravellers() {
        return travellers;
    }

    @Override
    public void setTravellers(List<Person> travellers) {
        this.travellers = travellers;
        System.out.println("EVENT\t"+travellers.size());
    }

    @JsonIgnore
    private Person owner;

    @Override
    @ManyToOne
    public Person getOwner() {
        return owner;
    }

    @Override
    @ManyToOne
    public Ville getArrivee() {
        return arrivee;
    }

    @Override
    public void setArrivee(Ville arrivee) {
        this.arrivee = arrivee;
    }

    @Override
    @ManyToOne
    public Ville getDepart() {
        return depart;
    }

    @Override
    public void setDepart(Ville depart) {
        this.depart = depart;
    }

    @Override
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     */
    public Event() {
        super();
    }

    public void addTraveller(Person trav) {
        if (!this.getTravellers().contains(trav)) {
            this.travellers.add(trav);
        }
    }


}

