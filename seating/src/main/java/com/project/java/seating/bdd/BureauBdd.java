package com.project.java.seating.bdd;


import java.util.List;

import com.project.java.seating.model.Bureau;
import com.project.java.seating.model.Collaborateur;
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
		Bureau.setNom_bureau(nom);
		Bureau.setX(x);
		Bureau.setY(y);
		
		projectEntityManager.getSessionFactory().getCurrentSession().save(Bureau);
		
		fermetureEntity();
	}
	
	
	public void update(int id,String nom,float x, float y,String direction, int idPlan, int idCollaborateur) {
		update(id,true,nom,true,x,true,y,true,direction,true,idPlan,true,idCollaborateur);
	}
	
	public void updateName(int id,String nom) {

		update(id,true,nom,false,0f,false,0f,false,null,false,0,false,0);
	}
	
	public void updateX(int id,float x) {

		update(id,false,null,true,x,false,0f,false,null,false,0,false,0);
	}
	
	public void updateY(int id,float y) {

		update(id,false,null,false,0f,true,y,false,null,false,0,false,0);
	}
	
	public void updateDirection(int id,String direction) {

		update(id,false,null,false,0f,false,0f,true,direction,false,0,false,0);
	}
	
	public void updateIdPlan(int id,int idPlan) {

		update(id,false,null,false,0f,false,0f,false,null,true,idPlan,false,0);
	}
	
	public void updateIdCollaborateur(int id,int idCollaborateur) {

		update(id,false,null,false,0f,false,0f,false,null,false,0,true,idCollaborateur);
	}
	
	
	private void update(int id,Boolean nomModifier,String nom,Boolean xModifier,float x,Boolean yModifier, float y,Boolean directionModifier, String direction,Boolean idPlanModifier, int idPlan,Boolean idCollaborateurModifier, int idCollaborateur) {
		ouvertureEntity();
		
		List bureaux = projectEntityManager.getSessionFactory().getCurrentSession().createQuery( "from Bureau WHERE id="+id ).list();
		Bureau bureau = (Bureau) bureaux.get(0);
		if(nomModifier)bureau.setNom_bureau(nom);
		if(xModifier)bureau.setX(x);
		if(yModifier)bureau.setY(y);
		if(directionModifier)bureau.setDirection(direction);
		if(idPlanModifier)bureau.setId_plan(idPlan);
		if(idCollaborateurModifier)bureau.setId_collaborateur(idCollaborateur);
		
		projectEntityManager.getSessionFactory().getCurrentSession().merge(bureau);
		
		fermetureEntity();
	}
	
	
}
