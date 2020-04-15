package dev.repository;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.SpringDataConfigTest;
import dev.entite.Plat;

@SpringJUnitConfig(classes = { SpringDataConfigTest.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@ActiveProfiles("PlatDaoJpa")
public class PlatRepositoryIntegrationTest {

	@Autowired
	PlatRepository repository;

	@Test
	void testFindAll() {
		assertThat(repository.findAll()).isNotEmpty();

	}

	@Test
	void testFindAllSortAsc() {
		Sort triAsc = Sort.sort(Plat.class).by(Plat::getPrixEnCentimesEuros).ascending();
		assertThat(repository.findAll(triAsc)).isNotEmpty();
		assertThat(repository.findAll(triAsc).get(0).getNom()).isEqualTo("Côte de boeuf");
		assertThat(repository.findAll(triAsc).get(5).getNom()).isEqualTo("Gigot d'agneau");
	}

	@Test
	void testFindAllSortDsc() {
		Sort triDesc = Sort.sort(Plat.class).by(Plat::getPrixEnCentimesEuros).descending();
		assertThat(repository.findAll(triDesc)).isNotEmpty();
		assertThat(repository.findAll(triDesc).get(0).getNom()).isEqualTo("Gigot d'agneau");
		assertThat(repository.findAll(triDesc).get(5).getNom()).isEqualTo("Côte de boeuf");
	}

	@Test
	void testFindAllPageable() {
		Sort triAsc = Sort.sort(Plat.class).by(Plat::getNom).ascending();
		Pageable pageable = PageRequest.of(0, 2, triAsc);
		assertThat(repository.findAll(pageable).toList().get(0).getNom()).isEqualTo("Blanquette de veau");
		assertThat(repository.findAll(pageable).toList().get(1).getNom()).isEqualTo("Couscous");
		assertThat(repository.findAll(pageable).toList().size()).isEqualTo(2);
	}

	@Test
	void testFindById() {
		assertThat(repository.findById(3).get().getNom()).isEqualTo("Couscous");
	}

	/**
	 * Comprend A quoi sert le get one
	 */
	@Test
	@Transactional
	void testGetOne() {
		assertThat(repository.getOne(3).getNom()).isEqualTo("Couscous");
	}

	@Test
	void testCount() {
		assertThat(repository.count()).isEqualTo(6);
	}

	@Test
	void testFindByPrix() {
//		assertThat(repository.findByPrixEnCentimesEuros(1000)).isEmpty();
		assertThat(repository.findByPrixEnCentimesEuros(1300).get(0).getNom()).isEqualTo("Magret de canard");
		assertThat(repository.findByPrixEnCentimesEuros(1300).get(1).getNom()).isEqualTo("Moules-frites");
	}

	@Test
	void testAvgPrix() {
		assertThat(repository.avgPrix(1000)).isEqualTo(1550d);
	}

	@Test
	void testFindByNomWithIngredients() {
		assertThat(repository.findByNomWithIngredients("Moules-frites")).hasSize(6);
	}

	@Test
	void testSave() {
		repository.save(new Plat("PalakPaneer", 2000));
		assertThat(repository.findAll()).contains(new Plat("PalakPaneer", 2000));
	}

	@Test
	void testChangerNom() {
		repository.changerNom("Côte de boeuf", "Falafel");
		assertThat(repository.findAll()).contains(new Plat("Falafel", 1100));
	}

}