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
import com.project.java.seating.model.Collaborateur;

/**
 * Servlet implementation class AddCollaborator
 */
@WebServlet(name = "deleteCollab", urlPatterns = { "/deleteCollab" })
public class DeleteCollaboratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CollaborateurBdd collaborateurBdd;
	private ApplicationContext ac;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCollaboratorServlet() {
		super();
		ac = new ClassPathXmlApplicationContext("beans.xml");
		collaborateurBdd = (CollaborateurBdd) ac.getBean("collaborateurBdd");
	}

	public void setCollaborateurBdd(CollaborateurBdd collaborateurBdd) {
		this.collaborateurBdd = collaborateurBdd;
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

		this.getServletContext().getRequestDispatcher("/deleteCollaborator.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("collab"));
		collaborateurBdd.deleteCollaborateur(request.getParameter("collab").split(":")[0]);
		response.getWriter().append(request.getParameter("collab").split(":")[1]);
		response.getWriter().append(" est supprimé");
	}

}
