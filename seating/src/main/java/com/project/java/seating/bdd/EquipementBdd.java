package com.project.java.seating.bdd;


import java.util.List;

import com.project.java.seating.model.Equipement;
import com.project.java.seating.persistence.ProjectEntityManager;

public class EquipementBdd {
	
	ProjectEntityManager projectEntityManager;
	
	public void ouvertureEntity() {
		projectEntityManager = new ProjectEntityManager();
		projectEntityManager.getSessionFactory().getCurrentSession().beginTransaction();
	}
	
	public void fermetureEntity() {
		projectEntityManager.getSessionFactory().getCurrentSession().getTransaction().commit();
		projectEntityManager.getSessionFactory().close();
	}
	
	public List<Equipement> getAll(){
		
		ouvertureEntity();
		
		List equipements = projectEntityManager.getSessionFactory().getCurrentSession().createQuery( "from Equipement" ).list();
		
		fermetureEntity();
		return equipements;
	}
	
	public Equipement get(int id){
		
		ouvertureEntity();
		
		List equipements = projectEntityManager.getSessionFactory().getCurrentSession().createQuery( "from Equipement WHERE id="+id ).list();
		Equipement equipement = (Equipement) equipements.get(0);
		
		fermetureEntity();
		
		return equipement;
	}
	
	public void create(String nom_equipement,String date_achat) {
		ouvertureEntity();
		
		Equipement equipement = new Equipement();
		equipement.setNom(nom_equipement);
		
		projectEntityManager.getSessionFactory().getCurrentSession().save(equipement);
		
		fermetureEntity();
	}
	
	
	public void update(int id,String nom_equipement,String date_achat) {
		update(id,nom_equipement,true,date_achat,true);
	}
	
	public void updateNom(int id,String nom_equipement,String date_achat) {
		update(id,nom_equipement,true,null,false);
	}
	
	public void updateDate(int id,String nom_equipement,String date_achat) {
		update(id,null,false,date_achat,true);
	}
	
	public void update(int id,String nom_equipement,Boolean nomModification,String date_achat,Boolean dateModification) {
		ouvertureEntity();
		
		List equipements = projectEntityManager.getSessionFactory().getCurrentSession().createQuery( "from Equipement WHERE id="+id ).list();
		Equipement equipement = (Equipement) equipements.get(0);
		if(nomModification)equipement.setNom(nom_equipement);
		
		projectEntityManager.getSessionFactory().getCurrentSession().merge(equipement);
		
		fermetureEntity();
	}
	
}
