package com.project.java.seating.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.java.seating.bdd.CollaborateurBdd;
import com.project.java.seating.model.Collaborateur;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/login")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CollaborateurBdd collaborateurBdd;
	private ApplicationContext ac;
	private static HttpSession session;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogInServlet() {
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
		
			// get request parameters for userID and password
			String user = request.getParameter("username");
			String pwd = request.getParameter("password");

			Collaborateur collaborateur = collaborateurBdd.findCollaborateur(user, pwd);
			if (collaborateur != null) {
				session = request.getSession(true);
				
				if (collaborateur.getEstAdministrateur())
					session.setAttribute("admin", "admin");
				// setting cookie to expiry in 5 mins
				session.setMaxInactiveInterval(60 * 5);
				session.setAttribute("user", user);
				response.sendRedirect("/seating/loginSuccess.jsp");
			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
				PrintWriter out = response.getWriter();
				out.println("<font color=red>Either user name or password is wrong.</font>");
				rd.include(request, response);
			}
		
	}

}
