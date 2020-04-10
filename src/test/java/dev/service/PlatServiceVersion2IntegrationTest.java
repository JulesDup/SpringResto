package dev.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.config.AppConfig;
import dev.dao.IPlatDao;
import dev.dao.PlatDaoMemoire;
import dev.entite.Plat;
import dev.exception.PlatException;

//création du context
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PlatServiceVersion2.class, PlatDaoMemoire.class })
//ou appConfig.class crée trop d'instances
@ActiveProfiles({ "PlatDaoMemoir", "PlatServiceVersion2" })
public class PlatServiceVersion2IntegrationTest {

	// injection du bean a tester
	@Autowired
	private PlatServiceVersion2 platServiceVersion2;

	@Test
	void TestAjouterPlatCasPassant() {
		Plat plat1 = new Plat("falafel", 1800);
		List<Plat> listPlat = new ArrayList<Plat>();
		listPlat.add(plat1);
		platServiceVersion2.ajouterPlat("falafel", 1800);
		assertThat(platServiceVersion2.listerPlats()).isEqualTo(listPlat);
	}

}
