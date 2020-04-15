package dev.entite;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ingredient")
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nom") // pas utile car mm nom que dans la table
	private String nom;

	@ManyToMany
	@JoinTable(name = "plat_ingredient", joinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "plat_id", referencedColumnName = "id"))
	private List<Plat> plats;

	
	
	/**Constructeur
	 * 
	 */
	public Ingredient() {
		super();
	}

	/**
	 * Constructeur
	 * 
	 * @param id
	 * @param nom
	 */
	public Ingredient(Integer id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	/**
	 * Constructeur
	 * 
	 * @param id
	 * @param nom
	 * @param plats
	 */
	public Ingredient(Integer id, String nom, List<Plat> plats) {
		super();
		this.id = id;
		this.nom = nom;
		this.plats = plats;
	}

	/**
	 * Getter
	 * 
	 * @return the plats
	 */
	public List<Plat> getPlats() {
		return plats;
	}

	/**
	 * Setter
	 * 
	 * @param plats the plats to set
	 */
	public void setPlats(List<Plat> plats) {
		this.plats = plats;
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Getter
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter
	 * 
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Setter
	 * 
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
}
