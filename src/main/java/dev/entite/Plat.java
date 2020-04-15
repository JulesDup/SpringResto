package dev.entite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "plat")
public class Plat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nom") // pas utile car mm nom que dans la table
	private String nom;
	@Column(name = "prix")
	private Integer prixEnCentimesEuros;

	@ManyToMany(mappedBy = "plats")
	private List<Ingredient> ingredients = new ArrayList<>();

	public Plat() {
	}

	public Plat(String nom, Integer prixEnCentimesEuros) {
		this.nom = nom;
		this.prixEnCentimesEuros = prixEnCentimesEuros;
	}

	/**
	 * Constructeur
	 * 
	 * @param id
	 * @param nom
	 * @param prixEnCentimesEuros
	 * @param ingrediens
	 */
	public Plat(Integer id, String nom, Integer prixEnCentimesEuros, List<Ingredient> ingrediens) {
		super();
		this.id = id;
		this.nom = nom;
		this.prixEnCentimesEuros = prixEnCentimesEuros;
		this.ingredients = ingrediens;
	}

	/**
	 * Getter
	 * 
	 * @return the ingrediens
	 */
	public List<Ingredient> getIngrediens() {
		return ingredients;
	}

	/**
	 * Setter
	 * 
	 * @param ingrediens the ingrediens to set
	 */
	public void setIngrediens(List<Ingredient> ingrediens) {
		this.ingredients = ingrediens;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getPrixEnCentimesEuros() {
		return prixEnCentimesEuros;
	}

	public void setPrixEnCentimesEuros(Integer prixEnCentimesEuros) {
		this.prixEnCentimesEuros = prixEnCentimesEuros;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prixEnCentimesEuros == null) ? 0 : prixEnCentimesEuros.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plat other = (Plat) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prixEnCentimesEuros == null) {
			if (other.prixEnCentimesEuros != null)
				return false;
		} else if (!prixEnCentimesEuros.equals(other.prixEnCentimesEuros))
			return false;
		return true;
	}

}
