package com.project.java.seating.services;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.java.seating.bdd.CollaborateurBdd;

/**
 * Services to add collaborators
 * 
 * @author cestzen
 *
 */
public class AddCollaborateurService extends GeneralServletService {
	private CollaborateurBdd collaborateurBdd;

	public AddCollaborateurService() {
		super();
	}

	public void setCollaborateurBdd(CollaborateurBdd collaborateurBdd) {
		this.collaborateurBdd = collaborateurBdd;
	}

	/**
	 * Adds collaborator from the request parameters
	 * 
	 * @param request
	 * @param response
	 * @param servletContext
	 * @throws IOException
	 * @throws ServletException
	 */
	public void addCollaborateur(HttpServletRequest request, HttpServletResponse response,
			ServletContext servletContext) throws IOException, ServletException {
		try {
			collaborateurBdd.create(request.getParameter("lastname"), request.getParameter("name"),
					request.getParameter("admin") == null || request.getParameter("admin").isEmpty() ? false : true,
					new Date(), request.getParameter("username"), request.getParameter("password"));

			this.successRedirect("Collaborateur cree", "/loginSuccess.jsp", servletContext, request, response);
		} catch (Exception e) {
			this.errorRedirect("OPERATION ECHUE", "/loginSuccess.jsp", servletContext, request, response);
		}
	}

}
