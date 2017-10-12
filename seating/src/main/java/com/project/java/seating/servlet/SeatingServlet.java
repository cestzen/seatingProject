package com.project.java.seating.servlet;

import java.io.IOException;
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
public class SeatingServlet extends HttpServlet implements ApplicationContextAware {
	private static final long serialVersionUID = 1L;
	private ApplicationContext ac;
	private AddCollaborateurService addCollaborateurService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeatingServlet() {
    	setApplicationContext(new ClassPathXmlApplicationContext("beans.xml"));
    	
    }
    
	public void setAc(ApplicationContext ac) {
		this.ac = ac;
	}



	public void setAddCollaborateurService(AddCollaborateurService addCollaborateurService) {
		this.addCollaborateurService = addCollaborateurService;
	}



	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		ac = context;
		
	}
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.getWriter().append(request.getParameter("name"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	

}
