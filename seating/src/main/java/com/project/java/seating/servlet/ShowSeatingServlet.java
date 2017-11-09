package com.project.java.seating.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.java.seating.bdd.BatimentBdd;
import com.project.java.seating.model.Batiment;
import com.project.java.seating.model.Plan;
import com.project.java.seating.services.ShowSeatingPlanService;

/**
 * Servlet implementation class ShowSeatingServlet
 */
@WebServlet("/showSeating")
public class ShowSeatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext ac;
	private ShowSeatingPlanService showSeatingPlanService;
	private BatimentBdd batimentBdd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowSeatingServlet() {
        super();
        ac = new ClassPathXmlApplicationContext("beans.xml");
        showSeatingPlanService = (ShowSeatingPlanService) ac.getBean("showSeatingPlanService");
        batimentBdd = (BatimentBdd) ac.getBean("batimentBdd");
    }
   
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Batiment> batiments = batimentBdd.getAll();
		List<String> nomBatiments = new ArrayList<>();
		for(Batiment batiment : batiments)
			nomBatiments.add(batiment.getNomBatiment());
		request.setAttribute( "nomsBatiments", nomBatiments );

	    this.getServletContext().getRequestDispatcher( "/chooseBatiment.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Batiment> batiments = batimentBdd.getAll();
		for(Batiment batiment : batiments){
			if(batiment.getNomBatiment().equals(request.getParameter("nomBatiment"))){
				List<String> nomsPlans = new ArrayList<>();
				for(Plan plan : batiment.getPlanList()){
					nomsPlans.add(plan.getNom());
				}
				request.setAttribute("nomsPlans", nomsPlans);
				this.getServletContext().getRequestDispatcher( "/choosePlan.jsp" ).forward( request, response );
				return;
			}
		}
		
		
	}

}
