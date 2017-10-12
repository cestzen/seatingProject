package com.project.java.seating.services;

import java.util.Date;

import org.hibernate.jpa.criteria.expression.function.CurrentDateFunction;

import com.project.java.seating.model.Collaborateur;
import com.project.java.seating.persistence.ProjectEntityManager;

public class AddCollaborateurService {

	public void addCollaborateur(String Nom, String Prenom) {
		ProjectEntityManager projectEntityManager = new ProjectEntityManager();
		Collaborateur collaborateur = new Collaborateur();
		collaborateur.setNom_collaborateur("Baykut");
		collaborateur.setPrenom_collaborateur("Beril");
		collaborateur.setEstAdministrateur(true);
		collaborateur.setDateArriver(new Date().toString()); 
		
	}		
}
