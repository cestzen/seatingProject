package com.project.java.seating.services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * General class that provides services for redirections
 * 
 * @author cestzen
 *
 */
public abstract class GeneralServletService {

	public GeneralServletService() {
	}

	/**
	 * redirects to a given url with the given error
	 * 
	 * @param errorString
	 * @param redirectUrl
	 * @param servletContext
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void errorRedirect(String errorString, String redirectUrl, ServletContext servletContext,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd = servletContext.getRequestDispatcher(redirectUrl);
		PrintWriter out = response.getWriter();
		out.println("<font color=red>MESSAGE : " + errorString + "</font>");
		rd.include(request, response);
	}

	public void successRedirect(String successString, String redirectUrl, ServletContext servletContext,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd = servletContext.getRequestDispatcher(redirectUrl);
		PrintWriter out = response.getWriter();
		out.println("<font color=green>MESSAGE : " + successString + "</font>");
		rd.include(request, response);
	}
}
