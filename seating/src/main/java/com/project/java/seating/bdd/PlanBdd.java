package com.project.java.seating.bdd;

import java.util.List;

import com.project.java.seating.model.Plan;
import com.project.java.seating.persistence.ProjectEntityManager;

public class PlanBdd {

	private ProjectEntityManager projectEntityManager;

	public PlanBdd() {
	}

	public void setProjectEntityManager(ProjectEntityManager projectEntityManager) {
		this.projectEntityManager = projectEntityManager;
	}

	public List<Plan> getAll() {

		projectEntityManager.ouvertureEntity();

		List plans = projectEntityManager.getSessionFactory().getCurrentSession().createQuery("from Plan").list();

		projectEntityManager.fermetureEntity();
		return plans;
	}

	public List<Plan> get(String batiment) {

		projectEntityManager.ouvertureEntity();

		List<Plan> plans = projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("SELECT p FROM Batiment b INNER JOIN b.planList p WHERE b.nomBatiment=:batiment")
				.setParameter("batiment", batiment).list();
		projectEntityManager.fermetureEntity();

		return plans;
	}

	public void create(String nom, float hauteur, float largeur) {
		projectEntityManager.ouvertureEntity();

		Plan plan = new Plan();
		plan.setNom(nom);
		plan.setHauteur(hauteur);
		plan.setLargeur(largeur);

		projectEntityManager.getSessionFactory().getCurrentSession().save(plan);

		projectEntityManager.fermetureEntity();
	}

	public void update(int id, String nom, float hauteur, float largeur) {
		projectEntityManager.ouvertureEntity();

		List<Plan> plans = projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("from Plan WHERE id=:id").setParameter("id", id).list();
		Plan plan = (Plan) plans.get(0);
		plan.setNom(nom);
		plan.setHauteur(hauteur);
		plan.setLargeur(largeur);

		projectEntityManager.getSessionFactory().getCurrentSession().merge(plan);

		projectEntityManager.fermetureEntity();
	}
}
