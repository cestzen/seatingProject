package com.project.java.seating.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.java.seating.bdd.CollaborateurBdd;

/**
 * Servlet implementation class AddCollaborator
 */
@WebServlet(name = "ajoutCollab", urlPatterns = { "/ajoutCollab" })
public class AddCollaborator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCollaborator() {
		super();
		// TODO Auto-generated constructor stub
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
		CollaborateurBdd bdd = new CollaborateurBdd();

		bdd.create(request.getParameter("lastname"), request.getParameter("name"),
				request.getParameter("admin") == null || request.getParameter("admin").isEmpty() ? true : false, request.getParameter("date"));
	}

}