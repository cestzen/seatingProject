package com.project.java.seating.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Collaborateur {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String nom_collaborateur;
	private String prenom_collaborateur;
	private Boolean estAdministrateur;
	private String dateArriver;
	
	public Collaborateur() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom_collaborateur() {
		return nom_collaborateur;
	}

	public void setNom_collaborateur(String nom_collaborateur) {
		this.nom_collaborateur = nom_collaborateur;
	}

	public String getPrenom_collaborateur() {
		return prenom_collaborateur;
	}

	public void setPrenom_collaborateur(String prenom_collaborateur) {
		this.prenom_collaborateur = prenom_collaborateur;
	}

	public Boolean getEstAdministrateur() {
		return estAdministrateur;
	}

	public void setEstAdministrateur(Boolean estAdministrateur) {
		this.estAdministrateur = estAdministrateur;
	}

	public String getDateArriver() {
		return dateArriver;
	}

	public void setDateArriver(String dateArriver) {
		this.dateArriver = dateArriver;
	}
	
}
