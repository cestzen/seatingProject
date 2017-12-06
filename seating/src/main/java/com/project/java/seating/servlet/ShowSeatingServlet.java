package com.project.java.seating.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.java.seating.services.ShowSeatingService;

/**
 * servlet for viewing the plans
 */
/**
 * Servlet implementation class ShowSeatingServlet
 */
@WebServlet("/showSeating")
public class ShowSeatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext ac;
	private ShowSeatingService showSeatingService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowSeatingServlet() {
		super();
		ac = new ClassPathXmlApplicationContext("beans.xml");
		showSeatingService = (ShowSeatingService) ac.getBean("showSeatingService");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		showSeatingService.getBuildings(request, response, this.getServletContext());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		showSeatingService.getPlans(request, response, this.getServletContext());

	}

}
