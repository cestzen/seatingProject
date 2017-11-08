package com.project.java.seating.servlet;

import java.io.IOException;

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
import com.project.java.seating.model.Batiment;
import com.project.java.seating.model.Bureau;
import com.project.java.seating.model.Plan;
import com.project.java.seating.services.ShowSeatingPlanService;

/**
 * Servlet implementation class AddOfficeServlet
 */
@WebServlet("/addBureaux")
public class AddOfficeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BatimentBdd batimentBdd;
	private ApplicationContext ac;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddOfficeServlet() {
		super();
		ac = new ClassPathXmlApplicationContext("beans.xml");
		batimentBdd = (BatimentBdd) ac.getBean("batimentBdd");
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
		response.getWriter().append("Served at: ").append(request.getParameter("data"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			JSONArray json = new JSONArray(request.getParameter("data"));
			Batiment batiment = new Batiment();
			batiment.setNomBatiment("testBatiment");
			Plan plan = new Plan();

			for(int i = 0; i < json.length(); i++){
				Bureau bureau = new Bureau();
				
				bureau.setNom((String) json.getJSONObject(i).get("name"));
				bureau.setX((Integer) json.getJSONObject(i).get("hor"));
				bureau.setY((Integer) json.getJSONObject(i).get("ver"));
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
