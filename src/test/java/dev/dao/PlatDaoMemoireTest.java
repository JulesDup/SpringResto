package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.service.PlatServiceVersion1;

public class PlatDaoMemoireTest {

	private PlatDaoMemoire platDaoMemoire;

	@BeforeEach
	void setUp() {
		this.platDaoMemoire = new PlatDaoMemoire();
	}

	@Test
	void listerPlatsVideALInitialisation() {
		assertThat(platDaoMemoire.listerPlats()).isEmpty();
	}

	@Test
	void ajouterPlatCasPassants() {

		platDaoMemoire.ajouterPlat("falafel", 1800);
		assertThat(platDaoMemoire.listerPlats()).isNotEmpty();
	}

}
