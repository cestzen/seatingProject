package com.project.java.seating.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/loginSuccess.jsp");
		PrintWriter out = response.getWriter();
		out.println("<font color=red>MESSAGE : Cette page n'existe pas</font>");
		rd.include(request, response);
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
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/loginSuccess.jsp");
		PrintWriter out = response.getWriter();
		out.println("<font color=green>MESSAGE : Collaborateur cree</font>");
		rd.include(request, response);
	}

}
