package com.project.java.seating.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
import com.project.java.seating.services.ShowSeatingPlanService;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Batiment> batiments = batimentBdd.getAll();
		List<String> nomBatiments = new ArrayList<>();
		for(Batiment batiment : batiments)
			nomBatiments.add(batiment.getNomBatiment());
		request.setAttribute( "nomsBatiments", nomBatiments );

	    this.getServletContext().getRequestDispatcher( "/uploadPlan.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	    
	    File file = new File(new File("C:/Users/Public/plans"), fileName);

	    try (InputStream input = filePart.getInputStream()) {
	        Files.copy(input, file.toPath());
	    }
	    response.getWriter().append("Reussi à télécharger ").append(fileName);
	}

}
