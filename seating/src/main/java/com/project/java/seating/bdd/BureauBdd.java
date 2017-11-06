package com.project.java.seating.bdd;


import java.util.List;

import com.project.java.seating.model.Bureau;
import com.project.java.seating.model.Collaborateur;
import com.project.java.seating.model.Plan;
import com.project.java.seating.persistence.ProjectEntityManager;

public class BureauBdd {
	ProjectEntityManager projectEntityManager;
	public BureauBdd() {
		
	} 
	
	public void ouvertureEntity() {
		projectEntityManager = new ProjectEntityManager();
		projectEntityManager.getSessionFactory().getCurrentSession().beginTransaction();
	}
	
	public void fermetureEntity() {
		projectEntityManager.getSessionFactory().getCurrentSession().getTransaction().commit();
		projectEntityManager.getSessionFactory().close();
	}
	
	public List<Bureau> getAll(){
		
		ouvertureEntity();
		
		List bureaux = projectEntityManager.getSessionFactory().getCurrentSession().createQuery( "from Bureau" ).list();
		
		fermetureEntity();
		return bureaux;
	}
	
	public Bureau get(int id){
		
		ouvertureEntity();
		
		List bureaux = projectEntityManager.getSessionFactory().getCurrentSession().createQuery( "from Bureau WHERE id=:id").setParameter("id", id).list();
		Bureau bureau = (Bureau) bureaux.get(0);
		
		fermetureEntity();
		
		return bureau;
	}
	
	public void create(String nom,float x, float y) {
		ouvertureEntity();
		
		Bureau Bureau = new Bureau();
		Bureau.setNom(nom);
		Bureau.setX(x);
		Bureau.setY(y);
		
		projectEntityManager.getSessionFactory().getCurrentSession().save(Bureau);
		
		fermetureEntity();
	}
	
	public void update(int id,Boolean nomModifier,String nom,Boolean xModifier,float x,Boolean yModifier, float y,Boolean directionModifier, String direction,Boolean idPlanModifier, Boolean idCollaborateurModifier, Collaborateur idCollaborateur) {
		ouvertureEntity();
		
		List bureaux = projectEntityManager.getSessionFactory().getCurrentSession().createQuery( "from Bureau WHERE id=:id").setParameter("id", id).list();
		Bureau bureau = (Bureau) bureaux.get(0);
		if(nomModifier)bureau.setNom(nom);
		if(xModifier)bureau.setX(x);
		if(yModifier)bureau.setY(y);
		if(idCollaborateurModifier)bureau.setCollaborateur(idCollaborateur);
		
		projectEntityManager.getSessionFactory().getCurrentSession().merge(bureau);
		
		fermetureEntity();
	}
	
	
}
