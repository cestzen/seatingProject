package com.project.java.seating.bdd;

import java.util.List;

import com.project.java.seating.model.Bureau;
import com.project.java.seating.model.Collaborateur;
import com.project.java.seating.model.Plan;
import com.project.java.seating.persistence.ProjectEntityManager;

/**
 * A DAO class that handles operations concerning the office desks
 * 
 * @author beril
 *
 */
public class BureauBdd {
	private ProjectEntityManager projectEntityManager;
	private CollaborateurBdd collaborateurBdd;

	public BureauBdd() {

	}

	public void setProjectEntityManager(ProjectEntityManager projectEntityManager) {
		this.projectEntityManager = projectEntityManager;
	}

	public void setCollaborateurBdd(CollaborateurBdd collaborateurBdd) {
		this.collaborateurBdd = collaborateurBdd;
	}

	public List<Bureau> getAll() {

		projectEntityManager.ouvertureEntity();

		List bureaux = projectEntityManager.getSessionFactory().getCurrentSession().createQuery("from Bureau").list();

		projectEntityManager.fermetureEntity();
		return bureaux;
	}

	public Bureau get(int id) {

		projectEntityManager.ouvertureEntity();

		List bureaux = projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("from Bureau WHERE id=:id").setParameter("id", id).list();
		Bureau bureau = (Bureau) bureaux.get(0);

		projectEntityManager.fermetureEntity();

		return bureau;
	}

	public void create(String nom, float x, float y) {
		projectEntityManager.ouvertureEntity();

		Bureau Bureau = new Bureau();
		Bureau.setNom(nom);
		Bureau.setX(x);
		Bureau.setY(y);

		projectEntityManager.getSessionFactory().getCurrentSession().save(Bureau);

		projectEntityManager.fermetureEntity();
	}

	public void update(int id, Boolean nomModifier, String nom, Boolean xModifier, float x, Boolean yModifier, float y,
			Boolean directionModifier, String direction, Boolean idPlanModifier, Boolean idCollaborateurModifier,
			Collaborateur idCollaborateur) {
		projectEntityManager.ouvertureEntity();

		List bureaux = projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("from Bureau WHERE id=:id").setParameter("id", id).list();
		Bureau bureau = (Bureau) bureaux.get(0);
		if (nomModifier)
			bureau.setNom(nom);
		if (xModifier)
			bureau.setX(x);
		if (yModifier)
			bureau.setY(y);
		if (idCollaborateurModifier)
			bureau.setCollaborateur(idCollaborateur);

		projectEntityManager.getSessionFactory().getCurrentSession().merge(bureau);

		projectEntityManager.fermetureEntity();
	}

	public void create(Bureau bureau) {
		projectEntityManager.ouvertureEntity();
		projectEntityManager.getSessionFactory().getCurrentSession().save(bureau);
		projectEntityManager.fermetureEntity();
	}

	public boolean findBureau(Bureau bureau) {
		projectEntityManager.ouvertureEntity();
		List bureaux = projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("from Bureau WHERE nom=:nom AND x=:x AND y=:y").setParameter("nom", bureau.getNom())
				.setParameter("x", bureau.getX()).setParameter("y", bureau.getY()).list();
		projectEntityManager.fermetureEntity();

		return bureaux.isEmpty();
	}

	public void update(Bureau bureau, String collab) {
		Collaborateur collaborateur = collaborateurBdd.findCollaborateur(collab);
		projectEntityManager.ouvertureEntity();
		Bureau bureaur = (Bureau) projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("from Bureau WHERE nom=:nom AND x=:x AND y=:y").setParameter("nom", bureau.getNom())
				.setParameter("x", bureau.getX()).setParameter("y", bureau.getY()).list().get(0);
		bureaur.setNom(bureau.getNom());
		bureaur.setCollaborateur(collaborateur);
		projectEntityManager.getSessionFactory().getCurrentSession().save(bureaur);
		projectEntityManager.fermetureEntity();
	}

}
