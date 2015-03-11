package fr.istic.m2gla.shared;


import fr.istic.m2gla.shared.IModele.IAvis;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 * @generated
 */
@Entity
public class Avis implements IAvis {
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getCommentaire() {
        return commentaire;
    }

    @Override
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public int getNote() {
        return note;
    }

    @Override
    public void setNote(int note) {
        this.note = note;
    }

    /**
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

    private String commentaire;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    private int note;

    /**
     *
     */
    private Person person;

    /**
     *
     * @return
     */
    @Override
    @ManyToOne
    public Person getPerson() {
        return person;
    }

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     */
    public Avis() {
        super();
    }

}

