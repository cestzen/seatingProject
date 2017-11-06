package com.project.java.seating.services;

import java.util.Date;

import com.project.java.seating.model.Collaborateur;
import com.project.java.seating.persistence.ProjectEntityManager;

public class AddCollaborateurService {

	public void addCollaborateur(String nom, String prenom) {
		ProjectEntityManager projectEntityManager = new ProjectEntityManager();
		Collaborateur collaborateur = new Collaborateur();
		collaborateur.setNom(nom);
		collaborateur.setPrenom(prenom);
		collaborateur.setEstAdministrateur(true);
		collaborateur.setDateArriver(new Date().toString()); 
		
		projectEntityManager.getSessionFactory().getCurrentSession().beginTransaction();
		projectEntityManager.getSessionFactory().getCurrentSession().save(collaborateur);
		projectEntityManager.getSessionFactory().getCurrentSession().getTransaction().commit();
		projectEntityManager.getSessionFactory().getCurrentSession().close();
		
	}		
}
