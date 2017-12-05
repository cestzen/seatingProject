package com.project.java.seating.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.project.java.seating.bdd.CollaborateurBdd;
import com.project.java.seating.bdd.EquipementBdd;
import com.project.java.seating.bdd.TypeEquipementBdd;
import com.project.java.seating.model.Batiment;
import com.project.java.seating.model.Collaborateur;
import com.project.java.seating.model.Equipement;
import com.project.java.seating.model.TypeEquipement;

/**
 * Servlet implementation class AddEquipement
 */
@WebServlet(name = "ajoutEquipment", urlPatterns = { "/ajoutEquipment" })
public class AddEquipmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EquipementBdd equipmentBdd;
	private ApplicationContext ac;
	private CollaborateurBdd collaborateurBdd;
	private TypeEquipementBdd typeEquipementBdd;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddEquipmentServlet() {
		super();
		ac = new ClassPathXmlApplicationContext("beans.xml");
		equipmentBdd = (EquipementBdd) ac.getBean("equipementBdd");
		collaborateurBdd = (CollaborateurBdd) ac.getBean("collaborateurBdd");
		typeEquipementBdd = (TypeEquipementBdd) ac.getBean("typeEquipementBdd");
	}

	public void setEquipementBdd(EquipementBdd equipementBdd) {
		this.equipmentBdd = equipementBdd;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		this.getServletContext().getRequestDispatcher("/addEquipement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Equipement eq = equipmentBdd.create(request.getParameter("externalId"), request.getParameter("type"));
		collaborateurBdd.addEquipement(request.getParameter("collab").split(":")[0], eq);
	}

}
