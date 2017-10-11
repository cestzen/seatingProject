package com.project.java.seating.persistence;

import com.project.java.seating.model.Collaborateur;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ProjectEntityManager {

		//Property based configuration
		private static SessionFactory sessionJavaConfigFactory;

	    private static SessionFactory buildSessionJavaConfigFactory() {
	    	try {
	    	Configuration configuration = new Configuration();
			
			//Create Properties, can be read from property files too
			Properties props = new Properties();
			props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			props.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/seating-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
			props.put("hibernate.connection.username", "test_user");
			props.put("hibernate.connection.password", "1234");
			props.put("hibernate.hbm2ddl.auto", "create");
			props.put("hibernate.current_session_context_class", "thread");
			
			configuration.setProperties(props);
			
			//we can set mapping file or class with annotation
			//addClass(Employee1.class) will look for resource
			// com/journaldev/hibernate/model/Employee1.hbm.xml (not good)
			configuration.addAnnotatedClass(Collaborateur.class);
			
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	    	System.out.println("Hibernate Java Config serviceRegistry created");
	    	
	    	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    	
	        return sessionFactory;
	    	}
	        catch (Throwable ex) {
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }
		
		public static SessionFactory getSessionFactory() {
			if(sessionJavaConfigFactory == null) sessionJavaConfigFactory = buildSessionJavaConfigFactory();
	        return sessionJavaConfigFactory;
	    }
		
		public static void main(String[] args) {
			ProjectEntityManager projectEntityManager = new ProjectEntityManager();
			Collaborateur collaborateur = new Collaborateur();
			collaborateur.setNom_collaborateur("Baykut");
			collaborateur.setPrenom_collaborateur("Beril");
			
			projectEntityManager.getSessionFactory().getCurrentSession().beginTransaction();
			projectEntityManager.getSessionFactory().getCurrentSession().save(collaborateur);
			projectEntityManager.getSessionFactory().getCurrentSession().getTransaction().commit();
		}
		
	}
