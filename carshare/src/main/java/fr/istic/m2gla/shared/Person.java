package fr.istic.m2gla.shared;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 * @generated
 */
@Entity
public class Person implements Serializable {
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

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    private long id;

    @OneToOne(cascade = CascadeType.ALL)
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

     /*   for(Preference p:this.preferences){
            List<Personne> listPers = p.getPersonnes();
            if(listPers == null)
                listPers = new ArrayList<>();
            listPers.add(this);
            p.setPersonnes(listPers);
        }*/
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    private List<Preference> preferences = new ArrayList<Preference>();

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

//    //private Set<Evenement> evenement;
//    @ManyToMany(cascade = CascadeType.PERSIST)
//    @JoinTable(
//            name = "m2gla_person_Event",
//            joinColumns ={@JoinColumn(name = "personne_id")},
//            inverseJoinColumns = @JoinColumn(name = "evenement_id")
//    )
    @OneToMany
    public List<Event> getMyEvents() {
        return myEvents;
    }

    public void setMyEvents(List<Event> myEvents) {
        this.myEvents = myEvents;
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */
    private List<Event> myEvents = new ArrayList<Event>();

    /**
     *
     */
    private List<Avis> mesAvis = new ArrayList<Avis>();

    @OneToMany(mappedBy = "personne")
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

}

