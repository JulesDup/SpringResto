package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import dev.entite.Ingredient;
import dev.entite.Plat;

public interface PlatRepository extends JpaRepository<Plat, Integer> {

	List<Plat> findByPrixEnCentimesEuros(int prix);

	@Query("select avg(p.prixEnCentimesEuros) from Plat p where prixEnCentimesEuros > :unPrix")
	Double avgPrix(@Param("unPrix") Integer prix);

	@Query("select p.ingredients from Plat p where p.nom = :unNom")
	List<Ingredient> findByNomWithIngredients(@Param("unNom") String nom);

	@Modifying
	@Query("update Plat p set nom = :nouveauNom where nom = :ancienNom ")
	void changerNom(@Param("ancienNom") String ancienNom, @Param("nouveauNom") String nouveauNom);
}
