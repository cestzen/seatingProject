package com.project.java.seating.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.java.seating.bdd.CollaborateurBdd;
import com.project.java.seating.model.Collaborateur;

public class DeleteCollaboratorService extends GeneralServletService {
	private CollaborateurBdd collaborateurBdd;

	public DeleteCollaboratorService() {
		super();
	}

	public void setCollaborateurBdd(CollaborateurBdd collaborateurBdd) {
		this.collaborateurBdd = collaborateurBdd;
	}

	public void createChoices(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws ServletException, IOException {
		List<Collaborateur> collaborateurs = collaborateurBdd.getAll();
		List<String> nomCollaborateurs = new ArrayList<>();
		for (Collaborateur collab : collaborateurs)
			nomCollaborateurs.add(collab.getId() + ": " + collab.getNom() + ", " + collab.getPrenom());
		request.setAttribute("collaborateurs", nomCollaborateurs);

		servletContext.getRequestDispatcher("/deleteCollaborator.jsp").forward(request, response);

	}

	public void removeCollaborateur(HttpServletRequest request, HttpServletResponse response,
			ServletContext servletContext) throws IOException, ServletException {
		collaborateurBdd.deleteCollaborateur(request.getParameter("collab").split(":")[0]);
		this.successRedirect("Collaborateur supprime", "/loginSuccess.jsp", servletContext, request, response);
	}

}
