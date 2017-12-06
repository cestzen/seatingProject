package com.project.java.seating.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.java.seating.bdd.BatimentBdd;
import com.project.java.seating.bdd.BureauBdd;
import com.project.java.seating.bdd.CollaborateurBdd;
import com.project.java.seating.bdd.EquipementBdd;
import com.project.java.seating.bdd.PlanBdd;
import com.project.java.seating.bdd.TypeEquipementBdd;
import com.project.java.seating.model.Batiment;
import com.project.java.seating.model.Bureau;
import com.project.java.seating.model.Equipement;
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
	private CollaborateurBdd collaborateurBdd;
	private EquipementBdd equipementBdd;
	private BureauBdd bureauBdd;
	private ApplicationContext ac;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddOfficeServlet() {
		super();
		ac = new ClassPathXmlApplicationContext("beans.xml");
		batimentBdd = (BatimentBdd) ac.getBean("batimentBdd");
		planBdd = (PlanBdd) ac.getBean("planBdd");
		bureauBdd = (BureauBdd) ac.getBean("bureauBdd");
		typeEquipementBdd = (TypeEquipementBdd) ac.getBean("typeEquipementBdd");
		equipementBdd = (EquipementBdd) ac.getBean("equipementBdd");
		collaborateurBdd = (CollaborateurBdd) ac.getBean("collaborateurBdd");
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
		String jsonString = request.getParameter("array");

		try {
			JSONArray jsonData = new JSONArray(jsonString);
			int nbDesks = jsonData.length();

			for (int i = 0; i < nbDesks; i++) {
				JSONObject singleData = jsonData.getJSONObject(i);
				Bureau bureau = new Bureau();
				bureau.setX(Float.parseFloat(singleData.getString("x")));
				bureau.setY(Float.parseFloat(singleData.getString("y")));
				bureau.setNom(singleData.getString("nom"));

				// add collaborateur
				bureau.setCollaborateur(collaborateurBdd.findCollaborateur(singleData.getString("nomUtilisateur")));

				if (singleData.getString("nomEquipment") != null && !singleData.getString("nomEquipment").isEmpty()) {
					// addEquipment to the bureau
					bureau.addEquipement(equipementBdd.create(singleData.getString("nomEquipment"),
							singleData.getString("nomTypeEquipment")));

				}
				bureauBdd.create(bureau);
				planBdd.addBureau(request.getParameter("planId"), bureau);
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/loginSuccess.jsp");
			PrintWriter out = response.getWriter();
			out.println("<font color=green>MESSAGE : Bureaux cree</font>");
			rd.include(request, response);
		} catch (JSONException e) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/loginSuccess.jsp");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>MESSAGE : OPERATION ECHUE</font>");
			rd.include(request, response);
		}

	}

}
