package com.project.java.seating.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.project.java.seating.bdd.BatimentBdd;
import com.project.java.seating.model.Batiment;
import com.project.java.seating.model.Plan;

public class ShowSeatingService extends GeneralServletService {

	private BatimentBdd batimentBdd;
	private Properties prop;

	public ShowSeatingService() throws IOException {
		super();
		prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream("application.properties");
		prop.load(stream);
	}

	public void setBatimentBdd(BatimentBdd batimentBdd) {
		this.batimentBdd = batimentBdd;
	}

	private List<String> getBuildings() {
		List<Batiment> batiments = batimentBdd.getAll();
		List<String> nomBatiments = new ArrayList<>();
		for (Batiment batiment : batiments) {
			nomBatiments.add(batiment.getNomBatiment());
		}
		return nomBatiments;
	}

	public void getBuildings(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws ServletException, IOException {

		List<String> nomBatiments = getBuildings();

		request.setAttribute("nomsBatiments", nomBatiments);

		servletContext.getRequestDispatcher("/chooseBatiment.jsp").forward(request, response);
	}

	public void getPlans(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws ServletException, IOException {
		List<Batiment> batiments = batimentBdd.getAll();
		for (Batiment batiment : batiments) {
			if (batiment.getNomBatiment().equals(request.getParameter("nomBatiment"))) {
				List<String> nomsPlans = new ArrayList<>();
				for (Plan plan : batiment.getPlanList()) {
					nomsPlans.add(plan.getNom());
				}
				request.setAttribute("nomsPlans", nomsPlans);
				servletContext.getRequestDispatcher("/choosePlan.jsp").forward(request, response);
				return;
			}
		}
	}

	public void uploadPlanGetBuildings(HttpServletRequest request, HttpServletResponse response,
			ServletContext servletContext) throws ServletException, IOException {
		List<String> nomBatiments = getBuildings();
		request.setAttribute("nomsBatiments", nomBatiments);

		servletContext.getRequestDispatcher("/uploadPlan.jsp").forward(request, response);
	}

	public void uploadPlans(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
			throws IOException, ServletException {
		try {
			Plan plan = new Plan();
			plan.setNom(request.getParameter("planName"));
			plan.setLargeur(Float.parseFloat(request.getParameter("largeur")));
			plan.setHauteur(Float.parseFloat(request.getParameter("hauteur")));

			Part filePart = request.getPart("file");
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

			File file = new File(new File(prop.getProperty("uploadedFiles.address")), fileName);
			new File(prop.getProperty("uploadedFiles.address")).mkdirs();

			try (InputStream input = filePart.getInputStream()) {
				Files.copy(input, file.toPath());
			}
			plan.setPath(prop.getProperty("uploadedFiles.address") + fileName);
			Batiment batiment = batimentBdd.get(request.getParameter("nomBatiment"));
			batiment.addPlan(plan);
			batimentBdd.save(batiment);

			this.successRedirect("Plan cree", "/loginSuccess.jsp", servletContext, request, response);
		} catch (Exception e) {
			this.errorRedirect("Plan n'etait pas cree", "/loginSuccess.jsp", servletContext, request, response);
		}
	}

}
