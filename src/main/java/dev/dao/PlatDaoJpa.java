package dev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.entite.Plat;

@Repository
@Profile("PlatDaoJpa")
public class PlatDaoJpa implements IPlatDao {

//	private EntityManagerFactory emf;

//	@PersistenceUnit
//	public void setEntityManagerFactory(EntityManagerFactory emf) {
//		this.emf = emf;
//	}

	// on peut laisser la gestion de l'entity Factory a Spring avec l'annotation sur
	// le setter suivant
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Plat> listerPlats() {
//		EntityManager em = this.emf.createEntityManager();
		TypedQuery<Plat> query = em.createQuery("select p from Plat p", Plat.class); /// ICI JPQL PAS DU SQL , Notion
																						/// OBJET!!!
//		List<Plat> listePlats = query.getResultList();
//		em.close();
//		return listePlats;

		return query.getResultList();
	}

	@Override
	@Transactional // permet de gere la methode bigin et commit de l'entity manager
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		Plat plat = new Plat(nomPlat, prixPlat);
		em.persist(plat);

	}

}
