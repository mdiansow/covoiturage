package fr.istic.m2gla.shared;


import fr.istic.m2gla.shared.IModele.IPreference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
@Entity
public class Preference implements IPreference {
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated

	 * @ordered
	 */
	
	private long id;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String nomPref;

    private String valPref;

   /* @OneToMany
    public List<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }*/

   //private List<Personne> personnes;

    @Override
    public String getNomPref() {
        return nomPref;
    }

    @Override
    public String getValPref() {
        return valPref;
    }

    @Override
    public void setValPref(String valPref) {
        this.valPref = valPref;
    }

    @Override
    public void setNomPref(String nomPref) {
        this.nomPref = nomPref;
    }

    /**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Preference(){
		super();
	}

}

