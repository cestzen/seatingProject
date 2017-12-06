package com.project.java.seating.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.project.java.seating.services.AddCollaborateurService;

/**
 * Servlet implementation class AddCollaborator
 */
@WebServlet(name = "ajoutCollab", urlPatterns = { "/ajoutCollab" })
public class AddCollaboratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AddCollaborateurService addCollaborateurService;
	private ApplicationContext ac;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCollaboratorServlet() {
		super();
		ac = new ClassPathXmlApplicationContext("beans.xml");
		addCollaborateurService = (AddCollaborateurService) ac.getBean("addCollaborateurService");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		addCollaborateurService.errorRedirect("OPERATION PAS TROUVE", "/loginSuccess.jsp", this.getServletContext(),
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		addCollaborateurService.addCollaborateur(request, response, this.getServletContext());

	}

}
