package com.project.java.seating.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.java.seating.bdd.BatimentBdd;
import com.project.java.seating.bdd.PlanBdd;
import com.project.java.seating.bdd.TypeEquipementBdd;
import com.project.java.seating.model.Batiment;
import com.project.java.seating.model.Bureau;
import com.project.java.seating.model.Plan;
import com.project.java.seating.model.TypeEquipement;
import com.project.java.seating.tools.JsonTools;

/**
 * Servlet implementation class AddOfficeServlet
 */
@WebServlet("/addBureaux")
public class AddOfficeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TypeEquipementBdd typeEquipementBdd;
	private BatimentBdd batimentBdd;
	private PlanBdd planBdd;
	private ApplicationContext ac;

	private static final JsonTools<Bureau> jsonTool = new JsonTools<>(Bureau.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddOfficeServlet() {
		super();
		ac = new ClassPathXmlApplicationContext("beans.xml");
		batimentBdd = (BatimentBdd) ac.getBean("batimentBdd");
		planBdd = (PlanBdd) ac.getBean("planBdd");
		typeEquipementBdd = (TypeEquipementBdd) ac.getBean("typeEquipementBdd");
	}

	public void setTypeEquipementBdd(TypeEquipementBdd typeEquipementBdd) {
		this.typeEquipementBdd = typeEquipementBdd;
	}


	public void setBatimentBdd(BatimentBdd batimentBdd) {
		this.batimentBdd = batimentBdd;
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getParameter("data"));
		
		//typeEquipementBdd.create("test");
		
		//String[] typesEquipements = addOfficeService.getTypesEquipement();
		
		Plan plan = planBdd.getPlan(request.getParameter("nomPlan"));
		String[] varPlan = new String[4];
		varPlan[0] = plan.getHauteur()+"";
		varPlan[1] = plan.getLargeur()+"";
		varPlan[2] = plan.getNom()+"";
		varPlan[3] = plan.getPath()+"";
		
		request.setAttribute( "varPlan",varPlan );

		
		List<TypeEquipement> typeEquipementList = typeEquipementBdd.getAll();
		String[] typesEquipements = new String[typeEquipementList.size()];
		
		for(int i = 0; i < typeEquipementList.size() ; i++)
			typesEquipements[i] = typeEquipementList.get(i).getNom();
		
		request.setAttribute( "typesEquipements",typesEquipements );

	    this.getServletContext().getRequestDispatcher( "/putOffice.jsp" ).forward( request, response );
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			Batiment batiment = new Batiment();
			batiment.setNomBatiment("testBatiment");
			Plan plan = new Plan();

			JSONArray json = new JSONArray(request.getParameter("data"));

			for (int i = 0; i < json.length(); i++) {
				Bureau bureau = jsonTool.createObject(json.getJSONObject(i).toString());
				plan.addBureau(bureau);
			}

			batiment.addPlan(plan);

			batimentBdd.create(batiment);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
