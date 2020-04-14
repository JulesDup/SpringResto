package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JdbcTestConfig;
import dev.config.JpaTestConfig;
import dev.entite.Plat;

@SpringJUnitConfig(classes = { JpaTestConfig.class, PlatDaoJpa.class })
@ActiveProfiles("PlatDaoJpa")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatDaoJpaIntegrationTest {

	@Autowired
	IPlatDao dao;

	@Test
	void testLiterPlatsNonVide() {
		assertThat(dao.listerPlats()).isNotEmpty();
	}

	@Test
	void testAjouterPlat() {
		dao.ajouterPlat("PalakPaneer", 2000);
		assertThat(dao.listerPlats()).contains(new Plat("PalakPaneer", 2000));
	}
}
