package com.project.java.seating.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nom;
	private float hauteur;
	private float largeur;
	private String path;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "bureauPlan")
	private List<Bureau> bureaux;

	public Plan() {

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

	public float getHauteur() {
		return hauteur;
	}

	public void setHauteur(float hauteur) {
		this.hauteur = hauteur;
	}

	public float getLargeur() {
		return largeur;
	}

	public void setLargeur(float largeur) {
		this.largeur = largeur;
	}

	public List<Bureau> getBureaux() {
		return bureaux;
	}

	public void setBureaux(List<Bureau> bureaux) {
		this.bureaux = bureaux;
	}

	public void addBureau(Bureau bureau) {
		if (this.bureaux == null)
			this.bureaux = new ArrayList<>();
		this.bureaux.add(bureau);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
