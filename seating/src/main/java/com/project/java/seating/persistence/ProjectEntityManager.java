package com.project.java.seating.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProjectEntityManager {
	private EntityManagerFactory emFactory;

	private ProjectEntityManager() {
	    emFactory = Persistence.createEntityManagerFactory("seating-db");

	  }

	public EntityManager getEntityManager() {

		return emFactory.createEntityManager();

	}

	public void close() {

		emFactory.close();

	}

}
