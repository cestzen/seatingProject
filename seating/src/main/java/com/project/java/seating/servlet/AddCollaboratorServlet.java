package com.project.java.seating.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.project.java.seating.bdd.CollaborateurBdd;

/**
 * Servlet implementation class AddCollaborator
 */
@WebServlet(name = "ajoutCollab", urlPatterns = { "/ajoutCollab" })
public class AddCollaboratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CollaborateurBdd collaborateurBdd;
	private ApplicationContext ac;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCollaboratorServlet() {
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		collaborateurBdd.create(request.getParameter("lastname"), request.getParameter("name"),
				request.getParameter("admin") == null || request.getParameter("admin").isEmpty() ? false : true,
				request.getParameter("date"), request.getParameter("username"), request.getParameter("password"));
	}

}
