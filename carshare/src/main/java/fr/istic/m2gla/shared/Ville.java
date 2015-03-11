package fr.istic.m2gla.shared;


import fr.istic.m2gla.shared.IModele.IVille;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 *
 * @generated
 */
@Entity
public class Ville implements Serializable, IVille {

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

    private String nom;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     * @ordered
     */

    private String cp;

    /**
     * <!-- begin-user-doc -->
     * <!--  end-user-doc  -->
     *
     * @generated
     */
    public Ville() {
        super();
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
    public String getNom() {
        return nom;
    }

    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String getCp() {
        return cp;
    }

    @Override
    public void setCp(String cp) {
        this.cp = cp;
    }

}

