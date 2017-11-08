package com.project.java.seating.bdd;

import com.project.java.seating.model.Collaborateur;
import com.project.java.seating.persistence.ProjectEntityManager;

public class CollaborateurBdd {

	private ProjectEntityManager projectEntityManager;

	public CollaborateurBdd() {
	}

	public void setProjectEntityManager(ProjectEntityManager projectEntityManager) {
		this.projectEntityManager = projectEntityManager;
	}

	public Collaborateur findCollaborateur(String userName, String password) {
		projectEntityManager.ouvertureEntity();
		Collaborateur collab = (Collaborateur) projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("FROM Collaborateur WHERE nomUtilisateur=:userName AND motDePasse=:password")
				.setParameter("userName", userName).setParameter("password", password).uniqueResult();
		projectEntityManager.fermetureEntity();
		return collab;
	}

	public void deleteCollaborateur(String id) {
		projectEntityManager.ouvertureEntity();

		projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("DELETE FROM Collaborateur WHERE id=:id").setParameter("id", id);

		projectEntityManager.fermetureEntity();
	}

	public void create(String nom_collaborateur, String prenom_collaborateur, Boolean estAdministrateur,
			String dateArriver, String username, String password) {
		projectEntityManager.ouvertureEntity();

		Collaborateur collaborateur = new Collaborateur();
		collaborateur.setNom(nom_collaborateur);
		collaborateur.setPrenom(prenom_collaborateur);
		collaborateur.setEstAdministrateur(estAdministrateur);
		collaborateur.setDateArriver(dateArriver);
		collaborateur.setNomUtilisateur(username);
		collaborateur.setMotDePasse(password);

		projectEntityManager.getSessionFactory().getCurrentSession().save(collaborateur);

		projectEntityManager.fermetureEntity();
	}

}
