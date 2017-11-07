package com.project.java.seating.bdd;

import java.util.List;
import com.project.java.seating.model.Collaborateur;
import com.project.java.seating.persistence.ProjectEntityManager;

public class CollaborateurBdd {

	private ProjectEntityManager projectEntityManager;

	public CollaborateurBdd() {
	}

	public void setProjectEntityManager(ProjectEntityManager projectEntityManager) {
		this.projectEntityManager = projectEntityManager;
	}

	public List<Collaborateur> getAll() {

		projectEntityManager.ouvertureEntity();

		List<Collaborateur> collaborateurs = projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("from Collaborateur").list();

		projectEntityManager.fermetureEntity();
		return collaborateurs;
	}

	public void deleteCollaborateur(String id) {
		projectEntityManager.ouvertureEntity();

		projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("DELETE FROM Collaborateur WHERE id=:id").setParameter("id", id);

		projectEntityManager.fermetureEntity();
	}

	public Collaborateur get(int id) {

		projectEntityManager.ouvertureEntity();

		List collaborateurs = projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("from Collaborateur WHERE id=:id").setParameter("id", id).list();
		Collaborateur collaborateur = (Collaborateur) collaborateurs.get(0);

		projectEntityManager.fermetureEntity();

		return collaborateur;
	}

	public void create(String nom_collaborateur, String prenom_collaborateur, Boolean estAdministrateur,
			String dateArriver) {
		projectEntityManager.ouvertureEntity();

		Collaborateur collaborateur = new Collaborateur();
		collaborateur.setNom(nom_collaborateur);
		collaborateur.setPrenom(prenom_collaborateur);
		collaborateur.setEstAdministrateur(estAdministrateur);
		collaborateur.setDateArriver(dateArriver);

		projectEntityManager.getSessionFactory().getCurrentSession().save(collaborateur);

		projectEntityManager.fermetureEntity();
	}

	public void update(int id, String nom_collaborateur, Boolean nomModification, String prenom_collaborateur,
			Boolean prenomModification, Boolean estAdministrateur, Boolean estAdministrateurModification,
			String dateArriver, Boolean dateArriverModification) {
		projectEntityManager.ouvertureEntity();

		List<Collaborateur> collaborateurs = projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("from Collaborateur WHERE id=:id").setParameter("id", id).list();
		Collaborateur collaborateur = (Collaborateur) collaborateurs.get(0);
		if (nomModification)
			collaborateur.setNom(nom_collaborateur);
		if (prenomModification)
			collaborateur.setPrenom(prenom_collaborateur);
		if (estAdministrateurModification)
			collaborateur.setEstAdministrateur(estAdministrateur);
		if (dateArriverModification)
			collaborateur.setDateArriver(dateArriver);

		projectEntityManager.getSessionFactory().getCurrentSession().merge(collaborateur);

		projectEntityManager.fermetureEntity();
	}

}
