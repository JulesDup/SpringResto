package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.entite.Plat;
import dev.service.PlatServiceVersion2;

//Creation du context
@SpringJUnitConfig(classes = PlatDaoFichier.class)
@TestPropertySource("classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatDaoFichierTest {
	@Autowired
	private IPlatDao dao;

	@Test
	void TestAjouterPlatCasPassant() {
		Plat plat1 = new Plat("falafel", 1800);
		List<Plat> listPlat = new ArrayList<Plat>();
		listPlat.add(plat1);
		dao.ajouterPlat("falafel", 1800);
		assertThat(dao.listerPlats()).isEqualTo(listPlat);
	}

	/**
	 * Permet de tester indépendant des test réalisé grace a
	 * l'annotation @DirtiesContext
	 * 
	 */
	@Test
	void TestAjouterPlatIndépendant() {
		Plat plat1 = new Plat("falafel1", 1800);
		List<Plat> listPlat = new ArrayList<Plat>();
		listPlat.add(plat1);
		dao.ajouterPlat("falafel1", 1800);
		assertThat(dao.listerPlats()).isEqualTo(listPlat);
	}
}