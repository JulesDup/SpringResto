package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JdbcTestConfig;

@SpringJUnitConfig(classes = { JdbcTestConfig.class, PlatDaoJdbc.class })
@ActiveProfiles("PlatDaoJdbc")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatDaoJdbcIntegrationTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private PlatDaoJdbc PlatDaoJdbc;

	@Test
	void listerPlatsNonVide() {
		assertThat(PlatDaoJdbc.listerPlats()).isNotEmpty();
	}

	@Test
	void ajouterPlat() {

		PlatDaoJdbc.ajouterPlat("falafel", 1800);
		assertThat(jdbcTemplate.queryForObject("select nom from plat where nom='falafel'", String.class))
				.isEqualTo("falafel");
	}
}
