package com.project.java.seating.persistence;

import com.project.java.seating.model.Batiment;
import com.project.java.seating.model.Bureau;
import com.project.java.seating.model.Collaborateur;
import com.project.java.seating.model.Equipement;
import com.project.java.seating.model.Plan;
import com.project.java.seating.model.TypeEquipement;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ProjectEntityManager {
	// Property based configuration
	private SessionFactory sessionJavaConfigFactory;

	public ProjectEntityManager() {
	}

	public void ouvertureEntity() {
		try{
		getSessionFactory().getCurrentSession().isDirty();
		}catch(HibernateException e){
			getSessionFactory().getCurrentSession().beginTransaction();
		}
	}

	public void fermetureEntity() {
		getSessionFactory().getCurrentSession().getTransaction().commit();
	}

	private SessionFactory buildSessionJavaConfigFactory() {
		try {
			Configuration configuration = new Configuration();

			// Create Properties, can be read from property files too
			Properties props = new Properties();
			props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			props.put("hibernate.connection.url",
					"jdbc:mysql://localhost:3306/seating-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
			props.put("hibernate.connection.username", "test_user");
			props.put("hibernate.connection.password", "1234");
			props.put("hibernate.hbm2ddl.auto", "update");
			props.put("hibernate.current_session_context_class", "thread");
			props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

			configuration.setProperties(props);

			// we can set mapping file or class with annotation
			// addClass(Employee1.class) will look for resource
			// com/journaldev/hibernate/model/Employee1.hbm.xml (not good)

			configuration.addAnnotatedClass(TypeEquipement.class);
			configuration.addAnnotatedClass(Equipement.class);
			configuration.addAnnotatedClass(Collaborateur.class);
			configuration.addAnnotatedClass(Bureau.class);
			configuration.addAnnotatedClass(Plan.class);
			configuration.addAnnotatedClass(Batiment.class);

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			System.out.println("Hibernate Java Config serviceRegistry created");

			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			return sessionFactory;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public SessionFactory getSessionFactory() {
		if (sessionJavaConfigFactory == null)
			sessionJavaConfigFactory = buildSessionJavaConfigFactory();
		return sessionJavaConfigFactory;
	}

	public static void main(String[] args) {
		ProjectEntityManager pe = new ProjectEntityManager();
		pe.getSessionFactory();
	}
}
