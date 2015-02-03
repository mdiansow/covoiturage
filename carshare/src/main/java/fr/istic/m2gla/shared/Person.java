package fr.istic.m2gla.shared;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 * @generated
 */
@Entity
public class Person {
    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    private String nom;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    private String prenom;

    @Column(unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    private String email;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    private long id;

    @ManyToOne
    public Ville getAddress() {
        return address;
    }

    public void setAddress(Ville address) {
        this.address = address;
    }

    private Ville address;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    /**
     * <!-- begin-user-doc -->
     * <p/>
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    private Voiture voiture;

    // @OneToMany(mappedBy = "personnes",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
/*
    @JoinTable(name = "Prefs&Personnes")
*/
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "m2gla_person_pref",
            joinColumns = {@JoinColumn(name = "personne_id")},
            inverseJoinColumns = @JoinColumn(name = "preference_id")
    )
    public List<Preference> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Preference> preferences) {
        this.preferences = preferences;
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    private List<Preference> preferences = new ArrayList<Preference>();

//    @ManyToMany
//    public List<Event> getMyTravels() {
//        return myTravels;
//    }
//
//    public void setMyTravels(List<Event> myTravels) {
//        this.myTravels = myTravels;
//    }

    /**
     * The travels during which I am the driver
     *
     * @generated
     * @ordered
     */

    private List<Event> myEvents;

    @OneToMany(mappedBy = "owner")
    public List<Event> getMyEvents() {
        return myEvents;
    }

    public void setMyEvents(List<Event> myEvents) {
        this.myEvents = myEvents;
    }

    /**
     * The events during witch I am a traveller
     */
    private List<Event> myTravels;

    @OneToMany
    public List<Event> getMyTravels() {
        return myTravels;
    }

    public void setMyTravels(List<Event> myTravels) {
        this.myTravels = myTravels;
    }

    /**
     *
     */
    private List<Avis> mesAvis = new ArrayList<Avis>();

    @OneToMany(mappedBy = "person")
    public List<Avis> getMesAvis() {
        return mesAvis;
    }

    public void setMesAvis(List<Avis> mesAvis) {
        this.mesAvis = mesAvis;
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     */
    public Person() {
        super();
    }

    public void addEvent(Event event) {
        this.myEvents.add(event);
        if (event.getOwner() != this) {
            event.setOwner(this);
        }
    }
}

