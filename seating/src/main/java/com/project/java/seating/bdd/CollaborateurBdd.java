package com.project.java.seating.bdd;

import java.util.List;

import com.project.java.seating.model.Collaborateur;
import com.project.java.seating.model.CollaborateurAncien;
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
		createRecordOfExitCollab(id);
		Collaborateur collab = get(id);
		projectEntityManager.ouvertureEntity();
		projectEntityManager.getSessionFactory().getCurrentSession().delete(collab);
		projectEntityManager.fermetureEntity();
	}

	private void createRecordOfExitCollab(String id) {
		CollaborateurAncien collabAncien = new CollaborateurAncien(get(id));

		projectEntityManager.ouvertureEntity();

		projectEntityManager.getSessionFactory().getCurrentSession().save(collabAncien);
		projectEntityManager.fermetureEntity();
	}

	private Collaborateur get(String id) {
		projectEntityManager.ouvertureEntity();

		List collaborateurs = projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("from Collaborateur WHERE id=:id").setParameter("id", Long.parseLong(id)).list();

		projectEntityManager.fermetureEntity();

		return (Collaborateur) collaborateurs.get(0);
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

	public List<Collaborateur> getAll() {
		projectEntityManager.ouvertureEntity();

		List collaborateurs = projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("from Collaborateur").list();

		projectEntityManager.fermetureEntity();

		return collaborateurs;
	}

}
