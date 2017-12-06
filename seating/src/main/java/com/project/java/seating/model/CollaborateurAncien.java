package com.project.java.seating.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CollaborateurAncien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nom;
	private String prenom;
	private String nomUtilisateur;
	private Boolean etaitAdministrateur;
	private Date dateDeSortie;
	private Date dateArriver;

	public CollaborateurAncien() {

	}

	public CollaborateurAncien(Collaborateur colab) {
		this.nom = colab.getNom();
		this.prenom = colab.getPrenom();
		this.nomUtilisateur = colab.getNomUtilisateur();
		this.etaitAdministrateur = colab.getEstAdministrateur();
		this.dateDeSortie = new Date();
		this.dateArriver = colab.getDateArriver();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateArriver() {
		return dateArriver;
	}

	public void setDateArriver(Date dateArriver) {
		this.dateArriver = dateArriver;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public Date getDateDeSortie() {
		return dateDeSortie;
	}

	public void setDateDeSortie(Date dateDeSortie) {
		this.dateDeSortie = dateDeSortie;
	}

	public Boolean getEtaitAdministrateur() {
		return etaitAdministrateur;
	}

	public void setEtaitAdministrateur(Boolean etaitAdministrateur) {
		this.etaitAdministrateur = etaitAdministrateur;
	}
}
