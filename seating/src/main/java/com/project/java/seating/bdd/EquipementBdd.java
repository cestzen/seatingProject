package com.project.java.seating.bdd;

import java.util.List;

import com.project.java.seating.model.Equipement;
import com.project.java.seating.model.TypeEquipement;
import com.project.java.seating.persistence.ProjectEntityManager;

/**
 * A DAO class that handles operations concerning the equipments
 * @author beril
 *
 */
public class EquipementBdd {

	ProjectEntityManager projectEntityManager;
	private TypeEquipementBdd typeEquipementBdd;

	public EquipementBdd() {
	}

	public void setTypeEquipementBdd(TypeEquipementBdd typeEquipementBdd) {
		this.typeEquipementBdd = typeEquipementBdd;
	}

	public void setProjectEntityManager(ProjectEntityManager projectEntityManager) {
		this.projectEntityManager = projectEntityManager;
	}

	public List<Equipement> getAll() {

		projectEntityManager.ouvertureEntity();

		List equipements = projectEntityManager.getSessionFactory().getCurrentSession().createQuery("from Equipement")
				.list();

		projectEntityManager.fermetureEntity();
		return equipements;
	}

	public Equipement get(int id) {

		projectEntityManager.ouvertureEntity();

		List equipements = projectEntityManager.getSessionFactory().getCurrentSession()
				.createQuery("from Equipement WHERE id=" + id).list();
		Equipement equipement = (Equipement) equipements.get(0);

		projectEntityManager.fermetureEntity();

		return equipement;
	}

	public Equipement create(String externalId, String type) {
		TypeEquipement typeEquipement = typeEquipementBdd.get(type);

		Equipement equipement = new Equipement();
		equipement.setExternalId(externalId);
		equipement.setTypeEquipement(typeEquipement);

		return equipement;
	}

}
