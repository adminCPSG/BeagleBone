package com.cpsgateway.ladderprog;


import java.io.IOException;
import java.io.PrintWriter;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cpsgateway.localio.LocalIOInit;

/**
 * Servlet implementation class LadderReqRes
 */
@WebServlet("/LadderReqRes")
public class StatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated constructor stub
		String ldrs = request.getParameter("name");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println(ldrs);
		String title="LadderResponse";
		String docType =
			      "<!doctype html public \"-//w3c//dtd html 4.0 " +
			      "transitional//en\">\n";
			      out.println(docType +
			        "<html>\n" +
			        "<head><title>" + title + "</title></head>\n"+
			        "<body bgcolor=\"#f0f0f0\">\n" +
			        "<h1 align=\"center\">" + title + "</h1>\n" +
			        "<p>The ladder Status is: " +
			        String.valueOf(LocalIOInit.localDIPorts.get(0).currentValue) + "</p>\n");
		// TODO Auto-generated method stub
	}
}
