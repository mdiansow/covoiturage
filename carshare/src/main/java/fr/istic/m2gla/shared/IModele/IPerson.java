package fr.istic.m2gla.shared.IModele;

import fr.istic.m2gla.shared.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by mds on 09/02/15.
 */
public interface IPerson extends Serializable {
    @Column(unique = true)
    String getEmail();

    void setEmail(String email);

    @Column(unique = true)
    String getUsername();

    void setUsername(String username);

    @ManyToOne
    Ville getAddress();

    void setAddress(Ville address);

    String getNom();

    void setNom(String nom);

    String getPrenom();

    void setPrenom(String prenom);

    String getCivilite();

    void setCivilite(String civilite);

    Date getDateDeNaissance();

    void setDateDeNaissance(Date dateDeNaissance);


    @OneToOne(cascade = CascadeType.ALL)
    Voiture getVoiture();

    void setVoiture(Voiture voiture);

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
    List<Preference> getPreferences();

    void setPreferences(List<Preference> preferences);

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    @LazyCollection(LazyCollectionOption.TRUE)
    List<Event> getMyEvents();

    void setMyEvents(List<Event> myEvents);

    @OneToMany(mappedBy = "person")
    List<Avis> getMesAvis();

    void setMesAvis(List<Avis> mesAvis);

    void setPassword(String password);
}
