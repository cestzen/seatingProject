package com.project.java.seating.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bureau {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String nom_bureau;
	private float y;
	private float x;
	private String direction;
	private int id_collaborateur;
	private int id_plan;
	private int id_direction;
	
	public Bureau() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom_bureau() {
		return nom_bureau;
	}
	public void setNom_bureau(String nom_bureau) {
		this.nom_bureau = nom_bureau;
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
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getId_collaborateur() {
		return id_collaborateur;
	}

	public void setId_collaborateur(int id_collaborateur) {
		this.id_collaborateur = id_collaborateur;
	}

	public int getId_plan() {
		return id_plan;
	}

	public void setId_plan(int id_plan) {
		this.id_plan = id_plan;
	}

	public int getId_direction() {
		return id_direction;
	}

	public void setId_direction(int id_direction) {
		this.id_direction = id_direction;
	}
	
	
}
