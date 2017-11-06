package com.project.java.seating.bdd;

import java.util.List;

import com.project.java.seating.model.Batiment;
import com.project.java.seating.persistence.ProjectEntityManager;

public class BatimentBdd {
	
	ProjectEntityManager projectEntityManager;
	
	public void ouvertureEntity() {
		projectEntityManager = new ProjectEntityManager();
		projectEntityManager.getSessionFactory().getCurrentSession().beginTransaction();
	}
	
	public void fermetureEntity() {
		projectEntityManager.getSessionFactory().getCurrentSession().getTransaction().commit();
		projectEntityManager.getSessionFactory().close();
	}
	
	public List<Batiment> getAll(){
		
		ouvertureEntity();
		
		List batiments = projectEntityManager.getSessionFactory().getCurrentSession().createQuery( "from Batiment" ).list();
		
		fermetureEntity();
		return batiments;
	}
	
	public Batiment get(int id){
		
		ouvertureEntity();
		
		List batiments = projectEntityManager.getSessionFactory().getCurrentSession().createQuery( "from Batiment WHERE id=:id").setParameter("id", id).list();
		Batiment batiment = (Batiment) batiments.get(0);
		fermetureEntity();
		
		return batiment;
	}
	
	public void create(String nom) {
		ouvertureEntity();
		
		Batiment batiment = new Batiment();
		batiment.setNomBatiment(nom);
		
		projectEntityManager.getSessionFactory().getCurrentSession().save(batiment);
		
		fermetureEntity();
	}
	
	
	public void update(int id,String nom) {
		ouvertureEntity();
		
		List batiments = projectEntityManager.getSessionFactory().getCurrentSession().createQuery( "from Batiment WHERE id=:id").setParameter("id", id).list();
		Batiment batiment = (Batiment) batiments.get(0);
		batiment.setNomBatiment(nom);
		
		projectEntityManager.getSessionFactory().getCurrentSession().merge(batiment);
		
		fermetureEntity();
	}
}
