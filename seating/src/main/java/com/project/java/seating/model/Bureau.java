package com.project.java.seating.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Bureau {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nom;
	private float y;
	private float x;
	@OneToOne
	@JoinColumn
	private Collaborateur collaborateur;
	@OneToMany
	@JoinColumn
	private List<Equipement> equipements;

	
	public Bureau() {
		
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


	public float getY() {
		return y;
	}


	public void setY(float y) {
		this.y = y;
	}


	public float getX() {
		return x;
	}


	public void setX(float x) {
		this.x = x;
	}


	public Collaborateur getCollaborateur() {
		return collaborateur;
	}


	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}


	public List<Equipement> getEquipements() {
		return equipements;
	}


	public void setEquipements(List<Equipement> equipements) {
		this.equipements = equipements;
	}

}
