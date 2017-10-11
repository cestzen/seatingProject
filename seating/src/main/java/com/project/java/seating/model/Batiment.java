package com.project.java.seating.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Batiment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private List<Plan> planList;
	private String nomBatiment;
	
	public Batiment() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Plan> getPlanList() {
		return planList;
	}
	public void setEtageList(List<Plan> planList) {
		this.planList = planList;
	}
	public String getNomBatiment() {
		return nomBatiment;
	}
	public void setNomBatiment(String nomBatiment) {
		this.nomBatiment = nomBatiment;
	}
	
	
}
