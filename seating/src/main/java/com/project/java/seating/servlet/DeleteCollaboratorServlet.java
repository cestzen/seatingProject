package com.project.java.seating.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.project.java.seating.services.DeleteCollaboratorService;

/**
 * servlet for deleting a leaving collaborateur
 */

/**
 * Servlet implementation class AddCollaborator
 */
@WebServlet(name = "deleteCollab", urlPatterns = { "/deleteCollab" })
public class DeleteCollaboratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ApplicationContext ac;
	private DeleteCollaboratorService deleteCollaboratorService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCollaboratorServlet() {
		super();
		ac = new ClassPathXmlApplicationContext("beans.xml");
		deleteCollaboratorService = (DeleteCollaboratorService) ac.getBean("deleteCollaboratorService");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		deleteCollaboratorService.createChoices(request, response, this.getServletContext());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		deleteCollaboratorService.removeCollaborateur(request, response, this.getServletContext());

	}

}
