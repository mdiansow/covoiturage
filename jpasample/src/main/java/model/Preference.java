package model;


import javax.persistence.*;
import java.util.Set;

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

    @ManyToOne
    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    private Personne personne;

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

