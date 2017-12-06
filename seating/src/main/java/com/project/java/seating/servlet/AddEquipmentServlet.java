package com.project.java.seating.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.project.java.seating.services.AddEquipementService;

/**
 * servlet for adding a new equipment
 */
/**
 * Servlet implementation class AddEquipement
 */
@WebServlet(name = "ajoutEquipment", urlPatterns = { "/ajoutEquipment" })
public class AddEquipmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext ac;
	private AddEquipementService addEquipementService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddEquipmentServlet() {
		super();
		ac = new ClassPathXmlApplicationContext("beans.xml");
		addEquipementService = (AddEquipementService) ac.getBean("addEquipementService");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		addEquipementService.prepareAddEquipement(request, response, this.getServletContext());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		addEquipementService.addEquipement(request, response, this.getServletContext());

	}

}
