package com.project.java.seating.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.java.seating.bdd.BatimentBdd;
import com.project.java.seating.model.Batiment;
import com.project.java.seating.model.Plan;

/**
 * Servlet implementation class UploadPlanServlet
 */
@MultipartConfig
@WebServlet(name = "uploadPlan", urlPatterns = { "/uploadPlan" })
public class UploadPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BatimentBdd batimentBdd;
	private ApplicationContext ac;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadPlanServlet() {
		super();
		ac = new ClassPathXmlApplicationContext("beans.xml");
		batimentBdd = (BatimentBdd) ac.getBean("batimentBdd");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Batiment> batiments = batimentBdd.getAll();
		List<String> nomBatiments = new ArrayList<>();
		for (Batiment batiment : batiments)
			nomBatiments.add(batiment.getNomBatiment());
		request.setAttribute("nomsBatiments", nomBatiments);

		this.getServletContext().getRequestDispatcher("/uploadPlan.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Plan plan = new Plan();
		plan.setNom(request.getParameter("planName"));
		plan.setLargeur(Float.parseFloat(request.getParameter("largeur")));
		plan.setHauteur(Float.parseFloat(request.getParameter("hauteur")));

		Part filePart = request.getPart("file"); // Retrieves <input type="file"
													// name="file">
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE
																								// fix.

		File file = new File(new File("C:/Users/Public/plans/"), fileName);
		new File("C:/Users/Public/plans").mkdirs();

		try (InputStream input = filePart.getInputStream()) {
			Files.copy(input, file.toPath());
		}
		plan.setPath("C:/Users/Public/plans/" + fileName);
		Batiment batiment = batimentBdd.get(request.getParameter("nomBatiment"));
		batiment.addPlan(plan);
		batimentBdd.save(batiment);

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/loginSuccess.jsp");
		PrintWriter out = response.getWriter();
		out.println("<font color=green>MESSAGE : Plan cree</font>");
		rd.include(request, response);
	}

}
