package fr.istic.m2gla.shared;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 * @generated
 */
@Entity
public class Event {
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public LinkedHashSet<Ville> getVilles() {
        return villes;
    }

    public void setVilles(LinkedHashSet<Ville> villes) {
        this.villes = villes;
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

    private String lieu;

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

    private LinkedHashSet<Ville> villes;

    /**
     * The travellers of the event
     */
    private List<Person> travellers = new ArrayList<>();

    @OneToMany
    @JoinTable(name = "TRAVELLERS")
    public List<Person> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Person> travellers) {
        this.travellers = travellers;
    }

    @JsonIgnore
    private Person owner;

    @ManyToOne
    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
//        if (!owner.getMyEvents().contains(this)) {
//            owner.addEvent(this);
//        }
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

