package com.project.java.seating.bdd;


import java.util.List;
import com.project.java.seating.model.Collaborateur;
import com.project.java.seating.persistence.ProjectEntityManager;

public class CollaborateurBdd {
	
	ProjectEntityManager projectEntityManager;
	
	public void ouvertureEntity() {
		projectEntityManager = new ProjectEntityManager();
		projectEntityManager.getSessionFactory().getCurrentSession().beginTransaction();
	}
	
	public void fermetureEntity() {
		projectEntityManager.getSessionFactory().getCurrentSession().getTransaction().commit();
		projectEntityManager.getSessionFactory().close();
	}
	
	public List<Collaborateur> getAll(){
		
		ouvertureEntity();
		
		List collaborateurs = projectEntityManager.getSessionFactory().getCurrentSession().createQuery( "from Collaborateur" ).list();
		
		fermetureEntity();
		return collaborateurs;
	}
	
	public Collaborateur get(int id){
		
		ouvertureEntity();
		
		List collaborateurs = projectEntityManager.getSessionFactory().getCurrentSession().createQuery( "from Collaborateur WHERE id=:id").setParameter("id", id).list();
		Collaborateur collaborateur = (Collaborateur) collaborateurs.get(0);
		
		fermetureEntity();
		
		return collaborateur;
	}
	
	public void create(String nom_collaborateur,String prenom_collaborateur,Boolean estAdministrateur,String dateArriver) {
		ouvertureEntity();
		
		Collaborateur collaborateur = new Collaborateur();
		collaborateur.setNom(nom_collaborateur);
		collaborateur.setPrenom(prenom_collaborateur);
		collaborateur.setEstAdministrateur(estAdministrateur);
		collaborateur.setDateArriver(dateArriver);
		
		projectEntityManager.getSessionFactory().getCurrentSession().save(collaborateur);
		
		fermetureEntity();
	}
	
	public void update(int id,String nom_collaborateur,Boolean nomModification,String prenom_collaborateur,Boolean prenomModification,Boolean estAdministrateur,Boolean estAdministrateurModification,String dateArriver,Boolean dateArriverModification) {
		ouvertureEntity();
		
		List<Collaborateur> collaborateurs = projectEntityManager.getSessionFactory().getCurrentSession().createQuery( "from Collaborateur WHERE id=:id").setParameter("id", id).list();
		Collaborateur collaborateur = (Collaborateur) collaborateurs.get(0);
		if(nomModification)collaborateur.setNom(nom_collaborateur);
		if(prenomModification)collaborateur.setPrenom(prenom_collaborateur);
		if(estAdministrateurModification)collaborateur.setEstAdministrateur(estAdministrateur);
		if(dateArriverModification)collaborateur.setDateArriver(dateArriver);
		
		projectEntityManager.getSessionFactory().getCurrentSession().merge(collaborateur);
		
		fermetureEntity();
	}
	
}
