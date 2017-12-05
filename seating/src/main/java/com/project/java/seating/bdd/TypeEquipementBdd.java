package com.project.java.seating.bdd;

import java.util.List;

import com.project.java.seating.model.TypeEquipement;
import com.project.java.seating.persistence.ProjectEntityManager;

public class TypeEquipementBdd {
	private ProjectEntityManager projectEntityManager;

	public TypeEquipementBdd() {
	}

	public void setProjectEntityManager(ProjectEntityManager projectEntityManager) {
		this.projectEntityManager = projectEntityManager;
	}

	public List<TypeEquipement> getAll() {
		projectEntityManager.ouvertureEntity();

		List typeEquipements = projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("from TypeEquipement").list();

		projectEntityManager.fermetureEntity();
		return typeEquipements;
	}
}
