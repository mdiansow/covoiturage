package fr.istic.m2gla.shared;


import javax.persistence.*;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
@Entity(name = "m2gla_preference")
public class Preference
{
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

    public String getNomPref() {
        return nomPref;
    }

    public String getValPref() {
        return valPref;
    }

    public void setValPref(String valPref) {
        this.valPref = valPref;
    }

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

