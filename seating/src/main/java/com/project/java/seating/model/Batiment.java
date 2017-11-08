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
public class Batiment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
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
	public void setEtageList(ArrayList<Plan> planList) {
		this.planList = planList;
	}
	public String getNomBatiment() {
		return nomBatiment;
	}
	public void setNomBatiment(String nomBatiment) {
		this.nomBatiment = nomBatiment;
	}
	
	public void addPlan(Plan plan){
		if(this.planList == null)
			this.planList = new ArrayList<>();
		planList.add(plan);
	}
	
}
