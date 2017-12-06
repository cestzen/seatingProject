package com.project.java.seating.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Collaborateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nom;
	private String prenom;
	@Column(unique=true)
	private String nomUtilisateur;
	private String motDePasse;
	private Boolean estAdministrateur;
	private String dateArriver;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="equipementCollaborateur")
	private List<Equipement> equipements;

	public Collaborateur() {

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

	public List<Equipement> getEquipements() {
		return equipements;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public void setEquipements(List<Equipement> equipements) {
		this.equipements = equipements;
	}

	public void addEquipement(Equipement equipement) {
		if (this.equipements == null)
			this.equipements = new ArrayList<>();
		this.equipements.add(equipement);
	}
}
