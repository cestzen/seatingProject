package com.project.java.seating.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.java.seating.services.AddCollaborateurService;

/**
 * Servlet implementation class SeatingServlet
 */
@WebServlet({ "/" })
public class SeatingServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private AddCollaborateurService addCollaborateurService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeatingServlet() {
    	super();
    	//addCollaborateurService = new AddCollaborateurService();
    	//setApplicationContext(new ClassPathXmlApplicationContext("beans.xml"));
    	
    }


	public void setAddCollaborateurService(AddCollaborateurService addCollaborateurService) {
		this.addCollaborateurService = addCollaborateurService;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
		PrintWriter out = response.getWriter();
		out.println("<font color=green>MESSAGE : LOGIN POUR LES EFFECTUER LES OPERATIONS</font>");
		rd.include(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	

}
