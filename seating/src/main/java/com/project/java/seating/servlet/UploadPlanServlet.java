package com.project.java.seating.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.java.seating.services.ShowSeatingService;

/**
 * servlet for uploading a new plan 
 */
/**
 * Servlet implementation class UploadPlanServlet
 */
@MultipartConfig
@WebServlet(name = "uploadPlan", urlPatterns = { "/uploadPlan" })
public class UploadPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShowSeatingService showSeatingService;
	private ApplicationContext ac;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadPlanServlet() {
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
		showSeatingService.uploadPlanGetBuildings(request, response, this.getServletContext());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		showSeatingService.uploadPlans(request, response, this.getServletContext());
		
	}

}
