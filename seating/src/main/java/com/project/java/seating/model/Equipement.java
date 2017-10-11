package com.project.java.seating.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equipement {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nom_equipement;
	private String date_achat;
	
	public Equipement() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom_equipement() {
		return nom_equipement;
	}
	public void setNom_equipement(String nom_equipement) {
		this.nom_equipement = nom_equipement;
	}
	public String getDate_achat() {
		return date_achat;
	}
	public void setDate_achat(String date_achat) {
		this.date_achat = date_achat;
	}
	
	
	
}
