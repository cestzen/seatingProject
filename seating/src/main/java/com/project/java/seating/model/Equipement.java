package com.project.java.seating.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Equipement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	@JoinColumn
	private TypeEquipement typeEquipement;
	private String nom;
	@Column(unique=true)
	private String externalId;

	public Equipement() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TypeEquipement getTypeEquipement() {
		return typeEquipement;
	}

	public void setTypeEquipement(TypeEquipement typeEquipement) {
		this.typeEquipement = typeEquipement;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

}
