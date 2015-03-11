package fr.istic.m2gla.shared.IModele;

import fr.istic.m2gla.shared.Person;

import javax.persistence.ManyToOne;

/**
 * Created by mds on 08/02/15.
 */
public interface IAvis {
    String getCommentaire();

    void setCommentaire(String commentaire);

    int getNote();

    void setNote(int note);

    @ManyToOne
    Person getPerson();

    void setPerson(Person person);
}
