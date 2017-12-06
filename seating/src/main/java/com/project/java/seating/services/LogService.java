package com.project.java.seating.services;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.java.seating.bdd.CollaborateurBdd;
import com.project.java.seating.model.Collaborateur;

/**
 * Handles login logout
 * 
 * @author cestzen
 *
 */
public class LogService extends GeneralServletService {
	private CollaborateurBdd collaborateurBdd;
	private static HttpSession session;

	public LogService() {
		super();
	}

	public void setCollaborateurBdd(CollaborateurBdd collaborateurBdd) {
		this.collaborateurBdd = collaborateurBdd;
	}

	public void logIn(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws IOException, ServletException {

		// get request parameters for userID and password
		String user = request.getParameter("username");
		String pwd = request.getParameter("password");

		Collaborateur collaborateur = collaborateurBdd.findCollaborateur(user, pwd);
		if (collaborateur != null) {
			session = request.getSession(true);

			if (collaborateur.getEstAdministrateur())
				session.setAttribute("admin", "admin");
			// setting cookie to expiry in 5 mins
			session.setMaxInactiveInterval(60 * 5);
			session.setAttribute("user", user);
			response.sendRedirect("/seating/loginSuccess.jsp");
		} else {
			this.errorRedirect("UTILISATEUR OU MOT DE PASSE INCORRECTE", "/login.jsp", servletContext, request,
					response);
		}

	}

	public void logOut(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		this.successRedirect("DECONNECTE", "/login.jsp", servletContext, request, response);
	}

}
