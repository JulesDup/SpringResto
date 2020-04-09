package dev.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.dao.IPlatDao;
import dev.dao.PlatDaoMemoire;
import dev.entite.Plat;
import dev.exception.PlatException;

public class PlatServiceVersion1Test {

	private PlatServiceVersion1 platServiceVersion1;
	private IPlatDao dao;

	@BeforeEach
	void setUp() {
		this.dao = mock(IPlatDao.class);
		this.platServiceVersion1 = new PlatServiceVersion1(dao);

	}

	@Test
	void TestAjouterPlatNomInvalide() {

		assertThatThrownBy(() -> {
			platServiceVersion1.ajouterPlat("f", 1800);
		}).hasMessage("un plat doit avoir un nom de plus de 3 caractères");

	}

	@Test
	void TestAjouterPlatPrixInvalide() {

		assertThatThrownBy(() -> {
			platServiceVersion1.ajouterPlat("falafel", 10);
		})
		.isInstanceOf(PlatException.class); 
//		.hasMessage("le prix d'un plat doit être supérieur à 5 €");

	}

	@Test
	void testAjouterPlatCasPassant() {
		platServiceVersion1.ajouterPlat("falafel", 1800);
		verify(dao).ajouterPlat("falafel", 1800);
	}

	@Test
	void TestListerPlats() {
		Plat plat1 = new Plat("Falafel", 1800);
		List<Plat> listPlat = new ArrayList<Plat>();
		listPlat.add(plat1);

		when(dao.listerPlats()).thenReturn(listPlat);
		assertThat(platServiceVersion1.listerPlats()).isEqualTo(listPlat);
	}
}
