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

import com.project.java.seating.services.AddOfficeService;

/**
 * servlet for adding a new bureau
 */
/**
 * Servlet implementation class AddOfficeServlet
 */
@WebServlet("/addBureaux")
public class AddOfficeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AddOfficeService addOfficeService;
	private ApplicationContext ac;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddOfficeServlet() {
		super();
		ac = new ClassPathXmlApplicationContext("beans.xml");
		addOfficeService = (AddOfficeService) ac.getBean("addOfficeService");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/loginSuccess.jsp");
		PrintWriter out = response.getWriter();
		out.println("<font color=red>MESSAGE : OPERATION PAS DISPONIBLE</font>");
		rd.include(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		addOfficeService.addOffice(request, response, this.getServletContext());
	}

}
