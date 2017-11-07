package com.project.java.seating.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.java.seating.services.ShowSeatingPlanService;

/**
 * Servlet implementation class ShowSeatingServlet
 */
@WebServlet("/showSeating")
public class ShowSeatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext ac;
	private ShowSeatingPlanService showSeatingPlanService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowSeatingServlet() {
        super();
        ac = new ClassPathXmlApplicationContext("beans.xml");
        showSeatingPlanService = (ShowSeatingPlanService) ac.getBean("showSeatingPlanService");
    }
    
    

	public void setShowSeatingPlanService(ShowSeatingPlanService showSeatingPlanService) {
		this.showSeatingPlanService = showSeatingPlanService;
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String batiment = request.getParameter("batiment");
		
		response.getWriter().println(showSeatingPlanService.getFloorPlans(batiment));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
