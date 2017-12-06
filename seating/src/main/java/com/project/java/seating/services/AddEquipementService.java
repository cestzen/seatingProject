package com.project.java.seating.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.java.seating.bdd.CollaborateurBdd;
import com.project.java.seating.bdd.EquipementBdd;
import com.project.java.seating.bdd.TypeEquipementBdd;
import com.project.java.seating.model.Collaborateur;
import com.project.java.seating.model.Equipement;
import com.project.java.seating.model.TypeEquipement;

/**
 * Service for adding equipment
 * 
 * @author berilb
 *
 */
public class AddEquipementService extends GeneralServletService {
	private CollaborateurBdd collaborateurBdd;
	private TypeEquipementBdd typeEquipementBdd;
	private EquipementBdd equipementBdd;

	public AddEquipementService() {
		super();
	}

	public void setCollaborateurBdd(CollaborateurBdd collaborateurBdd) {
		this.collaborateurBdd = collaborateurBdd;
	}

	public void setTypeEquipementBdd(TypeEquipementBdd typeEquipementBdd) {
		this.typeEquipementBdd = typeEquipementBdd;
	}

	public void setEquipementBdd(EquipementBdd equipementBdd) {
		this.equipementBdd = equipementBdd;
	}

	/**
	 * preparation of the equipement to add
	 * @param request
	 * @param response
	 * @param servletContext
	 * @throws IOException
	 * @throws ServletException
	 */
	public void prepareAddEquipement(HttpServletRequest request, HttpServletResponse response,
			ServletContext servletContext) throws IOException, ServletException {
		try {
			List<Collaborateur> collaborateurs = collaborateurBdd.getAll();
			List<String> nomCollaborateurs = new ArrayList<>();
			for (Collaborateur collab : collaborateurs)
				nomCollaborateurs.add(collab.getId() + ": " + collab.getNom() + ", " + collab.getPrenom());
			request.setAttribute("collaborateurs", nomCollaborateurs);

			List<TypeEquipement> types = typeEquipementBdd.getAll();
			List<String> typesNoms = new ArrayList<>();
			for (TypeEquipement type : types)
				typesNoms.add(type.getNom());
			request.setAttribute("typesEquipement", typesNoms);

			servletContext.getRequestDispatcher("/addEquipement.jsp").forward(request, response);
		} catch (Exception e) {
			this.errorRedirect("IMPOSSIBLE D'AJOUTER EQUIPEMENT", "/loginSuccess.jsp", servletContext, request,
					response);
		}

	}

	public void addEquipement(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws IOException, ServletException {
		Equipement eq = equipementBdd.create(request.getParameter("externalId"), request.getParameter("type"));
		collaborateurBdd.addEquipement(request.getParameter("collab").split(":")[0], eq);

		this.successRedirect("Equipement cree", "/loginSuccess.jsp", servletContext, request, response);

	}

}
