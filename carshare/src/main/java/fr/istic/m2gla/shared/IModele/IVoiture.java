package fr.istic.m2gla.shared.IModele;

import fr.istic.m2gla.shared.Person;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by mds on 12/03/15.
 * Class ${CLASS}
 */
public interface IVoiture {
    String getCouleur();

    void setCouleur(String couleur);

    String getMarque();

    void setMarque(String marque);

    String getModel();

    void setModel(String model);

    String getConfort();

    void setConfort(String confort);

    int getNbPlaces();

    void setNbPlaces(int nbPlaces);

    String getCategorie();

    void setCategorie(String categorie);

    void setId(long id);

    @Id
    @GeneratedValue
    long getId();

    @OneToOne(mappedBy = "voiture")
    @JsonIgnore
    Person getPerson();

    void setPerson(Person person);
}
