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
import com.project.java.seating.bdd.CollaborateurBdd;
import com.project.java.seating.bdd.PlanBdd;
import com.project.java.seating.bdd.TypeEquipementBdd;
import com.project.java.seating.model.Batiment;
import com.project.java.seating.model.Collaborateur;
import com.project.java.seating.model.Plan;
import com.project.java.seating.model.TypeEquipement;
import com.project.java.seating.services.ShowSeatingPlanService;

@WebServlet("/choosePlan")
public class ChoosePlanServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplicationContext ac;
	//private ShowSeatingPlanService showSeatingPlanService;
	private PlanBdd planBdd;
	private TypeEquipementBdd typeEquipementBdd;
	private CollaborateurBdd collaborateurBdd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChoosePlanServlet() {
        super();
        ac = new ClassPathXmlApplicationContext("beans.xml");
      //  showSeatingPlanService = (ShowSeatingPlanService) ac.getBean("showSeatingPlanService");
        planBdd = (PlanBdd) ac.getBean("planBdd");
		typeEquipementBdd = (TypeEquipementBdd) ac.getBean("typeEquipementBdd");
		collaborateurBdd = (CollaborateurBdd) ac.getBean("collaborateurBdd");
    }
   
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//List<Batiment> batiments = batimentBdd.getAll();
		
	    this.getServletContext().getRequestDispatcher( "/choosePlan.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Plan plan = planBdd.getPlan((String) request.getParameter("nomPlan"));
		//Plan plan = planBdd.getPlan("planTest");

		List<TypeEquipement> typeEquipementList = typeEquipementBdd.getAll();

		String[] typesEquipements = new String[typeEquipementList.size()];
		
		for(int i = 0; i < typeEquipementList.size() ; i++)
			typesEquipements[i] = typeEquipementList.get(i).getNom();

		request.setAttribute("typesEquipements",typesEquipements);
		
		List<Collaborateur> collaborateursList = collaborateurBdd.getAll();

		String[] collaborateurs = new String[collaborateursList.size()];
		
		for(int i = 0; i < collaborateursList.size() ; i++)
			collaborateurs[i] = collaborateursList.get(i).getNom();
		
		request.setAttribute("collaborateurs",collaborateurs);

		request.setAttribute("path",plan.getPath() );
	    this.getServletContext().getRequestDispatcher( "/putOffice.jsp" ).forward( request, response );
		
		
	}
}
