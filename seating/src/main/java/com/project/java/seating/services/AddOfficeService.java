package com.project.java.seating.services;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.project.java.seating.bdd.BureauBdd;
import com.project.java.seating.bdd.CollaborateurBdd;
import com.project.java.seating.bdd.EquipementBdd;
import com.project.java.seating.bdd.PlanBdd;
import com.project.java.seating.model.Bureau;

/**
 * service for adding new bureau to the plan
 * @author beril
 *
 */
public class AddOfficeService extends GeneralServletService {
	private PlanBdd planBdd;
	private CollaborateurBdd collaborateurBdd;
	private EquipementBdd equipementBdd;
	private BureauBdd bureauBdd;

	public AddOfficeService() {
		super();
	}

	public void setPlanBdd(PlanBdd planBdd) {
		this.planBdd = planBdd;
	}

	public void setCollaborateurBdd(CollaborateurBdd collaborateurBdd) {
		this.collaborateurBdd = collaborateurBdd;
	}

	public void setEquipementBdd(EquipementBdd equipementBdd) {
		this.equipementBdd = equipementBdd;
	}

	public void setBureauBdd(BureauBdd bureauBdd) {
		this.bureauBdd = bureauBdd;
	}

	public void addOffice(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws IOException, ServletException {
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
			this.successRedirect("Bureaux cree", "/loginSuccess.jsp", servletContext, request, response);
		} catch (JSONException e) {
			this.errorRedirect("OPERATION ECHUE", "/loginSuccess.jsp", servletContext, request, response);
		}
	}

}
