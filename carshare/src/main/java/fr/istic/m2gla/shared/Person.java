package fr.istic.m2gla.shared;

import fr.istic.m2gla.shared.IModele.IPerson;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 * @generated
 */
@Entity
public class Person implements IPerson {
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
    private String civilite;
    private Date dateDeNaissance;

    @Override
    @Column(unique = true)
    public String getEmail() {
        return email;
    }

    @Override
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

    @Column(unique = true)
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    private String username;

    private String password;

    @Override
    @ManyToOne
    public Ville getAddress() {
        return address;
    }

    @Override
    public void setAddress(Ville address) {
        this.address = address;
    }

    private Ville address;

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String getPrenom() {
        return prenom;
    }

    @Override
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String getCivilite() {
        return civilite;
    }

    @Override
    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    @Override
    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    @Override
    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    @OneToOne(cascade = CascadeType.ALL)
    public Voiture getVoiture() {
        return voiture;
    }

    @Override
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
    @Override
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "m2gla_person_pref",
            joinColumns = {@JoinColumn(name = "personne_id")},
            inverseJoinColumns = @JoinColumn(name = "preference_id")
    )
    public List<Preference> getPreferences() {
        return preferences;
    }

    @Override
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

    @Override
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    public List<Event> getMyEvents() {
        return myEvents;
    }

    @Override
    public void setMyEvents(List<Event> myEvents) {
        this.myEvents = myEvents;
    }
//
//    @ManyToOne
//    @Override
//    public Event getTravel() {
//        return travel;
//    }
//
//    @Override
//    public void setTravel(Event travel) {
//        this.travel = travel;
//    }
//
//    /**
//     * The events during witch I am a traveller
//     */
//    private Event travel;

    /**
     *
     */
    private List<Avis> mesAvis = new ArrayList<Avis>();

    @Override
    @OneToMany(mappedBy = "person")
    public List<Avis> getMesAvis() {
        return mesAvis;
    }

    @Override
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

