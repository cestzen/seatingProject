package com.project.java.seating.bdd;

import java.util.List;

import com.project.java.seating.model.Batiment;
import com.project.java.seating.persistence.ProjectEntityManager;

public class BatimentBdd {

	ProjectEntityManager projectEntityManager;

	public BatimentBdd() {
	}

	public void setProjectEntityManager(ProjectEntityManager projectEntityManager) {
		this.projectEntityManager = projectEntityManager;
	}

	public List<Batiment> getAll() {

		projectEntityManager.ouvertureEntity();

		List batiments = projectEntityManager.getSessionFactory().getCurrentSession().createQuery("from Batiment")
				.list();

		projectEntityManager.fermetureEntity();
		return batiments;
	}

	public Batiment get(int id) {

		projectEntityManager.ouvertureEntity();

		List batiments = projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("from Batiment WHERE id=:id").setParameter("id", id).list();
		Batiment batiment = (Batiment) batiments.get(0);
		projectEntityManager.fermetureEntity();

		return batiment;
	}

	public void create(String nom) {
		projectEntityManager.ouvertureEntity();

		Batiment batiment = new Batiment();
		batiment.setNomBatiment(nom);

		projectEntityManager.getSessionFactory().getCurrentSession().save(batiment);

		projectEntityManager.fermetureEntity();
	}

	public void update(int id, String nom) {
		projectEntityManager.ouvertureEntity();

		List batiments = projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("from Batiment WHERE id=:id").setParameter("id", id).list();
		Batiment batiment = (Batiment) batiments.get(0);
		batiment.setNomBatiment(nom);

		projectEntityManager.getSessionFactory().getCurrentSession().merge(batiment);

		projectEntityManager.fermetureEntity();
	}

	public void create(Batiment batiment) {
		projectEntityManager.ouvertureEntity();
		projectEntityManager.getSessionFactory().getCurrentSession().save(batiment);
		projectEntityManager.fermetureEntity();

	}

	public Batiment get(String nomBatiment) {
		projectEntityManager.ouvertureEntity();

		List batiments = projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("from Batiment WHERE nomBatiment=:nomBatiment").setParameter("nomBatiment", nomBatiment)
				.list();

		projectEntityManager.fermetureEntity();
		return (Batiment) batiments.get(0);

	}
	
	public Batiment save(Batiment batiment) {
		projectEntityManager.ouvertureEntity();

		Batiment bat = (Batiment) projectEntityManager.getSessionFactory().getCurrentSession().merge(batiment);

		projectEntityManager.fermetureEntity();
		
		return bat;

	}
}
